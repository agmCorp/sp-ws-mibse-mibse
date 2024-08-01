package uy.com.bse.cotizaciones;

import uy.com.bse.cotizaciones.lovs.ParamAcreedorObjetos;
import uy.com.bse.cotizaciones.lovs.ResultAcreedorObjetos;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaAcreedorObjetosSolver extends AbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultAcreedorObjetos ();
	}
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaAcreedorObjetos((ParamAcreedorObjetos) param);
	}

	private ResultAcreedorObjetos  listaAcreedorObjetos(ParamAcreedorObjetos param) {
		return (ResultAcreedorObjetos ) checkNull(new CotizacionesPersist().listaAcreedorObjetos(param));
	}
}
