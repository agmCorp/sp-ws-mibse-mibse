package uy.com.bse.genericos.logica;

import uy.bse.rector.servicios.parseo.ParseoConsultaAuditoriaDetalle;
import uy.com.bse.usuarios.operaciones.ParamConsultaAuditoriaDetalle;
import uy.com.bse.usuarios.operaciones.ResultConsultaAuditoriaDetalle;
import uy.com.bse.usuarios.persistencia.ServiciosUsuariosPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class ConsultaAuditoriaDetalleSolver extends XMLAbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultConsultaAuditoriaDetalle();
	}
	
	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new ServiciosUsuariosPersist().consultaAuditoriaDetalle((ParamConsultaAuditoriaDetalle) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ParseoConsultaAuditoriaDetalle unParseo = new ParseoConsultaAuditoriaDetalle(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			return unParseo.parsearConsultaAuditoriaDetalle();
		}
		return null;
	}
}
