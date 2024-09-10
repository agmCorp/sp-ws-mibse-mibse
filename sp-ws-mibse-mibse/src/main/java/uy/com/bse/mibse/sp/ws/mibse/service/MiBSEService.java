package uy.com.bse.mibse.sp.ws.mibse.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uy.com.bse.mibse.sp.ws.mibse.model.dto.*;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultCodiguera;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultGenerico;

@Service
public class MiBSEService {
	@Autowired
	LogicaMiBSE logicaMiBSE;
	private static final Logger log = LoggerFactory.getLogger(MiBSEService.class);

	public ResultObtenerComunicacionesCliente obtenerComunicacionesCliente(ParamObtenerComunicacionesCliente param) {
		log.debug("obtenerComunicacionesCliente start: " + param);
		ResultGenerico result = logicaMiBSE.solve(param);
		log.debug("obtenerComunicacionesCliente end");
		return (ResultObtenerComunicacionesCliente) result;
	}

	public ResultObtenerPolizasCliente obtenerPolizasCliente(ParamObtenerPolizasCliente param) {
		log.debug("obtenerPolizasCliente start: " + param);
		ResultGenerico result = logicaMiBSE.solve(param);
		log.debug("obtenerPolizasCliente end");
		return (ResultObtenerPolizasCliente) result;
	}

	// TODO: implementar el metodo actualizarFacturacionPoliza

	public ResultObtenerDatosCliente obtenerDatosCliente(ParamObtenerDatosCliente param) {
		log.debug("obtenerDatosCliente start: " + param);
		ResultGenerico result = logicaMiBSE.solve(param);
		log.debug("obtenerDatosCliente end");
		return (ResultObtenerDatosCliente) result;
	}

	public ResultObtenerNumeroCliente obtenerNumeroCliente(ParamObtenerNumeroCliente param) {
		log.debug("obtenerNumeroCliente start: " + param);
		ResultGenerico result = logicaMiBSE.solve(param);
		log.debug("obtenerNumeroCliente end");
		return (ResultObtenerNumeroCliente) result;
	}

	public ResultCodiguera listaProfesiones(ParamListaProfesiones param) {
		log.debug("listaProfesiones start: " + param);
		ResultGenerico result = logicaMiBSE.solve(param);
		log.debug("listaProfesiones end");
		return (ResultCodiguera) result;
	}

	public ResultExisteCliente existeCliente(ParamExisteCliente param) {
		log.debug("existeCliente start: " + param);
		ResultGenerico result = logicaMiBSE.solve(param);
		log.debug("existeCliente end");
		return (ResultExisteCliente) result;
	}
}
