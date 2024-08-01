package uy.com.bse;

import javax.ejb.Local;

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

@Local
public interface MiBseLocal {

	ResultObtenerComunicacionesCliente obtenerComunicacionesCliente(ParamObtenerComunicacionesCliente param);

	ResultObtenerPolizasCliente obtenerPolizasCliente(ParamObtenerPolizasCliente param);

	ResultGenerico actualizarFacturacionPoliza(ParamActualizarFacturacionPoliza param);

	ResultObtenerDatosCliente obtenerDatosCliente(ParamObtenerDatosCliente param);

	ResultObtenerNumeroCliente obtenerNumeroCliente(ParamObtenerNumeroCliente param);

	ResultGenerico actualizarComunicacionesCliente(ParamActualizarComunicacionesCliente param);

	ResultGenerico actualizarDatosCliente(ParamActualizarDatosCliente param);

	ResultGenerico actualizarFacturacionPolizaTodo(ParamActualizarFacturacionPolizaTodo param);

	ResultValidarCodigoAdhesion validarCodigoAdhesion(ParamValidarCodigoAdhesion param);

	ResultRegistrarCliente registrarCliente(ParamRegistrarCliente param);

	ResultCodiguera listaProfesiones(ParamListaProfesiones param);

	ResultCodiguera listaTipoDocumentos(ParamListaTipoDocumentos param);

	ResultObtenerMailCliente obtenerSeudoMailCliente(ParamObtenerMailCliente param);

	ResultGenerico olvidoClave(ParamOlvidoClave param);

	ResultObtenerDatosValidadosCliente obtenerDatosValidadosCliente(ParamObtenerDatosValidadosCliente param);

	ResultInformarPagoBancario informarPagoBancario(ParamInformarPagoBancario param);

	ResultInformarPagoRedes informarPagoRedes(ParamInformarPagoRedes param);

	ResultExisteCliente existeCliente(ParamExisteCliente param);

	ResultObtenerMaximoEndoso obtenerMaximoEndoso(ParamObtenerMaximoEndoso param);

	ResultValidacionSOA validacionSOA(ParamValidacionSOA param);

	ResultValidacionCartaVerde validacionCartaVerde(ParamValidacionCartaVerde param);

	ResultLogActividadMibseWsExt logActividadMibseWsExt(ParamLogActividadMibseWsExt param);

	ResultGenerico borrarMailCliente(ParamBorrarMailCliente param);

	ResultGenerico modificarMailCliente(ParamModificarMailCliente param);

	ResultGenerico altaMailCliente(ParamAltaMailCliente param);

	ResultObtenerMailsEnvioFacturaCliente obtenerMailsEnvioFacturaCliente(ParamObtenerMailsEnvioFacturaCliente param);

	ResultValidarCertificadoLibreDeudaADT validarCertificadoLibreDeudaADT(ParamValidarCertificadoLibreDeudaADT param);

	ResultObtenerMapaMsgSiniestro ObtenerMapaMsgSiniestro(ParamObtenerMapaMsgSiniestro param);

	ResultSubirArchivo subirArchivo(ParamSubirArchivo param);
	
	ResultProCarta proCarta(ParamProCarta param);
	
	ResultCorrespondeCartaPoliza correspondeCartaPoliza(ParamCorrespondeCartaPoliza param);
	
	ResultProCarta proCarta2(ParamProCarta2 param);
	
	ResultObtenerPolizasFacturasPagasCliente obtenerPolizasFacturasPagasCliente(ParamObtenerPolizasFacturasPagasCliente param);
	
	ResultAdherirFacturaDigital adherirFacturaDigital(ParamAdherirFacturaDigital param);
}
