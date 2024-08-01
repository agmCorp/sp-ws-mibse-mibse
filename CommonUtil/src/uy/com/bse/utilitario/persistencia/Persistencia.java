package uy.com.bse.utilitario.persistencia;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.ErrorResolver;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.util.Herramientas;
import uy.com.bse.utilitario.util.MailBuilder;

public abstract class Persistencia {

	protected static Logger log = LogManager.getLogger(Persistencia.class);

	protected abstract Connection crearConexion();

	/**
	 * Cierra la conexion
	 * 
	 * @param conn
	 * 
	 */
	protected void cerrarConexion(Connection conn) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(Persistencia.class);
		logueo.setMetodo("cerrarConexion");

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				logueo.setException("SQLException");
				logueo.setError(e.getMessage());
				log.error(logueo.getMensaje(),e);
			} catch (Exception ex) {
				logueo.setException("Exception");
				logueo.setError(ex.getMessage());
				log.error(logueo.getMensaje(),ex);
			}
		}

	}

	protected abstract String obtenerValor(String clave);

	protected void liberarRecursos(Connection connection, Statement statement) {
		liberarRecursos(statement);
		cerrarConexion(connection);

	}
	
	protected void liberarRecursos(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				log.error("No pude cerrar Statement:" + statement, e);
			}
		}
	}
	
	protected void liberarRecursos(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				log.error("No pude cerrar resultSet:" + resultSet, e);
			}
		}
	}

	protected void liberarRecursos(Connection connection, Statement statement, ResultSet resultSet) {
		liberarRecursos(resultSet);
		liberarRecursos(connection, statement);
	}
	

	// UTILITARIOS

	protected void procesarResultados(ResultXmlPL resultado, Logueo logueo, int codError, String descError, String sqlError, Clob clob) {
		if (codError == 0) {
			Herramientas herramientas = new Herramientas();
			resultado.setXml(herramientas.convertirClob(clob));
		} else {
			ServiciosError error = new ServiciosError();
			error.setCodigo(Integer.valueOf(codError));
			error.setDescripcion(descError);

			resultado.setError(error);
			resultado.setHayError(Boolean.TRUE);
			//Tratarlo como error + xml se necesitan los datos y el error
			if (codError<0) {
				log.info("Procesando error y xml: (codigo error negativo):" + codError);
				Herramientas herramientas = new Herramientas();
				resultado.setXml(herramientas.convertirClob(clob));
			}
		}

		logResultados(resultado, logueo, codError, descError, sqlError);
	}

	protected void logResultados(ResultXmlPL resultado, Logueo logueo, int codError, String descError, String sqlError) {
		logueo.setParametro("Codigo salida", codError);
		logueo.setParametro("Descripcion salida", descError);
		logueo.setParametro("Sql salida", sqlError);
		// FIXME VICKY QUITADO EL XML DEL LOG VOLVER A PONER SI ALGO
		logueo.setParametro("Xml", resultado.getXml());
		
		log.info(logueo.getSoloParametros());
	}

	protected void logResultados(Logueo logueo, int codError, String descError, String sqlError) {
		logueo.setParametro("Codigo salida", codError);
		logueo.setParametro("Descripcion salida", descError);
		logueo.setParametro("Sql salida", sqlError);
		log.info(logueo.getSoloParametros());
	}

	protected void catchSQLException(ResultGenerico resultado, Logueo logueo, SQLException e) {
		logueo.setException("SQLException");
		logueo.setError(e.getMessage());
		log.error(logueo.getMensaje(),e);
		resultado.setError(obtenerDescripcionError(Values.SQLEXCEPT));
		resultado.setHayError(Boolean.TRUE);
		enviarMail(logueo);

	}

	protected void catchException(ResultGenerico resultado, Logueo logueo, Exception ex) {
		logueo.setException("Exception");
		logueo.setError(ex.getMessage());
		log.error(logueo.getMensaje(),ex);
		resultado.setError(obtenerDescripcionError(Values.EXCEPTION));
		resultado.setHayError(Boolean.TRUE);
		enviarMail(logueo);
	}

	protected java.sql.Date toSqlDate(java.util.Date date) {
		if (date != null) {
			return new java.sql.Date(date.getTime());
		} else {
			return null;
		}

	}

	protected java.sql.Date toSqlDate(String date) throws ParseException {
		if (date != null) {
			int tieneGuion = date.indexOf('-');
			SimpleDateFormat format = null;
			if (tieneGuion >= 0) {
				format = new SimpleDateFormat("dd-MM-yyyy");
				Date parsed = format.parse(date);
				java.sql.Date sql = new java.sql.Date(parsed.getTime());
				return sql;
			} else {
				format = new SimpleDateFormat("dd/MM/yyyy");
				Date parsed = format.parse(date);
				java.sql.Date sql = new java.sql.Date(parsed.getTime());
				return sql;
			}
		} else {
			return null;
		}
	}

	protected String toString(java.sql.Date date) {
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

			try {
				return format.format(date);
			} catch (Exception e) {
				log.error(e);
			}
		}
		return null;
	}

	protected ServiciosError obtenerDescripcionError(String claveError) {
		return ErrorResolver.getError(claveError);
	}

	private void enviarMail(Logueo logueo) {
		try {
			MailBuilder.create().sentToDefaultConfigureMail(obtenerValorMail("mail.soporte.persistencia"), obtenerValorMail("ccmail.soporte.persistencia"), logueo.getMensaje(),
					"CommonUTIL Error en Persistencia BSE Ambiente: " + InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			log.debug("No se encontro el nombre de Host", e);
		}
	}

	private String obtenerValorMail(String clave) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Correo");
		logueo.setClase(MailBuilder.class);
		logueo.setMetodo("obtenerValorMail");

		String retorno = new String();

		InputStream input = null;
		String filename = "externalConfig.properties";
		Properties prop = new Properties();

		input = MailBuilder.class.getClassLoader().getResourceAsStream(filename);
		if (input == null) {
			logueo.setError("No se pudo encontrar el archivo: " + filename);
			log.error(logueo.getMensaje());
		} else {
			try {
				prop.load(input);
				retorno = prop.getProperty(clave);
			} catch (IOException e) {
				log.error(e.getMessage());
			} catch (Exception ex) {
				log.error(ex.getMessage());
			}
		}

		return retorno;
	}

}
