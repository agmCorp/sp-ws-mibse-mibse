package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class GuardarPlanesSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultGuardarPlanes ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return guardarPlanes((ParamGuardarPlanes) param);
	}

	private ResultGuardarPlanes  guardarPlanes(ParamGuardarPlanes param) {
		return (ResultGuardarPlanes ) checkNull(new CotOperaciones().guardarPlanes(param));
	}
}
