package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ModificarAcreedorXBienSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultModificarAcreedorXBien ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return modificarAcreedorXBien((ParamModificarAcreedorXBien) param);
	}

	private ResultModificarAcreedorXBien  modificarAcreedorXBien(ParamModificarAcreedorXBien param) {
		return (ResultModificarAcreedorXBien ) checkNull(new CotOperaciones().modificarAcreedorXBien(param));
	}
}
