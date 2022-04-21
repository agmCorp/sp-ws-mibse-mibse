package com.bse.servicios.ws.administracion;

import com.bse.negocio.comun.BSEException;
import com.bse.servicios.seguridad.IBSEonlinewsAdmEJBLocal;
import com.bse.servicios.seguridad.dt.DTSesion;
import com.bse.negocio.comun.Codigos;
import com.bse.negocio.comun.CodigosError;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

@WebService(serviceName = "Administracion")
public class Administracion{
    
    @Resource
    private WebServiceContext wsContext;

    private IBSEonlinewsAdmEJBLocal getEJBManager() throws NamingException {
        InitialContext ctx = new InitialContext();
        IBSEonlinewsAdmEJBLocal bean = (IBSEonlinewsAdmEJBLocal) ctx.lookup("BseTiendaEar/BSEonlinewsAdmEJB/local");        
        return bean;
    }

    private DTSesion getDTSesion(String usuario, String contrasena) {
        MessageContext mc = wsContext.getMessageContext();
        HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
        return new DTSesion(usuario, contrasena, req.getRemoteAddr());
    }

    @WebMethod
    public CambioContrasena cambiarContrasena(@WebParam(name = "usuario") String usuario, 
                                              @WebParam(name = "contrasena") String contrasena, 
                                              @WebParam(name = "nuevaContrasena") String nuevaContrasena) {
        CambioContrasena result = new CambioContrasena();
        try {
            getEJBManager().cambiarContrasena(getDTSesion(usuario, contrasena), nuevaContrasena);
            result.setResultado(Codigos.servicio_ok);            
        } catch (BSEException ex) {
            Logger.getLogger(Administracion.class.getName()).log(Level.ERROR, null, ex);
            result.setResultado(Codigos.servicio_error);
            result.setCodigoError(ex.getCodigoError());
            result.setDescripcionError(ex.getDescripcion());
        } catch (Exception ex) {
            Logger.getLogger(Administracion.class.getName()).log(Level.ERROR, null, ex);
            result.setResultado(Codigos.servicio_error);
            result.setCodigoError(CodigosError.excepcion_generica);
            result.setDescripcionError(BSEException.getDescripcionError(CodigosError.excepcion_generica));
        }
        return result;
    }    
}
