package uy.com.bse.polizas;

import uy.com.bse.polizas.consultas.ParamTipoCarta;
import uy.com.bse.polizas.consultas.ResultTipoCarta;
import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaTiposCartaSolver extends AbstractSolver {

	@Override
	public ResultGenerico procesoLogica(ParamGenerico param) {
		return listaTiposCarta((ParamTipoCarta) param) ;
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultTipoCarta();
	}

	private ResultTipoCarta listaTiposCarta(ParamTipoCarta param) {
		return (ResultTipoCarta) checkNull(new ServiciosPolizasPersist().listaTiposCarta(param));
	}

}
