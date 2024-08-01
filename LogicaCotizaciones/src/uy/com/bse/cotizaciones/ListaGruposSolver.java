package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamListaGrupos;
import uy.com.bse.cotizaciones.lovs.ResultListaGrupos;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaGruposSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultListaGrupos ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaGrupos((ParamListaGrupos) param);
	}

	private ResultListaGrupos  listaGrupos(ParamListaGrupos param) {
		return (ResultListaGrupos ) checkNull(new CotizacionesPersist().listaGrupos(param));
	}
}
