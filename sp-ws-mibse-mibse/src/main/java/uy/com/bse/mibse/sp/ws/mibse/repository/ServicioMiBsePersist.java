package uy.com.bse.mibse.sp.ws.mibse.repository;


import org.springframework.beans.factory.annotation.Autowired;
import uy.com.bse.mibse.sp.ws.mibse.model.dto.ParamObtenerComunicacionesCliente;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultXmlPL;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.exception.Values;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.log.Logueo;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.persistencia.Persistencia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import java.sql.Types;
import java.sql.Clob;
import java.util.HashMap;
import java.util.Map;


public class ServicioMiBsePersist {
    
    @Autowired
	private JdbcTemplate jdbcTemplate;
    @Autowired
    private Persistencia persistencia;
    private static final Logger logger = LoggerFactory.getLogger(ServicioMiBsePersist.class);


    public ResultXmlPL obtenerComunicacionesCliente(ParamObtenerComunicacionesCliente param) {

		ResultXmlPL resultado = new ResultXmlPL();
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServicioMiBsePersist.class);
		logueo.setMetodo("obtenerComunicacionesCliente");

		logueo.setParametro("Usuario", param.getUsuario());
		logueo.setParametro("Clave", param.getClave());

		try {
			//String nombrePL = obtenerValor("proc_obtenerDatosComunicacionesPersona");
            //TODO pasar a ConfigurationProperties de Spring 

            String nombrePL = "PAC_WEB_MIBSE.PRO_OBT_DATOS_CLIENTE";
			logueo.setNombrePl(nombrePL);

			SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName(nombrePL) //manipular el nombrePL
					.withProcedureName(nombrePL) //manipular el nombrePL
                    .withoutProcedureColumnMetaDataAccess()                    
					.declareParameters(
							new SqlParameter("p_usuario", Types.VARCHAR),
							new SqlOutParameter("p_codError", Types.INTEGER),
							new SqlOutParameter("p_descError", Types.VARCHAR),
							new SqlOutParameter("p_sqlError", Types.VARCHAR),
							new SqlOutParameter("p_datosComunicaciones", Types.CLOB)
					);

            Map<String, Object> inParams = new HashMap<>();
            inParams.put("p_usuario", param.getUsuario());
            Map<String, Object> out = simpleJdbcCall.execute(inParams);

			int codError = (int) out.get("p_codError");
			String descError = (String) out.get("p_descError");
			String sqlError = (String) out.get("p_sqlError");
			Clob clob = (Clob) out.get("p_datosComunicaciones");
			persistencia.procesarResultados(resultado, logueo, codError, descError, sqlError, clob);
		} catch (Exception e) {
			persistencia.catchException(resultado, logueo, e);
		}
		return resultado;
	}


}
