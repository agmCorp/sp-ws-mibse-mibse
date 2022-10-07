package com.bse.negocio.eoperson;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.bse.accesodatos.comun.CretTablasTienda;
import com.bse.accesodatos.comun.ItemCodiguera;
import com.bse.accesodatos.eoperson.CotizacionOPersonalTienda;
import com.bse.accesodatos.eoperson.PolizaOPersonalTienda;
import com.bse.accesodatos.esoa.CuotaPagoTienda;
import com.bse.accesodatos.esoa.MonedaTienda;
import com.bse.accesodatos.esoa.PlanCoberturaTienda;
import com.bse.accesodatos.esoa.PlanCuotaTienda;
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


public class EOPersonalMgrTienda implements IEOPersonalTienda {

    private static final Logger logger = Logger.getLogger(EOPersonalMgrTienda.class);

    /**
     * CONSTRUCTOR
     */
    public EOPersonalMgrTienda() {
    }

    /**
     *
     * @return
     */
    public static IEOPersonalTienda getEOPersonalMgr() {
        return new EOPersonalMgrTienda();
    }



    /**
     *
     * @param em
     * @param planCobertura
     * @param tipoObjeto
     * @param valorObjeto
     * @param tipoMovilidad
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    @Override
    public CotizacionOPersonalTienda cotizarOPersonalAnonimo( EntityManager em,
                                                              String planCobertura,
                                                              String tipoObjeto,
                                                              Double valorObjeto,
                                                              String tipoMovilidad ) throws Exception, BSEExceptionTienda {

        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        long tiempoInicioAux = 0;
        long tiempoTotalAux  = 0;
        String logEncabezado = "OPERSONAL - cotizarOPersonal (" + Long.toString(tiempoInicioMetodo) + ") - COTVARIOS";

        // LOGUEO PARAMETROS
        logCotizarOPersonalParametros( logEncabezado + " - INICIO",
                                       planCobertura, tipoObjeto, valorObjeto, tipoMovilidad );

        IEComunTienda eComunManager = FabricaNegocioTienda.getFabricaNegocio().getEComunMgr();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CONTROL de PARAMETROS - OBLIGATORIEDAD
        short er = -1;
        if      (planCobertura == null || planCobertura.equals(""))     er = CodigosErrorTienda.plan_cobertura_invalido;
        else if (tipoObjeto    == null || tipoObjeto.equals(""))        er = CodigosErrorTienda.tipo_objeto_invalido;
        else if (valorObjeto   == null || valorObjeto.doubleValue()<=0) er = CodigosErrorTienda.valor_objeto_invalido;
        else if (tipoMovilidad == null || tipoMovilidad.equals(""))     er = CodigosErrorTienda.tipo_movilidad_invalida;
        if ( er > -1 ) { throw new BSEExceptionTienda(er); }
        logger.info(logEncabezado + " - PARAMETROS - OK");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CARGA de DATOS desde CONFIGURACION para INVOCACION PL (algunos ademas se utilizan para armar SALIDA)
        String  tipoDocumento = " ";
        String  documento     = " ";
        int     sucursal      = Integer.parseInt(CodigosTienda.getCodigos().getSucursalEmisionOPersonal(em)); // 200
        int     ramo          = Integer.parseInt(CodigosTienda.getCodigos().getRamoEmisionOPersonal(em));     // 7
        String  producto      = CodigosTienda.getCodigos().getProductoEmisionOPersonal(em);           // "RD04"
        String  productor     = CodigosTienda.getCodigos().getProductorEmisionOPersonal(em);          // "1"
        Date    fechaDesde    = new Date();
        Integer planPago      = new Integer(0);

        String  moneda        = CodigosTienda.getCodigos().getMonedaEmisionOPersonal(em);             // "37"
        String  tipoCalculo   = CodigosTienda.getCodigos().getTipoCalculoEmisionOPersonal(em).trim(); // "N"
        String  formaPago     = CodigosTienda.getCodigos().getFormaPagoEmisionOPersonal(em).trim();   // "A"
        String  vigTecnica    = CodigosTienda.getCodigos().getVigTecnicaEmisionOPersonal(em).trim();  // ""
        int     medioPago     = Integer.parseInt(CodigosTienda.getCodigos().getMedioPagoEmisionOPersonal(em).trim());// 1
        String  origenPago    = CodigosTienda.getCodigos().getOrigenPagoEmisionOPersonal(em).trim();  // "A"
        String  tipoFact      = CodigosTienda.getCodigos().getTipoFactEmisionOPersonal(em).trim();    // "F"
        String  promocion     = CodigosTienda.getCodigos().getPromocionEmisionOPersonal(em).trim();   // "NOAP"
        String  renovacion    = CodigosTienda.getCodigos().getRenovacionEmisionOPersonal(em).trim();  // "R"
        String  usuarioWeb    = CodigosTienda.getCodigos().getUsuarioWebEmisionOPersonal(em).trim();  // "COTIZAWEB"

        String consumidorFinal = "N";
        String factMail        = "N";
        String coaseguro       = "N";
        String emitir          = "N";
        logger.info(logEncabezado + " - DATOS desde CONFIGURACION - OK");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // LOGUEO PARAMETROS de la Invocación PL
        logGuiones(logEncabezado);
        logCotizarOPersonalPl2( logEncabezado, "PACK_EMI_MIBSE.PRO_COTIZAR_VARIOS",
                                tipoDocumento, documento , sucursal       , ramo       , producto,
                                planCobertura, productor , fechaDesde     , valorObjeto, tipoMovilidad,
                                tipoObjeto   , planPago  , moneda         , tipoCalculo, formaPago,
                                vigTecnica   , medioPago , origenPago     , tipoFact   , promocion,
                                renovacion   , usuarioWeb, consumidorFinal, factMail   , coaseguro  , emitir);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // INVOCACION PL - PACK_EMI_MIBSE.PRO_COTIZAR_VARIOS
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Query q = em.createNamedQuery("PRO_COTIZAR_VARIOS");

        q.setParameter("P_TP_DOCUMENTO"     , tipoDocumento);                 // A FUEGO   " "
        q.setParameter("P_NRO_DOCUMENTO"    , documento);                     // A FUEGO   " "
        q.setParameter("P_SUCURSAL"         , String.valueOf(sucursal));      // x CONFIG
        q.setParameter("P_RAMO"             , String.valueOf(ramo));          // x CONFIG
        q.setParameter("P_PRODUCTO"         , producto);                      // x CONFIG
        q.setParameter("P_PLAN_COBERTURA"   , planCobertura);                 // PARAMETRO
        q.setParameter("P_PRODUCTOR"        , productor);                     // x CONFIG
        q.setParameter("P_FECHA_DESDE"      , fechaDesde);                    // A FUEGO - FECHA del DIA

        q.setParameter("P_VALOR_OBJETO"     , valorObjeto);                   // PARAMETRO
        q.setParameter("P_MOVILIDAD"        , Integer.valueOf(tipoMovilidad));// PARAMETRO
        q.setParameter("P_TIPO_OBJETO"      , Integer.valueOf(tipoObjeto));   // PARAMETRO

        q.setParameter("P_PLAN_PAGO"        , planPago);                 // A FUEGO   "0"
        q.setParameter("P_MONEDA"           , moneda);                   // x CONFIG
        q.setParameter("P_TIPO_CALCULO"     , tipoCalculo);              // x CONFIG
        q.setParameter("P_FORMA_PAGO"       , formaPago);                // x CONFIG
        q.setParameter("P_VIG_TECNICA"      , vigTecnica);               // x CONFIG
        q.setParameter("P_MEDIO_PAGO"       , medioPago);                // x CONFIG
        q.setParameter("P_ORIGEN_PAGO"      , origenPago);               // x CONFIG
        q.setParameter("P_TIPO_FACT"        , tipoFact);                 // x CONFIG
        q.setParameter("P_PROMOCION"        , promocion);                // x CONFIG
        q.setParameter("P_RENOVACION"       , renovacion);               // x CONFIG
        q.setParameter("P_USUARIO_WEB"      , usuarioWeb);               // x CONFIG

        q.setParameter("P_CONSUMIDOR_FINAL" , consumidorFinal);          // A FUEGO   "N"
        q.setParameter("P_FACT_MAIL"        , factMail);                 // A FUEGO   "N"
        q.setParameter("P_COASEGURO"        , coaseguro);                // A FUEGO   "N"
        q.setParameter("P_EMITIR"           , emitir);                   // A FUEGO   "N"

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
        //      -> R[4] = P_CUOTAS                      - type=String.class  ---------- NO SE UTILIZA
        //      -> R[5] = P_PLANES                      - type=String.class
        //      -> R[6] = P_RESULTADO                   - type=String.class
        //      -> R[7] = P_MENSAJE_EMISION             - type=String.class
        for (int i = 0; i < r.length; i++){
            if (r[i] != null) { logger.info(logEncabezado + " - " + logCotizaResult(i) + " = [" + r[i].toString() + "]");
            } else            { logger.info(logEncabezado + " - " + logCotizaResult(i) + " = [null]"); }
        }
        logger.info(logEncabezado + " - Tiempo Ejecucion PL [" + Long.toString(tiempoTotalAux) + "] milisegundos");
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

        int nroCotizacion      = (r[0] != null)?((Integer)r[0]).intValue():0;   // - PL OUT - P_COTIZACION_EMITIDA
        double premio          = (r[1] != null)?((Double)r[1]).doubleValue():0; // - PL OUT - P_PREMIO_COTIZACION
        double premioAFacturar = (r[2] != null)?((Double)r[2]).doubleValue():0; // - PL OUT - P_PREMIO_COTIZACION_FACTURAR
        Date   fechaHasta      = (Date)r[3];                                    // - PL OUT - P_FECHA_HASTA
        // r[4]                                     - PL OUT - P_CUOTAS   ---------- NO SE UTILIZA
        String planesCuotasStr = (r[5] != null)?(String)r[5]:"";                // - PL OUT - P_PLANES

        /*
        int nroCotizacion      = 45454545;
        double premio          = 323.23;
        double premioAFacturar = 525.25;
        Date   fechaHasta      = new Date();
        String planesCuotasStr = "*1;1;4742;1:4742#*2;2;4842;1:2421#2:2421#*3;3;4875;1:1625#2:1625#3:1625#*4;4;4912;1:1228#2:1228#3:1228#4:1228#*5;5;4945;1:989#2:989#3:989#4:989#5:989#*6;6;4980;1:830#2:830#3:830#4:830#5:830#6:830#";
        //String planesCuotasStr = "*1;1;295.67;1:295.67#*2;2;298.64;1:149.32#2:149.32#*3;3;299.82;1:99.94#2:99.94#3:99.94#*4;4;300.72;1:75.18#2:75.18#3:75.18#4:75.18#*5;5;301.9;1:60.38#2:60.38#3:60.38#4:60.38#5:60.38#*6;6;302.76;1:50.46#2:50.46#3:50.46#4:50.46#5:50.46#6:50.46#*7;7;303.96;1:43.42#2:43.42#3:43.42#4:43.42#5:43.42#6:43.42#7:43.44#*8;8;304.8;1:38.1#2:38.1#3:38.1#4:38.1#5:38.1#6:38.1#7:38.1#8:38.1#*9;9;306;1:34#2:34#3:34#4:34#5:34#6:34#7:34#8:34#9:34#*10;10;306.8;1:30.68#2:30.68#3:30.68#4:30.68#5:30.68#6:30.68#7:30.68#8:30.68#9:30.68#10:30.68#";
        */

        logGuiones(logEncabezado);
        logger.info(logEncabezado + " - COTIZACION RECTOR - OK");

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CARGAR RESULTADOS
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        CotizacionOPersonalTienda cotizacion = new CotizacionOPersonalTienda();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Ramo
        RamoTienda ramoObj = em.find(RamoTienda.class, Integer.valueOf(ramo));
        cotizacion.setRamo(ramoObj);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Producto
        ProductoTienda productoObj = em.find(ProductoTienda.class, producto);
        cotizacion.setProducto(productoObj);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Sucursal
        cotizacion.setSucursal(sucursal);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Nro. Cotizacion                          - PL OUT - P_COTIZACION_EMITIDA
        cotizacion.setNroCotizacion(nroCotizacion);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Tipo y Nro. Documento
        cotizacion.setTipoDocumento("");
        cotizacion.setNroDocumento("");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Productor
        cotizacion.setProductor(Integer.parseInt(productor));
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Moneda
        MonedaTienda monedaObj = eComunManager.consultaMoneda(em, moneda);
        cotizacion.setMoneda(monedaObj);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Premio                                   - PL OUT - P_PREMIO_COTIZACION
        cotizacion.setPremio(premio);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Premio a facturar                        - PL OUT - P_PREMIO_COTIZACION_FACTURAR
        cotizacion.setPremioFacturar(premioAFacturar);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Periodo
        //   FECHA DESDE - FECHA del DIA
        //   FECHA HASTA                            - PL OUT - P_FECHA_HASTA
        cotizacion.setFechaDesde(fechaDesde);
        cotizacion.setFechaHasta(fechaHasta);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Plan Pago -- NO SE CARGA
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Plan de Cobertura
        if ( planCobertura != null ) {
            PlanCoberturaTienda planCoberturaObj =
                                    eComunManager.consultaPlanCoberturaRamoProducto(em, ramo, producto, planCobertura);
            cotizacion.setPlanCobertura(planCoberturaObj);
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Planes de Cuotas                         - PL OUT - P_PLANES
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        logger.info(logEncabezado + " - PRE  - PLANES de CUOTAS - OK");
        //logger.info("planesCuotasStr [" + planesCuotasStr + "]");

        // *1;1;4742;1:4742#*2;2;4842;1:2421#2:2421#*3;3;4875;1:1625#2:1625#3:1625#*4;4;4912;1:1228#2:1228#3:1228#4:1228#*5;5;4945;1:989#2:989#3:989#4:989#5:989#*6;6;4980;1:830#2:830#3:830#4:830#5:830#6:830#

        ArrayList<PlanCuotaTienda> planesDeCuotas = new ArrayList<PlanCuotaTienda>();

        if ( !planesCuotasStr.equals("") ) {
            String[] planes = planesCuotasStr.split("\\*".toString());
            for ( int i = 0; i < planes.length; i++) {
                String plan = planes[i];  // 3;3;4875;1:1625#2:1625#3:1625#
                //logger.info("PLAN de " + i + " [" + plan + "]"); // PLAN de 3 [3;3;209.69;1:69.89#2:69.89#3:69.91#]

                if (plan == null || plan.equals("")) { continue; }

                String[] partes = plan.split(";");
                // Para EJEMPLO : PLAN = 3;3;4875;1:1625#2:1625#3:1625#
                // partes[0] = 3
                // partes[1] = 3
                // partes[2] = 209.69
                // partes[3] = 1:69.89#2:69.89#3:69.91#

                //logger.info("PLAN " + plan);
                //logger.info(" -> PLAN=[" + partes[0] + "] CUOTAS=[" + partes[1] + "] PTF=[" + partes[2] + "]");
                logger.info(logEncabezado + " - PLAN [" + plan
                                          + "] <- PLAN=" + partes[0] + " CUOTAS=" + partes[1] + " PTF=" + partes[2]);

                PlanCuotaTienda planCuota = new PlanCuotaTienda();

                PlanPagoTienda planDePago = eComunManager.consultaPlanPago(em, Integer.parseInt(partes[0]));
                planCuota.setPlanPago(planDePago);

                planCuota.setCantCuotas(Integer.parseInt(partes[1]));
                planCuota.setImporteTotal(Double.parseDouble(partes[2]));

                ArrayList<CuotaPagoTienda> cuotasDelPlan = new ArrayList<CuotaPagoTienda>();

                String[] cuotasVec = partes[3].split("#"); // 1:4742#
                // Para EJEMPLO que seguimos manejando
                // cuotasVec[0] = 1:69.89
                // cuotasVec[1] = 2:69.89
                // cuotasVec[2] = 3:69.91
                for ( int x = 0; x < cuotasVec.length; x++) {
                    String nroCuota = cuotasVec[x].split(":")[0];
                    String importeCuota = cuotasVec[x].split(":")[1];
                    //logger.info(" -> CUOTA " + nroCuota + " X $" + importeCuota);

                    CuotaPagoTienda cuotaPago = new CuotaPagoTienda();
                    cuotaPago.setNroCuota(Integer.parseInt(nroCuota));
                    cuotaPago.setImporte(Double.parseDouble(importeCuota));
                    cuotasDelPlan.add(cuotaPago);
                }
                planCuota.setCuotas(cuotasDelPlan);
                //
                planesDeCuotas.add(planCuota);
            }
        }
        //
        cotizacion.setPlanesDeCuotas(planesDeCuotas);
        logger.info(logEncabezado + " - POST - PLANES de CUOTAS - OK - " + Integer.toString(planesDeCuotas.size()) + " planes.");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
     * @param marca
     * @param serie
     * @param modelo
     * @param nroCotizacion
     * @param planPago
     * @param fechaFactura
     * @param consumoFinal
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    @Override
    public PolizaOPersonalTienda emitirOPersonal( EntityManager em,
                                                  String tipoDocumento,
                                                  String documento,
                                                  String marca,
                                                  String serie,
                                                  String modelo,
                                                  long   nroCotizacion,
                                                  String planPago,
                                                  Date   fechaFactura,
                                                  String consumoFinal ) throws Exception, BSEExceptionTienda {

        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        long tiempoInicioAux = 0;
        long tiempoTotalAux  = 0;
        String logEncabezado = "OPERSONAL - emitirOPersonal (" + Long.toString(tiempoInicioMetodo) + ") - EMIVARIOS";

        // LOGUEO PARAMETROS
        logEmitirOPersonalParametros( logEncabezado + " - INICIO",
                                      tipoDocumento, documento, marca       , serie       , modelo,
                                      nroCotizacion, planPago , fechaFactura, consumoFinal );

        IEComunTienda eComunManager = FabricaNegocioTienda.getFabricaNegocio().getEComunMgr();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CONTROL de PARAMETROS - OBLIGATORIEDAD
        short er = -1;
        if      (tipoDocumento == null || tipoDocumento.equals("") )     er = CodigosErrorTienda.tipo_documento_invalido;
        else if (documento     == null || documento.equals("") )         er = CodigosErrorTienda.documento_invalido;
        else if (      (marca  == null || marca.equals(""))
                 ||    (serie  == null || serie.equals(""))
                 ||    (modelo == null || modelo.equals("")) )           er = CodigosErrorTienda.faltan_datos_opersonales;
        else if (nroCotizacion <= 0)                                     er = CodigosErrorTienda.cotizacion_invalida;
        else if (planPago      == null || planPago.equals("") )          er = CodigosErrorTienda.plan_pago_invalido;
        else if ( fechaFactura == null )                                 er = CodigosErrorTienda.fecha_factura_invalida;
        else if (!consumoFinal.equals("S") && !consumoFinal.equals("N")) er = CodigosErrorTienda.consumidor_final_invalido;
        if ( er > -1 ) { throw new BSEExceptionTienda(er); }
        logger.info(logEncabezado + " - PARAMETROS - OK");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Valida Fecha Factura - Con MENOS de 1 AÑO de ANTIGUEDAD y NO SER una fecha futura
        String sqlFecha = "SELECT TO_NUMBER(TO_CHAR(TRUNC(SYSDATE), 'YYYYMMDD')), "
                        +        "TO_NUMBER(TO_CHAR(ADD_MONTHS(TRUNC(SYSDATE), -12), 'YYYYMMDD')), "
                        +        "TO_NUMBER(TO_CHAR(TRUNC(?), 'YYYYMMDD')) "
                        + "FROM DUAL";
        Query queryFecha = em.createNativeQuery(sqlFecha);
        queryFecha.setParameter(1, fechaFactura);

        @SuppressWarnings("unchecked")
        List<Object[]> resultFecha = queryFecha.getResultList();
        Object[] r1 = resultFecha.get(0);

        int fechaDiaActual   = (r1[0]!=null)?(((BigDecimal)r1[0]).intValue()):99991231;
        int fechaUnAnioAtras = (r1[0]!=null)?(((BigDecimal)r1[1]).intValue()):99991231;
        int fechaFacturaInt  = (r1[0]!=null)?(((BigDecimal)r1[2]).intValue()):99991231;

        if ( fechaDiaActual < fechaFacturaInt ) {
            logger.info(logEncabezado + " - Fecha Factura MAYOR a Fecha Actual - Fecha Factura=["
                   + Integer.toString(fechaFacturaInt) + "] Fecha Actual=[" + Integer.toString(fechaDiaActual) + "]");
            throw new BSEExceptionTienda(CodigosErrorTienda.fecha_factura_invalida);
        } else if (fechaFacturaInt < fechaUnAnioAtras) {
            logger.info(logEncabezado + " - Fecha Factura con MAS de 1 AÑO de ANTIGUEDAD - Fecha Factura=["
                   + Integer.toString(fechaFacturaInt)+"] Fecha de 1 año atras=["+Integer.toString(fechaUnAnioAtras)+"]");
            throw new BSEExceptionTienda(CodigosErrorTienda.fecha_factura_invalida);
        }
        logger.info(logEncabezado +" - FECHA FACTURA (ANTIGUEDAD) - OK - MENOS de 1 AÑO de ANTIGUEDAD - Fecha Factura["
                + Integer.toString(fechaFacturaInt) + "] >= Fecha 1 año atras["+Integer.toString(fechaUnAnioAtras)+"]");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CARGA de DATOS desde la CONFIGURACION
        int     sucursal      = Integer.parseInt(CodigosTienda.getCodigos().getSucursalEmisionOPersonal(em));
        int     ramo          = Integer.parseInt(CodigosTienda.getCodigos().getRamoEmisionOPersonal(em));
        String  producto      = CodigosTienda.getCodigos().getProductoEmisionOPersonal(em);

        String  productor     = CodigosTienda.getCodigos().getProductorEmisionOPersonal(em);
        String  moneda        = CodigosTienda.getCodigos().getMonedaEmisionOPersonal(em);
        logger.info(logEncabezado + " - DATOS desde CONFIGURACION - OK");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Valida Nro. Cotizacion
        boolean cotizacionOk = eComunManager.validarExistenciaCotizacion( em, sucursal, nroCotizacion, ramo, producto );
        if (!cotizacionOk)
            throw new BSEExceptionTienda(CodigosErrorTienda.cotizacion_invalida);
        logger.info(logEncabezado + " - NRO. COTIZACION - OK");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // LOGUEO PARAMETROS de la Invocación PL
        logGuiones(logEncabezado);
        logEmitirOPersonalPl2( logEncabezado, "PACK_EMI_MIBSE.PRO_EMITIR_VARIOS",
                               tipoDocumento, documento,
                               sucursal     , ramo     , producto,
                               marca        , modelo   , serie,
                               nroCotizacion, planPago , fechaFactura, consumoFinal);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // INVOCACION PL - PACK_EMI_MIBSE.PRO_EMITIR_VARIOS
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Query q = em.createNamedQuery("PRO_EMITIR_VARIOS");

        q.setParameter("P_TP_DOCUMENTO"         , tipoDocumento);               // PARAMETRO
        q.setParameter("P_NRO_DOCUMENTO"        , documento);                   // PARAMETRO

        q.setParameter("P_SUCURSAL"             , String.valueOf(sucursal));    // x CONFIG
        q.setParameter("P_RAMO"                 , String.valueOf(ramo));        // x CONFIG
        q.setParameter("P_PRODUCTO"             , producto);                    // x CONFIG

        q.setParameter("P_MARCA"                , marca);                       // PARAMETRO
        q.setParameter("P_MODELO"               , modelo);                      // PARAMETRO
        q.setParameter("P_SERIE"                , serie);                       // PARAMETRO
        q.setParameter("P_COTIZACION_EMITIDA"   , nroCotizacion);               // PARAMETRO
        q.setParameter("P_CONSUMO_FINAL"        , consumoFinal);                // PARAMETRO
        q.setParameter("P_PLAN_PAGO"            , Integer.valueOf(planPago));   // PARAMETRO
        q.setParameter("P_FECHA_FACTURA"        , fechaFactura);                // PARAMETRO

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
        //      -> R[6]  = P_CUOTAS                      - type=String.class
        //      -> R[7]  = P_RESULTADO                   - type=String.class
        //      -> R[8]  = P_MENSAJE_EMISION             - type=String.class
        for (int i = 0; i < r.length; i++){
            if (r[i] != null) { logger.info(logEncabezado + " - " + logEmiteResult(i) + " = [" + r[i].toString() + "]");
            } else            { logger.info(logEncabezado + " - " + logEmiteResult(i) + " = [null]"); }
        }
        logger.info(logEncabezado + " - Tiempo Ejecucion PL ["+Long.toString(tiempoTotalAux)+"] milisegundos");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // En caso de ERROR, se retorna el error especificado por el PL
        if (r[7] != null) {
            String codError  = (String)r[7];
            String descError = (r[8]!=null)?((String)r[8]):"";
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

        int    nroPoliza       = (r[0] != null)?((Integer)r[0]).intValue():0;   // - PL OUT - P_POLIZA_EMITIDA
        double premio          = (r[1] != null)?((Double)r[1]).doubleValue():0; // - PL OUT - P_PREMIO_COTIZACION
        double premioAFacturar = (r[2] != null)?((Double)r[2]).doubleValue():0; // - PL OUT - P_PREMIO_COTIZACION_FACTURAR
        Date   fechaDesde      = (Date)r[3];                                    // - PL OUT - P_FECHA_DESDE
        Date   fechaHasta      = (Date)r[4];                                    // - PL OUT - P_FECHA_HASTA
        String planCobertura   = (String)r[5];                                  // - PL OUT - P_PLAN_COBERTURA
        String cuotas          = (String)r[6];                                  // - PL OUT - P_CUOTAS

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CARGAR RESULTADOS
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        PolizaOPersonalTienda polizaOPersonal = new PolizaOPersonalTienda();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Ramo
        RamoTienda ramoObj = em.find(RamoTienda.class, Integer.valueOf(ramo));
        polizaOPersonal.setRamo(ramoObj);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Producto
        ProductoTienda productoObj = em.find(ProductoTienda.class, producto);
        polizaOPersonal.setProducto(productoObj);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Sucursal
        polizaOPersonal.setSucursal(sucursal);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Nro. Cotizacion
        polizaOPersonal.setNroCotizacion(nroCotizacion);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Tipo y Nro. Documento
        polizaOPersonal.setTipoDocumento(tipoDocumento);
        polizaOPersonal.setNroDocumento(documento);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Productor
        polizaOPersonal.setProductor(Integer.parseInt(productor));
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Moneda
        MonedaTienda monedaObj = eComunManager.consultaMoneda(em, moneda);
        polizaOPersonal.setMoneda(monedaObj);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Premio                                   - PL OUT - P_PREMIO_COTIZACION
        polizaOPersonal.setPremio(premio);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Premio a facturar                        - PL OUT - P_PREMIO_COTIZACION_FACTURAR
        polizaOPersonal.setPremioFacturar(premioAFacturar);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Periodo
        //   FECHA DESDE                            - PL OUT - P_FECHA_DESDE
        //   FECHA HASTA                            - PL OUT - P_FECHA_HASTA
        polizaOPersonal.setFechaDesde(fechaDesde);
        polizaOPersonal.setFechaHasta(fechaHasta);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Nro. Poliza                              - PL OUT - P_POLIZA_EMITIDA
        polizaOPersonal.setNroPoliza(nroPoliza);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Plan Pago
        PlanPagoTienda planPagoObj = eComunManager.consultaPlanPago(em, Integer.valueOf(planPago));
        polizaOPersonal.setPlanPago(planPagoObj);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Plan de Cobertura
        PlanCoberturaTienda planCoberturaObj = null;
        if ( planCobertura != null ) {
            planCoberturaObj = eComunManager.consultaPlanCoberturaRamoProducto(em, ramo, producto, planCobertura);
        }
        polizaOPersonal.setPlanCobertura(planCoberturaObj);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Cuotas
        if ( cuotas != null ) {
            logger.info(logEncabezado + " - PRE  - CUOTAS - OK");
            cuotas=cuotas.replace(" ", "");
            cuotas=cuotas.replace("\\13", "");
            cuotas=cuotas.replace("\\10", "");

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
            //
            polizaOPersonal.setCuotas(listaCuotas);
            logger.info(logEncabezado + " - POST - CUOTAS - OK - " + Integer.toString(listaCuotas.size()) + " cuotas.");
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Marca
        polizaOPersonal.setMarca(marca);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Serie
        polizaOPersonal.setSerie(serie);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Modelo
        polizaOPersonal.setModelo(modelo);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Fecha Factura
        polizaOPersonal.setFechaFactura(fechaFactura);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        logger.info(logEncabezado + " - RESULT : " + polizaOPersonal.toString());
        tiempoTotalMetodo = System.currentTimeMillis() - tiempoInicioMetodo;
        logger.info(logEncabezado + " - FIN - Tiempo Ejecucion METODO [" + Long.toString(tiempoTotalMetodo)+"] milisegundos");
        return polizaOPersonal;
    }



    /**
     *
     * @param em
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    @Override
    public List<PlanCoberturaTienda> consultaPlanesCobertura(EntityManager em) throws Exception, BSEExceptionTienda {
        String logEncabezado = "OPERSONAL - consultaPlanesCobertura";
        //logger.info(logEncabezado + " - INICIO");

        // Ramo
        String ramo = CodigosTienda.getCodigos().getRamoEmisionOPersonal(em);
        RamoTienda ramoObj = em.find(RamoTienda.class, Integer.valueOf(ramo));

        // Producto
        String producto = CodigosTienda.getCodigos().getProductoEmisionOPersonal(em);
        ProductoTienda productoObj = em.find(ProductoTienda.class, producto);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Desde BASE de DATOS - Obtener PLANES de COBERTURAS asociados al ramo/producto
        Query query = em.createNamedQuery("PlanCoberturaTienda.findByRamoyProducto");
        query.setParameter("ramo", ramoObj);
        query.setParameter("producto", productoObj);

        List<PlanCoberturaTienda> result = UtilTienda.castList(PlanCoberturaTienda.class, query.getResultList());
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Desde CONFIGURACION - Obtener PLANES de COBERTURAS
        HashMap<String, String> map = new HashMap<String, String>();
        String planesCoberturaConfigStr = CodigosTienda.getCodigos().getPlanesCoberturaEmisionOPersonal(em);

        if ( (planesCoberturaConfigStr != null) && (!(planesCoberturaConfigStr.equals(""))) ) {
            String[] planesCoberturaConfig = planesCoberturaConfigStr.split(",");
            if ( (planesCoberturaConfig != null) && (planesCoberturaConfig.length > 0) ) {
                for (int i=0; i < planesCoberturaConfig.length; i++) {
                    String[] aux = planesCoberturaConfig[i].split("\\|");
                    map.put(aux[0], aux[1]);
                }
            }
        } else { logger.info(logEncabezado + " - CONFIGURACION no registra PLANES de COBERTURAS"); }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // GENERAR RESULTADO - PLANES de COBERTURA registrados en BASE pero que además estén indicados por CONFIGURACION
        ArrayList<PlanCoberturaTienda> lista = new ArrayList<PlanCoberturaTienda>();
        if (result != null && result.size() > 0) {
            for (int i = 0; i < result.size(); i++) {
                PlanCoberturaTienda pc = (PlanCoberturaTienda)result.get(i);
                if ( map.containsKey(pc.getPlan()) ) { // Verifico si esta en MAP
                    pc.setDescripcion(map.get(pc.getPlan())); // Descripcion - Desde configuracion y no de base de datos
                    lista.add(pc);
                }
            }
        } else { logger.info(logEncabezado + " - BASE de DATOS no retorna PLANES de COBERTURAS"); }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        logger.info(logEncabezado + " - RESULT - [" + Integer.toString(lista.size()) + "] elementos.");
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
    public List<ItemCodiguera> consultaTiposObjetos(EntityManager em) throws Exception, BSEExceptionTienda {
        String logEncabezado = "OPERSONAL - consultaTiposObjetos";
        //logger.info(logEncabezado + " - INICIO");

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Desde BASE de DATOS - Obtener TIPOS de OBJETOS
        Query query = em.createNamedQuery("CretTablasTienda.findTabla");
        query.setParameter("codigoTabla", Integer.valueOf("170035"));

        List<CretTablasTienda> result = UtilTienda.castList(CretTablasTienda.class, query.getResultList());
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Desde CONFIGURACION - Obtener TIPOS de OBJETOS
        HashMap<String, String> map = new HashMap<String, String>();
        String tiposObjetosConfigStr = CodigosTienda.getCodigos().getTiposObjetosEmisionOPersonal(em);

        if ( (tiposObjetosConfigStr != null) && (!(tiposObjetosConfigStr.equals(""))) ) {
            String[] tiposObjetosConfig = tiposObjetosConfigStr.split(",");
            if ( (tiposObjetosConfig != null) && (tiposObjetosConfig.length > 0) ) {
                for (int i=0; i < tiposObjetosConfig.length; i++) {
                    map.put(tiposObjetosConfig[i], tiposObjetosConfig[i]);
                }
            }
        } else { logger.info(logEncabezado + " - CONFIGURACION no registra TIPOS de OBJETOS"); }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // GENERAR RESULTADO - TIPOS de OBJETOS registrados en BASE pero que además estén indicados por CONFIGURACION
        ArrayList<ItemCodiguera> lista = new ArrayList<ItemCodiguera>();
        if (result != null && result.size() > 0) {
            for (int i = 0; i < result.size(); i++) {
                CretTablasTienda tabla = (CretTablasTienda) result.get(i);
                if ( map.containsKey(tabla.getDato1()) ) { // Verifico si esta en MAP
                    ItemCodiguera tipo = new ItemCodiguera(tabla.getDato1(), tabla.getDescripcion());
                    lista.add(tipo);
                }
            }
        } else { logger.info(logEncabezado + " - BASE de DATOS no retorna TIPOS de OBJETOS"); }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        logger.info(logEncabezado + " - RESULT - [" + Integer.toString(lista.size()) + "] elementos.");
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
    public List<ItemCodiguera> consultaTiposMovilidad(EntityManager em) throws Exception, BSEExceptionTienda {
        String logEncabezado = "OPERSONAL - consultaTiposMovilidad";
        //logger.info(logEncabezado + " - INICIO");

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Desde BASE de DATOS - Obtener TIPOS de MOVILIDAD
        Query query = em.createNamedQuery("CretTablasTienda.findTabla");
        query.setParameter("codigoTabla", Integer.valueOf("171041"));

        List<CretTablasTienda> result = UtilTienda.castList(CretTablasTienda.class, query.getResultList());
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Desde CONFIGURACION - Obtener TIPOS de MOVILIDAD
        HashMap<String, String> map = new HashMap<String, String>();
        String tiposMovilidadConfigStr = CodigosTienda.getCodigos().getTiposMovilidadEmisionOPersonal(em);

        if ( (tiposMovilidadConfigStr != null) && (!(tiposMovilidadConfigStr.equals(""))) ) {
            String[] tiposMovilidadConfig = tiposMovilidadConfigStr.split(",");
            if ( (tiposMovilidadConfig != null) && (tiposMovilidadConfig.length > 0) ) {
                for (int i=0; i < tiposMovilidadConfig.length; i++) {
                    map.put(tiposMovilidadConfig[i], tiposMovilidadConfig[i]);
                }
            }
        } else { logger.info(logEncabezado + " - CONFIGURACION no registra TIPOS de MOVILIDAD"); }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // GENERAR RESULTADO - TIPOS de MOVILIDAD registrados en BASE pero que además estén indicados por CONFIGURACION
        ArrayList<ItemCodiguera> lista = new ArrayList<ItemCodiguera>();
        if (result != null && result.size() > 0) {
            for (int i = 0; i < result.size(); i++) {
                CretTablasTienda tabla = (CretTablasTienda) result.get(i);
                if ( map.containsKey(tabla.getDato1()) ) { // Verifico si esta en MAP
                    ItemCodiguera tipo = new ItemCodiguera(tabla.getDato1(), tabla.getDescripcion());
                    lista.add(tipo);
                }
            }
        } else { logger.info(logEncabezado + " - BASE de DATOS no retorna TIPOS de MOVILIDAD"); }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        logger.info(logEncabezado + " - RESULT - [" + Integer.toString(lista.size()) + "] elementos.");
        return lista;
    }



    /**
     *
     * @param logEncabezado
     * @param planCobertura
     * @param tipoObjeto
     * @param valorObjeto
     * @param tipoMovilidad
     */
    private void logCotizarOPersonalParametros( String logEncabezado,
                                                String planCobertura, String tipoObjeto,
                                                Double valorObjeto,   String tipoMovilidad ) {
        String logParametros =   "PlanCobertura [" + planCobertura
                             + "] TipoObjeto ["    + tipoObjeto
                             + "] ValorObjeto ["   + ((valorObjeto!=null)?valorObjeto.toString():"null")
                             + "] Movilidad ["     + tipoMovilidad
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
     * @param planCobertura
     * @param productor
     * @param fechaDesde
     * @param valorObjeto
     * @param tipoMovilidad
     * @param tipoObjeto
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
    private void logCotizarOPersonalPl2( String  logEncabezado, String titulo,
                                         String  tipoDocumento, String documento,
                                         int     sucursal     , int    ramo         , String producto       ,
                                         String  planCobertura, String productor    , Date   fechaDesde     ,
                                         Double  valorObjeto  , String tipoMovilidad, String tipoObjeto     ,
                                         Integer planPago     , String moneda       , String tipoCalculo    ,
                                         String  formaPago    , String vigTecnica   , int    medioPago      ,
                                         String  origenPago   , String tipoFact     , String promocion      ,
                                         String  renovacion   , String usuarioWeb   , String consumidorFinal,
                                         String  factMail     , String coaseguro    , String emitir ) {

        Logueo logueo = new Logueo();
        int pad = 19;
        logueo.setEncabezado(titulo);
        logueo.setParametro("P_TP_DOCUMENTO"    , tipoDocumento              , pad);
        logueo.setParametro("P_NRO_DOCUMENTO"   , documento                  , pad);
        logueo.setParametro("P_SUCURSAL"        , Integer.toString(sucursal) , pad);
        logueo.setParametro("P_RAMO"            , Integer.toString(ramo)     , pad);
        logueo.setParametro("P_PRODUCTO"        , producto                   , pad);
        logueo.setParametro("P_PLAN_COBERTURA"  , planCobertura              , pad);
        logueo.setParametro("P_PRODUCTOR"       , productor                  , pad);
        logueo.setParametro("P_FECHA_DESDE"     , ((fechaDesde!=null)?fechaDesde.toString():"null")  , pad);
        logueo.setParametro("P_VALOR_OBJETO"    , ((valorObjeto!=null)?valorObjeto.toString():"null"), pad);
        logueo.setParametro("P_MOVILIDAD"       , tipoMovilidad              , pad);
        logueo.setParametro("P_TIPO_OBJETO"     , tipoObjeto                 , pad);
        logueo.setParametro("P_PLAN_PAGO"       , ((planPago!=null)?planPago.toString():"null")      , pad);
        logueo.setParametro("P_MONEDA"          , moneda                     , pad);
        logueo.setParametro("P_TIPO_CALCULO"    , tipoCalculo                , pad);
        logueo.setParametro("P_FORMA_PAGO"      , formaPago                  , pad);
        logueo.setParametro("P_VIG_TECNICA"     , vigTecnica                 , pad);
        logueo.setParametro("P_MEDIO_PAGO"      , Integer.toString(medioPago), pad);
        logueo.setParametro("P_ORIGEN_PAGO"     , origenPago                 , pad);
        logueo.setParametro("P_TIPO_FACT"       , tipoFact                   , pad);
        logueo.setParametro("P_PROMOCION"       , promocion                  , pad);
        logueo.setParametro("P_RENOVACION"      , renovacion                 , pad);
        logueo.setParametro("P_USUARIO_WEB"     , usuarioWeb                 , pad);
        logueo.setParametro("P_CONSUMIDOR_FINAL", consumidorFinal            , pad);
        logueo.setParametro("P_FACT_MAIL"       , factMail                   , pad);
        logueo.setParametro("P_COASEGURO"       , coaseguro                  , pad);
        logueo.setParametro("P_EMITIR"          , emitir                     , pad);
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
     * @param marca
     * @param serie
     * @param modelo
     * @param nroCotizacion
     * @param planPago
     * @param fechaFactura
     * @param consumoFinal
     */
    private void logEmitirOPersonalParametros( String logEncabezado,
                                               String tipoDocumento, String documento   ,
                                               String marca        , String serie       , String modelo,
                                               long   nroCotizacion, String planPago    ,
                                               Date   fechaFactura , String consumoFinal ) {
        String logParametros =   "TipoDocumento [" + tipoDocumento
                             + "] Documento ["     + documento
                             + "] Marca ["         + marca
                             + "] Serie ["         + serie
                             + "] Modelo ["        + modelo
                             + "] NroCotizacion [" + Long.toString(nroCotizacion)
                             + "] PlanPago ["      + planPago
                             + "] FechaFactura ["  + ((fechaFactura!=null)?fechaFactura.toString():"null")
                             + "] ConsumoFinal ["  + consumoFinal + "]";
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
     * @param nroCotizacion
     * @param planPago
     * @param fechaFactura
     * @param consumoFinal
     */
    private void logEmitirOPersonalPl2( String logEncabezado, String titulo,
                                        String tipoDocumento, String documento   ,
                                        int    sucursal     , int    ramo        , String producto,
                                        String marca        , String modelo      , String serie,
                                        long   nroCotizacion, String planPago    ,
                                        Date   fechaFactura , String consumoFinal ) {
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
        logueo.setParametro("P_COTIZACION_EMITIDA", Long.toString(nroCotizacion), pad);
        logueo.setParametro("P_CONSUMO_FINAL"     , consumoFinal                , pad);
        logueo.setParametro("P_PLAN_PAGO"         , planPago                    , pad);
        logueo.setParametro("P_FECHA_FACTURA"     , ((fechaFactura!=null)?fechaFactura.toString():"null") , pad);
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
            result = "R[6]=P_CUOTAS";
            break;
        case 7:
            result = "R[7]=P_RESULTADO";
            break;
        case 8:
            result = "R[8]=P_MENSAJE_EMISION";
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
    private void logCotizarOPersonalPl( String  logEncabezado,
                                        String  tipoDocumento, String documento,
                                        int     sucursal     , int    ramo         , String producto       ,
                                        String  planCobertura, String productor    , Date   fechaDesde     ,
                                        Double  valorObjeto  , String tipoMovilidad, String tipoObjeto     ,
                                        Integer planPago     , String moneda       , String tipoCalculo    ,
                                        String  formaPago    , String vigTecnica   , String medioPago      ,
                                        String  origenPago   , String tipoFact     , String promocion      ,
                                        String  renovacion   , String usuarioWeb   , String consumidorFinal,
                                        String  factMail     , String coaseguro    , String emitir ) {

        String logInvocacion = logEncabezado + " ->"
                             +  " P_TP_DOCUMENTO ["     + tipoDocumento
                             + "] P_NRO_DOCUMENTO ["    + documento
                             + "] P_SUCURSAL ["         + String.valueOf(sucursal)
                             + "] P_RAMO ["             + String.valueOf(ramo)
                             + "] P_PRODUCTO ["         + producto
                             + "] P_PLAN_COBERTURA ["   + planCobertura
                             + "] P_PRODUCTOR ["        + productor
                             + "] P_FECHA_DESDE ["      + fechaDesde.toString()
                             + "] P_VALOR_OBJETO ["     + valorObjeto.toString()
                             + "] P_MOVILIDAD ["        + tipoMovilidad
                             + "] P_TIPO_OBJETO ["      + tipoObjeto
                             + "] P_PLAN_PAGO ["        + planPago
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
    private void logEmitirOPersonalPl( String logEncabezado,
                                       String tipoDocumento, String documento   ,
                                       int    sucursal     , int    ramo        , String producto,
                                       String marca        , String modelo      , String serie,
                                       long   nroCotizacion, String planPago    ,
                                       Date   fechaFactura , String consumoFinal ) {
        String logInvocacion = logEncabezado + " ->"
                             +  " P_TP_DOCUMENTO ["       + tipoDocumento
                             + "] P_NRO_DOCUMENTO ["      + documento
                             + "] P_SUCURSAL ["           + String.valueOf(sucursal)
                             + "] P_RAMO ["               + String.valueOf(ramo)
                             + "] P_PRODUCTO ["           + producto
                             + "] P_MARCA ["              + marca
                             + "] P_MODELO ["             + modelo
                             + "] P_SERIE ["              + serie
                             + "] P_COTIZACION_EMITIDA [" + Long.toString(nroCotizacion)
                             + "] P_PLAN_PAGO ["          + planPago
                             + "] P_FECHA_FACTURA ["      + fechaFactura.toString()
                             + "] P_CONSUMO_FINAL ["      + consumoFinal
                             + "]";
        logger.info(logInvocacion);
    }
    */
}
