package uy.com.bse.usuarios.ws;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import uy.com.bse.serviciosEJB.SeguridadServiciosRemote;
import uy.com.bse.usuarios.operaciones.ParamAltaUsuario;
import uy.com.bse.usuarios.operaciones.ParamBajaUsuario;
import uy.com.bse.usuarios.operaciones.ParamConsultaAuditoria;
import uy.com.bse.usuarios.operaciones.ParamConsultaAuditoriaDetalle;
import uy.com.bse.usuarios.operaciones.ParamModificacionUsuario;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.seguridad.ResultValidar;
import uy.com.bse.utilitario.servicios.ValidacionesAbstract;
import uy.ibm.responseTimeLogger.RTimeLogger;

public class ValidacionesUsuarios extends ValidacionesAbstract {

	public ServiciosError validarAltaUsuario(ParamAltaUsuario param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getAlcance() == null || param.getAlcance().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCedula() == null || param.getCedula().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getMail() == null || param.getMail().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNombre() == null || param.getNombre().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getTelefono() == null || param.getTelefono().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarModificarUsuario(ParamModificacionUsuario param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getAlcance() == null || param.getAlcance().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCedula() == null || param.getCedula().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getMail() == null || param.getMail().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNombre() == null || param.getNombre().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getTelefono() == null || param.getTelefono().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarBajaUsuario(ParamBajaUsuario param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCedula() == null || param.getCedula().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarConsultaAuditoria(ParamConsultaAuditoria param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarConsultaAuditoriaDetalle(ParamConsultaAuditoriaDetalle param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getFechaIni() == null || param.getFechaIni().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getFechaFin() == null || param.getFechaFin().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public static SeguridadServiciosRemote getEJB() throws NamingException {
		final InitialContext ctx = new InitialContext();
		return (SeguridadServiciosRemote) ctx.lookup("java:global/SeguridadServicios/SeguridadEJB/SeguridadServicios!uy.com.bse.serviciosEJB.SeguridadServiciosRemote");

	}

	protected boolean validarLogin(String usuario, String clave) {
		RTimeLogger.addCustomData("user", usuario);
		boolean salida = false;
		uy.com.bse.utilitario.seguridad.ParamValidar paramValidar = new uy.com.bse.utilitario.seguridad.ParamValidar();
		paramValidar.setClave(clave);
		paramValidar.setUsuario(usuario);
		uy.com.bse.utilitario.seguridad.ResultValidar result;
		try {
			result = (ResultValidar) getEJB().validar(paramValidar);
			salida = ((result != null) && (!result.getHayError()));
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return salida;
	}
}
