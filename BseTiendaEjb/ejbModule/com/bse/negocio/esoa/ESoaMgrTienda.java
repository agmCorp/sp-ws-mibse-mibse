package com.bse.negocio.esoa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bse.accesodatos.comun.CretTablasTienda;
import com.bse.accesodatos.esoa.CategoriaVehiculoTienda;
import com.bse.accesodatos.esoa.CotizacionSoaTienda;
import com.bse.accesodatos.esoa.CuotaPagoTienda;
import com.bse.accesodatos.esoa.MarcaVehiculoTienda;
import com.bse.accesodatos.esoa.MonedaTienda;
import com.bse.accesodatos.esoa.PlanCoberturaTienda;
import com.bse.accesodatos.esoa.PlanPagoTienda;
import com.bse.accesodatos.esoa.PolizaPortalPKTienda;
import com.bse.accesodatos.esoa.PolizaPortalTienda;
import com.bse.accesodatos.esoa.PolizaSoaTienda;
import com.bse.accesodatos.esoa.ProductoTienda;
import com.bse.accesodatos.esoa.RamoTienda;
import com.bse.negocio.FabricaNegocioTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import com.bse.negocio.comun.CodigosErrorTienda;
import com.bse.negocio.comun.CodigosTienda;
import com.bse.negocio.comun.IEComunTienda;
import com.bse.negocio.comun.UtilTienda;
import com.bse.servicios.utilitario.log.Logueo;

public class ESoaMgrTienda implements IESoaTienda{

    private static final Logger logger = LogManager.getLogger(ESoaMgrTienda.class);

    /**
     * CONSTRUCTOR
     */
    public ESoaMgrTienda() {
    }

    /**
     *
     * @return
     */
    public static IESoaTienda getESoaMgr() {
        return new ESoaMgrTienda();
    }



    /**
     *
     * @param em
     * @param planCobertura
     * @param marcaVehiculo
     * @param anioVehiculo
     * @param categoriaVehiculo
     * @param planPago
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    @Override
    public CotizacionSoaTienda cotizarSoaAnonimo( EntityManager em,
                                                  String planCobertura,
                                                  String marcaVehiculo,
                                                  String anioVehiculo,
                                                  String categoriaVehiculo,
                                                  int    planPago ) throws Exception, BSEExceptionTienda {

        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        long tiempoInicioAux = 0;
        long tiempoTotalAux  = 0;
        String logEncabezado = "SOA - cotizarSoa (" + Long.toString(tiempoInicioMetodo) + ") - COTSOA";

        // LOGUEO PARAMETROS
        logCotizarSoaParametros( logEncabezado + " - INICIO",
                                 planCobertura, marcaVehiculo, anioVehiculo, categoriaVehiculo, planPago);

        IEComunTienda eComunManager = FabricaNegocioTienda.getFabricaNegocio().getEComunMgr();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CONTROL de PARAMETROS - OBLIGATORIEDAD
        if ((marcaVehiculo == null) || marcaVehiculo.equals("")) {
            throw new BSEExceptionTienda(CodigosErrorTienda.marca_vehiculo_invalida);
        }
        if ((anioVehiculo == null) || anioVehiculo.equals("")) {
            throw new BSEExceptionTienda(CodigosErrorTienda.anio_vehiculo_invalido);
        }
        if ((categoriaVehiculo == null) || categoriaVehiculo.equals("")) {
            throw new BSEExceptionTienda(CodigosErrorTienda.categoria_vehiculo_invalida);
        }
        if ((planCobertura == null) || planCobertura.equals("")) {
            throw new BSEExceptionTienda(CodigosErrorTienda.plan_cobertura_invalido);
        }

        // Valida Plan de Pago segun configuracion
        String planesPago = CodigosTienda.getCodigos().getPlanesPagoSoa(em);
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

        logger.info(logEncabezado + " - PARAMETROS - OK");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CARGA de DATOS desde CONFIGURACION para INVOCACION PL
        String tipoDocumento     = " ";
        String documento         = " ";
        int    sucursal          = Integer.parseInt(CodigosTienda.getCodigos().getSucursalEmisionSoa(em));
        int    ramoSoa           = Integer.parseInt(CodigosTienda.getCodigos().getRamoEmisionSoa(em));
        String productoSoa       = CodigosTienda.getCodigos().getProductoEmisionSoa(em);
        String productor         = CodigosTienda.getCodigos().getProductorEmisionSoa(em);

        Date   fechaDesde        = new Date();
        String matriculaVehiculo = "AAA1000";
        String padronVehiculo    = "0";
        String motorVehiculo     = "0";
        String chasisVehiculo    = "";

        String moneda            = CodigosTienda.getCodigos().getMonedaEmisionSoa(em);
        String tipoCalculo       = CodigosTienda.getCodigos().getTipoCalculoEmisionSoa(em).trim();
        String formaPago         = CodigosTienda.getCodigos().getFormaPagoEmisionSoa(em).trim();
        String vigTecnica        = CodigosTienda.getCodigos().getVigTecnicaEmisionSoa(em).trim();
        String medioPago         = CodigosTienda.getCodigos().getMedioPagoEmisionSoa(em).trim();
        String origenPago        = CodigosTienda.getCodigos().getOrigenPagoEmisionSoa(em).trim();
        String tipoFact          = CodigosTienda.getCodigos().getTipoFactEmisionSoa(em).trim();
        String promocion         = CodigosTienda.getCodigos().getPromocionEmisionSoa(em).trim();
        String renovacion        = CodigosTienda.getCodigos().getRenovacionEmisionSoa(em).trim();
        String usuarioWeb        = CodigosTienda.getCodigos().getUsuarioWebEmisionSoa(em).trim();

        String  consumidorFinal = "N";
        String  factMail        = "N";
        String  coaseguro       = "N";
        String  emitir          = "N";
        logger.info(logEncabezado + " - DATOS desde CONFIGURACION - OK");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // LOGUEO PARAMETROS de la Invocaci�n PL
        logGuiones(logEncabezado);
        logCotizarSoaPl2( logEncabezado, "PACK_EMI_MIBSE.PRO_COTIZAR_SOA",
                          tipoDocumento , documento        , sucursal         , ramoSoa       ,
                          productoSoa   , productor        , fechaDesde       , marcaVehiculo ,
                          anioVehiculo  , categoriaVehiculo, matriculaVehiculo, padronVehiculo,
                          motorVehiculo , chasisVehiculo   , planCobertura    , planPago      ,
                          moneda        , tipoCalculo      , formaPago        , vigTecnica    ,
                          medioPago     , origenPago       , tipoFact         , promocion     ,
                          renovacion    , usuarioWeb       , consumidorFinal  , factMail      ,
                          coaseguro     , emitir );

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // INVOCACION PL - PACK_EMI_MIBSE.PRO_COTIZAR_SOA
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Query q = em.createNamedQuery("PRO_COTIZAR_SOA");

        q.setParameter("P_TP_DOCUMENTO"      , tipoDocumento);
        q.setParameter("P_NRO_DOCUMENTO"     , documento);
        q.setParameter("P_SUCURSAL"          , sucursal);
        q.setParameter("P_RAMO"              , String.valueOf(ramoSoa));
        q.setParameter("P_PRODUCTO"          , productoSoa);
        q.setParameter("P_PRODUCTOR"         , productor);
        q.setParameter("P_FECHA_DESDE"       , fechaDesde);
        q.setParameter("P_MARCA_VEHICULO"    , marcaVehiculo);
        q.setParameter("P_ANIO_VEHICULO"     , anioVehiculo);
        q.setParameter("P_CATEGORIA_VEHICULO", categoriaVehiculo);
        q.setParameter("P_MATRICULA_VEHICULO", matriculaVehiculo);
        q.setParameter("P_PADRON_VEHICULO"   , padronVehiculo);
        q.setParameter("P_MOTOR_VEHICULO"    , motorVehiculo);
        q.setParameter("P_CHASIS_VEHICULO"   , chasisVehiculo);
        q.setParameter("P_PLAN_COBERTURA"    , planCobertura);
        q.setParameter("P_PLAN_PAGO"         , planPago);
        q.setParameter("P_MONEDA"            , moneda);
        q.setParameter("P_TIPO_CALCULO"      , tipoCalculo);
        q.setParameter("P_FORMA_PAGO"        , formaPago);
        q.setParameter("P_VIG_TECNICA"       , vigTecnica);
        q.setParameter("P_MEDIO_PAGO"        , medioPago);
        q.setParameter("P_ORIGEN_PAGO"       , origenPago);
        q.setParameter("P_TIPO_FACT"         , tipoFact);
        q.setParameter("P_PROMOCION"         , promocion);
        q.setParameter("P_RENOVACION"        , renovacion);
        q.setParameter("P_USUARIO_WEB"       , usuarioWeb);

        q.setParameter("P_CONSUMIDOR_FINAL"  , consumidorFinal);
        q.setParameter("P_FACT_MAIL"         , factMail);
        q.setParameter("P_COASEGURO"         , coaseguro);
        q.setParameter("P_EMITIR"            , emitir);

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
        if ((r[6] != null) && !((String)r[6]).equals("0")) {
            String codError  = (String)r[6];
            String descError = (r[7]!=null)?((String)r[7]):"";
            // 16/09/2022 - SCapretti - Definicion Leticia - Conversado telefonicamente
            //    - En vez de devolver codigo, se retorna la descripcion del error.
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

        CotizacionSoaTienda cotizacion = new CotizacionSoaTienda();

        RamoTienda ramo = em.find(RamoTienda.class, Integer.valueOf(ramoSoa));
        cotizacion.setRamo(ramo);

        ProductoTienda productoObj = em.find(ProductoTienda.class, productoSoa);
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
        cotizacion.setFechaHasta((Date)r[3]);

        PlanPagoTienda planPagoObj = eComunManager.consultaPlanPago(em, planPago);
        planPagoObj.setCodigo(planPago);
        cotizacion.setPlanPago(planPagoObj);

        MarcaVehiculoTienda marca = consultaMarcaVehiculo(em, marcaVehiculo);
        cotizacion.setMarcaVehiculo(marca);

        CategoriaVehiculoTienda categoria = consultaCategoriaVehiculo(em, categoriaVehiculo);
        categoria.setCodigo(categoriaVehiculo);
        cotizacion.setCategoriaVehiculo(categoria);

        PlanCoberturaTienda planCoberturaObj = eComunManager.consultaPlanCobertura(em, planCobertura);
        planCoberturaObj.setPlan(planCobertura);
        cotizacion.setPlanCobertura(planCoberturaObj);

        cotizacion.setMatriculaVehiculo(matriculaVehiculo);
        cotizacion.setPadronVehiculo(padronVehiculo);
        cotizacion.setChasisVehiculo(chasisVehiculo);
        cotizacion.setMotorVehiculo(motorVehiculo);
        cotizacion.setAnioVehiculo(anioVehiculo);

        // HASTA NUEVO AVISO - NO SE INCLUYEN LAS CUOTAS

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
     * @param matriculaVehiculo
     * @param padronVehiculo
     * @param motorVehiculo
     * @param chasisVehiculo
     * @param nroCotizacion
     * @param consumoFinal
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    @Override
    public PolizaSoaTienda emitirSoa( EntityManager em,
                                      String tipoDocumento,
                                      String documento,
                                      String matriculaVehiculo,
                                      String padronVehiculo,
                                      String motorVehiculo,
                                      String chasisVehiculo,
                                      long   nroCotizacion,
                                      String consumoFinal ) throws Exception, BSEExceptionTienda{

        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        long tiempoInicioAux = 0;
        long tiempoTotalAux  = 0;
        String logEncabezado = "SOA - emitirSoa (" + Long.toString(tiempoInicioMetodo) + ") - EMISOA";

        // LOGUEO PARAMETROS
        logEmitirSoaParametros( logEncabezado + " - INICIO",
                                tipoDocumento, documento     , matriculaVehiculo, padronVehiculo,
                                motorVehiculo, chasisVehiculo, nroCotizacion    , consumoFinal );

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
        if ( ((matriculaVehiculo == null) || matriculaVehiculo.equals("")) &&
             ((chasisVehiculo == null) || chasisVehiculo.equals("")) ) {
            throw new BSEExceptionTienda(CodigosErrorTienda.matricula_vehiculo_invalida);
        }
        logger.info(logEncabezado + " - PARAMETROS - OK");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CARGA de DATOS desde CONFIGURACION para INVOCACION PL
        int    sucursal    = Integer.parseInt(CodigosTienda.getCodigos().getSucursalEmisionSoa(em));
        int    ramoSoa     = Integer.parseInt(CodigosTienda.getCodigos().getRamoEmisionSoa(em));
        String productoSoa = CodigosTienda.getCodigos().getProductoEmisionSoa(em);
        String productor   = CodigosTienda.getCodigos().getProductorEmisionSoa(em);
        String moneda      = CodigosTienda.getCodigos().getMonedaEmisionSoa(em);
        logger.info(logEncabezado + " - DATOS desde CONFIGURACION - OK");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Valida Nro. Cotizacion - Obtiene Plan de Pago
        String sqlCot = "select CAZB_CAFR_CD_FRAGMENT, CAZB_CAFR_CD_FRAGMENT " +
                        "from cart_cotiza_banco " +
                        "where cazb_capj_cd_sucursal = ? and cazb_nu_cotizacion = ? and " +
                        "cazb_nu_consecutivo = 1 and cazb_carp_cd_ramo = ? and cazb_capu_cd_producto = ?";

        Query queryCot = em.createNativeQuery(sqlCot);
        queryCot.setParameter(1, sucursal);
        queryCot.setParameter(2, nroCotizacion);
        queryCot.setParameter(3, ramoSoa);
        queryCot.setParameter(4, productoSoa);
        @SuppressWarnings("unchecked")
        List<Object[]> resultCot = queryCot.getResultList();

        if ((resultCot == null) || (resultCot.size() == 0)) {
            throw new BSEExceptionTienda(CodigosErrorTienda.cotizacion_invalida);
        }
        logger.info(logEncabezado + " - NRO. COTIZACION - OK");

        int planPago = 0;
        for (int i = 0; i < resultCot.size(); i++) {
            Object[] row = resultCot.get(i);
            planPago = ((BigDecimal)row[0]).intValue();
        }
        logger.info(logEncabezado + " - PLAN de PAGO desde COTIZACION - [" + Integer.toString(planPago) + "]");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // LOGUEO PARAMETROS de la Invocaci�n PL
        logGuiones(logEncabezado);
        logEmitirSoaPl2( logEncabezado, "PACK_EMI_MIBSE.PRO_EMITIR_SOA",
                         tipoDocumento    , documento     , sucursal     , ramoSoa       , productoSoa,
                         matriculaVehiculo, padronVehiculo, motorVehiculo, chasisVehiculo, nroCotizacion, consumoFinal);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // INVOCACION PL - PACK_EMI_MIBSE.PRO_EMITIR_SOA
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Query q = em.createNamedQuery("PRO_EMITIR_SOA");

        q.setParameter("P_TP_DOCUMENTO"      , tipoDocumento);
        q.setParameter("P_NRO_DOCUMENTO"     , documento);
        q.setParameter("P_SUCURSAL"          , String.valueOf(sucursal));
        q.setParameter("P_RAMO"              , String.valueOf(ramoSoa));
        q.setParameter("P_PRODUCTO"          , productoSoa);
        q.setParameter("P_MATRICULA_VEHICULO", matriculaVehiculo);
        q.setParameter("P_PADRON_VEHICULO"   , padronVehiculo);
        q.setParameter("P_MOTOR_VEHICULO"    , motorVehiculo);
        q.setParameter("P_CHASIS_VEHICULO"   , chasisVehiculo);
        q.setParameter("P_COTIZACION_EMITIDA", nroCotizacion);
        q.setParameter("P_CONSUMO_FINAL"     , consumoFinal);

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
        //      -> R[3]  = P_MARCA_VEHICULO              - type=String.class
        //      -> R[4]  = P_ANIO_VEHICULO               - type=String.class
        //      -> R[5]  = P_CATEGORIA_VEHICULO          - type=String.class
        //      -> R[6]  = P_FECHA_DESDE                 - type=Date.class
        //      -> R[7]  = P_FECHA_HASTA                 - type=Date.class
        //      -> R[8]  = P_PLAN_COBERTURA              - type=String.class
        //      -> R[9]  = P_PLAN_PAGO                   - type=Integer.class
        //      -> R[10] = P_CUOTAS                      - type=String.class
        //      -> R[11] = P_RESULTADO                   - type=String.class
        //      -> R[12] = P_MENSAJE_EMISION             - type=String.class
        for (int i = 0; i < r.length; i++){
            if (r[i] != null) { logger.info(logEncabezado + " - " + logEmiteResult(i) + " = [" + r[i].toString() + "]");
            } else            { logger.info(logEncabezado + " - " + logEmiteResult(i) + " = [null]"); }
        }
        logger.info(logEncabezado + " - Tiempo Ejecucion PL ["+Long.toString(tiempoTotalAux)+"] milisegundos");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // En caso de ERROR, se retorna el error especificado por el PL
        if (r[11] != null) {
            String codError  = (String)r[11];
            String descError = (r[12]!=null)?((String)r[12]):"";
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

        int nroPoliza            = ((Integer)r[0]).intValue();
        double premio            = ((Double)r[1]).doubleValue();
        double premioFacturar    = ((Double)r[2]).doubleValue();
        String marcaVehiculo     = (String)r[3];
        String anioVehiculo      = (String)r[4];
        String categoriaVehiculo = (String)r[5];
        Date fechaDesde          = (Date)r[6];
        Date fechaHasta          = (Date)r[7];
        String planCobertura     = (String)r[8];
        String cuotas            = (String)r[10];

        PolizaSoaTienda polizaSoa = new PolizaSoaTienda();

        RamoTienda ramoObj = em.find(RamoTienda.class, Integer.valueOf(ramoSoa));
        polizaSoa.setRamo(ramoObj);

        ProductoTienda productoObj = em.find(ProductoTienda.class, productoSoa);
        polizaSoa.setProducto(productoObj);

        polizaSoa.setSucursal(200);
        polizaSoa.setNroCotizacion(nroCotizacion);
        polizaSoa.setTipoDocumento(tipoDocumento);
        polizaSoa.setNroDocumento(documento);
        polizaSoa.setProductor(Integer.valueOf(productor));

        MonedaTienda monedaObj = eComunManager.consultaMoneda(em, moneda);
        polizaSoa.setMoneda(monedaObj);

        polizaSoa.setPremio(premio);
        polizaSoa.setPremioFacturar(premioFacturar);

        polizaSoa.setFechaDesde(fechaDesde);
        polizaSoa.setFechaHasta(fechaHasta);

        PlanCoberturaTienda planCoberturaObj = eComunManager.consultaPlanCobertura(em, planCobertura);
        polizaSoa.setPlanCobertura(planCoberturaObj);

        PlanPagoTienda planPagoObj = eComunManager.consultaPlanPago(em, planPago);
        polizaSoa.setPlanPago(planPagoObj);

        MarcaVehiculoTienda marcaObj = consultaMarcaVehiculo(em, marcaVehiculo);
        polizaSoa.setMarcaVehiculo(marcaObj);

        CategoriaVehiculoTienda categoriaObj = consultaCategoriaVehiculo(em, categoriaVehiculo);
        polizaSoa.setCategoriaVehiculo(categoriaObj);

        polizaSoa.setMatriculaVehiculo(matriculaVehiculo);
        polizaSoa.setPadronVehiculo(padronVehiculo);
        polizaSoa.setChasisVehiculo(chasisVehiculo);
        polizaSoa.setMotorVehiculo(motorVehiculo);
        polizaSoa.setAnioVehiculo(anioVehiculo);
        polizaSoa.setNroPoliza(nroPoliza);

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
        polizaSoa.setCuotas(listaCuotas);
        logger.info(logEncabezado + " - POST - CUOTAS - OK - " + Integer.toString(listaCuotas.size()) + " cuotas.");

        logger.info(logEncabezado + " - RESULT : " + polizaSoa.toString());
        tiempoTotalMetodo = System.currentTimeMillis() - tiempoInicioMetodo;
        logger.info(logEncabezado + " - FIN - Tiempo Ejecucion METODO [" + Long.toString(tiempoTotalMetodo)+"] milisegundos");
        return polizaSoa;
    }



    /**
     *
     * @param em
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    // Desde - SOA (SERVICIO)
    @Override
    public List<MarcaVehiculoTienda> consultaMarcasVehiculos(EntityManager em) throws Exception, BSEExceptionTienda {
        String logEncabezado = "SOA - consultaMarcasVehiculos";

        ArrayList<MarcaVehiculoTienda> lista = new ArrayList<MarcaVehiculoTienda>();

        Query query = em.createNamedQuery("CretTablasTienda.findTabla");
        query.setParameter("codigoTabla", Integer.valueOf("140001"));

        List<CretTablasTienda> result = UtilTienda.castList(CretTablasTienda.class, query.getResultList());

        if ((result != null) && (result.size() > 0)) {
            for (int i = 0; i < result.size(); i++) {
                CretTablasTienda tabla = result.get(i);
                MarcaVehiculoTienda marca = new MarcaVehiculoTienda(tabla.getDato1(), tabla.getDescripcion());
                lista.add(marca);
            }
        }

        logger.info(logEncabezado + " - RESULT - [" + Integer.toString(lista.size()) + "] elementos.");
        return lista;
    }



    /**
     *
     * @param em
     * @param marca
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    // Desde - SOA (SERVICIO)
    // Desde - SOA (EJB)
    @Override
    public MarcaVehiculoTienda consultaMarcaVehiculo(EntityManager em, String marca) throws Exception, BSEExceptionTienda {
        String logEncabezado = "SOA - consultaMarcaVehiculo";
        String logParametros = " - PARAMETROS -> Marca [" + marca + "]";

        Query query = em.createNamedQuery("CretTablasTienda.findByClave");
        query.setParameter("codigoTabla", Integer.valueOf("140001"));
        query.setParameter("codigoDato", marca);

        List<CretTablasTienda> result = UtilTienda.castList(CretTablasTienda.class, query.getResultList());

        if ((result != null) && (result.size() > 0)) {
            CretTablasTienda tabla = result.get(0);
            MarcaVehiculoTienda marcaObj = new MarcaVehiculoTienda(tabla.getDato1(), tabla.getDescripcion());

            logger.info(logEncabezado + logParametros + " - RESULT -> ["+ marcaObj.toString() +"]");
            return marcaObj;
        }

        logger.info(logEncabezado + logParametros + " - RESULT -> [NO EXISTE MARCA VEHICULO]");
        throw new BSEExceptionTienda(CodigosErrorTienda.marca_vehiculo_invalida);
    }



    /**
     *
     * @param em
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    // Desde - SOA (SERVICIO)
    @Override
    public List<CategoriaVehiculoTienda> consultaCategoriasVehiculos(EntityManager em) throws Exception, BSEExceptionTienda {
        String logEncabezado = "SOA - consultaCategoriasVehiculos";

        ArrayList<CategoriaVehiculoTienda> lista = new ArrayList<CategoriaVehiculoTienda>();

        Query query = em.createNamedQuery("CretTablasTienda.findTabla");
        query.setParameter("codigoTabla", Integer.valueOf("141021"));

        List<CretTablasTienda> result = UtilTienda.castList(CretTablasTienda.class, query.getResultList());

        if ((result != null) && (result.size() > 0)) {
            for (int i = 0; i < result.size(); i++) {
                CretTablasTienda tabla = result.get(i);
                CategoriaVehiculoTienda cat = new CategoriaVehiculoTienda(tabla.getDato1(), tabla.getDescripcion());
                lista.add(cat);
            }
        }

        logger.info(logEncabezado + " - RESULT - [" + Integer.toString(lista.size()) + "] elementos.");
        return lista;
    }



    /**
     *
     * @param em
     * @param categoria
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    // Desde - SOA (SERVICIO)
    // Desde - SOA (EJB)
    @Override
    public CategoriaVehiculoTienda consultaCategoriaVehiculo(EntityManager em, String categoria) throws Exception, BSEExceptionTienda {
        String logEncabezado = "SOA - consultaCategoriaVehiculo";
        String logParametros = " - PARAMETROS -> Categoria [" + categoria + "]";

        Query query = em.createNamedQuery("CretTablasTienda.findByClave");
        query.setParameter("codigoTabla", Integer.valueOf("141021"));
        query.setParameter("codigoDato", categoria);

        List<CretTablasTienda> result = UtilTienda.castList(CretTablasTienda.class, query.getResultList());

        if ((result != null) && (result.size() > 0)) {
            CretTablasTienda tabla = result.get(0);
            CategoriaVehiculoTienda categoriaObj = new CategoriaVehiculoTienda(tabla.getDato1(), tabla.getDescripcion());

            logger.info(logEncabezado + logParametros + " - RESULT -> ["+ categoriaObj.toString() +"]");
            return categoriaObj;
        }
        logger.info(logEncabezado + logParametros + " - RESULT -> [NO EXISTE CATEGORIA VEHICULO]");
        throw new BSEExceptionTienda(CodigosErrorTienda.categoria_vehiculo_invalida);
    }



    /**
     *
     * @param em
     * @param sucursal
     * @param ramo
     * @param producto
     * @param nroCotizacion
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    @Override
    public void alertarPagoRedes(EntityManager em, int sucursal, int ramo, String producto, long nroCotizacion) throws Exception, BSEExceptionTienda {
        logger.info("SOA - alertarPagoRedes");

        try {
            if ((sucursal == 0) || (ramo == 0) || (producto == null) || producto.trim().equals("") || (nroCotizacion == 0)) {
                throw new BSEExceptionTienda(CodigosErrorTienda.datos_de_consulta_vacios);
            }

            PolizaPortalPKTienda pk = new PolizaPortalPKTienda();
            pk.setSucursal(sucursal);
            pk.setRamo(ramo);
            pk.setProducto(producto);
            pk.setNroCotizacion(nroCotizacion);
            PolizaPortalTienda polizaPortalObj = em.find(PolizaPortalTienda.class, pk);

            if (polizaPortalObj.getEstado() != PolizaPortalTienda.ESTADO_POLIZA_EMITIDA) {
                throw new BSEExceptionTienda(CodigosErrorTienda.cotizacion_invalida);
            }

            polizaPortalObj.setFechaMod(new Date());
            polizaPortalObj.setPagoRedes("S");
            em.merge(polizaPortalObj);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BSEExceptionTienda(CodigosErrorTienda.cotizacion_invalida);
        }
    }



    /**
     *
     * @param logEncabezado
     * @param planCobertura
     * @param marcaVehiculo
     * @param anioVehiculo
     * @param categoriaVehiculo
     * @param planPago
     */
    private void logCotizarSoaParametros( String logEncabezado,
                                          String planCobertura    , String marcaVehiculo, String anioVehiculo,
                                          String categoriaVehiculo, int    planPago ) {
        String logParametros =   "PlanCobertura [" + planCobertura
                             + "] Marca ["         + marcaVehiculo
                             + "] Anio ["          + anioVehiculo
                             + "] Categoria ["     + categoriaVehiculo
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
     * @param marcaVehiculo
     * @param anioVehiculo
     * @param categoriaVehiculo
     * @param matriculaVehiculo
     * @param padronVehiculo
     * @param motorVehiculo
     * @param chasisVehiculo
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
     */
    private void logCotizarSoaPl2( String  logEncabezado    , String titulo         ,
                                   String  tipoDocumento    , String  documento     ,
                                   int     sucursal         , int     ramo          , String producto         ,
                                   String  productor        , Date    fechaDesde    ,
                                   String  marcaVehiculo    , String  anioVehiculo  , String categoriaVehiculo,
                                   String  matriculaVehiculo, String  padronVehiculo,
                                   String  motorVehiculo    , String  chasisVehiculo,
                                   String  planCobertura    , int     planPago      ,
                                   String  moneda           , String  tipoCalculo   ,
                                   String  formaPago        , String  vigTecnica    , String medioPago        ,
                                   String  origenPago       , String  tipoFact      , String promocion        ,
                                   String  renovacion       , String  usuarioWeb    , String consumidorFinal  ,
                                   String  factMail         , String  coaseguro     , String emitir ) {
        Logueo logueo = new Logueo();
        int pad = 21;
        logueo.setEncabezado(titulo);
        logueo.setParametro("P_TP_DOCUMENTO"      , tipoDocumento             , pad);
        logueo.setParametro("P_NRO_DOCUMENTO"     , documento                 , pad);
        logueo.setParametro("P_SUCURSAL"          , Integer.toString(sucursal), pad);
        logueo.setParametro("P_RAMO"              , Integer.toString(ramo)    , pad);
        logueo.setParametro("P_PRODUCTO"          , producto                  , pad);
        logueo.setParametro("P_PRODUCTOR"         , productor                 , pad);
        logueo.setParametro("P_FECHA_DESDE"       , ((fechaDesde!=null)?fechaDesde.toString():"null"), pad);
        logueo.setParametro("P_MARCA_VEHICULO"    , marcaVehiculo             , pad);
        logueo.setParametro("P_ANIO_VEHICULO"     , anioVehiculo              , pad);
        logueo.setParametro("P_CATEGORIA_VEHICULO", categoriaVehiculo         , pad);
        logueo.setParametro("P_MATRICULA_VEHICULO", matriculaVehiculo         , pad);
        logueo.setParametro("P_PADRON_VEHICULO"   , padronVehiculo            , pad);
        logueo.setParametro("P_MOTOR_VEHICULO"    , motorVehiculo             , pad);
        logueo.setParametro("P_CHASIS_VEHICULO"   , chasisVehiculo            , pad);
        logueo.setParametro("P_PLAN_COBERTURA"    , planCobertura             , pad);
        logueo.setParametro("P_PLAN_PAGO"         , Integer.toString(planPago), pad);
        logueo.setParametro("P_MONEDA"            , moneda                    , pad);
        logueo.setParametro("P_TIPO_CALCULO"      , tipoCalculo               , pad);
        logueo.setParametro("P_FORMA_PAGO"        , formaPago                 , pad);
        logueo.setParametro("P_VIG_TECNICA"       , vigTecnica                , pad);
        logueo.setParametro("P_MEDIO_PAGO"        , medioPago                 , pad);
        logueo.setParametro("P_ORIGEN_PAGO"       , origenPago                , pad);
        logueo.setParametro("P_TIPO_FACT"         , tipoFact                  , pad);
        logueo.setParametro("P_PROMOCION"         , promocion                 , pad);
        logueo.setParametro("P_RENOVACION"        , renovacion                , pad);
        logueo.setParametro("P_USUARIO_WEB"       , usuarioWeb                , pad);
        logueo.setParametro("P_CONSUMIDOR_FINAL"  , consumidorFinal           , pad);
        logueo.setParametro("P_FACT_MAIL"         , factMail                  , pad);
        logueo.setParametro("P_COASEGURO"         , coaseguro                 , pad);
        logueo.setParametro("P_EMITIR"            , emitir                    , pad);
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
     * @param tipoDocumento
     * @param documento
     * @param matriculaVehiculo
     * @param padronVehiculo
     * @param motorVehiculo
     * @param chasisVehiculo
     * @param nroCotizacion
     * @param consumoFinal
     */
    private void logEmitirSoaParametros( String logEncabezado,
                                         String tipoDocumento , String documento    , String matriculaVehiculo,
                                         String padronVehiculo, String motorVehiculo, String chasisVehiculo,
                                         long   nroCotizacion , String consumoFinal ) {
        String logParametros =   "TipoDocumento [" + tipoDocumento
                             + "] Documento ["     + documento
                             + "] Matricula ["     + matriculaVehiculo
                             + "] Padron ["        + padronVehiculo
                             + "] Motor ["         + motorVehiculo
                             + "] Chasis ["        + chasisVehiculo
                             + "] NroCotizacion [" + Long.toString(nroCotizacion)
                             + "] ConsumoFinal ["  + consumoFinal
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
     * @param matriculaVehiculo
     * @param padronVehiculo
     * @param motorVehiculo
     * @param chasisVehiculo
     * @param nroCotizacion
     * @param consumoFinal
     */
    private void logEmitirSoaPl2( String logEncabezado    , String titulo        ,
                                  String tipoDocumento    , String documento     ,
                                  int    sucursal         , int    ramo          , String producto,
                                  String matriculaVehiculo, String padronVehiculo,
                                  String motorVehiculo    , String chasisVehiculo,
                                  long   nroCotizacion    , String consumoFinal ) {
        Logueo logueo = new Logueo();
        int pad = 21;
        logueo.setEncabezado(titulo);
        logueo.setParametro("P_TP_DOCUMENTO"      , tipoDocumento               , pad);
        logueo.setParametro("P_NRO_DOCUMENTO"     , documento                   , pad);
        logueo.setParametro("P_SUCURSAL"          , Integer.toString(sucursal)  , pad);
        logueo.setParametro("P_RAMO"              , Integer.toString(ramo)      , pad);
        logueo.setParametro("P_PRODUCTO"          , producto                    , pad);
        logueo.setParametro("P_MATRICULA_VEHICULO", matriculaVehiculo           , pad);
        logueo.setParametro("P_PADRON_VEHICULO"   , padronVehiculo              , pad);
        logueo.setParametro("P_MOTOR_VEHICULO"    , motorVehiculo               , pad);
        logueo.setParametro("P_CHASIS_VEHICULO"   , chasisVehiculo              , pad);
        logueo.setParametro("P_COTIZACION_EMITIDA", Long.toString(nroCotizacion), pad);
        logueo.setParametro("P_CONSUMO_FINAL"     , consumoFinal                , pad);
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
            result = "R[3]=P_MARCA_VEHICULO";
            break;
        case 4:
            result = "R[4]=P_ANIO_VEHICULO";
            break;
        case 5:
            result = "R[5]=P_CATEGORIA_VEHICULO";
            break;
        case 6:
            result = "R[6]=P_FECHA_DESDE";
            break;
        case 7:
            result = "R[7]=P_FECHA_HASTA";
            break;
        case 8:
            result = "R[8]=P_PLAN_COBERTURA";
            break;
        case 9:
            result = "R[9]=P_PLAN_PAGO";
            break;
        case 10:
            result = "R[10]=P_CUOTAS";
            break;
        case 11:
            result = "R[11]=P_RESULTADO";
            break;
        case 12:
            result = "R[12]=P_MENSAJE_EMISION";
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
    private void logCotizarSoaPl( String  logEncabezado,
                                  String  tipoDocumento    , String  documento,
                                  int     sucursal         , int     ramo          , String producto         ,
                                  String  productor        , Date    fechaDesde    ,
                                  String  marcaVehiculo    , String  anioVehiculo  , String categoriaVehiculo,
                                  String  matriculaVehiculo, String  padronVehiculo,
                                  String  motorVehiculo    , String  chasisVehiculo,
                                  String  planCobertura    , int     planPago      ,
                                  String  moneda           , String  tipoCalculo   ,
                                  String  formaPago        , String  vigTecnica    , String medioPago        ,
                                  String  origenPago       , String  tipoFact      , String promocion        ,
                                  String  renovacion       , String  usuarioWeb    , String consumidorFinal  ,
                                  String  factMail         , String  coaseguro     , String emitir ) {

        String logInvocacion = logEncabezado + " ->"
                             +  " P_TP_DOCUMENTO ["       + tipoDocumento
                             + "] P_NRO_DOCUMENTO ["      + documento
                             + "] P_SUCURSAL ["           + String.valueOf(sucursal)
                             + "] P_RAMO ["               + String.valueOf(ramo)
                             + "] P_PRODUCTO ["           + producto
                             + "] P_PRODUCTOR ["          + productor
                             + "] P_FECHA_DESDE ["        + ((fechaDesde!=null)?fechaDesde.toString():"null")
                             + "] P_MARCA_VEHICULO ["     + marcaVehiculo
                             + "] P_ANIO_VEHICULO ["      + anioVehiculo
                             + "] P_CATEGORIA_VEHICULO [" + categoriaVehiculo
                             + "] P_MATRICULA_VEHICULO [" + matriculaVehiculo
                             + "] P_PADRON_VEHICULO ["    + padronVehiculo
                             + "] P_MOTOR_VEHICULO ["     + motorVehiculo
                             + "] P_CHASIS_VEHICULO ["    + chasisVehiculo
                             + "] P_PLAN_COBERTURA ["     + planCobertura
                             + "] P_PLAN_PAGO ["          + Integer.toString(planPago)
                             + "] P_MONEDA ["             + moneda
                             + "] P_TIPO_CALCULO ["       + tipoCalculo
                             + "] P_FORMA_PAGO ["         + formaPago
                             + "] P_VIG_TECNICA ["        + vigTecnica
                             + "] P_MEDIO_PAGO ["         + medioPago
                             + "] P_ORIGEN_PAGO ["        + origenPago
                             + "] P_TIPO_FACT ["          + tipoFact
                             + "] P_PROMOCION ["          + promocion
                             + "] P_RENOVACION ["         + renovacion
                             + "] P_USUARIO_WEB ["        + usuarioWeb
                             + "] P_CONSUMIDOR_FINAL ["   + consumidorFinal
                             + "] P_FACT_MAIL ["          + factMail
                             + "] P_COASEGURO ["          + coaseguro
                             + "] P_EMITIR ["             + emitir
                             + "]";
        logger.info(logInvocacion);
    }
    */

    /*
    private void logEmitirSoaPl( String logEncabezado,
                                 String tipoDocumento    , String documento     ,
                                 int    sucursal         , int    ramo          , String producto,
                                 String matriculaVehiculo, String padronVehiculo,
                                 String motorVehiculo    , String chasisVehiculo,
                                 long   nroCotizacion    , String consumoFinal ) {
        String logInvocacion = logEncabezado + " ->"
                             +  " P_TP_DOCUMENTO ["       + tipoDocumento
                             + "] P_NRO_DOCUMENTO ["      + documento
                             + "] P_SUCURSAL ["           + String.valueOf(sucursal)
                             + "] P_RAMO ["               + String.valueOf(ramo)
                             + "] P_PRODUCTO ["           + producto
                             + "] P_MATRICULA_VEHICULO [" + matriculaVehiculo
                             + "] P_PADRON_VEHICULO ["    + padronVehiculo
                             + "] P_MOTOR_VEHICULO ["     + motorVehiculo
                             + "] P_CHASIS_VEHICULO ["    + chasisVehiculo
                             + "] P_COTIZACION_EMITIDA [" + Long.toString(nroCotizacion)
                             + "] P_CONSUMO_FINAL ["      + consumoFinal
                             + "]";
        logger.info(logInvocacion);
    }
    */

}
