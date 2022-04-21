package com.bse.servicios.ws.indivi;

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

import com.bse.accesodatos.eindivi.AreaCirculacion;
import com.bse.accesodatos.eindivi.CotizacionIndivi;
import com.bse.accesodatos.eindivi.Modalidad;
import com.bse.accesodatos.eindivi.ModeloVehiculo;
import com.bse.accesodatos.eindivi.PolizaIndivi;
import com.bse.accesodatos.eindivi.TipoCombustible;
import com.bse.accesodatos.eindivi.TipoContratante;
import com.bse.accesodatos.eindivi.TipoVehiculo;
import com.bse.negocio.comun.BSEException;
import com.bse.negocio.comun.CodigosError;
import com.bse.servicios.eindivi.IEmisionIndiviEJBLocal;
import com.bse.servicios.seguridad.dt.DTSesion;
import com.bse.servicios.ws.soa.EmisionSoa;

@WebService(serviceName = "EmisionIndivi")
public class EmisionIndivi {

@Resource
private WebServiceContext wsContext;

private IEmisionIndiviEJBLocal getEJBManager() throws NamingException {
    InitialContext ctx = new InitialContext();
    IEmisionIndiviEJBLocal bean = (IEmisionIndiviEJBLocal) ctx.lookup("BseTiendaEar/EmisionIndiviEJB/local");
    return bean;
}

private DTSesion getDTSesion(String usuario, String contrasena) {
    MessageContext mc = wsContext.getMessageContext();
    HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
    return new DTSesion(usuario, contrasena, req.getRemoteAddr());
}

/*
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
		Logger.getLogger(EmisionIndivi.class.getName()).log(Level.ERROR, null, ex2);
		result.setCodigoError(String.valueOf(ex2.getCodigoError()));
		result.setDescripcionError(ex2.getDescripcion());
    } catch (Exception ex1) {
		Logger.getLogger(EmisionIndivi.class.getName()).log(Level.ERROR, null, ex1);
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
		Logger.getLogger(EmisionIndivi.class.getName()).log(Level.ERROR, null, ex2);
		result.setCodigoError(String.valueOf(ex2.getCodigoError()));
		result.setDescripcionError(ex2.getDescripcion());
    } catch (Exception ex1) {
		Logger.getLogger(EmisionIndivi.class.getName()).log(Level.ERROR, null, ex1);
		ex1.printStackTrace();
		result.setCodigoError(String.valueOf(CodigosError.excepcion_generica));
		result.setDescripcionError(BSEException.getDescripcionError(CodigosError.excepcion_generica));
    }
    return result;
}
*/

@WebMethod
public CotizacionIndiviResp cotizarIndiviAnonimo(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena,
        @WebParam(name = "planCobertura") String planCobertura,
        @WebParam(name = "marcaVehiculo") String marcaVehiculo,
        @WebParam(name = "modeloVehiculo") String modeloVehiculo,
        @WebParam(name = "anioVehiculo") String anioVehiculo,
        @WebParam(name = "tipoVehiculo") String tipoVehiculo,
        @WebParam(name = "combustible") String combustible,
        @WebParam(name = "modalidad") String modalidad,
        @WebParam(name = "areaCirculacion") String areaCirculacion,
        @WebParam(name = "planPago") int planPago) {
	CotizacionIndiviResp result = new CotizacionIndiviResp();
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	CotizacionIndivi cotizacionIndivi = getEJBManager().cotizarIndiviAnonimo(getDTSesion(usuario, contrasena), planCobertura, marcaVehiculo, modeloVehiculo, anioVehiculo, 
    			tipoVehiculo, combustible, modalidad, areaCirculacion, planPago);
    	cotizacionIndivi.setMatriculaVehiculo("");
        result.setCotizacionIndivi(cotizacionIndivi);
    } catch (BSEException ex2) {
    	ex2.printStackTrace();

    	Logger.getLogger(EmisionIndivi.class.getName()).log(Level.ERROR, null, ex2);
		result.setCodigoError(String.valueOf(ex2.getCodigoError()));
		result.setDescripcionError(ex2.getDescripcion());
    } catch (Exception ex1) {
    	ex1.printStackTrace();

    	Logger.getLogger(EmisionIndivi.class.getName()).log(Level.ERROR, null, ex1);
		ex1.printStackTrace();
		result.setCodigoError(String.valueOf(CodigosError.excepcion_generica));
		result.setDescripcionError(BSEException.getDescripcionError(CodigosError.excepcion_generica));
    }
    return result;
}

@WebMethod
public EmisionIndiviResp emitirIndivi(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena, 
        @WebParam(name = "tipoDocumento") String tipoDocumento, 
        @WebParam(name = "documento") String documento, 
        @WebParam(name = "matriculaVehiculo") String matriculaVehiculo, 
        @WebParam(name = "padronVehiculo") String padronVehiculo,
        @WebParam(name = "motorVehiculo") String motorVehiculo, 
        @WebParam(name = "chasisVehiculo") String chasisVehiculo, 
        @WebParam(name = "calidadContratante") String calidadContratante, 
        @WebParam(name = "nroCotizacion") long nroCotizacion, 
        @WebParam(name = "consumoFinal") String consumoFinal) {
	EmisionIndiviResp result = new EmisionIndiviResp();
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	PolizaIndivi polizaIndivi = getEJBManager().emitirIndivi(getDTSesion(usuario, contrasena), tipoDocumento, documento, matriculaVehiculo, padronVehiculo,
				motorVehiculo, chasisVehiculo, calidadContratante, nroCotizacion, consumoFinal);
        result.setPolizaIndivi(polizaIndivi);
    } catch (BSEException ex2) {
    	ex2.printStackTrace();
    	
		Logger.getLogger(EmisionIndivi.class.getName()).log(Level.ERROR, null, ex2);
		result.setCodigoError(String.valueOf(ex2.getCodigoError()));
		result.setDescripcionError(ex2.getDescripcion());
    } catch (Exception ex1) {
    	ex1.printStackTrace();
    	
		Logger.getLogger(EmisionIndivi.class.getName()).log(Level.ERROR, null, ex1);
		ex1.printStackTrace();
		result.setCodigoError(String.valueOf(CodigosError.excepcion_generica));
		result.setDescripcionError(BSEException.getDescripcionError(CodigosError.excepcion_generica));
    }
    return result;
}

@WebMethod
public TiposCombustibleResp consultaTiposCombustible(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena) {
	TiposCombustibleResp result = new TiposCombustibleResp();
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	List<TipoCombustible> lista = getEJBManager().consultaTiposCombustible(getDTSesion(usuario, contrasena));
        result.setTiposCombustible(lista);
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
public TiposVehiculosResp consultaTiposVehiculos(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena) {
	TiposVehiculosResp result = new TiposVehiculosResp();
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	List<TipoVehiculo> lista = getEJBManager().consultaTiposVehiculos(getDTSesion(usuario, contrasena));
        result.setTiposVehiculos(lista);
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
public TiposContratanteResp consultaTiposContratante(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena) {
	TiposContratanteResp result = new TiposContratanteResp();
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	List<TipoContratante> lista = getEJBManager().consultaTiposContratante(getDTSesion(usuario, contrasena));
        result.setTipos(lista);
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
public ModalidadesResp consultaModalidades(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena) {
	ModalidadesResp result = new ModalidadesResp();
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	List<Modalidad> lista = getEJBManager().consultaModalidades(getDTSesion(usuario, contrasena));
        result.setModalidades(lista);
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
public AreasCirculacionResp consultaAreasCirculacion(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena) {
	AreasCirculacionResp result = new AreasCirculacionResp();
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	List<AreaCirculacion> lista = getEJBManager().consultaAreasCirculacion(getDTSesion(usuario, contrasena));
        result.setAreasCirculacion(lista);
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
public ModelosVehiculosResp consultaModelosVehiculos(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena,
        @WebParam(name = "marcaVehiculo") String marcaVehiculo) {
	ModelosVehiculosResp result = new ModelosVehiculosResp();
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	List<ModeloVehiculo> lista = getEJBManager().consultaModelosVehiculos(getDTSesion(usuario, contrasena), marcaVehiculo);
        result.setTiposVehiculos(lista);
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
