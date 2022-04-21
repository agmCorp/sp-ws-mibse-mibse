package com.bse.servicios.ws.bici;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.bse.accesodatos.ebici.CotizacionBici;
import com.bse.accesodatos.ebici.PolizaBici;
import com.bse.accesodatos.ebici.TipoBici;
import com.bse.negocio.comun.BSEException;
import com.bse.negocio.comun.CodigosError;
import com.bse.servicios.ebicis.IEmisionBiciEJBLocal;
import com.bse.servicios.seguridad.dt.DTSesion;

@WebService(serviceName = "EmisionBici")
public class EmisionBici {

@Resource
private WebServiceContext wsContext;

private IEmisionBiciEJBLocal getEJBManager() throws NamingException {
    InitialContext ctx = new InitialContext();
    IEmisionBiciEJBLocal bean = (IEmisionBiciEJBLocal) ctx.lookup("BseTiendaEar/EmisionBiciEJB/local");
    return bean;
}

private DTSesion getDTSesion(String usuario, String contrasena) {
    MessageContext mc = wsContext.getMessageContext();
    HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
    return new DTSesion(usuario, contrasena, req.getRemoteAddr());
}

@WebMethod
public CotizacionBiciResp cotizarBiciAnonimo(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena,
        @WebParam(name = "planCobertura") String planCobertura,
        @WebParam(name = "valorBicicleta") Double valorBicicleta, 
        @WebParam(name = "vigenciaSeguro") Integer vigenciaSeguro) {
	CotizacionBiciResp result = new CotizacionBiciResp();
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	CotizacionBici cotizacionBici = getEJBManager().cotizarBiciAnonimo(getDTSesion(usuario, contrasena), planCobertura, valorBicicleta, vigenciaSeguro);
        result.setCotizacionBici(cotizacionBici);
    } catch (BSEException ex2) {
    	ex2.printStackTrace();

    	Logger.getLogger(EmisionBici.class.getName()).log(Level.ERROR, null, ex2);
		result.setCodigoError(String.valueOf(ex2.getCodigoError()));
		result.setDescripcionError(ex2.getDescripcion());
    } catch (Exception ex1) {
    	ex1.printStackTrace();

    	Logger.getLogger(EmisionBici.class.getName()).log(Level.ERROR, null, ex1);
		ex1.printStackTrace();
		result.setCodigoError(String.valueOf(CodigosError.excepcion_generica));
		result.setDescripcionError(BSEException.getDescripcionError(CodigosError.excepcion_generica));
    }
    return result;
}

//-	Fecha de factura (hasta 2 años y hasta hoy inclusive) *
//-	Tipo de bicicleta (clásica o eléctrica, lista desplegable)
//-	Fecha de nacimiento ** 
//-	Marca (texto plano no valida)
//-	Serie (texto plano no valida)
//-	Modelo (texto plano no valida)

@WebMethod
public EmisionBiciResp emitirBici(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena, 
        @WebParam(name = "planPago") String planPago, 
        @WebParam(name = "tipoDocumento") String tipoDocumento, 
        @WebParam(name = "documento") String documento, 
        @XmlElement(nillable=true)
        @WebParam(name = "fechaNacimientoCliente") Date fechaNacimientoCliente, 
        @WebParam(name = "nroCotizacion") long nroCotizacion, 
        @WebParam(name = "tipoBicicleta") String tipoBicicleta,
        @WebParam(name = "marca") String marca, 
        @WebParam(name = "serie") String serie, 
        @WebParam(name = "modelo") String modelo, 
        @WebParam(name = "fechaFactura") Date fechaFactura, 
        @WebParam(name = "consumoFinal") String consumoFinal) {
	EmisionBiciResp result = new EmisionBiciResp();
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	PolizaBici polizaBici = getEJBManager().emitirBici(getDTSesion(usuario, contrasena), planPago, tipoDocumento, 
    	        documento, fechaFactura, tipoBicicleta, fechaNacimientoCliente, marca, serie, 
    	        modelo, nroCotizacion, consumoFinal);
        result.setPolizaBici(polizaBici);
    } catch (BSEException ex2) {
    	ex2.printStackTrace();
    	
		Logger.getLogger(EmisionBici.class.getName()).log(Level.ERROR, null, ex2);
		result.setCodigoError(String.valueOf(ex2.getCodigoError()));
		result.setDescripcionError(ex2.getDescripcion());
    } catch (Exception ex1) {
    	ex1.printStackTrace();
    	
		Logger.getLogger(EmisionBici.class.getName()).log(Level.ERROR, null, ex1);
		ex1.printStackTrace();
		result.setCodigoError(String.valueOf(CodigosError.excepcion_generica));
		result.setDescripcionError(BSEException.getDescripcionError(CodigosError.excepcion_generica));
    }
    return result;
}

@WebMethod
public ClienteDeudaResp controlarClienteConDeuda(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena, 
        @WebParam(name = "tipoDocumento") String tipoDocumento, 
        @WebParam(name = "documento") String documento, 
        @WebParam(name = "nroCotizacion") int nroCotizacion, 
        @WebParam(name = "nroCertificado") int nroCertificado) {
	ClienteDeudaResp result = new ClienteDeudaResp();
	
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	String tieneDeuda = getEJBManager().clienteConDeuda(getDTSesion(usuario, contrasena), tipoDocumento, documento,
				nroCotizacion, nroCertificado);
        result.setClienteConDeuda(tieneDeuda);
    } catch (BSEException ex2) {
    	ex2.printStackTrace();
    	
		Logger.getLogger(EmisionBici.class.getName()).log(Level.ERROR, null, ex2);
		result.setCodigoError(String.valueOf(ex2.getCodigoError()));
		result.setDescripcionError(ex2.getDescripcion());
    } catch (Exception ex1) {
    	ex1.printStackTrace();
    	
		Logger.getLogger(EmisionBici.class.getName()).log(Level.ERROR, null, ex1);
		ex1.printStackTrace();
		result.setCodigoError(String.valueOf(CodigosError.excepcion_generica));
		result.setDescripcionError(BSEException.getDescripcionError(CodigosError.excepcion_generica));
    }
    return result;
}

@WebMethod
public TiposBicicletasResp consultaTiposBicicletas(@WebParam(name = "usuario") String usuario, 
        @WebParam(name = "contrasena") String contrasena) {
	TiposBicicletasResp result = new TiposBicicletasResp();
	
    try {
    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	List<TipoBici> tiposBicis = getEJBManager().consultaTiposBicicletas(getDTSesion(usuario, contrasena));
        result.setTiposBicicletas(tiposBicis);
    } catch (BSEException ex2) {
    	ex2.printStackTrace();
    	
		Logger.getLogger(EmisionBici.class.getName()).log(Level.ERROR, null, ex2);
		result.setCodigoError(String.valueOf(ex2.getCodigoError()));
		result.setDescripcionError(ex2.getDescripcion());
    } catch (Exception ex1) {
    	ex1.printStackTrace();
    	
		Logger.getLogger(EmisionBici.class.getName()).log(Level.ERROR, null, ex1);
		ex1.printStackTrace();
		result.setCodigoError(String.valueOf(CodigosError.excepcion_generica));
		result.setDescripcionError(BSEException.getDescripcionError(CodigosError.excepcion_generica));
    }
    return result;
}



}
