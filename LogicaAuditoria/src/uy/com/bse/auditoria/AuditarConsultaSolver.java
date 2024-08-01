package uy.com.bse.auditoria;

import uy.com.bse.auditoria.persistencia.AuditoriaPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class AuditarConsultaSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultGenerico ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return auditarConsulta((ParamAuditarConsulta) param);
	}

	private ResultGenerico  auditarConsulta(ParamAuditarConsulta param) {
		return (ResultGenerico ) checkNull(new AuditoriaPersist().auditarConsulta(param));
	}
}
