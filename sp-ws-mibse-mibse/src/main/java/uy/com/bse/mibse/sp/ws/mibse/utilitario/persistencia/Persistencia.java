package uy.com.bse.mibse.sp.ws.mibse.utilitario.persistencia;

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
import org.springframework.beans.factory.annotation.Autowired;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultXmlPL;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultGenerico;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ServiciosError;
import org.apache.catalina.valves.ErrorReportValve;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.exception.Values;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.log.Logueo;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.util.Herramientas;
//import uy.com.bse.mibse.sp.ws.mibse.utilitario.util.MailBuilder;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.exception.ErrorResolver;
import org.springframework.stereotype.Component;

@Component
public class Persistencia {

	private final Herramientas herramientas;
	private final Logueo logueo;

	@Autowired
	public Persistencia(Herramientas herramientas, Logueo logueo) {
		this.herramientas = herramientas;
		this.logueo = logueo;
	}

	protected static Logger log = LogManager.getLogger(Persistencia.class);

	public void procesarResultados(ResultXmlPL resultado, Logueo logueo, int codError, String descError, String sqlError, Clob clob) {
		if (codError == 0) {
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
				resultado.setXml(herramientas.convertirClob(clob));
			}
		}

		logResultados(resultado, logueo, codError, descError, sqlError);
	}

	public void logResultados(ResultXmlPL resultado, Logueo logueo, int codError, String descError, String sqlError) {
		logueo.setParametro("Codigo salida", codError);
		logueo.setParametro("Descripcion salida", descError);
		logueo.setParametro("Sql salida", sqlError);
		// FIXME VICKY QUITADO EL XML DEL LOG VOLVER A PONER SI ALGO
		logueo.setParametro("Xml", resultado.getXml());
		
		log.info(logueo.getSoloParametros());
	}

	public void logResultados(Logueo logueo, int codError, String descError, String sqlError) {
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

	public void catchException(ResultGenerico resultado, Logueo logueo, Exception ex) {
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
		/*try {
			MailBuilder.create().sentToDefaultConfigureMail(obtenerValorMail("mail.soporte.persistencia"), obtenerValorMail("ccmail.soporte.persistencia"), logueo.getMensaje(),
					"CommonUTIL Error en Persistencia BSE Ambiente: " + InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			log.debug("No se encontro el nombre de Host", e);
		}*/
	}
/*
	private String obtenerValorMail(String clave) {

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
	}*/

}
