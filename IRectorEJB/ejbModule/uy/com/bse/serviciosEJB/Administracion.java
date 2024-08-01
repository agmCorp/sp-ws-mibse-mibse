package uy.com.bse.serviciosEJB;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.LogicaRector;
import uy.com.bse.administracion.ParamAgregarEdoc;
import uy.com.bse.administracion.ParamObtenerDatosCorredor;
import uy.com.bse.administracion.ResultAgregarEdoc;
import uy.com.bse.administracion.ResultObtenerDatosCorredor;
import uy.com.bse.utilitario.dato.ResultGenerico;

@Stateless
public class Administracion implements AdministracionLocal {
	private static Logger log = LogManager.getLogger(Administracion.class);

	public Administracion() {
		super();
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultAgregarEdoc agregarEvolante(ParamAgregarEdoc param) {
		
		log.debug("agregarEvolante start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("agregarEvolante end");
		return (ResultAgregarEdoc) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerDatosCorredor obtenerDatosCorredor(ParamObtenerDatosCorredor param) {
		
		log.debug("obtenerDatosCorredor start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDatosCorredor end");
		return (ResultObtenerDatosCorredor) result;
	}

	


	
}
	
