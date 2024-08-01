package uy.com.bse.guc.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import uy.com.bse.guc.interfaces.ParamAltaUsuario;
import uy.com.bse.guc.interfaces.ParamCambioClave;
import uy.com.bse.guc.interfaces.ParamLogin;
import uy.com.bse.guc.interfaces.ParamOlvidoClave;
import uy.com.bse.guc.interfaces.ParamValidar;
import uy.com.bse.guc.interfaces.ResultAltaUsuario;
import uy.com.bse.guc.interfaces.ResultCambioClave;
import uy.com.bse.guc.interfaces.ResultLogin;
import uy.com.bse.guc.interfaces.ResultOlvidoClave;
import uy.com.bse.guc.interfaces.ResultValidar;

@WebService
public interface IWsServiciosGUC {
	@WebMethod
	public ResultLogin login(ParamLogin param);

	@WebMethod
	public ResultValidar validar(ParamValidar param);

	@WebMethod
	public ResultAltaUsuario altaUsuario(ParamAltaUsuario param);

	@WebMethod
	public ResultCambioClave cambioClave(ParamCambioClave param);

	@WebMethod
	public ResultOlvidoClave olvidoClave(ParamOlvidoClave param);
}
