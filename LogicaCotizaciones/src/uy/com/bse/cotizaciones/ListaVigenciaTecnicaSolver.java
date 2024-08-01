package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.consultas.ParamVigenciaTecnica;
import uy.com.bse.cotizaciones.consultas.ResultVigenciaTecnica;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaVigenciaTecnicaSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultVigenciaTecnica ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaVigenciaTecnica((ParamVigenciaTecnica) param);
	}

	private ResultVigenciaTecnica  listaVigenciaTecnica(ParamVigenciaTecnica param) {
		return (ResultVigenciaTecnica ) checkNull(new CotizacionesPersist().listaVigenciaTecnica(param));
	}
}
