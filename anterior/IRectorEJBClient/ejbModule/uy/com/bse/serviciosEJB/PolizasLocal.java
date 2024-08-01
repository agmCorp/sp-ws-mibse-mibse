package uy.com.bse.serviciosEJB;

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

public interface PolizasLocal {

	ResultAgregarCertificadoEndoso agregarCertificadoEndoso(ParamAgregarCertificadoEndoso param);

	ResultObtenerDatosBasicosEndoso obtenerDatosBasicosEndoso(ParamObtenerDatosBasicosEndoso param);

	ResultMotivoEndoso listaMotivosEndoso(ParamMotivoEndoso param);

	ResultObtenerDatosPoliza obtenerDatosPoliza(ParamObtenerDatosPoliza param);

	ResultGuardarImprimirOrdenCarta guardarImprimirOrdenCarta(ParamGuardarImprimirCarta param);

	ResultGuardarImprimirReporte guardarImprimirReporte(ParamGuardarImprimirReporte param);

	ResultObtenerCertificadosPoliza obtenerCertificadosPoliza(ParamObtenerCertificadosPoliza param);

	ResultObtenerDatosCertificadoPoliza obtenerDatosCertificadoPoliza(ParamObtenerDatosCertificadoPoliza param);

	ResultTipoAnulacion listaTipoAnulacion(ParamTipoAnulacion param);

	ResultCausaAnulacion listaCausaAnulacion(ParamCausaAnulacion param);

	ResultModoCalculo listaModoCalculoAnulacion(ParamModoCalculo param);

	ResultObtenerEndososPoliza obtenerEndososPoliza(ParamObtenerEndososPoliza param);

	ResultObtenerDatosEndosoPoliza obtenerDatosEndosoPoliza(ParamObtenerDatosEndosoPoliza param);

	ResultObtenerProductoresPoliza obtenerProductoresPoliza(ParamObtenerProductoresPoliza param);

	ResultObtenerDetallePoliza obtenerDetallePoliza(ParamObtenerDetallePoliza param);

	ResultObtenerPolizas obtenerPolizas(ParamObtenerPolizas param);

	ResultObtenerCuotasPoliza obtenerCuotasPoliza(ParamObtenerCuotasPoliza param);

	ResultObtenerFacturasPoliza obtenerFacturasPoliza(ParamObtenerFacturasPoliza param);

	ResultObtenerImputacionesPoliza obtenerImputacionesPoliza(ParamObtenerImputacionesPoliza param);

	ResultObtenerRemesasPoliza obtenerRemesasPoliza(ParamObtenerRemesasPoliza param);

	ResultObtenerTotalesCuotasPoliza obtenerTotalesCuotasPoliza(ParamObtenerTotalesCuotasPoliza param);

	ResultObtenerPreliqPendientesPoliza obtenerPreliqPendientesPoliza(ParamObtenerPreliqPendientesPoliza param);

	ResultObtenerCotizacionPoliza obtenerCotizacionPoliza(ParamObtenerCotizacionPoliza param);

	ResultObtenerContratantePoliza obtenerContratantePoliza(ParamObtenerContratantePoliza param);

	ResultObtenerAseguradoCertifPoliza obtenerAseguradoCertifPoliza(ParamObtenerAseguradoCertifPoliza param);

	ResultObtenerBienesAseguradosPoliza obtenerBienesAseguradosPoliza(ParamObtenerBienesAseguradosPoliza param);

	ResultObtenerTextosPoliza obtenerTextosPoliza(ParamObtenerTextosPoliza param);

	ResultObtenerDetalleTextoPoliza obtenerDetalleTextoPoliza(ParamObtenerDetalleTextoPoliza param);

	ResultObtenerAnexosPoliza obtenerAnexosPoliza(ParamObtenerAnexosPoliza param);

	ResultObtenerListaBienesPoliza obtenerListaBienesPoliza(ParamObtenerListaBienesPoliza param);

	ResultObtenerUbicacionBienPoliza obtenerUbicacionBienPoliza(ParamObtenerUbicacionBienPoliza param);

	ResultObtenerBeneficiariosPoliza obtenerBeneficiariosPoliza(ParamObtenerBeneficiariosPoliza param);

	ResultObtenerAcreedoresPoliza obtenerAcreedoresPoliza(ParamObtenerAcreedoresPoliza param);

	ResultObtenerFranquiciasPoliza obtenerFranquiciasPoliza(ParamObtenerFranquiciasPoliza param);

	ResultObtenerAhorrosPoliza obtenerAhorrosPoliza(ParamObtenerAhorrosPoliza param);

	ResultObtenerRemesasXCliente obtenerRemesasXCliente(ParamObtenerRemesasXCliente param);

	ResultObtenerDatosRemesa obtenerDatosRemesa(ParamObtenerDatosRemesa param);

	ResultObtenerCancelacionesRemesa obtenerCancelacionesRemesa(ParamObtenerCancelacionesRemesa param);

	ResultObtenerDetalleRemesa obtenerDetalleRemesa(ParamObtenerDetalleRemesa param);

	ResultObtenerOrigenRemesaAuto obtenerOrigenRemesaAutomatica(ParamObtenerOrigenRemesaAuto param);

	ResultObtenerNotasSiniestro obtenerNotasSiniestro(ParamObtenerNotasSiniestro param);

	ResultObtenerBonifNoSiniestroPoliza obtenerBonifNoSiniestroPoliza(ParamObtenerBonifNoSiniestroPoliza param);

	ResultObtenerDeclaracionSiniestro obtenerDeclaracionSiniestro(ParamObtenerDeclaracionSiniestro param);

	ResultObtenerDatosSiniestro obtenerDatosSiniestro(ParamObtenerDatosSiniestro param);

	ResultObtenerSiniestrosCliente obtenerSiniestrosCliente(ParamObtenerSiniestrosCliente param);

	ResultObtenerSiniestros obtenerSiniestros(ParamObtenerSiniestros param);

	ResultObtenerSiniestrosConFiltro obtenerSiniestrosConFiltro(ParamObtenerSiniestrosConFiltro param);

	ResultValidarPoliza validarPoliza(ParamValidarPoliza param);

	ResultValidarAnulacionPoliza validarAnulacionPoliza(ParamValidarAnulacionPoliza param);

	ResultAnularPoliza anularPoliza(ParamAnularPoliza param);

	ResultListaPlanesPagoRec listaPlanesPagoRecuotificacion(ParamListaPlanesPagoRec param);

	ResultValidarPolizaRec validarPolizaRecuotificacion(ParamValidarPolizaRec param);

	ResultCalcularRec calcularRecuotificacion(ParamCalcularRec param);

	ResultRecuotificarPoliza recuotificarPoliza(ParamRecuotificarPoliza param);

	ResultMotivoAbandono listaMotivoAbandono(ParamMotivoAbandono param);

	ResultOrigenEndoso listaOrigenEndoso(ParamOrigenEndoso param);

	ResultObtenerConsolidado obtenerConsolidadoPoliza(ParamObtenerConsolidado param);

	ResultNuevaCotizacionEndoso nuevaCotizacionEndoso(ParamNuevaCotizacionEndoso param);

	ResultTipoCarta listaTiposCarta(ParamTipoCarta param);

	ResultCartaXTipoCarta listaCartaXTipoCarta(ParamCartaXTipoCarta param);

	ResultObtenerValidarImpresionPoliza obtenerValidarImpresionPoliza(ParamObtenerValidarImpresionPoliza param);

	ResultDatosParametricosEndoso listaDatosParametricosEndoso(ParamDatosParametricosEndoso param);

	ResultValoresDatosParametricosEndoso listaValoresDatosParametricosEndoso(ParamValoresDatosParametricosEndoso param);

	ResultDatosXDatosParametricosEndoso obtenerDatosXDatoParametricoEndoso(ParamDatosXDatosParametricosEndoso param);

	ResultAnularCertificadoEndoso anularCertificadoEndoso(ParamAnularCertificadoEndoso param);

	ResultDatosParametricoPoliza listaDatosParametricosPoliza(ParamDatosParametricoPoliza param);

	ResultValoresDatosParametricosPoliza listaValoresDatosParametricosPoliza(ParamValoresDatosParametricosPoliza param);

	ResultDatosXDatosParametricosPoliza obtenerDatosXDatoParametricoPoliza(ParamDatosXDatosParametricosPoliza param);

	ResultObtenerDatosEndosoSinPremio obtenerDatosEndosoSinPremio(ParamObtenerDatosEndosoSinPremio entrada);

	ResultGenerarMarcaNoRenovar generarMarcaNoRenovar(ParamGenerarMarcaNoRenovar entrada);

	ResultGenerarEndoso generarEndoso(ParamGenerarEndoso entrada);

	ResultActualizarDatosParticulares actualizarDatosParticulares(ParamActualizarDatosParticulares entrada);

	ResultNuevaFacturacionInteractiva nuevaFacturacionInteractiva(ParamNuevaFacturacionInteractiva entrada);

	ResultValidarDetectarEndoso validarDetectarEndoso(ParamValidarDetectarEndoso param);

	ResultModificacionSinPremio modificacionSinPremio(ParamModificacionSinPremio param);

	ResultAcreedorObjetos listaAcreedorObjetos(ParamObjetosAcreedor param);
	
	ResultCotizacionPendiente cotizacionPendientePoliza(ParamCotizacionPendiente param);
	
	ResultCotizacionPendiente cotizacionPendientePolizaSinPremio(ParamCotizacionPendienteSinPremio param);
	
	ResultCondicion tienePermisoProducto(ParamTienePermisoProducto param);
	
	ResultCodiguera listaCertificadosParaEndosar(ParamListaCertificadosEndosar param);

	ResultObtenerDatosFacturaDigital obtenerDatosFacturaDigital(ParamObtenerDatosFacturaDigital param);
	
	ResultEnviarFacturaDigitalEMail enviarFacturaDigitalEMail(ParamEnviarFacturaDigitalEMail param);
	
	ResultObtenerDatosParametricos obtenerDatosParametricos(ParamObtenerDatosParametricos param);
	
	ResultCodiguera obtenerValoresXDatoParametrico(ParamObtenerValoresXDatoParametrico param);
	
	ResultObtenerPolizasCabezal obtenerPolizasCabezal(ParamObtenerPolizasCabezal param);
	
	ResultObtenerPolizaCabezalDetalle obtenerPolizaCabezalDetalle(ParamObtenerPolizaCabezalDetalle param);
	
    ResultFacturacionElectronica facturacionElectronica(ParamFacturacionElectronica param);

	ResultValidarRefacturacion validarRefacturacion(ParamValidarRefacturacion param);
	
	public ResultRefacturacionPoliza refacturarPoliza (ParamRefacturarPoliza param);
}
