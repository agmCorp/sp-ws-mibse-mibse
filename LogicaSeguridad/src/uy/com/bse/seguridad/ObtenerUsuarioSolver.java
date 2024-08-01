package uy.com.bse.seguridad;

import uy.com.bse.seguridad.token.TokenManager;
import uy.com.bse.seguridad.token.TokenRow;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.logica.LogicaSolver;
import uy.com.bse.utilitario.seguridad.ResultObtenerUsuario;

public final class ObtenerUsuarioSolver implements LogicaSolver {

	@Override
	public ResultGenerico solve(ParamGenerico param) {
		ResultObtenerUsuario result = null;
		if (param != null) {
			result = new ResultObtenerUsuario();
			String token = param.getClave();
			String usuario = obtenerUsuario(token);
			if (usuario==null) {
				result.setHayError(Boolean.TRUE);
				ServiciosError error = new ServiciosError();
				error.setCodigo(4); 
				error.setDescripcion("token no v\u00e1lido");
				result.setError(error);
				result.setUsuario(usuario);
			}else{
				result.setHayError(Boolean.FALSE);
				result.setUsuario(usuario);
			}
		}
		
		return result;
	}

	public static String obtenerUsuario(String token) {
		String salida = null;
		if ((token != null)) {
			TokenRow row = TokenManager.getRow(token);
			if (row != null) {
				salida = row.getUserName();
			}
		}
		return salida;
	}
}
