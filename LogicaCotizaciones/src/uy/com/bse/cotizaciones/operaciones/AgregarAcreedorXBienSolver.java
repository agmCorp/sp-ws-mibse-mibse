package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class AgregarAcreedorXBienSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultAgregarAcreedorXBien ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return agregarAcreedorXBien((ParamAgregarAcreedorXBien) param);
	}

	private ResultAgregarAcreedorXBien  agregarAcreedorXBien(ParamAgregarAcreedorXBien param) {
		return (ResultAgregarAcreedorXBien ) checkNull(new CotOperaciones().agregarAcreedorXBien(param));
	}
}
