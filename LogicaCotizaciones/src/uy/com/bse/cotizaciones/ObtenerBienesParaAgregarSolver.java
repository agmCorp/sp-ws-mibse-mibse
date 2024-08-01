package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.consultas.ParamObtenerBienes;
import uy.com.bse.cotizaciones.consultas.ResultObtenerBienes;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ObtenerBienesParaAgregarSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerBienes ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return obtenerBienesParaAgregar((ParamObtenerBienes) param);
	}

	private ResultObtenerBienes  obtenerBienesParaAgregar(ParamObtenerBienes param) {
		return (ResultObtenerBienes ) checkNull(new CotizacionesPersist().obtenerBienesParaAgregar(param));
	}
}
