package uy.com.bse.autodata.ws;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import uy.com.bse.autodata.operaciones.ParamConsultaAutodata;
import uy.com.bse.autodata.operaciones.ParamObtenerDatosAuto;
import uy.com.bse.autodata.operaciones.ParamObtenerModelosAutodata;
import uy.com.bse.serviciosEJB.SeguridadServiciosRemote;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.seguridad.ResultValidar;
import uy.com.bse.utilitario.servicios.ValidacionesAbstract;
import uy.ibm.responseTimeLogger.RTimeLogger;

public class ValidacionesAutodata extends ValidacionesAbstract {

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

	public ServiciosError validarConsultaAutodata(ParamConsultaAutodata param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodMarca() == null || param.getCodMarca().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodAno() == null || param.getCodAno().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodModelo() == null || param.getCodModelo().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerDatosAuto(ParamObtenerDatosAuto param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodAutoData() == null || param.getCodAutoData().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerModelosAutodata(ParamObtenerModelosAutodata param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}
}
