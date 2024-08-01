package uy.com.bse.autodata.persistencia;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import uy.com.bse.autodata.operaciones.ParamConsultaAutodata;
import uy.com.bse.autodata.operaciones.ParamObtenerDatosAuto;
import uy.com.bse.autodata.operaciones.ParamObtenerModelosAutodata;
import uy.com.bse.maestro.persistencia.ServiciosRector;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;

public class ServiciosAutodataPersist extends ServiciosRector {

	public ResultXmlPL consultaAutodata(ParamConsultaAutodata param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosAutodataPersist.class);
		logueo.setMetodo("consultaAutodata");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodMarca", param.getCodMarca());
		logueo.setParametro("CodAno", param.getCodAno());
		logueo.setParametro("CodModelo", param.getCodModelo());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_consultaAutodata");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.setString(1, param.getCodMarca());
			cstmt.setString(2, param.getCodAno());
			cstmt.setString(3, param.getCodModelo());

			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.execute();

			int codError = cstmt.getInt(4);
			String descError = cstmt.getString(5);
			String sqlError = cstmt.getString(6);
			String valor = cstmt.getString(7);

			if (codError == 0) {
				resultado.setXml(valor);
			} else {
				ServiciosError error = new ServiciosError();
				error.setCodigo(Integer.valueOf(codError));
				error.setDescripcion(descError);
				resultado.setError(error);
				resultado.setHayError(Boolean.TRUE);
			}

			logResultados(resultado, logueo, codError, descError, sqlError);
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, cstmt);
		}
		return resultado;
	}

	public ResultXmlPL obtenerDatosAuto(ParamObtenerDatosAuto param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosAutodataPersist.class);
		logueo.setMetodo("obtenerDatosAuto");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodAutoData", param.getCodAutoData());
		
		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerDatosAutoData");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?)}");
			cstmt.setString(1, param.getCodAutoData());
			cstmt.registerOutParameter(2, Types.INTEGER);
			cstmt.registerOutParameter(3, Types.VARCHAR);
			cstmt.registerOutParameter(4, Types.VARCHAR);
			cstmt.registerOutParameter(5, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(2);
			String descError = cstmt.getString(3);
			String sqlError = cstmt.getString(4);
			Clob clob = cstmt.getClob(5);

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
	
	public ResultXmlPL obtenerModelosAutodata(ParamObtenerModelosAutodata param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosAutodataPersist.class);
		logueo.setMetodo("obtenerModelosAutodata");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
	
		
		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerModelosAutoData");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?)}");
		
			cstmt.setString(1, param.getUsuario());
			cstmt.registerOutParameter(2, Types.INTEGER);
			cstmt.registerOutParameter(3, Types.VARCHAR);
			cstmt.registerOutParameter(4, Types.VARCHAR);
			cstmt.registerOutParameter(5, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(2);
			String descError = cstmt.getString(3);
			String sqlError = cstmt.getString(4);
			Clob clob = cstmt.getClob(5);

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
