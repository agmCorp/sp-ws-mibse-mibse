package uy.com.bse.serviciosEJB;

import javax.ejb.Local;

import uy.com.bse.auditoria.ParamAuditarConsulta;
import uy.com.bse.auditoria.ParamAuditarEndoso;
import uy.com.bse.utilitario.dato.ResultGenerico;

@Local
public interface AuditoriaLocal {

	public ResultGenerico auditarEndoso(final ParamAuditarEndoso param);
	
	public ResultGenerico auditarConsulta(final ParamAuditarConsulta param) ;

}
