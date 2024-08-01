package uy.com.bse.polizas.persistencia;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// import bse.ws.cfe.CfeFactura; AGM TODO
import uy.com.bse.cotizaciones.lovs.AcreedorObjetos;
import uy.com.bse.cotizaciones.lovs.ResultAcreedorObjetos;
import uy.com.bse.maestro.persistencia.ServiciosRector;
import uy.com.bse.polizas.consultas.ParamCartaXTipoCarta;
import uy.com.bse.polizas.consultas.ParamCausaAnulacion;
import uy.com.bse.polizas.consultas.ParamDatosParametricoPoliza;
import uy.com.bse.polizas.consultas.ParamDatosParametricosEndoso;
import uy.com.bse.polizas.consultas.ParamDatosXDatosParametricosEndoso;
import uy.com.bse.polizas.consultas.ParamDatosXDatosParametricosPoliza;
import uy.com.bse.polizas.consultas.ParamGenerarMarcaNoRenovar;
import uy.com.bse.polizas.consultas.ParamGuardarImprimirCarta;
import uy.com.bse.polizas.consultas.ParamGuardarImprimirReporte;
import uy.com.bse.polizas.consultas.ParamModoCalculo;
import uy.com.bse.polizas.consultas.ParamMotivoAbandono;
import uy.com.bse.polizas.consultas.ParamMotivoEndoso;
import uy.com.bse.polizas.consultas.ParamObjetosAcreedor;
import uy.com.bse.polizas.consultas.ParamObtenerAcreedoresPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerAhorrosPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerAnexosPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerAseguradoCertifPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerBeneficiariosPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerBienesAseguradosPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerBonifNoSiniestroPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerCancelacionesRemesa;
import uy.com.bse.polizas.consultas.ParamObtenerCertificadosPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerContratantePoliza;
import uy.com.bse.polizas.consultas.ParamObtenerCotizacionPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerCuotasPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerDatosBasicosEndoso;
import uy.com.bse.polizas.consultas.ParamObtenerDatosCertificadoPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerDatosEndosoPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerDatosEndosoSinPremio;
import uy.com.bse.polizas.consultas.ParamObtenerDatosFacturaDigital;
import uy.com.bse.polizas.consultas.ParamObtenerDatosParametricos;
import uy.com.bse.polizas.consultas.ParamObtenerDatosPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerDatosRemesa;
import uy.com.bse.polizas.consultas.ParamObtenerDatosSiniestro;
import uy.com.bse.polizas.consultas.ParamObtenerDeclaracionSiniestro;
import uy.com.bse.polizas.consultas.ParamObtenerDetallePoliza;
import uy.com.bse.polizas.consultas.ParamObtenerDetalleRemesa;
import uy.com.bse.polizas.consultas.ParamObtenerDetalleTextoPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerEndososPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerFacturasPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerFranquiciasPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerImputacionesPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerListaBienesPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerNotasSiniestro;
import uy.com.bse.polizas.consultas.ParamObtenerOrigenRemesaAuto;
import uy.com.bse.polizas.consultas.ParamObtenerPolizaCabezalDetalle;
import uy.com.bse.polizas.consultas.ParamObtenerPolizas;
import uy.com.bse.polizas.consultas.ParamObtenerPolizasCabezal;
import uy.com.bse.polizas.consultas.ParamObtenerPreliqPendientesPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerProductoresPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerRemesasPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerRemesasXCliente;
import uy.com.bse.polizas.consultas.ParamObtenerSiniestros;
import uy.com.bse.polizas.consultas.ParamObtenerSiniestrosCliente;
import uy.com.bse.polizas.consultas.ParamObtenerSiniestrosConFiltro;
import uy.com.bse.polizas.consultas.ParamObtenerTextosPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerTotalesCuotasPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerUbicacionBienPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerValidarImpresionPoliza;
import uy.com.bse.polizas.consultas.ParamObtenerValoresXDatoParametrico;
import uy.com.bse.polizas.consultas.ParamTienePermisoProducto;
import uy.com.bse.polizas.consultas.ParamTipoAnulacion;
import uy.com.bse.polizas.consultas.ParamTipoCarta;
import uy.com.bse.polizas.consultas.ParamValoresDatosParametricosEndoso;
import uy.com.bse.polizas.consultas.ParamValoresDatosParametricosPoliza;
import uy.com.bse.polizas.consultas.ResultActualizarDatosParticulares;
import uy.com.bse.polizas.consultas.ResultCartaXTipoCarta;
import uy.com.bse.polizas.consultas.ResultCausaAnulacion;
import uy.com.bse.polizas.consultas.ResultDatosParametricoPoliza;
import uy.com.bse.polizas.consultas.ResultDatosParametricosEndoso;
import uy.com.bse.polizas.consultas.ResultDatosXDatosParametricosPoliza;
import uy.com.bse.polizas.consultas.ResultGenerarEndoso;
import uy.com.bse.polizas.consultas.ResultModoCalculo;
import uy.com.bse.polizas.consultas.ResultMotivoAbandono;
import uy.com.bse.polizas.consultas.ResultMotivoEndoso;
import uy.com.bse.polizas.consultas.ResultObtenerDatosParametricos;
import uy.com.bse.polizas.consultas.ResultObtenerDetalleTextoPoliza;
import uy.com.bse.polizas.consultas.ResultObtenerSiniestros;
import uy.com.bse.polizas.consultas.ResultTipoAnulacion;
import uy.com.bse.polizas.consultas.ResultTipoCarta;
import uy.com.bse.polizas.consultas.ResultValoresDatosParametricosEndoso;
import uy.com.bse.polizas.consultas.ResultValoresDatosParametricosPoliza;
import uy.com.bse.polizas.entidades.CartaXTipoCarta;
import uy.com.bse.polizas.entidades.CausaAnulacion;
import uy.com.bse.polizas.entidades.DatoParametricoEndoso;
import uy.com.bse.polizas.entidades.DatoParametricoPoliza;
import uy.com.bse.polizas.entidades.DatosBasicosSiniestro;
import uy.com.bse.polizas.entidades.DatosXDatoParametrico;
import uy.com.bse.polizas.entidades.ModoCalculo;
import uy.com.bse.polizas.entidades.MotivoAbandono;
import uy.com.bse.polizas.entidades.MotivoEndoso;
import uy.com.bse.polizas.entidades.TipoAnulacion;
import uy.com.bse.polizas.entidades.TipoCarta;
import uy.com.bse.polizas.operaciones.ParamActualizarDatosParticulares;
import uy.com.bse.polizas.operaciones.ParamAgregarCertificadoEndoso;
import uy.com.bse.polizas.operaciones.ParamAnularCertificadoEndoso;
import uy.com.bse.polizas.operaciones.ParamAnularPoliza;
import uy.com.bse.polizas.operaciones.ParamCotizacionPendiente;
import uy.com.bse.polizas.operaciones.ParamCotizacionPendienteSinPremio;
import uy.com.bse.polizas.operaciones.ParamEnviarFacturaDigitalEMail;
import uy.com.bse.polizas.operaciones.ParamFacturacionElectronica;
import uy.com.bse.polizas.operaciones.ParamGenerarEndoso;
import uy.com.bse.polizas.operaciones.ParamNuevaCotizacionEndoso;
import uy.com.bse.polizas.operaciones.ParamNuevaFacturacionInteractiva;
import uy.com.bse.polizas.operaciones.ParamValidarAnulacionPoliza;
import uy.com.bse.polizas.operaciones.ParamValidarPoliza;
import uy.com.bse.polizas.operaciones.ResultAnularCertificadoEndoso;
import uy.com.bse.polizas.operaciones.ResultEnviarFacturaDigitalEMail;
import uy.com.bse.polizas.operaciones.ResultFacturacionElectronica;
import uy.com.bse.recuotificacion.OrigenEndoso;
import uy.com.bse.recuotificacion.ParamAcreedorEndoso;
import uy.com.bse.recuotificacion.ParamActualizarDatosBancariosEndoso;
import uy.com.bse.recuotificacion.ParamCalcularRec;
import uy.com.bse.recuotificacion.ParamListaCertificadosEndosar;
import uy.com.bse.recuotificacion.ParamListaPlanesPagoRec;
import uy.com.bse.recuotificacion.ParamObtenerConsolidado;
import uy.com.bse.recuotificacion.ParamOrigenEndoso;
import uy.com.bse.recuotificacion.ParamRecuotificarPoliza;
import uy.com.bse.recuotificacion.ParamValidarDetectarEndoso;
import uy.com.bse.recuotificacion.ParamValidarPolizaRec;
import uy.com.bse.recuotificacion.ResultOrigenEndoso;
import uy.com.bse.refacturar.ParamRefacturarPoliza;
import uy.com.bse.refacturar.ParamValidarRefacturacion;
import uy.com.bse.utilitario.dato.Codiguera;
import uy.com.bse.utilitario.dato.ResultCodiguera;
import uy.com.bse.utilitario.dato.ResultCondicion;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.util.Herramientas;

public class ServiciosPolizasPersist extends ServiciosRector {

	private static Logger LOG = LogManager.getLogger(ServiciosPolizasPersist.class);

	public ResultXmlPL obtenerDatosPoliza(ParamObtenerDatosPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerDatosPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerDatosPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));

			cstmt.setString(3, param.getUsuario());

			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);
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

	public ResultXmlPL guardarImprimirOrdenCarta(ParamGuardarImprimirCarta param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("guardarImprimirOrdenCarta");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero Certificado", param.getNumCertificado());
		logueo.setParametro("Numero Carta", param.getNumCarta());
		logueo.setParametro("Numero Reporte", param.getNumReporte());
		logueo.setParametro("Cod Reporte", param.getCodReporte());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("fun_guardarOrdenCarta");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{? = call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.setInt(2, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(3, Integer.valueOf(param.getNumPoliza()));
			cstmt.setInt(4, Integer.valueOf(param.getNumCertificado()));
			cstmt.setInt(5, Integer.valueOf(param.getNumEndoso()));        
			cstmt.setString(6, param.getNumCarta());
			cstmt.setString(7, param.getNumReporte());
			cstmt.setString(8, param.getCodReporte());
			cstmt.setString(9, param.getUsuario());

			cstmt.registerOutParameter(10, Types.INTEGER);
			cstmt.registerOutParameter(11, Types.VARCHAR);
			cstmt.registerOutParameter(12, Types.VARCHAR);
			cstmt.execute();

			int codError = cstmt.getInt(10);
			String descError = cstmt.getString(11);
			String sqlError = cstmt.getString(12);

			if (codError == 0) {
				StringBuffer sb = new StringBuffer();
				sb.append("<?xml version=\"1.0\"?>");
				sb.append('\n');
				sb.append("<resultado>");
				sb.append('\n');
				sb.append("<secuencia>");
				sb.append(cstmt.getString(1));
				sb.append("</secuencia>");
				sb.append('\n');
				sb.append("</resultado>");
				sb.append('\n');
				resultado.setXml(sb.toString());
				LOG.debug(resultado.getXml());
			} else {
				ServiciosError error = new ServiciosError();
				error.setCodigo(Integer.valueOf(codError));
				error.setDescripcion(descError);

				resultado.setError(error);
				resultado.setHayError(Boolean.TRUE);
			}

			logResultados(resultado, logueo, codError, descError, sqlError);
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, cstmt);
		}

		return resultado;

	}

	public ResultXmlPL guardarImprimirReporte(ParamGuardarImprimirReporte param) {
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("guardarImprimirReporte");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero Endoso", param.getNumEndoso());
		logueo.setParametro("Items", param.getItems());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("fun_guardarOrdenReporte");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{? = call " + nombrePL + "(?,?,?,?,?,?,?,?)}");
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setInt(2, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(3, Integer.valueOf(param.getNumPoliza()));
			cstmt.setInt(4, Integer.valueOf(param.getNumEndoso()));
			cstmt.setString(5, param.getItems());
			cstmt.setString(6, param.getUsuario());

			cstmt.registerOutParameter(7, Types.INTEGER);
			cstmt.registerOutParameter(8, Types.VARCHAR);
			cstmt.registerOutParameter(9, Types.VARCHAR);
			cstmt.execute();

			int codError = cstmt.getInt(7);
			String descError = cstmt.getString(8);
			String sqlError = cstmt.getString(9);

			if (codError == 0) {
				StringBuffer sb = new StringBuffer();
				sb.append("<?xml version=\"1.0\"?>");
				sb.append('\n');
				sb.append("<resultado>");
				sb.append('\n');
				sb.append("<secuencia>");
				sb.append(cstmt.getString(1));
				sb.append("</secuencia>");
				sb.append('\n');
				sb.append("</resultado>");
				sb.append('\n');
				resultado.setXml(sb.toString());
				LOG.debug(resultado.getXml());
			} else {
				ServiciosError error = new ServiciosError();
				error.setCodigo(Integer.valueOf(codError));
				error.setDescripcion(descError);

				resultado.setError(error);
				resultado.setHayError(Boolean.TRUE);
			}

			logResultados(resultado, logueo, codError, descError, sqlError);
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, cstmt);
		}
		return resultado;
	}

	public ResultXmlPL obtenerValidarImpresionPoliza(ParamObtenerValidarImpresionPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerValidarImpresionPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero Certificado", param.getNumCertificado());
		logueo.setParametro("Numero Endoso", param.getNumEndoso());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_validarImpresionPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			cstmt.setInt(3, Integer.valueOf(param.getNumCertificado()));
			cstmt.setInt(4, Integer.valueOf(param.getNumEndoso()));
			cstmt.setString(5, param.getUsuario());
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

	public ResultXmlPL obtenerCertificadosPoliza(ParamObtenerCertificadosPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerCertificadosPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerCertificadosPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));

			cstmt.setString(3, param.getUsuario());

			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);
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

	public ResultXmlPL obtenerDatosCertificadoPoliza(ParamObtenerDatosCertificadoPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerDatosCertificadoPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerDatosCertificadoPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			cstmt.setInt(3, Integer.valueOf(param.getNumCertificado()));
			cstmt.setString(4, param.getUsuario());

			cstmt.registerOutParameter(5, Types.INTEGER);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.registerOutParameter(8, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(5);
			String descError = cstmt.getString(6);
			String sqlError = cstmt.getString(7);
			Clob clob = cstmt.getClob(8);
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

	public ResultTipoAnulacion listaTipoAnulacion(ParamTipoAnulacion param) {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("listaTipoAnulacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultTipoAnulacion resultado = new ResultTipoAnulacion();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT 'S' CODIGO,'POR SALDO' DESCRIPCION FROM DUAL UNION ");
			sql.append("SELECT 'F' CODIGO,'POR FECHA' DESCRIPCION FROM DUAL ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());

			result = pst.executeQuery();

			while (result.next()) {
				TipoAnulacion obj = new TipoAnulacion();
				obj.setCodigo(result.getString("codigo"));
				obj.setDescripcion(result.getString("descripcion"));
				resultado.setUnTipoAnulacion(obj);
			}

			LOG.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	public ResultCausaAnulacion listaCausaAnulacion(ParamCausaAnulacion param) {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("listaCausaAnulacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo tipo anulacion", param.getCodTipoAnulacion());
		logueo.setParametro("Codigo ramo", param.getCodRamo());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultCausaAnulacion resultado = new ResultCausaAnulacion();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT CAME_CD_MOTIVO, CAME_DE_MOTIVO, NVL(CAME_TP_CALCULO, 'T') CAME_TP_CALCULO,SUBSTR(RV_MEANING, 1, 50) DESC_MODO_CALC ");
			sql.append("FROM CART_MOTIVOS_ENDOSOS, CG_REF_CODES ");
			sql.append("WHERE CAME_TP_TRANSAC = 'A' AND CAME_CD_MOTIVO <> 29 AND CAME_IN_POR_SALDO = ? ");
			sql.append("AND RV_DOMAIN = 'CART_COTIZA_BANCO.CAZB_TP_CALCULO' AND RV_LOW_VALUE = NVL(CAME_TP_CALCULO, 'T') ");
			sql.append("and (? = 2 and came_tp_calculo not in('T','F') or ? <> 2) ");
			sql.append("ORDER BY CAME_CD_MOTIVO ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());

			pst.setString(1, param.getCodTipoAnulacion());
			pst.setInt(2, Integer.valueOf(param.getCodRamo()));
			pst.setInt(3, Integer.valueOf(param.getCodRamo()));

			result = pst.executeQuery();

			while (result.next()) {
				CausaAnulacion obj = new CausaAnulacion();
				obj.setCodMotivo(result.getInt("CAME_CD_MOTIVO"));
				obj.setDescripMotivo(result.getString("CAME_DE_MOTIVO"));
				obj.setCodModoCalculo(result.getString("CAME_TP_CALCULO"));
				obj.setDescripModoCalculo(result.getString("DESC_MODO_CALC"));
				resultado.setUnaCausaAnulacion(obj);
			}

			LOG.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;

	}

	public ResultCartaXTipoCarta listaCartaXTipoCarta(ParamCartaXTipoCarta param) {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("listaCartaXTipoCarta");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Tipo", param.getTipo());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultCartaXTipoCarta resultado = new ResultCartaXTipoCarta();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT GECF_CD_CARTA, GECF_DE_DESCRIPCION, GECF_NM_REPORT ");
			sql.append("FROM GENT_CARTAS_DEFINICION ");
			sql.append("WHERE GECF_CD_TIPO_TEXTO = ? ");
			sql.append("AND GECF_GECR_CD_REPORTE = 'CERTIFICAD' ");
			sql.append("AND ('N' = 'P' OR ( GECF_IN_ESTADO = 'D' ");
			sql.append("AND EXISTS (SELECT 1 ");
			sql.append("FROM GENT_CARTAS_DEPARTAMENTOS ");
			sql.append("WHERE GEDP_GECF_CD_CARTA=GECF_CD_CARTA ");
			sql.append("AND GEDP_CJCM_CD_COMPANIA = 1 ");
			sql.append("AND GEDP_CJDP_CD_DEPARTAMENTO = (SELECT CAUS_CJDP_CD_DEPARTAMENTO ");
			sql.append("FROM CART_USUARIOS ");
			sql.append("WHERE CAUS_CD_USUARIO = PAC_WEB_UTIL.FUN_USUARIO_RECTOR(?))))) ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, Integer.valueOf(param.getTipo()));
			pst.setString(2, param.getUsuario());

			result = pst.executeQuery();

			while (result.next()) {
				CartaXTipoCarta obj = new CartaXTipoCarta();
				obj.setCodigo(result.getString("GECF_CD_CARTA"));
				obj.setDescripcion(result.getString("GECF_DE_DESCRIPCION"));
				obj.setReporte(result.getString("GECF_NM_REPORT"));
				resultado.setUno(obj);
			}

			LOG.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}
		return resultado;
	}

	public ResultTipoCarta listaTiposCarta(ParamTipoCarta param) {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("listaTiposCarta");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultTipoCarta resultado = new ResultTipoCarta();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT RV_LOW_VALUE,RV_MEANING ");
			sql.append("FROM CG_REF_CODES ");
			sql.append("WHERE RV_DOMAIN = 'GENT_CARTAS_DEFINICION.GECF_CD_TIPO_TEXTO' ");
			sql.append("AND EXISTS (SELECT 1 FROM GENT_CARTAS_DEFINICION ");
			sql.append("WHERE GECF_CD_TIPO_TEXTO = RV_LOW_VALUE ");
			sql.append("AND GECF_GECR_CD_REPORTE = 'CERTIFICAD' ");
			sql.append("AND ('N' = 'P' OR (GECF_IN_ESTADO = 'D' ");
			sql.append("AND EXISTS (SELECT 1 FROM GENT_CARTAS_DEPARTAMENTOS ");
			sql.append("WHERE GEDP_GECF_CD_CARTA = GECF_CD_CARTA ");
			sql.append("AND GEDP_CJCM_CD_COMPANIA = 1 ");
			sql.append("AND GEDP_CJDP_CD_DEPARTAMENTO = (SELECT CAUS_CJDP_CD_DEPARTAMENTO FROM CART_USUARIOS ");
			sql.append("WHERE CAUS_CD_USUARIO = PAC_WEB_UTIL.FUN_USUARIO_RECTOR(?)))))) ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, param.getUsuario());

			result = pst.executeQuery();

			while (result.next()) {
				TipoCarta obj = new TipoCarta();
				obj.setCodigo(result.getInt("RV_LOW_VALUE"));
				obj.setDescripcion(result.getString("RV_MEANING"));
				resultado.setUno(obj);
			}

			LOG.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	public ResultModoCalculo listaModoCalculoAnulacion(ParamModoCalculo param) {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("listaModoCalculoAnulacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultModoCalculo resultado = new ResultModoCalculo();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT SUBSTR(RV_LOW_VALUE,1,1) CODIGO, SUBSTR(RV_MEANING,1,30)  DESCRIPCION ");
			sql.append("from cg_ref_codes ");
			sql.append("WHERE RV_DOMAIN LIKE 'CART_COTIZA_BANCO.CAZB_TP_CALCULO' ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());

			result = pst.executeQuery();

			while (result.next()) {
				ModoCalculo obj = new ModoCalculo();
				obj.setCodigo(result.getString("CODIGO"));
				obj.setDescripcion(result.getString("DESCRIPCION"));
				resultado.setUnModoCalculo(obj);
			}

			LOG.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;

	}

	public ResultXmlPL obtenerEndososPoliza(ParamObtenerEndososPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerEndososPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerEndososPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			cstmt.setInt(3, Integer.valueOf(param.getNumCertificado()));
			cstmt.setString(4, param.getUsuario());

			cstmt.registerOutParameter(5, Types.INTEGER);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.registerOutParameter(8, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(5);
			String descError = cstmt.getString(6);
			String sqlError = cstmt.getString(7);
			Clob clob = cstmt.getClob(8);
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

	public ResultXmlPL obtenerDatosEndosoPoliza(ParamObtenerDatosEndosoPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerDatosEndosoPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero endoso", param.getNumEndoso());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerDatosEndosoPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			cstmt.setInt(3, Integer.valueOf(param.getNumCertificado()));
			cstmt.setInt(4, Integer.valueOf(param.getNumEndoso()));
			cstmt.setString(5, param.getUsuario());

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

	public ResultXmlPL obtenerProductoresPoliza(ParamObtenerProductoresPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerProductoresPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerProductoresPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			cstmt.setString(3, param.getUsuario());

			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);
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

	public ResultXmlPL obtenerDetallePoliza(ParamObtenerDetallePoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerDetallePoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerDetallePoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			cstmt.setString(3, param.getUsuario());

			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);
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

	public ResultXmlPL obtenerPolizas(ParamObtenerPolizas param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerPolizas");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Fecha desde de la poliza", param.getFechaDesde());
		logueo.setParametro("Fecha de emision de la poliza", param.getFechaEmision());
		logueo.setParametro("Codigo cliente", param.getCodCliente());
		logueo.setParametro("Cliente", param.getCliente());
		logueo.setParametro("Asegurado", param.getAsegurado());
		logueo.setParametro("Codigo producto", param.getCodProducto());
		logueo.setParametro("Fecha hasta de la poliza", param.getFechaHasta());
		logueo.setParametro("Vigente", param.getVigentes());
		logueo.setParametro("Orden", param.getOrden());
		logueo.setParametro("Codigo dato parametrico", param.getCodDatoParametrico());
		logueo.setParametro("Valor dato parametrico", param.getValorDatoParametrico());
		// FIXME FALTA BROKER
		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerPolizas");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			if (param.getCodRamo() != null) {
				cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			} else {
				cstmt.setNull(1, Types.INTEGER);
			}

			if (param.getNumPoliza() != null) {
				cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			} else {
				cstmt.setNull(2, Types.INTEGER);
			}

			if (param.getFechaDesde() == null || (param.getFechaDesde().equals("")) || (param.getFechaDesde().equals(Values.NULL))) {
				cstmt.setNull(3, Types.DATE);
			} else {
				cstmt.setDate(3, toSqlDate(param.getFechaDesde()));
			}

			if (param.getFechaEmision() == null || (param.getFechaEmision().equals("")) || (param.getFechaEmision().equals(Values.NULL))) {
				cstmt.setNull(4, Types.DATE);
			} else {
				cstmt.setDate(4, toSqlDate(param.getFechaEmision()));
			}

			cstmt.setString(5, param.getCodCliente());
			cstmt.setString(6, param.getCliente());
			cstmt.setString(7, param.getAsegurado());
			cstmt.setString(8, param.getCodProducto());

			if (param.getFechaHasta() == null || (param.getFechaHasta().equals("")) || (param.getFechaHasta().equals(Values.NULL))) {
				cstmt.setNull(9, Types.DATE);
			} else {
				cstmt.setDate(9, toSqlDate(param.getFechaHasta()));
			}

			if (param.getUsuario() == null || (param.getUsuario().equals("")) || (param.getUsuario().equals(Values.NULL))) {
				cstmt.setNull(10, Types.VARCHAR);
			} else {
				cstmt.setString(10, param.getUsuario());
			}

			cstmt.setString(11, param.getVigentes());
			if (param.getCodDatoParametrico() != null) {
				cstmt.setInt(12, param.getCodDatoParametrico());
			} else {
				cstmt.setNull(12, Types.INTEGER);
			}

			if (param.getValorDatoParametrico() == null || (param.getValorDatoParametrico().equals("")) || (param.getValorDatoParametrico().equals(Values.NULL))) {
				cstmt.setNull(13, Types.VARCHAR);
			} else {
				cstmt.setString(13, param.getValorDatoParametrico());
			}

			cstmt.setInt(14, param.getOrden());

			cstmt.registerOutParameter(15, Types.INTEGER);
			cstmt.registerOutParameter(16, Types.VARCHAR);
			cstmt.registerOutParameter(17, Types.VARCHAR);
			cstmt.registerOutParameter(18, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(15);
			String descError = cstmt.getString(16);
			String sqlError = cstmt.getString(17);
			Clob clob = cstmt.getClob(18);
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

	public ResultXmlPL obtenerPolizasCabezal(ParamObtenerPolizasCabezal param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerPolizasCabezal");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Fecha desde de la poliza", param.getFechaDesde());
		logueo.setParametro("Fecha hasta de la poliza", param.getFechaHasta());
		logueo.setParametro("Fecha emision desde de la poliza", param.getFechaEmision());
		logueo.setParametro("Fecha emision hasta de la poliza", param.getFechaEmisionHasta());
		logueo.setParametro("Codigo cliente", param.getCodCliente());
		logueo.setParametro("Descripcion cliente", param.getCliente());
		logueo.setParametro("Descripcion asegurado", param.getAsegurado());
		logueo.setParametro("Codigo producto", param.getCodProducto());
		logueo.setParametro("Codigo del productor", param.getCodProductor());
		logueo.setParametro("Estado", param.getVigentes());
		logueo.setParametro("Codigo dato parametrico", param.getCodDatoParametrico());
		logueo.setParametro("Valor dato parametrico", param.getValorDatoParametrico());
		logueo.setParametro("Ultimo Endoso", param.getUltimoEndoso());
		logueo.setParametro("Orden", param.getOrden());
		
		
		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerPolizas_cabezal");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			if (param.getCodRamo() != null) {
				cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			} else {
				cstmt.setNull(1, Types.INTEGER);
			}

			if (param.getNumPoliza() != null) {
				cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			} else {
				cstmt.setNull(2, Types.INTEGER);
			}

			if (param.getFechaDesde() == null || (param.getFechaDesde().equals("")) || (param.getFechaDesde().equals(Values.NULL))) {
				cstmt.setNull(3, Types.DATE);
			} else {
				cstmt.setDate(3, toSqlDate(param.getFechaDesde()));
			}

			if (param.getFechaHasta() == null || (param.getFechaHasta().equals("")) || (param.getFechaHasta().equals(Values.NULL))) {
				cstmt.setNull(4, Types.DATE);
			} else {
				cstmt.setDate(4, toSqlDate(param.getFechaHasta()));
			}
			
			
			
			if (param.getFechaEmision() == null || (param.getFechaEmision().equals("")) || (param.getFechaEmision().equals(Values.NULL))) {
				cstmt.setNull(5, Types.DATE);
			} else {
				cstmt.setDate(5, toSqlDate(param.getFechaEmision()));
			}
			
			if (param.getFechaEmisionHasta() == null || (param.getFechaEmisionHasta().equals("")) || (param.getFechaEmisionHasta().equals(Values.NULL))) {
				cstmt.setNull(6, Types.DATE);
			} else {
				cstmt.setDate(6, toSqlDate(param.getFechaEmisionHasta()));
			}

			cstmt.setString(7, param.getCodCliente());
			cstmt.setString(8, param.getCliente());
			cstmt.setString(9, param.getAsegurado());
			cstmt.setString(10, param.getCodProducto());

			

			if (param.getUsuario() == null || (param.getUsuario().equals("")) || (param.getUsuario().equals(Values.NULL))) {
				cstmt.setNull(11, Types.VARCHAR);
			} else {
				cstmt.setString(11, param.getUsuario());
			}

			if (param.getCodProductor() == null) {
				cstmt.setNull(12, Types.INTEGER);
			} else {
				cstmt.setInt(12, Integer.valueOf(param.getCodProductor()));
			}

			cstmt.setString(13, param.getVigentes());
			if (param.getCodDatoParametrico() != null) {
				cstmt.setInt(14, param.getCodDatoParametrico());
			} else {
				cstmt.setNull(14, Types.INTEGER);
			}

			if (param.getValorDatoParametrico() == null || (param.getValorDatoParametrico().equals("")) || (param.getValorDatoParametrico().equals(Values.NULL))) {
				cstmt.setNull(15, Types.VARCHAR);
			} else {
				cstmt.setString(15, param.getValorDatoParametrico());
			}
			
			if (param.getUltimoEndoso() != null) {
				cstmt.setString(16, param.getUltimoEndoso()?"S":"N");
			} else {
				cstmt.setNull(16, Types.VARCHAR);
			}

			cstmt.setInt(17, param.getOrden());

			cstmt.registerOutParameter(18, Types.INTEGER);
			cstmt.registerOutParameter(19, Types.VARCHAR);
			cstmt.registerOutParameter(20, Types.VARCHAR);
			cstmt.registerOutParameter(21, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(18);
			String descError = cstmt.getString(19);
			String sqlError = cstmt.getString(20);
			Clob clob = cstmt.getClob(21);
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

	public ResultXmlPL obtenerPolizaCabezalDetalle(ParamObtenerPolizaCabezalDetalle param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerPolizaCabezalDetalle");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtener_poliza_cabezal_detalle");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getCodRamo());
			cstmt.setInt(2, param.getNumPoliza());
			cstmt.setString(3, param.getUsuario());

			if (param.getCodProductor() == null) {
				cstmt.setNull(4, Types.INTEGER);

			} else {
				cstmt.setInt(4, Integer.valueOf(param.getCodProductor()));
			}

			cstmt.registerOutParameter(5, Types.INTEGER);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.registerOutParameter(8, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(5);
			String descError = cstmt.getString(6);
			String sqlError = cstmt.getString(7);
			Clob clob = cstmt.getClob(8);
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

	public ResultXmlPL obtenerCuotasPoliza(ParamObtenerCuotasPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerCuotasPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerCuotasPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			cstmt.setString(3, param.getAnuladas());
			cstmt.setString(4, param.getUsuario());

			cstmt.registerOutParameter(5, Types.INTEGER);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.registerOutParameter(8, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(5);
			String descError = cstmt.getString(6);
			String sqlError = cstmt.getString(7);
			Clob clob = cstmt.getClob(8);
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

	public ResultXmlPL obtenerFacturasPoliza(ParamObtenerFacturasPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerFacturasPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerFacturasPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			cstmt.setString(3, param.getAplicadas());
			cstmt.setString(4, param.getUsuario());

			cstmt.registerOutParameter(5, Types.INTEGER);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.registerOutParameter(8, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(5);
			String descError = cstmt.getString(6);
			String sqlError = cstmt.getString(7);
			Clob clob = cstmt.getClob(8);
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

	public ResultXmlPL obtenerImputacionesPoliza(ParamObtenerImputacionesPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerImputacionesPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerImputacionesPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			cstmt.setString(3, param.getUsuario());

			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);
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

	public ResultXmlPL obtenerRemesasPoliza(ParamObtenerRemesasPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerRemesasPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerRemesasPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			cstmt.setString(3, param.getUsuario());

			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);
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

	public ResultXmlPL obtenerTotalesCuotasPoliza(ParamObtenerTotalesCuotasPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerTotalesCuotasPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerTotalesCuotasPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			cstmt.setString(3, param.getUsuario());

			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);
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

	public ResultXmlPL obtenerPreliqPendientesPoliza(ParamObtenerPreliqPendientesPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerPreliqPendientesPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerPreliqPendientesPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			cstmt.setString(3, param.getUsuario());

			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);
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

	public ResultXmlPL obtenerCotizacionPoliza(ParamObtenerCotizacionPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerCotizacionPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero endoso", param.getNumEndoso());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerCotizacionPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			cstmt.setInt(3, Integer.valueOf(param.getNumCertificado()));
			cstmt.setInt(4, Integer.valueOf(param.getNumEndoso()));
			cstmt.setString(5, param.getUsuario());

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

	public ResultXmlPL obtenerContratantePoliza(ParamObtenerContratantePoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerContratantePoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerContratantePoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			cstmt.setString(3, param.getUsuario());

			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);
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

	public ResultXmlPL obtenerAseguradoCertifPoliza(ParamObtenerAseguradoCertifPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerAseguradoCertifPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerAseguradoCertifPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			cstmt.setInt(3, Integer.valueOf(param.getNumCertificado()));
			cstmt.setString(4, param.getUsuario());

			cstmt.registerOutParameter(5, Types.INTEGER);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.registerOutParameter(8, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(5);
			String descError = cstmt.getString(6);
			String sqlError = cstmt.getString(7);
			Clob clob = cstmt.getClob(8);
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

	public ResultXmlPL obtenerBienesAseguradosPoliza(ParamObtenerBienesAseguradosPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerBienesAseguradosPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero endoso", param.getNumEndoso());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerBienesAseguradosPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			cstmt.setInt(3, Integer.valueOf(param.getNumCertificado()));
			cstmt.setInt(4, Integer.valueOf(param.getNumEndoso()));
			cstmt.setString(5, param.getUsuario());

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

	public ResultXmlPL obtenerTextosPoliza(ParamObtenerTextosPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerTextosPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero endoso", param.getNumEndoso());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerTextosPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			cstmt.setInt(3, Integer.valueOf(param.getNumCertificado()));
			cstmt.setInt(4, Integer.valueOf(param.getNumEndoso()));
			cstmt.setString(5, param.getUsuario());

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

	public ResultObtenerDetalleTextoPoliza obtenerDetalleTextoPoliza(ParamObtenerDetalleTextoPoliza param) {
		ResultObtenerDetalleTextoPoliza resultado = new ResultObtenerDetalleTextoPoliza();

		Herramientas herramientas = new Herramientas();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerDetalleTextoPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo texto", param.getNumConsecutivoTexto());
		logueo.setParametro("Codigo texto", param.getCodTexto());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerDetalleTextoPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?)}");
			
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			cstmt.setInt(3, Integer.valueOf(param.getNumCertificado()));
			cstmt.setString(4, param.getNumConsecutivoTexto());
			cstmt.setString(5, param.getCodTexto());

			cstmt.setString(6, param.getUsuario());

			cstmt.registerOutParameter(7, Types.INTEGER);
			cstmt.registerOutParameter(8, Types.VARCHAR);
			cstmt.registerOutParameter(9, Types.VARCHAR);
			cstmt.registerOutParameter(10, Types.CLOB);

			cstmt.execute();

			Clob clob = cstmt.getClob(10);
			resultado.setDetalleTexto(herramientas.convertirClob(clob));

			int codError = cstmt.getInt(7);
			String descError = cstmt.getString(8);
			String sqlError = cstmt.getString(9);

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

	public ResultXmlPL obtenerAnexosPoliza(ParamObtenerAnexosPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerAnexosPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero endoso", param.getNumEndoso());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerAnexosPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			cstmt.setInt(3, Integer.valueOf(param.getNumCertificado()));
			cstmt.setInt(4, Integer.valueOf(param.getNumEndoso()));
			cstmt.setString(5, param.getUsuario());

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

	public ResultXmlPL obtenerListaBienesPoliza(ParamObtenerListaBienesPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerListaBienesPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero endoso", param.getNumEndoso());
		logueo.setParametro("Codigo de bien asegurado", param.getCodBienAsegurado());
		logueo.setParametro("Numero consecutivo de bien asegurado", param.getNumConsecutivoBienAsegurado());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerListaBienesPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			cstmt.setInt(3, Integer.valueOf(param.getNumCertificado()));
			cstmt.setInt(4, Integer.valueOf(param.getNumEndoso()));
			cstmt.setInt(5, Integer.valueOf(param.getCodBienAsegurado()));
			cstmt.setInt(6, Integer.valueOf(param.getNumConsecutivoBienAsegurado()));
			cstmt.setString(7, param.getUsuario());

			cstmt.registerOutParameter(8, Types.INTEGER);
			cstmt.registerOutParameter(9, Types.VARCHAR);
			cstmt.registerOutParameter(10, Types.VARCHAR);
			cstmt.registerOutParameter(11, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(8);
			String descError = cstmt.getString(9);
			String sqlError = cstmt.getString(10);
			Clob clob = cstmt.getClob(11);
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

	public ResultXmlPL obtenerUbicacionBienPoliza(ParamObtenerUbicacionBienPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerUbicacionBienPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo de bien asegurado", param.getNumConsecutivoBienAsegurado());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerUbicacionBienPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			cstmt.setInt(3, Integer.valueOf(param.getNumCertificado()));
			cstmt.setInt(4, Integer.valueOf(param.getNumEndoso()));
			cstmt.setInt(5, Integer.valueOf(param.getNumConsecutivoBienAsegurado()));
			cstmt.setString(6, param.getUsuario());

			cstmt.registerOutParameter(7, Types.INTEGER);
			cstmt.registerOutParameter(8, Types.VARCHAR);
			cstmt.registerOutParameter(9, Types.VARCHAR);
			cstmt.registerOutParameter(10, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(7);
			String descError = cstmt.getString(8);
			String sqlError = cstmt.getString(9);
			Clob clob = cstmt.getClob(10);
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

	public ResultXmlPL obtenerBeneficiariosPoliza(ParamObtenerBeneficiariosPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerBeneficiariosPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo de bien asegurado", param.getNumConsecutivoBienAsegurado());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerBeneficiariosPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			cstmt.setInt(3, Integer.valueOf(param.getNumCertificado()));
			cstmt.setInt(4, Integer.valueOf(param.getNumEndoso()));
			cstmt.setInt(5, Integer.valueOf(param.getNumConsecutivoBienAsegurado()));
			cstmt.setString(6, param.getUsuario());

			cstmt.registerOutParameter(7, Types.INTEGER);
			cstmt.registerOutParameter(8, Types.VARCHAR);
			cstmt.registerOutParameter(9, Types.VARCHAR);
			cstmt.registerOutParameter(10, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(7);
			String descError = cstmt.getString(8);
			String sqlError = cstmt.getString(9);
			Clob clob = cstmt.getClob(10);
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

	public ResultXmlPL obtenerAcreedoresPoliza(ParamObtenerAcreedoresPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerAcreedoresPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero endoso", param.getNumEndoso());
		logueo.setParametro("Codigo de bien asegurado", param.getCodBienAsegurado());
		logueo.setParametro("Numero consecutivo de bien asegurado", param.getNumConsecutivoBienAsegurado());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerAcreedoresPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			cstmt.setInt(3, Integer.valueOf(param.getNumCertificado()));
			cstmt.setInt(4, Integer.valueOf(param.getNumEndoso()));
			cstmt.setInt(5, Integer.valueOf(param.getCodBienAsegurado()));
			cstmt.setInt(6, Integer.valueOf(param.getNumConsecutivoBienAsegurado()));
			cstmt.setString(7, param.getUsuario());

			cstmt.registerOutParameter(8, Types.INTEGER);
			cstmt.registerOutParameter(9, Types.VARCHAR);
			cstmt.registerOutParameter(10, Types.VARCHAR);
			cstmt.registerOutParameter(11, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(8);
			String descError = cstmt.getString(9);
			String sqlError = cstmt.getString(10);
			Clob clob = cstmt.getClob(11);
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

	public ResultXmlPL obtenerFranquiciasPoliza(ParamObtenerFranquiciasPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerFranquiciasPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero endoso", param.getNumEndoso());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerFranquiciasPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			cstmt.setInt(3, Integer.valueOf(param.getNumCertificado()));
			cstmt.setInt(4, Integer.valueOf(param.getNumEndoso()));
			cstmt.setString(5, param.getUsuario());

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

	public ResultXmlPL obtenerAhorrosPoliza(ParamObtenerAhorrosPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerAhorrosPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero endoso", param.getNumEndoso());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerAhorrosPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			cstmt.setInt(3, Integer.valueOf(param.getNumCertificado()));
			cstmt.setInt(4, Integer.valueOf(param.getNumEndoso()));
			cstmt.setString(5, param.getUsuario());

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

	public ResultXmlPL obtenerRemesasXCliente(ParamObtenerRemesasXCliente param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerRemesasXCliente");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de cliente", param.getCodCliente());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerRemesasXCliente");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");
			cstmt.setString(1, param.getCodCliente());
			cstmt.setString(2, param.getUsuario());
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

	public ResultXmlPL obtenerDatosRemesa(ParamObtenerDatosRemesa param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerDatosRemesa");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero de remesa", param.getNumRemesa());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerDatosRemesa");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getNumRemesa()));
			cstmt.setString(2, param.getUsuario());

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

	public ResultXmlPL obtenerCancelacionesRemesa(ParamObtenerCancelacionesRemesa param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerCancelacionesRemesa");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero de remesa", param.getNumRemesa());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerCancelacionesRemesa");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getNumRemesa()));

			cstmt.setString(2, param.getUsuario());

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

	public ResultXmlPL obtenerDetalleRemesa(ParamObtenerDetalleRemesa param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerDetalleRemesa");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero de remesa", param.getNumRemesa());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerDetalleRemesa");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getNumRemesa()));
			cstmt.setString(2, param.getUsuario());

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

	public ResultXmlPL obtenerOrigenRemesaAutomatica(ParamObtenerOrigenRemesaAuto param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerOrigenRemesaAutomatica");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero de remesa", param.getNumRemesa());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerOrigenRemesaAutomatica");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getNumRemesa()));
			cstmt.setString(2, param.getUsuario());
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

	public ResultXmlPL obtenerNotasSiniestro(ParamObtenerNotasSiniestro param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerNotasSiniestro");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero de enlace", param.getEnlace());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerNotasSiniestro");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?)}");
			cstmt.setString(1, param.getEnlace());

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

	public ResultXmlPL obtenerBonifNoSiniestroPoliza(ParamObtenerBonifNoSiniestroPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerBonifNoSiniestroPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());
		logueo.setParametro("Numero de poliza", param.getNumPoliza());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerBonifNoSiniestroPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");

			if (param.getCodRamo() != null) {
				cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			}

			if (param.getNumPoliza() != null) {
				cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			}
			cstmt.setString(3, param.getUsuario());

			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);
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

	public ResultXmlPL obtenerDeclaracionSiniestro(ParamObtenerDeclaracionSiniestro param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerDeclaracionSiniestro");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());
		logueo.setParametro("Anio", param.getAnio());
		logueo.setParametro("Numero siniestro", param.getNumSiniestro());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerDeclaracionSiniestro");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getAnio()));
			cstmt.setInt(2, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(3, Integer.valueOf(param.getNumSiniestro()));

			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);
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

	public ResultXmlPL obtenerDatosSiniestro(ParamObtenerDatosSiniestro param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerDatosSiniestro");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());
		logueo.setParametro("Anio", param.getAnio());
		logueo.setParametro("Numero siniestro", param.getNumSiniestro());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerDatosSiniestro");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getAnio()));
			cstmt.setInt(2, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(3, Integer.valueOf(param.getNumSiniestro()));

			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);
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

	public ResultXmlPL obtenerSiniestrosCliente(ParamObtenerSiniestrosCliente param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerSiniestrosCliente");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de cliente", param.getCodCliente());
		logueo.setParametro("Nacionalidad de cliente", param.getNacionalidadCliente());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerSiniestrosCliente");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");
			cstmt.setString(1, param.getNacionalidadCliente());
			cstmt.setString(2, param.getCodCliente());

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

	public ResultObtenerSiniestros obtenerSiniestros(ParamObtenerSiniestros param) {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerSiniestros");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultObtenerSiniestros resultado = new ResultObtenerSiniestros();
		// FIXME FALTA BROKER
		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT SS.SISI_NU_ANNIO, SS.SISI_CARP_CD_RAMO, SS.SISI_NU_SINIESTRO, ");
			sql.append("SS.SISI_CAPO_NU_POLIZA, SS.SISI_CACE_NU_CERTIFICADO, SS.SISI_CACE_NU_ENDOSO, SS.SISI_CD_ENLACE ");
			sql.append("FROM SINT_SINIESTROS SS ");
			sql.append("WHERE EXISTS (select 1 from cart_certificados CC ");
			sql.append("where SS.sisi_casu_cd_sucursal = CC.cace_casu_cd_sucursal ");
			sql.append("and SS.sisi_carp_cd_ramo = CC.cace_carp_cd_ramo ");
			sql.append("and SS.sisi_capo_nu_poliza = CC.cace_capo_nu_poliza ");
			sql.append("and SS.sisi_cace_nu_certificado = CC.cace_nu_certificado ");
			sql.append("and CC.cace_capd_cd_productor = Pac_Web_Util.FUN_CODIGO_CORREDOR(?)) ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, param.getUsuario());

			result = pst.executeQuery();

			while (result.next()) {
				DatosBasicosSiniestro dat = new DatosBasicosSiniestro();
				dat.setAnio(result.getInt("SISI_NU_ANNIO"));
				dat.setCodRamo(result.getInt("SISI_CARP_CD_RAMO"));
				dat.setNumSiniestro(result.getInt("SISI_NU_SINIESTRO"));
				dat.setNumPoliza(result.getInt("SISI_CAPO_NU_POLIZA"));
				dat.setNumCertificado(result.getInt("SISI_CACE_NU_CERTIFICADO"));
				dat.setNumEndoso(result.getInt("SISI_CACE_NU_ENDOSO"));
				dat.setEnlace(result.getString("SISI_CD_ENLACE"));

				resultado.setUnDatoSiniestro(dat);
			}

			LOG.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;

	}

	public ResultXmlPL obtenerSiniestrosConFiltro(ParamObtenerSiniestrosConFiltro param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerSiniestrosConFiltro");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Fecha ocurrencia", param.getFechaOcurrencia());
		logueo.setParametro("Fecha entrada", param.getFechaEntrada());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo asegurado", param.getCodAsegurado());
		logueo.setParametro("Codigo ejecutivo", param.getCodEjecutivo());
		logueo.setParametro("Fuera pauta", param.getFueraPauta());
		logueo.setParametro("Asegurado", param.getAsegurado());
		logueo.setParametro("Descripcion ejecutivo", param.getDescripEjecutivo());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero siniestro", param.getNumSiniestro());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Anno", param.getAnio());
		logueo.setParametro("Codigo Usuario", param.getUsuario());
		logueo.setParametro("Codigo de productor", param.getCodProductor());
		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerSiniestrosConFiltro");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			if (param.getFechaOcurrencia() == null || (param.getFechaOcurrencia().equals("")) || (param.getFechaOcurrencia().equals(Values.NULL))) {
				cstmt.setNull(1, Types.DATE);
			} else {
				cstmt.setDate(1, toSqlDate(param.getFechaOcurrencia()));
			}

			if (param.getFechaEntrada() == null || (param.getFechaEntrada().equals("")) || (param.getFechaEntrada().equals(Values.NULL))) {
				cstmt.setNull(2, Types.DATE);
			} else {
				cstmt.setDate(2, toSqlDate(param.getFechaEntrada()));
			}

			if (param.getNumPoliza() != null) {
				cstmt.setInt(3, Integer.valueOf(param.getNumPoliza()));
			} else {
				cstmt.setNull(3, Types.INTEGER);
			}

			if (param.getCodAsegurado() != null) {
				cstmt.setInt(4, Integer.valueOf(param.getCodAsegurado()));
			} else {
				cstmt.setNull(4, Types.INTEGER);
			}

			if (param.getCodEjecutivo() != null) {
				cstmt.setInt(5, Integer.valueOf(param.getCodEjecutivo()));
			} else {
				cstmt.setNull(5, Types.INTEGER);
			}
			cstmt.setString(6, param.getFueraPauta());
			cstmt.setString(7, param.getAsegurado());
			cstmt.setString(8, param.getDescripEjecutivo());

			if (param.getCodRamo() != null) {
				cstmt.setInt(9, Integer.valueOf(param.getCodRamo()));
			} else {
				cstmt.setNull(9, Types.INTEGER);
			}

			if (param.getNumSiniestro() != null) {
				cstmt.setInt(10, Integer.valueOf(param.getNumSiniestro()));
			} else {
				cstmt.setNull(10, Types.INTEGER);
			}

			cstmt.setString(11, param.getNumCertificado());

			if (param.getAnio() != null) {
				cstmt.setInt(12, Integer.valueOf(param.getAnio()));
			} else {
				cstmt.setNull(12, Types.INTEGER);
			}

			cstmt.setString(13, param.getUsuario());

			if (param.getCodProductor() == null) {
				cstmt.setNull(14, Types.INTEGER);
			} else {
				cstmt.setInt(14, Integer.valueOf(param.getCodProductor()));
			}

			cstmt.registerOutParameter(15, Types.INTEGER);
			cstmt.registerOutParameter(16, Types.VARCHAR);
			cstmt.registerOutParameter(17, Types.VARCHAR);
			cstmt.registerOutParameter(18, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(15);
			String descError = cstmt.getString(16);
			String sqlError = cstmt.getString(17);
			Clob clob = cstmt.getClob(18);
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

	public ResultXmlPL validarPoliza(ParamValidarPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("validarPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_validarPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");

			if (param.getCodRamo() != null) {
				cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			} else {
				cstmt.setNull(1, Types.INTEGER);
			}

			if (param.getNumPoliza() != null) {
				cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			} else {
				cstmt.setNull(2, Types.INTEGER);
			}

			if (param.getNumCertificado() != null) {
				cstmt.setInt(3, Integer.valueOf(param.getNumCertificado()));
			} else {
				cstmt.setNull(3, Types.INTEGER);
			}

			cstmt.setString(4, param.getUsuario());

			cstmt.registerOutParameter(5, Types.INTEGER);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.registerOutParameter(8, Types.CLOB);

			cstmt.execute();

			int codError = cstmt.getInt(5);
			String descError = cstmt.getString(6);
			String sqlError = cstmt.getString(7);
			Clob clob = cstmt.getClob(8);
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

	public ResultXmlPL validarAnulacionPoliza(ParamValidarAnulacionPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("validarAnulacionPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero de poliza", param.getNumPoliza());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());
		logueo.setParametro("Numero de certificado", param.getNumCertificado());
		logueo.setParametro("Tipo de anulacion", param.getTipoAnulacion());
		logueo.setParametro("Fecha de anulacion", param.getFechaAnulacion());
		logueo.setParametro("Tiene facultativo", param.getTieneFacultativo());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_validarAnulacionPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			cstmt.setInt(3, Integer.valueOf(param.getNumCertificado()));
			cstmt.setString(4, param.getUsuario());
			cstmt.setString(5, param.getTipoAnulacion());

			if (param.getFechaAnulacion() != null) {
				cstmt.setDate(6, toSqlDate(param.getFechaAnulacion()));
			} else {
				cstmt.setNull(6, Types.DATE);
			}

			cstmt.setString(7, param.getTieneFacultativo());

			cstmt.registerOutParameter(8, Types.INTEGER);
			cstmt.registerOutParameter(9, Types.VARCHAR);
			cstmt.registerOutParameter(10, Types.VARCHAR);
			cstmt.registerOutParameter(11, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(8);
			String descError = cstmt.getString(9);
			String sqlError = cstmt.getString(10);
			Clob clob = cstmt.getClob(11);
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

	public ResultXmlPL anularPoliza(ParamAnularPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("anularPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Codigo motivo", param.getCodMotivoAbandono());
		logueo.setParametro("Aclaracion", param.getAclaracion());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_anularPoliza");
			logueo.setNombrePl(nombrePL);
			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?)}");

			if (param.getCodRamo() != null) {
				cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			} else {
				cstmt.setNull(1, Types.INTEGER);
			}

			if (param.getNumPoliza() != null) {
				cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			} else {
				cstmt.setNull(2, Types.INTEGER);
			}

			if (param.getNumCotizacion() != null) {
				cstmt.setInt(3, Integer.valueOf(param.getNumCotizacion()));
			} else {
				cstmt.setNull(3, Types.INTEGER);
			}

			if (param.getNumCertificado() != null) {
				cstmt.setInt(4, Integer.valueOf(param.getNumCertificado()));
			} else {
				cstmt.setNull(4, Types.INTEGER);
			}

			if (param.getCodMotivoAbandono() != null) {
				cstmt.setInt(5, Integer.valueOf(param.getCodMotivoAbandono()));
			} else {
				cstmt.setNull(5, Types.INTEGER);
			}
			cstmt.setString(6, param.getAclaracion());
			cstmt.setString(7, param.getUsuario());

			cstmt.registerOutParameter(8, Types.INTEGER);
			cstmt.registerOutParameter(9, Types.VARCHAR);
			cstmt.registerOutParameter(10, Types.VARCHAR);
			cstmt.registerOutParameter(11, Types.CLOB);

			cstmt.execute();

			int codError = cstmt.getInt(8);
			String descError = cstmt.getString(9);
			String sqlError = cstmt.getString(10);
			Clob clob = cstmt.getClob(11);

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

	public ResultXmlPL listaPlanesPagoRecuotificacion(ParamListaPlanesPagoRec param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("listaPlanesPagoRecuotificacion");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero de certificado", param.getNumCertificado());
		logueo.setParametro("Numero de poliza", param.getNumPoliza());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());
		logueo.setParametro("Numero de endoso", param.getNumEndoso());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_listaPlanesPagoRecuotificacion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?)}");

			if (param.getCodRamo() != null) {
				cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			}

			if (param.getNumPoliza() != null) {
				cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			}

			if (param.getNumCertificado() != null) {
				cstmt.setInt(3, Integer.valueOf(param.getNumCertificado()));
			} else {
				cstmt.setNull(3, Types.INTEGER);
			}

			if (param.getNumEndoso() != null) {
				cstmt.setInt(4, Integer.valueOf(param.getNumEndoso()));
			} else {
				cstmt.setNull(4, Types.INTEGER);
			}

			cstmt.setString(5, param.getUsuario());

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

	public ResultXmlPL validarPolizaRecuotificacion(ParamValidarPolizaRec param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("validarPolizaRecuotificacion");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_validarPolizaRecuotificacion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");

			if (param.getCodRamo() != null) {
				cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			}

			if (param.getNumPoliza() != null) {
				cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			}

			if (param.getNumCertificado() != null) {
				cstmt.setInt(3, Integer.valueOf(param.getNumCertificado()));
			} else {
				cstmt.setNull(3, Types.INTEGER);
			}

			cstmt.setString(4, param.getUsuario());

			cstmt.registerOutParameter(5, Types.INTEGER);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.registerOutParameter(8, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(5);
			String descError = cstmt.getString(6);
			String sqlError = cstmt.getString(7);
			Clob clob = cstmt.getClob(8);
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

	public ResultXmlPL calcularRecuotificacion(ParamCalcularRec param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("calcularRecuotificacion");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero endoso", param.getNumEndoso());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Fecha emision", param.getFechaEmision());
		logueo.setParametro("Codigo plan pago", param.getCodPlanPago());
		logueo.setParametro("Dia vencimiento", param.getDiaVencimiento());
		logueo.setParametro("Monto prima", param.getMontoPrima());
		logueo.setParametro("Monto premio", param.getMontoPremio());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_calcularRecuotificacion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			if (param.getCodRamo() != null) {
				cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			}

			if (param.getNumPoliza() != null) {
				cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			}

			if (param.getNumCertificado() != null) {
				cstmt.setInt(3, Integer.valueOf(param.getNumCertificado()));
			} else {
				cstmt.setNull(3, Types.INTEGER);
			}

			if (param.getNumEndoso() != null) {
				cstmt.setInt(4, Integer.valueOf(param.getNumEndoso()));
			}

			if (param.getNumCotizacion() != null) {
				cstmt.setInt(5, Integer.valueOf(param.getNumCotizacion()));
			}

			if (param.getFechaEmision() != null) {
				cstmt.setDate(6, toSqlDate(param.getFechaEmision()));
			}

			if (param.getCodPlanPago() != null) {
				cstmt.setInt(7, Integer.valueOf(param.getCodPlanPago()));
			}

			if (param.getDiaVencimiento() != null) {
				cstmt.setInt(8, Integer.valueOf(param.getDiaVencimiento()));
			} else {
				cstmt.setNull(8, Types.INTEGER);
			}

			cstmt.setString(9, "N"); // RECUOTIFICAR "S" CALCULAR "N"

			if (param.getMontoPrima() != null) {
				cstmt.setDouble(10, Double.valueOf(param.getMontoPrima()));
			}

			if (param.getMontoPremio() != null) {
				cstmt.setDouble(11, Double.valueOf(param.getMontoPremio()));
			}

			cstmt.setString(12, param.getUsuario());

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

	public ResultXmlPL recuotificarPoliza(ParamRecuotificarPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("recuotificarPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero endoso", param.getNumEndoso());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Fecha emision", param.getFechaEmision());
		logueo.setParametro("Codigo plan pago", param.getCodPlanPago());
		logueo.setParametro("Dia vencimiento", param.getDiaVencimiento());
		logueo.setParametro("Monto prima", param.getMontoPrima());
		logueo.setParametro("Monto premio", param.getMontoPremio());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_recuotificarPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			if (param.getCodRamo() != null) {
				cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			}

			if (param.getNumPoliza() != null) {
				cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			}

			if (param.getNumCertificado() != null) {
				cstmt.setInt(3, Integer.valueOf(param.getNumCertificado()));
			} else {
				cstmt.setNull(3, Types.INTEGER);
			}

			if (param.getNumEndoso() != null) {
				cstmt.setInt(4, Integer.valueOf(param.getNumEndoso()));
			}

			if (param.getNumCotizacion() != null) {
				cstmt.setInt(5, Integer.valueOf(param.getNumCotizacion()));
			}

			if (param.getFechaEmision() != null) {
				cstmt.setDate(6, toSqlDate(param.getFechaEmision()));
			}

			if (param.getCodPlanPago() != null) {
				cstmt.setInt(7, Integer.valueOf(param.getCodPlanPago()));
			}

			if (param.getDiaVencimiento() != null) {
				cstmt.setInt(8, Integer.valueOf(param.getDiaVencimiento()));
			} else {
				cstmt.setNull(8, Types.INTEGER);
			}

			cstmt.setString(9, "S"); // RECUOTIFICAR "S" CALCULAR "N"

			if (param.getMontoPrima() != null) {
				cstmt.setDouble(10, Double.valueOf(param.getMontoPrima()));
			}

			if (param.getMontoPremio() != null) {
				cstmt.setDouble(11, Double.valueOf(param.getMontoPremio()));
			}

			cstmt.setString(12, param.getUsuario());

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

	public ResultMotivoAbandono listaMotivoAbandono(ParamMotivoAbandono param) {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("listaMotivoAbandono");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultMotivoAbandono resultado = new ResultMotivoAbandono();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT RV_LOW_VALUE, RV_MEANING ");
			sql.append("FROM CG_REF_CODES ");
			sql.append("WHERE RV_DOMAIN = 'MOTIVO_ABANDONO' ");
			sql.append("Order by RV_LOW_VALUE ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());

			result = pst.executeQuery();

			while (result.next()) {
				MotivoAbandono mot = new MotivoAbandono();
				mot.setCodigo(result.getInt("RV_LOW_VALUE"));
				mot.setDescripcion(result.getString("RV_MEANING"));

				resultado.setUnMotivo(mot);
			}

			LOG.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;

	}

	public ResultOrigenEndoso listaOrigenEndoso(ParamOrigenEndoso param) {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("listaOrigenEndoso");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultOrigenEndoso resultado = new ResultOrigenEndoso();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT CAEO_CD_ORIGEN_ENDOSO, CAEO_DE_ORIGEN_ENDOSO ");
			sql.append("FROM CART_ORIGEN_ENDOSOS ");
			sql.append("ORDER BY 1 ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());

			result = pst.executeQuery();

			while (result.next()) {
				OrigenEndoso ori = new OrigenEndoso();
				ori.setCodigo(result.getInt("CAEO_CD_ORIGEN_ENDOSO"));
				ori.setDescripcion(result.getString("CAEO_DE_ORIGEN_ENDOSO"));
				resultado.setUnOrigen(ori);
			}

			LOG.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;

	}

	public ResultXmlPL obtenerConsolidadoPoliza(ParamObtenerConsolidado param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerConsolidadoPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero de poliza", param.getNumPoliza());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());
		logueo.setParametro("Numero de certificado", param.getNumCertificado());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerConsolidadoPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");

			if (param.getCodRamo() != null) {
				cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			}

			if (param.getNumPoliza() != null) {
				cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			}

			if (param.getNumCertificado() != null) {
				cstmt.setInt(3, Integer.valueOf(param.getNumCertificado()));
			} else {
				cstmt.setNull(3, Types.INTEGER);
			}

			cstmt.setString(4, param.getUsuario());

			cstmt.registerOutParameter(5, Types.INTEGER);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.registerOutParameter(8, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(5);
			String descError = cstmt.getString(6);
			String sqlError = cstmt.getString(7);
			Clob clob = cstmt.getClob(8);
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

	public ResultXmlPL nuevaCotizacionEndoso(ParamNuevaCotizacionEndoso param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("nuevaCotizacionEndoso");

		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());
		logueo.setParametro("Codigo de producto", param.getCodProducto());
		logueo.setParametro("Numero de poliza", param.getNumPoliza());
		logueo.setParametro("Codigo origen de endoso", param.getCodOrigenEndoso());
		logueo.setParametro("Fecha desde vigencia", param.getFechaDesdeVigencia());
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro("Codigo direccion envio", param.getCodDireccionEnvio());
		logueo.setParametro("Codigo direccion cobro", param.getCodDireccionCobro());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_nuevaCotizacionEndoso");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			if (param.getCodRamo() != null) {
				cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			}

			cstmt.setString(2, param.getCodProducto());

			if (param.getNumPoliza() != null) {
				cstmt.setInt(3, Integer.valueOf(param.getNumPoliza()));
			}

			if (param.getNumCertificado() != null) {
				cstmt.setInt(4, param.getNumCertificado());
			} else {
				cstmt.setNull(4, Types.INTEGER);
			}

			if (param.getCodOrigenEndoso() != null) {
				cstmt.setInt(5, Integer.valueOf(param.getCodOrigenEndoso()));
			}

			if (param.getFechaDesdeVigencia() != null) {
				cstmt.setDate(6, toSqlDate(param.getFechaDesdeVigencia()));
			}

			cstmt.setString(7, param.getUsuario());

			if (param.getCodDireccionEnvio() != null) {
				cstmt.setInt(8, param.getCodDireccionEnvio());
			}

			if (param.getCodDireccionCobro() != null) {
				cstmt.setInt(9, param.getCodDireccionCobro());
			}
			cstmt.registerOutParameter(10, Types.INTEGER);
			cstmt.registerOutParameter(11, Types.VARCHAR);
			cstmt.registerOutParameter(12, Types.VARCHAR);
			cstmt.registerOutParameter(13, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(10);
			String descError = cstmt.getString(11);
			String sqlError = cstmt.getString(12);
			Clob clob = cstmt.getClob(13);
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

	public ResultXmlPL agregarCertificadoEndoso(ParamAgregarCertificadoEndoso param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("agregarCertificadoEndoso");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero poliza", param.getNumPoliza());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_agregarCertificadoEndoso");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");

			if (param.getNumCotizacion() != null) {
				cstmt.setInt(1, param.getNumCotizacion());
			} else {
				cstmt.setNull(1, Types.NULL);
			}

			if (param.getNumCertificado() != null) {
				cstmt.setInt(2, param.getNumCertificado());
			} else {
				cstmt.setNull(2, Types.NULL);
			}

			if (param.getNumPoliza() != null) {
				cstmt.setInt(3, param.getNumPoliza());
			}

			cstmt.setString(4, param.getUsuario());

			cstmt.registerOutParameter(5, Types.INTEGER);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.registerOutParameter(8, Types.CLOB);

			cstmt.execute();

			int codError = cstmt.getInt(5);
			String descError = cstmt.getString(6);
			String sqlError = cstmt.getString(7);
			Clob clob = cstmt.getClob(8);
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

	public ResultXmlPL obtenerDatosBasicosEndoso(ParamObtenerDatosBasicosEndoso param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerDatosBasicosEndoso");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerDatosBasicosEndoso");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");

			if (param.getCodRamo() != null) {
				cstmt.setInt(1, param.getCodRamo());
			}

			if (param.getNumPoliza() != null) {
				cstmt.setInt(2, param.getNumPoliza());
			}

			cstmt.setString(3, param.getUsuario());

			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);
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

	public ResultMotivoEndoso listaMotivosEndoso(ParamMotivoEndoso param) {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("listaMotivosEndoso");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultMotivoEndoso resultado = new ResultMotivoEndoso();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT CAEO_CD_ORIGEN_ENDOSO, CAEO_DE_ORIGEN_ENDOSO ");
			sql.append("FROM CART_ORIGEN_ENDOSOS ");
			sql.append("WHERE CAEO_CD_ORIGEN_ENDOSO IN (1,2,3) ");
			sql.append("ORDER BY 1 ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());

			result = pst.executeQuery();

			ArrayList<MotivoEndoso> motivos = new ArrayList<MotivoEndoso>();
			while (result.next()) {
				MotivoEndoso mot = new MotivoEndoso();
				mot.setCodigo(result.getInt("CAEO_CD_ORIGEN_ENDOSO"));
				mot.setDescripcion(result.getString("CAEO_DE_ORIGEN_ENDOSO"));
				motivos.add(mot);
			}

			resultado.setMotivoEndoso(motivos);

			LOG.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;

	}
	//revisado con GCANTO, no tiene impacto BROKER
	public ResultDatosParametricosEndoso listaDatosParametricosEndoso(ParamDatosParametricosEndoso entrada) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("listaDatosParametricosEndoso");
		logueo.setParametro(Values.USUARIO, entrada.getUsuario());
		logueo.setParametro(Values.CLAVE, entrada.getClave());
		logueo.setParametro("Numero cotizacion", entrada.getNumCotizacion());
		logueo.setParametro("Numero poliza", entrada.getNumPoliza());
		logueo.setParametro("Codigo de ramo", entrada.getCodRamo());
		logueo.setParametro("Es ultimo endoso", entrada.getEsUltimoEndoso());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultDatosParametricosEndoso resultado = new ResultDatosParametricosEndoso();

		try {
			conn = crearConexion();

			if (entrada.getNumPoliza() != null && entrada.getCodRamo() != null) {
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT DISTINCT CPD.CRPD_CRCD_CD_DATO codigo, CRCD_DE_DATO descripcion ");
				sql.append("FROM CRET_PRODUCTOS_DATOS CPD, CRET_DATOS, CART_CERTIFICADOS_ENDOSOS CCE ");
				sql.append("WHERE CRPD_CARP_CD_RAMO = ? "); // codramo
				sql.append("AND CRPD_CAPO_NU_POLIZA = ? "); // numPoliza
				sql.append("AND CPD.CRPD_CRCD_CD_DATO = CRCD_CD_DATO ");
				sql.append("AND CACW_CARP_CD_RAMO = CPD.CRPD_CARP_CD_RAMO ");
				sql.append("AND CCE.CACW_CAPO_NU_POLIZA = CPD.CRPD_CAPO_NU_POLIZA ");
				sql.append("AND CCE.CACW_CACE_NU_CERTIFICADO = CPD.CRPD_CACE_NU_CERTIFICADO ");
				sql.append("AND CCE.CACW_NU_ENDOSO = CPD.CRPD_NU_ENDOSO ");
				sql.append("AND EXISTS (SELECT NULL FROM CRET_COTIZA_DATOS ");
				sql.append("WHERE CRCO_CAPJ_CD_SUCURSAL = CCE.CACW_CASU_CD_SUCURSAL ");
				sql.append("AND CRCO_CAZB_NU_COTIZACION  = CCE.CACW_CAZB_NU_COTIZACION ");
				sql.append("AND CRCO_CAZB_NU_CONSECUTIVO = CCE.CACW_CAZB_NU_CONSECUTIVO ");
				sql.append("AND CRCO_CARP_CD_RAMO = CRPD_CARP_CD_RAMO ");
				sql.append("AND CRCO_CAPU_CD_PRODUCTO = CRPD_CAPU_CD_PRODUCTO ");
				sql.append("AND CRCO_CD_BIEN_ASEG = CRPD_CD_BIEN_ASEG ");
				sql.append("AND CRCO_CRTD_CD_DATO = CRPD_CRCD_CD_DATO ");
				sql.append("AND  CRCO_ST_VISIBLE IN ('S','D') ) ");
				
				if(entrada.getEsUltimoEndoso()) {
				sql.append("AND CRPD_NU_ENDOSO = (SELECT MAX(CPD1.CRPD_NU_ENDOSO) ");
				sql.append("FROM CRET_PRODUCTOS_DATOS CPD1 ");
				sql.append("WHERE CPD1.CRPD_CARP_CD_RAMO = CPD.CRPD_CARP_CD_RAMO ");
				sql.append("AND CPD1.CRPD_CAPO_NU_POLIZA = CPD.CRPD_CAPO_NU_POLIZA ");
				sql.append("AND CPD1.CRPD_CACE_NU_CERTIFICADO = CPD.CRPD_CACE_NU_CERTIFICADO) ");
				sql.append("ORDER BY 2 ");
				}
				logueo.setParametro(Values.CONSULTA, sql.toString());
				pst = conn.prepareStatement(sql.toString());
				pst.setInt(1, entrada.getCodRamo());
				pst.setInt(2, entrada.getNumPoliza());
			} else {
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT DISTINCT CRCO_CRTD_CD_DATO codigo, CRCD_DE_DATO descripcion ");
				sql.append("FROM CRET_COTIZA_DATOS,CRET_DATOS ");
				sql.append("WHERE CRCD_CD_DATO = CRCO_CRTD_CD_DATO ");
				sql.append("AND CRCO_CAZB_NU_COTIZACION  = ? "); // numCotizacion
				sql.append("AND CRCO_ST_VISIBLE = 'S' ");
				sql.append("ORDER BY 2 ");

				logueo.setParametro(Values.CONSULTA, sql.toString());
				pst = conn.prepareStatement(sql.toString());
				pst.setInt(1, entrada.getNumCotizacion());
			}
			result = pst.executeQuery();

			ArrayList<DatoParametricoEndoso> datos = new ArrayList<DatoParametricoEndoso>();
			while (result.next()) {
				DatoParametricoEndoso dato = new DatoParametricoEndoso();
				dato.setCodigo(result.getInt("codigo"));
				dato.setDescripcion(result.getString("descripcion"));
				datos.add(dato);
			}

			resultado.setDatosParametricos(datos);

			LOG.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;

	}

	public ResultValoresDatosParametricosEndoso listaValoresDatosParametricos(ParamValoresDatosParametricosEndoso entrada) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("listaValoresDatosParametricos");
		logueo.setParametro(Values.USUARIO, entrada.getUsuario());
		logueo.setParametro(Values.CLAVE, entrada.getClave());
		logueo.setParametro("Numero cotizacion", entrada.getNumCotizacion());
		logueo.setParametro("Codigo dato", entrada.getCodDato());
		logueo.setParametro("Numero poliza", entrada.getNumPoliza());
		logueo.setParametro("Codigo ramo", entrada.getCodRamo());
		logueo.setParametro("Es ultimo endoso", entrada.getEsUltimoEndoso());
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultValoresDatosParametricosEndoso resultado = new ResultValoresDatosParametricosEndoso();
		
		try {
			conn = crearConexion();

			if (entrada.getNumPoliza() != null && entrada.getCodRamo() != null && entrada.getNumCotizacion()==null) {

				StringBuffer sql = new StringBuffer();
				sql.append("SELECT DISTINCT CRPD_DATO valor ");
				sql.append("FROM CRET_PRODUCTOS_DATOS CPD, CART_CERTIFICADOS_ENDOSOS CCE ");
				sql.append("WHERE CRPD_CARP_CD_RAMO = ? "); // RAMO
				sql.append("AND CRPD_CAPO_NU_POLIZA = ? "); // POLIZA
				sql.append("AND CRPD_CRCD_CD_DATO = ? "); // CODDATO
				sql.append("AND CACW_CARP_CD_RAMO = CPD.CRPD_CARP_CD_RAMO ");
				sql.append("AND CCE.CACW_CAPO_NU_POLIZA = CPD.CRPD_CAPO_NU_POLIZA ");
				sql.append("AND CCE.CACW_CACE_NU_CERTIFICADO = CPD.CRPD_CACE_NU_CERTIFICADO ");
				sql.append("AND CCE.CACW_NU_ENDOSO = CPD.CRPD_NU_ENDOSO ");
				sql.append("AND EXISTS (SELECT NULL FROM CRET_COTIZA_DATOS ");
				sql.append("WHERE CRCO_CAPJ_CD_SUCURSAL = CCE.CACW_CASU_CD_SUCURSAL ");
				sql.append("AND CRCO_CAZB_NU_COTIZACION = CCE.CACW_CAZB_NU_COTIZACION ");
				sql.append("AND CRCO_CAZB_NU_CONSECUTIVO = CCE.CACW_CAZB_NU_CONSECUTIVO ");
				sql.append("AND CRCO_CARP_CD_RAMO = CRPD_CARP_CD_RAMO ");
				sql.append("AND CRCO_CAPU_CD_PRODUCTO = CRPD_CAPU_CD_PRODUCTO ");
				sql.append("AND CRCO_CD_BIEN_ASEG = CRPD_CD_BIEN_ASEG ");
				sql.append("AND CRCO_CRTD_CD_DATO = CRPD_CRCD_CD_DATO ");
				sql.append("AND  CRCO_ST_VISIBLE IN ('S','D') )");
				if(entrada.getEsUltimoEndoso()) {
					sql.append("AND CRPD_NU_ENDOSO = (SELECT MAX(CPD1.CRPD_NU_ENDOSO) ");
					sql.append("FROM CRET_PRODUCTOS_DATOS CPD1 ");
					sql.append("WHERE CPD1.CRPD_CARP_CD_RAMO = CPD.CRPD_CARP_CD_RAMO ");
					sql.append("AND CPD1.CRPD_CAPO_NU_POLIZA = CPD.CRPD_CAPO_NU_POLIZA ");
					sql.append("AND CPD1.CRPD_CACE_NU_CERTIFICADO = CPD.CRPD_CACE_NU_CERTIFICADO) ");
					
					sql.append("AND NOT EXISTS (SELECT NULL ");
					sql.append("       FROM CART_CERTIFICADOS ");
					sql.append("       WHERE CACE_CASU_CD_SUCURSAL  = CPD.CRPD_CASU_CD_SUCURSAL  ");
					sql.append("       AND CACE_CARP_CD_RAMO      = CPD.CRPD_CARP_CD_RAMO        ");
	                sql.append("       AND CACE_CAPO_NU_POLIZA    = CPD.CRPD_CAPO_NU_POLIZA      ");
		            sql.append("       AND CACE_NU_CERTIFICADO    = CPD.CRPD_CACE_NU_CERTIFICADO ");
				    sql.append("       AND CACE_ST_CERTIFICADO    = 11) ");
                            
				    sql.append("        AND NOT EXISTS (SELECT NULL ");
				    sql.append("        FROM CART_COTIZA_BANCO ");
				    sql.append("        WHERE CAZB_CARP_CD_RAMO   = CPD.CRPD_CARP_CD_RAMO ");
				    sql.append("        AND CAZB_CAPO_NU_POLIZA = CPD.CRPD_CAPO_NU_POLIZA ");
				    sql.append("        AND CAZB_NU_CONSECUTIVO = CPD.CRPD_CACE_NU_CERTIFICADO ");
				    sql.append("        AND CAZB_IN_EMITIDA     = 'N') ");
					
					
					sql.append("ORDER BY 1 ");
				}
				logueo.setParametro(Values.CONSULTA, sql.toString());
				pst = conn.prepareStatement(sql.toString());
				pst.setInt(1, entrada.getCodRamo());
				pst.setInt(2, entrada.getNumPoliza());
				pst.setInt(3, entrada.getCodDato());
				
				result = pst.executeQuery();

				ArrayList<String> valores = new ArrayList<String>();
				while (result.next()) {
					String valor = new String();
					valor = result.getString("valor");
					valores.add(valor);
				}

				resultado.setValor(valores);

				
			} else if(entrada.getNumCotizacion()!=null) {
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT DISTINCT CRCO_DATO valor ");
				sql.append("FROM CRET_COTIZA_DATOS ");
				sql.append("WHERE CRCO_CAZB_NU_COTIZACION = ? "); // numCotizacion
				sql.append("AND CRCO_CRTD_CD_DATO = ? "); // codDato
				sql.append("ORDER BY 1 ");
				logueo.setParametro(Values.CONSULTA, sql.toString());
				pst = conn.prepareStatement(sql.toString());
				pst.setInt(1, entrada.getNumCotizacion());
				pst.setInt(2, entrada.getCodDato());
				
				result = pst.executeQuery();

				ArrayList<String> valores = new ArrayList<String>();
				while (result.next()) {
					String valor = new String();
					valor = result.getString("valor");
					valores.add(valor);
				}

				resultado.setValor(valores);

			}

			
			LOG.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	// revisado con GCANTO, no tiene impacto para BROKER
	public ResultXmlPL obtenerDatosXDatoParametricoEndoso(ParamDatosXDatosParametricosEndoso param) {
		Connection conn = null;
		CallableStatement cstmt = null;
		ResultXmlPL resultado = new ResultXmlPL();

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerDatosXDatoParametricoEndoso");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Codigo dato", param.getCodDato());
		logueo.setParametro("Codigo valor", param.getCodValor());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Ultimo endoso", param.getUltimoEndoso());

		try {
			conn = crearConexion();

			if (param.getNumPoliza() != null && param.getCodRamo() != null && param.getNumCotizacion() == null) {
				String nombrePL = obtenerValor("proc_obtenerDatosXDatoParametricoEndoso_poliza");
				logueo.setNombrePl(nombrePL);

				cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?)}");

				if (param.getCodRamo() != null) {
					cstmt.setInt(1, param.getCodRamo());
				}

				if (param.getNumPoliza() != null) {
					cstmt.setInt(2, param.getNumPoliza());
				}
				if (param.getCodDato() != null) {
					cstmt.setInt(3, param.getCodDato());
				}

				cstmt.setString(4, param.getCodValor());

				if (param.getUltimoEndoso() != null && param.getUltimoEndoso()) {
					cstmt.setString(5, "S");
				} else {
					cstmt.setString(5, "N");
				}
				cstmt.setString(6, param.getUsuario());

				cstmt.registerOutParameter(7, Types.INTEGER);
				cstmt.registerOutParameter(8, Types.VARCHAR);
				cstmt.registerOutParameter(9, Types.VARCHAR);
				cstmt.registerOutParameter(10, Types.CLOB);

				cstmt.execute();

				int codError = cstmt.getInt(7);
				String descError = cstmt.getString(8);
				String sqlError = cstmt.getString(9);
				Clob clob = cstmt.getClob(10);
				procesarResultados(resultado, logueo, codError, descError, sqlError, clob);

			} else if (param.getNumCotizacion() != null) {
				String nombrePL = obtenerValor("proc_obtenerDatosXDatoParametricoEndoso_cotizacion");
				logueo.setNombrePl(nombrePL);

				cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");

				cstmt.setInt(1, param.getNumCotizacion());

				if (param.getCodDato() != null) {
					cstmt.setInt(2, param.getCodDato());
				}

				cstmt.setString(3, param.getCodValor());

				cstmt.setString(4, param.getUsuario());

				cstmt.registerOutParameter(5, Types.INTEGER);
				cstmt.registerOutParameter(6, Types.VARCHAR);
				cstmt.registerOutParameter(7, Types.VARCHAR);
				cstmt.registerOutParameter(8, Types.CLOB);

				cstmt.execute();

				int codError = cstmt.getInt(5);
				String descError = cstmt.getString(6);
				String sqlError = cstmt.getString(7);
				Clob clob = cstmt.getClob(8);
				procesarResultados(resultado, logueo, codError, descError, sqlError, clob);
			}

		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, cstmt);
		}
		return resultado;
	}

	public ResultAnularCertificadoEndoso anularCertificadoEndoso(ParamAnularCertificadoEndoso param) {
		ResultAnularCertificadoEndoso resultado = new ResultAnularCertificadoEndoso();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("anularCertificadoEndoso");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_anularCertificadoEndoso");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");

			if (param.getNumCotizacion() != null) {
				cstmt.setInt(1, param.getNumCotizacion());
			}

			if (param.getNumCertificado() != null) {
				cstmt.setInt(2, param.getNumCertificado());
			}

			cstmt.setString(3, param.getUsuario());

			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.registerOutParameter(7, Types.CLOB);

			cstmt.execute();

			int codError = cstmt.getInt(4);
			String descError = cstmt.getString(5);
			String sqlError = cstmt.getString(6);
			// Clob clob = cstmt.getClob(7);
			if (codError == 0) {
				ServiciosError error = new ServiciosError();
				error.setDescripcion(descError);
				resultado.setError(error);
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

	//candidato a ser borrado , no se usa en presentacion 
	public ResultDatosParametricoPoliza listaDatosParametricosPoliza(ParamDatosParametricoPoliza entrada) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("listaDatosParametricosPoliza");
		logueo.setParametro(Values.USUARIO, entrada.getUsuario());
		logueo.setParametro(Values.CLAVE, entrada.getClave());
		logueo.setParametro("Numero poliza", entrada.getNumPoliza());
		logueo.setParametro("Codigo ramo", entrada.getCodRamo());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultDatosParametricoPoliza resultado = new ResultDatosParametricoPoliza();
		
		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT DISTINCT CPD.CRPD_CACE_NU_CERTIFICADO codigo, CRCD_DE_DATO descripcion ");
			sql.append("FROM CRET_PRODUCTOS_DATOS CPD, CRET_DATOS, CART_CERTIFICADOS_ENDOSOS CCE ");
			sql.append("WHERE CRPD_CARP_CD_RAMO = ? "); // codRamo
			sql.append("AND CRPD_CAPO_NU_POLIZA = ? "); // numPoliza
			sql.append("AND CPD.CRPD_CRCD_CD_DATO = CRCD_CD_DATO ");
			sql.append("AND CACW_CARP_CD_RAMO = CPD.CRPD_CARP_CD_RAMO ");
			sql.append("AND CCE.CACW_CAPO_NU_POLIZA = CPD.CRPD_CAPO_NU_POLIZA ");
			sql.append("AND CCE.CACW_CACE_NU_CERTIFICADO = CPD.CRPD_CACE_NU_CERTIFICADO ");
			sql.append("AND CCE.CACW_NU_ENDOSO = CPD.CRPD_NU_ENDOSO ");
			sql.append("AND EXISTS (SELECT NULL ");
			sql.append("FROM CRET_COTIZA_DATOS ");
			sql.append("WHERE CRCO_CAPJ_CD_SUCURSAL = CCE.CACW_CASU_CD_SUCURSAL ");
			sql.append("AND CRCO_CAZB_NU_COTIZACION = CCE.CACW_CAZB_NU_COTIZACION ");
			sql.append("AND CRCO_CAZB_NU_CONSECUTIVO = CCE.CACW_CAZB_NU_CONSECUTIVO ");
			sql.append("AND CRCO_CARP_CD_RAMO = CRPD_CARP_CD_RAMO ");
			sql.append("AND CRCO_CAPU_CD_PRODUCTO = CRPD_CAPU_CD_PRODUCTO ");
			sql.append("AND CRCO_CD_BIEN_ASEG = CRPD_CD_BIEN_ASEG ");
			sql.append("AND CRCO_CRTD_CD_DATO = CRPD_CRCD_CD_DATO ");
			sql.append("AND  CRCO_ST_VISIBLE IN ('S','D')) ");
			sql.append("AND CRPD_NU_ENDOSO = (SELECT MAX(CPD1.CRPD_NU_ENDOSO) ");
			sql.append("FROM CRET_PRODUCTOS_DATOS CPD1 ");
			sql.append("WHERE CPD1.CRPD_CARP_CD_RAMO = CPD.CRPD_CARP_CD_RAMO ");
			sql.append("AND CPD1.CRPD_CAPO_NU_POLIZA = CPD.CRPD_CAPO_NU_POLIZA ");
			sql.append("AND CPD1.CRPD_CACE_NU_CERTIFICADO = CPD.CRPD_CACE_NU_CERTIFICADO) ");
			sql.append("ORDER BY 2");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, entrada.getCodRamo());
			pst.setInt(2, entrada.getNumPoliza());

			result = pst.executeQuery();

			ArrayList<DatoParametricoEndoso> datos = new ArrayList<DatoParametricoEndoso>();
			while (result.next()) {
				DatoParametricoEndoso dato = new DatoParametricoEndoso();
				dato.setCodigo(result.getInt("codigo"));
				dato.setDescripcion(result.getString("descripcion"));
				datos.add(dato);
			}

			resultado.setDatosParametricos(datos);

			LOG.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;

	}

	public ResultValoresDatosParametricosPoliza listaValoresDatosParametricosPoliza(ParamValoresDatosParametricosPoliza entrada) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("listaValoresDatosParametricosPoliza");
		logueo.setParametro(Values.USUARIO, entrada.getUsuario());
		logueo.setParametro(Values.CLAVE, entrada.getClave());
		logueo.setParametro("Numero poliza", entrada.getNumPoliza());
		logueo.setParametro("Codigo dato", entrada.getCodDato());
		logueo.setParametro("Codigo ramo", entrada.getCodRamo());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultValoresDatosParametricosPoliza resultado = new ResultValoresDatosParametricosPoliza();
		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT DISTINCT CRPD_DATO valor ");
			sql.append("FROM CRET_PRODUCTOS_DATOS CPD, CART_CERTIFICADOS_ENDOSOS CCE ");
			sql.append("WHERE CRPD_CARP_CD_RAMO = ? "); // codRamo
			sql.append("AND CRPD_CAPO_NU_POLIZA = ? "); // numPoliza
			sql.append("AND CRPD_CRCD_CD_DATO = ? "); // codDato
			sql.append("AND CACW_CARP_CD_RAMO = CPD.CRPD_CARP_CD_RAMO ");
			sql.append("AND CCE.CACW_CAPO_NU_POLIZA = CPD.CRPD_CAPO_NU_POLIZA ");
			sql.append("AND CCE.CACW_CACE_NU_CERTIFICADO = CPD.CRPD_CACE_NU_CERTIFICADO ");
			sql.append("AND CCE.CACW_NU_ENDOSO = CPD.CRPD_NU_ENDOSO ");
			sql.append("AND EXISTS (SELECT NULL FROM CRET_COTIZA_DATOS ");
			sql.append("WHERE CRCO_CAPJ_CD_SUCURSAL = CCE.CACW_CASU_CD_SUCURSAL ");
			sql.append("AND CRCO_CAZB_NU_COTIZACION = CCE.CACW_CAZB_NU_COTIZACION ");
			sql.append("AND CRCO_CAZB_NU_CONSECUTIVO = CCE.CACW_CAZB_NU_CONSECUTIVO ");
			sql.append("AND CRCO_CARP_CD_RAMO = CRPD_CARP_CD_RAMO ");
			sql.append("AND CRCO_CAPU_CD_PRODUCTO = CRPD_CAPU_CD_PRODUCTO ");
			sql.append("AND CRCO_CD_BIEN_ASEG = CRPD_CD_BIEN_ASEG ");
			sql.append("AND CRCO_CRTD_CD_DATO = CRPD_CRCD_CD_DATO ");
			sql.append("AND  CRCO_ST_VISIBLE IN ('S','D')) ");
			sql.append("AND CRPD_NU_ENDOSO = (SELECT MAX(CPD1.CRPD_NU_ENDOSO) ");
			sql.append("FROM CRET_PRODUCTOS_DATOS CPD1 ");
			sql.append("WHERE CPD1.CRPD_CARP_CD_RAMO = CPD.CRPD_CARP_CD_RAMO ");
			sql.append("AND CPD1.CRPD_CAPO_NU_POLIZA = CPD.CRPD_CAPO_NU_POLIZA ");
			sql.append("AND CPD1.CRPD_CACE_NU_CERTIFICADO = CPD.CRPD_CACE_NU_CERTIFICADO) ");
			sql.append("ORDER BY 1 ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, entrada.getCodRamo());
			pst.setInt(2, entrada.getNumPoliza());
			pst.setInt(3, entrada.getCodDato());

			result = pst.executeQuery();

			ArrayList<String> valores = new ArrayList<String>();
			while (result.next()) {
				String valor = new String();
				valor = result.getString("valor");
				valores.add(valor);
			}

			resultado.setValor(valores);

			LOG.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	public ResultDatosXDatosParametricosPoliza obtenerDatosXDatoParametricoPoliza(ParamDatosXDatosParametricosPoliza entrada) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerDatosXDatoParametricoPoliza");
		logueo.setParametro(Values.USUARIO, entrada.getUsuario());
		logueo.setParametro(Values.CLAVE, entrada.getClave());
		logueo.setParametro("Numero poliza", entrada.getNumPoliza());
		logueo.setParametro("Codigo dato", entrada.getCodDato());
		logueo.setParametro("Codigo ramo", entrada.getCodRamo());
		logueo.setParametro("Valor dato", entrada.getValorDato());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultDatosXDatosParametricosPoliza resultado = new ResultDatosXDatosParametricosPoliza();
		// FIXME OIGRES FALTA BROK EN COD SQL
		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT DISTINCT CPD.CRPD_CACE_NU_CERTIFICADO, (SELECT CACN_NM_APELLIDO_RAZON||', '||CACN_NM_PERSONA_NATURAL ");
			sql.append("FROM CART_CLIENTES, CART_COTIZA_BANCO ");
			sql.append("WHERE CACN_CD_NACIONALIDAD = CAZB_CACN_CD_NACIONALIDAD ");
			sql.append("AND CACN_NU_CEDULA_RIF = CAZB_CACN_CD_CLIENTE ");
			sql.append("AND CAZB_NU_COTIZACION = CCE.CACW_CAZB_NU_COTIZACION ");
			sql.append("AND CAZB_NU_CONSECUTIVO = CCE.CACW_CAZB_NU_CONSECUTIVO) TOMADOR, ");
			sql.append("CPD.CRPD_CD_BIEN_ASEG, CRBA_DE_BIEN_ASEG  ");
			sql.append("FROM CRET_PRODUCTOS_DATOS CPD, CART_CERTIFICADOS_ENDOSOS CCE, CRET_BIENES_ASEGURADOS ");
			sql.append("WHERE CRPD_CARP_CD_RAMO = ? ");
			sql.append("AND CRPD_CAPO_NU_POLIZA = ? ");
			sql.append("AND CRPD_CRCD_CD_DATO = ? ");
			sql.append("AND CRPD_DATO = ? ");
			sql.append("AND CACW_CARP_CD_RAMO = CPD.CRPD_CARP_CD_RAMO ");
			sql.append("AND CCE.CACW_CAPO_NU_POLIZA = CPD.CRPD_CAPO_NU_POLIZA ");
			sql.append("AND CCE.CACW_CACE_NU_CERTIFICADO = CPD.CRPD_CACE_NU_CERTIFICADO ");
			sql.append("AND CCE.CACW_NU_ENDOSO = CPD.CRPD_NU_ENDOSO ");
			sql.append("AND CRBA_CARP_CD_RAMO = CPD.CRPD_CARP_CD_RAMO ");
			sql.append("AND CRBA_CD_BIEN_ASEG = CPD.CRPD_CD_BIEN_ASEG ");
			sql.append("AND EXISTS (SELECT NULL ");
			sql.append("            FROM CRET_COTIZA_DATOS ");
			sql.append("WHERE CRCO_CAPJ_CD_SUCURSAL = CCE.CACW_CASU_CD_SUCURSAL ");
			sql.append("AND CRCO_CAZB_NU_COTIZACION  = CCE.CACW_CAZB_NU_COTIZACION ");
			sql.append("AND CRCO_CAZB_NU_CONSECUTIVO = CCE.CACW_CAZB_NU_CONSECUTIVO ");
			sql.append("AND CRCO_CARP_CD_RAMO = CPD.CRPD_CARP_CD_RAMO ");
			sql.append("AND CRCO_CAPU_CD_PRODUCTO  = CPD.CRPD_CAPU_CD_PRODUCTO ");
			sql.append("AND CRCO_CD_BIEN_ASEG = CPD.CRPD_CD_BIEN_ASEG ");
			sql.append("AND CRCO_CRTD_CD_DATO = CPD.CRPD_CRCD_CD_DATO ");
			sql.append("AND  CRCO_ST_VISIBLE IN ('S','D')) ");
			sql.append("AND CRPD_NU_ENDOSO = (SELECT MAX(CPD1.CRPD_NU_ENDOSO) ");
			sql.append("FROM CRET_PRODUCTOS_DATOS CPD1 ");
			sql.append("WHERE CPD1.CRPD_CARP_CD_RAMO = CPD.CRPD_CARP_CD_RAMO ");
			sql.append("AND CPD1.CRPD_CAPO_NU_POLIZA = CPD.CRPD_CAPO_NU_POLIZA ");
			sql.append("AND CPD1.CRPD_CACE_NU_CERTIFICADO = CPD.CRPD_CACE_NU_CERTIFICADO) ");
			sql.append("ORDER BY 1,3 ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, entrada.getCodRamo());
			pst.setInt(2, entrada.getNumPoliza());
			pst.setInt(3, entrada.getCodDato());
			pst.setString(4, entrada.getValorDato());

			result = pst.executeQuery();

			ArrayList<DatosXDatoParametrico> valores = new ArrayList<DatosXDatoParametrico>();
			while (result.next()) {
				DatosXDatoParametrico dato = new DatosXDatoParametrico();
				dato.setNumCertificado(result.getInt("CRPD_CACE_NU_CERTIFICADO"));
				dato.setCliente(result.getString("TOMADOR"));
				dato.setCodBien(result.getInt("CRPD_CD_BIEN_ASEG"));
				dato.setDescBien(result.getString("CRBA_DE_BIEN_ASEG"));
				valores.add(dato);
			}

			resultado.setDatos(valores);

			LOG.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	public ResultXmlPL generarMarcaNoRenovar(ParamGenerarMarcaNoRenovar param) {
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("generarMarcaNoRenovar");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Fecha desde", param.getFechaDesde());
		logueo.setParametro("Codigo Origen", param.getCodOrigen());
		logueo.setParametro("Codigo Motivo Abandono", param.getCodMotAbandono());
		logueo.setParametro("Observacion Abandono", param.getObsAbandono());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_generaMarcaNoRenovar");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?)}");

			if (param.getCodRamo() != null) {
				cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			}

			if (param.getNumPoliza() != null) {
				cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			}

			if (param.getFechaDesde() == null) {
				cstmt.setNull(3, java.sql.Types.DATE);
			} else {
				cstmt.setDate(3, toSqlDate(param.getFechaDesde()));
			}

			if (param.getCodOrigen() != null) {
				cstmt.setInt(4, Integer.valueOf(param.getCodOrigen()));
			}

			if (param.getCodMotAbandono() != null) {
				cstmt.setInt(5, Integer.valueOf(param.getCodMotAbandono()));
			}

			cstmt.setString(6, param.getObsAbandono());

			cstmt.setString(7, param.getUsuario());

			cstmt.registerOutParameter(8, Types.INTEGER);
			cstmt.registerOutParameter(9, Types.VARCHAR);
			cstmt.registerOutParameter(10, Types.VARCHAR);
			cstmt.registerOutParameter(11, Types.CLOB);

			cstmt.execute();

			int codError = cstmt.getInt(8);
			String descError = cstmt.getString(9);
			String sqlError = cstmt.getString(10);
			Clob clob = cstmt.getClob(11);
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

	public ResultXmlPL obtenerDatosEndosoSinPremio(ParamObtenerDatosEndosoSinPremio param) {
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerDatosEndosoSinPremio");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_datos_endoso_sin_premio");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");

			if (param.getCodRamo() != null) {
				cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			}

			if (param.getNumPoliza() != null) {
				cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			}

			cstmt.setString(3, param.getUsuario());

			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);
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

	public ResultActualizarDatosParticulares actualizarDatosParticulares(ParamActualizarDatosParticulares param) {

		ResultActualizarDatosParticulares resultado = new ResultActualizarDatosParticulares();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("actualizarDatosParticulares");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero ramo", param.getNumRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero Certificado", param.getNumCertificado());
		logueo.setParametro("Numero de Endoso", param.getNumEndoso());
		logueo.setParametro("Codigo de Bien", param.getCodBien());
		logueo.setParametro("Codigo del dato", param.getCodDato());
		logueo.setParametro("Valor del dato", param.getCodValor());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("fun_actualizarDatosPart");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{? = call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?)}");

			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);

			if (param.getNumRamo() != null) {
				cstmt.setInt(2, Integer.valueOf(param.getNumRamo()));
			}

			if (param.getNumPoliza() != null) {
				cstmt.setInt(3, Integer.valueOf(param.getNumPoliza()));
			}

			cstmt.setInt(4, Integer.valueOf(param.getNumCertificado()));
			cstmt.setInt(5, Integer.valueOf(param.getNumEndoso()));
			cstmt.setInt(6, Integer.valueOf(param.getCodBien()));
			cstmt.setInt(7, Integer.valueOf(param.getCodDato()));
			cstmt.setString(8, param.getCodValor());
			cstmt.setString(9, param.getUsuario());

			cstmt.registerOutParameter(10, Types.INTEGER);
			cstmt.registerOutParameter(11, Types.VARCHAR);
			cstmt.registerOutParameter(12, Types.VARCHAR);

			cstmt.execute();

			int codError = cstmt.getInt(10);
			String descError = cstmt.getString(11);
			String sqlError = cstmt.getString(12);

			if (codError == 0) {
				String res = cstmt.getString(1);
				resultado.setMsgAviso(res);
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

	public ResultGenerarEndoso generarEndoso(ParamGenerarEndoso param) {
		ResultGenerarEndoso resultado = new ResultGenerarEndoso();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("generarEndoso");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero ramo", param.getNumRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Numero de certificado", param.getNumCertificado());
		logueo.setParametro("Numero de endoso", param.getNumEndoso());
		logueo.setParametro("Fecha desde", param.getFechaDesde());
		logueo.setParametro("Codigo origen", param.getCodOrigen());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("fun_generarEndoso");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{? = call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?)}");

			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);

			if (param.getNumRamo() != null) {
				cstmt.setInt(2, Integer.valueOf(param.getNumRamo()));
			}

			if (param.getNumPoliza() != null) {
				cstmt.setInt(3, Integer.valueOf(param.getNumPoliza()));
			}
			cstmt.setInt(4, Integer.valueOf(param.getNumCertificado()));
			cstmt.setInt(5, Integer.valueOf(param.getNumEndoso()));
			if (param.getFechaDesde() == null) {
				cstmt.setNull(6, java.sql.Types.DATE);
			} else {
				cstmt.setDate(6, toSqlDate(param.getFechaDesde()));
			}
			cstmt.setInt(7, Integer.valueOf(param.getCodOrigen()));
			cstmt.setString(8, param.getUsuario());

			cstmt.registerOutParameter(9, Types.INTEGER);
			cstmt.registerOutParameter(10, Types.VARCHAR);
			cstmt.registerOutParameter(11, Types.VARCHAR);
			cstmt.registerOutParameter(12, Types.CLOB);

			cstmt.execute();

			int codError = cstmt.getInt(9);
			String descError = cstmt.getString(10);
			String sqlError = cstmt.getString(11);

			if (codError == 0) {

				String res = cstmt.getString(1);
				resultado.setMsgAviso(res);
				ServiciosError error = new ServiciosError();
				error.setDescripcion(descError);
				resultado.setError(error);
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

	public ResultXmlPL nuevaFacturacionInteractiva(ParamNuevaFacturacionInteractiva param) {
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("nuevaFacturacionInteractiva");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_facturar_poliza");
			logueo.setNombrePl(nombrePL);
			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");

			if (param.getCodRamo() != null) {
				cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			} else {
				cstmt.setNull(1, Types.INTEGER);
			}
			if (param.getNumPoliza() != null) {
				cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			} else {
				cstmt.setNull(2, Types.INTEGER);
			}

			cstmt.setString(3, param.getUsuario());

			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);
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

	public ResultFacturacionElectronica facturacionElectronica(ParamFacturacionElectronica param) {
		Logueo logueo = new Logueo();
		ResultFacturacionElectronica resultado = new ResultFacturacionElectronica();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("facturacionElectronica");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero factura rector", param.getNumeroFactura());
		CallableStatement cstmt = null;
		Connection conn = null;
		// AGM TODO CfeFactura facturacion=null;
		try {
			String ambiente = manager.obtenerValor("serviciosRector.ambienteEFact");
			conn = this.crearConexion();
			String PL = obtenerValor("fun_existeUsuarioRector");
			cstmt = conn.prepareCall("{? = call " + PL + "(?)}");
			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setString(2, param.getUsuario());
			cstmt.execute();
			String usuario = cstmt.getString(1);
			logueo.setParametro("Ambiente facturacion", ambiente);
			logueo.setParametro("Usuario rector", usuario);

			log.info(logueo.getSoloParametros());
			log.info("inicio facturacion proxyAsFws");
			log.info("Invocando new CfeFactura");
			// AGM TODO facturacion = new CfeFactura(Long.valueOf(param.getNumeroFactura()), ambiente, usuario);
			// AGM TODO log.info(" invocado generar()" + facturacion.generar());
			// AGM TODO log.info(" invocado enviar(true)" + facturacion.enviar(true));
			// AGM TODO log.info(" invocado generado()" + facturacion.generado());
			log.info("fin facturacion proxyAsFws");
			/* AGM TODO
			if (facturacion.getRespuesta() != null && (facturacion.getRespuesta().getDocumentId() != null && (facturacion.getRespuesta().getErrorMsg() == null || "".equals(facturacion.getRespuesta().getErrorMsg())))) {
				resultado.setHayError(Boolean.FALSE);
				log.info("Facturacion correcta para factura " + param.getNumeroFactura());
				resultado.setDocumentId(facturacion.getRespuesta().getDocumentId());
			} else {
				log.error("Hubo error durante el proceso de facturacion de la factura " + param.getNumeroFactura());
				resultado.setHayError(Boolean.TRUE);
				resultado.setError(new ServiciosError(30, "Hubo error en el proceso de facturaci\u00f3n."));
			}
			*/
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			//FIXME OIGRES AGREGADO CERRAR CONEXION DE FACTURACION
			/* AGM TODO
			if (facturacion!=null) {
				try {
					log.info("Invocando cerrar conexion a base de facturacion");
					facturacion.getCnx().closeConnection();
				} catch (Exception e) {
					log.error("Cerrar conexion", e);
				}
			}
			*/
			liberarRecursos(conn, cstmt);
		}
		return resultado;

	}

	public ResultXmlPL validarDetectarEndoso(ParamValidarDetectarEndoso param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(getClass());
		logueo.setMetodo("validarDetectarEndoso");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero ramo", param.getNumRamo());
		logueo.setParametro("Numero de poliza", param.getNumPoliza());
		logueo.setParametro("Numero de certificado ", param.getNumCertificado());
		logueo.setParametro("Fecha desde ", param.getFechaDesde());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_validarDetectarEndoso");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getNumRamo());
			cstmt.setInt(2, param.getNumPoliza());
			cstmt.setInt(3, param.getNumCertificado());

			if (param.getFechaDesde() == null) {
				cstmt.setNull(4, java.sql.Types.DATE);
			} else {
				cstmt.setDate(4, toSqlDate(param.getFechaDesde()));
			}
			cstmt.setString(5, param.getUsuario());

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

	//revisado con GCANTO , no tiene impacto para BROKER
	public ResultAcreedorObjetos listaAcreedorObjetos(ParamObjetosAcreedor param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("listaAcreedorObjetos");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero de poliza", param.getNumPoliza());
		logueo.setParametro("Cod de ramo", param.getCodRamo());
		logueo.setParametro("Numero de sucursal", param.getSucursal());
		logueo.setParametro("Numero de certificado", param.getNumCertificado());
		logueo.setParametro("Codigo del bien del asegurado", param.getCodBienAsegurado());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultAcreedorObjetos resultado = new ResultAcreedorObjetos();
		
		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT 0 CODIGO, 'BIEN ASEGURADO' DESCRICPION FROM DUAL ");
			sql.append("UNION  ");
			sql.append("SELECT CRLB_CROB_CD_OBJETO CODIGO, CRLB_DESCRIPCION DESCRICPION ");
			sql.append("FROM CRET_LISTAS_BIENES ");
			sql.append("WHERE CRLB_CASU_CD_SUCURSAL = ? ");
			sql.append("AND CRLB_CARP_CD_RAMO = ? ");
			sql.append("AND CRLB_CAPO_NU_POLIZA  = ? ");
			sql.append("AND CRLB_CACE_NU_CERTIFICADO = ? ");
			sql.append("AND CRLB_CRBA_CD_BIEN_ASEG  = ? ");
			sql.append("AND CRLB_NU_ENDOSO = GEN_WEB_EMISION.FUN_MAX_ENDOSO_CERTIFICADO(CRLB_CASU_CD_SUCURSAL, ");
			sql.append("CRLB_CARP_CD_RAMO, ");
			sql.append("CRLB_CAPO_NU_POLIZA, ");
			sql.append("CRLB_CACE_NU_CERTIFICADO) ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());

			pst.setInt(1, Integer.valueOf(param.getSucursal()));
			pst.setInt(2, Integer.valueOf(param.getCodRamo()));
			pst.setInt(3, Integer.valueOf(param.getNumPoliza()));
			pst.setInt(4, Integer.valueOf(param.getNumCertificado()));
			pst.setInt(5, Integer.valueOf(param.getCodBienAsegurado()));

			result = pst.executeQuery();

			while (result.next()) {
				AcreedorObjetos item = new AcreedorObjetos();
				item.setCodigo(result.getInt("CODIGO"));
				item.setDescripcion(result.getString("DESCRICPION"));

				resultado.setUnObjeto(item);
			}

			LOG.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	public ResultGenerico actualizarAcreedor(ParamAcreedorEndoso param) {
		ResultGenerico resultado = new ResultGenerico();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(getClass());
		logueo.setMetodo("actualizarAcreedor");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo bien", param.getConsecutivoBien());
		logueo.setParametro("Codigo de acreedor", param.getCodAcreedor());
		logueo.setParametro("Codigo de objeto", param.getCodObjeto());
		logueo.setParametro("Tipo acreedor", param.getCodTipoAcreedor());
		logueo.setParametro("Porcentaje de participacion", param.getPorcentajeParticipacion());
		logueo.setParametro("Numero de poliza", param.getNumPoliza());
		logueo.setParametro("Numero de endoso", param.getNumEndoso());
		logueo.setParametro("Numero de ramo", param.getCodRamo());
		logueo.setParametro("Fecha de exclusion", param.getFechaExclusion());
		logueo.setParametro("Identificador", param.getIdentificador());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_mantAcreedores");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getCodRamo());
			cstmt.setInt(2, param.getNumPoliza());
			cstmt.setInt(3, param.getNumCertificado());
			cstmt.setInt(4, param.getConsecutivoBien());
			cstmt.setInt(5, param.getCodAcreedor());
			cstmt.setInt(6, param.getCodObjeto());
			cstmt.setString(7, param.getCodTipoAcreedor());

			cstmt.setDouble(8, param.getPorcentajeParticipacion());

			if (param.getFechaExclusion() == null) {
				cstmt.setNull(9, java.sql.Types.DATE);
			} else {
				cstmt.setDate(9, toSqlDate(param.getFechaExclusion()));
			}

			cstmt.setInt(10, Integer.valueOf(param.getNumEndoso()));
			cstmt.setString(11, param.getIdentificador());
			cstmt.setString(12, param.getUsuario());
			cstmt.registerOutParameter(13, Types.INTEGER);
			cstmt.registerOutParameter(14, Types.VARCHAR);
			cstmt.registerOutParameter(15, Types.VARCHAR);

			cstmt.execute();

			int codError = cstmt.getInt(13);
			String descError = cstmt.getString(14);
			String sqlError = cstmt.getString(15);

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

	public ResultGenerico actualizarDatosBancariosEndoso(ParamActualizarDatosBancariosEndoso param) {
		ResultGenerico resultado = new ResultGenerico();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setClase(getClass());
		logueo.setMetodo("actualizarDatosBancariosEndoso");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero de poliza", param.getNumPoliza());
		logueo.setParametro("Numero de endoso", param.getNumEndoso());
		logueo.setParametro("Numero de ramo", param.getNumRamo());
		logueo.setParametro("Numero domicilio banco", param.getNumDomicilioBanco());
		logueo.setParametro("Codigo de medio pago", param.getCodMedioPago());
		logueo.setParametro("Codigo de origen de pago", param.getCodOrigenPago());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_actualizarDatosBancariosEndoso");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getNumRamo());
			cstmt.setInt(2, param.getNumPoliza());
			cstmt.setInt(3, param.getNumEndoso());
			cstmt.setInt(4, param.getCodMedioPago());
			cstmt.setString(5, param.getCodOrigenPago());
			if (param.getNumDomicilioBanco() != null) {
				cstmt.setInt(6, param.getNumDomicilioBanco());
			} else {
				cstmt.setNull(6, Types.INTEGER);
			}
			cstmt.setString(7, param.getUsuario());

			cstmt.registerOutParameter(8, Types.INTEGER);
			cstmt.registerOutParameter(9, Types.VARCHAR);
			cstmt.registerOutParameter(10, Types.VARCHAR);

			cstmt.execute();

			int codError = cstmt.getInt(8);
			String descError = cstmt.getString(9);
			String sqlError = cstmt.getString(10);

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

	public ResultXmlPL cotizacionPendientePoliza(ParamCotizacionPendiente param) {
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("cotizacionPendientePoliza");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo ramo", param.getCodRamo());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_cotizacionPendientePoliza");
			logueo.setNombrePl(nombrePL);
			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");

			if (param.getCodRamo() != null) {
				cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			} else {
				cstmt.setNull(1, Types.INTEGER);
			}

			if (param.getNumPoliza() != null) {
				cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			} else {
				cstmt.setNull(2, Types.INTEGER);
			}

			cstmt.setString(3, param.getUsuario());

			cstmt.setString(4, param.getTipoTransaccion());

			cstmt.registerOutParameter(5, Types.INTEGER);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.registerOutParameter(8, Types.CLOB);
			cstmt.execute();
			int codError = cstmt.getInt(5);
			String descError = cstmt.getString(6);
			String sqlError = cstmt.getString(7);
			Clob clob = cstmt.getClob(8);
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
	
	public ResultXmlPL cotizacionPendientePolizaSinPremio(ParamCotizacionPendienteSinPremio param) {
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("cotizacionPendientePolizaSinPremio");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo ramo", param.getCodRamo());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_cotizacionPendientePoliza");
			logueo.setNombrePl(nombrePL);
			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?)}");

			if (param.getCodRamo() != null) {
				cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			} else {
				cstmt.setNull(1, Types.INTEGER);
			}

			if (param.getNumPoliza() != null) {
				cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			} else {
				cstmt.setNull(2, Types.INTEGER);
			}

			cstmt.setString(3, param.getUsuario());

			cstmt.setString(4,param.getTipoTransaccion());

			cstmt.registerOutParameter(5, Types.INTEGER);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.registerOutParameter(8, Types.CLOB);
			cstmt.setString(9, "N");
			cstmt.execute();
			int codError = cstmt.getInt(5);
			String descError = cstmt.getString(6);
			String sqlError = cstmt.getString(7);
			Clob clob = cstmt.getClob(8);
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


	public ResultCondicion tienePermisoProducto(ParamTienePermisoProducto param) {
		ResultCondicion resultado = new ResultCondicion();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("tienePermisoProducto");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Codigo producto", param.getCodProducto());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("fun_tiene_permiso_producto");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{? = call " + nombrePL + "(?,?,?)}");

			cstmt.registerOutParameter(1, java.sql.Types.VARCHAR);

			if (param.getCodRamo() != null) {
				cstmt.setInt(2, Integer.valueOf(param.getCodRamo()));
			} else {
				cstmt.setNull(2, Types.INTEGER);
			}

			cstmt.setString(3, param.getCodProducto());

			cstmt.setString(4, param.getUsuario());

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

	public ResultXmlPL listaCertificadosParaEndosar(ParamListaCertificadosEndosar param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(getClass());
		logueo.setMetodo("listaCertificadosParaEndosar");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_lista_cert_para_endosar");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getNumCotizacion());
			cstmt.setString(2, param.getUsuario());

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

	public ResultXmlPL obtenerDatosFacturaDigital(ParamObtenerDatosFacturaDigital param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerDatosFacturaDigitial");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerDatosFacturaDigital");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getCodRamo());
			cstmt.setInt(2, param.getNumPoliza());
			cstmt.setString(3, param.getUsuario());

			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);
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

	public ResultEnviarFacturaDigitalEMail enviarFacturaDigitalEMail(ParamEnviarFacturaDigitalEMail param) {
		ResultEnviarFacturaDigitalEMail resultado = new ResultEnviarFacturaDigitalEMail();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("enviarFacturaDigitalEMail");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Enviar factura digital por email", param.getEnviarFacturaDigitalEMail());
		logueo.setParametro("Consecutivo", param.getConsecutivo());
		logueo.setParametro("Comunicacion", param.getComunicacion());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_enviarFacturaDigitalEMail");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getCodRamo());
			cstmt.setInt(2, param.getNumPoliza());
			cstmt.setString(3, param.getUsuario());
			cstmt.setString(4, (param.getEnviarFacturaDigitalEMail()));
			if (param.getConsecutivo() != null) {
				cstmt.setInt(5, param.getConsecutivo());
			} else {
				cstmt.setNull(5, Types.INTEGER);
			}
			if (param.getComunicacion() != null) {
				cstmt.setString(6, (param.getComunicacion()));
			} else {
				cstmt.setNull(6, Types.VARCHAR);
			}
			cstmt.registerOutParameter(7, Types.INTEGER);
			cstmt.registerOutParameter(8, Types.VARCHAR);
			cstmt.registerOutParameter(9, Types.VARCHAR);

			cstmt.execute();

			int codError = cstmt.getInt(7);
			String descError = cstmt.getString(8);
			String sqlError = cstmt.getString(9);

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

	public ResultObtenerDatosParametricos obtenerDatosParametricos(ParamObtenerDatosParametricos param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerDatosParametricos");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("filtros", param.filtrosToString());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultObtenerDatosParametricos resultado = new ResultObtenerDatosParametricos();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
            //FIXME OIGRES MEJORA --FILTRAR POR RAMO Y PRODUCTO A FUTURO
			sql.append("SELECT DISTINCT CRDP_CARP_CD_RAMO, CRCD_CD_DATO Codigo, CRCD_DE_DATO Descripcion, CRCD_CD_TABLA_VALID cd_tabla, ( SELECT count(1)  ");
			sql.append("FROM cret_tablas  ");
			sql.append("WHERE CRTB_CD_TABLA = CRCD_CD_TABLA_VALID ) CANTIDAD ");
			sql.append("FROM CRET_DATOS, ");
			sql.append("CRET_DEFINICION_PRODUCTOS ");
			sql.append("WHERE CRCD_CD_DATO IN (SELECT SUBSTR(GEPA_NM_VALOR_CAMPO,1, INSTR(GEPA_NM_VALOR_CAMPO,'$')-1) ");
			sql.append("FROM GENT_PARAMETROS ");
			sql.append("WHERE GEPA_NM_CAMPO IN ( ");

			sql.append(param.filtrosToString());
			sql.append("  ) ) ");
			sql.append("AND CRCD_CD_DATO = CRDP_CRTD_CD_DATO (+) ");
			sql.append("ORDER BY 1, 3 ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());

			result = pst.executeQuery();

			while (result.next()) {
				DatoParametricoPoliza item = new DatoParametricoPoliza();
				item.setCodDato(result.getString("Codigo"));
				item.setDescripDato(result.getString("Descripcion"));
				item.setCodTabla(result.getInt("cd_tabla"));
				item.setCantidad(result.getInt("CANTIDAD"));

				resultado.setUno(item);
			}

			LOG.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}

	private int obtenerFiltros(ParamObtenerValoresXDatoParametrico param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerFiltros");
		logueo.setParametro("Codigo tabla", param.getCodTabla());
		int resultado = -1;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();

			sql.append("SELECT NVL(DECODE(CRTB_DATO3, NULL, DECODE(CRTB_DATO2, NULL, DECODE(CRTB_DATO1, NULL, 0), 1), 2), 0) CANTIDAD ");
			sql.append("FROM cret_tablas ");
			sql.append("WHERE CRTB_CD_TABLA = ? ");
			sql.append("AND ROWNUM = 1 ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, Integer.valueOf(param.getCodTabla()));

			result = pst.executeQuery();

			while (result.next()) {
				resultado = result.getInt("CANTIDAD");
			}

			LOG.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			logueo.setException("SQLException");
			logueo.setError(e.getMessage());
			log.error(logueo.getMensaje());
		} catch (Exception ex) {
			logueo.setException("Exception");
			logueo.setError(ex.getMessage());
			log.error(logueo.getMensaje());
		} finally {
			liberarRecursos(conn, pst, result);
		}
		return resultado;
	}

	public Boolean validarRequiereDomicilioBancario(Integer codMedioPago) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("validarRequiereDomicilioBancadio");
		logueo.setParametro("Codigo medio de pago", codMedioPago);
		Boolean resultado = Boolean.FALSE;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();

			sql.append("SELECT 1  FROM COBT_TIPOS_COBRANZAS ");
			sql.append("WHERE COTC_CAMD_CD_MEDIO_PAGO = ? ");
			sql.append("AND COTC_TP_CUENTA IS NOT NULL ");
			sql.append("AND ROWNUM = 1");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, codMedioPago);

			result = pst.executeQuery();

			while (result.next()) {
				resultado = Boolean.TRUE;
			}

			LOG.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			logueo.setException("SQLException");
			logueo.setError(e.getMessage());
			log.error(logueo.getMensaje());
		} catch (Exception ex) {
			logueo.setException("Exception");
			logueo.setError(ex.getMessage());
			log.error(logueo.getMensaje());
		} finally {
			liberarRecursos(conn, pst, result);
		}
		return resultado;
	}

	public ResultCodiguera obtenerValoresXDatoParametrico(ParamObtenerValoresXDatoParametrico param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("obtenerValoresXDatoParametrico");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("codTabla", param.getCodTabla());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultCodiguera resultado = new ResultCodiguera();
		int filtros = obtenerFiltros(param);
		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			switch (filtros) {
			case 0:
				sql.append(" SELECT CRTB_DATO1 Codigo, CRTB_DE_DATO Valor ");
				break;
			case 1:
				sql.append(" SELECT CRTB_DATO2 Codigo, CRTB_DE_DATO Valor ");
				break;
			case 2:
				sql.append(" SELECT CRTB_DATO3 Codigo, CRTB_DE_DATO Valor ");
				break;

			default:
				sql.append(" SELECT CRTB_DATO1 Codigo, CRTB_DE_DATO Valor ");
				break;
			}

			sql.append(" FROM CRET_TABLAS ");
			sql.append(" WHERE CRTB_CD_TABLA = ? ");
			if (param.getFiltro() != null && !param.getFiltro().isEmpty()) {
				sql.append(" AND UPPER(CRTB_DE_DATO) like  ? ");
			}
			sql.append(" ORDER BY 1, 2 ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, param.getCodTabla());
			if (param.getFiltro() != null && !param.getFiltro().isEmpty()) {
				pst.setString(2, "%" + param.getFiltro().toUpperCase() + "%");
			}
			result = pst.executeQuery();

			while (result.next()) {
				Codiguera item = new Codiguera();
				item.setCodigo(result.getString("Codigo"));
				item.setDescripcion(result.getString("Valor"));

				resultado.setUno(item);
			}

			LOG.info(logueo.getSoloParametros());
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, pst, result);
		}

		return resultado;
	}
	
	
	public ResultXmlPL validarRefacturacion (ParamValidarRefacturacion param){
		
		
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("validarRefacturacion");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_validarRefacturacion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));

			cstmt.setString(3, param.getUsuario());

			cstmt.registerOutParameter(4, Types.INTEGER);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.VARCHAR);
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
	
	public ResultXmlPL refacturarPoliza (ParamRefacturarPoliza param){
		
		
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("refacturarPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("solo cotiza", param.getSoloCotiza());
		logueo.setParametro("Datos parametricos", param.getListaDatosParametricos());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_refacturarPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setInt(2, Integer.valueOf(param.getNumPoliza()));
			cstmt.setString(3, param.getUsuario());
			cstmt.setString(4, param.getSoloCotiza());
			cstmt.setString(5, param.getListaDatosParametricos());
			
			
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
	
	
}