package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class AnularCertificadoSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultAnularCertificado ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return anularCertificado((ParamAnularCertificado) param);
	}

	private ResultAnularCertificado  anularCertificado(ParamAnularCertificado param) {
		return (ResultAnularCertificado ) checkNull(new CotOperaciones().anularCertificado(param));
	}
}
