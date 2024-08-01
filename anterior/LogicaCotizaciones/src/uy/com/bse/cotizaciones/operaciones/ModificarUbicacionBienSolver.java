package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ModificarUbicacionBienSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultModificarUbicacionBien ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return modificarUbicacionBien((ParamModificarUbicacionBien) param);
	}

	private ResultModificarUbicacionBien  modificarUbicacionBien(ParamModificarUbicacionBien param) {
		return (ResultModificarUbicacionBien ) checkNull(new CotOperaciones().modificarUbicacionBien(param));
	}
}
