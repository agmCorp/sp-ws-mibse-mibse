package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ModificarTextoCotizacionSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultModificarTextoCotizacion ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return modificarTextoCotizacion((ParamModificarTextoCotizacion) param);
	}

	private ResultModificarTextoCotizacion  modificarTextoCotizacion(ParamModificarTextoCotizacion param) {
		return (ResultModificarTextoCotizacion ) checkNull(new CotOperaciones().modificarTextoCotizacion(param));
	}
}
