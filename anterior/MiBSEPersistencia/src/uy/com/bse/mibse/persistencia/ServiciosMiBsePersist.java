package uy.com.bse.mibse.persistencia;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

import javax.naming.NamingException;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.mibse.ClientePago;
import uy.com.bse.mibse.ClientePagoBici;
import uy.com.bse.mibse.ClientePagoEDeportivas;
import uy.com.bse.mibse.ClientePagoIndivi;
import uy.com.bse.mibse.ClientePagoOPersonal;
import uy.com.bse.mibse.ClientePagoSOA;
import uy.com.bse.mibse.ClientePagoViajero;
import uy.com.bse.mibse.ParamActualizarComunicacionCliente;
import uy.com.bse.mibse.ParamActualizarDatosCliente;
import uy.com.bse.mibse.ParamActualizarFacturacionPoliza;
import uy.com.bse.mibse.ParamAdherirFacturaDigital;
import uy.com.bse.mibse.ParamAltaMailCliente;
import uy.com.bse.mibse.ParamBorrarMailCliente;
import uy.com.bse.mibse.ParamCorrespondeCartaPoliza;
import uy.com.bse.mibse.ParamInformarPagoRedes;
import uy.com.bse.mibse.ParamInformarPagoTienda;
import uy.com.bse.mibse.ParamListaProfesiones;
import uy.com.bse.mibse.ParamListaTipoDocumentos;
import uy.com.bse.mibse.ParamLogActividadMibseWsExt;
import uy.com.bse.mibse.ParamModificarMailCliente;
import uy.com.bse.mibse.ParamObtenerComunicacionesCliente;
import uy.com.bse.mibse.ParamObtenerDatosCliente;
import uy.com.bse.mibse.ParamObtenerDatosValidadosCliente;
import uy.com.bse.mibse.ParamObtenerMailCliente;
import uy.com.bse.mibse.ParamObtenerMailsEnvioFacturaCliente;
import uy.com.bse.mibse.ParamObtenerMapaMsgSiniestro;
import uy.com.bse.mibse.ParamObtenerMaximoEndoso;
import uy.com.bse.mibse.ParamObtenerNumeroCliente;
import uy.com.bse.mibse.ParamObtenerPolizasCliente;
import uy.com.bse.mibse.ParamObtenerPolizasFacturasPagasCliente;
import uy.com.bse.mibse.ParamProCarta;
import uy.com.bse.mibse.ParamProCarta2;
import uy.com.bse.mibse.ParamSubirArchivo;
import uy.com.bse.mibse.ParamValidacionCartaVerde;
import uy.com.bse.mibse.ParamValidacionSOA;
import uy.com.bse.mibse.ParamValidarCertificadoLibreDeudaADT;
import uy.com.bse.mibse.ParamValidarCodigoAdhesion;
import uy.com.bse.mibse.ResultAdherirFacturaDigital;
import uy.com.bse.mibse.ResultClientePago;
import uy.com.bse.mibse.ResultCorrespondeCartaPoliza;
import uy.com.bse.mibse.ResultLogActividadMibseWsExt;
import uy.com.bse.mibse.ResultObtenerDatosValidadosCliente;
import uy.com.bse.mibse.ResultObtenerMailCliente;
import uy.com.bse.mibse.ResultObtenerMailsEnvioFacturaCliente;
import uy.com.bse.mibse.ResultObtenerMaximoEndoso;
import uy.com.bse.mibse.ResultObtenerNumeroCliente;
import uy.com.bse.mibse.ResultProCarta;
import uy.com.bse.mibse.ResultSubirArchivo;
import uy.com.bse.mibse.ResultValidacionCartaVerde;
import uy.com.bse.mibse.ResultValidacionSOA;
import uy.com.bse.mibse.ResultValidarCertificadoLibreDeudaADT;
import uy.com.bse.mibse.ResultValidarCodigoAdhesion;
import uy.com.bse.utilitario.dato.Codiguera;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultCodiguera;
import uy.com.bse.utilitario.dato.ResultCondicion;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.persistencia.Persistencia;
import uy.com.bse.utilitario.properties.EARPropertiesManager;
import uy.com.bse.utilitario.util.MailBuilder;

public class ServiciosMiBsePersist extends Persistencia {

	private static Logger LOG = LogManager.getLogger(ServiciosMiBsePersist.class);
	EARPropertiesManager manager = new EARPropertiesManager("configMibse.properties");

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

			String jdni = manager.obtenerValor("mibse.JndiName");
			LOG.info("- Jdni: " + jdni + " -");

			if (jdni != null) {
				ds = (javax.sql.DataSource) ctx.lookup(jdni);

				conn = (Connection) ds.getConnection();

			}
		} catch (NamingException e) {
			logueo.setException("NamingException");
			logueo.setError(e.getMessage());
			LOG.error(logueo.getMensaje());
			enviarMail(logueo);
		} catch (SQLException e) {
			logueo.setException("SQLException");
			logueo.setError(e.getMessage());
			LOG.error(logueo.getMensaje());
			enviarMail(logueo);
		} catch (Exception ex) {
			logueo.setException("Exception");
			logueo.setError(ex.getMessage());
			LOG.error(logueo.getMensaje());
			enviarMail(logueo);
		}

		return conn;
	}

	private void enviarMail(Logueo logueo) {
		try {
			MailBuilder.create().sentToDefaultConfigureMail(manager.obtenerValor("mail.soporte.persistencia"),
					manager.obtenerValor("ccmail.soporte.persistencia"), logueo.getMensaje(),
					"ServiciosMiBsePersist Error en Persistencia BSE Ambiente: "
							+ InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			log.debug("No se encontro el nombre de Host", e);
		}
	}

	@Override
	protected String obtenerValor(String clave) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(Persistencia.class);
		logueo.setMetodo("obtenerValor");

		String retorno = new String();

		InputStream input = null;
		String filename = "databaseMiBSE.properties";
		Properties prop = new Properties();

		input = ServiciosMiBsePersist.class.getClassLoader().getResourceAsStream(filename);
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

	public ResultGenerico actualizarFacturacionPoliza(ParamActualizarFacturacionPoliza param) {
		ResultGenerico resultado = new ResultGenerico();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("actualizarFacturacionPoliza");

		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Validada", param.getCodMarca());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_actualizarFacturacionPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.setInt(1, param.getCodRamo());
			cstmt.setInt(2, param.getNumPoliza());
			if (param.getCodMarca().booleanValue()) {
				cstmt.setString(3, "S");
			} else {
				cstmt.setString(3, "N");
			}
			cstmt.setString(4, param.getUsuario());
			cstmt.registerOutParameter(5, Types.INTEGER);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.execute();

			int codError = cstmt.getInt(5);
			String descError = cstmt.getString(6);
			String sqlError = cstmt.getString(7);
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

	public ResultXmlPL obtenerDatosCliente(ParamObtenerDatosCliente param) {

		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("obtenerDatosCliente");

		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerDatosClienteMiBSE");
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

	public ResultXmlPL obtenerPolizasCliente(ParamObtenerPolizasCliente param) {

		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("obtenerPolizasCliente");

		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerPolizasMiBSE");
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

	public ResultValidarCodigoAdhesion validarCodigoAdhesion(ParamValidarCodigoAdhesion param) {
		ResultValidarCodigoAdhesion resultado = new ResultValidarCodigoAdhesion();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("validarCodigoAdhesion");

		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());
		logueo.setParametro("Codigo Adhesion ", param.getCodAdhesion());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("fun_validarCodigoAdhesion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{? = call " + nombrePL + "(?,?,?,?,?)}");

			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);

			cstmt.setString(2, param.getUsuario());
			cstmt.setString(3, param.getCodAdhesion());

			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);

			cstmt.execute();

			int codError = cstmt.getInt(4);
			String descError = cstmt.getString(5);
			String sqlError = cstmt.getString(6);

			if (codError == 0) {
				String res = cstmt.getString(1);
				resultado.setValidado(res);
				resultado.setHayError(Boolean.FALSE);
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

	public ResultObtenerNumeroCliente obtenerNumeroCliente(ParamObtenerNumeroCliente param) {
		ResultObtenerNumeroCliente resultado = new ResultObtenerNumeroCliente();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("obtenerNumeroCliente");

		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("fun_obtenerNumeroCliente");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{? = call " + nombrePL + "(?,?,?,?)}");

			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);

			cstmt.setString(2, param.getUsuario());

			cstmt.registerOutParameter(3, Types.INTEGER);
			cstmt.registerOutParameter(4, Types.VARCHAR);
			cstmt.registerOutParameter(5, Types.VARCHAR);

			cstmt.execute();

			int codError = cstmt.getInt(3);
			String descError = cstmt.getString(4);
			String sqlError = cstmt.getString(5);

			if (codError == 0) {
				String res = cstmt.getString(1);
				resultado.setNumCliente(res);
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

	public ResultGenerico actualizarComunicacionCliente(ParamActualizarComunicacionCliente param) {

		ResultGenerico resultado = new ResultGenerico();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("actualizarComunicacionesCliente");

		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());
		logueo.setParametro("Codigo Comunicacion", param.getComunicacion().getCodComunicacion());
		logueo.setParametro("Tipo Comunicacion", param.getComunicacion().getTipoComunicacion());
		logueo.setParametro("Valor Comunicacion", param.getComunicacion().getValorComunicacion());
		logueo.setParametro("Validada Comunicacion", param.getComunicacion().getValidada());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_actualizarDatosComunicaionesPersona");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");
			if (param.getComunicacion().getCodComunicacion() != null) {
				cstmt.setInt(1, param.getComunicacion().getCodComunicacion());
			} else {
				cstmt.setNull(1, Types.INTEGER);
			}
			cstmt.setInt(2, param.getComunicacion().getTipoComunicacion());
			cstmt.setString(3, param.getComunicacion().getValorComunicacion());
			if (param.getComunicacion().getValidada().equalsIgnoreCase("S")) {
				cstmt.setString(4, "S");
			} else {
				cstmt.setString(4, "N");
			}

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

	public ResultGenerico actualizarDatosCliente(ParamActualizarDatosCliente param) {

		ResultGenerico resultado = new ResultGenerico();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("actualizarDatosCliente");

		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());
		logueo.setParametro("Numero persona", param.getNumPersona());
		logueo.setParametro("Fecha nacimiento", param.getFechaNacimiento());
		logueo.setParametro("Sexo", param.getSexo());
		logueo.setParametro("Codigo Profesion", param.getCodProfesion());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_actualizarDatosCliente");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");

			if (param.getFechaNacimiento() != null) {
				SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				Date parsed = format.parse(param.getFechaNacimiento());
				java.sql.Date sql = new java.sql.Date(parsed.getTime());
				cstmt.setDate(1, sql);
			} else {
				cstmt.setNull(1, Types.DATE);
			}

			cstmt.setString(2, param.getSexo());

			if (param.getCodProfesion() != null) {
				cstmt.setInt(3, Integer.valueOf(param.getCodProfesion()));
			} else {
				cstmt.setNull(3, Types.INTEGER);
			}

			cstmt.setString(4, param.getUsuario());

			cstmt.registerOutParameter(5, Types.INTEGER);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.execute();

			int codError = cstmt.getInt(5);
			String descError = cstmt.getString(6);
			String sqlError = cstmt.getString(7);
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

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public ResultXmlPL obtenerComunicacionesCliente(ParamObtenerComunicacionesCliente param) {
		ResultXmlPL resultado = new ResultXmlPL();
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("obtenerComunicacionesCliente");

		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());

		try {
			String nombrePL = obtenerValor("proc_obtenerDatosComunicacionesPersona");
			logueo.setNombrePl(nombrePL);

			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withProcedureName(nombrePL)
					.declareParameters(
							new SqlParameter("p_usuario", Types.VARCHAR),
							new SqlOutParameter("p_codError", Types.INTEGER),
							new SqlOutParameter("p_descError", Types.VARCHAR),
							new SqlOutParameter("p_sqlError", Types.VARCHAR),
							new SqlOutParameter("p_datosComunicaciones", Types.CLOB)
					);

			SqlParameterSource in = new MapSqlParameterSource()
					.addValue("p_usuario", param.getUsuario());

			Map<String, Object> out = simpleJdbcCall.execute(in);

			int codError = (int) out.get("p_codError");
			String descError = (String) out.get("p_descError");
			String sqlError = (String) out.get("p_sqlError");
			Clob clob = (Clob) out.get("p_datosComunicaciones");
			procesarResultados(resultado, logueo, codError, descError, sqlError, clob);
		} catch (Exception e) {
			catchException(resultado, logueo, e);
		}
		return resultado;
	}

	public ResultCodiguera listaProfesiones(ParamListaProfesiones param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("listaProfesiones");
		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());

		ResultCodiguera resultado = new ResultCodiguera();

		try {
			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
					.withProcedureName("proc_listaProfesiones")
					.declareParameters(
							new SqlParameter("p_usuario", Types.VARCHAR),
							new SqlOutParameter("p_codError", Types.INTEGER),
							new SqlOutParameter("p_descError", Types.VARCHAR),
							new SqlOutParameter("p_sqlError", Types.VARCHAR),
							new SqlOutParameter("p_datosProfesiones", Types.CLOB)
					);

			SqlParameterSource in = new MapSqlParameterSource()
					.addValue("p_usuario", param.getUsuario());

			Map<String, Object> out = simpleJdbcCall.execute(in);

			int codError = (int) out.get("p_codError");
			String descError = (String) out.get("p_descError");
			String sqlError = (String) out.get("p_sqlError");
			Clob clob = (Clob) out.get("p_datosProfesiones");

			if (codError == 0) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(clob.getAsciiStream()));
				String line;
				while ((line = reader.readLine()) != null) {
					Codiguera dato = new Codiguera();
					String[] parts = line.split(",");
					dato.setCodigo(parts[0]);
					dato.setDescripcion(parts[1]);
					resultado.setUno(dato);
				}
			} else {
				catchException(resultado, logueo, new Exception("Error al obtener lista de profesiones: " + descError));
			}

		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		}
		return resultado;
		} finally {
			liberarRecursos(conn, pst, result);
		}
		return resultado;
	}

	public ResultCodiguera listaTipoDocumentos(ParamListaTipoDocumentos param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("listaTipoDocumentos");
		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultCodiguera resultado = new ResultCodiguera();

		try {
			conn = crearConexion();

			String sql = "SELECT CATU_TP_DOCUMENTO TP_DOCUMENTO, CATU_DE_DOCUMENTO DESC_DOCUMENTO FROM CART_TIPOS_DOCUMENTOS WHERE CATU_TP_DOCUMENTO IN ('CI', 'CIA', 'CIB', 'PAS') UNION SELECT 'RUT', 'RUT PERSONA JUR�DICA' FROM DUAL";

			logueo.setParametro("Consulta", sql);
			pst = conn.prepareStatement(sql);

			result = pst.executeQuery();

			while (result.next()) {
				Codiguera dato = new Codiguera();
				dato.setCodigo(result.getString("TP_DOCUMENTO"));
				dato.setDescripcion(result.getString("DESC_DOCUMENTO"));
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

	public ResultObtenerMailCliente obtenerMailCliente(ParamObtenerMailCliente param) {
		ResultObtenerMailCliente resultado = new ResultObtenerMailCliente();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("obtenerMailCliente");
		logueo.setParametro("Usuario", param.getUsuario());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("fun_obtenerMail");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{? = call " + nombrePL + "(?,?,?,?)}");

			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);

			cstmt.setString(2, param.getUsuario());

			cstmt.registerOutParameter(3, Types.INTEGER);
			cstmt.registerOutParameter(4, Types.VARCHAR);
			cstmt.registerOutParameter(5, Types.VARCHAR);

			cstmt.execute();

			int codError = cstmt.getInt(3);
			String descError = cstmt.getString(4);
			String sqlError = cstmt.getString(5);

			if (codError == 0) {
				String res = cstmt.getString(1);
				resultado.setMail(res);
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

	public ResultObtenerDatosValidadosCliente obtenerDatosValidadosCliente(ParamObtenerDatosValidadosCliente param) {
		ResultObtenerDatosValidadosCliente resultado = new ResultObtenerDatosValidadosCliente();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("obtenerMailCliente");
		logueo.setParametro("Usuario", param.getUsuario());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("fun_obtenerDatosValidados");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{? = call " + nombrePL + "(?,?,?,?)}");

			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);

			cstmt.setString(2, param.getUsuario());

			cstmt.registerOutParameter(3, Types.INTEGER);
			cstmt.registerOutParameter(4, Types.VARCHAR);
			cstmt.registerOutParameter(5, Types.VARCHAR);

			cstmt.execute();

			int codError = cstmt.getInt(3);
			String descError = cstmt.getString(4);
			String sqlError = cstmt.getString(5);

			if (codError == 0) {
				String res = cstmt.getString(1);
				resultado.setValidado(res);
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

	public ResultCondicion verificaDuplicidadUsuario(ParamGenerico param) {
		ResultCondicion resultado = new ResultCondicion();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("verificaDuplicidadUsuario");

		logueo.setParametro("Usuario", param.getUsuario());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("fun_verificarUsuarioDuplicado");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{? = call " + nombrePL + "(?,?,?,?)}");

			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);

			cstmt.setString(2, param.getUsuario());

			cstmt.registerOutParameter(3, Types.INTEGER);
			cstmt.registerOutParameter(4, Types.VARCHAR);
			cstmt.registerOutParameter(5, Types.VARCHAR);

			cstmt.execute();

			int codError = cstmt.getInt(3);
			String descError = cstmt.getString(4);
			String sqlError = cstmt.getString(5);

			if (codError == 0) {
				String res = cstmt.getString(1);
				resultado.setResultadoString(res);
			} else {
				ServiciosError error = new ServiciosError();
				error.setCodigo(Integer.valueOf(codError));
				error.setDescripcion(descError);
				String res = cstmt.getString(1);
				resultado.setResultadoString(res);
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

	public ResultClientePago obtenerClientePagoSOA(ParamInformarPagoTienda param) {
		final Integer ENDOSO = 0; // El endoso es siempre 0 luego de emitir la cotizaci�n
		final Integer DATO_MATRICULA = 141001;
		final Integer DATO_CHASIS = 141004;
		Connection conn = null;
		ResultClientePago resultado = new ResultClientePago();
		
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("obtenerClientePagoSOA");
		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());
		logueo.setParametro("idTransaccion", param.getIdTransaccion());

		try {
			conn = crearConexion();

			ClientePagoSOA clientePagoSOA = new ClientePagoSOA();
			copyParam(clientePagoSOA, param);
			clientePagoSOA.setSucursal(obtenerSucursal(conn, param.getCodRamo(), param.getNumPoliza()));
			clientePagoSOA.setNroEndoso(ENDOSO);
			clientePagoSOA.setNroSecuenciaPoliza(obtenerNroSecuenciaPoliza(conn));
			clientePagoSOA.setMail(obtenerMail(conn, crearEstructuraUsuario(param.getTipoDocumento(), param.getNroDocumento())));
			clientePagoSOA.setChasis(obtenerDatoProducto(conn, clientePagoSOA.getSucursal(), param.getCodRamo(), param.getNumPoliza(), DATO_CHASIS));
			clientePagoSOA.setMatricula(obtenerDatoProducto(conn, clientePagoSOA.getSucursal(), param.getCodRamo(), param.getNumPoliza(), DATO_MATRICULA));

			if (!(param instanceof ParamInformarPagoRedes)) {
					validacionSOA(conn, clientePagoSOA.getCodRamo(), clientePagoSOA.getNumPoliza(),
							clientePagoSOA.getCertificado(), clientePagoSOA.getTipoDocumento(),
							clientePagoSOA.getNroDocumento());
			}
			resultado.setClientePago(clientePagoSOA);
			LOG.info(logueo.getSoloParametros());
			
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			cerrarConexion(conn);
		}

		return resultado;
	}

	public ResultClientePago obtenerClientePagoViajero(ParamInformarPagoTienda param) {
		final Integer ENDOSO = 0; // El endoso es siempre 0 luego de emitir la cotizaci�n
		Connection conn = null;
		ResultClientePago resultado = new ResultClientePago();
		
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("obtenerClientePagoViajero");
		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());
		logueo.setParametro("idTransaccion", param.getIdTransaccion());

		try {
			conn = crearConexion();

			ClientePagoViajero clientePagoViajero = new ClientePagoViajero();
			copyParam(clientePagoViajero, param);
			clientePagoViajero.setSucursal(obtenerSucursal(conn, param.getCodRamo(), param.getNumPoliza()));
			clientePagoViajero.setNroEndoso(ENDOSO);
			clientePagoViajero.setNroSecuenciaPoliza(obtenerNroSecuenciaPoliza(conn));
			clientePagoViajero.setMail(obtenerMail(conn, crearEstructuraUsuario(param.getTipoDocumento(), param.getNroDocumento())));
			resultado.setClientePago(clientePagoViajero);
			LOG.info(logueo.getSoloParametros());
			
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			cerrarConexion(conn);
		}

		return resultado;
	}
	
	public ResultClientePago obtenerClientePagoEDeportivas(ParamInformarPagoTienda param) {
		final Integer ENDOSO = 0; // El endoso es siempre 0 luego de emitir la cotizaci�n
		final Integer DATO_MATRICULA = 151053;
		final Integer DATO_NOMBRE = 151052;
		Connection conn = null;
		ResultClientePago resultado = new ResultClientePago();
		
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("obtenerClientePagoEDeportivas");
		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());
		logueo.setParametro("idTransaccion", param.getIdTransaccion());

		try {
			conn = crearConexion();

			ClientePagoEDeportivas clientePagoEDeportivas = new ClientePagoEDeportivas();
			copyParam(clientePagoEDeportivas, param);
			clientePagoEDeportivas.setSucursal(obtenerSucursal(conn, param.getCodRamo(), param.getNumPoliza()));
			clientePagoEDeportivas.setNroEndoso(ENDOSO);
			clientePagoEDeportivas.setNroSecuenciaPoliza(obtenerNroSecuenciaPoliza(conn));
			clientePagoEDeportivas.setMail(obtenerMail(conn, crearEstructuraUsuario(param.getTipoDocumento(), param.getNroDocumento())));
			clientePagoEDeportivas.setNombre(obtenerDatoProducto(conn, clientePagoEDeportivas.getSucursal(), param.getCodRamo(), param.getNumPoliza(), DATO_NOMBRE));
			clientePagoEDeportivas.setMatricula(obtenerDatoProducto(conn, clientePagoEDeportivas.getSucursal(), param.getCodRamo(), param.getNumPoliza(), DATO_MATRICULA));
			resultado.setClientePago(clientePagoEDeportivas);
			LOG.info(logueo.getSoloParametros());
			
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			cerrarConexion(conn);
		}

		return resultado;
	}

	public ResultClientePago obtenerClientePagoBici(ParamInformarPagoTienda param) {
		final Integer ENDOSO = 0; // El endoso es siempre 0 luego de emitir la cotizaci�n
		final Integer DATO_MARCA = 171018;
		final Integer DATO_NOMBRE = 151052;
		Connection conn = null;
		ResultClientePago resultado = new ResultClientePago();
		
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("obtenerClientePagoBici");
		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());
		logueo.setParametro("idTransaccion", param.getIdTransaccion());

		try {
			conn = crearConexion();

			ClientePagoBici clientePagoBici = new ClientePagoBici();
			copyParam(clientePagoBici, param);
			clientePagoBici.setSucursal(obtenerSucursal(conn, param.getCodRamo(), param.getNumPoliza()));
			clientePagoBici.setNroEndoso(ENDOSO);
			clientePagoBici.setNroSecuenciaPoliza(obtenerNroSecuenciaPoliza(conn));
			clientePagoBici.setMail(obtenerMail(conn, crearEstructuraUsuario(param.getTipoDocumento(), param.getNroDocumento())));
			clientePagoBici.setNombre(obtenerDatoProducto(conn, clientePagoBici.getSucursal(), param.getCodRamo(), param.getNumPoliza(), DATO_NOMBRE));
			clientePagoBici.setMarca(obtenerDatoProducto(conn, clientePagoBici.getSucursal(), param.getCodRamo(), param.getNumPoliza(), DATO_MARCA));
			resultado.setClientePago(clientePagoBici);
			LOG.info(logueo.getSoloParametros());
			
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			cerrarConexion(conn);
		}

		return resultado;
	}

	public ResultClientePago obtenerClientePagoIndivi(ParamInformarPagoTienda param) {
		final Integer ENDOSO = 0; // El endoso es siempre 0 luego de emitir la cotizaci�n
		final Integer DATO_MOTOR = 141003;
		final Integer DATO_CHASIS = 141004;
		Connection conn = null;
		ResultClientePago resultado = new ResultClientePago();
		
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("obtenerClientePagoIndivi");
		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());
		logueo.setParametro("idTransaccion", param.getIdTransaccion());

		try {
			conn = crearConexion();

			ClientePagoIndivi clientePagoIndivi = new ClientePagoIndivi();
			copyParam(clientePagoIndivi, param);
			clientePagoIndivi.setSucursal(obtenerSucursal(conn, param.getCodRamo(), param.getNumPoliza()));
			clientePagoIndivi.setNroEndoso(ENDOSO);
			clientePagoIndivi.setNroSecuenciaPoliza(obtenerNroSecuenciaPoliza(conn));
			clientePagoIndivi.setMail(obtenerMail(conn, crearEstructuraUsuario(param.getTipoDocumento(), param.getNroDocumento())));
			clientePagoIndivi.setChasis(obtenerDatoProducto(conn, clientePagoIndivi.getSucursal(), param.getCodRamo(), param.getNumPoliza(), DATO_CHASIS));
			clientePagoIndivi.setMotor(obtenerDatoProducto(conn, clientePagoIndivi.getSucursal(), param.getCodRamo(), param.getNumPoliza(), DATO_MOTOR));
			resultado.setClientePago(clientePagoIndivi);
			LOG.info(logueo.getSoloParametros());
			
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			cerrarConexion(conn);
		}

		return resultado;
	}
	
	public ResultClientePago obtenerClientePagoOPersonal(ParamInformarPagoTienda param) {
		final Integer ENDOSO = 0; // El endoso es siempre 0 luego de emitir la cotizaci�n
		final Integer DATO_MARCA = 171011;
		final Integer DATO_SERIE = 171012;
		final Integer DATO_NOMBRE = 151052;
		Connection conn = null;
		ResultClientePago resultado = new ResultClientePago();
		
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("obtenerClientePagoOPersonal");
		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());
		logueo.setParametro("idTransaccion", param.getIdTransaccion());

		try {
			conn = crearConexion();

			ClientePagoOPersonal clientePagoOPersonal = new ClientePagoOPersonal();
			copyParam(clientePagoOPersonal, param);
			clientePagoOPersonal.setSucursal(obtenerSucursal(conn, param.getCodRamo(), param.getNumPoliza()));
			clientePagoOPersonal.setNroEndoso(ENDOSO);
			clientePagoOPersonal.setNroSecuenciaPoliza(obtenerNroSecuenciaPoliza(conn));
			clientePagoOPersonal.setMail(obtenerMail(conn, crearEstructuraUsuario(param.getTipoDocumento(), param.getNroDocumento())));
			clientePagoOPersonal.setNombre(obtenerDatoProducto(conn, clientePagoOPersonal.getSucursal(), param.getCodRamo(), param.getNumPoliza(), DATO_NOMBRE));
			clientePagoOPersonal.setMarca(obtenerDatoProducto(conn, clientePagoOPersonal.getSucursal(), param.getCodRamo(), param.getNumPoliza(), DATO_MARCA));
			clientePagoOPersonal.setSerie(obtenerDatoProducto(conn, clientePagoOPersonal.getSucursal(), param.getCodRamo(), param.getNumPoliza(), DATO_SERIE));
			resultado.setClientePago(clientePagoOPersonal);
			LOG.info(logueo.getSoloParametros());
			
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			cerrarConexion(conn);
		}

		return resultado;
	}


	public ResultGenerico gentOrdenes(Long secuencia, String parametro, String valor, Integer orden, Integer subOrden) {
		ResultGenerico resultado = new ResultGenerico();
		CallableStatement cstmt = null;
		Connection conn = null;
		String nombrePL = obtenerValor("proc_gentOrdenes");
		
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("gentOrdenes");
		logueo.setParametro("secuencia", secuencia);
		logueo.setParametro("parametro", parametro);
		logueo.setParametro("valor", valor);
		logueo.setParametro("orden", orden);
		logueo.setParametro("subOrden", subOrden);
		logueo.setNombrePl(nombrePL);
		
		try {
			conn = this.crearConexion();
			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?)}");
			cstmt.setLong(1, secuencia);
			cstmt.setString(2, parametro);
			cstmt.setString(3, valor);
			cstmt.setInt(4, orden);
			cstmt.setInt(5, subOrden);
			
			cstmt.executeUpdate();
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

	private String crearEstructuraUsuario(String tipoDocumento, String numDocumento) {
		StringBuilder sb = new StringBuilder();
		sb.append(tipoDocumento);
		sb.append("#");
		sb.append(numDocumento);
		return sb.toString();
	}

	private void copyParam(ClientePago clientePago, ParamInformarPagoTienda param) {
		clientePago.setIdTransaccion(param.getIdTransaccion());
		clientePago.setCodProducto(param.getCodProducto());
		clientePago.setDescProducto(param.getDescProducto());
		clientePago.setCodRamo(param.getCodRamo());
		clientePago.setDescRamo(param.getDescRamo());
		clientePago.setNumPoliza(param.getNumPoliza());
		clientePago.setCertificado(param.getCertificado());
		clientePago.setDocumentId(param.getDocumentId());
		clientePago.setTipoDocumento(param.getTipoDocumento());
		clientePago.setNroDocumento(param.getNroDocumento());
		
		String nombreAsegurado = "";
		String apellidos = param.getApellidos();
		String nombres = param.getNombres();
		if (apellidos != null && apellidos.length() > 0) {
			nombreAsegurado = apellidos;
		}
		if (nombres != null && nombres.length() > 0) {
			if (nombreAsegurado.length() > 0) {
				nombreAsegurado = nombreAsegurado + " " + nombres;
			} else {
				nombreAsegurado = nombres;
			}
		}
		clientePago.setNombreCompleto(nombreAsegurado);
		clientePago.setCodTipoMedioDePago(param instanceof ParamInformarPagoRedes ? ClientePago.EnumTipoMedioDePago.RED : ClientePago.EnumTipoMedioDePago.DEBITO);
	}
	
	private Integer obtenerSucursal(Connection conn, Integer codRamo, Integer numPoliza) throws Exception {
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		Integer sucursal = 0;
		String sql = "SELECT CAPO_CASU_CD_SUCURSAL FROM CART_POLIZAS WHERE CAPO_CARP_CD_RAMO = ? AND CAPO_NU_POLIZA = ?";
		
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("obtenerSucursal");
		logueo.setParametro("codRamo", codRamo);
		logueo.setParametro("numPoliza", numPoliza);
		logueo.setParametro("ConsultaSQL", sql);
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, codRamo);
			pst.setInt(2, numPoliza);
			
			resultSet = pst.executeQuery();
			while (resultSet.next()) {
				sucursal = resultSet.getInt("CAPO_CASU_CD_SUCURSAL");
			}
			logueo.setParametro("Valor salida", sucursal);
			LOG.info(logueo.getSoloParametros());
			
		} catch (Exception e) {
			logueo.setException("Exception");
			logueo.setError(e.getMessage());
			log.error(logueo.getMensaje(), e);
			throw new Exception(e);		
		} finally {
			liberarRecursos(pst);
			liberarRecursos(resultSet);
		}
		
		return sucursal;
	}
	
	private Long obtenerNroSecuenciaPoliza(Connection conn) throws Exception {
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		Long nroSecuenciaPoliza = 0L;
		String sql = "SELECT CARS_SECUENCIA.NEXTVAL FROM DUAL";

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("obtenerNroSecuenciaPoliza");
		logueo.setParametro("ConsultaSQL", sql);
		
		try {
			pst = conn.prepareStatement(sql);
	
			resultSet = pst.executeQuery();
			while (resultSet.next()) {
				nroSecuenciaPoliza = resultSet.getLong("NEXTVAL");
			}
			logueo.setParametro("Valor salida", nroSecuenciaPoliza);
			LOG.info(logueo.getSoloParametros());
			
		} catch (Exception e) {
			logueo.setException("Exception");
			logueo.setError(e.getMessage());
			log.error(logueo.getMensaje(), e);
			throw new Exception(e);		
		} finally {
			liberarRecursos(pst);
			liberarRecursos(resultSet);
		}
		
		return nroSecuenciaPoliza;
	}

	private String obtenerMail(Connection conn, String usuario) throws Exception {
		String mail = "";
		CallableStatement cstmt = null;
		String nombrePL = obtenerValor("fun_obtenerMail");
		
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("obtenerMail");
		logueo.setParametro("usuario", usuario);
		logueo.setNombrePl(nombrePL);
		
		try {
			cstmt = conn.prepareCall("{? = call " + nombrePL + "(?,?,?,?)}");
	
			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
	
			cstmt.setString(2, usuario);
	
			cstmt.registerOutParameter(3, Types.INTEGER);
			cstmt.registerOutParameter(4, Types.VARCHAR);
			cstmt.registerOutParameter(5, Types.VARCHAR);
	
			cstmt.execute();
	
			int codError = cstmt.getInt(3);
			String descError = cstmt.getString(4);
			String sqlError = cstmt.getString(5);
	
			if (codError == 0) {
				mail = cstmt.getString(1);
			} else {
				throw new Exception("** obtenerMail ** codigo de error: " + codError + ", descripcion: " + descError + ", SQLError: " + sqlError);
			}
			logueo.setParametro("Valor salida", mail);
			logResultados(logueo, codError, descError, sqlError);
			
		} catch (Exception e) {
			logueo.setException("Exception");
			logueo.setError(e.getMessage());
			log.error(logueo.getMensaje(), e);
			throw new Exception(e);		
		} finally {
			liberarRecursos(cstmt);
		}
		return mail;
	}
	
	private String obtenerDatoProducto(Connection conn, Integer sucursal, Integer codRamo, Integer numPoliza, Integer dato) throws Exception {
		PreparedStatement pst = null;
		ResultSet resultSet = null;
		String datoProducto = "";
		String sql = "SELECT CRPD_DATO FROM CRET_PRODUCTOS_DATOS WHERE CRPD_CASU_CD_SUCURSAL = ? AND CRPD_CARP_CD_RAMO = ? AND CRPD_CAPO_NU_POLIZA = ? AND CRPD_CRCD_CD_DATO = ? AND ROWNUM < 2";
		
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("obtenerDatoProducto");
		logueo.setParametro("sucursal", sucursal);
		logueo.setParametro("codRamo", codRamo);
		logueo.setParametro("numPoliza", numPoliza);
		logueo.setParametro("dato", dato);
		logueo.setParametro("ConsultaSQL", sql);
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, sucursal);
			pst.setInt(2, codRamo);
			pst.setInt(3, numPoliza);
			pst.setInt(4, dato);
			
			resultSet = pst.executeQuery();
			while (resultSet.next()) {
				datoProducto = resultSet.getString("CRPD_DATO");
				datoProducto = datoProducto != null ? datoProducto : "";
			}
			logueo.setParametro("Valor salida", datoProducto);
			LOG.info(logueo.getSoloParametros());
			
		} catch (Exception e) {
			logueo.setException("Exception");
			logueo.setError(e.getMessage());
			log.error(logueo.getMensaje(), e);
			throw new Exception(e);		
		} finally {
			liberarRecursos(pst);
			liberarRecursos(resultSet);
		}
		
		return datoProducto;
	}	
	
	private void validacionSOA(Connection conn, Integer codRamo, Integer numPoliza, Integer certificado, String tipoDocumento, String nroDocumento) throws Exception {
		CallableStatement cstmt = null;
		String nombrePL = obtenerValor("proc_validarSOA");
		
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("validacionSOA");
		logueo.setParametro("codRamo", codRamo);
		logueo.setParametro("numPoliza", numPoliza);
		logueo.setParametro("certificado", certificado);
		logueo.setParametro("tipoDocumento", tipoDocumento);
		logueo.setParametro("nroDocumento", nroDocumento);
		logueo.setNombrePl(nombrePL);
		
		try {
			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.setInt(1, codRamo);
			cstmt.setInt(2, numPoliza);
			cstmt.setInt(3, certificado);
			cstmt.setString(4, tipoDocumento);
			cstmt.setString(5, nroDocumento);
			cstmt.setNull(6, Types.NULL);
	
			cstmt.registerOutParameter(7, Types.INTEGER);
			cstmt.execute();
	
			int valSOA = cstmt.getInt(7);
			if (valSOA != 0) {
				throw new Exception("Error al ejecutar validacion SOA. Valor obtenido: " + valSOA);
			}
			
			logueo.setParametro("Valor salida", valSOA);
			LOG.info(logueo.getSoloParametros());
			
		} catch (Exception e) {
			logueo.setException("Exception");
			logueo.setError(e.getMessage());
			log.error(logueo.getMensaje(), e);
			throw new Exception(e);
		} finally {
			liberarRecursos(cstmt);
		}
	}
	
	public ResultValidacionSOA validacionSOA(ParamValidacionSOA param) {
		ResultValidacionSOA resultado = new ResultValidacionSOA();
		CallableStatement cstmt = null;
		Connection conn = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("validacionSOA");
		
		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());
		logueo.setParametro("CodRamo", param.getCodRamo());
		logueo.setParametro("NumPoliza", param.getNumPoliza());
		logueo.setParametro("certificado", param.getCertificado());
		logueo.setParametro("tipoDocumento", param.getTipoDocumento());
		logueo.setParametro("nroDocumento", param.getNroDocumento());
		logueo.setParametro("productor", param.getProductor());
		
		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_validarSOA");
			logueo.setNombrePl(nombrePL);
			
			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.setInt(1, param.getCodRamo());
			cstmt.setInt(2, param.getNumPoliza());
			cstmt.setInt(3, param.getCertificado());
			cstmt.setString(4, param.getTipoDocumento());
			cstmt.setString(5, param.getNroDocumento());
			if (param.getProductor() == null || param.getProductor() == 0) {
				cstmt.setNull(6, Types.INTEGER);
				
			} else {
				cstmt.setInt(6, param.getProductor());
			}
			cstmt.registerOutParameter(7, Types.INTEGER);
			cstmt.execute();
			
			int validacion = cstmt.getInt(7);
			resultado.setValidacion(validacion);
			logueo.setParametro("Valor salida", validacion);
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

	public ResultObtenerMaximoEndoso obtenerMaximoEndoso(ParamObtenerMaximoEndoso param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("obtenerMaximoEndoso");
		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());
		logueo.setParametro("CodRamo", param.getCodRamo());
		logueo.setParametro("NumPoliza", param.getNumPoliza());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultObtenerMaximoEndoso resultado = new ResultObtenerMaximoEndoso();

		try {
			conn = crearConexion();
			String sql = "SELECT MAX(O.CACW_NU_ENDOSO) FROM RECTOR.CART_CERTIFICADOS_ENDOSOS O WHERE O.CACW_CARP_CD_RAMO = ? AND O.CACW_CAPO_NU_POLIZA = ? AND O.CACW_CAME_TP_TRANSAC NOT IN ('L', 'C')";
			logueo.setParametro("Consulta", sql);
			pst = conn.prepareStatement(sql);
			pst.setInt(1, param.getCodRamo());
			pst.setInt(2, param.getNumPoliza());

			result = pst.executeQuery();

			while (result.next()) {
				resultado.setMaximoEndoso(result.getInt(1));
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
	
	public ResultValidacionCartaVerde validacionCartaVerde(ParamValidacionCartaVerde param) {
		ResultValidacionCartaVerde resultado = new ResultValidacionCartaVerde();
		CallableStatement cstmt = null;
		Connection conn = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("validacionCartaVerde");
		
		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());
		logueo.setParametro("CodRamo", param.getCodRamo());
		logueo.setParametro("NumPoliza", param.getNumPoliza());
		logueo.setParametro("certificado", param.getCertificado());
		logueo.setParametro("tipoDocumento", param.getTipoDocumento());
		logueo.setParametro("nroDocumento", param.getNroDocumento());
		logueo.setParametro("productor", param.getProductor());
		
		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_validarCartaVerde");
			logueo.setNombrePl(nombrePL);
			
			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, String.valueOf(param.getCodRamo()));
			cstmt.setString(2, String.valueOf(param.getNumPoliza()));
			cstmt.setString(3, String.valueOf(param.getCertificado()));
			cstmt.setString(4, param.getTipoDocumento());
			cstmt.setString(5, param.getNroDocumento());
			if (param.getProductor() == null || param.getProductor() == 0) {
				cstmt.setNull(6, Types.VARCHAR);
				
			} else {
				cstmt.setString(6, String.valueOf(param.getProductor()));
			}
			
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.registerOutParameter(8, Types.VARCHAR);
			cstmt.registerOutParameter(9, Types.VARCHAR);
			cstmt.registerOutParameter(10, Types.VARCHAR);
			cstmt.registerOutParameter(11, Types.VARCHAR);
			cstmt.execute();

			String sucursal =  cstmt.getString(7);
			String endoso = cstmt.getString(8);
			String formulario = cstmt.getString(9);
			String consecutivo = cstmt.getString(10);
			String result = cstmt.getString(11);
			
			resultado.setSucursal(sucursal);
			resultado.setEndoso(endoso);
			resultado.setFormulario(formulario);
			resultado.setConsecutivo(consecutivo);
			resultado.setResultado(result);
			
			logueo.setParametro("Valor salida", sucursal);
			logueo.setParametro("Valor salida", endoso);
			logueo.setParametro("Valor salida", formulario);
			logueo.setParametro("Valor salida", consecutivo);
			logueo.setParametro("Valor salida", result);
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
	

	public ResultLogActividadMibseWsExt logActividadMibseWsExt(ParamLogActividadMibseWsExt param) {
		ResultLogActividadMibseWsExt resultado = new ResultLogActividadMibseWsExt();
		CallableStatement cstmt = null;
		Connection conn = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("logActividadMibseWsExt");
		
		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());
		logueo.setParametro("Evento", param.getEvento());
		logueo.setParametro("Detalle", param.getDetalle());
		
		
		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_logActividadMibseWsExt");
			logueo.setNombrePl(nombrePL);
			
			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");
			cstmt.setString(1, String.valueOf(param.getEvento()));
			cstmt.setString(2, String.valueOf(param.getDetalle()));
			cstmt.setString(3, String.valueOf(param.getUsuario()));
			
			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.execute();

			int codError =  cstmt.getInt(4);
			String descError = cstmt.getString(5);
			String sqlError = cstmt.getString(6);
			
			resultado.setCodError(codError);
			resultado.setDescError(descError);
			resultado.setSqlError(sqlError);
			
			logueo.setParametro("Valor salida", codError);
			logueo.setParametro("Valor salida", descError);
			logueo.setParametro("Valor salida", sqlError);
			
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

	
	public ResultGenerico borrarMailCliente(ParamBorrarMailCliente param) {

		ResultGenerico resultado = new ResultGenerico();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("borrarMailCliente");

		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());
		logueo.setParametro("Codigo Comunicacion", param.getComunicacion().getCodComunicacion());
		logueo.setParametro("Valor Comunicacion", param.getComunicacion().getValorComunicacion());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_borrarMailPersona");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");
			if (param.getComunicacion().getCodComunicacion() != null) {
				cstmt.setInt(1, param.getComunicacion().getCodComunicacion());
			} else {
				cstmt.setNull(1, Types.INTEGER);
			}
			cstmt.setString(2, param.getComunicacion().getValorComunicacion());
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

	
	public ResultGenerico modificarMailCliente(ParamModificarMailCliente param) {

		ResultGenerico resultado = new ResultGenerico();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("modificarMailCliente");

		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());
		logueo.setParametro("Codigo Comunicacion", param.getComunicacion().getCodComunicacion());
		logueo.setParametro("Tipo Comunicacion", param.getComunicacion().getTipoComunicacion());
		logueo.setParametro("Valor Comunicacion", param.getComunicacion().getValorComunicacion());
		logueo.setParametro("Validada Comunicacion", param.getComunicacion().getValidada());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_modificarMailPersona");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");
			if (param.getComunicacion().getCodComunicacion() != null) {
				cstmt.setInt(1, param.getComunicacion().getCodComunicacion());
			} else {
				cstmt.setNull(1, Types.INTEGER);
			}
			cstmt.setInt(2, param.getComunicacion().getTipoComunicacion());
			cstmt.setString(3, param.getComunicacion().getValorComunicacion());
			if (param.getComunicacion().getValidada().equalsIgnoreCase("S")) {
				cstmt.setString(4, "S");
			} else {
				cstmt.setString(4, "N");
			}
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


	public ResultGenerico altaMailCliente(ParamAltaMailCliente param) {

		ResultGenerico resultado = new ResultGenerico();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("altaMailCliente");

		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());
		
		logueo.setParametro("Tipo Comunicacion", param.getComunicacion().getTipoComunicacion());
		logueo.setParametro("Valor Comunicacion", param.getComunicacion().getValorComunicacion());
		logueo.setParametro("Validada Comunicacion", param.getComunicacion().getValidada());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_altaMailPersona");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");
			
			cstmt.setInt(1, param.getComunicacion().getTipoComunicacion());
			cstmt.setString(2, param.getComunicacion().getValorComunicacion());
			//Nota
			cstmt.setString(3, "");
			if (param.getComunicacion().getValidada().equalsIgnoreCase("S")) {
				cstmt.setString(4, "S");
			} else {
				cstmt.setString(4, "N");
			}
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

	
	public ResultObtenerMailsEnvioFacturaCliente obtenerMailsEnvioFactura(ParamObtenerMailsEnvioFacturaCliente param) {
		ResultObtenerMailsEnvioFacturaCliente resultado = new ResultObtenerMailsEnvioFacturaCliente();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("obtenerMailsEnvioFactura");
		logueo.setParametro("Usuario", param.getUsuario());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("fun_obtenerMailsEnvioFacturaPersona");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{? = call " + nombrePL + "(?,?,?,?)}");

			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);

			cstmt.setString(2, param.getUsuario());

			cstmt.registerOutParameter(3, Types.INTEGER);
			cstmt.registerOutParameter(4, Types.VARCHAR);
			cstmt.registerOutParameter(5, Types.VARCHAR);

			cstmt.execute();

			int codError = cstmt.getInt(3);
			String descError = cstmt.getString(4);
			String sqlError = cstmt.getString(5);

			if (codError == 0) {
				String res = cstmt.getString(1);
				resultado.setMail(res);
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

	
	public ResultValidarCertificadoLibreDeudaADT validarCertificadoLibreDeudaADT(ParamValidarCertificadoLibreDeudaADT param) {
		ResultValidarCertificadoLibreDeudaADT resultado = new ResultValidarCertificadoLibreDeudaADT();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("validarCertificadoLibreDeudaADT");
		logueo.setParametro("Usuario", param.getUsuario());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_validarCertificadoLibreDeudaADT");
			logueo.setNombrePl(nombrePL);
			

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getRamo());
			cstmt.setString(2, param.getPoliza());

			cstmt.registerOutParameter(3, Types.VARCHAR);
			cstmt.registerOutParameter(4, Types.VARCHAR);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.execute();
			
			resultado.setEstado(cstmt.getString(3));
			
			if (resultado.getEstado().equals("0")) {
				resultado.setTitular(cstmt.getString(4));
				resultado.setFecha_vigencia(cstmt.getString(5));
				resultado.setProducto(cstmt.getString(6));
			}
			else {
				if (cstmt.getString(3)!=null) {
					resultado.setTitular(cstmt.getString(4));	
				}
			}
			
			/*
			int codError = cstmt.getInt(3);
			String descError = cstmt.getString(4);
			String sqlError = cstmt.getString(5);
			if (codError == 0) {
				String res = cstmt.getString(1);
				resultado.setMail(res);
			} else {
				ServiciosError error = new ServiciosError();
				error.setCodigo(Integer.valueOf(codError));
				error.setDescripcion(descError);

				resultado.setError(error);
				resultado.setHayError(Boolean.TRUE);
			}
			
			logResultados(logueo, codError, descError, sqlError);
			*/
			
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, cstmt);
		}
		return resultado;
	}

	public ResultXmlPL obtenerMapaMsgSiniestro(ParamObtenerMapaMsgSiniestro param) {
		
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("obtenerMapaMsgSiniestro");

		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());
		
		logueo.setParametro("Ruta", param.getRuta());
		logueo.setParametro("TieneDenuncia", param.getTieneDenuncia());
		logueo.setParametro("TieneReclamo", param.getTieneReclamo());
		logueo.setParametro("EstadoReclamo", param.getEstadoReclamo());
		logueo.setParametro("TieneSiniestro", param.getTieneSiniestro());
		logueo.setParametro("TineNota", param.getTieneNota());
		logueo.setParametro("NroNota", param.getNroNota());
		logueo.setParametro("TineSubNota", param.getTieneSubNota());
		logueo.setParametro("NroSubNota", param.getNroSubNota());
		logueo.setParametro("NotasAnteriores", param.getNotasAnteriores());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerMapaMsgSiniestroMiBSE");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, param.getRamo());
			cstmt.setString(2, param.getRuta());
			cstmt.setString(3, param.getTieneDenuncia());
			cstmt.setString(4, param.getTieneReclamo());
			cstmt.setString(5, param.getEstadoReclamo());
			cstmt.setString(6, param.getTieneSiniestro());
			cstmt.setString(7, param.getTieneNota());
			cstmt.setInt(8, param.getNroNota());
			cstmt.setString(9, param.getTieneSubNota());
			cstmt.setInt(10, param.getNroSubNota());
			cstmt.setString(11, param.getNotasAnteriores());
			
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
	
	public ResultSubirArchivo subirArchivo(ParamSubirArchivo param) {
		final String PATH = "/app/updownfile/corredores/tienda-bicibse/subir_archivos/";
		ResultSubirArchivo resultado = new ResultSubirArchivo();

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("subirArchivo");
		
		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());
		logueo.setParametro("NombreArchivo", param.getNombreArchivo());
		logueo.setParametro("Archivo null?", param.getArchivo() == null);
		
		InputStream is = null;
		OutputStream os = null;
		try {
			is = param.getArchivo().getInputStream();
			os = new FileOutputStream(new File(PATH + param.getNombreArchivo()));
			IOUtils.copy(is, os);
		} catch(Exception e) {
			catchException(resultado, logueo, e);
		} finally {
			IOUtils.closeQuietly(os);
			IOUtils.closeQuietly(is);
		}
		
		return resultado;
	}
	
	public ResultProCarta proCarta(ParamProCarta param) {
		ResultProCarta resultado = new ResultProCarta();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("proCarta");
		logueo.setParametro("Usuario", param.getUsuario());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_proCarta");
			logueo.setNombrePl(nombrePL);
			
			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?)}");

			cstmt.setInt(1, param.getRamo());
			cstmt.setString(2, param.getPoliza());

			cstmt.registerOutParameter(3, Types.INTEGER);
			cstmt.registerOutParameter(4, Types.VARCHAR);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			
			cstmt.execute();
			
			String descError = cstmt.getString(5);
			
			if (descError == null) {
				resultado.setNuCarta(cstmt.getInt(3));
				resultado.setNomReporte(cstmt.getString(4));
			} else {
				ServiciosError error = new ServiciosError();
				error.setCodigo(12);
				error.setDescripcion(descError);
				resultado.setError(error);
				resultado.setHayError(Boolean.TRUE);
			}
			logResultados(logueo, 12, descError, null);
			
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, cstmt);
		}
		return resultado;
	}

	public ResultCorrespondeCartaPoliza correspondeCartaPoliza(ParamCorrespondeCartaPoliza param) {
		ResultCorrespondeCartaPoliza resultado = new ResultCorrespondeCartaPoliza();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("correspondeCartaPoliza");

		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());
		
		logueo.setParametro("sucursal", param.getSucursal());		
		logueo.setParametro("ramo", param.getRamo());
		logueo.setParametro("poliza", param.getPoliza());
		logueo.setParametro("certificado", param.getCertificado());
		logueo.setParametro("cdCarta", param.getCdCarta());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("fun_correspondeCartaPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{? = call " + nombrePL + "(?,?,?,?,?,?,?)}");

			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
			
			cstmt.setInt(2, param.getSucursal());
			cstmt.setInt(3, param.getRamo());
			cstmt.setInt(4, param.getPoliza());
			cstmt.setInt(5, param.getCertificado());
			cstmt.setNull(6, Types.NUMERIC);
			cstmt.setString(7, param.getCdCarta());

			cstmt.registerOutParameter(8, Types.VARCHAR);
			
			cstmt.execute();

			String res = cstmt.getString(1);
			String mensaje = cstmt.getString(8);
			
			resultado.setCorrespondeCartaPoliza(res.toUpperCase().equals("S"));
			resultado.setMensaje(mensaje);
			
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
	
	// Idem ProCarta pero con 3 par�metros extra: certificado, endoso y cdCarta.
	public ResultProCarta proCarta2(ParamProCarta2 param) {
		ResultProCarta resultado = new ResultProCarta();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("proCarta2");
		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());
		
		logueo.setParametro("ramo", param.getRamo());
		logueo.setParametro("poliza", param.getPoliza());
		logueo.setParametro("certificado", param.getCertificado());
		logueo.setParametro("cdCarta", param.getCdCarta());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_proCarta");
			logueo.setNombrePl(nombrePL);
			
			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getRamo());
			cstmt.setString(2, param.getPoliza());
			cstmt.setInt(3, param.getCertificado());
			cstmt.setNull(4, Types.NUMERIC);
			cstmt.setString(5, param.getCdCarta());

			cstmt.registerOutParameter(6, Types.INTEGER);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.registerOutParameter(8, Types.VARCHAR);
			
			cstmt.execute();
			
			String descError = cstmt.getString(8);
			
			if (descError == null) {
				resultado.setNuCarta(cstmt.getInt(6));
				resultado.setNomReporte(cstmt.getString(7));
			} else {
				ServiciosError error = new ServiciosError();
				error.setCodigo(12);
				error.setDescripcion(descError);
				resultado.setError(error);
				resultado.setHayError(Boolean.TRUE);
			}
			logResultados(logueo, 12, descError, null);
			
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, cstmt);
		}
		return resultado;
	}

	public ResultXmlPL obtenerPolizasFacturasPagasCliente(ParamObtenerPolizasFacturasPagasCliente param) {

		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("obtenerPolizasFacturasPagasCliente");

		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());
		logueo.setParametro("DiasVencimientoPoliza", param.getDiasVencimientoPoliza().toString());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerPolizasFacturasPagasMiBSE");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");
			cstmt.setString(1, param.getUsuario());
			cstmt.setInt(2, param.getDiasVencimientoPoliza());

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
	
	public ResultAdherirFacturaDigital adherirFacturaDigital(ParamAdherirFacturaDigital param) {
		ResultAdherirFacturaDigital resultado = new ResultAdherirFacturaDigital();
		CallableStatement cstmt = null;
		Connection conn = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosMiBsePersist.class);
		logueo.setMetodo("adherirFacturaDigital");

		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());
		logueo.setParametro("sucusal", param.getSucursal());
		logueo.setParametro("CodRamo", param.getCodRamo());
		logueo.setParametro("NumPoliza", param.getNumPoliza());
		
		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_adherirFacturaDigital");
			logueo.setNombrePl(nombrePL);
			
			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");
			cstmt.setInt(1, param.getSucursal());			
			cstmt.setInt(2, param.getCodRamo());
			cstmt.setInt(3, param.getNumPoliza());
			
			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.execute();
			
			int codError = cstmt.getInt(4);
			String descError = cstmt.getString(5);
			String sqlError = cstmt.getString(6);

			if (codError != 0) {
				ServiciosError error = new ServiciosError();
				error.setCodigo(codError);
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
}
