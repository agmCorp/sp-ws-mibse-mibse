package com.bse.servicios.ws.viajeros;

import java.util.ArrayList;
import java.util.Date;
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

import com.bse.accesodatos.esoa.PlanCoberturaTienda;
import com.bse.accesodatos.esoa.PlanPagoTienda;
import com.bse.accesodatos.eviajeros.CoberturaMuerteTienda;
import com.bse.accesodatos.eviajeros.CoberturaPrexistentesTienda;
import com.bse.accesodatos.eviajeros.CotizacionViajerosTienda;
import com.bse.accesodatos.eviajeros.PolizaViajerosTienda;
import com.bse.accesodatos.eviajeros.ViajeroTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import com.bse.negocio.comun.CodigosErrorTienda;
import com.bse.servicios.comun.IEmisionComunTiendaEJBLocal;
import com.bse.servicios.eviajeros.IEmisionViajerosTiendaEJBLocal;
import com.bse.servicios.seguridad.dt.DTSesionTienda;
import com.bse.servicios.ws.soa.PlanCoberturaTiendaResp;
import com.bse.servicios.ws.soa.PlanPagoTiendaResp;
import com.bse.servicios.ws.soa.PlanesCoberturaTiendaResp;
import com.bse.servicios.ws.soa.PlanesPagoTiendaResp;

@WebService(serviceName = "EmisionViajeros")
public class EmisionViajerosTienda {

    private static final Logger logger = Logger.getLogger(EmisionViajerosTienda.class);


    @Resource
    private WebServiceContext wsContext;

    /**
     *
     * @return
     * @throws NamingException
     */
    private IEmisionViajerosTiendaEJBLocal getEJBManager() throws NamingException {
        InitialContext ctx = new InitialContext();
        IEmisionViajerosTiendaEJBLocal bean = (IEmisionViajerosTiendaEJBLocal)
                                                            ctx.lookup("BseTiendaEar/EmisionViajerosTiendaEJB/local");
        return bean;
    }



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
     * @param planPago
     * @param fechaDesde
     * @param fechaHasta
     * @param listaPersonas
     * @param extension
     * @param fechaSalidaPais
     * @return
     */
    @WebMethod
    public CotizacionViajerosTiendaResp cotizarViajerosAnonimo( @WebParam(name = "usuario")         String usuario,
                                                                @WebParam(name = "contrasena")      String contrasena,
                                                                @WebParam(name = "planCobertura")   String planCobertura,
                                                                @WebParam(name = "planPago")        int    planPago,
                                                                @WebParam(name = "fechaDesde")      Date   fechaDesde,
                                                                @WebParam(name = "fechaHasta")      Date   fechaHasta,
                                                                @WebParam(name = "listaPersonas")   ArrayList<ViajeroTienda> listaPersonas,
                                                                @WebParam(name = "extension")       String extension,
                                                                @WebParam(name = "fechaSalidaPais") Date   fechaSalidaPais ) {
        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        String logEncabezado = "SERVICE - VIAJEROS - cotizarViajerosAnonimo - TIENDA";
        logger.info(logEncabezado + " - INICIO");

        CotizacionViajerosTiendaResp result = new CotizacionViajerosTiendaResp();
        try {
            result.setCodigoError("00");
            result.setDescripcionError("");

            CotizacionViajerosTienda cotizacionViajeros = getEJBManager().cotizarViajerosAnonimo(
                                                                                    getDTSesion(usuario, contrasena),
                                                                                    planCobertura,
                                                                                    planPago,
                                                                                    fechaDesde,
                                                                                    fechaHasta,
                                                                                    listaPersonas,
                                                                                    extension,
                                                                                    fechaSalidaPais);
            result.setCotizacionViajeros(cotizacionViajeros);

        } catch (BSEExceptionTienda ex2) {
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
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
     * @param tipoDocumentoContratante
     * @param documentoContratante
     * @param listaPersonas
     * @param nroCotizacion
     * @param consumoFinal
     * @param extension
     * @param fechaSalidaPais
     * @return
     */
    @WebMethod
    public EmisionViajerosTiendaResp emitirViajeros( @WebParam(name = "usuario")                  String usuario,
                                                     @WebParam(name = "contrasena")               String contrasena,
                                                     @WebParam(name = "tipoDocumentoContratante") String tipoDocumentoContratante,
                                                     @WebParam(name = "documentoContratante")     String documentoContratante,
                                                     @WebParam(name = "listaPersonas")            ArrayList<ViajeroTienda> listaPersonas,
                                                     @WebParam(name = "nroCotizacion")            long   nroCotizacion,
                                                     @WebParam(name = "consumoFinal")             String consumoFinal,
                                                     @WebParam(name = "extension")                String extension,
                                                     @WebParam(name = "fechaSalidaPais")          Date   fechaSalidaPais) {
        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        String logEncabezado = "SERVICE - VIAJEROS - emitirViajeros - TIENDA";
        logger.info(logEncabezado + " - INICIO");

        EmisionViajerosTiendaResp result = new EmisionViajerosTiendaResp();

        try {
            PolizaViajerosTienda poliza = getEJBManager().emitirViajeros( getDTSesion(usuario, contrasena),
                                                                          tipoDocumentoContratante,
                                                                          documentoContratante,
                                                                          listaPersonas,
                                                                          nroCotizacion,
                                                                          consumoFinal,
                                                                          extension,
                                                                          fechaSalidaPais );
            result.setPolizaViajeros(poliza);
            result.setCodigoError("00");
            result.setDescripcionError("");

        } catch (BSEExceptionTienda ex2) {
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
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
    @WebMethod
    public CoberturasPrexistentesTiendaResp consultaCoberturasPrexistentes( @WebParam(name = "usuario")    String usuario,
                                                                            @WebParam(name = "contrasena") String contrasena) {
        String logEncabezado = "SERVICE - VIAJEROS - consultaCoberturasPrexistentes - TIENDA";

        CoberturasPrexistentesTiendaResp result = new CoberturasPrexistentesTiendaResp();
        try {
            result.setCodigoError("00");
            result.setDescripcionError("");
            List<CoberturaPrexistentesTienda> lista = getEJBManager().consultaCoberturasPrexistentes(getDTSesion(usuario, contrasena));
            result.setCoberturasPrexistentes(lista);

        } catch (BSEExceptionTienda ex2) {
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
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
    @WebMethod
    public CoberturasMuerteTiendaResp consultaCoberturasMuerte( @WebParam(name = "usuario")    String usuario,
                                                                @WebParam(name = "contrasena") String contrasena) {
        String logEncabezado = "SERVICE - VIAJEROS - consultaCoberturasMuerte - TIENDA";

        CoberturasMuerteTiendaResp result = new CoberturasMuerteTiendaResp();
        try {
            result.setCodigoError("00");
            result.setDescripcionError("");
            List<CoberturaMuerteTienda> lista = getEJBManager().consultaCoberturasMuerte(getDTSesion(usuario, contrasena));
            result.setCoberturasMuerte(lista);

        } catch (BSEExceptionTienda ex2) {
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
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
    @WebMethod
    public PlanesCoberturaTiendaResp consultaPlanesCobertura( @WebParam(name = "usuario")    String usuario,
                                                              @WebParam(name = "contrasena") String contrasena,
                                                              @WebParam(name = "ramo")       int    ramo,
                                                              @WebParam(name = "producto")   String producto) {
        String logEncabezado = "SERVICE - VIAJEROS - consultaPlanesCobertura - TIENDA";

        PlanesCoberturaTiendaResp result = new PlanesCoberturaTiendaResp();
        try {
            result.setCodigoError("00");
            result.setDescripcionError("");
            List<PlanCoberturaTienda> lista = getComunEJBManager().consultaPlanesCobertura(getDTSesion(usuario, contrasena), ramo, producto);
            result.setPlanesCobertura(lista);

        } catch (BSEExceptionTienda ex2) {
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
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
    @WebMethod
    public PlanCoberturaTiendaResp consultaPlanCobertura( @WebParam(name = "usuario")    String usuario,
                                                          @WebParam(name = "contrasena") String contrasena,
                                                          @WebParam(name = "plan")       String plan ) {
        String logEncabezado = "SERVICE - VIAJEROS - consultaPlanCobertura - TIENDA";

        PlanCoberturaTiendaResp result = new PlanCoberturaTiendaResp();
        try {
            result.setCodigoError("00");
            result.setDescripcionError("");
            PlanCoberturaTienda planObj = getComunEJBManager().consultaPlanCobertura( getDTSesion(usuario, contrasena),
                                                                                      plan );
            result.setPlanCobertura(planObj);

        } catch (BSEExceptionTienda ex2) {
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
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
    @WebMethod
    public PlanesPagoTiendaResp consultaPlanesPago( @WebParam(name = "usuario")    String usuario,
                                                    @WebParam(name = "contrasena") String contrasena ) {
        String logEncabezado = "SERVICE - VIAJEROS - consultaPlanesPago - TIENDA";

        PlanesPagoTiendaResp result = new PlanesPagoTiendaResp();
        try {
            result.setCodigoError("00");
            result.setDescripcionError("");
            List<PlanPagoTienda> lista = getComunEJBManager().consultaPlanesPago(getDTSesion(usuario, contrasena));
            result.setPlanesPago(lista);

        } catch (BSEExceptionTienda ex2) {
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
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
    @WebMethod
    public PlanPagoTiendaResp consultaPlanPago( @WebParam(name = "usuario")    String usuario,
                                                @WebParam(name = "contrasena") String contrasena,
                                                @WebParam(name = "plan")       int    plan ) {
        String logEncabezado = "SERVICE - VIAJEROS - consultaPlanPago - TIENDA";

        PlanPagoTiendaResp result = new PlanPagoTiendaResp();
        try {
            result.setCodigoError("00");
            result.setDescripcionError("");
            PlanPagoTienda planObj = getComunEJBManager().consultaPlanPago(getDTSesion(usuario, contrasena), plan);
            result.setPlanPago(planObj);

        } catch (BSEExceptionTienda ex2) {
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result.setCodigoError(codError);
            result.setDescripcionError(descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
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
