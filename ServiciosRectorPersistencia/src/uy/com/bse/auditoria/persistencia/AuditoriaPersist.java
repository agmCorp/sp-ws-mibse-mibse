package uy.com.bse.auditoria.persistencia;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import uy.com.bse.auditoria.ParamAuditarConsulta;
import uy.com.bse.auditoria.ParamAuditarEndoso;
import uy.com.bse.maestro.persistencia.ServiciosRector;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;

public class AuditoriaPersist extends ServiciosRector{
	
	
	public ResultGenerico auditarEndoso(ParamAuditarEndoso param) {
		ResultGenerico resultado = new ResultGenerico();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setClase(AuditoriaPersist.class);
		logueo.setMetodo("auditarEndoso");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero endoso", param.getNumEndoso());
		logueo.setParametro("Accion", param.getAccion());
		logueo.setParametro("Detalle", param.getDetalle());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_auditar");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			
			if (param.getNumCotizacion() != null) {
				cstmt.setInt(1, param.getNumCotizacion());
			} else {
				cstmt.setNull(1, Types.INTEGER);
			}
			
			if (param.getCodRamo() != null) {
				cstmt.setInt(2, param.getCodRamo());
			} else {
				cstmt.setNull(2, Types.INTEGER);
			}
			
			if (param.getNumPoliza() != null) {
				cstmt.setInt(3, param.getNumPoliza());
			} else {
				cstmt.setNull(3, Types.INTEGER);
			}
			
			if (param.getNumEndoso() != null) {
				cstmt.setInt(4, param.getNumEndoso());
			} else {
				cstmt.setNull(4, Types.INTEGER);
			}
			cstmt.setString(5, param.getAccion());
			cstmt.setString(6, param.getDetalle());
			cstmt.setString(7, param.getUsuario());
			
			cstmt.execute();
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			logueo.setException("SQLException");
			logueo.setError(e.getMessage());
			log.error(logueo.getMensaje());
			//NO MANDA MAIL
			resultado.setHayError(Boolean.TRUE);
			resultado.setError(obtenerDescripcionError(Values.SQLEXCEPT));
		} catch (Exception ex) {
			logueo.setException("Exception");
			logueo.setError(ex.getMessage());
			log.error(logueo.getMensaje());
			//NO MANDA MAIL
			resultado.setHayError(Boolean.TRUE);
			resultado.setError(obtenerDescripcionError(Values.EXCEPTION));
		} finally {
			liberarRecursos(conn, cstmt);
		}

		return resultado;
	}
	
	public ResultGenerico auditarConsulta(ParamAuditarConsulta param) {
		ResultGenerico resultado = new ResultGenerico();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setClase(AuditoriaPersist.class);
		logueo.setMetodo("auditarConsulta");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Accion", param.getAccion());
		logueo.setParametro("Detalle", param.getDetalle());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_auditar");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			
		     //NO SE USAN
				cstmt.setNull(1, Types.INTEGER);
				cstmt.setNull(2, Types.INTEGER);
				cstmt.setNull(3, Types.INTEGER);
				cstmt.setNull(4, Types.INTEGER);
	
			cstmt.setString(5, param.getAccion());
			cstmt.setString(6, param.getDetalle());
			cstmt.setString(7, param.getUsuario());
			
			cstmt.execute();
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			logueo.setException("SQLException");
			logueo.setError(e.getMessage());
			log.error(logueo.getMensaje());
			//NO MANDA MAIL
			resultado.setHayError(Boolean.TRUE);
			resultado.setError(obtenerDescripcionError(Values.SQLEXCEPT));
		} catch (Exception ex) {
			logueo.setException("Exception");
			logueo.setError(ex.getMessage());
			log.error(logueo.getMensaje());
			//NO MANDA MAIL
			resultado.setHayError(Boolean.TRUE);
			resultado.setError(obtenerDescripcionError(Values.EXCEPTION));
		} finally {
			liberarRecursos(conn, cstmt);
		}

		return resultado;
	}


}
