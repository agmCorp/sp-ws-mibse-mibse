package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.consultas.ParamObtenerTextoClausula;
import uy.com.bse.cotizaciones.consultas.ResultObtenerTextoClausula;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ObtenerTextoClausulaSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerTextoClausula ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return obtenerTextoClausula((ParamObtenerTextoClausula) param);
	}

	private ResultObtenerTextoClausula  obtenerTextoClausula(ParamObtenerTextoClausula param) {
		return (ResultObtenerTextoClausula ) checkNull(new CotizacionesPersist().obtenerTextoClausula(param));
	}
}
