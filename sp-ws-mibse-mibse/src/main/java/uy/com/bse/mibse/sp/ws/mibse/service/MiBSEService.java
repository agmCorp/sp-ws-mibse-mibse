package uy.com.bse.mibse.sp.ws.mibse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.com.bse.mibse.sp.ws.mibse.model.dto.ParamObtenerComunicacionesCliente;
import uy.com.bse.mibse.sp.ws.mibse.model.dto.ResultObtenerComunicacionesCliente;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultGenerico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MiBSEService {
    @Autowired
    LogicaMiBSE LogicaMiBSE;
    private static final Logger log = LoggerFactory.getLogger(MiBSEService.class);


    public ResultObtenerComunicacionesCliente obtenerComunicacionesCliente(ParamObtenerComunicacionesCliente param) {
		log.debug("obtenerComunicacionesCliente start: " + param);
		ResultGenerico result = LogicaMiBSE.solve(param);
		log.debug("obtenerComunicacionesCliente end");
		return (ResultObtenerComunicacionesCliente) result;
	}
    
}
