package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class AltaTextoCotizacionSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultAltaTextoCotizacion ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return altaTextoCotizacion((ParamAltaTextoCotizacion) param);
	}

	private ResultAltaTextoCotizacion  altaTextoCotizacion(ParamAltaTextoCotizacion param) {
		return (ResultAltaTextoCotizacion ) checkNull(new CotOperaciones().altaTextoCotizacion(param));
	}
}
