package uy.com.bse.genericos.logica;

import uy.bse.rector.servicios.parseo.ParseoConsultaAuditoria;
import uy.com.bse.usuarios.operaciones.ParamConsultaAuditoria;
import uy.com.bse.usuarios.operaciones.ResultConsultaAuditoria;
import uy.com.bse.usuarios.persistencia.ServiciosUsuariosPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class ConsultaAuditoriaSolver extends XMLAbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultConsultaAuditoria();
	}
	
	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new ServiciosUsuariosPersist().consultaAuditoria( (ParamConsultaAuditoria) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ParseoConsultaAuditoria unParseo = new ParseoConsultaAuditoria(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			return unParseo.parsearConsultaAuditoria();
		}
		return null;
	}
}
