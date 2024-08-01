package uy.com.bse.polizas;


import uy.com.bse.polizas.operaciones.ParamEnviarFacturaDigitalEMail;
import uy.com.bse.polizas.operaciones.ResultEnviarFacturaDigitalEMail;
import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;


public class EnviarFacturaDigitalEMailSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultEnviarFacturaDigitalEMail();
	}

	@Override
	protected ResultGenerico procesoLogica(ParamGenerico param) {
		return checkNull(new ServiciosPolizasPersist().enviarFacturaDigitalEMail((ParamEnviarFacturaDigitalEMail) param));
	}
}
