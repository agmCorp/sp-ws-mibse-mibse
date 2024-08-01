package uy.com.bse.cotizadorvya.ws;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import uy.com.bse.cotizadorvya.entidades.EnumCodCategoria;
import uy.com.bse.cotizadorvya.operaciones.ParamCalcularPlanes;
import uy.com.bse.cotizadorvya.operaciones.ParamMontosIniciales;
import uy.com.bse.cotizadorvya.operaciones.ParamObtenerCoberturas;
import uy.com.bse.cotizadorvya.operaciones.ParamPorcentajeAnticipo;
import uy.com.bse.cotizadorvya.operaciones.ParamRentaMaxima;
import uy.com.bse.serviciosEJB.SeguridadServiciosRemote;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.seguridad.ResultValidar;
import uy.com.bse.utilitario.servicios.ValidacionesAbstract;
import uy.ibm.responseTimeLogger.RTimeLogger;

public class ValidacionesCotizadorVYA extends ValidacionesAbstract {

	public ServiciosError validarCalcularPlanes(ParamCalcularPlanes param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodMoneda() == null || param.getCodMoneda().equals("") || param.getCodMoneda() <= 0) {
			claveDeError = Values.VALNULL;
		} else if (param.getFechaNacimiento() == null || param.getFechaNacimiento().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCapital() == null || param.getCapital() <= 0) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodCategoria() == null || param.getCodCategoria().equals("") || EnumCodCategoria.getEnumValue(param.getCodCategoria()) == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getMuerteAccidental() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getInvalidez() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getIngresoSeguro() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getIngresoSeguro()) {
			if (param.getIngresoMensual() == null || param.getIngresoMensual() <= 0) {
				claveDeError = Values.VALNULL;
			} else if(param.getRentaIngresoSeguro() == null || param.getRentaIngresoSeguro() <= 0) {
				claveDeError = Values.VALNULL;
			}
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerCoberturas(ParamObtenerCoberturas param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}

		return getErrorsByClave(claveDeError);
	}
	
	public ServiciosError validarMontosIniciales(ParamMontosIniciales param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodMoneda() == null || param.getCodMoneda().equals("") || param.getCodMoneda() <= 0) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerRentaMaxima(ParamRentaMaxima param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getIngresoMensual() == null || param.getIngresoMensual() <= 0) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}
	
	public ServiciosError validarObtenerPorcentajeAnticipo(ParamPorcentajeAnticipo param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodProducto() == null || param.getCodProducto().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodPlan() == null || param.getCodPlan().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodMoneda() == null || param.getCodMoneda().equals("") || param.getCodMoneda() <= 0) {
			claveDeError = Values.VALNULL;
		} else if (param.getEdad() == null || param.getEdad().equals("") || param.getEdad() <= 0) {
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
