package uy.com.bse.guc.ws;

import uy.com.bse.guc.interfaces.ParamAltaUsuario;
import uy.com.bse.guc.interfaces.ParamCambioClave;
import uy.com.bse.guc.interfaces.ParamLogin;
import uy.com.bse.guc.interfaces.ParamOlvidoClave;
import uy.com.bse.guc.interfaces.ParamValidar;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.servicios.ValidacionesAbstract;


public class ValidacionesGUC extends ValidacionesAbstract {

	public ServiciosError validarLogin(ParamLogin param) {
		String claveDeError = null;
		if (param.getUsuario() == null || param.getUsuario().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getContrasena() == null || param.getContrasena().equals("")) {
			claveDeError = Values.VALNULL;
		} 

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarAltaUsuario(ParamAltaUsuario param) {
		String claveDeError = null;
		if (param.getUsuario() == null || param.getUsuario().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getClaveUsuario() == null || param.getClaveUsuario().equals("")) {
			claveDeError = Values.VALNULL;
		} 

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarCambioClave(ParamCambioClave param) {
		String claveDeError = null;
		if (param.getUsuario() == null || param.getUsuario().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getClaveAnterior() == null || param.getClaveAnterior().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getClaveNueva() == null || param.getClaveNueva().equals("")) {
			claveDeError = Values.VALNULL;
		} 

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarOlvidoClave(ParamOlvidoClave param) {
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

}
