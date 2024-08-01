package uy.com.bse.clientes.logica;

import uy.bse.rector.servicios.parseo.ParseoMaestro;
import uy.com.bse.clientes.consultas.ParamPolizasAsociadasClientes;
import uy.com.bse.clientes.consultas.ResultPolizasAsociadasClientes;
import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.consultas.ParamPolizasAsociadas;
import uy.com.bse.maestro.personas.consultas.ResultPolizasAsociadas;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class ObtenerPolizasAsociadasClientesSolver extends XMLAbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultPolizasAsociadasClientes();
	}
	
	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new ServiciosRectorPersist().obtenerPolizasAsociadasClientes( (ParamPolizasAsociadasClientes) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ParseoMaestro unParseo = new ParseoMaestro(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			return unParseo.parsearObtenerPolizasAsociadasClientes();
		}
		return null;
	}
}
