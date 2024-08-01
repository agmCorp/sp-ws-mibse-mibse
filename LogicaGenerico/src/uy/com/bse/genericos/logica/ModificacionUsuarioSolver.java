package uy.com.bse.genericos.logica;

import uy.com.bse.usuarios.operaciones.ParamModificacionUsuario;
import uy.com.bse.usuarios.operaciones.ResultModificacionUsuario;
import uy.com.bse.usuarios.persistencia.ServiciosUsuariosPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ModificacionUsuarioSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultModificacionUsuario();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return modificacionUsuario((ParamModificacionUsuario) param);
	}

	private ResultModificacionUsuario modificacionUsuario(ParamModificacionUsuario param) {
		return (ResultModificacionUsuario) checkNull(new ServiciosUsuariosPersist().modificacionUsuario(param));
	}
}
