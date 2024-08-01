package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ModificarOpcionesFacturacionSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultModificarOpcionesFacturacion();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return modificarOpcionesFacturacion((ParamModificarOpcionesFacturacion) param);
	}

	private ResultModificarOpcionesFacturacion  modificarOpcionesFacturacion(ParamModificarOpcionesFacturacion param) {
		return (ResultModificarOpcionesFacturacion) checkNull(new CotOperaciones().modificarOpcionesFacturacion(param));
	}
}
