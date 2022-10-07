package com.bse.servicios.ws.parametros;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.bse.negocio.comun.BSEExceptionTienda;
import com.bse.servicios.parametros.*;
import com.bse.servicios.seguridad.dt.DTSesionTienda;

@WebService(serviceName = "Parametros")
public class ParametrosTienda {

@Resource
private WebServiceContext wsContext;

private IParametrosTiendaEJBLocal getEJBManager() throws NamingException {
    InitialContext ctx = new InitialContext();
    IParametrosTiendaEJBLocal bean = (IParametrosTiendaEJBLocal) ctx.lookup("BseTiendaEar/ParametrosTiendaEJB/local");
    return bean;
}

private DTSesionTienda getDTSesion(String usuario, String contrasena) {
    MessageContext mc = wsContext.getMessageContext();
    HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
    return new DTSesionTienda(usuario, contrasena, req.getRemoteAddr());
}

@WebMethod
public String actualizarParametro(@WebParam(name = "usuario") String usuario, 
                                  @WebParam(name = "contrasena") String contrasena,
                                  @WebParam(name = "aplicacion") String aplicacion,
                                  @WebParam(name = "clave") String clave,
                                  @WebParam(name = "subclave") String subclave,
                                  @WebParam(name = "valor") String valor) {
    try {
    	return getEJBManager().actualizarParametro(getDTSesion(usuario, contrasena), aplicacion, clave, subclave, valor);
    } catch (BSEExceptionTienda ex2) {
		Logger.getLogger(ParametrosTienda.class.getName()).log(Level.ERROR, null, ex2);
    } catch (Exception ex1) {
		Logger.getLogger(ParametrosTienda.class.getName()).log(Level.ERROR, null, ex1);
		ex1.printStackTrace();
    }
    return "ERROR";
}

@WebMethod
public String agregarParametro(@WebParam(name = "usuario") String usuario, 
                               @WebParam(name = "contrasena") String contrasena,
                               @WebParam(name = "aplicacion") String aplicacion,
                               @WebParam(name = "clave") String clave,
                               @WebParam(name = "subclave") String subclave,
                               @WebParam(name = "valor") String valor) {
    try {
    	return getEJBManager().agregarParametro(getDTSesion(usuario, contrasena), aplicacion, clave, subclave, valor);
    } catch (BSEExceptionTienda ex2) {
		Logger.getLogger(ParametrosTienda.class.getName()).log(Level.ERROR, null, ex2);
    } catch (Exception ex1) {
		Logger.getLogger(ParametrosTienda.class.getName()).log(Level.ERROR, null, ex1);
		ex1.printStackTrace();
    }
    return "ERROR";
}

@WebMethod
public String agregarRol(@WebParam(name = "usuario") String usuario, 
                         @WebParam(name = "contrasena") String contrasena,
                         @WebParam(name = "rol") String rol) {
    try {
    	return getEJBManager().agregarRol(getDTSesion(usuario, contrasena), rol);
    } catch (BSEExceptionTienda ex2) {
		Logger.getLogger(ParametrosTienda.class.getName()).log(Level.ERROR, null, ex2);
    } catch (Exception ex1) {
		Logger.getLogger(ParametrosTienda.class.getName()).log(Level.ERROR, null, ex1);
		ex1.printStackTrace();
    }
    return "ERROR";
}

@WebMethod
public String agregarOperacion(@WebParam(name = "usuario") String usuario, 
                               @WebParam(name = "contrasena") String contrasena,
                               @WebParam(name = "interfaz") String interfaz, 
                               @WebParam(name = "metodo") String metodo, 
                               @WebParam(name = "activo") short activo) {
    try {
    	return getEJBManager().agregarOperacion(getDTSesion(usuario, contrasena), interfaz, metodo, activo);
    } catch (BSEExceptionTienda ex2) {
		Logger.getLogger(ParametrosTienda.class.getName()).log(Level.ERROR, null, ex2);
    } catch (Exception ex1) {
		Logger.getLogger(ParametrosTienda.class.getName()).log(Level.ERROR, null, ex1);
		ex1.printStackTrace();
    }
    return "ERROR";
}

@WebMethod
public String agregarRolOperacion(@WebParam(name = "usuario") String usuario, 
                                  @WebParam(name = "contrasena") String contrasena,
                                  @WebParam(name = "rol") String rol, 
                                  @WebParam(name = "interfaz") String interfaz, 
                                  @WebParam(name = "metodo") String metodo) {
    try {
    	return getEJBManager().agregarRolOperacion(getDTSesion(usuario, contrasena), rol, interfaz, metodo);
    } catch (BSEExceptionTienda ex2) {
		Logger.getLogger(ParametrosTienda.class.getName()).log(Level.ERROR, null, ex2);
    } catch (Exception ex1) {
		Logger.getLogger(ParametrosTienda.class.getName()).log(Level.ERROR, null, ex1);
		ex1.printStackTrace();
    }
    return "ERROR";
}

@WebMethod
public String agregarUsuarioRol(@WebParam(name = "usuario") String usuario, 
                                @WebParam(name = "contrasena") String contrasena,
                                @WebParam(name = "rol") String rol, 
                                @WebParam(name = "usuarioDelRol") String usuarioDelRol) {
    try {
    	return getEJBManager().agregarUsuarioRol(getDTSesion(usuario, contrasena), rol, usuarioDelRol);
    } catch (BSEExceptionTienda ex2) {
		Logger.getLogger(ParametrosTienda.class.getName()).log(Level.ERROR, null, ex2);
    } catch (Exception ex1) {
		Logger.getLogger(ParametrosTienda.class.getName()).log(Level.ERROR, null, ex1);
		ex1.printStackTrace();
    }
    return "ERROR";
}

@WebMethod
public String clearCache(@WebParam(name = "usuario") String usuario, 
                         @WebParam(name = "contrasena") String contrasena) {
    try {
    	return getEJBManager().clearCache(getDTSesion(usuario, contrasena));
    } catch (BSEExceptionTienda ex2) {
		Logger.getLogger(ParametrosTienda.class.getName()).log(Level.ERROR, null, ex2);
    } catch (Exception ex1) {
		Logger.getLogger(ParametrosTienda.class.getName()).log(Level.ERROR, null, ex1);
		ex1.printStackTrace();
    }
    return "ERROR";
}

}
