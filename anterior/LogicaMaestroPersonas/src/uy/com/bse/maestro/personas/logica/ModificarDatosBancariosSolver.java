package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.datosbancarios.ParamModificarDatoBancario;
import uy.com.bse.maestro.personas.datosbancarios.ResultDatoBancario;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ModificarDatosBancariosSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultDatoBancario();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return modificarDatosBancarios((ParamModificarDatoBancario) param);
	}

	private ResultDatoBancario modificarDatosBancarios(ParamModificarDatoBancario param) {
		return (ResultDatoBancario) checkNull(new ServiciosRectorPersist().modificarDatosBancarios(param));
	}
}
