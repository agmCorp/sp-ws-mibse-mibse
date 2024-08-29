package uy.com.bse.mibse.sp.ws.mibse.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import uy.com.bse.mibse.sp.ws.mibse.model.dto.ResultObtenerComunicacionesCliente;
import uy.com.bse.mibse.sp.ws.mibse.model.dto.ParamObtenerComunicacionesCliente;

@WebService(endpointInterface = "uy.com.bse.mibse.ws.IWsServiciosMiBse", serviceName = "WsServiciosMiBse")
//TODO revisar notation
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE, style = SOAPBinding.Style.RPC)

public interface IWsServiciosMiBse {
    @WebMethod
    ResultObtenerComunicacionesCliente obtenerComunicacionesCliente(ParamObtenerComunicacionesCliente param);
}
