package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class QuitarAcreedorXBienSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultQuitarAcreedorXBien ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return quitarAcreedorXBien((ParamQuitarAcreedorXBien) param);
	}

	private ResultQuitarAcreedorXBien  quitarAcreedorXBien(ParamQuitarAcreedorXBien param) {
		return (ResultQuitarAcreedorXBien ) checkNull(new CotOperaciones().quitarAcreedorXBien(param));
	}
}
