package uy.com.bse.clientes.logica;

import uy.bse.rector.servicios.parseo.ParseoMaestro;
import uy.com.bse.clientes.consultas.ParamObtenerDetalleConsultaClientes;
import uy.com.bse.clientes.consultas.ResultObtenerDetalleConsultaClientes;
import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class ObtenerDetalleConsultaClientesSolver extends XMLAbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerDetalleConsultaClientes();
	}
	
	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new ServiciosRectorPersist().obtenerDetalleConsultaClientes((ParamObtenerDetalleConsultaClientes) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ParseoMaestro unParseo = new ParseoMaestro(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			return unParseo.parsearObtenerDetalleConsultaClientes();
		}
		return null;
	}
}
