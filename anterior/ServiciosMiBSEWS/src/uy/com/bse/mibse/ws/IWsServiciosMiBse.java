package uy.com.bse.mibse.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.MTOM;
import javax.xml.ws.soap.SOAPBinding;

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
import uy.com.bse.mibse.ParamListaProfesiones;
import uy.com.bse.mibse.ParamListaTipoDocumentos;
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
import uy.com.bse.mibse.ResultAdherirFacturaDigital;
import uy.com.bse.mibse.ResultCorrespondeCartaPoliza;
import uy.com.bse.mibse.ResultExisteCliente;
import uy.com.bse.mibse.ResultInformarPagoBancario;
import uy.com.bse.mibse.ResultInformarPagoRedes;
import uy.com.bse.mibse.ResultLogActividadMibseWsExt;
import uy.com.bse.mibse.ResultObtenerComunicacionesCliente;
import uy.com.bse.mibse.ResultObtenerDatosCliente;
import uy.com.bse.mibse.ResultObtenerDatosValidadosCliente;
import uy.com.bse.mibse.ResultObtenerMailCliente;
import uy.com.bse.mibse.ResultObtenerMailsEnvioFacturaCliente;
import uy.com.bse.mibse.ResultObtenerMapaMsgSiniestro;
import uy.com.bse.mibse.ResultObtenerMaximoEndoso;
import uy.com.bse.mibse.ResultObtenerNumeroCliente;
import uy.com.bse.mibse.ResultObtenerPolizasCliente;
import uy.com.bse.mibse.ResultObtenerPolizasFacturasPagasCliente;
import uy.com.bse.mibse.ResultProCarta;
import uy.com.bse.mibse.ResultRegistrarCliente;
import uy.com.bse.mibse.ResultSubirArchivo;
import uy.com.bse.mibse.ResultValidacionCartaVerde;
import uy.com.bse.mibse.ResultValidacionSOA;
import uy.com.bse.mibse.ResultValidarCertificadoLibreDeudaADT;
import uy.com.bse.mibse.ResultValidarCodigoAdhesion;
import uy.com.bse.utilitario.dato.ResultCodiguera;
import uy.com.bse.utilitario.dato.ResultGenerico;

@WebService
@MTOM
@BindingType(value = SOAPBinding.SOAP12HTTP_MTOM_BINDING)
public interface IWsServiciosMiBse {
	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultObtenerComunicacionesCliente obtenerComunicacionesCliente(ParamObtenerComunicacionesCliente param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultObtenerPolizasCliente obtenerPolizasCliente(ParamObtenerPolizasCliente param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultGenerico actualizarFacturacionPoliza(ParamActualizarFacturacionPoliza param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultObtenerDatosCliente obtenerDatosCliente(ParamObtenerDatosCliente param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultObtenerNumeroCliente obtenerNumeroCliente(ParamObtenerNumeroCliente param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultGenerico actualizarComunicacionesCliente(ParamActualizarComunicacionesCliente param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultGenerico actualizarDatosCliente(ParamActualizarDatosCliente param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultGenerico actualizarFacturacionPolizaTodo(ParamActualizarFacturacionPolizaTodo param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultValidarCodigoAdhesion validarCodigoAdhesion(ParamValidarCodigoAdhesion param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultRegistrarCliente registrarCliente(ParamRegistrarCliente param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultCodiguera listaProfesiones(ParamListaProfesiones param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultCodiguera listaTipoDocumentos(ParamListaTipoDocumentos param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultObtenerMailCliente obtenerSeudoMailCliente(ParamObtenerMailCliente param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultGenerico olvidoClave(ParamOlvidoClave param);
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultObtenerDatosValidadosCliente obtenerDatosValidadosCliente(ParamObtenerDatosValidadosCliente param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultInformarPagoBancario informarPagoBancario(ParamInformarPagoBancario param);
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultInformarPagoRedes informarPagoRedes(ParamInformarPagoRedes param);
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultExisteCliente existeCliente(ParamExisteCliente param);
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultObtenerMaximoEndoso obtenerMaximoEndoso(ParamObtenerMaximoEndoso param);
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultValidacionSOA validacionSOA(ParamValidacionSOA param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultValidacionCartaVerde validacionCartaVerde(ParamValidacionCartaVerde param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultLogActividadMibseWsExt logActividadMibseWsExt(ParamLogActividadMibseWsExt param);
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultGenerico borrarMailCliente(ParamBorrarMailCliente param);
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultGenerico modificarMailCliente(ParamModificarMailCliente param);
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultGenerico altaMailCliente(ParamAltaMailCliente param);
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultObtenerMailsEnvioFacturaCliente obtenerMailsEnvioFacturaCliente(ParamObtenerMailsEnvioFacturaCliente param);
	
	
	@WebMethod
	ResultValidarCertificadoLibreDeudaADT validarCertificadoLibreDeudaADT(ParamValidarCertificadoLibreDeudaADT param);
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultObtenerMapaMsgSiniestro obtenerMapaMsgSiniestro(ParamObtenerMapaMsgSiniestro param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultSubirArchivo subirArchivo(ParamSubirArchivo param);
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultProCarta proCarta(ParamProCarta param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultCorrespondeCartaPoliza correspondeCartaPoliza(ParamCorrespondeCartaPoliza param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultProCarta proCarta2(ParamProCarta2 param);
	
	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultObtenerPolizasFacturasPagasCliente obtenerPolizasFacturasPagasCliente(ParamObtenerPolizasFacturasPagasCliente param);

	/**
	 * 
	 * @param param
	 * @return
	 */
	@WebMethod
	ResultAdherirFacturaDigital adherirFacturaDigital(ParamAdherirFacturaDigital param);
}
