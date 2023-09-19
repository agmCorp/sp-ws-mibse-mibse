package com.bse.servicios.ws.soa;

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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bse.accesodatos.esoa.CategoriaVehiculoTienda;
import com.bse.accesodatos.esoa.CotizacionSoaTienda;
import com.bse.accesodatos.esoa.MarcaVehiculoTienda;
import com.bse.accesodatos.esoa.PlanCoberturaTienda;
import com.bse.accesodatos.esoa.PlanPagoTienda;
import com.bse.accesodatos.esoa.PolizaSoaTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import com.bse.negocio.comun.CodigosErrorTienda;
import com.bse.servicios.comun.IEmisionComunTiendaEJBLocal;
import com.bse.servicios.esoa.IEmisionSoaTiendaEJBLocal;
import com.bse.servicios.seguridad.dt.DTSesionTienda;

import uy.com.bse.common.audit.annotations.Audit;
import uy.com.bse.common.audit.interceptors.AuditorManager;
import uy.com.bse.common.webservices.monitoring.constants.Constants;

@WebService(serviceName = "EmisionSoa")
public class EmisionSoaTienda {

    private static final Logger logger = LogManager.getLogger(EmisionSoaTienda.class);


    @Resource
    private WebServiceContext wsContext;

    /**
     *
     * @return
     * @throws NamingException
     */
    private IEmisionSoaTiendaEJBLocal getEJBManager() throws NamingException {
        InitialContext ctx = new InitialContext();
   //     IEmisionSoaTiendaEJBLocal bean = (IEmisionSoaTiendaEJBLocal)
   //                                                         ctx.lookup("BseTiendaEar/EmisionSoaTiendaEJB/local");
        IEmisionSoaTiendaEJBLocal bean = (IEmisionSoaTiendaEJBLocal)
                ctx.lookup("java:global/BseTiendaEar/BseTiendaEjb/EmisionSoaTiendaEJB!com.bse.servicios.esoa.IEmisionSoaTiendaEJBLocal");
return bean;

    }

    /**
    *
    * @return
    * @throws NamingException
    */
    private IEmisionComunTiendaEJBLocal getComunEJBManager() throws NamingException {
        InitialContext ctx = new InitialContext();
      //  IEmisionComunTiendaEJBLocal bean = (IEmisionComunTiendaEJBLocal)
        //                                                    ctx.lookup("BseTiendaEar/EmisionComunTiendaEJB/local");
        IEmisionComunTiendaEJBLocal bean = (IEmisionComunTiendaEJBLocal)
                ctx.lookup("java:global/BseTiendaEar/BseTiendaEjb/EmisionComunTiendaEJB!com.bse.servicios.comun.IEmisionComunTiendaEJBLocal");

        return bean;
    }

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


    /**
    *
    * @param usuario
    * @param contrasena
    * @param planCobertura
    * @param marcaVehiculo
    * @param anioVehiculo
    * @param categoriaVehiculo
    * @param planPago
    * @return
    */
    @Audit(excludedParams = "arg1")
    @WebMethod
    public CotizacionSoaTiendaResp cotizarSoaAnonimo( @WebParam(name = "usuario")           String usuario,
                                                      @WebParam(name = "contrasena")        String contrasena,
                                                      @WebParam(name = "planCobertura")     String planCobertura,
                                                      @WebParam(name = "marcaVehiculo")     String marcaVehiculo,
                                                      @WebParam(name = "anioVehiculo")      String anioVehiculo,
                                                      @WebParam(name = "categoriaVehiculo") String categoriaVehiculo,
                                                      @WebParam(name = "planPago")          int    planPago ) {
        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        String logEncabezado = "SERVICE - SOA - cotizarSoaAnonimo - TIENDA";
        logger.info(logEncabezado + " - INICIO");

        CotizacionSoaTiendaResp result = new CotizacionSoaTiendaResp();
        try {

			AuditorManager.addStatusAudit(Constants.STATUS_OK);
            result.setCodigoError("00");
            result.setDescripcionError("");
            CotizacionSoaTienda cotizacionSoa = getEJBManager().cotizarSoaAnonimo( getDTSesion(usuario, contrasena),
                                                                                   planCobertura,
                                                                                   marcaVehiculo,
                                                                                   anioVehiculo,
                                                                                   categoriaVehiculo,
                                                                                   planPago );
            cotizacionSoa.setMatriculaVehiculo("");
            result.setCotizacionSoa(cotizacionSoa);

        } catch (BSEExceptionTienda ex2) {
        	AuditorManager.addStatusAudit(Constants.STATUS_ERROR);
			AuditorManager.addExceptionAudit(ex2);
			AuditorManager.LogExceptionAudit(ex2);
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
        	AuditorManager.addStatusAudit(Constants.STATUS_ERROR);
			AuditorManager.addExceptionAudit(ex1);
			AuditorManager.LogExceptionAudit(ex1);
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex1);
            ex1.printStackTrace();
        }

        tiempoTotalMetodo = System.currentTimeMillis() - tiempoInicioMetodo;
        logger.info(logEncabezado + " - FIN - TIEMPO TOTAL EJECUCION [" + Long.toString(tiempoTotalMetodo)+"] milisegundos");
        return result;
    }


    /**
     *
     * @param usuario
     * @param contrasena
     * @param tipoDocumento
     * @param documento
     * @param matriculaVehiculo
     * @param padronVehiculo
     * @param motorVehiculo
     * @param chasisVehiculo
     * @param nroCotizacion
     * @param consumoFinal
     * @return
     */
    @Audit(excludedParams = "arg1")
    @WebMethod
    public EmisionSoaTiendaResp emitirSoa( @WebParam(name = "usuario")           String usuario,
                                           @WebParam(name = "contrasena")        String contrasena,
                                           @WebParam(name = "tipoDocumento")     String tipoDocumento,
                                           @WebParam(name = "documento")         String documento,
                                           @WebParam(name = "matriculaVehiculo") String matriculaVehiculo,
                                           @WebParam(name = "padronVehiculo")    String padronVehiculo,
                                           @WebParam(name = "motorVehiculo")     String motorVehiculo,
                                           @WebParam(name = "chasisVehiculo")    String chasisVehiculo,
                                           @WebParam(name = "nroCotizacion")     long   nroCotizacion,
                                           @WebParam(name = "consumoFinal")      String consumoFinal ) {
        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        String logEncabezado = "SERVICE - SOA - emitirSoa - TIENDA";
        logger.info(logEncabezado + " - INICIO");

        EmisionSoaTiendaResp result = new EmisionSoaTiendaResp();
        try {

			AuditorManager.addStatusAudit(Constants.STATUS_OK);
            result.setCodigoError("00");
            result.setDescripcionError("");
            PolizaSoaTienda polizaSoa = getEJBManager().emitirSoa( getDTSesion(usuario, contrasena),
                                                                   tipoDocumento,
                                                                   documento,
                                                                   matriculaVehiculo,
                                                                   padronVehiculo,
                                                                   motorVehiculo,
                                                                   chasisVehiculo,
                                                                   nroCotizacion,
                                                                   consumoFinal );
            result.setPolizaSoa(polizaSoa);

        } catch (BSEExceptionTienda ex2) {
        	AuditorManager.addStatusAudit(Constants.STATUS_ERROR);
			AuditorManager.addExceptionAudit(ex2);
			AuditorManager.LogExceptionAudit(ex2);
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
        	AuditorManager.addStatusAudit(Constants.STATUS_ERROR);
			AuditorManager.addExceptionAudit(ex1);
			AuditorManager.LogExceptionAudit(ex1);
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex1);
            ex1.printStackTrace();
        }

        tiempoTotalMetodo = System.currentTimeMillis() - tiempoInicioMetodo;
        logger.info(logEncabezado + " - FIN - TIEMPO TOTAL EJECUCION [" + Long.toString(tiempoTotalMetodo)+"] milisegundos");
        return result;
    }


    /**
     *
     * @param usuario
     * @param contrasena
     * @return
     */
    @Audit(excludedParams = "arg1")
    @WebMethod
    public MarcasVehiculosTiendaResp consultaMarcasVehiculos( @WebParam(name = "usuario")    String usuario,
                                                              @WebParam(name = "contrasena") String contrasena ) {
        String logEncabezado = "SERVICE - SOA - consultaMarcasVehiculos - TIENDA";

        MarcasVehiculosTiendaResp result = new MarcasVehiculosTiendaResp();
        try {

			AuditorManager.addStatusAudit(Constants.STATUS_OK);
            result.setCodigoError("00");
            result.setDescripcionError("");
            List<MarcaVehiculoTienda> lista = getEJBManager().consultaMarcasVehiculos(getDTSesion(usuario, contrasena));
            result.setMarcasVehiculos(lista);

        } catch (BSEExceptionTienda ex2) {
        	AuditorManager.addStatusAudit(Constants.STATUS_ERROR);
			AuditorManager.addExceptionAudit(ex2);
			AuditorManager.LogExceptionAudit(ex2);
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
        	AuditorManager.addStatusAudit(Constants.STATUS_ERROR);
			AuditorManager.addExceptionAudit(ex1);
			AuditorManager.LogExceptionAudit(ex1);
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex1);
            ex1.printStackTrace();
        }
        return result;
    }


    /**
     *
     * @param usuario
     * @param contrasena
     * @param marca
     * @return
     */
    @Audit(excludedParams = "arg1")
    @WebMethod
    public MarcaVehiculoTiendaResp consultaMarcaVehiculo( @WebParam(name = "usuario")    String usuario,
                                                          @WebParam(name = "contrasena") String contrasena,
                                                          @WebParam(name = "marca")      String marca ) {
        String logEncabezado = "SERVICE - SOA - consultaMarcaVehiculo - TIENDA";

        MarcaVehiculoTiendaResp result = new MarcaVehiculoTiendaResp();
        try {

			AuditorManager.addStatusAudit(Constants.STATUS_OK);
            result.setCodigoError("00");
            result.setDescripcionError("");
            MarcaVehiculoTienda marcaObj = getEJBManager().consultaMarcaVehiculo(getDTSesion(usuario, contrasena), marca);
            result.setMarcaVehiculo(marcaObj);

        } catch (BSEExceptionTienda ex2) {
        	AuditorManager.addStatusAudit(Constants.STATUS_ERROR);
			AuditorManager.addExceptionAudit(ex2);
			AuditorManager.LogExceptionAudit(ex2);
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
        	AuditorManager.addStatusAudit(Constants.STATUS_ERROR);
			AuditorManager.addExceptionAudit(ex1);
			AuditorManager.LogExceptionAudit(ex1);
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex1);
            ex1.printStackTrace();
        }
        return result;
    }


    /**
     *
     * @param usuario
     * @param contrasena
     * @return
     */
    @Audit(excludedParams = "arg1")
    @WebMethod
    public CategoriasVehiculosTiendaResp consultaCategoriasVehiculos( @WebParam(name = "usuario")    String usuario,
                                                                      @WebParam(name = "contrasena") String contrasena ) {
        String logEncabezado = "SERVICE - SOA - consultaCategoriasVehiculos - TIENDA";

        CategoriasVehiculosTiendaResp result = new CategoriasVehiculosTiendaResp();
        try {

			AuditorManager.addStatusAudit(Constants.STATUS_OK);
            result.setCodigoError("00");
            result.setDescripcionError("");
            List<CategoriaVehiculoTienda> lista = getEJBManager().consultaCategoriasVehiculos(getDTSesion(usuario, contrasena));
            result.setCategoriasVehiculos(lista);

        } catch (BSEExceptionTienda ex2) {
        	AuditorManager.addStatusAudit(Constants.STATUS_ERROR);
			AuditorManager.addExceptionAudit(ex2);
			AuditorManager.LogExceptionAudit(ex2);
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
        	AuditorManager.addStatusAudit(Constants.STATUS_ERROR);
			AuditorManager.addExceptionAudit(ex1);
			AuditorManager.LogExceptionAudit(ex1);
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex1);
            ex1.printStackTrace();
        }
        return result;
    }


    /**
     *
     * @param usuario
     * @param contrasena
     * @param categoria
     * @return
     */
    @Audit(excludedParams = "arg1")
    @WebMethod
    public CategoriaVehiculoTiendaResp consultaCategoriaVehiculo( @WebParam(name = "usuario")    String usuario,
                                                                  @WebParam(name = "contrasena") String contrasena,
                                                                  @WebParam(name = "categoria")  String categoria) {
        String logEncabezado = "SERVICE - SOA - consultaCategoriaVehiculo - TIENDA";

        CategoriaVehiculoTiendaResp result = new CategoriaVehiculoTiendaResp();
        try {

			AuditorManager.addStatusAudit(Constants.STATUS_OK);
            result.setCodigoError("00");
            result.setDescripcionError("");
            CategoriaVehiculoTienda cat = getEJBManager().consultaCategoriaVehiculo(getDTSesion(usuario, contrasena), categoria);
            result.setCategoriaVehiculo(cat);

        } catch (BSEExceptionTienda ex2) {
        	AuditorManager.addStatusAudit(Constants.STATUS_ERROR);
			AuditorManager.addExceptionAudit(ex2);
			AuditorManager.LogExceptionAudit(ex2);
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
        	AuditorManager.addStatusAudit(Constants.STATUS_ERROR);
			AuditorManager.addExceptionAudit(ex1);
			AuditorManager.LogExceptionAudit(ex1);
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex1);
            ex1.printStackTrace();
        }
        return result;
    }


    /**
     *
     * @param usuario
     * @param contrasena
     * @param plan
     * @return
     */
    @Audit(excludedParams = "arg1")
    @WebMethod
    public PlanCoberturaTiendaResp consultaPlanCobertura( @WebParam(name = "usuario")    String usuario,
                                                          @WebParam(name = "contrasena") String contrasena,
                                                          @WebParam(name = "plan")       String plan ) {
        String logEncabezado = "SERVICE - SOA - consultaPlanCobertura - TIENDA";

        PlanCoberturaTiendaResp result = new PlanCoberturaTiendaResp();
        try {


			AuditorManager.addStatusAudit(Constants.STATUS_OK);
            result.setCodigoError("00");
            result.setDescripcionError("");
            PlanCoberturaTienda planObj = getComunEJBManager().consultaPlanCobertura(getDTSesion(usuario, contrasena), plan);
            result.setPlanCobertura(planObj);

        } catch (BSEExceptionTienda ex2) {
        	AuditorManager.addStatusAudit(Constants.STATUS_ERROR);
			AuditorManager.addExceptionAudit(ex2);
			AuditorManager.LogExceptionAudit(ex2);
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
        	AuditorManager.addStatusAudit(Constants.STATUS_ERROR);
			AuditorManager.addExceptionAudit(ex1);
			AuditorManager.LogExceptionAudit(ex1);
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex1);
            ex1.printStackTrace();
        }
        return result;
    }


    /**
     *
     * @param usuario
     * @param contrasena
     * @param ramo
     * @param producto
     * @return
     */
    @Audit(excludedParams = "arg1")
    @WebMethod
    public PlanesCoberturaTiendaResp consultaPlanesCobertura( @WebParam(name = "usuario")    String usuario,
                                                              @WebParam(name = "contrasena") String contrasena,
                                                              @WebParam(name = "ramo")       int    ramo,
                                                              @WebParam(name = "producto")   String producto ) {
        String logEncabezado = "SERVICE - SOA - consultaPlanesCobertura - TIENDA";

        PlanesCoberturaTiendaResp result = new PlanesCoberturaTiendaResp();
        try {

			AuditorManager.addStatusAudit(Constants.STATUS_OK);
            result.setCodigoError("00");
            result.setDescripcionError("");
            List<PlanCoberturaTienda> lista = getComunEJBManager().consultaPlanesCobertura(getDTSesion(usuario, contrasena), ramo, producto);
            result.setPlanesCobertura(lista);

        } catch (BSEExceptionTienda ex2) {
        	AuditorManager.addStatusAudit(Constants.STATUS_ERROR);
			AuditorManager.addExceptionAudit(ex2);
			AuditorManager.LogExceptionAudit(ex2);
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
        	AuditorManager.addStatusAudit(Constants.STATUS_ERROR);
			AuditorManager.addExceptionAudit(ex1);
			AuditorManager.LogExceptionAudit(ex1);
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex1);
            ex1.printStackTrace();
        }
        return result;
    }


    /**
     *
     * @param usuario
     * @param contrasena
     * @param plan
     * @return
     */
    @Audit(excludedParams = "arg1")
    @WebMethod
    public PlanPagoTiendaResp consultaPlanPago( @WebParam(name = "usuario")    String usuario,
                                                @WebParam(name = "contrasena") String contrasena,
                                                @WebParam(name = "plan")       int    plan ) {
        String logEncabezado = "SERVICE - SOA - consultaPlanPago - TIENDA";

        PlanPagoTiendaResp result = new PlanPagoTiendaResp();
        try {

			AuditorManager.addStatusAudit(Constants.STATUS_OK);
            result.setCodigoError("00");
            result.setDescripcionError("");
            PlanPagoTienda planObj = getComunEJBManager().consultaPlanPago(getDTSesion(usuario, contrasena), plan);
            result.setPlanPago(planObj);

        } catch (BSEExceptionTienda ex2) {
        	AuditorManager.addStatusAudit(Constants.STATUS_ERROR);
			AuditorManager.addExceptionAudit(ex2);
			AuditorManager.LogExceptionAudit(ex2);
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
        	AuditorManager.addStatusAudit(Constants.STATUS_ERROR);
			AuditorManager.addExceptionAudit(ex1);
			AuditorManager.LogExceptionAudit(ex1);
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex1);
            ex1.printStackTrace();
        }
        return result;
    }


    /**
     *
     * @param usuario
     * @param contrasena
     * @return
     */
    @Audit(excludedParams = "arg1")
    @WebMethod
    public PlanesPagoTiendaResp consultaPlanesPago( @WebParam(name = "usuario")    String usuario,
                                                    @WebParam(name = "contrasena") String contrasena ) {
        String logEncabezado = "SERVICE - SOA - consultaPlanesPago - TIENDA";

        PlanesPagoTiendaResp result = new PlanesPagoTiendaResp();
        try {

			AuditorManager.addStatusAudit(Constants.STATUS_OK);
            result.setCodigoError("00");
            result.setDescripcionError("");
            List<PlanPagoTienda> lista = getComunEJBManager().consultaPlanesPago(getDTSesion(usuario, contrasena));
            result.setPlanesPago(lista);

        } catch (BSEExceptionTienda ex2) {
        	AuditorManager.addStatusAudit(Constants.STATUS_ERROR);
			AuditorManager.addExceptionAudit(ex2);
			AuditorManager.LogExceptionAudit(ex2);
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
        	AuditorManager.addStatusAudit(Constants.STATUS_ERROR);
			AuditorManager.addExceptionAudit(ex1);
			AuditorManager.LogExceptionAudit(ex1);
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex1);
            ex1.printStackTrace();
        }
        return result;
    }


    /**
     *
     * @param codigo
     * @param descripcion
     * @return
     */
    private String logError(String codigo, String descripcion) {
        return " - ERROR: CODIGO=[" + codigo + "] DESCRIPCION=[" + descripcion + "]";
    }

}
