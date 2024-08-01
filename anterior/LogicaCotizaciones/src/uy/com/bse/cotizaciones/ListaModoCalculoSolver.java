package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamModoCalculo;
import uy.com.bse.cotizaciones.lovs.ResultListaModoCalculo;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaModoCalculoSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaModoCalculo ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaModoCalculo((ParamModoCalculo) param);
	}

	private ResultListaModoCalculo  listaModoCalculo(ParamModoCalculo param) {
		return (ResultListaModoCalculo ) checkNull(new CotizacionesPersist().listaModoCalculo(param));
	}
}
