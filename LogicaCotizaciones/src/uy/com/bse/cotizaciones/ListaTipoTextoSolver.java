package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamTipoTexto;
import uy.com.bse.cotizaciones.lovs.ResultTipoTexto;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaTipoTextoSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultTipoTexto ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaTipoTexto((ParamTipoTexto) param);
	}

	private ResultTipoTexto  listaTipoTexto(ParamTipoTexto param) {
		return (ResultTipoTexto ) checkNull(new CotizacionesPersist().listaTipoTexto(param));
	}
}
