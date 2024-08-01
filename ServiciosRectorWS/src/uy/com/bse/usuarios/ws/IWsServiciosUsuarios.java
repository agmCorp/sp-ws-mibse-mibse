package uy.com.bse.usuarios.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

import uy.com.bse.usuarios.operaciones.ParamAltaUsuario;
import uy.com.bse.usuarios.operaciones.ParamBajaUsuario;
import uy.com.bse.usuarios.operaciones.ParamConsultaAuditoria;
import uy.com.bse.usuarios.operaciones.ParamConsultaAuditoriaDetalle;
import uy.com.bse.usuarios.operaciones.ParamModificacionUsuario;
import uy.com.bse.usuarios.operaciones.ResultAltaUsuario;
import uy.com.bse.usuarios.operaciones.ResultBajaUsuario;
import uy.com.bse.usuarios.operaciones.ResultConsultaAuditoria;
import uy.com.bse.usuarios.operaciones.ResultConsultaAuditoriaDetalle;
import uy.com.bse.usuarios.operaciones.ResultModificacionUsuario;

@WebService
public interface IWsServiciosUsuarios {
	/**
	 * 
	 * @param param
	 * @return Realiza el alta de un usuario perteneciente a un corredor.
	 */

	@WebMethod
	public ResultAltaUsuario altaUsuario (ParamAltaUsuario param);

	/**
	 * 
	 * @param param
	 * @return Realiza la modificación de datos de un usuario perteneciente a un corredor.
	 */
	
	@WebMethod
	public ResultModificacionUsuario modificacionUsuario (ParamModificacionUsuario param);

	/**
	 * 
	 * @param param
	 * @return Realiza la baja lógica de un usuario perteneciente a un corredor.
	 */
	
	@WebMethod
	public ResultBajaUsuario bajaUsuario (ParamBajaUsuario param);

	/**
	 * 
	 * @param param
	 * @return Devuelve la información de auditoría.
	 */
	@WebMethod
	public ResultConsultaAuditoria consultaAuditoria (ParamConsultaAuditoria param);

	/**
	 * 
	 * @param param
	 * @return Devuelve el detalle de la información de auditoría.
	 */
	@WebMethod
	public ResultConsultaAuditoriaDetalle consultaAuditoriaDetalle (ParamConsultaAuditoriaDetalle param);
}
