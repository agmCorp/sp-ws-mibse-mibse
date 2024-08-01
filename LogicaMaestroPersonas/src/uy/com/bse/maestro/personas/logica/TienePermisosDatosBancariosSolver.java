package uy.com.bse.maestro.personas.logica;

import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.maestro.personas.datosbancarios.ParamTienePermisoDatosBancarios;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultCondicion;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class TienePermisosDatosBancariosSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultCondicion();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return tienePermisoDatosBancarios((ParamTienePermisoDatosBancarios) param);
	}

	private ResultCondicion tienePermisoDatosBancarios(ParamTienePermisoDatosBancarios param) {
		return (ResultCondicion) checkNull(new ServiciosRectorPersist().tienePermisoDatosBancarios(param));
	}
}
