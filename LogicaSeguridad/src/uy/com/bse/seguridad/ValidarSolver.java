package uy.com.bse.seguridad;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.seguridad.token.TokenManager;
import uy.com.bse.seguridad.token.TokenRow;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.logica.LogicaSolver;
import uy.com.bse.utilitario.seguridad.ParamValidar;
import uy.com.bse.utilitario.seguridad.ResultValidar;

public final class ValidarSolver implements LogicaSolver {

	private static final Logger LOG = LogManager.getLogger(ValidarSolver.class);
	@Override
	public ResultGenerico solve(ParamGenerico param) {
		ResultValidar result = null;
		ParamValidar myParam = (ParamValidar)param;
		if (myParam != null) {
			result = new ResultValidar();
			String token = myParam.getClave();
			String userName = myParam.getUsuario();
			if (!validate(token, userName)) {
				result.setHayError(Boolean.TRUE);
				ServiciosError error = new ServiciosError();
				error.setCodigo(3); 
				error.setDescripcion("token y/o usuario no v\u00e1lidos");
				result.setError(error);
			}
		}
		
		return result;
	}

	public static boolean validate(String token, String userName) {
		boolean salida = false;
		if ((token != null) && (userName!=null)) {
			//Autentico el token, verificando que sea valido
			TokenRow row = TokenManager.getRow(token);
			if (row != null) {
				//Verifico que el usuario devuelto coincide con el proporcionado
				salida = row.validateUserName(userName);
			}
		}
		return salida;
	}
}
