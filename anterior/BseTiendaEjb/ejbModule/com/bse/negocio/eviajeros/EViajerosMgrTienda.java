package com.bse.negocio.eviajeros;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bse.accesodatos.esoa.CuotaPagoTienda;
import com.bse.accesodatos.esoa.MonedaTienda;
import com.bse.accesodatos.esoa.PlanCoberturaTienda;
import com.bse.accesodatos.esoa.PlanPagoTienda;
import com.bse.accesodatos.esoa.ProductoTienda;
import com.bse.accesodatos.esoa.RamoTienda;
import com.bse.accesodatos.eviajeros.CoberturaMuerteTienda;
import com.bse.accesodatos.eviajeros.CoberturaPrexistentesTienda;
import com.bse.accesodatos.eviajeros.CotizacionViajerosTienda;
import com.bse.accesodatos.eviajeros.PolizaViajerosTienda;
import com.bse.accesodatos.eviajeros.ViajeroTienda;
import com.bse.negocio.FabricaNegocioTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import com.bse.negocio.comun.CodigosErrorTienda;
import com.bse.negocio.comun.CodigosTienda;
import com.bse.negocio.comun.IEComunTienda;
import com.bse.servicios.utilitario.log.Logueo;


public class EViajerosMgrTienda implements IEViajerosTienda{

    private static final Logger logger = LogManager.getLogger(EViajerosMgrTienda.class);

    /**
     * CONSTRUCTOR
     */
    private EViajerosMgrTienda() {
    }

    /**
     *
     * @return
     */
    public static IEViajerosTienda getEViajerosMgr() {
        return new EViajerosMgrTienda();
    }



    /**
     *
     * @param em
     * @param planCobertura
     * @param planPago
     * @param fechaDesde
     * @param fechaHasta
     * @param listaPersonas
     * @param extension
     * @param fechaSalidaPais
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    @Override
    public CotizacionViajerosTienda cotizarViajerosAnonimo( EntityManager em,
                                                            String planCobertura,
                                                            int    planPago,
                                                            Date   fechaDesde,
                                                            Date   fechaHasta,
                                                            ArrayList<ViajeroTienda> listaPersonas,
                                                            String extension,
                                                            Date   fechaSalidaPais ) throws Exception, BSEExceptionTienda {

        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        long tiempoInicioAux = 0;
        long tiempoTotalAux  = 0;
        String logEncabezado = "VIAJEROS - cotizarViajeros ("+Long.toString(tiempoInicioMetodo) + ") - COTVIAJEROS";

        // LOGUEO PARAMETROS
        logCotizarViajerosParametros( logEncabezado + " - INICIO",
                                      planCobertura, planPago , fechaDesde     , fechaHasta,
                                      listaPersonas, extension, fechaSalidaPais);

        IEComunTienda eComunManager = FabricaNegocioTienda.getFabricaNegocio().getEComunMgr();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CONTROL de PARAMETROS - OBLIGATORIEDAD - VALORES

        // Valida Plan de Pago permitidos segun configuracion
        String planesPago = CodigosTienda.getCodigos().getPlanesPagoViajeros(em);
        logger.info(logEncabezado + " - Config - Planes de Pagos validos [" + planesPago + "]");

        String[] planesVec = planesPago.split(",");
        boolean planValido = false;
        for(int i = 0; i < planesVec.length; i++){
            if (planesVec[i].equals(String.valueOf(planPago))){
                planValido = true;
                i = planesVec.length;
            }
        }
        if (!planValido) {
            throw new BSEExceptionTienda(CodigosErrorTienda.plan_pago_invalido);
        }

        if ((planCobertura == null) || planCobertura.trim().equals("")) {
            throw new BSEExceptionTienda(CodigosErrorTienda.plan_cobertura_invalido);
        }
        if ((fechaDesde == null) || (fechaHasta == null)) {
            throw new BSEExceptionTienda(CodigosErrorTienda.fechas_invalidas);
        }
        if (!fechaHasta.after(fechaDesde)) {
            throw new BSEExceptionTienda(CodigosErrorTienda.fechas_invalidas);
        }

        // valido fechas
        Date ayer = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ayer);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        ayer = calendar.getTime();
        if (!fechaDesde.after(ayer)) {
            throw new BSEExceptionTienda(CodigosErrorTienda.fechas_invalidas);
        }

        Calendar cDesde = Calendar.getInstance();
        Calendar cHasta = Calendar.getInstance();
        cDesde.setTime(fechaDesde);
        cHasta.setTime(fechaHasta);
        int daysBetween = (int) ((fechaHasta.getTime() - fechaDesde.getTime()) / (1000 * 60 * 60 * 24));

        daysBetween++;
        if (daysBetween > 366) {
            throw new BSEExceptionTienda(CodigosErrorTienda.fechas_invalidas);
        }

        if ((listaPersonas == null) || (listaPersonas.size() == 0)) {
            throw new BSEExceptionTienda(CodigosErrorTienda.lista_personas_invalida);
        }

        if ((extension == null) || extension.trim().equals("")) {
            extension = "N";
        }
        if (extension.equals("N")) {
            fechaSalidaPais = fechaDesde;
        }

        // valido plan de pago
        PlanPagoTienda planPagoObj = null;
        try {
            planPagoObj = eComunManager.consultaPlanPago(em, planPago);
        } catch (Exception e) {
            throw new BSEExceptionTienda(CodigosErrorTienda.plan_pago_invalido);
        }
        logger.info(logEncabezado + " - PARAMETROS - OK");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CARGA de DATOS desde CONFIGURACION para INVOCACION PL
        String tipoDocumento    = " ";
        String documento        = " ";
        int    sucursal         = Integer.parseInt(CodigosTienda.getCodigos().getSucursalEmisionViajeros(em));
        int    ramoViajeros     = Integer.parseInt(CodigosTienda.getCodigos().getRamoEmisionViajeros(em));
        String productoViajeros = CodigosTienda.getCodigos().getProductoEmisionViajeros(em);
        String productor        = CodigosTienda.getCodigos().getProductorEmisionViajeros(em);

        String moneda           = CodigosTienda.getCodigos().getMonedaEmisionViajeros(em);
        String tipoCalculo      = CodigosTienda.getCodigos().getTipoCalculoEmisionViajeros(em).trim();
        String formaPago        = CodigosTienda.getCodigos().getFormaPagoEmisionViajeros(em).trim();
        String vigTecnica       = CodigosTienda.getCodigos().getVigTecnicaEmisionViajeros(em).trim();
        String medioPago        = CodigosTienda.getCodigos().getMedioPagoEmisionViajeros(em).trim();
        String origenPago       = CodigosTienda.getCodigos().getOrigenPagoEmisionViajeros(em).trim();
        String tipoFact         = CodigosTienda.getCodigos().getTipoFactEmisionViajeros(em).trim();
        String promocion        = CodigosTienda.getCodigos().getPromocionEmisionViajeros(em).trim();
        String renovacion       = CodigosTienda.getCodigos().getRenovacionEmisionViajeros(em).trim();
        String usuarioWeb       = CodigosTienda.getCodigos().getUsuarioWebEmisionViajeros(em).trim();

        String  consumidorFinal = "S";
        String  factMail        = "N";
        String  coaseguro       = "N";
        String  emitir          = "N";
        logger.info(logEncabezado + " - DATOS desde CONFIGURACION - OK");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        // valido lista de viajeros
        String slistaPersonas = ""; //"43,gmuller2004@gmail.com,20000; 20,pedro@gmail.com,20000;";
        for(int i = 0; i < listaPersonas.size(); i++){
            if ((listaPersonas.get(i).getCapitalMuerte() == null) || (listaPersonas.get(i).getCapitalMuerte().floatValue() <= 0)) {
                throw new BSEExceptionTienda(CodigosErrorTienda.capital_muerte_invalido);
            }
            if ((listaPersonas.get(i).getCoberturaPrexistentes() == null) || (listaPersonas.get(i).getCoberturaPrexistentes().floatValue() <= 0)) {
                throw new BSEExceptionTienda(CodigosErrorTienda.capital_cobertura_prexistentes_invalido);
            }

            String coberturaCovid = "N,N";
            if (listaPersonas.get(i).getCoberturaCovid() != null){
                if (listaPersonas.get(i).getCoberturaCovid().equals(ViajeroTienda.COVID_COBERTURA_PLUS)) {
                    coberturaCovid = "N,S";
                }
                if (listaPersonas.get(i).getCoberturaCovid().equals(ViajeroTienda.COVID_COBERTURA_BASICA)) {
                    coberturaCovid = "S,N";
                }
            }

            slistaPersonas += "20" + "," + "pp@gmail.com" + "," + // edad, email, cap muerte, cob preexistentes, covid, covid plus
                    listaPersonas.get(i).getCapitalMuerte() + "," + listaPersonas.get(i).getCoberturaPrexistentes()
                    + "," + coberturaCovid + ";";
        }

        // valido plan de cobertura
        boolean planOk = false;
        List<PlanCoberturaTienda> planesCobertura = eComunManager.consultaPlanesCobertura(em, ramoViajeros, productoViajeros);
        if ((planesCobertura != null) && (planesCobertura.size() > 0)) {
            for (int i = 0; i < planesCobertura.size(); i++) {
                PlanCoberturaTienda pc = planesCobertura.get(i);
                if (pc.getPlan().equals(planCobertura)){
                    planOk = true;
                }
            }
        }
        if (!planOk)
         {
            throw new BSEExceptionTienda(CodigosErrorTienda.plan_cobertura_invalido);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        }

        // LOGUEO PARAMETROS de la Invocaci�n PL
        logGuiones(logEncabezado);
        logCotizarViajerosPl2( logEncabezado  , "PACK_EMI_MIBSE.PRO_COTIZAR_VIAJEROS",
                               tipoDocumento  , documento  , sucursal  , ramoViajeros , productoViajeros,
                               productor      , fechaDesde , fechaHasta, planCobertura, planPago        ,
                               moneda         , tipoCalculo, formaPago , vigTecnica   , medioPago       ,
                               origenPago     , tipoFact   , promocion , renovacion   , usuarioWeb      ,
                               consumidorFinal, factMail   , coaseguro , emitir       , slistaPersonas  ,
                               extension      , fechaSalidaPais);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // INVOCACION PL - PACK_EMI_MIBSE.PRO_COTIZAR_VIAJEROS
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Query q = em.createNamedQuery("PRO_COTIZAR_VIAJEROS");

        q.setParameter("P_TP_DOCUMENTO"     , tipoDocumento);
        q.setParameter("P_NRO_DOCUMENTO"    , documento);
        q.setParameter("P_SUCURSAL"         , sucursal);
        q.setParameter("P_RAMO"             , ramoViajeros);
        q.setParameter("P_PRODUCTO"         , productoViajeros);
        q.setParameter("P_PRODUCTOR"        , productor);
        q.setParameter("P_FECHA_DESDE"      , fechaDesde);
        q.setParameter("P_FECHA_HASTA"      , fechaHasta);
        q.setParameter("P_PLAN_COBERTURA"   , planCobertura);
        q.setParameter("P_PLAN_PAGO"        , planPago);
        q.setParameter("P_MONEDA"           , moneda);
        q.setParameter("P_TIPO_CALCULO"     , tipoCalculo);
        q.setParameter("P_FORMA_PAGO"       , formaPago);
        q.setParameter("P_VIG_TECNICA"      , vigTecnica);
        q.setParameter("P_MEDIO_PAGO"       , medioPago);
        q.setParameter("P_ORIGEN_PAGO"      , origenPago);
        q.setParameter("P_TIPO_FACT"        , tipoFact);
        q.setParameter("P_PROMOCION"        , promocion);
        q.setParameter("P_RENOVACION"       , renovacion);
        q.setParameter("P_USUARIO_WEB"      , usuarioWeb);
        q.setParameter("P_CONSUMIDOR_FINAL" , consumidorFinal);
        q.setParameter("P_FACT_MAIL"        , factMail);
        q.setParameter("P_COASEGURO"        , coaseguro);
        q.setParameter("P_EMITIR"           , emitir);
        q.setParameter("P_LISTA_PERSONAS"   , slistaPersonas);
        q.setParameter("P_EXTENSION"        , extension);
        q.setParameter("P_FECHA_SALIDA_PAIS", fechaSalidaPais);

        logger.info(logEncabezado + " - PRE  - INVOCACION PACK_EMI_MIBSE - OK");
        tiempoInicioAux = System.currentTimeMillis();

        @SuppressWarnings("unchecked")
        List<Object[]> l = q.getResultList();
        Object[] r = l.get(0);

        tiempoTotalAux = System.currentTimeMillis() - tiempoInicioAux;
        logger.info(logEncabezado + " - POST - INVOCACION PACK_EMI_MIBSE - OK");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // LOGUEO de RESULTADO obtenidos
        //      -> R[0] = P_COTIZACION_EMITIDA          - type=Long.class
        //      -> R[1] = P_PREMIO_COTIZACION           - type=Float.class
        //      -> R[2] = P_PREMIO_COTIZACION_FACTURAR  - type=Float.class
        //      -> R[3] = P_CUOTAS                      - type=String.class
        //      -> R[4] = P_LISTA_PERSONAS              - type=String.class
        //      -> R[5] = P_RESULTADO                   - type=String.class
        //      -> R[6] = P_MENSAJE_EMISION             - type=String.class
        for (int i = 0; i < r.length; i++){
            if (r[i] != null) { logger.info(logEncabezado + " - " + logCotizaResult(i) + " = [" + r[i].toString() + "]");
            } else            { logger.info(logEncabezado + " - " + logCotizaResult(i) + " = [null]"); }
        }
        logger.info(logEncabezado + " - Tiempo Ejecucion PL ["+Long.toString(tiempoTotalAux)+"] milisegundos");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // En caso de ERROR, se retorna el error especificado por el PL
        if ((r[5] != null) && !((String)r[5]).equals("0")) {
            String codError  = (String)r[5];
            String descError = (r[6]!=null)?((String)r[6]):"";
            BSEExceptionTienda exc = new BSEExceptionTienda(CodigosErrorTienda.error_cotizacion_rector, descError);

            String aux = "------------------------------------------------------------";
            logger.info(logEncabezado + " - " + aux + "ERROR_TIENDA" + aux);
            logger.info(logEncabezado +  " - ERROR: COD_PL [" + codError
                                      + "] - DESC_ERROR_PL [" + descError + "]");
            logger.info(logEncabezado + " - " + aux + "------------" + aux);

            tiempoTotalMetodo = System.currentTimeMillis() - tiempoInicioMetodo;
            logger.info(logEncabezado+" - FIN - Tiempo Ejecucion METODO ["+Long.toString(tiempoTotalMetodo)+"] milisegundos");
            logGuiones(logEncabezado);

            throw exc;
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        logGuiones(logEncabezado);
        logger.info(logEncabezado + " - COTIZACION RECTOR - OK");

        // armo retorno
        CotizacionViajerosTienda cotizacion = new CotizacionViajerosTienda();

        RamoTienda ramo = em.find(RamoTienda.class, Integer.valueOf(ramoViajeros));
        cotizacion.setRamo(ramo);

        ProductoTienda productoObj = em.find(ProductoTienda.class, productoViajeros);
        cotizacion.setProducto(productoObj);

        cotizacion.setSucursal(sucursal);

        cotizacion.setNroCotizacion(0);
        if (r[0] != null) {
            cotizacion.setNroCotizacion(((Long)r[0]).intValue());
        }

        cotizacion.setTipoDocumento("");
        cotizacion.setNroDocumento("");
        cotizacion.setProductor(1);

        MonedaTienda monedaObj = eComunManager.consultaMoneda(em, moneda);
        cotizacion.setMoneda(monedaObj);

        cotizacion.setPremio(0);
        if (r[1] != null) {
            cotizacion.setPremio(((Double)r[1]).doubleValue());
        }

        cotizacion.setPremioFacturar(0);
        if (r[2] != null) {
            cotizacion.setPremioFacturar(((Double)r[2]).doubleValue());
        }

        planPagoObj = eComunManager.consultaPlanPago(em, planPago); // .setCodigo(planPago);
        cotizacion.setPlanPago(planPagoObj);

        PlanCoberturaTienda planCoberturaObj = eComunManager.consultaPlanCobertura(em, planCobertura);
        cotizacion.setPlanCobertura(planCoberturaObj);

        String cuotasStr = (String)r[3];
        String[] vecCuotas = cuotasStr.split(";");

        logger.info(logEncabezado + " - PRE  - CUOTAS - OK");
        ArrayList<CuotaPagoTienda> cuotas = new ArrayList<CuotaPagoTienda>();
        for(int i = 0; i < vecCuotas.length; i++){
            CuotaPagoTienda cuota = new CuotaPagoTienda();
            cuota.setNroCuota(Integer.parseInt(vecCuotas[i].split(":")[0]));
            cuota.setImporte(Double.parseDouble(vecCuotas[i].split(":")[1]));
            cuotas.add(cuota);
        }
        cotizacion.setCuotas(cuotas);
        logger.info(logEncabezado + " - POST - CUOTAS - OK - " + Integer.toString(cuotas.size()) + " cuotas.");

        cotizacion.setFechaDesde(fechaDesde);
        cotizacion.setFechaHasta(fechaHasta);

        logger.info(logEncabezado + " - PRE  - LISTA de VIAJEROS - OK");

        Float tasaMsp = Float.valueOf(CodigosTienda.getCodigos().getTasaMsp(em)); // 1.02F;

        String sql = "select CAZB_NU_CONSECUTIVO, CAZB_MT_PREMIO " +
                     "from cart_cotiza_planes " +
                     "where cazb_capj_cd_sucursal = ? and cazb_nu_cotizacion = ? and cazb_nu_consecutivo > 0";

        Query query = em.createNativeQuery(sql);
        query.setParameter(1, CodigosTienda.getCodigos().getSucursalEmisionViajeros(em));
        query.setParameter(2, cotizacion.getNroCotizacion());
        @SuppressWarnings("unchecked")
        List<Object[]> resultPlanes = query.getResultList();

        ArrayList<ViajeroTienda> viajeros = new ArrayList<ViajeroTienda>();
        for(int i = 0; i < listaPersonas.size(); i++){
            ViajeroTienda viajero2 = new ViajeroTienda();

            viajero2.setCapitalMuerte(listaPersonas.get(i).getCapitalMuerte());
            viajero2.setCoberturaPrexistentes(listaPersonas.get(i).getCoberturaPrexistentes());
            viajero2.setCoberturaCovid(listaPersonas.get(i).getCoberturaCovid());
            viajero2.setMoneda(monedaObj);

            for (int x = 0; x < resultPlanes.size(); x++) {
                Object[] rPlanes = resultPlanes.get(x);

                int consecutivo = ((BigDecimal)rPlanes[0]).intValue();
                Float premio = ((BigDecimal)rPlanes[1]).floatValue();

                if (consecutivo == (i+1)) {
                    viajero2.setPremio(premio * tasaMsp);
                }
            }

            viajeros.add(viajero2);
        }
        cotizacion.setListaViajeros(viajeros);
        logger.info(logEncabezado + " - POST - LISTA de VIAJEROS - OK");

        cotizacion.setExtension(extension);
        cotizacion.setFechaSalidaPais(fechaSalidaPais);

        logger.info(logEncabezado + " - RESULT : " + cotizacion.toString());
        tiempoTotalMetodo = System.currentTimeMillis() - tiempoInicioMetodo;
        logger.info(logEncabezado + " - FIN - Tiempo Ejecucion METODO [" + Long.toString(tiempoTotalMetodo)+"] milisegundos");
        return cotizacion;
    }



    /**
     *
     * @param em
     * @param tipoDocumentoContratante
     * @param documentoContratante
     * @param listaPersonas
     * @param nroCotizacion
     * @param consumoFinal
     * @param extension
     * @param fechaSalidaPais
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    @Override
    public PolizaViajerosTienda emitirViajeros( EntityManager em,
                                                String tipoDocumentoContratante,
                                                String documentoContratante,
                                                ArrayList<ViajeroTienda> listaPersonas,
                                                long   nroCotizacion,
                                                String consumoFinal,
                                                String extension,
                                                Date   fechaSalidaPais ) throws Exception, BSEExceptionTienda{

        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        long tiempoInicioAux = 0;
        long tiempoTotalAux  = 0;
        String logEncabezado = "VIAJEROS - emitirViajeros (" + Long.toString(tiempoInicioMetodo) + ") - EMIVIAJEROS";

        // LOGUEO PARAMETROS
        logEmitirViajerosParametros( logEncabezado + " - INICIO",
                                     tipoDocumentoContratante, documentoContratante, listaPersonas  , nroCotizacion,
                                     consumoFinal            , extension           , fechaSalidaPais);

        IEComunTienda eComunManager = FabricaNegocioTienda.getFabricaNegocio().getEComunMgr();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CARGA de DATOS desde la CONFIGURACION
        int    sucursal         = Integer.parseInt(CodigosTienda.getCodigos().getSucursalEmisionViajeros(em));
        int    ramoViajeros     = Integer.parseInt(CodigosTienda.getCodigos().getRamoEmisionViajeros(em));
        String productoViajeros = CodigosTienda.getCodigos().getProductoEmisionViajeros(em);
        String productor        = CodigosTienda.getCodigos().getProductorEmisionViajeros(em);
        String moneda           = CodigosTienda.getCodigos().getMonedaEmisionViajeros(em);
        logger.info(logEncabezado + " - DATOS desde CONFIGURACION - OK");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Valida Nro. Cotizacion
        if (nroCotizacion == 0) {
            throw new BSEExceptionTienda(CodigosErrorTienda.cotizacion_invalida);
        }

        String sqlCot = "select * " +
                        "from cart_cotiza_banco " +
                        "where cazb_capj_cd_sucursal = ? and cazb_nu_cotizacion = ? and " +
                              "cazb_nu_consecutivo = 0 and cazb_carp_cd_ramo = ? and cazb_capu_cd_producto = ? and cazb_capo_nu_poliza is null";

        Query queryCot = em.createNativeQuery(sqlCot);
        queryCot.setParameter(1, sucursal);
        queryCot.setParameter(2, nroCotizacion);
        queryCot.setParameter(3, ramoViajeros);
        queryCot.setParameter(4, productoViajeros);
        @SuppressWarnings("unchecked")
        List<Object[]> resultCot = queryCot.getResultList();

        if ((resultCot == null) || (resultCot.size() == 0)) {
            throw new BSEExceptionTienda(CodigosErrorTienda.cotizacion_invalida);
        }
        logger.info(logEncabezado + " - NRO. COTIZACION - OK");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CONTROL de PARAMETROS - OBLIGATORIEDAD
        if ((listaPersonas == null) || (listaPersonas.size() == 0)) {
            throw new BSEExceptionTienda(CodigosErrorTienda.lista_personas_invalida);
        }

        if ((consumoFinal == null) || (!consumoFinal.trim().equals("S") && !consumoFinal.trim().equals("N"))) {
            throw new BSEExceptionTienda(CodigosErrorTienda.consumidor_final_invalido);
        }

        if ((extension == null) || extension.trim().equals("")) {
            extension = "N";
        }
        if (extension.equals("N") && (fechaSalidaPais  == null)) {
            fechaSalidaPais = new Date();
        }

        String sqlCot2 = "select max(cazb_nu_consecutivo) from cart_cotiza_banco " +
                         "where cazb_capj_cd_sucursal = ? and cazb_nu_cotizacion = ? and " +
                               "cazb_carp_cd_ramo = ? and cazb_capu_cd_producto = ? and cazb_capo_nu_poliza is null";
        Query queryCot2 = em.createNativeQuery(sqlCot2);
        queryCot2.setParameter(1, sucursal);
        queryCot2.setParameter(2, nroCotizacion);
        queryCot2.setParameter(3, ramoViajeros);
        queryCot2.setParameter(4, productoViajeros);
        Object res = queryCot2.getSingleResult();
        BigDecimal qty = (BigDecimal)res;
        if (listaPersonas.size() != qty.intValue()) {
            throw new BSEExceptionTienda(CodigosErrorTienda.lista_personas_invalida);
        }

        String regexEmail = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regexEmail);

        String slistaPersonas = "";
        Hashtable<String,String> control = new Hashtable<String,String>();

        for(int i = 0; i < listaPersonas.size(); i++){
            ViajeroTienda viajero = listaPersonas.get(i);

            if ((viajero.getEdad() < 0) || (viajero.getEdad() > 140)) {
                throw new BSEExceptionTienda(CodigosErrorTienda.edad_invalida);
            }

            if ((viajero.getEmail() == null) || viajero.getEmail().trim().equals("")) {
                throw new BSEExceptionTienda(CodigosErrorTienda.email_invalido);
            }

            Matcher matcher = pattern.matcher(viajero.getEmail());
            if (!matcher.matches()) {
                throw new BSEExceptionTienda(CodigosErrorTienda.email_invalido);
            }

            if ((viajero.getTipoDocumento() == null) || viajero.getTipoDocumento().trim().equals("")) {
                throw new BSEExceptionTienda(CodigosErrorTienda.tipo_documento_invalido);
            }
            if ((viajero.getDocumento() == null) || viajero.getDocumento().trim().equals("")) {
                throw new BSEExceptionTienda(CodigosErrorTienda.documento_invalido);
            }
            if ((viajero.getCapitalMuerte() == null) || (viajero.getCapitalMuerte().floatValue() <= 0)) {
                throw new BSEExceptionTienda(CodigosErrorTienda.capital_muerte_invalido);
            }
            if ((viajero.getCoberturaPrexistentes() == null) || (viajero.getCoberturaPrexistentes().floatValue() <= 0)) {
                throw new BSEExceptionTienda(CodigosErrorTienda.capital_cobertura_prexistentes_invalido);
            }

            String key = viajero.getTipoDocumento() + "-" + viajero.getDocumento();
            if (!control.containsKey(key)) {
                control.put(key, key);
            } else {
                throw new BSEExceptionTienda(CodigosErrorTienda.lista_personas_invalida);
            }

            String nombre = " ";
            if ((viajero.getNombre() != null) && !viajero.getNombre().equals("")) {
                nombre = viajero.getNombre();
            }

            String apellido = " ";
            if ((viajero.getApellido() != null) && !viajero.getApellido().equals("")) {
                apellido = viajero.getApellido();
            }

            String coberturaCovid = "N,N";
            if (listaPersonas.get(i).getCoberturaCovid() != null){
                if (listaPersonas.get(i).getCoberturaCovid().equals(ViajeroTienda.COVID_COBERTURA_PLUS)) {
                    coberturaCovid = "N,S";
                }
                if (listaPersonas.get(i).getCoberturaCovid().equals(ViajeroTienda.COVID_COBERTURA_BASICA)) {
                    coberturaCovid = "S,N";
                }
            }

            slistaPersonas += viajero.getEdad() + ","
                            + viajero.getEmail() + ","
                            + viajero.getCapitalMuerte() + ","
                            + viajero.getCoberturaPrexistentes() + ","
                            + viajero.getTipoDocumento() + ","
                            + viajero.getDocumento() + ","
                            + nombre + ","
                            + apellido + ","
                            + coberturaCovid + ";";
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // LOGUEO PARAMETROS de la Invocaci�n PL
        logGuiones(logEncabezado);
        logEmitirViajerosPl2( logEncabezado, "PACK_EMI_MIBSE.PRO_EMITIR_VIAJEROS",
                              tipoDocumentoContratante, documentoContratante, sucursal    , ramoViajeros  ,
                              productoViajeros        , nroCotizacion       , consumoFinal, slistaPersonas,
                              extension               , fechaSalidaPais );

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // INVOCACION PL - PACK_EMI_MIBSE.PRO_EMITIR_VIAJEROS
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Query q = em.createNamedQuery("PRO_EMITIR_VIAJEROS");

        q.setParameter("P_TP_DOCUMENTO_CONTRATANTE" , tipoDocumentoContratante);
        q.setParameter("P_NRO_DOCUMENTO_CONTRATANTE", documentoContratante);
        q.setParameter("P_SUCURSAL"                 , String.valueOf(sucursal));
        q.setParameter("P_RAMO"                     , String.valueOf(ramoViajeros));
        q.setParameter("P_PRODUCTO"                 , productoViajeros);
        q.setParameter("P_COTIZACION_EMITIDA"       , nroCotizacion);
        q.setParameter("P_CONSUMIDOR_FINAL"         , consumoFinal);
        q.setParameter("P_LISTA_PERSONAS"           , slistaPersonas);
        q.setParameter("P_EXTENSION"                , extension);
        q.setParameter("P_FECHA_SALIDA_PAIS"        , fechaSalidaPais);

        logger.info(logEncabezado + " - PRE  - INVOCACION PACK_EMI_MIBSE - OK");
        tiempoInicioAux = System.currentTimeMillis();

        @SuppressWarnings("unchecked")
        List<Object[]> l = q.getResultList();
        Object[] r = l.get(0);

        tiempoTotalAux = System.currentTimeMillis() - tiempoInicioAux;
        logger.info(logEncabezado + " - POST - INVOCACION PACK_EMI_MIBSE - OK");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // LOGUEO de RESULTADO obtenidos
        //      -> R[0]  = P_POLIZA_EMITIDA              - type=Integer.class
        //      -> R[1]  = P_PREMIO_COTIZACION           - type=Float.class
        //      -> R[2]  = P_PREMIO_COTIZACION_FACTURAR  - type=Float.class
        //      -> R[3]  = P_FECHA_DESDE                 - type=Date.class
        //      -> R[4]  = P_FECHA_HASTA                 - type=Date.class
        //      -> R[5]  = P_PLAN_COBERTURA              - type=String.class
        //      -> R[6]  = P_PLAN_PAGO                   - type=Integer.class
        //      -> R[7]  = P_CUOTAS                      - type=String.class
        //      -> R[8]  = P_RESULTADO                   - type=String.class
        //      -> R[9]  = P_MENSAJE_EMISION             - type=String.class
        for (int i = 0; i < r.length; i++){
            if (r[i] != null) { logger.info(logEncabezado + " - " + logEmiteResult(i) + " = [" + r[i].toString() + "]");
            } else            { logger.info(logEncabezado + " - " + logEmiteResult(i) + " = [null]"); }
        }
        logger.info(logEncabezado + " - Tiempo Ejecucion PL [" + Long.toString(tiempoTotalAux) + "] milisegundos");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // En caso de ERROR, se retorna el error especificado por el PL
        if (r[8] != null) {
            String codError  = (String)r[8];
            String descError = (r[9]!=null)?((String)r[9]):"";
            BSEExceptionTienda exc = new BSEExceptionTienda(CodigosErrorTienda.error_emision_rector, descError);

            String aux = "------------------------------------------------------------";
            logger.info(logEncabezado + " - " + aux + "ERROR_TIENDA" + aux);
            logger.info(logEncabezado +  " - ERROR: COD_PL [" + codError
                                      + "] - DESC_ERROR_PL [" + descError + "]");
            logger.info(logEncabezado + " - " + aux + "------------" + aux);

            tiempoTotalMetodo = System.currentTimeMillis() - tiempoInicioMetodo;
            logger.info(logEncabezado+" - FIN - Tiempo Ejecucion METODO ["+Long.toString(tiempoTotalMetodo)+"] milisegundos");
            logGuiones(logEncabezado);

            throw exc;
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        logGuiones(logEncabezado);
        logger.info(logEncabezado + " - EMISION RECTOR - OK");

        PolizaViajerosTienda ret = new PolizaViajerosTienda();

        RamoTienda ramo = em.find(RamoTienda.class, Integer.valueOf(ramoViajeros));
        ret.setRamo(ramo);

        ProductoTienda productoObj = em.find(ProductoTienda.class, productoViajeros);
        ret.setProducto(productoObj);
        ret.setSucursal(sucursal);
        ret.setNroCotizacion(nroCotizacion);

        ret.setTipoDocumento(listaPersonas.get(0).getTipoDocumento());
        ret.setNroDocumento(listaPersonas.get(0).getDocumento());
        ret.setProductor(Integer.parseInt(productor));

        MonedaTienda monedaObj = eComunManager.consultaMoneda(em, moneda);
        ret.setMoneda(monedaObj);

        double premio = ((Double)r[1]).doubleValue();
        double premioFacturar = ((Double)r[2]).doubleValue();

        ret.setTipoDocumento(tipoDocumentoContratante);
        ret.setNroDocumento(documentoContratante);
        ret.setPremio(premio);
        ret.setPremioFacturar(premioFacturar);
        ret.setFechaDesde((Date)r[3]);
        ret.setFechaHasta((Date)r[4]);
        ret.setNroPoliza(Integer.parseInt(r[0].toString()));

        int planPago = ((Integer)r[6]).intValue();
        PlanPagoTienda planPagoObj = eComunManager.consultaPlanPago(em, planPago);
        ret.setPlanPago(planPagoObj);

        String planCobertura =  (String)r[5];
        PlanCoberturaTienda planCoberturaObj = eComunManager.consultaPlanCobertura(em, planCobertura);
        ret.setPlanCobertura(planCoberturaObj);
        ret.setListaViajeros(listaPersonas);

        Float tasaMsp = Float.valueOf(CodigosTienda.getCodigos().getTasaMsp(em)); // 1.02F;

        String sql = "select CAZB_NU_CONSECUTIVO, CAZB_MT_PREMIO " +
                     "from cart_cotiza_planes " +
                     "where cazb_capj_cd_sucursal = ? and cazb_nu_cotizacion = ? and cazb_nu_consecutivo > 0";

        Query query = em.createNativeQuery(sql);
        query.setParameter(1, CodigosTienda.getCodigos().getSucursalEmisionViajeros(em));
        query.setParameter(2, nroCotizacion);
        @SuppressWarnings("unchecked")
        List<Object[]> resultPlanes = query.getResultList();

        for(int i = 0; i < listaPersonas.size(); i++){
            ViajeroTienda viajero2 = listaPersonas.get(i);

            for (int x = 0; x < resultPlanes.size(); x++) {
                Object[] rPlanes = resultPlanes.get(x);

                int consecutivoViajero = ((BigDecimal)rPlanes[0]).intValue();
                Float premioViajero = ((BigDecimal)rPlanes[1]).floatValue();

                if (consecutivoViajero == (i+1)) {
                    viajero2.setPremio(premioViajero * tasaMsp);
                }
            }
        }

        logger.info(logEncabezado + " - PRE  - CUOTAS - OK");
        String cuotas = (String)r[7];
        cuotas=cuotas.replace(" ", "");
        cuotas=cuotas.replace("\\13", "");
        cuotas=cuotas.replace("\\10", "");

        // String regex = "\b<cuota>\b";
        cuotas = cuotas.replace("<cuota>", "%");
        String[] vecCuotas = cuotas.split("%");

        ArrayList<CuotaPagoTienda> listaCuotas = new ArrayList<CuotaPagoTienda>();
        for(int z = 0; z < vecCuotas.length; z++){
            if ((vecCuotas[z] != null) && !vecCuotas[z].trim().equals("")){
                CuotaPagoTienda cuota = new CuotaPagoTienda();

                int inicio = vecCuotas[z].indexOf("<nrocuota>")+"<nrocuota>".length();
                int fin = vecCuotas[z].indexOf("</nrocuota>");

                int nroCuota = Integer.parseInt(vecCuotas[z].substring(inicio, fin));
                cuota.setNroCuota(nroCuota);

                inicio = vecCuotas[z].indexOf("<premiofacturacion>")+"<premiofacturacion>".length();
                fin = vecCuotas[z].indexOf("</premiofacturacion>");
                double importeCuota = Double.parseDouble(vecCuotas[z].substring(inicio, fin));

                cuota.setImporte(importeCuota);

                listaCuotas.add(cuota);
            }
        }
        ret.setCuotas(listaCuotas);
        logger.info(logEncabezado + " - POST - CUOTAS - OK - " + Integer.toString(listaCuotas.size()) + " cuotas.");

        ret.setExtension(extension);
        ret.setFechaSalidaPais(fechaSalidaPais);

        logger.info(logEncabezado + " - RESULT : " + ret.toString());
        tiempoTotalMetodo = System.currentTimeMillis() - tiempoInicioMetodo;
        logger.info(logEncabezado + " - FIN - Tiempo Ejecucion METODO [" + Long.toString(tiempoTotalMetodo)+"] milisegundos");
        return ret;
    }



    /**
     *
     * @param em
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    @Override
    public ArrayList<CoberturaPrexistentesTienda> consultaCoberturasPrexistentes(EntityManager em) throws Exception, BSEExceptionTienda {
        String logEncabezado = "VIAJEROS - consultaCoberturasPrexistentes";

        ArrayList<CoberturaPrexistentesTienda> lista = new ArrayList<CoberturaPrexistentesTienda>();
        String par = "B";

        logger.info(logEncabezado + " - ---------Comienzo Cob Prex---------------------");

        String sql = "select crtb_dato2, crtb_de_dato " +
                     "from cret_tablas " +
                     "where crtb_cd_tabla = 220217 and crtb_dato1 = ? " +
                     "order by to_number(trim(crtb_dato2))";

        Query query = em.createNativeQuery(sql);
        query.setParameter(1, par);
        @SuppressWarnings("unchecked")
        List<Object[]> result = query.getResultList();

        CoberturaPrexistentesTienda obj = new CoberturaPrexistentesTienda();
        obj.setCobertura(500); // parametrizar esto!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        obj.setDescCobertura("U$S 500 B�SICO");
        lista.add(obj);
        logger.info(logEncabezado + " - " + obj.toString());

        for (int x = 0; x < result.size(); x++) {
            Object[] r = result.get(x);

            float cobertura = Float.parseFloat((String)r[0]);
            String descCobertura = (String)r[1];

            obj = new CoberturaPrexistentesTienda();
            obj.setCobertura(cobertura);
            obj.setDescCobertura(descCobertura);
            lista.add(obj);
            logger.info(logEncabezado + " - " + obj.toString());
        }
        logger.info(logEncabezado + " - ---------------Fin Cob Prex--------------------");

        return lista;
    }



    /**
     *
     * @param em
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    @Override
    public ArrayList<CoberturaMuerteTienda> consultaCoberturasMuerte(EntityManager em) throws Exception, BSEExceptionTienda {
        String logEncabezado = "VIAJEROS - consultaCoberturasMuerte";

        ArrayList<CoberturaMuerteTienda> lista = new ArrayList<CoberturaMuerteTienda>();

        logger.info(logEncabezado + " - ---------Comienzo Cob x Muerte---------------------");

        String sql = "select crtb_dato1, crtb_de_dato " +
                     "from cret_tablas " +
                     "where crtb_cd_tabla = '220195'";

        Query query = em.createNativeQuery(sql);
        @SuppressWarnings("unchecked")
        List<Object[]> result = query.getResultList();

        for (int x = 0; x < result.size(); x++) {
            Object[] r = result.get(x);

            float cobertura = Float.parseFloat((String)r[0]);
            String descCobertura = (String)r[1];

            CoberturaMuerteTienda obj = new CoberturaMuerteTienda();
            obj.setCobertura(cobertura);
            obj.setDescCobertura(descCobertura);
            lista.add(obj);
            logger.info(logEncabezado + " - " + obj.toString());
        }
        logger.info(logEncabezado + " - ---------------Fin Cob x Muerte--------------------");

        return lista;
    }



    /**
     *
     * @param logEncabezado
     * @param planCobertura
     * @param planPago
     * @param fechaDesde
     * @param fechaHasta
     * @param listaPersonas
     * @param extension
     * @param fechaSalidaPais
     */
    private void logCotizarViajerosParametros( String logEncabezado,
                                               String planCobertura,
                                               int    planPago,
                                               Date   fechaDesde,
                                               Date   fechaHasta,
                                               ArrayList<ViajeroTienda> listaPersonas,
                                               String extension,
                                               Date   fechaSalidaPais ) {

        String auxListaPersonas = "";
        if        ( listaPersonas == null )  { auxListaPersonas = "ListaPersonas [null]";
        } else if ( listaPersonas.isEmpty()) { auxListaPersonas = "ListaPersonas [VACIA]";
        } else {
            auxListaPersonas = "ListaPersonas " + listaPersonas.toString();
        }

        String logParametros =   "PlanCobertura ["   + planCobertura
                             + "] PlanPago ["        + Integer.toString(planPago)
                             + "] FechaDesde ["      + ((fechaDesde!=null)?fechaDesde.toString():"null")
                             + "] FechaHasta ["      + ((fechaHasta!=null)?fechaHasta.toString():"null")
                             + "] "                  + auxListaPersonas
                             +  " Extension ["       + extension
                             + "] FechaSalidaPais [" + ((fechaSalidaPais!=null)?fechaSalidaPais.toString():"null")
                             + "]";
        logger.info(logEncabezado + " - Parametros -> " + logParametros);
    }

    /**
     *
     * @param logEncabezado
     * @param titulo
     * @param tipoDocumento
     * @param documento
     * @param sucursal
     * @param ramo
     * @param producto
     * @param productor
     * @param fechaDesde
     * @param fechaHasta
     * @param planCobertura
     * @param planPago
     * @param moneda
     * @param tipoCalculo
     * @param formaPago
     * @param vigTecnica
     * @param medioPago
     * @param origenPago
     * @param tipoFact
     * @param promocion
     * @param renovacion
     * @param usuarioWeb
     * @param consumidorFinal
     * @param factMail
     * @param coaseguro
     * @param emitir
     * @param slistaPersonas
     * @param extension
     * @param fechaSalidaPais
     */
    private void logCotizarViajerosPl2( String  logEncabezado , String titulo         ,
                                        String  tipoDocumento , String  documento     ,
                                        int     sucursal      , int     ramo          , String producto       ,
                                        String  productor     , Date    fechaDesde    , Date   fechaHasta     ,
                                        String  planCobertura , int     planPago      ,
                                        String  moneda        , String  tipoCalculo   ,
                                        String  formaPago     , String  vigTecnica    , String medioPago      ,
                                        String  origenPago    , String  tipoFact      , String promocion      ,
                                        String  renovacion    , String  usuarioWeb    , String consumidorFinal,
                                        String  factMail      , String  coaseguro     , String emitir         ,
                                        String  slistaPersonas, String  extension     , Date  fechaSalidaPais ) {
        Logueo logueo = new Logueo();
        int pad = 20;
        logueo.setEncabezado(titulo);
        logueo.setParametro("P_TP_DOCUMENTO"     , tipoDocumento             , pad);
        logueo.setParametro("P_NRO_DOCUMENTO"    , documento                 , pad);
        logueo.setParametro("P_SUCURSAL"         , Integer.toString(sucursal), pad);
        logueo.setParametro("P_RAMO"             , Integer.toString(ramo)    , pad);
        logueo.setParametro("P_PRODUCTO"         , producto                  , pad);
        logueo.setParametro("P_PRODUCTOR"        , productor                 , pad);
        logueo.setParametro("P_FECHA_DESDE"      , ((fechaDesde!=null)?fechaDesde.toString():"null"), pad);
        logueo.setParametro("P_FECHA_HASTA"      , ((fechaHasta!=null)?fechaHasta.toString():"null"), pad);
        logueo.setParametro("P_PLAN_COBERTURA"   , planCobertura             , pad);
        logueo.setParametro("P_PLAN_PAGO"        , Integer.toString(planPago), pad);
        logueo.setParametro("P_MONEDA"           , moneda                    , pad);
        logueo.setParametro("P_TIPO_CALCULO"     , tipoCalculo               , pad);
        logueo.setParametro("P_FORMA_PAGO"       , formaPago                 , pad);
        logueo.setParametro("P_VIG_TECNICA"      , vigTecnica                , pad);
        logueo.setParametro("P_MEDIO_PAGO"       , medioPago                 , pad);
        logueo.setParametro("P_ORIGEN_PAGO"      , origenPago                , pad);
        logueo.setParametro("P_TIPO_FACT"        , tipoFact                  , pad);
        logueo.setParametro("P_PROMOCION"        , promocion                 , pad);
        logueo.setParametro("P_RENOVACION"       , renovacion                , pad);
        logueo.setParametro("P_USUARIO_WEB"      , usuarioWeb                , pad);
        logueo.setParametro("P_CONSUMIDOR_FINAL" , consumidorFinal           , pad);
        logueo.setParametro("P_FACT_MAIL"        , factMail                  , pad);
        logueo.setParametro("P_COASEGURO"        , coaseguro                 , pad);
        logueo.setParametro("P_EMITIR"           , emitir                    , pad);
        logueo.setParametro("P_LISTA_PERSONAS"   , slistaPersonas            , pad);
        logueo.setParametro("P_EXTENSION"        , extension                 , pad);
        logueo.setParametro("P_FECHA_SALIDA_PAIS", ((fechaSalidaPais!=null)?fechaSalidaPais.toString():"null"), pad);
        logger.info(logEncabezado + " - " + logueo.getSoloParametros());
    }

    /**
     *
     * @param i
     * @return
     */
    private String logCotizaResult(int i) {
        int pad = 33;
        String result = "R[" + Integer.toString(i) + "]";
        switch (i) {
        case 0:
            result = "R[0]=P_COTIZACION_EMITIDA";
            break;
        case 1:
            result = "R[1]=P_PREMIO_COTIZACION";
            break;
        case 2:
            result = "R[2]=P_PREMIO_COTIZACION_FACTURAR";
            break;
        case 3:
            result = "R[3]=P_CUOTAS";
            break;
        case 4:
            result = "R[4]=P_LISTA_PERSONAS";
            break;
        case 5:
            result = "R[5]=P_RESULTADO";
            break;
        case 6:
            result = "R[6]=P_MENSAJE_EMISION";
            break;

        default:
            break;
        }
        result = String.format("%-"+Integer.toString(pad)+"s", result);
        return result;
    }



    /**
     *
     * @param logEncabezado
     * @param tipoDocumentoContratante
     * @param documentoContratante
     * @param listaPersonas
     * @param nroCotizacion
     * @param consumoFinal
     * @param extension
     * @param fechaSalidaPais
     */
    private void logEmitirViajerosParametros( String logEncabezado,
                                              String tipoDocumentoContratante,
                                              String documentoContratante,
                                              ArrayList<ViajeroTienda> listaPersonas,
                                              long   nroCotizacion,
                                              String consumoFinal,
                                              String extension,
                                              Date   fechaSalidaPais ) {

        String auxListaPersonas = "";
        if        ( listaPersonas == null )  { auxListaPersonas = "ListaPersonas [null]";
        } else if ( listaPersonas.isEmpty()) { auxListaPersonas = "ListaPersonas [VACIA]";
        } else {
            auxListaPersonas = "ListaPersonas " + listaPersonas.toString();
        }

        String logParametros =   "TipoDocumentoContratante [" + tipoDocumentoContratante
                             + "] DocumentoContratante ["     + documentoContratante
                             + "] "                           + auxListaPersonas
                             +  " NroCotizacion ["            + Long.toString(nroCotizacion)
                             + "] ConsumoFinal ["             + consumoFinal
                             + "] Extension ["                + extension
                             + "] FechaSalidaPais ["          + ((fechaSalidaPais!=null)?fechaSalidaPais.toString():"null")
                             + "]";
        logger.info(logEncabezado + " - Parametros -> " + logParametros);
    }

    /**
     *
     * @param logEncabezado
     * @param titulo
     * @param tipoDocumento
     * @param documento
     * @param sucursal
     * @param ramo
     * @param producto
     * @param nroCotizacion
     * @param consumoFinal
     * @param slistaPersonas
     * @param extension
     * @param fechaSalidaPais
     */
    private void logEmitirViajerosPl2( String logEncabezado, String titulo         ,
                                       String tipoDocumento, String documento      ,
                                       int    sucursal     , int    ramo           , String producto,
                                       long   nroCotizacion, String consumoFinal   , String slistaPersonas,
                                       String extension    , Date   fechaSalidaPais ) {
        Logueo logueo = new Logueo();
        int pad = 28;
        logueo.setEncabezado(titulo);
        logueo.setParametro("P_TP_DOCUMENTO_CONTRATANTE" , tipoDocumento               , pad);
        logueo.setParametro("P_NRO_DOCUMENTO_CONTRATANTE", documento                   , pad);
        logueo.setParametro("P_SUCURSAL"                 , Integer.toString(sucursal)  , pad);
        logueo.setParametro("P_RAMO"                     , Integer.toString(ramo)      , pad);
        logueo.setParametro("P_PRODUCTO"                 , producto                    , pad);
        logueo.setParametro("P_COTIZACION_EMITIDA"       , Long.toString(nroCotizacion), pad);
        logueo.setParametro("P_CONSUMIDOR_FINAL"         , consumoFinal                , pad);
        logueo.setParametro("P_LISTA_PERSONAS"           , slistaPersonas              , pad);
        logueo.setParametro("P_EXTENSION"                , extension                   , pad);
        logueo.setParametro("P_FECHA_SALIDA_PAIS"        , ((fechaSalidaPais!=null)?fechaSalidaPais.toString():"null"), pad);
        logger.info(logEncabezado + " - " + logueo.getSoloParametros());
    }

    /**
     *
     * @param i
     * @return
     */
    private String logEmiteResult(int i) {
        int pad = 33;
        String result = "R[" + Integer.toString(i) + "]";
        switch (i) {
        case 0:
            result = "R[0]=P_POLIZA_EMITIDA";
            break;
        case 1:
            result = "R[1]=P_PREMIO_COTIZACION";
            break;
        case 2:
            result = "R[2]=P_PREMIO_COTIZACION_FACTURAR";
            break;
        case 3:
            result = "R[3]=P_FECHA_DESDE";
            break;
        case 4:
            result = "R[4]=P_FECHA_HASTA";
            break;
        case 5:
            result = "R[5]=P_PLAN_COBERTURA";
            break;
        case 6:
            result = "R[6]=P_PLAN_PAGO";
            break;
        case 7:
            result = "R[7]=P_CUOTAS";
            break;
        case 8:
            result = "R[8]=P_RESULTADO";
            break;
        case 9:
            result = "R[9]=P_MENSAJE_EMISION";
            break;

        default:
            break;
        }
        result = String.format("%-"+Integer.toString(pad)+"s", result);
        return result;
    }

    /**
     *
     * @param logEncabezado
     */
    private void logGuiones(String logEncabezado) {
        String tanda = "----------";
        String result = "";
        for (int i=0; i < 10; i++) {
            result += tanda;
        }
        logger.info(logEncabezado + " - " + result);
    }

    /*
    private void logCotizarViajerosPl( String  logEncabezado,
                                       String  tipoDocumento , String  documento,
                                       int     sucursal      , int     ramo          , String producto       ,
                                       String  productor     , Date    fechaDesde    , Date   fechaHasta     ,
                                       String  planCobertura , int     planPago      ,
                                       String  moneda        , String  tipoCalculo   ,
                                       String  formaPago     , String  vigTecnica    , String medioPago      ,
                                       String  origenPago    , String  tipoFact      , String promocion      ,
                                       String  renovacion    , String  usuarioWeb    , String consumidorFinal,
                                       String  factMail      , String  coaseguro     , String emitir         ,
                                       String  slistaPersonas, String  extension     , Date  fechaSalidaPais ) {
        String logInvocacion = logEncabezado + " ->"
                             +  " P_TP_DOCUMENTO ["      + tipoDocumento
                             + "] P_NRO_DOCUMENTO ["     + documento
                             + "] P_SUCURSAL ["          + Integer.toString(sucursal)
                             + "] P_RAMO ["              + Integer.toString(ramo)
                             + "] P_PRODUCTO ["          + producto
                             + "] P_PRODUCTOR ["         + productor
                             + "] P_FECHA_DESDE ["       + ((fechaDesde!=null)?fechaDesde.toString():"null")
                             + "] P_FECHA_HASTA ["       + ((fechaHasta!=null)?fechaHasta.toString():"null")
                             + "] P_PLAN_COBERTURA ["    + planCobertura
                             + "] P_PLAN_PAGO ["         + Integer.toString(planPago)
                             + "] P_MONEDA ["            + moneda
                             + "] P_TIPO_CALCULO ["      + tipoCalculo
                             + "] P_FORMA_PAGO ["        + formaPago
                             + "] P_VIG_TECNICA ["       + vigTecnica
                             + "] P_MEDIO_PAGO ["        + medioPago
                             + "] P_ORIGEN_PAGO ["       + origenPago
                             + "] P_TIPO_FACT ["         + tipoFact
                             + "] P_PROMOCION ["         + promocion
                             + "] P_RENOVACION ["        + renovacion
                             + "] P_USUARIO_WEB ["       + usuarioWeb
                             + "] P_CONSUMIDOR_FINAL ["  + consumidorFinal
                             + "] P_FACT_MAIL ["         + factMail
                             + "] P_COASEGURO ["         + coaseguro
                             + "] P_EMITIR ["            + emitir
                             + "] P_LISTA_PERSONAS ["    + slistaPersonas
                             + "] P_EXTENSION ["         + extension
                             + "] P_FECHA_SALIDA_PAIS [" + ((fechaSalidaPais!=null)?fechaSalidaPais.toString():"null")
                             + "]";
        logger.info(logInvocacion);
    }
    */

    /*
    private void logEmitirViajerosPl( String logEncabezado,
                                      String tipoDocumento, String documento      ,
                                      int    sucursal     , int    ramo           , String producto,
                                      long   nroCotizacion, String consumoFinal   , String slistaPersonas,
                                      String extension    , Date   fechaSalidaPais ) {
        String logInvocacion = logEncabezado + " ->"
                             +  " P_TP_DOCUMENTO_CONTRATANTE ["  + tipoDocumento
                             + "] P_NRO_DOCUMENTO_CONTRATANTE [" + documento
                             + "] P_SUCURSAL ["                  + String.valueOf(sucursal)
                             + "] P_RAMO ["                      + String.valueOf(ramo)
                             + "] P_PRODUCTO ["                  + producto
                             + "] P_COTIZACION_EMITIDA ["        + Long.toString(nroCotizacion)
                             + "] P_CONSUMIDOR_FINAL ["          + consumoFinal
                             + "] P_LISTA_PERSONAS ["            + slistaPersonas
                             + "] P_EXTENSION ["                 + extension
                             + "] P_FECHA_SALIDA_PAIS ["         + ((fechaSalidaPais!=null)?fechaSalidaPais.toString():"null")
                             + "]";
        logger.info(logInvocacion);
    }
    */

}
