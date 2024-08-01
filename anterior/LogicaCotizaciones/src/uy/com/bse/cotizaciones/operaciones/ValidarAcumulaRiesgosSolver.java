package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ValidarAcumulaRiesgosSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultValidarAcumulaRiesgos ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return validarAcumulaRiesgos((ParamValidarAcumulaRiesgos) param);
	}

	private ResultValidarAcumulaRiesgos  validarAcumulaRiesgos(ParamValidarAcumulaRiesgos param) {
		return (ResultValidarAcumulaRiesgos ) checkNull(new CotOperaciones().validarAcumulaRiesgos(param));
	}
}
