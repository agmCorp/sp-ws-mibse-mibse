package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class BajaUbicacionBienSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultBajaUbicacionBien ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return bajaUbicacionBien((ParamBajaUbicacionBien) param);
	}

	private ResultBajaUbicacionBien  bajaUbicacionBien(ParamBajaUbicacionBien param) {
		return (ResultBajaUbicacionBien ) checkNull(new CotOperaciones().bajaUbicacionBien(param));
	}
}
