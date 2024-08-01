package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.datosbancarios.ParamListaBancosPersona;
import uy.com.bse.maestro.personas.datosbancarios.ResultListaBancosPersona;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaBancosPersonaSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaBancosPersona();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return listaBancosPersona((ParamListaBancosPersona) param);
	}

	private ResultListaBancosPersona listaBancosPersona(ParamListaBancosPersona param) {
		return (ResultListaBancosPersona) checkNull(new ServiciosRectorPersist().listaBancosPersona(param));
	}
}
