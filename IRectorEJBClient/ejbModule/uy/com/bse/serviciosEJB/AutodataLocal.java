package uy.com.bse.serviciosEJB;

import uy.com.bse.autodata.operaciones.ParamConsultaAutodata;
import uy.com.bse.autodata.operaciones.ParamObtenerDatosAuto;
import uy.com.bse.autodata.operaciones.ParamObtenerModelosAutodata;
import uy.com.bse.autodata.operaciones.ResultConsultaAutodata;
import uy.com.bse.autodata.operaciones.ResultObtenerDatosAuto;
import uy.com.bse.autodata.operaciones.ResultObtenerModelosAutodata;

public interface AutodataLocal {
	public ResultConsultaAutodata consultaAutodata(ParamConsultaAutodata param);

	public ResultObtenerDatosAuto obtenerDatosAuto(ParamObtenerDatosAuto param);

	public ResultObtenerModelosAutodata obtenerModelosAutodata(ParamObtenerModelosAutodata param);
}
