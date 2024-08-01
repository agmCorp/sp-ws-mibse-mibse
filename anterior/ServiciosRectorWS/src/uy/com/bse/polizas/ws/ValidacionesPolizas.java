package uy.com.bse.polizas.ws;

import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import uy.com.bse.cotizaciones.consultas.AcreedorBien;
import uy.com.bse.cotizaciones.consultas.BienCert;
import uy.com.bse.polizas.consultas.ParamCartaXTipoCarta;
import uy.com.bse.polizas.consultas.ParamCausaAnulacion;
import uy.com.bse.polizas.consultas.ParamDatosParametricoPoliza;
import uy.com.bse.polizas.consultas.ParamDatosParametricosEndoso;
import uy.com.bse.polizas.consultas.ParamDatosXDatosParametricosEndoso;
import uy.com.bse.polizas.consultas.ParamDatosXDatosParametricosPoliza;
import uy.com.bse.polizas.consultas.ParamGenerarMarcaNoRenovar;
import uy.com.bse.polizas.consultas.ParamGuardarImprimirCarta;
import uy.com.bse.polizas.consultas.ParamGuardarImprimirReporte;
import uy.com.bse.polizas.consultas.ParamModoCalculo;
import uy.com.bse.polizas.consultas.ParamMotivoAbandono;
import uy.com.bse.polizas.consultas.ParamMotivoEndoso;
import uy.com.bse.polizas.consultas.ParamObjetosAcreedor;
import uy.com.bse.polizas.consultas.ParamObtenerAcreedoresPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerAhorrosPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerAnexosPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerAseguradoCertifPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerBeneficiariosPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerBienesAseguradosPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerBonifNoSiniestroPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerCancelacionesRemesa;
import uy.com.bse.polizas.consultas.ParamObtenerCertificadosPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerContratantePoliza;
import uy.com.bse.polizas.consultas.ParamObtenerCotizacionPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerCuotasPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerDatosBasicosEndoso;
import uy.com.bse.polizas.consultas.ParamObtenerDatosCertificadoPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerDatosEndosoPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerDatosEndosoSinPremio;
import uy.com.bse.polizas.consultas.ParamObtenerDatosFacturaDigital;
import uy.com.bse.polizas.consultas.ParamObtenerDatosParametricos;
import uy.com.bse.polizas.consultas.ParamObtenerDatosPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerDatosRemesa;
import uy.com.bse.polizas.consultas.ParamObtenerDatosSiniestro;
import uy.com.bse.polizas.consultas.ParamObtenerDeclaracionSiniestro;
import uy.com.bse.polizas.consultas.ParamObtenerDetallePoliza;
import uy.com.bse.polizas.consultas.ParamObtenerDetalleRemesa;
import uy.com.bse.polizas.consultas.ParamObtenerDetalleTextoPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerEndososPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerFacturasPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerFranquiciasPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerImputacionesPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerListaBienesPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerNotasSiniestro;
import uy.com.bse.polizas.consultas.ParamObtenerOrigenRemesaAuto;
import uy.com.bse.polizas.consultas.ParamObtenerPolizaCabezalDetalle;
import uy.com.bse.polizas.consultas.ParamObtenerPolizas;
import uy.com.bse.polizas.consultas.ParamObtenerPolizasCabezal;
import uy.com.bse.polizas.consultas.ParamObtenerPreliqPendientesPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerProductoresPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerRemesasPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerRemesasXCliente;
import uy.com.bse.polizas.consultas.ParamObtenerSiniestros;
import uy.com.bse.polizas.consultas.ParamObtenerSiniestrosCliente;
import uy.com.bse.polizas.consultas.ParamObtenerSiniestrosConFiltro;
import uy.com.bse.polizas.consultas.ParamObtenerTextosPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerTotalesCuotasPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerUbicacionBienPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerValidarImpresionPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerValoresXDatoParametrico;
import uy.com.bse.polizas.consultas.ParamTienePermisoProducto;
import uy.com.bse.polizas.consultas.ParamTipoAnulacion;
import uy.com.bse.polizas.consultas.ParamTipoCarta;
import uy.com.bse.polizas.consultas.ParamValoresDatosParametricosEndoso;
import uy.com.bse.polizas.consultas.ParamValoresDatosParametricosPoliza;
import uy.com.bse.polizas.operaciones.ParamActualizarDatosParticulares;
import uy.com.bse.polizas.operaciones.ParamAgregarCertificadoEndoso;
import uy.com.bse.polizas.operaciones.ParamAnularCertificadoEndoso;
import uy.com.bse.polizas.operaciones.ParamAnularPoliza;
import uy.com.bse.polizas.operaciones.ParamCotizacionPendiente;
import uy.com.bse.polizas.operaciones.ParamCotizacionPendienteSinPremio;
import uy.com.bse.polizas.operaciones.ParamEnviarFacturaDigitalEMail;
import uy.com.bse.polizas.operaciones.ParamGenerarEndoso;
import uy.com.bse.polizas.operaciones.ParamNuevaCotizacionEndoso;
import uy.com.bse.polizas.operaciones.ParamNuevaFacturacionInteractiva;
import uy.com.bse.polizas.operaciones.ParamValidarAnulacionPoliza;
import uy.com.bse.polizas.operaciones.ParamValidarPoliza;
import uy.com.bse.recuotificacion.ParamCalcularRec;
import uy.com.bse.recuotificacion.ParamListaCertificadosEndosar;
import uy.com.bse.recuotificacion.ParamListaPlanesPagoRec;
import uy.com.bse.recuotificacion.ParamModificacionSinPremio;
import uy.com.bse.recuotificacion.ParamObtenerConsolidado;
import uy.com.bse.recuotificacion.ParamOrigenEndoso;
import uy.com.bse.recuotificacion.ParamRecuotificarPoliza;
import uy.com.bse.recuotificacion.ParamValidarDetectarEndoso;
import uy.com.bse.recuotificacion.ParamValidarPolizaRec;
import uy.com.bse.refacturar.ParamRefacturarPoliza;
import uy.com.bse.refacturar.ParamValidarRefacturacion;
import uy.com.bse.serviciosEJB.SeguridadServiciosRemote;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.seguridad.ResultValidar;
import uy.com.bse.utilitario.servicios.ValidacionesAbstract;
import uy.ibm.responseTimeLogger.RTimeLogger;

public class ValidacionesPolizas extends ValidacionesAbstract {

	public ServiciosError validarObtenerDatosPoliza(ParamObtenerDatosPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerCertificadosPoliza(ParamObtenerCertificadosPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerDatosCertificadoPoliza(ParamObtenerDatosCertificadoPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaTipoAnulacion(ParamTipoAnulacion param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaCausaAnulacion(ParamCausaAnulacion param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodTipoAnulacion() == null || param.getCodTipoAnulacion().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaModoCalculoAnulacion(ParamModoCalculo param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerEndososPoliza(ParamObtenerEndososPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerDatosEndosoPoliza(ParamObtenerDatosEndosoPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumEndoso() == null || param.getNumEndoso().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerProductoresPoliza(ParamObtenerProductoresPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerDetallePoliza(ParamObtenerDetallePoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerPolizas(ParamObtenerPolizas param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getOrden() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerCuotasPoliza(ParamObtenerCuotasPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerFacturasPoliza(ParamObtenerFacturasPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerImputacionesPoliza(ParamObtenerImputacionesPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerRemesasPoliza(ParamObtenerRemesasPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerTotalesCuotasPoliza(ParamObtenerTotalesCuotasPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerPreliqPendientesPoliza(ParamObtenerPreliqPendientesPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerCotizacionPoliza(ParamObtenerCotizacionPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumEndoso() == null || param.getNumEndoso().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerContratantePoliza(ParamObtenerContratantePoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerAseguradoCertifPoliza(ParamObtenerAseguradoCertifPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerBienesAseguradosPoliza(ParamObtenerBienesAseguradosPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumEndoso() == null || param.getNumEndoso().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerTextosPoliza(ParamObtenerTextosPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumEndoso() == null || param.getNumEndoso().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerDetalleTextoPoliza(ParamObtenerDetalleTextoPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodTexto() == null || param.getCodTexto().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumConsecutivoTexto() == null || param.getNumConsecutivoTexto().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerAnexosPoliza(ParamObtenerAnexosPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumEndoso() == null || param.getNumEndoso().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerListaBienesPoliza(ParamObtenerListaBienesPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumEndoso() == null || param.getNumEndoso().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodBienAsegurado() == null || param.getCodBienAsegurado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumConsecutivoBienAsegurado() == null || param.getNumConsecutivoBienAsegurado().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerUbicacionBienPoliza(ParamObtenerUbicacionBienPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumEndoso() == null || param.getNumEndoso().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumConsecutivoBienAsegurado() == null || param.getNumConsecutivoBienAsegurado().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerBeneficiariosPoliza(ParamObtenerBeneficiariosPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumEndoso() == null || param.getNumEndoso().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumConsecutivoBienAsegurado() == null || param.getNumConsecutivoBienAsegurado().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerAcreedoresPoliza(ParamObtenerAcreedoresPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumEndoso() == null || param.getNumEndoso().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodBienAsegurado() == null || param.getCodBienAsegurado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumConsecutivoBienAsegurado() == null || param.getNumConsecutivoBienAsegurado().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerFranquiciasPoliza(ParamObtenerFranquiciasPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumEndoso() == null || param.getNumEndoso().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerAhorrosPoliza(ParamObtenerAhorrosPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumEndoso() == null || param.getNumEndoso().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerRemesasXCliente(ParamObtenerRemesasXCliente param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodCliente() == null || param.getCodCliente().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerDatosRemesa(ParamObtenerDatosRemesa param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumRemesa() == null || param.getNumRemesa().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerCancelacionesRemesa(ParamObtenerCancelacionesRemesa param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumRemesa() == null || param.getNumRemesa().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerDetalleRemesa(ParamObtenerDetalleRemesa param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumRemesa() == null || param.getNumRemesa().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerOrigenRemesaAutomatica(ParamObtenerOrigenRemesaAuto param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumRemesa() == null || param.getNumRemesa().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerNotasSiniestro(ParamObtenerNotasSiniestro param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getEnlace() == null || param.getEnlace().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerBonifNoSiniestroPoliza(ParamObtenerBonifNoSiniestroPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerDeclaracionSiniestro(ParamObtenerDeclaracionSiniestro param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getAnio() == null || param.getAnio().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumSiniestro() == null || param.getNumSiniestro().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerDatosSiniestro(ParamObtenerDatosSiniestro param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getAnio() == null || param.getAnio().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumSiniestro() == null || param.getNumSiniestro().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerSiniestrosCliente(ParamObtenerSiniestrosCliente param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodCliente() == null || param.getCodCliente().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNacionalidadCliente() == null || param.getNacionalidadCliente().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerSiniestros(ParamObtenerSiniestros param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerSiniestrosConFiltro(ParamObtenerSiniestrosConFiltro param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarValidarPoliza(ParamValidarPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.CLAVEINCORRECTA;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarValidarAnulacionPoliza(ParamValidarAnulacionPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;

		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;

		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;

		} else if (param.getTipoAnulacion() == null || param.getTipoAnulacion().equals("")) {
			claveDeError = Values.VALNULL;

		}

		if (!("S".equals(param.getTipoAnulacion()))) {
			if (param.getFechaAnulacion() == null || param.getFechaAnulacion().equals("")) {
				claveDeError = Values.VALNULL;
			}
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarAnularPoliza(ParamAnularPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCotizacion() == null || param.getNumCotizacion().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodMotivoAbandono() == null || param.getCodMotivoAbandono().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getAclaracion() == null || param.getAclaracion().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaPlanesPagoRecuotificacion(ParamListaPlanesPagoRec param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarValidarPolizaRecuotificacion(ParamValidarPolizaRec param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarCalcularRecuotificacion(ParamCalcularRec param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumEndoso() == null || param.getNumEndoso().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCotizacion() == null || param.getNumCotizacion().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getFechaEmision() == null || param.getFechaEmision().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodPlanPago() == null || param.getCodPlanPago().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getMontoPrima() == null || param.getMontoPrima().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getMontoPremio() == null || param.getMontoPremio().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarRecuotificarPoliza(ParamRecuotificarPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumEndoso() == null || param.getNumEndoso().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCotizacion() == null || param.getNumCotizacion().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getFechaEmision() == null || param.getFechaEmision().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodPlanPago() == null || param.getCodPlanPago().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getMontoPrima() == null || param.getMontoPrima().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getMontoPremio() == null || param.getMontoPremio().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaMotivoAbandono(ParamMotivoAbandono param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaOrigenEndoso(ParamOrigenEndoso param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerConsolidadoPoliza(ParamObtenerConsolidado param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarNuevaCotizacionEndoso(ParamNuevaCotizacionEndoso param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodProducto() == null || param.getCodProducto().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodOrigenEndoso() == null || param.getCodOrigenEndoso().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getFechaDesdeVigencia() == null || param.getFechaDesdeVigencia().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodDireccionCobro() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodDireccionEnvio() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaCartaXTipoCarta(ParamCartaXTipoCarta param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getTipo() == null || param.getTipo().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaTiposCarta(ParamTipoCarta param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerImpresionPoliza(ParamObtenerValidarImpresionPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarGuardarImprimirOrdenCarta(ParamGuardarImprimirCarta param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCarta() == null || param.getNumCarta().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumReporte() == null || param.getNumReporte().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodReporte() == null || param.getCodReporte().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarGuardarImprimirReporte(ParamGuardarImprimirReporte param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumEndoso() == null || param.getNumEndoso().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getItems() == null || param.getItems().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarAgregarCertificadoEndoso(ParamAgregarCertificadoEndoso param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerDatosBasicosEndoso(ParamObtenerDatosBasicosEndoso param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaMotivosEndoso(ParamMotivoEndoso param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaDatosParametricosEndoso(ParamDatosParametricosEndoso param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null && param.getEsUltimoEndoso()==null && (param.getNumPoliza() == null || param.getCodRamo() == null)) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaValoresDatosParametricosEndoso(ParamValoresDatosParametricosEndoso param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null && param.getEsUltimoEndoso()==null && (param.getNumPoliza() == null || param.getCodRamo() == null)) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodDato() == null) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);

	}

	public ServiciosError validarObtenerDatosXDatoParametricoEndoso(ParamDatosXDatosParametricosEndoso param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null && (param.getNumPoliza() == null || param.getCodRamo() == null)) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodDato() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);

	}

	public ServiciosError validarAnularCertificadoEndoso(ParamAnularCertificadoEndoso param) {
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

	public ServiciosError validarListaDatosParametricosPoliza(ParamDatosParametricoPoliza param) {
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

	public ServiciosError validarListaValoresDatosParametricosPoliza(ParamValoresDatosParametricosPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodDato() == null) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerDatosXDatoParametricoPoliza(ParamDatosXDatosParametricosPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodDato() == null) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);

	}

	public ServiciosError validarObtenerDatosEndosoSinPremio(ParamObtenerDatosEndosoSinPremio param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError generarMarcaNoRenovar(ParamGenerarMarcaNoRenovar param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError generarEndoso(ParamGenerarEndoso param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumRamo() == null || param.getNumRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError actualizarDatosParticulares(ParamActualizarDatosParticulares param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumRamo() == null || param.getNumRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumEndoso() == null || param.getNumEndoso().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodBien() == null || param.getCodBien().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodDato() == null || param.getCodDato().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError nuevaFacturacionInteractiva(ParamNuevaFacturacionInteractiva param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarValidarDetectarEndoso(ParamValidarDetectarEndoso param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumRamo() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getFechaDesde() == null || param.getFechaDesde().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarModificacionSinPremio(ParamModificacionSinPremio param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumRamo() == null || param.getNumRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getFechaDesde() == null || param.getFechaDesde().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumEndoso() == null || param.getNumEndoso().equals("")) {
			claveDeError = Values.VALNULL;
		}

		if (param.getBienes() != null && !param.getBienes().isEmpty()) {
			for (BienCert bien : param.getBienes()) {
				ServiciosError error = validarAcreedores(bien.getAcreedoresBien());
				if (error != null) {
					return error;
				}
			}
		}

		return getErrorsByClave(claveDeError);
	}

	private ServiciosError validarAcreedores(ArrayList<AcreedorBien> acreedoresBien) {
		ServiciosError error = null;
		Double porciento = Double.valueOf(0d);
		boolean limitePorciento = false;
		for (AcreedorBien acreedorBien : acreedoresBien) {
			if (acreedorBien.getFechaExclusion() == null || acreedorBien.getFechaExclusion().equals("")) {
				porciento += acreedorBien.getPorcentajeParticipacion();
			}
			if (Double.valueOf(100d) < acreedorBien.getPorcentajeParticipacion()) {
				limitePorciento = true;
				break;
			}
		}
		if (porciento > Double.valueOf(100d)) {
			error = new ServiciosError(26, "El porcentaje de participaci\u00f3n de todos los acreedores en cada bien no puede exceder el 100%.");
		}
		if (limitePorciento) {
			error = new ServiciosError(26, "El porcentaje de participaci\u00f3n del acreedor en cada bien no puede exceder el 100%.");
		}

		return error;
	}

	public ServiciosError validarListaAcreedorObjetos(ParamObjetosAcreedor param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodBienAsegurado() == null || param.getCodBienAsegurado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getSucursal() == null || param.getSucursal().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarCotizacionPendientePoliza(ParamCotizacionPendiente param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarTienePermisoProducto(ParamTienePermisoProducto param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodProducto() == null || param.getCodProducto().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerDatosFacturaDigital(ParamObtenerDatosFacturaDigital param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumPoliza() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodRamo() == null) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarEnviarFacturaDigitalEMail(ParamEnviarFacturaDigitalEMail param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumPoliza() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodRamo() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getEnviarFacturaDigitalEMail() == null || param.getEnviarFacturaDigitalEMail().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaCertificadosParaEndosar(ParamListaCertificadosEndosar param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerValoresXDatoParametrico(ParamObtenerValoresXDatoParametrico param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodTabla() == null) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerDatosParametricos(ParamObtenerDatosParametricos param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getFiltros() == null || param.getFiltros().isEmpty()) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerPolizasCabezal(ParamObtenerPolizasCabezal param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getOrden() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerPolizaCabezalDetalle(ParamObtenerPolizaCabezalDetalle param) {
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

	public ServiciosError validarCotizacionPendientePolizaSinPremio(ParamCotizacionPendienteSinPremio param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarRefacturacion(ParamValidarRefacturacion param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError refacturarPoliza(ParamRefacturarPoliza param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

}
