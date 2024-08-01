package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class GuardarCoberturasSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultGuardarCoberturas ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return guardarCoberturas((ParamGuardarCoberturas) param);
	}

	private ResultGuardarCoberturas  guardarCoberturas(ParamGuardarCoberturas param) {
		return (ResultGuardarCoberturas ) checkNull(new CotOperaciones().guardarCoberturas(param));
	}
}
