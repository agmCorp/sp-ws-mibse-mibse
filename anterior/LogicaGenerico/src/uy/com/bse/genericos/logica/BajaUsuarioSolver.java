package uy.com.bse.genericos.logica;

import uy.com.bse.usuarios.operaciones.ParamBajaUsuario;
import uy.com.bse.usuarios.operaciones.ResultBajaUsuario;
import uy.com.bse.usuarios.persistencia.ServiciosUsuariosPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class BajaUsuarioSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultBajaUsuario();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return bajaUsuario((ParamBajaUsuario) param);
	}

	private ResultBajaUsuario bajaUsuario(ParamBajaUsuario param) {
		return (ResultBajaUsuario) checkNull(new ServiciosUsuariosPersist().bajaUsuario(param));
	}
}
