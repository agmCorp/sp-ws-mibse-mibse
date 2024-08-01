package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class CalcularValidarVigenciaSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultCalcularValidarVigencia ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return calcularValidarVigencia((ParamCalcularValidarVigencia) param);
	}

	private ResultCalcularValidarVigencia  calcularValidarVigencia(ParamCalcularValidarVigencia param) {
		return (ResultCalcularValidarVigencia ) checkNull(new CotOperaciones().calcularValidarVigencia(param));
	}
}
