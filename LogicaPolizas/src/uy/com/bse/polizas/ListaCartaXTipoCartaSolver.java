package uy.com.bse.polizas;

import uy.com.bse.polizas.consultas.ParamCartaXTipoCarta;
import uy.com.bse.polizas.consultas.ResultCartaXTipoCarta;
import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaCartaXTipoCartaSolver extends AbstractSolver{
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaCartaXTipoCarta((ParamCartaXTipoCarta) param);
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultCartaXTipoCarta();
	}
	
	private ResultCartaXTipoCarta listaCartaXTipoCarta(ParamCartaXTipoCarta param)  {
		return (ResultCartaXTipoCarta) checkNull(new ServiciosPolizasPersist().listaCartaXTipoCarta(param));
	}

}
