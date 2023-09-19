package com.bse.servicios.ws.administracion;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bse.negocio.comun.BSEExceptionTienda;
import com.bse.negocio.comun.CodigosErrorTienda;
import com.bse.negocio.comun.CodigosTienda;
import com.bse.servicios.seguridad.IBSEAdmTiendaEJBLocal;
import com.bse.servicios.seguridad.dt.DTSesionTienda;

@WebService(serviceName = "Administracion")
public class AdministracionTienda{

    private static final Logger logger = LogManager.getLogger(AdministracionTienda.class);


    @Resource
    private WebServiceContext wsContext;

    private IBSEAdmTiendaEJBLocal getEJBManager() throws NamingException {
        InitialContext ctx = new InitialContext();
     //   IBSEAdmTiendaEJBLocal bean = (IBSEAdmTiendaEJBLocal) ctx.lookup("BseTiendaEar/BSEAdmTiendaEJB/local");
        IBSEAdmTiendaEJBLocal bean = (IBSEAdmTiendaEJBLocal) ctx.lookup("java:global/BseTiendaEar/BseTiendaEjb/BSEAdmTiendaEJB!com.bse.servicios.seguridad.IBSEAdmTiendaEJBLocal");

        return bean;
    }

    private DTSesionTienda getDTSesion(String usuario, String contrasena) {
        MessageContext mc = wsContext.getMessageContext();
        HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
        return new DTSesionTienda(usuario, contrasena, req.getRemoteAddr());
    }

    @WebMethod
    public CambioContrasenaTienda cambiarContrasena(@WebParam(name = "usuario") String usuario,
                                              @WebParam(name = "contrasena") String contrasena,
                                              @WebParam(name = "nuevaContrasena") String nuevaContrasena) {
        CambioContrasenaTienda result = new CambioContrasenaTienda();
        try {
            getEJBManager().cambiarContrasena(getDTSesion(usuario, contrasena), nuevaContrasena);
            result.setResultado(CodigosTienda.servicio_ok);
        } catch (BSEExceptionTienda ex) {
            //Logger.getLogger(AdministracionTienda.class.getName()).log(Level.ERROR, null, ex);
            logger.log(Level.ERROR, "", ex);
            result.setResultado(CodigosTienda.servicio_error);
            result.setCodigoError(ex.getCodigoError());
            result.setDescripcionError(ex.getDescripcion());
        } catch (Exception ex) {
            //Logger.getLogger(AdministracionTienda.class.getName()).log(Level.ERROR, null, ex);
            logger.log(Level.ERROR, "", ex);
            result.setResultado(CodigosTienda.servicio_error);
            result.setCodigoError(CodigosErrorTienda.excepcion_generica);
            result.setDescripcionError(BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica));
        }
        return result;
    }
}
