package uy.com.bse.auditoria;

import uy.com.bse.auditoria.persistencia.AuditoriaPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class AuditarEndosoSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultGenerico ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return auditarEndoso((ParamAuditarEndoso) param);
	}

	private ResultGenerico  auditarEndoso(ParamAuditarEndoso param) {
		return (ResultGenerico ) checkNull(new AuditoriaPersist().auditarEndoso(param));
	}
}
