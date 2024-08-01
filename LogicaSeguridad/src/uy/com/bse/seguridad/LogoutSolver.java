package uy.com.bse.seguridad;

import uy.com.bse.seguridad.token.TokenManager;
import uy.com.bse.seguridad.token.TokenRow;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.logica.LogicaSolver;
import uy.com.bse.utilitario.seguridad.ParamLogout;
import uy.com.bse.utilitario.seguridad.ResultLogout;

public final class LogoutSolver implements LogicaSolver {

	@Override
	public ResultGenerico solve(ParamGenerico param) {
		ResultLogout result = null;
		ParamLogout myParam = (ParamLogout)param;
		if (myParam != null) {
			result = new ResultLogout();
			String token = myParam.getClave();
			String userName = myParam.getUsuario();
			if (!logout(token)) {
				result.setHayError(Boolean.TRUE);
				ServiciosError error = new ServiciosError();
				error.setCodigo(2); error.setDescripcion("No se pudo obtener hacer logout");
				result.setError(error);
			}
		}
		return result;
	}

	public static boolean logout(String token) {
		TokenRow row = TokenManager.removeRow(token);
		return (row!=null);
		
	}

}
