package uy.com.bse.guc.persistencia.impl;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Formatter;
import java.util.Properties;
import java.util.TimeZone;

import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.guc.interfaces.GUCValues;
import uy.com.bse.guc.interfaces.ParamAltaUsuario;
import uy.com.bse.guc.interfaces.ParamCambioClave;
import uy.com.bse.guc.interfaces.ParamLogin;
import uy.com.bse.guc.interfaces.ParamOlvidoClave;
import uy.com.bse.guc.interfaces.ParamValidar;
import uy.com.bse.guc.interfaces.ResultAltaUsuario;
import uy.com.bse.guc.interfaces.ResultCambioClave;
import uy.com.bse.guc.interfaces.ResultClaveUsada;
import uy.com.bse.guc.interfaces.ResultLogin;
import uy.com.bse.guc.interfaces.ResultOlvidoClave;
import uy.com.bse.guc.interfaces.ResultValidar;
import uy.com.bse.guc.persistencia.GUCDataProvider;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.ErrorResolver;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.persistencia.Persistencia;
import uy.com.bse.utilitario.properties.EARPropertiesManager;

public class GUCPersist extends Persistencia implements GUCDataProvider {
	private static final Logger LOG = LogManager.getLogger(GUCPersist.class);
	private EARPropertiesManager manager= new EARPropertiesManager("configGUC.properties");
	@Override
	protected Connection crearConexion() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(Persistencia.class);
		logueo.setMetodo("crearConexion");

		javax.naming.Context ic = null;
		Connection conn = null;
		javax.naming.Context ctx = null;
		javax.sql.DataSource ds = null;
		try {
			TimeZone timeZone = TimeZone.getTimeZone("America/Montevideo");
			TimeZone.setDefault(timeZone);
			ic = new javax.naming.InitialContext();
			ctx = (javax.naming.Context) ic.lookup("java:/");

			String jdni = manager.obtenerValor("GUC.JndiName");
			LOG.info("- Jdni: " + jdni + " -");

			if (jdni != null) {
				ds = (javax.sql.DataSource) ctx.lookup(jdni);

				conn = (Connection) ds.getConnection();
			}
		} catch (NamingException e) {
			logueo.setException("NamingException");
			logueo.setError(e.getMessage());
			LOG.error(logueo.getMensaje());
		} catch (SQLException e) {
			logueo.setException("SQLException");
			logueo.setError(e.getMessage());
			LOG.error(logueo.getMensaje());
		} catch (Exception ex) {
			logueo.setException("Exception");
			logueo.setError(ex.getMessage());
			LOG.error(logueo.getMensaje());
		}

		return conn;
	}

	@Override
	protected String obtenerValor(String clave) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(Persistencia.class);
		logueo.setMetodo("obtenerValor");

		String retorno = new String();

		InputStream input = null;
		String filename = "databaseGUC.properties";
		Properties prop = new Properties();

		input = Persistencia.class.getClassLoader().getResourceAsStream(filename);
		if (input == null) {
			logueo.setError("No se pudo encontrar el archivo: " + filename);
			LOG.error(logueo.getMensaje());
		} else {
			try {
				prop.load(input);
				retorno = prop.getProperty(clave);
				// FIXME OIGRES, CUANDO NO ESTE UN PROPERTIES :::: AVISE VALORAR
			} catch (IOException e) {
				LOG.error(e.getMessage());
			} catch (Exception ex) {
				LOG.error(ex.getMessage());
			}
		}
		return retorno;
	}

	public ResultLogin login(ParamLogin param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(GUCPersist.class);
		logueo.setMetodo("login");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		Connection conn = null;
		CallableStatement cstmt = null;
		ResultLogin resultado = new ResultLogin();

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_login");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");
			cstmt.setString(1, param.getUsuario());
			cstmt.setString(2, encriptar(param.getContrasena()));
			cstmt.registerOutParameter(3, Types.INTEGER);
			cstmt.registerOutParameter(4, Types.VARCHAR);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.execute();

			int codError = cstmt.getInt(3);
			String descError = cstmt.getString(4);
			String sqlError = cstmt.getString(5);
			String mensajeInformacion = cstmt.getString(6);
			if (codError != 0) {
				ServiciosError error = new ServiciosError();
				error.setCodigo(Integer.valueOf(codError));
				error.setDescripcion(descError);
				resultado.setAutenticado(Boolean.FALSE);
				resultado.setError(error);
				resultado.setHayError(Boolean.TRUE);
				resultado.setMensajeInformacion(null);
			} else {
				resultado.setHayError(Boolean.FALSE);
				resultado.setMensajeInformacion(mensajeInformacion);
				resultado.setAutenticado(Boolean.TRUE);
				resultado.setUsuarioAutenticado(param.getUsuario());
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

	private ResultGenerico audita(String usuario, String codigoEvento, String codCambio, String valorAnterior, String valorNuevo) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(GUCPersist.class);
		logueo.setMetodo("audita");
		logueo.setParametro(Values.USUARIO, usuario);
		logueo.setParametro("Codigo de evento", codigoEvento);
		logueo.setParametro("Codigo de cambio", codCambio);
		logueo.setParametro("Valor anterior", valorAnterior);
		logueo.setParametro("Valor nuevo", valorNuevo);
		Connection conn = null;
		CallableStatement cstmt = null;
		ResultGenerico resultado = new ResultGenerico();

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_auditoria");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");
			if (codigoEvento != null) {
				cstmt.setString(1, codigoEvento);
			} else {
				cstmt.setNull(1, Types.VARCHAR);
			}
			if (valorNuevo != null) {
				cstmt.setString(2, valorNuevo);
			} else {
				cstmt.setNull(2, Types.VARCHAR);
			}

			if (valorAnterior != null) {
				cstmt.setString(3, valorAnterior);
			} else {
				cstmt.setNull(3, Types.VARCHAR);
			}

			if (codCambio != null) {
				cstmt.setString(4, codCambio);
			} else {
				cstmt.setNull(4, Types.VARCHAR);
			}

			cstmt.setString(5, usuario);
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
			} else {
				resultado.setHayError(Boolean.FALSE);
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

	public ResultValidar validar(ParamValidar param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(GUCPersist.class);
		logueo.setMetodo("validar");
		logueo.setParametro(Values.USUARIO, param.getUsuario());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultValidar resultado = new ResultValidar();

		try {
			conn = crearConexion();

			final StringBuffer sql = new StringBuffer(230);
			if (param.getClaveUsuario() != null) {
				sql.append("SELECT CUMB_CD_USUARIO  FROM COTW_USUARIOS_MIBSE WHERE CUMB_CD_USUARIO = ? AND CUMB_PASSWORD = ?");
				logueo.setParametro(Values.CONSULTA, sql.toString());
				pst = conn.prepareStatement(sql.toString());
				pst.setString(1, param.getUsuario());
				pst.setString(2, encriptar(param.getClaveUsuario()));
			} else {
				sql.append("SELECT CUMB_CD_USUARIO  FROM COTW_USUARIOS_MIBSE WHERE CUMB_CD_USUARIO = ?");
				logueo.setParametro(Values.CONSULTA, sql.toString());
				pst = conn.prepareStatement(sql.toString());
				pst.setString(1, param.getUsuario());
			}

			result = pst.executeQuery();
			if (result.next()) {
				resultado.setHayError(Boolean.FALSE);
				resultado.setUser(result.getString("CUMB_CD_USUARIO"));
			} else {
				resultado.setHayError(Boolean.FALSE);
				resultado.setUser(null);
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

	public ResultAltaUsuario altaUsuario(ParamAltaUsuario param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(GUCPersist.class);
		logueo.setMetodo("altaUsuario");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro("tipo clave", param.getTipoClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultAltaUsuario resultado = new ResultAltaUsuario();

		try {
			conn = crearConexion();

			final StringBuffer sql = new StringBuffer(230);
			sql.append("INSERT INTO COTW_USUARIOS_MIBSE (CUMB_CD_USUARIO, CUMB_PASSWORD, CUMB_TP_CLAVE) VALUES (?,?,?)");
			String claveEncriptada = encriptar(param.getClaveUsuario());
			logueo.setParametro(Values.CONSULTA, sql.toString());
			System.out.println("CONSULTA " + sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, param.getUsuario());
			pst.setString(2, claveEncriptada);
			pst.setInt(3, param.getTipoClave());

			int cambios = pst.executeUpdate();
			if (cambios > 0) {
				resultado.setHayError(Boolean.FALSE);
				resultado.setUser(param.getUsuario());
			} else {
				resultado.setHayError(Boolean.TRUE);
				resultado.setError(ErrorResolver.getError(Values.NOTUPDATED));
				resultado.setUser(null);
			}

			LOG.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst);
		}
		return resultado;
	}

	public ResultCambioClave cambioClave(ParamCambioClave param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(GUCPersist.class);
		logueo.setMetodo("cambioClave");
		logueo.setParametro(Values.USUARIO, param.getUsuario());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultCambioClave resultado = new ResultCambioClave();

		try {
			conn = crearConexion();

			final StringBuffer sql = new StringBuffer(230);
			sql.append("UPDATE COTW_USUARIOS_MIBSE SET CUMB_PASSWORD = ?, CUMB_TP_CLAVE = ? WHERE CUMB_CD_USUARIO= ? AND CUMB_PASSWORD = ?");
			String claveEncriptada = encriptar(param.getClaveNueva());
			String claveViejaEncriptada = encriptar(param.getClaveAnterior());
			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, claveEncriptada);
			pst.setInt(2, Integer.valueOf(GUCValues.CLAVEUSUARIO));
			pst.setString(3, param.getUsuario());
			pst.setString(4, claveViejaEncriptada);

			int cambios = pst.executeUpdate();
			if (cambios == 1) {
				resultado.setHayError(Boolean.FALSE);
				audita(param.getUsuario(), GUCValues.EVENT_CAMBIO_CLAVE, null, claveViejaEncriptada, claveEncriptada);
			} else {
				resultado.setHayError(Boolean.TRUE);
				resultado.setError(ErrorResolver.getError(Values.NOTUPDATED));
			}

			LOG.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst);
		}
		return resultado;
	}

	public ResultClaveUsada verificarClaveUsadaAnteriormente(ParamCambioClave param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(GUCPersist.class);
		logueo.setMetodo("verificarClaveUsadaAnteriormente");
		logueo.setParametro(Values.USUARIO, param.getUsuario());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultClaveUsada resultado = new ResultClaveUsada();
		ResultSet result = null;
		try {
			conn = crearConexion();

			final StringBuffer sql = new StringBuffer(230);
			sql.append(" SELECT 1 FROM ( SELECT K.CCCM_PASSWORD_OLD ");
					sql.append(" FROM (SELECT J.CCCM_PASSWORD_OLD ");
							sql.append("FROM COTW_CAMBIOS_CLAVE_MIBSE J ");
									sql.append("WHERE CCCM_CD_USUARIO = ? ");
											sql.append("ORDER BY CCCM_FE_ACTUALIZACION DESC) K "); 
													sql.append("WHERE ROWNUM <= 10) H WHERE H.CCCM_PASSWORD_OLD = ?");

			String claveEncriptada = encriptar(param.getClaveNueva());
			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, param.getUsuario());
			pst.setString(2, claveEncriptada);

			result = pst.executeQuery();
			if (result.next()) {
				resultado.setHayError(Boolean.FALSE);
				resultado.setUsada(Boolean.TRUE);
			} else {
				resultado.setHayError(Boolean.FALSE);
				resultado.setUsada(Boolean.FALSE);
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

	public ResultOlvidoClave olvidoClave(ParamOlvidoClave param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(GUCPersist.class);
		logueo.setMetodo("olvidoClave");
		logueo.setParametro(Values.USUARIO, param.getUsuario());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultOlvidoClave resultado = new ResultOlvidoClave();

		try {
			conn = crearConexion();
			final StringBuffer sql = new StringBuffer(230);
			sql.append("UPDATE COTW_USUARIOS_MIBSE SET CUMB_PASSWORD = ?, CUMB_TP_CLAVE = ? WHERE CUMB_CD_USUARIO= ?");
			String claveEncriptada = encriptar(param.getClaveNueva());
			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, claveEncriptada);
			pst.setInt(2, Integer.valueOf(GUCValues.CLAVERESET));
			pst.setString(3, param.getUsuario());

			int cambios = pst.executeUpdate();
			if (cambios == 1) {
				resultado.setHayError(Boolean.FALSE);
				audita(param.getUsuario(), GUCValues.EVENT_RESTABLECIMIENTO_CLAVE, null, claveEncriptada, param.getEmail());
			} else {
				resultado.setHayError(Boolean.TRUE);
				resultado.setError(ErrorResolver.getError(Values.NOTUPDATED));
			}

			LOG.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst);
		}
		return resultado;
	}

	private String encriptar(String texto) throws NoSuchAlgorithmException {
		String resultado = null;
		MessageDigest digest = MessageDigest.getInstance("SHA-512");
		byte[] buffer = texto.getBytes();
		digest.update(buffer, 0, buffer.length);
		byte[] hashValue = digest.digest();
		Formatter formater = new Formatter();
		for (int i = 0; i < hashValue.length; i++) {
			formater.format("%02x", hashValue[i]);
		}
		resultado = formater.toString();
		return resultado;
	}

}
