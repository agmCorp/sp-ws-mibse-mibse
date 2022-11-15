package com.bse.servicios.ws.indivi;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.log4j.Logger;

import com.bse.accesodatos.comun.ItemCodiguera;
import com.bse.accesodatos.eindivi.CotizacionIndiviTienda;
import com.bse.accesodatos.eindivi.DatosVariosIndivi;
import com.bse.accesodatos.eindivi.ItemDeptoLocalidadArea;
import com.bse.accesodatos.eindivi.PolizaIndiviTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import com.bse.negocio.comun.CodigosErrorTienda;
import com.bse.servicios.comun.IEmisionComunTiendaEJBLocal;
import com.bse.servicios.eindivi.IEmisionIndiviTiendaEJBLocal;
import com.bse.servicios.seguridad.dt.DTSesionTienda;
import com.bse.servicios.ws.comun.ClienteDeudaTiendaResp;


@WebService(serviceName = "EmisionIndivi")
public class EmisionIndiviTienda {

    private static final Logger logger = Logger.getLogger(EmisionIndiviTienda.class);


    @Resource
    private WebServiceContext wsContext;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     * @return
     * @throws NamingException
     */
    private IEmisionIndiviTiendaEJBLocal getEJBManager() throws NamingException {
        InitialContext ctx = new InitialContext();
        IEmisionIndiviTiendaEJBLocal bean = (IEmisionIndiviTiendaEJBLocal)
                                                            ctx.lookup("BseTiendaEar/EmisionIndiviTiendaEJB/local");
        return bean;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     * @return
     * @throws NamingException
     */
    private IEmisionComunTiendaEJBLocal getComunEJBManager() throws NamingException {
        InitialContext ctx = new InitialContext();
        IEmisionComunTiendaEJBLocal bean = (IEmisionComunTiendaEJBLocal)
                                                            ctx.lookup("BseTiendaEar/EmisionComunTiendaEJB/local");
        return bean;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     * @param usuario
     * @param contrasena
     * @return
     */
    private DTSesionTienda getDTSesion(String usuario, String contrasena) {
        MessageContext mc = wsContext.getMessageContext();
        HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
        return new DTSesionTienda(usuario, contrasena, req.getRemoteAddr());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Genera cotizacion para el vehiculo indicado
     * @param usuario - OBLIGATORIO
     * @param contrasena - OBLIGATORIO
     * @param marcaVehiculo - OBLIGATORIO
     * @param anioVehiculo - OBLIGATORIO
     * @param tipoVehiculo - OBLIGATORIO
     * @param combustible - OBLIGATORIO
     * @param versionVehiculo - OBLIGATORIO
     * @param areaCirculacion - OBLIGATORIO
     * @return
     */
    @WebMethod
    public CotizacionIndiviTiendaResp cotizarIndiviAnonimo( @WebParam(name = "usuario")         String usuario,
                                                            @WebParam(name = "contrasena")      String contrasena,
                                                            @WebParam(name = "marcaVehiculo")   String marcaVehiculo,
                                                            @WebParam(name = "anioVehiculo")    String anioVehiculo,
                                                            @WebParam(name = "tipoVehiculo")    String tipoVehiculo,
                                                            @WebParam(name = "combustible")     String combustible,
                                                            @WebParam(name = "versionVehiculo") String versionVehiculo,
                                                            @WebParam(name = "areaCirculacion") String areaCirculacion ){
        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        String logEncabezado = "SERVICE - INDIVI - cotizarIndiviAnonimo - TIENDA";
        logger.info(logEncabezado + " - INICIO");

        CotizacionIndiviTiendaResp result = null;
        try {
            CotizacionIndiviTienda cotizacionIndivi = getEJBManager().cotizarIndiviAnonimo(
                                                                                    getDTSesion(usuario, contrasena),
                                                                                    marcaVehiculo,
                                                                                    anioVehiculo,
                                                                                    tipoVehiculo,
                                                                                    combustible,
                                                                                    versionVehiculo,
                                                                                    areaCirculacion );
            result = new CotizacionIndiviTiendaResp(cotizacionIndivi, "00", "");

        } catch (BSEExceptionTienda ex2) {
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result = new CotizacionIndiviTiendaResp(codError, descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result = new CotizacionIndiviTiendaResp(codError, descError);
            logger.error(logEncabezado + logError(codError, descError), ex1);
            ex1.printStackTrace();
        }

        //logger.info(logEncabezado + " - RESULT : " + result.toString());
        tiempoTotalMetodo = System.currentTimeMillis() - tiempoInicioMetodo;
        logger.info(logEncabezado + " - FIN - TIEMPO TOTAL EJECUCION [" + Long.toString(tiempoTotalMetodo)+"] milisegundos");
        return result;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Genera la póliza para el indicado indicado y de acuerdo a la cotización previamente realizada
     * @param usuario - OBLIGATORIO
     * @param contrasena - OBLIGATORIO
     * @param tipoDocumento - OBLIGATORIO
     * @param documento - OBLIGATORIO
     * @param nroCotizacion - OBLIGATORIO
     * @param matriculaVehiculo - OPCIONAL
     * @param padronVehiculo - OPCIONAL
     * @param motorVehiculo - OBLIGATORIO, en conjunto con chasis, al menos uno de ellos debe indicarse
     * @param chasisVehiculo - OBLIGATORIO, en conjunto con motor, al menos uno de ellos debe indicarse
     * @param planCobertura - OBLIGATORIO
     * @param planPago - OBLIGATORIO
     * @param modalidad - OBLIGATORIO
     * @param consumoFinal - OBLIGATORIO - Valor: S o N
     * @return
     */
    @WebMethod
    public EmisionIndiviTiendaResp emitirIndivi( @WebParam(name = "usuario")            String usuario,
                                                 @WebParam(name = "contrasena")         String contrasena,
                                                 @WebParam(name = "tipoDocumento")      String tipoDocumento,
                                                 @WebParam(name = "documento")          String documento,
                                                 @WebParam(name = "nroCotizacion")      long   nroCotizacion,
                                                 @WebParam(name = "matriculaVehiculo")  String matriculaVehiculo,
                                                 @WebParam(name = "padronVehiculo")     String padronVehiculo,
                                                 @WebParam(name = "motorVehiculo")      String motorVehiculo,
                                                 @WebParam(name = "chasisVehiculo")     String chasisVehiculo,
                                                 @WebParam(name = "planCobertura")      String planCobertura,
                                                 @WebParam(name = "planPago")           String planPago,
                                                 @WebParam(name = "modalidad")          String modalidad,
                                                 @WebParam(name = "consumoFinal")       String consumoFinal ) {
        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        String logEncabezado = "SERVICE - INDIVI - emitirIndivi - TIENDA";
        logger.info(logEncabezado + " - INICIO");

        EmisionIndiviTiendaResp result = null;
        try {
            PolizaIndiviTienda polizaIndivi = getEJBManager().emitirIndivi( getDTSesion(usuario, contrasena),
                                                                            tipoDocumento,
                                                                            documento,
                                                                            nroCotizacion,
                                                                            matriculaVehiculo,
                                                                            padronVehiculo,
                                                                            motorVehiculo,
                                                                            chasisVehiculo,
                                                                            planCobertura,
                                                                            planPago,
                                                                            modalidad,
                                                                            consumoFinal );
            result = new EmisionIndiviTiendaResp(polizaIndivi, "00", "");

        } catch (BSEExceptionTienda ex2) {
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result = new EmisionIndiviTiendaResp(codError, descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result = new EmisionIndiviTiendaResp(codError, descError);
            logger.error(logEncabezado + logError(codError, descError), ex1);
            ex1.printStackTrace();
        }

        //logger.info(logEncabezado + " - RESULT : " + result.toString());
        tiempoTotalMetodo = System.currentTimeMillis() - tiempoInicioMetodo;
        logger.info(logEncabezado + " - FIN - TIEMPO TOTAL EJECUCION [" + Long.toString(tiempoTotalMetodo)+"] milisegundos");
        return result;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Verifica la existencia de deuda del cliente especificado
     * @param usuario - OBLIGATORIO
     * @param contrasena - OBLIGATORIO
     * @param tipoDocumento - OBLIGATORIO
     * @param documento - OBLIGATORIO
     * @param nroCotizacion - OBLIGATORIO
     * @param nroCertificado
     * @return
     */
    @WebMethod
    public ClienteDeudaTiendaResp controlarClienteConDeuda( @WebParam(name = "usuario")        String usuario,
                                                            @WebParam(name = "contrasena")     String contrasena,
                                                            @WebParam(name = "tipoDocumento")  String tipoDocumento,
                                                            @WebParam(name = "documento")      String documento,
                                                            @WebParam(name = "nroCotizacion")  int nroCotizacion,
                                                            @WebParam(name = "nroCertificado") int nroCertificado ) {
        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        String logEncabezado = "SERVICE - INDIVI - controlarClienteConDeuda - TIENDA";
        logger.info(logEncabezado + " - INICIO");

        ClienteDeudaTiendaResp result = null;
        try {
            String tieneDeuda = getComunEJBManager().clienteConDeuda( getDTSesion(usuario, contrasena),
                                                                      tipoDocumento,
                                                                      documento,
                                                                      nroCotizacion,
                                                                      nroCertificado);
            result = new ClienteDeudaTiendaResp(tieneDeuda, "00", "");

        } catch (BSEExceptionTienda ex2) {
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result = new ClienteDeudaTiendaResp(codError, descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result = new ClienteDeudaTiendaResp(codError, descError);
            logger.error(logEncabezado + logError(codError, descError), ex1);
            ex1.printStackTrace();
        }

        //logger.info(logEncabezado + " - RESULT : " + result.toString());
        tiempoTotalMetodo = System.currentTimeMillis() - tiempoInicioMetodo;
        logger.info(logEncabezado + " - FIN - TIEMPO TOTAL EJECUCION [" + Long.toString(tiempoTotalMetodo)+"] milisegundos");
        return result;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Retorna las areas de circulación habilitadas para la tienda de acuerdo al producto
     * @param usuario - OBLIGATORIO
     * @param contrasena - OBLIGATORIO
     * @return
     */
    @WebMethod
    public AreasCirculacionTiendaResp consultaAreasCirculacion( @WebParam(name = "usuario")    String usuario,
                                                                @WebParam(name = "contrasena") String contrasena ) {
        String logEncabezado = "SERVICE - INDIVI - consultaAreasCirculacion - TIENDA";

        AreasCirculacionTiendaResp result = null;
        try {
            List<ItemCodiguera> lista = getEJBManager().consultaAreasCirculacion(getDTSesion(usuario, contrasena));
            result = new AreasCirculacionTiendaResp(lista, "00", "");

        } catch (BSEExceptionTienda ex2) {
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result = new AreasCirculacionTiendaResp(codError, descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result = new AreasCirculacionTiendaResp(codError, descError);
            logger.error(logEncabezado + logError(codError, descError), ex1);
            ex1.printStackTrace();
        }

        //logger.info(logEncabezado + " - RESULT : " + result.toString());
        return result;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Retorna los tipos de vehiculos habilitados para la tienda de acuerdo al producto
     * @param usuario - OBLIGATORIO
     * @param contrasena - OBLIGATORIO
     * @return
     */
    @WebMethod
    public TiposVehiculosTiendaResp consultaTiposVehiculos( @WebParam(name = "usuario")    String usuario,
                                                            @WebParam(name = "contrasena") String contrasena ) {
        String logEncabezado = "SERVICE - INDIVI - consultaTiposVehiculos - TIENDA";

        TiposVehiculosTiendaResp result = null;
        try {
            List<ItemCodiguera> lista = getEJBManager().consultaTiposVehiculos(getDTSesion(usuario, contrasena));
            result = new TiposVehiculosTiendaResp(lista, "00", "");

        } catch (BSEExceptionTienda ex2) {
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result = new TiposVehiculosTiendaResp(codError, descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result = new TiposVehiculosTiendaResp(codError, descError);
            logger.error(logEncabezado + logError(codError, descError), ex1);
            ex1.printStackTrace();
        }

        //logger.info(logEncabezado + " - RESULT : " + result.toString());
        return result;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Retorna los tipos de combustibles habilitados para la tienda de acuerdo al producto
     * @param usuario - OBLIGATORIO
     * @param contrasena - OBLIGATORIO
     * @return
     */
    @WebMethod
    public TiposCombustibleTiendaResp consultaTiposCombustibles( @WebParam(name = "usuario")    String usuario,
                                                                 @WebParam(name = "contrasena") String contrasena ) {
        String logEncabezado = "SERVICE - INDIVI - consultaTiposCombustibles - TIENDA";

        TiposCombustibleTiendaResp result = null;
        try {
            List<ItemCodiguera> lista = getEJBManager().consultaTiposCombustible(getDTSesion(usuario, contrasena));
            result = new TiposCombustibleTiendaResp(lista, "00", "");

        } catch (BSEExceptionTienda ex2) {
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result = new TiposCombustibleTiendaResp(codError, descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result = new TiposCombustibleTiendaResp(codError, descError);
            logger.error(logEncabezado + logError(codError, descError), ex1);
            ex1.printStackTrace();
        }

        //logger.info(logEncabezado + " - RESULT : " + result.toString());
        return result;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Retorna las marcas de vehiculos habilitadas para la tienda de acuerdo al producto
     * @param usuario - OBLIGATORIO
     * @param contrasena - OBLIGATORIO
     * @return
     */
    @WebMethod
    public MarcasVehiculosTiendaResp consultaMarcasVehiculos( @WebParam(name = "usuario")    String usuario,
                                                              @WebParam(name = "contrasena") String contrasena ) {
        String logEncabezado = "SERVICE - INDIVI - consultaMarcasVehiculos - TIENDA";

        MarcasVehiculosTiendaResp result = null;
        try {
            List<ItemCodiguera> lista = getEJBManager().consultaMarcasVehiculos(getDTSesion(usuario, contrasena));
            result = new MarcasVehiculosTiendaResp(lista, "00", "");

        } catch (BSEExceptionTienda ex2) {
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result = new MarcasVehiculosTiendaResp(codError, descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result = new MarcasVehiculosTiendaResp(codError, descError);
            logger.error(logEncabezado + logError(codError, descError), ex1);
            ex1.printStackTrace();
        }

        //logger.info(logEncabezado + " - RESULT : " + result.toString());
        return result;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Retorna las familias de vehiculos habilitadas según los parametros recibidos
     * @param usuario - OBLIGATORIO
     * @param contrasena - OBLIGATORIO
     * @param marcaVehiculo - OBLIGATORIO
     * @param anioVehiculo - OBLIGATORIO
     * @param tipoVehiculo - OBLIGATORIO
     * @param combustible - OBLIGATORIO
     * @return
     */
    @WebMethod
    public FamiliasVehiculosTiendaResp consultaFamiliasVehiculos( @WebParam(name = "usuario")       String usuario,
                                                                  @WebParam(name = "contrasena")    String contrasena,
                                                                  @WebParam(name = "marcaVehiculo") String marcaVehiculo,
                                                                  @WebParam(name = "anioVehiculo")  String anioVehiculo,
                                                                  @WebParam(name = "tipoVehiculo")  String tipoVehiculo,
                                                                  @WebParam(name = "combustible")   String combustible ) {
        String logEncabezado = "SERVICE - INDIVI - consultaFamiliasVehiculos - TIENDA";

        FamiliasVehiculosTiendaResp result = null;
        try {
            List<ItemCodiguera> lista = getEJBManager().consultaFamiliasVehiculos( getDTSesion(usuario, contrasena),
                                                                                     marcaVehiculo,
                                                                                     anioVehiculo,
                                                                                     tipoVehiculo,
                                                                                     combustible );
            result = new FamiliasVehiculosTiendaResp(lista, "00", "");

        } catch (BSEExceptionTienda ex2) {
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result = new FamiliasVehiculosTiendaResp(codError, descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result = new FamiliasVehiculosTiendaResp(codError, descError);
            logger.error(logEncabezado + logError(codError, descError), ex1);
            ex1.printStackTrace();
        }

        //logger.info(logEncabezado + " - RESULT : " + result.toString());
        return result;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Retorna las versiones de vehiculos habilitados según los parametros recibidos
     * @param usuario - OBLIGATORIO
     * @param contrasena - OBLIGATORIO
     * @param marcaVehiculo - OBLIGATORIO
     * @param anioVehiculo - OBLIGATORIO
     * @param tipoVehiculo - OBLIGATORIO
     * @param combustible - OBLIGATORIO
     * @param familiaVehiculo - OBLIGATORIO
     * @return
     */
    @WebMethod
    public VersionesVehiculosTiendaResp consultaVersionesVehiculos( @WebParam(name = "usuario")         String usuario,
                                                                    @WebParam(name = "contrasena")      String contrasena,
                                                                    @WebParam(name = "marcaVehiculo")   String marcaVehiculo,
                                                                    @WebParam(name = "anioVehiculo")    String anioVehiculo,
                                                                    @WebParam(name = "tipoVehiculo")    String tipoVehiculo,
                                                                    @WebParam(name = "combustible")     String combustible,
                                                                    @WebParam(name = "familiaVehiculo") String familiaVehiculo ) {
        String logEncabezado = "SERVICE - INDIVI - consultaVersionesVehiculos - TIENDA";

        VersionesVehiculosTiendaResp result = null;
        try {
            List<ItemCodiguera> lista = getEJBManager().consultaVersionesVehiculos( getDTSesion(usuario, contrasena),
                                                                                    marcaVehiculo,
                                                                                    anioVehiculo,
                                                                                    tipoVehiculo,
                                                                                    combustible,
                                                                                    familiaVehiculo);
            result = new VersionesVehiculosTiendaResp(lista, "00", "");

        } catch (BSEExceptionTienda ex2) {
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result = new VersionesVehiculosTiendaResp(codError, descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result = new VersionesVehiculosTiendaResp(codError, descError);
            logger.error(logEncabezado + logError(codError, descError), ex1);
            ex1.printStackTrace();
        }

        //logger.info(logEncabezado + " - RESULT : " + result.toString());
        return result;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Retorna un conjunto de datos fijos utilizados por la capa de presentacion para el producto INDIVI
     * @param usuario - OBLIGATORIO
     * @param contrasena - OBLIGATORIO
     * @return
     */
    @WebMethod
    public DatosVariosTiendaResp consultaDatosVarios( @WebParam(name = "usuario")    String usuario,
                                                      @WebParam(name = "contrasena") String contrasena ) {
        String logEncabezado = "SERVICE - INDIVI - consultaDatosVarios - TIENDA";

        DatosVariosTiendaResp result = null;
        try {
            DatosVariosIndivi datosVarios = getEJBManager().consultaDatosVarios(getDTSesion(usuario, contrasena));
            result = new DatosVariosTiendaResp(datosVarios, "00", "");

        } catch (BSEExceptionTienda ex2) {
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result = new DatosVariosTiendaResp(codError, descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result = new DatosVariosTiendaResp(codError, descError);
            logger.error(logEncabezado + logError(codError, descError), ex1);
            ex1.printStackTrace();
        }

        //logger.info(logEncabezado + " - RESULT : " + result.toString());
        return result;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Retorna el área de circulación asociada a cada uno de los departamentos
     * En el caso que dentro de un departamento se tengan mas de un área asociada, se retorna valor nulo para el área.
     * @param usuario - OBLIGATORIO
     * @param contrasena - OBLIGATORIO
     * @return
     */
    @WebMethod
    public DeptosAreaCirculacionResp consultaDepartamentosArea( @WebParam(name = "usuario")    String usuario,
                                                                @WebParam(name = "contrasena") String contrasena ) {
        String logEncabezado = "SERVICE - INDIVI - consultaDepartamentosArea - TIENDA";

        DeptosAreaCirculacionResp result = null;
        try {
            List<ItemDeptoLocalidadArea> lista = getEJBManager().consultaDepartamentosArea(getDTSesion(usuario, contrasena));
            result = new DeptosAreaCirculacionResp(lista, "00", "");

        } catch (BSEExceptionTienda ex2) {
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result = new DeptosAreaCirculacionResp(codError, descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result = new DeptosAreaCirculacionResp(codError, descError);
            logger.error(logEncabezado + logError(codError, descError), ex1);
            ex1.printStackTrace();
        }

        //logger.info(logEncabezado + " - RESULT : " + result.toString());
        return result;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Retorna el área de circulación asociada a cada uno de las localidades del departamento dado.
     * @param usuario - OBLIGATORIO
     * @param contrasena - OBLIGATORIO
     * @param idDepto - OBLIGATORIO
     * @return
     */
    @WebMethod
    public LocalidadesAreaCirculacionResp consultaLocalidadesDeptoArea( @WebParam(name = "usuario")      String usuario,
                                                                        @WebParam(name = "contrasena")   String contrasena,
                                                                        @WebParam(name = "departamento") String idDepto ) {
        String logEncabezado = "SERVICE - INDIVI - consultaLocalidadesDeptoArea - TIENDA";

        LocalidadesAreaCirculacionResp result = null;
        try {
            List<ItemDeptoLocalidadArea> lista =
                                getEJBManager().consultaLocalidadesDeptoArea(getDTSesion(usuario, contrasena), idDepto);
            result = new LocalidadesAreaCirculacionResp(lista, "00", "");

        } catch (BSEExceptionTienda ex2) {
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result = new LocalidadesAreaCirculacionResp(codError, descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result = new LocalidadesAreaCirculacionResp(codError, descError);
            logger.error(logEncabezado + logError(codError, descError), ex1);
            ex1.printStackTrace();
        }

        //logger.info(logEncabezado + " - RESULT : " + result.toString());
        return result;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     * @param codigo
     * @param descripcion
     * @return
     */
    private String logError(String codigo, String descripcion) {
        return " - ERROR: CODIGO=[" + codigo + "] DESCRIPCION=[" + descripcion + "]";
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
