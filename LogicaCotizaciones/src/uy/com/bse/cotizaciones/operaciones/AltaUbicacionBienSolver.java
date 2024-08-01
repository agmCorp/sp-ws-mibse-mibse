package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class AltaUbicacionBienSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultAltaUbicacionBien ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return altaUbicacionBien((ParamAltaUbicacionBien) param);
	}

	private ResultAltaUbicacionBien  altaUbicacionBien(ParamAltaUbicacionBien param) {
		return (ResultAltaUbicacionBien ) checkNull(new CotOperaciones().altaUbicacionBien(param));
	}
}
