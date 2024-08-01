package uy.com.bse.cotizaciones.vehiculo;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import uy.com.bse.cotizaciones.lovs.ParamListaValoresDatoVehiculo;
import uy.com.bse.cotizaciones.operaciones.ParamActualizarCertificadoCeroVehiculo;
import uy.com.bse.cotizaciones.operaciones.ParamActualizarPromocionVehiculo;
import uy.com.bse.cotizaciones.operaciones.ParamCalcularCuotasVehiculo;
import uy.com.bse.cotizaciones.operaciones.ParamNuevaCotizacionVehiculo;
import uy.com.bse.cotizaciones.operaciones.ParamValidarDatosVehiculo;
import uy.com.bse.serviciosEJB.SeguridadServiciosRemote;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.seguridad.ResultValidar;
import uy.com.bse.utilitario.servicios.ValidacionesAbstract;
import uy.ibm.responseTimeLogger.RTimeLogger;

public class ValidacionesCotizadorVehiculo extends ValidacionesAbstract {



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


	public ServiciosError validarValidarDatosVehiculo(ParamValidarDatosVehiculo param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getLista() == null) {
			claveDeError = Values.VALNULL;
		} 
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarCalcularCuotasVehiculo(ParamCalcularCuotasVehiculo param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarNuevaCotizacionVehiculo(ParamNuevaCotizacionVehiculo param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarActualizarPromocionVehiculo(ParamActualizarPromocionVehiculo param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodPromocion() == null) {
			claveDeError = Values.VALNULL;
		}
		
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarActualizarCertificadoCeroVehiculo(ParamActualizarCertificadoCeroVehiculo param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		}
		else if (param.getCodMoneda() == null) {
			claveDeError = Values.VALNULL;
		}
		else if (param.getCodPromocion() == null) {
			claveDeError = Values.VALNULL;
		}
		else if (param.getFechaDesde() == null) {
			claveDeError = Values.VALNULL;
		}
		else if (param.getFechaHasta()== null) {
			claveDeError = Values.VALNULL;
		}
		else if (param.getFormaPago() == null) {
			claveDeError = Values.VALNULL;
		}
		else if (param.getTipoCalculo() == null) {
			claveDeError = Values.VALNULL;
		}
		
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaValoresDatoParametricoVehiculo(ParamListaValoresDatoVehiculo param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}else if (param.getCantFiltros() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodDato() == null) {
			claveDeError = Values.VALNULL;
		}else if ("1".equals(param.getCantFiltros()) && param.getPrimerFiltro()==null) {
			claveDeError = Values.VALNULL;
		}else if ("2".equals(param.getCantFiltros()) && (param.getPrimerFiltro()==null||param.getSegundoFiltro()==null)) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

}
