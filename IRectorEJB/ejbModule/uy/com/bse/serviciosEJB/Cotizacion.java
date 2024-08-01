package uy.com.bse.serviciosEJB;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.LogicaRector;
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
import uy.com.bse.cotizaciones.lovs.ParamListaValoresDatoVehiculo;
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
import uy.com.bse.utilitario.dato.ResultCodiguera;
import uy.com.bse.utilitario.dato.ResultGenerico;

@Stateless
public class Cotizacion implements CotizacionLocal {

	private static Logger log = LogManager.getLogger(Cotizacion.class);

	public Cotizacion() {
		super();
	}
	
	@Interceptors(UserInterceptor.class)
	public ResultObtenerDomiciliosCliente obtenerDomiciliosCliente(ParamObtenerDomiciliosCliente param) {
		log.debug("obtenerDomiciliosCliente start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDomiciliosCliente end: " + param);
		return (ResultObtenerDomiciliosCliente) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerContratanteAsegurado obtenerContratanteAsegurado(ParamObtenerContratanteAsegurado param) {
		log.debug("obtenerContratanteAsegurado start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerContratanteAsegurado end: " + param);
		return (ResultObtenerContratanteAsegurado) result;
	}

	@Interceptors(UserInterceptor.class)
	public ResultPersonaSeleccionada obtenerPersonaSeleccionada(ParamPersonaSeleccionada param) {
		log.debug("obtenerPersonaSeleccionada start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerPersonaSeleccionada end: " + param);
		return (ResultPersonaSeleccionada) result;
	}

	@Interceptors(UserInterceptor.class)
	public ResultDatosXTipoNota listaDatosXTipoNota(ParamDatosXTipoNota param) {
		log.debug("listaDatosXTipoNota start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaDatosXTipoNota end: " + param);
		return (ResultDatosXTipoNota) result;
	}

	@Interceptors(UserInterceptor.class)
	public ResultValoresTipoNota listaValoresTipoNota(ParamValoresTipoNota param) {
		log.debug("listaValoresTipoNota start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaValoresTipoNota end: " + param);
		return (ResultValoresTipoNota) result;
	}

	
	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultObtenerPlanesCobertura obtenerPlanesCobertura(ParamObtenerPlanesCobertura param) {
		log.debug("obtenerPlanesCobertura start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerPlanesCobertura end: " + param);
		return (ResultObtenerPlanesCobertura) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerDetalleCuota obtenerDetalleCuotas(ParamObtenerDetalleCuota param) {
		log.debug("obtenerDetalleCuotas start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDetalleCuotas end: " + param);
		return (ResultObtenerDetalleCuota) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultObtenerResumenPlanesCobertura obtenerResumenPlanesCobertura(ParamObtenerResumenPlanesCobertura param) {
		log.debug("obtenerResumenPlanesCobertura start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerResumenPlanesCobertura end: " + param);
		return (ResultObtenerResumenPlanesCobertura) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultRenovacionXProducto obtenerRenovacionXProducto(ParamRenovacionXProducto param) {
		log.debug("obtenerRenovacionXProducto start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerRenovacionXProducto end: " + param);
		return (ResultRenovacionXProducto) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultObtenerCoberturasXCertificado obtenerCoberturasXCertificado(ParamObtenerCoberturasXCertificado param) {
		log.debug("obtenerCoberturasXCertificado start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerCoberturasXCertificado end: " + param);
		return (ResultObtenerCoberturasXCertificado) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerDatosBancarios obtenerDatosBancarios(ParamObtenerDatosBancarios param) {
		log.debug("obtenerDatosBancarios start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDatosBancarios end: " + param);
		return (ResultObtenerDatosBancarios) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerFechaEmision obtenerFechaEmision(ParamObtenerFechaEmision param) {
		log.debug("obtenerFechaEmision start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerFechaEmision end: " + param);
		return (ResultObtenerFechaEmision) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaSecciones listaSecciones(ParamSecciones param) {
		log.debug("listaSecciones start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaSecciones end: " + param);
		return (ResultListaSecciones) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaProducto listaProducto(ParamProducto param) {
		log.debug("listaProducto start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaProducto end: " + param);
		return (ResultListaProducto) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaTipo listaTipo(ParamTipo param) {
		log.debug("listaTipo start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaTipo end: " + param);
		return (ResultListaTipo) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaModificada listaModificada(ParamModificada param) {
		log.debug("listaModificada start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaModificada end: " + param);
		return (ResultListaModificada) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaEnPauta listaEnPauta(ParamEnPauta param) {
		log.debug("listaEnPauta start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaEnPauta end: " + param);
		return (ResultListaEnPauta) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaTiposFacturacion listaTiposFacturacion(ParamTiposFacturacion param) {
		log.debug("listaTiposFacturacion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaTiposFacturacion end: " + param);
		return (ResultListaTiposFacturacion) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaMedioPago listaMedioPago(ParamMedioPago param) {
		log.debug("listaMedioPago start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaMedioPago end: " + param);
		return (ResultListaMedioPago) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaOrigenPago listaOrigenPago(ParamOrigenPago param) {
		log.debug("listaOrigenPago start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaOrigenPago end: " + param);
		return (ResultListaOrigenPago) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaPromocion listaPromocion(ParamPromocion param) {
		log.debug("listaPromocion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaPromocion end: " + param);
		return (ResultListaPromocion) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaRenovacion listaRenovacion(ParamRenovacion param) {
		log.debug("listaRenovacion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaRenovacion end: " + param);
		return (ResultListaRenovacion) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaMoneda listaMoneda(ParamMoneda param) {
		log.debug("listaMoneda start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaMoneda end: " + param);
		return (ResultListaMoneda) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaModoCalculo listaModoCalculo(ParamModoCalculo param) {
		log.debug("listaModoCalculo start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaModoCalculo end: " + param);
		return (ResultListaModoCalculo) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaVigencia listaVigencia(ParamVigencia param) {
		log.debug("listaVigencia start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaVigencia end: " + param);
		return (ResultListaVigencia) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaValoresDato listaValoresDatoParametrico(ParamListaValoresDato param) {
		log.debug("listaValoresDatoParametrico start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaValoresDatoParametrico end: " + param);
		return (ResultListaValoresDato) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultDatos listaDatos(ParamDatos param) {
		log.debug("listaDatos start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaDatos end: " + param);
		return (ResultDatos) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultClientes obtenerClientes(ParamClientes param) {
		log.debug("obtenerClientes start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerClientes end: " + param);
		return (ResultClientes) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultFueraPautas obtenerFueraPautas(ParamFueraPautas param) {
		log.debug("obtenerFueraPautas start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerFueraPautas end: " + param);
		return (ResultFueraPautas) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultBonificacionNS obtenerBonificacionNS(ParamBonificacionNS param) {
		log.debug("obtenerBonificacionNS start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerBonificacionNS end: " + param);
		return (ResultBonificacionNS) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultCotizaciones obtenerCotizaciones(ParamCotizaciones param) {
		log.debug("obtenerCotizaciones start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerCotizaciones end: " + param);
		return (ResultCotizaciones) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultDatosCotizacion obtenerDatosCotizacion(ParamDatoCotizacion param) {
		log.debug("obtenerDatosCotizacion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDatosCotizacion end: " + param);
		return (ResultDatosCotizacion) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultCertificados obtenerCertificados(ParamCertificados param) {
		log.debug("obtenerCertificados start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerCertificados end: " + param);
		return (ResultCertificados) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultDatoParametrico obtenerDatoParametrico(ParamDatoParametrico param) {
		log.debug("obtenerDatoParametrico start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDatoParametrico end: " + param);
		return (ResultDatoParametrico) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultDatosCertificados obtenerDatosCertificados(ParamDatosCertificados param) {
		log.debug("obtenerDatosCertificados start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDatosCertificados end: " + param);
		return (ResultDatosCertificados) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerBienes obtenerBienesParaAgregar(ParamObtenerBienes param) {
		log.debug("obtenerBienesParaAgregar start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerBienesParaAgregar end: " + param);
		return (ResultObtenerBienes) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerDatosBasicosCotiza obtenerDatosBasicosCotizacion(ParamObtenerDatosBasicosCotiza param) {
		log.debug("obtenerDatosBasicosCotizacion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDatosBasicosCotizacion end: " + param);
		return (ResultObtenerDatosBasicosCotiza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerUbicacionBien obtenerUbicacionBien(ParamObtenerUbicacionBien param) {
		log.debug("obtenerUbicacionBien start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerUbicacionBien end: " + param);
		return (ResultObtenerUbicacionBien) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultVigenciaTecnica listaVigenciaTecnica(ParamVigenciaTecnica param) {
		log.debug("listaVigenciaTecnica start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaVigenciaTecnica end: " + param);
		return (ResultVigenciaTecnica) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultParentesco listaParentesco(ParamParentesco param) {
		log.debug("listaParentesco start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaParentesco end: " + param);
		return (ResultParentesco) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerBeneficiarios obtenerBeneficiarios(ParamObtenerBeneficiarios param) {
		log.debug("obtenerBeneficiarios start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerBeneficiarios end: " + param);
		return (ResultObtenerBeneficiarios) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaObjetos listaObjetos(ParamObjetos param) {
		log.debug("listaObjetos start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaObjetos end: " + param);
		return (ResultListaObjetos) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaMarcasObjeto listaMarcasObjeto(ParamMarcasObjeto param) {
		log.debug("listaMarcasObjeto start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaMarcasObjeto end: " + param);
		return (ResultListaMarcasObjeto) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerListaBienes obtenerListaBienes(ParamObtenerListaBienes param) {
		log.debug("obtenerListaBienes start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerListaBienes end: " + param);
		return (ResultObtenerListaBienes) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaGrupos listaGrupos(ParamListaGrupos param) {
		log.debug("listaGrupos start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaGrupos end: " + param);
		return (ResultListaGrupos) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaTipoNota listaTipoNota(ParamListaTipoNota param) {
		log.debug("listaTipoNota start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaTipoNota end: " + param);
		return (ResultListaTipoNota) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerAnexosCotizacion obtenerAnexosCotizacion(ParamObtenerAnexosCotizacion param) {
		log.debug("obtenerAnexosCotizacion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerAnexosCotizacion end: " + param);
		return (ResultObtenerAnexosCotizacion) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaBancos listaBancos(ParamListaBancos param) {
		log.debug("listaBancos start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaBancos end: " + param);
		return (ResultListaBancos) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerComponentes obtenerComponentes(ParamObtenerComponentes param) {
		log.debug("obtenerComponentes start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerComponentes end: " + param);
		return (ResultObtenerComponentes) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerFranquicias obtenerFranquicias(ParamObtenerFranquicias param) {
		log.debug("obtenerFranquicias start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerFranquicias end: " + param);
		return (ResultObtenerFranquicias) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerTextoClausula obtenerTextoClausula(ParamObtenerTextoClausula param) {
		log.debug("obtenerTextoClausula start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerTextoClausula end: " + param);
		return (ResultObtenerTextoClausula) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultTiposNotaGenerica listaGenericaXTiposNota(ParamTiposNotaGenerica param) {
		log.debug("listaGenericaXTiposNota start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaGenericaXTiposNota end: " + param);
		return (ResultTiposNotaGenerica) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerNotasPoliza obtenerNotasPoliza(ParamObtenerNotasPoliza param) {
		log.debug("obtenerNotasPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerNotasPoliza end: " + param);
		return (ResultObtenerNotasPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerDatosRenovacion obtenerDatosRenovacion(ParamObtenerDatosRenovacion param) {
		log.debug("obtenerDatosRenovacion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDatosRenovacion end: " + param);
		return (ResultObtenerDatosRenovacion) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerClientesSolicitante obtenerClientesSolicitante(ParamObtenerClientesSolicitante param) {
		log.debug("obtenerClientesSolicitante start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerClientesSolicitante end: " + param);
		return (ResultObtenerClientesSolicitante) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerPlanesPago obtenerPlanesPago(ParamObtenerPlanesPago param) {
		log.debug("obtenerPlanesPago start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerPlanesPago end: " + param);
		return (ResultObtenerPlanesPago) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultDatosModificacion obtenerDatosModificacion(ParamDatosModificacion param) {
		log.debug("obtenerDatosModificacion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDatosModificacion end: " + param);
		return (ResultDatosModificacion) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultTipoTexto listaTipoTexto(ParamTipoTexto param) {
		log.debug("listaTipoTexto start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaTipoTexto end: " + param);
		return (ResultTipoTexto) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultTextosCotizacion obtenerTextosCotizacion(ParamTextosCotizacion param) {
		log.debug("obtenerTextosCotizacion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerTextosCotizacion end: " + param);
		return (ResultTextosCotizacion) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultDetalleTextoCotizacion obtenerDetalleTextoCotizacion(ParamDetalleTextoCotizacion param) {
		log.debug("obtenerDetalleTextoCotizacion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDetalleTextoCotizacion end: " + param);
		return (ResultDetalleTextoCotizacion) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultEncabezadoCotizacion obtenerEncabezadoCotizacion(ParamEncabezadoCotizacion param) {
		log.debug("obtenerEncabezadoCotizacion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerEncabezadoCotizacion end: " + param);
		return (ResultEncabezadoCotizacion) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultAcreedor listaAcreedores(ParamAcreedor param) {
		log.debug("listaAcreedores start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaAcreedores end: " + param);
		return (ResultAcreedor) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultAcreedorObjetos listaAcreedorObjetos(ParamAcreedorObjetos param) {
		log.debug("listaAcreedorObjetos start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaAcreedorObjetos end: " + param);
		return (ResultAcreedorObjetos) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultTipoAcreedores listaTipoAcreedores(ParamTipoAcreedores param) {
		log.debug("listaTipoAcreedores start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaTipoAcreedores end: " + param);
		return (ResultTipoAcreedores) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerAcreedoresXBien obtenerAcreedoresXBien(ParamObtenerAcreedoresXBien param) {
		log.debug("obtenerAcreedoresXBien start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerAcreedoresXBien end: " + param);
		return (ResultObtenerAcreedoresXBien) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerDatosPreviosEndoso obtenerDatosPreviosEndoso(ParamObtenerDatosPreviosEndoso param) {
		log.debug("obtenerDatosPreviosEndoso start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDatosPreviosEndoso end: " + param);
		return (ResultObtenerDatosPreviosEndoso) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaTodasSecciones listaTodasSecciones(ParamListaTodasSecciones param) {
		log.debug("listaTodasSecciones start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaTodasSecciones end: " + param);
		return (ResultListaTodasSecciones) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaTodosProductos listaTodosProductos(ParamListaTodosProductos param) {
		log.debug("listaTodosProductos start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaTodosProductos end: " + param);
		return (ResultListaTodosProductos) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerCoberturasXBien obtenerCoberturasXBien(ParamObtenerCoberturasXBien param) {
		log.debug("obtenerCoberturasXBien start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerCoberturasXBien end: " + param);
		return (ResultObtenerCoberturasXBien) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaValoresDato listaValoresDatoParametricoVehiculo(ParamListaValoresDatoVehiculo param) {
		log.debug("listaValoresDatoParametricoVehiculo start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaValoresDatoParametricoVehiculo end: " + param);
		return (ResultListaValoresDato) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultAcreedor listaAcreedoresFiltrados(ParamAcreedoresFiltrados param) {
		log.debug("listaAcreedoresFiltrados start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaAcreedoresFiltrados end: " + param);
		return (ResultAcreedor) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerCabezalCotizaciones obtenerCabezalCotizaciones(ParamObtenerCabezalCotizaciones param) {
		log.debug("obtenerCabezalCotizaciones start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerCabezalCotizaciones end: " + param);
		return (ResultObtenerCabezalCotizaciones) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerDetalleBusquedaCotizacion obtenerDetalleBusquedaCotizacion(ParamDetalleBusquedaCotizacion param) {
		log.debug("obtenerDetalleBusquedaCotizacion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDetalleBusquedaCotizacion end: " + param);
		return (ResultObtenerDetalleBusquedaCotizacion) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaPlanesPagoRen listaPlanesPagoRenovacion(ParamListaPlanesPagoRen param) {
		log.debug("listaPlanesPagoRenovacion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaPlanesPagoRenovacion end: " + param);
		return (ResultListaPlanesPagoRen) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultCodiguera listaNivelesComision(ParamNivelesComision param) {
		log.debug("listaNivelesComision start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaNivelesComision end: " + param);
		return (ResultCodiguera) result;
	}
	
	@Override
	@Interceptors(UserInterceptor.class)
	public ResultCodiguera listaNivelesComisionProd(ParamNivelesComisionProd param) {
		log.debug("listaNivelesComisionProd start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaNivelesComisionProd end: " + param);
		return (ResultCodiguera) result;
	}

}
