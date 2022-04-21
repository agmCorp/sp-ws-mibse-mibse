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

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.bse.accesodatos.esoa.CategoriaVehiculo;
import com.bse.accesodatos.esoa.CotizacionSoa;
import com.bse.accesodatos.esoa.MarcaVehiculo;
import com.bse.accesodatos.esoa.PlanCobertura;
import com.bse.accesodatos.esoa.PlanPago;
import com.bse.accesodatos.esoa.PolizaSoa;
import com.bse.negocio.comun.BSEException;
import com.bse.negocio.comun.CodigosError;
import com.bse.servicios.esoa.IEmisionSoaEJBLocal;
import com.bse.servicios.seguridad.dt.DTSesion;

@WebService(serviceName = "EmisionSoa")
public class EmisionSoa {

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
public PlanCoberturaResp consultaPlanCobertura(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena, 
        @WebParam(name = "plan") String plan) {
	PlanCoberturaResp result = new PlanCoberturaResp();
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	PlanCobertura planObj = getEJBManager().consultaPlanCobertura(getDTSesion(usuario, contrasena), plan);
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
    	List<PlanCobertura> lista = getEJBManager().consultaPlanesCobertura(getDTSesion(usuario, contrasena), ramo, producto);
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
    	PlanPago planObj = getEJBManager().consultaPlanPago(getDTSesion(usuario, contrasena), plan);
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

    	List<PlanPago> lista = getEJBManager().consultaPlanesPago(getDTSesion(usuario, contrasena));
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
public MarcaVehiculoResp consultaMarcaVehiculo(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena,
        @WebParam(name = "marca") String marca) {
	MarcaVehiculoResp result = new MarcaVehiculoResp();
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	MarcaVehiculo marcaObj = getEJBManager().consultaMarcaVehiculo(getDTSesion(usuario, contrasena), marca);
        result.setMarcaVehiculo(marcaObj);
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
public MarcasVehiculosResp consultaMarcasVehiculos(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena) {
	MarcasVehiculosResp result = new MarcasVehiculosResp();
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	List<MarcaVehiculo> lista = getEJBManager().consultaMarcasVehiculos(getDTSesion(usuario, contrasena));
        result.setMarcasVehiculos(lista);
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
public CategoriaVehiculoResp consultaCategoriaVehiculo(@WebParam(name = "usuario") String usuario,         
			@WebParam(name = "contrasena") String contrasena,
			@WebParam(name = "categoria") String categoria) {
	CategoriaVehiculoResp result = new CategoriaVehiculoResp();
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	CategoriaVehiculo cat = getEJBManager().consultaCategoriaVehiculo(getDTSesion(usuario, contrasena), categoria);
        result.setCategoriaVehiculo(cat);
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
public CategoriasVehiculosResp consultaCategoriasVehiculos(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena) {
	CategoriasVehiculosResp result = new CategoriasVehiculosResp();
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	List<CategoriaVehiculo> lista = getEJBManager().consultaCategoriasVehiculos(getDTSesion(usuario, contrasena));
        result.setCategoriasVehiculos(lista);
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
public CotizacionSoaResp cotizarSoaAnonimo(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena,
        @WebParam(name = "planCobertura") String planCobertura,
        @WebParam(name = "marcaVehiculo") String marcaVehiculo,
        @WebParam(name = "anioVehiculo") String anioVehiculo,
        @WebParam(name = "categoriaVehiculo") String categoriaVehiculo,
        @WebParam(name = "planPago") int planPago) {
	CotizacionSoaResp result = new CotizacionSoaResp();
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	CotizacionSoa cotizacionSoa = getEJBManager().cotizarSoaAnonimo(getDTSesion(usuario, contrasena), planCobertura, marcaVehiculo, anioVehiculo, categoriaVehiculo, planPago);
    	cotizacionSoa.setMatriculaVehiculo("");
        result.setCotizacionSoa(cotizacionSoa);
    } catch (BSEException ex2) {
    	ex2.printStackTrace();

    	Logger.getLogger(EmisionSoa.class.getName()).log(Level.ERROR, null, ex2);
		result.setCodigoError(String.valueOf(ex2.getCodigoError()));
		result.setDescripcionError(ex2.getDescripcion());
    } catch (Exception ex1) {
    	ex1.printStackTrace();

    	Logger.getLogger(EmisionSoa.class.getName()).log(Level.ERROR, null, ex1);
		ex1.printStackTrace();
		result.setCodigoError(String.valueOf(CodigosError.excepcion_generica));
		result.setDescripcionError(BSEException.getDescripcionError(CodigosError.excepcion_generica));
    }
    return result;
}

@WebMethod
public EmisionSoaResp emitirSoa(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena, 
        @WebParam(name = "tipoDocumento") String tipoDocumento, 
        @WebParam(name = "documento") String documento, 
        @WebParam(name = "matriculaVehiculo") String matriculaVehiculo, 
        @WebParam(name = "padronVehiculo") String padronVehiculo,
        @WebParam(name = "motorVehiculo") String motorVehiculo, 
        @WebParam(name = "chasisVehiculo") String chasisVehiculo, 
        @WebParam(name = "nroCotizacion") long nroCotizacion, 
        @WebParam(name = "consumoFinal") String consumoFinal) {
	EmisionSoaResp result = new EmisionSoaResp();
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	PolizaSoa polizaSoa = getEJBManager().emitirSoa(getDTSesion(usuario, contrasena), tipoDocumento, documento, matriculaVehiculo, padronVehiculo,
				motorVehiculo, chasisVehiculo, nroCotizacion, consumoFinal);
        result.setPolizaSoa(polizaSoa);
    } catch (BSEException ex2) {
    	ex2.printStackTrace();
    	
		Logger.getLogger(EmisionSoa.class.getName()).log(Level.ERROR, null, ex2);
		result.setCodigoError(String.valueOf(ex2.getCodigoError()));
		result.setDescripcionError(ex2.getDescripcion());
    } catch (Exception ex1) {
    	ex1.printStackTrace();
    	
		Logger.getLogger(EmisionSoa.class.getName()).log(Level.ERROR, null, ex1);
		ex1.printStackTrace();
		result.setCodigoError(String.valueOf(CodigosError.excepcion_generica));
		result.setDescripcionError(BSEException.getDescripcionError(CodigosError.excepcion_generica));
    }
    return result;
}


}
