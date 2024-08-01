package uy.com.bse.cotizadorvya.persistencia;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.cotizadorvya.entidades.Cobertura;
import uy.com.bse.cotizadorvya.operaciones.ParamCalcularPlanes;
import uy.com.bse.cotizadorvya.operaciones.ParamMontosIniciales;
import uy.com.bse.cotizadorvya.operaciones.ParamObtenerCoberturas;
import uy.com.bse.cotizadorvya.operaciones.ParamPorcentajeAnticipo;
import uy.com.bse.cotizadorvya.operaciones.ParamRentaMaxima;
import uy.com.bse.cotizadorvya.operaciones.ResultObtenerCoberturas;
import uy.com.bse.cotizadorvya.operaciones.ResultRentaMaxima;
import uy.com.bse.maestro.persistencia.ServiciosRector;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;

public class ServiciosCotizadorVYAPersist extends ServiciosRector {
	private static Logger LOG = LogManager.getLogger(ServiciosCotizadorVYAPersist.class);
	public static final String SERVRECTPROP = "serviciosRector.properties";	

	public ResultXmlPL calcularPlanes(ParamCalcularPlanes param) {
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosCotizadorVYAPersist.class);
		logueo.setMetodo("calcularPlanes");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		
        logueo.setParametro("codMoneda", param.getCodMoneda());
		logueo.setParametro("fechaNacimiento", param.getFechaNacimiento());
		logueo.setParametro("capital", param.getCapital());
		logueo.setParametro("codCategoria", param.getCodCategoria());
		logueo.setParametro("codPlan", param.getCodPlan());
		logueo.setParametro("muerteAccidental", param.getMuerteAccidental());
		logueo.setParametro("invalidez", param.getInvalidez());
		logueo.setParametro("ingresoSeguro", param.getIngresoSeguro());
		logueo.setParametro("ingresoMensual", param.getIngresoMensual());
		logueo.setParametro("rentaIngresoSeguro", param.getRentaIngresoSeguro());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_calcularPlanes");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			
			cstmt.setInt(1, param.getCodMoneda());
			cstmt.setDate(2, toSqlDate(param.getFechaNacimiento()));
			cstmt.setDouble(3, param.getCapital());
			cstmt.setString(4, param.getCodCategoria());
			if (param.getCodPlan() != null) {
				cstmt.setString(5, param.getCodPlan());
			} else {
				cstmt.setNull(5, Types.VARCHAR);
			}
			if (param.getMuerteAccidental()) {
				cstmt.setString(6, "S");
			} else {
				cstmt.setString(6, "N");
			}
			if (param.getInvalidez()) {
				cstmt.setString(7, "S");
			} else {
				cstmt.setString(7, "N");
			}
			if (param.getIngresoSeguro()) {
				cstmt.setString(8, "S");
			} else {
				cstmt.setString(8, "N");
			}			
			if (param.getRentaIngresoSeguro() != null) {
				cstmt.setDouble(9, param.getRentaIngresoSeguro());
			} else {
				cstmt.setNull(9, Types.NUMERIC);
			}			
			if (param.getIngresoMensual() != null) {
				cstmt.setDouble(10, param.getIngresoMensual());
			} else {
				cstmt.setNull(10, Types.NUMERIC);
			}			
			cstmt.setString(11, param.getUsuario());
			
			cstmt.registerOutParameter(12, Types.INTEGER);
			cstmt.registerOutParameter(13, Types.VARCHAR);
			cstmt.registerOutParameter(14, Types.VARCHAR);
			cstmt.registerOutParameter(15, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(12);
			String descError = cstmt.getString(13);
			String sqlError = cstmt.getString(14);
			Clob clob = cstmt.getClob(15);
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
	
	public ResultObtenerCoberturas obtenerCoberturas(ParamObtenerCoberturas param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosCotizadorVYAPersist.class);
		logueo.setMetodo("obtenerCoberturas");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultObtenerCoberturas resultado = new ResultObtenerCoberturas();

		try {
			conn = crearConexion();

			final StringBuffer sql = new StringBuffer();
			sql.append(" SELECT DISTINCT SUBSTR(B.DATO, 1, INSTR(B.DATO, ':')-1) cod_producto,");
			sql.append(" SUBSTR(B.DATO, INSTR(B.DATO, ':',1)+1, INSTR(B.DATO, ':',-1)-INSTR(B.DATO, ':',1)-1) cod_plan,");
			sql.append(" TRIM(SUBSTR(B.DATO, INSTR(B.DATO, ':',-1)+1, LENGTH(B.DATO))) cod_categoria,");
			sql.append(" DECODE(SUBSTR(B.DATO, 1, INSTR(B.DATO, ':')-1), 'TNOC',");
			sql.append(" (SELECT CAPB_DE_PLAN ||' '||SUBSTR(SUBSTR(B.DATO, INSTR(B.DATO, ':',1)+1, INSTR(B.DATO, ':',-1)-INSTR(B.DATO, ':',1)-1), -2)");
			sql.append(" FROM CART_PRODPLANES");
			sql.append(" WHERE CAPB_CARP_CD_RAMO = 12");
			sql.append(" AND CAPB_CAPU_CD_PRODUCTO = SUBSTR(B.DATO, 1, INSTR(B.DATO, ':')-1)");
			sql.append(" AND CAPB_CD_PLAN = 'TNOC'),");
			sql.append(" (SELECT CAPB_DE_PLAN");
			sql.append(" FROM CART_PRODPLANES");
			sql.append(" WHERE CAPB_CARP_CD_RAMO = 12");
			sql.append(" AND CAPB_CAPU_CD_PRODUCTO = SUBSTR(B.DATO, 1, INSTR(B.DATO, ':')-1)");
			sql.append(" AND CAPB_CD_PLAN = SUBSTR(B.DATO, INSTR(B.DATO, ':',1)+1, INSTR(B.DATO, ':',-1)-INSTR(B.DATO, ':',1)-1))) desc_plan");
			sql.append(" FROM (SELECT TRIM(REGEXP_SUBSTR(str, '[^|]+', 1, LEVEL)) dato");
			sql.append(" FROM (SELECT P1.GEPA_NM_VALOR_CAMPO||'|'||P2.GEPA_NM_VALOR_CAMPO||'|'||P3.GEPA_NM_VALOR_CAMPO||'|'||P4.GEPA_NM_VALOR_CAMPO STR");
			sql.append(" FROM GENT_PARAMETROS P1,");
			sql.append(" GENT_PARAMETROS P2,");
			sql.append(" GENT_PARAMETROS P3,");
			sql.append(" GENT_PARAMETROS P4");
			sql.append(" WHERE P1.GEPA_NM_CAMPO = 'PLANES_COT_VIDA_TEMP'");
			sql.append(" AND P2.GEPA_NM_CAMPO = 'PLANES_COT_VIDA_TNOC'");
			sql.append(" AND P3.GEPA_NM_CAMPO = 'PLANES_COT_VIDA_VITAL'");
			sql.append(" AND P4.GEPA_NM_CAMPO = 'PLANES_COT_VIDA_VYA')");
			sql.append(" CONNECT BY INSTR(str, '|', 1, LEVEL - 1) > 0) B");
			sql.append(" ORDER BY 1, 2");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			result = pst.executeQuery();
			
			ArrayList<Cobertura> coberturas = new ArrayList<Cobertura>();
			
			Cobertura cobertura;
			while (result != null && result.next()) {
				cobertura = new Cobertura();
				
				String codProducto = result.getString("COD_PRODUCTO");
				if (codProducto != null) {
					cobertura.setCodProducto(codProducto);
				}
				String codPlan = result.getString("COD_PLAN");
				if (codPlan != null) {
					cobertura.setCodPlan(codPlan);
				}
				String codCategoria = result.getString("COD_CATEGORIA");
				if (codCategoria != null) {
					cobertura.setCodCategoria(codCategoria);
				}
				String nombrePlan = result.getString("DESC_PLAN");
				if (nombrePlan != null) {
					cobertura.setNombrePlan(nombrePlan);
				}
				
				coberturas.add(cobertura);
			}
			resultado.setCoberturas(coberturas);
			LOG.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}
		return resultado;
	}
	
	public ResultXmlPL montosIniciales(ParamMontosIniciales param) {
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosCotizadorVYAPersist.class);
		logueo.setMetodo("montosIniciales");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		
        logueo.setParametro("codMoneda", param.getCodMoneda());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_montosIniciales");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?)}");
			
			cstmt.setInt(1, param.getCodMoneda());
			
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
	
	public ResultRentaMaxima obtenerRentaMaxima(ParamRentaMaxima param) {
		ResultRentaMaxima resultado = new ResultRentaMaxima();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosCotizadorVYAPersist.class);
		logueo.setMetodo("obtenerRentaMaxima");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("ingresoMensual", param.getIngresoMensual());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("fun_obtenerRentaMaxima");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{? = call " + nombrePL + "(?,?,?,?)}");

			cstmt.setDouble(2, param.getIngresoMensual());

			cstmt.registerOutParameter(1, Types.DOUBLE);
			cstmt.registerOutParameter(3, Types.INTEGER);
			cstmt.registerOutParameter(4, Types.VARCHAR);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.execute();

			double rentaMaxima = cstmt.getDouble(1);
			int codError = cstmt.getInt(3);
			String descError = cstmt.getString(4);
			String sqlError = cstmt.getString(5);

			if (codError != 0) {
				ServiciosError error = new ServiciosError();
				error.setCodigo(Integer.valueOf(codError));
				error.setDescripcion(descError);

				resultado.setError(error);
				resultado.setHayError(Boolean.TRUE);
			}else{
				resultado.setRentaMaxima(Double.valueOf(rentaMaxima));
			}

			logueo.setParametro("Retorno Funcion", rentaMaxima);
			logResultados(logueo, codError, descError, sqlError);
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, cstmt);
		}
		return resultado;
	}
	
	public ResultXmlPL obtenerPorcentajeAnticipo(ParamPorcentajeAnticipo param) {
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosCotizadorVYAPersist.class);
		logueo.setMetodo("obtenerPorcentajeAnticipo");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		
        logueo.setParametro("codProducto", param.getCodProducto());
        logueo.setParametro("codPlan", param.getCodPlan());
		logueo.setParametro("codMoneda", param.getCodMoneda());
		logueo.setParametro("edad", param.getEdad());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_porcentajeAnticipo");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?)}");
			
			cstmt.setString(1, param.getCodProducto());
			cstmt.setString(2, param.getCodPlan());
			cstmt.setInt(3, param.getCodMoneda());
			cstmt.setInt(4, param.getEdad());
			cstmt.setString(5, param.getUsuario());			
			
			cstmt.registerOutParameter(6, Types.INTEGER);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.registerOutParameter(8, Types.VARCHAR);
			cstmt.registerOutParameter(9, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(6);
			String descError = cstmt.getString(7);
			String sqlError = cstmt.getString(8);
			Clob clob = cstmt.getClob(9);
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
