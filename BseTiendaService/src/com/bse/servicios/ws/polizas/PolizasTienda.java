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

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bse.negocio.comun.BSEExceptionTienda;
import com.bse.negocio.comun.CodigosErrorTienda;
import com.bse.servicios.esoa.IEmisionSoaTiendaEJBLocal;
import com.bse.servicios.seguridad.dt.DTSesionTienda;

import uy.com.bse.common.audit.annotations.Audit;
import uy.com.bse.common.audit.interceptors.AuditorManager;
import uy.com.bse.common.webservices.monitoring.constants.Constants;


@WebService(serviceName = "Polizas")
public class PolizasTienda {

    private static final Logger logger = LogManager.getLogger(PolizasTienda.class);

@Resource
private WebServiceContext wsContext;

private IEmisionSoaTiendaEJBLocal getEJBManager() throws NamingException {
    InitialContext ctx = new InitialContext();
  //  IEmisionSoaTiendaEJBLocal bean = (IEmisionSoaTiendaEJBLocal) ctx.lookup("BseTiendaEar/EmisionSoaTiendaEJB/local");
    IEmisionSoaTiendaEJBLocal bean = (IEmisionSoaTiendaEJBLocal) ctx.lookup("java:global/BseTiendaEar/BseTiendaEjb/EmisionSoaTiendaEJB!com.bse.servicios.esoa.IEmisionSoaTiendaEJBLocal");

    return bean;
}

private DTSesionTienda getDTSesion(String usuario, String contrasena) {
    MessageContext mc = wsContext.getMessageContext();
    HttpServletRequest req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
    return new DTSesionTienda(usuario, contrasena, req.getRemoteAddr());
}

@Audit(excludedParams = "arg1")
@WebMethod
public AlertaPagoRedesTiendaResp alertarPagoRedes(@WebParam(name = "usuario") String usuario,
                                                  @WebParam(name = "contrasena") String contrasena,
                                                  @WebParam(name = "sucursal") int sucursal,
                                                  @WebParam(name = "ramo") int ramo,
                                                  @WebParam(name = "producto") String producto,
                                                  @WebParam(name = "nroCotizacion") long nroCotizacion){
	AlertaPagoRedesTiendaResp result = new AlertaPagoRedesTiendaResp();
    try {
    	AuditorManager.addStatusAudit(Constants.STATUS_OK);

    	result.setCodigoError("00");
    	result.setDescripcionError("");
    	getEJBManager().alertarPagoRedes(getDTSesion(usuario, contrasena), sucursal, ramo, producto, nroCotizacion);
//        result.setPolizaSoa(polizaSoa);
    } catch (BSEExceptionTienda ex2) {
    	AuditorManager.addStatusAudit(Constants.STATUS_ERROR);
		AuditorManager.addExceptionAudit(ex2);
		AuditorManager.LogExceptionAudit(ex2);
    	ex2.printStackTrace();

		//Logger.getLogger(PolizasTienda.class.getName()).log(Level.ERROR, null, ex2);
        logger.log(Level.ERROR, "", ex2);
		result.setCodigoError(String.valueOf(ex2.getCodigoError()));
		result.setDescripcionError(ex2.getDescripcion());
    } catch (Exception ex1) {
    	AuditorManager.addStatusAudit(Constants.STATUS_ERROR);
		AuditorManager.addExceptionAudit(ex1);
		AuditorManager.LogExceptionAudit(ex1);
    	ex1.printStackTrace();

		//Logger.getLogger(PolizasTienda.class.getName()).log(Level.ERROR, null, ex1);
        logger.log(Level.ERROR, "", ex1);
		ex1.printStackTrace();
		result.setCodigoError(String.valueOf(CodigosErrorTienda.excepcion_generica));
		result.setDescripcionError(BSEExceptionTienda.getDescripcionError(CodigosErrorTienda.excepcion_generica));
    }
    return result;
}



}
