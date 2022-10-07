package com.bse.servicios.ws.opersonal;

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

import com.bse.accesodatos.comun.ItemCodiguera;
import com.bse.accesodatos.eoperson.CotizacionOPersonalTienda;
import com.bse.accesodatos.eoperson.PolizaOPersonalTienda;
import com.bse.accesodatos.esoa.PlanCoberturaTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import com.bse.negocio.comun.CodigosErrorTienda;
import com.bse.servicios.comun.IEmisionComunTiendaEJBLocal;
import com.bse.servicios.eoperson.IEmisionOPersonalTiendaEJBLocal;
import com.bse.servicios.seguridad.dt.DTSesionTienda;
import com.bse.servicios.ws.comun.ClienteDeudaTiendaResp;


@WebService(serviceName = "EmisionOPersonal")
public class EmisionOPersonalTienda {

    private static final Logger logger = Logger.getLogger(EmisionOPersonalTienda.class);


    @Resource
    private WebServiceContext wsContext;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     * @return
     * @throws NamingException
     */
    private IEmisionOPersonalTiendaEJBLocal getEJBManager() throws NamingException {
        InitialContext ctx = new InitialContext();
        IEmisionOPersonalTiendaEJBLocal bean = (IEmisionOPersonalTiendaEJBLocal) 
                                                            ctx.lookup("BseTiendaEar/EmisionOPersonalTiendaEJB/local");
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
     * Genera cotizacion para el objeto personal indicado
     * @param usuario - OBLIGATORIO
     * @param contrasena - OBLIGATORIO
     * @param planCobertura - OBLIGATORIO
     * @param tipoObjeto - OBLIGATORIO
     * @param valorObjeto - OBLIGATORIO
     * @param movilidad - OBLIGATORIO
     * @return
     */
    @WebMethod
    public CotizacionOPersonalTiendaResp cotizarOPersonalAnonimo( @WebParam(name = "usuario")       String usuario,
                                                                  @WebParam(name = "contrasena")    String contrasena,
                                                                  @WebParam(name = "planCobertura") String planCobertura,
                                                                  @WebParam(name = "tipoObjeto")    String tipoObjeto,
                                                                  @WebParam(name = "valorObjeto")   Double valorObjeto,
                                                                  @WebParam(name = "movilidad")     String movilidad ) {
        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        String logEncabezado = "SERVICE - OPERSONAL - cotizarOPersonalAnonimo - TIENDA";
        logger.info(logEncabezado + " - INICIO");

        CotizacionOPersonalTiendaResp result = null;
        try {
            CotizacionOPersonalTienda cotizacionOPersonal = getEJBManager().cotizarOPersonalAnonimo(
                                                                                    getDTSesion(usuario, contrasena),
                                                                                    planCobertura,
                                                                                    tipoObjeto,
                                                                                    valorObjeto,
                                                                                    movilidad );
            result = new CotizacionOPersonalTiendaResp(cotizacionOPersonal, "00", "");

        } catch (BSEExceptionTienda ex2) {
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result = new CotizacionOPersonalTiendaResp(codError, descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result = new CotizacionOPersonalTiendaResp(codError, descError);
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
     * Genera la póliza para el objeto personal indicado y de acuerdo a la cotización previamente realizada
     * @param usuario - OBLIGATORIO
     * @param contrasena - OBLIGATORIO
     * @param tipoDocumento - OBLIGATORIO
     * @param documento - OBLIGATORIO
     * @param marca - OBLIGATORIO
     * @param serie - OBLIGATORIO
     * @param modelo - OBLIGATORIO
     * @param nroCotizacion - OBLIGATORIO
     * @param planPago - OBLIGATORIO
     * @param fechaFactura - OBLIGATORIO
     * @param consumoFinal - OBLIGATORIO
     * @return
     */
    @WebMethod
    public EmisionOPersonalTiendaResp emitirOPersonal( @WebParam(name = "usuario")       String usuario,
                                                       @WebParam(name = "contrasena")    String contrasena,
                                                       @WebParam(name = "tipoDocumento") String tipoDocumento,
                                                       @WebParam(name = "documento")     String documento,
                                                       @WebParam(name = "marca")         String marca,
                                                       @WebParam(name = "serie")         String serie,
                                                       @WebParam(name = "modelo")        String modelo,
                                                       @WebParam(name = "nroCotizacion") long   nroCotizacion,
                                                       @WebParam(name = "planPago")      String planPago,
                                                       @WebParam(name = "fechaFactura")  Date   fechaFactura,
                                                       @WebParam(name = "consumoFinal")  String consumoFinal ) {
        long tiempoInicioMetodo = System.currentTimeMillis();
        long tiempoTotalMetodo  = 0;
        String logEncabezado = "SERVICE - OPERSONAL - emitirOPersonal - TIENDA";
        logger.info(logEncabezado + " - INICIO");

        EmisionOPersonalTiendaResp result = null;
        try {
            PolizaOPersonalTienda polizaOPersonal = getEJBManager().emitirOPersonal( getDTSesion(usuario, contrasena),
                                                                                     tipoDocumento,
                                                                                     documento,
                                                                                     marca,
                                                                                     serie,
                                                                                     modelo,
                                                                                     nroCotizacion,
                                                                                     planPago,
                                                                                     fechaFactura,
                                                                                     consumoFinal );
            result = new EmisionOPersonalTiendaResp(polizaOPersonal, "00", "");

        } catch (BSEExceptionTienda ex2) {
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result = new EmisionOPersonalTiendaResp(codError, descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result = new EmisionOPersonalTiendaResp(codError, descError);
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
        String logEncabezado = "SERVICE - OPERSONAL - controlarClienteConDeuda - TIENDA";
        logger.info(logEncabezado + " - INICIO");

        ClienteDeudaTiendaResp result = null;
        try {
            String tieneDeuda = getComunEJBManager().clienteConDeuda( getDTSesion(usuario, contrasena),
                                                                      tipoDocumento,
                                                                      documento,
                                                                      nroCotizacion,
                                                                      nroCertificado );
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
     *
     * @param usuario
     * @param contrasena
     * @return
     */
    @WebMethod
    public PlanesCoberturaTiendaResp consultaPlanesCobertura( @WebParam(name = "usuario")    String usuario,
                                                              @WebParam(name = "contrasena") String contrasena ) {
        String logEncabezado = "SERVICE - OPERSONAL - consultaPlanesCobertura - TIENDA";

        PlanesCoberturaTiendaResp result = null;
        try {
            List<PlanCoberturaTienda> lista = getEJBManager().consultaPlanesCobertura(getDTSesion(usuario, contrasena));
            result = new PlanesCoberturaTiendaResp(lista, "00", "");

        } catch (BSEExceptionTienda ex2) {
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result = new PlanesCoberturaTiendaResp(codError, descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result = new PlanesCoberturaTiendaResp(codError, descError);
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
     * @param usuario
     * @param contrasena
     * @return
     */
    @WebMethod
    public TiposObjetosTiendaResp consultaTiposObjetos( @WebParam(name = "usuario")    String usuario,
                                                        @WebParam(name = "contrasena") String contrasena ) {
        String logEncabezado = "SERVICE - OPERSONAL - consultaTiposObjetos - TIENDA";

        TiposObjetosTiendaResp result = null;
        try {
            List<ItemCodiguera> lista = getEJBManager().consultaTiposObjetos(getDTSesion(usuario, contrasena));
            result = new TiposObjetosTiendaResp(lista, "00", "");

        } catch (BSEExceptionTienda ex2) {
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result = new TiposObjetosTiendaResp(codError, descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result = new TiposObjetosTiendaResp(codError, descError);
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
     * @param usuario
     * @param contrasena
     * @return
     */
    @WebMethod
    public TiposMovilidadTiendaResp consultaTiposMovilidad( @WebParam(name = "usuario")    String usuario,
                                                            @WebParam(name = "contrasena") String contrasena ) {
        String logEncabezado = "SERVICE - OPERSONAL - consultaTiposMovilidad - TIENDA";

        TiposMovilidadTiendaResp result = null;
        try {
            List<ItemCodiguera> lista = getEJBManager().consultaTiposMovilidad(getDTSesion(usuario, contrasena));
            result = new TiposMovilidadTiendaResp(lista, "00", "");

        } catch (BSEExceptionTienda ex2) {
            String codError  = String.valueOf(ex2.getCodigoError());
            String descError = ex2.getDescripcion();
            result = new TiposMovilidadTiendaResp(codError, descError);
            logger.error(logEncabezado + logError(codError, descError), ex2);

        } catch (Exception ex1) {
            String codError  = String.valueOf(CodigosErrorTienda.excepcion_generica);
            String descError = BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica);
            result = new TiposMovilidadTiendaResp(codError, descError);
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
