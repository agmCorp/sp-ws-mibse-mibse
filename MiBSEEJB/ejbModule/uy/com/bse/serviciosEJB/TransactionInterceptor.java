package uy.com.bse.serviciosEJB;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.utilitario.dato.ResultGenerico;

@Interceptor
public class TransactionInterceptor {
	private static Logger log = LogManager.getLogger(TransactionInterceptor.class);

	@Resource
	private SessionContext sessionContext;

	@AroundInvoke
	public Object chequeoPreCondiciones(InvocationContext ctx) throws Exception {
		ResultGenerico obj = (ResultGenerico) ctx.proceed();
		if (obj.getHayError().booleanValue() && obj.getError().getCodigo().intValue()>0) {
			log.error("BEGIN TRANSACTION ROLLBACK FOR "+ctx.getMethod().getName());
			sessionContext.setRollbackOnly();
			log.error("END TRANSACTION ROLLBACK FOR "+ctx.getMethod().getName());
		}
		return obj;
	}
}
