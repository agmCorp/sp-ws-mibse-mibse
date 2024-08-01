package uy.com.bse.seguridad.ws;

import javax.naming.NamingException;

import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.seguridad.ParamLogin;
import uy.com.bse.utilitario.seguridad.ParamLoginGroup;
import uy.com.bse.utilitario.seguridad.ParamLogout;
import uy.com.bse.utilitario.seguridad.ParamValidar;
import uy.com.bse.utilitario.seguridad.ResultValidar;
import uy.com.bse.utilitario.servicios.ValidacionesAbstract;

public class ValidacionesSeguridad extends ValidacionesAbstract {

	public boolean validarLogin(ParamLogin param) {

		boolean correcto = false;
		ParamValidar paramValidar = new ParamValidar();
		paramValidar.setUsuario(param.getUsuario());
		paramValidar.setClave(param.getClave());

		ResultValidar result;
		try {
			result = WsSeguridadServicios.getEJBManager().validar(paramValidar);
			if (result != null) {
				correcto = !result.getHayError();
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return correcto;
	}

	public ServiciosError validarLogout(ParamLogout param) {
		String claveDeError = null;
		if (param.getUsuario() == null || param.getUsuario().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarValidar(ParamValidar param) {
		String claveDeError = null;
		if (param.getUsuario() == null || param.getUsuario().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getClave() == null || param.getClave().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarParametrosLogin(ParamLogin param) {
		String claveDeError = null;
		if (param.getUsuario() == null || param.getUsuario().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getClave() == null || param.getClave().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}
	
	public ServiciosError validarLoginGroup(ParamLoginGroup param) {
		String claveDeError = null;
		if (param.getUsuario() == null || param.getUsuario().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getClave() == null || param.getClave().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

}
