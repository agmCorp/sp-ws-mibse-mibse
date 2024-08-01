package uy.com.bse.cotizaciones.operaciones.ws;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import uy.com.bse.cotizaciones.operaciones.DatosPlanesCotizacion;
import uy.com.bse.cotizaciones.operaciones.ParamActualizarCertificadoCero;
import uy.com.bse.cotizaciones.operaciones.ParamActualizarCoberturaBien;
import uy.com.bse.cotizaciones.operaciones.ParamActualizarDatos;
import uy.com.bse.cotizaciones.operaciones.ParamActualizarDatosBancarios;
import uy.com.bse.cotizaciones.operaciones.ParamActualizarDirCotizacion;
import uy.com.bse.cotizaciones.operaciones.ParamActualizarPromocionVehiculo;
import uy.com.bse.cotizaciones.operaciones.ParamAgregarAcreedorXBien;
import uy.com.bse.cotizaciones.operaciones.ParamAgregarBien;
import uy.com.bse.cotizaciones.operaciones.ParamAgregarCertificado;
import uy.com.bse.cotizaciones.operaciones.ParamAltaBeneficiario;
import uy.com.bse.cotizaciones.operaciones.ParamAltaListaBienes;
import uy.com.bse.cotizaciones.operaciones.ParamAltaTextoCotizacion;
import uy.com.bse.cotizaciones.operaciones.ParamAltaUbicacionBien;
import uy.com.bse.cotizaciones.operaciones.ParamAnularCertificado;
import uy.com.bse.cotizaciones.operaciones.ParamAnularCotizacion;
import uy.com.bse.cotizaciones.operaciones.ParamBajaBeneficiario;
import uy.com.bse.cotizaciones.operaciones.ParamBajaListaBienes;
import uy.com.bse.cotizaciones.operaciones.ParamBajaUbicacionBien;
import uy.com.bse.cotizaciones.operaciones.ParamCalcularCertificado;
import uy.com.bse.cotizaciones.operaciones.ParamCalcularCobertura;
import uy.com.bse.cotizaciones.operaciones.ParamCalcularCotizacion;
import uy.com.bse.cotizaciones.operaciones.ParamCalcularCuotasVehiculo;
import uy.com.bse.cotizaciones.operaciones.ParamCalcularValidarVigencia;
import uy.com.bse.cotizaciones.operaciones.ParamConfigurarFechasPromocion;
import uy.com.bse.cotizaciones.operaciones.ParamCopiarCotizaPoli;
import uy.com.bse.cotizaciones.operaciones.ParamEnviarCotizacionFueraPauta;
import uy.com.bse.cotizaciones.operaciones.ParamGuardarAsegurado;
import uy.com.bse.cotizaciones.operaciones.ParamGuardarCoberturas;
import uy.com.bse.cotizaciones.operaciones.ParamGuardarPlanes;
import uy.com.bse.cotizaciones.operaciones.ParamHabilitoAgregarBien;
import uy.com.bse.cotizaciones.operaciones.ParamHabilitoBeneficiarios;
import uy.com.bse.cotizaciones.operaciones.ParamHabilitoBotones;
import uy.com.bse.cotizaciones.operaciones.ParamHabilitoListaBienes;
import uy.com.bse.cotizaciones.operaciones.ParamHabilitoQuitarBien;
import uy.com.bse.cotizaciones.operaciones.ParamHabilitoUbicacion;
import uy.com.bse.cotizaciones.operaciones.ParamHabilitoVigenciaTecnica;
import uy.com.bse.cotizaciones.operaciones.ParamIngresarNotaPoliza;
import uy.com.bse.cotizaciones.operaciones.ParamModificarAcreedorXBien;
import uy.com.bse.cotizaciones.operaciones.ParamModificarBeneficiario;
import uy.com.bse.cotizaciones.operaciones.ParamModificarListaBienes;
import uy.com.bse.cotizaciones.operaciones.ParamModificarOpcionesFacturacion;
import uy.com.bse.cotizaciones.operaciones.ParamModificarTextoCotizacion;
import uy.com.bse.cotizaciones.operaciones.ParamModificarUbicacionBien;
import uy.com.bse.cotizaciones.operaciones.ParamNuevaCotizacion;
import uy.com.bse.cotizaciones.operaciones.ParamNuevaCotizacionVehiculo;
import uy.com.bse.cotizaciones.operaciones.ParamQuitarAcreedorXBien;
import uy.com.bse.cotizaciones.operaciones.ParamQuitarBien;
import uy.com.bse.cotizaciones.operaciones.ParamRenovacionManual;
import uy.com.bse.cotizaciones.operaciones.ParamSetearPlanCobertura;
import uy.com.bse.cotizaciones.operaciones.ParamValidarAcumulaRiesgos;
import uy.com.bse.cotizaciones.operaciones.ParamValidarDatos;
import uy.com.bse.cotizaciones.operaciones.ParamValidarDatosVehiculo;
import uy.com.bse.serviciosEJB.SeguridadServiciosRemote;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.seguridad.ResultValidar;
import uy.com.bse.utilitario.servicios.ValidacionesAbstract;
import uy.ibm.responseTimeLogger.RTimeLogger;

public class ValidacionesCotOperaciones extends ValidacionesAbstract {

	public ServiciosError validarNuevaCotizacion(final ParamNuevaCotizacion param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodProducto() == null || param.getCodProducto().equals(Values.NULL) || param.getCodProducto().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodRamo() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getTipoFacturacion() == null || param.getTipoFacturacion().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodMoneda() == null || param.getCodMoneda().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getMedioPago() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getOrigenPago() == null || param.getOrigenPago().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodPromocion() == null || param.getCodPromocion().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getRenovacion() == null || param.getRenovacion().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getTipoCalculo() == null || param.getTipoCalculo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getVigencia() == null || param.getVigencia().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getFechaDesdeVigencia() == null || param.getFechaDesdeVigencia().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getFechaHastaVigencia() == null || param.getFechaHastaVigencia().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodClienteContratante() == null || param.getCodClienteContratante().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodDireccionCobro() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodDireccionEnvio() == null) {
			claveDeError = Values.VALNULL;
		}

		if (param.getTipoCopia() != null) {
			if (param.getNumACopiar() == null) {
				claveDeError = Values.VALNULL;
			}
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarCopiarCotizaPoli(ParamCopiarCotizaPoli param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCotizacionACopiar() != null && param.getNumPolizaACopiar() != null) {
			claveDeError = "valuesNoNull";
		} else if (param.getNumCotizacionACopiar() == null && param.getNumPolizaACopiar() == null) {
			claveDeError = "valuesNull";
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarAnularCertificado(ParamAnularCertificado param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumConsecutivo() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarAgregarBien(ParamAgregarBien param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumConsecutivo() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodBien() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarAgregarCertificado(ParamAgregarCertificado param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarQuitarBien(ParamQuitarBien param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumConsecutivoBien() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarAnularCotizacion(final ParamAnularCotizacion param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarHabilitoAgregarBien(ParamHabilitoAgregarBien param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarHabilitoQuitarBien(ParamHabilitoQuitarBien param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodBien() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getConsecutivoBien() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarHabilitoListaBienes(ParamHabilitoListaBienes param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodBien() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getConsecutivoBien() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarHabilitoUbicacion(ParamHabilitoUbicacion param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodBien() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getConsecutivoBien() == null) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarHabilitoBeneficiarios(ParamHabilitoBeneficiarios param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodBien() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getConsecutivoBien() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarAltaUbicacionBien(ParamAltaUbicacionBien param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null || param.getNumCotizacion().equals(Values.NULL) || param.getNumCotizacion().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals(Values.NULL) || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getConsecutivoBien() == null || param.getConsecutivoBien().equals(Values.NULL) || param.getConsecutivoBien().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPostal() == null || param.getNumPostal().equals(Values.NULL) || param.getNumPostal().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCalle() == null || param.getCalle().equals(Values.NULL) || param.getCalle().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumero() == null || param.getNumero().equals(Values.NULL) || param.getNumero().equals("")) {
			claveDeError = Values.VALNULL;
		} /*else if (param.getNumPadron() == null || param.getNumPadron().equals(Values.NULL) || param.getNumPadron().equals("")) {
			claveDeError = Values.VALNUL
		}*/

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarBajaUbicacionBien(ParamBajaUbicacionBien param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null || param.getNumCotizacion().equals(Values.NULL) || param.getNumCotizacion().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals(Values.NULL) || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getConsecutivoBien() == null || param.getConsecutivoBien().equals(Values.NULL) || param.getConsecutivoBien().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarModificarUbicacionBien(ParamModificarUbicacionBien param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null || param.getNumCotizacion().equals(Values.NULL) || param.getNumCotizacion().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals(Values.NULL) || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getConsecutivoBien() == null || param.getConsecutivoBien().equals(Values.NULL) || param.getConsecutivoBien().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getConsecutivoUbicacion() == null || param.getConsecutivoUbicacion().equals(Values.NULL) || param.getConsecutivoUbicacion().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPostal() == null || param.getNumPostal().equals(Values.NULL) || param.getNumPostal().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCalle() == null || param.getCalle().equals(Values.NULL) || param.getCalle().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumero() == null || param.getNumero().equals(Values.NULL) || param.getNumero().equals("")) {
			claveDeError = Values.VALNULL;
		} 

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarValidarDatos(ParamValidarDatos param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null || param.getNumCotizacion().equals(Values.NULL) || param.getNumCotizacion().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarActualizarDatos(ParamActualizarDatos param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null || param.getNumCotizacion().equals(Values.NULL) || param.getNumCotizacion().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals(Values.NULL) || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumConsBien() == null || param.getNumConsBien().equals(Values.NULL) || param.getNumConsBien().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getDatoCod() == null || param.getDatoCod().equals(Values.NULL) || param.getDatoCod().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarGuardarCoberturas(ParamGuardarCoberturas param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null || param.getNumCotizacion().equals(Values.NULL) || param.getNumCotizacion().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals(Values.NULL) || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodPlanCobertura() == null || param.getCodPlanCobertura().equals(Values.NULL) || param.getCodPlanCobertura().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarCalcularCotizacion(ParamCalcularCotizacion param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null || param.getNumCotizacion().equals(Values.NULL) || param.getNumCotizacion().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals(Values.NULL) || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodPlanPago() == null || param.getCodPlanPago().equals(Values.NULL) || param.getCodPlanPago().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarHabilitoVigenciaTecnica(ParamHabilitoVigenciaTecnica param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodProducto() == null || param.getCodProducto().equals("") || param.getCodProducto().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getTipoVigencia() == null || param.getTipoVigencia().equals("") || param.getTipoVigencia().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);

	}

	public ServiciosError validarAltaBeneficiario(ParamAltaBeneficiario param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("") || param.getNumCertificado().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumConsecutivoBien() == null || param.getNumConsecutivoBien().equals("") || param.getNumConsecutivoBien().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getTipoDocumento() == null || param.getTipoDocumento().equals("") || param.getTipoDocumento().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumDocumento() == null || param.getNumDocumento().equals("") || param.getNumDocumento().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getBeneficiario() == null || param.getBeneficiario().equals("") || param.getBeneficiario().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodParentesco() == null || param.getCodParentesco().equals("") || param.getCodParentesco().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getPorcentajeParticipacion() == null || param.getPorcentajeParticipacion().equals("") || param.getPorcentajeParticipacion().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarModificarBeneficiario(ParamModificarBeneficiario param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("") || param.getNumCertificado().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumConsecutivoBien() == null || param.getNumConsecutivoBien().equals("") || param.getNumConsecutivoBien().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getTipoDocumentoReferente() == null || param.getTipoDocumentoReferente().equals("") || param.getTipoDocumentoReferente().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumDocumentoReferente() == null || param.getNumDocumentoReferente().equals("") || param.getNumDocumentoReferente().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getTipoDocumento() == null || param.getTipoDocumento().equals("") || param.getTipoDocumento().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumDocumento() == null || param.getNumDocumento().equals("") || param.getNumDocumento().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getBeneficiario() == null || param.getBeneficiario().equals("") || param.getBeneficiario().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodParentesco() == null || param.getCodParentesco().equals("") || param.getCodParentesco().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getPorcentajeParticipacion() == null || param.getPorcentajeParticipacion().equals("") || param.getPorcentajeParticipacion().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);

	}

	public ServiciosError validarBajaBeneficiario(ParamBajaBeneficiario param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("") || param.getNumCertificado().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumConsecutivoBien() == null || param.getNumConsecutivoBien().equals("") || param.getNumConsecutivoBien().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getTipoDocumentoReferente() == null || param.getTipoDocumentoReferente().equals("") || param.getTipoDocumentoReferente().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumDocumentoReferente() == null || param.getNumDocumentoReferente().equals("") || param.getNumDocumentoReferente().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getFechaExclusion() == null || param.getFechaExclusion().equals("") || param.getFechaExclusion().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;

		}

		return getErrorsByClave(claveDeError);

	}

	public ServiciosError validarAltaListaBienes(ParamAltaListaBienes param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("") || param.getNumCertificado().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumConsecutivoBien() == null || param.getNumConsecutivoBien().equals("") || param.getNumConsecutivoBien().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodObjeto() == null || param.getCodObjeto().equals("") || param.getCodObjeto().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getDescripcion() == null || param.getDescripcion().equals("") || param.getDescripcion().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getValor() == null || param.getValor().equals("") || param.getValor().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumUnidades() == null || param.getNumUnidades().equals("") || param.getNumUnidades().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);

	}

	public ServiciosError validarBajaListaBienes(ParamBajaListaBienes param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("") || param.getNumCertificado().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumConsecutivoBien() == null || param.getNumConsecutivoBien().equals("") || param.getNumConsecutivoBien().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumOrdinal() == null || param.getNumOrdinal().equals("") || param.getNumOrdinal().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);

	}

	public ServiciosError validarModificarListaBienes(ParamModificarListaBienes param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("") || param.getNumCertificado().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumConsecutivoBien() == null || param.getNumConsecutivoBien().equals("") || param.getNumConsecutivoBien().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodObjeto() == null || param.getCodObjeto().equals("") || param.getCodObjeto().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getDescripcion() == null || param.getDescripcion().equals("") || param.getDescripcion().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getValor() == null || param.getValor().equals("") || param.getValor().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumUnidades() == null || param.getNumUnidades().equals("") || param.getNumUnidades().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumOrdinal() == null || param.getNumOrdinal().equals("") || param.getNumOrdinal().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);

	}

	public ServiciosError validarRenovacionManual(ParamRenovacionManual param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("") || param.getCodRamo().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("") || param.getNumPoliza().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getUsuario() == null || param.getUsuario().equals("") || param.getUsuario().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodProductor() == null || param.getCodProductor().equals("") || param.getCodProductor().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodMoneda() == null || param.getCodMoneda().equals("") || param.getCodMoneda().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodPromocion() == null || param.getCodPromocion().equals("") || param.getCodPromocion().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getRenovacion() == null || param.getRenovacion().equals("") || param.getRenovacion().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getTipoCalculo() == null || param.getTipoCalculo().equals("") || param.getTipoCalculo().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getFormaPago() == null || param.getFormaPago().equals("") || param.getFormaPago().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getPlanPago() == null || param.getPlanPago().equals("") || param.getPlanPago().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getMedioPago() == null || param.getMedioPago().equals("") || param.getMedioPago().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getOrigenPago() == null || param.getOrigenPago().equals("") || param.getOrigenPago().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getSoloCotiza() == null || param.getSoloCotiza().equals("") || param.getSoloCotiza().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);

	}

	public ServiciosError validarIngresarNotaPoliza(ParamIngresarNotaPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodTipoNota() == null || param.getCodTipoNota().equals("") || param.getCodTipoNota().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getEnlace() == null || param.getEnlace().equals("") || param.getEnlace().equals(Values.NULL)) {
		}

		return getErrorsByClave(claveDeError);

	}

	public ServiciosError validarHabilitoBotones(ParamHabilitoBotones param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null || param.getNumCotizacion().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodBien() == null || param.getCodBien().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getConsecutivoBien() == null || param.getConsecutivoBien().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarAcumulaRiesgos(ParamValidarAcumulaRiesgos param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null || param.getNumCotizacion().equals(Values.NULL) || param.getNumCotizacion().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals(Values.NULL) || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarAltaTextoCotizacion(ParamAltaTextoCotizacion param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null || param.getNumCotizacion().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodTexto() == null || param.getCodTexto().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getImpresion() == null || param.getImpresion().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getFechaDesde() == null || param.getImpresion().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarModificarTextoCotizacion(ParamModificarTextoCotizacion param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null || param.getNumCotizacion().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumConsecutivoTexto() == null || param.getNumConsecutivoTexto().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodTexto() == null || param.getCodTexto().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getImpresion() == null || param.getImpresion().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getFechaDesde() == null || param.getImpresion().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarAgregarAcreedorXBien(ParamAgregarAcreedorXBien param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null || param.getNumCotizacion().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumConsecutivoBien() == null || param.getNumConsecutivoBien().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodAcreedor() == null || param.getCodAcreedor().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodObjeto() == null || param.getCodObjeto().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getTipoAcreedor() == null || param.getTipoAcreedor().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getPorcentajeParticipacion() == null || param.getPorcentajeParticipacion().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarModificarAcreedorXBien(ParamModificarAcreedorXBien param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null || param.getNumCotizacion().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumConsecutivoBien() == null || param.getNumConsecutivoBien().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodAcreedor() == null || param.getCodAcreedor().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodObjeto() == null || param.getCodObjeto().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getTipoAcreedor() == null || param.getTipoAcreedor().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getPorcentajeParticipacion() == null || param.getPorcentajeParticipacion().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getIdentificador() == null || param.getIdentificador().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarQuitarAcreedorXBien(ParamQuitarAcreedorXBien param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getIdentificador() == null || param.getIdentificador().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarCalcularValidarVigencia(ParamCalcularValidarVigencia param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodProducto() == null || param.getCodProducto().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodPromocion() == null || param.getCodPromocion().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getModoCalculo() == null || param.getModoCalculo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getVigencia() == null || param.getVigencia().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getTipoTransaccion() == null || param.getTipoTransaccion().equals("")) {
			claveDeError = Values.VALNULL;
		}

		if ("P".equals(param.getModoCalculo()) && "P".equals(param.getVigencia())) {
			if (param.getFechaHastaVigencia() == null || param.getFechaHastaVigencia().equals("")) {
				claveDeError = Values.VALNULL;
			}
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarGuardarPlanes(ParamGuardarPlanes param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else {
			for (int i = 0; i < param.getPlanesCotizacion().size(); i++) {

				DatosPlanesCotizacion dat = param.getPlanesCotizacion().get(i);

				if (dat.getCodPlanCobertura() == null) {
					claveDeError = Values.VALNULL;
				} else if (dat.getCodPlanPago() == null || dat.getCodPlanPago().equals("") || dat.getCodPlanPago().equals("null")) {
					claveDeError = Values.VALNULL;
				} else if (dat.getNumCertificado() == null) {
					claveDeError = Values.VALNULL;
				}
			}
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarActualizarDireccionesCotizacion(ParamActualizarDirCotizacion param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodDireccionCobro() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodDireccionEnvio() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarActualizarCoberturaBien(ParamActualizarCoberturaBien param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumConsecutivo() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodCobertura() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodRamoCobertura() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getValor() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarCalcularCobertura(ParamCalcularCobertura param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumBien() == null) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarCalcularCertificado(ParamCalcularCertificado param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarSetearPlanCobertura(ParamSetearPlanCobertura param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getPlanCobertura() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarGuardarAsegurado(ParamGuardarAsegurado param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarActualizarCertificadoCero(ParamActualizarCertificadoCero param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		}
		// FIXME OIGRES VALIDACION DE ESTO QUE FALTAN PILA

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarActualizarDatosBancarios(ParamActualizarDatosBancarios param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarEnviarCotizacionFueraPauta(ParamEnviarCotizacionFueraPauta param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
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

	public ServiciosError validarModificarOpcionesFacturacion(ParamModificarOpcionesFacturacion param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getEnviarFacturaEmail() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getEmitirConRUT() == null) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
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

	public ServiciosError validarConfigurarFechasPromocion(ParamConfigurarFechasPromocion param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodPromocion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getFechaDesde() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getFechaHasta() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}
}
