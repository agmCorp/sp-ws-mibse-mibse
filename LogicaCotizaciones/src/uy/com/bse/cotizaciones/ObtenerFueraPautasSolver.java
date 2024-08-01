package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.consultas.ParamFueraPautas;
import uy.com.bse.cotizaciones.consultas.ResultFueraPautas;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ObtenerFueraPautasSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultFueraPautas ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return obtenerFueraPautas((ParamFueraPautas) param);
	}

	private ResultFueraPautas  obtenerFueraPautas(ParamFueraPautas param) {
		return (ResultFueraPautas ) checkNull(new CotizacionesPersist().obtenerFueraPautas(param));
	}
}
