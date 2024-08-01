package uy.com.bse.cotizaciones.persistencia;

import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.cotizaciones.consultas.Cliente;
import uy.com.bse.cotizaciones.consultas.FueraPauta;
import uy.com.bse.cotizaciones.consultas.ParamBonificacionNS;
import uy.com.bse.cotizaciones.consultas.ParamCertificados;
import uy.com.bse.cotizaciones.consultas.ParamClientes;
import uy.com.bse.cotizaciones.consultas.ParamCotizaciones;
import uy.com.bse.cotizaciones.consultas.ParamDatoCotizacion;
import uy.com.bse.cotizaciones.consultas.ParamDatoParametrico;
import uy.com.bse.cotizaciones.consultas.ParamDatosCertificados;
import uy.com.bse.cotizaciones.consultas.ParamDatosModificacion;
import uy.com.bse.cotizaciones.consultas.ParamDetalleBusquedaCotizacion;
import uy.com.bse.cotizaciones.consultas.ParamDetalleTextoCotizacion;
import uy.com.bse.cotizaciones.consultas.ParamEncabezadoCotizacion;
import uy.com.bse.cotizaciones.consultas.ParamFueraPautas;
import uy.com.bse.cotizaciones.consultas.ParamListaPlanesPagoRen;
import uy.com.bse.cotizaciones.consultas.ParamObtenerAcreedoresXBien;
import uy.com.bse.cotizaciones.consultas.ParamObtenerAnexosCotizacion;
import uy.com.bse.cotizaciones.consultas.ParamObtenerBeneficiarios;
import uy.com.bse.cotizaciones.consultas.ParamObtenerBienes;
import uy.com.bse.cotizaciones.consultas.ParamObtenerCabezalCotizaciones;
import uy.com.bse.cotizaciones.consultas.ParamObtenerClientesSolicitante;
import uy.com.bse.cotizaciones.consultas.ParamObtenerCoberturasXBien;
import uy.com.bse.cotizaciones.consultas.ParamObtenerCoberturasXCertificado;
import uy.com.bse.cotizaciones.consultas.ParamObtenerComponentes;
import uy.com.bse.cotizaciones.consultas.ParamObtenerContratanteAsegurado;
import uy.com.bse.cotizaciones.consultas.ParamObtenerDatosBancarios;
import uy.com.bse.cotizaciones.consultas.ParamObtenerDatosBasicosCotiza;
import uy.com.bse.cotizaciones.consultas.ParamObtenerDatosPreviosEndoso;
import uy.com.bse.cotizaciones.consultas.ParamObtenerDatosRenovacion;
import uy.com.bse.cotizaciones.consultas.ParamObtenerDetalleCuota;
import uy.com.bse.cotizaciones.consultas.ParamObtenerDomiciliosCliente;
import uy.com.bse.cotizaciones.consultas.ParamObtenerFechaEmision;
import uy.com.bse.cotizaciones.consultas.ParamObtenerFranquicias;
import uy.com.bse.cotizaciones.consultas.ParamObtenerListaBienes;
import uy.com.bse.cotizaciones.consultas.ParamObtenerNotasPoliza;
import uy.com.bse.cotizaciones.consultas.ParamObtenerPlanesCobertura;
import uy.com.bse.cotizaciones.consultas.ParamObtenerPlanesPago;
import uy.com.bse.cotizaciones.consultas.ParamObtenerResumenPlanesCobertura;
import uy.com.bse.cotizaciones.consultas.ParamObtenerTextoClausula;
import uy.com.bse.cotizaciones.consultas.ParamObtenerUbicacionBien;
import uy.com.bse.cotizaciones.consultas.ParamParentesco;
import uy.com.bse.cotizaciones.consultas.ParamPersonaSeleccionada;
import uy.com.bse.cotizaciones.consultas.ParamTextosCotizacion;
import uy.com.bse.cotizaciones.consultas.ParamVigenciaTecnica;
import uy.com.bse.cotizaciones.consultas.Parentesco;
import uy.com.bse.cotizaciones.consultas.ResultClientes;
import uy.com.bse.cotizaciones.consultas.ResultDetalleTextoCotizacion;
import uy.com.bse.cotizaciones.consultas.ResultFueraPautas;
import uy.com.bse.cotizaciones.consultas.ResultObtenerBienes;
import uy.com.bse.cotizaciones.consultas.ResultObtenerClientesSolicitante;
import uy.com.bse.cotizaciones.consultas.ResultObtenerFechaEmision;
import uy.com.bse.cotizaciones.consultas.ResultObtenerTextoClausula;
import uy.com.bse.cotizaciones.consultas.ResultParentesco;
import uy.com.bse.cotizaciones.consultas.ResultVigenciaTecnica;
import uy.com.bse.cotizaciones.consultas.VigenciaTecnica;
import uy.com.bse.cotizaciones.entidades.Acreedor;
import uy.com.bse.cotizaciones.entidades.BienAgregar;
import uy.com.bse.cotizaciones.entidades.DatosBanco;
import uy.com.bse.cotizaciones.lovs.AcreedorObjetos;
import uy.com.bse.cotizaciones.lovs.Dato;
import uy.com.bse.cotizaciones.lovs.EnPauta;
import uy.com.bse.cotizaciones.lovs.Facturacion;
import uy.com.bse.cotizaciones.lovs.Grupo;
import uy.com.bse.cotizaciones.lovs.MarcaObjeto;
import uy.com.bse.cotizaciones.lovs.MedioPago;
import uy.com.bse.cotizaciones.lovs.Modificada;
import uy.com.bse.cotizaciones.lovs.ModoCalculo;
import uy.com.bse.cotizaciones.lovs.Moneda;
import uy.com.bse.cotizaciones.lovs.Objeto;
import uy.com.bse.cotizaciones.lovs.OrigenPago;
import uy.com.bse.cotizaciones.lovs.ParamAcreedor;
import uy.com.bse.cotizaciones.lovs.ParamAcreedorObjetos;
import uy.com.bse.cotizaciones.lovs.ParamAcreedoresFiltrados;
import uy.com.bse.cotizaciones.lovs.ParamDatos;
import uy.com.bse.cotizaciones.lovs.ParamDatosXTipoNota;
import uy.com.bse.cotizaciones.lovs.ParamEnPauta;
import uy.com.bse.cotizaciones.lovs.ParamListaBancos;
import uy.com.bse.cotizaciones.lovs.ParamListaGrupos;
import uy.com.bse.cotizaciones.lovs.ParamListaTipoNota;
import uy.com.bse.cotizaciones.lovs.ParamListaTodasSecciones;
import uy.com.bse.cotizaciones.lovs.ParamListaTodosProductos;
import uy.com.bse.cotizaciones.lovs.ParamListaValoresDato;
import uy.com.bse.cotizaciones.lovs.ParamListaValoresDatoVehiculo;
import uy.com.bse.cotizaciones.lovs.ParamMarcasObjeto;
import uy.com.bse.cotizaciones.lovs.ParamMedioPago;
import uy.com.bse.cotizaciones.lovs.ParamModificada;
import uy.com.bse.cotizaciones.lovs.ParamModoCalculo;
import uy.com.bse.cotizaciones.lovs.ParamMoneda;
import uy.com.bse.cotizaciones.lovs.ParamNivelesComision;
import uy.com.bse.cotizaciones.lovs.ParamNivelesComisionProd;
import uy.com.bse.cotizaciones.lovs.ParamObjetos;
import uy.com.bse.cotizaciones.lovs.ParamOrigenPago;
import uy.com.bse.cotizaciones.lovs.ParamProducto;
import uy.com.bse.cotizaciones.lovs.ParamPromocion;
import uy.com.bse.cotizaciones.lovs.ParamRenovacion;
import uy.com.bse.cotizaciones.lovs.ParamRenovacionXProducto;
import uy.com.bse.cotizaciones.lovs.ParamSecciones;
import uy.com.bse.cotizaciones.lovs.ParamTipo;
import uy.com.bse.cotizaciones.lovs.ParamTipoAcreedores;
import uy.com.bse.cotizaciones.lovs.ParamTipoTexto;
import uy.com.bse.cotizaciones.lovs.ParamTiposFacturacion;
import uy.com.bse.cotizaciones.lovs.ParamTiposNotaGenerica;
import uy.com.bse.cotizaciones.lovs.ParamValoresTipoNota;
import uy.com.bse.cotizaciones.lovs.ParamVigencia;
import uy.com.bse.cotizaciones.lovs.Producto;
import uy.com.bse.cotizaciones.lovs.Promocion;
import uy.com.bse.cotizaciones.lovs.Ramo;
import uy.com.bse.cotizaciones.lovs.Renovacion;
import uy.com.bse.cotizaciones.lovs.ResultAcreedor;
import uy.com.bse.cotizaciones.lovs.ResultAcreedorObjetos;
import uy.com.bse.cotizaciones.lovs.ResultDatos;
import uy.com.bse.cotizaciones.lovs.ResultDatosXTipoNota;
import uy.com.bse.cotizaciones.lovs.ResultListaBancos;
import uy.com.bse.cotizaciones.lovs.ResultListaEnPauta;
import uy.com.bse.cotizaciones.lovs.ResultListaGrupos;
import uy.com.bse.cotizaciones.lovs.ResultListaMarcasObjeto;
import uy.com.bse.cotizaciones.lovs.ResultListaMedioPago;
import uy.com.bse.cotizaciones.lovs.ResultListaModificada;
import uy.com.bse.cotizaciones.lovs.ResultListaModoCalculo;
import uy.com.bse.cotizaciones.lovs.ResultListaMoneda;
import uy.com.bse.cotizaciones.lovs.ResultListaObjetos;
import uy.com.bse.cotizaciones.lovs.ResultListaOrigenPago;
import uy.com.bse.cotizaciones.lovs.ResultListaProducto;
import uy.com.bse.cotizaciones.lovs.ResultListaPromocion;
import uy.com.bse.cotizaciones.lovs.ResultListaRenovacion;
import uy.com.bse.cotizaciones.lovs.ResultListaSecciones;
import uy.com.bse.cotizaciones.lovs.ResultListaTipo;
import uy.com.bse.cotizaciones.lovs.ResultListaTipoNota;
import uy.com.bse.cotizaciones.lovs.ResultListaTiposFacturacion;
import uy.com.bse.cotizaciones.lovs.ResultListaTodasSecciones;
import uy.com.bse.cotizaciones.lovs.ResultListaTodosProductos;
import uy.com.bse.cotizaciones.lovs.ResultListaValoresDato;
import uy.com.bse.cotizaciones.lovs.ResultListaVigencia;
import uy.com.bse.cotizaciones.lovs.ResultRenovacionXProducto;
import uy.com.bse.cotizaciones.lovs.ResultTipoAcreedores;
import uy.com.bse.cotizaciones.lovs.ResultTipoTexto;
import uy.com.bse.cotizaciones.lovs.Tipo;
import uy.com.bse.cotizaciones.lovs.TipoAcreedor;
import uy.com.bse.cotizaciones.lovs.TipoNota;
import uy.com.bse.cotizaciones.lovs.TipoTexto;
import uy.com.bse.cotizaciones.lovs.ValorDato;
import uy.com.bse.cotizaciones.lovs.Vigencia;
import uy.com.bse.cotizaciones.operaciones.CotOperaciones;
import uy.com.bse.maestro.persistencia.ServiciosRector;
import uy.com.bse.maestro.personas.domicilio.DireccionEC;
import uy.com.bse.polizas.persistencia.ServiciosPolizasPersist;
import uy.com.bse.utilitario.dato.Codiguera;
import uy.com.bse.utilitario.dato.ResultCodiguera;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.dato.ServiciosError;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.properties.PropertiesManager;
import uy.com.bse.utilitario.util.Herramientas;

public class CotizacionesPersist extends ServiciosRector {

	private static final Logger LOG = LogManager.getLogger(CotizacionesPersist.class);

	private static final String SERVRECTPROP = "serviciosRector.properties";
	private static final String COTIZPROP = "cotizaciones.properties";

	public ResultListaSecciones listaSecciones(final ParamSecciones param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaSecciones");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaSecciones resultado = new ResultListaSecciones();

		try {
			conn = crearConexion();

			final StringBuffer sql = new StringBuffer(230);
			sql.append("SELECT CARP_CD_RAMO, CARP_DE_RAMO FROM CART_RAMOS_POLIZAS where ");
			sql.append("PAC_PERMISOS.FUN_PERMISO_USUARIO(ZPU_GEN_FUNCIONES.B_USER(PAC_WEB_UTIL.FUN_USUARIO_RECTOR('" + param.getUsuario() + "')), CARP_CD_RAMO,null,NULL) ");
			sql.append("IN ('T','P') ");
			sql.append("AND EXISTS (SELECT 1 FROM CART_PRODPLANES WHERE CAPB_CARP_CD_RAMO = CARP_CD_RAMO AND ");
			sql.append("PAC_PERMISOS.FUN_PERMISO_USUARIO(ZPU_GEN_FUNCIONES.B_USER(PAC_WEB_UTIL.FUN_USUARIO_RECTOR('" + param.getUsuario()
					+ "')), CAPB_CARP_CD_RAMO, CAPB_CAPU_CD_PRODUCTO,CAPB_CD_PLAN)='T') ");
			sql.append("ORDER BY 1");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());

			result = pst.executeQuery();

			while (result.next()) {
				final Ramo obj = new Ramo();
				obj.setCodigo(result.getInt("CARP_CD_RAMO"));
				obj.setDescripcion(result.getString("CARP_DE_RAMO"));
				resultado.setUnRamo(obj);
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

	public ResultListaTodasSecciones listaTodasSecciones(final ParamListaTodasSecciones param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaTodasSecciones");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaTodasSecciones resultado = new ResultListaTodasSecciones();

		try {
			conn = crearConexion();

			final StringBuffer sql = new StringBuffer(230);
			sql.append("SELECT CARP_CD_RAMO, CARP_DE_RAMO FROM CART_RAMOS_POLIZAS  ");
			sql.append(" ORDER BY 1");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			result = pst.executeQuery();

			while (result.next()) {
				final Ramo obj = new Ramo();
				obj.setCodigo(result.getInt("CARP_CD_RAMO"));
				obj.setDescripcion(result.getString("CARP_DE_RAMO"));

				resultado.setUnRamo(obj);
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

	/**
	 * 
	 * @param param
	 * @return Lista de productos
	 */
	public ResultListaProducto listaProducto(final ParamProducto param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaProducto");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaProducto resultado = new ResultListaProducto();

		try {
			conn = crearConexion();

			final StringBuffer sql = new StringBuffer(280);

			sql.append("SELECT capu_cd_producto, capu_de_producto ");
			sql.append("FROM CART_PRODUCTOS ");
			sql.append("where CAPU_ST_PRODUCTO = 1 AND CAPU_FE_INICIO <= SYSDATE ");
			sql.append("AND capu_carp_cd_ramo = ? ");
			sql.append("and PAC_PERMISOS.FUN_PERMISO_USUARIO(ZPU_GEN_FUNCIONES.B_USER(PAC_WEB_UTIL.FUN_USUARIO_RECTOR(?)), ");
			sql.append("capu_carp_cd_ramo, capu_cd_producto, NULL) IN ('T', 'P') ");
			sql.append("AND EXISTS ");
			sql.append("(SELECT 1 FROM CART_PRODPLANES ");
			sql.append("WHERE CAPB_CARP_CD_RAMO = ? ");
			sql.append("AND CAPB_CAPU_CD_PRODUCTO = capu_cd_producto ");
			sql.append("AND PAC_PERMISOS.FUN_PERMISO_USUARIO(ZPU_GEN_FUNCIONES.B_USER(PAC_WEB_UTIL.FUN_USUARIO_RECTOR(?)), ");
			sql.append("CAPB_CARP_CD_RAMO, ");
			sql.append("CAPB_CAPU_CD_PRODUCTO, ");
			sql.append("CAPB_CD_PLAN) = 'T') ");
			sql.append("ORDER BY 1 ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, param.getCodRamo());
			pst.setString(2, param.getUsuario());
			pst.setInt(3, param.getCodRamo());
			pst.setString(4, param.getUsuario());

			result = pst.executeQuery();

			while (result.next()) {
				final Producto obj = new Producto();
				obj.setCodigo(result.getString("capu_cd_producto"));
				obj.setDescripcion(result.getString("capu_de_producto"));
				resultado.setUnProducto(obj);
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

	public ResultListaTodosProductos listaTodosProductos(final ParamListaTodosProductos param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaTodosProductos");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaTodosProductos resultado = new ResultListaTodosProductos();

		try {
			conn = crearConexion();

			final StringBuffer sql = new StringBuffer(280);
			sql.append("SELECT capu_cd_producto, capu_de_producto FROM CART_PRODUCTOS where ");
			sql.append("CAPU_ST_PRODUCTO = 1 AND CAPU_FE_INICIO <= SYSDATE AND capu_carp_cd_ramo=? ");
			sql.append(" ORDER BY 1");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, param.getCodRamo());
			result = pst.executeQuery();

			while (result.next()) {
				final Producto obj = new Producto();
				obj.setCodigo(result.getString("capu_cd_producto"));
				obj.setDescripcion(result.getString("capu_de_producto"));

				resultado.setUnProducto(obj);
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

	/**
	 * 
	 * @param param
	 * @return Lista de tipos
	 */
	public ResultListaTipo listaTipo(final ParamTipo param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("ListaTipo");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultListaTipo resultado = new ResultListaTipo();

		try {
			// Este método no tiene consulta
			final PropertiesManager properties = new PropertiesManager();
			properties.setNombreArchivo(COTIZPROP);

			final String valorObtenido = properties.obtenerValor("cantidadListaTipo");
			final int cantidadElementos = Integer.parseInt(valorObtenido);

			for (int i = 1; i <= cantidadElementos; i++) {
				final Tipo obj = new Tipo();
				obj.setCodigo(properties.obtenerValor("tipo-" + i + "-cod"));
				obj.setDescripcion(properties.obtenerValor("tipo-" + i + "-des"));

				resultado.setUnTipo(obj);
			}

			LOG.info(logueo.getSoloParametros());
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		}

		return resultado;
	}

	/**
	 * 
	 * @param param
	 * @return Lista de modificadas
	 */
	public ResultListaModificada listaModificada(final ParamModificada param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaModificada");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultListaModificada resultado = new ResultListaModificada();

		try {
			// Este método no tiene consulta
			final PropertiesManager properties = new PropertiesManager();
			properties.setNombreArchivo(COTIZPROP);

			final String valorObtenido = properties.obtenerValor("cantidadListaModificada");
			final int cantidadElementos = Integer.parseInt(valorObtenido);

			for (int i = 1; i <= cantidadElementos; i++) {
				final Modificada obj = new Modificada();
				obj.setCodigo(properties.obtenerValor("modif-" + i + "-cod"));
				obj.setDescripcion(properties.obtenerValor("modif-" + i + "-des"));

				resultado.setUnModificada(obj);
			}

			LOG.info(logueo.getSoloParametros());
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		}
		return resultado;
	}

	/**
	 * 
	 * @param param
	 * @return Lista de en pautas
	 */
	public ResultListaEnPauta listaEnPauta(final ParamEnPauta param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaEnPauta");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultListaEnPauta resultado = new ResultListaEnPauta();

		try {
			// Este método no tiene consulta
			final PropertiesManager properties = new PropertiesManager();
			properties.setNombreArchivo(COTIZPROP);

			final String valorObtenido = properties.obtenerValor("cantidadListaEnPauta");
			final int cantidadElementos = Integer.parseInt(valorObtenido);

			for (int i = 1; i <= cantidadElementos; i++) {
				final EnPauta obj = new EnPauta();
				obj.setCodigo(properties.obtenerValor("pauta-" + i + "-cod"));
				obj.setDescripcion(properties.obtenerValor("pauta-" + i + "-des"));

				resultado.setUnEnPauta(obj);
			}

			LOG.info(logueo.getSoloParametros());
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		}

		return resultado;
	}

	/**
	 * 
	 * @param param
	 * @return Lista de tipos de facturacion
	 */
	public ResultListaTiposFacturacion listaTiposFacturacion(final ParamTiposFacturacion param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaTiposFacturacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaTiposFacturacion resultado = new ResultListaTiposFacturacion();

		try {
			conn = crearConexion();

			final StringBuffer sql = new StringBuffer();
			sql.append("select ctfa_tp_facturacion codigo, ctfa_de_facturacion descripcion ");
			sql.append("from cart_tipos_facturacion where ctfa_in_mostrar_lov = 'S'");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());

			result = pst.executeQuery();

			while (result.next()) {
				final Facturacion obj = new Facturacion();
				obj.setCodigo(result.getString("codigo"));
				obj.setDescripcion(result.getString("descripcion"));

				resultado.setUnElemento(obj);
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

	/**
	 * 
	 * @param param
	 * @return Lista de medio de pago
	 */
	public ResultListaMedioPago listaMedioPago(final ParamMedioPago param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaMedioPago");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaMedioPago resultado = new ResultListaMedioPago();

		try {
			conn = crearConexion();

			final StringBuffer sql = new StringBuffer(140);
			sql.append("select camd_cd_medio_pago codigo, camd_de_medio_pago descripcion, CAMD_IN_DOMICILIACION ");
			sql.append("from cart_medio_pago WHERE CAMD_IN_EMISION = 'S'");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());

			result = pst.executeQuery();

			while (result.next()) {
				final MedioPago obj = new MedioPago();
				obj.setCodigo(result.getInt("codigo"));
				obj.setDescripcion(result.getString("descripcion"));

				resultado.setUnElemento(obj);
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

	/**
	 * 
	 * @param param
	 * @return Lista de origen de pago
	 */
	public ResultListaOrigenPago listaOrigenPago(final ParamOrigenPago param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaOrigenPago");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaOrigenPago resultado = new ResultListaOrigenPago();

		try {
			conn = crearConexion();

			final StringBuffer sql = new StringBuffer(100);
			sql.append("select  COTC_CD_ORIGEN, COTC_DE_ORIGEN from COBT_TIPOS_COBRANZAS ");
			sql.append("where COTC_CAMD_CD_MEDIO_PAGO = ?");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, param.getCodMedioPago());

			result = pst.executeQuery();

			while (result.next()) {
				final OrigenPago obj = new OrigenPago();
				obj.setCodigo(result.getString("COTC_CD_ORIGEN"));
				obj.setDescripcion(result.getString("COTC_DE_ORIGEN"));

				resultado.setUnElemento(obj);
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

	/**
	 * 
	 * @param param
	 * @return Lista de promociones
	 
	 */
	public ResultListaPromocion listaPromocion(final ParamPromocion param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaPromocion");
		logueo.setParametro("Codigo producto", param.getCodProducto());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero cliente", param.getCodCliente());
		logueo.setParametro("Codigo de productor", param.getCodProductor());
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaPromocion resultado = new ResultListaPromocion();

		try {
			conn = crearConexion();

			final PropertiesManager properties = new PropertiesManager();
			properties.setNombreArchivo(SERVRECTPROP);

			final String codigoNacionalidad = properties.obtenerValor("codigoNacionalidad");
			final int codigoTabla = Integer.parseInt(properties.obtenerValor("codigoTabla"));
			final int rolGeneral = Integer.parseInt(properties.obtenerValor("rolGeneral"));
			final int rolProductor = Integer.parseInt(properties.obtenerValor("rolProductor"));
			StringBuffer sql = new StringBuffer(470);
			sql.append("SELECT carm_cd_promocion, crtb_de_dato FROM CART_REL_COTIZA_PROMOCION, CRET_TABLAS WHERE ");
			sql.append("carm_carp_cd_ramo = ? AND carm_capu_cd_producto= ? AND ");
			sql.append("((carm_cabu_nu_persona = (SELECT cacn_cabu_nu_persona FROM CART_CLIENTES WHERE ");
			sql.append("cacn_cd_nacionalidad  = ? AND  cacn_nu_cedula_rif = ?) ");
			if (param.getCodProductor()!=null) {
				sql.append("OR carm_cabu_nu_persona = -1) OR carm_capd_cd_productor = ?) ");	
			} else {
				sql.append("OR carm_cabu_nu_persona = -1) OR carm_capd_cd_productor = Pac_Web_Util.FUN_CODIGO_CORREDOR(?)) ");
			}
			sql.append("and carm_cacl_cd_tipo_rol IN (?, ?) and crtb_cd_tabla = ? ");
			sql.append("and carm_cd_promocion = crtb_index");
			
			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());

			pst.setInt(1, param.getCodRamo());
			pst.setString(2, param.getCodProducto());
			pst.setString(3, codigoNacionalidad);

			if (param.getCodCliente() == null) {
				pst.setNull(4, Types.INTEGER);
			} else {
				pst.setString(4, param.getCodCliente());
			}

			if (param.getCodProductor()!=null) {
				pst.setString(5, param.getCodProductor());
			} else {
				pst.setString(5, param.getUsuario());
			}
			
			pst.setInt(6, rolProductor);
			pst.setInt(7, rolGeneral);
			pst.setInt(8, codigoTabla);

			result = pst.executeQuery();

			while (result.next()) {
				final Promocion obj = new Promocion();
				obj.setCodigo(result.getString("carm_cd_promocion"));
				obj.setDescripcion(result.getString("crtb_de_dato"));

				resultado.setUnElemento(obj);
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
	/**
	 * 
	 * @param param
	 * @return Lista de renovaciones
	 */
	public ResultListaRenovacion listaRenovacion(final ParamRenovacion param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaRenovacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaRenovacion resultado = new ResultListaRenovacion();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT CRTB_INDEX, CRTB_DE_DATO FROM CRET_TABLAS WHERE CRTB_CD_TABLA = 900906");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());

			result = pst.executeQuery();

			while (result.next()) {
				Renovacion obj = new Renovacion();
				obj.setCodigo(result.getString("CRTB_INDEX"));
				obj.setDescripcion(result.getString("CRTB_DE_DATO"));

				resultado.setUnElemento(obj);
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

	public ResultListaMoneda listaMoneda(final ParamMoneda param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaMoneda");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaMoneda resultado = new ResultListaMoneda();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT CAMO_CD_MONEDA CODIGO, CAMO_DE_MONEDA DESCRIPCION, camo_sm_moneda ");
			sql.append("FROM CART_MONEDAS WHERE ");
			sql.append("CAMO_IN_EMISION = 'S' order by CAMO_DE_MONEDA");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());

			result = pst.executeQuery();

			while (result.next()) {
				Moneda obj = new Moneda();
				obj.setCodigo(result.getInt("CODIGO"));
				obj.setDescripcion(result.getString("DESCRIPCION"));
				obj.setSimbolo(result.getString("camo_sm_moneda")); // simbolo
																	// moneda

				resultado.setUnElemento(obj);
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

	public ResultListaModoCalculo listaModoCalculo(final ParamModoCalculo param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaModoCalculo");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaModoCalculo resultado = new ResultListaModoCalculo();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT SUBSTR(RV_LOW_VALUE, 1, 3) CODIGO, SUBSTR(RV_MEANING, 1, 40) DESCRIPCION ");
			sql.append("FROM CG_REF_CODES WHERE RV_DOMAIN = 'CART_COTIZA_BANCO.CAZB_TP_CALCULO' AND ");
			sql.append("RV_LOW_VALUE NOT IN ('M','F','C','R')");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());

			result = pst.executeQuery();

			while (result.next()) {
				ModoCalculo obj = new ModoCalculo();
				obj.setCodigo(result.getString("CODIGO"));
				obj.setDescripcion(result.getString("DESCRIPCION"));

				resultado.setUnElemento(obj);
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

	public ResultListaVigencia listaVigencia(final ParamVigencia param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaVigencia");
		logueo.setParametro("Producto", param.getCodProducto());
		logueo.setParametro("Ramo", param.getCodRamo());
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaVigencia resultado = new ResultListaVigencia();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT CAFP_CD_VIGENCIA CODIGO, CAFP_DE_VIGENCIA DESCRIPCION, CAFP_NU_MESES_VIGENCIA ");
			sql.append("MESES FROM CART_FR_VIGENCIAS, CART_FR_VIGENCIAS_PRODUCTOS WHERE ");
			sql.append("CAFP_CD_VIGENCIA = CAPG_CAFP_CD_VIGENCIA AND CAPG_CAPU_CD_PRODUCTO = ? ");
			sql.append("AND CAPG_CARP_CD_RAMO  = ? ORDER BY CAFP_CD_VIGENCIA");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, param.getCodProducto());
			pst.setInt(2, param.getCodRamo());

			result = pst.executeQuery();

			while (result.next()) {
				Vigencia obj = new Vigencia();
				obj.setCodigo(result.getString("CODIGO"));
				obj.setDescripcion(result.getString("DESCRIPCION"));

				resultado.setUnElemento(obj);
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

	public ResultListaValoresDato listaValoresDatoParametricoVehiculo(ParamListaValoresDatoVehiculo param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaValoresDatoParametricoVehiculo");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cantida de filtros", param.getCantFiltros());
		logueo.setParametro("Codigo dato", param.getCodDato());
		logueo.setParametro("Primer filtro", param.getPrimerFiltro());
		logueo.setParametro("Segundo filtro", param.getSegundoFiltro());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaValoresDato resultado = new ResultListaValoresDato();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			switch (Integer.parseInt(param.getCantFiltros())) {
			case 0:

				sql.append("SELECT T.CRTB_DATO1 CODIGO, T.CRTB_DE_DATO ");
				sql.append("FROM CRET_TABLAS T, ");
				sql.append(" CRET_DATOS D ");
				sql.append("WHERE D.CRCD_CD_DATO =? ");
				sql.append(" AND T.CRTB_CD_TABLA = D.CRCD_CD_TABLA_VALID ");
				sql.append("ORDER BY T.CRTB_DATO1");
				logueo.setParametro(Values.CONSULTA, sql.toString());
				pst = conn.prepareStatement(sql.toString());
				pst.setInt(1, Integer.valueOf(param.getCodDato()));

				break;

			case 1:

				sql.append("SELECT T.CRTB_DATO2 CODIGO, T.CRTB_DE_DATO ");
				sql.append("FROM CRET_TABLAS T, ");
				sql.append("CRET_DATOS D ");
				sql.append("WHERE D.CRCD_CD_DATO = ? ");
				sql.append("AND T.CRTB_CD_TABLA = D.CRCD_CD_TABLA_VALID ");
				sql.append("AND T.CRTB_INDEX LIKE ? ");
				sql.append("ORDER BY T.CRTB_DATO2");

				logueo.setParametro(Values.CONSULTA, sql.toString());
				pst = conn.prepareStatement(sql.toString());
				pst.setInt(1, Integer.valueOf(param.getCodDato()));
				pst.setString(2, (param.getPrimerFiltro() + "|%"));

				break;
			case 2:

				sql.append("SELECT T.CRTB_DATO3 CODIGO, T.CRTB_DE_DATO ");
				sql.append("FROM CRET_TABLAS T, ");
				sql.append("CRET_DATOS D ");
				sql.append("WHERE D.CRCD_CD_DATO = ? ");
				sql.append("AND T.CRTB_CD_TABLA = D.CRCD_CD_TABLA_VALID ");
				sql.append("AND T.CRTB_INDEX LIKE ? "); // --Concatena Cod Marca
														// + | + Cod Tipo Veh +
														// Cod Tipo Comb + Cod
														// Año + |
				sql.append("ORDER BY T.CRTB_DATO3 ");

				logueo.setParametro(Values.CONSULTA, sql.toString());
				pst = conn.prepareStatement(sql.toString());
				pst.setInt(1, Integer.valueOf(param.getCodDato()));
				StringBuffer sb = new StringBuffer();

				sb.append(param.getPrimerFiltro());
				sb.append("|");
				sb.append(param.getSegundoFiltro());
				sb.append("|%");
				pst.setString(2, sb.toString());

				break;

			default:
				break;
			}

			LOG.info(logueo.getSoloParametros());
			if (pst != null) {
				result = pst.executeQuery();

				while (result.next()) {
					ValorDato obj = new ValorDato();
					obj.setCodigo(result.getString("CODIGO"));
					obj.setDecripcion(result.getString("CRTB_DE_DATO"));
					resultado.setUnElemento(obj);
				}
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

	public ResultListaValoresDato listaValoresDatoParametrico(ParamListaValoresDato param) {
		// FIXME OIGRES... AQUI NO SE ESTAN VALIDANDO TODOS LOS PARAMETROS...VER
		// SI HAY OTROS OBLIGATORIOS, EJ EL CODIGO DE LA TABLA.
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaValoresDatoParametrico");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Codigo dato", param.getCodDato());
		logueo.setParametro("Codigo tabla", param.getCodTabla());
		logueo.setParametro("Numero consecutivo del bien", param.getNumConsBien());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaValoresDato resultado = new ResultListaValoresDato();
		int cantFiltros = obtenerFiltros(param);
		if (cantFiltros > -1) {
			try {
				conn = crearConexion();

				StringBuffer sql = new StringBuffer();
				switch (cantFiltros) {
				case 0:
					sql.append("SELECT CRTB_DATO1 CODIGO, CRTB_DE_DATO ");
					sql.append("FROM CRET_TABLAS ");
					sql.append("WHERE CRTB_CD_TABLA = ? ");
					sql.append("ORDER BY CRTB_DATO1");
					logueo.setParametro(Values.CONSULTA, sql.toString());
					pst = conn.prepareStatement(sql.toString());

					pst.setInt(1, Integer.valueOf(param.getCodTabla()));

					break;

				case 1:
					sql.append("SELECT CRTB_DATO2 CODIGO, CRTB_DE_DATO ");
					sql.append("FROM CRET_TABLAS, CRET_COTIZA_DATOS ");
					sql.append("WHERE CRTB_CD_TABLA = ? ");
					sql.append("AND CRCO_CRTD_CD_DATO = ? ");
					sql.append("AND CRCO_CAZB_NU_COTIZACION = ?");
					sql.append("AND CRCO_CAZB_NU_CONSECUTIVO = ? ");
					sql.append("AND CRCO_CD_BIEN_ASEG = ? ");
					sql.append("AND CRTB_INDEX LIKE SUBSTR(CRCO_DE_DATO, 1, INSTR(CRCO_DE_DATO, '|'))||'%' ");
					sql.append("ORDER BY CRTB_DATO2 ");

					logueo.setParametro(Values.CONSULTA, sql.toString());
					pst = conn.prepareStatement(sql.toString());
					pst.setInt(1, Integer.valueOf(param.getCodTabla()));
					pst.setInt(2, Integer.valueOf(param.getCodDato()));
					pst.setInt(3, Integer.valueOf(param.getNumCotizacion()));
					pst.setInt(4, Integer.valueOf(param.getNumCertificado()));
					pst.setInt(5, Integer.valueOf(param.getNumConsBien()));

					break;
				case 2:
					sql.append("SELECT CRTB_DATO3 CODIGO, CRTB_DE_DATO ");
					sql.append("FROM CRET_TABLAS, CRET_COTIZA_DATOS ");
					sql.append("WHERE CRTB_CD_TABLA  = ? ");
					sql.append("AND CRCO_CRTD_CD_DATO = ? ");
					sql.append("AND CRCO_CAZB_NU_COTIZACION = ? ");
					sql.append("AND CRCO_CAZB_NU_CONSECUTIVO = ? ");
					sql.append("AND CRCO_CD_BIEN_ASEG = ? ");
					sql.append("AND CRTB_INDEX LIKE SUBSTR(CRCO_DE_DATO, 1, INSTR(CRCO_DE_DATO, '|', 1, 2))||'%' ");
					sql.append("ORDER BY CRTB_DATO3 ");
					logueo.setParametro(Values.CONSULTA, sql.toString());
					pst = conn.prepareStatement(sql.toString());
					pst.setInt(1, Integer.valueOf(param.getCodTabla()));
					pst.setInt(2, Integer.valueOf(param.getCodDato()));
					pst.setInt(3, Integer.valueOf(param.getNumCotizacion()));
					pst.setInt(4, Integer.valueOf(param.getNumCertificado()));
					pst.setInt(5, Integer.valueOf(param.getNumConsBien()));
					break;

				default:
					break;
				}

				LOG.info(logueo.getSoloParametros());

				if (pst != null) {
					result = pst.executeQuery();

					while (result.next()) {
						ValorDato obj = new ValorDato();
						obj.setCodigo(result.getString("CODIGO"));
						obj.setDecripcion(result.getString("CRTB_DE_DATO"));

						resultado.setUnElemento(obj);
					}
				}

			} catch (SQLException e) {
				catchSQLException(resultado, logueo, e);
			} catch (Exception ex) {
				catchException(resultado, logueo, ex);
			} finally {
				liberarRecursos(conn, pst, result);
			}

		} else {
			resultado.setError(new ServiciosError(33,"Error obteniendo los filtros"));
			resultado.setHayError(Boolean.TRUE);
		}
		return resultado;

	}

	private int obtenerFiltros(ParamListaValoresDato param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerFiltros");
		logueo.setParametro("Num Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Num Certificado", param.getNumCertificado());
		logueo.setParametro("Num Consecutivo Bien", param.getNumConsBien());
		logueo.setParametro("Codigo Dato", param.getCodDato());
		int resultado = -1;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT LENGTH(CRCO_DE_DATO) - LENGTH(REPLACE(CRCO_DE_DATO,'|')) CANTIDAD ");
			sql.append("FROM CRET_COTIZA_DATOS ");
			sql.append("WHERE CRCO_CAZB_NU_COTIZACION = ? ");
			sql.append("AND CRCO_CAZB_NU_CONSECUTIVO = ? ");
			sql.append("AND CRCO_CD_BIEN_ASEG = ? ");
			sql.append("AND CRCO_CRTD_CD_DATO = ? ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			pst.setInt(2, Integer.valueOf(param.getNumCertificado()));
			pst.setInt(3, Integer.valueOf(param.getNumConsBien()));
			pst.setInt(4, Integer.valueOf(param.getCodDato()));

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

	public ResultClientes obtenerClientes(final ParamClientes param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerClientes");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de cliente", param.getCodCliente());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultClientes resultado = new ResultClientes();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("select CACN_NU_CEDULA_RIF,CACN_NM_APELLIDO_RAZON,CACN_NM_PERSONA_NATURAL, ");
			sql.append("CACN_TP_DOCUMENTO,CACN_NU_DOCUMENTO, CACN_CUIT, CADO_DE_CALLE ");
			sql.append("||' '|| CADO_DE_NUMERO ||' '|| CADO_DE_DEPARTAMENTO direccion from cart_clientes, ");
			sql.append("cart_personas_direcciones cado_1 where (EXISTS (SELECT '1' FROM cart_certificados ");
			sql.append("WHERE CACE_CACN_CD_NACIONALIDAD=CACN_CD_NACIONALIDAD AND CACE_CACN_NU_CEDULA_RIF ");
			sql.append("= CACN_NU_CEDULA_RIF  AND CACE_CAPD_CD_PRODUCTOR = (SELECT CAUS_CAPD_CD_PRODUCTOR ");
			sql.append("FROM CART_USUARIOS WHERE CAUS_CD_USUARIO = (select COUM_CD_USU_RECTOR ");
			sql.append("from cotw_usuarios_mapping where COUM_CD_USU_DOMINIO = ?))) OR CACN_CABU_NU_PERSONA = ");
			sql.append("(SELECT prod.capd_cabu_nu_persona FROM CART_ROL_PRODUCTORES prod, ");
			sql.append("CART_USUARIOS usu WHERE prod.capd_cd_productor = usu.CAUS_CAPD_CD_PRODUCTOR ");
			sql.append("AND CAUS_CD_USUARIO=(select COUM_CD_USU_RECTOR from cotw_usuarios_mapping ");
			sql.append("where COUM_CD_USU_DOMINIO = ?))) and CACN_CABU_NU_PERSONA = CADO_NU_PERSONA ");
			sql.append("and CADO_CATD_CD_TIPO_DIRECCION = 1 and CADO_CONSECUTIVO_DIRECCION ");
			sql.append("= (select max(CADO_CONSECUTIVO_DIRECCION ) from  cart_personas_direcciones cado_2 ");
			sql.append("where cado_2.cado_nu_persona=cado_1.cado_nu_persona and cado_2.CADO_CATD_CD_TIPO_DIRECCION = 1)");

			boolean codCliente = false;
			if (param.getCodCliente() != null) {
				codCliente = true;
				sql.append(" and CACN_NU_CEDULA_RIF = ?");
			}

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, param.getUsuario());
			pst.setString(2, param.getUsuario());

			if (codCliente) {
				pst.setString(3, param.getCodCliente());
			}

			result = pst.executeQuery();

			while (result.next()) {

				Cliente obj = new Cliente();
				obj.setCodCliente(result.getString("CACN_NU_CEDULA_RIF"));
				obj.setApellidoRazon(result.getString("CACN_NM_APELLIDO_RAZON"));
				obj.setNombre(result.getString("CACN_NM_PERSONA_NATURAL"));
				obj.setTipoDoc(result.getString("CACN_TP_DOCUMENTO"));
				obj.setNumDoc(result.getString("CACN_NU_DOCUMENTO"));
				obj.setRut(result.getString("CACN_CUIT"));
				obj.setDireccion(result.getString("DIRECCION"));

				resultado.setUnElemento(obj);
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

	public ResultFueraPautas obtenerFueraPautas(final ParamFueraPautas param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerFueraPautas");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Cotizacion", param.getNumCotizacion());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultFueraPautas resultado = new ResultFueraPautas();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("select COFP_CAZB_NU_COTIZACION, COFP_CAZB_NU_CONSECUTIVO, COFP_NU_ORDINAL, COFP_DE_FUERA_PAUTA ");
			sql.append("from CART_COTIZA_FP where COFP_CAZB_NU_COTIZACION = ? order by cofp_nu_ordinal");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, param.getNumCotizacion());

			result = pst.executeQuery();

			while (result.next()) {
				FueraPauta obj = new FueraPauta();
				obj.setDescripcion(result.getString("COFP_DE_FUERA_PAUTA"));
				obj.setNumConsecutivo(result.getInt("COFP_CAZB_NU_CONSECUTIVO"));
				obj.setNumCotizacion(result.getInt("COFP_CAZB_NU_COTIZACION"));
				obj.setNumOrdinal(result.getInt("COFP_NU_ORDINAL"));

				resultado.setUnElemento(obj);
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

	public ResultXmlPL obtenerBonificacionNS(ParamBonificacionNS param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerBonificacionNS");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero de cotizacion", param.getNumCotizacion());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerBonificacionNS");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");

			if (param.getNumCotizacion() != null) {
				cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			}
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

	public ResultXmlPL obtenerCotizaciones(ParamCotizaciones param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerCotizaciones");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de ramo", param.getRamo());
		logueo.setParametro("Fecha desde", param.getFechaDesde());
		logueo.setParametro("Fecha hasta", param.getFechaHasta());
		logueo.setParametro("Nombre cliente", param.getNombreCliente());
		logueo.setParametro("Apellido o razon social de cliente", param.getApellidoRazonSocialCliente());
		logueo.setParametro("Numero de cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero de poliza", param.getNumPoliza());
		logueo.setParametro("En pauta", param.getEnPauta());
		logueo.setParametro("Modificada", param.getModificada());
		logueo.setParametro("Producto", param.getProducto());
		logueo.setParametro("Tipo", param.getTipo());
		logueo.setParametro("Codigo moneda", param.getCodMoneda());
		logueo.setParametro("Orden", param.getOrden());
		logueo.setParametro("Codigo del productor", param.getCodProductor());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerCotizaciones");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			if (param.getRamo() != null) {
				cstmt.setInt(1, Integer.valueOf(param.getRamo()));
			} else {
				cstmt.setNull(1, Types.INTEGER);
			}

			if (param.getFechaDesde() != null) {
				cstmt.setDate(2, toSqlDate(param.getFechaDesde()));
			} else {
				cstmt.setNull(2, Types.DATE);
			}

			if (param.getFechaHasta() != null) {
				cstmt.setDate(3, toSqlDate(param.getFechaHasta()));
			} else {
				cstmt.setNull(3, Types.DATE);
			}

			cstmt.setString(4, param.getNombreCliente());

			cstmt.setString(5, param.getApellidoRazonSocialCliente());

			if (param.getNumCotizacion() != null) {
				cstmt.setInt(6, Integer.valueOf(param.getNumCotizacion()));
			} else {
				cstmt.setNull(6, Types.INTEGER);
			}

			if (param.getNumPoliza() != null) {
				cstmt.setInt(7, Integer.valueOf(param.getNumPoliza()));
			} else {
				cstmt.setNull(7, Types.INTEGER);
			}

			cstmt.setString(8, param.getEnPauta());
			cstmt.setString(9, param.getModificada());
			cstmt.setString(10, param.getProducto());
			cstmt.setString(11, param.getUsuario());

			if (param.getCodProductor() == null) {
				cstmt.setNull(12, Types.INTEGER);
			} else {
				cstmt.setInt(12, Integer.valueOf(param.getCodProductor()));
			}

			cstmt.setString(13, param.getTipo());
			cstmt.setString(14, param.getCodMoneda());
			cstmt.setInt(15, param.getOrden());

			cstmt.registerOutParameter(16, Types.INTEGER);
			cstmt.registerOutParameter(17, Types.VARCHAR);
			cstmt.registerOutParameter(18, Types.VARCHAR);
			cstmt.registerOutParameter(19, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(16);
			String descError = cstmt.getString(17);
			String sqlError = cstmt.getString(18);
			Clob clob = cstmt.getClob(19);
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

	public ResultXmlPL obtenerCabezalCotizaciones(ParamObtenerCabezalCotizaciones param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerCabezalCotizaciones");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de ramo", param.getRamo());
		logueo.setParametro("Fecha desde", param.getFechaDesde());
		logueo.setParametro("Fecha hasta", param.getFechaHasta());
		logueo.setParametro("Nombre cliente", param.getNombreCliente());
		logueo.setParametro("Apellido o razon social de cliente", param.getApellidoRazonSocialCliente());
		logueo.setParametro("Numero de cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero de poliza", param.getNumPoliza());
		logueo.setParametro("En pauta", param.getEnPauta());
		logueo.setParametro("Modificada", param.getModificada());
		logueo.setParametro("Producto", param.getProducto());
		logueo.setParametro("Tipo", param.getTipo());
		logueo.setParametro("Codigo moneda", param.getCodMoneda());
		logueo.setParametro("Orden", param.getOrden());
		logueo.setParametro("Codigo del productor", param.getCodProductor());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerCabezalCotizaciones");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

			if (param.getRamo() != null) {
				cstmt.setInt(1, Integer.valueOf(param.getRamo()));
			} else {
				cstmt.setNull(1, Types.INTEGER);
			}

			if (param.getFechaDesde() != null) {
				cstmt.setDate(2, toSqlDate(param.getFechaDesde()));
			} else {
				cstmt.setNull(2, Types.DATE);
			}

			if (param.getFechaHasta() != null) {
				cstmt.setDate(3, toSqlDate(param.getFechaHasta()));
			} else {
				cstmt.setNull(3, Types.DATE);
			}

			cstmt.setString(4, param.getNombreCliente());

			cstmt.setString(5, param.getApellidoRazonSocialCliente());

			if (param.getNumCotizacion() != null) {
				cstmt.setInt(6, Integer.valueOf(param.getNumCotizacion()));
			} else {
				cstmt.setNull(6, Types.INTEGER);
			}

			if (param.getNumPoliza() != null) {
				cstmt.setInt(7, Integer.valueOf(param.getNumPoliza()));
			} else {
				cstmt.setNull(7, Types.INTEGER);
			}

			cstmt.setString(8, param.getEnPauta());
			cstmt.setString(9, param.getModificada());
			cstmt.setString(10, param.getProducto());
			cstmt.setString(11, param.getUsuario());

			if (param.getCodProductor() == null) {
				cstmt.setNull(12, Types.INTEGER);
			} else {
				cstmt.setInt(12, Integer.valueOf(param.getCodProductor()));
			}

			cstmt.setString(13, param.getTipo());
			cstmt.setString(14, param.getCodMoneda());
			cstmt.setInt(15, param.getOrden());

			cstmt.registerOutParameter(16, Types.INTEGER);
			cstmt.registerOutParameter(17, Types.VARCHAR);
			cstmt.registerOutParameter(18, Types.VARCHAR);
			cstmt.registerOutParameter(19, Types.CLOB);
			cstmt.execute();

			int codError = cstmt.getInt(16);
			String descError = cstmt.getString(17);
			String sqlError = cstmt.getString(18);
			Clob clob = cstmt.getClob(19);
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

	public ResultXmlPL obtenerDatosCotizacion(ParamDatoCotizacion param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerDatosCotizacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_datos_cotizacion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.setInt(1, param.getNumCotizacion());

			if (param.getNumCertificado() == null) {
				cstmt.setNull(2, Types.INTEGER);
			} else {
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

	public ResultXmlPL obtenerDetalleBusquedaCotizacion(ParamDetalleBusquedaCotizacion param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerDetalleBusquedaCotizacion");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Codigo de productor", param.getCodProductor());
		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerDetalleBusquedaCotizacion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.setInt(1, param.getNumCotizacion());
			cstmt.setString(2, param.getUsuario());

			if (param.getCodProductor() == null) {
				cstmt.setNull(3, Types.INTEGER);
			} else {
				cstmt.setInt(3, Integer.valueOf(param.getCodProductor()));
			}

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

	public ResultXmlPL obtenerCertificados(ParamCertificados param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerCertificados");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_certificado");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?)}");
			cstmt.setInt(1, param.getNumCotizacion());

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

	public ResultXmlPL obtenerDatoParametrico(ParamDatoParametrico param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerDatoParametrico");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Codigo de dato", param.getDatoCod());
		logueo.setParametro("Valor de dato", param.getDatoValor());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_dato_parametrico");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.setInt(1, param.getNumCotizacion());
			cstmt.setString(2, param.getDatoCod());
			cstmt.setString(3, param.getDatoValor());

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

	public ResultXmlPL obtenerDatosCertificados(ParamDatosCertificados param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerDatosCertificados");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero consecutivo", param.getNumCertificado());

		final PropertiesManager properties = new PropertiesManager();
		properties.setNombreArchivo(SERVRECTPROP);

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_dato_certificado");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, param.getNumCotizacion());
			cstmt.setInt(2, param.getNumCertificado());
			cstmt.setString(3, param.getUsuario());

			String listoDato = properties.obtenerValor("listoDatoDatosCertificados");

			cstmt.setString(4, listoDato);

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

	public ResultObtenerBienes obtenerBienesParaAgregar(ParamObtenerBienes param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerBienesParaAgregar");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero consecutivo", param.getNumConsecutivo());
		logueo.setParametro("Producto", param.getCodProducto());
		logueo.setParametro("Ramo", param.getCodRamo());
		// FIXME OIGRES, el numero de la sucursal no se usa... ver con gustavo.
		logueo.setParametro("Sucursal", param.getNumSucursal());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultObtenerBienes resultado = new ResultObtenerBienes();

		try {
			conn = crearConexion();

			final StringBuffer sql = new StringBuffer();
			sql.append("SELECT crba_cd_bien_aseg codigo, crba_de_bien_aseg descripcion FROM cret_bienes_asegurados WHERE ");
			sql.append("crba_carp_cd_ramo = ? AND (crba_tp_bien_aseg = 'N' AND NOT EXISTS (SELECT NULL FROM ");
			sql.append("cret_cotiza_certif_bienes WHERE crci_cazb_nu_cotizacion = ");
			sql.append("? AND crci_cazb_nu_consecutivo = ? AND crci_crpb_cd_bien_aseg = ");
			sql.append("crba_cd_bien_aseg) OR crba_tp_bien_aseg = 'R') AND EXISTS (SELECT NULL FROM   cret_productos_bienes WHERE ");
			sql.append("crpb_carp_cd_ramo = crba_carp_cd_ramo AND crpb_capu_cd_producto = ? AND crpb_cd_bien_aseg = ");
			sql.append("crba_cd_bien_aseg) ORDER BY crba_cd_bien_aseg");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, param.getCodRamo());
			pst.setInt(2, param.getNumCotizacion());
			pst.setInt(3, param.getNumConsecutivo());
			pst.setString(4, param.getCodProducto());

			result = pst.executeQuery();

			while (result.next()) {
				BienAgregar bien = new BienAgregar();
				bien.setCodBien(result.getInt("codigo"));
				bien.setDescripBien(result.getString("descripcion"));

				resultado.setUnItem(bien);
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

	public ResultDatos listaDatos(ParamDatos param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaDatos");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultDatos resultado = new ResultDatos();

		try {
			conn = crearConexion();

			final StringBuffer sql = new StringBuffer();
			sql.append("SELECT DISTINCT CRDP_CRTD_CD_DATO, CRCD_DE_DATO FROM CRET_DEFINICION_PRODUCTOS, ");
			sql.append("CRET_DATOS, CRET_BIENES_ASEGURADOS WHERE  CRDP_CRTD_CD_DATO = CRCD_CD_DATO AND ");
			sql.append("CRDP_CARP_CD_RAMO = ? AND CRDP_CAPU_CD_PRODUCTO = ? AND ");
			sql.append("CRBA_CARP_CD_RAMO = CRDP_CARP_CD_RAMO AND CRBA_CD_BIEN_ASEG = CRDP_CD_BIEN_ASEG ");
			sql.append("AND CRDP_IN_IDENTIFICADOR IN ('I','B') AND (CRDP_IN_USO_COTIZAR='S' OR ");
			sql.append("CRDP_IN_USO_POLIZA='S') ORDER BY CRCD_DE_DATO");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, param.getCodRamo());
			pst.setString(2, param.getCodProducto());

			result = pst.executeQuery();

			while (result.next()) {
				Dato item = new Dato();
				item.setCodigo(result.getInt("CRDP_CRTD_CD_DATO"));
				item.setDescripcion(result.getString("CRCD_DE_DATO"));

				resultado.setUnItem(item);
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

	public ResultXmlPL obtenerDatosBasicosCotizacion(ParamObtenerDatosBasicosCotiza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerDatosBasicosCotizacion");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Codigo producto", param.getCodProducto());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerDatosBasicosCotizacion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setString(2, param.getCodProducto());
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

	public ResultXmlPL obtenerUbicacionBien(ParamObtenerUbicacionBien param) {

		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerUbicacionBien");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero consecutivo", param.getConsecutivoBien());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerUbicacionBien");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			cstmt.setInt(2, Integer.valueOf(param.getNumCertificado()));
			cstmt.setInt(3, Integer.valueOf(param.getConsecutivoBien()));

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

	public ResultVigenciaTecnica listaVigenciaTecnica(ParamVigenciaTecnica param) {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaVigenciaTecnica");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Ramo", param.getCodRamo());
		logueo.setParametro("Producto", param.getCodProducto());
		logueo.setParametro("Tipo vigencia", param.getTipoVigencia());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultVigenciaTecnica resultado = new ResultVigenciaTecnica();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("select CAVT_CAFP_CD_VIGENCIA_TECNICA CODIGO, CAFP_DE_VIGENCIA DESCRIPCION ");
			sql.append("from cart_fr_vigencias, cart_vig_tecnicas_productos ");
			sql.append("where CAVT_CARP_CD_RAMO = ? ");
			sql.append("and CAVT_CAPU_CD_PRODUCTO = ? ");
			sql.append("and CAVT_CAFP_CD_VIGENCIA = ? ");
			sql.append("and CAFP_CD_VIGENCIA = CAVT_CAFP_CD_VIGENCIA_TECNICA ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, Integer.valueOf(param.getCodRamo()));
			pst.setString(2, param.getCodProducto());
			pst.setString(3, param.getTipoVigencia());

			result = pst.executeQuery();

			while (result.next()) {
				VigenciaTecnica obj = new VigenciaTecnica();
				obj.setCodigo(result.getString("CODIGO"));
				obj.setDescripcion(result.getString("DESCRIPCION"));

				resultado.setUnItem(obj);
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

	public ResultParentesco listaParentesco(ParamParentesco param) {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaParentesco");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultParentesco resultado = new ResultParentesco();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("select rv_low_value codigo,rv_meaning descripcion ");
			sql.append("from cg_ref_codes ");
			sql.append("where rv_domain = 'PARENTESCO' ");
			sql.append("order by 1");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());

			result = pst.executeQuery();

			while (result.next()) {
				Parentesco obj = new Parentesco();
				obj.setCodigo(result.getString("codigo"));
				obj.setDescripcion(result.getString("descripcion"));

				resultado.setUnElemento(obj);
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

	public ResultXmlPL obtenerBeneficiarios(ParamObtenerBeneficiarios param) {

		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerBeneficiarios");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo del bien", param.getNumConsecutivoBien());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerBeneficiarios");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			cstmt.setInt(2, Integer.valueOf(param.getNumCertificado()));
			cstmt.setInt(3, Integer.valueOf(param.getNumConsecutivoBien()));

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

	public ResultListaObjetos listaObjetos(ParamObjetos param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaObjetos");
		logueo.setParametro("Producto", param.getCodProducto());
		logueo.setParametro("Ramo", param.getCodRamo());
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaObjetos resultado = new ResultListaObjetos();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT CROB_CD_OBJETO, CROB_DE_OBJETO ");
			sql.append("FROM cret_objetos, cret_objetos_ramos ");
			sql.append("WHERE CROB_CD_OBJETO = CROR_CROB_CD_OBJETO ");
			sql.append("AND CROR_CARP_CD_RAMO = ? ");
			sql.append("AND CROR_CAPU_CD_PRODUCTO = ? ");
			sql.append("ORDER BY 2 ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, Integer.valueOf(param.getCodRamo()));
			pst.setString(2, param.getCodProducto());

			result = pst.executeQuery();

			while (result.next()) {
				Objeto obj = new Objeto();
				obj.setCodigo(result.getString("CROB_CD_OBJETO"));
				obj.setDescripcion(result.getString("CROB_DE_OBJETO"));

				resultado.setUnElemento(obj);
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

	public ResultListaMarcasObjeto listaMarcasObjeto(ParamMarcasObjeto param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaMarcasObjeto");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaMarcasObjeto resultado = new ResultListaMarcasObjeto();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT CRMA_CD_MARCA,CRMA_DE_MARCA  ");
			sql.append("FROM CRET_MARCAS ");
			sql.append("ORDER BY 2");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());

			result = pst.executeQuery();

			while (result.next()) {
				MarcaObjeto obj = new MarcaObjeto();
				obj.setCodigo(result.getString("CRMA_CD_MARCA"));
				obj.setDescripcion(result.getString("CRMA_DE_MARCA"));

				resultado.setUnElemento(obj);
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

	public ResultXmlPL obtenerListaBienes(ParamObtenerListaBienes param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerListaBienes");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo del bien", param.getNumConsecutivoBien());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerListaBienesBien");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			cstmt.setInt(2, Integer.valueOf(param.getNumCertificado()));
			cstmt.setInt(3, Integer.valueOf(param.getNumConsecutivoBien()));

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

	public ResultListaGrupos listaGrupos(ParamListaGrupos param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaGrupos");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaGrupos resultado = new ResultListaGrupos();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT RV_LOW_VALUE, RV_MEANING  ");
			sql.append("FROM CG_REF_CODES ");
			sql.append("WHERE RV_DOMAIN = 'CART_RAMOS_POLIZA.GRUPO_RAMOS'");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());

			result = pst.executeQuery();

			while (result.next()) {
				Grupo obj = new Grupo();
				obj.setCodigo(result.getString("RV_LOW_VALUE"));
				obj.setDescripcion(result.getString("RV_MEANING"));

				resultado.setUnElemento(obj);
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
	//REVISADO CON GCANTO, no tiene impacto por BROKERS
	public ResultListaTipoNota listaTipoNota(ParamListaTipoNota param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaTipoNota");
		logueo.setParametro("Circuito", param.getCodCircuito());
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaTipoNota resultado = new ResultListaTipoNota();
		
		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT GENT_CD_TIPO_NOTA, GENT_DE_TIPO_NOTA ");
			sql.append("FROM (SELECT TO_NUMBER(GENT_CD_TIPO_NOTA) GENT_CD_TIPO_NOTA , GENT_DE_TIPO_NOTA ");
			sql.append("FROM GENT_TIPOS_NOTAS ");
			sql.append("WHERE GENT_GECI_CD_CIRCUITO = ? ");
			sql.append("AND GENT_IN_AUTOMATICO = 'N' ");
			sql.append("AND GENT_CD_TIPO_NOTA <> 12 ");
			sql.append("UNION ");
			sql.append("SELECT TO_NUMBER(GENT_CD_TIPO_NOTA) GENT_CD_TIPO_NOTA, GENT_DE_TIPO_NOTA ");
			sql.append("FROM GENT_TIPOS_NOTAS ");
			sql.append("WHERE GENT_GECI_CD_CIRCUITO = ? ");
			sql.append("AND GENT_IN_AUTOMATICO = 'N' ");
			sql.append("AND GENT_CD_TIPO_NOTA = 12 ");
			sql.append("AND ");
			sql.append("PAC_WEB_UTIL.FUN_TIENE_PERMISO(ZPU_GEN_FUNCIONES.B_USER(PAC_WEB_UTIL.FUN_USUARIO_RECTOR('" + param.getUsuario() + "')), ");
			sql.append("43) = 'S' ) ");

			sql.append("ORDER BY  TO_NUMBER(GENT_CD_TIPO_NOTA) ASC");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, Integer.valueOf(param.getCodCircuito()));
			pst.setInt(2, Integer.valueOf(param.getCodCircuito()));

			result = pst.executeQuery();

			while (result.next()) {
				TipoNota obj = new TipoNota();
				obj.setCodigo(result.getString("GENT_CD_TIPO_NOTA"));
				obj.setDescripcion(result.getString("GENT_DE_TIPO_NOTA"));

				resultado.setUnElemento(obj);
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

	public ResultXmlPL obtenerAnexosCotizacion(ParamObtenerAnexosCotizacion param) {

		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerAnexosCotizacion");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_anexosCotizacion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			cstmt.setInt(2, Integer.valueOf(param.getNumCertificado()));

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

	public ResultListaBancos listaBancos(ParamListaBancos param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaBancos");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero persona", param.getNumPersona());
		logueo.setParametro("Codigo medio pago", param.getCodMedioPago());
		logueo.setParametro("Codigo origen pago", param.getCodOrigenPago());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultListaBancos resultado = new ResultListaBancos();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("select CADM_CABA_CD_BANCO, CABA_DE_BANCO, CADM_CATT_TP_TARJETA, COTC_DE_ORIGEN CATT_DE_TARJETA, CADM_NU_CUENTA, CADM_NU_DOMICILIO ");
			sql.append("from cart_domicilios_bancarios, cart_bancos, cobt_tipos_cobranzas ");
			sql.append("where cadm_cabu_nu_persona = ? "); // numPersona
			sql.append("and cadm_tp_cuenta  = (SELECT CTC.COTC_TP_CUENTA ");
			sql.append("						FROM COBT_TIPOS_COBRANZAS CTC ");
			sql.append("						WHERE CTC.COTC_CAMD_CD_MEDIO_PAGO =  ?	 "); // codMedioPago
			sql.append("						AND ROWNUM = 1) ");
			sql.append("and cadm_catt_tp_tarjeta = ? "); // codOrigenPago
			sql.append("and caba_cd_banco = cadm_caba_cd_banco ");
			sql.append("and caba_cd_banco = cotc_caba_cd_banco ");
			sql.append("and cadm_catt_tp_tarjeta = cotc_cd_origen ");
			sql.append("UNION ");
			sql.append("select CADM_CABA_CD_BANCO, CABA_DE_BANCO, CADM_CATT_TP_TARJETA, COTC_DE_ORIGEN CATT_DE_TARJETA, CADM_NU_CUENTA, CADM_NU_DOMICILIO ");
			sql.append("from cart_domicilios_bancarios, cart_bancos, cobt_tipos_cobranzas ");
			sql.append("where cadm_cabu_nu_persona = ? "); // numPersona
			sql.append("and   cadm_tp_cuenta  = (SELECT CTC.COTC_TP_CUENTA ");
			sql.append("FROM COBT_TIPOS_COBRANZAS CTC ");
			sql.append("WHERE CTC.COTC_CAMD_CD_MEDIO_PAGO = ? "); // codMedioPago
			sql.append("AND ROWNUM = 1) ");
			sql.append("and cadm_catt_tp_tarjeta = ? "); // codOrigenPago
			sql.append("and caba_cd_banco = cadm_caba_cd_banco ");
			sql.append("and cadm_tp_cuenta = cotc_tp_cuenta ");
			sql.append("and cadm_catt_tp_tarjeta = cotc_catt_tp_tarjeta ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, Integer.valueOf(param.getNumPersona()));
			pst.setInt(2, Integer.valueOf(param.getCodMedioPago()));
			pst.setString(3, param.getCodOrigenPago());
			pst.setInt(4, Integer.valueOf(param.getNumPersona()));
			pst.setInt(5, Integer.valueOf(param.getCodMedioPago()));
			pst.setString(6, param.getCodOrigenPago());

			result = pst.executeQuery();

			while (result.next()) {
				DatosBanco obj = new DatosBanco();
				obj.setCodBanco(result.getInt("CADM_CABA_CD_BANCO"));
				obj.setDescripcionBanco(result.getString("CABA_DE_BANCO"));
				obj.setTipoTarjeta(result.getString("CADM_CATT_TP_TARJETA"));
				obj.setDescripOrigenTarjeta(result.getString("CATT_DE_TARJETA"));
				obj.setNumCuenta(result.getString("CADM_NU_CUENTA"));
				obj.setNumDomicilio(result.getInt("CADM_NU_DOMICILIO"));

				resultado.setUnDatoBanco(obj);
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

	public ResultXmlPL obtenerComponentes(ParamObtenerComponentes param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerComponentes");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerComponentes");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			cstmt.setNull(2, Types.INTEGER);

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

	public ResultXmlPL obtenerFranquicias(ParamObtenerFranquicias param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerFranquicias");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo del bien", param.getNumConsecutivoBien());
		logueo.setParametro("Codigo bien asegurado", param.getCodBienAsegurado());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerFranquicias");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			cstmt.setInt(2, Integer.valueOf(param.getNumCertificado()));

			if (param.getNumConsecutivoBien() == null) {
				cstmt.setNull(3, Types.INTEGER);
			} else {
				cstmt.setInt(3, Integer.valueOf(param.getNumConsecutivoBien()));
			}

			if (param.getCodBienAsegurado() == null) {
				cstmt.setNull(4, Types.INTEGER);
			} else {
				cstmt.setInt(4, Integer.valueOf(param.getCodBienAsegurado()));
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

	public ResultObtenerTextoClausula obtenerTextoClausula(ParamObtenerTextoClausula param) {
		ResultObtenerTextoClausula resultado = new ResultObtenerTextoClausula();
		Herramientas herramientas = new Herramientas();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("obtenerTextoClausula");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());
		logueo.setParametro("Codigo clausula", param.getCodClausula());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerTextoClausula");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getCodRamo()));
			cstmt.setString(2, param.getCodClausula());

			cstmt.registerOutParameter(3, Types.INTEGER);
			cstmt.registerOutParameter(4, Types.VARCHAR);
			cstmt.registerOutParameter(5, Types.VARCHAR);
			cstmt.registerOutParameter(6, Types.CLOB);

			cstmt.execute();

			resultado.setTextoClausula(herramientas.convertirClob(cstmt.getClob(6)));

			int codError = cstmt.getInt(3);
			String descError = cstmt.getString(4);
			String sqlError = cstmt.getString(5);

			if (codError != 0) {
				ServiciosError error = new ServiciosError();
				error.setCodigo(Integer.valueOf(codError));
				error.setDescripcion(descError);

				resultado.setError(error);
				resultado.setHayError(Boolean.TRUE);
			}

			logueo.setParametro("Codigo salida", codError);
			logueo.setParametro("Descripcion salida", descError);
			logueo.setParametro("Sql salida", sqlError);

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

	public ResultXmlPL listaGenericaXTiposNota(ParamTiposNotaGenerica param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaGenericaXTiposNota");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de tipo nota", param.getCodTipoNota());
		logueo.setParametro("Codigo circuito", param.getCodCircuito());
		logueo.setParametro("Enlace", param.getEnlace());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_listaGenericaXTiposNota");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.setString(1, param.getEnlace());
			cstmt.setString(2, param.getCodCircuito());
			cstmt.setInt(3, Integer.valueOf(param.getCodTipoNota()));

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

	public ResultXmlPL obtenerNotasPoliza(ParamObtenerNotasPoliza param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();

		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerNotasPoliza");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Enlace", param.getEnlace());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerNotasPoliza");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");
			cstmt.setString(1, param.getEnlace());
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

	public ResultXmlPL obtenerDatosRenovacion(ParamObtenerDatosRenovacion param) {

		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerDatosRenovacion");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerDatosRenovacion");
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
	
	//candidata a borrarse , porque no la usa nadie , revisado para BROKER 
	public ResultObtenerClientesSolicitante obtenerClientesSolicitante(ParamObtenerClientesSolicitante param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerClientesSolicitante");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Apellido o razon", param.getApellidoRazon());
		logueo.setParametro("Nombre", param.getNombre());
		logueo.setParametro("Numero cliente", param.getCodCliente());
		logueo.setParametro("Tipo documento", param.getTipoDocumento());
		logueo.setParametro("Numero documento", param.getNumDocumento());
		logueo.setParametro("RUT", param.getRut());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultObtenerClientesSolicitante resultado = new ResultObtenerClientesSolicitante();
		// FIXME OIGRES FALTA BROK EN COD SQL
		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT CACN_CABU_NU_PERSONA, ZPU_GEN_FUNCIONES.FUN_NOMBRE_CLIENTE(CACN_CD_NACIONALIDAD , CACN_NU_CEDULA_RIF) nombre, ");
			sql.append("CACN_CD_NACIONALIDAD, CACN_NU_CEDULA_RIF, CACN_TP_DOCUMENTO, CACN_NU_DOCUMENTO, CACN_CUIT, CACN_FE_BAJA, CACN_IN_ANULACION_CORRIDA, ");
			sql.append("D.CADO_DE_CALLE, D.CADO_DE_NUMERO, D.CADO_DE_DEPARTAMENTO, D.GECP_DE_LOCALIDAD, D.GECP_CD_CODIGO_POSTAL, ");
			sql.append("D.GEMU_DE_MUNICIPIO, D.CAES_DE_ESTADO, D.CAPA_DE_PAIS, CACN_NU_DIA_VTO  ");
			sql.append("FROM CART_CLIENTES, ");
			sql.append("(SELECT DP.CADO_NU_PERSONA, DP.CADO_DE_CALLE, DP.CADO_DE_NUMERO, DP.CADO_DE_DEPARTAMENTO, GECP_DE_LOCALIDAD, GECP_CD_CODIGO_POSTAL, ");
			sql.append("GEMU_DE_MUNICIPIO, CAES_DE_ESTADO, CAPA_DE_PAIS ");
			sql.append("FROM CART_PERSONAS_DIRECCIONES DP, GENT_CODIGOS_POSTALES, CART_ESTADOS, GENT_MUNICIPIOS, CART_PAISES ");
			sql.append("WHERE DP.CADO_CATD_CD_TIPO_DIRECCION = 1 AND GECP_NU_POSTAL = DP.CADO_GECP_NU_POSTAL AND CAES_CD_ESTADO = GECP_CAES_CD_PROVINCIA ");
			sql.append("AND GEMU_CAES_CD_PROVINCIA = GECP_CAES_CD_PROVINCIA AND GEMU_CD_MUNICIPIO = GECP_GEMU_CD_MUNICIPIO AND CAPA_CD_PAIS = ( ");
			sql.append("SELECT TO_NUMBER(GEPA_NM_VALOR_CAMPO) FROM GENT_PARAMETROS WHERE GEPA_NM_CAMPO = 'PAIS_LOCAL') AND DP.CADO_CONSECUTIVO_DIRECCION = ");
			sql.append("(SELECT MAX(CADO_CONSECUTIVO_DIRECCION) FROM CART_PERSONAS_DIRECCIONES D WHERE D.CADO_NU_PERSONA = DP.CADO_NU_PERSONA AND D.CADO_CATD_CD_TIPO_DIRECCION = 1)) D ");
			sql.append("WHERE CACN_CD_NACIONALIDAD != 'C' AND D.CADO_NU_PERSONA (+) = CACN_CABU_NU_PERSONA AND ");
			sql.append("(NOT EXISTS (SELECT '1' FROM CART_USUARIOS WHERE CAUS_CD_USUARIO = ");
			sql.append("ZPU_GEN_FUNCIONES.B_USER(USER) AND CAUS_CAPD_CD_PRODUCTOR IS NOT NULL) OR ");
			sql.append("EXISTS (SELECT '1' FROM CART_CERTIFICADOS WHERE CACE_CACN_CD_NACIONALIDAD=CACN_CD_NACIONALIDAD AND CACE_CACN_NU_CEDULA_RIF=CACN_NU_CEDULA_RIF ");
			sql.append("AND CACE_CAPD_CD_PRODUCTOR = (SELECT CAUS_CAPD_CD_PRODUCTOR FROM CART_USUARIOS WHERE CAUS_CD_USUARIO = ");
			sql.append("(SELECT COUM_CD_USU_RECTOR FROM COTW_USUARIOS_MAPPING WHERE COUM_CD_USU_DOMINIO = ?))) ");
			sql.append("OR CACN_CABU_NU_PERSONA = (SELECT PROD.CAPD_CABU_NU_PERSONA FROM CART_ROL_PRODUCTORES PROD, CART_USUARIOS USU ");
			sql.append("WHERE PROD.CAPD_CD_PRODUCTOR = USU.CAUS_CAPD_CD_PRODUCTOR AND CAUS_CD_USUARIO = ");
			sql.append("(SELECT COUM_CD_USU_RECTOR FROM COTW_USUARIOS_MAPPING WHERE COUM_CD_USU_DOMINIO = ? )) ");
			sql.append("OR EXISTS  (SELECT 1 FROM CART_USUARIOS WHERE CAUS_CD_USUARIO =  ");
			sql.append("(SELECT COUM_CD_USU_RECTOR FROM COTW_USUARIOS_MAPPING WHERE COUM_CD_USU_DOMINIO = ?) AND CAUS_CJDP_CD_DEPARTAMENTO = ");
			sql.append("(SELECT GEPA_NM_VALOR_CAMPO FROM GENT_PARAMETROS WHERE GEPA_NM_CAMPO='PRODUCTOR_TOTAL_CLIENTES'))) ");

			if (param.getApellidoRazon() != null) {
				sql.append("AND CACN_NM_APELLIDO_RAZON = ? ");
			}

			if (param.getNombre() != null) {
				sql.append("AND  CACN_NM_PERSONA_NATURAL = ? ");
			}

			if (param.getCodCliente() != null) {
				sql.append("AND CACN_NU_CEDULA_RIF = ? ");
			}
			if (param.getTipoDocumento() != null) {
				sql.append("AND CACN_TP_DOCUMENTO = ? ");
			}
			if (param.getNumDocumento() != null) {
				sql.append("AND CACN_NU_DOCUMENTO = ? ");
			}
			if (param.getRut() != null) {
				sql.append("AND CACN_CUIT  = ? ");
			}

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());

			pst.setString(1, param.getUsuario());
			pst.setString(2, param.getUsuario());
			pst.setString(3, param.getUsuario());

			Integer i = 3;

			if (param.getApellidoRazon() != null) {
				i++;
				pst.setString(i, param.getApellidoRazon());
			}

			if (param.getNombre() != null) {
				i++;
				pst.setString(i, param.getNombre());
			}

			if (param.getCodCliente() != null) {
				i++;
				pst.setInt(i, Integer.valueOf(param.getCodCliente()));
			}

			if (param.getTipoDocumento() != null) {
				i++;
				pst.setString(i, param.getTipoDocumento());
			}

			if (param.getNumDocumento() != null) {
				i++;
				pst.setInt(i, Integer.valueOf(param.getNumDocumento()));
			}

			if (param.getRut() != null) {
				i++;
				pst.setString(i, param.getRut());
			}

			result = pst.executeQuery();

			while (result.next()) {
				Cliente obj = new Cliente();
				DireccionEC dir = new DireccionEC();
				obj.setNumPersona(result.getString("CACN_CABU_NU_PERSONA"));
				obj.setNombreCompleto(result.getString("nombre"));
				obj.setNacionalidad(result.getString("CACN_CD_NACIONALIDAD"));
				obj.setCodCliente(result.getString("CACN_NU_CEDULA_RIF"));
				obj.setTipoDoc(result.getString("CACN_TP_DOCUMENTO"));
				obj.setNumDoc(result.getString("CACN_NU_DOCUMENTO"));
				obj.setRut(result.getString("CACN_CUIT"));
				obj.setFechaBaja(result.getString("CACN_FE_BAJA"));
				obj.setAnulacionCorrida(result.getString("CACN_IN_ANULACION_CORRIDA"));
				if (result.getString("CACN_NU_DIA_VTO") != null) {
					obj.setDiaVencimiento(result.getInt("CACN_NU_DIA_VTO"));
				}
				dir.setDireccion(result.getString("CADO_DE_CALLE"));
				dir.setNumeroPuerta(result.getString("CADO_DE_NUMERO"));
				dir.setCodDepto(result.getInt("CADO_DE_DEPARTAMENTO"));
				dir.setDescripRadio(result.getString("GECP_DE_LOCALIDAD"));
				dir.setNumPostal(result.getInt("GECP_CD_CODIGO_POSTAL"));
				dir.setDescripLocalidad(result.getString("GEMU_DE_MUNICIPIO"));
				dir.setDescripDepto(result.getString("CAES_DE_ESTADO"));
				dir.setDescripPais(result.getString("CAPA_DE_PAIS"));

				obj.setDireccionCompleta(dir);
				resultado.setUnClienteSolicitante(obj);
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

	public ResultXmlPL obtenerPlanesPago(ParamObtenerPlanesPago param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerPlanesPago");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerPlanesPago");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			cstmt.setInt(2, Integer.valueOf(param.getNumCertificado()));
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

	public ResultXmlPL obtenerDatosModificacion(ParamDatosModificacion param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerDatosModificacion");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo", param.getNumConsecutivoBien());

		PropertiesManager properties = new PropertiesManager();
		properties.setNombreArchivo(SERVRECTPROP);

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerDatosModificacion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");
			cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			if (param.getNumCertificado() != null) {
				cstmt.setInt(2, Integer.valueOf(param.getNumCertificado()));
			} else {
				cstmt.setNull(2, Types.INTEGER);
			}

			if (param.getNumConsecutivoBien() != null) {
				cstmt.setInt(3, Integer.valueOf(param.getNumConsecutivoBien()));
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

	public ResultTipoTexto listaTipoTexto(ParamTipoTexto param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaTipoTexto");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultTipoTexto resultado = new ResultTipoTexto();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer(230);
			sql.append("SELECT CRTX_CD_TEXTO, CRTX_TITULO_TEXTO ");
			sql.append("FROM CRET_TEXTOS WHERE CRTX_CARP_CD_RAMO= ? ");
			sql.append("ORDER BY CRTX_CD_TEXTO ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, param.getCodRamo());

			result = pst.executeQuery();

			while (result.next()) {
				TipoTexto obj = new TipoTexto();
				obj.setCodigo(result.getInt("CRTX_CD_TEXTO"));
				obj.setDescripcion(result.getString("CRTX_TITULO_TEXTO"));

				resultado.setUnTipoTexto(obj);
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

	public ResultXmlPL obtenerTextosCotizacion(ParamTextosCotizacion param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerTextosCotizacion");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerTextosCotizacion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");

			if (param.getNumCotizacion() != null) {
				cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			}

			if (param.getNumCertificado() != null) {
				cstmt.setInt(2, Integer.valueOf(param.getNumCertificado()));
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

	public ResultDetalleTextoCotizacion obtenerDetalleTextoCotizacion(ParamDetalleTextoCotizacion param) {
		ResultDetalleTextoCotizacion resultado = new ResultDetalleTextoCotizacion();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotOperaciones.class);
		logueo.setMetodo("obtenerDetalleTextoCotizacion");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo del texto", param.getNumConsecutivoTexto());
		logueo.setParametro("Codigo texto", param.getCodTexto());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerDetalleTextoCotizacion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");

			if (param.getNumCotizacion() != null) {
				cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			}

			if (param.getNumCertificado() != null) {
				cstmt.setInt(2, Integer.valueOf(param.getNumCertificado()));
			}

			if (param.getNumConsecutivoTexto() != null) {
				cstmt.setInt(3, Integer.valueOf(param.getNumConsecutivoTexto()));
			}

			if (param.getCodTexto() != null) {
				cstmt.setInt(4, Integer.valueOf(param.getCodTexto()));
			}

			cstmt.registerOutParameter(5, Types.INTEGER);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.registerOutParameter(7, Types.VARCHAR);
			cstmt.registerOutParameter(8, Types.VARCHAR);

			cstmt.execute();

			resultado.setDetalleTexto(cstmt.getString(8));

			int codError = cstmt.getInt(5);
			String descError = cstmt.getString(6);
			String sqlError = cstmt.getString(7);

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

	public ResultXmlPL obtenerEncabezadoCotizacion(ParamEncabezadoCotizacion param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerEncabezadoCotizacion");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo del bien", param.getNumConsecutivoBien());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerEncabezadoCotizacion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");

			if (param.getNumCotizacion() != null) {
				cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			}

			if (param.getNumCertificado() != null) {
				cstmt.setInt(2, Integer.valueOf(param.getNumCertificado()));
			}

			if (param.getNumConsecutivoBien() != null) {
				cstmt.setInt(3, Integer.valueOf(param.getNumConsecutivoBien()));
			}

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

	public ResultAcreedor listaAcreedores(ParamAcreedor param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaAcreedores");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultAcreedor resultado = new ResultAcreedor();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ALL CART_ACREEDORES.CAAC_CD_ACREEDOR, ");
			sql.append("CART_ACREEDORES.CAAC_DE_ACREEDOR, CART_ACREEDORES.CACC_CABU_NU_PERSONA ");
			sql.append("FROM CART_ACREEDORES ");
			sql.append("ORDER BY 1 ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());

			result = pst.executeQuery();

			while (result.next()) {
				Acreedor item = new Acreedor();

				item.setCodigo(result.getInt("CAAC_CD_ACREEDOR"));
				item.setNombreAcreedor(result.getString("CAAC_DE_ACREEDOR"));
				item.setNumPersona(result.getInt("CACC_CABU_NU_PERSONA"));

				resultado.setUnAcreedor(item);
			}

			logueo.setParametro("CANTIDAD ACREEDORES", resultado.getAcreedores().size());
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

	public ResultAcreedor listaAcreedoresFiltrados(ParamAcreedoresFiltrados param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaAcreedoresFiltrados");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("FiltroNombre", param.getFiltroNombre());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultAcreedor resultado = new ResultAcreedor();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ALL CART_ACREEDORES.CAAC_CD_ACREEDOR, ");
			sql.append("CART_ACREEDORES.CAAC_DE_ACREEDOR, CART_ACREEDORES.CACC_CABU_NU_PERSONA ");
			sql.append("FROM CART_ACREEDORES WHERE CAAC_DE_ACREEDOR LIKE ? ");
			sql.append("ORDER BY 1 ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			if (param.getFiltroNombre() != null) {
				pst.setString(1, "%" + param.getFiltroNombre().toUpperCase() + "%");
			} else {
				pst.setNull(1, Types.VARCHAR);
			}

			result = pst.executeQuery();

			while (result.next()) {
				Acreedor item = new Acreedor();

				item.setCodigo(result.getInt("CAAC_CD_ACREEDOR"));
				item.setNombreAcreedor(result.getString("CAAC_DE_ACREEDOR"));
				item.setNumPersona(result.getInt("CACC_CABU_NU_PERSONA"));

				resultado.setUnAcreedor(item);
			}

			logueo.setParametro("CANTIDAD ACREEDORES", resultado.getAcreedores().size());
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

	public ResultAcreedorObjetos listaAcreedorObjetos(ParamAcreedorObjetos param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaAcreedorObjetos");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero de cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero de certificado", param.getNumCertificado());
		logueo.setParametro("Codigo del bien del asegurado", param.getCodBienAsegurado());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultAcreedorObjetos resultado = new ResultAcreedorObjetos();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT 0 CRCA_CROB_CD_OBJETO, 'BIEN ASEGURADO' CRCA_DESCRIPCION ");
			sql.append("FROM DUAL ");
			sql.append("UNION ");
			sql.append("SELECT CRCA_CROB_CD_OBJETO, CRCA_DESCRIPCION ");
			sql.append("FROM   CRET_COTIZA_LISTA_BIENES ");
			sql.append("WHERE  CRCA_CAZB_NU_COTIZACION  = ? "); // numCotizacion
			sql.append("AND CRCA_CAZB_NU_CONSECUTIVO = ? "); // numCertificado
			sql.append("AND CRCA_CRCI_CD_BIEN_ASEG  = ? "); // codBienAsegurado

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());

			pst.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			pst.setInt(2, Integer.valueOf(param.getNumCertificado()));
			pst.setInt(3, Integer.valueOf(param.getCodBienAsegurado()));

			result = pst.executeQuery();

			while (result.next()) {
				AcreedorObjetos item = new AcreedorObjetos();

				item.setCodigo(result.getInt("CRCA_CROB_CD_OBJETO"));
				item.setDescripcion(result.getString("CRCA_DESCRIPCION"));

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

	public ResultTipoAcreedores listaTipoAcreedores(ParamTipoAcreedores param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaTipoAcreedores");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultTipoAcreedores resultado = new ResultTipoAcreedores();

		try {
			conn = crearConexion();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT RV_LOW_VALUE, RV_MEANING ");
			sql.append("FROM CG_REF_CODES ");
			sql.append("WHERE RV_DOMAIN = 'CART_ACREEDORES.CAAC_TP_ACREEDOR' ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());

			result = pst.executeQuery();

			while (result.next()) {
				TipoAcreedor item = new TipoAcreedor();

				item.setTipo(result.getString("RV_LOW_VALUE"));
				item.setDescripcion(result.getString("RV_MEANING"));

				resultado.setUnTipo(item);
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

	public ResultXmlPL obtenerAcreedoresXBien(ParamObtenerAcreedoresXBien param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerAcreedoresXBien");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Numero consecutivo del bien", param.getNumConsecutivoBien());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerAcreedoresXBien");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");

			if (param.getNumCotizacion() != null) {
				cstmt.setInt(1, Integer.valueOf(param.getNumCotizacion()));
			}

			if (param.getNumCertificado() != null) {
				cstmt.setInt(2, Integer.valueOf(param.getNumCertificado()));
			}

			if (param.getNumConsecutivoBien() != null) {
				cstmt.setInt(3, Integer.valueOf(param.getNumConsecutivoBien()));
			}

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

	public ResultXmlPL obtenerDatosPreviosEndoso(ParamObtenerDatosPreviosEndoso param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerDatosPreviosEndoso");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerDatosPreviosEndoso");
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

	public ResultXmlPL obtenerDomiciliosCliente(ParamObtenerDomiciliosCliente param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerDomiciliosCliente");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero persona", param.getNumPersona());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerDomiciliosCliente");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getNumPersona());
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

	public ResultXmlPL obtenerContratanteAsegurado(ParamObtenerContratanteAsegurado param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerContratanteAsegurado");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Apellido Razon Social", param.getApellidoRazonSocial());
		logueo.setParametro("Numero documento", param.getNumDocumento());
		logueo.setParametro("Rut", param.getRut());
		logueo.setParametro("Tipo documento", param.getTipoDocumento());
		logueo.setParametro("Nombre", param.getNombre());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerContratanteAsegurado");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?)}");

			if (param.getTipoDocumento() != null) {
				cstmt.setString(1, param.getTipoDocumento());
			} else {
				cstmt.setNull(1, Types.VARCHAR);
			}
			if (param.getNumDocumento() != null) {
				cstmt.setString(2, param.getNumDocumento());
			} else {
				cstmt.setNull(2, Types.INTEGER);
			}
			if (param.getNombre() != null) {
				cstmt.setString(3, param.getNombre());
			} else {
				cstmt.setNull(3, Types.VARCHAR);
			}
			if (param.getApellidoRazonSocial() != null) {
				cstmt.setString(4, param.getApellidoRazonSocial());
			} else {
				cstmt.setNull(4, Types.VARCHAR);
			}
			if (param.getRut() != null) {
				cstmt.setString(5, param.getRut());
			} else {
				cstmt.setNull(5, Types.VARCHAR);
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
		} catch (SQLException e) {
			catchSQLException(resultado, logueo, e);
		} catch (Exception ex) {
			catchException(resultado, logueo, ex);
		} finally {
			liberarRecursos(conn, cstmt);
		}
		return resultado;
	}

	public ResultXmlPL obtenerPersonaSeleccionada(ParamPersonaSeleccionada param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerPersonaSeleccionada");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero persona", param.getNumPersona());
		logueo.setParametro("Tipo cliente", param.getTipoCliente());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerPersonaSeleccionada");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");

			if (param.getNumPersona() != null) {
				cstmt.setInt(1, param.getNumPersona());
			}

			if (param.getTipoCliente() != null) {
				cstmt.setString(2, param.getTipoCliente());
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

	public ResultDatosXTipoNota listaDatosXTipoNota(ParamDatosXTipoNota param) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaDatosXTipoNota");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo tipo nota", param.getCodTipoNota());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultDatosXTipoNota resultado = new ResultDatosXTipoNota();

		try {
			conn = crearConexion();

			final StringBuffer sql = new StringBuffer(230);
			sql.append("SELECT GENT_DE_ETIQUETA1 FROM GENT_TIPOS_NOTAS WHERE GENT_GECI_CD_CIRCUITO = '4' AND ");
			sql.append("GENT_CD_TIPO_NOTA = ? AND GENT_DE_ETIQUETA1 IS NOT NULL UNION ALL ");
			sql.append("SELECT GENT_DE_ETIQUETA2 FROM GENT_TIPOS_NOTAS WHERE GENT_GECI_CD_CIRCUITO = '4' AND ");
			sql.append("GENT_CD_TIPO_NOTA = ? AND GENT_DE_ETIQUETA2 IS NOT NULL");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());

			pst.setInt(1, param.getCodTipoNota());
			pst.setInt(2, param.getCodTipoNota());

			result = pst.executeQuery();

			while (result.next()) {
				String obj = new String();
				obj = result.getString("GENT_DE_ETIQUETA1");

				resultado.setUnElemento(obj);
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

	public ResultXmlPL listaValoresTipoNota(ParamValoresTipoNota param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaValoresTipoNota");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Circuito", param.getCircuito());
		logueo.setParametro("Valor", param.getCodValor());
		logueo.setParametro("Enlace", param.getEnlace());
		logueo.setParametro("Tipo de nota", param.getTipoNota());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_listaValoresTipoNota");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");

			cstmt.setString(1, param.getEnlace());
			cstmt.setString(2, param.getCircuito());
			cstmt.setString(3, param.getTipoNota());
			cstmt.setString(4, param.getCodValor());

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

	public ResultXmlPL obtenerPlanesCobertura(ParamObtenerPlanesCobertura param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerPlanesCobertura");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Requerido", param.getRequerido());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerPlanesCobertura");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getNumCotizacion());
			cstmt.setInt(2, param.getNumCertificado());
			cstmt.setString(3, param.getRequerido());
			cstmt.setString(4, param.getRequeridoPlanPago());
			cstmt.setString(5, param.getUsuario());
			cstmt.setNull(9, Types.CLOB);

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

	public ResultXmlPL obtenerDetalleCuotas(ParamObtenerDetalleCuota param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerDetalleCuotas");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerDetalleCuota");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getNumCotizacion());
			cstmt.setInt(2, param.getNumCertificado());
			cstmt.setString(3, param.getCodPlanPago());
			if (param.getDiaVto() != null) {
				cstmt.setInt(4, param.getDiaVto());
			} else {
				cstmt.setNull(4, Types.INTEGER);
			}
			cstmt.setDouble(5, param.getMontoPrima());
			cstmt.setDouble(6, param.getMontoPremio());
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

	public ResultXmlPL obtenerResumenPlanesCobertura(ParamObtenerResumenPlanesCobertura param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerResumenPlanesCobertura");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero Cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerResumenPlanesCobertura");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getNumCotizacion());
			if (param.getNumCertificado() != null) {
				cstmt.setInt(2, param.getNumCertificado());
			} else {
				cstmt.setNull(2, Types.INTEGER);
			}

			cstmt.setString(3, param.getUsuario());
			cstmt.setNull(7, Types.CLOB);

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

	public ResultRenovacionXProducto obtenerRenovacionXProducto(ParamRenovacionXProducto param) {
		CallableStatement cstmt = null;
		Connection conn = null;

		Logueo logueo = new Logueo();
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerRenovacionXProducto");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo producto", param.getCodProducto());
		logueo.setParametro("Codigo ramo", param.getCodRamo());

		ResultRenovacionXProducto resultado = new ResultRenovacionXProducto();

		try {
			conn = crearConexion();
			String nombrePL = obtenerValor("fun_obtenerRenovacionXProducto");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{? = call " + nombrePL + "(?,?,?,?,?,?)}");

			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.setInt(2, param.getCodRamo());
			cstmt.setString(3, param.getCodProducto());
			cstmt.setString(4, param.getUsuario());

			cstmt.registerOutParameter(1, Types.VARCHAR);
			cstmt.registerOutParameter(5, Types.INTEGER);
			cstmt.registerOutParameter(6, Types.VARCHAR);
			cstmt.registerOutParameter(7, Types.VARCHAR);

			cstmt.execute();

			String tipo = cstmt.getString(1);
			resultado.setTipoRenovacion(tipo);

			int codError = cstmt.getInt(5);
			String descError = cstmt.getString(6);
			String sqlError = cstmt.getString(7);

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

	public ResultXmlPL obtenerCoberturasXBien(ParamObtenerCoberturasXBien param) {
		CallableStatement cstmt = null;
		Connection conn = null;

		Logueo logueo = new Logueo();
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerCoberturasXBien");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());
		logueo.setParametro("Consecutivo Bien", param.getConsecutivoBien());
		logueo.setParametro("Cotizando", param.getCotizando());

		ResultXmlPL resultado = new ResultXmlPL();

		try {
			conn = crearConexion();
			String nombrePL = obtenerValor("proc_coberturas_cotizacion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getNumCotizacion());
			cstmt.setInt(2, param.getNumCertificado());
			cstmt.setInt(3, param.getConsecutivoBien());
			if ("S".equalsIgnoreCase(param.getCotizando())) {
				cstmt.setString(4, param.getCotizando());
			} else {
				cstmt.setString(4, "N");
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

	public ResultXmlPL obtenerCoberturasXCertificado(ParamObtenerCoberturasXCertificado param) {
		CallableStatement cstmt = null;
		Connection conn = null;

		Logueo logueo = new Logueo();
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerCoberturasXCertificado");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero cotizacion", param.getNumCotizacion());
		logueo.setParametro("Numero certificado", param.getNumCertificado());

		ResultXmlPL resultado = new ResultXmlPL();

		try {
			conn = crearConexion();
			String nombrePL = obtenerValor("proc_obtenerCoberturasXCertificado");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getNumCotizacion());
			cstmt.setInt(2, param.getNumCertificado());
			cstmt.setInt(3, param.getNumBien());
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

	public ResultXmlPL obtenerDatosBancarios(ParamObtenerDatosBancarios param) {
		CallableStatement cstmt = null;
		Connection conn = null;

		Logueo logueo = new Logueo();
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerDatosBancarios");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo cliente", param.getCodCliente());
		logueo.setParametro("Codigo medio pago", param.getCodMedioPago());
		logueo.setParametro("Codigo origen", param.getCodOrigen());

		ResultXmlPL resultado = new ResultXmlPL();

		try {
			conn = crearConexion();
			String nombrePL = obtenerValor("proc_obtenerDatosBancarios");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?)}");

			cstmt.setInt(1, param.getCodMedioPago());
			cstmt.setString(2, param.getCodOrigen());
			cstmt.setInt(3, param.getCodCliente());
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

	public ResultObtenerFechaEmision obtenerFechaEmision(ParamObtenerFechaEmision param) {
		CallableStatement cstmt = null;
		Connection conn = null;

		Logueo logueo = new Logueo();
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerFechaEmision");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultObtenerFechaEmision resultado = new ResultObtenerFechaEmision();

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

			String fecha = cstmt.getString(1);
			resultado.setFechaEmision(fecha);

			int codError = cstmt.getInt(3);
			String descError = cstmt.getString(4);
			String sqlError = cstmt.getString(5);

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

	//lista de niveles de comision del broker 
	
	public ResultCodiguera listaNivelesComision(final ParamNivelesComision param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaNivelesComision");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());
		logueo.setParametro("Codigo de producto", param.getCodProducto());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultCodiguera resultado = new ResultCodiguera();

		try {
			conn = crearConexion();

			final StringBuffer sql = new StringBuffer(280);

			sql.append("SELECT DISTINCT CANO_NIVEL CODIGO_NIVEL, CANO_DE_NIVEL DESC_NIVEL ");
			sql.append("FROM CART_NIVELES_organizador WHERE CANO_CARP_CD_RAMO = ? ");
			sql.append("and cano_capu_cd_producto= ? ");
			sql.append("ORDER BY CODIGO_NIVEL, CANO_DE_NIVEL ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, param.getCodRamo());
			pst.setString(2, param.getCodProducto());
			result = pst.executeQuery();

			while (result.next()) {
				final Codiguera obj = new Codiguera();
				obj.setCodigo(result.getString("CODIGO_NIVEL"));
				obj.setDescripcion(result.getString("DESC_NIVEL"));
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
	
	//lista niveles de comision de productor 
	public ResultCodiguera listaNivelesComisionProd(final ParamNivelesComisionProd param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("listaNivelesComisionProd");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());
		logueo.setParametro("Codigo de producto", param.getCodProducto());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultCodiguera resultado = new ResultCodiguera();

		try {
			conn = crearConexion();

			final StringBuffer sql = new StringBuffer(280);

			sql.append("SELECT DISTINCT CANI_NIVEL CODIGO_NIVEL, CANI_DE_NIVEL DESC_NIVEL ");
			sql.append("FROM CART_NIVELES WHERE CANI_CARP_CD_RAMO = ? ");
			sql.append("AND CANI_CAPU_CD_PRODUCTO = ? ");
			sql.append("ORDER BY CODIGO_NIVEL, CANI_NIVEL");


			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setInt(1, param.getCodRamo());
			pst.setString(2, param.getCodProducto());
			result = pst.executeQuery();

			while (result.next()) {
				final Codiguera obj = new Codiguera();
				obj.setCodigo(result.getString("CODIGO_NIVEL"));
				obj.setDescripcion(result.getString("DESC_NIVEL"));
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


	public ResultXmlPL listaPlanesPagoRenovacion(ParamListaPlanesPagoRen param) {
		ResultXmlPL resultado = new ResultXmlPL();

		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ServiciosPolizasPersist.class);
		logueo.setMetodo("listaPlanesPagoRenovacion");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Numero de certificado", param.getNumCertificado());
		logueo.setParametro("Numero de poliza", param.getNumPoliza());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());
		logueo.setParametro("Numero de endoso", param.getNumEndoso());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_listaPlanesPagoRenovacion");
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
}
