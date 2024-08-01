package uy.com.bse.guc.logica;

import uy.com.bse.guc.interfaces.ParamCambioClave;
import uy.com.bse.guc.interfaces.ParamValidar;
import uy.com.bse.guc.interfaces.ResultCambioClave;
import uy.com.bse.guc.interfaces.ResultClaveUsada;
import uy.com.bse.guc.interfaces.ResultValidar;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.logica.AbstractSolver;

public final class CambioClaveSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultCambioClave();
	}

	@Override
	protected ResultGenerico procesoLogica(ParamGenerico param) {
		ResultCambioClave resultCambio = null;
		ValidarSolver validarSolver = new ValidarSolver();
		ParamCambioClave pcambioClave = (ParamCambioClave) param;
		ParamValidar pvalidar = new ParamValidar();
		pvalidar.setClaveUsuario(pcambioClave.getClaveAnterior());
		pvalidar.setUsuario(param.getUsuario());
		pvalidar.setClave(param.getClave());
		ResultValidar result = (ResultValidar) validarSolver.solve(pvalidar);
		if (!result.getHayError().booleanValue() && result.getUser() != null) {
			if (pcambioClave.getClaveAnterior().equals(pcambioClave.getClaveNueva())) {
				resultCambio = errorClaveRepetida();
			} else {
				if (NoCoincidenciaClavesAnteriores(pcambioClave)) {
					resultCambio = (ResultCambioClave) checkNull(DataProviderUtil.getDataProvider().cambioClave(pcambioClave));
				} else {
					resultCambio = errorClaveRepetida();
				}
			}
		} else {
			resultCambio = errorClaveAntiguaNoCoincide();
		}
		return checkNull(resultCambio);
	}

	private boolean NoCoincidenciaClavesAnteriores(ParamCambioClave pcambioClave) {
		ResultClaveUsada resultado = DataProviderUtil.getDataProvider().verificarClaveUsadaAnteriormente(pcambioClave);
		if (!resultado.getHayError().booleanValue() && resultado.getUsada().booleanValue() == false) {
			return true;
		} else {
			return false;
		}

	}

	private ResultCambioClave errorClaveRepetida() {
		ResultCambioClave resultCambio;
		resultCambio = new ResultCambioClave();
		resultCambio.setHayError(Boolean.TRUE);
		resultCambio.setError(new ServiciosError(600, "La contrase\u00f1a ya fue utilizada"));
		return resultCambio;
	}

	private ResultCambioClave errorClaveAntiguaNoCoincide() {
		ResultCambioClave resultCambio;
		resultCambio = new ResultCambioClave();
		resultCambio.setHayError(Boolean.TRUE);
		resultCambio.setError(new ServiciosError(600, "La contrase\u00f1a actual no es correcta"));
		return resultCambio;
	}

}
