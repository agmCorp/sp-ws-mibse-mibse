package uy.com.bse.mibse.sp.ws.mibse.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import uy.com.bse.mibse.sp.ws.mibse.config.DatabaseMiBsePropertiesConfig;
import uy.com.bse.mibse.sp.ws.mibse.model.dto.*;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.*;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.exception.Values;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.log.Logueo;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.persistencia.Persistencia;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.slf4j.Logger;
import java.sql.Types;
import java.sql.Clob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ServicioMiBsePersist {

    @Autowired
	private JdbcTemplate jdbcTemplate;
    @Autowired
    private Persistencia persistencia;
	@Autowired
	private Logueo logueo;
	@Autowired
	private DatabaseMiBsePropertiesConfig databaseMiBseProperties;

    private static final Logger logger = LoggerFactory.getLogger(ServicioMiBsePersist.class);
	private SimpleJdbcCall persistenciaCallXmlResponse;
	private SimpleJdbcCall persistenciaCallNumericResponse;


	@PostConstruct
	public void init() {
		persistenciaCallXmlResponse = new SimpleJdbcCall(jdbcTemplate)
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(
						new SqlParameter("p_usuario", Types.VARCHAR),
						new SqlOutParameter("p_codError", Types.INTEGER),
						new SqlOutParameter("p_descError", Types.VARCHAR),
						new SqlOutParameter("p_sqlError", Types.VARCHAR),
						new SqlOutParameter("p_datosComunicaciones", Types.CLOB)
				);
		persistenciaCallNumericResponse = new SimpleJdbcCall(jdbcTemplate)
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(
						new SqlOutParameter("return", Types.VARCHAR),
						new SqlParameter("p_usuario", Types.VARCHAR),
						new SqlOutParameter("p_codError", Types.INTEGER),
						new SqlOutParameter("p_descError", Types.VARCHAR),
						new SqlOutParameter("p_sqlError", Types.VARCHAR)
				)
				.withReturnValue();
	}

	public ResultXmlPL obtenerComunicacionesCliente(ParamObtenerComunicacionesCliente param) {
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServicioMiBsePersist.class);
		logueo.setMetodo("obtenerComunicacionesCliente");

		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());
		logueo.resetParametros();
		String nombrePL = databaseMiBseProperties.getObtenerDatosComunicacionesPersona();

		Map<String, Object> inParams = new HashMap<>();
		inParams.put("p_usuario", param.getUsuario());

		return ejecutarProcedimiento(nombrePL, inParams, "p_datosComunicaciones",logueo);
	}

	public ResultXmlPL obtenerPolizasCliente(ParamObtenerPolizasCliente param) {
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServicioMiBsePersist.class);
		logueo.setMetodo("obtenerPolizasCliente");

		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());
		logueo.resetParametros();
		String nombrePL = databaseMiBseProperties.getObtenerPolizas();

		Map<String, Object> inParams = new HashMap<>();
		inParams.put("p_usuario", param.getUsuario());

		return ejecutarProcedimiento(nombrePL, inParams, "p_datosComunicaciones",logueo);
	}

	public ResultXmlPL obtenerDatosCliente(ParamObtenerDatosCliente param) {
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServicioMiBsePersist.class);
		logueo.setMetodo("obtenerDatosCliente");

		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());
		logueo.resetParametros();
		String nombrePL = databaseMiBseProperties.getObtenerDatosCliente();

		Map<String, Object> inParams = new HashMap<>();
		inParams.put("p_usuario", param.getUsuario());

		return ejecutarProcedimiento(nombrePL, inParams, "p_datosComunicaciones",logueo);
	}

	public ResultObtenerNumeroCliente obtenerNumeroCliente(ParamObtenerNumeroCliente param) {
		ResultObtenerNumeroCliente resultado = new ResultObtenerNumeroCliente();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServicioMiBsePersist.class);
		logueo.setMetodo("obtenerNumeroCliente");

		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());

		String nombrePL = databaseMiBseProperties.getObtenerNumeroCliente();
		logueo.resetParametros();
		persistenciaCallNumericResponse.setCatalogName(databaseMiBseProperties.getCatalogName(nombrePL));
		persistenciaCallNumericResponse.setProcedureName(databaseMiBseProperties.getProcedureName(nombrePL));
		logueo.setNombrePl(nombrePL);

		Map<String, Object> inParams = new HashMap<>();
		inParams.put("p_usuario", param.getUsuario());

		// TODO: revisar si se puede pasar a función para reutilizar
		try {
			Map<String, Object> out = persistenciaCallNumericResponse.execute(inParams);

			Integer codError = (Integer) out.get("p_codError"); // Cambiar a Integer para manejar nulls
			String descError = (String) out.get("p_descError");
			String sqlError = (String) out.get("p_sqlError");

			if (codError == null || codError == 0) {
				String res = (String) out.get("return");
				resultado.setNumCliente(res);
				resultado.setHayError(Boolean.FALSE);
				codError = 0;
			} else {
				ServiciosError error = new ServiciosError();
				error.setCodigo(codError);
				error.setDescripcion(descError);

				resultado.setError(error);
				resultado.setHayError(Boolean.TRUE);
			}
			persistencia.logResultados(logueo, codError, descError, sqlError);
		} catch (Exception e) {
			persistencia.catchException(resultado, logueo, e);
		}

		return resultado;
	}

	public ResultCodiguera listaProfesiones(ParamListaProfesiones param) {
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServicioMiBsePersist.class);
		logueo.setMetodo("listaProfesiones");
		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());
		logueo.resetParametros();
		ResultCodiguera resultado = new ResultCodiguera();

		String sql = "SELECT CAPW_CD_PROFESION COD_PROFESION, CAPW_DE_PROFESION DESC_PROFESION FROM CART_PROFESIONES";
		logueo.setParametro("Consulta", sql);

		try {
			List<Codiguera> profesiones = jdbcTemplate.query(sql, (rs, rowNum) -> {
				Codiguera profesion = new Codiguera();
				profesion.setCodigo(rs.getString("COD_PROFESION"));
				profesion.setDescripcion(rs.getString("DESC_PROFESION"));
				return profesion;
			});

			if (!profesiones.isEmpty()) {
				resultado.setItems(profesiones);
			}

		} catch (Exception e) {
			persistencia.catchException(resultado, logueo, e);
		}

		return resultado;
	}


	private ResultXmlPL ejecutarProcedimiento(String nombrePL, Map<String, Object> inParams, String nombreResultado,
											  Logueo logueo) {
		ResultXmlPL resultado = new ResultXmlPL();
		try {
			persistenciaCallXmlResponse.setCatalogName(databaseMiBseProperties.getCatalogName(nombrePL));
			persistenciaCallXmlResponse.setProcedureName(databaseMiBseProperties.getProcedureName(nombrePL));
			logueo.setNombrePl(nombrePL);

			Map<String, Object> out = persistenciaCallXmlResponse.execute(inParams);

			int codError = (int) out.get("p_codError");
			String descError = (String) out.get("p_descError");
			String sqlError = (String) out.get("p_sqlError");
			Clob clob = (Clob) out.get(nombreResultado);

			persistencia.procesarResultados(resultado, logueo, codError, descError, sqlError, clob);
		} catch (Exception e) {
			persistencia.catchException(resultado, logueo, e);
		}
		return resultado;
	}
}
