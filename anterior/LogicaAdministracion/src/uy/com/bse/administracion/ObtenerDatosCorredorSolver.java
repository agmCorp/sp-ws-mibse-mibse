package uy.com.bse.administracion;

import uy.com.bse.administracion.persistencia.AdministracionPersist;
import uy.com.bse.reportes.ParamObtenerClientesCorredores;
import uy.com.bse.reportes.ResultObtenerClientesCorredores;
import uy.com.bse.reportes.persistencia.ConsultaReportesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;


public class ObtenerDatosCorredorSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerClientesCorredores ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return checkNull(new AdministracionPersist().obtenerDatosCorredor((ParamObtenerDatosCorredor) param));
	}

	
}
