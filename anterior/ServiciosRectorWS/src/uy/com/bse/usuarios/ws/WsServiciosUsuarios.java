package uy.com.bse.usuarios.ws;

import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.serviciosEJB.UsuariosLocal;
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
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.ErrorResolver;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;

@WebService(endpointInterface = "uy.com.bse.usuarios.ws.IWsServiciosUsuarios", serviceName = "WsServiciosUsuarios")
public class WsServiciosUsuarios implements IWsServiciosUsuarios {
	private static Logger LOG = LogManager.getLogger(WsServiciosUsuarios.class);

	public ResultAltaUsuario altaUsuario(ParamAltaUsuario param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosUsuarios.class);
		logueo.setMetodo("altaUsuario");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Alcance", param.getAlcance());
		logueo.setParametro("Cedula", param.getCedula());
		logueo.setParametro("Email", param.getMail());
		logueo.setParametro("Nombre", param.getNombre());
		logueo.setParametro("Telefono", param.getTelefono());

		ResultAltaUsuario datos = new ResultAltaUsuario();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesUsuarios validar = new ValidacionesUsuarios();
			ServiciosError error = validar.validarAltaUsuario(param);

			if (error == null) {
				datos = WsServiciosUsuarios.getEJBManager().altaUsuario(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultModificacionUsuario modificacionUsuario(ParamModificacionUsuario param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosUsuarios.class);
		logueo.setMetodo("modificacionUsuario");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Alcance", param.getAlcance());
		logueo.setParametro("Cedula", param.getCedula());
		logueo.setParametro("Email", param.getMail());
		logueo.setParametro("Nombre", param.getNombre());
		logueo.setParametro("Telefono", param.getTelefono());

		ResultModificacionUsuario datos = new ResultModificacionUsuario();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesUsuarios validar = new ValidacionesUsuarios();
			ServiciosError error = validar.validarModificarUsuario(param);

			if (error == null) {
				datos = WsServiciosUsuarios.getEJBManager().modificacionUsuario(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		}catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultBajaUsuario bajaUsuario(ParamBajaUsuario param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosUsuarios.class);
		logueo.setMetodo("bajaUsuario");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cedula", param.getCedula());

		ResultBajaUsuario datos = new ResultBajaUsuario();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesUsuarios validar = new ValidacionesUsuarios();
			ServiciosError error = validar.validarBajaUsuario(param);

			if (error == null) {
				datos = WsServiciosUsuarios.getEJBManager().bajaUsuario(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultConsultaAuditoria consultaAuditoria(ParamConsultaAuditoria param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosUsuarios.class);
		logueo.setMetodo("consultaAuditoria");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Fecha inicial", param.getFechaIni());
		logueo.setParametro("Fecha final", param.getFechaFin());
		logueo.setParametro("Nombre", param.getNombre());
		logueo.setParametro("Apellido", param.getApellido());
		logueo.setParametro("Usuario empleado", param.getUsuarioEmpleado());
		logueo.setParametro("Cedula", param.getCedula());

		ResultConsultaAuditoria datos = new ResultConsultaAuditoria();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesUsuarios validar = new ValidacionesUsuarios();
			ServiciosError error = validar.validarConsultaAuditoria(param);

			if (error == null) {
				datos = WsServiciosUsuarios.getEJBManager().consultaAuditoria(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultConsultaAuditoriaDetalle consultaAuditoriaDetalle(ParamConsultaAuditoriaDetalle param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsServiciosUsuarios.class);
		logueo.setMetodo("consultaAuditoriaDetalle");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		logueo.setParametro("Fecha inicial", param.getFechaIni());
		logueo.setParametro("Fecha final", param.getFechaFin());

		ResultConsultaAuditoriaDetalle datos = new ResultConsultaAuditoriaDetalle();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesUsuarios validar = new ValidacionesUsuarios();
			ServiciosError error = validar.validarConsultaAuditoriaDetalle(param);

			if (error == null) {
				datos = WsServiciosUsuarios.getEJBManager().consultaAuditoriaDetalle(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		}  catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public static UsuariosLocal getEJBManager() throws NamingException {
		final InitialContext ctx = new InitialContext();
		return (UsuariosLocal) ctx.lookup("global/ServiciosRector/IRectorEJB/Usuarios!uy.com.bse.serviciosEJB.UsuariosLocal");
	}

	private String catchException(Logueo logueo, Exception e) {
		String claveError = Values.CLWEBSERVEXCP;
		logueo.setException(Values.EXCEPTION);
		logueo.setError(e.getMessage());
		LOG.error(logueo.getMensaje(),e);
		return claveError;
	}

	private void finallyBlock(String claveError, ResultGenerico datos) {
		if (claveError != null) {
			datos.setHayError(Boolean.TRUE);
			datos.setError(ErrorResolver.getError(claveError));
		}
	}

}
