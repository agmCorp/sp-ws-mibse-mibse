package uy.com.bse.genericos.logica;

import uy.com.bse.usuarios.operaciones.ParamAltaUsuario;
import uy.com.bse.usuarios.operaciones.ResultAltaUsuario;
import uy.com.bse.usuarios.persistencia.ServiciosUsuariosPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class AltaUsuarioSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultAltaUsuario();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return altaUsuario((ParamAltaUsuario) param);
	}

	private ResultAltaUsuario altaUsuario(ParamAltaUsuario param) {
		return (ResultAltaUsuario) checkNull(new ServiciosUsuariosPersist().altaUsuario(param));
	}
}
