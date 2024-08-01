package uy.com.bse.serviciosEJB;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.LogicaRector;
import uy.com.bse.auditoria.ParamAuditarConsulta;
import uy.com.bse.auditoria.ParamAuditarEndoso;
import uy.com.bse.utilitario.dato.ResultGenerico;

@Stateless
public class Auditoria implements AuditoriaLocal {
	private static Logger log = LogManager.getLogger(Auditoria.class);

	public Auditoria() {
		super();
	}


	@Override
	@Interceptors(UserInterceptor.class)
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ResultGenerico auditarEndoso(ParamAuditarEndoso param) {
		log.debug("auditarEndoso start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("auditarEndoso end");
		return result;
	}


	@Override
	@Interceptors(UserInterceptor.class)
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ResultGenerico auditarConsulta(ParamAuditarConsulta param) {
		log.debug("auditarConsulta start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("auditarConsulta end");
		return  result;
	}
}
	
