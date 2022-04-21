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

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.bse.accesodatos.edepor.CoberturaRc;
import com.bse.accesodatos.edepor.CotizacionEDepor;
import com.bse.accesodatos.edepor.PolizaEDepor;
import com.bse.accesodatos.edepor.TipoBuque;
import com.bse.negocio.comun.BSEException;
import com.bse.negocio.comun.CodigosError;
import com.bse.servicios.edepor.IEmisionEDeporEJBLocal;
import com.bse.servicios.seguridad.dt.DTSesion;

@WebService(serviceName = "EmisionEDepor")
public class EmisionEDepor {

@Resource
private WebServiceContext wsContext;

private IEmisionEDeporEJBLocal getEJBManager() throws NamingException {
    InitialContext ctx = new InitialContext();
    IEmisionEDeporEJBLocal bean = (IEmisionEDeporEJBLocal) ctx.lookup("BseTiendaEar/EmisionEDeporEJB/local");
    return bean;
}

private DTSesion getDTSesion(String usuario, String contrasena) {
    MessageContext mc = wsContext.getMessageContext();
    HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
    return new DTSesion(usuario, contrasena, req.getRemoteAddr());
}

@WebMethod
public CotizacionEDeporResp cotizarEDeporAnonimo(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena,
        @WebParam(name = "planCobertura") String planCobertura,
        @WebParam(name = "tipoBuque") String tipoBuque,
        @WebParam(name = "fechaDesde") Date fechaDesde,
        @WebParam(name = "fechaHasta") Date fechaHasta,
        @WebParam(name = "capital") double capital,
        @WebParam(name = "planPago") int planPago) {
	CotizacionEDeporResp result = new CotizacionEDeporResp();
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	CotizacionEDepor cotizacionEDepor = getEJBManager().cotizarEDeporAnonimo(getDTSesion(usuario, contrasena), planCobertura,  tipoBuque,
    	        fechaDesde, fechaHasta, capital, planPago);
        result.setCotizacionEDepor(cotizacionEDepor);
    } catch (BSEException ex2) {
    	ex2.printStackTrace();

    	Logger.getLogger(EmisionEDepor.class.getName()).log(Level.ERROR, null, ex2);
		result.setCodigoError(String.valueOf(ex2.getCodigoError()));
		result.setDescripcionError(ex2.getDescripcion());
    } catch (Exception ex1) {
    	ex1.printStackTrace();

    	Logger.getLogger(EmisionEDepor.class.getName()).log(Level.ERROR, null, ex1);
		ex1.printStackTrace();
		result.setCodigoError(String.valueOf(CodigosError.excepcion_generica));
		result.setDescripcionError(BSEException.getDescripcionError(CodigosError.excepcion_generica));
    }
    return result;
}

@WebMethod
public EmisionEDeporResp emitirEDepor(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena, 
        @WebParam(name = "tipoDocumento") String tipoDocumento, 
        @WebParam(name = "documento") String documento, 
        @WebParam(name = "matriculaBuque") String matriculaBuque, 
        @WebParam(name = "nombreBuque") String nombreBuque, 
        @WebParam(name = "nroCotizacion") long nroCotizacion, 
        @WebParam(name = "consumoFinal") String consumoFinal) {
	EmisionEDeporResp result = new EmisionEDeporResp();
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	PolizaEDepor polizaEDepor = getEJBManager().emitirEDepor(getDTSesion(usuario, contrasena), tipoDocumento, documento, matriculaBuque, nombreBuque, nroCotizacion, consumoFinal);
        result.setPolizaEDepor(polizaEDepor);
    } catch (BSEException ex2) {
    	ex2.printStackTrace();
    	
		Logger.getLogger(EmisionEDepor.class.getName()).log(Level.ERROR, null, ex2);
		result.setCodigoError(String.valueOf(ex2.getCodigoError()));
		result.setDescripcionError(ex2.getDescripcion());
    } catch (Exception ex1) {
    	ex1.printStackTrace();
    	
		Logger.getLogger(EmisionEDepor.class.getName()).log(Level.ERROR, null, ex1);
		ex1.printStackTrace();
		result.setCodigoError(String.valueOf(CodigosError.excepcion_generica));
		result.setDescripcionError(BSEException.getDescripcionError(CodigosError.excepcion_generica));
    }
    return result;
}

@WebMethod
public TiposBuquesResp consultaTiposBuques(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena) {
	TiposBuquesResp result = new TiposBuquesResp();
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	List<TipoBuque> tiposBuques = getEJBManager().consultaTiposBuques(getDTSesion(usuario, contrasena));
        result.setTiposBuques(tiposBuques);
    } catch (BSEException ex2) {
    	ex2.printStackTrace();
    	
		Logger.getLogger(EmisionEDepor.class.getName()).log(Level.ERROR, null, ex2);
		result.setCodigoError(String.valueOf(ex2.getCodigoError()));
		result.setDescripcionError(ex2.getDescripcion());
    } catch (Exception ex1) {
    	ex1.printStackTrace();
    	
		Logger.getLogger(EmisionEDepor.class.getName()).log(Level.ERROR, null, ex1);
		ex1.printStackTrace();
		result.setCodigoError(String.valueOf(CodigosError.excepcion_generica));
		result.setDescripcionError(BSEException.getDescripcionError(CodigosError.excepcion_generica));
    }
    return result;
}

@WebMethod
public CoberturasRcResp consultaCoberturasRc(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena) {
	CoberturasRcResp result = new CoberturasRcResp();
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	List<CoberturaRc> cobs = getEJBManager().consultaCoberturasRc(getDTSesion(usuario, contrasena));
        result.setCoberturasRc(cobs);
    } catch (BSEException ex2) {
    	ex2.printStackTrace();
    	
		Logger.getLogger(EmisionEDepor.class.getName()).log(Level.ERROR, null, ex2);
		result.setCodigoError(String.valueOf(ex2.getCodigoError()));
		result.setDescripcionError(ex2.getDescripcion());
    } catch (Exception ex1) {
    	ex1.printStackTrace();
    	
		Logger.getLogger(EmisionEDepor.class.getName()).log(Level.ERROR, null, ex1);
		ex1.printStackTrace();
		result.setCodigoError(String.valueOf(CodigosError.excepcion_generica));
		result.setDescripcionError(BSEException.getDescripcionError(CodigosError.excepcion_generica));
    }
    return result;
}



}
