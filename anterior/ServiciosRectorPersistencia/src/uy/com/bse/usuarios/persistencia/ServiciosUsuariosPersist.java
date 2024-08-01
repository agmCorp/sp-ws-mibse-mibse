package uy.com.bse.usuarios.persistencia;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.maestro.persistencia.ServiciosRector;
import uy.com.bse.usuarios.operaciones.ParamAltaUsuario;
import uy.com.bse.usuarios.operaciones.ParamBajaUsuario;
import uy.com.bse.usuarios.operaciones.ParamConsultaAuditoria;
import uy.com.bse.usuarios.operaciones.ParamConsultaAuditoriaDetalle;
import uy.com.bse.usuarios.operaciones.ParamModificacionUsuario;
import uy.com.bse.usuarios.operaciones.ResultAltaUsuario;
import uy.com.bse.usuarios.operaciones.ResultBajaUsuario;
import uy.com.bse.usuarios.operaciones.ResultModificacionUsuario;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.util.ValuesUtils;

public class ServiciosUsuariosPersist extends ServiciosRector {
	private static Logger LOG = LogManager.getLogger(ServiciosUsuariosPersist.class);
	public static final String SERVRECTPROP = "serviciosRector.properties";	

	public ResultAltaUsuario altaUsuario(ParamAltaUsuario param) {
		ResultAltaUsuario resultado = new ResultAltaUsuario();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosUsuariosPersist.class);
		logueo.setMetodo("altaUsuario");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Alcance", param.getAlcance());
		logueo.setParametro("Cedula", param.getCedula());
		logueo.setParametro("Mail", param.getMail());
		logueo.setParametro("Nombre", param.getNombre());
		logueo.setParametro("Telefono", param.getTelefono());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_altaUsuario");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, param.getCedula());
			cstmt.setString(2, param.getUsuario());
			cstmt.setString(3, param.getNombre());
			cstmt.setString(4, param.getTelefono());
			cstmt.setString(5, param.getMail());
			cstmt.setInt(6, Integer.valueOf(param.getAlcance()));
			cstmt.registerOutParameter(7, Types.INTEGER);
			cstmt.registerOutParameter(8, Types.VARCHAR);
			cstmt.registerOutParameter(9, Types.VARCHAR);
			cstmt.execute();

			int codError = cstmt.getInt(7);
			String descError = cstmt.getString(8);
			String sqlError = cstmt.getString(9);

			if (codError != 0) {
				ServiciosError error = new ServiciosError();
				error.setCodigo(Integer.valueOf(codError));
				error.setDescripcion(descError);

				resultado.setError(error);
				resultado.setHayError(Boolean.TRUE);
				resultado.setUsuarioEjecutor(param.getUsuario());
			}else{
				resultado.setUsuarioEjecutor(param.getUsuario());
				resultado.setNroCorredor(ValuesUtils.toString(obtenerCodigoCorredor(param)));
			}

			logueo.setParametro("Codigo salida", codError);
			logueo.setParametro("Descripcion salida", descError);
			logueo.setParametro("Sql salida", sqlError);
			LOG.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, cstmt);
		}

		return resultado;

	}

	public ResultModificacionUsuario modificacionUsuario(ParamModificacionUsuario param) {
		ResultModificacionUsuario resultado = new ResultModificacionUsuario();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosUsuariosPersist.class);
		logueo.setMetodo("modificacionUsuario");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Alcance", param.getAlcance());
		logueo.setParametro("Cedula", param.getCedula());
		logueo.setParametro("Mail", param.getMail());
		logueo.setParametro("Nombre", param.getNombre());
		logueo.setParametro("Telefono", param.getTelefono());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_modificacionUsuario");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, param.getCedula());
			cstmt.setString(2, param.getUsuario());
			cstmt.setString(3, param.getNombre());
			cstmt.setString(4, param.getTelefono());
			cstmt.setString(5, param.getMail());
			cstmt.setInt(6, Integer.valueOf(param.getAlcance()));
			cstmt.registerOutParameter(7, Types.INTEGER);
			cstmt.registerOutParameter(8, Types.VARCHAR);
			cstmt.registerOutParameter(9, Types.VARCHAR);
			cstmt.execute();

			int codError = cstmt.getInt(7);
			String descError = cstmt.getString(8);
			String sqlError = cstmt.getString(9);

			if (codError != 0) {
				ServiciosError error = new ServiciosError();
				error.setCodigo(Integer.valueOf(codError));
				error.setDescripcion(descError);

				resultado.setError(error);
				resultado.setHayError(Boolean.TRUE);
			}

			logueo.setParametro("Codigo salida", codError);
			logueo.setParametro("Descripcion salida", descError);
			logueo.setParametro("Sql salida", sqlError);

			LOG.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, cstmt);
		}

		return resultado;
	}

	public ResultBajaUsuario bajaUsuario(ParamBajaUsuario param) {
		ResultBajaUsuario resultado = new ResultBajaUsuario();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosUsuariosPersist.class);
		logueo.setMetodo("bajaUsuario");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cedula", param.getCedula());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_bajaUsuario");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?)}");
			cstmt.setString(1, param.getCedula());
			cstmt.setString(2, param.getUsuario());
			cstmt.registerOutParameter(3, Types.INTEGER);
			cstmt.registerOutParameter(4, Types.VARCHAR);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.execute();

			int codError = cstmt.getInt(3);
			String descError = cstmt.getString(4);
			String sqlError = cstmt.getString(5);

			if (codError != 0) {
				ServiciosError error = new ServiciosError();
				error.setCodigo(Integer.valueOf(codError));
				error.setDescripcion(descError);

				resultado.setError(error);
				resultado.setHayError(Boolean.TRUE);
			}

			logueo.setParametro("Codigo salida", codError);
			logueo.setParametro("Descripcion salida", descError);
			logueo.setParametro("Sql salida", sqlError);

			LOG.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, cstmt);
		}
		return resultado;
	}

	public ResultXmlPL consultaAuditoria(ParamConsultaAuditoria param) {
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosUsuariosPersist.class);
		logueo.setMetodo("consultaAuditoria");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Fecha inicial", param.getFechaIni());
		logueo.setParametro("Fecha final", param.getFechaFin());
		logueo.setParametro("Nombre", param.getNombre());
		logueo.setParametro("Apellido", param.getApellido());
		logueo.setParametro("Usuario empleado", param.getUsuarioEmpleado());
		logueo.setParametro("Cedula", param.getCedula());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_consultaAuditoria");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, param.getUsuario());

			if (param.getFechaIni() == null || (param.getFechaIni().equals("")) || (param.getFechaIni().equals(Values.NULL))) {
				cstmt.setNull(2, Types.DATE);
			} else {
				cstmt.setDate(2, toSqlDate(param.getFechaIni()));
			}

			if (param.getFechaFin() == null || (param.getFechaFin().equals("")) || (param.getFechaFin().equals(Values.NULL))) {
				cstmt.setNull(3, Types.DATE);
			} else {
				cstmt.setDate(3, toSqlDate(param.getFechaFin()));
			}

			cstmt.setString(4, param.getNombre());
			cstmt.setString(5, param.getApellido());
			cstmt.setString(6, param.getUsuarioEmpleado());
			cstmt.setString(7, param.getCedula());

			cstmt.registerOutParameter(8, Types.INTEGER);
			cstmt.registerOutParameter(9, Types.VARCHAR);
			cstmt.registerOutParameter(10, Types.VARCHAR);
			cstmt.registerOutParameter(11, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(8);
			String descError = cstmt.getString(9);
			String sqlError = cstmt.getString(10);
			Clob clob = cstmt.getClob(11);
			procesarResultados(resultado, logueo, codError, descError, sqlError, clob);
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, cstmt);
		}
		return resultado;

	}

	public ResultXmlPL consultaAuditoriaDetalle(ParamConsultaAuditoriaDetalle param) {
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosUsuariosPersist.class);
		logueo.setMetodo("consultaAuditoriaDetalle");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Fecha inicial", param.getFechaIni());
		logueo.setParametro("Fecha final", param.getFechaFin());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_consultaAuditoriaDetalle");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.setString(1, param.getUsuario());

			if (param.getFechaIni() == null || (param.getFechaIni().equals("")) || (param.getFechaIni().equals(Values.NULL))) {
				cstmt.setNull(2, Types.DATE);
			} else {
				cstmt.setDate(2, toSqlDate(param.getFechaIni()));
			}

			if (param.getFechaFin() == null || (param.getFechaFin().equals("")) || (param.getFechaFin().equals(Values.NULL))) {
				cstmt.setNull(3, Types.DATE);
			} else {
				cstmt.setDate(3, toSqlDate(param.getFechaFin()));
			}

			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.registerOutParameter(7, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(4);
			String descError = cstmt.getString(5);
			String sqlError = cstmt.getString(6);
			Clob clob = cstmt.getClob(7);
			procesarResultados(resultado, logueo, codError, descError, sqlError, clob);
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, cstmt);
		}
		return resultado;
	}

}
