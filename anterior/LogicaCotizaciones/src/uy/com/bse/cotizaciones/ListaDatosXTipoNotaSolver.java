package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamDatosXTipoNota;
import uy.com.bse.cotizaciones.lovs.ResultDatosXTipoNota;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public final class ListaDatosXTipoNotaSolver extends AbstractSolver {
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
     return listaDatosXTipoNota((ParamDatosXTipoNota) param);
	}

	private ResultDatosXTipoNota listaDatosXTipoNota(ParamDatosXTipoNota param) {
		return (ResultDatosXTipoNota) checkNull(new CotizacionesPersist().listaDatosXTipoNota(param));
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultDatosXTipoNota();
	}


}