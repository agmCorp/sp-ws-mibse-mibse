package uy.com.bse.serviciosEJB;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.LogicaRector;
import uy.com.bse.auditoria.ParamAuditarEndoso;
import uy.com.bse.cotizaciones.lovs.ResultAcreedorObjetos;
import uy.com.bse.polizas.consultas.*;
import uy.com.bse.polizas.operaciones.ParamActualizarDatosParticulares;
import uy.com.bse.polizas.operaciones.ParamAgregarCertificadoEndoso;
import uy.com.bse.polizas.operaciones.ParamAnularCertificadoEndoso;
import uy.com.bse.polizas.operaciones.ParamAnularPoliza;
import uy.com.bse.polizas.operaciones.ParamCotizacionPendiente;
import uy.com.bse.polizas.operaciones.ParamCotizacionPendienteSinPremio;
import uy.com.bse.polizas.operaciones.ParamEnviarFacturaDigitalEMail;
import uy.com.bse.polizas.operaciones.ParamFacturacionElectronica;
import uy.com.bse.polizas.operaciones.ParamGenerarEndoso;
import uy.com.bse.polizas.operaciones.ParamNuevaCotizacionEndoso;
import uy.com.bse.polizas.operaciones.ParamNuevaFacturacionInteractiva;
import uy.com.bse.polizas.operaciones.ParamValidarAnulacionPoliza;
import uy.com.bse.polizas.operaciones.ParamValidarPoliza;
import uy.com.bse.polizas.operaciones.ResultAgregarCertificadoEndoso;
import uy.com.bse.polizas.operaciones.ResultAnularCertificadoEndoso;
import uy.com.bse.polizas.operaciones.ResultAnularPoliza;
import uy.com.bse.polizas.operaciones.ResultCotizacionPendiente;
import uy.com.bse.polizas.operaciones.ResultEnviarFacturaDigitalEMail;
import uy.com.bse.polizas.operaciones.ResultFacturacionElectronica;
import uy.com.bse.polizas.operaciones.ResultNuevaCotizacionEndoso;
import uy.com.bse.polizas.operaciones.ResultNuevaFacturacionInteractiva;
import uy.com.bse.polizas.operaciones.ResultValidarAnulacionPoliza;
import uy.com.bse.polizas.operaciones.ResultValidarPoliza;
import uy.com.bse.recuotificacion.ParamCalcularRec;
import uy.com.bse.recuotificacion.ParamListaCertificadosEndosar;
import uy.com.bse.recuotificacion.ParamListaPlanesPagoRec;
import uy.com.bse.recuotificacion.ParamModificacionSinPremio;
import uy.com.bse.recuotificacion.ParamObtenerConsolidado;
import uy.com.bse.recuotificacion.ParamOrigenEndoso;
import uy.com.bse.recuotificacion.ParamRecuotificarPoliza;
import uy.com.bse.recuotificacion.ParamValidarDetectarEndoso;
import uy.com.bse.recuotificacion.ParamValidarPolizaRec;
import uy.com.bse.recuotificacion.ResultCalcularRec;
import uy.com.bse.recuotificacion.ResultListaPlanesPagoRec;
import uy.com.bse.recuotificacion.ResultModificacionSinPremio;
import uy.com.bse.recuotificacion.ResultObtenerConsolidado;
import uy.com.bse.recuotificacion.ResultOrigenEndoso;
import uy.com.bse.recuotificacion.ResultRecuotificarPoliza;
import uy.com.bse.recuotificacion.ResultValidarDetectarEndoso;
import uy.com.bse.recuotificacion.ResultValidarPolizaRec;
import uy.com.bse.refacturar.ParamRefacturarPoliza;
import uy.com.bse.refacturar.ParamValidarRefacturacion;
import uy.com.bse.refacturar.ResultRefacturacionPoliza;
import uy.com.bse.refacturar.ResultValidarRefacturacion;
import uy.com.bse.utilitario.dato.ResultCodiguera;
import uy.com.bse.utilitario.dato.ResultCondicion;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.util.ValuesUtils;

@Stateless
public class Polizas implements PolizasLocal {

	private static Logger log = LogManager.getLogger(Polizas.class);

	@EJB
	private AuditoriaLocal auditoria;

	public Polizas() {
		super();
	}

	@Interceptors(TransactionInterceptorRector.class)
	public ResultAgregarCertificadoEndoso agregarCertificadoEndoso(ParamAgregarCertificadoEndoso param) {
		log.debug("agregarCertificadoEndoso start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("agregarCertificadoEndoso end");
		return (ResultAgregarCertificadoEndoso) result;
	}

	@Interceptors(UserInterceptor.class)
	public ResultObtenerDatosBasicosEndoso obtenerDatosBasicosEndoso(ParamObtenerDatosBasicosEndoso param) {
		log.debug("obtenerDatosBasicosEndoso start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDatosBasicosEndoso end");
		return (ResultObtenerDatosBasicosEndoso) result;
	}

	@Interceptors(UserInterceptor.class)
	public ResultMotivoEndoso listaMotivosEndoso(ParamMotivoEndoso param) {
		log.debug("listaMotivosEndoso start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaMotivosEndoso end");
		return (ResultMotivoEndoso) result;

	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerDatosPoliza obtenerDatosPoliza(ParamObtenerDatosPoliza param) {
		log.debug("obtenerDatosPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDatosPoliza end");
		return (ResultObtenerDatosPoliza) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultGuardarImprimirOrdenCarta guardarImprimirOrdenCarta(ParamGuardarImprimirCarta param) {
		log.debug("guardarImprimirOrdenCarta start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("guardarImprimirOrdenCarta end");
		return (ResultGuardarImprimirOrdenCarta) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultGuardarImprimirReporte guardarImprimirReporte(ParamGuardarImprimirReporte param) {
		log.debug("guardarImprimirReporte start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("guardarImprimirReporte end");
		return (ResultGuardarImprimirReporte) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerCertificadosPoliza obtenerCertificadosPoliza(ParamObtenerCertificadosPoliza param) {
		log.debug("obtenerCertificadosPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerCertificadosPoliza end");
		return (ResultObtenerCertificadosPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerDatosCertificadoPoliza obtenerDatosCertificadoPoliza(ParamObtenerDatosCertificadoPoliza param) {
		log.debug("obtenerDatosCertificadoPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDatosCertificadoPoliza end");
		return (ResultObtenerDatosCertificadoPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultTipoAnulacion listaTipoAnulacion(ParamTipoAnulacion param) {
		log.debug("listaTipoAnulacion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaTipoAnulacion end");
		return (ResultTipoAnulacion) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultCausaAnulacion listaCausaAnulacion(ParamCausaAnulacion param) {
		log.debug("listaCausaAnulacion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaCausaAnulacion end");
		return (ResultCausaAnulacion) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultModoCalculo listaModoCalculoAnulacion(ParamModoCalculo param) {
		log.debug("listaModoCalculoAnulacion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaModoCalculoAnulacion end");
		return (ResultModoCalculo) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerEndososPoliza obtenerEndososPoliza(ParamObtenerEndososPoliza param) {
		log.debug("obtenerEndososPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerEndososPoliza end");
		return (ResultObtenerEndososPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerDatosEndosoPoliza obtenerDatosEndosoPoliza(ParamObtenerDatosEndosoPoliza param) {
		log.debug("obtenerDatosEndosoPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDatosEndosoPoliza end");
		return (ResultObtenerDatosEndosoPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerProductoresPoliza obtenerProductoresPoliza(ParamObtenerProductoresPoliza param) {
		log.debug("obtenerProductoresPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerProductoresPoliza end");
		return (ResultObtenerProductoresPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerDetallePoliza obtenerDetallePoliza(ParamObtenerDetallePoliza param) {
		log.debug("obtenerDetallePoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDetallePoliza end");
		return (ResultObtenerDetallePoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerPolizas obtenerPolizas(ParamObtenerPolizas param) {
		log.debug("obtenerPolizas start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerPolizas end");
		return (ResultObtenerPolizas) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerCuotasPoliza obtenerCuotasPoliza(ParamObtenerCuotasPoliza param) {
		log.debug("obtenerCuotasPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerCuotasPoliza end");
		return (ResultObtenerCuotasPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerFacturasPoliza obtenerFacturasPoliza(ParamObtenerFacturasPoliza param) {
		log.debug("obtenerFacturasPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerFacturasPoliza end");
		return (ResultObtenerFacturasPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerImputacionesPoliza obtenerImputacionesPoliza(ParamObtenerImputacionesPoliza param) {
		log.debug("obtenerImputacionesPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerImputacionesPoliza end");
		return (ResultObtenerImputacionesPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerRemesasPoliza obtenerRemesasPoliza(ParamObtenerRemesasPoliza param) {
		log.debug("obtenerRemesasPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerRemesasPoliza end");
		return (ResultObtenerRemesasPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerTotalesCuotasPoliza obtenerTotalesCuotasPoliza(ParamObtenerTotalesCuotasPoliza param) {
		log.debug("obtenerTotalesCuotasPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerTotalesCuotasPoliza end");
		return (ResultObtenerTotalesCuotasPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerPreliqPendientesPoliza obtenerPreliqPendientesPoliza(ParamObtenerPreliqPendientesPoliza param) {
		log.debug("obtenerPreliqPendientesPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerPreliqPendientesPoliza end");
		return (ResultObtenerPreliqPendientesPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerCotizacionPoliza obtenerCotizacionPoliza(ParamObtenerCotizacionPoliza param) {
		log.debug("obtenerCotizacionPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerCotizacionPoliza end");
		return (ResultObtenerCotizacionPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerContratantePoliza obtenerContratantePoliza(ParamObtenerContratantePoliza param) {
		log.debug("obtenerContratantePoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerContratantePoliza end");
		return (ResultObtenerContratantePoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerAseguradoCertifPoliza obtenerAseguradoCertifPoliza(ParamObtenerAseguradoCertifPoliza param) {
		log.debug("obtenerAseguradoCertifPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerAseguradoCertifPoliza end");
		return (ResultObtenerAseguradoCertifPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerBienesAseguradosPoliza obtenerBienesAseguradosPoliza(ParamObtenerBienesAseguradosPoliza param) {
		log.debug("obtenerBienesAseguradosPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerBienesAseguradosPoliza end");
		return (ResultObtenerBienesAseguradosPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerTextosPoliza obtenerTextosPoliza(ParamObtenerTextosPoliza param) {
		log.debug("obtenerTextosPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerTextosPoliza end");
		return (ResultObtenerTextosPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerDetalleTextoPoliza obtenerDetalleTextoPoliza(ParamObtenerDetalleTextoPoliza param) {
		log.debug("obtenerDetalleTextoPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDetalleTextoPoliza end");
		return (ResultObtenerDetalleTextoPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerAnexosPoliza obtenerAnexosPoliza(ParamObtenerAnexosPoliza param) {
		log.debug("obtenerAnexosPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerAnexosPoliza end");
		return (ResultObtenerAnexosPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerListaBienesPoliza obtenerListaBienesPoliza(ParamObtenerListaBienesPoliza param) {
		log.debug("obtenerListaBienesPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerListaBienesPoliza end");
		return (ResultObtenerListaBienesPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerUbicacionBienPoliza obtenerUbicacionBienPoliza(ParamObtenerUbicacionBienPoliza param) {
		log.debug("obtenerUbicacionBienPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerUbicacionBienPoliza end");
		return (ResultObtenerUbicacionBienPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerBeneficiariosPoliza obtenerBeneficiariosPoliza(ParamObtenerBeneficiariosPoliza param) {
		log.debug("obtenerBeneficiariosPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerBeneficiariosPoliza end");
		return (ResultObtenerBeneficiariosPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerAcreedoresPoliza obtenerAcreedoresPoliza(ParamObtenerAcreedoresPoliza param) {
		log.debug("obtenerAcreedoresPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerAcreedoresPoliza end");
		return (ResultObtenerAcreedoresPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerFranquiciasPoliza obtenerFranquiciasPoliza(ParamObtenerFranquiciasPoliza param) {
		log.debug("obtenerFranquiciasPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerFranquiciasPoliza end");
		return (ResultObtenerFranquiciasPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerAhorrosPoliza obtenerAhorrosPoliza(ParamObtenerAhorrosPoliza param) {
		log.debug("obtenerAhorrosPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerAhorrosPoliza end");
		return (ResultObtenerAhorrosPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerRemesasXCliente obtenerRemesasXCliente(ParamObtenerRemesasXCliente param) {
		log.debug("obtenerRemesasXCliente start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerRemesasXCliente end");
		return (ResultObtenerRemesasXCliente) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerDatosRemesa obtenerDatosRemesa(ParamObtenerDatosRemesa param) {
		log.debug("obtenerDatosRemesa start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDatosRemesa end");
		return (ResultObtenerDatosRemesa) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerCancelacionesRemesa obtenerCancelacionesRemesa(ParamObtenerCancelacionesRemesa param) {
		log.debug("obtenerCancelacionesRemesa start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerCancelacionesRemesa end");
		return (ResultObtenerCancelacionesRemesa) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerDetalleRemesa obtenerDetalleRemesa(ParamObtenerDetalleRemesa param) {
		log.debug("obtenerDetalleRemesa start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDetalleRemesa end");
		return (ResultObtenerDetalleRemesa) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerOrigenRemesaAuto obtenerOrigenRemesaAutomatica(ParamObtenerOrigenRemesaAuto param) {
		log.debug("obtenerOrigenRemesaAutomatica start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerOrigenRemesaAutomatica end");
		return (ResultObtenerOrigenRemesaAuto) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerNotasSiniestro obtenerNotasSiniestro(ParamObtenerNotasSiniestro param) {
		log.debug("obtenerNotasSiniestro start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerNotasSiniestro end");
		return (ResultObtenerNotasSiniestro) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerBonifNoSiniestroPoliza obtenerBonifNoSiniestroPoliza(ParamObtenerBonifNoSiniestroPoliza param) {
		log.debug("obtenerBonifNoSiniestroPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerBonifNoSiniestroPoliza end");
		return (ResultObtenerBonifNoSiniestroPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerDeclaracionSiniestro obtenerDeclaracionSiniestro(ParamObtenerDeclaracionSiniestro param) {
		log.debug("obtenerDeclaracionSiniestro start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDeclaracionSiniestro end");
		return (ResultObtenerDeclaracionSiniestro) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerDatosSiniestro obtenerDatosSiniestro(ParamObtenerDatosSiniestro param) {
		log.debug("obtenerDatosSiniestro start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDatosSiniestro end");
		return (ResultObtenerDatosSiniestro) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerSiniestrosCliente obtenerSiniestrosCliente(ParamObtenerSiniestrosCliente param) {
		log.debug("obtenerSiniestrosCliente start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerSiniestrosCliente end");
		return (ResultObtenerSiniestrosCliente) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerSiniestros obtenerSiniestros(ParamObtenerSiniestros param) {
		log.debug("obtenerSiniestros start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerSiniestros end");
		return (ResultObtenerSiniestros) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerSiniestrosConFiltro obtenerSiniestrosConFiltro(ParamObtenerSiniestrosConFiltro param) {
		log.debug("obtenerSiniestrosConFiltro start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerSiniestrosConFiltro end");
		return (ResultObtenerSiniestrosConFiltro) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultValidarPoliza validarPoliza(ParamValidarPoliza param) {
		log.debug("validarPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("validarPoliza end");
		return (ResultValidarPoliza) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultValidarAnulacionPoliza validarAnulacionPoliza(ParamValidarAnulacionPoliza param) {
		log.debug("validarAnulacionPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("validarAnulacionPoliza end");
		return (ResultValidarAnulacionPoliza) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultAnularPoliza anularPoliza(ParamAnularPoliza param) {
		log.debug("anularPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("anularPoliza end");
		return (ResultAnularPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultListaPlanesPagoRec listaPlanesPagoRecuotificacion(ParamListaPlanesPagoRec param) {
		log.debug("listaPlanesPagoRecuotificacion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaPlanesPagoRecuotificacion end");
		return (ResultListaPlanesPagoRec) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultValidarPolizaRec validarPolizaRecuotificacion(ParamValidarPolizaRec param) {
		log.debug("validarPolizaRecuotificacion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("validarPolizaRecuotificacion end");
		return (ResultValidarPolizaRec) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultCalcularRec calcularRecuotificacion(ParamCalcularRec param) {
		log.debug("calcularRecuotificacion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("calcularRecuotificacion end");
		return (ResultCalcularRec) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultRecuotificarPoliza recuotificarPoliza(ParamRecuotificarPoliza param) {
		log.debug("recuotificarPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("recuotificarPoliza end");
		return (ResultRecuotificarPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultMotivoAbandono listaMotivoAbandono(ParamMotivoAbandono param) {
		log.debug("listaMotivoAbandono start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaMotivoAbandono end");
		return (ResultMotivoAbandono) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultOrigenEndoso listaOrigenEndoso(ParamOrigenEndoso param) {
		log.debug("listaOrigenEndoso start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaOrigenEndoso end");
		return (ResultOrigenEndoso) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerConsolidado obtenerConsolidadoPoliza(ParamObtenerConsolidado param) {
		log.debug("obtenerConsolidadoPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerConsolidadoPoliza end");
		return (ResultObtenerConsolidado) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultNuevaCotizacionEndoso nuevaCotizacionEndoso(ParamNuevaCotizacionEndoso param) {
		log.debug("nuevaCotizacionEndoso start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("nuevaCotizacionEndoso end");
		return (ResultNuevaCotizacionEndoso) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultTipoCarta listaTiposCarta(ParamTipoCarta param) {
		log.debug("listaTiposCarta start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaTiposCarta end");
		return (ResultTipoCarta) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultCartaXTipoCarta listaCartaXTipoCarta(ParamCartaXTipoCarta param) {
		log.debug("listaCartaXTipoCarta start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaCartaXTipoCarta end");
		return (ResultCartaXTipoCarta) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultObtenerValidarImpresionPoliza obtenerValidarImpresionPoliza(ParamObtenerValidarImpresionPoliza param) {
		log.debug("obtenerValidarImpresionPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerValidarImpresionPoliza end");
		return (ResultObtenerValidarImpresionPoliza) result;
	}

	@Interceptors(UserInterceptor.class)
	public ResultDatosParametricosEndoso listaDatosParametricosEndoso(ParamDatosParametricosEndoso param) {
		log.debug("listaDatosParametricosEndoso start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaDatosParametricosEndoso end");
		return (ResultDatosParametricosEndoso) result;
	}

	@Interceptors(UserInterceptor.class)
	public ResultValoresDatosParametricosEndoso listaValoresDatosParametricosEndoso(ParamValoresDatosParametricosEndoso param) {
		log.debug("listaValoresDatosParametricosEndoso start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaValoresDatosParametricosEndoso end");
		return (ResultValoresDatosParametricosEndoso) result;
	}

	@Interceptors(UserInterceptor.class)
	public ResultDatosXDatosParametricosEndoso obtenerDatosXDatoParametricoEndoso(ParamDatosXDatosParametricosEndoso param) {
		log.debug("obtenerDatosXDatoParametricoEndoso start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDatosXDatoParametricoEndoso end");
		return (ResultDatosXDatosParametricosEndoso) result;
	}

	@Interceptors(TransactionInterceptorRector.class)
	public ResultAnularCertificadoEndoso anularCertificadoEndoso(ParamAnularCertificadoEndoso param) {
		log.debug("anularCertificadoEndoso start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("anularCertificadoEndoso end");
		return (ResultAnularCertificadoEndoso) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultDatosParametricoPoliza listaDatosParametricosPoliza(ParamDatosParametricoPoliza param) {
		log.debug("listaDatosParametricosPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaDatosParametricosPoliza end");
		return (ResultDatosParametricoPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultValoresDatosParametricosPoliza listaValoresDatosParametricosPoliza(ParamValoresDatosParametricosPoliza param) {
		log.debug("listaValoresDatosParametricosPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaValoresDatosParametricosPoliza end");
		return (ResultValoresDatosParametricosPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultDatosXDatosParametricosPoliza obtenerDatosXDatoParametricoPoliza(ParamDatosXDatosParametricosPoliza param) {
		log.debug("obtenerDatosXDatoParametricoPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDatosXDatoParametricoPoliza end");
		return (ResultDatosXDatosParametricosPoliza) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerDatosEndosoSinPremio obtenerDatosEndosoSinPremio(ParamObtenerDatosEndosoSinPremio param) {
		log.debug("obtenerDatosEndosoSinPremio start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDatosEndosoSinPremio end");
		return (ResultObtenerDatosEndosoSinPremio) result;

	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultGenerarMarcaNoRenovar generarMarcaNoRenovar(ParamGenerarMarcaNoRenovar param) {
		log.debug("generarMarcaNoRenovar start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("generarMarcaNoRenovar end");
		return (ResultGenerarMarcaNoRenovar) result;

	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultGenerarEndoso generarEndoso(ParamGenerarEndoso param) {
		log.debug("generarEndoso start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("generarEndoso end");
		return (ResultGenerarEndoso) result;

	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultActualizarDatosParticulares actualizarDatosParticulares(ParamActualizarDatosParticulares param) {
		log.debug("actualizarDatosParticulares start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("actualizarDatosParticulares end");
		return (ResultActualizarDatosParticulares) result;

	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultNuevaFacturacionInteractiva nuevaFacturacionInteractiva(ParamNuevaFacturacionInteractiva param) {
		log.debug("nuevaFacturacionInteractiva start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("nuevaFacturacionInteractiva end");
		return (ResultNuevaFacturacionInteractiva) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultValidarDetectarEndoso validarDetectarEndoso(ParamValidarDetectarEndoso param) {
		log.debug("validarDetectarEndoso start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("validarDetectarEndoso end");
		return (ResultValidarDetectarEndoso) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultModificacionSinPremio modificacionSinPremio(ParamModificacionSinPremio param) {
		log.debug("modificacionSinPremio start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		auditoria.auditarEndoso(new ParamAuditarEndoso(param.getUsuario(), param.getClave(), null, ValuesUtils.toInteger(param.getNumRamo()), ValuesUtils.toInteger(param.getNumPoliza()), ValuesUtils
				.toInteger(param.getNumEndoso()), "ENDOSO_CUALITATIVO", getDetalleAuditoria(result)));
		log.debug("modificacionSinPremio end");
		return (ResultModificacionSinPremio) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultAcreedorObjetos listaAcreedorObjetos(ParamObjetosAcreedor param) {
		log.debug("listaAcreedorObjetos start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaAcreedorObjetos end");
		return (ResultAcreedorObjetos) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultCotizacionPendiente cotizacionPendientePoliza(ParamCotizacionPendiente param) {
		log.debug("cotizacionPendientePoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("cotizacionPendientePoliza end");
		return (ResultCotizacionPendiente) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultCondicion tienePermisoProducto(ParamTienePermisoProducto param) {
		log.debug("tienePermisoProducto start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("tienePermisoProducto end");
		return (ResultCondicion) result;

	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultCodiguera listaCertificadosParaEndosar(ParamListaCertificadosEndosar param) {
		log.debug("listaCertificadosParaEndosar start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("listaCertificadosParaEndosar end");
		return (ResultCodiguera) result;

	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerDatosParametricos obtenerDatosParametricos(ParamObtenerDatosParametricos param) {
		log.debug("obtenerDatosParametricos start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDatosParametricos end");
		return (ResultObtenerDatosParametricos) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultCodiguera obtenerValoresXDatoParametrico(ParamObtenerValoresXDatoParametrico param) {
		log.debug("obtenerValoresXDatoParametrico start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerValoresXDatoParametrico end");
		return (ResultCodiguera) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerDatosFacturaDigital obtenerDatosFacturaDigital(ParamObtenerDatosFacturaDigital param) {
		log.debug("obtenerDatosFacturaDigital start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerDatosFacturaDigital end");
		return (ResultObtenerDatosFacturaDigital) result;
	}

	@Override
	@Interceptors(TransactionInterceptorRector.class)
	public ResultEnviarFacturaDigitalEMail enviarFacturaDigitalEMail(ParamEnviarFacturaDigitalEMail param) {
		log.debug("enviarFacturaDigitalEMail start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("enviarFacturaDigitalEMail end");
		return (ResultEnviarFacturaDigitalEMail) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerPolizasCabezal obtenerPolizasCabezal(ParamObtenerPolizasCabezal param) {
		log.debug("obtenerPolizasCabezal start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerPolizasCabezal end");
		return (ResultObtenerPolizasCabezal) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultObtenerPolizaCabezalDetalle obtenerPolizaCabezalDetalle(ParamObtenerPolizaCabezalDetalle param) {
		log.debug("obtenerPolizaCabezalDetalle start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("obtenerPolizaCabezalDetalle end");
		return (ResultObtenerPolizaCabezalDetalle) result;
	}
	
	@Override
	@Interceptors(UserInterceptor.class)
	public ResultFacturacionElectronica facturacionElectronica(ParamFacturacionElectronica param) {
		log.debug("facturacionElectronica start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("facturacionElectronica end");
		return (ResultFacturacionElectronica) result;
	}
	
	@Override
	@Interceptors(UserInterceptor.class)
	public ResultCotizacionPendiente cotizacionPendientePolizaSinPremio(ParamCotizacionPendienteSinPremio param) {
		log.debug("cotizacionPendientePolizaSinPremio start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("cotizacionPendientePolizaSinPremio end");
		return (ResultCotizacionPendiente) result;
	}

	private String getDetalleAuditoria(ResultGenerico result) {
		String resultado = null;
		if (result.getHayError().booleanValue()) {
			StringBuffer detalleSB = new StringBuffer();
			detalleSB.append("ERROR: ");
			detalleSB.append(result.getError().getCodigo());
			detalleSB.append(" - ");
			detalleSB.append(result.getError().getDescripcion());
		} else {
			resultado = "OK";
		}
		return resultado;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultValidarRefacturacion validarRefacturacion(ParamValidarRefacturacion param) {
		log.debug("validarRefacturacion start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("validarRefacturacion end");
		return (ResultValidarRefacturacion) result;
	}

	@Override
	@Interceptors(UserInterceptor.class)
	public ResultRefacturacionPoliza refacturarPoliza(ParamRefacturarPoliza param) {
		log.debug("refacturarPoliza start: " + param);
		ResultGenerico result = LogicaRector.solve(param);
		log.debug("refacturarPoliza end");
		return (ResultRefacturacionPoliza) result;
	}

	
}