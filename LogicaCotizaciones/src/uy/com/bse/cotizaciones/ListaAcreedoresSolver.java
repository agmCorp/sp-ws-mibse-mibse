package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamAcreedor;
import uy.com.bse.cotizaciones.lovs.ResultAcreedor;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaAcreedoresSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultAcreedor ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaAcreedores((ParamAcreedor) param);
	}

	private ResultAcreedor  listaAcreedores(ParamAcreedor param) {
		return (ResultAcreedor ) checkNull(new CotizacionesPersist().listaAcreedores(param));
	}
}
