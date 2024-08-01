package uy.com.bse.reportes;

import uy.com.bse.reportes.persistencia.ConsultaReportesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;


public class ObtenerFacturasDelProductorProximasAVencerEn12DiasSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerFacturasDelProductorProximasAVencerEn12Dias ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return checkNull(new ConsultaReportesPersist().obtenerFacturasDelProductorProximasAVencerEn12Dias((ParamObtenerFacturasDelProductorProximasAVencerEn12Dias) param));
	}

	
}
