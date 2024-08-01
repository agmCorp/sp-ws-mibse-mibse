package uy.com.bse.guc.ejb;


import javax.ejb.Stateless;
import javax.interceptor.ExcludeClassInterceptors;
import javax.interceptor.Interceptors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.guc.GUCRemote;
import uy.com.bse.guc.interfaces.ParamAltaUsuario;
import uy.com.bse.guc.interfaces.ParamCambioClave;
import uy.com.bse.guc.interfaces.ParamLogin;
import uy.com.bse.guc.interfaces.ParamOlvidoClave;
import uy.com.bse.guc.interfaces.ParamValidar;
import uy.com.bse.guc.interfaces.ResultAltaUsuario;
import uy.com.bse.guc.interfaces.ResultCambioClave;
import uy.com.bse.guc.interfaces.ResultLogin;
import uy.com.bse.guc.interfaces.ResultOlvidoClave;
import uy.com.bse.guc.interfaces.ResultValidar;
import uy.com.bse.guc.logica.AltaUsuarioSolver;
import uy.com.bse.guc.logica.CambioClaveSolver;
import uy.com.bse.guc.logica.LoginSolver;
import uy.com.bse.guc.logica.OlvidoClaveSolver;
import uy.com.bse.guc.logica.ValidarSolver;
import uy.com.bse.utilitario.dato.ResultGenerico;


/**
 * Session Bean implementation class GUC
 */
@Stateless
@Interceptors(TransactionInterceptor.class)
public class GUC implements GUCRemote {

	private static Logger log = LogManager.getLogger(GUC.class);
  
    public GUC() {
       super();
    }

	@Override
	@ExcludeClassInterceptors
	public ResultLogin login(ParamLogin param) {
		log.debug("login start: " + param);
		ResultGenerico result = new LoginSolver().solve(param);
		log.debug("login end: " + param);
		return (ResultLogin)result; 
	}

	@Override
	public ResultValidar validar(ParamValidar param) {
		log.debug("validar start: " + param);
		ResultGenerico result = new ValidarSolver().solve(param);
		log.debug("validar end: " + param);
		return (ResultValidar)result; 
	}

	@Override
	public ResultAltaUsuario altaUsuario(ParamAltaUsuario param) {
		log.debug("altaUsuario start: " + param);
		ResultGenerico result = new AltaUsuarioSolver().solve(param);
		log.debug("altaUsuario end: " + param);
		return (ResultAltaUsuario)result;
	}

	@Override
	public ResultCambioClave cambioClave(ParamCambioClave param) {
		log.debug("cambioClave start: " + param);
		ResultGenerico result = new CambioClaveSolver().solve(param);
		log.debug("cambioClave end: " + param);
		return (ResultCambioClave)result; 
	}

	@Override
	public ResultOlvidoClave olvidoClave(ParamOlvidoClave param) {
		log.debug("olvidoClave start: " + param);
		ResultGenerico result = new OlvidoClaveSolver().solve(param);
		log.debug("olvidoClave end: " + param);
		return (ResultOlvidoClave)result; 
	}

}
