package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.logica.AbstractSolver;
import uy.com.bse.utilitario.util.ValuesUtils;

public class RenovacionManualSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultRenovacionManual ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return renovacionManual((ParamRenovacionManual) param);
	}

	private ResultRenovacionManual  renovacionManual(ParamRenovacionManual param) {
		CotOperaciones persistencia = new CotOperaciones();
		if (persistencia.validarRequiereDomicilioBancario(ValuesUtils.toInteger(param.getMedioPago()))) {
			if (param.getCodBanco()==null ||param.getCodBanco().isEmpty()) {
				ResultGenerico resultado = getMyResultInstance();
				resultado.setError(new ServiciosError(30,"Debe especificar el domicilio bancario"));
				return (ResultRenovacionManual) resultado;
			}
		}
		return (ResultRenovacionManual ) checkNull(new CotOperaciones().renovacionManual(param));
	}
}
