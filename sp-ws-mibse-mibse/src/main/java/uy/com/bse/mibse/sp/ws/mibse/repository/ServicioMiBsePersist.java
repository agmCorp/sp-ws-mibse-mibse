package uy.com.bse.mibse.sp.ws.mibse.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import uy.com.bse.mibse.sp.ws.mibse.config.MiBseProperties;
import uy.com.bse.mibse.sp.ws.mibse.model.dto.ParamObtenerComunicacionesCliente;
import uy.com.bse.mibse.sp.ws.mibse.model.dto.ParamObtenerDatosCliente;
import uy.com.bse.mibse.sp.ws.mibse.model.dto.ParamObtenerPolizasCliente;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultXmlPL;
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
	MiBseProperties miBseProperties;
    private static final Logger logger = LoggerFactory.getLogger(ServicioMiBsePersist.class);
	private SimpleJdbcCall persistenciaCall;

	@PostConstruct
	public void init() {
		persistenciaCall = new SimpleJdbcCall(jdbcTemplate)
				.withCatalogName("PAC_WEB_MIBSE") //manipular el nombrePL TODO deshardcodear
				.withProcedureName("PRO_OBT_DATOS_CLIENTE") //manipular el nombrePL TODO deshardcodear
				.withoutProcedureColumnMetaDataAccess()
				.declareParameters(
						new SqlParameter("p_usuario", Types.VARCHAR),
						new SqlOutParameter("p_codError", Types.INTEGER),
						new SqlOutParameter("p_descError", Types.VARCHAR),
						new SqlOutParameter("p_sqlError", Types.VARCHAR),
						new SqlOutParameter("p_datosComunicaciones", Types.CLOB)
				);
	}

	public ResultXmlPL obtenerComunicacionesCliente(ParamObtenerComunicacionesCliente param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServicioMiBsePersist.class);
		logueo.setMetodo("obtenerComunicacionesCliente");

		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());

		String nombrePL = miBseProperties.getObtenerDatosComunicacionesPersona();

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

		String nombrePL = miBseProperties.getObtenerPolizas();

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

		String nombrePL = miBseProperties.getObtenerDatosCliente();

		Map<String, Object> inParams = new HashMap<>();
		inParams.put("p_usuario", param.getUsuario());

		return ejecutarProcedimiento(nombrePL, inParams, "p_datosComunicaciones",logueo);
	}

	private ResultXmlPL ejecutarProcedimiento(String nombrePL, Map<String, Object> inParams, String nombreResultado, Logueo logueo) {
		ResultXmlPL resultado = new ResultXmlPL();
		try {
			persistenciaCall.setCatalogName(miBseProperties.getCatalogName(nombrePL));
			persistenciaCall.setProcedureName(miBseProperties.getProcedureName(nombrePL));
			logueo.setNombrePl(nombrePL);

			Map<String, Object> out = persistenciaCall.execute(inParams);

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
