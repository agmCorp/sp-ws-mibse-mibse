package uy.com.bse.mibse.ws;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import uy.com.bse.mibse.DatosComunicacion;
import uy.com.bse.mibse.ParamActualizarComunicacionesCliente;
import uy.com.bse.mibse.ParamActualizarDatosCliente;
import uy.com.bse.mibse.ParamActualizarFacturacionPoliza;
import uy.com.bse.mibse.ParamActualizarFacturacionPolizaTodo;
import uy.com.bse.mibse.ParamAdherirFacturaDigital;
import uy.com.bse.mibse.ParamAltaMailCliente;
import uy.com.bse.mibse.ParamBorrarMailCliente;
import uy.com.bse.mibse.ParamCorrespondeCartaPoliza;
import uy.com.bse.mibse.ParamExisteCliente;
import uy.com.bse.mibse.ParamInformarPagoBancario;
import uy.com.bse.mibse.ParamInformarPagoRedes;
import uy.com.bse.mibse.ParamLogActividadMibseWsExt;
import uy.com.bse.mibse.ParamModificarMailCliente;
import uy.com.bse.mibse.ParamObtenerComunicacionesCliente;
import uy.com.bse.mibse.ParamObtenerDatosCliente;
import uy.com.bse.mibse.ParamObtenerDatosValidadosCliente;
import uy.com.bse.mibse.ParamObtenerMailCliente;
import uy.com.bse.mibse.ParamObtenerMailsEnvioFacturaCliente;
import uy.com.bse.mibse.ParamObtenerMapaMsgSiniestro;
import uy.com.bse.mibse.ParamObtenerMaximoEndoso;
import uy.com.bse.mibse.ParamObtenerNumeroCliente;
import uy.com.bse.mibse.ParamObtenerPolizasCliente;
import uy.com.bse.mibse.ParamObtenerPolizasFacturasPagasCliente;
import uy.com.bse.mibse.ParamOlvidoClave;
import uy.com.bse.mibse.ParamProCarta;
import uy.com.bse.mibse.ParamProCarta2;
import uy.com.bse.mibse.ParamRegistrarCliente;
import uy.com.bse.mibse.ParamSubirArchivo;
import uy.com.bse.mibse.ParamValidacionCartaVerde;
import uy.com.bse.mibse.ParamValidacionSOA;
import uy.com.bse.mibse.ParamValidarCertificadoLibreDeudaADT;
import uy.com.bse.mibse.ParamValidarCodigoAdhesion;
import uy.com.bse.serviciosEJB.SeguridadServiciosRemote;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.seguridad.ResultValidar;
import uy.com.bse.utilitario.servicios.ValidacionesAbstract;
import uy.ibm.responseTimeLogger.RTimeLogger;

public class ValidacionesMiBse extends ValidacionesAbstract {

	public ServiciosError validarObtenerComunicacionesCliente(ParamObtenerComunicacionesCliente param) {
		return getErrorsByClave(errorValidarLogin(param));
	}

	public ServiciosError validarObtenerPolizasCliente(ParamObtenerPolizasCliente param) {
		return getErrorsByClave(errorValidarLogin(param));
	}

	public ServiciosError validarActualizarFacturacionPoliza(ParamActualizarFacturacionPoliza param) {
		String claveDeError = errorValidarLogin(param);
		if (param.getCodMarca() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodRamo() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerDatosCliente(ParamObtenerDatosCliente param) {
		return getErrorsByClave(errorValidarLogin(param));
	}

	public ServiciosError validarObtenerNumeroCliente(ParamObtenerNumeroCliente param) {
		return getErrorsByClave(errorValidarLogin(param));
	}

	public ServiciosError validarActualizarComunicacionesCliente(ParamActualizarComunicacionesCliente param) {
		String claveDeError = errorValidarLogin(param);
		if (param.getComunicaciones() == null) {
			claveDeError = Values.VALNULL;
		} else {
			for (DatosComunicacion dato : param.getComunicaciones()) {
				claveDeError = validaComunicacion(dato);
				if (claveDeError != null) {
					break;
				}
			}
		}
		return getErrorsByClave(claveDeError);
	}

	private String validaComunicacion(DatosComunicacion dato) {
		String claveDeError = null;
		// FIXME OIGRES VALIDAR POR TIPOS... LAS COMUNICACIONES FALTA
		if (dato.getTipoComunicacion() == null || (dato.getValidada() == null || dato.getValidada().equals(""))) {
			claveDeError = Values.VALNULL;

		}
		return claveDeError;
	}

	private String validaComunicacion2(DatosComunicacion dato) {
		String claveDeError = null;
		if (dato.getCodComunicacion() == null || dato.getNumPersona() == null || dato.getValorComunicacion().equals("")) {
			claveDeError = Values.VALNULL;

		}
		return claveDeError;
	}
	
	
	private String validaComunicacionAltaMail(DatosComunicacion dato) {
		String claveDeError = null;
		if (dato.getTipoComunicacion() == null || dato.getNumPersona() == null || dato.getValorComunicacion().equals("")) {
			claveDeError = Values.VALNULL;

		}
		return claveDeError;
	}
		
	
	
	public ServiciosError validarActualizarDatosCliente(ParamActualizarDatosCliente param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getTipoDocumento() == null || param.getTipoDocumento().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getComunicaciones() != null) {
			for (DatosComunicacion dato : param.getComunicaciones()) {
				claveDeError = validaComunicacion(dato);
				if (claveDeError != null) {
					break;
				}
			}
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarActualizarFacturacionPolizaTodo(ParamActualizarFacturacionPolizaTodo param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getDatosFacturacions() == null || param.getDatosFacturacions().isEmpty()) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarValidarCodigoAdhesion(ParamValidarCodigoAdhesion param) {
		String claveDeError = null;
		if (param.getUsuario() == null || param.getUsuario().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodAdhesion() == null || param.getCodAdhesion().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	private String errorValidarLogin(ParamGenerico param) {
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			return Values.CLAVEINCORRECTA;
		}
		return null;
	}

	public ServiciosError validarRegistroCliente(ParamRegistrarCliente param) {
		String claveDeError = null;
		if (param.getTipoDocumento() == null || param.getTipoDocumento().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodigoAdhesion() == null || param.getCodigoAdhesion().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumDocumento() == null || param.getNumDocumento().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getContrasena() == null || param.getContrasena().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerSeudoMailCliente(ParamObtenerMailCliente param) {
		String claveDeError = null;
		if (param.getUsuario() == null || param.getUsuario().equals("")) {
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

	public ServiciosError validarObtenerDatosValidadosCliente(ParamObtenerDatosValidadosCliente param) {
		String claveDeError = null;
		if (param.getUsuario() == null || param.getUsuario().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public static SeguridadServiciosRemote getEJB() throws NamingException {
		final InitialContext ctx = new InitialContext();
		return (SeguridadServiciosRemote) ctx.lookup(
				"java:global/SeguridadServicios/SeguridadEJB/SeguridadServicios!uy.com.bse.serviciosEJB.SeguridadServiciosRemote");

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

	public ServiciosError validarInformarPagoBancario(ParamInformarPagoBancario param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getIdTransaccion() == null || param.getIdTransaccion().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodigoAutorizacion() == null || param.getCodigoAutorizacion().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getDocumentId() == null || param.getDocumentId().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodProducto() == null || param.getCodProducto().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getDescProducto() == null || param.getDescProducto().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodRamo() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getDescRamo() == null || param.getDescRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCertificado() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getTipoDocumento() == null || param.getTipoDocumento().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNroDocumento() == null || param.getNroDocumento().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarInformarPagoRedes(ParamInformarPagoRedes param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getIdTransaccion() == null || param.getIdTransaccion().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getDocumentId() == null || param.getDocumentId().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodProducto() == null || param.getCodProducto().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getDescProducto() == null || param.getDescProducto().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodRamo() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getDescRamo() == null || param.getDescRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCertificado() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getTipoDocumento() == null || param.getTipoDocumento().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNroDocumento() == null || param.getNroDocumento().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarExisteCliente(ParamExisteCliente param) {
		String claveDeError = null;
		if (param.getTipoDocumento() == null || param.getTipoDocumento().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumDocumento() == null || param.getNumDocumento().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerMaximoEndoso(ParamObtenerMaximoEndoso param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarValidacionSOA(ParamValidacionSOA param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCertificado() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarValidacionCartaVerde(ParamValidacionCartaVerde param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCertificado() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarLogActividadMibseWsExt(ParamLogActividadMibseWsExt param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getEvento() == null || param.getEvento().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getDetalle() == null || param.getDetalle().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getUsuario() == null || param.getUsuario().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarBorrarMailCliente(ParamBorrarMailCliente param) {
		String claveDeError = errorValidarLogin(param);
		if (param.getComunicacion() == null) {
			claveDeError = Values.VALNULL;
		} else {

			claveDeError = validaComunicacion2(param.getComunicacion());

		}
		return getErrorsByClave(claveDeError);
	}
	
	
	public ServiciosError validarModificarMailCliente(ParamModificarMailCliente param) {
		String claveDeError = errorValidarLogin(param);
		if (param.getComunicacion() == null) {
			claveDeError = Values.VALNULL;
		} else {

			claveDeError = validaComunicacion2(param.getComunicacion());

		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarAltaMailCliente(ParamAltaMailCliente param) {
		String claveDeError = errorValidarLogin(param);
		if (param.getComunicacion() == null) {
			claveDeError = Values.VALNULL;
		} else {

			claveDeError = validaComunicacionAltaMail(param.getComunicacion());

		}
		return getErrorsByClave(claveDeError);
	}
	

	public ServiciosError validarObtenerMailsEnvioFacturaCliente(ParamObtenerMailsEnvioFacturaCliente param) {
		String claveDeError = null;
		if (param.getUsuario() == null || param.getUsuario().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	
	public ServiciosError validarCertificadoLibreDeudaADT(ParamValidarCertificadoLibreDeudaADT param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getPoliza() == null || param.getPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getPoliza() == "0" || !this.isNumeric(param.getPoliza())) {
			claveDeError = Values.VALNUMERICO;
		}
		return getErrorsByClave(claveDeError);
	}
	
	public ServiciosError validarMapaMsgSiniestro(ParamObtenerMapaMsgSiniestro param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}
	
	public ServiciosError validarSubirArchivo(ParamSubirArchivo param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNombreArchivo() == null || param.getNombreArchivo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getArchivo() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}
	
	public ServiciosError validarProCarta(ParamProCarta param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getPoliza() == null || param.getPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getPoliza() == "0" || !this.isNumeric(param.getPoliza())) {
			claveDeError = Values.VALNUMERICO;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarCorrespondeCartaPoliza(ParamCorrespondeCartaPoliza param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getSucursal() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getRamo() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getPoliza() == null) {
			claveDeError = Values.VALNUMERICO;
		} else if (param.getCertificado() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCdCarta() == null || param.getCdCarta().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarProCarta2(ParamProCarta2 param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getRamo() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getPoliza() == "0" || !this.isNumeric(param.getPoliza())) {
			claveDeError = Values.VALNUMERICO;
		} else if (param.getCertificado() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCdCarta() == null || param.getCdCarta().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerPolizasFacturasPagasCliente(ParamObtenerPolizasFacturasPagasCliente param) {
		
		String claveDeError = errorValidarLogin(param);
		if (param.getDiasVencimientoPoliza() == null) {
			claveDeError = Values.VALNULL;
		} 
		return getErrorsByClave(claveDeError);
	}
	
	public ServiciosError validarAdherirFacturaDigital(ParamAdherirFacturaDigital param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getSucursal() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodRamo() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}
	
}
