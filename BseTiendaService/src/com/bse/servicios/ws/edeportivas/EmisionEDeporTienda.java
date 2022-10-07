package com.bse.servicios.ws.edeportivas;

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

import com.bse.accesodatos.edepor.CoberturaRcTienda;
import com.bse.accesodatos.edepor.CotizacionEDeporTienda;
import com.bse.accesodatos.edepor.PolizaEDeporTienda;
import com.bse.accesodatos.edepor.TipoBuqueTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import com.bse.negocio.comun.CodigosErrorTienda;
import com.bse.servicios.edepor.IEmisionEDeporTiendaEJBLocal;
import com.bse.servicios.seguridad.dt.DTSesionTienda;

@WebService(serviceName = "EmisionEDepor")
public class EmisionEDeporTienda {

    private static final Logger logger = Logger.getLogger(EmisionEDeporTienda.class);

    @Resource
    private WebServiceContext wsContext;

    /**
     *
     * @return
     * @throws NamingException
     */
    private IEmisionEDeporTiendaEJBLocal getEJBManager() throws NamingException {
        InitialContext ctx = new InitialContext();
        IEmisionEDeporTiendaEJBLocal bean = (IEmisionEDeporTiendaEJBLocal) 
                                                            ctx.lookup("BseTiendaEar/EmisionEDeporTiendaEJB/local");
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
     * @param tipoBuque
     * @param fechaDesde
     * @param fechaHasta
     * @param capital
     * @param planPago
     * @return
     */
    @WebMethod
    public CotizacionEDeporTiendaResp cotizarEDeporAnonimo( @WebParam(name = "usuario")       String usuario,
                                                            @WebParam(name = "contrasena")    String contrasena,
                                                            @WebParam(name = "planCobertura") String planCobertura,
                                                            @WebParam(name = "tipoBuque")     String tipoBuque,
                                                            @WebParam(name = "fechaDesde")    Date   fechaDesde,
                                                            @WebParam(name = "fechaHasta")    Date   fechaHasta,
                                                            @WebParam(name = "capital")       double capital,
                                                            @WebParam(name = "planPago")      int    planPago ) {
        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        String logEncabezado = "SERVICE - EDEPOR - cotizarEDeporAnonimo - TIENDA";
        logger.info(logEncabezado + " - INICIO");

        CotizacionEDeporTiendaResp result = new CotizacionEDeporTiendaResp();
        try {
            result.setCodigoError("00");
            result.setDescripcionError("");
            CotizacionEDeporTienda cotizacionEDepor = getEJBManager().cotizarEDeporAnonimo(
                                                                                    getDTSesion(usuario, contrasena),
                                                                                    planCobertura,
                                                                                    tipoBuque,
                                                                                    fechaDesde,
                                                                                    fechaHasta,
                                                                                    capital,
                                                                                    planPago );
            result.setCotizacionEDepor(cotizacionEDepor);

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
     * @param tipoDocumento
     * @param documento
     * @param matriculaBuque
     * @param nombreBuque
     * @param nroCotizacion
     * @param consumoFinal
     * @return
     */
    @WebMethod
    public EmisionEDeporTiendaResp emitirEDepor( @WebParam(name = "usuario")        String usuario,
                                                 @WebParam(name = "contrasena")     String contrasena,
                                                 @WebParam(name = "tipoDocumento")  String tipoDocumento,
                                                 @WebParam(name = "documento")      String documento,
                                                 @WebParam(name = "matriculaBuque") String matriculaBuque,
                                                 @WebParam(name = "nombreBuque")    String nombreBuque,
                                                 @WebParam(name = "nroCotizacion")  long   nroCotizacion,
                                                 @WebParam(name = "consumoFinal")   String consumoFinal ) {
        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        String logEncabezado = "SERVICE - EDEPOR - emitirEDepor - TIENDA";
        logger.info(logEncabezado + " - INICIO");

        EmisionEDeporTiendaResp result = new EmisionEDeporTiendaResp();
        try {
            result.setCodigoError("00");
            result.setDescripcionError("");
            PolizaEDeporTienda polizaEDepor = getEJBManager().emitirEDepor( getDTSesion(usuario, contrasena),
                                                                            tipoDocumento,
                                                                            documento,
                                                                            matriculaBuque,
                                                                            nombreBuque,
                                                                            nroCotizacion,
                                                                            consumoFinal );
            result.setPolizaEDepor(polizaEDepor);

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
    public TiposBuquesTiendaResp consultaTiposBuques( @WebParam(name = "usuario")    String usuario,
                                                      @WebParam(name = "contrasena") String contrasena ) {
        String logEncabezado = "SERVICE - EDEPOR - consultaTiposBuques - TIENDA";

        TiposBuquesTiendaResp result = new TiposBuquesTiendaResp();
        try {
            result.setCodigoError("00");
            result.setDescripcionError("");
            List<TipoBuqueTienda> tiposBuques = getEJBManager().consultaTiposBuques(getDTSesion(usuario, contrasena));
            result.setTiposBuques(tiposBuques);

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
    public CoberturasRcTiendaResp consultaCoberturasRc( @WebParam(name = "usuario")    String usuario,
                                                        @WebParam(name = "contrasena") String contrasena ) {
        String logEncabezado = "SERVICE - EDEPOR - consultaCoberturasRc - TIENDA";

        CoberturasRcTiendaResp result = new CoberturasRcTiendaResp();
        try {
            result.setCodigoError("00");
            result.setDescripcionError("");
            List<CoberturaRcTienda> cobs = getEJBManager().consultaCoberturasRc(getDTSesion(usuario, contrasena));
            result.setCoberturasRc(cobs);

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
