package uy.com.bse.maestro.personas.logica;

import uy.bse.rector.servicios.parseo.ParseoMaestro;
import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.consultas.ParamObtenerPersonasClientes;
import uy.com.bse.maestro.personas.consultas.ResultObtenerPersonasClientes;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class ObtenerPersonasClientesSolver extends XMLAbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerPersonasClientes();
	}
	
	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new ServiciosRectorPersist().obtenerPersonasClientes( (ParamObtenerPersonasClientes) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ParseoMaestro unParseo = new ParseoMaestro(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			return unParseo.parsearObtenerPersonasClientes();
		}
		return null;
	}
}
