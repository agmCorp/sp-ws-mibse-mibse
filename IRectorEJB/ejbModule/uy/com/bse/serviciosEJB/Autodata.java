package uy.com.bse.serviciosEJB;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.LogicaRector;
import uy.com.bse.autodata.operaciones.ParamConsultaAutodata;
import uy.com.bse.autodata.operaciones.ParamObtenerDatosAuto;
import uy.com.bse.autodata.operaciones.ParamObtenerModelosAutodata;
import uy.com.bse.autodata.operaciones.ResultConsultaAutodata;
import uy.com.bse.autodata.operaciones.ResultObtenerDatosAuto;
import uy.com.bse.autodata.operaciones.ResultObtenerModelosAutodata;
import uy.com.bse.utilitario.dato.ResultGenerico;

@Stateless
public class Autodata implements AutodataLocal {

	private static Logger log = LogManager.getLogger(Consultas.class);

	public Autodata() {
		super();
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultConsultaAutodata consultaAutodata(ParamConsultaAutodata param) {
		log.debug("consultaAutodata start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("consultaAutodata end");
		return (ResultConsultaAutodata) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerDatosAuto obtenerDatosAuto(ParamObtenerDatosAuto param) {
		log.debug("obtenerDatosAuto start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDatosAuto end");
		return (ResultObtenerDatosAuto) result;
	}
	
	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerModelosAutodata obtenerModelosAutodata(ParamObtenerModelosAutodata param){
		log.debug("obtenerModelosAutodata start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerModelosAutodata end");
		return (ResultObtenerModelosAutodata) result;
	}

}
