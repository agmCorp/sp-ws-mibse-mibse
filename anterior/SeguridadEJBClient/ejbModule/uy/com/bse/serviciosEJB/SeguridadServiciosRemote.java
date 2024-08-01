package uy.com.bse.serviciosEJB;

import javax.ejb.Remote;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.seguridad.ParamLogin;
import uy.com.bse.utilitario.seguridad.ParamLoginGroup;
import uy.com.bse.utilitario.seguridad.ParamLogout;
import uy.com.bse.utilitario.seguridad.ParamValidar;
import uy.com.bse.utilitario.seguridad.ResultLogin;
import uy.com.bse.utilitario.seguridad.ResultLoginGroup;
import uy.com.bse.utilitario.seguridad.ResultLogout;
import uy.com.bse.utilitario.seguridad.ResultObtenerUsuario;
import uy.com.bse.utilitario.seguridad.ResultValidar;

@Remote
public interface SeguridadServiciosRemote {

	ResultLogin login(ParamLogin param);
	ResultLoginGroup loginGroup(ParamLoginGroup param);
	ResultLogout logout(ParamLogout param);
	ResultValidar validar(ParamValidar param);
	ResultObtenerUsuario obtenerUsuario(ParamGenerico param);
 }
