package uy.com.bse.cotizaciones.ws;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
import uy.com.bse.cotizaciones.consultas.ResultBonificacionNS;
import uy.com.bse.cotizaciones.consultas.ResultCertificados;
import uy.com.bse.cotizaciones.consultas.ResultClientes;
import uy.com.bse.cotizaciones.consultas.ResultCotizaciones;
import uy.com.bse.cotizaciones.consultas.ResultDatoParametrico;
import uy.com.bse.cotizaciones.consultas.ResultDatosCertificados;
import uy.com.bse.cotizaciones.consultas.ResultDatosCotizacion;
import uy.com.bse.cotizaciones.consultas.ResultDatosModificacion;
import uy.com.bse.cotizaciones.consultas.ResultDetalleTextoCotizacion;
import uy.com.bse.cotizaciones.consultas.ResultEncabezadoCotizacion;
import uy.com.bse.cotizaciones.consultas.ResultFueraPautas;
import uy.com.bse.cotizaciones.consultas.ResultListaPlanesPagoRen;
import uy.com.bse.cotizaciones.consultas.ResultObtenerAcreedoresXBien;
import uy.com.bse.cotizaciones.consultas.ResultObtenerAnexosCotizacion;
import uy.com.bse.cotizaciones.consultas.ResultObtenerBeneficiarios;
import uy.com.bse.cotizaciones.consultas.ResultObtenerBienes;
import uy.com.bse.cotizaciones.consultas.ResultObtenerCabezalCotizaciones;
import uy.com.bse.cotizaciones.consultas.ResultObtenerClientesSolicitante;
import uy.com.bse.cotizaciones.consultas.ResultObtenerCoberturasXBien;
import uy.com.bse.cotizaciones.consultas.ResultObtenerCoberturasXCertificado;
import uy.com.bse.cotizaciones.consultas.ResultObtenerComponentes;
import uy.com.bse.cotizaciones.consultas.ResultObtenerContratanteAsegurado;
import uy.com.bse.cotizaciones.consultas.ResultObtenerDatosBancarios;
import uy.com.bse.cotizaciones.consultas.ResultObtenerDatosBasicosCotiza;
import uy.com.bse.cotizaciones.consultas.ResultObtenerDatosPreviosEndoso;
import uy.com.bse.cotizaciones.consultas.ResultObtenerDatosRenovacion;
import uy.com.bse.cotizaciones.consultas.ResultObtenerDetalleBusquedaCotizacion;
import uy.com.bse.cotizaciones.consultas.ResultObtenerDetalleCuota;
import uy.com.bse.cotizaciones.consultas.ResultObtenerDomiciliosCliente;
import uy.com.bse.cotizaciones.consultas.ResultObtenerFechaEmision;
import uy.com.bse.cotizaciones.consultas.ResultObtenerFranquicias;
import uy.com.bse.cotizaciones.consultas.ResultObtenerListaBienes;
import uy.com.bse.cotizaciones.consultas.ResultObtenerNotasPoliza;
import uy.com.bse.cotizaciones.consultas.ResultObtenerPlanesCobertura;
import uy.com.bse.cotizaciones.consultas.ResultObtenerPlanesPago;
import uy.com.bse.cotizaciones.consultas.ResultObtenerResumenPlanesCobertura;
import uy.com.bse.cotizaciones.consultas.ResultObtenerTextoClausula;
import uy.com.bse.cotizaciones.consultas.ResultObtenerUbicacionBien;
import uy.com.bse.cotizaciones.consultas.ResultParentesco;
import uy.com.bse.cotizaciones.consultas.ResultPersonaSeleccionada;
import uy.com.bse.cotizaciones.consultas.ResultTextosCotizacion;
import uy.com.bse.cotizaciones.consultas.ResultVigenciaTecnica;
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
import uy.com.bse.cotizaciones.lovs.ResultAcreedor;
import uy.com.bse.cotizaciones.lovs.ResultAcreedorObjetos;
import uy.com.bse.cotizaciones.lovs.ResultDatos;
import uy.com.bse.cotizaciones.lovs.ResultDatosXTipoNota;
import uy.com.bse.cotizaciones.lovs.ResultListaBancos;
import uy.com.bse.cotizaciones.lovs.ResultListaEnPauta;
import uy.com.bse.cotizaciones.lovs.ResultListaGrupos;
import uy.com.bse.cotizaciones.lovs.ResultListaMarcasObjeto;
import uy.com.bse.cotizaciones.lovs.ResultListaMedioPago;
import uy.com.bse.cotizaciones.lovs.ResultListaModificada;
import uy.com.bse.cotizaciones.lovs.ResultListaModoCalculo;
import uy.com.bse.cotizaciones.lovs.ResultListaMoneda;
import uy.com.bse.cotizaciones.lovs.ResultListaObjetos;
import uy.com.bse.cotizaciones.lovs.ResultListaOrigenPago;
import uy.com.bse.cotizaciones.lovs.ResultListaProducto;
import uy.com.bse.cotizaciones.lovs.ResultListaPromocion;
import uy.com.bse.cotizaciones.lovs.ResultListaRenovacion;
import uy.com.bse.cotizaciones.lovs.ResultListaSecciones;
import uy.com.bse.cotizaciones.lovs.ResultListaTipo;
import uy.com.bse.cotizaciones.lovs.ResultListaTipoNota;
import uy.com.bse.cotizaciones.lovs.ResultListaTiposFacturacion;
import uy.com.bse.cotizaciones.lovs.ResultListaTodasSecciones;
import uy.com.bse.cotizaciones.lovs.ResultListaTodosProductos;
import uy.com.bse.cotizaciones.lovs.ResultListaValoresDato;
import uy.com.bse.cotizaciones.lovs.ResultListaVigencia;
import uy.com.bse.cotizaciones.lovs.ResultRenovacionXProducto;
import uy.com.bse.cotizaciones.lovs.ResultTipoAcreedores;
import uy.com.bse.cotizaciones.lovs.ResultTipoTexto;
import uy.com.bse.cotizaciones.lovs.ResultTiposNotaGenerica;
import uy.com.bse.cotizaciones.lovs.ResultValoresTipoNota;
import uy.com.bse.serviciosEJB.CotizacionLocal;
import uy.com.bse.utilitario.dato.ResultCodiguera;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.ErrorResolver;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.properties.EARPropertiesManager;
import uy.com.bse.utilitario.util.MailBuilder;

@WebService(endpointInterface = "uy.com.bse.cotizaciones.ws.IWsCotizaciones", serviceName = "WsCotizaciones")
public class WsCotizaciones implements IWsCotizaciones {

	private static final Logger LOG = LogManager.getLogger(WsCotizaciones.class);

	// Funcionalidades

	public ResultListaSecciones listaSecciones(final ParamSecciones param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("ListaSecciones");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultListaSecciones datos = new ResultListaSecciones();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarListaSecciones(param);
			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaSecciones(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		
		return datos;
	}

	public ResultListaProducto listaProducto(final ParamProducto param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("ListaProducto");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());

		ResultListaProducto datos = new ResultListaProducto();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarListaProducto(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaProducto(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultListaTipo listaTipo(final ParamTipo param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("ListaTipo");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultListaTipo datos = new ResultListaTipo();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarListaTipo(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaTipo(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultListaModificada listaModificada(final ParamModificada param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("ListaModificada");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultListaModificada datos = new ResultListaModificada();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarListaModificada(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaModificada(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultListaEnPauta listaEnPauta(final ParamEnPauta param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("ListaEnPauta");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultListaEnPauta datos = new ResultListaEnPauta();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarListaEnPauta(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaEnPauta(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultListaTiposFacturacion listaTiposFacturacion(final ParamTiposFacturacion param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("ListaTiposFacturacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultListaTiposFacturacion datos = new ResultListaTiposFacturacion();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarListaTiposFacturacion(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaTiposFacturacion(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultListaMedioPago listaMedioPago(final ParamMedioPago param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("ListaMedioPago");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultListaMedioPago datos = new ResultListaMedioPago();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarListaMedioPago(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaMedioPago(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultListaOrigenPago listaOrigenPago(final ParamOrigenPago param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("ListaOrigenPago");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo medio de pago", param.getCodMedioPago());

		ResultListaOrigenPago datos = new ResultListaOrigenPago();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarListaOrigenPago(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaOrigenPago(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultListaPromocion listaPromocion(final ParamPromocion param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("ListaPromocion");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo producto", param.getCodProducto());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero cliente", param.getCodCliente());
		logueo.setParametro("Codigo de productor", param.getCodProductor());
		ResultListaPromocion datos = new ResultListaPromocion();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarListaPromocion(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaPromocion(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultListaRenovacion listaRenovacion(final ParamRenovacion param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("ListaRenovacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultListaRenovacion datos = new ResultListaRenovacion();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarListaRenovacion(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaRenovacion(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultListaMoneda listaMoneda(final ParamMoneda param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("listaMoneda");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultListaMoneda datos = new ResultListaMoneda();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarListaMoneda(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaMoneda(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultListaModoCalculo listaModoCalculo(final ParamModoCalculo param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("ListaModoCalculo");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultListaModoCalculo datos = new ResultListaModoCalculo();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarListaModoCalculo(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaModoCalculo(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultListaVigencia listaVigencia(final ParamVigencia param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("ListaVigencia");
		logueo.setParametro("Producto", param.getCodProducto());
		logueo.setParametro("Ramo", param.getCodRamo());
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultListaVigencia datos = new ResultListaVigencia();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarListaVigencia(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaVigencia(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	// EMPIEZA VICKY A AGREGAR CODIGO
	/*
	 * Lista los posibles valores que puede tener un dato paramétrico de un bien
	 * de una cotización
	 */

	public ResultListaValoresDato listaValoresDatoParametrico(ParamListaValoresDato param) {

		ResultListaValoresDato datos = new ResultListaValoresDato();
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("listaValoresDatoParametrico");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Codigo dato", param.getCodDato());
		logueo.setParametro("Codigo tabla", param.getCodTabla());
		logueo.setParametro("Numero consecutivo del bien", param.getNumConsBien());
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarListaValoresDatoParametrico(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaValoresDatoParametrico(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;

	}

	public ResultClientes obtenerClientes(final ParamClientes param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerClientes");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de cliente", param.getCodCliente());

		ResultClientes datos = new ResultClientes();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarObtenerClientes(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerClientes(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultFueraPautas obtenerFueraPautas(final ParamFueraPautas param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerFueraPautas");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());

		ResultFueraPautas datos = new ResultFueraPautas();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarObtenerFueraPautas(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerFueraPautas(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultBonificacionNS obtenerBonificacionNS(final ParamBonificacionNS param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerBonificacionNS");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());

		ResultBonificacionNS datos = new ResultBonificacionNS();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarObtenerBonificacionNS(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerBonificacionNS(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultCotizaciones obtenerCotizaciones(ParamCotizaciones param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerCotizaciones");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de ramo", param.getRamo());
		logueo.setParametro("Fecha desde", param.getFechaDesde());
		logueo.setParametro("Fecha hasta", param.getFechaHasta());
		logueo.setParametro("Nombre cliente", param.getNombreCliente());
		logueo.setParametro("Apellido o razon social de cliente", param.getApellidoRazonSocialCliente());
		logueo.setParametro("Numero de cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero de poliza", param.getNumPoliza());
		logueo.setParametro("En pauta", param.getEnPauta());
		logueo.setParametro("Modificada", param.getModificada());
		logueo.setParametro("Producto", param.getProducto());
		logueo.setParametro("Tipo", param.getTipo());
		logueo.setParametro("Codigo moneda", param.getCodMoneda());
		logueo.setParametro("Orden", param.getOrden());

		ResultCotizaciones datos = new ResultCotizaciones();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			ServiciosError error = validar.validarObtenerCotizaciones(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerCotizaciones(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultDatosCotizacion obtenerDatosCotizacion(final ParamDatoCotizacion param) {
		String claveError = null;

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerDatosCotizacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());

		ResultDatosCotizacion datos = new ResultDatosCotizacion();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarObtenerDatosCotizacion(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerDatosCotizacion(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultCertificados obtenerCertificados(ParamCertificados param) {
		String claveError = null;

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerCertificados");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());

		ResultCertificados datos = new ResultCertificados();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarObtenerCertificados(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerCertificados(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultDatoParametrico obtenerDatoParametrico(ParamDatoParametrico param) {
		String claveError = null;

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerDatoParametrico");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Codigo de dato", param.getDatoCod());
		logueo.setParametro("Valor de dato", param.getDatoValor());

		ResultDatoParametrico datos = new ResultDatoParametrico();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarObtenerDatoParametrico(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerDatoParametrico(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultDatosCertificados obtenerDatosCertificados(ParamDatosCertificados param) {
		String claveError = null;

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerDatosCertificados");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero consecutivo", param.getNumCertificado());

		ResultDatosCertificados datos = new ResultDatosCertificados();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarObtenerDatosCertificados(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerDatosCertificados(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultObtenerBienes obtenerBienesParaAgregar(ParamObtenerBienes param) {
		String claveError = null;

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerBienesParaAgregar");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero consecutivo", param.getNumConsecutivo());
		logueo.setParametro("Producto", param.getCodProducto());
		logueo.setParametro("Ramo", param.getCodRamo());
		logueo.setParametro("Sucursal", param.getNumSucursal());

		ResultObtenerBienes datos = new ResultObtenerBienes();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarObtenerBienesParaAgregar(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerBienesParaAgregar(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultDatos listaDatos(ParamDatos param) {
		String claveError = null;

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("listaDatos");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Producto", param.getCodProducto());
		logueo.setParametro("Ramo", param.getCodRamo());

		ResultDatos datos = new ResultDatos();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarListarDatos(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaDatos(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultObtenerDatosBasicosCotiza obtenerDatosBasicosCotizacion(ParamObtenerDatosBasicosCotiza param) {
		String claveError = null;

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerDatosBasicosCotizacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Producto", param.getCodProducto());
		logueo.setParametro("Ramo", param.getCodRamo());

		ResultObtenerDatosBasicosCotiza datos = new ResultObtenerDatosBasicosCotiza();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarObtenerDatosBasicosCotiz(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerDatosBasicosCotizacion(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultObtenerUbicacionBien obtenerUbicacionBien(ParamObtenerUbicacionBien param) {
		String claveError = null;

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerUbicacionBien");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo bien", param.getConsecutivoBien());

		ResultObtenerUbicacionBien datos = new ResultObtenerUbicacionBien();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarObtenerUbicacionBien(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerUbicacionBien(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;

	}

	public ResultVigenciaTecnica listaVigenciaTecnica(ParamVigenciaTecnica param) {
		String claveError = null;

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("listaVigenciaTecnica");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Ramo", param.getCodRamo());
		logueo.setParametro("Producto", param.getCodProducto());
		logueo.setParametro("Vigencia", param.getTipoVigencia());

		ResultVigenciaTecnica datos = new ResultVigenciaTecnica();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarListarVigenciaTecnica(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaVigenciaTecnica(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultParentesco listaParentesco(ParamParentesco param) {
		String claveError = null;

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("listaParentesco");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultParentesco datos = new ResultParentesco();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarListarParentesco(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaParentesco(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultObtenerBeneficiarios obtenerBeneficiarios(ParamObtenerBeneficiarios param) {
		String claveError = null;

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerBeneficiarios");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo bien", param.getNumConsecutivoBien());

		ResultObtenerBeneficiarios datos = new ResultObtenerBeneficiarios();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarObtenerBeneficiarios(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerBeneficiarios(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultListaObjetos listaObjetos(ParamObjetos param) {
		String claveError = null;

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("listaObjetos");
		logueo.setParametro("Producto", param.getCodProducto());
		logueo.setParametro("Ramo", param.getCodRamo());
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultListaObjetos datos = new ResultListaObjetos();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarListaObjetos(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaObjetos(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultListaMarcasObjeto listaMarcasObjeto(ParamMarcasObjeto param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("listaMarcasObjeto");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultListaMarcasObjeto datos = new ResultListaMarcasObjeto();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarListaMarcasObjeto(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaMarcasObjeto(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;

	}

	public ResultObtenerListaBienes obtenerListaBienes(ParamObtenerListaBienes param) {
		String claveError = null;

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerListaBienes");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo bien", param.getNumConsecutivoBien());

		ResultObtenerListaBienes datos = new ResultObtenerListaBienes();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarObtenerListaBienes(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerListaBienes(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultListaGrupos listaGrupos(ParamListaGrupos param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("listaGrupos");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultListaGrupos datos = new ResultListaGrupos();

		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarListaGrupos(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaGrupos(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;

	}

	public ResultListaTipoNota listaTipoNota(ParamListaTipoNota param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("listaTipoNota");
		logueo.setParametro("Circuito", param.getCodCircuito());
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultListaTipoNota datos = new ResultListaTipoNota();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarListaTipoNota(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaTipoNota(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultObtenerAnexosCotizacion obtenerAnexosCotizacion(ParamObtenerAnexosCotizacion param) {
		String claveError = null;

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerAnexosCotizacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		ResultObtenerAnexosCotizacion datos = new ResultObtenerAnexosCotizacion();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarObtenerAnexosCotizacion(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerAnexosCotizacion(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultListaBancos listaBancos(ParamListaBancos param) {
		String claveError = null;

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("listaBancos");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero persona", param.getNumPersona());
		logueo.setParametro("Codigo del medio de pago", param.getCodMedioPago());
		logueo.setParametro("Codigo del origen de pago", param.getCodOrigenPago());

		ResultListaBancos datos = new ResultListaBancos();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarListaBancos(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaBancos(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultObtenerComponentes obtenerComponentes(ParamObtenerComponentes param) {
		String claveError = null;

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerComponentes");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());

		ResultObtenerComponentes datos = new ResultObtenerComponentes();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarObtenerComponentes(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerComponentes(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultObtenerFranquicias obtenerFranquicias(ParamObtenerFranquicias param) {
		String claveError = null;

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerFranquicias");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo del bien", param.getNumConsecutivoBien());

		ResultObtenerFranquicias datos = new ResultObtenerFranquicias();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			ServiciosError error = validar.validarObtenerFranquicias(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerFranquicias(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultObtenerTextoClausula obtenerTextoClausula(ParamObtenerTextoClausula param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerTextoClausula");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());
		logueo.setParametro("Codigo clausula", param.getCodClausula());

		ResultObtenerTextoClausula datos = new ResultObtenerTextoClausula();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			ServiciosError error = validar.validarObtenerTextoClausula(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerTextoClausula(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultTiposNotaGenerica listaGenericaXTiposNota(ParamTiposNotaGenerica param) {
		String claveError = null;

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("listaGenericaXTiposNota");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo tipo nota ", param.getCodTipoNota());
		logueo.setParametro("Codigo circuito", param.getCodCircuito());
		logueo.setParametro("Enlace", param.getEnlace());

		ResultTiposNotaGenerica datos = new ResultTiposNotaGenerica();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			ServiciosError error = validar.validarListaGenericaXTiposNota(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaGenericaXTiposNota(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultObtenerNotasPoliza obtenerNotasPoliza(ParamObtenerNotasPoliza param) {
		String claveError = null;

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerNotasPoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Enlace", param.getEnlace());

		ResultObtenerNotasPoliza datos = new ResultObtenerNotasPoliza();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			ServiciosError error = validar.validarObtenerNotasPoliza(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerNotasPoliza(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultObtenerDatosRenovacion obtenerDatosRenovacion(ParamObtenerDatosRenovacion param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerDatosRenovacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());

		ResultObtenerDatosRenovacion datos = new ResultObtenerDatosRenovacion();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			ServiciosError error = validar.validarObtenerDatosRenovacion(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerDatosRenovacion(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultObtenerClientesSolicitante obtenerClientesSolicitante(ParamObtenerClientesSolicitante param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerClientesSolicitante");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Apellido o razon", param.getApellidoRazon());
		logueo.setParametro("Nombre", param.getNombre());
		logueo.setParametro("Codigo cliente", param.getCodCliente());
		logueo.setParametro("Tipo documento", param.getTipoDocumento());
		logueo.setParametro("Numero documento", param.getNumDocumento());
		logueo.setParametro("RUT", param.getRut());

		ResultObtenerClientesSolicitante datos = new ResultObtenerClientesSolicitante();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			ServiciosError error = validar.validarObtenerClientesSolicitante(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerClientesSolicitante(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultObtenerPlanesPago obtenerPlanesPago(ParamObtenerPlanesPago param) {
		String claveError = null;

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerPlanesPago");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		ResultObtenerPlanesPago datos = new ResultObtenerPlanesPago();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarObtenerPlanesPago(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerPlanesPago(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultDatosModificacion obtenerDatosModificacion(ParamDatosModificacion param) {
		String claveError = null;

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerDatosModificacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero consecutivo", param.getNumConsecutivoBien());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		ResultDatosModificacion datos = new ResultDatosModificacion();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarObtenerDatosModificacion(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerDatosModificacion(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultTipoTexto listaTipoTexto(ParamTipoTexto param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("listaTipoTexto");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());

		ResultTipoTexto datos = new ResultTipoTexto();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			ServiciosError error = validar.validarListaTipoTexto(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaTipoTexto(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultTextosCotizacion obtenerTextosCotizacion(ParamTextosCotizacion param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerTextosCotizacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		ResultTextosCotizacion datos = new ResultTextosCotizacion();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			ServiciosError error = validar.validarObtenerTextosCotizacion(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerTextosCotizacion(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultDetalleTextoCotizacion obtenerDetalleTextoCotizacion(ParamDetalleTextoCotizacion param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerDetalleTextoCotizacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo del texto", param.getNumConsecutivoTexto());
		logueo.setParametro("Codigo texto", param.getCodTexto());

		ResultDetalleTextoCotizacion datos = new ResultDetalleTextoCotizacion();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			ServiciosError error = validar.validarObtenerDetalleTextoCotizacion(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerDetalleTextoCotizacion(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultEncabezadoCotizacion obtenerEncabezadoCotizacion(ParamEncabezadoCotizacion param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerEncabezadoCotizacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo del bien", param.getNumConsecutivoBien());

		ResultEncabezadoCotizacion datos = new ResultEncabezadoCotizacion();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			ServiciosError error = validar.validarObtenerEncabezadoCotizacion(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerEncabezadoCotizacion(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultAcreedor listaAcreedores(ParamAcreedor param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("listaAcreedores");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultAcreedor datos = new ResultAcreedor();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			ServiciosError error = validar.validarListaAcreedores(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaAcreedores(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultAcreedorObjetos listaAcreedorObjetos(ParamAcreedorObjetos param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("listaAcreedorObjetos");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero de cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero de certificado", param.getNumCertificado());
		logueo.setParametro("Codigo del bien del asegurado", param.getCodBienAsegurado());

		ResultAcreedorObjetos datos = new ResultAcreedorObjetos();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			ServiciosError error = validar.validarListaAcreedorObjetos(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaAcreedorObjetos(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultTipoAcreedores listaTipoAcreedores(ParamTipoAcreedores param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("listaTipoAcreedores");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultTipoAcreedores datos = new ResultTipoAcreedores();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			ServiciosError error = validar.validarListaTipoAcreedores(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaTipoAcreedores(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultObtenerAcreedoresXBien obtenerAcreedoresXBien(ParamObtenerAcreedoresXBien param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerAcreedoresXBien");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo del bien", param.getNumConsecutivoBien());

		ResultObtenerAcreedoresXBien datos = new ResultObtenerAcreedoresXBien();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			ServiciosError error = validar.validarObtenerAcreedoresXBien(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerAcreedoresXBien(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultObtenerDatosPreviosEndoso obtenerDatosPreviosEndoso(ParamObtenerDatosPreviosEndoso param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerDatosPreviosEndoso");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());

		ResultObtenerDatosPreviosEndoso datos = new ResultObtenerDatosPreviosEndoso();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			ServiciosError error = validar.validarObtenerDatosPreviosEndoso(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerDatosPreviosEndoso(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultObtenerDomiciliosCliente obtenerDomiciliosCliente(ParamObtenerDomiciliosCliente param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerDomiciliosCliente");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero persona", param.getNumPersona());

		ResultObtenerDomiciliosCliente datos = new ResultObtenerDomiciliosCliente();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			ServiciosError error = validar.validarObtenerDomiciliosCliente(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerDomiciliosCliente(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultObtenerContratanteAsegurado obtenerContratanteAsegurado(ParamObtenerContratanteAsegurado param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerContratanteAsegurado");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Apellido Razon Social", param.getApellidoRazonSocial());
		logueo.setParametro("Numero documento", param.getNumDocumento());
		logueo.setParametro("Rut", param.getRut());
		logueo.setParametro("Tipo documento", param.getTipoDocumento());
		logueo.setParametro("Nombre", param.getNombre());

		ResultObtenerContratanteAsegurado datos = new ResultObtenerContratanteAsegurado();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			ServiciosError error = validar.validarObtenerContratanteAsegurado(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerContratanteAsegurado(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultPersonaSeleccionada obtenerPersonaSeleccionada(ParamPersonaSeleccionada param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerPersonaSeleccionada");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero persona", param.getNumPersona());
		logueo.setParametro("Tipo cliente", param.getTipoCliente());

		ResultPersonaSeleccionada datos = new ResultPersonaSeleccionada();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			ServiciosError error = validar.validarObtenerPersonaSeleccionada(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerPersonaSeleccionada(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultDatosXTipoNota listaDatosXTipoNota(ParamDatosXTipoNota param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("listaDatosXTipoNota");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo tipo nota", param.getCodTipoNota());

		ResultDatosXTipoNota datos = new ResultDatosXTipoNota();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			ServiciosError error = validar.validarListaDatosXtipoNota(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaDatosXTipoNota(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}
		return datos;
	}

	public ResultValoresTipoNota listaValoresTipoNota(ParamValoresTipoNota param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("listaValoresTipoNota");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Circuito", param.getCircuito());
		logueo.setParametro("Valor", param.getCodValor());
		logueo.setParametro("Enlace", param.getEnlace());
		logueo.setParametro("Tipo de nota", param.getTipoNota());

		ResultValoresTipoNota datos = new ResultValoresTipoNota();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			ServiciosError error = validar.validarListaValoresTipoNota(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaValoresTipoNota(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultObtenerPlanesCobertura obtenerPlanesCobertura(ParamObtenerPlanesCobertura param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerPlanesCobertura");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Requerdio", param.getRequerido());

		ResultObtenerPlanesCobertura datos = new ResultObtenerPlanesCobertura();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			ServiciosError error = validar.validarObtenerPlanesCobertura(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerPlanesCobertura(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultObtenerDetalleCuota obtenerDetalleCuotas(ParamObtenerDetalleCuota param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerDetalleCuotas");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Codigo plan pago", param.getCodPlanPago());
		logueo.setParametro("Dia vencimiento", param.getDiaVto());
		logueo.setParametro("Monto prima", param.getMontoPrima());
		logueo.setParametro("Monto premio", param.getMontoPremio());

		ResultObtenerDetalleCuota datos = new ResultObtenerDetalleCuota();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			ServiciosError error = validar.validarObtenerDetalleCuotas(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerDetalleCuotas(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultObtenerResumenPlanesCobertura obtenerResumenPlanesCobertura(ParamObtenerResumenPlanesCobertura param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerResumenPlanesCobertura");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		ResultObtenerResumenPlanesCobertura datos = new ResultObtenerResumenPlanesCobertura();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			ServiciosError error = validar.validarObtenerResumenPlanesCobertura(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerResumenPlanesCobertura(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultRenovacionXProducto obtenerRenovacionXProducto(ParamRenovacionXProducto param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerRenovacionXProducto");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Codigo producto", param.getCodProducto());

		ResultRenovacionXProducto datos = new ResultRenovacionXProducto();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			ServiciosError error = validar.validarObtenerRenovacionXProducto(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerRenovacionXProducto(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultListaTodasSecciones listaTodasSecciones(ParamListaTodasSecciones param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("listaTodasSecciones");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultListaTodasSecciones datos = new ResultListaTodasSecciones();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarListaTodasSecciones(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaTodasSecciones(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultListaTodosProductos listaTodosProductos(ParamListaTodosProductos param) {
		String claveError = null;
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("listaTodosProductos");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());

		ResultListaTodosProductos datos = new ResultListaTodosProductos();
		LOG.info(logueo.getSoloParametros());
		try {
			final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			final ServiciosError error = validar.validarListaTodosProductos(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().listaTodosProductos(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultObtenerCoberturasXCertificado obtenerCoberturasXCertificado(ParamObtenerCoberturasXCertificado param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerCoberturasXCertificado");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero bien", param.getNumBien());

		ResultObtenerCoberturasXCertificado datos = new ResultObtenerCoberturasXCertificado();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			ServiciosError error = validar.validarObtenerCoberturasXCertificado(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerCoberturasXCertificado(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultObtenerDatosBancarios obtenerDatosBancarios(ParamObtenerDatosBancarios param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerDatosBancarios");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo cliente", param.getCodCliente());
		logueo.setParametro("Codigo medio pago", param.getCodMedioPago());
		logueo.setParametro("Codigo origen", param.getCodOrigen());

		ResultObtenerDatosBancarios datos = new ResultObtenerDatosBancarios();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			ServiciosError error = validar.validarObtenerDatosBancarios(param);
			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerDatosBancarios(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}

	public ResultObtenerFechaEmision obtenerFechaEmision(ParamObtenerFechaEmision param) {
		String claveError = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerFechaEmision");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultObtenerFechaEmision datos = new ResultObtenerFechaEmision();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			ServiciosError error = validar.validarObtenerFechaEmision(param);
			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerFechaEmision(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}
	
	public ResultObtenerCoberturasXBien obtenerCoberturasXBien(ParamObtenerCoberturasXBien param) {
		String claveError = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(WsCotizaciones.class);
		logueo.setMetodo("obtenerCoberturasXBien");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		ResultObtenerCoberturasXBien datos = new ResultObtenerCoberturasXBien();
		LOG.info(logueo.getSoloParametros());
		try {
			ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
			ServiciosError error = validar.validarObtenerCoberturasXBien(param);

			if (error == null) {
				datos = WsCotizaciones.getEJBManager().obtenerCoberturasXBien(param);
			} else {
				datos.setError(error);
				datos.setHayError(Boolean.TRUE);
			}
			
		} catch (Exception e) {
			claveError = catchException(logueo, e);
		} finally {
			finallyBlock(claveError, datos);
		}

		return datos;
	}
	
	


	// Utiles

	public static CotizacionLocal getEJBManager() throws NamingException {
		final InitialContext ctx = new InitialContext();
		return (CotizacionLocal) ctx.lookup("global/ServiciosRector/IRectorEJB/Cotizacion!uy.com.bse.serviciosEJB.CotizacionLocal");
	}

	private String catchException(Logueo logueo, Exception e) {
		String claveError = Values.CLWEBSERVEXCP;
		logueo.setException(Values.EXCEPTION);
		logueo.setError(e.getMessage());
		LOG.error(logueo.getMensaje(),e);
		enviarMail( logueo);
		return claveError;
	}

	private void finallyBlock(String claveError, ResultGenerico datos) {
		if (claveError != null) {
			datos.setHayError(Boolean.TRUE);
			datos.setError(ErrorResolver.getError(claveError));
		}
	}

	//FIXME OIGRES... ESTO POR PRUEBAS... NO PONER EN PRODUCCION
		private void enviarMail(Logueo logueo) {
			try {
				EARPropertiesManager manager= new EARPropertiesManager("configServiciosRector.properties");
				MailBuilder.create().sentToDefaultConfigureMail(manager.obtenerValor("serviciosRector.mail.soporte"),manager.obtenerValor("serviciosRector.ccmail.soporte"),logueo.getMensaje(), "SERVICIO COTIZACIONES Error  BSE Ambiente: " + InetAddress.getLocalHost().getHostName());
			} catch (UnknownHostException e) {
				LOG.error("Error en m\u00e9todo enviarMail", e);
			}
		}

		public ResultAcreedor listaAcreedoresFiltrados(ParamAcreedoresFiltrados param) {
			String claveError = null;
			final Logueo logueo = new Logueo();
			logueo.setEncabezado(Values.ENCABEZADOWS);
			logueo.setClase(WsCotizaciones.class);
			logueo.setMetodo("listaAcreedoresFiltrados");
			logueo.setParametro(Values.USUARIO, param.getUsuario());
			logueo.setParametro(Values.CLAVE, param.getClave());
			logueo.setParametro("FiltroNombre", param.getFiltroNombre());

			ResultAcreedor datos = new ResultAcreedor();
			LOG.info(logueo.getSoloParametros());
			try {
				final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
				final ServiciosError error = validar.validarListaAcreedoresFiltrados(param);

				if (error == null) {
					datos = WsCotizaciones.getEJBManager().listaAcreedoresFiltrados(param);
				} else {
					datos.setError(error);
					datos.setHayError(Boolean.TRUE);
				}
				
			} catch (Exception e) {
				claveError = catchException(logueo, e);
			} finally {
				finallyBlock(claveError, datos);
			}

			return datos;
		}

		public ResultObtenerCabezalCotizaciones obtenerCabezalCotizaciones(ParamObtenerCabezalCotizaciones param) {
			String claveError = null;
			final Logueo logueo = new Logueo();
			logueo.setEncabezado(Values.ENCABEZADOWS);
			logueo.setClase(WsCotizaciones.class);
			logueo.setMetodo("obtenerCabezalCotizaciones");
			logueo.setParametro(Values.USUARIO, param.getUsuario());
			logueo.setParametro(Values.CLAVE, param.getClave());
			logueo.setParametro("Codigo productor", param.getCodProductor());
			logueo.setParametro("Codigo de ramo", param.getRamo());
			logueo.setParametro("Fecha desde", param.getFechaDesde());
			logueo.setParametro("Fecha hasta", param.getFechaHasta());
			logueo.setParametro("Nombre cliente", param.getNombreCliente());
			logueo.setParametro("Apellido o razon social de cliente", param.getApellidoRazonSocialCliente());
			logueo.setParametro("Numero de cotizacion", param.getNumCotizacion());
			logueo.setParametro("Numero de poliza", param.getNumPoliza());
			logueo.setParametro("En pauta", param.getEnPauta());
			logueo.setParametro("Modificada", param.getModificada());
			logueo.setParametro("Producto", param.getProducto());
			logueo.setParametro("Tipo", param.getTipo());
			logueo.setParametro("Codigo moneda", param.getCodMoneda());
			logueo.setParametro("Orden", param.getOrden());

			ResultObtenerCabezalCotizaciones datos = new ResultObtenerCabezalCotizaciones();
			LOG.info(logueo.getSoloParametros());
			try {
				ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
				ServiciosError error = validar.validarObtenerCabezalCotizaciones(param);

				if (error == null) {
					datos = WsCotizaciones.getEJBManager().obtenerCabezalCotizaciones(param);
				} else {
					datos.setError(error);
					datos.setHayError(Boolean.TRUE);
				}
				
			} catch (Exception e) {
				claveError = catchException(logueo, e);
			} finally {
				finallyBlock(claveError, datos);
			}

			return datos;
		}

		public ResultObtenerDetalleBusquedaCotizacion obtenerDetalleBusquedaCotizacion(ParamDetalleBusquedaCotizacion param) {
			String claveError = null;
			Logueo logueo = new Logueo();
			logueo.setEncabezado(Values.ENCABEZADOWS);
			logueo.setClase(WsCotizaciones.class);
			logueo.setMetodo("obtenerDetalleBusquedaCotizacion");
			logueo.setParametro(Values.USUARIO, param.getUsuario());
			logueo.setParametro(Values.CLAVE, param.getClave());
			logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		

			ResultObtenerDetalleBusquedaCotizacion datos = new ResultObtenerDetalleBusquedaCotizacion();
			LOG.info(logueo.getSoloParametros());
			try {
				ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
				ServiciosError error = validar.validarObtenerDetalleBusquedaCotizacion(param);

				if (error == null) {
					datos = WsCotizaciones.getEJBManager().obtenerDetalleBusquedaCotizacion(param);
				} else {
					datos.setError(error);
					datos.setHayError(Boolean.TRUE);
				}
				
			} catch (Exception e) {
				claveError = catchException(logueo, e);
			} finally {
				finallyBlock(claveError, datos);
			}

			return datos;

		}

		public ResultCodiguera listaNivelesComision(ParamNivelesComision param) {
			String claveError = null;
			final Logueo logueo = new Logueo();
			logueo.setEncabezado(Values.ENCABEZADOWS);
			logueo.setClase(WsCotizaciones.class);
			logueo.setMetodo("listaNivelesComision");
			logueo.setParametro(Values.USUARIO, param.getUsuario());
			logueo.setParametro(Values.CLAVE, param.getClave());
			logueo.setParametro("Codigo de ramo", param.getCodRamo());
			logueo.setParametro("Codigo de producto", param.getCodProducto());

			ResultCodiguera datos = new ResultCodiguera();
			LOG.info(logueo.getSoloParametros());
			try {
				final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
				final ServiciosError error = validar.validarListaNivelesComision(param);

				if (error == null) {
					datos = WsCotizaciones.getEJBManager().listaNivelesComision(param);
				} else {
					datos.setError(error);
					datos.setHayError(Boolean.TRUE);
				}
				
			} catch (Exception e) {
				claveError = catchException(logueo, e);
			} finally {
				finallyBlock(claveError, datos);
			}

			return datos;
		}
		
		public ResultCodiguera listaNivelesComisionProd(ParamNivelesComisionProd param) {
			String claveError = null;
			final Logueo logueo = new Logueo();
			logueo.setEncabezado(Values.ENCABEZADOWS);
			logueo.setClase(WsCotizaciones.class);
			logueo.setMetodo("listaNivelesComisionProd");
			logueo.setParametro(Values.USUARIO, param.getUsuario());
			logueo.setParametro(Values.CLAVE, param.getClave());
			logueo.setParametro("Codigo de ramo", param.getCodRamo());
			logueo.setParametro("Codigo de producto", param.getCodProducto());

			ResultCodiguera datos = new ResultCodiguera();
			LOG.info(logueo.getSoloParametros());
			try {
				final ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
				final ServiciosError error = validar.validarListaNivelesComisionProd(param);

				if (error == null) {
					datos = WsCotizaciones.getEJBManager().listaNivelesComisionProd(param);
				} else {
					datos.setError(error);
					datos.setHayError(Boolean.TRUE);
				}
				
			} catch (Exception e) {
				claveError = catchException(logueo, e);
			} finally {
				finallyBlock(claveError, datos);
			}

			return datos;
		}
		
		public ResultListaPlanesPagoRen listaPlanesPagoRenovacion(ParamListaPlanesPagoRen param) {
			String claveError = null;

			Logueo logueo = new Logueo();
			logueo.setEncabezado(Values.ENCABEZADOWS);
			logueo.setClase(WsCotizaciones.class);
			logueo.setMetodo("listaPlanesPagoRenovacion");
			logueo.setParametro(Values.USUARIO, param.getUsuario());
			logueo.setParametro(Values.CLAVE, param.getClave());
			logueo.setParametro("Numero de certificado", param.getNumCertificado());
			logueo.setParametro("Numero de poliza", param.getNumPoliza());
			logueo.setParametro("Codigo de ramo", param.getCodRamo());
			logueo.setParametro("Numero de endoso", param.getNumEndoso());

			ResultListaPlanesPagoRen datos = new ResultListaPlanesPagoRen();
			LOG.info(logueo.getSoloParametros());
			try {
				ValidacionesCotizaciones validar = new ValidacionesCotizaciones();
				ServiciosError error = validar.validarListaPlanesPagoRecuotificacion(param);

				if (error == null) {
					datos = WsCotizaciones.getEJBManager().listaPlanesPagoRenovacion(param);
				} else {
					datos.setError(error);
					datos.setHayError(Boolean.TRUE);
				}

			} catch (Exception e) {
				claveError = catchException(logueo, e);
			} finally {
				finallyBlock(claveError, datos);
			}

			return datos;
		}


		
}
