package uy.com.bse.consultas.ws;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import uy.com.bse.consultas.operaciones.ParamActualizarPolizaFlotante;
import uy.com.bse.consultas.operaciones.ParamObtenerCabezalNomina;
import uy.com.bse.consultas.operaciones.ParamObtenerCoberturasIntegranteNomina;
import uy.com.bse.consultas.operaciones.ParamObtenerDetallePolizaFlotante;
import uy.com.bse.consultas.operaciones.ParamObtenerIntegrantesNomina;
import uy.com.bse.consultas.operaciones.ParamObtenerIntegrantesNominaAccidenteTrabajo;
import uy.com.bse.consultas.operaciones.ParamObtenerMovimientosComision;
import uy.com.bse.consultas.operaciones.ParamObtenerNominasVida;
import uy.com.bse.consultas.operaciones.ParamObtenerPolizasFlotantes;
import uy.com.bse.consultas.operaciones.ParamTipoIntegranteNomina;
import uy.com.bse.serviciosEJB.SeguridadServiciosRemote;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.seguridad.ResultValidar;
import uy.com.bse.utilitario.servicios.ValidacionesAbstract;
import uy.com.bse.utilitario.validacion.ValidadorMesAnio;
import uy.ibm.responseTimeLogger.RTimeLogger;

public class ValidacionesConsultas extends ValidacionesAbstract {

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

	public ServiciosError validarObtenerCabezalNomina(ParamObtenerCabezalNomina param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumNomina() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumIntegrante() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerCoberturasIntegranteNomina(ParamObtenerCoberturasIntegranteNomina param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumNomina() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumIntegrante() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaTipoIntegranteNomina(ParamTipoIntegranteNomina param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerIntegrantesNomina(ParamObtenerIntegrantesNomina param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumDetalle() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerNominasVida(ParamObtenerNominasVida param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerPolizasFlotantes(ParamObtenerPolizasFlotantes param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarActualizarPolizaFlotante(ParamActualizarPolizaFlotante param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getIdentificadorWeb() == null) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerDetallePolizaFlotante(ParamObtenerDetallePolizaFlotante param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getIdentificador() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerMovimientosComision(ParamObtenerMovimientosComision param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodMoneda() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerIntegrantesNominaAccidenteTrabajo(ParamObtenerIntegrantesNominaAccidenteTrabajo param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumPoliza() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodRamo() == null) {
			claveDeError = Values.VALNULL;
		} else if (new ValidadorMesAnio().validate(param.getMesCargo()) != null) {
			claveDeError = Values.VALMESANNO;
		} 

		return getErrorsByClave(claveDeError);
	}

}
