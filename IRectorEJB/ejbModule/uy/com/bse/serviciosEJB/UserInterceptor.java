package uy.com.bse.serviciosEJB;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import uy.com.bse.utilitario.dato.ResultGenerico;

@Interceptor
public class UserInterceptor {

	@Resource
	private SessionContext sessionContext;

	@AroundInvoke
	public Object chequeoPreCondiciones(InvocationContext ctx) throws Exception {

		UserUtils.solveUser(ctx);
		ResultGenerico obj = (ResultGenerico) ctx.proceed();

		return obj;
	}

}
