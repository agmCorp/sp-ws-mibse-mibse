package uy.com.bse.guc;

import javax.ejb.Remote;

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

@Remote
public interface GUCRemote {

	public ResultLogin login(ParamLogin param);

	public ResultValidar validar(ParamValidar param);

	public ResultAltaUsuario altaUsuario(ParamAltaUsuario param);

	public ResultCambioClave cambioClave(ParamCambioClave param);

	public ResultOlvidoClave olvidoClave(ParamOlvidoClave param);
	
}
