package uy.com.bse.polizas;

import uy.com.bse.polizas.operaciones.ParamAnularCertificadoEndoso;
import uy.com.bse.polizas.operaciones.ResultAnularCertificadoEndoso;
import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class AnularCertificadoEndosoSolver extends AbstractSolver{
	
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
     return checkNull(new ServiciosPolizasPersist().anularCertificadoEndoso((ParamAnularCertificadoEndoso) param));
	}


	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultAnularCertificadoEndoso();
	}
	

}
