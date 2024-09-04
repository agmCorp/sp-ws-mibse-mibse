package uy.com.bse.mibse.sp.ws.mibse.ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import uy.com.bse.mibse.sp.ws.mibse.model.dto.*;

@WebService(
        endpointInterface = "uy.com.bse.mibse.sp.ws.mibse.IWsServiciosMiBse",
        portName = "WsServiciosMiBsePort",
        name = "WsServiciosMiBse",
        targetNamespace = "http://WsServiciosMiBse.ws.mibse.bse.com/",
        serviceName = "WsServiciosMiBse")
public interface IWsServiciosMiBse {
    @WebMethod
    public ResultObtenerComunicacionesCliente obtenerComunicacionesCliente(@WebParam(name = "param") ParamObtenerComunicacionesCliente param);

    @WebMethod
    public ResultObtenerPolizasCliente obtenerPolizasCliente(@WebParam(name = "param") ParamObtenerPolizasCliente param);

    // TODO: implementar el metodo actualizarFacturacionPoliza

    @WebMethod
    public ResultObtenerDatosCliente obtenerDatosCliente(@WebParam(name = "param") ParamObtenerDatosCliente param);

    @WebMethod
    ResultObtenerNumeroCliente obtenerNumeroCliente(@WebParam(name = "param") ParamObtenerNumeroCliente param);
}
