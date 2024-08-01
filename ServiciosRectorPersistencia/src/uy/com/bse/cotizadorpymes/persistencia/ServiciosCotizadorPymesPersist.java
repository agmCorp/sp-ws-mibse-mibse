package uy.com.bse.cotizadorpymes.persistencia;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import uy.com.bse.cotizadores.pymes.ParamCalcularPymes;
import uy.com.bse.cotizadores.pymes.ParamNuevaCotizacionPymes;
import uy.com.bse.maestro.persistencia.ServiciosRector;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;

public class ServiciosCotizadorPymesPersist extends ServiciosRector {


	public ResultXmlPL nuevaCotizacionPymes(ParamNuevaCotizacionPymes param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosCotizadorPymesPersist.class);
		logueo.setMetodo("nuevaCotizacionPymes");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de productor", param.getCodProductor());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_crear_cotizacion_pymes");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");
			cstmt.setString(1, param.getUsuario());
			
			
			 if (param.getCodProductor() == null) {  
			 cstmt.setNull(2,Types.INTEGER); 
			 
			  } else { 
			  cstmt.setInt(2, Integer.valueOf(param.getCodProductor())); 
			  }
			 
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

/*

                                                                      
                                                                       p_cd_ubicacion         IN  VARCHAR2,                                                                              --Texto
                                                                       p_tp_calc              IN  CART_COTIZA_BANCO.CAZB_TP_CALCULO%TYPE,                        --Texto
                                                                       p_fr_pago              IN  CART_COTIZA_BANCO.CAZB_FR_PAGO_ELEGIDA%TYPE,            --Texto
                                                                       p_fe_desde             IN  CART_COTIZA_BANCO.CAZB_FE_DESDE%TYPE,               --Fecha
                                                                       p_fe_hasta             IN  CART_COTIZA_BANCO.CAZB_FE_HASTA%TYPE,               --Fecha
                                                                       p_cd_usuario           IN  COTW_USUARIOS_MAPPING.COUM_CD_USU_DOMINIO%TYPE,


 * 
 * 
 * 
 * 
 * 
 */

	public ResultXmlPL calcularPymes(ParamCalcularPymes param) {
		
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosCotizadorPymesPersist.class);
		logueo.setMetodo("calcularPymes");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero de cotizacion", param.getNumCotizacion());
		logueo.setParametro("Tipo de calculo", param.getTipoCalculo());
		logueo.setParametro("Forma de pago", param.getFormaPago());
		logueo.setParametro("Fecha desde", param.getFechaDesde());
		logueo.setParametro("Fecha hasta", param.getFechaHasta());
		logueo.setParametro("Actividad", param.getActividad());
		logueo.setParametro("Capital hurto", param.getCapitalHurto());
		logueo.setParametro("Codigo ubicacion", param.getCodUbicacion());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_calcular_pymes");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, param.getNumCotizacion());
			if( param.getCapitalHurto()!=null){
			cstmt.setDouble(2, param.getCapitalHurto());
			}else {
				cstmt.setNull(2,Types.DOUBLE);
			}
			cstmt.setString(3, param.getCodUbicacion());
			cstmt.setString(4, param.getTipoCalculo());
			cstmt.setString(5, param.getFormaPago());
			cstmt.setString(6, param.getFechaDesde());;
			cstmt.setString(7, param.getFechaHasta());
			cstmt.setString(8, param.getUsuario());
			
			cstmt.registerOutParameter(9, Types.INTEGER);
			cstmt.registerOutParameter(10, Types.VARCHAR);
			cstmt.registerOutParameter(11, Types.VARCHAR);
			cstmt.registerOutParameter(12, Types.CLOB);
			
			
			cstmt.execute();

			int codError = cstmt.getInt(9);
			String descError = cstmt.getString(10);
			String sqlError = cstmt.getString(11);
			Clob clob = cstmt.getClob(12);
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

	


}