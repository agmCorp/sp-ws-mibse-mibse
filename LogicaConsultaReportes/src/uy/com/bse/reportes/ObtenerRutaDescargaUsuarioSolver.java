package uy.com.bse.reportes;

import uy.com.bse.reportes.persistencia.ConsultaReportesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;


public class ObtenerRutaDescargaUsuarioSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerRutaDescargaUsuario ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return checkNull(new ConsultaReportesPersist().obtenerRutaDescargaUsuario((ParamObtenerRutaDescargaUsuario) param));
	}
	
}
