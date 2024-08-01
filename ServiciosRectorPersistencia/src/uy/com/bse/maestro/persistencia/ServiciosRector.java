package uy.com.bse.maestro.persistencia;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.TimeZone;

import javax.naming.NamingException;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.persistencia.Persistencia;
import uy.com.bse.utilitario.properties.EARPropertiesManager;
import uy.com.bse.utilitario.util.MailBuilder;
import uy.ibm.responseTimeLogger.RTimeLogger;

public class ServiciosRector extends Persistencia {

	protected EARPropertiesManager manager= new EARPropertiesManager("configServiciosRector.properties");
	/**
	 * Crea la conexion
	 * 
	 * @return Un objeto Connection con la información o null en caso de error.
	 */
	public Connection crearConexion() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRector.class);
		logueo.setMetodo("crearConexion");

		javax.naming.Context ic = null;
		Connection conn = null;
		javax.naming.Context ctx = null;
		javax.sql.DataSource ds = null;
		try {
			RTimeLogger.registerStart(getClass());

			TimeZone timeZone = TimeZone.getTimeZone("America/Montevideo");
			TimeZone.setDefault(timeZone);

			ic = new javax.naming.InitialContext();
			ctx = (javax.naming.Context) ic.lookup("java:/");

			String jdni = manager.obtenerValor("serviciosRector.JndiName");
			log.info("- Jdni: " + jdni + " -");

			if (jdni != null) {
				ds = (javax.sql.DataSource) ctx.lookup(jdni);

				conn = (Connection) ds.getConnection();
				/*BEGIN
				DBMS_SESSION.MODIFY_PACKAGE_STATE(DBMS_SESSION.REINITIALIZE);
				END;*/
				log.info("OBTUVE CONEXION BD");
			}
		} catch (NamingException e) {
			logueo.setException("NamingException");
			logueo.setError(e.getMessage());
			log.error(logueo.getMensaje());
			enviarMail( logueo);
		} catch (SQLException e) {
			logueo.setException("SQLException");
			logueo.setError(e.getMessage());
			log.error(logueo.getMensaje());
			enviarMail( logueo);
		} catch (Exception ex) {
			logueo.setException("Exception");
			logueo.setError(ex.getMessage());
			log.error(logueo.getMensaje());
			enviarMail( logueo);
		}

		return conn;
	}
	
	public Integer obtenerCodigoCorredor(ParamGenerico param) {

		Integer resultado = null;
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRector.class);
		logueo.setMetodo("obtenerCodigoCorredor");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		try {
			conn = this.crearConexion();
			logueo.setNombrePl("pac_web_util.FUN_CODIGO_CORREDOR");
			cstmt = conn.prepareCall("{? = call pac_web_util.FUN_CODIGO_CORREDOR(?) }");

			cstmt.registerOutParameter(1, java.sql.Types.INTEGER);
			cstmt.setString(2, param.getUsuario());

			cstmt.execute();

			int retorno = cstmt.getInt(1);
			//FIXME OIGRES TEMA ESTE DE CODIGO DE CORREDOR
			if (retorno > 0) {
				resultado = Integer.valueOf(retorno);
			}

			logueo.setParametro("Retorno Funcion", retorno);
			logResultados(logueo, 0, null, null);

		} catch (SQLException e) {
			log.error(e);
		} catch (Exception ex) {
			log.error(ex);
		} finally {
			liberarRecursos(conn, cstmt);
		}

		return resultado;
	}

	/**
	 * Cierra la conexion
	 * 
	 * @param conn
	 * 
	 */
	@Override
	protected void cerrarConexion(Connection conn) {
		super.cerrarConexion(conn);
		RTimeLogger.registerStop(getClass());
	}

	public String obtenerValor(String clave) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Conexion");
		logueo.setClase(ServiciosRector.class);
		logueo.setMetodo("obtenerValor");

		String retorno = new String();

		InputStream input = null;
		String filename = "database.properties";
		Properties prop = new Properties();

		input = ServiciosRector.class.getClassLoader().getResourceAsStream(filename);
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
	
	private void enviarMail(Logueo logueo) {
		try {
			MailBuilder.create().sentToDefaultConfigureMail(manager.obtenerValor("mail.soporte.persistencia"),manager.obtenerValor("ccmail.soporte.persistencia"),logueo.getMensaje(), "ServiciosRectorPersistencia Error en Persistencia BSE Ambiente: " + InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			log.debug("No se encontro el nombre de Host", e);
		}
	}
}
