package com.bse.negocio.eindivi;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;

import com.bse.accesodatos.comun.CretTablasTienda;
import com.bse.accesodatos.comun.ItemCodiguera;
import com.bse.accesodatos.eindivi.CotizacionIndiviTienda;
import com.bse.accesodatos.eindivi.DatosVariosIndivi;
import com.bse.accesodatos.eindivi.PolizaIndiviTienda;
import com.bse.accesodatos.eindivi.XmlCotizacion;
import com.bse.accesodatos.eindivi.XmlPlanCobertura;
import com.bse.accesodatos.esoa.CuotaPagoTienda;
import com.bse.accesodatos.esoa.MarcaVehiculoTienda;
import com.bse.accesodatos.esoa.MonedaTienda;
import com.bse.accesodatos.esoa.PlanCoberturaTienda;
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
import com.bse.servicios.utilitario.util.Herramientas;


public class EIndiviMgrTienda implements IEIndiviTienda{

    private static final Logger logger = Logger.getLogger(EIndiviMgrTienda.class);

    /**
     * CONSTRUCTOR
     */
    public EIndiviMgrTienda() {

    }

    /**
     *
     * @return
     */
    public static IEIndiviTienda getEIndiviMgr() {
        return new EIndiviMgrTienda();
    }



    /**
     *
     * @param em
     * @param marcaVehiculo
     * @param anioVehiculo
     * @param tipoVehiculo
     * @param combustible
     * @param versionVehiculo
     * @param areaCirculacion
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    @Override
    public CotizacionIndiviTienda cotizarIndiviAnonimo( EntityManager em,
                                                        String marcaVehiculo,
                                                        String anioVehiculo,
                                                        String tipoVehiculo,
                                                        String combustible,
                                                        String versionVehiculo,
                                                        String areaCirculacion ) throws Exception, BSEExceptionTienda {

        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        long tiempoInicioAux = 0;
        long tiempoTotalAux  = 0;
        String logEncabezado = "INDIVI - cotizarIndivi (" + Long.toString(tiempoInicioMetodo) + ") - COTINDIVI";

        // LOGUEO PARAMETROS
        logCotizarIndiviParametros( logEncabezado + " - INICIO",
                                    marcaVehiculo, anioVehiculo   , tipoVehiculo,
                                    combustible  , versionVehiculo, areaCirculacion );

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CONTROL de PARAMETROS - OBLIGATORIEDAD
        short er = -1;
        if      ( marcaVehiculo   == null || marcaVehiculo.equals("") )   er = CodigosErrorTienda.marca_vehiculo_invalida;
        else if ( anioVehiculo    == null || anioVehiculo.equals("") )    er = CodigosErrorTienda.anio_vehiculo_invalido;
        else if ( tipoVehiculo    == null || tipoVehiculo.equals("") )    er = CodigosErrorTienda.tipo_vehiculo_invalido;
        else if ( combustible     == null || combustible.equals("") )     er = CodigosErrorTienda.combustible_invalido;
        else if ( versionVehiculo == null || versionVehiculo.equals("") ) er = CodigosErrorTienda.version_vehiculo_invalido;
        else if ( areaCirculacion == null || areaCirculacion.equals("") ) er = CodigosErrorTienda.area_ciculacion_invalida;
        if ( er > -1 ) { throw new BSEExceptionTienda(er); }
        logger.info(logEncabezado + " - PARAMETROS - OK");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CARGA de DATOS desde CONFIGURACION para INVOCACION PLs
        String tipoDocumento = " ";
        String documento     = " ";
        String tipoFact      = CodigosTienda.getCodigos().getTipoFactEmisionIndivi(em).trim();              // "F"
        int    sucursal      = Integer.parseInt(CodigosTienda.getCodigos().getSucursalEmisionIndivi(em));   // 200
        int    ramo          = Integer.parseInt(CodigosTienda.getCodigos().getRamoEmisionIndivi(em).trim());// 4
        String producto      = CodigosTienda.getCodigos().getProductoEmisionIndivi(em).trim();              // "INDIVI"
        String moneda        = CodigosTienda.getCodigos().getMonedaEmisionIndivi(em);                       // "20"
        int    medioPago     = Integer.parseInt(CodigosTienda.getCodigos().getMedioPagoEmisionIndivi(em).trim());// 1
        String origenPago    = CodigosTienda.getCodigos().getOrigenPagoEmisionIndivi(em).trim();            // "A"
        String promocion     = CodigosTienda.getCodigos().getPromocionEmisionIndivi(em).trim();             // "NOAP"
        String tipoCalculo   = CodigosTienda.getCodigos().getTipoCalculoEmisionIndivi(em).trim();           // "N"
        String formaPago     = CodigosTienda.getCodigos().getFormaPagoEmisionIndivi(em).trim();             // "A"
        String renovacion    = CodigosTienda.getCodigos().getRenovacionEmisionIndivi(em).trim();            // "R"
        String usuario       = CodigosTienda.getCodigos().getUsuarioWebEmisionIndivi(em).trim();            // "COTIZAWEB"
        logger.info(logEncabezado + " - DATOS desde CONFIGURACION - OK");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // LOGUEO PARAMETROS de la Invocación PL
        logGuiones(logEncabezado);
        logCotizarIndiviPl2( logEncabezado, "PACK_EMI_MIBSE.PRO_COTIZAR_INDIVI",
                             tipoDocumento, documento      , tipoFact,
                             sucursal     , ramo           , producto,
                             moneda       , medioPago      ,
                             origenPago   , promocion      , tipoCalculo,
                             formaPago    , renovacion     , usuario,
                             marcaVehiculo, tipoVehiculo   , anioVehiculo,
                             combustible  , versionVehiculo, areaCirculacion );

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // INVOCACION PL - PACK_EMI_MIBSE.PRO_COTIZAR_INDIVI
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Query q = em.createNamedQuery("PRO_COTIZAR_INDIVI");

        q.setParameter("P_TP_DOCUMENTO"    , tipoDocumento);    // A FUEGO   " "
        q.setParameter("P_NRO_DOCUMENTO"   , documento);        // A FUEGO   " "

        q.setParameter("P_TIPO_FACT"       , tipoFact);         // x CONFIG
        q.setParameter("P_SUCURSAL"        , sucursal);         // x CONFIG
        q.setParameter("P_RAMO"            , ramo);             // x CONFIG
        q.setParameter("P_PRODUCTO"        , producto);         // x CONFIG
        q.setParameter("P_MONEDA"          , moneda);           // x CONFIG
        q.setParameter("P_MEDIO_PAGO"      , medioPago);        // x CONFIG
        q.setParameter("P_ORIGEN_PAGO"     , origenPago);       // x CONFIG
        q.setParameter("P_PROMOCION"       , promocion);        // x CONFIG
        q.setParameter("P_TIPO_CALCULO"    , tipoCalculo);      // x CONFIG
        q.setParameter("P_FORMA_PAGO"      , formaPago);        // x CONFIG
        q.setParameter("P_RENOVACION"      , renovacion);       // x CONFIG

        q.setParameter("P_MARCA_VEHICULO"  , marcaVehiculo);    // PARAMETRO
        q.setParameter("P_TIPO_VEHICULO"   , tipoVehiculo);     // PARAMETRO
        q.setParameter("P_ANIO_VEHICULO"   , anioVehiculo);     // PARAMETRO
        q.setParameter("P_COMBUSTIBLE"     , combustible);      // PARAMETRO
        q.setParameter("P_MODELO_VEHICULO" , versionVehiculo);  // PARAMETRO
        q.setParameter("P_AREA_CIRCULACION", areaCirculacion);  // PARAMETRO

        q.setParameter("P_USUARIO"         , usuario);          // x CONFIG

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
        //      -> R[0] = P_COD_ERROR     - type=Integer.class
        //      -> R[1] = P_DESC_ERROR    - type=String.class
        //      -> R[2] = P_SQL_ERROR     - type=String.class
        //      -> R[3] = P_RESULTADO     - type=java.sql.Clob.class
        for (int i = 0; i < r.length; i++){
            if (r[i] != null) { logger.info(logEncabezado + " - " + logCotizaResult(i) + " = [" + r[i].toString() + "]");
            } else            { logger.info(logEncabezado + " - " + logCotizaResult(i) + " = [null]"); }
        }
        logger.info(logEncabezado + " - Tiempo Ejecucion PL [" + Long.toString(tiempoTotalAux) + "] milisegundos");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // En caso de ERROR, se retorna el error especificado por el PL
        if (r[0] != null && ((Integer)r[0]).intValue() != 0){
            Integer codError  = (Integer)r[0];
            String  descError = (r[1]!=null)?((String)r[1]):"";
            BSEExceptionTienda exc = new BSEExceptionTienda(CodigosErrorTienda.error_cotizacion_rector, descError);

            if (r[2] != null) { descError += " / " + (String)r[2]; }

            String aux = "------------------------------------------------------------";
            logger.info(logEncabezado + " - " + aux + "ERROR_TIENDA" + aux);
            logger.info(logEncabezado +  " - ERROR: COD_PL [" + codError.toString()
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

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // NO hubo error en PL -> PARSEO del RESULTADO
        java.sql.Clob resultado = (java.sql.Clob)r[3];
        Herramientas herramientas = new Herramientas();
        String strClob = herramientas.convertirClob(resultado);
        if ( strClob == null ) {
            String descError = "Error al obtener la información de la cotización";
            BSEExceptionTienda exc = new BSEExceptionTienda(CodigosErrorTienda.error_cotizacion_rector, descError);
            throw exc;
        }
        logger.info(logEncabezado + " - Result PL Clob to String [" + strClob + "]");

        XmlCotizacion cotizacionXML = getCotizacion(logEncabezado, strClob);
        if ( cotizacionXML == null ) {
            String descError = "Error al obtener la información de la cotización";
            BSEExceptionTienda exc = new BSEExceptionTienda(CodigosErrorTienda.error_cotizacion_rector, descError);
            throw exc;
        }
        logger.info(logEncabezado + " - COTIZACION RECTOR UNMARSHALLING - OK");


        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /*java.sql.Clob resultado = null;
        XmlCotizacion cotizacionXML = getCotizacion1(resultado);**/

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CARGAR RESULTADOS
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        CotizacionIndiviTienda cotizacion = new CotizacionIndiviTienda();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Marca Vehiculo
        cotizacion.setMarcaVehiculo(marcaVehiculo);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Anio Vehiculo
        cotizacion.setAnioVehiculo(anioVehiculo);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Tipo Vehiculo
        cotizacion.setTipoVehiculo(tipoVehiculo);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Combustible
        cotizacion.setCombustible(combustible);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Modelo Vehiculo
        cotizacion.setVersionVehiculo(versionVehiculo);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Area de Circulación
        cotizacion.setAreaCirculacion(areaCirculacion);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Nro. de Cotizacion
        cotizacion.setNroCotizacion(cotizacionXML.getNumero());
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Lista de Planes de Pagos Preferenciales
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Iterator<String> it1 = cotizacionXML.getCuotas().iterator();
        ArrayList<String> cuotas = new ArrayList<String>();
        while (it1.hasNext()) { cuotas.add(it1.next()); }
        cotizacion.setPagoPreferecial(cuotas);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Fecha de Cotizacion
        cotizacion.setFechaCotizacion(cotizacionXML.getFechaCotizacion());
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Planes de Cobertura
        ArrayList<XmlPlanCobertura> planesCobertura = new ArrayList<XmlPlanCobertura>();
        Iterator<XmlPlanCobertura> it = cotizacionXML.getCertificados().get(0).getPlanesCobertura().iterator();
        while ( it.hasNext() ) { planesCobertura.add(it.next()); }
        cotizacion.setPlanesCobertura(planesCobertura);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        logger.info(logEncabezado + " - RESULT : " + cotizacion.toString());
        tiempoTotalMetodo = System.currentTimeMillis() - tiempoInicioMetodo;
        logger.info(logEncabezado + " - FIN - Tiempo Ejecucion METODO [" + Long.toString(tiempoTotalMetodo)+"] milisegundos");

        return cotizacion;
    }

    /**
     *
     * @param logEncabezado
     * @param resultString
     * @return
     */
    private XmlCotizacion getCotizacion(String logEncabezado, String resultString) {
        XmlCotizacion cotizacionXML = null;
        Logueo logueo = new Logueo();
        logueo.setEncabezado(logEncabezado + " - Error al Unmarshal XML");
        logueo.setClase(EIndiviMgrTienda.class);
        logueo.setMetodo("getCotizacion");

        try {
            // STRING --> XmlCotizacion
            StringBuffer xmlStr = new StringBuffer( resultString );

            JAXBContext jc = JAXBContext.newInstance(XmlCotizacion.class);
            Unmarshaller u = jc.createUnmarshaller();
            cotizacionXML= (XmlCotizacion) u.unmarshal( new StreamSource( new StringReader( xmlStr.toString() ) ) );
            //logger.info(logEncabezado + " - CotizacionXML [" + cotizacionXML.toString() + "]");

        } catch (JAXBException e) {
              cotizacionXML = null;
              logueo.setException("JAXBException");
              logueo.setError(e.getMessage());
              logger.error(logueo.getMensaje());
              e.printStackTrace();
          } catch (Exception e) {
              cotizacionXML = null;
              logueo.setException("Exception");
              logueo.setError(e.getMessage());
              logger.error(logueo.getMensaje());
              e.printStackTrace();
        }
        return cotizacionXML;
    }



    /**
     *
     * @param em
     * @param tipoDocumento
     * @param documento
     * @param nroCotizacion
     * @param matriculaVehiculo
     * @param padronVehiculo
     * @param motorVehiculo
     * @param chasisVehiculo
     * @param planCobertura
     * @param planPago
     * @param modalidad
     * @param consumoFinal
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    @Override
    public PolizaIndiviTienda emitirIndivi( EntityManager em,
                                            String tipoDocumento,
                                            String documento,
                                            long   nroCotizacion,
                                            String matriculaVehiculo,
                                            String padronVehiculo,
                                            String motorVehiculo,
                                            String chasisVehiculo,
                                            String planCobertura,
                                            String planPago,
                                            String modalidad,
                                            String consumoFinal )  throws Exception, BSEExceptionTienda {

        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        long tiempoInicioAux = 0;
        long tiempoTotalAux  = 0;

        String logEncabezado = "INDIVI - emitirIndivi (" + Long.toString(tiempoInicioMetodo) + ") - EMIINDIVI";

        // LOGUEO PARAMETROS
        logEmitirIndiviParametros( logEncabezado + " - INICIO",
                                   tipoDocumento    , documento     , nroCotizacion,
                                   matriculaVehiculo, padronVehiculo, motorVehiculo, chasisVehiculo,
                                   planCobertura    , planPago      , modalidad    , consumoFinal);

        IEComunTienda eComunManager = FabricaNegocioTienda.getFabricaNegocio().getEComunMgr();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CONTROL de PARAMETROS - OBLIGATORIEDAD
        short er = -1;
        if      ( tipoDocumento      == null || tipoDocumento.equals(""))   er = CodigosErrorTienda.tipo_documento_invalido;
        else if ( documento == null || documento.equals(""))                er = CodigosErrorTienda.documento_invalido;
        else if ( nroCotizacion <= 0)                                       er = CodigosErrorTienda.cotizacion_invalida;
        else if (    (motorVehiculo  == null || motorVehiculo.equals(""))
                 &&  (chasisVehiculo == null || chasisVehiculo.equals(""))) er = CodigosErrorTienda.falta_motor_o_chasis;
        else if ( planCobertura      == null || planCobertura.equals(""))   er = CodigosErrorTienda.plan_cobertura_invalido;
        else if ( planPago           == null || planPago.equals(""))        er = CodigosErrorTienda.plan_pago_invalido;
        else if ( modalidad          == null || modalidad.equals(""))       er = CodigosErrorTienda.modalidad_invalida;
        else if ( !consumoFinal.equals("S") && !consumoFinal.equals("N"))   er = CodigosErrorTienda.consumidor_final_invalido;
        if ( er > -1 ) { throw new BSEExceptionTienda(er); }
        logger.info(logEncabezado + " - PARAMETROS - OK");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CARGA de DATOS desde CONFIGURACION para INVOCACION PL
        int    sucursal  = Integer.parseInt(CodigosTienda.getCodigos().getSucursalEmisionIndivi(em));    // 200
        int    ramo      = Integer.parseInt(CodigosTienda.getCodigos().getRamoEmisionIndivi(em).trim()); // 4
        String producto  = CodigosTienda.getCodigos().getProductoEmisionIndivi(em).trim();               // "INDIVI"
        String usuario   = CodigosTienda.getCodigos().getUsuarioWebEmisionIndivi(em).trim();             // "COTIZAWEB"

        // CARGA de DATOS desde CONFIGURACION - SOLO NECESARIOS para armar SALIDA
        String productor = CodigosTienda.getCodigos().getProductorEmisionIndivi(em).trim();              // "1"
        String moneda    = CodigosTienda.getCodigos().getMonedaEmisionIndivi(em);                        // "20"
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
        logEmitirIndiviPl2( logEncabezado, "PACK_EMI_MIBSE.PRO_EMITIR_INDIVI",
                            tipoDocumento    , documento     ,
                            sucursal         , ramo          , producto     , nroCotizacion,
                            matriculaVehiculo, padronVehiculo, motorVehiculo, chasisVehiculo,
                            planCobertura    , planPago      , consumoFinal ,
                            modalidad        , usuario );

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // INVOCACION PL - PACK_EMI_MIBSE.PRO_EMITIR_INDIVI
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Query q = em.createNamedQuery("PRO_EMITIR_INDIVI");

        q.setParameter("P_TP_DOCUMENTO"       , tipoDocumento);             // PARAMETRO
        q.setParameter("P_NRO_DOCUMENTO"      , documento);                 // PARAMETRO

        q.setParameter("P_SUCURSAL"           , sucursal);                  // x CONFIG
        q.setParameter("P_RAMO"               , ramo);                      // x CONFIG
        q.setParameter("P_PRODUCTO"           , producto);                  // x CONFIG

        q.setParameter("P_COTIZACION_EMITIDA" , nroCotizacion);             // PARAMETRO
        q.setParameter("P_MATRICULA_VEHICULO" , matriculaVehiculo);         // PARAMETRO
        q.setParameter("P_PADRON_VEHICULO"    , padronVehiculo);            // PARAMETRO
        q.setParameter("P_MOTOR_VEHICULO"     , motorVehiculo);             // PARAMETRO
        q.setParameter("P_CHASIS_VEHICULO"    , chasisVehiculo);            // PARAMETRO
        q.setParameter("P_PLAN_COBERTURA"     , planCobertura);             // PARAMETRO
        q.setParameter("P_PLAN_PAGO"          , Integer.valueOf(planPago)); // PARAMETRO
        q.setParameter("P_CONSUMIDOR_FINAL"   , consumoFinal);              // PARAMETRO
        q.setParameter("P_MODALIDAD"          , modalidad);                 // PARAMETRO

        q.setParameter("P_USUARIO"            , usuario);                   // x CONFIG

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
        //      -> R[3]  = P_MARCA_VEHICULO              - type=String.class
        //      -> R[4]  = P_ANIO_VEHICULO               - type=String.class
        //      -> R[5]  = P_FECHA_DESDE                 - type=Date.class
        //      -> R[6]  = P_FECHA_HASTA                 - type=Date.class
        //      -> R[7]  = P_CUOTAS                      - type=String.class
        //      -> R[8]  = P_COD_ERROR                   - type=Integer.class
        //      -> R[9]  = P_DESC_ERROR                  - type=String.class
        //      -> R[10] = P_SQL_ERROR                   - type=String.class
        for (int i = 0; i < r.length; i++){
            if (r[i] != null) { logger.info(logEncabezado + " - " + logEmiteResult(i) + " = [" + r[i].toString() + "]");
            } else            { logger.info(logEncabezado + " - " + logEmiteResult(i) + " = [null]"); }
        }
        logger.info(logEncabezado + " - Tiempo Ejecucion PL ["+Long.toString(tiempoTotalAux)+"] milisegundos");
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // En caso de ERROR, se retorna el error especificado por el PL
        if (r[8] != null && ((Integer)r[8]).intValue() != 0) {
            Integer codError  = (Integer)r[8];
            String  descError = (r[9]!=null)?((String)r[9]):"";
            //descError = descError.replace("\n", " ");
            BSEExceptionTienda exc = new BSEExceptionTienda(CodigosErrorTienda.error_emision_rector, descError);

            if (r[10] != null) { descError += " / " + (String)r[10]; }

            String aux = "------------------------------------------------------------";
            logger.info(logEncabezado + " - " + aux + "ERROR_TIENDA" + aux);
            logger.info(logEncabezado +  " - ERROR: COD_PL [" + codError.toString()
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

        int    nroPoliza         = (r[0]!=null)?((Integer)r[0]).intValue():0;   // - PL OUT - P_POLIZA_EMITIDA
        double premio            = (r[1]!=null)?((Double)r[1]).doubleValue():0; // - PL OUT - P_PREMIO_COTIZACION
        double premioFacturar    = (r[2]!=null)?((Double)r[2]).doubleValue():0; // - PL OUT - P_PREMIO_COTIZACION_FACTURAR
        String marcaVehiculo     = (String)r[3];                                // - PL OUT - P_MARCA_VEHICULO
        String anioVehiculo      = (String)r[4];                                // - PL OUT - P_ANIO_VEHICULO
        Date   fechaDesde        = (r[5]!=null)?(Date)r[5]:null;                // - PL OUT - P_FECHA_DESDE
        Date   fechaHasta        = (r[6]!=null)?(Date)r[6]:null;                // - PL OUT - P_FECHA_HASTA
        String cuotas            = (String)r[7];                                // - PL OUT - P_CUOTAS


        /*
        int    nroPoliza         = 25893123;
        double premio            = 255.32;
        double premioFacturar    = 233.55;
        String marcaVehiculo     = "CHE700";
        String anioVehiculo      = "2011";
        String categoriaVehiculo = "A1A1";
        java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd");
        Date   fechaDesde        = formatter.parse("2022-08-05");
        Date   fechaHasta        = formatter.parse("2022-12-31");
        String cuotas            = "<cuota><nrocuota>1</nrocuota><motivo>EMISION/ALTA</motivo><factura></factura>"
                                 + "<fechahasta>23-08-2022</fechahasta><primapura>99.93</primapura>"
                                 + "<premio>167.95</premio><premiofacturacion>206.8</premiofacturacion></cuota>";
        */
        //String cuotas = "";

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CARGAR RESULTADOS
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        PolizaIndiviTienda polizaIndivi = new PolizaIndiviTienda();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Ramo
        RamoTienda ramoObj = em.find(RamoTienda.class, Integer.valueOf(ramo));
        polizaIndivi.setRamo(ramoObj);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Producto
        ProductoTienda productoObj = em.find(ProductoTienda.class, producto);
        polizaIndivi.setProducto(productoObj);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Sucursal
        polizaIndivi.setSucursal(sucursal);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Nro. Cotizacion
        polizaIndivi.setNroCotizacion(nroCotizacion);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Tipo y Nro. Documento
        polizaIndivi.setTipoDocumento(tipoDocumento);
        polizaIndivi.setNroDocumento(documento);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Productor
        polizaIndivi.setProductor(Integer.parseInt(productor));
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Moneda
        MonedaTienda monedaObj = eComunManager.consultaMoneda(em, moneda);
        polizaIndivi.setMoneda(monedaObj);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Premio
        polizaIndivi.setPremio(premio);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Premio a facturar
        polizaIndivi.setPremioFacturar(premioFacturar);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Periodo - FECHA DESDE / FECHA HASTA
        polizaIndivi.setFechaDesde(fechaDesde);
        polizaIndivi.setFechaHasta(fechaHasta);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Nro. Poliza
        polizaIndivi.setNroPoliza(nroPoliza);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Plan de Pago
        if ( planPago != null ) {
            PlanPagoTienda planPagoObj = eComunManager.consultaPlanPago(em, Integer.valueOf(planPago));
            polizaIndivi.setPlanPago(planPagoObj);
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Plan de Cobertura
        if ( planCobertura != null ) {
            PlanCoberturaTienda planCoberturaObj = eComunManager.consultaPlanCoberturaRamoProducto(em, ramo, producto, planCobertura);
            polizaIndivi.setPlanCobertura(planCoberturaObj);
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Cuotas
        if ( (cuotas != null) && (!cuotas.equals("")) ) {
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
            polizaIndivi.setCuotas(listaCuotas);
            logger.info(logEncabezado + " - POST - CUOTAS - OK - " + Integer.toString(listaCuotas.size()) + " cuotas.");
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Marca Vehiculo
        MarcaVehiculoTienda marcaObj = null;
        if ( marcaVehiculo != null ) {
            Query queryMarca = em.createNamedQuery("CretTablasTienda.findByClave");
            queryMarca.setParameter("codigoTabla", Integer.valueOf("140001"));
            queryMarca.setParameter("codigoDato", marcaVehiculo);

            List<CretTablasTienda> resultMarca = UtilTienda.castList(CretTablasTienda.class, queryMarca.getResultList());

            if (resultMarca != null && resultMarca.size() > 0) {
                CretTablasTienda tabla = (CretTablasTienda) resultMarca.get(0);
                marcaObj = new MarcaVehiculoTienda(tabla.getDato1(), tabla.getDescripcion());
            }
        }
        //
        polizaIndivi.setMarcaVehiculo(marcaObj);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Categoria Vehiculo
        // -- NO APLICA para INDIVI
        polizaIndivi.setCategoriaVehiculo(null);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Matricula Vehiculo
        polizaIndivi.setMatriculaVehiculo(matriculaVehiculo);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Padron Vehiculo
        polizaIndivi.setPadronVehiculo(padronVehiculo);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Chasis Vehiculo
        polizaIndivi.setChasisVehiculo(chasisVehiculo);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Motor Vehiculo
        polizaIndivi.setMotorVehiculo(motorVehiculo);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Anio Vehiculo
        polizaIndivi.setAnioVehiculo(anioVehiculo);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        logger.info(logEncabezado + " - RESULT : " + polizaIndivi.toString());
        tiempoTotalMetodo = System.currentTimeMillis() - tiempoInicioMetodo;
        logger.info(logEncabezado + " - FIN - Tiempo Ejecucion METODO [" + Long.toString(tiempoTotalMetodo)+"] milisegundos");

        return polizaIndivi;
    }



    /**
     *
     * @param em
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    @Override
    public List<ItemCodiguera> consultaAreasCirculacion(EntityManager em) throws Exception, BSEExceptionTienda {
        String logEncabezado = "INDIVI - consultaAreasCirculacion";
        //logger.info(logEncabezado + " - INICIO");

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Desde BASE de DATOS - Obtener AREAS de CIRCULACION
        Query query = em.createNamedQuery("CretTablasTienda.findTabla");
        query.setParameter("codigoTabla", Integer.valueOf("140007"));

        List<CretTablasTienda> result = UtilTienda.castList(CretTablasTienda.class, query.getResultList());
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Desde CONFIGURACION - Obtener AREAS de CIRCULACION
        HashMap<String, String> map = new HashMap<String, String>();
        String areasConfigStr = CodigosTienda.getCodigos().getAreasCirculacionIndivi(em);

        if ( (areasConfigStr != null) && (!(areasConfigStr.equals(""))) ) {
            String[] areasConfig = areasConfigStr.split(",");
            if ( (areasConfig != null) && (areasConfig.length > 0) ) {
                for (int i=0; i < areasConfig.length; i++) {
                    map.put(areasConfig[i], areasConfig[i]);
                }
            }
        } else { logger.info(logEncabezado + " - CONFIGURACION no registra AREAS de CIRCULACION"); }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // GENERAR RESULTADO - AREAS de CIRCULACION registrados en BASE pero que además estén indicados por CONFIGURACION
        ArrayList<ItemCodiguera> lista = new ArrayList<ItemCodiguera>();
        if (result != null && result.size() > 0) {
            for (int i = 0; i < result.size(); i++) {
                CretTablasTienda tabla = (CretTablasTienda) result.get(i);
                if ( map.containsKey(tabla.getDato1()) ) {
                    ItemCodiguera tipo = new ItemCodiguera(tabla.getDato1(), tabla.getDescripcion());
                    lista.add(tipo);
                }
            }
        } else { logger.info(logEncabezado + " - BASE de DATOS no retorna AREAS de CIRCULACION"); }
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
    public List<ItemCodiguera> consultaTiposVehiculos(EntityManager em) throws Exception, BSEExceptionTienda {
        String logEncabezado = "INDIVI - consultaTiposVehiculos";
        //logger.info(logEncabezado + " - INICIO");

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Desde BASE de DATOS - Obtener TIPOS de VEHICULOS
        Query query = em.createNamedQuery("CretTablasTienda.findTabla");
        query.setParameter("codigoTabla", Integer.valueOf("140004"));

        List<CretTablasTienda> result = UtilTienda.castList(CretTablasTienda.class, query.getResultList());
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Desde CONFIGURACION - Obtener TIPOS de VEHICULOS
        HashMap<String, String> map = new HashMap<String, String>();
        String tiposConfigStr = CodigosTienda.getCodigos().getTiposVehiculosIndivi(em);

        if ( (tiposConfigStr != null) && (!(tiposConfigStr.equals(""))) ) {
            String[] tiposConfig = tiposConfigStr.split(",");
            if ( (tiposConfig != null) && (tiposConfig.length > 0) ) {
                for (int i=0; i < tiposConfig.length; i++) {
                    map.put(tiposConfig[i], tiposConfig[i]);
                }
            }
        } else { logger.info(logEncabezado + " - CONFIGURACION no registra TIPOS de VEHICULOS"); }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // GENERAR RESULTADO - TIPOS de VEHICULOS registrados en BASE pero que además estén indicados por CONFIGURACION
        ArrayList<ItemCodiguera> lista = new ArrayList<ItemCodiguera>();
        if (result != null && result.size() > 0) {
            for (int i = 0; i < result.size(); i++) {
                CretTablasTienda tabla = (CretTablasTienda) result.get(i);
                if ( map.containsKey(tabla.getDato1()) ) {
                    ItemCodiguera tipo = new ItemCodiguera(tabla.getDato1(), tabla.getDescripcion());
                    lista.add(tipo);
                }
            }
        } else { logger.info(logEncabezado + " - BASE de DATOS no retorna TIPOS de VEHICULOS"); }
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
    public List<ItemCodiguera> consultaTiposCombustible(EntityManager em) throws Exception, BSEExceptionTienda {
        String logEncabezado = "INDIVI - consultaTiposCombustible";
        //logger.info(logEncabezado + " - INICIO");

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Desde BASE de DATOS - Obtener TIPOS de COMBUSTIBLES
        Query query = em.createNamedQuery("tiposCombustiblesIndivi");

        @SuppressWarnings("unchecked")
        List<Object[]> l = query.getResultList();
        Iterator<Object[]> it = l.iterator();
        if ( !it.hasNext() ) { logger.info(logEncabezado + " - BASE de DATOS no retorna TIPOS de COMBUSTIBLES"); }

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // GENERAR RESULTADO - TIPOS de COMBUSTIBLES registrados en BASE
        ArrayList<ItemCodiguera> lista = new ArrayList<ItemCodiguera>();
        while ( it.hasNext() ) {
            Object[] r = it.next();
            ItemCodiguera marca = new ItemCodiguera((String)r[0], (String)r[1]);
            lista.add(marca);
        }
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
    public List<ItemCodiguera> consultaMarcasVehiculos(EntityManager em) throws Exception, BSEExceptionTienda {
        String logEncabezado = "INDIVI - consultaMarcasVehiculos";
        //logger.info(logEncabezado + " - INICIO");

        ArrayList<ItemCodiguera> lista = new ArrayList<ItemCodiguera>();
        ItemCodiguera marca = null;

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Desde CONFIGURACION - Obtener MARCAS de VEHICULOS MAS USADAS y se CARGAN al RESULTADO
        HashMap<String, String> map = new HashMap<String, String>();
        String marcasPrefStr = CodigosTienda.getCodigos().getMarcasEmisionIndivi(em);

        if ( (marcasPrefStr != null) && (!(marcasPrefStr.equals(""))) ) {
            String[] marcasPref = marcasPrefStr.split(",");
            if ( (marcasPref != null) && (marcasPref.length > 0) ) {
                for (int i=0; i < marcasPref.length; i++) {
                    String[] aux = marcasPref[i].split("\\|");
                    marca = new ItemCodiguera(aux[0], aux[1]);
                    lista.add(marca);
                    map.put(marca.getCodigo(), marca.getNombre());
                }
            }
        } else { logger.info(logEncabezado + " - CONFIGURACION no registra MARCAS de VEHICULOS MAS USADAS"); }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Desde BASE de DATOS - Obtener MARCAS de VEHICULOS
        Query query = em.createNamedQuery("marcasVehiculosIndivi");

        @SuppressWarnings("unchecked")
        List<Object[]> l = query.getResultList();
        Iterator<Object[]> it = l.iterator();
        if ( !it.hasNext() ) { logger.info(logEncabezado + " - BASE de DATOS no retorna MARCAS de VEHICULOS"); }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // GENERAR RESULTADO - MARCAS de VEHICULOS registrados en BASE excluyendo las ya ingresadas
        while ( it.hasNext() ) {
            Object[] r = it.next();
            if ( ! map.containsKey((String)r[0]) ) { // Si no esta en el MAP, agrego la marca
                marca = new ItemCodiguera((String)r[0], (String)r[1]);
                lista.add(marca);
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        logger.info(logEncabezado + " - RESULT - [" + Integer.toString(lista.size()) + "] elementos.");
        return lista;
    }



    /**
     *
     * @param em
     * @param marcaVehiculo
     * @param anioVehiculo
     * @param tipoVehiculo
     * @param combustible
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    @Override
    public List<ItemCodiguera> consultaFamiliasVehiculos( EntityManager em,
                                                          String marcaVehiculo,
                                                          String anioVehiculo,
                                                          String tipoVehiculo,
                                                          String combustible ) throws Exception, BSEExceptionTienda {
        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        String logEncabezado = "INDIVI - consultaFamiliasVehiculos ";
        String logParametros = "Marca [" + marcaVehiculo + "] Anio [" + anioVehiculo
                           + "] Tipo ["  + tipoVehiculo  + "] Combustible ["  + combustible  + "]";
        //logger.info(logEncabezado + " - INICIO - Parametros -> " + logParametros);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CONTROL de PARAMETROS - OBLIGATORIEDAD
        short er = -1;
        if      (marcaVehiculo == null || marcaVehiculo.equals("")) er = CodigosErrorTienda.marca_vehiculo_invalida;
        else if (anioVehiculo == null || anioVehiculo.equals(""))   er = CodigosErrorTienda.anio_vehiculo_invalido;
        else if (tipoVehiculo == null || tipoVehiculo.equals(""))   er = CodigosErrorTienda.tipo_vehiculo_invalido;
        else if (combustible == null || combustible.equals(""))     er = CodigosErrorTienda.combustible_invalido;
        if ( er > -1 ) { throw new BSEExceptionTienda(er); }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Desde BASE de DATOS - Obtener FAMILIAS de VEHICULOS
        Query query = em.createNamedQuery("familiasVehiculosIndivi");

        // <marca>|<tipo><combustible><anio>
        String parametro = marcaVehiculo + "|" + tipoVehiculo + combustible + anioVehiculo;

        @SuppressWarnings("unchecked")
        List<Object[]> l = query
                                .setParameter(1, parametro)
                                .setParameter(2, parametro)
                                .getResultList();
        Iterator<Object[]> it = l.iterator();
        if ( !it.hasNext() ) { logger.info(logEncabezado + " - BASE de DATOS no retorna FAMILIAS de VEHICULOS"); }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // GENERAR RESULTADO - FAMILIAS de VEHICULOS obtenidos desde la base de datos
        ArrayList<ItemCodiguera> lista = new ArrayList<ItemCodiguera>();
        while ( it.hasNext() ) {
            Object[] r = it.next();
            ItemCodiguera marca = new ItemCodiguera((String)r[0], (String)r[1]);
            lista.add(marca);
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        tiempoTotalMetodo = System.currentTimeMillis() - tiempoInicioMetodo;
        //logger.info(logEncabezado + " - RESULT - [" + Integer.toString(lista.size()) + "] elementos.");
        logger.info(logEncabezado + " - PARAMETROS -> " + logParametros
                                  + " - RESULT -> ["    + Integer.toString(lista.size())
                                  + "] elementos - ["  + Long.toString(tiempoTotalMetodo) + "] milisegundos");
        return lista;
    }



    /**
     *
     * @param em
     * @param marcaVehiculo
     * @param anioVehiculo
     * @param tipoVehiculo
     * @param combustible
     * @param familiaVehiculo
     * @return
     * @throws Exception
     * @throws BSEExceptionTienda
     */
    @Override
    public List<ItemCodiguera> consultaVersionesVehiculos ( EntityManager em,
                                                            String marcaVehiculo,
                                                            String anioVehiculo,
                                                            String tipoVehiculo,
                                                            String combustible,
                                                            String familiaVehiculo ) throws Exception, BSEExceptionTienda {
        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        String logEncabezado = "INDIVI - consultaVersionesVehiculos";
        String logParametros = "Marca ["   + marcaVehiculo   + "] Anio [" + anioVehiculo
                           + "] Tipo ["    + tipoVehiculo    + "] Combustible ["  + combustible
                           + "] Familia [" + familiaVehiculo + "]";
        //logger.info(logEncabezado + " - INICIO - Parametros -> " + logParametros);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // CONTROL de PARAMETROS - OBLIGATORIEDAD
        short er = -1;
        if      (marcaVehiculo == null || marcaVehiculo.equals(""))     er = CodigosErrorTienda.marca_vehiculo_invalida;
        else if (anioVehiculo == null || anioVehiculo.equals(""))       er = CodigosErrorTienda.anio_vehiculo_invalido;
        else if (tipoVehiculo == null || tipoVehiculo.equals(""))       er = CodigosErrorTienda.tipo_vehiculo_invalido;
        else if (combustible == null || combustible.equals(""))         er = CodigosErrorTienda.combustible_invalido;
        else if (familiaVehiculo == null || familiaVehiculo.equals("")) er = CodigosErrorTienda.familia_vehiculo_invalida;
        if ( er > -1 ) { throw new BSEExceptionTienda(er); }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Desde BASE de DATOS - Obtener VERSIONES de VEHICULOS
        Query query = em.createNamedQuery("versionesVehiculosIndivi");

        // <marca>|<tipo><combustible><anio>
        String parametro = marcaVehiculo + "|" + tipoVehiculo + combustible + anioVehiculo;

        @SuppressWarnings("unchecked")
        List<Object[]> l = query
                                .setParameter(1, parametro)
                                .setParameter(2, parametro)
                                .setParameter(3, familiaVehiculo)
                                .setParameter(4, parametro)
                                .setParameter(5, familiaVehiculo)
                                .getResultList();
        Iterator<Object[]> it = l.iterator();
        if ( !it.hasNext() ) { logger.info(logEncabezado + " - BASE de DATOS no retorna VERSIONES de VEHICULOS"); }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // GENERAR RESULTADO - VERSIONES de VEHICULOS obtenidos desde la base de datos
        ArrayList<ItemCodiguera> lista = new ArrayList<ItemCodiguera>();
        while ( it.hasNext() ) {
            Object[] r = it.next();
            ItemCodiguera marca = new ItemCodiguera((String)r[0], (String)r[1]);
            lista.add(marca);
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        tiempoTotalMetodo = System.currentTimeMillis() - tiempoInicioMetodo;
        //logger.info(logEncabezado + " - RESULT - [" + Integer.toString(lista.size()) + "] elementos.");
        logger.info(logEncabezado + " - PARAMETROS -> " + logParametros
                                  + " - RESULT -> ["    + Integer.toString(lista.size())
                                  + "] elementos - ["  + Long.toString(tiempoTotalMetodo) + "] milisegundos");
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
    public DatosVariosIndivi consultaDatosVarios(EntityManager em) throws Exception, BSEExceptionTienda {
        String logEncabezado = "INDIVI - consultaDatosVarios";

        // Sucursal
        String sucursal = CodigosTienda.getCodigos().getSucursalEmisionIndivi(em); // "200"

        // Ramo
        String ramo = CodigosTienda.getCodigos().getRamoEmisionIndivi(em); // "4"
        RamoTienda ramoObj = em.find(RamoTienda.class, Integer.valueOf(ramo));

        // Producto
        String producto = CodigosTienda.getCodigos().getProductoEmisionIndivi(em); // "INDIVI"
        ProductoTienda productoObj = em.find(ProductoTienda.class, producto);

        // Moneda
        String moneda = CodigosTienda.getCodigos().getMonedaEmisionIndivi(em); // "20"
        MonedaTienda monedaObj = em.find(MonedaTienda.class, moneda);

        // Plan Pago Inicial
        String planPagoInicial = CodigosTienda.getCodigos().getPlanPagoInicialIndivi(em); // "10"

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // Cargo INSTANCIA de RETORNAR
        DatosVariosIndivi datosVarios = new DatosVariosIndivi(sucursal, ramoObj, productoObj, monedaObj, planPagoInicial);
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //logger.info(logEncabezado + " - RESULT - [" + datosVarios.toString() + "].");
        logger.info(logEncabezado + " - FIN");
        return datosVarios;
    }



    /**
     *
     * @param logEncabezado
     * @param marcaVehiculo
     * @param anioVehiculo
     * @param tipoVehiculo
     * @param combustible
     * @param versionVehiculo
     * @param areaCirculacion
     */
    private void logCotizarIndiviParametros( String logEncabezado,
                                             String marcaVehiculo, String anioVehiculo,    String tipoVehiculo,
                                             String combustible,   String versionVehiculo, String areaCirculacion ) {
        String logParametros =   "Marca ["           + marcaVehiculo
                             + "] Anio ["            + anioVehiculo
                             + "] Tipo ["            + tipoVehiculo
                             + "] Combustible ["     + combustible
                             + "] Version ["         + versionVehiculo
                             + "] AreaCirculacion [" + areaCirculacion + "]";
        logger.info(logEncabezado + " - Parametros -> " + logParametros);

    }

    /**
     *
     * @param logEncabezado
     * @param titulo
     * @param tipoDocumento
     * @param documento
     * @param tipoFact
     * @param sucursal
     * @param ramo
     * @param producto
     * @param moneda
     * @param medioPago
     * @param origenPago
     * @param promocion
     * @param tipoCalculo
     * @param formaPago
     * @param renovacion
     * @param usuarioWeb
     * @param marcaVehiculo
     * @param tipoVehiculo
     * @param anioVehiculo
     * @param combustible
     * @param modeloVehiculo
     * @param areaCirculacion
     */
    private void logCotizarIndiviPl2( String logEncabezado, String titulo,
                                      String tipoDocumento, String documento,      String tipoFact,
                                      int    sucursal,      int    ramo,           String producto,
                                      String moneda,        int    medioPago,
                                      String origenPago,    String promocion,      String tipoCalculo,
                                      String formaPago,     String renovacion,     String usuarioWeb,
                                      String marcaVehiculo, String tipoVehiculo,   String anioVehiculo,
                                      String combustible,   String modeloVehiculo, String areaCirculacion ) {
        Logueo logueo = new Logueo();
        int pad = 19;
        logueo.setEncabezado(titulo);
        logueo.setParametro("P_TP_DOCUMENTO"    , tipoDocumento              , pad);
        logueo.setParametro("P_NRO_DOCUMENTO"   , documento                  , pad);
        logueo.setParametro("P_TIPO_FACT"       , tipoFact                   , pad);
        logueo.setParametro("P_SUCURSAL"        , Integer.toString(sucursal) , pad);
        logueo.setParametro("P_RAMO"            , Integer.toString(ramo)     , pad);
        logueo.setParametro("P_PRODUCTO"        , producto                   , pad);
        logueo.setParametro("P_MONEDA"          , moneda                     , pad);
        logueo.setParametro("P_MEDIO_PAGO"      , Integer.toString(medioPago), pad);
        logueo.setParametro("P_ORIGEN_PAGO"     , origenPago                 , pad);
        logueo.setParametro("P_PROMOCION"       , promocion                  , pad);
        logueo.setParametro("P_TIPO_CALCULO"    , tipoCalculo                , pad);
        logueo.setParametro("P_FORMA_PAGO"      , formaPago                  , pad);
        logueo.setParametro("P_RENOVACION"      , renovacion                 , pad);
        logueo.setParametro("P_MARCA_VEHICULO"  , marcaVehiculo              , pad);
        logueo.setParametro("P_TIPO_VEHICULO"   , tipoVehiculo               , pad);
        logueo.setParametro("P_ANIO_VEHICULO"   , anioVehiculo               , pad);
        logueo.setParametro("P_COMBUSTIBLE"     , combustible                , pad);
        logueo.setParametro("P_MODELO_VEHICULO" , modeloVehiculo             , pad);
        logueo.setParametro("P_AREA_CIRCULACION", areaCirculacion            , pad);
        logueo.setParametro("P_USUARIO"         , usuarioWeb                 , pad);
        logger.info(logEncabezado + " - " + logueo.getSoloParametros());
    }

    /**
     *
     * @param i
     * @return
     */
    private String logCotizaResult(int i) {
        int pad = 17;
        String result = "R[" + Integer.toString(i) + "]";
        switch (i) {
        case 0:
            result = "R[0]=P_COD_ERROR";
            break;
        case 1:
            result = "R[1]=P_DESC_ERROR";
            break;
        case 2:
            result = "R[2]=P_SQL_ERROR";
            break;
        case 3:
            result = "R[3]=P_RESULTADO";
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
     * @param nroCotizacion
     * @param matriculaVehiculo
     * @param padronVehiculo
     * @param motorVehiculo
     * @param chasisVehiculo
     * @param planCobertura
     * @param planPago
     * @param modalidad
     * @param consumoFinal
     */
    private void logEmitirIndiviParametros( String logEncabezado    ,
                                            String tipoDocumento    , String documento,      long   nroCotizacion,
                                            String matriculaVehiculo, String padronVehiculo,
                                            String motorVehiculo    , String chasisVehiculo,
                                            String planCobertura    , String planPago,
                                            String modalidad        , String consumoFinal ) {
        String logParametros =   "TipoDocumento [" + tipoDocumento
                             + "] Documento ["     + documento
                             + "] NroCotizacion [" + Long.toString(nroCotizacion)
                             + "] Matricula ["     + matriculaVehiculo
                             + "] Padron ["        + padronVehiculo
                             + "] Motor ["         + motorVehiculo
                             + "] Chasis ["        + chasisVehiculo
                             + "] PlanCobertura [" + planCobertura
                             + "] PlanPago ["      + planPago
                             + "] Modalidad ["     + modalidad
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
     * @param nroCotizacion
     * @param matriculaVehiculo
     * @param padronVehiculo
     * @param motorVehiculo
     * @param chasisVehiculo
     * @param planCobertura
     * @param planPago
     * @param consumoFinal
     * @param modalidad
     * @param usuario
     */
    private void logEmitirIndiviPl2( String logEncabezado    , String titulo        ,
                                     String tipoDocumento    , String documento     ,
                                     int    sucursal         , int ramo          , String producto,
                                     long   nroCotizacion    ,
                                     String matriculaVehiculo, String padronVehiculo,
                                     String motorVehiculo    , String chasisVehiculo,
                                     String planCobertura    , String planPago      , String consumoFinal,
                                     String modalidad        , String usuario ) {
        Logueo logueo = new Logueo();
        int pad = 21;
        logueo.setEncabezado(titulo);
        logueo.setParametro("P_TP_DOCUMENTO"      , tipoDocumento               , pad);
        logueo.setParametro("P_NRO_DOCUMENTO"     , documento                   , pad);
        logueo.setParametro("P_SUCURSAL"          , Integer.toString(sucursal)  , pad);
        logueo.setParametro("P_RAMO"              , Integer.toString(ramo)      , pad);
        logueo.setParametro("P_PRODUCTO"          , producto                    , pad);
        logueo.setParametro("P_COTIZACION_EMITIDA", Long.toString(nroCotizacion), pad);
        logueo.setParametro("P_MATRICULA_VEHICULO", matriculaVehiculo           , pad);
        logueo.setParametro("P_PADRON_VEHICULO"   , padronVehiculo              , pad);
        logueo.setParametro("P_MOTOR_VEHICULO"    , motorVehiculo               , pad);
        logueo.setParametro("P_CHASIS_VEHICULO"   , chasisVehiculo              , pad);
        logueo.setParametro("P_PLAN_COBERTURA"    , planCobertura               , pad);
        logueo.setParametro("P_PLAN_PAGO"         , planPago                    , pad);
        logueo.setParametro("P_CONSUMIDOR_FINAL"  , consumoFinal                , pad);
        logueo.setParametro("P_MODALIDAD"         , modalidad                   , pad);
        logueo.setParametro("P_USUARIO"           , usuario                     , pad);
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
            result = "R[5]=P_FECHA_DESDE";
            break;
        case 6:
            result = "R[6]=P_FECHA_HASTA";
            break;
        case 7:
            result = "R[7]=P_CUOTAS";
            break;
        case 8:
            result = "R[8]=P_COD_ERROR";
            break;
        case 9:
            result = "R[9]=P_DESC_ERROR";
            break;
        case 10:
            result = "R[10]=P_SQL_ERROR";
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
    private void logCotizarIndiviPl( String logEncabezado,
                                     String tipoDocumento, String documento,      String tipoFact,
                                     String sucursal,      String moneda,         String medioPago,
                                     String origenPago,    String promocion,      String tipoCalculo,
                                     String formaPago,     String renovacion,     String usuarioWeb,
                                     String marcaVehiculo, String tipoVehiculo,   String anioVehiculo,
                                     String combustible,   String modeloVehiculo, String areaCirculacion ) {

        String logInvocacion = logEncabezado + " ->"
                             +  " P_TP_DOCUMENTO ["     + tipoDocumento
                             + "] P_NRO_DOCUMENTO ["    + documento
                             + "] P_TIPO_FACT ["        + tipoFact
                             + "] P_SUCURSAL ["         + sucursal
                             + "] P_MONEDA ["           + moneda
                             + "] P_MEDIO_PAGO ["       + medioPago
                             + "] P_ORIGEN_PAGO ["      + origenPago
                             + "] P_PROMOCION ["        + promocion
                             + "] P_TIPO_CALCULO ["     + tipoCalculo
                             + "] P_FORMA_PAGO ["       + formaPago
                             + "] P_RENOVACION ["       + renovacion
                             + "] P_MARCA_VEHICULO ["   + marcaVehiculo
                             + "] P_TIPO_VEHICULO ["    + tipoVehiculo
                             + "] P_ANIO_VEHICULO ["    + anioVehiculo
                             + "] P_COMBUSTIBLE ["      + combustible
                             + "] P_MODELO_VEHICULO ["  + modeloVehiculo
                             + "] P_AREA_CIRCULACION [" + areaCirculacion
                             + "] P_USUARIO_WEB ["      + usuarioWeb
                             + "]";
        logger.info(logInvocacion);
    }
    */

    /*
    private void logEmitirIndiviPl( String logEncabezado,
                                    String tipoDocumento    , String documento     , long   nroCotizacion,
                                    String matriculaVehiculo, String padronVehiculo,
                                    String motorVehiculo    , String chasisVehiculo,
                                    String planCobertura    , String planPago      , String consumoFinal,
                                    String modalidad        , String usuario ) {
        String logInvocacion = logEncabezado + " ->"
                             +  " P_TP_DOCUMENTO ["       + tipoDocumento
                             + "] P_NRO_DOCUMENTO ["      + documento
                             + "] P_COTIZACION_EMITIDA [" + Long.toString(nroCotizacion)
                             + "] P_MATRICULA_VEHICULO [" + matriculaVehiculo
                             + "] P_PADRON_VEHICULO ["    + padronVehiculo
                             + "] P_MOTOR_VEHICULO ["     + motorVehiculo
                             + "] P_CHASIS_VEHICULO ["    + chasisVehiculo
                             + "] P_PLAN_COBERTURA ["     + planCobertura
                             + "] P_PLAN_PAGO ["          + planPago
                             + "] P_CONSUMIDOR_FINAL ["   + consumoFinal
                             + "] P_MODALIDAD ["          + modalidad
                             + "] P_USUARIO ["            + usuario
                             + "]";
        logger.info(logInvocacion);
    }
    */


    /*private XmlCotizacion getCotizacion1(java.sql.Clob resultado) {
        XmlCotizacion cotizacionXML = null;
        try {
            JAXBContext jc = JAXBContext.newInstance(XmlCotizacion.class);
            Unmarshaller u = jc.createUnmarshaller();
            StringBuffer xmlStr = new StringBuffer( xml2() );
            cotizacionXML= (XmlCotizacion) u.unmarshal( new StreamSource( new StringReader( xmlStr.toString() ) ) );

            System.out.println(cotizacionXML.toString());

          } catch (JAXBException e) {
            e.printStackTrace();
          }

        return cotizacionXML;
    }

    private static String xml2() {
        return "<?xml version=\"1.0\"?> <cotizacion> <nu-cotizacion>987654321</nu-cotizacion> <lista-cuotas>1,-21,6,10</lista-cuotas> <certificado-list> <certificado> <nu-certificado>1</nu-certificado> <simbolo-moneda>$</simbolo-moneda> <desc-moneda>PESO URUGUAYO</desc-moneda> <plan-cobertura-cert-list> <plan-cobertura-cert> <cod-plan-cobertura-cert>GLOBAL</cod-plan-cobertura-cert> <desc-plan-cobertura-cert>SEGURO GLOBAL</desc-plan-cobertura-cert> <forma-pago-list> <forma-pago> <cod-modalidad>A</cod-modalidad> <desc-modalidad>Anual</desc-modalidad> <mt-prima>19032.07</mt-prima> <mt-premio>31072.77</mt-premio> <excluido>N</excluido> <mt-deducible>16302</mt-deducible> <plan-pago-list> <plan-pago> <cod-plan-pago>1</cod-plan-pago> <desc-plan-pago>1 Cuotas</desc-plan-pago> <mt-cuota>38667</mt-cuota> </plan-pago> <plan-pago> <cod-plan-pago>-20</cod-plan-pago> <desc-plan-pago>2 Cuotas</desc-plan-pago> <mt-cuota>19333</mt-cuota> </plan-pago> <plan-pago> <cod-plan-pago>-21</cod-plan-pago> <desc-plan-pago>3 Cuotas</desc-plan-pago> <mt-cuota>12889</mt-cuota> </plan-pago> <plan-pago> <cod-plan-pago>4</cod-plan-pago> <desc-plan-pago>4 Cuotas</desc-plan-pago> <mt-cuota>10015</mt-cuota> </plan-pago> <plan-pago> <cod-plan-pago>5</cod-plan-pago> <desc-plan-pago>5 Cuotas</desc-plan-pago> <mt-cuota>8066</mt-cuota> </plan-pago> <plan-pago> <cod-plan-pago>6</cod-plan-pago> <desc-plan-pago>6 Cuotas</desc-plan-pago> <mt-cuota>6767</mt-cuota> </plan-pago> <plan-pago> <cod-plan-pago>7</cod-plan-pago> <desc-plan-pago>7 Cuotas</desc-plan-pago> <mt-cuota>5839</mt-cuota> </plan-pago> <plan-pago> <cod-plan-pago>8</cod-plan-pago> <desc-plan-pago>8 Cuotas</desc-plan-pago> <mt-cuota>5148</mt-cuota> </plan-pago> <plan-pago><cod-plan-pago>9</cod-plan-pago> <desc-plan-pago>9 Cuotas</desc-plan-pago><mt-cuota>4606</mt-cuota> </plan-pago><plan-pago> <cod-plan-pago>10</cod-plan-pago><desc-plan-pago>10 Cuotas</desc-plan-pago> <mt-cuota>4172</mt-cuota></plan-pago> </plan-pago-list></forma-pago> <forma-pago><cod-modalidad>3x2</cod-modalidad> <desc-modalidad>3x2</desc-modalidad><mt-prima>4294.21</mt-prima> <mt-premio>7010.96</mt-premio><excluido>S</excluido> <mt-deducible>0</mt-deducible><plan-pago-list> <plan-pago><cod-plan-pago>1</cod-plan-pago> <desc-plan-pago>1 Cuotas</desc-plan-pago><mt-cuota>8724</mt-cuota> </plan-pago><plan-pago> <cod-plan-pago>-20</cod-plan-pago><desc-plan-pago>2 Cuotas</desc-plan-pago> <mt-cuota>4362</mt-cuota></plan-pago> <plan-pago><cod-plan-pago>-21</cod-plan-pago> <desc-plan-pago>3 Cuotas</desc-plan-pago><mt-cuota>2908</mt-cuota> </plan-pago><plan-pago> <cod-plan-pago>4</cod-plan-pago><desc-plan-pago>4 Cuotas</desc-plan-pago> <mt-cuota>2260</mt-cuota></plan-pago> <plan-pago><cod-plan-pago>5</cod-plan-pago> <desc-plan-pago>5 Cuotas</desc-plan-pago><mt-cuota>1820</mt-cuota> </plan-pago><plan-pago> <cod-plan-pago>6</cod-plan-pago><desc-plan-pago>6 Cuotas</desc-plan-pago> <mt-cuota>1527</mt-cuota></plan-pago> <plan-pago><cod-plan-pago>7</cod-plan-pago> <desc-plan-pago>7 Cuotas</desc-plan-pago><mt-cuota>1317</mt-cuota> </plan-pago><plan-pago> <cod-plan-pago>8</cod-plan-pago><desc-plan-pago>8 Cuotas</desc-plan-pago> <mt-cuota>1161</mt-cuota></plan-pago> <plan-pago><cod-plan-pago>9</cod-plan-pago> <desc-plan-pago>9 Cuotas</desc-plan-pago><mt-cuota>1039</mt-cuota> </plan-pago><plan-pago> <cod-plan-pago>10</cod-plan-pago><desc-plan-pago>10 Cuotas</desc-plan-pago> <mt-cuota>941</mt-cuota></plan-pago> </plan-pago-list></forma-pago> </forma-pago-list></plan-cobertura-cert> <plan-cobertura-cert><cod-plan-cobertura-cert>TRIPLE</cod-plan-cobertura-cert> <desc-plan-cobertura-cert>SEGURO TRIPLE</desc-plan-cobertura-cert><forma-pago-list> <forma-pago><cod-modalidad>A</cod-modalidad> <desc-modalidad>Anual</desc-modalidad><mt-prima>9816.88</mt-prima> <mt-premio>16027.56</mt-premio><excluido>N</excluido> <mt-deducible>16302</mt-deducible><plan-pago-list> <plan-pago><cod-plan-pago>1</cod-plan-pago> <desc-plan-pago>1 Cuotas</desc-plan-pago><mt-cuota>19945</mt-cuota> </plan-pago><plan-pago> <cod-plan-pago>-20</cod-plan-pago><desc-plan-pago>2 Cuotas</desc-plan-pago> <mt-cuota>9972</mt-cuota></plan-pago> <plan-pago><cod-plan-pago>-21</cod-plan-pago> <desc-plan-pago>3 Cuotas</desc-plan-pago><mt-cuota>6648</mt-cuota> </plan-pago><plan-pago> <cod-plan-pago>4</cod-plan-pago><desc-plan-pago>4 Cuotas</desc-plan-pago> <mt-cuota>5166</mt-cuota></plan-pago> <plan-pago><cod-plan-pago>5</cod-plan-pago> <desc-plan-pago>5 Cuotas</desc-plan-pago><mt-cuota>4160</mt-cuota> </plan-pago><plan-pago> <cod-plan-pago>6</cod-plan-pago><desc-plan-pago>6 Cuotas</desc-plan-pago> <mt-cuota>3490</mt-cuota></plan-pago> <plan-pago><cod-plan-pago>7</cod-plan-pago> <desc-plan-pago>7 Cuotas</desc-plan-pago><mt-cuota>3012</mt-cuota> </plan-pago><plan-pago> <cod-plan-pago>8</cod-plan-pago><desc-plan-pago>8 Cuotas</desc-plan-pago> <mt-cuota>2655</mt-cuota></plan-pago> <plan-pago><cod-plan-pago>9</cod-plan-pago> <desc-plan-pago>9 Cuotas</desc-plan-pago><mt-cuota>2376</mt-cuota> </plan-pago><plan-pago> <cod-plan-pago>10</cod-plan-pago><desc-plan-pago>10 Cuotas</desc-plan-pago> <mt-cuota>2152</mt-cuota></plan-pago> </plan-pago-list></forma-pago> <forma-pago> <cod-modalidad>3x2</cod-modalidad><desc-modalidad>3x2</desc-modalidad> <mt-prima>7814.77</mt-prima><mt-premio>12758.81</mt-premio> <excluido>N</excluido><mt-deducible>16302</mt-deducible> <plan-pago-list><plan-pago> <cod-plan-pago>1</cod-plan-pago><desc-plan-pago>1 Cuotas</desc-plan-pago> <mt-cuota>15877</mt-cuota></plan-pago> <plan-pago><cod-plan-pago>-20</cod-plan-pago> <desc-plan-pago>2 Cuotas</desc-plan-pago><mt-cuota>7939</mt-cuota> </plan-pago><plan-pago> <cod-plan-pago>-21</cod-plan-pago><desc-plan-pago>3 Cuotas</desc-plan-pago> <mt-cuota>5292</mt-cuota></plan-pago> <plan-pago><cod-plan-pago>4</cod-plan-pago> <desc-plan-pago>4 Cuotas</desc-plan-pago><mt-cuota>4112</mt-cuota> </plan-pago><plan-pago> <cod-plan-pago>5</cod-plan-pago><desc-plan-pago>5 Cuotas</desc-plan-pago> <mt-cuota>3312</mt-cuota></plan-pago> <plan-pago><cod-plan-pago>6</cod-plan-pago> <desc-plan-pago>6 Cuotas</desc-plan-pago><mt-cuota>2778</mt-cuota> </plan-pago><plan-pago> <cod-plan-pago>7</cod-plan-pago><desc-plan-pago>7 Cuotas</desc-plan-pago> <mt-cuota>2397</mt-cuota></plan-pago> <plan-pago><cod-plan-pago>8</cod-plan-pago> <desc-plan-pago>8 Cuotas</desc-plan-pago><mt-cuota>2114</mt-cuota> </plan-pago><plan-pago> <cod-plan-pago>9</cod-plan-pago><desc-plan-pago>9 Cuotas</desc-plan-pago> <mt-cuota>1891</mt-cuota></plan-pago> <plan-pago><cod-plan-pago>10</cod-plan-pago> <desc-plan-pago>10 Cuotas</desc-plan-pago><mt-cuota>1713</mt-cuota> </plan-pago></plan-pago-list> </forma-pago></forma-pago-list> </plan-cobertura-cert></plan-cobertura-cert-list> </certificado></certificado-list> <fe-cotizacion>22-02-2022</fe-cotizacion></cotizacion>";
    }*/

    // Gustavo
    /*private static String xml3() {
        return "<?xml version=\"1.0\"?> <cotizacion>   <nu-cotizacion>41797546</nu-cotizacion>   <lista-cuotas>1,-21,6,10</lista-cuotas>   <certificado-list>     <certificado>       <nu-certificado>1</nu-certificado>       <simbolo-moneda>$</simbolo-moneda>       <desc-moneda>PESO URUGUAYO</desc-moneda>       <plan-cobertura-cert-list>         <plan-cobertura-cert>           <forma-pago-list>             <forma-pago>               <cod-modalidad>A</cod-modalidad>               <desc-modalidad>Anual</desc-modalidad>               <mt-prima>8771.98</mt-prima>               <mt-premio>14321.6</mt-premio>               <excluido>N</excluido>               <mt-deducible>19738</mt-deducible>               <plan-pago-list>                 <plan-pago>                   <cod-plan-pago>1</cod-plan-pago>                   <desc-plan-pago>1 Cuotas</desc-plan-pago>                   <mt-cuota>17822</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>-20</cod-plan-pago>                   <desc-plan-pago>2 Cuotas</desc-plan-pago>                   <mt-cuota>8911</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>-21</cod-plan-pago>                   <desc-plan-pago>3 Cuotas</desc-plan-pago>                   <mt-cuota>5941</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>4</cod-plan-pago>                   <desc-plan-pago>4 Cuotas</desc-plan-pago>                   <mt-cuota>4616</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>5</cod-plan-pago>                   <desc-plan-pago>5 Cuotas</desc-plan-pago>                   <mt-cuota>3718</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>6</cod-plan-pago>                   <desc-plan-pago>6 Cuotas</desc-plan-pago>                   <mt-cuota>3119</mt-cuota>                 </plan-pago>                <plan-pago>                   <cod-plan-pago>7</cod-plan-pago>                   <desc-plan-pago>7 Cuotas</desc-plan-pago>                   <mt-cuota>2691</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>8</cod-plan-pago>                   <desc-plan-pago>8 Cuotas</desc-plan-pago>                   <mt-cuota>2373</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>9</cod-plan-pago>                   <desc-plan-pago>9 Cuotas</desc-plan-pago>                   <mt-cuota>2123</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>10</cod-plan-pago>                   <desc-plan-pago>10 Cuotas</desc-plan-pago>                   <mt-cuota>1923</mt-cuota>                 </plan-pago>               </plan-pago-list>             </forma-pago>             <forma-pago>               <cod-modalidad>3X2</cod-modalidad>               <desc-modalidad>3X2</desc-modalidad>               <mt-prima>17560.86</mt-prima>               <mt-premio>28670.79</mt-premio>               <excluido>N</excluido>               <mt-deducible>19738</mt-deducible>               <plan-pago-list>                 <plan-pago>                   <cod-plan-pago>1</cod-plan-pago>                   <desc-plan-pago>1 Cuotas</desc-plan-pago>                   <mt-cuota>35678</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>-20</cod-plan-pago>                   <desc-plan-pago>2 Cuotas</desc-plan-pago>                   <mt-cuota>17839</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>-21</cod-plan-pago>                   <desc-plan-pago>3 Cuotas</desc-plan-pago>                   <mt-cuota>11893</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>4</cod-plan-pago>                   <desc-plan-pago>4 Cuotas</desc-plan-pago>                   <mt-cuota>9241</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>5</cod-plan-pago>                   <desc-plan-pago>5 Cuotas</desc-plan-pago>                   <mt-cuota>7442</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>6</cod-plan-pago>                   <desc-plan-pago>6 Cuotas</desc-plan-pago>                   <mt-cuota>6244</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>7</cod-plan-pago>                   <desc-plan-pago>7 Cuotas</desc-plan-pago>                   <mt-cuota>5387</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>8</cod-plan-pago>                   <desc-plan-pago>8 Cuotas</desc-plan-pago>                   <mt-cuota>4750</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>9</cod-plan-pago>                   <desc-plan-pago>9 Cuotas</desc-plan-pago>                   <mt-cuota>4250</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>10</cod-plan-pago>                   <desc-plan-pago>10 Cuotas</desc-plan-pago>                   <mt-cuota>3850</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>12</cod-plan-pago>                   <desc-plan-pago>12 Cuotas</desc-plan-pago>                   <mt-cuota>3253</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>24</cod-plan-pago>                   <desc-plan-pago>24 Cuotas</desc-plan-pago>                   <mt-cuota>1802</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>63</cod-plan-pago>                   <desc-plan-pago>3 Anualidades</desc-plan-pago>                   <mt-cuota>14218</mt-cuota>                 </plan-pago>               </plan-pago-list>             </forma-pago>           </forma-pago-list>           <cod-plan-cobertura-cert>DOBLE</cod-plan-cobertura-cert>           <desc-plan-cobertura-cert>SEGURO DOBLE</desc-plan-cobertura-cert>         </plan-cobertura-cert>         <plan-cobertura-cert>           <forma-pago-list>             <forma-pago>               <cod-modalidad>A</cod-modalidad>               <desc-modalidad>Anual</desc-modalidad>               <mt-prima>11455.77</mt-prima>               <mt-premio>18703.3</mt-premio>               <excluido>N</excluido>               <mt-deducible>19738</mt-deducible>               <plan-pago-list>                 <plan-pago>                   <cod-plan-pago>1</cod-plan-pago>                   <desc-plan-pago>1 Cuotas</desc-plan-pago>                   <mt-cuota>23274</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>-20</cod-plan-pago>                   <desc-plan-pago>2 Cuotas</desc-plan-pago>                   <mt-cuota>11637</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>-21</cod-plan-pago>                   <desc-plan-pago>3 Cuotas</desc-plan-pago>                   <mt-cuota>7758</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>4</cod-plan-pago>                   <desc-plan-pago>4 Cuotas</desc-plan-pago>                   <mt-cuota>6028</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>5</cod-plan-pago>                   <desc-plan-pago>5 Cuotas</desc-plan-pago>                   <mt-cuota>4855</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>6</cod-plan-pago>                   <desc-plan-pago>6 Cuotas</desc-plan-pago>                   <mt-cuota>4073</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>7</cod-plan-pago>                   <desc-plan-pago>7 Cuotas</desc-plan-pago>                   <mt-cuota>3514</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>8</cod-plan-pago>                   <desc-plan-pago>8 Cuotas</desc-plan-pago>                   <mt-cuota>3098</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>9</cod-plan-pago>                   <desc-plan-pago>9 Cuotas</desc-plan-pago>                   <mt-cuota>2772</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>10</cod-plan-pago>                   <desc-plan-pago>10 Cuotas</desc-plan-pago>                   <mt-cuota>2511</mt-cuota>                 </plan-pago>               </plan-pago-list>             </forma-pago>             <forma-pago>               <cod-modalidad>3X2</cod-modalidad>               <desc-modalidad>3X2</desc-modalidad>               <mt-prima>22933.61</mt-prima>               <mt-premio>37442.63</mt-premio>               <excluido>N</excluido>               <mt-deducible>19738</mt-deducible>               <plan-pago-list>                 <plan-pago>                   <cod-plan-pago>1</cod-plan-pago>                   <desc-plan-pago>1 Cuotas</desc-plan-pago>                   <mt-cuota>46594</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>-20</cod-plan-pago>                   <desc-plan-pago>2 Cuotas</desc-plan-pago>                   <mt-cuota>23297</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>-21</cod-plan-pago>                   <desc-plan-pago>3 Cuotas</desc-plan-pago>                   <mt-cuota>15531</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>4</cod-plan-pago>                   <desc-plan-pago>4 Cuotas</desc-plan-pago>                   <mt-cuota>12068</mt-cuota>              </plan-pago>                 <plan-pago>                   <cod-plan-pago>5</cod-plan-pago>                   <desc-plan-pago>5 Cuotas</desc-plan-pago>                   <mt-cuota>9719</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>6</cod-plan-pago>                   <desc-plan-pago>6 Cuotas</desc-plan-pago>                   <mt-cuota>8154</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>7</cod-plan-pago>                   <desc-plan-pago>7 Cuotas</desc-plan-pago>                   <mt-cuota>7036</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>8</cod-plan-pago>                   <desc-plan-pago>8 Cuotas</desc-plan-pago>                   <mt-cuota>6203</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>9</cod-plan-pago>                   <desc-plan-pago>9 Cuotas</desc-plan-pago>                   <mt-cuota>5550</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>10</cod-plan-pago>                   <desc-plan-pago>10 Cuotas</desc-plan-pago>                   <mt-cuota>5027</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>12</cod-plan-pago>                   <desc-plan-pago>12 Cuotas</desc-plan-pago>                   <mt-cuota>4248</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>24</cod-plan-pago>                   <desc-plan-pago>24 Cuotas</desc-plan-pago>                   <mt-cuota>2353</mt-cuota>                 </plan-pago>                 <plan-pago>                   <cod-plan-pago>63</cod-plan-pago>                   <desc-plan-pago>3 Anualidades</desc-plan-pago>                   <mt-cuota>18568</mt-cuota>                 </plan-pago>               </plan-pago-list>             </forma-pago>           </forma-pago-list>           <cod-plan-cobertura-cert>TRIPLE</cod-plan-cobertura-cert>           <desc-plan-cobertura-cert>SEGURO TRIPLE</desc-plan-cobertura-cert>         </plan-cobertura-cert>       </plan-cobertura-cert-list>     </certificado>   </certificado-list> </cotizacion> ";
    }*/

}
