package com.bse.negocio.edepor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bse.accesodatos.comun.CretTablasTienda;
import com.bse.accesodatos.edepor.CoberturaRcTienda;
import com.bse.accesodatos.edepor.CotizacionEDeporTienda;
import com.bse.accesodatos.edepor.PolizaEDeporTienda;
import com.bse.accesodatos.edepor.TipoBuqueTienda;
import com.bse.accesodatos.esoa.CuotaPagoTienda;
import com.bse.accesodatos.esoa.MonedaTienda;
import com.bse.accesodatos.esoa.PlanCoberturaTienda;
import com.bse.accesodatos.esoa.PlanPagoTienda;
import com.bse.accesodatos.esoa.ProductoTienda;
import com.bse.accesodatos.esoa.RamoTienda;
import com.bse.negocio.FabricaNegocioTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import com.bse.negocio.comun.CodigosErrorTienda;
import com.bse.negocio.comun.CodigosTienda;
import com.bse.negocio.comun.IEComunTienda;
import com.bse.negocio.comun.UtilTienda;
import com.bse.servicios.utilitario.log.Logueo;


public class EDeporMgrTienda implements IEDeporTienda{

    private static final Logger logger = LogManager.getLogger(EDeporMgrTienda.class);

    /**
     * CONSTRUCTOR
     */
    public EDeporMgrTienda() {
    }

    /**
     *
     * @return
     */
    public static IEDeporTienda getEDeporMgr() {
        return new EDeporMgrTienda();
    }



    /**
     *
     * @param em
     * @param planCobertura
     * @param tipoBuque
     * @param fechaDesde
     * @param fechaHasta
     * @param capital
     * @param planPago
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    @Override
    public CotizacionEDeporTienda cotizarEDeporAnonimo( EntityManager em,
                                                        String planCobertura,
                                                        String tipoBuque,
                                                        Date   fechaDesde,
                                                        Date   fechaHasta,
                                                        double capital,
                                                        int    planPago ) throws Exception, BSEExceptionTienda {

        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        long tiempoInicioAux = 0;
        long tiempoTotalAux  = 0;
        String logEncabezado = "EDEPOR - cotizarEDepor ("+Long.toString(tiempoInicioMetodo)+") - COTDEPOR";

        // LOGUEO PARAMETROS
        logCotizarEDeporParametros( logEncabezado + " - INICIO",
                                    planCobertura, tipoBuque, fechaDesde, fechaHasta, capital, planPago);

        IEComunTienda eComunManager = FabricaNegocioTienda.getFabricaNegocio().getEComunMgr();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CONTROL de PARAMETROS - OBLIGATORIEDAD - VALORES
        if ((tipoBuque == null) || tipoBuque.equals("")) {
            throw new BSEExceptionTienda(CodigosErrorTienda.tipo_buque_invalido);
        }
        if ((planCobertura == null) || planCobertura.equals("")) {
            throw new BSEExceptionTienda(CodigosErrorTienda.plan_cobertura_invalido);
        }

        // Valida Plan de Pago segun configuracion
        String planesPago = CodigosTienda.getCodigos().getPlanesPagoEDepor(em);
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
        if ((fechaDesde == null) || (fechaHasta == null)) {
            throw new BSEExceptionTienda(CodigosErrorTienda.fechas_invalidas);
        }
        if (fechaHasta.before(fechaDesde)) {
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

        TipoBuqueTienda tipoBuqueObj = null;
        try {
            tipoBuqueObj = consultaTipoBuque(em, tipoBuque);
        } catch (Exception e) {
            tipoBuqueObj = null;
            throw new BSEExceptionTienda(CodigosErrorTienda.tipo_buque_invalido);
        }
        logger.info(logEncabezado + " - PARAMETROS - OK");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CARGA de DATOS desde CONFIGURACION para INVOCACION PL
        String tipoDocumento   = " ";
        String documento      = " ";
        int    sucursal       = Integer.parseInt(CodigosTienda.getCodigos().getSucursalEmisionEDepor(em));
        int    ramoEDepor     = Integer.parseInt(CodigosTienda.getCodigos().getRamoEmisionEDepor(em));
        String productoEDepor = CodigosTienda.getCodigos().getProductoEmisionEDepor(em);
        String productor      = CodigosTienda.getCodigos().getProductorEmisionEDepor(em);

        String moneda         = CodigosTienda.getCodigos().getMonedaEmisionEDepor(em);
        String tipoCalculo    = CodigosTienda.getCodigos().getTipoCalculoEmisionEDepor(em).trim();
        String formaPago      = CodigosTienda.getCodigos().getFormaPagoEmisionEDepor(em).trim();
        String vigTecnica     = CodigosTienda.getCodigos().getVigTecnicaEmisionEDepor(em).trim();
        String medioPago      = CodigosTienda.getCodigos().getMedioPagoEmisionEDepor(em).trim();
        String origenPago     = CodigosTienda.getCodigos().getOrigenPagoEmisionEDepor(em).trim();
        String tipoFact       = CodigosTienda.getCodigos().getTipoFactEmisionEDepor(em).trim();
        String promocion      = CodigosTienda.getCodigos().getPromocionEmisionEDepor(em).trim();
        String renovacion     = CodigosTienda.getCodigos().getRenovacionEmisionEDepor(em).trim();
        String usuarioWeb     = CodigosTienda.getCodigos().getUsuarioWebEmisionEDepor(em).trim();

        String  consumidorFinal = "N";
        String  factMail        = "N";
        String  coaseguro       = "N";
        String  emitir          = "N";
        logger.info(logEncabezado + " - DATOS desde CONFIGURACION - OK");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // LOGUEO PARAMETROS de la Invocaci�n PL
        logGuiones(logEncabezado);
        logCotizarEDeporPl2( logEncabezado, "PACK_EMI_MIBSE.PRO_COTIZAR_EDEPOR",
                             tipoDocumento, documento  , sucursal  , ramoEDepor   , productoEDepor,
                             productor    , fechaDesde , fechaHasta, planCobertura, planPago      ,
                             moneda       , tipoCalculo, formaPago , vigTecnica   , medioPago     ,
                             origenPago   , tipoFact   , promocion , renovacion   , usuarioWeb    , consumidorFinal,
                             factMail     , coaseguro  , emitir    , tipoBuque    , capital );


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // INVOCACION PL - PACK_EMI_MIBSE.PRO_COTIZAR_BICI
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Query q = em.createNamedQuery("PRO_COTIZAR_EDEPOR");

        q.setParameter("P_TP_DOCUMENTO"     , tipoDocumento);
        q.setParameter("P_NRO_DOCUMENTO"    , documento);
        q.setParameter("P_SUCURSAL"         , sucursal);
        q.setParameter("P_RAMO"             , String.valueOf(ramoEDepor));
        q.setParameter("P_PRODUCTO"         , productoEDepor);
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
        q.setParameter("P_TIPO_EMBARCACION" , tipoBuque);
        q.setParameter("P_CAPITAL_RC"       , capital);

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
        //      -> R[4] = P_RESULTADO                   - type=String.class
        //      -> R[5] = P_MENSAJE_EMISION             - type=String.class
        for (int i = 0; i < r.length; i++){
            if (r[i] != null) { logger.info(logEncabezado + " - " + logCotizaResult(i) + " = [" + r[i].toString() + "]");
            } else            { logger.info(logEncabezado + " - " + logCotizaResult(i) + " = [null]"); }
        }
        logger.info(logEncabezado + " - Tiempo Ejecucion PL ["+Long.toString(tiempoTotalAux)+"] milisegundos");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // En caso de ERROR, se retorna el error especificado por el PL
        if ((r[4] != null) && !((String)r[4]).equals("0")) {
            String codError  = (String)r[4];
            String descError = (r[5]!=null)?((String)r[5]):"";
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

        CotizacionEDeporTienda cotizacion = new CotizacionEDeporTienda();

        RamoTienda ramo = em.find(RamoTienda.class, Integer.valueOf(ramoEDepor));
        cotizacion.setRamo(ramo);

        ProductoTienda productoObj = em.find(ProductoTienda.class, productoEDepor);
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

        cotizacion.setFechaDesde(fechaDesde);
        cotizacion.setFechaHasta(fechaHasta);

        PlanPagoTienda planPagoObj = eComunManager.consultaPlanPago(em, planPago);
        /*planPagoObj.setCodigo(planPago);*/
        cotizacion.setPlanPago(planPagoObj);

        PlanCoberturaTienda planCoberturaObj = eComunManager.consultaPlanCoberturaRamo(em, ramo.getRamo(), planCobertura);
        /*planCoberturaObj.setPlan(planCobertura);
        planCoberturaObj.setProducto(productoObj);*/
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

        cotizacion.setTipoBuque(tipoBuqueObj);
        cotizacion.setCapital(capital);

        logger.info(logEncabezado + " - RESULT : " + cotizacion.toString());
        tiempoTotalMetodo = System.currentTimeMillis() - tiempoInicioMetodo;
        logger.info(logEncabezado + " - FIN - Tiempo Ejecucion METODO [" + Long.toString(tiempoTotalMetodo)+"] milisegundos");
        return cotizacion;
    }



    /**
     *
     * @param em
     * @param tipoDocumento
     * @param documento
     * @param matriculaBuque
     * @param nombreBuque
     * @param nroCotizacion
     * @param consumoFinal
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    @Override
    public PolizaEDeporTienda emitirEDepor( EntityManager em,
                                            String tipoDocumento,
                                            String documento,
                                            String matriculaBuque,
                                            String nombreBuque,
                                            long   nroCotizacion,
                                            String consumoFinal ) throws Exception, BSEExceptionTienda{

        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        long tiempoInicioAux = 0;
        long tiempoTotalAux  = 0;
        String logEncabezado = "EDEPOR - emitirEDepor (" + Long.toString(tiempoInicioMetodo) + ") - EMIDEPOR";

        // LOGUEO PARAMETROS
        logEmitirEDeporParametros( logEncabezado + " - INICIO",
                                   tipoDocumento, documento, matriculaBuque, nombreBuque, nroCotizacion, consumoFinal );

        IEComunTienda eComunManager = FabricaNegocioTienda.getFabricaNegocio().getEComunMgr();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CONTROL de PARAMETROS - OBLIGATORIEDAD
        if ((tipoDocumento == null) || tipoDocumento.equals("")) {
            throw new BSEExceptionTienda(CodigosErrorTienda.tipo_documento_invalido);
        }
        if ((documento == null) || documento.equals("")) {
            throw new BSEExceptionTienda(CodigosErrorTienda.documento_invalido);
        }
        if (nroCotizacion == 0) {
            throw new BSEExceptionTienda(CodigosErrorTienda.cotizacion_invalida);
        }
        if (!consumoFinal.equals("S") && !consumoFinal.equals("N")) {
            throw new BSEExceptionTienda(CodigosErrorTienda.consumidor_final_invalido);
        }
        if ((matriculaBuque == null) || matriculaBuque.equals("")) {
            throw new BSEExceptionTienda(CodigosErrorTienda.matricula_vehiculo_invalida);
        }
        if ((nombreBuque == null) || nombreBuque.equals("")) {
            throw new BSEExceptionTienda(CodigosErrorTienda.nombre_invalido);
        }
        logger.info(logEncabezado + " - PARAMETROS - OK");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CARGA de DATOS desde la CONFIGURACION
        int    sucursal       = Integer.parseInt(CodigosTienda.getCodigos().getSucursalEmisionEDepor(em));
        int    ramoEDepor     = Integer.parseInt(CodigosTienda.getCodigos().getRamoEmisionEDepor(em));
        String productoEDepor = CodigosTienda.getCodigos().getProductoEmisionEDepor(em);
        String productor      = CodigosTienda.getCodigos().getProductorEmisionEDepor(em);
        String moneda         = CodigosTienda.getCodigos().getMonedaEmisionEDepor(em);
        String usuarioWeb     = CodigosTienda.getCodigos().getUsuarioWebEmisionEDepor(em).trim();
        logger.info(logEncabezado + " - DATOS desde CONFIGURACION - OK");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Valida Nro. Cotizacion
        boolean cotizacionOk = eComunManager.validarExistenciaCotizacion
                                                            ( em, sucursal, nroCotizacion, ramoEDepor, productoEDepor );
        if (!cotizacionOk) {
            throw new BSEExceptionTienda(CodigosErrorTienda.cotizacion_invalida);
        }
        logger.info(logEncabezado + " - NRO. COTIZACION - OK");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // LOGUEO PARAMETROS de la Invocaci�n PL
        logGuiones(logEncabezado);
        logEmitirEDeporPl2( logEncabezado, "PACK_EMI_MIBSE.PRO_EMITIR_EDEPOR",
                            tipoDocumento, documento   , sucursal      , ramoEDepor , productoEDepor,
                            nroCotizacion, consumoFinal, matriculaBuque, nombreBuque, usuarioWeb );


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // INVOCACION PL - PACK_EMI_MIBSE.PRO_EMITIR_VARIOS
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Query q = em.createNamedQuery("PRO_EMITIR_EDEPOR");

        q.setParameter("P_TP_DOCUMENTO_CONTRATANTE" , tipoDocumento);
        q.setParameter("P_NRO_DOCUMENTO_CONTRATANTE", documento);
        q.setParameter("P_SUCURSAL"                 , String.valueOf(sucursal));
        q.setParameter("P_RAMO"                     , String.valueOf(ramoEDepor));
        q.setParameter("P_PRODUCTO"                 , productoEDepor);
        q.setParameter("P_COTIZACION_EMITIDA"       , nroCotizacion);
        q.setParameter("P_CONSUMIDOR_FINAL"         , consumoFinal);
        q.setParameter("P_MATRICULA_BUQUE"          , matriculaBuque);
        q.setParameter("P_NOMBRE_BUQUE"             , nombreBuque);
        q.setParameter("P_USUARIO_WEB"              , usuarioWeb);

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
        //      -> R[7]  = P_TIPO_EMBARCACION            - type=String.class
        //      -> R[8]  = P_CAPITAL_RC                  - type=Float.class
        //      -> R[9]  = P_CUOTAS                      - type=String.class
        //      -> R[10] = P_RESULTADO                   - type=String.class
        //      -> R[11] = P_MENSAJE_EMISION             - type=String.class
        for (int i = 0; i < r.length; i++){
            if (r[i] != null) { logger.info(logEncabezado + " - " + logEmiteResult(i) + " = [" + r[i].toString() + "]");
            } else            { logger.info(logEncabezado + " - " + logEmiteResult(i) + " = [null]"); }
        }
        logger.info(logEncabezado + " - Tiempo Ejecucion PL ["+Long.toString(tiempoTotalAux)+"] milisegundos");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // En caso de ERROR, se retorna el error especificado por el PL
        if (r[10] != null) {
            String codError  = (String)r[10];
            String descError = (r[11]!=null)?((String)r[11]):"";
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

        int    nroPoliza      = ((Integer)r[0]).intValue();
        double premio         = ((Double)r[1]).doubleValue();
        double premioFacturar = ((Double)r[2]).doubleValue();
        Date   fechaDesde     = (Date)r[3];
        Date   fechaHasta     = (Date)r[4];
        String planCobertura  = (String)r[5];
        int    planPago       = ((Integer)r[6]).intValue();
        String tipoBuque      = (String)r[7];
        double capital        = ((Double)r[8]).doubleValue();
        String cuotas         = (String)r[9];

        PolizaEDeporTienda polizaObj = new PolizaEDeporTienda();

        RamoTienda ramoObj = em.find(RamoTienda.class, Integer.valueOf(ramoEDepor));
        polizaObj.setRamo(ramoObj);

        ProductoTienda productoObj = em.find(ProductoTienda.class, productoEDepor);
        polizaObj.setProducto(productoObj);

        polizaObj.setSucursal(200);
        polizaObj.setNroCotizacion(nroCotizacion);
        polizaObj.setTipoDocumento(tipoDocumento);
        polizaObj.setNroDocumento(documento);
        polizaObj.setProductor(Integer.valueOf(productor));

        MonedaTienda monedaObj = eComunManager.consultaMoneda(em, moneda);
        polizaObj.setMoneda(monedaObj);

        polizaObj.setPremio(premio);
        polizaObj.setPremioFacturar(premioFacturar);

        polizaObj.setFechaDesde(fechaDesde);
        polizaObj.setFechaHasta(fechaHasta);

        PlanCoberturaTienda planCoberturaObj = eComunManager.consultaPlanCoberturaRamo(em, ramoObj.getRamo(), planCobertura);
        planCoberturaObj.setProducto(productoObj);
        polizaObj.setPlanCobertura(planCoberturaObj);

        PlanPagoTienda planPagoObj = eComunManager.consultaPlanPago(em, planPago);
        polizaObj.setPlanPago(planPagoObj);

        polizaObj.setNroPoliza(nroPoliza);

        logger.info(logEncabezado + " - PRE  - CUOTAS - OK");
        cuotas=cuotas.replace(" ", "");
        cuotas=cuotas.replace("\\13", "");
        cuotas=cuotas.replace("\\10", "");

        //String regex = "\b<cuota>\b";
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
        polizaObj.setCuotas(listaCuotas);
        logger.info(logEncabezado + " - POST - CUOTAS - OK - " + Integer.toString(listaCuotas.size()) + " cuotas.");

        polizaObj.setCapital(capital);
        TipoBuqueTienda tipo = consultaTipoBuque(em, tipoBuque);
        polizaObj.setTipoBuque(tipo);
        polizaObj.setMatriculaBuque(matriculaBuque);
        polizaObj.setNombreBuque(nombreBuque);


        logger.info(logEncabezado + " - RESULT : " + polizaObj.toString());
        tiempoTotalMetodo = System.currentTimeMillis() - tiempoInicioMetodo;
        logger.info(logEncabezado + " - FIN - Tiempo Ejecucion METODO [" + Long.toString(tiempoTotalMetodo)+"] milisegundos");
        return polizaObj;
    }



    /**
     *
     * @param em
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    @Override
    public List<TipoBuqueTienda> consultaTiposBuques(EntityManager em) throws Exception, BSEExceptionTienda {
        String logEncabezado = "EDEPOR - consultaTiposBuques";

        ArrayList<TipoBuqueTienda> lista = new ArrayList<TipoBuqueTienda>();

        Query query = em.createNamedQuery("CretTablasTienda.findTabla");
        query.setParameter("codigoTabla", Integer.valueOf("150196"));

        List<CretTablasTienda> result = UtilTienda.castList(CretTablasTienda.class, query.getResultList());

        if ((result != null) && (result.size() > 0)) {
            for (int i = 0; i < result.size(); i++) {
                CretTablasTienda tabla = result.get(i);
                TipoBuqueTienda o = new TipoBuqueTienda(tabla.getDato1(), tabla.getDescripcion());
                lista.add(o);
            }
        }

        logger.info(logEncabezado + " - RESULT - [" + Integer.toString(lista.size()) + "] elementos.");
        return lista;
    }



    /**
     *
     * @param em
     * @param tipo
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    @Override
    public TipoBuqueTienda consultaTipoBuque(EntityManager em, String tipo) throws Exception, BSEExceptionTienda {

        Query query = em.createNamedQuery("CretTablasTienda.findByClave");
        query.setParameter("codigoTabla", Integer.valueOf("150196"));
        query.setParameter("codigoDato", tipo);

        CretTablasTienda tabla = (CretTablasTienda)query.getSingleResult();
        TipoBuqueTienda o = new TipoBuqueTienda(tabla.getDato1(), tabla.getDescripcion());

        return o;
    }



    /**
     *
     * @param em
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    @Override
    public List<CoberturaRcTienda> consultaCoberturasRc(EntityManager em) throws Exception, BSEExceptionTienda {
        String logEncabezado = "EDEPOR - consultaCoberturasRc";

        //logger.info(logEncabezado + " - ---------Comienzo Cob Rc---------------------");
        ArrayList<CoberturaRcTienda> lista = new ArrayList<CoberturaRcTienda>();

        Query query = em.createNamedQuery("CretTablasTienda.findTabla");
        query.setParameter("codigoTabla", Integer.valueOf("150145"));

        List<CretTablasTienda> result = UtilTienda.castList(CretTablasTienda.class, query.getResultList());

        if ((result != null) && (result.size() > 0)) {
            for (int i = 0; i < result.size(); i++) {
                CretTablasTienda tabla = result.get(i);
                CoberturaRcTienda o = new CoberturaRcTienda(tabla.getDato1(), tabla.getDescripcion());
                lista.add(o);
                //logger.info(logEncabezado + " - " + o.toString());
            }
        }
        //logger.info(logEncabezado + " - ---------------Fin Cob Rc--------------------");

        logger.info(logEncabezado + " - RESULT - [" + Integer.toString(lista.size()) + "] elementos.");
        return lista;
    }



    /**
     *
     * @param logEncabezado
     * @param planCobertura
     * @param tipoBuque
     * @param fechaDesde
     * @param fechaHasta
     * @param capital
     * @param planPago
     */
    private void logCotizarEDeporParametros( String logEncabezado,
                                             String planCobertura, String tipoBuque,
                                             Date   fechaDesde   , Date   fechaHasta,
                                             double capital      , int    planPago ) {
        String logParametros =   "PlanCobertura [" + planCobertura
                             + "] TipoBuque ["     + tipoBuque
                             + "] FechaDesde ["    + ((fechaDesde!=null)?fechaDesde.toString():"null")
                             + "] FechaHasta ["    + ((fechaHasta!=null)?fechaHasta.toString():"null")
                             + "] Capital ["       + Double.toString(capital)
                             + "] PlanPago ["      + Integer.toString(planPago)
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
     * @param tipoBuque
     * @param capital
     */
    private void logCotizarEDeporPl2( String  logEncabezado , String titulo         ,
                                      String  tipoDocumento , String  documento     ,
                                      int     sucursal      , int     ramo          , String producto       ,
                                      String  productor     , Date    fechaDesde    , Date   fechaHasta     ,
                                      String  planCobertura , int     planPago      ,
                                      String  moneda        , String  tipoCalculo   ,
                                      String  formaPago     , String  vigTecnica    , String medioPago      ,
                                      String  origenPago    , String  tipoFact      , String promocion      ,
                                      String  renovacion    , String  usuarioWeb    , String consumidorFinal,
                                      String  factMail      , String  coaseguro     , String emitir,
                                      String  tipoBuque     , double  capital) {
        Logueo logueo = new Logueo();
        int pad = 19;
        logueo.setEncabezado(titulo);
        logueo.setParametro("P_TP_DOCUMENTO"    , tipoDocumento             , pad);
        logueo.setParametro("P_NRO_DOCUMENTO"   , documento                 , pad);
        logueo.setParametro("P_SUCURSAL"        , Integer.toString(sucursal), pad);
        logueo.setParametro("P_RAMO"            , Integer.toString(ramo)    , pad);
        logueo.setParametro("P_PRODUCTO"        , producto                  , pad);
        logueo.setParametro("P_PRODUCTOR"       , productor                 , pad);
        logueo.setParametro("P_FECHA_DESDE"     , ((fechaDesde!=null)?fechaDesde.toString():"null"), pad);
        logueo.setParametro("P_FECHA_HASTA"     , ((fechaHasta!=null)?fechaHasta.toString():"null"), pad);
        logueo.setParametro("P_PLAN_COBERTURA"  , planCobertura             , pad);
        logueo.setParametro("P_PLAN_PAGO"       , Integer.toString(planPago), pad);
        logueo.setParametro("P_MONEDA"          , moneda                    , pad);
        logueo.setParametro("P_TIPO_CALCULO"    , tipoCalculo               , pad);
        logueo.setParametro("P_FORMA_PAGO"      , formaPago                 , pad);
        logueo.setParametro("P_VIG_TECNICA"     , vigTecnica                , pad);
        logueo.setParametro("P_MEDIO_PAGO"      , medioPago                 , pad);
        logueo.setParametro("P_ORIGEN_PAGO"     , origenPago                , pad);
        logueo.setParametro("P_TIPO_FACT"       , tipoFact                  , pad);
        logueo.setParametro("P_PROMOCION"       , promocion                 , pad);
        logueo.setParametro("P_RENOVACION"      , renovacion                , pad);
        logueo.setParametro("P_USUARIO_WEB"     , usuarioWeb                , pad);
        logueo.setParametro("P_CONSUMIDOR_FINAL", consumidorFinal           , pad);
        logueo.setParametro("P_FACT_MAIL"       , factMail                  , pad);
        logueo.setParametro("P_COASEGURO"       , coaseguro                 , pad);
        logueo.setParametro("P_EMITIR"          , emitir                    , pad);
        logueo.setParametro("P_TIPO_EMBARCACION", tipoBuque                 , pad);
        logueo.setParametro("P_CAPITAL_RC"      , Double.toString(capital)  , pad);
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
            result = "R[4]=P_RESULTADO";
            break;
        case 5:
            result = "R[5]=P_MENSAJE_EMISION";
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
     * @param tipoDocumento
     * @param documento
     * @param matriculaBuque
     * @param nombreBuque
     * @param nroCotizacion
     * @param consumoFinal
     */
    private void logEmitirEDeporParametros( String logEncabezado,
                                            String tipoDocumento , String documento,
                                            String matriculaBuque, String nombreBuque,
                                            long   nroCotizacion , String consumoFinal ) {

        String logParametros =   "TipoDocumento ["   + tipoDocumento
                             + "] Documento ["       + documento
                             + "] MatriculaBuque ["  + matriculaBuque
                             + "] NombreBuque ["     + nombreBuque
                             + "] NroCotizacion ["   + Long.toString(nroCotizacion)
                             + "] ConsumoFinal ["    + consumoFinal + "]";

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
     * @param matriculaBuque
     * @param nombreBuque
     * @param usuarioWeb
     */
    private void logEmitirEDeporPl2( String logEncabezado , String titulo      ,
                                     String tipoDocumento , String documento   ,
                                     int    sucursal      , int    ramo        , String producto,
                                     long   nroCotizacion , String consumoFinal,
                                     String matriculaBuque, String nombreBuque , String usuarioWeb  ) {
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
        logueo.setParametro("P_MATRICULA_BUQUE"          , matriculaBuque              , pad);
        logueo.setParametro("P_NOMBRE_BUQUE"             , nombreBuque                 , pad);
        logueo.setParametro("P_USUARIO_WEB"              , usuarioWeb                  , pad);
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
            result = "R[7]=P_TIPO_EMBARCACION";
            break;
        case 8:
            result = "R[8]=P_CAPITAL_RC";
            break;
        case 9:
            result = "R[9]=P_CUOTAS";
            break;
        case 10:
            result = "R[10]=P_RESULTADO";
            break;
        case 11:
            result = "R[11]=P_MENSAJE_EMISION";
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
        for (int i=0; i < 12; i++) {
            result += tanda;
        }
        logger.info(logEncabezado + " - " + result);
    }

    /*
    private void logCotizarEDeporPl( String  logEncabezado,
                                     String  tipoDocumento , String  documento,
                                     int     sucursal      , int     ramo          , String producto       ,
                                     String  productor     , Date    fechaDesde    , Date   fechaHasta     ,
                                     String  planCobertura , int     planPago      ,
                                     String  moneda        , String  tipoCalculo   ,
                                     String  formaPago     , String  vigTecnica    , String medioPago      ,
                                     String  origenPago    , String  tipoFact      , String promocion      ,
                                     String  renovacion    , String  usuarioWeb    , String consumidorFinal,
                                     String  factMail      , String  coaseguro     , String emitir,
                                     String  tipoBuque     , double  capital ) {

        String logInvocacion = logEncabezado + " ->"
                             +  " P_TP_DOCUMENTO ["     + tipoDocumento
                             + "] P_NRO_DOCUMENTO ["    + documento
                             + "] P_SUCURSAL ["         + String.valueOf(sucursal)
                             + "] P_RAMO ["             + String.valueOf(ramo)
                             + "] P_PRODUCTO ["         + producto
                             + "] P_PRODUCTOR ["        + productor
                             + "] P_FECHA_DESDE ["      + ((fechaDesde!=null)?fechaDesde.toString():"null")
                             + "] P_FECHA_HASTA ["      + ((fechaHasta!=null)?fechaHasta.toString():"null")
                             + "] P_PLAN_COBERTURA ["   + planCobertura
                             + "] P_PLAN_PAGO ["        + Integer.toString(planPago)
                             + "] P_MONEDA ["           + moneda
                             + "] P_TIPO_CALCULO ["     + tipoCalculo
                             + "] P_FORMA_PAGO ["       + formaPago
                             + "] P_VIG_TECNICA ["      + vigTecnica
                             + "] P_MEDIO_PAGO ["       + medioPago
                             + "] P_ORIGEN_PAGO ["      + origenPago
                             + "] P_TIPO_FACT ["        + tipoFact
                             + "] P_PROMOCION ["        + promocion
                             + "] P_RENOVACION ["       + renovacion
                             + "] P_USUARIO_WEB ["      + usuarioWeb
                             + "] P_CONSUMIDOR_FINAL [" + consumidorFinal
                             + "] P_FACT_MAIL ["        + factMail
                             + "] P_COASEGURO ["        + coaseguro
                             + "] P_EMITIR ["           + emitir
                             + "] P_TIPO_EMBARCACION [" + tipoBuque
                             + "] P_CAPITAL_RC ["       + Double.toString(capital)
                             + "]";
        logger.info(logInvocacion);
    }
    */

    /*
    private void logEmitirEDeporPl( String logEncabezado,
                                    String tipoDocumento , String documento   ,
                                    int    sucursal      , int    ramo        , String producto,
                                    long   nroCotizacion , String consumoFinal,
                                    String matriculaBuque, String nombreBuque , String usuarioWeb  ) {
        String logInvocacion = logEncabezado + " ->"
                             +  " P_TP_DOCUMENTO_CONTRATANTE ["  + tipoDocumento
                             + "] P_NRO_DOCUMENTO_CONTRATANTE [" + documento
                             + "] P_SUCURSAL ["                  + String.valueOf(sucursal)
                             + "] P_RAMO ["                      + String.valueOf(ramo)
                             + "] P_PRODUCTO ["                  + producto
                             + "] P_COTIZACION_EMITIDA ["        + Long.toString(nroCotizacion)
                             + "] P_CONSUMIDOR_FINAL ["          + consumoFinal
                             + "] P_MATRICULA_BUQUE ["           + matriculaBuque
                             + "] P_NOMBRE_BUQUE ["              + nombreBuque
                             + "] P_USUARIO_WEB ["               + usuarioWeb
                             + "]";
        logger.info(logInvocacion);
    }
    */

}
