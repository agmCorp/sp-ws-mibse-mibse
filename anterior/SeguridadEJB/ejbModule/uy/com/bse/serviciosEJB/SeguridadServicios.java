package uy.com.bse.serviciosEJB;

import javax.ejb.Stateless;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.seguridad.LoginGroupSolver;
import uy.com.bse.seguridad.LoginSolver;
import uy.com.bse.seguridad.LogoutSolver;
import uy.com.bse.seguridad.ObtenerUsuarioSolver;
import uy.com.bse.seguridad.ValidarSolver;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.seguridad.ParamLogin;
import uy.com.bse.utilitario.seguridad.ParamLoginGroup;
import uy.com.bse.utilitario.seguridad.ParamLogout;
import uy.com.bse.utilitario.seguridad.ParamValidar;
import uy.com.bse.utilitario.seguridad.ResultLogin;
import uy.com.bse.utilitario.seguridad.ResultLoginGroup;
import uy.com.bse.utilitario.seguridad.ResultLogout;
import uy.com.bse.utilitario.seguridad.ResultObtenerUsuario;
import uy.com.bse.utilitario.seguridad.ResultValidar;

@Stateless
public class SeguridadServicios implements SeguridadServiciosRemote {

	private static Logger log = LogManager.getLogger(SeguridadServicios.class);

	public SeguridadServicios() {
		super();
	}

	public ResultLogin login(ParamLogin param) {
		log.debug("login start: " + param);
		ResultGenerico result = new LoginSolver().solve(param);
		log.debug("login end: ");
		return (ResultLogin) result;

	}

	public ResultLogout logout(ParamLogout param) {
		log.debug("logout start: " + param);
		ResultGenerico result = new LogoutSolver().solve(param);
		log.debug("logout end: ");
		return (ResultLogout) result;
	}

	public ResultValidar validar(ParamValidar param) {
		log.debug("validar start: " + param);
		ResultGenerico result = new ValidarSolver().solve(param);
		log.debug("validar end: ");
		return (ResultValidar) result;
	}

	public ResultObtenerUsuario obtenerUsuario(ParamGenerico param) {
		log.debug("obtenerUsuario start: " + param);
		ResultGenerico result = new ObtenerUsuarioSolver().solve(param);
		log.debug("obtenerUsuario end: ");
		return (ResultObtenerUsuario) result;
	}

	public ResultLoginGroup loginGroup(ParamLoginGroup param) {
		log.debug("login start: " + param);
		ResultGenerico result = new LoginGroupSolver().solve(param);
		log.debug("login end: ");
		return (ResultLoginGroup) result;
	}

	

}
