package com.bse.negocio.ebicis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.bse.accesodatos.comun.CretTablasTienda;
import com.bse.accesodatos.ebici.CotizacionBiciTienda;
import com.bse.accesodatos.ebici.PolizaBiciTienda;
import com.bse.accesodatos.ebici.TipoBiciTienda;
import com.bse.accesodatos.esoa.CuotaPagoTienda;
import com.bse.accesodatos.esoa.MonedaTienda;
import com.bse.accesodatos.esoa.PlanCoberturaTienda;
import com.bse.accesodatos.esoa.PlanCuotaTienda;
import com.bse.accesodatos.esoa.PlanPagoTienda;
import com.bse.accesodatos.esoa.ProductoTienda;
import com.bse.accesodatos.esoa.RamoTienda;
import com.bse.negocio.FabricaNegocioTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import com.bse.negocio.comun.CodigosTienda;
import com.bse.negocio.comun.CodigosErrorTienda;
import com.bse.negocio.comun.IEComunTienda;
import com.bse.negocio.comun.UtilTienda;
import com.bse.servicios.utilitario.log.Logueo;


public class EBiciMgrTienda implements IEBiciTienda{

    private static final Logger logger = Logger.getLogger(EBiciMgrTienda.class);

    /**
     * CONSTRUCTOR
     */
    public EBiciMgrTienda() {
    }

    /**
     *
     * @return
     */
    public static IEBiciTienda getEBiciMgr() {
        return new EBiciMgrTienda();
    }



    /**
     *
     * @param em
     * @param planCobertura
     * @param valorBicicleta
     * @param vigenciaSeguro
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    @Override
    public CotizacionBiciTienda cotizarBiciAnonimo( EntityManager em,
                                                    String  planCobertura,
                                                    Double  valorBicicleta,
                                                    Integer vigenciaSeguro ) throws Exception, BSEExceptionTienda {

        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        long tiempoInicioAux = 0;
        long tiempoTotalAux  = 0;
        String logEncabezado = "BICI - cotizarBici (" + Long.toString(tiempoInicioMetodo) + ") - COTBICI";

        // LOGUEO PARAMETROS
        logCotizarBiciParametros( logEncabezado + " - INICIO",
                                  planCobertura, valorBicicleta, vigenciaSeguro );

        IEComunTienda eComunManager = FabricaNegocioTienda.getFabricaNegocio().getEComunMgr();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CARGA de DATOS desde CONFIGURACION para INVOCACION PL
        String  tipoDocumento = " ";
        String  documento     = " ";
        int     sucursal      = Integer.parseInt(CodigosTienda.getCodigos().getSucursalEmisionBici(em));
        int     ramoBici      = Integer.parseInt(CodigosTienda.getCodigos().getRamoEmisionBici(em));
        String  productoBici  = CodigosTienda.getCodigos().getProductoEmisionBici(em);
        String  productor     = CodigosTienda.getCodigos().getProductorEmisionBici(em);
        Date    fechaDesde    = new Date();
        Integer planPago      = new Integer(0);

        String  moneda        = CodigosTienda.getCodigos().getMonedaEmisionBici(em);
        String  tipoCalculo   = CodigosTienda.getCodigos().getTipoCalculoEmisionBici(em).trim();
        String  formaPago     = CodigosTienda.getCodigos().getFormaPagoEmisionBici(em).trim();
        String  vigTecnica    = CodigosTienda.getCodigos().getVigTecnicaEmisionBici(em).trim();
        String  medioPago     = CodigosTienda.getCodigos().getMedioPagoEmisionBici(em).trim();
        String  origenPago    = CodigosTienda.getCodigos().getOrigenPagoEmisionBici(em).trim();
        String  tipoFact      = CodigosTienda.getCodigos().getTipoFactEmisionBici(em).trim();
        String  promocion     = CodigosTienda.getCodigos().getPromocionEmisionBici(em).trim();
        String  renovacion    = CodigosTienda.getCodigos().getRenovacionEmisionBici(em).trim();
        String  usuarioWeb    = CodigosTienda.getCodigos().getUsuarioWebEmisionBici(em).trim();

        String  consumidorFinal = "N";
        String  factMail        = "N";
        String  coaseguro       = "N";
        String  emitir          = "N";
        logger.info(logEncabezado + " - DATOS desde CONFIGURACION - OK");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // LOGUEO PARAMETROS de la Invocación PL
        logGuiones(logEncabezado);
        logCotizarBiciPl2( logEncabezado, "PACK_EMI_MIBSE.PRO_COTIZAR_BICI",
                           tipoDocumento, documento      , sucursal      , ramoBici      , productoBici,
                           productor    , fechaDesde     , valorBicicleta, vigenciaSeguro, planPago,
                           planCobertura, moneda         , tipoCalculo   , formaPago     , vigTecnica,
                           medioPago    , origenPago     , tipoFact      , promocion     , renovacion,
                           usuarioWeb   , consumidorFinal, factMail      , coaseguro     , emitir);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // INVOCACION PL - PACK_EMI_MIBSE.PRO_COTIZAR_BICI
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Query q = em.createNamedQuery("PRO_COTIZAR_BICI");

        q.setParameter("P_TP_DOCUMENTO"     , tipoDocumento);
        q.setParameter("P_NRO_DOCUMENTO"    , documento);
        q.setParameter("P_SUCURSAL"         , sucursal);
        q.setParameter("P_RAMO"             , String.valueOf(ramoBici));
        q.setParameter("P_PRODUCTO"         , productoBici);
        q.setParameter("P_PRODUCTOR"        , productor);
        q.setParameter("P_FECHA_DESDE"      , fechaDesde);
        q.setParameter("P_VALOR_BICI"       , valorBicicleta);
        q.setParameter("P_VIGENCIA_SEGURO"  , vigenciaSeguro);
        q.setParameter("P_PLAN_PAGO"        , planPago);
        q.setParameter("P_PLAN_COBERTURA"   , planCobertura);
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
        //      -> R[3] = P_FECHA_HASTA                 - type=Date.class
        //      -> R[4] = P_CUOTAS                      - type=String.class
        //      -> R[5] = P_PLANES                      - type=String.class
        //      -> R[6] = P_RESULTADO                   - type=String.class
        //      -> R[7] = P_MENSAJE_EMISION             - type=String.class
        for (int i = 0; i < r.length; i++){
            if (r[i] != null) { logger.info(logEncabezado + " - " + logCotizaResult(i) + " = [" + r[i].toString() + "]");
            } else            { logger.info(logEncabezado + " - " + logCotizaResult(i) + " = [null]"); }
        }
        logger.info(logEncabezado + " - Tiempo Ejecucion PL ["+Long.toString(tiempoTotalAux)+"] milisegundos");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // En caso de ERROR, se retorna el error especificado por el PL
        if (r[6] != null && !((String)r[6]).equals("0")) {
            String codError  = (String)r[6];
            String descError = (r[7]!=null)?((String)r[7]):"";
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

        CotizacionBiciTienda cotizacion = new CotizacionBiciTienda();

        RamoTienda ramo = em.find(RamoTienda.class, Integer.valueOf(ramoBici));
        cotizacion.setRamo(ramo);

        ProductoTienda productoObj = em.find(ProductoTienda.class, productoBici);
        cotizacion.setProducto(productoObj);

        cotizacion.setSucursal(sucursal);

        cotizacion.setNroCotizacion(0);
        if (r[0] != null)
            cotizacion.setNroCotizacion(((Integer)r[0]).intValue());

        cotizacion.setTipoDocumento("");
        cotizacion.setNroDocumento("");
        cotizacion.setProductor(1);

        MonedaTienda monedaObj = eComunManager.consultaMoneda(em, moneda);
        cotizacion.setMoneda(monedaObj);

        cotizacion.setPremio(0);
        if (r[1] != null)
            cotizacion.setPremio(((Double)r[1]).doubleValue());

        cotizacion.setPremioFacturar(0);
        if (r[2] != null)
            cotizacion.setPremioFacturar(((Double)r[2]).doubleValue());

        cotizacion.setFechaDesde(fechaDesde);
        cotizacion.setFechaHasta((Date)r[3]);

        //String cuotasStr = (String)r[4];
        //String[] vecCuotas = cuotasStr.split(";");

        String planesCuotasStr = "";
        if (r[5] != null)
            planesCuotasStr = (String)r[5];

        // *1;1;4742;1:4742#*2;2;4842;1:2421#2:2421#*3;3;4875;1:1625#2:1625#3:1625#*4;4;4912;1:1228#2:1228#3:1228#4:1228#*5;5;4945;1:989#2:989#3:989#4:989#5:989#*6;6;4980;1:830#2:830#3:830#4:830#5:830#6:830#

        // parte de planes de cuotas, comentado hasta nuevo aviso

        logger.info(logEncabezado + " - PRE  - PLANES de CUOTAS - OK");
        ArrayList<PlanCuotaTienda> planesDeCuotas = new ArrayList<PlanCuotaTienda>();

        String[] planes = planesCuotasStr.split("\\*".toString());
        for(int i = 0; i < planes.length; i++){
            String plan = planes[i];

            //logger.info("PLAN de " + i + " " + plan);

            if (plan == null || plan.equals(""))
                continue;

            String[] partes = plan.split(";"); // 1;1;4742;1:4742#

            //logger.info("PLAN " + plan);
            //logger.info(" PLAN="+partes[0]+" CUOTAS="+partes[1] + " PTF="+partes[2]);
            logger.info(logEncabezado + " - PLAN [" + plan
                                      + "] <- PLAN=" + partes[0] + " CUOTAS=" + partes[1] + " PTF=" + partes[2]);


            PlanCuotaTienda planCuota = new PlanCuotaTienda();

            PlanPagoTienda planDePago = null;
            if ( partes[0]!=null ) { planDePago = eComunManager.consultaPlanPago(em, Integer.parseInt(partes[0])); }
            planCuota.setPlanPago(planDePago);

            planCuota.setCantCuotas(Integer.parseInt(partes[1]));
            planCuota.setImporteTotal(Double.parseDouble(partes[2]));

            ArrayList<CuotaPagoTienda> cuotasDelPlan = new ArrayList<CuotaPagoTienda>();

            String[] cuotasVec = partes[3].split("#"); // 1:4742#
            for(int x = 0; x < cuotasVec.length; x++){
                String nroCuota = cuotasVec[x].split(":")[0];
                String importeCuota = cuotasVec[x].split(":")[1];
                //logger.info(" CUOTA " + nroCuota + " X $" + importeCuota);

                CuotaPagoTienda cuotaPago = new CuotaPagoTienda();
                cuotaPago.setNroCuota(Integer.parseInt(nroCuota));
                cuotaPago.setImporte(Double.parseDouble(importeCuota));
                cuotasDelPlan.add(cuotaPago);
            }
            planCuota.setCuotas(cuotasDelPlan);
            planesDeCuotas.add(planCuota);
        }

        cotizacion.setPlanesDeCuotas(planesDeCuotas);
        logger.info(logEncabezado + " - POST - PLANES de CUOTAS - OK - " + Integer.toString(planesDeCuotas.size()) + " planes.");

        PlanCoberturaTienda planCoberturaObj = null;
        if ( planCobertura != null ) {
            planCoberturaObj = eComunManager.consultaPlanCoberturaRamoProducto(em,ramoBici,productoBici,planCobertura);
        }
        cotizacion.setPlanCobertura(planCoberturaObj);

        logger.info(logEncabezado + " - RESULT : " + cotizacion.toString());
        tiempoTotalMetodo = System.currentTimeMillis() - tiempoInicioMetodo;
        logger.info(logEncabezado + " - FIN - Tiempo Ejecucion METODO [" + Long.toString(tiempoTotalMetodo)+"] milisegundos");

        return cotizacion;
    }



    /**
     *
     * @param em
     * @param planPago
     * @param tipoDocumento
     * @param documento
     * @param fechaFactura
     * @param tipoBicicleta
     * @param fechaNacimientoCliente
     * @param marca
     * @param serie
     * @param modelo
     * @param nroCotizacion
     * @param consumoFinal
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    @Override
    public PolizaBiciTienda emitirBici( EntityManager em,
                                        String planPago,
                                        String tipoDocumento,
                                        String documento,
                                        Date   fechaFactura,
                                        String tipoBicicleta,
                                        Date   fechaNacimientoCliente,
                                        String marca,
                                        String serie,
                                        String modelo,
                                        long   nroCotizacion,
                                        String consumoFinal ) throws Exception, BSEExceptionTienda{

        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        long tiempoInicioAux = 0;
        long tiempoTotalAux  = 0;
        String logEncabezado = "BICI - emitirBici (" + Long.toString(tiempoInicioMetodo) + ") - EMIBICI";

        // LOGUEO PARAMETROS
        logEmitirBiciParametros( logEncabezado + " - INICIO",
                                 planPago     , tipoDocumento, documento,
                                 fechaFactura , tipoBicicleta, fechaNacimientoCliente,
                                 marca        , serie        , modelo,
                                 nroCotizacion, consumoFinal );

        IEComunTienda eComunManager = FabricaNegocioTienda.getFabricaNegocio().getEComunMgr();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CONTROL de PARAMETROS - OBLIGATORIEDAD
        if (tipoDocumento == null || tipoDocumento.equals(""))
            throw new BSEExceptionTienda(CodigosErrorTienda.tipo_documento_invalido);
        if (documento == null || documento.equals(""))
            throw new BSEExceptionTienda(CodigosErrorTienda.documento_invalido);
        if (nroCotizacion == 0)
            throw new BSEExceptionTienda(CodigosErrorTienda.cotizacion_invalida);
        if (!consumoFinal.equals("S") && !consumoFinal.equals("N"))
            throw new BSEExceptionTienda(CodigosErrorTienda.consumidor_final_invalido);
        if ( (marca == null || marca.equals("") || modelo == null || modelo.equals("") ||
              serie == null || serie.equals("") || tipoBicicleta == null || tipoBicicleta.equals("")) )
            throw new BSEExceptionTienda(CodigosErrorTienda.faltan_datos_bicicleta);
        logger.info(logEncabezado + " - PARAMETROS - OK");

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Valida Fecha Factura
        Date ahora = new Date();
        /*
        long edadEnDias = (ahora.getTime() - fechaNacimientoCliente.getTime()) / 1000 / 60 / 60 / 24;
        int anos = Double.valueOf(edadEnDias / 365.25d).intValue();
        if (anos < 18 || anos > 65)
          throw new BSEException(CodigosError.edad_invalida);
        */

        long edadFacturaEnDias = (ahora.getTime() - fechaFactura.getTime()) / 1000 / 60 / 60 / 24;
        int anosFactura = Double.valueOf(edadFacturaEnDias / 365.25d).intValue();
        if (anosFactura > 2 || edadFacturaEnDias < 0)
            throw new BSEExceptionTienda(CodigosErrorTienda.fecha_factura_invalida);
        logger.info(logEncabezado + " - FECHA FACTURA (ANTIGUEDAD) - OK");

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CARGA de DATOS desde la CONFIGURACION
        int    sucursal  = Integer.parseInt(CodigosTienda.getCodigos().getSucursalEmisionBici(em));
        int    ramo      = Integer.parseInt(CodigosTienda.getCodigos().getRamoEmisionBici(em));
        String producto  = CodigosTienda.getCodigos().getProductoEmisionBici(em);
        String productor = CodigosTienda.getCodigos().getProductorEmisionBici(em);
        String moneda    = CodigosTienda.getCodigos().getMonedaEmisionBici(em);
        logger.info(logEncabezado + " - DATOS desde CONFIGURACION - OK");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Valida Nro. Cotizacion
        boolean cotizacionOk = eComunManager.validarExistenciaCotizacion( em,
                                                                          sucursal, nroCotizacion, ramo, producto);
        if (!cotizacionOk)
            throw new BSEExceptionTienda(CodigosErrorTienda.cotizacion_invalida);
        logger.info(logEncabezado + " - NRO. COTIZACION - OK");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // LOGUEO PARAMETROS de la Invocación PL
        logGuiones(logEncabezado);
        logEmitirBiciPl2( logEncabezado, "PACK_EMI_MIBSE.PRO_EMITIR_BICI",
                          tipoDocumento, documento, sucursal    , ramo                  , productor,
                          marca        , modelo   , serie       , tipoBicicleta         , nroCotizacion,
                          consumoFinal , planPago , fechaFactura, fechaNacimientoCliente);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // INVOCACION PL - PACK_EMI_MIBSE.PRO_EMITIR_VARIOS
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Query q = em.createNamedQuery("PRO_EMITIR_BICI");

        q.setParameter("P_TP_DOCUMENTO"      , tipoDocumento);
        q.setParameter("P_NRO_DOCUMENTO"     , documento);
        q.setParameter("P_SUCURSAL"          , String.valueOf(sucursal));
        q.setParameter("P_RAMO"              , String.valueOf(ramo));
        q.setParameter("P_PRODUCTO"          , producto);
        q.setParameter("P_MARCA"             , marca);
        q.setParameter("P_MODELO"            , modelo);
        q.setParameter("P_SERIE"             , serie);
        q.setParameter("P_TIPO_VEHICULO"     , tipoBicicleta);
        q.setParameter("P_COTIZACION_EMITIDA", Long.valueOf(nroCotizacion));
        q.setParameter("P_CONSUMO_FINAL"     , consumoFinal);
        q.setParameter("P_PLAN_PAGO"         , Integer.valueOf(planPago));
        q.setParameter("P_FECHA_FACTURA"     , fechaFactura);
        q.setParameter("P_FECHA_NAC_CLI"     , fechaNacimientoCliente);

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
        //      -> R[0]  = P_POLIZA_EMITIDA              - type=Long.class
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

        int    nroPoliza      = ((Integer)r[0]).intValue();
        double premio         = ((Double)r[1]).doubleValue();
        double premioFacturar = ((Double)r[2]).doubleValue();
        Date   fechaDesde     = (Date)r[3];
        Date   fechaHasta     = (Date)r[4];
        String planCobertura  = (String)r[5];
        String cuotas         = (String)r[7];

        PolizaBiciTienda polizaBici = new PolizaBiciTienda();

        RamoTienda ramoObj = em.find(RamoTienda.class, Integer.valueOf(ramo));
        polizaBici.setRamo(ramoObj);

        ProductoTienda productoObj = em.find(ProductoTienda.class, producto);
        polizaBici.setProducto(productoObj);

        polizaBici.setSucursal(200);
        polizaBici.setNroCotizacion(nroCotizacion);
        polizaBici.setTipoDocumento(tipoDocumento);
        polizaBici.setNroDocumento(documento);
        polizaBici.setProductor(Integer.valueOf(productor));

        MonedaTienda monedaObj = eComunManager.consultaMoneda(em, moneda);
        polizaBici.setMoneda(monedaObj);

        polizaBici.setPremio(premio);
        polizaBici.setPremioFacturar(premioFacturar);

        polizaBici.setFechaDesde(fechaDesde);
        polizaBici.setFechaHasta(fechaHasta);

        PlanCoberturaTienda planCoberturaObj = null;
        if (planCobertura!=null) { planCoberturaObj = eComunManager.consultaPlanCoberturaRamo(em, ramo, planCobertura);}
        polizaBici.setPlanCobertura(planCoberturaObj);


        PlanPagoTienda planPagoObj = null;
        if (planPago!=null) { planPagoObj = eComunManager.consultaPlanPago(em, Integer.parseInt(planPago));  }
        polizaBici.setPlanPago(planPagoObj);

        polizaBici.setNroPoliza(nroPoliza);
        polizaBici.setFechaFactura(fechaFactura);
        polizaBici.setTipoBicicleta(tipoBicicleta);
        polizaBici.setFechaNacimientoCliente(fechaNacimientoCliente);
        polizaBici.setMarca(marca);
        polizaBici.setSerie(serie);
        polizaBici.setModelo(modelo);

        logger.info(logEncabezado + " - PRE  - CUOTAS - OK");
        cuotas=cuotas.replace(" ", "");
        cuotas=cuotas.replace("\\13", "");
        cuotas=cuotas.replace("\\10", "");
        //Pattern.quote(arg0)

        //String regex = "\b<cuota>\b";
        cuotas = cuotas.replace("<cuota>", "%");
        String[] vecCuotas = cuotas.split("%");

        ArrayList<CuotaPagoTienda> listaCuotas = new ArrayList<CuotaPagoTienda>();
        for(int z = 0; z < vecCuotas.length; z++){
            if (vecCuotas[z] != null && !vecCuotas[z].trim().equals("")){
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

        polizaBici.setCuotas(listaCuotas);
        logger.info(logEncabezado + " - POST - CUOTAS - OK - " + Integer.toString(listaCuotas.size()) + " cuotas.");

        logger.info(logEncabezado + " - RESULT : " + polizaBici.toString());
        tiempoTotalMetodo = System.currentTimeMillis() - tiempoInicioMetodo;
        logger.info(logEncabezado + " - FIN - Tiempo Ejecucion METODO [" + Long.toString(tiempoTotalMetodo)+"] milisegundos");
        return polizaBici;
    }



    /**
     *
     * @param em
     * @param tipoDocumento
     * @param nroDocumento
     * @param nroCotizacion
     * @param nroCertificado
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    @Override
    public String clienteConDeuda( EntityManager em,
                                   String tipoDocumento,
                                   String nroDocumento,
                                   Integer nroCotizacion,
                                   Integer nroCertificado ) throws Exception, BSEExceptionTienda {

        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        long tiempoInicioAux = 0;
        long tiempoTotalAux  = 0;
        String logEncabezado = "BICI - clienteConDeuda (" + Long.toString(tiempoInicioMetodo) + ") - CLIDEUDA";

        // LOGUEO PARAMETROS
        logClienteConDeudaParametros( logEncabezado + " - INICIO",
                                      tipoDocumento, nroDocumento, nroCotizacion, nroCertificado);

        if (tipoDocumento == null || tipoDocumento.equals(""))
            throw new BSEExceptionTienda(CodigosErrorTienda.tipo_documento_invalido);
        if (nroDocumento == null || nroDocumento.equals(""))
            throw new BSEExceptionTienda(CodigosErrorTienda.documento_invalido);
        if (nroCotizacion == 0)
            throw new BSEExceptionTienda(CodigosErrorTienda.cotizacion_invalida);

        // LOGUEO PARAMETROS de la Invocación PL
        logGuiones(logEncabezado);
        logClienteConDeudaPl( logEncabezado + " - PACK_EMI_MIBSE.PRO_CONTROLAR_CLIENTE_DEUDA - PARAMETROS",
                              tipoDocumento, nroDocumento, nroCotizacion, nroCertificado );

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // INVOCACION PL - PACK_EMI_MIBSE.PRO_CONTROLAR_CLIENTE_DEUDA
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Query q = em.createNamedQuery("PRO_CONTROLAR_CLIENTE_DEUDA");

        q.setParameter("P_TP_DOCUMENTO", tipoDocumento);
        q.setParameter("P_NRO_DOCUMENTO", nroDocumento);
        q.setParameter("P_NU_COTIZACION", nroCotizacion);
        q.setParameter("P_NU_CERTIFICADO", nroCertificado);

        tiempoInicioAux = System.currentTimeMillis();

        @SuppressWarnings("unchecked")
        List<Object[]> l = q.getResultList();
        Object[] r = l.get(0);

        tiempoTotalAux = System.currentTimeMillis() - tiempoInicioAux;
        logger.info(logEncabezado + " - Tiempo Ejecucion PL ["+Long.toString(tiempoTotalAux)+"] milisegundos");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // VERIFICO RESULTADO y en caso de no obtener RESULTADO-> Se DISPARA Exception
        //      -> R[4]  = P_RESULTADO  - type=String.class
        if (r[4] != null) {
            logger.info(logEncabezado+" - R4 = ["+r[4].toString()+"]");
        } else {
            logger.info(logEncabezado+" - R4 = [null]");

            tiempoTotalMetodo = System.currentTimeMillis() - tiempoInicioMetodo;
            logger.info(logEncabezado+" - Tiempo Ejecucion METODO ["+Long.toString(tiempoTotalMetodo)+"] milisegundos");

            throw new BSEExceptionTienda(CodigosErrorTienda.no_existe_cliente);
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        logGuiones(logEncabezado);

        String noTieneDeuda = r[4].toString();

        if (noTieneDeuda.equals("F") || noTieneDeuda.equals("N")){
            logger.info(logEncabezado + " - RESULT - TieneDeuda [S]");
            tiempoTotalMetodo = System.currentTimeMillis() - tiempoInicioMetodo;
            logger.info(logEncabezado + " - FIN - Tiempo Ejecucion METODO [" + Long.toString(tiempoTotalMetodo)+"] milisegundos");

            return "S";

        }else{
            if (!noTieneDeuda.equals("S"))
                throw new BSEExceptionTienda(CodigosErrorTienda.error_emision_rector);
        }

        logger.info(logEncabezado + " - RESULT - TieneDeuda [N]");
        tiempoTotalMetodo = System.currentTimeMillis() - tiempoInicioMetodo;
        logger.info(logEncabezado + " - FIN - Tiempo Ejecucion METODO [" + Long.toString(tiempoTotalMetodo)+"] milisegundos");

        return "N";
    }



    /**
     *
     * @param em
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    @Override
    public List<TipoBiciTienda> consultaTiposBicicletas(EntityManager em) throws Exception, BSEExceptionTienda {
        String logEncabezado = "BICI - consultaTiposBicicletas";
        //logger.info(logEncabezado + " - INICIO");

        ArrayList<TipoBiciTienda> lista = new ArrayList<TipoBiciTienda>();

        Query query = em.createNamedQuery("CretTablasTienda.findTabla");
        query.setParameter("codigoTabla", Integer.valueOf("170092"));

        List<CretTablasTienda> result = UtilTienda.castList(CretTablasTienda.class, query.getResultList());

        if (result != null && result.size() > 0) {
            for (int i = 0; i < result.size(); i++) {
                CretTablasTienda tabla = (CretTablasTienda) result.get(i);
                TipoBiciTienda tb = new TipoBiciTienda(tabla.getDato1(), tabla.getDescripcion());
                lista.add(tb);
            }
        }

        logger.info(logEncabezado + " - RESULT - [" + Integer.toString(lista.size()) + "] elementos.");
        return lista;
    }



    /**
     *
     * @param logEncabezado
     * @param planCobertura
     * @param valorBicicleta
     * @param vigenciaSeguro
     */
    private void logCotizarBiciParametros( String logEncabezado,
                                           String  planCobertura,
                                           Double  valorBicicleta,
                                           Integer vigenciaSeguro ) {
        String logParametros =   "PlanCobertura ["  + planCobertura
                             + "] ValorBicicleta [" + ((valorBicicleta!=null)?valorBicicleta.toString():"null")
                             + "] VigenciaSeguro [" + ((vigenciaSeguro!=null)?vigenciaSeguro.toString():"null")
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
     * @param valorBicicleta
     * @param vigenciaSeguro
     * @param planPago
     * @param planCobertura
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
     */
    private void logCotizarBiciPl2( String  logEncabezado , String titulo         ,
                                    String  tipoDocumento , String  documento     ,
                                    int     sucursal      , int     ramo          , String  producto       ,
                                    String  productor     , Date    fechaDesde    ,
                                    Double  valorBicicleta, Integer vigenciaSeguro, Integer planPago      ,
                                    String  planCobertura , String  moneda        , String  tipoCalculo   ,
                                    String  formaPago     , String  vigTecnica    , String  medioPago      ,
                                    String  origenPago    , String  tipoFact      , String  promocion      ,
                                    String  renovacion    , String  usuarioWeb    , String  consumidorFinal,
                                    String  factMail      , String  coaseguro     , String  emitir ) {

        Logueo logueo = new Logueo();
        int pad = 19;
        logueo.setEncabezado(titulo);
        logueo.setParametro("P_TP_DOCUMENTO"    , tipoDocumento             , pad);
        logueo.setParametro("P_NRO_DOCUMENTO"   , documento                 , pad);
        logueo.setParametro("P_SUCURSAL"        , Integer.toString(sucursal), pad);
        logueo.setParametro("P_RAMO"            , Integer.toString(ramo)    , pad);
        logueo.setParametro("P_PRODUCTO"        , producto                  , pad);
        logueo.setParametro("P_PRODUCTOR"       , productor                 , pad);
        logueo.setParametro("P_FECHA_DESDE"     , ((fechaDesde!=null)?fechaDesde.toString():"null")        , pad);
        logueo.setParametro("P_VALOR_BICI"      , ((valorBicicleta!=null)?valorBicicleta.toString():"null"), pad);
        logueo.setParametro("P_VIGENCIA_SEGURO" , ((vigenciaSeguro!=null)?vigenciaSeguro.toString():"null"), pad);
        logueo.setParametro("P_PLAN_PAGO"       , ((planPago!=null)?planPago.toString():"null"), pad);
        logueo.setParametro("P_PLAN_COBERTURA"  , planCobertura             , pad);
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
            result = "R[3]=P_FECHA_HASTA";
            break;
        case 4:
            result = "R[4]=P_CUOTAS";
            break;
        case 5:
            result = "R[5]=P_PLANES";
            break;
        case 6:
            result = "R[6]=P_RESULTADO";
            break;
        case 7:
            result = "R[7]=P_MENSAJE_EMISION";
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
     * @param planPago
     * @param tipoDocumento
     * @param documento
     * @param fechaFactura
     * @param tipoBicicleta
     * @param fechaNacCliente
     * @param marca
     * @param serie
     * @param modelo
     * @param nroCotizacion
     * @param consumoFinal
     */
    private void logEmitirBiciParametros( String logEncabezado,
                                          String planPago     , String tipoDocumento, String documento,
                                          Date   fechaFactura , String tipoBicicleta, Date   fechaNacCliente,
                                          String marca        , String serie        , String modelo,
                                          long   nroCotizacion, String consumoFinal ) {

        String logParametros =   "PlanPago ["        + planPago
                             + "] TipoDocumento ["   + tipoDocumento
                             + "] Documento ["       + documento
                             + "] FechaFactura ["    + ((fechaFactura!=null)?fechaFactura.toString():"null")
                             + "] TipoBicicleta ["   + tipoBicicleta
                             + "] FechaNacCliente [" + ((fechaNacCliente!=null)?fechaNacCliente.toString():"null")
                             + "] Marca ["           + marca
                             + "] Serie ["           + serie
                             + "] Modelo ["          + modelo
                             + "] NroCotizacion ["   + Long.toString(nroCotizacion)
                             + "] ConsumoFinal ["    + consumoFinal
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
     * @param marca
     * @param modelo
     * @param serie
     * @param tipoBicicleta
     * @param nroCotizacion
     * @param consumoFinal
     * @param planPago
     * @param fechaFactura
     * @param fechaNacCliente
     */
    private void logEmitirBiciPl2( String logEncabezado, String titulo      ,
                                   String tipoDocumento, String documento   ,
                                   int    sucursal     , int    ramo        , String producto,
                                   String marca        , String modelo      , String serie   , String tipoBicicleta,
                                   long   nroCotizacion, String consumoFinal, String planPago,
                                   Date   fechaFactura , Date fechaNacCliente  ) {
        Logueo logueo = new Logueo();
        int pad = 21;
        logueo.setEncabezado(titulo);
        logueo.setParametro("P_TP_DOCUMENTO"      , tipoDocumento               , pad);
        logueo.setParametro("P_NRO_DOCUMENTO"     , documento                   , pad);
        logueo.setParametro("P_SUCURSAL"          , Integer.toString(sucursal)  , pad);
        logueo.setParametro("P_RAMO"              , Integer.toString(ramo)      , pad);
        logueo.setParametro("P_PRODUCTO"          , producto                    , pad);
        logueo.setParametro("P_MARCA"             , marca                       , pad);
        logueo.setParametro("P_MODELO"            , modelo                      , pad);
        logueo.setParametro("P_SERIE"             , serie                       , pad);
        logueo.setParametro("P_TIPO_VEHICULO"     , tipoBicicleta               , pad);
        logueo.setParametro("P_COTIZACION_EMITIDA", Long.toString(nroCotizacion), pad);
        logueo.setParametro("P_CONSUMO_FINAL"     , consumoFinal                , pad);
        logueo.setParametro("P_PLAN_PAGO"         , planPago                    , pad);
        logueo.setParametro("P_FECHA_FACTURA"     , ((fechaFactura!=null)?fechaFactura.toString():"null") , pad);
        logueo.setParametro("P_FECHA_NAC_CLI"     , ((fechaNacCliente!=null)?fechaNacCliente.toString():"null") , pad);
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
     * @param tipoDocumento
     * @param nroDocumento
     * @param nroCotizacion
     * @param nroCertificado
     */
    private void logClienteConDeudaParametros( String  logEncabezado,
                                               String  tipoDocumento,
                                               String  nroDocumento,
                                               Integer nroCotizacion,
                                               Integer nroCertificado ) {

        String logParametros =   "TipoDocumento ["  + tipoDocumento
                             + "] NroDocumento ["   + nroDocumento
                             + "] NroCotizacion ["  + ((nroCotizacion!=null)?Integer.toString(nroCotizacion):"null")
                             + "] NroCertificado [" + ((nroCertificado!=null)?Integer.toString(nroCertificado):"null")
                             + "]";
        logger.info(logEncabezado + " - Parametros -> " + logParametros);
    }

    /**
     *
     * @param logEncabezado
     * @param tipoDocumento
     * @param nroDocumento
     * @param nroCotizacion
     * @param nroCertificado
     */
    private void logClienteConDeudaPl( String  logEncabezado,
                                       String  tipoDocumento,
                                       String  nroDocumento,
                                       Integer nroCotizacion,
                                       Integer nroCertificado ) {
        String logInvocacion = logEncabezado + " ->"
                             +  " P_TP_DOCUMENTO ["   + tipoDocumento
                             + "] P_NRO_DOCUMENTO ["  + nroDocumento
                             + "] P_NU_COTIZACION ["  + ((nroCotizacion!=null)?Integer.toString(nroCotizacion):"null")
                             + "] P_NU_CERTIFICADO [" + ((nroCertificado!=null)?Integer.toString(nroCertificado):"null")
                             + "]";
        logger.info(logInvocacion);
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
    private void logCotizarBiciPl( String  logEncabezado,
                                   String  tipoDocumento , String  documento,
                                   int     sucursal      , int     ramo          , String producto       ,
                                   String  productor     , Date    fechaDesde    ,
                                   Double  valorBicicleta, Integer vigenciaSeguro,
                                   Integer planPago      , String  planCobertura ,
                                   String  moneda        , String  tipoCalculo   ,
                                   String  formaPago     , String  vigTecnica    , String medioPago      ,
                                   String  origenPago    , String  tipoFact      , String promocion      ,
                                   String  renovacion    , String  usuarioWeb    , String consumidorFinal,
                                   String  factMail      , String  coaseguro     , String emitir ) {

        String logInvocacion = logEncabezado + " ->"
                             +  " P_TP_DOCUMENTO ["     + tipoDocumento
                             + "] P_NRO_DOCUMENTO ["    + documento
                             + "] P_SUCURSAL ["         + String.valueOf(sucursal)
                             + "] P_RAMO ["             + String.valueOf(ramo)
                             + "] P_PRODUCTO ["         + producto
                             + "] P_PRODUCTOR ["        + productor
                             + "] P_FECHA_DESDE ["      + ((fechaDesde!=null)?fechaDesde.toString():"null")
                             + "] P_VALOR_BICI ["       + ((valorBicicleta!=null)?valorBicicleta.toString():"null")
                             + "] P_VIGENCIA_SEGURO ["  + ((vigenciaSeguro!=null)?vigenciaSeguro.toString():"null")
                             + "] P_PLAN_PAGO ["        + ((planPago!=null)?planPago.toString():"null")
                             + "] P_PLAN_COBERTURA ["   + planCobertura
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
                             + "]";
        logger.info(logInvocacion);
    }
    */

    /*
    private void logEmitirBiciPl( String logEncabezado,
                                  String tipoDocumento, String documento   ,
                                  int    sucursal     , int    ramo        , String producto,
                                  String marca        , String modelo      , String serie   , String tipoBicicleta,
                                  long   nroCotizacion, String consumoFinal, String planPago,
                                  Date   fechaFactura , Date fechaNacCliente  ) {
        String logInvocacion = logEncabezado + " ->"
                             +  " P_TP_DOCUMENTO ["       + tipoDocumento
                             + "] P_NRO_DOCUMENTO ["      + documento
                             + "] P_SUCURSAL ["           + String.valueOf(sucursal)
                             + "] P_RAMO ["               + String.valueOf(ramo)
                             + "] P_PRODUCTO ["           + producto
                             + "] P_MARCA ["              + marca
                             + "] P_MODELO ["             + modelo
                             + "] P_SERIE ["              + serie
                             + "] P_TIPO_VEHICULO ["      + tipoBicicleta
                             + "] P_COTIZACION_EMITIDA [" + Long.toString(nroCotizacion)
                             + "] P_CONSUMO_FINAL ["      + consumoFinal
                             + "] P_PLAN_PAGO ["          + planPago
                             + "] P_FECHA_FACTURA ["      + ((fechaFactura!=null)?fechaFactura.toString():"null")
                             + "] P_FECHA_NAC_CLI ["      + ((fechaNacCliente!=null)?fechaNacCliente.toString():"null")
                             + "]";
        logger.info(logInvocacion);
    }
    */

}
