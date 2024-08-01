package uy.com.bse.serviciosEJB;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.utilitario.dato.ResultGenerico;

@Interceptor
public class TransactionInterceptorRector {
	private static Logger log = LogManager.getLogger(TransactionInterceptorRector.class);

	@Resource
	private SessionContext sessionContext;

	@AroundInvoke
	public Object chequeoPreCondiciones(InvocationContext ctx) throws Exception {

		UserUtils.solveUser(ctx);
		ResultGenerico obj = (ResultGenerico) ctx.proceed();

		try {
			if (obj.getHayError().booleanValue() && obj.getError().getCodigo().intValue()>0) {
				log.error("BEGIN TRANSACTION ROLLBACK FOR " + ctx.getMethod().getName());
				sessionContext.setRollbackOnly();
				log.error("END TRANSACTION ROLLBACK FOR " + ctx.getMethod().getName());
			}
		} catch (Exception e) {
			log.error("ERROR EN EL MANEJO DE LA TRANSACCION" + ctx.getMethod().getName());
		}

		return obj;
	}

	public static SeguridadServiciosRemote getEJB() throws NamingException {
		final InitialContext ctx = new InitialContext();
		return (SeguridadServiciosRemote) ctx.lookup(
				"java:global/SeguridadServicios/SeguridadEJB/SeguridadServicios!uy.com.bse.serviciosEJB.SeguridadServiciosRemote");

	}
}
