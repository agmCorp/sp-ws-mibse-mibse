package uy.com.bse.polizas.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import uy.com.bse.cotizaciones.lovs.ResultAcreedorObjetos;
import uy.com.bse.polizas.consultas.*;
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
import uy.com.bse.polizas.operaciones.ResultAgregarCertificadoEndoso;
import uy.com.bse.polizas.operaciones.ResultAnularCertificadoEndoso;
import uy.com.bse.polizas.operaciones.ResultAnularPoliza;
import uy.com.bse.polizas.operaciones.ResultCotizacionPendiente;
import uy.com.bse.polizas.operaciones.ResultEnviarFacturaDigitalEMail;
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

@WebService
public interface IWsServiciosPolizas {

	/**
	 * 
	 * @param param
	 * @return Devuelve los datos básicos de una póliza.
	 */
	@WebMethod
	public ResultObtenerDatosPoliza obtenerDatosPoliza (ParamObtenerDatosPoliza param);
	
	/**
	 * 
	 * @param param
	 * @return Devuelve los certificados de una póliza.
	 */
	@WebMethod
	public ResultObtenerCertificadosPoliza obtenerCertificadosPoliza (ParamObtenerCertificadosPoliza param);
	
	/**
	 * 
	 * @param param
	 * @return Obtiene los datos de un certificado de una póliza.
	 */
	@WebMethod
	public ResultObtenerDatosCertificadoPoliza obtenerDatosCertificadoPoliza (ParamObtenerDatosCertificadoPoliza param);
	
	/**
	 * 
	 * @param param
	 * @return Lista los distintos tipos anulaciones existentes.
	 */
	@WebMethod
	public ResultTipoAnulacion listaTipoAnulacion (ParamTipoAnulacion param);
	
	/**
	 * 
	 * @param param
	 * @return Lista las distintas causas de anulacion.
	 */
	@WebMethod
	public ResultCausaAnulacion listaCausaAnulacion (ParamCausaAnulacion param);
	
	/**
	 * 
	 * @param param
	 * @return Lista los distintos modos de calculo de una anulacion de poliza.
	 */
	@WebMethod
	public ResultModoCalculo listaModoCalculoAnulacion (ParamModoCalculo param);
	
	/**
	 * 
	 * @param param
	 * @return Devuelve los endosos de una poliza.
	 */
	@WebMethod
	public ResultObtenerEndososPoliza obtenerEndososPoliza (ParamObtenerEndososPoliza param);
	
	/**
	 * 
	 * @param param
	 * @return Devuelve los datos de un endoso de una poliza.
	 */
	@WebMethod
	public ResultObtenerDatosEndosoPoliza obtenerDatosEndosoPoliza (ParamObtenerDatosEndosoPoliza param);
	
	@WebMethod
	public ResultObtenerDatosEndosoSinPremio obtenerDatosEndosoSinPremio(ParamObtenerDatosEndosoSinPremio entrada);
	
	/**
	 * 
	 * @param param
	 * @return Devuelve los productores de una poliza.
	 */
	@WebMethod
	public ResultObtenerProductoresPoliza obtenerProductoresPoliza (ParamObtenerProductoresPoliza param);
	
	/**
	 * 
	 * @param param
	 * @return Devuelve los detalles de una poliza.
	 */
	@WebMethod
	public ResultObtenerDetallePoliza obtenerDetallePoliza (ParamObtenerDetallePoliza param);

	/**
	 * 
	 * @param param
	 * @return Devuelve las polizas segun un criterio de busqueda.
	 */
	@WebMethod
	public ResultObtenerPolizas obtenerPolizas (ParamObtenerPolizas param);
	
	/**
	 * 
	 * @param param
	 * @return Devuelve las cuotas de una poliza.
	 */
	@WebMethod
	public ResultObtenerCuotasPoliza obtenerCuotasPoliza (ParamObtenerCuotasPoliza param);
	
	/**
	 * 
	 * @param param
	 * @return Devuelve las facturas de una poliza.
	 */
	@WebMethod
	public ResultObtenerFacturasPoliza obtenerFacturasPoliza (ParamObtenerFacturasPoliza param);
	
	/**
	 * 
	 * @param param
	 * @return Devuelve las imputaciones de una poliza.
	 */
	@WebMethod
	public ResultObtenerImputacionesPoliza obtenerImputacionesPoliza (ParamObtenerImputacionesPoliza param);
	
	/**
	 * 
	 * @param param
	 * @return Devuelve las remesas de una poliza.
	 */
	@WebMethod
	public ResultObtenerRemesasPoliza obtenerRemesasPoliza (ParamObtenerRemesasPoliza param);
	
	/**
	 * 
	 * @param param
	 * @return Devuelve los totales de cuotas de una poliza.
	 */
	@WebMethod
	public ResultObtenerTotalesCuotasPoliza obtenerTotalesCuotasPoliza (ParamObtenerTotalesCuotasPoliza param);
	
	/**
	 * 
	 * @param param
	 * @return Devuelve las preliquidaciones pendientes de una poliza.
	 */
	@WebMethod
	public ResultObtenerPreliqPendientesPoliza obtenerPreliqPendientesPoliza (ParamObtenerPreliqPendientesPoliza param);
	
	/**
	 * 
	 * @param param
	 * @return Devuelve las cotizaciones de una poliza.
	 */
	@WebMethod
	public ResultObtenerCotizacionPoliza obtenerCotizacionPoliza (ParamObtenerCotizacionPoliza param);
	
	/**
	 * 
	 * @param param
	 * @return Devuelve los contratante de una poliza.
	 */
	@WebMethod	
	public ResultObtenerContratantePoliza obtenerContratantePoliza (ParamObtenerContratantePoliza param);
	
	/**
	 * 
	 * @param param
	 * @return Devuelve los datos del asegurado de un certificado de una póliza.
	 */
	@WebMethod		
	public ResultObtenerAseguradoCertifPoliza obtenerAseguradoCertifPoliza (ParamObtenerAseguradoCertifPoliza param);
	
	/**
	 * 
	 * @param param
	 * @return Devuelve los bienes asegurados en determinado certificado y endoso de una póliza.
	 */
	@WebMethod	
	public ResultObtenerBienesAseguradosPoliza obtenerBienesAseguradosPoliza (ParamObtenerBienesAseguradosPoliza param);
	
	/**
	 * 
	 * @param param
	 * @return Devuelve los textos de un certificado y endoso de una póliza.
	 */
	@WebMethod	
	public ResultObtenerTextosPoliza obtenerTextosPoliza (ParamObtenerTextosPoliza param);
	
	/**
	 * 
	 * @param param
	 * @return Devuelve el detalle de un texto de un certificado y endoso de una póliza.
	 */
	@WebMethod	
	public ResultObtenerDetalleTextoPoliza obtenerDetalleTextoPoliza (ParamObtenerDetalleTextoPoliza param);
	
	/**
	 * 
	 * @param param
	 * @return Devuelve los anexos de un certificado y endoso de una póliza.
	 */
	@WebMethod
	public ResultObtenerAnexosPoliza obtenerAnexosPoliza (ParamObtenerAnexosPoliza param);
	
	/**
	 * 
	 * @param param
	 * @return Devuelve la lista de bienes de un certificado y endoso de una póliza.
	 */
	@WebMethod
	public ResultObtenerListaBienesPoliza obtenerListaBienesPoliza (ParamObtenerListaBienesPoliza param);
	
	/**
	 * 
	 * @param param
	 * @return Devuelve la ubicación de un bien de determinado certificado y endoso de una póliza.
	 */
	@WebMethod
	public ResultObtenerUbicacionBienPoliza obtenerUbicacionBienPoliza (ParamObtenerUbicacionBienPoliza param);
	
	/**
	 * 
	 * @param param
	 * @return Devuelve los beneficiarios de un bien de un certificado y endoso de un una póliza.
	 */
	@WebMethod
	public ResultObtenerBeneficiariosPoliza obtenerBeneficiariosPoliza (ParamObtenerBeneficiariosPoliza param);
	
	/**
	 * 
	 * @param param
	 * @return los acreedores de un bien de un certificado y endoso de una póliza.
	 */
	@WebMethod
	public ResultObtenerAcreedoresPoliza obtenerAcreedoresPoliza (ParamObtenerAcreedoresPoliza param);
	
	/**
	 * 
	 * @param param
	 * @return las franquicias de un certificado y endoso de una póliza.
	 */
	@WebMethod	
	public ResultObtenerFranquiciasPoliza obtenerFranquiciasPoliza (ParamObtenerFranquiciasPoliza param);
	
	/**
	 * 
	 * @param param
	 * @return Devuelve los aportes y datos para el cálculo de renta para un certificado y endoso de una póliza.
	 */
	@WebMethod
	public ResultObtenerAhorrosPoliza obtenerAhorrosPoliza (ParamObtenerAhorrosPoliza param);
	
	/**
	 * 
	 * @param param
	 * @return Devuelve las remesas de un cliente.
	 */
	@WebMethod
	public ResultObtenerRemesasXCliente obtenerRemesasXCliente (ParamObtenerRemesasXCliente param);
	
	/**
	 * 
	 * @param param
	 * @return Devuelve los datos de una remesa.
	 */
	@WebMethod
	public ResultObtenerDatosRemesa obtenerDatosRemesa(ParamObtenerDatosRemesa param);
	
	/**
	 * 
	 * @param param
	 * @return Devuelve las cancelaciones de una remesa.
	 */
	@WebMethod
	public ResultObtenerCancelacionesRemesa obtenerCancelacionesRemesa (ParamObtenerCancelacionesRemesa param);
	
	/**
	 * 
	 * @param param
	 * @return Obtiene el detalle de cobro o el detalle de anulado, dependiendo del estado de la remesa.
	 */
	@WebMethod 
	public ResultObtenerDetalleRemesa obtenerDetalleRemesa (ParamObtenerDetalleRemesa param);
	
	/**
	 * 
	 * @param param
	 * @return Obtiene información del origen de la remesa automática.
	 */
	@WebMethod
	public ResultObtenerOrigenRemesaAuto obtenerOrigenRemesaAutomatica (ParamObtenerOrigenRemesaAuto param);
	
	/**
	 * 
	 * @param param
	 * @return Obtiene las notas de un siniestro.
	 */
	@WebMethod
	public ResultObtenerNotasSiniestro obtenerNotasSiniestro (ParamObtenerNotasSiniestro param);
	

	/**
	 * 
	 * @param param
	 * @return Obtiene las cláusulas de bonificación no siniestro de una póliza.
	 */
	@WebMethod	
	public ResultObtenerBonifNoSiniestroPoliza obtenerBonifNoSiniestroPoliza (ParamObtenerBonifNoSiniestroPoliza param);
	
	/**
	 * 
	 * @param param
	 * @return Obtiene los datos de la declaracion de un siniestro.
	 */
	@WebMethod	
	public ResultObtenerDeclaracionSiniestro obtenerDeclaracionSiniestro (ParamObtenerDeclaracionSiniestro param);
	
	/**
	 * 
	 * @param param
	 * @return Obtiene los datos de un siniestro.
	 */
	@WebMethod	
	public ResultObtenerDatosSiniestro obtenerDatosSiniestro (ParamObtenerDatosSiniestro param);
	
	/**
	 * 
	 * @param param
	 * @return Obtiene los siniestros de un cliente.
	 */
	@WebMethod	
	public ResultObtenerSiniestrosCliente obtenerSiniestrosCliente (ParamObtenerSiniestrosCliente param);
	
	/**
	 * 
	 * @param param
	 * @return Obtiene todos los siniestros.
	 */
	@WebMethod	
	public ResultObtenerSiniestros obtenerSiniestros (ParamObtenerSiniestros param);
	
	/**
	 * 
	 * @param param
	 * @return Obtiene todos los siniestros con filtro.
	 */
	@WebMethod	
	public ResultObtenerSiniestrosConFiltro obtenerSiniestrosConFiltro (ParamObtenerSiniestrosConFiltro param);
	
	/**
	 * 
	 * @param param
	 * @return Valida los datos de la póliza.
	 */
	@WebMethod
	public ResultValidarPoliza validarPoliza (ParamValidarPoliza param);
	
	/**
	 * 
	 * @param param
	 * @return Valida la anulacion de una poliza.
	 */
	@WebMethod
	public ResultValidarAnulacionPoliza validarAnulacionPoliza (ParamValidarAnulacionPoliza param);
	

	/**
	 * 
	 * @param param
	 * @return Anula una poliza.
	 */
	@WebMethod	
	public ResultAnularPoliza anularPoliza (ParamAnularPoliza param);
	
	/**
	 * 
	 * @param param
	 * @return Lista los planes de pago para la recuotificación.
	 */
	@WebMethod		
	public ResultListaPlanesPagoRec listaPlanesPagoRecuotificacion (ParamListaPlanesPagoRec param);
	
	/**
	 * 
	 * @param param
	 * @return Valida los datos de una póliza que se quiere recuotificar.
	 */
	@WebMethod		
	public ResultValidarPolizaRec validarPolizaRecuotificacion (ParamValidarPolizaRec param);
	
	/**
	 * 
	 * @param param
	 * @return Calcula la Recuotificación según el plan de pago elegido y muestra el detalle de las cuotas.
	 */
	@WebMethod	
	public ResultCalcularRec calcularRecuotificacion (ParamCalcularRec param);
		
	/**
	 * 
	 * @param param
	 * @return Recuotifica la póliza con determinado plan de pago.
	 */
	@WebMethod	
	public ResultRecuotificarPoliza recuotificarPoliza (ParamRecuotificarPoliza param);
	
	/**
	 * 
	 * @param param
	 * @return Lista los motivos de abandono por los cuales se quisiera anular una póliza.
	 */
	@WebMethod
	public ResultMotivoAbandono listaMotivoAbandono (ParamMotivoAbandono param);
	
	/**
	 * 
	 * @param param
	 * @return Lista los posibles orígenes de endoso.
	 */
	@WebMethod
	public ResultOrigenEndoso listaOrigenEndoso (ParamOrigenEndoso param);
	
	/**
	 * 
	 * @param param
	 * @return Obtiene la información del consolidado de la póliza
	 */
	@WebMethod
	public ResultObtenerConsolidado obtenerConsolidadoPoliza (ParamObtenerConsolidado param);
	
	/**
	 * 
	 * @param param
	 * @return Crea una nueva cotización de endoso
	 */
	@WebMethod
	public ResultNuevaCotizacionEndoso nuevaCotizacionEndoso (ParamNuevaCotizacionEndoso param);
	
	/**
	 * 
	 * @param param
	 * @return lista tipos de carta para reportes
	 */
	@WebMethod
	public ResultTipoCarta listaTiposCarta (ParamTipoCarta param);
	
	/**
	 * 
	 * @param param
	 * @return lista tipos de carta  de un tipo de carta para reportes
	 */
	@WebMethod
	public ResultCartaXTipoCarta listaCartaXTipoCarta (ParamCartaXTipoCarta param);
	
	/**
	 * 
	 * @param param
	 * @return Obtiene datos necesarios para los posibles opciones de impresion de poliza
	 */
	@WebMethod
	public ResultObtenerValidarImpresionPoliza obtenerValidarImpresionPoliza(ParamObtenerValidarImpresionPoliza param);
	
	/**
	 * 
	 * @param param
	 * @return Crea el reporte de la carta y retorna el numero de secuencia del mismo
	 */
	@WebMethod
	public ResultGuardarImprimirOrdenCarta guardarImprimirOrdenCarta(ParamGuardarImprimirCarta param);
	
	/**
	 * 
	 * @param param
	 * @return Crea el reporte  de la poliza y retorna el numero de secuencia del mismo
	 */
	@WebMethod
	public ResultGuardarImprimirReporte guardarImprimirReporte(ParamGuardarImprimirReporte param);
	
	@WebMethod
	public ResultAgregarCertificadoEndoso agregarCertificadoEndoso(ParamAgregarCertificadoEndoso param);
	
	@WebMethod	
	public ResultObtenerDatosBasicosEndoso obtenerDatosBasicosEndoso(ParamObtenerDatosBasicosEndoso param);
	
	@WebMethod
	public ResultMotivoEndoso listaMotivosEndoso(ParamMotivoEndoso param);
	
	@WebMethod
	public ResultDatosParametricosEndoso listaDatosParametricosEndoso(ParamDatosParametricosEndoso param);
	
	@WebMethod
	public ResultValoresDatosParametricosEndoso listaValoresDatosParametricosEndoso(ParamValoresDatosParametricosEndoso param);
	
	@WebMethod
	public ResultDatosXDatosParametricosEndoso obtenerDatosXDatoParametricoEndoso(ParamDatosXDatosParametricosEndoso param);

	@WebMethod
	public ResultAnularCertificadoEndoso anularCertificadoEndoso(ParamAnularCertificadoEndoso param);
	
	@WebMethod
	public ResultDatosParametricoPoliza listaDatosParametricosPoliza(ParamDatosParametricoPoliza param);
	
	@WebMethod
	public ResultValoresDatosParametricosPoliza listaValoresDatosParametricosPoliza(ParamValoresDatosParametricosPoliza param) ;
	
	@WebMethod
	public ResultDatosXDatosParametricosPoliza obtenerDatosXDatoParametricoPoliza(ParamDatosXDatosParametricosPoliza entrada);

	@WebMethod
	public ResultGenerarMarcaNoRenovar generarMarcaNoRenovar(ParamGenerarMarcaNoRenovar entrada);
	
	@WebMethod
	public ResultGenerarEndoso generarEndoso(ParamGenerarEndoso entrada);
	
	@WebMethod
	public ResultActualizarDatosParticulares actualizarDatosParticulares(ParamActualizarDatosParticulares entrada);
	
	@WebMethod
	public ResultNuevaFacturacionInteractiva nuevaFacturacionInteractiva(ParamNuevaFacturacionInteractiva param);
	
	@WebMethod
	public ResultValidarDetectarEndoso validarDetectarEndoso (ParamValidarDetectarEndoso param);
	
	@WebMethod
	public ResultModificacionSinPremio modificacionSinPremio(ParamModificacionSinPremio param);
	
	@WebMethod
	public ResultAcreedorObjetos listaAcreedorObjetos(ParamObjetosAcreedor param); 
	
	@WebMethod
	public ResultCotizacionPendiente cotizacionPendientePoliza(ParamCotizacionPendiente param);
	
	@WebMethod
	public ResultCondicion tienePermisoProducto(ParamTienePermisoProducto param);

	@WebMethod
	public ResultCodiguera listaCertificadosParaEndosar(ParamListaCertificadosEndosar param);

	@WebMethod
	public ResultObtenerDatosFacturaDigital obtenerDatosFacturaDigital(ParamObtenerDatosFacturaDigital param);
	
	@WebMethod
	public ResultEnviarFacturaDigitalEMail enviarFacturaDigitalEMail(ParamEnviarFacturaDigitalEMail param);
	
	@WebMethod
	public ResultObtenerDatosParametricos obtenerDatosParametricos(ParamObtenerDatosParametricos param);
	
	@WebMethod
	public ResultCodiguera obtenerValoresXDatoParametrico(ParamObtenerValoresXDatoParametrico param);
	
	@WebMethod
	public ResultObtenerPolizasCabezal obtenerPolizasCabezal(ParamObtenerPolizasCabezal param);
	
	@WebMethod
	public ResultObtenerPolizaCabezalDetalle obtenerPolizaCabezalDetalle(ParamObtenerPolizaCabezalDetalle param);
	
	@WebMethod
	public ResultCotizacionPendiente cotizacionPendientePolizaSinPremio(ParamCotizacionPendienteSinPremio param);
	
	@WebMethod
	public ResultValidarRefacturacion validarRefacturacion (ParamValidarRefacturacion param);

	
	@WebMethod
	public ResultRefacturacionPoliza refacturarPoliza (ParamRefacturarPoliza param);




}
