package uy.com.bse.consultas.persistencia;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import uy.com.bse.consultas.operaciones.ParamActualizarPolizaFlotante;
import uy.com.bse.consultas.operaciones.ParamObtenerCabezalNomina;
import uy.com.bse.consultas.operaciones.ParamObtenerCoberturasIntegranteNomina;
import uy.com.bse.consultas.operaciones.ParamObtenerDetallePolizaFlotante;
import uy.com.bse.consultas.operaciones.ParamObtenerIntegrantesNomina;
import uy.com.bse.consultas.operaciones.ParamObtenerIntegrantesNominaAccidenteTrabajo;
import uy.com.bse.consultas.operaciones.ParamObtenerMovimientosComision;
import uy.com.bse.consultas.operaciones.ParamObtenerNominasVida;
import uy.com.bse.consultas.operaciones.ParamObtenerPolizasFlotantes;
import uy.com.bse.consultas.operaciones.ParamTipoIntegranteNomina;
import uy.com.bse.maestro.persistencia.ServiciosRector;
import uy.com.bse.utilitario.dato.Codiguera;
import uy.com.bse.utilitario.dato.ResultCodiguera;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;

public class ConsultasPersist extends ServiciosRector {

	public ResultXmlPL obtenerNominasVida(ParamObtenerNominasVida param) {
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ConsultasPersist.class);
		logueo.setMetodo("obtenerNominasVida");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Num nomina", param.getNumNomina());
		logueo.setParametro("Tipo integrante", param.getTipoIntegrante());
		logueo.setParametro("Num certificado", param.getNumCertificado());
		logueo.setParametro("Vigente", param.getVigente());
		logueo.setParametro("Col orden", param.getColumnaOrden());
		logueo.setParametro("Num documento", param.getNumDocumento());
		logueo.setParametro("Primer nombre", param.getPrimerNombre());
		logueo.setParametro("Primer apellido", param.getPrimerApellido());
		logueo.setParametro("Num documento relacionado", param.getNumDocumentoRelacionado());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerNominasVida");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			if (param.getNumNomina() != null) {
				cstmt.setInt(1, param.getNumNomina());
			} else {
				cstmt.setNull(1, Types.INTEGER);
			}

			cstmt.setString(2, param.getTipoIntegrante());

			if (param.getNumCertificado() != null) {
				cstmt.setInt(3, param.getNumCertificado());
			} else {
				cstmt.setNull(3, Types.INTEGER);
			}

			cstmt.setString(4, param.getVigente());
            cstmt.setString(5, param.getNumDocumento());
            cstmt.setString(6, param.getPrimerApellido());
            cstmt.setString(7, param.getPrimerNombre());
            cstmt.setString(8, param.getNumDocumentoRelacionado());


			if (param.getColumnaOrden() != null) {
				cstmt.setInt(9, param.getColumnaOrden());
			} else {
				cstmt.setNull(9, Types.INTEGER);
			}

			cstmt.setString(10, param.getUsuario());

			cstmt.registerOutParameter(11, Types.INTEGER);
			cstmt.registerOutParameter(12, Types.VARCHAR);
			cstmt.registerOutParameter(13, Types.VARCHAR);
			cstmt.registerOutParameter(14, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(11);
			String descError = cstmt.getString(12);
			String sqlError = cstmt.getString(13);
			Clob clob = cstmt.getClob(14);
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

	public ResultXmlPL obtenerIntegrantesNomina(ParamObtenerIntegrantesNomina param) {
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ConsultasPersist.class);
		logueo.setMetodo("obtenerIntegrantesNomina");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Num detalle", param.getNumDetalle());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerIntegrantesNomina");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");

			if (param.getNumDetalle() != null) {
				cstmt.setInt(1, param.getNumDetalle());
			} else {
				cstmt.setNull(1, Types.INTEGER);
			}

			cstmt.setString(2, param.getUsuario());

			cstmt.registerOutParameter(3, Types.INTEGER);
			cstmt.registerOutParameter(4, Types.VARCHAR);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(3);
			String descError = cstmt.getString(4);
			String sqlError = cstmt.getString(5);
			Clob clob = cstmt.getClob(6);
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

	public ResultXmlPL obtenerCabezalNomina(ParamObtenerCabezalNomina param) {
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ConsultasPersist.class);
		logueo.setMetodo("obtenerCabezalNomina");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Num nomina", param.getNumNomina());
		logueo.setParametro("Num integrante", param.getNumIntegrante());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerCabezalNomina");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			if (param.getNumNomina() != null) {
				cstmt.setInt(1, param.getNumNomina());
			} else {
				cstmt.setNull(1, Types.INTEGER);
			}
			if (param.getNumIntegrante() != null) {
				cstmt.setInt(2, param.getNumIntegrante());
			} else {
				cstmt.setNull(2, Types.INTEGER);
			}

			cstmt.setString(3, param.getUsuario());

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

	public ResultXmlPL obtenerCoberturasIntegranteNomina(ParamObtenerCoberturasIntegranteNomina param) {
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ConsultasPersist.class);
		logueo.setMetodo("obtenerCoberturasIntegranteNomina");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Num nomina", param.getNumNomina());
		logueo.setParametro("Num integrante", param.getNumIntegrante());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerCoberturasIntegranteNomina");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			if (param.getNumNomina() != null) {
				cstmt.setInt(1, param.getNumNomina());
			} else {
				cstmt.setNull(1, Types.INTEGER);
			}
			if (param.getNumIntegrante() != null) {
				cstmt.setInt(2, param.getNumIntegrante());
			} else {
				cstmt.setNull(2, Types.INTEGER);
			}

			cstmt.setString(3, param.getUsuario());

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

	public ResultXmlPL obtenerPolizasFlotantes(ParamObtenerPolizasFlotantes param) {
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ConsultasPersist.class);
		logueo.setMetodo("obtenerPolizasFlotantes");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Nombre", param.getNombreAsegurado());
		logueo.setParametro("Num. poliza", param.getNumPoliza());
		logueo.setParametro("Identificador web", param.getIdentificadorWeb());
		logueo.setParametro("Fecha embarque", param.getFechaEmbarque());
		logueo.setParametro("Identificador embarque", param.getIdentificadorEmbarque());
		logueo.setParametro("Num. poliza factura", param.getNumPolizaFactura());
		//
		logueo.setParametro("Certificado no facturado", param.getCertNoFac());
		//se agrega parametro para saber cuales no estan facturadas
		//p_cert_sin_fact       IN VARCHAR2
		try {

			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerPolizasFlotantes");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?)}");

			cstmt.setString(1, param.getNombreAsegurado());
			if (param.getNumPoliza() != null) {
				cstmt.setInt(2, param.getNumPoliza());
			} else {
				cstmt.setNull(2, Types.INTEGER);
			}

			cstmt.setString(3,param.getIdentificadorEmbarque());
			
			
			if (param.getFechaEmbarque() != null) {
				cstmt.setDate(4, toSqlDate(param.getFechaEmbarque()));
			} else {
				cstmt.setNull(4, Types.DATE);
			}
			
			if (param.getIdentificadorWeb() != null) {
				cstmt.setInt(5, Integer.valueOf(param.getIdentificadorWeb()));
			} else {
				cstmt.setNull(5, Types.INTEGER);
			}

			
			if (param.getNumPolizaFactura() != null) {
				cstmt.setInt(6, param.getNumPolizaFactura());
			} else {
				cstmt.setNull(6, Types.INTEGER);
			}
			
			cstmt.setString(7,param.getCertNoFac());
			
			cstmt.setString(8, param.getUsuario());

			cstmt.registerOutParameter(9, Types.INTEGER);
			cstmt.registerOutParameter(10, Types.VARCHAR);
			cstmt.registerOutParameter(11, Types.VARCHAR);
			cstmt.registerOutParameter(12, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(9);
			String descError = cstmt.getString(10);
			String sqlError = cstmt.getString(11);
			Clob clob = cstmt.getClob(12);
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

	public ResultXmlPL obtenerDetallePolizaFlotante(ParamObtenerDetallePolizaFlotante param) {
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ConsultasPersist.class);
		logueo.setMetodo("obtenerDetallePolizaFlotante");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Identificador", param.getIdentificador());

		try {

			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerDetallePolizaFlotante");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");
			cstmt.setInt(1, param.getIdentificador());
			cstmt.setString(2, param.getUsuario());

			cstmt.registerOutParameter(3, Types.INTEGER);
			cstmt.registerOutParameter(4, Types.VARCHAR);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(3);
			String descError = cstmt.getString(4);
			String sqlError = cstmt.getString(5);
			Clob clob = cstmt.getClob(6);
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

	public ResultCodiguera listaTipoIntegranteNomina(ParamTipoIntegranteNomina param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ConsultasPersist.class);
		logueo.setMetodo("listaTipoIntegranteNomina");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultCodiguera resultado = new ResultCodiguera();

		try {
			conn = crearConexion();
			String sql = "SELECT CD_INTEGRANTE, DESC_INTEGRANTE FROM NOM_TIPOS_INTEGRANTES ORDER BY 2";
			logueo.setParametro("Consulta", sql);
			pst = conn.prepareStatement(sql);

			result = pst.executeQuery();

			while (result.next()) {
				Codiguera dato = new Codiguera();
				dato.setCodigo(result.getString("CD_INTEGRANTE"));
				dato.setDescripcion(result.getString("DESC_INTEGRANTE"));
				resultado.setUno(dato);
			}

		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}
		return resultado;
	}
	
	
	public ResultGenerico actualizarPolizaFlotante(ParamActualizarPolizaFlotante param) {
		ResultGenerico resultado = new ResultGenerico();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ConsultasPersist.class);
		logueo.setMetodo("actualizarPolizaFlotante");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Identificador", param.getIdentificadorWeb());
		logueo.setParametro("Num DUA", param.getNumDua());
		logueo.setParametro("Observaciones", param.getObservaciones());
		

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_actualizarPolizaFlotante");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getIdentificadorWeb());
			cstmt.setString(2, param.getNumDua());
			cstmt.setString(3, param.getObservaciones());
			cstmt.setString(4, param.getUsuario());

			cstmt.registerOutParameter(5, Types.INTEGER);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.execute();

			int codError = cstmt.getInt(5);
			String descError = cstmt.getString(6);
			String sqlError = cstmt.getString(7);

			if (codError != 0) {
				ServiciosError error = new ServiciosError();
				error.setCodigo(Integer.valueOf(codError));
				error.setDescripcion(descError);

				resultado.setError(error);
				resultado.setHayError(Boolean.TRUE);
			}

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


	public ResultXmlPL obtenerMovimientosComision(ParamObtenerMovimientosComision param) {
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ConsultasPersist.class);
		logueo.setMetodo("obtenerMovimientosComision");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de moneda", param.getCodMoneda());
		logueo.setParametro("Periodo", param.getPeriodo());
		
		try {

			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerMovimientosComision");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");

			
			if (param.getCodMoneda() != null) {
				cstmt.setInt(1, param.getCodMoneda());
			} else {
				cstmt.setNull(1, Types.INTEGER);
			}

			cstmt.setString(2,param.getPeriodo());
			
			
			
			cstmt.setString(3, param.getUsuario());

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
	
	public ResultXmlPL obtenerIntegrantesNominaAccidenteTrabajo(ParamObtenerIntegrantesNominaAccidenteTrabajo param) {
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ConsultasPersist.class);
		logueo.setMetodo("obtenerIntegrantesNominaAccidenteTrabajo");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cod ramo", param.getCodRamo());
		logueo.setParametro("Num poliza", param.getNumPoliza());
		logueo.setParametro("Mes cargo", param.getMesCargo());
		logueo.setParametro("tipo documento", param.getTipoDocumento());
		logueo.setParametro("Num documento", param.getNumDocumento());
		logueo.setParametro("nombre", param.getNombre());
		logueo.setParametro("Cod productor", param.getCodProductor());
		
	
		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerIntegrantesNominaAccidenteTrabajo");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?)}");
			if (param.getCodRamo() != null) {
				cstmt.setInt(1, param.getCodRamo());
			} else {
				cstmt.setNull(1, Types.INTEGER);
			}
			if (param.getNumPoliza() != null) {
				cstmt.setInt(2, param.getNumPoliza());
			} else {
				cstmt.setNull(2, Types.INTEGER);
			}
			
			cstmt.setString(3, param.getMesCargo());

			cstmt.setString(4, param.getTipoDocumento());
			cstmt.setString(5, param.getNumDocumento());
			cstmt.setString(6, param.getNombre());
			cstmt.setString(7, param.getUsuario());
			
			if (param.getCodProductor() == null) {
				cstmt.setNull(8, Types.INTEGER);
			} else {
				cstmt.setInt(8, Integer.valueOf(param.getCodProductor()));
			}
			
			cstmt.registerOutParameter(9, Types.INTEGER);
			cstmt.registerOutParameter(10, Types.VARCHAR);
			cstmt.registerOutParameter(11, Types.VARCHAR);
			cstmt.registerOutParameter(12, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(9);
			String descError = cstmt.getString(10);
			String sqlError = cstmt.getString(11);
			Clob clob = cstmt.getClob(12);
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