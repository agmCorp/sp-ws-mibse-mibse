package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamListaValoresDato;
import uy.com.bse.cotizaciones.lovs.ResultListaValoresDato;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaValoresDatoParametricoSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaValoresDato ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaValoresDatoParametrico((ParamListaValoresDato) param);
	}

	private ResultListaValoresDato  listaValoresDatoParametrico(ParamListaValoresDato param) {
		return (ResultListaValoresDato ) checkNull(new CotizacionesPersist().listaValoresDatoParametrico(param));
	}
}
