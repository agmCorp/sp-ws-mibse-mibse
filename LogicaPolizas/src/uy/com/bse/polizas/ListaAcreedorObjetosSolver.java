package uy.com.bse.polizas;

import uy.com.bse.cotizaciones.lovs.ResultAcreedorObjetos;
import uy.com.bse.polizas.consultas.ParamObjetosAcreedor;
import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class ListaAcreedorObjetosSolver extends AbstractSolver{
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return listaAcreedorObjetos((ParamObjetosAcreedor) param);
	}

	private ResultAcreedorObjetos listaAcreedorObjetos(ParamObjetosAcreedor myParam) {
			return (ResultAcreedorObjetos) checkNull(new ServiciosPolizasPersist().listaAcreedorObjetos(myParam));
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultAcreedorObjetos();
	}

}
