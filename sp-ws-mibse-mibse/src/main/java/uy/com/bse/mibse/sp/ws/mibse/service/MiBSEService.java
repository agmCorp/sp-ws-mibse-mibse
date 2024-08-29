package uy.com.bse.mibse.sp.ws.mibse.service;

import org.springframework.beans.factory.annotation.Autowired;
import uy.com.bse.mibse.sp.ws.mibse.utilitarios.dato.ResultGenerico;
import uy.com.bse.mibse.sp.ws.mibse.model.dto.ParamObtenerComunicacionesCliente;
import uy.com.bse.mibse.sp.ws.mibse.model.dto.ResultObtenerComunicacionesCliente;

public class MiBSEService {
    @Autowired
    LogicaMiBSE LogicaMiBSE;

    public ResultObtenerComunicacionesCliente obtenerComunicacionesCliente(ParamObtenerComunicacionesCliente param) {
		log.debug("obtenerComunicacionesCliente start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("obtenerComunicacionesCliente end");
		return (ResultObtenerComunicacionesCliente) result;
	}
    
}
