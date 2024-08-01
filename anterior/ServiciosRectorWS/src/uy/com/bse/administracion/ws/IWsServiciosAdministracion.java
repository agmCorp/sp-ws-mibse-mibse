package uy.com.bse.administracion.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import uy.com.bse.administracion.ParamAgregarEdoc;
import uy.com.bse.administracion.ParamObtenerDatosCorredor;
import uy.com.bse.administracion.ResultAgregarEdoc;
import uy.com.bse.administracion.ResultObtenerDatosCorredor;

@WebService
public interface IWsServiciosAdministracion {
	/**
	 * 
	 * @param param
	 * @return obtiene los datos de un corredor.
	 */

	@WebMethod
	public ResultObtenerDatosCorredor obtenerDatosCorredor (ParamObtenerDatosCorredor param);

	/**
	 * 
	 * @param param
	 * @return Realiza la adhesion a E-volante de un corredor.
	 */
	
	@WebMethod
	public ResultAgregarEdoc agregarEvolante (ParamAgregarEdoc param);

	}
