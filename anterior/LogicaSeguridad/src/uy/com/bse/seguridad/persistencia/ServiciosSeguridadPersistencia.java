package uy.com.bse.seguridad.persistencia;

import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;
import java.util.TimeZone;

import javax.naming.NamingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.persistencia.Persistencia;
import uy.com.bse.utilitario.properties.EARPropertiesManager;

public class ServiciosSeguridadPersistencia extends Persistencia {
	private static Logger LOG = LogManager.getLogger(ServiciosSeguridadPersistencia.class);
	private EARPropertiesManager manager= new EARPropertiesManager("configSeguridad.properties");
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

			String jdni = manager.obtenerValor("seguridadServicios.JndiName");
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
		String filename = "databaseSeguridad.properties";
		Properties prop = new Properties();

		input = ServiciosSeguridadPersistencia.class.getClassLoader().getResourceAsStream(filename);
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
	
	public void auditarLogin(String appUser, String userName){
		CallableStatement cstmt = null;
		Connection conn = null;

		Logueo logueo = new Logueo();
		logueo.setClase(ServiciosSeguridadPersistencia.class);
		logueo.setMetodo("auditarLogin");

		try {
			conn = crearConexion();
			String nombrePL = obtenerValor("proc_auditaLogin");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?)}");

			cstmt.setString(1,userName);
			cstmt.setString(2,appUser);

			cstmt.execute();


			logResultados(logueo, 0, null, null);
		} catch (SQLException e) {
			
			logueo.setException("SQLException");
			logueo.setError(e.getMessage());
			LOG.error(logueo.getMensaje());
		

		} finally {
			liberarRecursos(conn, cstmt);
		}
		
	}

	public boolean existeUsuarioRector(String userName) {
		boolean salida = false;

		CallableStatement cstmt = null;
		Connection conn = null;

		Logueo logueo = new Logueo();
		logueo.setClase(ServiciosSeguridadPersistencia.class);
		logueo.setMetodo("existeUsuarioRector");
		logueo.setParametro(Values.USUARIO, userName);
		String USER = userName.toUpperCase();

		try {
			conn = crearConexion();
			String nombrePL = obtenerValor("fun_existeUsuarioRector");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{? = call " + nombrePL + "(?)}");

			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setString(2, USER);

			cstmt.execute();

			String usuario = cstmt.getString(1);

			if (usuario != null) {
				salida = true;
			}
			logResultados(logueo, 0, null, null);
		} catch (SQLException e) {
			// FIXME OIGRES... QUE SE HACE EN ESTE CASO SE AVISA A GUSTAVO SI EL
			// TIPO NO TIENE USUARIO RECTOR ??
			logueo.setException("SQLException");
			logueo.setError(e.getMessage());
			LOG.error(logueo.getMensaje());
			//FIXME OIGRES, QUITADO ESTE ENVIO DE MAIL PORQUE NO SE HACE NADA CON EL.
			/*
			 * try {
			 * MailBuilder.create().sentToDefaultConfigureMail(manager.obtenerValor
			 * ("seguridadServicios.mail.soporte"),manager.obtenerValor(
			 * "seguridadServicios.ccmail.soporte"),logueo.getMensaje(),
			 * "ServicioSeguridadPersistencia Error en Persistencia BSE Ambiente: "
			 * +InetAddress.getLocalHost().getHostName() ); } catch
			 * (UnknownHostException e1) {
			 * log.debug("No se encontro el host para el titulo del mail ",e1);
			 * }
			 */

			String msg = "No se ha encontrado datos para : " + userName;
			LOG.error(msg);
		} finally {
			LOG.debug("salida :" + salida);
			liberarRecursos(conn, cstmt);
		}
		return salida;

	}

	public ResultXmlPL removeRow(String token) {
		return NotImplementedMethod();
	}

	public ResultXmlPL getRow(String token) {
		return NotImplementedMethod();
	}

	public ResultXmlPL createTokenRow(String token, String sessionID, String userName) {
		return NotImplementedMethod();
	}

	private ResultXmlPL NotImplementedMethod() {
		ResultXmlPL row = new ResultXmlPL();
		ServiciosError error = new ServiciosError();
		error.setCodigo(Integer.valueOf(500));
		error.setDescripcion("NO SE HA IMPLEMENTADO EL PL");
		row.setError(error);
		row.setHayError(Boolean.TRUE);
		return row;
	}
}
