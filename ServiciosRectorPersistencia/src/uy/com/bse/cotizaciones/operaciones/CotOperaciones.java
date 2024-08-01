package uy.com.bse.cotizaciones.operaciones;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.bse.rector.servicios.persistencia.ParseoXml;
import uy.com.bse.maestro.persistencia.ServiciosRector;
import uy.com.bse.servicios.rector.emision.ParamEmision;
import uy.com.bse.servicios.rector.emision.ParamValidarCotizacion;
import uy.com.bse.servicios.rector.emision.ResultValidarCotizacion;
import uy.com.bse.servicios.rector.emision.ValidaCotizacion;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.util.Herramientas;

public class CotOperaciones extends ServiciosRector {

	private static final Logger LOG = LogManager.getLogger(CotOperaciones.class);

	public ResultXmlPL nuevaCotizacion(ParamNuevaCotizacion param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("nuevaCotizacion");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Ramo", param.getCodRamo());
		logueo.setParametro("Producto", param.getCodProducto());
		logueo.setParametro("Tipo de facturacion", param.getTipoFacturacion());
		logueo.setParametro("Codigo de moneda", param.getCodMoneda());
		logueo.setParametro("Medio de pago", param.getMedioPago());
		logueo.setParametro("Origen de pago", param.getOrigenPago());
		logueo.setParametro("Codigo de promocion", param.getCodPromocion());
		logueo.setParametro("Renovacion", param.getRenovacion());
		logueo.setParametro("Tipo de calculo", param.getTipoCalculo());
		logueo.setParametro("Vigencia", param.getVigencia());
		logueo.setParametro("Fecha desde", param.getFechaDesdeVigencia());
		logueo.setParametro("Fecha hasta", param.getFechaHastaVigencia());
		logueo.setParametro("Tipo de vigencia tecnica", param.getTipoVigenciaTecnica());
		logueo.setParametro("Anulacion corrida", param.getAnulacionCorrida());
		logueo.setParametro("Cliente contratante", param.getCodClienteContratante());
		logueo.setParametro("Codigo de cliente asegurado", param.getCodClienteAsegurado());
		logueo.setParametro("Tipo de copia", param.getTipoCopia());
		logueo.setParametro("Numero a copiar", param.getNumACopiar());
		logueo.setParametro("Codigo direccion envio", param.getCodDireccionEnvio());
		logueo.setParametro("Codigo direccion cobro", param.getCodDireccionCobro());
		logueo.setParametro("Codigo del productor", param.getCodProductor());
		logueo.setParametro("Envio factura por mail", param.getEnviarFacturaEmail());
		logueo.setParametro("Emitir con rut", param.getEmitirConRUT());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_nueva_cotizacion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, param.getCodRamo());
			cstmt.setString(2, param.getCodProducto());

			if (param.getTipoFacturacion() == null) {
				cstmt.setNull(3, Types.VARCHAR);
			} else {
				cstmt.setString(3, param.getTipoFacturacion());
			}

			if (param.getCodMoneda() == null) {
				cstmt.setNull(4, Types.VARCHAR);
			} else {
				cstmt.setString(4, param.getCodMoneda());
			}

			if (param.getMedioPago() == null) {
				cstmt.setNull(5, Types.INTEGER);
			} else {
				cstmt.setInt(5, param.getMedioPago());
			}

			if (param.getOrigenPago() == null) {
				cstmt.setNull(6, Types.VARCHAR);
			} else {
				cstmt.setString(6, param.getOrigenPago());
			}

			if (param.getCodPromocion() == null) {
				cstmt.setNull(7, Types.VARCHAR);
			} else {
				cstmt.setString(7, param.getCodPromocion());
			}

			if (param.getRenovacion() == null) {
				cstmt.setNull(8, Types.VARCHAR);
			} else {
				cstmt.setString(8, param.getRenovacion());
			}

			if (param.getTipoCalculo() == null) {
				cstmt.setNull(9, Types.VARCHAR);
			} else {
				cstmt.setString(9, param.getTipoCalculo());
			}

			if (param.getVigencia() == null) {
				cstmt.setNull(10, Types.VARCHAR);
			} else {
				cstmt.setString(10, param.getVigencia());
			}

			if (param.getFechaDesdeVigencia() == null) {
				cstmt.setNull(11, java.sql.Types.DATE);
			} else {
				cstmt.setDate(11, toSqlDate(param.getFechaDesdeVigencia()));
			}

			if (param.getFechaHastaVigencia() == null) {
				cstmt.setNull(12, java.sql.Types.DATE);
			} else {
				cstmt.setDate(12, toSqlDate(param.getFechaHastaVigencia()));
			}

			if (param.getTipoVigenciaTecnica() == null) {
				cstmt.setNull(13, Types.VARCHAR);
			} else {
				cstmt.setString(13, param.getTipoVigenciaTecnica());
			}

			if (param.getAnulacionCorrida() == null) {
				cstmt.setNull(14, Types.VARCHAR);
			} else {
				cstmt.setString(14, param.getAnulacionCorrida());
			}

			if (param.getCodClienteContratante() == null) {
				cstmt.setNull(15, Types.VARCHAR);
			} else {
				cstmt.setString(15, param.getCodClienteContratante());
			}

			if (param.getCodClienteAsegurado() == null) {
				cstmt.setNull(16, Types.VARCHAR);
			} else {
				cstmt.setString(16, param.getCodClienteAsegurado());
			}

			if (param.getUsuario() == null) {
				cstmt.setNull(17, Types.VARCHAR);
			} else {
				cstmt.setString(17, param.getUsuario());
			}
			if (param.getCodProductor() == null) {
				cstmt.setNull(18, Types.INTEGER);
			} else {
				cstmt.setInt(18, Integer.valueOf(param.getCodProductor()));
			}

			if (param.getTipoCopia() == null) {
				cstmt.setNull(19, Types.INTEGER);
			} else {
				cstmt.setInt(19, param.getTipoCopia());
			}

			if (param.getNumACopiar() == null) {
				cstmt.setNull(20, Types.INTEGER);
			} else {
				cstmt.setInt(20, param.getNumACopiar());
			}

			cstmt.setNull(21, Types.VARCHAR);

			if (param.getCodDireccionEnvio() != null) {
				cstmt.setInt(22, param.getCodDireccionEnvio());
			}
			if (param.getCodDireccionCobro() != null) {
				cstmt.setInt(23, param.getCodDireccionCobro());
			}
			
			if (param.getEnviarFacturaEmail() != null) {
				cstmt.setString(24, (param.getEnviarFacturaEmail()) ? "S" : "N");
			} else {
				cstmt.setNull(24, Types.VARCHAR);
			}
			if (param.getEmitirConRUT() != null) {
				cstmt.setInt(25, (param.getEmitirConRUT()) ? 1 : 0);
			} else {
				cstmt.setNull(25, Types.INTEGER);
			}
			cstmt.registerOutParameter(26, Types.INTEGER);
			cstmt.registerOutParameter(27, Types.VARCHAR);
			cstmt.registerOutParameter(28, Types.VARCHAR);
			cstmt.registerOutParameter(29, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(26);
			String descError = cstmt.getString(27);
			String sqlError = cstmt.getString(28);
			Clob clob = cstmt.getClob(29);
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

	public ResultCopiarCotizaPoli copiarCotizaPoli(ParamCopiarCotizaPoli param) {
		ResultCopiarCotizaPoli resultado = new ResultCopiarCotizaPoli();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("copiarCotizaPoli");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Cotizacion a copiar", param.getNumCotizacionACopiar());
		logueo.setParametro("Poliza a copiar", param.getNumPolizaACopiar());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_copiarCotizaPoli");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getNumCotizacion());

			boolean correcto = true;

			if (param.getNumCotizacionACopiar() != null) {
				cstmt.setInt(2, 1);
				cstmt.setInt(3, param.getNumCotizacionACopiar());
			} else if (param.getNumPolizaACopiar() != null) {
				cstmt.setInt(2, 2);
				cstmt.setInt(3, param.getNumPolizaACopiar());
			} else {
				correcto = false;
				resultado.setHayError(Boolean.TRUE);
				resultado.setError(obtenerDescripcionError("nullParams"));
				logueo.setErrorDatos("Ambos parametros son null, solo uno debe serlo");
			}

			if (correcto) {
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
			}
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, cstmt);
		}
		return resultado;
	}

	public ResultAnularCertificado anularCertificado(ParamAnularCertificado param) {
		ResultAnularCertificado resultado = new ResultAnularCertificado();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("anularCertificado");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Consecutivo", param.getNumConsecutivo());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_anularCertificado");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getNumCotizacion());
			cstmt.setInt(2, param.getNumConsecutivo());
			cstmt.setString(3, param.getUsuario());

			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.execute();

			int codError = cstmt.getInt(4);
			String descError = cstmt.getString(5);
			String sqlError = cstmt.getString(6);

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

	public ResultXmlPL agregarBien(ParamAgregarBien param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("agregarBien");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Consecutivo", param.getNumConsecutivo());
		logueo.setParametro("Codigo de bien", param.getCodBien());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_agregarBien");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, param.getNumCotizacion());
			cstmt.setInt(2, param.getNumConsecutivo());
			cstmt.setInt(3, param.getCodBien());
			cstmt.setString(4, param.getUsuario());

			cstmt.registerOutParameter(5, Types.INTEGER);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.registerOutParameter(8, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(5);
			String descError = cstmt.getString(6);
			String sqlError = cstmt.getString(7);
			Clob clob = cstmt.getClob(8);
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

	public ResultAgregarCertificado agregarCertificado(ParamAgregarCertificado param) {
		ResultAgregarCertificado resultado = new ResultAgregarCertificado();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("agregarCertificado");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_agregarCertificado");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.setInt(1, param.getNumCotizacion());
			cstmt.setString(3, param.getUsuario());

			cstmt.registerOutParameter(2, Types.INTEGER);
			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.registerOutParameter(7, Types.CLOB);
			cstmt.execute();

			int nuevoConsecutivo = cstmt.getInt(2);
			resultado.setNumConsecutivo(nuevoConsecutivo);
			int codError = cstmt.getInt(4);
			String descError = cstmt.getString(5);
			String sqlError = cstmt.getString(6);
			Clob clob = cstmt.getClob(7);

			logueo.setParametro("nuevo consecutivo", nuevoConsecutivo);
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

	public ResultXmlPL quitarBien(ParamQuitarBien param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("quitarBien");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Consecutivo bien", param.getNumConsecutivoBien());
		logueo.setParametro("Certificado", param.getNumCertificado());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_borrarbien");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, param.getNumCotizacion());
			cstmt.setInt(2, param.getNumCertificado());
			cstmt.setInt(3, param.getNumConsecutivoBien());
			cstmt.setString(4, param.getUsuario());

			cstmt.registerOutParameter(5, Types.INTEGER);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.registerOutParameter(8, Types.CLOB);
			cstmt.setNull(8, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(5);
			String descError = cstmt.getString(6);
			String sqlError = cstmt.getString(7);
			Clob clob = cstmt.getClob(8);
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

	public ResultAnularCotizacion anularCotizacion(ParamAnularCotizacion param) {
		ResultAnularCotizacion resultado = new ResultAnularCotizacion();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("anularCotizacion");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_anularCotizacion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?)}");
			cstmt.setInt(1, param.getNumCotizacion());
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

	public ResultHabilitoAgregarBien habilitoAgregarBien(ParamHabilitoAgregarBien param) {
		ResultHabilitoAgregarBien resultado = new ResultHabilitoAgregarBien();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("habilitoAgregarBien");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Certificado", param.getNumCertificado());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("fun_habilitoBotonInsertar");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{? = call " + nombrePL + "(?,?,?,?,?)}");
			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);

			cstmt.setInt(2, param.getNumCotizacion());
			cstmt.setInt(3, param.getNumCertificado());

			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);

			cstmt.execute();

			int codError = cstmt.getInt(4);
			String descError = cstmt.getString(5);
			String sqlError = cstmt.getString(6);

			if (codError == 0) {
				resultado.setHabilitar(cstmt.getString(1));
			} else {
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

	public ResultHabilitoQuitarBien habilitoQuitarBien(ParamHabilitoQuitarBien param) {
		ResultHabilitoQuitarBien resultado = new ResultHabilitoQuitarBien();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("habilitoQuitarBien");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Certificado", param.getNumCertificado());
		logueo.setParametro("Codigo bien", param.getCodBien());
		logueo.setParametro("Consecutivo bien", param.getConsecutivoBien());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("fun_habilitoBotonBorrar");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{? = call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);

			cstmt.setInt(2, param.getNumCotizacion());
			cstmt.setInt(3, param.getNumCertificado());
			cstmt.setInt(4, param.getCodBien());
			cstmt.setInt(5, param.getConsecutivoBien());

			cstmt.registerOutParameter(6, Types.INTEGER);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.registerOutParameter(8, Types.VARCHAR);

			cstmt.execute();

			int codError = cstmt.getInt(6);
			String descError = cstmt.getString(7);
			String sqlError = cstmt.getString(8);

			if (codError == 0) {
				resultado.setHabilitar(cstmt.getString(1));
			} else {
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

	public ResultHabilitoListaBienes habilitoListaBienes(ParamHabilitoListaBienes param) {
		ResultHabilitoListaBienes resultado = new ResultHabilitoListaBienes();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("habilitoListaBienes");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Certificado", param.getNumCertificado());
		logueo.setParametro("Codigo bien", param.getCodBien());
		logueo.setParametro("Consecutivo bien", param.getConsecutivoBien());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("fun_habilitoListaBienes");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{? = call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);

			cstmt.setInt(2, param.getNumCotizacion());
			cstmt.setInt(3, param.getNumCertificado());
			cstmt.setInt(4, param.getCodBien());
			cstmt.setInt(5, param.getConsecutivoBien());

			cstmt.registerOutParameter(6, Types.INTEGER);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.registerOutParameter(8, Types.VARCHAR);

			cstmt.execute();

			int codError = cstmt.getInt(6);
			String descError = cstmt.getString(7);
			String sqlError = cstmt.getString(8);

			if (codError == 0) {
				resultado.setHabilitar(cstmt.getString(1));
			} else {
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

	public ResultHabilitoUbicacion habilitoUbicacion(ParamHabilitoUbicacion param) {
		ResultHabilitoUbicacion resultado = new ResultHabilitoUbicacion();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("habilitoUbicacion");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Certificado", param.getNumCertificado());
		logueo.setParametro("Codigo bien", param.getCodBien());
		logueo.setParametro("Consecutivo bien", param.getConsecutivoBien());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("fun_habilitoUbicacion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{? = call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);

			cstmt.setInt(2, param.getNumCotizacion());
			cstmt.setInt(3, param.getNumCertificado());
			cstmt.setInt(4, param.getCodBien());
			cstmt.setInt(5, param.getConsecutivoBien());

			cstmt.registerOutParameter(6, Types.INTEGER);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.registerOutParameter(8, Types.VARCHAR);

			cstmt.execute();

			int codError = cstmt.getInt(6);
			String descError = cstmt.getString(7);
			String sqlError = cstmt.getString(8);

			if (codError == 0) {
				resultado.setHabilitar(cstmt.getString(1));
			} else {
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

	public ResultHabilitoBeneficiarios habilitoBeneficiarios(ParamHabilitoBeneficiarios param) {
		ResultHabilitoBeneficiarios resultado = new ResultHabilitoBeneficiarios();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("habilitoBeneficiarios");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Certificado", param.getNumCertificado());
		logueo.setParametro("Codigo bien", param.getCodBien());
		logueo.setParametro("Consecutivo bien", param.getConsecutivoBien());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("fun_habilitoBeneficiario");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{? = call " + nombrePL + "(?,?,?,?,?,?,?,?)}");
			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);

			cstmt.setInt(2, param.getNumCotizacion());
			cstmt.setInt(3, param.getNumCertificado());
			cstmt.setInt(4, param.getCodBien());
			cstmt.setInt(5, param.getConsecutivoBien());
			cstmt.setString(6, param.getUsuario());

			cstmt.registerOutParameter(7, Types.INTEGER);
			cstmt.registerOutParameter(8, Types.VARCHAR);
			cstmt.registerOutParameter(9, Types.VARCHAR);

			cstmt.execute();

			int codError = cstmt.getInt(7);
			String descError = cstmt.getString(8);
			String sqlError = cstmt.getString(9);

			if (codError == 0) {
				resultado.setHabilitar(cstmt.getString(1));
			} else {
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

	/* @author fabiana */
	public ResultAltaUbicacionBien altaUbicacionBien(ParamAltaUbicacionBien param) {

		ResultAltaUbicacionBien resultado = new ResultAltaUbicacionBien();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("altaUbicacionBien");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo bien", param.getConsecutivoBien());
		logueo.setParametro("Numero postal", param.getNumPostal());
		logueo.setParametro("Calle", param.getCalle());
		logueo.setParametro("Numero calle", param.getNumero());
		logueo.setParametro("Piso", param.getPiso());
		logueo.setParametro("Departamento", param.getDpto());
		logueo.setParametro("Numero fax", param.getFax());
		logueo.setParametro("Numero telefono", param.getTel());
		logueo.setParametro("Numero padron", param.getNumPadron());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_altaUbicacionBien");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			cstmt.setInt(2, Integer.valueOf(param.getNumCertificado()));
			cstmt.setInt(3, Integer.valueOf(param.getConsecutivoBien()));
			cstmt.setNull(4, Types.INTEGER);
			cstmt.setInt(5, Integer.valueOf(param.getNumPostal()));
			cstmt.setString(6, param.getCalle());
			cstmt.setString(7, param.getNumero());
			cstmt.setString(8, param.getPiso());
			cstmt.setString(9, param.getDpto());
			cstmt.setString(10, param.getFax());
			cstmt.setString(11, param.getTel());
			cstmt.setString(12, param.getNumPadron());
			cstmt.setString(13, param.getUsuario());
			cstmt.registerOutParameter(14, Types.INTEGER);
			cstmt.registerOutParameter(15, Types.VARCHAR);
			cstmt.registerOutParameter(16, Types.VARCHAR);

			cstmt.execute();

			int codError = cstmt.getInt(14);
			String descError = cstmt.getString(15);
			String sqlError = cstmt.getString(16);

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

	/* @author fabiana */
	public ResultBajaUbicacionBien bajaUbicacionBien(ParamBajaUbicacionBien param) {

		ResultBajaUbicacionBien resultado = new ResultBajaUbicacionBien();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("bajaUbicacionBien");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo bien", param.getConsecutivoBien());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_bajaUbicacionBien");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			cstmt.setInt(2, Integer.valueOf(param.getNumCertificado()));
			cstmt.setInt(3, Integer.valueOf(param.getConsecutivoBien()));

			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);

			cstmt.execute();

			int codError = cstmt.getInt(4);
			String descError = cstmt.getString(5);
			String sqlError = cstmt.getString(6);

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

	public ResultModificarUbicacionBien modificarUbicacionBien(ParamModificarUbicacionBien param) {

		ResultModificarUbicacionBien resultado = new ResultModificarUbicacionBien();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("modificarUbicacionBien");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo bien", param.getConsecutivoBien());
		logueo.setParametro("Numero consecutivo ubicacion", param.getConsecutivoUbicacion());
		logueo.setParametro("Numero postal", param.getNumPostal());
		logueo.setParametro("Calle", param.getCalle());
		logueo.setParametro("Numero calle", param.getNumero());
		logueo.setParametro("Piso", param.getPiso());
		logueo.setParametro("Departamento", param.getDpto());
		logueo.setParametro("Numero fax", param.getFax());
		logueo.setParametro("Numero telefono", param.getTel());
		logueo.setParametro("Numero padron", param.getNumPadron());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_modificarUbicacionBien");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			cstmt.setInt(2, Integer.valueOf(param.getNumCertificado()));
			cstmt.setInt(3, Integer.valueOf(param.getConsecutivoBien()));
			cstmt.setInt(4, Integer.valueOf(param.getConsecutivoUbicacion()));
			cstmt.setInt(5, Integer.valueOf(param.getNumPostal()));
			cstmt.setString(6, param.getCalle());
			if (param.getNumero() != null) {
				cstmt.setInt(7, Integer.valueOf(param.getNumero()));
			} else {
				cstmt.setNull(7, Types.INTEGER);
			}
			if (param.getPiso() != null) {
				cstmt.setString(8, param.getPiso());
			} else {
				cstmt.setNull(8, Types.VARCHAR);
			}

			if (param.getDpto() != null) {
				cstmt.setString(9, param.getDpto());
			} else {
				cstmt.setNull(9, Types.VARCHAR);
			}

			/*
			 * if (param.getPiso() != null) { cstmt.setInt(8,
			 * Integer.valueOf(param.getPiso())); } else { cstmt.setNull(8, Types.INTEGER);
			 * } if (param.getDpto() != null) { cstmt.setInt(9,
			 * Integer.valueOf(param.getDpto())); } else { cstmt.setNull(9, Types.INTEGER);
			 * }
			 */

			if (param.getFax() != null) {
				cstmt.setInt(10, Integer.valueOf(param.getFax()));
			} else {
				cstmt.setNull(10, Types.INTEGER);
			}

			if (param.getTel() != null) {
				cstmt.setInt(11, Integer.valueOf(param.getTel()));
			} else {
				cstmt.setNull(11, Types.INTEGER);
			}
			if (param.getNumPadron() != null) {
				cstmt.setString(12, param.getNumPadron());
			} else {
				cstmt.setNull(12, Types.VARCHAR);
			}
			cstmt.setString(13, param.getUsuario());
			cstmt.registerOutParameter(14, Types.INTEGER);
			cstmt.registerOutParameter(15, Types.VARCHAR);
			cstmt.registerOutParameter(16, Types.VARCHAR);

			cstmt.execute();

			int codError = cstmt.getInt(14);
			String descError = cstmt.getString(15);
			String sqlError = cstmt.getString(16);

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

	/*
	 * posicion del bien o del mal FI
	 */
	public ResultXmlPL validarDatos(ParamValidarDatos param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("validarDatos");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero de cotizacion", param.getNumCotizacion());
		logueo.setParametro("Posicion del bien", param.getPosicionBien());
		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_validarDatos");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));

			if (param.getNumCertificado() != null) {
				cstmt.setInt(2, Integer.valueOf(param.getNumCertificado()));
			} else {
				cstmt.setNull(2, Types.INTEGER);
			}

			if (param.getPosicionBien() != null) {
				cstmt.setInt(3, Integer.valueOf(param.getPosicionBien()));
			} else {
				cstmt.setNull(3, Types.INTEGER);
			}
			cstmt.setString(4, param.getUsuario());

			cstmt.registerOutParameter(5, Types.INTEGER);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.registerOutParameter(8, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(5);
			String descError = cstmt.getString(6);
			String sqlError = cstmt.getString(7);
			Clob clob = cstmt.getClob(8);
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

	/**
	 * @author vcorrea
	 */

	public ResultActualizarDatos actualizarDatos(ParamActualizarDatos param) {
		ResultActualizarDatos resultado = new ResultActualizarDatos();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("actualizarDatos");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Posicion del bien", param.getNumConsBien());
		logueo.setParametro("Codigo del dato del bien", param.getDatoCod());
		logueo.setParametro("Valor del dato del bien", param.getDatoValor());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("fun_actualizarDatos");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{? = call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?)}");

			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
			cstmt.setInt(2, Integer.valueOf(param.getNumCotizacion()));
			cstmt.setInt(3, Integer.valueOf(param.getNumCertificado()));
			cstmt.setInt(4, Integer.valueOf(param.getNumConsBien()));
			cstmt.setInt(5, Integer.valueOf(param.getDatoCod()));
			cstmt.setString(6, param.getDatoValor());

			cstmt.setString(7, param.getUsuario());
			cstmt.registerOutParameter(8, Types.VARCHAR);
			cstmt.registerOutParameter(9, Types.INTEGER);
			cstmt.registerOutParameter(10, Types.VARCHAR);
			cstmt.registerOutParameter(11, Types.VARCHAR);
			cstmt.registerOutParameter(12, Types.CLOB);

			cstmt.execute();

			String estadoActualizacion = cstmt.getString(1);

			String valorRetorno = cstmt.getString(8);

			int codError = cstmt.getInt(9);
			String descError = cstmt.getString(10);
			String sqlError = cstmt.getString(11);

			resultado.setValorDatoActualizado(valorRetorno);
			resultado.setEstadoActualizacion(estadoActualizacion);

			if (codError == 0) {
				Herramientas herramientas = new Herramientas();
				resultado.setXml(herramientas.convertirClob(cstmt.getClob(12)));
				ServiciosError error = new ServiciosError();
				error.setDescripcion(descError);
				resultado.setError(error);
			} else {
				ServiciosError error = new ServiciosError();
				error.setCodigo(Integer.valueOf(codError));
				error.setDescripcion(descError);

				resultado.setError(error);
				resultado.setHayError(Boolean.TRUE);
			}

			logueo.setParametro("Retorno Funcion", estadoActualizacion);
			logueo.setParametro("Retorno Valor Actualizado", valorRetorno);
			logResultados(logueo, codError, descError, sqlError);
			LOG.debug(resultado.getXml());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, cstmt);
		}

		return resultado;

	}

	public ResultGuardarCoberturas guardarCoberturas(ParamGuardarCoberturas param) {
		ResultGuardarCoberturas resultado = new ResultGuardarCoberturas();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("guardarCoberturas");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero de cotizacion", param.getNumCotizacion());
		logueo.setParametro("Codigo plan cobertura", param.getCodPlanCobertura());
		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_guardarCoberturas");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?)}");

			cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			cstmt.setInt(2, Integer.valueOf(param.getNumCertificado()));
			cstmt.setString(3, param.getCodPlanCobertura());
			cstmt.setString(4, param.getListarCoberturas());
			cstmt.setString(5, param.getUsuario());

			cstmt.registerOutParameter(6, Types.INTEGER);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.registerOutParameter(8, Types.VARCHAR);
			cstmt.registerOutParameter(9, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(6);
			String descError = cstmt.getString(7);
			String sqlError = cstmt.getString(8);

			if (codError == 0) {
				final Herramientas herramientas = new Herramientas();
				resultado.setXml(herramientas.convertirClob(cstmt.getClob(9)));
			} else {
				ServiciosError error = new ServiciosError();
				error.setCodigo(Integer.valueOf(codError));
				error.setDescripcion(descError);

				resultado.setError(error);
				resultado.setHayError(Boolean.TRUE);
			}

			logueo.setParametro("Codigo salida", codError);
			logueo.setParametro("Descripcion salida", descError);
			logueo.setParametro("Sql salida", sqlError);
			logueo.setParametro("Xml", resultado.getXml());

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

	public ResultXmlPL calcularCotizacion(ParamCalcularCotizacion param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("calcularCotizacion");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero de cotizacion", param.getNumCotizacion());
		logueo.setParametro("Codigo plan de pago", param.getCodPlanPago());
		logueo.setParametro("Dia vencimiento", param.getDiaVencimiento());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_calcularCotizacion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			cstmt.setInt(2, Integer.valueOf(param.getNumCertificado()));
			cstmt.setString(3, param.getCodPlanPago());

			if (param.getDiaVencimiento() != null) {
				cstmt.setInt(4, param.getDiaVencimiento());
			} else {
				cstmt.setNull(4, Types.INTEGER);
			}

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

	public ResultHabilitoVigenciaTecnica habilitoVigenciaTecnica(ParamHabilitoVigenciaTecnica param) {

		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("habilitoVigenciaTecnica");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultHabilitoVigenciaTecnica resultado = new ResultHabilitoVigenciaTecnica();

		try {
			conn = crearConexion();

			final StringBuffer sql = new StringBuffer();
			sql.append("SELECT capg_in_vigencia_tecnica FROM cart_fr_vigencias_productos ");
			sql.append("WHERE capg_carp_cd_ramo = ? ");
			sql.append("AND capg_capu_cd_producto = ? ");
			sql.append("AND capg_cafp_cd_vigencia = ? ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, Integer.valueOf(param.getCodRamo()));
			pst.setString(2, param.getCodProducto());
			pst.setString(3, param.getTipoVigencia());

			result = pst.executeQuery();
			resultado.setHabilitar("N");
			while (result != null && result.next()) {

				String vigencia = result.getString("capg_in_vigencia_tecnica");

				if (vigencia != null && vigencia.equals("S")) {
					resultado.setHabilitar("S");
				}

			}
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

	public ResultAltaBeneficiario altaBeneficiario(ParamAltaBeneficiario param) {
		ResultAltaBeneficiario resultado = new ResultAltaBeneficiario();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("altaBeneficiario");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo bien", param.getNumConsecutivoBien());
		logueo.setParametro("Tipo documento", param.getTipoDocumento());
		logueo.setParametro("Numero documento", param.getNumDocumento());
		logueo.setParametro("Nombre beneficiario", param.getBeneficiario());
		logueo.setParametro("Codigo parentesco", param.getCodParentesco());
		logueo.setParametro("Porcentaje de participacion", param.getPorcentajeParticipacion());
		logueo.setParametro("Fecha de nacimiento", param.getFechaNacimiento());
		logueo.setParametro("Observaciones", param.getObservaciones());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_altaBeneficiario");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			cstmt.setInt(2, Integer.valueOf(param.getNumCertificado()));
			cstmt.setInt(3, Integer.valueOf(param.getNumConsecutivoBien()));
			cstmt.setNull(4, Types.INTEGER);
			cstmt.setNull(5, Types.INTEGER);
			cstmt.setString(6, param.getTipoDocumento());
			cstmt.setInt(7, Integer.valueOf(param.getNumDocumento()));
			cstmt.setString(8, param.getBeneficiario());
			cstmt.setString(9, param.getCodParentesco());
			cstmt.setDouble(10, Double.valueOf(param.getPorcentajeParticipacion()));

			if (param.getFechaNacimiento() == null || (param.getFechaNacimiento().equals("")) || (param.getFechaNacimiento().equals(Values.NULL))) {

				cstmt.setNull(11, Types.DATE);

			} else {
				cstmt.setDate(11, toSqlDate(param.getFechaNacimiento()));
			}

			cstmt.setString(12, param.getObservaciones());
			cstmt.setString(13, param.getUsuario());
			cstmt.registerOutParameter(14, Types.INTEGER);
			cstmt.registerOutParameter(15, Types.VARCHAR);
			cstmt.registerOutParameter(16, Types.VARCHAR);

			cstmt.execute();

			int codError = cstmt.getInt(14);
			String descError = cstmt.getString(15);
			String sqlError = cstmt.getString(16);

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

	public ResultModificarBeneficiario modificarBeneficiario(ParamModificarBeneficiario param) {
		ResultModificarBeneficiario resultado = new ResultModificarBeneficiario();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("modificarBeneficiario");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo bien", param.getNumConsecutivoBien());
		logueo.setParametro("Tipo documento referente", param.getTipoDocumentoReferente());
		logueo.setParametro("Numero documento referente", param.getNumDocumentoReferente());
		logueo.setParametro("Tipo documento", param.getTipoDocumento());
		logueo.setParametro("Numero documento", param.getNumDocumento());
		logueo.setParametro("Nombre beneficiario", param.getBeneficiario());
		logueo.setParametro("Codigo parentesco", param.getCodParentesco());
		logueo.setParametro("Porcentaje de participacion", param.getPorcentajeParticipacion());
		logueo.setParametro("Fecha de nacimiento", param.getFechaNacimiento());
		logueo.setParametro("Observaciones", param.getObservaciones());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_modificarBeneficiario");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			cstmt.setInt(2, Integer.valueOf(param.getNumCertificado()));
			cstmt.setInt(3, Integer.valueOf(param.getNumConsecutivoBien()));
			cstmt.setString(4, param.getTipoDocumentoReferente());
			cstmt.setInt(5, Integer.valueOf(param.getNumDocumentoReferente()));
			cstmt.setString(6, param.getTipoDocumento());
			cstmt.setInt(7, Integer.valueOf(param.getNumDocumento()));
			cstmt.setString(8, param.getBeneficiario());
			cstmt.setString(9, param.getCodParentesco());
			cstmt.setDouble(10, Double.valueOf(param.getPorcentajeParticipacion()));

			if (param.getFechaNacimiento() == null || (param.getFechaNacimiento().equals("")) || (param.getFechaNacimiento().equals(Values.NULL))) {

				cstmt.setNull(11, Types.DATE);

			} else {
				cstmt.setDate(11, toSqlDate(param.getFechaNacimiento()));
			}

			cstmt.setString(12, param.getObservaciones());
			cstmt.setString(13, param.getUsuario());
			cstmt.registerOutParameter(14, Types.INTEGER);
			cstmt.registerOutParameter(15, Types.VARCHAR);
			cstmt.registerOutParameter(16, Types.VARCHAR);

			cstmt.execute();

			int codError = cstmt.getInt(14);
			String descError = cstmt.getString(15);
			String sqlError = cstmt.getString(16);

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

	public ResultBajaBeneficiario bajaBeneficiario(ParamBajaBeneficiario param) {
		ResultBajaBeneficiario resultado = new ResultBajaBeneficiario();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("bajaBeneficiario");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo bien", param.getNumConsecutivoBien());
		logueo.setParametro("Tipo documento referente", param.getTipoDocumentoReferente());
		logueo.setParametro("Numero documento referente", param.getNumDocumentoReferente());
		logueo.setParametro("Fecha exclusion", param.getFechaExclusion());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_bajaBeneficiario");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			cstmt.setInt(2, Integer.valueOf(param.getNumCertificado()));
			cstmt.setInt(3, Integer.valueOf(param.getNumConsecutivoBien()));
			cstmt.setString(4, param.getTipoDocumentoReferente());
			cstmt.setInt(5, Integer.valueOf(param.getNumDocumentoReferente()));

			cstmt.setDate(6, toSqlDate(param.getFechaExclusion()));

			cstmt.setString(7, param.getUsuario());
			cstmt.registerOutParameter(8, Types.INTEGER);
			cstmt.registerOutParameter(9, Types.VARCHAR);
			cstmt.registerOutParameter(10, Types.VARCHAR);

			cstmt.execute();

			int codError = cstmt.getInt(8);
			String descError = cstmt.getString(9);
			String sqlError = cstmt.getString(10);

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

	public ResultAltaListaBienes altaListaBienes(ParamAltaListaBienes param) {
		ResultAltaListaBienes resultado = new ResultAltaListaBienes();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("altaListaBienes");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo bien", param.getNumConsecutivoBien());
		logueo.setParametro("Codigo objeto", param.getCodObjeto());
		logueo.setParametro("Descripcion", param.getDescripcion());
		logueo.setParametro("Valor", param.getValor());
		logueo.setParametro("Numero unidades", param.getNumUnidades());
		logueo.setParametro("Codigo marca", param.getCodMarca());
		logueo.setParametro("Serial", param.getSerial());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_altaListaBienes");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			cstmt.setInt(2, Integer.valueOf(param.getNumCertificado()));
			cstmt.setInt(3, Integer.valueOf(param.getNumConsecutivoBien()));
			cstmt.setInt(4, Integer.valueOf(param.getCodObjeto()));
			cstmt.setString(5, param.getDescripcion());
			cstmt.setDouble(6, Double.valueOf(param.getValor()));
			cstmt.setInt(7, Integer.valueOf(param.getNumUnidades()));
			if (param.getCodMarca() == null || (param.getCodMarca().equals("")) || (param.getCodMarca().equals(Values.NULL))) {

				cstmt.setNull(8, Types.INTEGER);

			} else {
				cstmt.setInt(8, Integer.valueOf(param.getCodMarca()));
			}

			cstmt.setString(9, param.getSerial());
			cstmt.setNull(10, Types.INTEGER);

			cstmt.registerOutParameter(10, Types.INTEGER);
			cstmt.setString(11, param.getUsuario());
			cstmt.registerOutParameter(12, Types.INTEGER);
			cstmt.registerOutParameter(13, Types.VARCHAR);
			cstmt.registerOutParameter(14, Types.VARCHAR);

			cstmt.execute();

			resultado.setNumOrdinal(cstmt.getString(10));
			int codError = cstmt.getInt(12);
			String descError = cstmt.getString(13);
			String sqlError = cstmt.getString(14);

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

	public ResultBajaListaBienes bajaListaBienes(ParamBajaListaBienes param) {
		ResultBajaListaBienes resultado = new ResultBajaListaBienes();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("bajaListaBienes");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo bien", param.getNumConsecutivoBien());
		logueo.setParametro("Numero ordinal", param.getNumOrdinal());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_bajaListaBienes");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			cstmt.setInt(2, Integer.valueOf(param.getNumCertificado()));
			cstmt.setInt(3, Integer.valueOf(param.getNumConsecutivoBien()));
			cstmt.setString(4, param.getNumOrdinal());

			cstmt.setString(5, param.getUsuario());
			cstmt.registerOutParameter(6, Types.INTEGER);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.registerOutParameter(8, Types.VARCHAR);

			cstmt.execute();

			int codError = cstmt.getInt(6);
			String descError = cstmt.getString(7);
			String sqlError = cstmt.getString(8);

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

	public ResultModificarListaBienes modificarListaBienes(ParamModificarListaBienes param) {
		ResultModificarListaBienes resultado = new ResultModificarListaBienes();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("modificarListaBienes");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo bien", param.getNumConsecutivoBien());
		logueo.setParametro("Codigo objeto", param.getCodObjeto());
		logueo.setParametro("Descripcion", param.getDescripcion());
		logueo.setParametro("Valor", param.getValor());
		logueo.setParametro("Numero unidades", param.getNumUnidades());
		logueo.setParametro("Codigo marca", param.getCodMarca());
		logueo.setParametro("Serial", param.getSerial());
		logueo.setParametro("Numero ordinal", param.getNumOrdinal());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_modificarListaBienes");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			cstmt.setInt(2, Integer.valueOf(param.getNumCertificado()));
			cstmt.setInt(3, Integer.valueOf(param.getNumConsecutivoBien()));
			cstmt.setInt(4, Integer.valueOf(param.getCodObjeto()));
			cstmt.setString(5, param.getDescripcion());
			cstmt.setDouble(6, Double.valueOf(param.getValor()));
			cstmt.setInt(7, Integer.valueOf(param.getNumUnidades()));
			if (param.getCodMarca() == null || (param.getCodMarca().equals("")) || (param.getCodMarca().equals(Values.NULL))) {
				cstmt.setNull(8, Types.INTEGER);
			} else {
				cstmt.setInt(8, Integer.valueOf(param.getCodMarca()));
			}
			if (param.getSerial() == null || (param.getSerial().equals("")) || (param.getSerial().equals(Values.NULL))) {
				cstmt.setNull(9, Types.INTEGER);
			} else {
				cstmt.setInt(9, Integer.valueOf(param.getCodMarca()));
			}
			cstmt.setInt(10, Integer.valueOf(param.getNumOrdinal()));
			cstmt.setString(11, param.getUsuario());
			cstmt.registerOutParameter(12, Types.INTEGER);
			cstmt.registerOutParameter(13, Types.VARCHAR);
			cstmt.registerOutParameter(14, Types.VARCHAR);

			cstmt.execute();

			int codError = cstmt.getInt(12);
			String descError = cstmt.getString(13);
			String sqlError = cstmt.getString(14);

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

	public ResultXmlPL configurarFechasPromocion(ParamConfigurarFechasPromocion param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("configurarFechasPromocion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo promocion", param.getCodPromocion());
		logueo.setParametro("Fecha desde", param.getFechaDesde());
		logueo.setParametro("Fecha hasta", param.getFechaHasta());

		try {
			conn = this.crearConexion();

			String nombrePL = obtenerValor("pro_config_fechas_promocion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");

			cstmt.setString(1, param.getCodPromocion());
			if (param.getFechaDesde() == null) {
				cstmt.setNull(2, java.sql.Types.DATE);
			} else {
				cstmt.setDate(2, toSqlDate(param.getFechaDesde()));
			}

			if (param.getFechaHasta() == null) {
				cstmt.setNull(3, java.sql.Types.DATE);
			} else {
				cstmt.setDate(3, toSqlDate(param.getFechaHasta()));
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

	public ResultRenovacionManual renovacionManual(ParamRenovacionManual param) {
		ResultRenovacionManual resultado = new ResultRenovacionManual();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("renovacionManual");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo productor", param.getCodProductor());
		logueo.setParametro("Codigo moneda", param.getCodMoneda());
		logueo.setParametro("Codigo promocion", param.getCodPromocion());
		logueo.setParametro("Renovacion", param.getRenovacion());
		logueo.setParametro("Tipo calculo", param.getTipoCalculo());
		logueo.setParametro("Forma pago", param.getFormaPago());
		logueo.setParametro("Fecha hasta vigencia", param.getFechaHastaVigencia());
		logueo.setParametro("Plan pago", param.getPlanPago());
		logueo.setParametro("Medio pago", param.getMedioPago());
		logueo.setParametro("Origen pago", param.getOrigenPago());
		logueo.setParametro("Codigo banco", param.getCodBanco());
		logueo.setParametro("Solo cotiza", param.getSoloCotiza());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_renovacionManual");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			cstmt.setString(3, param.getUsuario());
			cstmt.setInt(4, Integer.valueOf(param.getCodProductor()));
			cstmt.setString(5, param.getCodMoneda());
			cstmt.setString(6, param.getCodPromocion());
			cstmt.setString(7, param.getRenovacion());
			cstmt.setString(8, param.getTipoCalculo());
			cstmt.setString(9, param.getFormaPago());

			if (param.getFechaHastaVigencia() == null) {
				cstmt.setNull(10, java.sql.Types.DATE);
			} else {
				cstmt.setDate(10, toSqlDate(param.getFechaHastaVigencia()));
			}

			cstmt.setInt(11, Integer.valueOf(param.getPlanPago()));
			cstmt.setInt(12, Integer.valueOf(param.getMedioPago()));
			cstmt.setString(13, param.getOrigenPago());
			if (param.getCodBanco() != null) {
				cstmt.setInt(14, Integer.valueOf(param.getCodBanco()));
			} else {
				cstmt.setNull(14, Types.INTEGER);
			}

			cstmt.setString(15, param.getSoloCotiza());

			cstmt.registerOutParameter(16, Types.VARCHAR);
			cstmt.registerOutParameter(17, Types.INTEGER);
			cstmt.registerOutParameter(18, Types.INTEGER);
			cstmt.registerOutParameter(19, Types.VARCHAR);
			cstmt.registerOutParameter(20, Types.VARCHAR);

			cstmt.execute();

			Integer numCotizacion = cstmt.getInt(17);
			if (numCotizacion != 0) {
				resultado.setNumCotizacion(cstmt.getInt(17));
			}
			resultado.setResultado(cstmt.getString(16));
			int codError = cstmt.getInt(18);
			String descError = cstmt.getString(19);
			String sqlError = cstmt.getString(20);

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
			logueo.setParametro("Numero cotizacion", resultado.getNumCotizacion());
			logueo.setParametro("Resultado", resultado.getResultado());
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

	public ResultIngresarNotaPoliza ingresarNotaPoliza(ParamIngresarNotaPoliza param) {
		ResultIngresarNotaPoliza resultado = new ResultIngresarNotaPoliza();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("ingresarNotaPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo tipo nota", param.getCodTipoNota());
		logueo.setParametro("Enlace", param.getEnlace());
		logueo.setParametro("Observaciones", param.getObservaciones());
		logueo.setParametro("Valor 1", param.getValor1());
		logueo.setParametro("Valor 2", param.getValor2());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_ingresarNotaPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, param.getCodTipoNota());
			cstmt.setString(2, param.getEnlace());
			cstmt.setString(3, param.getObservaciones());
			cstmt.setString(4, param.getUsuario());
			cstmt.setString(5, param.getValor1());
			cstmt.setString(6, param.getValor2());

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

	public ResultHabilitoBotones habilitoBotones(ParamHabilitoBotones param) {
		ResultHabilitoBotones resultado = new ResultHabilitoBotones();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("habilitoBotones");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Certificado", param.getNumCertificado());
		logueo.setParametro("Codigo bien", param.getCodBien());
		logueo.setParametro("Consecutivo bien", param.getConsecutivoBien());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_habilitoBotones");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			cstmt.setInt(2, Integer.valueOf(param.getNumCertificado()));
			cstmt.setInt(3, Integer.valueOf(param.getCodBien()));
			cstmt.setInt(4, Integer.valueOf(param.getConsecutivoBien()));
			cstmt.setString(5, param.getUsuario());

			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.registerOutParameter(8, Types.VARCHAR);
			cstmt.registerOutParameter(9, Types.VARCHAR);
			cstmt.registerOutParameter(10, Types.VARCHAR);
			cstmt.registerOutParameter(11, Types.VARCHAR);

			cstmt.registerOutParameter(12, Types.INTEGER);
			cstmt.registerOutParameter(13, Types.VARCHAR);
			cstmt.registerOutParameter(14, Types.VARCHAR);

			cstmt.execute();

			resultado.setHabilitarAgregarBien(cstmt.getString(6));
			resultado.setHabilitarQuitarBien(cstmt.getString(7));
			resultado.setHabilitarListaBienes(cstmt.getString(8));
			resultado.setHabilitarUbicacion(cstmt.getString(9));
			resultado.setHabilitarBeneficiarios(cstmt.getString(10));
			resultado.setHabilitoAcreedores(cstmt.getString(11));

			int codError = cstmt.getInt(12);
			String descError = cstmt.getString(13);
			String sqlError = cstmt.getString(14);

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

	public ResultValidarAcumulaRiesgos validarAcumulaRiesgos(ParamValidarAcumulaRiesgos param) {
		ResultValidarAcumulaRiesgos resultado = new ResultValidarAcumulaRiesgos();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("validarAcumulaRiesgos");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_validarAcumulaRiesgos");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			cstmt.setInt(2, Integer.valueOf(param.getNumCertificado()));

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

	public ResultAltaTextoCotizacion altaTextoCotizacion(ParamAltaTextoCotizacion param) {
		ResultAltaTextoCotizacion resultado = new ResultAltaTextoCotizacion();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("altaTextoCotizacion");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Codigo de texto", param.getCodTexto());
		logueo.setParametro("Impresion", param.getImpresion());
		logueo.setParametro("Fecha desde", param.getFechaDesde());
		logueo.setParametro("Fecha hasta", param.getFechaHasta());
		logueo.setParametro("Detalle texto", param.getDetalleTexto());
		logueo.setParametro("Persiste", param.getPersiste());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_altaTextoCotizacion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			if (param.getNumCotizacion() != null) {
				cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			}

			if (param.getNumCertificado() != null) {
				cstmt.setInt(2, Integer.valueOf(param.getNumCertificado()));
			}

			cstmt.setString(4, param.getImpresion());

			if (param.getFechaDesde() != null) {
				cstmt.setDate(5, toSqlDate(param.getFechaDesde()));
			} else {
				cstmt.setNull(5, Types.DATE);
			}

			if (param.getFechaHasta() != null) {
				cstmt.setDate(6, toSqlDate(param.getFechaHasta()));
			} else {
				cstmt.setNull(6, Types.DATE);
			}

			if (param.getCodTexto() != null) {
				cstmt.setInt(7, Integer.valueOf(param.getCodTexto()));
			}

			cstmt.setString(8, param.getDetalleTexto());
			cstmt.setString(9, param.getPersiste());
			cstmt.setString(10, param.getUsuario());

			cstmt.registerOutParameter(3, Types.INTEGER);
			cstmt.registerOutParameter(11, Types.INTEGER);
			cstmt.registerOutParameter(12, Types.VARCHAR);
			cstmt.registerOutParameter(13, Types.VARCHAR);

			cstmt.execute();

			resultado.setNumConsecutivoTexto(cstmt.getInt(3));

			int codError = cstmt.getInt(11);
			String descError = cstmt.getString(12);
			String sqlError = cstmt.getString(13);

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

	public ResultModificarTextoCotizacion modificarTextoCotizacion(ParamModificarTextoCotizacion param) {
		ResultModificarTextoCotizacion resultado = new ResultModificarTextoCotizacion();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("modificarTextoCotizacion");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo texto", param.getNumConsecutivoTexto());
		logueo.setParametro("Codigo de texto", param.getCodTexto());
		logueo.setParametro("Impresion", param.getImpresion());
		logueo.setParametro("Fecha desde", param.getFechaDesde());
		logueo.setParametro("Fecha hasta", param.getFechaHasta());
		logueo.setParametro("Detalle texto", param.getDetalleTexto());
		logueo.setParametro("Persiste", param.getPersiste());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_modificarTextoCotizacion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			if (param.getNumCotizacion() != null) {
				cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			}

			if (param.getNumCertificado() != null) {
				cstmt.setInt(2, Integer.valueOf(param.getNumCertificado()));
			}

			if (param.getNumConsecutivoTexto() != null) {
				cstmt.setInt(3, Integer.valueOf(param.getNumConsecutivoTexto()));
			}
			cstmt.setString(4, param.getImpresion());

			if (param.getFechaDesde() != null) {
				cstmt.setDate(5, toSqlDate(param.getFechaDesde()));
			} else {
				cstmt.setNull(5, Types.DATE);
			}

			if (param.getFechaHasta() != null) {
				cstmt.setDate(6, toSqlDate(param.getFechaHasta()));
			} else {
				cstmt.setNull(6, Types.DATE);
			}

			if (param.getCodTexto() != null) {
				cstmt.setInt(7, Integer.valueOf(param.getCodTexto()));
			}

			cstmt.setString(8, param.getDetalleTexto());
			cstmt.setString(9, param.getPersiste());
			cstmt.setString(10, param.getUsuario());
			cstmt.registerOutParameter(11, Types.INTEGER);
			cstmt.registerOutParameter(12, Types.VARCHAR);
			cstmt.registerOutParameter(13, Types.VARCHAR);

			cstmt.execute();

			int codError = cstmt.getInt(11);
			String descError = cstmt.getString(12);
			String sqlError = cstmt.getString(13);

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

	public ResultAgregarAcreedorXBien agregarAcreedorXBien(ParamAgregarAcreedorXBien param) {
		ResultAgregarAcreedorXBien resultado = new ResultAgregarAcreedorXBien();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("agregarAcreedorXBien");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo bien", param.getNumConsecutivoBien());
		logueo.setParametro("Codigo de acreedor", param.getCodAcreedor());
		logueo.setParametro("Codigo de objeto", param.getCodObjeto());
		logueo.setParametro("Tipo acreedor", param.getTipoAcreedor());
		logueo.setParametro("Porcentaje de participacion", param.getPorcentajeParticipacion());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_agregarAcreedoresXBien");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			if (param.getNumCotizacion() != null) {
				cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			}

			if (param.getNumCertificado() != null) {
				cstmt.setInt(2, Integer.valueOf(param.getNumCertificado()));
			}

			if (param.getNumConsecutivoBien() != null) {
				cstmt.setInt(3, Integer.valueOf(param.getNumConsecutivoBien()));
			}

			if (param.getCodAcreedor() != null) {
				cstmt.setInt(4, Integer.valueOf(param.getCodAcreedor()));
			}

			if (param.getCodObjeto() != null) {
				cstmt.setInt(5, Integer.valueOf(param.getCodObjeto()));
			}
			cstmt.setString(6, param.getTipoAcreedor());

			if (param.getPorcentajeParticipacion() != null) {
				cstmt.setDouble(7, Double.valueOf(param.getPorcentajeParticipacion()));
			}

			cstmt.setNull(8, Types.VARCHAR);
			cstmt.setNull(9, Types.DATE);
			cstmt.setString(10, param.getUsuario());

			cstmt.registerOutParameter(8, Types.VARCHAR);
			cstmt.registerOutParameter(11, Types.INTEGER);
			cstmt.registerOutParameter(12, Types.VARCHAR);
			cstmt.registerOutParameter(13, Types.VARCHAR);

			cstmt.execute();

			String identificador = cstmt.getString(8);
			resultado.setIdentificador(identificador);
			int codError = cstmt.getInt(11);
			String descError = cstmt.getString(12);
			String sqlError = cstmt.getString(13);

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

	public ResultModificarAcreedorXBien modificarAcreedorXBien(ParamModificarAcreedorXBien param) {
		ResultModificarAcreedorXBien resultado = new ResultModificarAcreedorXBien();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("modificarAcreedorXBien");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo bien", param.getNumConsecutivoBien());
		logueo.setParametro("Codigo de acreedor", param.getCodAcreedor());
		logueo.setParametro("Codigo de objeto", param.getCodObjeto());
		logueo.setParametro("Tipo acreedor", param.getTipoAcreedor());
		logueo.setParametro("Porcentaje de participacion", param.getPorcentajeParticipacion());
		logueo.setParametro("Identificador", param.getIdentificador());
		logueo.setParametro("Fecha de Exclusion", param.getFechaExclusion());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_modificarAcreedorXBien");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			if (param.getNumCotizacion() != null) {
				cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			}

			if (param.getNumCertificado() != null) {
				cstmt.setInt(2, Integer.valueOf(param.getNumCertificado()));
			}

			if (param.getNumConsecutivoBien() != null) {
				cstmt.setInt(3, Integer.valueOf(param.getNumConsecutivoBien()));
			}

			if (param.getCodAcreedor() != null) {
				cstmt.setInt(4, Integer.valueOf(param.getCodAcreedor()));
			}

			if (param.getCodObjeto() != null) {
				cstmt.setInt(5, Integer.valueOf(param.getCodObjeto()));
			}
			cstmt.setString(6, param.getTipoAcreedor());

			if (param.getPorcentajeParticipacion() != null) {
				cstmt.setDouble(7, Double.valueOf(param.getPorcentajeParticipacion()));
			}

			cstmt.setString(8, param.getIdentificador());

			if (param.getFechaExclusion() != null) {
				cstmt.setDate(9, toSqlDate(param.getFechaExclusion()));
			} else {
				cstmt.setNull(9, Types.DATE);
			}

			cstmt.setString(10, param.getUsuario());
			cstmt.registerOutParameter(11, Types.INTEGER);
			cstmt.registerOutParameter(12, Types.VARCHAR);
			cstmt.registerOutParameter(13, Types.VARCHAR);

			cstmt.execute();

			int codError = cstmt.getInt(11);
			String descError = cstmt.getString(12);
			String sqlError = cstmt.getString(13);

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

	public ResultQuitarAcreedorXBien quitarAcreedorXBien(ParamQuitarAcreedorXBien param) {
		ResultQuitarAcreedorXBien resultado = new ResultQuitarAcreedorXBien();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("quitarAcreedorXBien");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Identificador", param.getIdentificador());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_quitarAcreedorXBien");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?)}");

			cstmt.setString(1, param.getIdentificador());
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

	public ResultCalcularValidarVigencia calcularValidarVigencia(ParamCalcularValidarVigencia param) {
		ResultCalcularValidarVigencia resultado = new ResultCalcularValidarVigencia();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("calcularValidarVigencia");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Codigo producto", param.getCodProducto());
		logueo.setParametro("Numero de poliza", param.getNumPoliza());
		logueo.setParametro("Codigo de promocion", param.getCodPromocion());
		logueo.setParametro("Modo de calculo", param.getModoCalculo());
		logueo.setParametro("Vigencia", param.getVigencia());
		logueo.setParametro("Tipo de transaccion", param.getTipoTransaccion());
		logueo.setParametro("Fecha desde", param.getFechaDesdeVigencia());
		logueo.setParametro("Fecha hasta", param.getFechaHastaVigencia());
		logueo.setParametro("Codigo de vigencia tecnica", param.getCodVigenciaTecnica());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_calcularValidarVigencia");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			if (param.getCodRamo() != null) {
				cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			}

			cstmt.setString(2, param.getCodProducto());

			if (param.getNumPoliza() != null) {
				cstmt.setInt(3, Integer.valueOf(param.getNumPoliza()));
			} else {
				cstmt.setNull(3, Types.INTEGER);
			}

			cstmt.setString(4, param.getCodPromocion());
			cstmt.setString(5, param.getModoCalculo());
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.setString(6, param.getVigencia());
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.setString(7, param.getTipoTransaccion());

			if (param.getFechaDesdeVigencia() == null) {
				cstmt.setNull(8, java.sql.Types.DATE);
			} else {
				cstmt.setDate(8, toSqlDate(param.getFechaDesdeVigencia()));
			}

			if (param.getFechaHastaVigencia() == null) {
				cstmt.setNull(9, java.sql.Types.DATE);
			} else {
				cstmt.setDate(9, toSqlDate(param.getFechaHastaVigencia()));
			}

			cstmt.setString(10, param.getCodVigenciaTecnica());
			cstmt.setNull(11, java.sql.Types.DATE);
			cstmt.setNull(12, java.sql.Types.DATE);
			cstmt.setString(13, param.getUsuario());

			cstmt.registerOutParameter(8, Types.DATE); // fechaDesdeVigencia
			cstmt.registerOutParameter(9, Types.DATE); // fechaHastaVigencia
			cstmt.registerOutParameter(11, Types.DATE);// fechaDesdeVigenciaTecnica
			cstmt.registerOutParameter(12, Types.DATE);// fechaHastaVigenciaTecnica

			cstmt.registerOutParameter(14, Types.INTEGER);
			cstmt.registerOutParameter(15, Types.VARCHAR);
			cstmt.registerOutParameter(16, Types.VARCHAR);

			cstmt.execute();

			int codError = cstmt.getInt(14);
			String descError = cstmt.getString(15);
			String sqlError = cstmt.getString(16);

			if (codError == 0) {

				if (cstmt.getString(5) != null) {
					resultado.setModoCalculo(cstmt.getString(5));
				}
				if (cstmt.getString(6) != null) {
					resultado.setVigencia(cstmt.getString(6));
				}

				Date d = new Date();
				DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

				if (cstmt.getDate(8) != null) {
					d = cstmt.getDate(8);
					String fechaDesdeVigencia = formatoFecha.format(d);
					resultado.setFechaDesdeVigencia(fechaDesdeVigencia);
				}

				if (cstmt.getDate(9) != null) {
					d = cstmt.getDate(9);
					String fechaHastaVigencia = formatoFecha.format(d);
					resultado.setFechaHastaVigencia(fechaHastaVigencia);
				}

				if (cstmt.getDate(11) != null) {
					d = cstmt.getDate(11);
					String fechaDesdeVigenciaTecnica = new SimpleDateFormat("dd/MM/yyyy").format(d);
					resultado.setFechaDesdeVigenciaTecnica(fechaDesdeVigenciaTecnica);
				}

				if (cstmt.getDate(12) != null) {
					d = cstmt.getDate(12);
					String fechaHastaVigenciaTecnica = new SimpleDateFormat("dd/MM/yyyy").format(d);
					resultado.setFechaHastaVigenciaTecnica(fechaHastaVigenciaTecnica);
				}

			}

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

	public ResultGuardarPlanes guardarPlanes(ParamGuardarPlanes param) {
		ResultGuardarPlanes resultado = new ResultGuardarPlanes();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("guardarPlanes");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());

		for (int i = 0; i < param.getPlanesCotizacion().size(); i++) {
			logueo.setParametro("Certificado_" + i, param.getPlanesCotizacion().get(i).getNumCertificado());
			logueo.setParametro("PlanCobertura_" + i, param.getPlanesCotizacion().get(i).getCodPlanCobertura());
			logueo.setParametro("PlanPago_" + i, param.getPlanesCotizacion().get(i).getCodPlanPago());
		}

		try {

			for (int i = 0; i < param.getPlanesCotizacion().size(); i++) {

				DatosPlanesCotizacion aux = param.getPlanesCotizacion().get(i);

				conn = this.crearConexion();
				String nombrePL = obtenerValor("proc_guardarPlanes");
				logueo.setNombrePl(nombrePL);

				cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");

				cstmt.setInt(1, param.getNumCotizacion());
				cstmt.setInt(2, aux.getNumCertificado());
				cstmt.setString(3, aux.getCodPlanCobertura());
				cstmt.setString(4, aux.getCodPlanPago());
				cstmt.setString(5, param.getUsuario());

				cstmt.registerOutParameter(6, Types.INTEGER);
				cstmt.registerOutParameter(7, Types.VARCHAR);
				cstmt.registerOutParameter(8, Types.VARCHAR);
				cstmt.execute();

				int codError = cstmt.getInt(6);
				String descError = cstmt.getString(7);
				String sqlError = cstmt.getString(8);

				if (codError != 0) {
					ServiciosError error = new ServiciosError();
					error.setCodigo(Integer.valueOf(codError));
					error.setDescripcion(descError);

					resultado.setError(error);
					resultado.setHayError(Boolean.TRUE);
				}
				logResultados(logueo, codError, descError, sqlError);
			}
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, cstmt);
		}

		return resultado;
	}

	public ResultActualizarDirCotizacion actualizarDireccionesCotizacion(ParamActualizarDirCotizacion param) {
		ResultActualizarDirCotizacion resultado = new ResultActualizarDirCotizacion();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("actualizarDireccionesCotizacion");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Codigo direccion cobro", param.getCodDireccionCobro());
		logueo.setParametro("Codigo direccion envio", param.getCodDireccionEnvio());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_actualizarDireccionesCotizacion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getNumCotizacion());
			cstmt.setInt(2, param.getCodDireccionEnvio());
			cstmt.setInt(3, param.getCodDireccionCobro());
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

	public ResultXmlPL actualizarCoberturaBien(ParamActualizarCoberturaBien param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("actualizarCoberturaBien");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Consecutivo bien", param.getNumConsecutivo());
		logueo.setParametro("Codigo plan cobertura", param.getCodPlanCobertura());
		logueo.setParametro("Codigo ramo cobertura", param.getCodRamoCobertura());
		logueo.setParametro("Codigo cobertura", param.getCodCobertura());
		logueo.setParametro("Valor", param.getValor());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("fun_actualizarCoberturaBien");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{? = call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?)}");

			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);

			cstmt.setInt(2, param.getNumCotizacion());
			cstmt.setInt(3, param.getNumCertificado());
			cstmt.setInt(4, param.getNumConsecutivo());
			cstmt.setString(5, param.getCodPlanCobertura());
			cstmt.setInt(6, param.getCodRamoCobertura());
			cstmt.setString(7, param.getCodCobertura());
			cstmt.setDouble(8, param.getValor());
			cstmt.setString(9, param.getUsuario());

			cstmt.registerOutParameter(10, Types.INTEGER);
			cstmt.registerOutParameter(11, Types.VARCHAR);
			cstmt.registerOutParameter(12, Types.VARCHAR);
			cstmt.registerOutParameter(13, Types.CLOB);

			cstmt.execute();

			int codError = cstmt.getInt(10);
			String descError = cstmt.getString(11);
			String sqlError = cstmt.getString(12);
			Clob clob = cstmt.getClob(13);
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

	public ResultXmlPL calcularCobertura(ParamCalcularCobertura param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("calcularCobertura");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Codigo plan cobertura", param.getCodPlanCobertura());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_calcularCobertura");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getNumCotizacion());
			cstmt.setInt(2, param.getNumCertificado());
			cstmt.setInt(3, param.getNumBien());
			cstmt.setString(4, param.getCodPlanCobertura());
			cstmt.setString(5, param.getUsuario());

			cstmt.registerOutParameter(6, Types.INTEGER);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.registerOutParameter(8, Types.VARCHAR);
			cstmt.registerOutParameter(9, Types.CLOB);

			cstmt.execute();

			int codError = cstmt.getInt(6);
			String descError = cstmt.getString(7);
			String sqlError = cstmt.getString(8);

			if (codError == 0) {
				Herramientas herramientas = new Herramientas();
				resultado.setXml(herramientas.convertirClob(cstmt.getClob(9)));
				LOG.debug(resultado.getXml());
			} else {
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

	public ResultGenerico setearPlanCobertura(ParamSetearPlanCobertura param) {

		ResultGenerico resultado = new ResultGenerico();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("setearPlanCobertura");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Plan cobertura", param.getPlanCobertura());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_setearPlanCob");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getNumCotizacion());

			if (param.getNumCertificado() != null) {
				cstmt.setInt(2, param.getNumCertificado());
			} else {
				cstmt.setNull(2, Types.NULL);
			}

			cstmt.setString(3, param.getPlanCobertura());
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

	public ResultXmlPL calcularCertificado(ParamCalcularCertificado param) {

		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("calcularCertificado");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Plan cobertura", param.getPlanCobertura());
		logueo.setParametro("Plan pago", param.getPlanPago());
		logueo.setParametro("Dia vencimiento", param.getDiaVto());
		logueo.setParametro("Cod nivel comision productor", param.getCodNivelComisionProductor());
		logueo.setParametro("Cod nivel comision broker", param.getCodNivelComisionBroker());
		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_calcularCertificado");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getNumCotizacion());
			if (param.getNumCertificado() != null) {
				cstmt.setInt(2, param.getNumCertificado());
			} else {
				cstmt.setNull(2, Types.NULL);
			}
			if (param.getPlanCobertura() != null) {
				cstmt.setString(3, param.getPlanCobertura());
			} else {
				cstmt.setNull(3, Types.NULL);
			}

			if (param.getPlanPago() != null) {
				cstmt.setString(4, param.getPlanPago());
			} else {
				cstmt.setNull(4, Types.NULL);
			}

			if (param.getDiaVto() != null) {
				cstmt.setInt(5, param.getDiaVto());
			} else {
				cstmt.setNull(5, Types.INTEGER);
			}

			cstmt.setString(6, param.getUsuario());
			cstmt.setString(7, param.getRequeridoPlanesCobertura());

			if (param.getCodNivelComisionProductor() != null) {
				cstmt.setString(8, param.getCodNivelComisionProductor());
			} else {
				cstmt.setNull(8, Types.NULL);
			}

			if (param.getCodNivelComisionBroker() != null) {
				cstmt.setString(9, param.getCodNivelComisionBroker());
			} else {
				cstmt.setNull(9, Types.NULL);
			}

			cstmt.registerOutParameter(10, Types.INTEGER);
			cstmt.registerOutParameter(11, Types.VARCHAR);
			cstmt.registerOutParameter(12, Types.VARCHAR);
			cstmt.registerOutParameter(13, Types.CLOB);

			cstmt.execute();

			int codError = cstmt.getInt(10);
			String descError = cstmt.getString(11);
			String sqlError = cstmt.getString(12);
			Clob clob = cstmt.getClob(13);
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

	public ResultGuardarAsegurado guardarAsegurado(ParamGuardarAsegurado param) {
		ResultGuardarAsegurado resultado = new ResultGuardarAsegurado();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("guardarAsegurado");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Codigo cliente", param.getCodCliente());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_guardarAsegurado");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getNumCotizacion());
			cstmt.setInt(2, param.getNumCertificado());
			cstmt.setInt(3, param.getCodCliente());
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

	public ResultGenerico actualizarCertificadoCeroVehiculo(ParamActualizarCertificadoCeroVehiculo param) {
		ResultGenerico resultado = new ResultGenerico();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("actualizarCertificadoCeroVehiculo");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de productor", param.getCodProductor());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Codigo moneda", param.getCodMoneda());
		logueo.setParametro("Codigo promocion", param.getCodPromocion());
		logueo.setParametro("Tipo calculo", param.getTipoCalculo());
		logueo.setParametro("Fecha desde", param.getFechaDesde());
		logueo.setParametro("Fecha hasta", param.getFechaHasta());
		logueo.setParametro("Forma pago", param.getFormaPago());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_actualizar_certificadoCero_vehiculo");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getNumCotizacion());
			if (param.getCodMoneda() == null) {
				cstmt.setNull(2, Types.VARCHAR);
			} else {
				cstmt.setString(2, param.getCodMoneda());
			}
			if (param.getCodPromocion() == null) {
				cstmt.setNull(3, Types.VARCHAR);
			} else {
				cstmt.setString(3, param.getCodPromocion());
			}
			if (param.getTipoCalculo() == null) {
				cstmt.setNull(4, Types.VARCHAR);
			} else {
				cstmt.setString(4, param.getTipoCalculo());
			}

			if (param.getFormaPago() == null) {
				cstmt.setNull(5, Types.VARCHAR);
			} else {
				cstmt.setString(5, param.getFormaPago());
			}

			if (param.getFechaDesde() == null) {
				cstmt.setNull(6, java.sql.Types.DATE);
			} else {
				cstmt.setDate(6, toSqlDate(param.getFechaDesde()));
			}

			if (param.getFechaHasta() == null) {
				cstmt.setNull(7, java.sql.Types.DATE);
			} else {
				cstmt.setDate(7, toSqlDate(param.getFechaHasta()));
			}

			cstmt.setString(8, param.getUsuario());

			if (param.getCodProductor() == null) {
				cstmt.setNull(9, Types.INTEGER);
			} else {
				cstmt.setInt(9, Integer.valueOf(param.getCodProductor()));
			}

			cstmt.registerOutParameter(10, Types.INTEGER);
			cstmt.registerOutParameter(11, Types.VARCHAR);
			cstmt.registerOutParameter(12, Types.VARCHAR);

			cstmt.execute();

			int codError = cstmt.getInt(10);
			String descError = cstmt.getString(11);
			String sqlError = cstmt.getString(12);

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

	public ResultActualizarDatosBancarios actualizarDatosBancarios(ParamActualizarDatosBancarios param) {
		ResultActualizarDatosBancarios resultado = new ResultActualizarDatosBancarios();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("actualizarDatosBancarios");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero domicilio banco", param.getNumDomicilioBanco());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_actualizarDatosBancarios");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getNumCotizacion());
			if (param.getNumDomicilioBanco() != null) {
				cstmt.setInt(2, param.getNumDomicilioBanco());
			} else {
				cstmt.setNull(2, Types.INTEGER);
			}

			cstmt.setString(3, param.getUsuario());

			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);

			cstmt.execute();

			int codError = cstmt.getInt(4);
			String descError = cstmt.getString(5);
			String sqlError = cstmt.getString(6);

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

	public ResultEnviarCotizacionFueraPauta enviarCotizacionFueraPauta(ParamEnviarCotizacionFueraPauta param) {
		ResultEnviarCotizacionFueraPauta resultado = new ResultEnviarCotizacionFueraPauta();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("enviarCotizacionFueraPauta");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_enviarCotizacionFueraPauta");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?)}");

			cstmt.setInt(1, param.getNumCotizacion());

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

	public ResultModificarOpcionesFacturacion modificarOpcionesFacturacion(ParamModificarOpcionesFacturacion param) {
		ResultModificarOpcionesFacturacion resultado = new ResultModificarOpcionesFacturacion();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("modificarOpcionesFacturacion");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Enviar factura por email", param.getEnviarFacturaEmail());
		logueo.setParametro("Emitir con RUT", param.getEmitirConRUT());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_modificarOpcionesFacturacion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getNumCotizacion());
			cstmt.setString(2, param.getUsuario());

			cstmt.setString(3, (param.getEnviarFacturaEmail()) ? "S" : "N");
			cstmt.setInt(4, (param.getEmitirConRUT()) ? 1 : 0);

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

	public ResultXmlPL nuevaCotizacionVehiculo(ParamNuevaCotizacionVehiculo param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("nuevaCotizacionVehiculo");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de productor", param.getCodProductor());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_crear_cotizacion_vehiculo");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");
			cstmt.setString(1, param.getUsuario());

			if (param.getCodProductor() == null) {
				cstmt.setNull(2, Types.INTEGER);

			} else {
				cstmt.setInt(2, Integer.valueOf(param.getCodProductor()));
			}

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

	public ResultXmlPL validarDatosVehiculo(ParamValidarDatosVehiculo param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("validarDatosVehiculo");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Lista", param.getLista());
		logueo.setParametro("Modo", param.getModo());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("fun_actualizar_validar_datos_vehiculo");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{? = call " + nombrePL + "(?,?,?,?,?,?,?,?)}");
			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);

			cstmt.setInt(2, param.getNumCotizacion());
			cstmt.setString(3, param.getLista());
			cstmt.setString(4, param.getModo());
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
			if (codError != 0) {
				ServiciosError error = new ServiciosError();
				error.setCodigo(Integer.valueOf(codError));
				error.setDescripcion(descError);
				resultado.setError(error);
				resultado.setHayError(Boolean.TRUE);
			}
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

	public ResultXmlPL actualizarPromocionVehiculo(ParamActualizarPromocionVehiculo param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("actualizarPromocionVehiculo");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Codigo de promocion", param.getCodPromocion());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_actualizar_promocion_vehiculo");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getNumCotizacion());
			cstmt.setString(2, param.getCodPromocion());
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

	public ResultXmlPL calcularCuotasVehiculo(ParamCalcularCuotasVehiculo param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("calcularCuotasVehiculo");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero de cotizacion", param.getNumCotizacion());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_calcular_cuotas_vehiculo");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");
			cstmt.setInt(1, param.getNumCotizacion());
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

	public ResultActualizarCertificadoCero actualizarCertificadoCero(ParamActualizarCertificadoCero param) {
		ResultActualizarCertificadoCero resultado = new ResultActualizarCertificadoCero();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("actualizarCertificadoCero");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Codigo producto", param.getCodProducto());
		logueo.setParametro("Tipo facturacion", param.getTipoFacturacion());
		logueo.setParametro("Codigo moneda", param.getCodMoneda());
		logueo.setParametro("Medio pago", param.getMedioPago());
		logueo.setParametro("Origen pago", param.getOrigenPago());
		logueo.setParametro("Codigo promocion", param.getCodPromocion());
		logueo.setParametro("Renovacion", param.getRenovacion());
		logueo.setParametro("Tipo calculo", param.getTipoCalculo());
		logueo.setParametro("Vigencia", param.getVigencia());
		logueo.setParametro("Fecha desde vigencia", param.getFechaDesdeVigencia());
		logueo.setParametro("Fecha hasta vigencia", param.getFechaHastaVigencia());
		logueo.setParametro("Tipo vigencia tecnica", param.getTipoVigenciaTecnica());
		logueo.setParametro("Anulacion corrida", param.getAnulacionCorrida());
		logueo.setParametro("Codigo cliente", param.getCodClienteContratante());
		logueo.setParametro("Codigo direccion cobro", param.getCodDireccionCobro());
		logueo.setParametro("Codigo direccion envio", param.getCodDireccionEnvio());
		logueo.setParametro("Fecha desde vigencia tecnica", param.getFechaDesdeVigenciaTecnica());
		logueo.setParametro("Fecha hasta vigencia tecnica ", param.getFechaHastaVigenciaTecnica());
		logueo.setParametro("Codigo productor ", param.getCodProductor());
		logueo.setParametro("Envio factura por mail", param.getEnviarFacturaEmail());
		logueo.setParametro("Emitir con rut", param.getEmitirConRUT());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_actualizarCertificadoCero");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getNumCotizacion());
			cstmt.setInt(2, param.getNumCertificado());
			cstmt.setInt(3, param.getCodRamo());
			cstmt.setString(4, param.getCodProducto());

			if (param.getTipoFacturacion() == null) {
				cstmt.setNull(5, Types.VARCHAR);
			} else {
				cstmt.setString(5, param.getTipoFacturacion());
			}

			if (param.getCodMoneda() == null) {
				cstmt.setNull(6, Types.VARCHAR);
			} else {
				cstmt.setString(6, param.getCodMoneda());
			}

			if (param.getMedioPago() == null) {
				cstmt.setNull(7, Types.INTEGER);
			} else {
				cstmt.setInt(7, param.getMedioPago());
			}

			if (param.getOrigenPago() == null) {
				cstmt.setNull(8, Types.VARCHAR);
			} else {
				cstmt.setString(8, param.getOrigenPago());
			}

			if (param.getCodPromocion() == null) {
				cstmt.setNull(9, Types.VARCHAR);
			} else {
				cstmt.setString(9, param.getCodPromocion());
			}

			if (param.getRenovacion() == null) {
				cstmt.setNull(10, Types.VARCHAR);
			} else {
				cstmt.setString(10, param.getRenovacion());
			}

			if (param.getTipoCalculo() == null) {
				cstmt.setNull(11, Types.VARCHAR);
			} else {
				cstmt.setString(11, param.getTipoCalculo());
			}

			if (param.getVigencia() == null) {
				cstmt.setNull(12, Types.VARCHAR);
			} else {
				cstmt.setString(12, param.getVigencia());
			}

			if (param.getFechaDesdeVigencia() == null) {
				cstmt.setNull(13, java.sql.Types.DATE);
			} else {
				cstmt.setDate(13, toSqlDate(param.getFechaDesdeVigencia()));
			}

			if (param.getFechaHastaVigencia() == null) {
				cstmt.setNull(14, java.sql.Types.DATE);
			} else {
				cstmt.setDate(14, toSqlDate(param.getFechaHastaVigencia()));
			}

			if (param.getTipoVigenciaTecnica() == null) {
				cstmt.setNull(15, Types.VARCHAR);
			} else {
				cstmt.setString(15, param.getTipoVigenciaTecnica());
			}

			if (param.getAnulacionCorrida() == null) {
				cstmt.setNull(16, Types.VARCHAR);
			} else {
				cstmt.setString(16, param.getAnulacionCorrida());
			}

			if (param.getCodClienteContratante() == null) {
				cstmt.setNull(17, Types.VARCHAR);
			} else {
				cstmt.setString(17, param.getCodClienteContratante());
			}

			if (param.getCodDireccionEnvio() == null) {
				cstmt.setNull(18, Types.INTEGER);
			} else {
				cstmt.setInt(18, param.getCodDireccionEnvio());
			}

			if (param.getCodDireccionCobro() == null) {
				cstmt.setNull(19, Types.INTEGER);
			} else {
				cstmt.setInt(19, param.getCodDireccionCobro());
			}

			if (param.getFechaDesdeVigenciaTecnica() == null) {
				cstmt.setNull(20, java.sql.Types.DATE);
			} else {
				cstmt.setDate(20, toSqlDate(param.getFechaDesdeVigenciaTecnica()));
			}

			if (param.getFechaHastaVigenciaTecnica() == null) {
				cstmt.setNull(21, java.sql.Types.DATE);
			} else {
				cstmt.setDate(21, toSqlDate(param.getFechaHastaVigenciaTecnica()));
			}

			cstmt.setString(22, param.getUsuario());
			if (param.getCodProductor() == null) {
				cstmt.setNull(23, Types.INTEGER);
			} else {
				cstmt.setInt(23, Integer.valueOf(param.getCodProductor()));
			}
			if (param.getEnviarFacturaEmail()!=null) {
				cstmt.setString(24, (param.getEnviarFacturaEmail()) ? "S" : "N");
			} else {
				cstmt.setNull(24, Types.VARCHAR);
			}
			
			if (param.getEmitirConRUT()!=null) {
				cstmt.setInt(25, (param.getEmitirConRUT()) ? 1 : 0);
			} else {
				cstmt.setNull(25, Types.INTEGER);
			}

			cstmt.registerOutParameter(26, Types.INTEGER);
			cstmt.registerOutParameter(27, Types.VARCHAR);
			cstmt.registerOutParameter(28, Types.VARCHAR);

			cstmt.execute();

			int codError = cstmt.getInt(26);
			String descError = cstmt.getString(27);
			String sqlError = cstmt.getString(28);

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

	public Boolean validarRequiereDomicilioBancario(Integer codMedioPago) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("validarRequiereDomicilioBancario");
		logueo.setParametro("Codigo medio de pago", codMedioPago);
		Boolean resultado = Boolean.FALSE;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();

			sql.append("SELECT 1  FROM COBT_TIPOS_COBRANZAS ");
			sql.append("WHERE COTC_CAMD_CD_MEDIO_PAGO=? ");
			sql.append("AND COTC_TP_CUENTA IS NOT NULL ");
			sql.append("AND ROWNUM = 1");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, codMedioPago);

			result = pst.executeQuery();

			while (result.next()) {
				resultado = Boolean.TRUE;
			}

			LOG.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			logueo.setException("SQLException");
			logueo.setError(e.getMessage());
			log.error(logueo.getMensaje());
		} catch (Exception ex) {
			logueo.setException("Exception");
			logueo.setError(ex.getMessage());
			log.error(logueo.getMensaje());
		} finally {
			liberarRecursos(conn, pst, result);
		}
		return resultado;
	}

	public ResultValidarCotizacion validarCotizacion(ParamValidarCotizacion param) {
		ResultValidarCotizacion resultado = new ResultValidarCotizacion();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("validarCotizacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("fun_cot_valida");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{? = call " + nombrePL + "(?,?,?,?,?,?)}");
			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);

			cstmt.setInt(2, param.getNumCotizacion());

			cstmt.setNull(7, java.sql.Types.CLOB);

			cstmt.registerOutParameter(3, Types.VARCHAR);
			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);

			cstmt.registerOutParameter(7, Types.CLOB);
			cstmt.execute();

			String ret = cstmt.getString(1);

			Integer planPago = cstmt.getInt(3);
			Integer codigoError = cstmt.getInt(4);
			String mensajeError = cstmt.getString(5);

			log.debug(cstmt.getClob(7));

			Herramientas herramientas = new Herramientas();
			String res = herramientas.convertirClob(cstmt.getClob(7));

			ValidaCotizacion cot = new ValidaCotizacion();

			if (ret.equals("S")) {
				cot.setCotizacionValida(Boolean.TRUE);

			} else {
				cot.setCotizacionValida(Boolean.FALSE);

				if (res != null) {
					ParseoXml parseo = new ParseoXml(res);

					if (parseo.generarDoc()) {
						ArrayList<String> pautas = parseo.parsearValidarCotizacion();
						cot.setFueraDePauta(pautas);
					}
				} else {

					cot.setPlanPago(planPago);
					cot.setCodError(codigoError);
					cot.setDescripcion(mensajeError);
				}

			}

			resultado.setValidar(cot);

			logueo.setParametro("Return", ret);
			logueo.setParametro("Mensaje", mensajeError);
			logueo.setParametro("Codigo de error", codigoError);
			logueo.setParametro("Plan de pago", planPago);
			logueo.setParametro("Resultado", res);

			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, cstmt);
		}

		return resultado;
	}

	// version que Gus me hizo cambiar , gracias totales

	/*
	 * public ResultXmlPL emitirPoliza(ParamEmision param) { ResultXmlPL resultado =
	 * new ResultXmlPL(); CallableStatement cstmt = null; Connection conn = null;
	 * Logueo logueo = new Logueo();
	 * 
	 * logueo.setEncabezado(Values.ENCABEZADOPERSIST);
	 * logueo.setClase(CotOperaciones.class); logueo.setMetodo("emitirPoliza()");
	 * logueo.setParametro(Values.USUARIO, param.getUsuario());
	 * logueo.setParametro(Values.CLAVE, param.getClave());
	 * logueo.setParametro("Cotizacion", param.getNumCotizacion());
	 * logueo.setParametro("Plan de pago", param.getPlanPago());
	 * 
	 * try { conn = this.crearConexion(); String nombrePL =
	 * obtenerValor("proc_emitir_cotizacion"); logueo.setNombrePl(nombrePL);
	 * 
	 * cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");
	 * 
	 * cstmt.setInt(1, param.getNumCotizacion());
	 * 
	 * if (param.getPlanPago() == null) { cstmt.setNull(2, Types.INTEGER); } else {
	 * cstmt.setInt(2, param.getPlanPago()); } cstmt.setString(3,
	 * param.getUsuario());
	 * 
	 * if (param.getCodProductor() == null) { cstmt.setNull(4, Types.INTEGER); }
	 * else { cstmt.setInt(4, Integer.valueOf(param.getCodProductor())); }
	 * 
	 * cstmt.registerOutParameter(5, Types.INTEGER); // Codigo de error
	 * cstmt.registerOutParameter(6, Types.VARCHAR); // Desc de error
	 * cstmt.registerOutParameter(7, Types.VARCHAR); // Sql error
	 * cstmt.registerOutParameter(8, Types.CLOB);
	 * 
	 * cstmt.execute();
	 * 
	 * int codError = cstmt.getInt(5); String descError = cstmt.getString(6); String
	 * sqlError = cstmt.getString(7);
	 * 
	 * if (codError == 0) { Herramientas herramientas = new Herramientas();
	 * resultado.setXml(herramientas.convertirClob(cstmt.getClob(8))); } else {
	 * ServiciosError error = new ServiciosError();
	 * error.setCodigo(Integer.valueOf(codError)); error.setDescripcion(descError);
	 * 
	 * resultado.setError(error); resultado.setHayError(Boolean.TRUE); }
	 * 
	 * logueo.setParametro("Codigo salida", codError);
	 * logueo.setParametro("Descripcion salida", descError);
	 * logueo.setParametro("Sql salida", sqlError); logueo.setParametro("Xml",
	 * resultado.getXml());
	 * 
	 * log.info(logueo.getSoloParametros()); } catch (SQLException e) {
	 * catchSQLException(resultado, logueo, e); } catch (Exception ex) {
	 * catchException(resultado, logueo, ex); } finally { liberarRecursos(conn,
	 * cstmt); } return resultado;
	 * 
	 * }
	 * 
	 */

	public ResultXmlPL emitirPoliza(ParamEmision param) {
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("emitirPoliza()");

		logueo.setParametro("Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Plan de pago", param.getPlanPago());
		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_emitir_cotizacion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getNumCotizacion());

			if (param.getPlanPago() == null) {
				cstmt.setNull(2, Types.INTEGER);
			} else {
				cstmt.setInt(2, param.getPlanPago());
			}
			cstmt.setString(3, param.getUsuario());

			cstmt.registerOutParameter(4, Types.INTEGER); // Codigo de error
			cstmt.registerOutParameter(5, Types.VARCHAR); // Desc de error
			cstmt.registerOutParameter(6, Types.VARCHAR); // Sql error
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