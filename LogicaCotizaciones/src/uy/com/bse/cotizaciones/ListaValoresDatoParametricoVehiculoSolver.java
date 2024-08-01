package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamListaValoresDatoVehiculo;
import uy.com.bse.cotizaciones.lovs.ResultListaValoresDato;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaValoresDatoParametricoVehiculoSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaValoresDato ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaValoresDatoParametrico((ParamListaValoresDatoVehiculo) param);
	}

	private ResultListaValoresDato  listaValoresDatoParametrico(ParamListaValoresDatoVehiculo param) {
		return (ResultListaValoresDato ) checkNull(new CotizacionesPersist().listaValoresDatoParametricoVehiculo(param));
	}
}
