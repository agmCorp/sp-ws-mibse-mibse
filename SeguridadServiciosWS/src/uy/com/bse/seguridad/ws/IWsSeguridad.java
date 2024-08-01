package uy.com.bse.seguridad.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import uy.com.bse.utilitario.seguridad.ParamLogin;
import uy.com.bse.utilitario.seguridad.ParamLoginGroup;
import uy.com.bse.utilitario.seguridad.ParamLogout;
import uy.com.bse.utilitario.seguridad.ParamValidar;
import uy.com.bse.utilitario.seguridad.ResultLogin;
import uy.com.bse.utilitario.seguridad.ResultLoginGroup;
import uy.com.bse.utilitario.seguridad.ResultLogout;
import uy.com.bse.utilitario.seguridad.ResultValidar;



@WebService
public interface IWsSeguridad {

	@WebMethod
	public ResultLogin login(ParamLogin param);
	
	@WebMethod
	public ResultLoginGroup loginGroup(ParamLoginGroup param);

	@WebMethod
	public ResultValidar validar(ParamValidar param);

	@WebMethod
	public ResultLogout logout(ParamLogout param);

}
