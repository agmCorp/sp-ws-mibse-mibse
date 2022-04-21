package com.bse.servicios.ws.polizas;

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

import com.bse.negocio.comun.BSEException;
import com.bse.negocio.comun.CodigosError;
import com.bse.servicios.esoa.IEmisionSoaEJBLocal;
import com.bse.servicios.seguridad.dt.DTSesion;


@WebService(serviceName = "Polizas")
public class Polizas {

@Resource
private WebServiceContext wsContext;

private IEmisionSoaEJBLocal getEJBManager() throws NamingException {
    InitialContext ctx = new InitialContext();
    IEmisionSoaEJBLocal bean = (IEmisionSoaEJBLocal) ctx.lookup("BseTiendaEar/EmisionSoaEJB/local");
    return bean;
}

private DTSesion getDTSesion(String usuario, String contrasena) {
    MessageContext mc = wsContext.getMessageContext();
    HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
    return new DTSesion(usuario, contrasena, req.getRemoteAddr());
}

@WebMethod
public AlertaPagoRedesResp alertarPagoRedes(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena, 
        @WebParam(name = "sucursal") int sucursal, 
        @WebParam(name = "ramo") int ramo, 
        @WebParam(name = "producto") String producto, 
        @WebParam(name = "nroCotizacion") long nroCotizacion){
	AlertaPagoRedesResp result = new AlertaPagoRedesResp();
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	getEJBManager().alertarPagoRedes(getDTSesion(usuario, contrasena), sucursal, ramo, producto, nroCotizacion);
//        result.setPolizaSoa(polizaSoa);
    } catch (BSEException ex2) {
    	ex2.printStackTrace();
    	
		Logger.getLogger(Polizas.class.getName()).log(Level.ERROR, null, ex2);
		result.setCodigoError(String.valueOf(ex2.getCodigoError()));
		result.setDescripcionError(ex2.getDescripcion());
    } catch (Exception ex1) {
    	ex1.printStackTrace();
    	
		Logger.getLogger(Polizas.class.getName()).log(Level.ERROR, null, ex1);
		ex1.printStackTrace();
		result.setCodigoError(String.valueOf(CodigosError.excepcion_generica));
		result.setDescripcionError(BSEException.getDescripcionError(CodigosError.excepcion_generica));
    }
    return result;
}



}
