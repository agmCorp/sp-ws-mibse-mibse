package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.consultas.ParamParentesco;
import uy.com.bse.cotizaciones.consultas.ResultParentesco;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaParentescoSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultParentesco ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaParentesco((ParamParentesco) param);
	}

	private ResultParentesco  listaParentesco(ParamParentesco param) {
		return (ResultParentesco ) checkNull(new CotizacionesPersist().listaParentesco(param));
	}
}
