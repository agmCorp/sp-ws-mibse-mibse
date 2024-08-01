package uy.com.bse.serviciosEJB;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.LogicaRector;
import uy.com.bse.usuarios.operaciones.ParamAltaUsuario;
import uy.com.bse.usuarios.operaciones.ParamBajaUsuario;
import uy.com.bse.usuarios.operaciones.ParamConsultaAuditoria;
import uy.com.bse.usuarios.operaciones.ParamConsultaAuditoriaDetalle;
import uy.com.bse.usuarios.operaciones.ParamModificacionUsuario;
import uy.com.bse.usuarios.operaciones.ResultAltaUsuario;
import uy.com.bse.usuarios.operaciones.ResultBajaUsuario;
import uy.com.bse.usuarios.operaciones.ResultConsultaAuditoria;
import uy.com.bse.usuarios.operaciones.ResultConsultaAuditoriaDetalle;
import uy.com.bse.usuarios.operaciones.ResultModificacionUsuario;
import uy.com.bse.utilitario.dato.ResultGenerico;

@Stateless
public class Usuarios implements UsuariosLocal {

	private static Logger log = LogManager.getLogger(Usuarios.class);

	public Usuarios() {
		super();
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultAltaUsuario altaUsuario(ParamAltaUsuario param) {
		log.debug("altaUsuario start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("altaUsuario end: " + param);
		return (ResultAltaUsuario) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultModificacionUsuario modificacionUsuario(ParamModificacionUsuario param) {
		log.debug("modificacionUsuario start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("modificacionUsuario end: " + param);
		return (ResultModificacionUsuario) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultBajaUsuario bajaUsuario(ParamBajaUsuario param) {
		log.debug("bajaUsuario start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("altaUsuario end: " + param);
		return (ResultBajaUsuario) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultConsultaAuditoria consultaAuditoria(ParamConsultaAuditoria param) {
		log.debug("consultaAuditoria start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("consultaAuditoria end: " + param);
		return (ResultConsultaAuditoria) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultConsultaAuditoriaDetalle consultaAuditoriaDetalle(ParamConsultaAuditoriaDetalle param) {
		log.debug("consultaAuditoriaDetalle start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("consultaAuditoriaDetalle end: " + param);
		return (ResultConsultaAuditoriaDetalle) result;
	}
}
