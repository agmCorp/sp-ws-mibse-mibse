package uy.com.bse.serviciosEJB;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.LogicaMiBSE;
import uy.com.bse.MiBseLocal;
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

@Stateless
@Interceptors(TransactionInterceptor.class)
public class MiBse implements MiBseLocal {

	private static Logger log = LogManager.getLogger(MiBse.class);
	
	public MiBse() {
		super();
	}

	@Override
	public ResultObtenerComunicacionesCliente obtenerComunicacionesCliente(ParamObtenerComunicacionesCliente param) {
		log.debug("obtenerComunicacionesCliente start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("obtenerComunicacionesCliente end");
		return (ResultObtenerComunicacionesCliente) result;
	}

	@Override
	public ResultObtenerPolizasCliente obtenerPolizasCliente(ParamObtenerPolizasCliente param) {
		log.debug("obtenerPolizasCliente start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("obtenerPolizasCliente end");
		return (ResultObtenerPolizasCliente) result;
	}

	@Override
	public ResultGenerico actualizarFacturacionPoliza(ParamActualizarFacturacionPoliza param) {
		log.debug("actualizarFacturacionPoliza start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("actualizarFacturacionPoliza end");
		return  result;
	}

	@Override
	public ResultObtenerDatosCliente obtenerDatosCliente(ParamObtenerDatosCliente param) {
		log.debug("obtenerDatosCliente start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("obtenerDatosCliente end");
		return (ResultObtenerDatosCliente) result;
	}

	@Override
	public ResultObtenerNumeroCliente obtenerNumeroCliente(ParamObtenerNumeroCliente param) {
		log.debug("obtenerNumeroCliente start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("obtenerNumeroCliente end");
		return (ResultObtenerNumeroCliente) result;
	}

	@Override
	public ResultGenerico actualizarComunicacionesCliente(ParamActualizarComunicacionesCliente param) {
		log.debug("actualizarComunicacionesCliente start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("actualizarComunicacionesCliente end");
		return  result;
	}

	@Override
	public ResultGenerico actualizarDatosCliente(ParamActualizarDatosCliente param) {
		log.debug("actualizarDatosCliente start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("actualizarDatosCliente end");
		return result;
	}

	@Override
	public ResultGenerico actualizarFacturacionPolizaTodo(ParamActualizarFacturacionPolizaTodo param) {
		log.debug("actualizarFacturacionPolizaTodo start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("actualizarFacturacionPolizaTodo end");
		return result;
	}

	@Override
	public ResultValidarCodigoAdhesion validarCodigoAdhesion(ParamValidarCodigoAdhesion param) {
		log.debug("validarCodigoAdhesion start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("validarCodigoAdhesion end");
		return (ResultValidarCodigoAdhesion) result;
	}

	@Override
	public ResultRegistrarCliente registrarCliente(ParamRegistrarCliente param) {
		log.debug("registrarCliente start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("registrarCliente end");
		return (ResultRegistrarCliente) result;
	}
	
	@Override
	public ResultCodiguera listaProfesiones(ParamListaProfesiones param) {
		log.debug("listaProfesiones start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("listaProfesiones end");
		return (ResultCodiguera) result;
	}

	@Override
	public ResultCodiguera listaTipoDocumentos(ParamListaTipoDocumentos param) {
		log.debug("listaTipoDocumentos start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("listaTipoDocumentos end");
		return (ResultCodiguera) result;
	}

	@Override
	public ResultObtenerMailCliente obtenerSeudoMailCliente(ParamObtenerMailCliente param) {
		log.debug("obtenerMailCliente start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("obtenerMailCliente end");
		return (ResultObtenerMailCliente) result;
	}

	@Override
	public ResultGenerico olvidoClave(ParamOlvidoClave param) {
		log.debug("olvidoClave start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("olvidoClave end");
		return (ResultGenerico) result;
	}

	@Override
	public ResultObtenerDatosValidadosCliente obtenerDatosValidadosCliente(ParamObtenerDatosValidadosCliente param) {
		log.debug("obtenerDatosValidadosCliente start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("obtenerDatosValidadosCliente end");
		return (ResultObtenerDatosValidadosCliente) result;
	}

	@Override
	public ResultInformarPagoBancario informarPagoBancario(ParamInformarPagoBancario param) {
		log.debug("informarPagoBancario start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("informarPagoBancario end");
		return (ResultInformarPagoBancario) result;
	}
	
	@Override
	public ResultInformarPagoRedes informarPagoRedes(ParamInformarPagoRedes param) {
		log.debug("informarPagoRedes start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("informarPagoRedes end");
		return (ResultInformarPagoRedes) result;
	}
	
	@Override
	public ResultExisteCliente existeCliente(ParamExisteCliente param) {
		log.debug("existeCliente start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("existeCliente end");
		return (ResultExisteCliente) result;
	}

	@Override
	public ResultObtenerMaximoEndoso obtenerMaximoEndoso(ParamObtenerMaximoEndoso param) {
		log.debug("obtenerMaximoEndoso start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("obtenerMaximoEndoso end");
		return (ResultObtenerMaximoEndoso) result;
	}

	@Override
	public ResultValidacionSOA validacionSOA(ParamValidacionSOA param) {
		log.debug("validacionSOA start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("validacionSOA end");
		return (ResultValidacionSOA) result;
	}
	
	@Override
	public ResultValidacionCartaVerde validacionCartaVerde(ParamValidacionCartaVerde param) {
		log.debug("ResultValidacionCartaVerde start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("ResultValidacionCartaVerde end");
		return (ResultValidacionCartaVerde) result;
	}

	@Override
	public ResultLogActividadMibseWsExt logActividadMibseWsExt(ParamLogActividadMibseWsExt param) {
		log.debug("logActividadMibseWsExt start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("logActividadMibseWsExt end");
		return (ResultLogActividadMibseWsExt)  result;
	}
	
	@Override
	public ResultGenerico borrarMailCliente(ParamBorrarMailCliente param) {
		log.debug("borrarMailCliente start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("borrarMailCliente end");
		return (ResultGenerico) result;
	}
	
	@Override
	public ResultGenerico modificarMailCliente(ParamModificarMailCliente param) {
		log.debug("modificarMailCliente start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("modificarMailCliente end");
		return (ResultGenerico) result;
	}
	
	@Override
	public ResultGenerico altaMailCliente(ParamAltaMailCliente param) {
		log.debug("altaMailCliente start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("altaMailCliente end");
		return (ResultGenerico) result;
	}
	
	@Override
	public ResultObtenerMailsEnvioFacturaCliente obtenerMailsEnvioFacturaCliente(ParamObtenerMailsEnvioFacturaCliente param) {
		log.debug("obtenerMailsEnvioFacturaCliente start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("obtenerMailsEnvioFacturaCliente end");
		return (ResultObtenerMailsEnvioFacturaCliente) result;
	}
	
	@Override
	public ResultValidarCertificadoLibreDeudaADT validarCertificadoLibreDeudaADT(ParamValidarCertificadoLibreDeudaADT param) {
		log.debug("validarCertificadoLibreDeudaADT start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("validarCertificadoLibreDeudaADT end");
		return (ResultValidarCertificadoLibreDeudaADT) result;
	}
	
	@Override
	public ResultObtenerMapaMsgSiniestro ObtenerMapaMsgSiniestro(ParamObtenerMapaMsgSiniestro param) {
		log.debug("ObtenerMapaMsgSiniestro start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("ObtenerMapaMsgSiniestro end");
		return (ResultObtenerMapaMsgSiniestro) result;
	}

	@Override
	public ResultSubirArchivo subirArchivo(ParamSubirArchivo param) {
		log.debug("subirArchivo start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("subirArchivo end");
		return (ResultSubirArchivo) result;
	}
	
	@Override
	public ResultProCarta proCarta(ParamProCarta param) {
		log.debug("proCarta start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("proCarta end");
		return (ResultProCarta) result;
	}

	@Override
	public ResultCorrespondeCartaPoliza correspondeCartaPoliza(ParamCorrespondeCartaPoliza param) {
		log.debug("correspondeCartaPoliza start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("correspondeCartaPoliza end");
		return (ResultCorrespondeCartaPoliza) result;
	}

	@Override
	public ResultProCarta proCarta2(ParamProCarta2 param) {
		log.debug("proCarta2 start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("proCarta2 end");
		return (ResultProCarta) result;
	}

	@Override
	public ResultObtenerPolizasFacturasPagasCliente obtenerPolizasFacturasPagasCliente(ParamObtenerPolizasFacturasPagasCliente param) {
		log.debug("obtenerPolizasCliente start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("obtenerPolizasFacturasPagasCliente end");
		return (ResultObtenerPolizasFacturasPagasCliente) result;
	}

	@Override
	public ResultAdherirFacturaDigital adherirFacturaDigital(ParamAdherirFacturaDigital param) {
		log.debug("adherirFacturaDigital start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("adherirFacturaDigital end");
		return (ResultAdherirFacturaDigital) result;
	}
}
