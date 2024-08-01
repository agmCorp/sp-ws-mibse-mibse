package uy.com.bse.cotizaciones.ws;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import uy.com.bse.cotizaciones.consultas.ParamBonificacionNS;
import uy.com.bse.cotizaciones.consultas.ParamCertificados;
import uy.com.bse.cotizaciones.consultas.ParamClientes;
import uy.com.bse.cotizaciones.consultas.ParamCotizaciones;
import uy.com.bse.cotizaciones.consultas.ParamDatoCotizacion;
import uy.com.bse.cotizaciones.consultas.ParamDatoParametrico;
import uy.com.bse.cotizaciones.consultas.ParamDatosCertificados;
import uy.com.bse.cotizaciones.consultas.ParamDatosModificacion;
import uy.com.bse.cotizaciones.consultas.ParamDetalleBusquedaCotizacion;
import uy.com.bse.cotizaciones.consultas.ParamDetalleTextoCotizacion;
import uy.com.bse.cotizaciones.consultas.ParamEncabezadoCotizacion;
import uy.com.bse.cotizaciones.consultas.ParamFueraPautas;
import uy.com.bse.cotizaciones.consultas.ParamListaPlanesPagoRen;
import uy.com.bse.cotizaciones.consultas.ParamObtenerAcreedoresXBien;
import uy.com.bse.cotizaciones.consultas.ParamObtenerAnexosCotizacion;
import uy.com.bse.cotizaciones.consultas.ParamObtenerBeneficiarios;
import uy.com.bse.cotizaciones.consultas.ParamObtenerBienes;
import uy.com.bse.cotizaciones.consultas.ParamObtenerCabezalCotizaciones;
import uy.com.bse.cotizaciones.consultas.ParamObtenerClientesSolicitante;
import uy.com.bse.cotizaciones.consultas.ParamObtenerCoberturasXBien;
import uy.com.bse.cotizaciones.consultas.ParamObtenerCoberturasXCertificado;
import uy.com.bse.cotizaciones.consultas.ParamObtenerComponentes;
import uy.com.bse.cotizaciones.consultas.ParamObtenerContratanteAsegurado;
import uy.com.bse.cotizaciones.consultas.ParamObtenerDatosBancarios;
import uy.com.bse.cotizaciones.consultas.ParamObtenerDatosBasicosCotiza;
import uy.com.bse.cotizaciones.consultas.ParamObtenerDatosPreviosEndoso;
import uy.com.bse.cotizaciones.consultas.ParamObtenerDatosRenovacion;
import uy.com.bse.cotizaciones.consultas.ParamObtenerDetalleCuota;
import uy.com.bse.cotizaciones.consultas.ParamObtenerDomiciliosCliente;
import uy.com.bse.cotizaciones.consultas.ParamObtenerFechaEmision;
import uy.com.bse.cotizaciones.consultas.ParamObtenerFranquicias;
import uy.com.bse.cotizaciones.consultas.ParamObtenerListaBienes;
import uy.com.bse.cotizaciones.consultas.ParamObtenerNotasPoliza;
import uy.com.bse.cotizaciones.consultas.ParamObtenerPlanesCobertura;
import uy.com.bse.cotizaciones.consultas.ParamObtenerPlanesPago;
import uy.com.bse.cotizaciones.consultas.ParamObtenerResumenPlanesCobertura;
import uy.com.bse.cotizaciones.consultas.ParamObtenerTextoClausula;
import uy.com.bse.cotizaciones.consultas.ParamObtenerUbicacionBien;
import uy.com.bse.cotizaciones.consultas.ParamParentesco;
import uy.com.bse.cotizaciones.consultas.ParamPersonaSeleccionada;
import uy.com.bse.cotizaciones.consultas.ParamTextosCotizacion;
import uy.com.bse.cotizaciones.consultas.ParamVigenciaTecnica;
import uy.com.bse.cotizaciones.lovs.ParamAcreedor;
import uy.com.bse.cotizaciones.lovs.ParamAcreedorObjetos;
import uy.com.bse.cotizaciones.lovs.ParamAcreedoresFiltrados;
import uy.com.bse.cotizaciones.lovs.ParamDatos;
import uy.com.bse.cotizaciones.lovs.ParamDatosXTipoNota;
import uy.com.bse.cotizaciones.lovs.ParamEnPauta;
import uy.com.bse.cotizaciones.lovs.ParamListaBancos;
import uy.com.bse.cotizaciones.lovs.ParamListaGrupos;
import uy.com.bse.cotizaciones.lovs.ParamListaTipoNota;
import uy.com.bse.cotizaciones.lovs.ParamListaTodasSecciones;
import uy.com.bse.cotizaciones.lovs.ParamListaTodosProductos;
import uy.com.bse.cotizaciones.lovs.ParamListaValoresDato;
import uy.com.bse.cotizaciones.lovs.ParamMarcasObjeto;
import uy.com.bse.cotizaciones.lovs.ParamMedioPago;
import uy.com.bse.cotizaciones.lovs.ParamModificada;
import uy.com.bse.cotizaciones.lovs.ParamModoCalculo;
import uy.com.bse.cotizaciones.lovs.ParamMoneda;
import uy.com.bse.cotizaciones.lovs.ParamNivelesComision;
import uy.com.bse.cotizaciones.lovs.ParamNivelesComisionProd;
import uy.com.bse.cotizaciones.lovs.ParamObjetos;
import uy.com.bse.cotizaciones.lovs.ParamOrigenPago;
import uy.com.bse.cotizaciones.lovs.ParamProducto;
import uy.com.bse.cotizaciones.lovs.ParamPromocion;
import uy.com.bse.cotizaciones.lovs.ParamRenovacion;
import uy.com.bse.cotizaciones.lovs.ParamRenovacionXProducto;
import uy.com.bse.cotizaciones.lovs.ParamSecciones;
import uy.com.bse.cotizaciones.lovs.ParamTipo;
import uy.com.bse.cotizaciones.lovs.ParamTipoAcreedores;
import uy.com.bse.cotizaciones.lovs.ParamTipoTexto;
import uy.com.bse.cotizaciones.lovs.ParamTiposFacturacion;
import uy.com.bse.cotizaciones.lovs.ParamTiposNotaGenerica;
import uy.com.bse.cotizaciones.lovs.ParamValoresTipoNota;
import uy.com.bse.cotizaciones.lovs.ParamVigencia;
import uy.com.bse.serviciosEJB.SeguridadServiciosRemote;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.seguridad.ResultValidar;
import uy.com.bse.utilitario.servicios.ValidacionesAbstract;
import uy.ibm.responseTimeLogger.RTimeLogger;

public class ValidacionesCotizaciones extends ValidacionesAbstract {

	public ServiciosError validarListaSecciones(final ParamSecciones param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaTodasSecciones(final ParamListaTodasSecciones param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaProducto(final ParamProducto param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaTodosProductos(final ParamListaTodosProductos param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaTipo(final ParamTipo param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaModificada(final ParamModificada param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaEnPauta(final ParamEnPauta param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaTiposFacturacion(final ParamTiposFacturacion param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaMedioPago(final ParamMedioPago param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaOrigenPago(final ParamOrigenPago param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodMedioPago() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaPromocion(final ParamPromocion param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodProducto() == null || param.getCodProducto().equals(Values.NULL)
				|| param.getCodProducto().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodRamo() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaRenovacion(final ParamRenovacion param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaMoneda(final ParamMoneda param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaModoCalculo(final ParamModoCalculo param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaVigencia(final ParamVigencia param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodProducto() == null || param.getCodProducto().equals("null") || param.getCodProducto().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerClientes(final ParamClientes param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodCliente() != null && (param.getCodCliente().equals("") || param.getCodCliente().equals(Values.NULL))) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaValoresDatoParametrico(ParamListaValoresDato param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumConsBien() != null && (param.getNumConsBien().equals("") || param.getNumConsBien().equals(Values.NULL))) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodTabla() != null && (param.getCodTabla().equals("") || param.getCodTabla().equals(Values.NULL))) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerFueraPautas(final ParamFueraPautas param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerBonificacionNS(final ParamBonificacionNS param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerCotizaciones(ParamCotizaciones param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getOrden() == null ) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerDatosCotizacion(ParamDatoCotizacion param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerCertificados(ParamCertificados param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerDatoParametrico(ParamDatoParametrico param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getDatoCod() == null || param.getDatoCod().equals("") || param.getDatoCod().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getDatoValor() == null || param.getDatoValor().equals("") || param.getDatoValor().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerDatosCertificados(ParamDatosCertificados param) {
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

	public ServiciosError validarObtenerBienesParaAgregar(ParamObtenerBienes param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;

		} else if (param.getNumConsecutivo() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodRamo() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodProducto() == null || param.getCodProducto().equals("") || param.getCodProducto().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListarDatos(ParamDatos param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodProducto() == null || param.getCodProducto().equals("") || param.getCodProducto().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerDatosBasicosCotiz(ParamObtenerDatosBasicosCotiza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodProducto() == null || param.getCodProducto().equals("") || param.getCodProducto().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerUbicacionBien(ParamObtenerUbicacionBien param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getConsecutivoBien() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListarVigenciaTecnica(ParamVigenciaTecnica param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("") || param.getCodRamo().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodProducto() == null || param.getCodProducto().equals("") || param.getCodProducto().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getTipoVigencia() == null || param.getCodProducto().equals("") || param.getCodProducto().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListarParentesco(ParamParentesco param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerBeneficiarios(ParamObtenerBeneficiarios param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null || param.getNumCotizacion().equals("") || param.getNumCotizacion().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("") || param.getNumCertificado().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumConsecutivoBien() == null || param.getNumConsecutivoBien().equals("") || param.getNumConsecutivoBien().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaObjetos(ParamObjetos param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodProducto() == null || param.getCodProducto().equals("null") || param.getCodProducto().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaMarcasObjeto(ParamMarcasObjeto param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave()))
			claveDeError = Values.CLAVEINCORRECTA;

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerListaBienes(ParamObtenerListaBienes param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null || param.getNumCotizacion().equals("") || param.getNumCotizacion().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("") || param.getNumCertificado().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumConsecutivoBien() == null || param.getNumConsecutivoBien().equals("") || param.getNumConsecutivoBien().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaGrupos(ParamListaGrupos param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave()))
			claveDeError = Values.CLAVEINCORRECTA;

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaTipoNota(ParamListaTipoNota param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodCircuito() == null || param.getCodCircuito().equals("null") || param.getCodCircuito().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerAnexosCotizacion(ParamObtenerAnexosCotizacion param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null || param.getNumCotizacion().equals("") || param.getNumCotizacion().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("") || param.getNumCertificado().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaBancos(ParamListaBancos param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumPersona() == null || param.getNumPersona().equals("null") || param.getNumPersona().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodMedioPago() == null || param.getCodMedioPago().equals("null") || param.getCodMedioPago().equals("")) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodOrigenPago() == null || param.getCodOrigenPago().equals("null") || param.getCodOrigenPago().equals("")) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerComponentes(ParamObtenerComponentes param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null || param.getNumCotizacion().equals("") || param.getNumCotizacion().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerFranquicias(ParamObtenerFranquicias param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null || param.getNumCotizacion().equals("") || param.getNumCotizacion().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("") || param.getNumCertificado().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerTextoClausula(ParamObtenerTextoClausula param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("") || param.getCodRamo().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodClausula() == null || param.getCodClausula().equals("") || param.getCodClausula().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaGenericaXTiposNota(ParamTiposNotaGenerica param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodCircuito() == null || param.getCodCircuito().equals("") || param.getCodCircuito().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodTipoNota() == null || param.getCodTipoNota().equals("") || param.getCodTipoNota().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getEnlace() == null || param.getEnlace().equals("") || param.getEnlace().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerNotasPoliza(ParamObtenerNotasPoliza param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getEnlace() == null || param.getEnlace().equals("") || param.getEnlace().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerDatosRenovacion(ParamObtenerDatosRenovacion param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("") || param.getCodRamo().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumPoliza() == null || param.getNumPoliza().equals("") || param.getNumPoliza().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerClientesSolicitante(ParamObtenerClientesSolicitante param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave()))
			claveDeError = Values.CLAVEINCORRECTA;
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerPlanesPago(ParamObtenerPlanesPago param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null || param.getNumCotizacion().equals("") || param.getNumCotizacion().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null || param.getNumCertificado().equals("") || param.getNumCertificado().equals(Values.NULL)) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerDatosModificacion(ParamDatosModificacion param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaTipoTexto(ParamTipoTexto param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodRamo() == null || param.getCodRamo().equals("")) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerTextosCotizacion(ParamTextosCotizacion param) {
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

	public ServiciosError validarObtenerDetalleTextoCotizacion(ParamDetalleTextoCotizacion param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumConsecutivoTexto() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodTexto() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerEncabezadoCotizacion(ParamEncabezadoCotizacion param) {
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

	public ServiciosError validarListaAcreedores(ParamAcreedor param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaAcreedorObjetos(ParamAcreedorObjetos param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (null==param.getNumCotizacion()) {
			claveDeError = Values.VALNULL;
		} else if (null==param.getNumCertificado()) {
			claveDeError = Values.VALNULL;
		} else if (null==param.getCodBienAsegurado()) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaTipoAcreedores(ParamTipoAcreedores param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerAcreedoresXBien(ParamObtenerAcreedoresXBien param) {
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

	public ServiciosError validarObtenerDatosPreviosEndoso(ParamObtenerDatosPreviosEndoso param) {
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

	public ServiciosError validarObtenerDomiciliosCliente(ParamObtenerDomiciliosCliente param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumPersona() == null) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerContratanteAsegurado(ParamObtenerContratanteAsegurado param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerPersonaSeleccionada(ParamPersonaSeleccionada param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumPersona() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getTipoCliente() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaDatosXtipoNota(ParamDatosXTipoNota param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodTipoNota() == null) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaValoresTipoNota(ParamValoresTipoNota param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCircuito() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getClave() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodValor() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getEnlace() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getTipoNota() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerPlanesCobertura(ParamObtenerPlanesCobertura param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getRequerido() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerDetalleCuotas(ParamObtenerDetalleCuota param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodPlanPago() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getMontoPremio() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getMontoPrima() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerResumenPlanesCobertura(ParamObtenerResumenPlanesCobertura param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerRenovacionXProducto(ParamRenovacionXProducto param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getCodProducto() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getCodRamo() == null) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerCoberturasXCertificado(ParamObtenerCoberturasXCertificado param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null) {
			claveDeError = Values.VALNULL;
		}else if (param.getNumBien() == null) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerDatosBancarios(ParamObtenerDatosBancarios param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}

		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarObtenerFechaEmision(ParamObtenerFechaEmision param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}
		return getErrorsByClave(claveDeError);
	}
	
	public ServiciosError validarObtenerCoberturasXBien(ParamObtenerCoberturasXBien param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		} else if (param.getNumCertificado() == null) {
			claveDeError = Values.VALNULL;
		}else if (param.getConsecutivoBien() == null) {
			claveDeError = Values.VALNULL;
		}
		
		return getErrorsByClave(claveDeError);
	}
	
	public ServiciosError validarListaAcreedoresFiltrados(ParamAcreedoresFiltrados param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		}else if (param.getFiltroNombre() == null|| param.getFiltroNombre().equals("")) {
			claveDeError = Values.VALNULL;
		} 
		
		return getErrorsByClave(claveDeError);
	}
	
	
	public ServiciosError validarObtenerDetalleBusquedaCotizacion(ParamDetalleBusquedaCotizacion param) {
		String claveDeError = null;

		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getNumCotizacion() == null) {
			claveDeError = Values.VALNULL;
		}

		return getErrorsByClave(claveDeError);
	}
	
	public ServiciosError validarObtenerCabezalCotizaciones(ParamObtenerCabezalCotizaciones param) {
		String claveDeError = null;
		if (!this.validarLogin(param.getUsuario(), param.getClave())) {
			claveDeError = Values.CLAVEINCORRECTA;
		} else if (param.getOrden() == null ) {
			claveDeError = Values.VALNULL;
		}
		return getErrorsByClave(claveDeError);
	}

	public ServiciosError validarListaNivelesComision(ParamNivelesComision param) {
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
	public ServiciosError validarListaNivelesComisionProd(ParamNivelesComisionProd param) {
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
			salida = ((result!=null) && (!result.getHayError()));
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return salida;
	}

	public ServiciosError validarListaPlanesPagoRecuotificacion(
			ParamListaPlanesPagoRen param) {
		// TODO Auto-generated method stub
		return null;
	}



	

	
	
	

	
}
