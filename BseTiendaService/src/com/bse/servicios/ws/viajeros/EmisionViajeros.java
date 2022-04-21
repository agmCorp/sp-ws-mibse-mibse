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

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.bse.accesodatos.esoa.PlanCobertura;
import com.bse.accesodatos.esoa.PlanPago;
import com.bse.accesodatos.eviajeros.CoberturaMuerte;
import com.bse.accesodatos.eviajeros.CoberturaPrexistentes;
import com.bse.accesodatos.eviajeros.CotizacionViajeros;
import com.bse.accesodatos.eviajeros.PolizaViajeros;
import com.bse.accesodatos.eviajeros.Viajero;
import com.bse.negocio.comun.BSEException;
import com.bse.negocio.comun.CodigosError;
import com.bse.servicios.esoa.IEmisionSoaEJBLocal;
import com.bse.servicios.eviajeros.IEmisionViajerosEJBLocal;
import com.bse.servicios.seguridad.dt.DTSesion;
import com.bse.servicios.ws.soa.EmisionSoa;
import com.bse.servicios.ws.soa.PlanCoberturaResp;
import com.bse.servicios.ws.soa.PlanPagoResp;
import com.bse.servicios.ws.soa.PlanesCoberturaResp;
import com.bse.servicios.ws.soa.PlanesPagoResp;

@WebService(serviceName = "EmisionViajeros")
public class EmisionViajeros {

@Resource
private WebServiceContext wsContext;

private IEmisionViajerosEJBLocal getEJBManager() throws NamingException {
    InitialContext ctx = new InitialContext();
    IEmisionViajerosEJBLocal bean = (IEmisionViajerosEJBLocal) ctx.lookup("BseTiendaEar/EmisionViajerosEJB/local");
    return bean;
}

private IEmisionSoaEJBLocal getSoaEJBManager() throws NamingException {
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
public CotizacionViajerosResp cotizarViajerosAnonimo(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena,
        @WebParam(name = "planCobertura") String planCobertura,
        @WebParam(name = "planPago") int planPago,
        @WebParam(name = "fechaDesde") Date fechaDesde, 
        @WebParam(name = "fechaHasta") Date fechaHasta,
        @WebParam(name = "listaPersonas") ArrayList<Viajero> listaPersonas,
        @WebParam(name = "extension") String extension, 
        @WebParam(name = "fechaSalidaPais") Date fechaSalidaPais) {
	CotizacionViajerosResp result = new CotizacionViajerosResp();
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	
    	CotizacionViajeros cotizacionViajeros = getEJBManager().cotizarViajerosAnonimo(getDTSesion(usuario, contrasena), 
    			planCobertura, planPago, fechaDesde, fechaHasta, listaPersonas, extension, fechaSalidaPais);
        result.setCotizacionViajeros(cotizacionViajeros);
    } catch (BSEException ex2) {
		Logger.getLogger(EmisionViajeros.class.getName()).log(Level.ERROR, null, ex2);
		result.setCodigoError(String.valueOf(ex2.getCodigoError()));
		result.setDescripcionError(ex2.getDescripcion());
    } catch (Exception ex1) {
		Logger.getLogger(EmisionViajeros.class.getName()).log(Level.ERROR, null, ex1);
		ex1.printStackTrace();
		result.setCodigoError(String.valueOf(CodigosError.excepcion_generica));
		result.setDescripcionError(BSEException.getDescripcionError(CodigosError.excepcion_generica));
    }
    return result;
}

@WebMethod
public EmisionViajerosResp emitirViajeros(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena, 
        @WebParam(name = "tipoDocumentoContratante") String tipoDocumentoContratante, 
        @WebParam(name = "documentoContratante") String documentoContratante, 
        @WebParam(name = "listaPersonas") ArrayList<Viajero> listaPersonas, 
        @WebParam(name = "nroCotizacion") long nroCotizacion,
        @WebParam(name = "consumoFinal") String consumoFinal,
        @WebParam(name = "extension") String extension, 
        @WebParam(name = "fechaSalidaPais") Date fechaSalidaPais) {

	EmisionViajerosResp result = new EmisionViajerosResp();

    try {
    	PolizaViajeros poliza = getEJBManager().emitirViajeros(getDTSesion(usuario, contrasena), tipoDocumentoContratante, documentoContratante, 
    	        listaPersonas, nroCotizacion, consumoFinal, extension, fechaSalidaPais);
    	result.setPolizaViajeros(poliza);
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    } catch (BSEException ex2) {
		Logger.getLogger(EmisionViajeros.class.getName()).log(Level.ERROR, null, ex2);
		result.setCodigoError(String.valueOf(ex2.getCodigoError()));
		result.setDescripcionError(ex2.getDescripcion());
    } catch (Exception ex1) {
		Logger.getLogger(EmisionViajeros.class.getName()).log(Level.ERROR, null, ex1);
		ex1.printStackTrace();
		result.setCodigoError(String.valueOf(CodigosError.excepcion_generica));
		result.setDescripcionError(BSEException.getDescripcionError(CodigosError.excepcion_generica));
    }
	
	return result;
}

//-----------------

@WebMethod
public PlanCoberturaResp consultaPlanCobertura(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena, 
        @WebParam(name = "plan") String plan) {
	PlanCoberturaResp result = new PlanCoberturaResp();
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	PlanCobertura planObj = getSoaEJBManager().consultaPlanCobertura(getDTSesion(usuario, contrasena), plan);
        result.setPlanCobertura(planObj);
    } catch (BSEException ex2) {
		Logger.getLogger(EmisionSoa.class.getName()).log(Level.ERROR, null, ex2);
		result.setCodigoError(String.valueOf(ex2.getCodigoError()));
		result.setDescripcionError(ex2.getDescripcion());
    } catch (Exception ex1) {
		Logger.getLogger(EmisionSoa.class.getName()).log(Level.ERROR, null, ex1);
		ex1.printStackTrace();
		result.setCodigoError(String.valueOf(CodigosError.excepcion_generica));
		result.setDescripcionError(BSEException.getDescripcionError(CodigosError.excepcion_generica));
    }
    return result;
}

@WebMethod
public PlanesCoberturaResp consultaPlanesCobertura(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena, 
        @WebParam(name = "ramo") int ramo, 
        @WebParam(name = "producto") String producto) {
	PlanesCoberturaResp result = new PlanesCoberturaResp();
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	List<PlanCobertura> lista = getSoaEJBManager().consultaPlanesCobertura(getDTSesion(usuario, contrasena), ramo, producto);
        result.setPlanesCobertura(lista);
    } catch (BSEException ex2) {
		Logger.getLogger(EmisionSoa.class.getName()).log(Level.ERROR, null, ex2);
		result.setCodigoError(String.valueOf(ex2.getCodigoError()));
		result.setDescripcionError(ex2.getDescripcion());
    } catch (Exception ex1) {
		Logger.getLogger(EmisionSoa.class.getName()).log(Level.ERROR, null, ex1);
		ex1.printStackTrace();
		result.setCodigoError(String.valueOf(CodigosError.excepcion_generica));
		result.setDescripcionError(BSEException.getDescripcionError(CodigosError.excepcion_generica));
    }
    return result;
}

@WebMethod
public PlanPagoResp consultaPlanPago(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena,
        @WebParam(name = "plan") int plan) {
	PlanPagoResp result = new PlanPagoResp();
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	PlanPago planObj = getSoaEJBManager().consultaPlanPago(getDTSesion(usuario, contrasena), plan);
        result.setPlanPago(planObj);
    } catch (BSEException ex2) {
		Logger.getLogger(EmisionSoa.class.getName()).log(Level.ERROR, null, ex2);
		result.setCodigoError(String.valueOf(ex2.getCodigoError()));
		result.setDescripcionError(ex2.getDescripcion());
    } catch (Exception ex1) {
		Logger.getLogger(EmisionSoa.class.getName()).log(Level.ERROR, null, ex1);
		ex1.printStackTrace();
		result.setCodigoError(String.valueOf(CodigosError.excepcion_generica));
		result.setDescripcionError(BSEException.getDescripcionError(CodigosError.excepcion_generica));
    }
    return result;
}

@WebMethod
public PlanesPagoResp consultaPlanesPago(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena) {
	PlanesPagoResp result = new PlanesPagoResp();
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");

    	List<PlanPago> lista = getSoaEJBManager().consultaPlanesPago(getDTSesion(usuario, contrasena));
		result.setPlanesPago(lista);
    } catch (BSEException ex2) {
		Logger.getLogger(EmisionSoa.class.getName()).log(Level.ERROR, null, ex2);
		result.setCodigoError(String.valueOf(ex2.getCodigoError()));
		result.setDescripcionError(ex2.getDescripcion());
    } catch (Exception ex1) {
		Logger.getLogger(EmisionSoa.class.getName()).log(Level.ERROR, null, ex1);
		ex1.printStackTrace();
		result.setCodigoError(String.valueOf(CodigosError.excepcion_generica));
		result.setDescripcionError(BSEException.getDescripcionError(CodigosError.excepcion_generica));
    }
    return result;
}

@WebMethod
public CoberturasPrexistentesResp consultaCoberturasPrexistentes(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena) {
	CoberturasPrexistentesResp result = new CoberturasPrexistentesResp();
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	List<CoberturaPrexistentes> lista = getEJBManager().consultaCoberturasPrexistentes(getDTSesion(usuario, contrasena));
        result.setCoberturasPrexistentes(lista);
    } catch (BSEException ex2) {
		Logger.getLogger(EmisionSoa.class.getName()).log(Level.ERROR, null, ex2);
		result.setCodigoError(String.valueOf(ex2.getCodigoError()));
		result.setDescripcionError(ex2.getDescripcion());
    } catch (Exception ex1) {
		Logger.getLogger(EmisionSoa.class.getName()).log(Level.ERROR, null, ex1);
		ex1.printStackTrace();
		result.setCodigoError(String.valueOf(CodigosError.excepcion_generica));
		result.setDescripcionError(BSEException.getDescripcionError(CodigosError.excepcion_generica));
    }
    return result;
}

@WebMethod
public CoberturasMuerteResp consultaCoberturasMuerte(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena) {
	CoberturasMuerteResp result = new CoberturasMuerteResp();
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	List<CoberturaMuerte> lista = getEJBManager().consultaCoberturasMuerte(getDTSesion(usuario, contrasena));
        result.setCoberturasMuerte(lista);
    } catch (BSEException ex2) {
		Logger.getLogger(EmisionSoa.class.getName()).log(Level.ERROR, null, ex2);
		result.setCodigoError(String.valueOf(ex2.getCodigoError()));
		result.setDescripcionError(ex2.getDescripcion());
    } catch (Exception ex1) {
		Logger.getLogger(EmisionSoa.class.getName()).log(Level.ERROR, null, ex1);
		ex1.printStackTrace();
		result.setCodigoError(String.valueOf(CodigosError.excepcion_generica));
		result.setDescripcionError(BSEException.getDescripcionError(CodigosError.excepcion_generica));
    }
    return result;
}

}
