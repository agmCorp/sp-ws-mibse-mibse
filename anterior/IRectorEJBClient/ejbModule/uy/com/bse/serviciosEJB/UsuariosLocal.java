package uy.com.bse.serviciosEJB;

import uy.com.bse.administracion.ParamObtenerDatosCorredor;
import uy.com.bse.administracion.ResultObtenerDatosCorredor;
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

public interface UsuariosLocal {
	public ResultAltaUsuario altaUsuario(ParamAltaUsuario param);

	public ResultModificacionUsuario modificacionUsuario(ParamModificacionUsuario param);

	public ResultBajaUsuario bajaUsuario(ParamBajaUsuario param);

	public ResultConsultaAuditoria consultaAuditoria(ParamConsultaAuditoria param);

	public ResultConsultaAuditoriaDetalle consultaAuditoriaDetalle(ParamConsultaAuditoriaDetalle param);

}
