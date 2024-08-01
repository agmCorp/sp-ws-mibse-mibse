package uy.com.bse.polizas;

import uy.com.bse.polizas.consultas.ParamTienePermisoProducto;
import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultCondicion;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.logica.AbstractSolver;

public class TienePermisoProductoSolver extends AbstractSolver{
	
	@Override
	public ResultGenerico procesoLogica(ParamGenerico param){
		return tienePermiso( (ParamTienePermisoProducto) param);
	}

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultCondicion();
	}

	private ResultGenerico tienePermiso(ParamTienePermisoProducto param) {
		return (ResultGenerico) checkNull(new ServiciosPolizasPersist().tienePermisoProducto(param));
	}
}
