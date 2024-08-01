package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.datosbancarios.ParamBorrarDatoBancario;
import uy.com.bse.maestro.personas.datosbancarios.ResultDatoBancario;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class BorrarDatosBancariosSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultDatoBancario();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return borrarDatosBancarios((ParamBorrarDatoBancario) param);
	}

	private ResultDatoBancario borrarDatosBancarios(ParamBorrarDatoBancario param) {
		return (ResultDatoBancario) checkNull(new ServiciosRectorPersist().borrarDatosBancarios(param));
	}
}
