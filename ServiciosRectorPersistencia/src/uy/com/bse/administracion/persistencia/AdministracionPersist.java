package uy.com.bse.administracion.persistencia;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import uy.com.bse.administracion.ParamAgregarEdoc;
import uy.com.bse.administracion.ParamObtenerDatosCorredor;
import uy.com.bse.administracion.ResultAgregarEdoc;
import uy.com.bse.administracion.ResultObtenerDatosCorredor;
import uy.com.bse.auditoria.ParamAuditarConsulta;
import uy.com.bse.auditoria.ParamAuditarEndoso;
import uy.com.bse.maestro.persistencia.ServiciosRector;
import uy.com.bse.maestro.persistencia.ServiciosRectorPersist;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;

public class AdministracionPersist extends ServiciosRector{
	
	
	
	
/*
 * SELECT P.CABU_NM_PERSONA NOMBRE, 
       P.CABU_NM_APELLIDO_RAZON APELLIDO_RAZON, 
       P.CABU_NU_PERSONA NRO_PERSONA, 
       PAC_WEB_UTIL.FUN_CODIGO_CORREDOR(:usu_domino) CODIGO_CORREDOR,
       DECODE ((SELECT COUNT(1)
         FROM CART_PERSONAS_EDOCS PE
        WHERE PE.CPDE_CABU_NU_PERSONA = P.CABU_NU_PERSONA), 0, 'N', 'S') IND_EVOLANTE,
       (SELECT U.CAUS_MAIL
          FROM CART_USUARIOS U
         WHERE U.CAUS_CD_USUARIO = PAC_WEB_UTIL.FUN_USUARIO_RECTOR(:usu_domino)) MAIL
  FROM CART_MAE_PERSONAS P
WHERE P.CABU_NU_PERSONA = PAC_WEB_UTIL.FUN_CODIGO_PERSONA_CORREDOR(:usu_domino)

 * 
 * 
 * 
 * 
 * 
 */
	
	
	public ResultObtenerDatosCorredor obtenerDatosCorredor (ParamObtenerDatosCorredor param){
		ResultObtenerDatosCorredor resultado = new ResultObtenerDatosCorredor();
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Capa persistencia");
		logueo.setClase(AdministracionPersist.class);
		logueo.setMetodo("obtenerDatosCorredor()");
		
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		
		
		StringBuffer sql = new StringBuffer();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		
		try {
			conn = this.crearConexion();
			sql.append("SELECT P.CABU_NM_PERSONA NOMBRE, ");
			sql.append("P.CABU_NM_APELLIDO_RAZON APELLIDO_RAZON, ");
			sql.append("P.CABU_NU_PERSONA NRO_PERSONA, ");
			sql.append("PAC_WEB_UTIL.FUN_CODIGO_CORREDOR(?) CODIGO_CORREDOR, ");
			sql.append("DECODE ((SELECT COUNT(1) ");
			sql.append("FROM CART_PERSONAS_EDOCS PE ");
			sql.append("WHERE PE.CPDE_CABU_NU_PERSONA = P.CABU_NU_PERSONA), 0, 'N', 'S') IND_EVOLANTE, ");
			sql.append("(SELECT U.CAUS_MAIL ");
			sql.append("FROM CART_USUARIOS U ");
			sql.append("WHERE U.CAUS_CD_USUARIO = PAC_WEB_UTIL.FUN_USUARIO_RECTOR(?)) MAIL FROM CART_MAE_PERSONAS P ");
			sql.append("WHERE P.CABU_NU_PERSONA = PAC_WEB_UTIL.FUN_CODIGO_PERSONA_CORREDOR(?");
			sql.append(")");
			
			String consulta = sql.toString();

			logueo.setParametro("Consulta obtenerDatosCorredor ::",consulta );
			pst = conn.prepareStatement(consulta);
			pst.setString(1, param.getUsuario());
			pst.setString(2, param.getUsuario());
			pst.setString(3, param.getUsuario());

			result = pst.executeQuery();
			
			while (result != null && result.next()) {
			resultado.setHayError(Boolean.FALSE);
				
			resultado.setApellido(result.getString("APELLIDO_RAZON"));
			resultado.setNombre(result.getString("NOMBRE"));
			resultado.setEmail(result.getString("MAIL"));
			resultado.setNroPersona(result.getInt("NRO_PERSONA"));
			resultado.setCodCorredor(result.getInt("CODIGO_CORREDOR"));
			resultado.setAdhesionEvolante(result.getString("IND_EVOLANTE"));
			
			}
			log.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst);
		}

		return resultado;
	}
	
	/**
	 * INSERT INTO RECTOR.CART_PERSONAS_EDOCS
   (CPDE_CABU_NU_PERSONA, CPDE_CATL_CD_TIPO_ROL, CPDE_AVISO_VENC, CPDE_CAUS_CD_USUARIO)
VALUES
   (:nro_persona, 2, 'X', PAC_WEB_UTIL.FUN_USUARIO_RECTOR(:usu_domino));


	 * 
	 * 
	 * 
	 * */
	
	
	public ResultAgregarEdoc agregarEvolante(ParamAgregarEdoc param) {
		ResultAgregarEdoc resultado = new ResultAgregarEdoc();
		
		PreparedStatement pst = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setClase(AdministracionPersist.class);
		logueo.setMetodo("agregarEvolante");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
	
		
		
		
		try {
			conn = this.crearConexion();

			String query ="INSERT INTO RECTOR.CART_PERSONAS_EDOCS"+
					   "(CPDE_CABU_NU_PERSONA, CPDE_CATL_CD_TIPO_ROL, CPDE_AVISO_VENC, CPDE_CAUS_CD_USUARIO)"+
					   "VALUES "+
					     " (?, 2, 'X',PAC_WEB_UTIL.FUN_USUARIO_RECTOR(?))";
			
			logueo.setParametro("Consulta", query);
			pst = conn.prepareStatement(query);
			pst.setInt(1, param.getCodPersona());
			pst.setString(2, param.getUsuario());
			pst.executeUpdate();
			log.info(logueo.getSoloParametros());
			
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst);
		}

		return resultado;
	}


}
