package uy.com.bse.serviciosEJB;

import javax.ejb.Local;

import uy.com.bse.administracion.ParamAgregarEdoc;
import uy.com.bse.administracion.ParamObtenerDatosCorredor;
import uy.com.bse.administracion.ResultAgregarEdoc;
import uy.com.bse.administracion.ResultObtenerDatosCorredor;


@Local
public interface AdministracionLocal {

	ResultAgregarEdoc agregarEvolante(ParamAgregarEdoc param);

	ResultObtenerDatosCorredor obtenerDatosCorredor(ParamObtenerDatosCorredor param);

	

}
