package uy.com.bse.autodata.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;



import uy.com.bse.autodata.operaciones.ParamConsultaAutodata;
import uy.com.bse.autodata.operaciones.ParamObtenerDatosAuto;
import uy.com.bse.autodata.operaciones.ParamObtenerModelosAutodata;
import uy.com.bse.autodata.operaciones.ResultConsultaAutodata;
import uy.com.bse.autodata.operaciones.ResultObtenerDatosAuto;
import uy.com.bse.autodata.operaciones.ResultObtenerModelosAutodata;

@WebService
public interface IWsServiciosAutodata {
	@WebMethod
	public ResultConsultaAutodata consultaAutodata (ParamConsultaAutodata param);
	
	@WebMethod
	public ResultObtenerDatosAuto obtenerDatosAuto (ParamObtenerDatosAuto param);
	
	@WebMethod
	public ResultObtenerModelosAutodata obtenerModelosAutodata(ParamObtenerModelosAutodata param);
}
