package uy.com.bse.cotizaciones.operaciones;

import uy.bse.rector.servicios.parseo.ParseoCotOperaciones;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class ActualizarDatosSolver extends XMLAbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultActualizarDatos();
	}

	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new CotOperaciones().actualizarDatos((ParamActualizarDatos) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		final ParseoCotOperaciones unParseo = new ParseoCotOperaciones(xmlResult.getXml());
		ResultActualizarDatos resultado =null;
		if (unParseo.generarDoc().booleanValue()) {
			String estado = ((ResultActualizarDatos)xmlResult).getEstadoActualizacion();
			String valor = ((ResultActualizarDatos)xmlResult).getValorDatoActualizado();
			resultado = unParseo.parsearActualizarDatos();
			resultado.setEstadoActualizacion(estado);
			resultado.setValorDatoActualizado(valor);
		}
		return resultado;
	}
}
