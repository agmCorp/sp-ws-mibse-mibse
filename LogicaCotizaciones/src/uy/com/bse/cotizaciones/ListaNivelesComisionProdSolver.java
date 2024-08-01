package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamNivelesComisionProd;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultCodiguera;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaNivelesComisionProdSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultCodiguera();
	}

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return listaNivelesComisionProd((ParamNivelesComisionProd) param);
	}

	private ResultCodiguera listaNivelesComisionProd(ParamNivelesComisionProd param) {
		return (ResultCodiguera) checkNull(new CotizacionesPersist().listaNivelesComisionProd(param));
	}
}
