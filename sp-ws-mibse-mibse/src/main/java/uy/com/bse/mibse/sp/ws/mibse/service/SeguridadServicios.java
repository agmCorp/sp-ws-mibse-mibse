package uy.com.bse.mibse.sp.ws.mibse.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.seguridad.ResultLogin;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.seguridad.ResultValidar;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.seguridad.ParamLogin;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.seguridad.ParamValidar;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultGenerico;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.logica.LoginSolver;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.logica.ValidarSolver;

@Service
public class SeguridadServicios {

	private static Logger log = LogManager.getLogger(SeguridadServicios.class);

	@Autowired
	LoginSolver loginSolver;

	@Autowired
	ValidarSolver validarSolver;

	public SeguridadServicios() {
		super();
	}

	public ResultLogin login(ParamLogin param) {
		log.debug("login start: " + param);
		ResultGenerico result = loginSolver.solve(param);
		log.debug("login end: ");
		return (ResultLogin) result;

	}

	public ResultValidar validar(ParamValidar param) {
		log.debug("validar start: " + param);
		ResultGenerico result = validarSolver.solve(param);
		log.debug("validar end: ");
		return (ResultValidar) result;
	}
	

}
