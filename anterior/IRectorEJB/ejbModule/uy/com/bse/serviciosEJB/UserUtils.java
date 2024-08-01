package uy.com.bse.serviciosEJB;

import javax.interceptor.InvocationContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.utilitario.dato.ParamGenerico;

public final class UserUtils {
	
	private static Logger log = LogManager.getLogger(UserUtils.class);
	
	private static SeguridadServiciosRemote getEJB() throws NamingException {
		final InitialContext ctx = new InitialContext();
		return (SeguridadServiciosRemote) ctx.lookup("java:global/SeguridadServicios/SeguridadEJB/SeguridadServicios!uy.com.bse.serviciosEJB.SeguridadServiciosRemote");
	}  
	
	public static void solveUser(InvocationContext ctx)throws Exception  {
		Object[] params = ctx.getParameters();
		ParamGenerico param = (ParamGenerico) params[0];

		if (param.getUsuario().contains("=")) {
			String usuario = param.getUsuario();
			ParamGenerico parametro= new ParamGenerico();
			parametro.setClave(param.getClave());
			parametro.setUsuario(param.getUsuario());
			param.setUsuario(getEJB().obtenerUsuario(parametro).getUsuario());
			log.info("cambio de usuario " + usuario + " por " + param.getUsuario());
		}
	}

}
