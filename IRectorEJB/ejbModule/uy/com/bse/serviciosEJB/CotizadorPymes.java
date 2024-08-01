package uy.com.bse.serviciosEJB;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.LogicaRector;
import uy.com.bse.cotizadores.pymes.ParamCalcularPymes;
import uy.com.bse.cotizadores.pymes.ParamNuevaCotizacionPymes;
import uy.com.bse.cotizadores.pymes.ResultCalcularPymes;
import uy.com.bse.cotizadores.pymes.ResultNuevaCotizacionPymes;
import uy.com.bse.utilitario.dato.ResultGenerico;

@Stateless
@Interceptors(TransactionInterceptorRector.class)
public class CotizadorPymes implements CotizadorPymesLocal {
	
	private static Logger log = LogManager.getLogger(CotOperaciones.class);	
	
	public CotizadorPymes() {
		super();
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultNuevaCotizacionPymes nuevaCotizacionPymes(ParamNuevaCotizacionPymes param) {
		log.debug("nuevaCotizacionPymes start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("nuevaCotizacionPymes end");
		return (ResultNuevaCotizacionPymes)result; 
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultCalcularPymes calcularPymes(ParamCalcularPymes param) {
		log.debug("calcularPymes start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("calcularPymes end");
		return (ResultCalcularPymes)result; 
	}

	
}
