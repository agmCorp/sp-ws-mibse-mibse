package uy.com.bse.maestro.persistencia;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.bse.rector.servicios.persistencia.ParseoXml;
import uy.com.bse.clientes.consultas.ParamExportarClientes;
import uy.com.bse.clientes.consultas.ParamObtenerClientes;
import uy.com.bse.clientes.consultas.ParamObtenerDetalleConsultaClientes;
import uy.com.bse.clientes.consultas.ParamPolizasAsociadasClientes;
import uy.com.bse.cotizaciones.entidades.DatosBanco;
import uy.com.bse.maestro.personas.comunicaciones.ComunicacionEC;
import uy.com.bse.maestro.personas.comunicaciones.ParamAltaComunicacion;
import uy.com.bse.maestro.personas.comunicaciones.ParamBajaComunicacion;
import uy.com.bse.maestro.personas.comunicaciones.ParamModificarComunicacion;
import uy.com.bse.maestro.personas.comunicaciones.ResultComunicacion;
import uy.com.bse.maestro.personas.comunicaciones.ResultComunicacionEC;
import uy.com.bse.maestro.personas.consultas.DatosComunicacion;
import uy.com.bse.maestro.personas.consultas.DatosPersonaFisica;
import uy.com.bse.maestro.personas.consultas.DatosPersonaJuridica;
import uy.com.bse.maestro.personas.consultas.ParamObtenerPersonas;
import uy.com.bse.maestro.personas.consultas.ParamObtenerPersonasClientes;
import uy.com.bse.maestro.personas.consultas.ParamPersona;
import uy.com.bse.maestro.personas.consultas.ParamPolizasAsociadas;
import uy.com.bse.maestro.personas.consultas.ResultComunicaciones;
import uy.com.bse.maestro.personas.consultas.ResultObtenerPersonas;
import uy.com.bse.maestro.personas.consultas.ResultRoles;
import uy.com.bse.maestro.personas.consultas.Rol;
import uy.com.bse.maestro.personas.datosbancarios.Banco;
import uy.com.bse.maestro.personas.datosbancarios.DatoBancario;
import uy.com.bse.maestro.personas.datosbancarios.ParamBorrarDatoBancario;
import uy.com.bse.maestro.personas.datosbancarios.ParamListaBancosPersona;
import uy.com.bse.maestro.personas.datosbancarios.ParamModificarDatoBancario;
import uy.com.bse.maestro.personas.datosbancarios.ParamObtenerDatosBancariosPersona;
import uy.com.bse.maestro.personas.datosbancarios.ParamTienePermisoDatosBancarios;
import uy.com.bse.maestro.personas.datosbancarios.ResultDatoBancario;
import uy.com.bse.maestro.personas.datosbancarios.ResultListaBancosPersona;
import uy.com.bse.maestro.personas.datosbancarios.ResultObtenerDatosBancariosPersona;
import uy.com.bse.maestro.personas.domicilio.DireccionEC;
import uy.com.bse.maestro.personas.domicilio.ParamDatosBajaDireccion;
import uy.com.bse.maestro.personas.domicilio.ParamDatosDireccion;
import uy.com.bse.maestro.personas.domicilio.ResultBajaDireccion;
import uy.com.bse.maestro.personas.domicilio.ResultDireccion;
import uy.com.bse.maestro.personas.interfaces.Comunicacion;
import uy.com.bse.maestro.personas.interfaces.Direccion;
import uy.com.bse.maestro.personas.interfaces.EstadoCivil;
import uy.com.bse.maestro.personas.interfaces.MotivoBaja;
import uy.com.bse.maestro.personas.interfaces.ParamEstadoCivil;
import uy.com.bse.maestro.personas.interfaces.ParamListaSexo;
import uy.com.bse.maestro.personas.interfaces.ParamMotivosBaja;
import uy.com.bse.maestro.personas.interfaces.ParamObtenerPersonasAlta;
import uy.com.bse.maestro.personas.interfaces.ParamPersonaCorredor;
import uy.com.bse.maestro.personas.interfaces.ParamProfesiones;
import uy.com.bse.maestro.personas.interfaces.ParamRadio;
import uy.com.bse.maestro.personas.interfaces.ParamTipoComunicaciones;
import uy.com.bse.maestro.personas.interfaces.ParamTipoDireccion;
import uy.com.bse.maestro.personas.interfaces.ParamTipoPersoneria;
import uy.com.bse.maestro.personas.interfaces.Personeria;
import uy.com.bse.maestro.personas.interfaces.Profesion;
import uy.com.bse.maestro.personas.interfaces.ResultListaEstadoCivil;
import uy.com.bse.maestro.personas.interfaces.ResultListaMotivosBaja;
import uy.com.bse.maestro.personas.interfaces.ResultListaProfesiones;
import uy.com.bse.maestro.personas.interfaces.ResultListaSexo;
import uy.com.bse.maestro.personas.interfaces.ResultListaTipoComunicaciones;
import uy.com.bse.maestro.personas.interfaces.ResultListaTipoDireccion;
import uy.com.bse.maestro.personas.interfaces.ResultListaTipoPersoneria;
import uy.com.bse.maestro.personas.interfaces.ResultObtenerPersonasAlta;
import uy.com.bse.maestro.personas.interfaces.ResultPersonaCorredor;
import uy.com.bse.maestro.personas.interfaces.Sexo;
import uy.com.bse.maestro.personas.personas.DatosDirecciones;
import uy.com.bse.maestro.personas.personas.ParamAltaRolCliente;
import uy.com.bse.maestro.personas.personas.ParamDatosActivarPersona;
import uy.com.bse.maestro.personas.personas.ParamDatosBajaPersona;
import uy.com.bse.maestro.personas.personas.ParamDatosPersonaFisica;
import uy.com.bse.maestro.personas.personas.ParamDatosPersonaJuridica;
import uy.com.bse.maestro.personas.personas.ParamExistePersona;
import uy.com.bse.maestro.personas.personas.ParamObtenerDireccion;
import uy.com.bse.maestro.personas.personas.PersonaFisica;
import uy.com.bse.maestro.personas.personas.PersonaJuridica;
import uy.com.bse.maestro.personas.personas.ResultActivarPersona;
import uy.com.bse.maestro.personas.personas.ResultAltaRolCliente;
import uy.com.bse.maestro.personas.personas.ResultBajaPersona;
import uy.com.bse.maestro.personas.personas.ResultExistePersona;
import uy.com.bse.maestro.personas.personas.ResultObtenerDirecciones;
import uy.com.bse.maestro.personas.personas.ResultPersonaFisica;
import uy.com.bse.maestro.personas.personas.ResultPersonaJuridica;
import uy.com.bse.maestro.personas.rol.ParamDatosRolCliente;
import uy.com.bse.maestro.personas.rol.ResultRolCliente;
import uy.com.bse.servicios.rector.emision.ParamEmision;
import uy.com.bse.servicios.rector.emision.ParamValidarCotizacion;
import uy.com.bse.servicios.rector.emision.ResultValidarCotizacion;
import uy.com.bse.servicios.rector.emision.ValidaCotizacion;
import uy.com.bse.servicios.rector.interfaces.Actividad;
import uy.com.bse.servicios.rector.interfaces.Departamento;
import uy.com.bse.servicios.rector.interfaces.Documento;
import uy.com.bse.servicios.rector.interfaces.Localidad;
import uy.com.bse.servicios.rector.interfaces.Organismo;
import uy.com.bse.servicios.rector.interfaces.Pais;
import uy.com.bse.servicios.rector.interfaces.ParamCodigoPostal;
import uy.com.bse.servicios.rector.interfaces.ParamContactoWeb;
import uy.com.bse.servicios.rector.interfaces.ParamListaActividades;
import uy.com.bse.servicios.rector.interfaces.ParamListaDepartamentos;
import uy.com.bse.servicios.rector.interfaces.ParamListaOrganismos;
import uy.com.bse.servicios.rector.interfaces.ParamListaPaises;
import uy.com.bse.servicios.rector.interfaces.ParamListaProductoresAsignados;
import uy.com.bse.servicios.rector.interfaces.ParamListaProductoresAsignadosVigentes;
import uy.com.bse.servicios.rector.interfaces.ParamListaSucursales;
import uy.com.bse.servicios.rector.interfaces.ParamListaTipoDoc;
import uy.com.bse.servicios.rector.interfaces.ParamLocalidades;
import uy.com.bse.servicios.rector.interfaces.Postal;
import uy.com.bse.servicios.rector.interfaces.ResultContactoWeb;
import uy.com.bse.servicios.rector.interfaces.ResultListaActividades;
import uy.com.bse.servicios.rector.interfaces.ResultListaCodPostal;
import uy.com.bse.servicios.rector.interfaces.ResultListaDepartamentos;
import uy.com.bse.servicios.rector.interfaces.ResultListaLocalidades;
import uy.com.bse.servicios.rector.interfaces.ResultListaOrganismos;
import uy.com.bse.servicios.rector.interfaces.ResultListaPaises;
import uy.com.bse.servicios.rector.interfaces.ResultListaSucursales;
import uy.com.bse.servicios.rector.interfaces.ResultListaTipoDoc;
import uy.com.bse.servicios.rector.interfaces.ResultTipoUsuario;
import uy.com.bse.servicios.rector.interfaces.Sucursal;
import uy.com.bse.utilitario.dato.Codiguera;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultCodiguera;
import uy.com.bse.utilitario.dato.ResultCondicion;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.properties.PropertiesManager;
import uy.com.bse.utilitario.util.Herramientas;
import uy.com.bse.utilitario.util.ValuesUtils;

public class ServiciosRectorPersist extends ServiciosRector {

	private static Logger log = LogManager.getLogger(ServiciosRectorPersist.class);

	private final int numAlta = -1;
	private final String propertiesMaestropersonas = "maestroPersonas.properties";
	private final String propertiesServiciosector = "serviciosRector.properties";
	public static final String SERVRECTPROP = "serviciosRector.properties";
	public static final String COTIZPROP = "cotizaciones.properties";

	/**
	 * Consulta los tipos de documentos disponibles
	 * 
	 * @return Retorna una lista con los documentos disponibles
	 */
	public ResultListaTipoDoc listaTipoDoc(ParamListaTipoDoc param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("listaTipoDoc");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Accion", param.getAccion());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaTipoDoc resultado = new ResultListaTipoDoc();
		String sql = null;

		try {
			conn = crearConexion();
			StringBuffer sqlb = new StringBuffer();
			if (param.getAccion() != null) {

				if (param.getAccion().equalsIgnoreCase("A")) {
					sqlb.append("select ctd.catu_tp_documento, ctd.catu_de_documento");
					sqlb.append(" from cart_tipos_documentos ctd");
					sqlb.append(" where ctd.catu_tp_documento not in ('DIP', 'BPS', 'PAD', 'PRO', 'CIP', 'OP')");
					sqlb.append(" union");
					sqlb.append(" select ctd.catu_tp_documento, ctd.catu_de_documento");
					sqlb.append(" from cart_tipos_documentos ctd");
					sqlb.append(" where ctd.catu_tp_documento in ('DIP', 'BPS', 'PAD', 'PRO')");
					sqlb.append(" and exists (select *");
					sqlb.append(" from cart_usuarios_permisos cup");
					sqlb.append(" where cup.caup_caus_cd_usuario = ?");
					sqlb.append(" and cup.caup_cper_cd_permiso = 403)");
					sqlb.append(" union");
					sqlb.append(" select ctd.catu_tp_documento, ctd.catu_de_documento");
					sqlb.append(" from cart_tipos_documentos ctd");
					sqlb.append(" where ctd.catu_tp_documento in ('CIP')");
					sqlb.append(" and exists (select *");
					sqlb.append(" from cart_usuarios_permisos cup");
					sqlb.append(" where cup.caup_caus_cd_usuario = ?");
					sqlb.append(" and cup.caup_cper_cd_permiso = 404)");
					sqlb.append(" union");
					sqlb.append(" select ctd.catu_tp_documento, ctd.catu_de_documento");
					sqlb.append(" from cart_tipos_documentos ctd");
					sqlb.append(" where ctd.catu_tp_documento in ('OP')");
					sqlb.append(" and exists (select *");
					sqlb.append(" from cart_usuarios_permisos cup");
					sqlb.append(" where cup.caup_caus_cd_usuario = ?");
					sqlb.append(" and cup.caup_cper_cd_permiso = 405)");

				} else if (param.getAccion().equalsIgnoreCase("C")) {
					sqlb.append("select ctd.catu_tp_documento, ctd.catu_de_documento");
					sqlb.append(" from cart_tipos_documentos ctd");
				}
				sql = sqlb.toString();
				logueo.setParametro("Consulta", sql);
				pst = conn.prepareStatement(sql);

				if (param.getAccion().equalsIgnoreCase("A")) {
					pst.setString(1, param.getUsuario());
					pst.setString(2, param.getUsuario());
					pst.setString(3, param.getUsuario());
				}

				result = pst.executeQuery();

				ArrayList<Documento> listaDoc = new ArrayList<Documento>();

				while (result.next()) {
					Documento documento = new Documento();
					documento.setTipoDocumento(result.getString("CATU_TP_DOCUMENTO"));
					documento.setDescDocumento(result.getString("CATU_DE_DOCUMENTO"));

					listaDoc.add(documento);
				}

				resultado.setDocumento(listaDoc);
			}
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	/**
	 * Consulta los organismos disponibles
	 * 
	 * @return Retorana una lista con los organismos disponibles
	 */
	public ResultListaOrganismos listaOrganismos(ParamListaOrganismos param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("listaOrganismos");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaOrganismos resultado = new ResultListaOrganismos();

		try {
			conn = crearConexion();

			String sql = "SELECT CATG_CD_TIPO_ORGANISMO, CATG_DE_TIPO_ORGANISMO FROM CART_TIPOS_ORGANISMOS ORDER BY CATG_CD_TIPO_ORGANISMO";

			logueo.setParametro("Consulta", sql);
			pst = conn.prepareStatement(sql);

			result = pst.executeQuery();

			while (result.next()) {
				Organismo organismo = new Organismo();
				organismo.setTipoOrganismo(result.getString("CATG_CD_TIPO_ORGANISMO"));
				organismo.setDescOrganismo(result.getString("CATG_DE_TIPO_ORGANISMO"));

				resultado.setUnOrganismo(organismo);
			}
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	/**
	 * Consulta las actividades disponibles
	 * 
	 * @return Retorana una lista con las actividades disponibles
	 */
	public ResultListaActividades listaActividades(ParamListaActividades param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("listaActividades");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaActividades resultado = new ResultListaActividades();

		try {
			conn = crearConexion();

			String sql = "SELECT CAAE_CD_ACTIVIDAD, CAAE_DE_ACTIVIDAD FROM CART_ACTIVIDADES ORDER BY CAAE_CD_ACTIVIDAD";

			logueo.setParametro("Consulta", sql);
			pst = conn.prepareStatement(sql);

			result = pst.executeQuery();

			while (result.next()) {
				Actividad actividad = new Actividad();
				actividad.setCodActividad(result.getString("CAAE_CD_ACTIVIDAD"));
				actividad.setDescActividad(result.getString("CAAE_DE_ACTIVIDAD"));

				resultado.setUnaActivadad(actividad);
			}
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	/**
	 * Consulta los paises disponibles
	 * 
	 * @return Retorana una lista con los paises disponibles
	 */
	public ResultListaPaises listaPaises(ParamListaPaises param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("listaPaises");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaPaises resultado = new ResultListaPaises();

		try {
			conn = crearConexion();

			String sql = "SELECT CAPA_CD_PAIS, CAPA_DE_PAIS FROM CART_PAISES ORDER BY CAPA_CD_PAIS";

			logueo.setParametro("Consulta", sql);
			pst = conn.prepareStatement(sql);

			result = pst.executeQuery();

			while (result.next()) {
				Pais pais = new Pais();
				pais.setCodPais(result.getString("CAPA_CD_PAIS"));
				pais.setDescPais(result.getString("CAPA_DE_PAIS"));

				resultado.setUnPais(pais);
			}
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	/**
	 * Consulta los departamentos disponibles
	 * 
	 * @return Retorana una lista con los departamentos disponibles
	 */
	public ResultListaDepartamentos listaDepartamentos(ParamListaDepartamentos param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("listaDepartamentos");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaDepartamentos resultado = new ResultListaDepartamentos();

		try {
			conn = crearConexion();

			String sql = "SELECT CAES_CD_ESTADO, CAES_DE_ESTADO FROM CART_ESTADOS ORDER BY CAES_CD_ESTADO";

			logueo.setParametro("Consulta", sql);
			pst = conn.prepareStatement(sql);

			result = pst.executeQuery();

			while (result.next()) {
				Departamento depto = new Departamento();
				depto.setCodDepartamento(result.getString("CAES_CD_ESTADO"));
				depto.setDescDepartamento(result.getString("CAES_DE_ESTADO"));

				resultado.setUnDepartamento(depto);
			}
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	/**
	 * Pasarela para consultar las localidades
	 * 
	 * @param param
	 * @return Una lista de localidades
	 */
	public ResultListaLocalidades listaLocalidades(ParamLocalidades param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("listaLocalidades");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Pais", param.getPais());
		logueo.setParametro("Departamento", param.getDepartamento());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaLocalidades resultado = new ResultListaLocalidades();

		try {
			conn = crearConexion();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT GEMU_CD_MUNICIPIO, GEMU_DE_MUNICIPIO, GEMU_CAES_CD_PROVINCIA, caes_de_estado from ");
			sql.append("gent_municipios, cart_estados where GEMU_CAES_CD_PROVINCIA=nvl(?, gemu_caes_cd_provincia) ");
			sql.append("and caes_cd_estado = gemu_caes_cd_provincia ORDER BY gemu_cd_municipio");

			logueo.setParametro("Consulta", sql.toString());
			pst = conn.prepareStatement(sql.toString());

			if (param.getDepartamento() == null) {
				pst.setNull(1, Types.VARCHAR);
			} else {
				pst.setString(1, param.getDepartamento());
			}

			result = pst.executeQuery();

			while (result.next()) {
				Localidad loc = new Localidad();

				loc.setCodMunicipio(result.getString("GEMU_CD_MUNICIPIO"));
				loc.setDescMunicipio(result.getString("GEMU_DE_MUNICIPIO"));

				resultado.setUnaLocalidad(loc);
			}
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	/**
	 * Pasarela para consultar los codigos postales de uruguay y Montevideo
	 * 
	 * @param param
	 * @return Una lista de codigos postales
	 */
	public ResultListaCodPostal postalUruguayMontevideo(ParamCodigoPostal param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("postalUruguayMontevideo");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Pais", param.getCodPais());
		logueo.setParametro("Departamento", param.getCodDepartamento());
		logueo.setParametro("Localidad", param.getLocalidad());
		logueo.setParametro("Numero postal", param.getNumPostal());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaCodPostal resultado = new ResultListaCodPostal();

		try {
			conn = crearConexion();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT nu_postal, cd_postal, DE_COD_POSTAL, CD_cALLE, DE_CALLE, NU_INICIO, NU_fINAL ");
			sql.append("FROM (SELECT GECP_NU_POSTAL NU_POSTAL, GECP_CD_CODIGO_POSTAL cd_postal, GECP_DE_LOCALIDAD ");
			sql.append("DE_COD_POSTAL, GECC_CD_CALLE CD_cALLE, GECC_DE_CALLE DE_CALLE, GECP_NU_INICIO NU_INICIO, ");
			sql.append("GECP_NU_FINAL NU_FINAL, GECP_ST_ORDEN PARI, GECP_CAES_CD_PROVINCIA  DEP, ");
			sql.append("GECP_GEMU_CD_MUNICIPIO  LOC ,GEMU_De_MUNICIPIO DE_LOC, NULL NU_INICIO_CALLE, ");
			sql.append("NULL NU_fINal_CALLE, GECc_NU_PARTICIONes NU_PARTICION FROM gent_codigos_postales, ");
			sql.append("GENT_MUNICIPIOS, GENT_CALLES_CAPITAL WHERE gecp_caes_cd_provincia = ");
			sql.append("NVL(?,gecp_caes_cd_provincia) AND gecp_gemu_cd_municipio = NVL(?,gecp_gemu_cd_municipio) ");
			sql.append("AND gecp_gemu_cd_municipio=gemu_cd_municipio AND gemu_caes_cd_provincia=gecp_caes_cd_provincia ");
			sql.append("AND GECC_CD_CALLE =GECP_GECC_CD_cALLE AND GECP_NU_POSTAL=NVL(?,GECP_NU_POSTAL) AND ");
			sql.append("gecc_nu_particiones = 0 UNION ( SELECT GECP_NU_POSTAL NU_POSTAL, GECP_CD_CODIGO_POSTAL ");
			sql.append("cd_postal, GECP_DE_LOCALIDAD DE_COD_POSTAL, GECC_CD_CALLE CD_cALLE, GECC_DE_CALLE DE_CALLE, ");
			sql.append("GECP_NU_INICIO NU_INICIO, GECP_NU_FINAL NU_FINAL, GECP_ST_ORDEN PARI, GECP_CAES_CD_PROVINCIA  ");
			sql.append("DEP, GECP_GEMU_CD_MUNICIPIO  LOC, GEMU_De_MUNICIPIO DE_LOC, GECD_NU_INICIO NU_INICIO_cALLE,  ");
			sql.append("GECD_NU_FINAL NU_fINal_CALLE,  GECD_NU_PARTICION NU_PARTICION FROM gent_codigos_postales, ");
			sql.append("GENT_MUNICIPIOS, GENT_CALLES_CAPITAL, GENT_CALLES_CAPITAL_DETALLE WHERE ");
			sql.append("gecp_caes_cd_provincia=NVL(?,gecp_caes_cd_provincia) AND ");
			sql.append("gecp_gemu_cd_municipio=NVL(?,gecp_gemu_cd_municipio) AND ");
			sql.append("gecp_gemu_cd_municipio=gemu_cd_municipio AND gemu_caes_cd_provincia=gecp_caes_cd_provincia	");
			sql.append("AND GECC_CD_CALLE =GECP_GECC_CD_cALLE AND GECD_GECC_CD_CALLE = GECC_CD_CALLE AND ");
			sql.append("GECP_NU_POSTAL=NVL(?,GECP_NU_POSTAL) AND gecc_nu_particiones > 0 AND ");
			sql.append("GECP_NU_INICIO = GECD_NU_INICIO) ) datos ORDER BY datos.CD_cALLE, datos.NU_INICIO,  ");
			sql.append("datos.NU_fINal");

			logueo.setParametro("Consulta", sql.toString());
			pst = conn.prepareStatement(sql.toString());

			pst.setInt(1, new Integer(param.getCodDepartamento()).intValue());
			pst.setInt(4, new Integer(param.getCodDepartamento()).intValue());

			if (param.getLocalidad() == null) {
				pst.setNull(2, Types.VARCHAR);
				pst.setNull(5, Types.VARCHAR);
			} else {
				pst.setString(2, param.getLocalidad());
				pst.setString(5, param.getLocalidad());
			}

			if (param.getNumPostal() == null) {
				pst.setNull(3, Types.VARCHAR);
				pst.setNull(6, Types.VARCHAR);
			} else {
				pst.setInt(3, new Integer(param.getNumPostal()).intValue());
				pst.setInt(6, new Integer(param.getNumPostal()).intValue());
			}

			result = pst.executeQuery();

			while (result.next()) {
				Postal postal = new Postal();
				postal.setNumPostal(result.getString("nu_postal"));
				postal.setCodPostal(result.getString("cd_postal"));
				postal.setCodCalle(result.getString("CD_cALLE"));
				postal.setDescCalle(result.getString("DE_CALLE"));
				postal.setNumInicio(result.getString("NU_INICIO"));
				postal.setNumFinal(result.getString("NU_fINAL"));

				resultado.setUnPostal(postal);
			}
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}
		return resultado;
	}

	/**
	 * Pasarela para consultar los codigos postales de uruguay y no geografico
	 * 
	 * @param param
	 * @return Una lista de codigos postales
	 */
	public ResultListaCodPostal postalUruguayNoGeografico(ParamCodigoPostal param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("postalUruguayMontevideo");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Pais", param.getCodPais());
		logueo.setParametro("Departamento", param.getCodDepartamento());
		logueo.setParametro("Localidad", param.getLocalidad());
		logueo.setParametro("Numero postal", param.getNumPostal());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaCodPostal resultado = new ResultListaCodPostal();

		try {
			conn = crearConexion();

			String sql = "select p.GECP_CD_CODIGO_POSTAL, p.GECP_DE_LOCALIDAD, p.GECP_NU_POSTAL from gent_codigos_postales p where p.GECP_CAES_CD_PROVINCIA=0 order by p.GECP_CD_CODIGO_POSTAL";

			logueo.setParametro("Consulta", sql);
			pst = conn.prepareStatement(sql);

			result = pst.executeQuery();

			while (result.next()) {
				Postal postal = new Postal();
				postal.setCodPostal(result.getString("GECP_CD_CODIGO_POSTAL"));
				postal.setDescLocalidad(result.getString("GECP_DE_LOCALIDAD"));
				postal.setNumPostal(result.getString("GECP_NU_POSTAL"));

				resultado.setUnPostal(postal);
			}
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	/**
	 * Pasarela para consultar los codigos postales de uruguay y el interior
	 * 
	 * @param param
	 * @return Una lista de codigos postales
	 */
	public ResultListaCodPostal postalUruguayInterior(ParamCodigoPostal param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("postalUruguayMontevideo");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Pais", param.getCodPais());
		logueo.setParametro("Departamento", param.getCodDepartamento());
		logueo.setParametro("Localidad", param.getLocalidad());
		logueo.setParametro("Numero postal", param.getNumPostal());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaCodPostal resultado = new ResultListaCodPostal();

		try {
			conn = crearConexion();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT  GECP.GECP_NU_POSTAL NU_POSTAL, gecp.GECP_CD_CODIGO_POSTAL cd_postal, GECP.GECP_DE_LOCALIDAD DE_COD_POSTAL,");
			sql.append("GECP_CAES_CD_PROVINCIA  DEP, GECP_GEMU_CD_MUNICIPIO  LOC , GEMU_De_MUNICIPIO DE_LOC, CAES_DE_ESTADO DE_DEP ");
			sql.append("FROM gent_codigos_postales  GECP, CART_eSTADOS , GENT_MUNICIPIOS, GENT_PARAMETROS WHERE ");
			sql.append("(GECP_GECC_CD_cALLE IS NULL AND gECP_CAES_CD_PROVINCIA <> GEPA_NM_VALOR_CAMPO AND GEPA_NM_CAMPO = 'CAPITAL_FEDERAL')");
			sql.append("AND gECP_CAES_CD_PROVINCIA = NVL(?,gecp_caes_cd_provincia) AND GECP_GEMU_CD_MUNICIPIO = NVL(?,gecp_gemu_cd_municipio)");
			sql.append("AND CAES_CD_ESTADO = GECP_CAES_CD_PROVINCIA aND GEMU_CD_MUNICIPIO = GECP_GEMU_CD_MUNICIPIO AND gemu_caes_cd_provincia = gecp_caes_cd_provincia ");
			sql.append("and gecp_nu_postal = nvl(?,gecp_nu_postal) ORDER BY gecp.GECP_CD_CODIGO_POSTAL, GECP.GECP_DE_LOCALIDAD");

			logueo.setParametro("Consulta", sql.toString());
			pst = conn.prepareStatement(sql.toString());

			pst.setInt(1, new Integer(param.getCodDepartamento()).intValue());

			if (param.getLocalidad() == null) {
				pst.setNull(2, java.sql.Types.INTEGER);
			} else {
				pst.setInt(2, new Integer(param.getLocalidad()).intValue());
			}

			if (param.getNumPostal() == null) {
				pst.setNull(3, java.sql.Types.INTEGER);
			} else {
				pst.setInt(3, new Integer(param.getNumPostal()).intValue());
			}

			result = pst.executeQuery();

			while (result.next()) {
				Postal postal = new Postal();
				postal.setNumPostal(result.getString("nu_postal"));
				postal.setCodPostal(result.getString("cd_postal"));
				postal.setCodDepartamento(result.getString("dep"));
				postal.setCodLocalidad(result.getString("loc"));
				postal.setDescDepartamento(result.getString("de_dep"));
				postal.setDescLocalidad(result.getString("de_loc"));
				postal.setLocalidad(result.getString("de_cod_postal"));

				resultado.setUnPostal(postal);
			}
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	/**
	 * Pasarela para consultar los codigos postales de cualquier pais
	 * 
	 * @param param
	 * @return Una lista de codigos postales
	 */
	public ResultListaCodPostal postalCualquierPais(ParamCodigoPostal param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("postalUruguayMontevideo");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Pais", param.getCodPais());
		logueo.setParametro("Departamento", param.getCodDepartamento());
		logueo.setParametro("Localidad", param.getLocalidad());
		logueo.setParametro("Numero postal", param.getNumPostal());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaCodPostal resultado = new ResultListaCodPostal();

		try {
			conn = crearConexion();

			String sql = "select * from GENT_CODIGOS_POSTALES_EXTRA where GECE_CAPA_CD_PAIS = ? ORDER BY GECE_CD_CODIGO_POSTAL";

			logueo.setParametro("Consulta", sql);
			pst = conn.prepareStatement(sql);

			pst.setInt(1, new Integer(param.getCodPais()).intValue());

			result = pst.executeQuery();

			while (result.next()) {
				Postal postal = new Postal();
				postal.setNumPostal(result.getString("gece_nu_postal"));
				postal.setCodPostal(result.getString("gece_cd_codigo_postal"));
				postal.setDescDepartamento(result.getString("gece_de_provincia"));
				postal.setDescLocalidad(result.getString("gece_de_localidad"));

				resultado.setUnPostal(postal);
			}
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	/**
	 * Consulta las sucursales disponibles
	 * 
	 * @return Retorana una lista con las sucursales disponibles
	 */
	public ResultListaSucursales listaSucursales(ParamListaSucursales param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("listaSucursales");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaSucursales resultado = new ResultListaSucursales();

		try {
			conn = crearConexion();

			String sql = "SELECT * FROM xxrc_pay_groups_v WHERE substr(pay_group_code,1,3)='G00'";

			logueo.setParametro("Consulta", sql);
			pst = conn.prepareStatement(sql);

			result = pst.executeQuery();

			while (result.next()) {
				Sucursal suc = new Sucursal();
				suc.setCodSucursal(result.getString("PAY_GROUP_CODE"));
				suc.setDescSucursal(result.getString("NAME"));
				suc.setNomSucursal(result.getString("DESCRIPTION"));

				resultado.setUnaSucursal(suc);
			}
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	/**
	 * Inserta los datos de contacto
	 * 
	 * @param param
	 * @return El resultado de insertar los datos de contacto
	 */
	public ResultContactoWeb altaContactoWeb(ParamContactoWeb param) {
		PropertiesManager properties = new PropertiesManager();
		properties.setNombreArchivo(this.propertiesServiciosector);

		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("altaContactoWeb");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Apellido", param.getApellido());
		logueo.setParametro("Email", param.getEmail());
		logueo.setParametro("Nombre", param.getNombre());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Documento", param.getNumDocumento());
		logueo.setParametro("Telefono", param.getTelefono());
		logueo.setParametro("Cotizador", param.getCotizador());

		log.info("Cantidad noticias: " + param.getNoticia().size());

		for (int i = 0; i < param.getNoticia().size(); i++) {
			logueo.setParametro("Noticia", param.getNoticia().get(i).intValue());
		}

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet generatedKeys = null;
		ResultContactoWeb resultado = new ResultContactoWeb();

		try {
			conn = crearConexion();

			String query = "insert into cotw_contactos_personas (COPE_CD_DOCUMENTO, COPE_NM_NOMBRES, COPE_NM_APELLIDOS, COPE_TL_TELEFONO, COPE_ML_MAIL, COPE_CAZB_NU_COTIZACION, COPE_NM_COTIZADOR) values (?, ?, ?, ?, ?, ?, ?)";

			pst = conn.prepareStatement(query, new String[] { "COPE_CD_CONTACTO" });
			pst.setInt(1, new Integer(param.getNumDocumento()).intValue());
			pst.setString(2, param.getNombre());
			pst.setString(3, param.getApellido());

			if (param.getTelefono() == null) {
				pst.setNull(4, Types.VARCHAR);
			} else {
				pst.setString(4, param.getTelefono());
			}

			pst.setString(5, param.getEmail());
			pst.setInt(6, param.getNumCotizacion());

			if (param.getCotizador() == null) {
				pst.setNull(7, Types.INTEGER);
			} else {
				pst.setInt(7, param.getCotizador());
			}

			logueo.setParametro("Consulta", query);
			int affectedRows = pst.executeUpdate();

			if (affectedRows != 0) {
				generatedKeys = pst.getGeneratedKeys();
				if (generatedKeys.next()) {
					int idContacto = generatedKeys.getInt(1);
					logueo.setParametro("Id insertado", idContacto);
					for (int i = 0; i < param.getNoticia().size(); i++) {
						String queryNot = "insert into cotw_contactos_noticias (CONO_COPE_CD_PERSONA, CONO_CNOT_CD_NOTICIA) values (?, ?)";
						pst = conn.prepareStatement(queryNot);
						pst.setInt(1, idContacto);
						pst.setInt(2, param.getNoticia().get(i).intValue());
						pst.executeUpdate();
					}
				}
			} else {
				Herramientas tools = new Herramientas();

				resultado.setHayError(Boolean.TRUE);

				String valorObtenido = properties.obtenerValor("persistenciaContactoNoFilasAfectadas-codigo");
				Integer codigo = tools.checkValorPropertiesNumerico(Values.CODDEFECTO, valorObtenido);

				String descObtenida = properties.obtenerValor("persistenciaContactoNoFilasAfectadas-descripcion");
				String descripcion = tools.checkValorPropertiesString(Values.DESDEFECTO, descObtenida);

				ServiciosError retornarError = new ServiciosError();
				retornarError.setCodigo(codigo);
				retornarError.setDescripcion(descripcion);

				resultado.setError(retornarError);
			}
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception e) {
			catchException(resultado, logueo, e);
		} finally {
			liberarRecursos(conn, pst, generatedKeys);
		}

		return resultado;
	}

	/**
	 * Lista las profesiones existentes
	 * 
	 * @param param
	 * @return Un objeto con una lista de profesiones
	 */
	public ResultListaProfesiones listaProfesiones(ParamProfesiones param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("listaProfesiones");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaProfesiones resultado = new ResultListaProfesiones();

		try {
			conn = crearConexion();

			String sql = "select capw_cd_profesion, capw_de_profesion from cart_profesiones order by capw_cd_profesion";

			logueo.setParametro("Consulta", sql);
			pst = conn.prepareStatement(sql);

			result = pst.executeQuery();

			while (result.next()) {
				Profesion dato = new Profesion();
				dato.setCodigo(result.getString("capw_cd_profesion"));
				dato.setDescripcion(result.getString("capw_de_profesion"));

				resultado.setUnaProfesion(dato);
			}
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	/**
	 * Lista los motivos de baja existentes
	 * 
	 * @param param
	 * @return Un objeto con una lista de motivos de baja
	 */
	public ResultListaMotivosBaja listaMotivosBaja(ParamMotivosBaja param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("listaMotivosBaja()");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaMotivosBaja resultado = new ResultListaMotivosBaja();

		try {
			conn = crearConexion();

			String sql = "select CAMB_CD_MOTIVO_BAJA, CAMB_DE_MOTIVO_BAJA from cart_motivos_baja order by CAMB_CD_MOTIVO_BAJA";

			logueo.setParametro("Consulta", sql);
			pst = conn.prepareStatement(sql);

			result = pst.executeQuery();

			while (result.next()) {
				MotivoBaja dato = new MotivoBaja();
				dato.setCodigo(result.getString("CAMB_CD_MOTIVO_BAJA"));
				dato.setDescripcion(result.getString("CAMB_DE_MOTIVO_BAJA"));

				resultado.setUnMotivoBaja(dato);
			}
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	/**
	 * Lista los estados civiles existentes
	 * 
	 * @param param
	 * @return Un objeto con una lista de estados civil
	 */
	public ResultListaEstadoCivil listaEstadoCivil(ParamEstadoCivil param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("listaEstadoCivil()");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaEstadoCivil resultado = new ResultListaEstadoCivil();

		try {
			conn = crearConexion();

			String sql = "select rv_low_value, rv_meaning from cg_ref_codes where rv_domain = 'EDO CIVIL'";

			logueo.setParametro("Consulta", sql);
			pst = conn.prepareStatement(sql);

			result = pst.executeQuery();

			while (result.next()) {
				EstadoCivil dato = new EstadoCivil();
				dato.setCodigo(result.getString("rv_low_value"));
				dato.setDescripcion(result.getString("rv_meaning"));

				resultado.setUnEstadoCivil(dato);
			}
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	/**
	 * Lista las direcciones existentes
	 * 
	 * @param param
	 * @return Un objeto con una lista de direcciones
	 */
	public ResultListaTipoDireccion listaTipoDireccion(ParamTipoDireccion param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("listaTipoDireccion()");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaTipoDireccion resultado = new ResultListaTipoDireccion();

		try {
			conn = crearConexion();

			String sql = "SELECT ALL CART_TIPOS_DIRECCION.CATD_CD_TIPO_DIRECCION, CART_TIPOS_DIRECCION.CATD_DE_TIPO_DIRECCION FROM CART_TIPOS_DIRECCION ORDER BY CART_TIPOS_DIRECCION.CATD_CD_TIPO_DIRECCION";

			logueo.setParametro("Consulta", sql);
			pst = conn.prepareStatement(sql);

			result = pst.executeQuery();

			while (result.next()) {
				Direccion dato = new Direccion();
				dato.setCodigo(result.getString("CATD_CD_TIPO_DIRECCION"));
				dato.setDescripcion(result.getString("CATD_DE_TIPO_DIRECCION"));

				resultado.setUnaDireccion(dato);
			}
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	/**
	 * Lista los tipos de comunicaciones
	 * 
	 * @param param
	 *            Lista los tipos de comunicaciones existentes
	 * @return Un objeto con una lista de tipos de comunicaciones
	 */
	public ResultListaTipoComunicaciones listaTipoComunicaciones(ParamTipoComunicaciones param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("listaTipoComunicaciones()");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaTipoComunicaciones resultado = new ResultListaTipoComunicaciones();

		try {
			conn = crearConexion();

			String sql = "SELECT ALL CATN_CD_TIPO_COMUNICACION, CATN_DE_TIPO_COMUNICACION FROM CART_TIPOS_COMUNICACION ORDER BY 1";

			logueo.setParametro("Consulta", sql);
			pst = conn.prepareStatement(sql);

			result = pst.executeQuery();

			while (result.next()) {
				Comunicacion dato = new Comunicacion();
				dato.setCodigo(result.getString("CATN_CD_TIPO_COMUNICACION"));
				dato.setDescripcion(result.getString("CATN_DE_TIPO_COMUNICACION"));

				resultado.setUnaComunicacion(dato);
			}
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	/**
	 * Lista los tipos de personeria existentes
	 * 
	 * @param param
	 * @return Un objeto con una lista de tipos de personeria
	 */
	public ResultListaTipoPersoneria listaTipoPersoneria(ParamTipoPersoneria param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("listaTipoPersoneria()");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaTipoPersoneria resultado = new ResultListaTipoPersoneria();

		try {
			conn = crearConexion();

			String sql = "SELECT CATZ_CD_RAZON_SOCIAL, CATZ_DE_RAZON_SOCIAL FROM CART_TIPOS_RAZON ORDER BY CATZ_CD_RAZON_SOCIAL";

			logueo.setParametro("Consulta", sql);
			pst = conn.prepareStatement(sql);

			result = pst.executeQuery();

			while (result.next()) {
				Personeria dato = new Personeria();
				dato.setCodigo(result.getString("CATZ_CD_RAZON_SOCIAL"));
				dato.setDescripcion(result.getString("CATZ_DE_RAZON_SOCIAL"));

				resultado.setUnaPersoneria(dato);
			}
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	/**
	 * Consulta de personas
	 * 
	 * @param param
	 * @return Una lista de personas fisicas y una de personas juridicas
	 */
	public ResultObtenerPersonas obtenerPersonas(ParamObtenerPersonas param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("obtenerPersonas");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("PrimerCriterio", param.getPrimerCriterio());
		logueo.setParametro("PrimerOperador", param.getPrimerOperador());
		logueo.setParametro("PrimerValor", param.getPrimerValor());
		logueo.setParametro("SegundoCriterio", param.getSegundoCriterio());
		logueo.setParametro("SegundoOperador", param.getSegundoOperador());
		logueo.setParametro("SegundoValor", param.getSegundoValor());
		logueo.setParametro("OperadorLogico", param.getOperadorLogico());

		StringBuffer consultaGeneral = new StringBuffer();
		ResultObtenerPersonas resultado = null;

		// Parte estatica de la consulta general.
		consultaGeneral.append("Select CABU_NU_PERSONA, CABU_NM_APELLIDO_RAZON, CABU_NM_PERSONA, cabu_nu_cuit, cabu_catu_tp_documento, cabu_nu_documento,");
		consultaGeneral.append("(SELECT CACN_NU_CEDULA_RIF FROM CART_CLIENTES WHERE CACN_CABU_NU_PERSONA = CABU_NU_PERSONA) codCliente,");

		consultaGeneral.append("(select CATU_DE_DOCUMENTO from CART_TIPOS_DOCUMENTOS ");
		consultaGeneral.append("where CATU_TP_DOCUMENTO = cabu_catu_tp_documento) descripTipoDoc, ");
		consultaGeneral.append("(SELECT COUNT(*) FROM CART_PERSONAS_ROLES WHERE CACL_CABU_NU_PERSONA ");
		consultaGeneral.append("= CABU_NU_PERSONA) cantRoles From CART_MAE_PERSONAS where ");

		PropertiesManager properties = new PropertiesManager();
		properties.setNombreArchivo(this.propertiesMaestropersonas);

		// Obtengo el valor correspondiente al criterio (tengo la descripcion
		// del criterio)
		String primerCriterio = this.getCampoCriterio(properties, param.getPrimerCriterio(), "criterios");

		if (primerCriterio.equals("CACN_NU_CEDULA_RIF")) {
			// Obtengo el tipo que debe tener el primer valor
			String tipoDePrimerValor = this.tipoPrimerValorCedula(primerCriterio);
			String primerOperador = this.getOperador(properties, param.getPrimerOperador());

			if (tipoDePrimerValor != null && primerOperador != null) {
				String primerValor = param.getPrimerValor();
				// Convierto a mayúsculas si es String
				if (tipoDePrimerValor.equals("CHAR") || tipoDePrimerValor.equals("VARCHAR"))
					primerValor = primerValor.toUpperCase();
				// Cargo en la consulta general
				consultaGeneral.append("EXISTS (SELECT 1 FROM CART_CLIENTES WHERE CABU_NU_PERSONA = CACN_CABU_NU_PERSONA");
				consultaGeneral.append(" AND " + primerCriterio + " " + primerOperador + " " + primerValor + ")");
				// Cargo en la consulta general la consulta correspondiente al
				// segundo criterio
				if (param.getSegundoCriterio() != null) {
					// Obtengo el valor correspondiente al operador ingresado
					String segundoOperador = this.getOperador(properties, param.getSegundoOperador());
					// Obtengo el valor correspondiente al criterio (tengo la
					// descripcion del criterio)
					String valorSegundoCriterio = this.getCampoCriterio(properties, param.getSegundoCriterio(), "criterioDos");
					// Obtengo el tipo que debe tener el valor
					String tipoDeValor = this.tipoSegundoValor(valorSegundoCriterio);
					String segundoValor = param.getSegundoValor();
					// Traigo operador logico
					String operLogico = this.operadorLogico(properties, param.getOperadorLogico());
					// Convierto a mayúsculas si es String
					if (tipoDeValor.equals("CHAR") || tipoDeValor.equals("VARCHAR2"))
						segundoValor = segundoValor.toUpperCase();

					consultaGeneral.append(" " + operLogico + " " + valorSegundoCriterio + " " + segundoOperador + " '" + segundoValor + "'");
				}

				log.info(consultaGeneral.toString());
				resultado = this.consultarPersona(consultaGeneral.toString());
			}
			logueo.setParametro("Sql", consultaGeneral.toString());
		} else {
			// Obtengo el tipo que debe tener el primer valor
			String tipoDePrimerValor = this.tipoPrimerValorNoCedula(primerCriterio);
			String primerOperador = this.getOperador(properties, param.getPrimerOperador());

			if (tipoDePrimerValor != null && primerOperador != null) {
				String primerValor = param.getPrimerValor();
				// Convierto a mayusculas si es String
				if (tipoDePrimerValor.equals("CHAR") || tipoDePrimerValor.equals("VARCHAR2"))
					primerValor = primerValor.toUpperCase();

				// Cargo en la consulta general
				consultaGeneral.append(primerCriterio + " " + primerOperador + " '" + primerValor + "'");

				// Agrego consulta de segundo criterio si cumple
				if (param.getSegundoCriterio() != null) {
					// Obtengo el valor correspondiente al operador ingresado
					String segundoOperador = this.getOperador(properties, param.getSegundoOperador());
					// Obtengo el valor correspondiente al criterio (tengo la
					// descripcion del criterio)
					String valorSegundoCriterio = this.getCampoCriterio(properties, param.getSegundoCriterio(), "criterioDos");
					// Obtengo el tipo que debe tener el valor
					String tipoDeValor = this.tipoSegundoValor(valorSegundoCriterio);
					String segundoValor = param.getSegundoValor();
					// Convierto a mayusculas si es String
					if (tipoDeValor.equals("CHAR") || tipoDeValor.equals("VARCHAR2"))
						segundoValor = segundoValor.toUpperCase();
					// Traigo operador logico
					String operLogico = this.operadorLogico(properties, param.getOperadorLogico());
					// Agrego a consulta general
					consultaGeneral.append(" " + operLogico + " " + valorSegundoCriterio + " " + segundoOperador + " '" + segundoValor + "'");
				}
				log.info(consultaGeneral.toString());
				resultado = this.consultarPersona(consultaGeneral.toString());
			}
			logueo.setParametro("Sql", consultaGeneral.toString());
		}

		log.info(logueo.getSoloParametros());
		return resultado;
	}

	private String tipoPrimerValorCedula(String criterio) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("tipoPrimerValorCedula");

		String resultado = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;

		StringBuffer consultaParcial = new StringBuffer();
		consultaParcial.append("SELECT DATA_TYPE FROM GENV_TAB_COLUMNS WHERE TABLE_NAME = 'CART_CLIENTES' ");
		consultaParcial.append("AND COLUMN_NAME = '" + criterio + "'");

		try {
			conn = crearConexion();
			pst = conn.prepareStatement(consultaParcial.toString());
			result = pst.executeQuery();

			while (result.next()) {
				resultado = result.getString("DATA_TYPE");
			}

		} catch (SQLException e) {
			resultado = null;
			logueo.setException("SQLException");
			logueo.setError(e.getMessage());
			log.error(logueo.getMensaje());
		} catch (Exception ex) {
			resultado = null;
			logueo.setException("Exception");
			logueo.setError(ex.getMessage());
			log.error(logueo.getMensaje());
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	private String tipoPrimerValorNoCedula(String criterio) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("tipoPrimerValorNoCedula");

		String resultado = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;

		StringBuffer consultaParcial = new StringBuffer();
		consultaParcial.append("SELECT DATA_TYPE FROM GENV_TAB_COLUMNS WHERE TABLE_NAME = 'CART_MAE_PERSONAS'");
		consultaParcial.append(" AND COLUMN_NAME = '" + criterio + "'");

		try {
			conn = crearConexion();
			pst = conn.prepareStatement(consultaParcial.toString());
			result = pst.executeQuery();

			while (result.next()) {
				resultado = result.getString("DATA_TYPE");
			}
		} catch (SQLException e) {
			resultado = null;
			logueo.setException("SQLException");
			logueo.setError(e.getMessage());
			log.error(logueo.getMensaje());
		} catch (Exception ex) {
			resultado = null;
			logueo.setException("Exception");
			logueo.setError(ex.getMessage());
			log.error(logueo.getMensaje());
		} finally {

			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	private String tipoSegundoValor(String criterio) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("tipoSegundoValor");

		String resultado = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;

		StringBuffer consultaParcial = new StringBuffer();

		consultaParcial.append("SELECT DATA_TYPE FROM GENV_TAB_COLUMNS WHERE TABLE_NAME = 'CART_MAE_PERSONAS'");
		consultaParcial.append(" AND COLUMN_NAME = '" + criterio + "'");

		try {
			conn = crearConexion();
			pst = conn.prepareStatement(consultaParcial.toString());
			result = pst.executeQuery();

			while (result.next()) {
				resultado = result.getString("DATA_TYPE");
			}
		} catch (SQLException e) {
			resultado = null;
			logueo.setException("SQLException");
			logueo.setError(e.getMessage());
			log.error(logueo.getMensaje());
		} catch (Exception ex) {
			resultado = null;
			logueo.setException("Exception");
			logueo.setError(ex.getMessage());
			log.error(logueo.getMensaje());
		} finally {

			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	private ResultObtenerPersonas consultarPersona(String query) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("consultarPersona");

		ResultObtenerPersonas resultado = new ResultObtenerPersonas();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;

		try {
			conn = crearConexion();
			pst = conn.prepareStatement(query);
			result = pst.executeQuery();

			while (result.next()) {
				String documento = result.getString("CABU_NU_DOCUMENTO");

				if (documento != null) {
					// Persona fisica
					DatosPersonaFisica fisica = new DatosPersonaFisica();
					fisica.setApellido(result.getString("CABU_NM_APELLIDO_RAZON"));
					fisica.setNombre(result.getString("CABU_NM_PERSONA"));
					fisica.setCantRoles(result.getInt("CANTROLES"));
					fisica.setCodPersona(result.getInt("CABU_NU_PERSONA"));
					fisica.setNumDocumento(result.getString("cabu_nu_documento"));
					fisica.setTipoDocumento(result.getString("cabu_catu_tp_documento"));
					if (result.getString("codCliente") != null) {
						fisica.setCodCliente(result.getString("codCliente"));
					}
					resultado.setUnaPersonaFisica(fisica);
				} else {
					// Persona juridica
					DatosPersonaJuridica juridica = new DatosPersonaJuridica();

					juridica.setCantRoles(result.getInt("CANTROLES"));
					juridica.setCodPersona(result.getInt("CABU_NU_PERSONA"));
					juridica.setRazonSocial(result.getString("CABU_NM_APELLIDO_RAZON"));
					juridica.setRuc(result.getString("cabu_nu_cuit"));
					if (result.getString("codCliente") != null) {
						juridica.setCodCliente(result.getString("codCliente"));
					}

					resultado.setUnaPersonaJuridica(juridica);
				}
			}
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	private String operadorLogico(PropertiesManager prop, String operador) {
		String resultado = null;
		String and = prop.obtenerValor("operadorAnd");
		String or = prop.obtenerValor("operadorOr");

		if (operador.equals(and))
			resultado = "and";
		if (operador.equals(or))
			resultado = "or";

		return resultado;
	}

	private String getOperador(PropertiesManager prop, String operador) {

		String retorno = null;

		int cantidadOperadores = 0;

		String ret = prop.obtenerValor("cantidadOperadores");
		if (ret != null && this.isNumeric(ret))
			cantidadOperadores = new Integer(ret).intValue();

		boolean encontrado = false;

		for (int i = 0; i < cantidadOperadores && !encontrado; i++) {
			String aux = prop.obtenerValor("operador-" + i + "-descripcion");
			if (aux.equals(operador)) {
				retorno = prop.obtenerValor("operador-" + i + "-valor");
				encontrado = true;
			}
		}

		return retorno;
	}

	/**
	 * Da de alta una persona fisica
	 * 
	 * @param param
	 * @return Un objeto personaFisica
	 */

	/*
	 * 
	 * 
	 * */

	public ResultPersonaFisica altaPersonaFisica(ParamDatosPersonaFisica param) {
		ResultPersonaFisica resultado = new ResultPersonaFisica();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("altaPersonaFisica");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodPersona", param.getCodPersona());
		logueo.setParametro("Nombre", param.getNombre());
		logueo.setParametro("Apellido", param.getApellido());
		logueo.setParametro("CodigoEstadoCivil", param.getCodEstadoCivil());
		logueo.setParametro("CodigoProfesion", param.getCodProfesion());
		logueo.setParametro("FechaNacimiento", param.getFechaNacimiento());
		logueo.setParametro("TipoDocumento", param.getTipoDocumento());
		logueo.setParametro("NumDocumento", param.getNumDocumento());
		logueo.setParametro("Sexo", param.getSexo());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_mant_persona");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			if (param.getCodPersona() == null) {
				cstmt.setInt(1, this.numAlta);
			} else {

				cstmt.setInt(1, param.getCodPersona());
			}

			cstmt.setString(2, param.getTipoDocumento());
			cstmt.setString(3, param.getNumDocumento());

			cstmt.setString(4, param.getRut());

			cstmt.setString(5, param.getApellido());
			cstmt.setString(6, param.getNombre());

			if (param.getFechaNacimiento() == null) {
				cstmt.setNull(7, java.sql.Types.DATE);
			} else {
				cstmt.setDate(7, toSqlDate(param.getFechaNacimiento()));
			}

			if (param.getSexo() == null) {
				cstmt.setNull(8, java.sql.Types.VARCHAR);
			} else {
				cstmt.setString(8, param.getSexo());
			}

			if (param.getCodEstadoCivil() == null) {
				cstmt.setNull(9, java.sql.Types.VARCHAR);
			} else {
				cstmt.setString(9, param.getCodEstadoCivil());
			}

			if (param.getCodProfesion() == null)
				cstmt.setNull(10, Types.INTEGER);
			else
				cstmt.setInt(10, param.getCodProfesion());

			// Codigo organismo
			cstmt.setNull(11, Types.VARCHAR);
			// Codigo actividad
			cstmt.setNull(12, Types.INTEGER);

			cstmt.setNull(13, Types.INTEGER);

			cstmt.setString(14, param.getUsuario());

			cstmt.registerOutParameter(15, Types.INTEGER);
			cstmt.registerOutParameter(16, Types.VARCHAR);
			cstmt.registerOutParameter(17, Types.VARCHAR);
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.execute();

			int codError = cstmt.getInt(15);
			String descError = cstmt.getString(16);
			String sqlError = cstmt.getString(17);
			int numPersona = cstmt.getInt(1);

			if (codError != 0) {
				ServiciosError error = new ServiciosError();
				error.setCodigo(new Integer(codError));
				error.setDescripcion(descError);
				resultado.setError(error);
				resultado.setHayError(Boolean.TRUE);
			} else {
				PersonaFisica persona = new PersonaFisica();
				persona.setApellido(param.getApellido());
				persona.setCodEstadoCivil(param.getCodEstadoCivil());
				persona.setCodPersona(numPersona);
				persona.setCodProfesion(param.getCodProfesion());
				persona.setFechaNacimiento(param.getFechaNacimiento());
				persona.setNombre(param.getNombre());
				persona.setRut(param.getRut());
				persona.setNumDocumento(param.getNumDocumento());
				persona.setSexo(param.getSexo());
				persona.setTipoDocumento(param.getTipoDocumento());

				resultado.setPersona(persona);
			}

			logueo.setParametro("Codigo salida", codError);
			logueo.setParametro("Descripcion salida", descError);
			logueo.setParametro("Sql salida", sqlError);
			logueo.setParametro("Numero persona", numPersona);

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

	/**
	 * Da de alta una persona juridica
	 * 
	 * @param param
	 * @return Un objeto personaJuridica
	 */
	public ResultPersonaJuridica altaPersonaJuridica(ParamDatosPersonaJuridica param) {
		ResultPersonaJuridica resultado = new ResultPersonaJuridica();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("altaPersonaJuridica");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodigoDireccion", param.getCodDireccion());
		logueo.setParametro("CodActividad", param.getCodActividad());
		logueo.setParametro("CodOrganismo", param.getCodOrganismo());
		logueo.setParametro("RazonSocial", param.getRazonSocial());
		logueo.setParametro("Ruc", param.getRuc());
		logueo.setParametro("FechaInicio", param.getFechaInicio());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_mant_persona");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			if (param.getCodPersona() == null) {
				cstmt.setInt(1, this.numAlta);
			} else {

				cstmt.setInt(1, param.getCodPersona());
			}
			// Tipo de documento
			cstmt.setNull(2, java.sql.Types.VARCHAR);
			// Numero de documento
			cstmt.setNull(3, java.sql.Types.VARCHAR);
			// Ruc
			cstmt.setString(4, param.getRuc());
			// Apellido - Razon social
			cstmt.setString(5, param.getRazonSocial());
			// Nombre
			cstmt.setNull(6, java.sql.Types.VARCHAR);
			// Fecha nacimiento - Fecha de inicio
			if (param.getFechaInicio() == null)
				cstmt.setNull(7, java.sql.Types.DATE);
			else {
				cstmt.setDate(7, toSqlDate(param.getFechaInicio()));
			}

			// Sexo
			cstmt.setNull(8, Types.VARCHAR);
			// Codigo estado civil
			cstmt.setNull(9, Types.VARCHAR);
			// Codigo profesion
			cstmt.setNull(10, Types.INTEGER);
			// Codigo organismo
			if (param.getCodOrganismo() == null)
				cstmt.setNull(11, Types.VARCHAR);
			else
				cstmt.setString(11, param.getCodOrganismo());
			// Codigo actividad
			if (param.getCodActividad() == null)
				cstmt.setNull(12, Types.INTEGER);
			else
				cstmt.setInt(12, param.getCodActividad());

			// Codigo direccion
			if (param.getCodDireccion() == null)
				cstmt.setNull(13, Types.INTEGER);
			else
				cstmt.setInt(13, param.getCodDireccion());

			// Usuario
			cstmt.setString(14, param.getUsuario());

			cstmt.registerOutParameter(15, Types.INTEGER);
			cstmt.registerOutParameter(16, Types.VARCHAR);
			cstmt.registerOutParameter(17, Types.VARCHAR);
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.execute();

			int codError = cstmt.getInt(15);
			String descError = cstmt.getString(16);
			String sqlError = cstmt.getString(17);
			int numPersona = cstmt.getInt(1);

			if (codError != 0) {
				ServiciosError error = new ServiciosError();
				error.setCodigo(new Integer(codError));
				error.setDescripcion(descError);
				resultado.setError(error);
				resultado.setHayError(Boolean.TRUE);
			} else {
				PersonaJuridica persona = new PersonaJuridica();
				if (param.getCodActividad() != null) {
					persona.setCodActividad(param.getCodActividad());
				}
				if (param.getCodOrganismo() != null) {
					persona.setCodOrganismo(param.getCodOrganismo());
				}
				persona.setCodPersona(numPersona);
				if (param.getFechaInicio() != null) {
					persona.setFechaInicio(param.getFechaInicio());
				}
				persona.setRazonSocial(param.getRazonSocial());
				persona.setRuc(param.getRuc());

				resultado.setPersona(persona);
			}

			logueo.setParametro("Codigo salida", codError);
			logueo.setParametro("Descripcion salida", descError);
			logueo.setParametro("Sql salida", sqlError);
			logueo.setParametro("Numero de persona", numPersona);

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

	/**
	 * Modifica una persona fisica
	 * 
	 * @param param
	 * @return Un objeto personaFisica
	 */
	public ResultPersonaFisica modificarPersonaFisica(ParamDatosPersonaFisica param) {
		ResultPersonaFisica resultado = new ResultPersonaFisica();
		CallableStatement cstmt = null;
		Connection conn = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("modificarPersonaFisica");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodPersona", param.getCodPersona());
		logueo.setParametro("CodigoDireccion", param.getCodDireccion());
		logueo.setParametro("Nombre", param.getNombre());
		logueo.setParametro("Apellido", param.getApellido());
		logueo.setParametro("CodigoEstadoCivil", param.getCodEstadoCivil());
		logueo.setParametro("CodigoProfesion", param.getCodProfesion());
		logueo.setParametro("FechaNacimiento", param.getFechaNacimiento());
		logueo.setParametro("TipoDocumento", param.getTipoDocumento());
		logueo.setParametro("NumDocumento", param.getNumDocumento());
		logueo.setParametro("Sexo", param.getSexo());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_mant_persona");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, param.getCodPersona());

			if (param.getTipoDocumento() == null)
				cstmt.setNull(2, java.sql.Types.VARCHAR);
			else
				cstmt.setString(2, param.getTipoDocumento());

			if (param.getNumDocumento() == null)
				cstmt.setNull(3, java.sql.Types.VARCHAR);
			else
				cstmt.setString(3, param.getNumDocumento());

			if (param.getRut() == null)
				cstmt.setNull(4, Types.VARCHAR);
			else
				cstmt.setString(4, param.getRut());

			if (param.getApellido() == null)
				cstmt.setNull(5, java.sql.Types.VARCHAR);
			else
				cstmt.setString(5, param.getApellido());

			if (param.getNombre() == null)
				cstmt.setNull(6, java.sql.Types.VARCHAR);
			else
				cstmt.setString(6, param.getNombre());

			if (param.getFechaNacimiento() == null)
				cstmt.setNull(7, java.sql.Types.DATE);
			else {
				cstmt.setDate(7, toSqlDate(param.getFechaNacimiento()));
			}

			if (param.getSexo() == null)
				cstmt.setInt(8, java.sql.Types.VARCHAR);
			else
				cstmt.setString(8, param.getSexo());

			if (param.getCodEstadoCivil() == null)
				cstmt.setNull(9, java.sql.Types.VARCHAR);
			else
				cstmt.setString(9, param.getCodEstadoCivil());

			if (param.getCodProfesion() == null)
				cstmt.setNull(10, java.sql.Types.INTEGER);
			else
				cstmt.setInt(10, param.getCodProfesion());

			// Codigo organismo
			cstmt.setNull(11, Types.VARCHAR);
			// Codigo actividad
			cstmt.setNull(12, Types.INTEGER);

			if (param.getCodDireccion() == null)
				cstmt.setNull(13, java.sql.Types.INTEGER);
			else
				cstmt.setInt(13, param.getCodDireccion());

			cstmt.setString(14, param.getUsuario());

			cstmt.registerOutParameter(15, Types.INTEGER);
			cstmt.registerOutParameter(16, Types.VARCHAR);
			cstmt.registerOutParameter(17, Types.VARCHAR);
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.execute();

			int codError = cstmt.getInt(15);
			String descError = cstmt.getString(16);
			String sqlError = cstmt.getString(17);
			int numPersona = cstmt.getInt(1);

			if (codError != 0) {
				ServiciosError error = new ServiciosError();
				error.setCodigo(new Integer(codError));
				error.setDescripcion(descError);
				resultado.setError(error);
				resultado.setHayError(Boolean.TRUE);
				logueo.setParametro("PL sql error", sqlError);
			} else {
				PersonaFisica persona = new PersonaFisica();
				persona.setApellido(param.getApellido());
				persona.setCodDireccionHabitual(param.getCodDireccion());
				if (param.getCodEstadoCivil() != null) {
					persona.setCodEstadoCivil(param.getCodEstadoCivil());
				}
				persona.setCodPersona(numPersona);
				persona.setRut(param.getRut());
				if (param.getCodProfesion() != null) {
					persona.setCodProfesion(param.getCodProfesion());
				}
				if (param.getFechaNacimiento() != null) {
					persona.setFechaNacimiento(param.getFechaNacimiento());
				}
				persona.setNombre(param.getNombre());
				persona.setNumDocumento(param.getNumDocumento());
				persona.setSexo(param.getSexo());
				persona.setTipoDocumento(param.getTipoDocumento());

				resultado.setPersona(persona);
			}

			logueo.setParametro("Codigo salida", codError);
			logueo.setParametro("Descripcion salida", descError);
			logueo.setParametro("Sql salida", sqlError);
			logueo.setParametro("Numero persona", numPersona);

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

	/**
	 * Modifica una persona juridica
	 * 
	 * @param param
	 * @return Un objeto personaJuridica
	 */
	public ResultPersonaJuridica modificarPersonaJuridica(ParamDatosPersonaJuridica param) {
		ResultPersonaJuridica resultado = new ResultPersonaJuridica();
		CallableStatement cstmt = null;
		Connection conn = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("modificarPersonaFisica");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodigoDireccion", param.getCodDireccion());
		logueo.setParametro("CodActividad", param.getCodActividad());
		logueo.setParametro("CodOrganismo", param.getCodOrganismo());
		logueo.setParametro("RazonSocial", param.getRazonSocial());
		logueo.setParametro("Ruc", param.getRuc());
		logueo.setParametro("FechaInicio", param.getFechaInicio());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_mant_persona");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, param.getCodPersona());
			// Tipo de documento
			cstmt.setNull(2, java.sql.Types.VARCHAR);
			// Numero de documento
			cstmt.setNull(3, java.sql.Types.VARCHAR);
			// Ruc
			if (param.getRuc() == null)
				cstmt.setNull(4, Types.VARCHAR);
			else
				cstmt.setString(4, param.getRuc());

			// Apellido - Razon social
			if (param.getRazonSocial() == null)
				cstmt.setNull(5, Types.VARCHAR);
			else
				cstmt.setString(5, param.getRazonSocial());
			// Nombre
			cstmt.setNull(6, java.sql.Types.VARCHAR);
			// Fecha nacimiento - Fecha de inicio
			if (param.getFechaInicio() == null)
				cstmt.setNull(7, java.sql.Types.DATE);
			else {
				cstmt.setDate(7, toSqlDate(param.getFechaInicio()));
			}

			// Sexo
			cstmt.setNull(8, Types.VARCHAR);
			// Codigo estado civil
			cstmt.setNull(9, Types.VARCHAR);
			// Codigo profesion
			cstmt.setNull(10, Types.INTEGER);
			// Codigo organismo
			if (param.getCodOrganismo() == null)
				cstmt.setNull(11, Types.VARCHAR);
			else
				cstmt.setString(11, param.getCodOrganismo());
			// Codigo actividad
			if (param.getCodActividad() == null)
				cstmt.setNull(12, Types.INTEGER);
			else
				cstmt.setInt(12, param.getCodActividad());

			// Codigo direccion
			if (param.getCodDireccion() == null)
				cstmt.setNull(13, Types.INTEGER);
			else
				cstmt.setInt(13, param.getCodDireccion());

			// Usuario
			cstmt.setString(14, param.getUsuario());

			cstmt.registerOutParameter(15, Types.INTEGER);
			cstmt.registerOutParameter(16, Types.VARCHAR);
			cstmt.registerOutParameter(17, Types.VARCHAR);
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.execute();

			int codError = cstmt.getInt(15);
			String descError = cstmt.getString(16);
			String sqlError = cstmt.getString(17);
			int numPersona = cstmt.getInt(1);

			if (codError != 0) {
				ServiciosError error = new ServiciosError();
				error.setCodigo(new Integer(codError));
				error.setDescripcion(descError);
				resultado.setError(error);
				resultado.setHayError(Boolean.TRUE);
				logueo.setParametro("PL sql error", sqlError);
			} else {
				PersonaJuridica persona = new PersonaJuridica();
				persona.setCodActividad(param.getCodActividad());
				persona.setCodDireccionHabitual(param.getCodDireccion());
				persona.setCodOrganismo(param.getCodOrganismo());
				persona.setCodPersona(numPersona);
				persona.setFechaInicio(param.getFechaInicio());
				persona.setRazonSocial(param.getRazonSocial());
				persona.setRuc(param.getRuc());

				resultado.setPersona(persona);
			}

			logueo.setParametro("Codigo salida", codError);
			logueo.setParametro("Descripcion salida", descError);
			logueo.setParametro("Sql salida", sqlError);
			logueo.setParametro("Numero persona", numPersona);

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

	/**
	 * Baja de una persona
	 * 
	 * @param param
	 * @return Codigo de error y descripcion en caso de que sea necesario
	 */
	public ResultBajaPersona bajaPersona(ParamDatosBajaPersona param) {
		ResultBajaPersona resultado = new ResultBajaPersona();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("altaPersonaFisica");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodMotivoBaja", param.getCodMotivoBaja());
		logueo.setParametro("CodPersona", param.getCodPersona());
		logueo.setParametro("FechaBaja", param.getFechaBaja());
		logueo.setParametro("TipoOperacion", param.getTipoOperacion());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_baja_persona");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.setInt(1, param.getCodPersona());

			if (param.getFechaBaja() != null) {
				cstmt.setDate(2, toSqlDate(param.getFechaBaja()));
			} else {
				cstmt.setNull(2, Types.DATE);
			}

			if (param.getCodMotivoBaja() == null)
				cstmt.setNull(3, Types.INTEGER);
			else
				cstmt.setInt(3, param.getCodMotivoBaja());

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
				error.setCodigo(new Integer(codError));
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

	/**
	 * Alta de una comunicacion
	 * 
	 * @param param
	 * @return Una lista de comunicaciones
	 */
	public ResultComunicacion altaComunicaciones(ParamAltaComunicacion param) {
		ResultComunicacion resultado = new ResultComunicacion();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("altaComunicaciones");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodPersona", param.getCodPersona());
		logueo.setParametro("Tipo de comunicacion", param.getTipoComunicacion());
		logueo.setParametro("Valor de la comunicacion", param.getValorComunicacion());
		logueo.setParametro("Nota", param.getNota());

		try {

			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_mant_comunicacion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?)}");

			cstmt.setInt(1, this.numAlta);
			cstmt.setInt(2, param.getCodPersona());
			cstmt.setInt(3, param.getTipoComunicacion());
			cstmt.setString(4, param.getValorComunicacion());
			cstmt.setString(5, param.getNota());
			cstmt.setString(6, param.getUsuario());

			cstmt.registerOutParameter(7, Types.INTEGER);
			cstmt.registerOutParameter(8, Types.VARCHAR);
			cstmt.registerOutParameter(9, Types.VARCHAR);
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.execute();

			int codError = cstmt.getInt(7);
			String descError = cstmt.getString(8);
			String sqlError = cstmt.getString(9);
			int consecutivo = cstmt.getInt(1);

			if (codError != 0) {
				ServiciosError error = new ServiciosError();
				error.setCodigo(new Integer(codError));
				error.setDescripcion(descError);
				resultado.setError(error);
				resultado.setHayError(Boolean.TRUE);
			} else {
				// Cargo la salida
				ComunicacionEC comunicacion = new ComunicacionEC();
				comunicacion.setCodComunicacion(consecutivo);
				comunicacion.setTipoComunicacion(param.getTipoComunicacion());
				comunicacion.setValorComunicacion(param.getValorComunicacion());
				comunicacion.setFechaActualizacion(param.getFechaActualizacion());
				comunicacion.setNota(param.getNota());
				resultado.setComunicacionPersona(comunicacion);
			}

			logueo.setParametro("Retorno Consecutivo", consecutivo);
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

	/**
	 * Modifica una o varias comunicaciones
	 * 
	 * @param param
	 * @return una lista de comunicaciones
	 */
	public ResultComunicacion modificarComunicaciones(ParamModificarComunicacion param) {
		ResultComunicacion resultado = new ResultComunicacion();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("modificarComunicaciones");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodPersona", param.getCodPersona());
		logueo.setParametro("Codigo de comunicacion", param.getCodComunicacion());
		logueo.setParametro("Tipo de comunicacion", param.getTipoComunicacion());
		logueo.setParametro("Valor de la comunicacion", param.getValorComunicacion());
		logueo.setParametro("Nota", param.getNota());

		try {

			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_mant_comunicacion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getCodComunicacion());
			cstmt.setInt(2, param.getCodPersona());
			cstmt.setInt(3, param.getTipoComunicacion());
			cstmt.setString(4, param.getValorComunicacion());
			cstmt.setString(5, param.getNota());
			cstmt.setString(6, param.getUsuario());

			cstmt.registerOutParameter(7, Types.INTEGER);
			cstmt.registerOutParameter(8, Types.VARCHAR);
			cstmt.registerOutParameter(9, Types.VARCHAR);
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.execute();

			int codError = cstmt.getInt(7);
			String descError = cstmt.getString(8);
			String sqlError = cstmt.getString(9);
			int consecutivo = cstmt.getInt(1);

			if (codError != 0) {
				ServiciosError error = new ServiciosError();
				error.setCodigo(new Integer(codError));
				error.setDescripcion(descError);
				resultado.setError(error);
				resultado.setHayError(Boolean.TRUE);
			} else {
				// Cargo la salida
				ComunicacionEC comunicacion = new ComunicacionEC();
				comunicacion.setCodComunicacion(consecutivo);
				comunicacion.setTipoComunicacion(param.getTipoComunicacion());
				comunicacion.setValorComunicacion(param.getValorComunicacion());
				comunicacion.setFechaActualizacion(param.getFechaActualizacion());
				comunicacion.setNota(param.getNota());

				resultado.setComunicacionPersona(comunicacion);
			}

			logueo.setParametro("Retorno Consecutivo", consecutivo);
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

	/**
	 * Da de baja una o varias comunicaciones
	 * 
	 * @param param
	 * @return Codigo de error y descripcion en caso de que sea necesario
	 */
	public ResultComunicacionEC bajaComunicaciones(ParamBajaComunicacion param) {
		ResultComunicacionEC resultado = new ResultComunicacionEC();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("bajaComunicaciones");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodPersona", param.getCodPersona());
		logueo.setParametro("Codigo comunicacion", param.getCodComunicacion());

		try {

			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_baja_comunicacion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getCodPersona());
			cstmt.setInt(2, param.getCodComunicacion());
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
				error.setCodigo(new Integer(codError));
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

	/**
	 * Modifica los datos de un rol cliente
	 * 
	 * @param param
	 * @return Codigo de error y descripcion en caso de que sea necesario
	 */
	public ResultRolCliente modificarDatosRolCliente(ParamDatosRolCliente param) {
		ResultRolCliente resultado = new ResultRolCliente();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("modificarDatosRolCliente");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodPersona", param.getCodPersona());
		logueo.setParametro("CodSucursalPago", param.getCodSucursalPago());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_mant_rol");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.setString(1, param.getCodCliente());
			cstmt.setInt(2, param.getCodPersona());

			if (param.getCodSucursalPago() == null)
				cstmt.setNull(3, Types.INTEGER);
			else
				cstmt.setString(3, param.getCodSucursalPago());

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
				error.setCodigo(new Integer(codError));
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

	public ResultDireccion altaDireccion(ParamDatosDireccion param) {
		ResultDireccion resultado = new ResultDireccion();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("altaDireccion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodPersona", param.getCodPersona());
		logueo.setParametro("Aclaraciones", param.getAclaraciones());
		logueo.setParametro("Apto", param.getApto());
		logueo.setParametro("Calle", param.getCalle());
		logueo.setParametro("NumeroPuerta", param.getNumeroPuerta());
		logueo.setParametro("NumPostal", param.getNumPostal());
		logueo.setParametro("Padron", param.getPadron());
		logueo.setParametro("Piso", param.getPiso());
		logueo.setParametro("TipoDireccion", param.getTipoDireccion());
		logueo.setParametro("Unidad", param.getUnidad());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_mant_direccion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getCodPersona());
			cstmt.setInt(2, param.getTipoDireccion());
			cstmt.setInt(3, param.getNumPostal());
			cstmt.setString(4, param.getCalle());
			
			if (param.getNumeroPuerta()==null) {
				//FIXME OIGRES SE PUSO 0 as� lo pide rector
				cstmt.setString(5, "0");
			} else {
				cstmt.setString(5, param.getNumeroPuerta());
			}
			
			if (param.getPiso() == null)
				cstmt.setNull(6, Types.VARCHAR);
			else
				cstmt.setString(6, param.getPiso());

			if (param.getApto() == null)
				cstmt.setNull(7, Types.VARCHAR);
			cstmt.setString(7, param.getApto());

			if (param.getUnidad() == null)
				cstmt.setNull(8, Types.VARCHAR);
			else
				cstmt.setString(8, param.getUnidad());

			if (param.getPadron() == null)
				cstmt.setNull(9, Types.VARCHAR);
			else
				cstmt.setString(9, param.getPadron());

			if (param.getAclaraciones() == null)
				cstmt.setNull(10, Types.VARCHAR);
			else
				cstmt.setString(10, param.getAclaraciones());

			cstmt.setString(11, param.getUsuario());
			cstmt.setInt(12, this.numAlta);

			cstmt.registerOutParameter(12, Types.INTEGER);
			cstmt.registerOutParameter(13, Types.INTEGER);
			cstmt.registerOutParameter(14, Types.VARCHAR);
			cstmt.registerOutParameter(15, Types.VARCHAR);

			cstmt.execute();

			int consecutivo = cstmt.getInt(12);
			int codError = cstmt.getInt(13);
			String descError = cstmt.getString(14);
			String sqlError = cstmt.getString(15);

			if (codError != 0) {
				ServiciosError error = new ServiciosError();
				error.setCodigo(new Integer(codError));
				error.setDescripcion(descError);
				resultado.setError(error);
				resultado.setHayError(Boolean.TRUE);
			} else {
				DireccionEC dir = new DireccionEC();

				dir.setCodDireccion(consecutivo);
				dir.setAclaraciones(param.getAclaraciones());
				dir.setTipoDireccion(param.getTipoDireccion());
				dir.setNumPostal(param.getNumPostal());
				dir.setDireccion(param.getCalle());
				dir.setNumeroPuerta(ValuesUtils.toString(param.getNumeroPuerta()));
				dir.setUnidad(param.getUnidad());
				dir.setPiso(param.getPiso());
				dir.setApto(param.getApto());
				dir.setPadron(param.getPadron());
				dir.setAclaraciones(param.getAclaraciones());

				resultado.setDireccion(dir);
			}

			logueo.setParametro("Retorno Consecutivo", consecutivo);
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

	/**
	 * Da de alta una direccion
	 * 
	 * @param param
	 * @return Un objeto con la direccion creada
	 */
	public ResultDireccion modificarDireccion(ParamDatosDireccion param) {
		ResultDireccion resultado = new ResultDireccion();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("modificarDireccion");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodPersona", param.getCodPersona());
		logueo.setParametro("Aclaraciones", param.getAclaraciones());
		logueo.setParametro("Apto", param.getApto());
		logueo.setParametro("Calle", param.getCalle());
		logueo.setParametro("NumeroPuerta", param.getNumeroPuerta());
		logueo.setParametro("NumPostal", param.getNumPostal());
		logueo.setParametro("Padron", param.getPadron());
		logueo.setParametro("Piso", param.getPiso());
		logueo.setParametro("TipoDireccion", param.getTipoDireccion());
		logueo.setParametro("Unidad", param.getUnidad());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_mant_direccion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getCodPersona());

			if (param.getTipoDireccion() == null)
				cstmt.setNull(2, Types.INTEGER);
			else
				cstmt.setInt(2, param.getTipoDireccion());

			if (param.getNumPostal() == null)
				cstmt.setNull(3, Types.INTEGER);
			else
				cstmt.setInt(3, param.getNumPostal());

			if (param.getCalle() == null)
				cstmt.setNull(4, Types.VARCHAR);
			else
				cstmt.setString(4, param.getCalle());

			if (param.getNumeroPuerta() == null)
				//FIXME OIGRES SE PUSO 0, Asi pide rector.
				cstmt.setString(5, "0");
			else
				cstmt.setString(5, param.getNumeroPuerta());

			if (param.getPiso() == null)
				cstmt.setNull(6, Types.VARCHAR);
			else
				cstmt.setString(6, param.getPiso());

			if (param.getApto() == null)
				cstmt.setNull(7, Types.VARCHAR);
			cstmt.setString(7, param.getApto());

			if (param.getUnidad() == null)
				cstmt.setNull(8, Types.VARCHAR);
			else
				cstmt.setString(8, param.getUnidad());

			if (param.getPadron() == null)
				cstmt.setNull(9, Types.VARCHAR);
			else
				cstmt.setString(9, param.getPadron());

			if (param.getAclaraciones() == null)
				cstmt.setNull(10, Types.VARCHAR);
			else
				cstmt.setString(10, param.getAclaraciones());

			cstmt.setString(11, param.getUsuario());

			if (param.getCodDireccion() == null)
				cstmt.setNull(12, Types.VARCHAR);
			cstmt.setInt(12, param.getCodDireccion());

			cstmt.registerOutParameter(12, Types.INTEGER);
			cstmt.registerOutParameter(13, Types.INTEGER);
			cstmt.registerOutParameter(14, Types.VARCHAR);
			cstmt.registerOutParameter(15, Types.VARCHAR);
			cstmt.execute();

			int consecutivo = cstmt.getInt(12);
			int codError = cstmt.getInt(13);
			String descError = cstmt.getString(14);
			String sqlError = cstmt.getString(15);

			if (codError != 0) {
				ServiciosError error = new ServiciosError();
				error.setCodigo(new Integer(codError));
				error.setDescripcion(descError);
				resultado.setError(error);
				resultado.setHayError(Boolean.TRUE);
			} else {
				DireccionEC dir = new DireccionEC();

				dir.setCodDireccion(consecutivo);
				dir.setAclaraciones(param.getAclaraciones());
				dir.setTipoDireccion(param.getTipoDireccion());
				dir.setNumPostal(param.getNumPostal());
				dir.setDireccion(param.getCalle());

				if (param.getNumeroPuerta() != null)
					dir.setNumeroPuerta(param.getNumeroPuerta());

				dir.setUnidad(param.getUnidad());
				dir.setPiso(param.getPiso());
				dir.setApto(param.getApto());
				dir.setPadron(param.getPadron());
				dir.setAclaraciones(param.getAclaraciones());

				resultado.setDireccion(dir);
			}

			logueo.setParametro("Retorno Consecutivo", consecutivo);
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

	/**
	 * Da de baja una direccion
	 * 
	 * @param param
	 * @return Codigo de error y descripcion en caso de que sea necesario
	 */
	public ResultBajaDireccion bajaDireccion(ParamDatosBajaDireccion param) {
		ResultBajaDireccion resultado = new ResultBajaDireccion();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("bajaDireccion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodPersona", param.getCodPersona());
		logueo.setParametro("CodDireccion", param.getCodDireccion());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_baja_direccion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getCodPersona());
			cstmt.setInt(2, param.getCodDireccion());
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
				error.setCodigo(new Integer(codError));
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

	/**
	 * Consulta de persona fisica
	 * 
	 * @param param
	 * @return Un objeto de persona fisica
	 */
	public ResultPersonaFisica obtenerDatosPersonaFisica(ParamPersona param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("obtenerDatosPersonaFisica()");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodPersona", param.getCodPersona());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultPersonaFisica resultado = new ResultPersonaFisica();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();

			sql.append("SELECT  c.cabu_catu_tp_documento, td.catu_de_documento, c.cabu_nu_documento, ");
			sql.append("c.cabu_nm_apellido_razon, c.cabu_nm_persona, c.cabu_fe_nacimiento,  c.cabu_estado_civil, ");
			sql.append("c.cabu_cado_consecutivo_dire, pd.cado_de_calle, pd.cado_de_numero, ");
			sql.append("c.cabu_camb_cd_motivo_baja, mb.camb_de_motivo_baja, c.cabu_capw_cd_profesion, ");
			sql.append("p.capw_de_profesion, c.cabu_caus_cd_usuario, c.cabu_fe_actualizacion, c.cabu_fe_alta, ");
			sql.append("c.cabu_fe_baja, (select rv_meaning from  cg_ref_codes where  RV_DOMAIN = 'EDO CIVIL' and ");
			sql.append("RV_LOW_VALUE = c.cabu_estado_civil) desc_civil, (select rv_meaning from cg_ref_codes ");
			sql.append("where  RV_DOMAIN = 'SEXO' and RV_LOW_VALUE = c.cabu_st_sexo) desc_sexo, ");
			sql.append("(SELECT CACN_NU_CEDULA_RIF FROM CART_CLIENTES "); // codCliente
			sql.append("WHERE CACN_CABU_NU_PERSONA = CABU_NU_PERSONA) codCliente, c.cabu_nu_cuit, ");
			sql.append("DECODE(NVL(PEP.CPEP_IN_PEP, 0), 0, 'N', 'S') in_pep, TO_CHAR(TO_DATE(PEP.CPEP_FECHA_FORMULARIO, 'DD/MM/YYYY')) fecha_pep ");
			sql.append("from CART_MAE_PERSONAS C, cart_tipos_documentos TD, CART_TIPOS_RAZON TR, CART_PROFESIONES P, ");
			sql.append("CART_MOTIVOS_BAJA MB, CART_PERSONAS_DIRECCIONES PD, CART_PERSONAS_PEP PEP WHERE cabu_nu_persona = ? ");
			sql.append("and c.cabu_catu_tp_documento = td.catu_tp_documento and c.cabu_capw_cd_profesion = p.capw_cd_profesion (+) and ");
			sql.append("c.cabu_camb_cd_motivo_baja = mb.camb_cd_motivo_baja (+) and c.cabu_cado_consecutivo_dire ");
			sql.append("= pd.cado_consecutivo_direccion (+) and c.cabu_nu_persona = pd.cado_nu_persona (+) and ");
			sql.append("c.cabu_catz_cd_razon_social = tr.catz_cd_razon_social and ");
			sql.append("pep.cpep_cabu_nu_persona (+) = c.cabu_nu_persona");

			logueo.setParametro("Consulta", sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, param.getCodPersona());

			result = pst.executeQuery();

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

			while (result.next()) {
				PersonaFisica persona = new PersonaFisica();

				persona.setApellido(result.getString("cabu_nm_apellido_razon"));
				persona.setCodDireccionHabitual(result.getInt("cabu_cado_consecutivo_dire"));
				persona.setCodEstadoCivil(result.getString("cabu_estado_civil"));
				persona.setCodMotivoBaja(result.getInt("cabu_camb_cd_motivo_baja"));
				persona.setCodPersona(param.getCodPersona());
				persona.setCodProfesion(result.getInt("cabu_capw_cd_profesion"));
				persona.setUsuario(result.getString("cabu_caus_cd_usuario"));
				persona.setRut(result.getString("cabu_nu_cuit"));

				if (result.getString("codCliente") != null) {
					persona.setCodCliente(result.getString("codCliente"));
				}
				java.sql.Date actualizacion = result.getDate("cabu_fe_actualizacion");
				if (actualizacion != null)
					persona.setFechaActualizacion(dateFormat.format(actualizacion));

				java.sql.Date alta = result.getDate("cabu_fe_alta");
				if (alta != null)
					persona.setFechaAlta(dateFormat.format(alta));

				java.sql.Date baja = result.getDate("cabu_fe_baja");
				if (baja != null)
					persona.setFechaBaja(dateFormat.format(baja));

				java.sql.Date nacimiento = result.getDate("cabu_fe_nacimiento");
				if (nacimiento != null)
					persona.setFechaNacimiento(dateFormat.format(nacimiento));

				persona.setNombre(result.getString("cabu_nm_persona"));
				persona.setNumDocumento(result.getString("cabu_nu_documento"));
				persona.setSexo(result.getString("DESC_SEXO"));
				persona.setTipoDocumento(result.getString("cabu_catu_tp_documento"));
				persona.setDescripTipoDocumento(result.getString("catu_de_documento"));

				if (persona.getCodDireccionHabitual() != null) {
					String descCalle = result.getString("cado_de_calle");
					String descNumero = result.getString("cado_de_numero");

					persona.setDescripDireccionHabitual(descCalle + " " + descNumero);
				}

				persona.setDescripEstadoCivil(result.getString("desc_civil"));
				persona.setDescripProfesion(result.getString("capw_de_profesion"));
				persona.setDescripMotivoBaja(result.getString("camb_de_motivo_baja"));

				persona.setFechaDiligencia(result.getString("fecha_pep"));
				persona.setEsPEP(result.getString("in_pep"));

				resultado.setPersona(persona);
			}
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	/**
	 * Consulta de persona juridica
	 * 
	 * @param param
	 * @return Un objeto de persona juridica
	 */
	public ResultPersonaJuridica obtenerDatosPersonaJuridica(ParamPersona param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("obtenerDatosPersonaJuridica()");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodPersona", param.getCodPersona());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultPersonaJuridica resultado = new ResultPersonaJuridica();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();

			sql.append("SELECT c.cabu_nu_cuit, c.cabu_nm_apellido_razon, c.cabu_fe_nacimiento, ");
			sql.append("c.cabu_caae_cd_actividad, ac.caae_de_actividad, c.cabu_cado_consecutivo_dire, ");
			sql.append("pd.cado_de_calle, pd.cado_de_numero, c.cabu_camb_cd_motivo_baja, ");
			sql.append("mb.camb_de_motivo_baja, c.cabu_catg_cd_tipo_organismo, tpo.catg_de_tipo_organismo, ");
			sql.append("c.cabu_caus_cd_usuario, c.cabu_fe_actualizacion, c.cabu_fe_alta, c.cabu_fe_baja, ");
			sql.append("(SELECT CACN_NU_CEDULA_RIF FROM CART_CLIENTES "); // codCliente
			sql.append("WHERE CACN_CABU_NU_PERSONA = CABU_NU_PERSONA) codCliente, ");
			sql.append("DECODE(NVL(PEP.CPEP_IN_PEP, 0), 0, 'N', 'S') in_pep, TO_CHAR(TO_DATE(PEP.CPEP_FECHA_FORMULARIO, 'DD/MM/YYYY')) fecha_pep ");
			sql.append("from CART_MAE_PERSONAS C, CART_TIPOS_RAZON TR, CART_TIPOS_ORGANISMOS tpo, ");
			sql.append("CART_MOTIVOS_BAJA MB, CART_ACTIVIDADES ac, CART_PERSONAS_DIRECCIONES PD, CART_PERSONAS_PEP PEP ");
			sql.append("WHERE cabu_nu_persona = ? and c.cabu_catg_cd_tipo_organismo ");
			sql.append("= tpo.catg_cd_tipo_organismo (+) and c.cabu_camb_cd_motivo_baja = ");
			sql.append("mb.camb_cd_motivo_baja (+) and c.cabu_caae_cd_actividad = ac.caae_cd_actividad ");
			sql.append("(+) and c.cabu_cado_consecutivo_dire = pd.cado_consecutivo_direccion (+) and ");
			sql.append("c.cabu_nu_persona = pd.cado_nu_persona (+) and c.cabu_catz_cd_razon_social ");
			sql.append("= tr.catz_cd_razon_social and ");
			sql.append("pep.cpep_cabu_nu_persona (+) = c.cabu_nu_persona");

			logueo.setParametro("Consulta", sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, param.getCodPersona());

			result = pst.executeQuery();

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

			while (result.next()) {
				PersonaJuridica persona = new PersonaJuridica();

				persona.setCodActividad(result.getInt("cabu_caae_cd_actividad"));
				persona.setCodDireccionHabitual(result.getInt("cabu_cado_consecutivo_dire"));
				persona.setCodMotivoBaja(result.getInt("cabu_camb_cd_motivo_baja"));
				persona.setCodOrganismo(result.getString("cabu_catg_cd_tipo_organismo"));
				persona.setCodPersona(param.getCodPersona());
				if (result.getString("codCliente") != null) {
					persona.setCodCliente(result.getString("codCliente"));
				}
				java.sql.Date actualizacion = result.getDate("cabu_fe_actualizacion");
				if (actualizacion != null)
					persona.setFechaActualizacion(dateFormat.format(actualizacion));

				java.sql.Date alta = result.getDate("cabu_fe_alta");
				if (alta != null)
					persona.setFechaAlta(dateFormat.format(alta));

				java.sql.Date baja = result.getDate("cabu_fe_baja");
				if (baja != null)
					persona.setFechaBaja(dateFormat.format(baja));

				java.sql.Date nacimiento = result.getDate("cabu_fe_nacimiento");
				if (nacimiento != null)
					persona.setFechaInicio(dateFormat.format(nacimiento));

				persona.setRazonSocial(result.getString("cabu_nm_apellido_razon"));
				persona.setRuc(result.getString("cabu_nu_cuit"));
				persona.setDescripActividad(result.getString("caae_de_actividad"));
				persona.setDescripMotivoBaja(result.getString("camb_de_motivo_baja"));
				persona.setDescripOrganismo(result.getString("catg_de_tipo_organismo"));
				persona.setUsuario(result.getString("cabu_caus_cd_usuario"));

				if (persona.getCodDireccionHabitual() != null) {
					String descCalle = result.getString("cado_de_calle");
					String descNumero = result.getString("cado_de_numero");

					persona.setDescripDireccionHabitual(descCalle + " " + descNumero);
				}

				persona.setFechaDiligencia(result.getString("fecha_pep"));
				persona.setEsPEP(result.getString("in_pep"));
				resultado.setPersona(persona);
			}
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	/**
	 * Consulta las direcciones de una persona
	 * 
	 * @param param
	 * @return Una lista de direcciones
	 */
	public ResultObtenerDirecciones obtenerDireccionesDeUnaPersona(ParamPersona param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("obtenerDireccionesDeUnaPersona");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodPersona", param.getCodPersona());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultObtenerDirecciones resultado = new ResultObtenerDirecciones();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();

			sql.append("select d.cado_consecutivo_direccion, ");
			sql.append("d.cado_catd_cd_tipo_direccion, ");
			sql.append("td.catd_de_tipo_direccion, ");
			sql.append("d.cado_fe_actualizacion, ");
			sql.append("cp.gecp_cd_codigo_postal, ");
			sql.append("cp.gecp_de_localidad, ");
			sql.append("d.cado_de_calle, ");
			sql.append("d.cado_de_numero, ");
			sql.append("d.cado_de_fax, ");
			sql.append("NVL((select 'S' ");
			sql.append("from CART_CERTIFICADOS, CART_ROL_CLIENTES ");
			sql.append("where CACN_CABU_NU_PERSONA = ? ");
			sql.append("AND CACE_CACN_CD_NACIONALIDAD = CACN_CD_NACIONALIDAD ");
			sql.append("AND CACE_CACN_NU_CEDULA_RIF = CACN_NU_CEDULA_RIF ");
			sql.append("AND (CACE_CADO_NU_DIRE_ENVIO = cado_consecutivo_direccion OR ");
			sql.append("CACE_CADO_NU_DIRE_COBRO = cado_consecutivo_direccion) ");
			sql.append("AND ROWNUM = 1), ");
			sql.append("'N') tiene_poliza ");
			sql.append("from CART_PERSONAS_DIRECCIONES d, ");
			sql.append("GENT_CODIGOS_POSTALES     cp, ");
			sql.append("CART_TIPOS_DIRECCION      td ");
			sql.append("where d.cado_nu_persona = ? ");
			sql.append("and td.CATD_CD_TIPO_DIRECCION = d.CADO_CATD_CD_TIPO_DIRECCION ");
			sql.append("and cp.GECP_NU_POSTAL = d.CADO_GECP_NU_POSTAL ");
			sql.append("Order By d.cado_catd_cd_tipo_direccion ASC, d.cado_consecutivo_direccion ");

			logueo.setParametro("Consulta", sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, param.getCodPersona());
			pst.setInt(2, param.getCodPersona());

			result = pst.executeQuery();

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

			while (result.next()) {
				DatosDirecciones direccion = new DatosDirecciones();

				direccion.setDescripTipoDomicilio(result.getString("catd_de_tipo_direccion"));

				String deCalle = result.getString("cado_de_calle");
				String deNumero = result.getString("cado_de_numero");
				String codPostal = result.getString("gecp_cd_codigo_postal");
				String deLocalidad = result.getString("gecp_de_localidad");

				direccion.setDireccion(deCalle + " " + deNumero + " " + codPostal + " " + deLocalidad);

				java.sql.Date actualizacion = result.getDate("cado_fe_actualizacion");
				if (actualizacion != null)
					direccion.setFechaActualizacion(dateFormat.format(actualizacion));

				direccion.setCodDireccion(result.getInt("cado_consecutivo_direccion"));
				direccion.setTipoDomicilio(result.getInt("cado_catd_cd_tipo_direccion"));
				direccion.setTienePolizasAsociadas(result.getString("tiene_poliza"));
				direccion.setAclaraciones(result.getString("cado_de_fax"));

				resultado.setUnaDireccion(direccion);
			}
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	/**
	 * Consulta de una direccion
	 * 
	 * @param param
	 * @return Retorna un objeto direccionEC
	 */
	public ResultDireccion obtenerDatosDeUnaDireccion(ParamObtenerDireccion param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("obtenerDatosDeUnaDireccion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodPersona", param.getCodPersona());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultDireccion resultado = new ResultDireccion();

		CallableStatement cstmt = null;
		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();

			sql.append("select d.CADO_CATD_CD_TIPO_DIRECCION, td.CATD_DE_TIPO_DIRECCION, d.CADO_GECP_NU_POSTAL, ");
			sql.append("d.CADO_DE_CALLE, d.cado_de_departamento, d.cado_de_piso, d.cado_de_unidad, d.cado_de_numero, ");
			sql.append("d.cado_nu_padron_catastral, d.cado_de_fax from CART_PERSONAS_DIRECCIONES d, ");
			sql.append("CART_TIPOS_DIRECCION td where d.cado_nu_persona = ? and ");
			sql.append("d.cado_consecutivo_direccion = ? and td.CATD_CD_TIPO_DIRECCION = ");
			sql.append("d.cado_catd_cd_tipo_direccion");

			logueo.setParametro("Consulta", sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, param.getCodPersona());
			pst.setInt(2, param.getCodDireccion());

			result = pst.executeQuery();
			Integer numPostal = 0;

			String nombrePL = obtenerValor("proc_obtenerDatosDireccion");
			logueo.setNombrePl(nombrePL);
			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			while (result.next()) {
				DireccionEC direccion = new DireccionEC();
				direccion.setCodDireccion(param.getCodDireccion());
				numPostal = result.getInt("CADO_GECP_NU_POSTAL");
				direccion.setTipoDireccion(result.getInt("CADO_CATD_CD_TIPO_DIRECCION"));
				direccion.setDescripTipoDirecion(result.getString("CATD_DE_TIPO_DIRECCION"));
				direccion.setNumPostal(result.getInt("CADO_GECP_NU_POSTAL"));
				direccion.setDireccion(result.getString("CADO_DE_CALLE"));
				direccion.setApto(result.getString("cado_de_departamento"));
				direccion.setPiso(result.getString("cado_de_piso"));
				direccion.setUnidad(result.getString("cado_de_unidad"));
				direccion.setNumeroPuerta(result.getString("cado_de_numero"));
				direccion.setPadron(result.getString("cado_nu_padron_catastral"));
				// FIXME OIGRES PDP-1091 No Mostrar el valos de las aclaraciones.
				direccion.setAclaraciones(result.getString("cado_de_fax"));

				cstmt.setInt(1, numPostal);

				cstmt.registerOutParameter(2, Types.INTEGER);
				cstmt.registerOutParameter(3, Types.INTEGER);
				cstmt.registerOutParameter(4, Types.VARCHAR);
				cstmt.registerOutParameter(5, Types.INTEGER);
				cstmt.registerOutParameter(6, Types.VARCHAR);
				cstmt.registerOutParameter(7, Types.INTEGER);
				cstmt.registerOutParameter(8, Types.VARCHAR);
				cstmt.registerOutParameter(9, Types.INTEGER);
				cstmt.registerOutParameter(10, Types.VARCHAR);
				cstmt.registerOutParameter(11, Types.INTEGER);
				cstmt.registerOutParameter(12, Types.INTEGER);
				cstmt.registerOutParameter(13, Types.VARCHAR);

				cstmt.execute();

				direccion.setNumRadio(cstmt.getInt(2));
				cstmt.setNull(3, Types.INTEGER);
				direccion.setDescripDepto(cstmt.getString(8));
				direccion.setCodLocalidad(cstmt.getInt(5));
				direccion.setDescripLocalidad(cstmt.getString(6));
				direccion.setCodDepto(cstmt.getInt(7));
				direccion.setDescripRadio(cstmt.getString(4));
				direccion.setCodPais(cstmt.getInt(9));
				direccion.setDescripPais(cstmt.getString(10));
				cstmt.setNull(11, Types.INTEGER);
				cstmt.setNull(12, Types.INTEGER);
				cstmt.setNull(13, Types.VARCHAR);

				resultado.setDireccion(direccion);
				cstmt.clearParameters();
			}

			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(cstmt);
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	/**
	 * Consulta los roles de una persona
	 * 
	 * @param param
	 * @return Una lista de roles
	 */
	public ResultRoles obtenerRolesDeUnaPersona(ParamPersona param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("obtenerRolesDeUnaPersona()");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodPersona", param.getCodPersona());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultRoles resultado = new ResultRoles();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();

			sql.append("select r.cacl_catl_cd_tipo_rol, tr.catl_de_tipo_rol ");
			sql.append("FROM CART_PERSONAS_ROLES r, CART_TIPOS_ROL tr ");
			sql.append("where r.cacl_cabu_nu_persona = ? and r.cacl_catl_cd_tipo_rol ");
			sql.append("= tr.catl_cd_tipo_rol");

			logueo.setParametro("Consulta", sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, param.getCodPersona());

			result = pst.executeQuery();

			while (result.next()) {
				Rol rol = new Rol();

				rol.setDescripRol(result.getString("catl_de_tipo_rol"));
				rol.setTipoRol(result.getInt("cacl_catl_cd_tipo_rol"));

				resultado.setUnRol(rol);
			}
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	/**
	 * Consulta las comunicaciones de una persona
	 * 
	 * @param param
	 * @return Una lista de datos de comunicaciones
	 */
	public ResultComunicaciones obtenerComunicacionesDeUnaPersona(ParamPersona param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("obtenerComunicacionesDeUnaPersona");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodPersona", param.getCodPersona());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultComunicaciones resultado = new ResultComunicaciones();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();

			sql.append("select co.CACF_NU_CONSECUTIVO, ");
			sql.append("co.CACF_CATN_CD_TIPO_COMUNICACION, ");
			sql.append("tc.catn_de_tipo_comunicacion, ");
			sql.append("co.cacf_nota, ");
			sql.append("co.CACF_DE_COMUNICACION, ");
			sql.append("co.CACF_FE_ACTUALIZACION, ");
			sql.append("NVL(co.cacf_com_validada,'N') contacto_validado ");
			sql.append("FROM CART_PERSONAS_COMUNICACION co, CART_TIPOS_COMUNICACION tc ");
			sql.append("where CACF_CABU_NU_PERSONA = ? ");
			sql.append("and co.cacf_catn_cd_tipo_comunicacion = tc.catn_cd_tipo_comunicacion ");

			logueo.setParametro("Consulta", sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, param.getCodPersona());

			result = pst.executeQuery();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

			while (result.next()) {
				DatosComunicacion com = new DatosComunicacion();

				com.setCodComunicacion(result.getInt("CACF_NU_CONSECUTIVO"));
				com.setDescripComunicacion(result.getString("CACF_DE_COMUNICACION"));

				java.sql.Date actualizacion = result.getDate("CACF_FE_ACTUALIZACION");
				if (actualizacion != null)
					com.setFechaActualizacion(dateFormat.format(actualizacion));

				com.setTipoComunicacion(result.getInt("CACF_CATN_CD_TIPO_COMUNICACION"));
				com.setValorComunicacion(result.getString("catn_de_tipo_comunicacion"));
				com.setNota(result.getString("cacf_nota"));
				com.setContactoValido(result.getString("contacto_validado"));

				resultado.setUnaComunicacion(com);
			}
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	/**
	 * Activa una persona
	 * 
	 * @param param
	 * @return Codigo de error y descripcion en caso de que sea necesario
	 */
	public ResultActivarPersona activarPersona(ParamDatosActivarPersona param) {
		ResultActivarPersona resultado = new ResultActivarPersona();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("activarPersona");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodPersona", param.getCodPersona());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_baja_persona");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getCodPersona());
			cstmt.setNull(2, Types.DATE);
			cstmt.setNull(3, Types.INTEGER);
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
				error.setCodigo(new Integer(codError));
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

	private String getCampoCriterio(PropertiesManager prop, String criterio, String numCriterio) {

		String retorno = null;

		int cantidadCriterios = 0;

		String ret = prop.obtenerValor("Cantidad" + numCriterio);
		if (ret != null && this.isNumeric(ret))
			cantidadCriterios = new Integer(ret).intValue();

		boolean encontrado = false;

		for (int i = 0; i < cantidadCriterios && !encontrado; i++) {
			String aux = prop.obtenerValor(numCriterio + "-" + i + "-descripcion");
			if (aux.equals(criterio)) {
				retorno = prop.obtenerValor(numCriterio + "-" + i + "-valor");
				encontrado = true;
			}
		}

		return retorno;
	}

	public ResultValidarCotizacion validarCotizacion(ParamValidarCotizacion param) {
		ResultValidarCotizacion resultado = new ResultValidarCotizacion();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("validarCotizacion");

		logueo.setParametro("Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());

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

	public ResultXmlPL emitirPoliza(ParamEmision param) {
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("emitirPoliza()");

		logueo.setParametro("Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Plan de pago", param.getPlanPago());
		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_emitir_cotizacion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,)}");

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

	public ResultListaSexo listaSexo(ParamListaSexo param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Logueo.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("listaSexo");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaSexo resultado = new ResultListaSexo();

		try {
			conn = crearConexion();

			String sql = "select rv_low_value, rv_meaning from cg_ref_codes where rv_domain = 'SEXO'";

			logueo.setParametro("Consulta", sql);
			pst = conn.prepareStatement(sql);

			result = pst.executeQuery();

			while (result.next()) {
				Sexo obj = new Sexo();
				obj.setCodigo(result.getString("rv_low_value"));
				obj.setDescripcion(result.getString("rv_meaning"));

				resultado.setUnSexo(obj);
			}
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}
		return resultado;
	}

	public ResultXmlPL obtenerPersonasClientes(ParamObtenerPersonasClientes param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("obtenerPersonasClientes");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Apellido o razon", param.getApellidoRazon());
		logueo.setParametro("Nombre", param.getNombre());
		logueo.setParametro("Codigo cliente", param.getCodCliente());
		logueo.setParametro("RUT", param.getRut());
		logueo.setParametro("Tipo documento", param.getTipoDoc());
		logueo.setParametro("Numero documento", param.getNumDocumento());
		logueo.setParametro("Tipo persona", param.getTipoPersona());
		logueo.setParametro("Todos los clientes", param.getTodosLosClientes());
		logueo.setParametro("Telefono", param.getTel());
		logueo.setParametro("Orden", param.getOrden());
		logueo.setParametro("Codigo productor", param.getCodProductor());
		log.info(logueo.getSoloParametros());
		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerPersonasClientes");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, param.getNombre());
			cstmt.setString(2, param.getApellidoRazon());
			cstmt.setString(3, param.getCodCliente());
			cstmt.setString(4, param.getRut());
			cstmt.setString(5, param.getTipoDoc());
			cstmt.setString(6, param.getNumDocumento());
			cstmt.setString(7, param.getTipoPersona());
			cstmt.setString(8, param.getUsuario());

			if (param.getCodProductor() == null) {
				cstmt.setNull(9, Types.INTEGER);
			} else {
				cstmt.setInt(9, Integer.valueOf(param.getCodProductor()));
			}

			cstmt.setString(10, param.getTodosLosClientes());
			cstmt.setString(11, param.getTel());

			if (param.getOrden() != null) {
				cstmt.setInt(12, Integer.valueOf(param.getOrden()));
			} else {
				cstmt.setNull(12, Types.INTEGER);
			}

			cstmt.registerOutParameter(13, Types.INTEGER);
			cstmt.registerOutParameter(14, Types.VARCHAR);
			cstmt.registerOutParameter(15, Types.VARCHAR);
			cstmt.registerOutParameter(16, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(13);
			String descError = cstmt.getString(14);
			String sqlError = cstmt.getString(15);
			Clob clob = cstmt.getClob(16);
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

	public ResultAltaRolCliente altaRolCliente(ParamAltaRolCliente param) {
		ResultAltaRolCliente resultado = new ResultAltaRolCliente();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("altaRolCliente");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero persona", param.getNumPersona());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_altaRolCliente");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getNumPersona()));
			cstmt.setString(2, param.getUsuario());

			cstmt.registerOutParameter(3, Types.INTEGER);
			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.execute();

			resultado.setCodCliente(cstmt.getString(3));

			int codError = cstmt.getInt(4);
			String descError = cstmt.getString(5);
			String sqlError = cstmt.getString(6);

			if (codError != 0) {
				ServiciosError error = new ServiciosError();
				error.setCodigo(new Integer(codError));
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

	public ResultObtenerPersonasAlta obtenerPersonasAlta(ParamObtenerPersonasAlta param) {
		ResultObtenerPersonasAlta resultado = new ResultObtenerPersonasAlta();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("obtenerPersonasAlta");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Apellido/Razon", param.getApellidoRazon());
		logueo.setParametro("Nombre", param.getNombre());
		logueo.setParametro("RUT", param.getRut());
		logueo.setParametro("Tipo documento", param.getTipoDoc());
		logueo.setParametro("Numero documento", param.getNumDocumento());
		logueo.setParametro("Tipo persona", param.getTipoPersona());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerPersonasAlta");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, param.getNombre());
			cstmt.setString(2, param.getApellidoRazon());
			cstmt.setString(3, param.getTipoPersona());
			cstmt.setString(4, param.getRut());
			cstmt.setString(5, param.getTipoDoc());
			cstmt.setString(6, param.getNumDocumento());

			cstmt.registerOutParameter(7, Types.INTEGER);
			cstmt.registerOutParameter(8, Types.INTEGER);
			cstmt.registerOutParameter(9, Types.INTEGER);
			cstmt.registerOutParameter(10, Types.VARCHAR);
			cstmt.registerOutParameter(11, Types.VARCHAR);
			cstmt.execute();

			resultado.setNumPersona(cstmt.getInt(7));
			resultado.setCodCliente(cstmt.getString(8));

			int codError = cstmt.getInt(9);
			String descError = cstmt.getString(10);
			String sqlError = cstmt.getString(11);

			if (codError != 0) {
				ServiciosError error = new ServiciosError();
				error.setCodigo(new Integer(codError));
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

	public ResultXmlPL listaRadio(ParamRadio param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("listaRadio");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo pais", param.getCodPais());
		logueo.setParametro("Codigo departamento", param.getCodDepartamento());
		logueo.setParametro("Codigo localidad", param.getCodLocalidad());
		logueo.setParametro("Numero postal", param.getNumPostal());
		logueo.setParametro("Numero radio", param.getNumRadio());
		logueo.setParametro("Codigo calle", param.getCodCalle());
		logueo.setParametro("Descripcion calle", param.getDescripCalle());
		logueo.setParametro("Numero puerta", param.getNumPuerta());
		logueo.setParametro("Descripcion departamento", param.getDescripDepartamento());
		logueo.setParametro("Descripcion localidad", param.getDescripLocalidad());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_listaRadio");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, param.getCodPais());
			cstmt.setString(2, param.getCodDepartamento());
			cstmt.setString(3, param.getCodLocalidad());

			if (param.getNumPostal() != null) {
				cstmt.setInt(4, Integer.valueOf(param.getNumPostal()));
			} else {
				cstmt.setNull(4, Types.INTEGER);
			}

			if (param.getNumRadio() != null) {
				cstmt.setInt(5, Integer.valueOf(param.getNumRadio()));
			} else {
				cstmt.setNull(5, Types.INTEGER);
			}

			if (param.getCodCalle() != null) {
				cstmt.setInt(6, Integer.valueOf(param.getCodCalle()));
			} else {
				cstmt.setNull(6, Types.INTEGER);
			}

			cstmt.setString(7, param.getDescripCalle());

			if (param.getNumPuerta() != null) {
				cstmt.setInt(8, Integer.valueOf(param.getNumPuerta()));
			} else {
				cstmt.setNull(8, Types.INTEGER);
			}
			cstmt.setString(9, param.getDescripDepartamento());
			cstmt.setString(10, param.getDescripLocalidad());

			cstmt.registerOutParameter(11, Types.INTEGER);
			cstmt.registerOutParameter(12, Types.VARCHAR);
			cstmt.registerOutParameter(13, Types.VARCHAR);
			cstmt.registerOutParameter(14, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(11);
			String descError = cstmt.getString(12);
			String sqlError = cstmt.getString(13);

			if (codError == 0) {
				Herramientas herramientas = new Herramientas();
				resultado.setXml(herramientas.convertirClob(cstmt.getClob(14)));
			} else {
				ServiciosError error = new ServiciosError();
				error.setCodigo(Integer.valueOf(codError));
				error.setDescripcion(descError);

				resultado.setError(error);
				resultado.setHayError(Boolean.TRUE);
			}

			logueo.setParametro("Xml", "XML finalizado listado radio");
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

	public ResultPersonaCorredor obtenerPersonaCorredor(ParamPersonaCorredor param) {
		ResultPersonaCorredor resultado = new ResultPersonaCorredor();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("obtenerPersonaCorredor");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Id corredor", param.getIdCorredor());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("fun_obtenerPersonaCorredor");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{? = call " + nombrePL + "(?)}");

			cstmt.registerOutParameter(1, java.sql.Types.INTEGER);

			cstmt.setString(2, param.getIdCorredor());

			cstmt.execute();

			Integer codPersona = cstmt.getInt(1);

			resultado.setCodPersona(ValuesUtils.toString(codPersona));

			logueo.setParametro("codPersona", codPersona);

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

	public ResultXmlPL obtenerPolizasAsociadas(ParamPolizasAsociadas param) {
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("obtenerPolizasAsociadas");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo cliente", param.getCodCliente());
		logueo.setParametro("Codigo direccion", param.getCodDireccion());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerPolizasAsociadas");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");

			cstmt.setString(1, param.getCodCliente());

			cstmt.setInt(2, param.getCodDireccion());

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

	public ResultXmlPL obtenerPolizasAsociadasClientes(ParamPolizasAsociadasClientes param) {
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("obtenerPolizasAsociadasClientes");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo cliente", param.getCodCliente());
		logueo.setParametro("Codigo direccion", param.getCodDireccion());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerPolizasAsociadasClientes");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");

			cstmt.setString(1, param.getCodCliente());

			cstmt.setInt(2, param.getCodDireccion());

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

	public ResultDatoBancario modificarDatosBancarios(ParamModificarDatoBancario param) {
		ResultDatoBancario resultado = new ResultDatoBancario();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("modificarDatosBancarios");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero consecutivo", param.getNumCons());
		logueo.setParametro("Numero de persona", param.getCodPersona());
		logueo.setParametro("Codigo de banco", param.getCodBanco());
		logueo.setParametro("Tipo de cuenta", param.getTipoCuenta());
		logueo.setParametro("Tipo de tarjeta", param.getTipoTarjeta());
		logueo.setParametro("Numero de cuenta", param.getNumcuenta());
		logueo.setParametro("Fecha de vencimiento", param.getFechaVencimiento());

		try {

			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_mant_datos_bancarios");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?)}");

			cstmt.setNull(1, Types.INTEGER);
			cstmt.setInt(2, param.getCodPersona());
			cstmt.setInt(3, param.getCodBanco());
			cstmt.setInt(4, param.getTipoCuenta());
			cstmt.setInt(5, param.getTipoTarjeta());
			cstmt.setString(6, param.getNumcuenta());
			if (param.getFechaVencimiento() != null) {
				cstmt.setDate(7, toSqlDate(param.getFechaVencimiento()));
			} else {
				cstmt.setNull(7, Types.DATE);
			}
			cstmt.setString(8, param.getUsuario());

			cstmt.registerOutParameter(9, Types.INTEGER);
			cstmt.registerOutParameter(10, Types.VARCHAR);
			cstmt.registerOutParameter(11, Types.VARCHAR);
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.execute();

			int codError = cstmt.getInt(9);
			String descError = cstmt.getString(10);
			String sqlError = cstmt.getString(11);
			int consecutivo = cstmt.getInt(1);

			if (codError != 0) {
				ServiciosError error = new ServiciosError();
				error.setCodigo(new Integer(codError));
				error.setDescripcion(descError);
				resultado.setError(error);
				resultado.setHayError(Boolean.TRUE);
			} else {
				// Cargo la salida
				DatoBancario datoBancario = new DatoBancario();
				datoBancario.setNumConsecutivo(consecutivo);

				resultado.setDatoBancario(datoBancario);
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

	/**
	 * Borra un dato bancario
	 * 
	 * @param param
	 * @return borra un dato bancario
	 */
	public ResultDatoBancario borrarDatosBancarios(ParamBorrarDatoBancario param) {
		ResultDatoBancario resultado = new ResultDatoBancario();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("borrarDatosBancarios");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		logueo.setParametro("Numero consecutivo", param.getNumCons());
		logueo.setParametro("Numero de persona", param.getCodPersona());

		try {

			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_baja_datos_bancarios");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getCodPersona());
			cstmt.setInt(2, param.getNumCons());
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
				error.setCodigo(new Integer(codError));
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

	/**
	 * Consulta los datos bancarios de tipo 5 (tarjeta de credito) de una persona
	 * 
	 * @param param
	 * @return Una lista de datos bancarios
	 */
	public ResultObtenerDatosBancariosPersona obtenerDatosBancariosPersona(ParamObtenerDatosBancariosPersona param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("obtenerDatosBancarios()");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("CodPersona", param.getCodCliente());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultObtenerDatosBancariosPersona resultado = new ResultObtenerDatosBancariosPersona();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();

			sql.append("SELECT CADM_NU_DOMICILIO NU_DOMICILIO,CADM_CABA_CD_BANCO COD_BANCO,");
			sql.append("(select CABA_DE_BANCO");
			sql.append("   from cart_bancos ");
			sql.append("   where CABA_CD_BANCO = CADM_CABA_CD_BANCO) DESC_BANCO, ");
			sql.append("CADM_TP_CUENTA TP_CUENTA,");
			sql.append(" (SELECT SUBSTR(RV_MEANING,1,30)  RV_MEANING ");
			sql.append("   FROM    CG_REF_CODES ");
			sql.append(" WHERE  RV_DOMAIN LIKE 'CART_DOMICILIOS_BANCARIOS.CADM_TP_CUENTA'");
			sql.append("    and RV_LOW_VALUE = CADM_TP_CUENTA) DESC_TP_CUENTA,   ");
			sql.append("CADM_CATT_TP_TARJETA TP_TARJETA,");
			sql.append("(SELECT CATT_DE_TARJETA ");
			sql.append("  FROM CART_TIPOS_TARJETAS ");
			sql.append("WHERE CATT_TP_TARJETA = CADM_CATT_TP_TARJETA) DESC_TP_TARJETA,");
			sql.append("CADM_NU_CUENTA,");
			sql.append("CADM_FE_VENCIMIENTO");
			sql.append(" FROM CART_DOMICILIOS_BANCARIOS");
			sql.append(" WHERE CADM_CABU_NU_PERSONA = ?");
			sql.append(" AND CADM_TP_CUENTA = 5");

			String consulta = sql.toString();

			logueo.setParametro("Consulta obtenerDatosBancariosPersona ::", consulta);
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, param.getCodCliente());

			result = pst.executeQuery();

			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

			while (result.next()) {
				DatosBanco datoBancario = new DatosBanco();
				datoBancario.setCodBanco(result.getInt("COD_BANCO"));
				datoBancario.setDescripcionBanco(result.getString("DESC_BANCO"));
				datoBancario.setDescripOrigenTarjeta(result.getString("DESC_TP_TARJETA"));
				datoBancario.setNumCuenta(result.getString("CADM_NU_CUENTA"));
				datoBancario.setNumDomicilio(result.getInt("NU_DOMICILIO"));
				datoBancario.setTipoTarjeta(result.getString("TP_TARJETA"));

				java.sql.Date vencimiento = result.getDate("CADM_FE_VENCIMIENTO");
				if (vencimiento != null)
					datoBancario.setFechaVencimiento(dateFormat.format(vencimiento));

				resultado.setUnDatosBanco(datoBancario);
			}
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	public ResultListaBancosPersona listaBancosPersona(ParamListaBancosPersona param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("listaBancosPersona");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaBancosPersona resultado = new ResultListaBancosPersona();

		try {
			conn = crearConexion();

			String sql = "SELECT CABA_CD_BANCO, CABA_DE_BANCO FROM CART_BANCOS WHERE CABA_CD_BANCO IN (503, 501, 500, 815, 506) ORDER BY 2";

			logueo.setParametro("Consulta lista bancos :: ", sql);
			pst = conn.prepareStatement(sql);

			result = pst.executeQuery();

			while (result.next()) {
				Banco banco = new Banco();
				banco.setCodBanco(result.getString("CABA_CD_BANCO"));
				banco.setDescBanco(result.getString(("CABA_DE_BANCO")));
				resultado.setUnBanco(banco);
			}
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;

	}

	public ResultExistePersona existePersona(ParamExistePersona param) {
		ResultExistePersona resultado = new ResultExistePersona();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("existePersona");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Rut", param.getRut());
		logueo.setParametro("Numero de documento", param.getNumDocumento());
		logueo.setParametro("Tipo de documento", param.getTipoDoc());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_existePersona");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			cstmt.setString(1, param.getRut());

			if (param.getTipoDoc() == null) {
				cstmt.setNull(2, java.sql.Types.VARCHAR);
			} else {
				cstmt.setString(2, param.getTipoDoc());
			}

			if (param.getNumDocumento() == null) {
				cstmt.setNull(3, java.sql.Types.VARCHAR);
			} else {
				cstmt.setString(3, param.getNumDocumento());
			}

			cstmt.setString(11, param.getUsuario());
			cstmt.registerOutParameter(1, Types.VARCHAR); // rut
			cstmt.registerOutParameter(2, Types.VARCHAR); // tipoDocumento
			cstmt.registerOutParameter(3, Types.VARCHAR); // numDocumento
			cstmt.registerOutParameter(4, Types.VARCHAR); // nombre
			cstmt.registerOutParameter(5, Types.VARCHAR); // apellidoRazon
			cstmt.registerOutParameter(6, Types.INTEGER); // tipoPersona
			cstmt.registerOutParameter(7, Types.INTEGER); // numPersona
			cstmt.registerOutParameter(8, Types.VARCHAR); // codCliente
			cstmt.registerOutParameter(9, Types.VARCHAR); // descripTipoDocumento
			cstmt.registerOutParameter(10, Types.VARCHAR); // descripTipoPersona
			cstmt.registerOutParameter(12, Types.VARCHAR); // esCliente
			cstmt.registerOutParameter(13, Types.INTEGER); // codError
			cstmt.registerOutParameter(14, Types.VARCHAR); // descripError
			cstmt.registerOutParameter(15, Types.VARCHAR); // sqlError

			cstmt.execute();

			resultado.setRut(cstmt.getString(1));
			resultado.setTipoDoc(cstmt.getString(2));
			resultado.setNumeroDocumento(cstmt.getString(3));
			resultado.setNombre(cstmt.getString(4));
			resultado.setApellidoRazon(cstmt.getString(5));

			Integer tipoPersona = cstmt.getInt(6);
			if (tipoPersona != 0) {
				resultado.setTipoPersona(cstmt.getInt(6));
			}

			Integer numPersona = cstmt.getInt(7);
			if (numPersona != 0) {
				resultado.setNumPersona(cstmt.getInt(7));
			}

			resultado.setCodCliente(cstmt.getString(8));
			resultado.setDescripTipoDocumento(cstmt.getString(9));
			resultado.setDescripTipoPersona(cstmt.getString(10));
			resultado.setEsCliente(cstmt.getString(12));

			int codError = cstmt.getInt(13);
			String descError = cstmt.getString(14);
			String sqlError = cstmt.getString(15);

			if (codError != 0) {
				ServiciosError error = new ServiciosError();
				error.setCodigo(new Integer(codError));
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

	public boolean isNumeric(String s) {
		return s.matches("[-+]?\\d*\\.?\\d+");
	}

	public ResultCondicion tienePermisoDatosBancarios(ParamTienePermisoDatosBancarios param) {
		ResultCondicion resultado = new ResultCondicion();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("tienePermisoDatosBancarios");

		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());
		logueo.setParametro("Codigo permiso", param.getCodPermiso());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("fun_tiene_permiso_datos_bancarios");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{? = call " + nombrePL + "(?,?)}");

			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);

			cstmt.setString(2, param.getUsuario());

			if (param.getCodPermiso() != null) {
				cstmt.setInt(3, param.getCodPermiso());

			} else {
				cstmt.setNull(3, Types.INTEGER);
			}
			cstmt.execute();

			String res = cstmt.getString(1);
			resultado.setResultadoString(res);
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

	public ResultXmlPL obtenerClientes(ParamObtenerClientes param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("obtenerClientes");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Productor", param.getCodProductor());
		logueo.setParametro("Apellido o razon", param.getApellidoRazon());
		logueo.setParametro("Nombre", param.getNombre());
		logueo.setParametro("Codigo cliente", param.getCodCliente());
		logueo.setParametro("RUT", param.getRut());
		logueo.setParametro("Tipo documento", param.getTipoDoc());
		logueo.setParametro("Numero documento", param.getNumDocumento());
		logueo.setParametro("Tipo persona", param.getTipoPersona());
		logueo.setParametro("Todos los clientes", param.getTodosLosClientes());
		logueo.setParametro("Telefono", param.getTel());
		logueo.setParametro("Orden", param.getOrden());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerClientes");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, param.getNombre());
			cstmt.setString(2, param.getApellidoRazon());
			cstmt.setString(3, param.getCodCliente());
			cstmt.setString(4, param.getRut());
			cstmt.setString(5, param.getTipoDoc());
			cstmt.setString(6, param.getNumDocumento());
			cstmt.setString(7, param.getTipoPersona());
			cstmt.setString(8, param.getUsuario());
			if (param.getCodProductor() != null) {
				cstmt.setInt(9, Integer.valueOf(param.getCodProductor()));
			} else {
				cstmt.setNull(9, Types.INTEGER);
			}
			cstmt.setString(10, param.getTodosLosClientes());
			cstmt.setString(11, param.getTel());

			if (param.getOrden() != null) {
				cstmt.setInt(12, Integer.valueOf(param.getOrden()));
			} else {
				cstmt.setNull(12, Types.INTEGER);
			}

			cstmt.registerOutParameter(13, Types.INTEGER);
			cstmt.registerOutParameter(14, Types.VARCHAR);
			cstmt.registerOutParameter(15, Types.VARCHAR);
			cstmt.registerOutParameter(16, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(13);
			String descError = cstmt.getString(14);
			String sqlError = cstmt.getString(15);
			Clob clob = cstmt.getClob(16);

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

	public ResultXmlPL obtenerDetalleConsultaClientes(ParamObtenerDetalleConsultaClientes param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("obtenerDetalleConsultaClientes");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de productor ", param.getCodProductor());
		logueo.setParametro("Numero de persona", param.getNumPersona());
		logueo.setParametro("Numero de cliente", param.getCodCliente());
		logueo.setParametro("Todos los clientes", param.getTodosLosClientes());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerDetalleConsultaClientes");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, param.getNumPersona());
			cstmt.setString(2, param.getCodCliente());
			cstmt.setString(3, param.getUsuario());
			if (param.getCodProductor() != null) {
				cstmt.setInt(4, Integer.valueOf(param.getCodProductor()));
			} else {
				cstmt.setNull(4, Types.INTEGER);
			}
			cstmt.setString(5, param.getTodosLosClientes());

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

	public ResultXmlPL exportarClientes(ParamExportarClientes param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("exportarClientes");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		logueo.setParametro("Numeros de personas", param.getListaNumerosPersonas());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_exportarClientes");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");
			cstmt.setString(1, param.getListaNumerosPersonas());
			cstmt.setString(2, param.getUsuario());

			cstmt.registerOutParameter(3, Types.INTEGER);
			cstmt.registerOutParameter(4, Types.VARCHAR);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(3);
			String descError = cstmt.getString(4);
			String sqlError = cstmt.getString(5);

			if (codError == 0) {
				final Herramientas herramientas = new Herramientas();
				resultado.setXml(herramientas.convertirClob(cstmt.getClob(6)));
			} else {
				ServiciosError error = new ServiciosError();
				error.setCodigo(Integer.valueOf(codError));
				error.setDescripcion(descError);

				resultado.setError(error);
				resultado.setHayError(Boolean.TRUE);
			}

			logueo.setParametro("Xml", "XML finalizado exportar clientes");
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

	public ResultCodiguera listaProductoresAsignados(ParamListaProductoresAsignados param) {
		ResultCodiguera resultado = new ResultCodiguera();
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setMetodo("listaProductoresAsignados");
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Ramo", param.getCodRamo());
		logueo.setParametro("Cod Producto", param.getCodProducto());
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		Integer codCorredor = obtenerCodigoCorredor(param);
		logueo.setParametro("Cod Productor", codCorredor);
		String fechaEmision = obtenerFechaEmision(param);
		logueo.setParametro("Fecha emision", fechaEmision);
		try {
			if (codCorredor == null) {
				conn = crearConexion();
				StringBuffer sql = new StringBuffer();

				sql.append("SELECT CAPD_CD_PRODUCTOR CODIGO,  CAPD_NM_PRODUCTOR DESCRIPCION  ");
				sql.append("FROM CART_PRODUCTORES,  ");
				sql.append(" CART_PRODUCTORES_ESQUEMAS  ");
				sql.append("WHERE CAPD_CD_PRODUCTOR = CAPZ_CAPD_CD_PRODUCTOR  ");
				sql.append(" AND CAPD_CRES_CD_ESQUEMA = CAPZ_CAEQ_CD_ESQUEMA   ");
				sql.append("AND ZPU_CRE_PROCESO.FUN_PRODUCTOR_SITUACION(CAPD_CD_PRODUCTOR, ?, ? ) IS NULL ");
				sql.append("AND 1= DECODE(PAC_WEB_UTIL.FUN_USUARIO_RECTOR(?),  NULL,1, ZPU_GEN_FUNCIONES.FUN_PUEDE_EMITIR_ORGANIZADOR_B(NULL, CAPD_CD_PRODUCTOR, ?, ?, ? )) ");
				sql.append("ORDER BY 2 ");

				logueo.setParametro(Values.CONSULTA, sql.toString());
				pst = conn.prepareStatement(sql.toString());

				if (fechaEmision != null) {
					pst.setDate(1, toSqlDate(fechaEmision));
				} else {
					pst.setNull(1, Types.DATE);
				}

				if (param.getCodRamo() != null) {
					pst.setInt(2, param.getCodRamo());
				} else {
					pst.setNull(2, Types.INTEGER);
				}

				pst.setString(3, param.getUsuario());

				if (param.getCodRamo() != null) {
					pst.setInt(4, param.getCodRamo());
				} else {
					pst.setNull(4, Types.INTEGER);
				}

				if (param.getCodProducto() != null) {
					pst.setString(5, param.getCodProducto());
				} else {
					pst.setNull(5, Types.NULL);
				}

				if (fechaEmision != null) {
					pst.setDate(6, toSqlDate(fechaEmision));
				} else {
					pst.setNull(6, Types.DATE);
				}
			} else {

				conn = crearConexion();
				StringBuffer sql = new StringBuffer();

				sql.append("SELECT CABP_CAPD_CD_PRODUCTOR CODIGO, DESC_PRODUCTOR DESCRIPCION ");
				sql.append(" FROM (SELECT CABP_CAPD_CD_PRODUCTOR, (SELECT CAPD_NM_PRODUCTOR FROM CART_PRODUCTORES WHERE CAPD_CD_PRODUCTOR = CABP_CAPD_CD_PRODUCTOR) DESC_PRODUCTOR ");
				sql.append("FROM CART_PRODUCTORES, ");
				sql.append("CART_PRODUCTOR_ORGANIZADOR, ");
				sql.append("CART_PRODUCTORES_ESQUEMAS ");
				sql.append(" WHERE CAPD_CD_PRODUCTOR = CAPZ_CAPD_CD_PRODUCTOR  ");
				sql.append("  AND CAPD_CRES_CD_ESQUEMA = CAPZ_CAEQ_CD_ESQUEMA   ");
				sql.append(" AND CABP_CARP_CD_RAMO = NVL(?, CABP_CARP_CD_RAMO)  ");
				sql.append(" AND CABP_CAPU_CD_PRODUCTO = NVL(?, CABP_CAPU_CD_PRODUCTO) ");
				sql.append(" AND ZPU_CRE_PROCESO.FUN_PRODUCTOR_SITUACION(CABP_CAPD_CD_PRODUCTOR, ?, ? ) IS NULL ");

				sql.append(" AND CABP_CAPD_CD_PRODUCTOR = CAPD_CD_PRODUCTOR ");
				sql.append(" AND CABP_CD_ORGANIZADOR = ? ");
				sql.append("AND CABP_CARP_CD_RAMO = NVL(?, CABP_CARP_CD_RAMO)  ");
				sql.append("AND CABP_CAPU_CD_PRODUCTO = NVL(?, CABP_CAPU_CD_PRODUCTO)  ");
				sql.append("  UNION  ");
				sql.append("  SELECT CAPD_CD_PRODUCTOR, CAPD_NM_PRODUCTOR DESC_PRODUCTOR  ");
				sql.append("   FROM CART_PRODUCTORES, ");
				sql.append("  CART_PRODUCTORES_ESQUEMAS ");
				sql.append("  WHERE CAPD_CD_PRODUCTOR = CAPZ_CAPD_CD_PRODUCTOR  ");
				sql.append("   AND CAPD_CRES_CD_ESQUEMA = CAPZ_CAEQ_CD_ESQUEMA  ");
				sql.append("   AND CAPD_CD_PRODUCTOR = ?");
				sql.append(" AND ZPU_CRE_PROCESO.FUN_PRODUCTOR_SITUACION(CAPD_CD_PRODUCTOR, ?, ?) IS NULL) ");

				sql.append("ORDER BY 2 ");

				logueo.setParametro(Values.CONSULTA, sql.toString());
				pst = conn.prepareStatement(sql.toString());

				if (param.getCodRamo() != null) {
					pst.setInt(1, param.getCodRamo());
				} else {
					pst.setNull(1, Types.INTEGER);
				}

				if (param.getCodProducto() != null) {
					pst.setString(2, param.getCodProducto());
				} else {
					pst.setNull(2, Types.NULL);
				}
				if (fechaEmision != null) {
					pst.setDate(3, toSqlDate(fechaEmision));
				} else {
					pst.setNull(3, Types.DATE);
				}

				if (param.getCodRamo() != null) {
					pst.setInt(4, param.getCodRamo());
				} else {
					pst.setNull(4, Types.INTEGER);
				}

				pst.setInt(5, codCorredor);

				if (param.getCodRamo() != null) {
					pst.setInt(6, param.getCodRamo());
				} else {
					pst.setNull(6, Types.INTEGER);
				}

				if (param.getCodProducto() != null) {
					pst.setString(7, param.getCodProducto());
				} else {
					pst.setNull(7, Types.NULL);
				}
				pst.setInt(8, codCorredor);

				if (fechaEmision != null) {
					pst.setDate(9, toSqlDate(fechaEmision));
				} else {
					pst.setNull(9, Types.DATE);
				}
				if (param.getCodRamo() != null) {
					pst.setInt(10, param.getCodRamo());
				} else {
					pst.setNull(10, Types.INTEGER);
				}
			}

			result = pst.executeQuery();

			while (result.next()) {
				Codiguera item = new Codiguera();
				item.setCodigo(result.getString("CODIGO"));
				item.setDescripcion(result.getString("DESCRIPCION"));
				resultado.setUno(item);
			}

			if (codCorredor != null) {
				Codiguera codiguera = new Codiguera();
				codiguera.setCodigo(String.valueOf(codCorredor));
				resultado.setValorDefecto(codiguera);
			} else {
				Codiguera codiguera = new Codiguera();
				codiguera.setCodigo(String.valueOf(1));
				resultado.setValorDefecto(codiguera);
			}

			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;

	}

	public ResultCodiguera listaProductoresAsignadosVigentes(ParamListaProductoresAsignadosVigentes param) {
		ResultCodiguera resultado = new ResultCodiguera();
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("listaProductoresAsignadosVigentes");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Ramo", param.getCodRamo());
		logueo.setParametro("Cod Producto", param.getCodProducto());
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		String fechaEmision = obtenerFechaEmision(param);
		logueo.setParametro("Fecha emision", fechaEmision);
		Integer codCorredor = obtenerCodigoCorredor(param);
		logueo.setParametro("Codigo Productor", codCorredor);

		try {

			if (codCorredor == null) {

				conn = crearConexion();
				StringBuffer sql = new StringBuffer();

				sql.append("SELECT CAPD_CD_PRODUCTOR CODIGO,  CAPD_NM_PRODUCTOR DESCRIPCION  ");
				sql.append("FROM CART_PRODUCTORES,  ");
				sql.append(" CART_PRODUCTORES_ESQUEMAS  ");
				sql.append("WHERE CAPD_CD_PRODUCTOR = CAPZ_CAPD_CD_PRODUCTOR  ");
				sql.append(" AND CAPD_CRES_CD_ESQUEMA = CAPZ_CAEQ_CD_ESQUEMA   ");
				sql.append("AND ZPU_CRE_PROCESO.FUN_PRODUCTOR_SITUACION(CAPD_CD_PRODUCTOR, ?, ? ) IS NULL ");
				sql.append("AND 1= DECODE(PAC_WEB_UTIL.FUN_USUARIO_RECTOR(?),  NULL,1, ZPU_GEN_FUNCIONES.FUN_PUEDE_EMITIR_ORGANIZADOR_B(NULL, CAPD_CD_PRODUCTOR, ?, ?, ? )) ");
				sql.append("ORDER BY 2 ");

				logueo.setParametro(Values.CONSULTA, sql.toString());
				pst = conn.prepareStatement(sql.toString());

				if (fechaEmision != null) {
					pst.setDate(1, toSqlDate(fechaEmision));
				} else {
					pst.setNull(1, Types.DATE);
				}

				if (param.getCodRamo() != null) {
					pst.setInt(2, param.getCodRamo());
				} else {
					pst.setNull(2, Types.INTEGER);
				}

				pst.setString(3, param.getUsuario());

				if (param.getCodRamo() != null) {
					pst.setInt(4, param.getCodRamo());
				} else {
					pst.setNull(4, Types.INTEGER);
				}

				if (param.getCodProducto() != null) {
					pst.setString(5, param.getCodProducto());
				} else {
					pst.setNull(5, Types.NULL);
				}

				if (fechaEmision != null) {
					pst.setDate(6, toSqlDate(fechaEmision));
				} else {
					pst.setNull(6, Types.DATE);
				}

			} else {

				conn = crearConexion();
				StringBuffer sql = new StringBuffer();

				sql.append("SELECT CABP_CAPD_CD_PRODUCTOR CODIGO, DESC_PRODUCTOR DESCRIPCION ");
				sql.append(" FROM (SELECT CABP_CAPD_CD_PRODUCTOR, (SELECT CAPD_NM_PRODUCTOR FROM CART_PRODUCTORES WHERE CAPD_CD_PRODUCTOR = CABP_CAPD_CD_PRODUCTOR) DESC_PRODUCTOR ");
				sql.append("FROM CART_PRODUCTORES, ");
				sql.append("CART_PRODUCTOR_ORGANIZADOR, ");
				sql.append("CART_PRODUCTORES_ESQUEMAS ");
				sql.append(" WHERE CAPD_CD_PRODUCTOR = CAPZ_CAPD_CD_PRODUCTOR  ");
				sql.append("  AND CAPD_CRES_CD_ESQUEMA = CAPZ_CAEQ_CD_ESQUEMA   ");
				sql.append(" AND CABP_CARP_CD_RAMO = NVL(?, CABP_CARP_CD_RAMO)  ");
				sql.append(" AND CABP_CAPU_CD_PRODUCTO = NVL(?, CABP_CAPU_CD_PRODUCTO) ");
				sql.append(" AND CABP_FE_DESDE <= ? ");
				sql.append("AND NVL(CABP_FE_HASTA, SYSDATE) >= ? ");
				sql.append(" AND ZPU_CRE_PROCESO.FUN_PRODUCTOR_SITUACION(CABP_CAPD_CD_PRODUCTOR, ?, ? ) IS NULL ");

				sql.append(" AND CABP_CAPD_CD_PRODUCTOR = CAPD_CD_PRODUCTOR ");
				sql.append(" AND CABP_CD_ORGANIZADOR = ? ");
				sql.append("AND CABP_CARP_CD_RAMO = NVL(?, CABP_CARP_CD_RAMO)  ");
				sql.append("AND CABP_CAPU_CD_PRODUCTO = NVL(?, CABP_CAPU_CD_PRODUCTO)  ");
				sql.append("  UNION  ");
				sql.append("  SELECT CAPD_CD_PRODUCTOR, CAPD_NM_PRODUCTOR DESC_PRODUCTOR  ");
				sql.append("   FROM CART_PRODUCTORES, ");
				sql.append("  CART_PRODUCTORES_ESQUEMAS ");
				sql.append("  WHERE CAPD_CD_PRODUCTOR = CAPZ_CAPD_CD_PRODUCTOR  ");
				sql.append("   AND CAPD_CRES_CD_ESQUEMA = CAPZ_CAEQ_CD_ESQUEMA  ");
				sql.append("   AND CAPD_CD_PRODUCTOR = ?");
				sql.append(" AND ZPU_CRE_PROCESO.FUN_PRODUCTOR_SITUACION(CAPD_CD_PRODUCTOR, ?, ?) IS NULL) ");

				sql.append("ORDER BY 2 ");

				logueo.setParametro(Values.CONSULTA, sql.toString());
				pst = conn.prepareStatement(sql.toString());

				if (param.getCodRamo() != null) {
					pst.setInt(1, param.getCodRamo());
				} else {
					pst.setNull(1, Types.INTEGER);
				}

				if (param.getCodProducto() != null) {
					pst.setString(2, param.getCodProducto());
				} else {
					pst.setNull(2, Types.NULL);
				}

				if (fechaEmision != null) {
					pst.setDate(3, toSqlDate(fechaEmision));
				} else {
					pst.setNull(3, Types.DATE);
				}
				if (fechaEmision != null) {
					pst.setDate(4, toSqlDate(fechaEmision));
				} else {
					pst.setNull(4, Types.DATE);
				}
				if (fechaEmision != null) {
					pst.setDate(5, toSqlDate(fechaEmision));
				} else {
					pst.setNull(5, Types.DATE);
				}
				if (param.getCodRamo() != null) {
					pst.setInt(6, param.getCodRamo());
				} else {
					pst.setNull(6, Types.INTEGER);
				}

				pst.setInt(7, codCorredor);

				if (param.getCodRamo() != null) {
					pst.setInt(8, param.getCodRamo());
				} else {
					pst.setNull(8, Types.INTEGER);
				}

				if (param.getCodProducto() != null) {
					pst.setString(9, param.getCodProducto());
				} else {
					pst.setNull(9, Types.NULL);
				}
				pst.setInt(10, codCorredor);

				if (fechaEmision != null) {
					pst.setDate(11, toSqlDate(fechaEmision));
				} else {
					pst.setNull(11, Types.DATE);
				}
				if (param.getCodRamo() != null) {
					pst.setInt(12, param.getCodRamo());
				} else {
					pst.setNull(12, Types.INTEGER);
				}

			}

			result = pst.executeQuery();

			while (result.next()) {
				Codiguera item = new Codiguera();
				item.setCodigo(result.getString("CODIGO"));
				item.setDescripcion(result.getString("DESCRIPCION"));
				resultado.setUno(item);
			}

			if (codCorredor != null) {
				Codiguera codiguera = new Codiguera();
				codiguera.setCodigo(String.valueOf(codCorredor));
				resultado.setValorDefecto(codiguera);
			} else {
				Codiguera codiguera = new Codiguera();
				codiguera.setCodigo(String.valueOf(1));
				resultado.setValorDefecto(codiguera);
			}

			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;

	}

	public ResultCondicion usuarioBroker(ParamGenerico param) {
		ResultCondicion resultado = new ResultCondicion();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("usuarioBroker");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("fun_esBroker");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{? = call " + nombrePL + "(?)}");

			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);
			cstmt.setString(2, param.getUsuario());

			cstmt.execute();

			String broker = cstmt.getString(1);

			resultado.setError(new ServiciosError());
			resultado.setResultadoString(broker);

			logueo.setParametro("Retorno Funcion", broker);
			logResultados(logueo, 0, null, null);

		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, cstmt);
		}

		return resultado;

	}

	public ResultTipoUsuario tipoUsuario(ParamGenerico param) {
		ResultTipoUsuario resultado = new ResultTipoUsuario();
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("tipoUsuario");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Integer codCorredor = obtenerCodigoCorredor(param);
		if (codCorredor == null) {
			resultado.setTipo("FUNCIONARIO");
		} else {
			if (usuarioBroker(param).getResultado()) {
				resultado.setTipo("BROKER");
			} else {
				resultado.setTipo("CORREDOR");
			}
		}
		logResultados(logueo, 0, null, null);

		return resultado;

	}

	private String obtenerFechaEmision(ParamGenerico param) {
		CallableStatement cstmt = null;
		Connection conn = null;

		Logueo logueo = new Logueo();
		logueo.setClase(ServiciosRectorPersist.class);
		logueo.setMetodo("obtenerFechaEmision");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		String resultado = null;
		ResultGenerico resultParaLoggeo = new ResultGenerico();
		try {
			conn = crearConexion();
			String nombrePL = obtenerValor("fun_obtenerFechaEmision");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{? = call " + nombrePL + "(?,?,?,?)}");

			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setString(2, param.getUsuario());

			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.registerOutParameter(3, Types.INTEGER);
			cstmt.registerOutParameter(4, Types.VARCHAR);
			cstmt.registerOutParameter(5, Types.VARCHAR);

			cstmt.execute();

			resultado = cstmt.getString(1);

			int codError = cstmt.getInt(3);
			String descError = cstmt.getString(4);
			String sqlError = cstmt.getString(5);

			if (codError != 0) {
				ServiciosError error = new ServiciosError();
				error.setCodigo(Integer.valueOf(codError));
				error.setDescripcion(descError);
				resultParaLoggeo.setError(error);
				resultParaLoggeo.setHayError(Boolean.TRUE);
			}

			logResultados(logueo, codError, descError, sqlError);
		} catch (SQLException e) {
			catchSQLException(resultParaLoggeo, logueo, e);
			resultado = null;
		} catch (Exception ex) {
			catchException(resultParaLoggeo, logueo, ex);
			resultado = null;
		} finally {
			liberarRecursos(conn, cstmt);
		}

		return resultado;
	}

}
