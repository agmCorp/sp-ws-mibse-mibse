package uy.com.bse.reportes.persistencia;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.consultas.persistencia.ConsultasPersist;
import uy.com.bse.cotizaciones.persistencia.CotizacionesPersist;
import uy.com.bse.maestro.persistencia.ServiciosRector;
import uy.com.bse.reportes.ParamListaReportesResumenEmision;
import uy.com.bse.reportes.ParamObtenerClientesCorredores;
import uy.com.bse.reportes.ParamObtenerConsultasExportables;
import uy.com.bse.reportes.ParamObtenerFacturasDelProductorProximasAVencer;
import uy.com.bse.reportes.ParamObtenerFacturasDelProductorProximasAVencerEn12Dias;
import uy.com.bse.reportes.ParamObtenerIncentivoPlusDetalle;
import uy.com.bse.reportes.ParamObtenerIncentivoPlusTotales;
import uy.com.bse.reportes.ParamObtenerListadoControlADTBPSParaProductor;
import uy.com.bse.reportes.ParamObtenerLogsUsuariosProductor;
import uy.com.bse.reportes.ParamObtenerNombreFicheros;
import uy.com.bse.reportes.ParamObtenerNuevaTarifaIncendioProductor;
import uy.com.bse.reportes.ParamObtenerPolizasAdheridasDebitoAutomatico;
import uy.com.bse.reportes.ParamObtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores;
import uy.com.bse.reportes.ParamObtenerPolizasSinMatriculasXProductores;
import uy.com.bse.reportes.ParamObtenerResumenEmision;
import uy.com.bse.reportes.ParamObtenerRutaDescargaUsuario;
import uy.com.bse.reportes.ParamObtenerSeguroAutomovil;
import uy.com.bse.reportes.ParamObtenerTotalesResumenEmision;
import uy.com.bse.reportes.ResultObtenerClientesCorredores;
import uy.com.bse.reportes.ResultObtenerFacturasDelProductorProximasAVencer;
import uy.com.bse.reportes.ResultObtenerFacturasDelProductorProximasAVencerEn12Dias;
import uy.com.bse.reportes.ResultObtenerIncentivoPlusDetalle;
import uy.com.bse.reportes.ResultObtenerIncentivoPlusTotales;
import uy.com.bse.reportes.ResultObtenerListadoControlADTBPSParaProductor;
import uy.com.bse.reportes.ResultObtenerLogsUsuariosProductor;
import uy.com.bse.reportes.ResultObtenerNombreFicheros;
import uy.com.bse.reportes.ResultObtenerNuevaTarifaIncendioProductor;
import uy.com.bse.reportes.ResultObtenerPolizasAdheridasDebitoAutomatico;
import uy.com.bse.reportes.ResultObtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores;
import uy.com.bse.reportes.ResultObtenerPolizasSinMatriculasXProductores;
import uy.com.bse.reportes.ResultObtenerRutaDescargaUsuario;
import uy.com.bse.reportes.ResultObtenerSeguroAutomovil;
import uy.com.bse.reportes.entidades.DatoFichero;
import uy.com.bse.reportes.entidades.ReporteADTBPS;
import uy.com.bse.reportes.entidades.ReporteClienteCorredor;
import uy.com.bse.reportes.entidades.ReporteFactura;
import uy.com.bse.reportes.entidades.ReporteIncentivoPlusDetalle;
import uy.com.bse.reportes.entidades.ReporteIncentivoPlusTotal;
import uy.com.bse.reportes.entidades.ReporteLogsUsuariosProductor;
import uy.com.bse.reportes.entidades.ReporteNuevaTarifaIncendioProductor;
import uy.com.bse.reportes.entidades.ReportePolizaSinMatricula;
import uy.com.bse.reportes.entidades.ReportePolizasAdheridasDebitoAutomatico;
import uy.com.bse.reportes.entidades.ReportePolizasParaRehabilitar;
import uy.com.bse.reportes.entidades.ReporteSeguroAutomovil;
import uy.com.bse.utilitario.dato.Codiguera;
import uy.com.bse.utilitario.dato.ResultCodiguera;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.excepcion.ErrorResolver;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.util.ValuesUtils;

public class ConsultaReportesPersist extends ServiciosRector {

	private final class DatoFicheroComparator implements Comparator<DatoFichero> {
		public int compare(DatoFichero o1, DatoFichero o2) {
			if (o1.getUltimaModificacion() != null && o2.getUltimaModificacion() != null) {
				return o2.getUltimaModificacion().compareTo(o1.getUltimaModificacion());
			} else {
				// FIXME OIGRES, SI NO TIENE ALGUNO... ASUMO SON IGUALES // NO
				// DEBERIA PASAR
				return 0;
			}
		}
	}

	private static Logger LOG = LogManager.getLogger(ConsultaReportesPersist.class);

	public ResultObtenerFacturasDelProductorProximasAVencerEn12Dias obtenerFacturasDelProductorProximasAVencerEn12Dias(final ParamObtenerFacturasDelProductorProximasAVencerEn12Dias param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ConsultaReportesPersist.class);
		logueo.setMetodo("obtenerFacturasDelProductorProximasAVencerEn12Dias");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultObtenerFacturasDelProductorProximasAVencerEn12Dias resultado = new ResultObtenerFacturasDelProductorProximasAVencerEn12Dias();

		try {
			conn = crearConexion();

			final StringBuffer sql = new StringBuffer(230);
			sql.append("WITH FACTURAS AS ");
			sql.append("( ");
			sql.append("SELECT F.CAFC_NU_FACTURA, F.CAFC_FE_SEGUNDO_VTO, F.CAFC_CAMO_CD_MONEDA ");
			sql.append("FROM CART_FACTURAS F ");
			sql.append("WHERE F.CAFC_ST_ESTADO = 1 ");
			sql.append("AND ((F.CAFC_FE_SEGUNDO_VTO) BETWEEN (SYSDATE -1) AND (SYSDATE + 12))) ");

			sql.append("SELECT Q.PRODUCTOR,Q.FACTURA,Q.RAMO, Q.POLIZA, Q.CERTIFICADO, Q.APELLIDO_RAZON_SOCIAL, Q.MONEDA, Q.IMPORTE, Q.SDO_VENCIMIENTO, Q.CUOTA, Q.PRODUCTO, Q.MEDIO_PAGO ");
			sql.append("FROM  ");
			sql.append("(SELECT C.CACE_CAPD_CD_PRODUCTOR PRODUCTOR,  ");
			sql.append("F.CAFC_NU_FACTURA FACTURA, ");
			sql.append("R.CARE_CARP_CD_RAMO RAMO, ");
			sql.append("R.CARE_CAPO_NU_POLIZA POLIZA, ");
			sql.append("R.CARE_CACE_NU_CERTIFICADO CERTIFICADO, ");
			sql.append("MP.CABU_NM_APELLIDO_RAZON ||' '||MP.CABU_NM_PERSONA  APELLIDO_RAZON_SOCIAL, ");
			sql.append("F.CAFC_CAMO_CD_MONEDA MONEDA, ");
			sql.append("FC.CAFT_MT_COMPONENTE IMPORTE, ");
			sql.append("TO_CHAR(F.CAFC_FE_SEGUNDO_VTO,'DD/MM/YYYY') SDO_VENCIMIENTO, r.care_nu_consecutivo_cuota CUOTA, c.cace_capu_cd_producto PRODUCTO, ");

			sql.append("(SELECT CAMD_DE_MEDIO_PAGO  FROM CART_MEDIO_PAGO WHERE CAMD_CD_MEDIO_PAGO = C.CACE_CAMD_CD_MEDIO_PAGO) MEDIO_PAGO ");

			sql.append("FROM RECTOR.CART_CERTIFICADOS C, ");
			sql.append("FACTURAS F,  ");
			sql.append("RECTOR.CART_FACTURAS_COMPONENTES FC,  ");
			sql.append("RECTOR.CART_FACTURAS_RECIBOS FR,  ");
			sql.append("RECTOR.CART_MAE_PERSONAS MP,  ");
			sql.append("RECTOR.CART_RECIBOS R, ");
			sql.append("RECTOR.CART_ROL_CLIENTES RC, ");
			sql.append("CART_USUARIOS D ");
			sql.append("WHERE F.CAFC_NU_FACTURA = FR.CAFB_CAFC_NU_FACTURA ");
			sql.append("AND FR.CAFB_CARE_NU_RECIBO = R.CARE_NU_RECIBO ");
			sql.append("AND R.CARE_CACN_NU_CEDULA_RIF = RC.CACN_NU_CEDULA_RIF ");
			sql.append("AND RC.CACN_CABU_NU_PERSONA = MP.CABU_NU_PERSONA ");
			sql.append("AND F.CAFC_NU_FACTURA = FC.CAFT_CAFC_NU_FACTURA ");
			sql.append("AND R.CARE_CARP_CD_RAMO = C.CACE_CARP_CD_RAMO ");
			sql.append("AND R.CARE_CAPO_NU_POLIZA = C.CACE_CAPO_NU_POLIZA ");
			sql.append("AND R.CARE_CACE_NU_CERTIFICADO = C.CACE_NU_CERTIFICADO ");
			sql.append("AND FC.CAFT_CAPP_CD_COMPONENTE='PRFAC' ");
			sql.append("AND C.CACE_ST_CERTIFICADO<>11 ");
			sql.append("AND C.CACE_CAMD_CD_MEDIO_PAGO NOT IN (24,25,26,30,70) ");
			sql.append("AND D.CAUS_CD_USUARIO = PAC_WEB_UTIL.FUN_USUARIO_RECTOR(?) ");
			sql.append("AND C.CACE_CAPD_CD_PRODUCTOR = D.CAUS_CAPD_CD_PRODUCTOR ");
			sql.append("UNION ");
			sql.append("SELECT C.CACE_CAPD_CD_PRODUCTOR PRODUCTOR,  ");
			sql.append("F.CAFC_NU_FACTURA FACTURA, ");
			sql.append("R.CARE_CARP_CD_RAMO RAMO, ");
			sql.append("R.CARE_CAPO_NU_POLIZA POLIZA, ");
			sql.append("R.CARE_CACE_NU_CERTIFICADO CERTIFICADO, ");
			sql.append("MP.CABU_NM_APELLIDO_RAZON ||' '||MP.CABU_NM_PERSONA  APELLIDO_RAZON_SOCIAL, ");
			sql.append("F.CAFC_CAMO_CD_MONEDA MONEDA, ");
			sql.append("FC.CAFT_MT_COMPONENTE IMPORTE, ");
			sql.append("TO_CHAR(F.CAFC_FE_SEGUNDO_VTO,'DD/MM/YYYY') SDO_VENCIMIENTO, r.care_nu_consecutivo_cuota CUOTA, c.cace_capu_cd_producto PRODUCTO, ");
			sql.append("(SELECT CAMD_DE_MEDIO_PAGO  FROM CART_MEDIO_PAGO WHERE CAMD_CD_MEDIO_PAGO = C.CACE_CAMD_CD_MEDIO_PAGO) MEDIO_PAGO ");
			sql.append("FROM RECTOR.CART_CERTIFICADOS C, ");
			sql.append("FACTURAS F, ");
			sql.append("RECTOR.CART_FACTURAS_COMPONENTES FC, ");
			sql.append("RECTOR.CART_FACTURAS_RECIBOS FR, ");
			sql.append("RECTOR.CART_MAE_PERSONAS MP,  ");
			sql.append("RECTOR.CART_RECIBOS R,  ");
			sql.append("RECTOR.CART_ROL_CLIENTES RC, ");
			sql.append("CART_USUARIOS D,  ");
			sql.append("SINT_SINIESTROS SIN,  ");
			sql.append("CRET_PRODUCTOS_DATOS CPD1, "); // SINIESTRO
			sql.append("CRET_PRODUCTOS_DATOS CPD2, "); // AÑO SINIESTRO
			sql.append("CRET_CERTIFICADO_BIENES CB ");
			//sql.append("CART_CERTIFICADOS C90 ");
			sql.append("WHERE F.CAFC_NU_FACTURA = FR.CAFB_CAFC_NU_FACTURA ");
			sql.append("AND FR.CAFB_CARE_NU_RECIBO = R.CARE_NU_RECIBO ");
			sql.append("AND R.CARE_CACN_NU_CEDULA_RIF = RC.CACN_NU_CEDULA_RIF ");
			sql.append("AND RC.CACN_CABU_NU_PERSONA = MP.CABU_NU_PERSONA ");
			sql.append("AND F.CAFC_NU_FACTURA = FC.CAFT_CAFC_NU_FACTURA ");
			sql.append("AND R.CARE_CARP_CD_RAMO = C.CACE_CARP_CD_RAMO ");
			sql.append("AND R.CARE_CAPO_NU_POLIZA = C.CACE_CAPO_NU_POLIZA ");
			sql.append("AND R.CARE_CACE_NU_CERTIFICADO = C.CACE_NU_CERTIFICADO ");
			// sql.append("AND (TRUNC(F.CAFC_FE_SEGUNDO_VTO) BETWEEN TRUNC(SYSDATE) AND TRUNC(SYSDATE+12)) ");
			sql.append("AND FC.CAFT_CAPP_CD_COMPONENTE='PRFAC' ");
			sql.append("AND C.CACE_CARP_CD_RAMO=90 ");
			sql.append("AND C.CACE_CAPU_CD_PRODUCTO='50' ");
			// sql.append("AND F.CAFC_ST_ESTADO=1 ");
			sql.append("AND C.CACE_ST_CERTIFICADO<>11 ");
			sql.append("AND C.CACE_CAMD_CD_MEDIO_PAGO NOT IN (24,25,26,30,70) ");
			sql.append("AND D.CAUS_CD_USUARIO = PAC_WEB_UTIL.FUN_USUARIO_RECTOR(?) ");
			sql.append("AND CPD1.CRPD_CARP_CD_RAMO=CB.CRCB_CARP_CD_RAMO  ");
			sql.append("AND CPD1.CRPD_CAPO_NU_POLIZA=CB.CRCB_CAPO_NU_POLIZA ");
			sql.append("AND CPD1.CRPD_CACE_NU_CERTIFICADO=CB.CRCB_CACE_NU_CERTIFICADO ");
			sql.append("AND CPD1.CRPD_CD_BIEN_ASEG=CB.CRCB_CD_BIEN_ASEG ");
			sql.append("AND CPD1.CRPD_NU_ENDOSO=CB.CRCB_NU_ENDOSO ");
			sql.append("AND CPD1.CRPD_CAPU_CD_PRODUCTO=C.CACE_CAPU_CD_PRODUCTO ");
			sql.append("AND CPD1.CRPD_CRCD_CD_DATO=107  ");// NRO SINIESTRO
			sql.append("AND CPD2.CRPD_CARP_CD_RAMO=CB.CRCB_CARP_CD_RAMO  ");
			sql.append("AND CPD2.CRPD_CAPO_NU_POLIZA=CB.CRCB_CAPO_NU_POLIZA ");
			sql.append("AND CPD2.CRPD_CACE_NU_CERTIFICADO=CB.CRCB_CACE_NU_CERTIFICADO ");
			sql.append("AND CPD2.CRPD_CD_BIEN_ASEG=CB.CRCB_CD_BIEN_ASEG ");
			sql.append("AND CPD2.CRPD_NU_ENDOSO=CB.CRCB_NU_ENDOSO ");
			sql.append("AND CPD2.CRPD_CAPU_CD_PRODUCTO=C.CACE_CAPU_CD_PRODUCTO ");
			sql.append("AND CPD2.CRPD_CRCD_CD_DATO=120  "); // AÑO SINIESTRO
			sql.append("AND CB.CRCB_CARP_CD_RAMO=C.CACE_CARP_CD_RAMO ");
			sql.append("AND CB.CRCB_CAPO_NU_POLIZA=C.CACE_CAPO_NU_POLIZA ");
			sql.append("AND CB.CRCB_NU_ENDOSO=C.CACE_NU_ENDOSO ");
			sql.append("AND CB.CRCB_CRBA_CD_BIEN_ASEG=10 "); // BIEN PRESTAMO
			sql.append("AND SIN.SISI_CARP_CD_RAMO=4 ");
			sql.append("AND SIN.SISI_NU_SINIESTRO=CPD1.CRPD_DE_DATO ");
			sql.append("AND SIN.SISI_NU_ANNIO=CPD2.CRPD_DE_DATO ");
			sql.append("AND C.CACE_CARP_CD_RAMO=SIN.SISI_CARP_CD_RAMO ");
			sql.append("AND C.CACE_CAPO_NU_POLIZA=SIN.SISI_CAPO_NU_POLIZA ");
			sql.append("AND C.CACE_NU_CERTIFICADO=SIN.SISI_CACE_NU_CERTIFICADO ");
			//sql.append("AND CF.CACE_CAPD_CD_PRODUCTOR =D.CAUS_CAPD_CD_PRODUCTOR  ");
			sql.append(") Q ");
			sql.append("ORDER BY APELLIDO_RAZON_SOCIAL, SDO_VENCIMIENTO ASC ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, param.getUsuario());
			pst.setString(2, param.getUsuario());

			result = pst.executeQuery();

			while (result.next()) {
				Integer moneda = result.getInt("MONEDA");
				Integer codramo = result.getInt("RAMO");
				Integer certificado = result.getInt("CERTIFICADO");
				Integer numPoliza = result.getInt("POLIZA");
				String razonSocial = result.getString("APELLIDO_RAZON_SOCIAL");
				String vencimiento = result.getString("SDO_VENCIMIENTO");
				String importe = result.getString("IMPORTE");
				String productor = result.getString("PRODUCTOR");
				String factura = result.getString("FACTURA");
				String producto = result.getString("PRODUCTO");
				String medioPago = result.getString("MEDIO_PAGO");
				Integer numCuota = result.getInt("CUOTA");
				if (result.wasNull()) {
					numCuota = null;
				}
				resultado
						.setUno(new ReporteFactura(numPoliza, codramo, certificado, razonSocial, moneda, ValuesUtils.toDouble(importe), vencimiento, productor, factura, numCuota, producto, medioPago));
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

	public ResultObtenerFacturasDelProductorProximasAVencer obtenerFacturasDelProductorProximasAVencer(final ParamObtenerFacturasDelProductorProximasAVencer param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ConsultaReportesPersist.class);
		logueo.setMetodo("obtenerFacturasDelProductorProximasAVencer");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultObtenerFacturasDelProductorProximasAVencer resultado = new ResultObtenerFacturasDelProductorProximasAVencer();

		try {
			conn = crearConexion();

			final StringBuffer sql = new StringBuffer(230);
			sql.append("WITH CERTIFICADOS_FACTURAS AS ");
			sql.append("( ");
			sql.append("SELECT C.CACE_CAPD_CD_PRODUCTOR PRODUCTOR, ");
			sql.append(" F.CAFC_NU_FACTURA FACTURA, ");
			sql.append("R.CARE_CARP_CD_RAMO RAMO, ");
			sql.append("R.CARE_CAPO_NU_POLIZA POLIZA, ");
			sql.append("R.CARE_CACE_NU_CERTIFICADO CERTIFICADO, ");
			sql.append(" MP.CABU_NM_APELLIDO_RAZON APELLIDO_RAZON_SOCIAL, ");
			sql.append(" F.CAFC_CAMO_CD_MONEDA MONEDA, ");
			sql.append(" FC.CAFT_MT_COMPONENTE IMPORTE, ");
			sql.append(" TO_CHAR(F.CAFC_FE_SEGUNDO_VTO, 'DD/MM/YYYY') SDO_VENCIMIENTO, ");
			sql.append(" C.CACE_CAPU_CD_PRODUCTO CD_PRODUCTO, ");
			sql.append(" C.CACE_NU_ENDOSO NU_ENDOSO ");

			sql.append(" FROM RECTOR.CART_CERTIFICADOS         C, ");
			sql.append("  RECTOR.CART_FACTURAS             F, ");
			sql.append("   RECTOR.CART_FACTURAS_COMPONENTES FC, ");
			sql.append("  RECTOR.CART_FACTURAS_RECIBOS     FR, ");
			sql.append("  RECTOR.CART_MAE_PERSONAS         MP, ");
			sql.append("   RECTOR.CART_RECIBOS              R, ");
			sql.append("  RECTOR.CART_ROL_CLIENTES         RC, ");
			sql.append("  CART_USUARIOS                    D ");
			sql.append(" WHERE D.CAUS_CD_USUARIO = PAC_WEB_UTIL.FUN_USUARIO_RECTOR(?)  ");
			sql.append(" AND C.CACE_CAPD_CD_PRODUCTOR = D.CAUS_CAPD_CD_PRODUCTOR    ");
			sql.append(" AND   F.CAFC_ST_ESTADO = 1 ");
			sql.append(" AND F.CAFC_NU_FACTURA = FR.CAFB_CAFC_NU_FACTURA ");
			sql.append(" AND FR.CAFB_CARE_NU_RECIBO = R.CARE_NU_RECIBO ");
			sql.append(" AND R.CARE_CACN_NU_CEDULA_RIF = RC.CACN_NU_CEDULA_RIF ");
			sql.append(" AND RC.CACN_CABU_NU_PERSONA = MP.CABU_NU_PERSONA ");
			sql.append(" AND F.CAFC_NU_FACTURA = FC.CAFT_CAFC_NU_FACTURA ");
			sql.append(" AND R.CARE_CARP_CD_RAMO = C.CACE_CARP_CD_RAMO ");
			sql.append(" AND R.CARE_CAPO_NU_POLIZA = C.CACE_CAPO_NU_POLIZA ");
			sql.append(" AND R.CARE_CACE_NU_CERTIFICADO = C.CACE_NU_CERTIFICADO ");
			sql.append(" AND (((SYSDATE) > (F.CAFC_FE_PRIMER_VTO) AND ");
			sql.append("     (SYSDATE) <= (F.CAFC_FE_SEGUNDO_VTO)) OR ");
			sql.append(" ((F.CAFC_FE_SEGUNDO_VTO) BETWEEN (SYSDATE) AND (SYSDATE + 12))) ");
			sql.append(" AND FC.CAFT_CAPP_CD_COMPONENTE = 'PRFAC' ");
			sql.append(" AND C.CACE_ST_CERTIFICADO <> 11 ");
			sql.append(" AND C.CACE_CAMD_CD_MEDIO_PAGO NOT IN (24, 25, 26, 30, 70) ");

			sql.append(" ) ");

			sql.append("SELECT Q.PRODUCTOR, ");
			sql.append(" Q.FACTURA, ");
			sql.append("Q.RAMO, ");
			sql.append(" Q.POLIZA, ");
			sql.append(" Q.CERTIFICADO, ");
			sql.append(" Q.APELLIDO_RAZON_SOCIAL, ");
			sql.append(" Q.MONEDA, ");
			sql.append(" Q.IMPORTE, ");
			sql.append(" Q.SDO_VENCIMIENTO ");

			sql.append(" FROM ( ");

			sql.append("SELECT CF.PRODUCTOR, ");
			sql.append(" CF.FACTURA, ");
			sql.append("  CF.RAMO, ");
			sql.append(" CF.POLIZA, ");
			sql.append("  CF.CERTIFICADO, ");
			sql.append(" CF.APELLIDO_RAZON_SOCIAL, ");
			sql.append(" CF.MONEDA, ");
			sql.append(" CF.IMPORTE, ");
			sql.append(" CF.SDO_VENCIMIENTO ");
			sql.append("  FROM CERTIFICADOS_FACTURAS CF ");

			sql.append("  UNION ");

			sql.append(" SELECT CF.PRODUCTOR, ");
			sql.append("  CF.FACTURA, ");
			sql.append("  CF.RAMO, ");
			sql.append(" CF.POLIZA, ");
			sql.append("  CF.CERTIFICADO, ");
			sql.append(" CF.APELLIDO_RAZON_SOCIAL, ");
			sql.append(" CF.MONEDA, ");
			sql.append(" CF.IMPORTE, ");
			sql.append(" CF.SDO_VENCIMIENTO ");
			sql.append("  FROM CERTIFICADOS_FACTURAS   CF, ");
			sql.append("  SINT_SINIESTROS         SINN, ");
			sql.append("  CRET_PRODUCTOS_DATOS    CPD1,  ");// SINIESTRO
			sql.append("  CRET_PRODUCTOS_DATOS    CPD2, "); // AÑO SINIESTRO
			sql.append("  CRET_CERTIFICADO_BIENES CB ");
			//sql.append("  CART_CERTIFICADOS       C90 ");
			sql.append(" WHERE CPD1.CRPD_CARP_CD_RAMO = CB.CRCB_CARP_CD_RAMO ");
			sql.append(" AND CPD1.CRPD_CAPO_NU_POLIZA = CB.CRCB_CAPO_NU_POLIZA ");
			sql.append(" AND CPD1.CRPD_CACE_NU_CERTIFICADO = CB.CRCB_CACE_NU_CERTIFICADO ");
			sql.append("  AND CPD1.CRPD_CD_BIEN_ASEG = CB.CRCB_CD_BIEN_ASEG ");
			sql.append(" AND CPD1.CRPD_NU_ENDOSO = CB.CRCB_NU_ENDOSO ");
			sql.append(" AND CPD1.CRPD_CAPU_CD_PRODUCTO = CF.CD_PRODUCTO ");
			sql.append(" AND CPD1.CRPD_CRCD_CD_DATO = 107  "); // NRO SINIESTRO
			sql.append(" AND CPD2.CRPD_CARP_CD_RAMO = CB.CRCB_CARP_CD_RAMO ");
			sql.append(" AND CPD2.CRPD_CAPO_NU_POLIZA = CB.CRCB_CAPO_NU_POLIZA ");
			sql.append(" AND CPD2.CRPD_CACE_NU_CERTIFICADO = CB.CRCB_CACE_NU_CERTIFICADO ");
			sql.append(" AND CPD2.CRPD_CD_BIEN_ASEG = CB.CRCB_CD_BIEN_ASEG ");
			sql.append(" AND CPD2.CRPD_NU_ENDOSO = CB.CRCB_NU_ENDOSO ");
			sql.append("AND CPD2.CRPD_CAPU_CD_PRODUCTO = CF.CD_PRODUCTO ");
			sql.append(" AND CPD2.CRPD_CRCD_CD_DATO = 120 "); // AÑO SINIESTRO
			sql.append(" AND CB.CRCB_CARP_CD_RAMO = CF.RAMO  ");
			sql.append(" AND CB.CRCB_CAPO_NU_POLIZA = CF.POLIZA  ");
			sql.append(" AND CB.CRCB_NU_ENDOSO = CF.NU_ENDOSO ");
			sql.append(" AND CB.CRCB_CRBA_CD_BIEN_ASEG = 10  "); // BIEN
																	// PRESTAMO
			sql.append(" AND SINN.SISI_CARP_CD_RAMO = 4 ");
			sql.append(" AND SINN.SISI_NU_SINIESTRO = CPD1.CRPD_DE_DATO  ");
			sql.append(" AND SINN.SISI_NU_ANNIO = CPD2.CRPD_DE_DATO  ");
			sql.append(" AND CF.RAMO = SINN.SISI_CARP_CD_RAMO ");
			sql.append(" AND CF.POLIZA = SINN.SISI_CAPO_NU_POLIZA ");
			sql.append(" AND CF.CERTIFICADO = SINN.SISI_CACE_NU_CERTIFICADO ");
			sql.append(" AND CF.PRODUCTOR = CF.PRODUCTOR  ");
			sql.append("  ) Q  ");
			sql.append(" ORDER BY APELLIDO_RAZON_SOCIAL, SDO_VENCIMIENTO ASC ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, param.getUsuario());

			result = pst.executeQuery();

			while (result.next()) {
				Integer moneda = result.getInt("MONEDA");
				Integer codramo = result.getInt("RAMO");
				Integer certificado = result.getInt("CERTIFICADO");
				Integer numPoliza = result.getInt("POLIZA");
				String razonSocial = result.getString("APELLIDO_RAZON_SOCIAL");
				String vencimiento = result.getString("SDO_VENCIMIENTO");
				String importe = result.getString("IMPORTE");
				String productor = result.getString("PRODUCTOR");
				String factura = result.getString("FACTURA");
				// FIXME OIGRES, FALTAN LOS DATOS DE PRODUCTO CUOTA Y MEDIO DE
				// PAGO
				resultado.setUno(new ReporteFactura(numPoliza, codramo, certificado, razonSocial, moneda, ValuesUtils.toDouble(importe), vencimiento, productor, factura));
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

	public ResultObtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores obtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores(
			final ParamObtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ConsultaReportesPersist.class);
		logueo.setMetodo("obtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultObtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores resultado = new ResultObtenerPolizasEnCondicionesEstarRehabilitadasParaCorredores();

		try {
			conn = crearConexion();

			final StringBuffer sql = new StringBuffer(230);
			sql.append("SELECT FRM.CAFC_NU_FACTURA, ");
			sql.append("FRM.CARE_CARP_CD_RAMO, ");
			sql.append("FRM.CARE_CAPO_NU_POLIZA, ");
			sql.append("FRM.CARE_CACE_NU_CERTIFICADO, ");
			sql.append("MP.CABU_NM_APELLIDO_RAZON || ' ' || MP.CABU_NM_PERSONA AS RAZON, ");
			sql.append("FRM.CARE_CAMO_CD_MONEDA, ");
			sql.append("FC.CAFT_MT_COMPONENTE, ");
			sql.append("TO_CHAR(FRM.CAFC_FE_SEGUNDO_VTO, 'DD/MM/YYYY') AS FE_SEGUNDO_VTO, ");
			sql.append("TO_CHAR(FRM.CAFC_FE_SEGUNDO_VTO + 25, 'DD/MM/YYYY') AS FE_SEGUNDO_VTO_25 ");
			sql.append("FROM RECTOR.CART_CERTIFICADOS         C, ");
			sql.append("RECTOR.CART_FACTURAS_COMPONENTES FC, ");
			sql.append("RECTOR.CART_MAE_PERSONAS         MP, ");
			sql.append("RECTOR.CART_ROL_CLIENTES         CLI, ");
			sql.append("RECTOR.CARV_FACTURAS_RECIBOS_MAT FRM ");
			sql.append("WHERE C.CACE_CASU_CD_SUCURSAL = FRM.CARE_CASU_CD_SUCURSAL ");
			sql.append("AND C.CACE_CARP_CD_RAMO = FRM.CARE_CARP_CD_RAMO ");
			sql.append("AND C.CACE_CAPO_NU_POLIZA = FRM.CARE_CAPO_NU_POLIZA ");
			sql.append("AND C.CACE_NU_CERTIFICADO = FRM.CARE_CACE_NU_CERTIFICADO ");
			sql.append("AND FRM.CAFC_NU_FACTURA = FC.CAFT_CAFC_NU_FACTURA ");
			sql.append("AND C.CACE_CACN_NU_CEDULA_RIF = CLI.CACN_NU_CEDULA_RIF ");
			sql.append("AND CLI.CACN_CABU_NU_PERSONA = MP.CABU_NU_PERSONA ");
			sql.append("AND FRM.CAFC_ST_ESTADO = 1 ");
			sql.append("AND FC.CAFT_CAPP_CD_COMPONENTE = 'PRFAC' ");
			sql.append("AND C.CACE_CAMD_CD_MEDIO_PAGO = 1 ");
			sql.append("AND (FRM.CAFC_FE_SEGUNDO_VTO) BETWEEN (SYSDATE - 22) AND (SYSDATE - 9) ");
			sql.append("AND (C.CACE_FE_HASTA) > (SYSDATE + 2) ");
			sql.append("AND C.CACE_ST_CERTIFICADO <> 11 ");
			sql.append("AND C.CACE_CAMD_CD_MEDIO_PAGO NOT IN (70) ");
			sql.append("AND NOT EXISTS ");
			sql.append("(SELECT 1 ");
			sql.append("FROM RECTOR.CARV_FACTURAS_RECIBOS_MAT FRM2 ");
			sql.append("WHERE FRM.CAFC_NU_FACTURA = FRM2.CAFC_NU_FACTURA ");
			sql.append("AND FRM2.CARE_NU_RECIBO > FRM.CARE_NU_RECIBO) ");
			sql.append("AND C.CACE_CAPD_CD_PRODUCTOR = ");
			sql.append("PAC_WEB_UTIL.FUN_CODIGO_CORREDOR(?) ");
			sql.append("ORDER BY FRM.CAFC_FE_SEGUNDO_VTO, ");
			sql.append("MP.CABU_NM_APELLIDO_RAZON, ");
			sql.append("FRM.CARE_CARP_CD_RAMO, ");
			sql.append("FRM.CARE_CAPO_NU_POLIZA ");
			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, param.getUsuario());
			result = pst.executeQuery();
			while (result.next()) {
				String moneda = result.getString("CARE_CAMO_CD_MONEDA");
				Integer codRamo = result.getInt("CARE_CARP_CD_RAMO");
				Integer certificado = result.getInt("CARE_CACE_NU_CERTIFICADO");
				Integer numPoliza = result.getInt("CARE_CAPO_NU_POLIZA");
				String vencimiento = result.getString("FE_SEGUNDO_VTO");
				String segundoVencimiento = result.getString("FE_SEGUNDO_VTO_25");
				String factura = result.getString("CAFC_NU_FACTURA");
				Double componente = result.getDouble("CAFT_MT_COMPONENTE");
				String razonSocial = result.getString("RAZON");
				resultado.setUno(new ReportePolizasParaRehabilitar(numPoliza, codRamo, certificado, razonSocial, moneda, vencimiento, componente, factura, segundoVencimiento));

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

	public ResultObtenerListadoControlADTBPSParaProductor obtenerListadoControlADTBPSParaProductor(final ParamObtenerListadoControlADTBPSParaProductor param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ConsultaReportesPersist.class);
		logueo.setMetodo("obtenerListadoControlADTBPSParaProductor");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Fecha", param.getFecha());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultObtenerListadoControlADTBPSParaProductor resultado = new ResultObtenerListadoControlADTBPSParaProductor();

		try {
			conn = crearConexion();

			final StringBuffer sql = new StringBuffer(1500);

			sql.append("SELECT CACW_CAPO_NU_POLIZA, CACW_CACE_NU_CEDULA, CACN_NM_APELLIDO_RAZON || DECODE(CACN_NM_PERSONA_NATURAL,NULL,'',', ') ||CACN_NM_PERSONA_NATURAL RAZON ,  ");
			sql.append("CACW_FE_DESDE, (SELECT CRPD_DE_DATO||'-'||CRTB_DE_DATO    ");
			sql.append("FROM CRET_PRODUCTOS_DATOS,CRET_TABLAS   ");
			sql.append("WHERE CRPD_CARP_CD_RAMO=CCE.CACW_CARP_CD_RAMO  AND CRPD_CAPO_NU_POLIZA=CCE.CACW_CAPO_NU_POLIZA  ");
			sql.append("AND CRPD_CACE_NU_CERTIFICADO=CCE.CACW_CACE_NU_CERTIFICADO AND CRPD_NU_ENDOSO=CCE.CACW_NU_ENDOSO  ");
			sql.append("AND CRPD_CAPU_CD_PRODUCTO=CCE.CACW_CAPU_CD_PRODUCTO AND CRPD_CRCD_CD_DATO=CRTB_CD_TABLA  ");
			sql.append("AND CRPD_DE_DATO=CRTB_DATO1  AND CRPD_CD_BIEN_ASEG=CCB.CRCB_CD_BIEN_ASEG AND CCB.CRCB_CRBA_CD_BIEN_ASEG=3  ");
			sql.append("AND CRPD_CRCD_CD_DATO=120170),  ");
			sql.append("(SELECT CRPD_DE_DATO  ");
			sql.append(" FROM CRET_PRODUCTOS_DATOS  ");
			sql.append("WHERE CRPD_CARP_CD_RAMO=CCE.CACW_CARP_CD_RAMO  AND CRPD_CAPO_NU_POLIZA=CCE.CACW_CAPO_NU_POLIZA  ");
			sql.append(" AND CRPD_CACE_NU_CERTIFICADO=CCE.CACW_CACE_NU_CERTIFICADO AND CRPD_NU_ENDOSO=CCE.CACW_NU_ENDOSO  ");
			sql.append("AND CRPD_CAPU_CD_PRODUCTO=CCE.CACW_CAPU_CD_PRODUCTO AND CRPD_CD_BIEN_ASEG=CCB.CRCB_CD_BIEN_ASEG  ");
			sql.append("AND CCB.CRCB_CRBA_CD_BIEN_ASEG=3 AND CRPD_CRCD_CD_DATO=120157)   ");
			sql.append("FROM CART_CERTIFICADOS_ENDOSOS CCE,  ");
			sql.append("CRET_CERTIFICADO_BIENES CCB,   ");
			sql.append("CART_CLIENTES CL  ");
			sql.append("WHERE CCE.CACW_CAPU_CD_PRODUCTO='ADTBPS' AND CCE.CACW_CARP_CD_RAMO=2 AND CCE.CACW_CACE_NU_CERTIFICADO=1  ");
			sql.append("AND CCE.CACW_NU_ENDOSO=0 AND CCE.CACW_CACE_NU_CEDULA=CL.CACN_NU_CEDULA_RIF AND CCE.CACW_CACE_CD_NACIONALIDAD=CL.CACN_CD_NACIONALIDAD  ");
			sql.append("AND CCB.CRCB_CARP_CD_RAMO=CCE.CACW_CARP_CD_RAMO  AND CCB.CRCB_CAPO_NU_POLIZA=CCE.CACW_CAPO_NU_POLIZA  ");
			sql.append("AND CCB.CRCB_CACE_NU_CERTIFICADO=CCE.CACW_CACE_NU_CERTIFICADO AND CCB.CRCB_NU_ENDOSO=CCE.CACW_NU_ENDOSO   ");
			sql.append("AND EXISTS (SELECT NULL ");
			sql.append("FROM CART_CERTIFICADOS CC, CART_USUARIOS D ");
			sql.append("WHERE CC.CACE_CARP_CD_RAMO=CCE.CACW_CARP_CD_RAMO  AND CC.CACE_CASU_CD_SUCURSAL=CCE.CACW_CASU_CD_SUCURSAL ");
			sql.append("AND CC.CACE_CAPO_NU_POLIZA=CCE.CACW_CAPO_NU_POLIZA AND CC.CACE_NU_CERTIFICADO=CCE.CACW_CACE_NU_CERTIFICADO ");
			sql.append("AND CC.CACE_CAPD_CD_PRODUCTOR = D.CAUS_CAPD_CD_PRODUCTOR ");
			sql.append("AND D.CAUS_CD_USUARIO = PAC_WEB_UTIL.FUN_USUARIO_RECTOR(?))  ");
			sql.append("AND CCB.CRCB_CRBA_CD_BIEN_ASEG = 3 AND CCE.CACW_FE_DESDE >= (?) ");
			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, param.getUsuario());
			pst.setDate(2, toSqlDate(param.getFecha()));
			result = pst.executeQuery();
			while (result.next()) {
				String cedula = result.getString("CACW_CACE_NU_CEDULA");
				String razonSocial = result.getString("RAZON");
				Integer numPoliza = result.getInt("CACW_CAPO_NU_POLIZA");
				String datoTabla = result.getString(5);
				Double datoProducto = result.getDouble(6);
				String fechaDesde = ValuesUtils.toString(result.getDate("CACW_FE_DESDE"));
				resultado.setUno(new ReporteADTBPS(numPoliza, razonSocial, cedula, datoTabla, datoProducto, fechaDesde));
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

	public ResultObtenerIncentivoPlusTotales obtenerIncentivoPlusTotales(final ParamObtenerIncentivoPlusTotales param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ConsultaReportesPersist.class);
		logueo.setMetodo("obtenerIncentivoPlusTotales");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Mes desde", param.getMesDesde());
		logueo.setParametro("Mes hasta", param.getMesHasta());
		logueo.setParametro("Codigo de ramo", param.getCodRamo());
		logueo.setParametro("Tipo", param.getTipo());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultObtenerIncentivoPlusTotales resultado = new ResultObtenerIncentivoPlusTotales();

		try {
			conn = crearConexion();
			final StringBuffer sql = new StringBuffer(230);

			sql.append("SELECT DESDE, HASTA, RAMO, PRODUCTO, PLAN, TIPO, CANTIDAD, TOTAL_PUNTOS ");
			sql.append("FROM ");
			sql.append("( ");
			sql.append("SELECT '" + param.getMesDesde() + "' DESDE, '" + param.getMesHasta() + "' HASTA, ");
			sql.append("P.RAMO RAMO, P.PRODUCTO PRODUCTO, P.PLAN_N PLAN, ");
			sql.append("P.TIPO TIPO, ");
			sql.append("COUNT(*) CANTIDAD, SUM(P.PUNTAJE) TOTAL_PUNTOS ");
			sql.append("FROM RECTOR.BSE_PUNTAJE_PRODUCTORES_AUTOS P, CART_USUARIOS  ");
			sql.append("WHERE  ");
			sql.append("P.PRODUCTOR_N =CAUS_CAPD_CD_PRODUCTOR ");
			sql.append("AND TO_DATE('01/'||P.MES,'DD/MM/YYYY') BETWEEN TO_DATE('01/'||?,'DD/MM/YYYY') AND LAST_DAY(TO_DATE('01/'||?,'DD/MM/YYYY')) ");

			sql.append("AND P.RAMO=NVL(?,P.RAMO) ");
			sql.append("AND P.TIPO=NVL(?,P.TIPO) ");
			sql.append("AND CAUS_CD_USUARIO=PAC_WEB_UTIL.FUN_USUARIO_RECTOR(?) ");
			sql.append("AND P.ID_SECUENCIA= (SELECT MAX(A.ID_SECUENCIA) ");
			sql.append("FROM RECTOR.BSE_PUNTAJE_PRODUCTORES_AUTOS A ");
			sql.append("WHERE A.MES=P.MES AND A.RAMO=P.RAMO ");
			sql.append(") ");
			sql.append("GROUP BY '" + param.getMesDesde() + "', '" + param.getMesHasta() + "', ");
			sql.append("P.RAMO, P.PRODUCTO, P.PLAN_N,  ");
			sql.append("P.TIPO  ");
			sql.append(") ");
			sql.append("ORDER BY  RAMO, PRODUCTO, PLAN, TIPO ");
			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, param.getMesDesde());
			pst.setString(2, param.getMesHasta());

			if (param.getCodRamo() != null) {
				pst.setInt(3, param.getCodRamo());
			} else {
				pst.setNull(3, Types.INTEGER);
			}
			if (param.getTipo() != null) {
				pst.setString(4, param.getTipo());
			} else {
				pst.setNull(4, Types.VARCHAR);
			}

			pst.setString(5, param.getUsuario());

			result = pst.executeQuery();

			while (result.next()) {
				String desde = result.getString("DESDE");
				Integer codRamo = result.getInt("RAMO");
				String tipo = result.getString("TIPO");
				String hasta = result.getString("HASTA");
				String plan = result.getString("PLAN");
				String producto = result.getString("PRODUCTO");
				Integer cantidad = result.getInt("CANTIDAD");
				Double totalPuntos = result.getDouble("TOTAL_PUNTOS");
				resultado.setUno(new ReporteIncentivoPlusTotal(codRamo, producto, tipo, cantidad, totalPuntos, desde, plan, hasta));
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

	public ResultObtenerPolizasSinMatriculasXProductores obtenerPolizasSinMatriculasXProductores(final ParamObtenerPolizasSinMatriculasXProductores param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ConsultaReportesPersist.class);
		logueo.setMetodo("obtenerPolizasSinMatriculasXProductores");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultObtenerPolizasSinMatriculasXProductores resultado = new ResultObtenerPolizasSinMatriculasXProductores();

		try {
			conn = crearConexion();

			final StringBuffer sql = new StringBuffer(230);
			sql.append("SELECT  ");
			sql.append("CCERT.CACE_CARP_CD_RAMO, ");
			sql.append("CRPD_CAPO_NU_POLIZA , ");
			sql.append("CCERT.CACE_NU_CERTIFICADO, ");
			sql.append("(CACN_NM_APELLIDO_RAZON||' '||CACN_NM_PERSONA_NATURAL) RAZON, ");
			sql.append("CACN_NU_CEDULA_RIF "); // NUMERO CLIENTE
			sql.append("FROM ");
			sql.append("CRET_PRODUCTOS_DATOS, ");
			sql.append("CART_CERTIFICADOS CCERT, ");
			sql.append("CART_CLIENTES ");
			sql.append("WHERE ");
			sql.append("CCERT.CACE_CASU_CD_SUCURSAL = CRPD_CASU_CD_SUCURSAL AND ");
			sql.append("CCERT.CACE_CARP_CD_RAMO = CRPD_CARP_CD_RAMO AND ");
			sql.append("CCERT.CACE_CAPO_NU_POLIZA = CRPD_CAPO_NU_POLIZA AND ");
			sql.append("CCERT.CACE_NU_CERTIFICADO = CRPD_CACE_NU_CERTIFICADO AND ");
			sql.append("CCERT.CACE_NU_ENDOSO = CRPD_NU_ENDOSO AND ");
			sql.append("CCERT.CACE_CACN_NU_CEDULA_RIF = CACN_NU_CEDULA_RIF AND ");
			sql.append("CCERT.CACE_ST_CERTIFICADO <> 11 AND ");
			sql.append("CCERT.CACE_FE_HASTA >= SYSDATE AND  ");
			sql.append("CRPD_CRCD_CD_DATO = 141001 AND ");
			sql.append("CRPD_DATO IS NULL ");
			sql.append("AND  CRPD_NU_ENDOSO = (SELECT      MAX(CCEND_U.CACW_NU_ENDOSO) ");
			sql.append("FROM        CART_CERTIFICADOS_ENDOSOS       CCEND_U ");
			sql.append("WHERE       CCEND_U.CACW_CARP_CD_RAMO           =       CCERT.CACE_CARP_CD_RAMO ");
			sql.append("AND         CCEND_U.CACW_CAPO_NU_POLIZA         =       CCERT.CACE_CAPO_NU_POLIZA ");
			sql.append("AND         CCEND_U.CACW_CACE_NU_CERTIFICADO    =       CCERT.CACE_NU_CERTIFICADO ");
			sql.append("AND         CCEND_U.CACW_CAME_TP_TRANSAC        NOT IN  ('C', 'U') ");
			sql.append("AND         NOT EXISTS  ( ");
			sql.append("SELECT  1 ");
			sql.append("FROM    CART_CERTIFICADOS_ENDOSOS       CCEND_U2 ");
			sql.append("WHERE   CCEND_U2.CACW_CARP_CD_RAMO          =   CCEND_U.CACW_CARP_CD_RAMO ");
			sql.append("AND     CCEND_U2.CACW_CAPO_NU_POLIZA        =   CCEND_U.CACW_CAPO_NU_POLIZA ");
			sql.append("AND     CCEND_U2.CACW_NU_ENDOSO             =   CCEND_U.CACW_NU_ENDOSO + 1 ");
			sql.append("AND     CCEND_U2.CACW_CACE_NU_CERTIFICADO   =   0 ");
			sql.append("AND     CCEND_U2.CACW_CAME_TP_TRANSAC       =   'U' ");
			sql.append(")) ");
			sql.append("AND CCERT.CACE_CAPD_CD_PRODUCTOR = PAC_WEB_UTIL.FUN_CODIGO_CORREDOR(?) ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, param.getUsuario());
			result = pst.executeQuery();

			while (result.next()) {
				Integer numPoliza = result.getInt("CRPD_CAPO_NU_POLIZA");
				Integer codRamo = result.getInt("CACE_CARP_CD_RAMO");
				Integer certificado = result.getInt("CACE_NU_CERTIFICADO");
				String razonSocial = result.getString("RAZON");
				String cliente = result.getString("CACN_NU_CEDULA_RIF");
				ReportePolizaSinMatricula dato = new ReportePolizaSinMatricula();
				dato.setNumCliente(cliente);
				dato.setCertificado(certificado);
				dato.setCodRamo(codRamo);
				dato.setNumPoliza(numPoliza);
				dato.setRazonSocial(razonSocial);
				resultado.setUno(dato);
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

	public ResultObtenerClientesCorredores obtenerClientesCorredores(final ParamObtenerClientesCorredores param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ConsultaReportesPersist.class);
		logueo.setMetodo("obtenerClientesCorredores");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultObtenerClientesCorredores resultado = new ResultObtenerClientesCorredores();

		try {
			conn = crearConexion();

			final StringBuffer sql = new StringBuffer(230);
			sql.append("SELECT   NRO_PRODUCTOR, ");
			sql.append("NRO_PERSONA, ");
			sql.append("NVL (TP_DOCUMENTO, 'RUT') TIPODOCUMENTO, ");
			sql.append("DECODE(NRO_DOCUMENTO,'0',PER.CABU_NU_CUIT,NRO_DOCUMENTO) NUMERODOCUMENTO, ");
			sql.append("APELLIDO_O_RAZON, ");
			sql.append("NOMBRE, ");
			sql.append("TELEFONO, ");
			sql.append("CELULAR, ");
			sql.append("EMAIL, ");
			sql.append("   (SELECT MAX(CACN_NU_CEDULA_RIF)");
			sql.append("      FROM CART_ROL_CLIENTES");
			sql.append("     WHERE CACN_CABU_NU_PERSONA = PER.CABU_NU_PERSONA) NRO_CLIENTE ");

			sql.append("FROM   RECTOR.COMUNICACIONES_POR_PRODUCTOR COM, ");
			sql.append("CART_USUARIOS US, ");
			sql.append("CART_MAE_PERSONAS PER ");
			sql.append("WHERE       PER.CABU_NU_PERSONA = COM.NRO_PERSONA ");
			sql.append("AND US.CAUS_CAPD_CD_PRODUCTOR = COM.NRO_PRODUCTOR ");
			sql.append("AND INICIO_A.F_GETUS_EMPLEADO_PRODUCTOR IS NULL ");
			sql.append("AND US.CAUS_CD_USUARIO = PAC_WEB_UTIL.FUN_USUARIO_RECTOR(?) ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, param.getUsuario());
			result = pst.executeQuery();

			while (result.next()) {
				Integer numProductor = result.getInt("NRO_PRODUCTOR");
				Integer numPersona = result.getInt("NRO_PERSONA");
				String tipoDocumento = result.getString("TIPODOCUMENTO");
				String numDocumento = result.getString("NUMERODOCUMENTO");
				String razonSocial = result.getString("APELLIDO_O_RAZON");
				String nombre = result.getString("NOMBRE");
				String telefono = result.getString("TELEFONO");
				String celular = result.getString("CELULAR");
				String mail = result.getString("EMAIL");
				Integer numCliente = result.getInt("NRO_CLIENTE");

				resultado.setUno(new ReporteClienteCorredor(numProductor, numPersona, numCliente,tipoDocumento, numDocumento, razonSocial, nombre, telefono, celular, mail));
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

	public ResultObtenerIncentivoPlusDetalle obtenerIncentivoPlusDetalle(final ParamObtenerIncentivoPlusDetalle param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(CotizacionesPersist.class);
		logueo.setMetodo("obtenerIncentivoPlusDetalle");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Mes Anio", param.getMesAnio());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultObtenerIncentivoPlusDetalle resultado = new ResultObtenerIncentivoPlusDetalle();

		try {
			conn = crearConexion();

			final StringBuffer sql = new StringBuffer(230);
			sql.append("SELECT MES, RAMO, PRODUCTO, TIPO, ");
			sql.append("POLIZA_ACTUAL, CERTIFICADO_ACTUAL, PLAN_ACTUAL,DESDE_ACTUAL, HASTA_ACTUAL, ");
			sql.append("MATRICULA, CHASIS,PUNTAJE, MONEDA, CLIENTE  ");
			sql.append("FROM ");
			sql.append("( ");
			sql.append("SELECT P.MES MES, P.RAMO RAMO, P.PRODUCTO PRODUCTO, P.TIPO TIPO, ");
			sql.append("P.POLIZA_N POLIZA_ACTUAL, P.CERTIFICADO_N  CERTIFICADO_ACTUAL, P.PLAN_N PLAN_ACTUAL, P.FE_DESDE_N  DESDE_ACTUAL, P.FE_HASTA_N  HASTA_ACTUAL,  ");
			sql.append("P.MATRICULA_N MATRICULA,    ");
			sql.append("P.CHASIS_N CHASIS, ");
			sql.append("P.PUNTAJE PUNTAJE,  ");
			sql.append("P.MONEDA_V MONEDA,  ");
			sql.append("P.CLIENTE_V CLIENTE  ");
			sql.append("FROM RECTOR.BSE_PUNTAJE_PRODUCTORES_AUTOS P, CART_USUARIOS  ");
			sql.append("WHERE P.PRODUCTOR_N=CAUS_CAPD_CD_PRODUCTOR ");
			sql.append("AND CAUS_CD_USUARIO=PAC_WEB_UTIL.FUN_USUARIO_RECTOR(?) ");
			sql.append("AND P.MES=? ");// MES
			sql.append("AND TO_DATE('01/'||P.MES,'DD/MM/YYYY') > TO_DATE('28/02/2014','DD/MM/YYYY')  ");
			sql.append("AND P.RAMO=NVL(?,P.RAMO) "); // RAMO
			sql.append("AND P.TIPO=NVL(?,P.TIPO) "); // TIPO
			sql.append("AND P.ID_SECUENCIA= (SELECT MAX(ID_SECUENCIA) ");
			sql.append("FROM RECTOR.BSE_PUNTAJE_PRODUCTORES_AUTOS A ");
			sql.append("WHERE A.MES=P.MES AND A.RAMO=P.RAMO ");
			sql.append(") ");
			sql.append(") ");
			sql.append("ORDER BY MES, RAMO, PRODUCTO, TIPO ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());

			pst.setString(1, param.getUsuario());
			pst.setString(2, param.getMesAnio());

			if (param.getCodRamo() != null) {
				pst.setInt(3, param.getCodRamo());
			} else {
				pst.setNull(3, Types.INTEGER);
			}
			if (param.getTipo() != null) {
				pst.setString(4, param.getTipo());
			} else {
				pst.setNull(4, Types.VARCHAR);
			}

			result = pst.executeQuery();

			while (result.next()) {
				String mesAnio = result.getString("MES");
				Integer codRamo = result.getInt("RAMO");
				String producto = result.getString("PRODUCTO");
				String tipo = result.getString("TIPO");
				Integer numPoliza = result.getInt("POLIZA_ACTUAL");
				Integer certificado = result.getInt("CERTIFICADO_ACTUAL");
				String plan = result.getString("PLAN_ACTUAL");
				java.sql.Date desde = result.getDate("DESDE_ACTUAL");
				java.sql.Date hasta = result.getDate("HASTA_ACTUAL");
				String matricula = result.getString("MATRICULA");
				String chasis = result.getString("CHASIS");
				String puntaje = result.getString("PUNTAJE");
				Integer moneda = result.getInt("MONEDA");
				Integer cliente = result.getInt("CLIENTE");
				ReporteIncentivoPlusDetalle dato = new ReporteIncentivoPlusDetalle(numPoliza, codRamo, certificado, mesAnio, matricula, producto, tipo, plan, chasis, puntaje, moneda, cliente);
				if (desde != null) {
					dato.setDesde(new Date(desde.getTime()));
				}
				if (hasta != null) {
					dato.setHasta(new Date(hasta.getTime()));
				}
				resultado.setUno(dato);
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

	public ResultObtenerPolizasAdheridasDebitoAutomatico obtenerPolizasAdheridasDebitoAutomatico(final ParamObtenerPolizasAdheridasDebitoAutomatico param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ConsultaReportesPersist.class);
		logueo.setMetodo("obtenerPolizasAdheridasDebitoAutomatico");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Fecha desde", param.getFechaDesde());
		logueo.setParametro("Fecha hasta", param.getFechaHasta());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultObtenerPolizasAdheridasDebitoAutomatico resultado = new ResultObtenerPolizasAdheridasDebitoAutomatico();

		try {
			conn = crearConexion();

			final StringBuffer sql = new StringBuffer(230);

			sql.append("SELECT E.CACW_CAMD_CD_MEDIO_PAGO,E.CACW_COTC_CD_ORIGEN,E.CACW_CARP_CD_RAMO,E.CACW_CAPO_NU_POLIZA,E.CACW_FE_DESDE ");
			sql.append("FROM RECTOR.CART_CERTIFICADOS_ENDOSOS E, RECTOR.CART_CERTIFICADOS C ");
			sql.append("WHERE E.CACW_CAMD_CD_MEDIO_PAGO IN (2, 4) ");
			sql.append("AND C.CACE_CAMD_CD_MEDIO_PAGO = E.CACW_CAMD_CD_MEDIO_PAGO ");
			sql.append("AND C.CACE_COTC_CD_ORIGEN = E.CACW_COTC_CD_ORIGEN ");
			sql.append("AND C.CACE_CAPO_NU_POLIZA = E.CACW_CAPO_NU_POLIZA ");
			sql.append("AND C.CACE_CARP_CD_RAMO = E.CACW_CARP_CD_RAMO ");
			sql.append("AND C.CACE_NU_CERTIFICADO = E.CACW_CACE_NU_CERTIFICADO ");
			sql.append("AND E.CACW_CACE_NU_CERTIFICADO = 0 ");
			sql.append("AND C.CACE_ST_CERTIFICADO <> 11 ");
			sql.append("AND C.CACE_CAPD_CD_PRODUCTOR = PAC_WEB_UTIL.FUN_CODIGO_CORREDOR(?) ");
			sql.append("AND (E.CACW_NU_ENDOSO = 0 ");
			sql.append("OR ");
			sql.append("(E.CACW_NU_ENDOSO > 0 ");
			sql.append("AND EXISTS  ");
			sql.append("(SELECT 1 ");
			sql.append("FROM RECTOR.CART_CERTIFICADOS_ENDOSOS EE ");
			sql.append("WHERE EE.CACW_CAPO_NU_POLIZA = E.CACW_CAPO_NU_POLIZA ");
			sql.append("AND EE.CACW_CARP_CD_RAMO = E.CACW_CARP_CD_RAMO ");
			sql.append("AND EE.CACW_CACE_NU_CERTIFICADO = E.CACW_CACE_NU_CERTIFICADO ");
			sql.append("AND E.CACW_NU_ENDOSO = EE.CACW_NU_ENDOSO + 1 ");
			sql.append("AND EE.CACW_CAMD_CD_MEDIO_PAGO <> E.CACW_CAMD_CD_MEDIO_PAGO))) ");
			sql.append("AND TRUNC(E.CACW_FE_DESDE) BETWEEN ? AND ? ");
			sql.append("ORDER BY E.CACW_CAMD_CD_MEDIO_PAGO, E.CACW_COTC_CD_ORIGEN, E.CACW_CARP_CD_RAMO, E.CACW_CAPO_NU_POLIZA ");
			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, param.getUsuario());
			pst.setDate(2, toSqlDate(param.getFechaDesde()));
			pst.setDate(3, toSqlDate(param.getFechaHasta()));
			result = pst.executeQuery();

			while (result.next()) {
				String medioPago = result.getString("CACW_CAMD_CD_MEDIO_PAGO");
				String origen = result.getString("CACW_COTC_CD_ORIGEN");
				Integer codRamo = result.getInt("CACW_CARP_CD_RAMO");
				Integer numPoliza = result.getInt("CACW_CAPO_NU_POLIZA");
				String fechaDesde = ValuesUtils.toString(result.getDate("CACW_FE_DESDE"));

				resultado.setUno(new ReportePolizasAdheridasDebitoAutomatico(numPoliza, codRamo, fechaDesde, medioPago, origen));
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

	public ResultObtenerLogsUsuariosProductor obtenerLogsUsuariosProductor(final ParamObtenerLogsUsuariosProductor param) {
		// FIXME OIGRES FALTA:::n usa
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ConsultaReportesPersist.class);
		logueo.setMetodo("obtenerLogsUsuariosProductor");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Numero poliza", param.getNumPoliza());
		logueo.setParametro("Fecha desde", param.getFechaDesde());
		logueo.setParametro("Fecha hasta", param.getFechaHasta());
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultObtenerLogsUsuariosProductor resultado = new ResultObtenerLogsUsuariosProductor();

		try {
			conn = crearConexion();

			final StringBuffer sql = new StringBuffer(230);

			sql.append("SELECT CAAP_ID_LOG , CAAP_CAUP_CD_USUARIO, CAAP_GEPO_CD_PROGRAMA, CAAP_TIPO_ACCION, TO_CHAR(CAAP_FE_LOG,'DD/MM/YYYY HH24:MI:SS') FECHALOG, ");
			sql.append("CAAP_CAZB_NU_COTIZACION, CAAP_CAZB_NU_CONSECUTIVO, CAAP_CARP_CD_RAMO, CAAP_CAPO_NU_POLIZA, CAAP_CACE_NU_CERTIFICADO, CAAP_CACW_NU_ENDOSO, CAAP_CABU_NU_PERSONA, ");
			sql.append("CAAP_DATOS_AMPLIADOS ");
			sql.append("FROM CART_ACTIVIDAD_PRODUCTOR ");
			sql.append("WHERE CAAP_CAUP_CD_USUARIO = NVL(PAC_WEB_UTIL.FUN_USUARIO_RECTOR(?),CAAP_CAUP_CD_USUARIO)  ");
			sql.append("AND CAAP_CAUS_CD_USUARIO = DECODE(?,NULL,PAC_WEB_UTIL.FUN_USUARIO_RECTOR(?),NULL) ");
			sql.append("AND CAAP_CAUP_CD_USUARIO<>CAAP_CAUS_CD_USUARIO ");
			sql.append("AND CAAP_CARP_CD_RAMO=NVL(?,CAAP_CARP_CD_RAMO) AND CAAP_CAPO_NU_POLIZA=NVL(?,CAAP_CAPO_NU_POLIZA) ");
			sql.append("AND TRUNC(CAAP_FE_LOG) BETWEEN ? AND ? ");
			sql.append("ORDER BY CAAP_ID_LOG ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, param.getUsuario());
			pst.setString(2, param.getUsuario());
			pst.setString(3, param.getUsuario());
			if (param.getCodRamo() != null) {
				pst.setInt(4, param.getCodRamo());
			} else {
				pst.setNull(4, java.sql.Types.INTEGER);
			}

			if (param.getNumPoliza() != null) {
				pst.setInt(5, param.getNumPoliza());
			} else {
				pst.setNull(5, java.sql.Types.INTEGER);
			}

			pst.setDate(6, toSqlDate(param.getFechaDesde()));
			pst.setDate(7, toSqlDate(param.getFechaHasta()));
			result = pst.executeQuery();
			while (result.next()) {

				Integer numEndoso = result.getInt("CAAP_CACW_NU_ENDOSO");
				Integer numCertificado = result.getInt("CAAP_CACE_NU_CERTIFICADO");
				Integer numPersona = result.getInt("CAAP_CABU_NU_PERSONA");
				Integer numCotizacion = result.getInt("CAAP_CAZB_NU_COTIZACION");
				Integer numConsecutivo = result.getInt("CAAP_CAZB_NU_CONSECUTIVO");
				String datosAmpliados = result.getString("CAAP_DATOS_AMPLIADOS");
				Integer codRamo = result.getInt("CAAP_CARP_CD_RAMO");
				Integer numPoliza = result.getInt("CAAP_CAPO_NU_POLIZA");
				String fechaHora = result.getString("FECHALOG");
				String programa = result.getString("CAAP_GEPO_CD_PROGRAMA");
				String codUsuario = result.getString("CAAP_CAUP_CD_USUARIO");
				String tipoAccion = result.getString("CAAP_TIPO_ACCION");
				String idLog = result.getString("CAAP_ID_LOG");
				resultado.setUno(new ReporteLogsUsuariosProductor(datosAmpliados, numCotizacion, numPoliza, numCertificado, numConsecutivo, codRamo, numEndoso, numPersona, idLog, codUsuario,
						programa, tipoAccion, fechaHora));
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

	public ResultXmlPL obtenerResumenEmision(ParamObtenerResumenEmision param) {
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ConsultaReportesPersist.class);
		logueo.setMetodo("obtenerResumenEmision");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("MesAnio", param.getMesAnio());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Codigo producto", param.getCodProducto());
		logueo.setParametro("Reporte", param.getReporte());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_detalle_renovacion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, param.getMesAnio());

			if (param.getCodRamo() != null) {
				cstmt.setInt(2, param.getCodRamo());
			} else {
				cstmt.setNull(2, Types.INTEGER);
			}

			cstmt.setString(3, param.getCodProducto());
			cstmt.setString(4, param.getReporte());

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

	/*
    PROCEDURE PRO_TOTALES_RENOVACION(p_periodo               IN VARCHAR2,
            p_cd_ramo               IN CART_RAMOS_POLIZAS.CARP_CD_RAMO%TYPE,
            p_cd_producto           IN CART_PRODUCTOS.CAPU_CD_PRODUCTO%TYPE,         
            p_cd_usuario            IN COTW_USUARIOS_MAPPING.COUM_CD_USU_DOMINIO%TYPE,
            p_cd_productor          IN CART_COTIZA_BANCO.CAZB_CAPD_CD_PRODUCTOR%TYPE, --Productor Hijo o Padre mismo
            p_cod_error            OUT NUMBER,
            p_desc_error           OUT VARCHAR2,
            p_sql_error            OUT VARCHAR2,
            p_datos                OUT CLOB
           );
*/
	public ResultXmlPL obtenerTotalesResumenEmision(ParamObtenerTotalesResumenEmision param) {
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ConsultaReportesPersist.class);
		logueo.setMetodo("obtenerTotalesResumenEmision");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		logueo.setParametro("MesAnio", param.getMesAnio());
		logueo.setParametro("Codigo ramo", param.getCodRamo());
		logueo.setParametro("Codigo producto", param.getCodProducto());

		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_totales_renovacion");
			logueo.setNombrePl(nombrePL);

			cstmt = conn.prepareCall("{call " + nombrePL + "(?,?,?,?,?,?,?,?,?)}");
			cstmt.setString(1, param.getMesAnio());

			if (param.getCodRamo() != null) {
				cstmt.setInt(2, param.getCodRamo());
			} else {
				cstmt.setNull(2, Types.INTEGER);
			}

			cstmt.setString(3, param.getCodProducto());

			cstmt.setString(4, param.getUsuario());
			
			if(param.getCodProductor()!=null){
				cstmt.setInt(5, Integer.valueOf(param.getCodProductor()));
			}else{
				cstmt.setNull(5, Types.INTEGER);
			}

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

	public ResultCodiguera obtenerListaReportesResumenEmision(ParamListaReportesResumenEmision param) {
		ResultCodiguera resultado = new ResultCodiguera();
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ConsultaReportesPersist.class);
		logueo.setMetodo("obtenerListaReportesResumenEmision");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		log.info(logueo.getSoloParametros());
		resultado.setUno(crearCodiguera("AUTOMATICAS", "POLIZAS PENDIENTES DE RENOVACION AUTOMATICA"));
		resultado.setUno(crearCodiguera("MANUALES", "POLIZAS PENDIENTES DE RENOVACION MANUAL"));
		resultado.setUno(crearCodiguera("MODIFICADAS", "POLIZAS RENOVADAS MODIFICADAS"));
		resultado.setUno(crearCodiguera("POL_ANULADAS", "POLIZAS ANULADAS"));
		resultado.setUno(crearCodiguera("NORENUEVAN", "POLIZAS NO RENUEVAN"));
		resultado.setUno(crearCodiguera("NUEVAS", "POLIZAS NUEVAS"));
		resultado.setUno(crearCodiguera("POLIZ_SIN_COT", "POLIZAS SIN COTIZACION"));

		return resultado;

	}

	private Codiguera crearCodiguera(String codigo, String descripcion) {
		Codiguera codiguera = new Codiguera();
		codiguera.setCodigo(codigo);
		codiguera.setDescripcion(descripcion);
		return codiguera;
	}

	public ResultObtenerNombreFicheros obtenerNombreFicheros(ParamObtenerNombreFicheros param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ConsultaReportesPersist.class);
		logueo.setMetodo("obtenerNombreFicheros");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultObtenerNombreFicheros resultado = new ResultObtenerNombreFicheros();
		String ruta = manager.obtenerValor("serviciosRector.ruta_descarga_ficheros");
		String carpeta = manager.obtenerValor("serviciosRector.carpeta_descargas");
		logueo.setParametro("Ruta", ruta);
		logueo.setParametro("Carpeta", carpeta);
		LOG.info(logueo.getSoloParametros());
		File folderFile = new File(ruta + param.getUsuario().toLowerCase() + "/" + carpeta);
		String tokenid = "token=" + param.getClave() + "&id=";
		if ((folderFile.exists())) {
			File[] files = folderFile.listFiles();
			if (files != null) {
				for (File file : files) {
					if (!file.isDirectory()) {
						DatoFichero fichero = new DatoFichero();
						fichero.setNombre(file.getName());
						fichero.setUltimaModificacion(file.lastModified());
						int i = file.getName().lastIndexOf('.');
						if (i > 0) {
							fichero.setExtension(file.getName().substring(i + 1));
						}
						fichero.setUri(tokenid + file.getName());
						resultado.setUno(fichero);
					}
				}
			}
			Collections.sort(resultado.getFicheros(), new DatoFicheroComparator());
		} else {
			resultado.setHayError(Boolean.TRUE);
			resultado.setError(ErrorResolver.getError(Values.CONFIGURATION_EXCEPTION));
		}
		return resultado;
	}

	public ResultObtenerRutaDescargaUsuario obtenerRutaDescargaUsuario(ParamObtenerRutaDescargaUsuario param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ConsultaReportesPersist.class);
		logueo.setMetodo("obtenerRutaDescargaUsuario");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		ResultObtenerRutaDescargaUsuario resultado = new ResultObtenerRutaDescargaUsuario();
		String ruta = manager.obtenerValor("serviciosRector.ruta_descarga_ficheros");
		String carpeta = manager.obtenerValor("serviciosRector.carpeta_descargas");
		logueo.setParametro("Ruta", ruta);
		logueo.setParametro("Carpeta", carpeta);
		LOG.info(logueo.getSoloParametros());
		if (ruta == null) {
			resultado.setHayError(Boolean.TRUE);
			resultado.setError(ErrorResolver.getError(Values.CONFIGURATION_EXCEPTION));
		} else {
			resultado.setHayError(Boolean.FALSE);
			if (ruta != null && carpeta != null) {
				resultado.setRuta(ruta + param.getUsuario().toLowerCase() + "/" + carpeta + "/");
			} else {
				resultado.setRuta(ruta + param.getUsuario().toLowerCase() + "/");
			}

		}
		return resultado;
	}

	public ResultObtenerSeguroAutomovil obtenerSeguroAutomovil(final ParamObtenerSeguroAutomovil param) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ConsultaReportesPersist.class);
		logueo.setMetodo("obtenerSeguroAutomovil");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultObtenerSeguroAutomovil resultado = new ResultObtenerSeguroAutomovil();

		try {
			conn = crearConexion();

			final StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("ICPOL.ICPO_POLIZA_ID ID_POLIZA, ");
			sql.append("ICPOL.ICPO_RAMO RAMO, ");
			sql.append("ICPOL.ICPO_CD_CLIENTE NRO_CLIENTE, ");
			sql.append("ICPOL.ICPO_CD_PRODUCTOR PRODUCTOR, ");
			sql.append("ICPOL.ICPO_FE_OPERACION EMISION, ");
			sql.append("ICPOL.ICPO_FE_INICIO_VIG INICIO_VIG, ");
			sql.append("ICPOL.ICPO_FE_FIN_VIG FIN_VIG, ");
			sql.append("ICPOL.ICPO_NU_POLIZA POLIZA, ");
			sql.append("ICPOL.ICPO_CERTIFICADO CERTIFICADO, ");

			sql.append("(SELECT RV_MEANING ");
			sql.append(" FROM CG_REF_CODES ");
			sql.append("WHERE RV_DOMAIN LIKE 'TP_TRANSAC' ");
			sql.append("AND RV_LOW_VALUE = CCEND.CACW_CAME_TP_TRANSAC) TRAMITE, ");

			sql.append("DECODE(CCEND.CACW_CAME_TP_TRANSAC,'A', (SELECT CAME_DE_MOTIVO ");
			sql.append(" FROM CART_MOTIVOS_ENDOSOS ");
			sql.append("WHERE CAME_TP_TRANSAC=CCEND.CACW_CAME_TP_TRANSAC ");
			sql.append("AND CAME_CD_MOTIVO=CCEND.CACW_CAME_CD_MOTIVO),NULL) MOTIVO_ANULACION, ");

			sql.append(" GCOPO.GECP_DE_LOCALIDAD ||' '|| CPEDI.CADO_DE_CALLE ||' '|| CPEDI.CADO_DE_NUMERO ||' '|| CPEDI.CADO_DE_PISO ||' '|| CPEDI.CADO_DE_DEPARTAMENTO DIR_COBRO, ");

			sql.append("(SELECT CRTB_DE_DATO ");
			sql.append("FROM CRET_PRODUCTOS_DATOS,CRET_TABLAS ");
			sql.append(" WHERE CRPD_CASU_CD_SUCURSAL=CCEND.CACW_CASU_CD_SUCURSAL ");
			sql.append("AND CRPD_CARP_CD_RAMO=CCEND.CACW_CARP_CD_RAMO ");
			sql.append("AND CRPD_CAPO_NU_POLIZA=CCEND.CACW_CAPO_NU_POLIZA ");
			sql.append(" AND CRPD_CACE_NU_CERTIFICADO=CCEND.CACW_CACE_NU_CERTIFICADO ");
			sql.append("AND CRPD_NU_ENDOSO=CCEND.CACW_NU_ENDOSO ");
			sql.append("AND CRPD_CAPU_CD_PRODUCTO=CCEND.CACW_CAPU_CD_PRODUCTO ");
			sql.append("AND CRPD_CRCD_CD_DATO=CRTB_CD_TABLA ");
			sql.append("AND CRPD_DE_DATO=CRTB_INDEX ");
			sql.append("AND CRPD_CD_BIEN_ASEG=1 ");
			sql.append("AND CRPD_CRCD_CD_DATO=140001) MARCA, ");

			sql.append("(SELECT CRTB_DE_DATO ");
			sql.append("FROM CRET_PRODUCTOS_DATOS,CRET_TABLAS  ");
			sql.append("WHERE CRPD_CASU_CD_SUCURSAL=CCEND.CACW_CASU_CD_SUCURSAL  ");
			sql.append("AND CRPD_CARP_CD_RAMO=CCEND.CACW_CARP_CD_RAMO  ");
			sql.append("AND CRPD_CAPO_NU_POLIZA=CCEND.CACW_CAPO_NU_POLIZA ");
			sql.append("AND CRPD_CACE_NU_CERTIFICADO=CCEND.CACW_CACE_NU_CERTIFICADO  ");
			sql.append("AND CRPD_NU_ENDOSO=CCEND.CACW_NU_ENDOSO ");
			sql.append("AND CRPD_CAPU_CD_PRODUCTO=CCEND.CACW_CAPU_CD_PRODUCTO  ");
			sql.append("AND CRPD_CRCD_CD_DATO=CRTB_CD_TABLA  ");
			sql.append("AND CRPD_DE_DATO=CRTB_INDEX  ");
			sql.append("AND CRPD_CD_BIEN_ASEG=1  ");
			sql.append("AND CRPD_CRCD_CD_DATO=140002) MODELO,  ");

			sql.append("(SELECT CRPD_DATO ");
			sql.append(" FROM CRET_PRODUCTOS_DATOS ");
			sql.append("WHERE CRPD_CASU_CD_SUCURSAL=CCEND.CACW_CASU_CD_SUCURSAL  ");
			sql.append("AND CRPD_CARP_CD_RAMO=CCEND.CACW_CARP_CD_RAMO  ");
			sql.append("AND CRPD_CAPO_NU_POLIZA=CCEND.CACW_CAPO_NU_POLIZA  ");
			sql.append("AND CRPD_CACE_NU_CERTIFICADO=CCEND.CACW_CACE_NU_CERTIFICADO  ");
			sql.append("AND CRPD_NU_ENDOSO=CCEND.CACW_NU_ENDOSO  ");
			sql.append("AND CRPD_CAPU_CD_PRODUCTO=CCEND.CACW_CAPU_CD_PRODUCTO  ");
			sql.append("AND CRPD_CD_BIEN_ASEG=1  ");
			sql.append("AND CRPD_CRCD_CD_DATO=140003) ANIO,  ");

			sql.append("(SELECT CRPD_DE_DATO FROM CRET_PRODUCTOS_DATOS  ");
			sql.append("WHERE CRPD_CASU_CD_SUCURSAL=CCEND.CACW_CASU_CD_SUCURSAL  ");
			sql.append("AND CRPD_CARP_CD_RAMO=CCEND.CACW_CARP_CD_RAMO  ");
			sql.append("AND CRPD_CAPO_NU_POLIZA=CCEND.CACW_CAPO_NU_POLIZA  ");
			sql.append("AND CRPD_CACE_NU_CERTIFICADO=CCEND.CACW_CACE_NU_CERTIFICADO  ");
			sql.append("AND CRPD_NU_ENDOSO=CCEND.CACW_NU_ENDOSO  ");
			sql.append("AND CRPD_CAPU_CD_PRODUCTO=CCEND.CACW_CAPU_CD_PRODUCTO ");
			sql.append("AND CRPD_CD_BIEN_ASEG=1  ");
			sql.append("AND CRPD_CRCD_CD_DATO=142001) COMBUSTIBLE,  ");

			sql.append("(SELECT CRPD_DE_DATO  ");
			sql.append("FROM CRET_PRODUCTOS_DATOS  ");
			sql.append("WHERE CRPD_CASU_CD_SUCURSAL=CCEND.CACW_CASU_CD_SUCURSAL  ");
			sql.append("AND CRPD_CARP_CD_RAMO=CCEND.CACW_CARP_CD_RAMO  ");
			sql.append("AND CRPD_CAPO_NU_POLIZA=CCEND.CACW_CAPO_NU_POLIZA  ");
			sql.append("AND CRPD_CACE_NU_CERTIFICADO=CCEND.CACW_CACE_NU_CERTIFICADO  ");
			sql.append("AND CRPD_NU_ENDOSO=CCEND.CACW_NU_ENDOSO  ");
			sql.append("AND CRPD_CAPU_CD_PRODUCTO=CCEND.CACW_CAPU_CD_PRODUCTO ");
			sql.append("AND CRPD_CD_BIEN_ASEG=1  ");
			sql.append("AND CRPD_CRCD_CD_DATO=141001) MATRICULA, ");

			sql.append("(SELECT CRPD_DE_DATO  ");
			sql.append("FROM CRET_PRODUCTOS_DATOS ");
			sql.append("WHERE CRPD_CASU_CD_SUCURSAL=CCEND.CACW_CASU_CD_SUCURSAL  ");
			sql.append("AND CRPD_CARP_CD_RAMO=CCEND.CACW_CARP_CD_RAMO ");
			sql.append("AND CRPD_CAPO_NU_POLIZA=CCEND.CACW_CAPO_NU_POLIZA  ");
			sql.append("AND CRPD_CACE_NU_CERTIFICADO=CCEND.CACW_CACE_NU_CERTIFICADO  ");
			sql.append("AND CRPD_NU_ENDOSO=CCEND.CACW_NU_ENDOSO ");
			sql.append("AND CRPD_CAPU_CD_PRODUCTO=CCEND.CACW_CAPU_CD_PRODUCTO  ");
			sql.append(" AND CRPD_CD_BIEN_ASEG=1  ");
			sql.append("AND CRPD_CRCD_CD_DATO=141003) MOTOR,  ");

			sql.append("(SELECT CRPD_DE_DATO  ");
			sql.append("FROM CRET_PRODUCTOS_DATOS  ");
			sql.append("WHERE CRPD_CARP_CD_RAMO=CCEND.CACW_CARP_CD_RAMO  ");
			sql.append("AND CRPD_CAPO_NU_POLIZA=CCEND.CACW_CAPO_NU_POLIZA  ");
			sql.append("AND CRPD_CACE_NU_CERTIFICADO=CCEND.CACW_CACE_NU_CERTIFICADO  ");
			sql.append("AND CRPD_NU_ENDOSO=CCEND.CACW_NU_ENDOSO  ");
			sql.append("AND CRPD_CAPU_CD_PRODUCTO=CCEND.CACW_CAPU_CD_PRODUCTO  ");
			sql.append("AND CRPD_CD_BIEN_ASEG=1  ");
			sql.append("AND CRPD_CRCD_CD_DATO=141002) PADRON,  ");

			sql.append("(SELECT CRPD_DE_DATO  ");
			sql.append("FROM CRET_PRODUCTOS_DATOS ");
			sql.append("WHERE CRPD_CARP_CD_RAMO=CCEND.CACW_CARP_CD_RAMO  ");
			sql.append("AND CRPD_CAPO_NU_POLIZA=CCEND.CACW_CAPO_NU_POLIZA  ");
			sql.append("AND CRPD_CACE_NU_CERTIFICADO=CCEND.CACW_CACE_NU_CERTIFICADO  ");
			sql.append("AND CRPD_NU_ENDOSO=CCEND.CACW_NU_ENDOSO  ");
			sql.append("AND CRPD_CAPU_CD_PRODUCTO=CCEND.CACW_CAPU_CD_PRODUCTO  ");
			sql.append("AND CRPD_CD_BIEN_ASEG=1  ");
			sql.append("AND CRPD_CRCD_CD_DATO=141004) CHASIS, ");

			sql.append("(SELECT CRTB_DE_DATO ");
			sql.append(" FROM CRET_PRODUCTOS_DATOS,CRET_TABLAS, CRET_DATOS ");
			sql.append("WHERE CRPD_CASU_CD_SUCURSAL=CCEND.CACW_CASU_CD_SUCURSAL ");
			sql.append("AND CRPD_CARP_CD_RAMO=CCEND.CACW_CARP_CD_RAMO  ");
			sql.append("AND CRPD_CAPO_NU_POLIZA=CCEND.CACW_CAPO_NU_POLIZA  ");
			sql.append("AND CRPD_CACE_NU_CERTIFICADO=CCEND.CACW_CACE_NU_CERTIFICADO  ");
			sql.append("AND CRPD_NU_ENDOSO=CCEND.CACW_NU_ENDOSO  ");
			sql.append("AND CRPD_CAPU_CD_PRODUCTO=CCEND.CACW_CAPU_CD_PRODUCTO  ");
			sql.append("AND CRCD_CD_TABLA_VALID=CRTB_CD_TABLA  ");
			sql.append("AND CRPD_CRCD_CD_DATO=CRCD_CD_DATO  ");
			sql.append("AND CRPD_DE_DATO=CRTB_INDEX  ");
			sql.append("AND CRPD_CD_BIEN_ASEG=1  ");
			sql.append("AND CRPD_CRCD_CD_DATO=141007) CALIDAD, ");

			sql.append("(SELECT ");
			sql.append("(RTRIM(XMLAGG(XMLELEMENT(E,REPLACE(CARA.CAAC_DE_ACREEDOR,',',' ')||', '|| ");
			sql.append("NVL((SELECT CPCOM.CACF_DE_COMUNICACION  ");
			sql.append("FROM CART_PERSONAS_COMUNICACION CPCOM  ");
			sql.append("WHERE CARA.CACC_CABU_NU_PERSONA=CPCOM.CACF_CABU_NU_PERSONA  ");
			sql.append("AND CPCOM.CACF_CATN_CD_TIPO_COMUNICACION=1  ");
			sql.append("AND CPCOM.CACF_NU_CONSECUTIVO=(SELECT MAX(CPCOM1.CACF_NU_CONSECUTIVO)  ");
			sql.append("FROM CART_PERSONAS_COMUNICACION CPCOM1  ");
			sql.append("WHERE CPCOM1.CACF_CABU_NU_PERSONA=CPCOM.CACF_CABU_NU_PERSONA  ");
			sql.append("AND CPCOM1.CACF_CATN_CD_TIPO_COMUNICACION=1)  ");
			sql.append("),NULL)|| ', ')  ");
			sql.append("ORDER BY CRAC.CRAC_CARP_CD_RAMO, CRAC.CRAC_CAPO_NU_POLIZA, CRAC.CRAC_CACE_NU_CERTIFICADO, CRAC.CRAC_NU_ENDOSO).EXTRACT(LOWER('//TEXT()')), ', '))  ");
			sql.append("FROM CRET_ACREEDORES CRAC, CART_ROL_ACREEDORES CARA, CART_PERSONAS_ROLES CAPR ");
			sql.append("WHERE CCEND.CACW_CARP_CD_RAMO = CRAC.CRAC_CARP_CD_RAMO  ");
			sql.append("AND CCEND.CACW_CAPO_NU_POLIZA = CRAC.CRAC_CAPO_NU_POLIZA ");
			sql.append("AND CCEND.CACW_CACE_NU_CERTIFICADO = CRAC.CRAC_CACE_NU_CERTIFICADO ");
			sql.append("AND CCEND.CACW_NU_ENDOSO = CRAC.CRAC_NU_ENDOSO  ");
			sql.append("AND CRAC.CRAC_CRCI_CD_BIEN_ASEG = 1  ");
			sql.append("AND CRAC.CRAC_CAAC_CD_ACREEDOR = CARA.CAAC_CD_ACREEDOR  ");
			sql.append("AND CARA.CACC_CABU_NU_PERSONA = CAPR.CACL_CABU_NU_PERSONA  ");
			sql.append("AND CAPR.CACL_CATL_CD_TIPO_ROL = 5  ");
			sql.append("GROUP BY CRAC.CRAC_CARP_CD_RAMO, CRAC.CRAC_CAPO_NU_POLIZA, CRAC.CRAC_CACE_NU_CERTIFICADO, CRAC.CRAC_NU_ENDOSO ) CESIONARIOS_TELEFONOS, ");
			sql.append("ICPOL.ICPO_PLAN PLAN, ");

			sql.append("(SELECT CRTB_DE_DATO  ");
			sql.append("FROM CRET_PRODUCTOS_DATOS, CRET_TABLAS, CRET_DATOS  ");
			sql.append("WHERE CRPD_CASU_CD_SUCURSAL=CCEND.CACW_CASU_CD_SUCURSAL  ");
			sql.append("AND CRPD_CARP_CD_RAMO=CCEND.CACW_CARP_CD_RAMO  ");
			sql.append("AND CRPD_CAPO_NU_POLIZA=CCEND.CACW_CAPO_NU_POLIZA ");
			sql.append("AND CRPD_CACE_NU_CERTIFICADO=CCEND.CACW_CACE_NU_CERTIFICADO  ");
			sql.append("AND CRPD_NU_ENDOSO=CCEND.CACW_NU_ENDOSO  ");
			sql.append("AND CRPD_CAPU_CD_PRODUCTO=CCEND.CACW_CAPU_CD_PRODUCTO  ");
			sql.append("AND CRCD_CD_TABLA_VALID=CRTB_CD_TABLA  ");
			sql.append("AND CRPD_CRCD_CD_DATO=CRCD_CD_DATO  ");
			sql.append("AND CRPD_DE_DATO=CRTB_INDEX  ");
			sql.append("AND CRPD_CD_BIEN_ASEG=1  ");
			sql.append("AND CRPD_CRCD_CD_DATO=140007) ZONA_CIRCULA,  ");

			sql.append("(SELECT CRPD_DATO  ");
			sql.append("FROM CRET_PRODUCTOS_DATOS ");
			sql.append("WHERE CRPD_CASU_CD_SUCURSAL=CCEND.CACW_CASU_CD_SUCURSAL  ");
			sql.append("AND CRPD_CARP_CD_RAMO=CCEND.CACW_CARP_CD_RAMO  ");
			sql.append("AND CRPD_CAPO_NU_POLIZA=CCEND.CACW_CAPO_NU_POLIZA  ");
			sql.append("AND CRPD_CACE_NU_CERTIFICADO=CCEND.CACW_CACE_NU_CERTIFICADO  ");
			sql.append("AND CRPD_NU_ENDOSO=CCEND.CACW_NU_ENDOSO  ");
			sql.append("AND CRPD_CAPU_CD_PRODUCTO=CCEND.CACW_CAPU_CD_PRODUCTO  ");
			sql.append("AND CRPD_CD_BIEN_ASEG=1 ");
			sql.append("AND CRPD_CRCD_CD_DATO=140008) DEDUCIBLE , ");

			sql.append("ICPOL.ICPO_MONEDA MON_DEDUCIBLE, ");

			sql.append("(SELECT CRC.CARC_MT_DEDUCIBLE  ");
			sql.append("FROM CARH_RIESGOS_CUBIERTOS CRC ");
			sql.append("WHERE CCEND.CACW_CACE_NU_CERTIFICADO=CRC.CARC_CACE_NU_CERTIFICADO  ");
			sql.append("AND CACW_CAPO_NU_POLIZA=CRC.CARC_CAPO_NU_POLIZA ");
			sql.append("AND CCEND.CACW_CASU_CD_SUCURSAL=CRC.CARC_CASU_CD_SUCURSAL  ");
			sql.append("AND CCEND.CACW_CARP_CD_RAMO=CRC.CARC_CARP_CD_RAMO  ");
			sql.append("AND CCEND.CACW_NU_ENDOSO=CRC.CARC_NU_ENDOSO  ");
			sql.append("AND CRC.CARC_MT_DEDUCIBLE=(SELECT MAX(CARC_MT_DEDUCIBLE) ");
			sql.append("FROM CARH_RIESGOS_CUBIERTOS  ");
			sql.append("WHERE CARC_CACE_NU_CERTIFICADO=CRC.CARC_CACE_NU_CERTIFICADO  ");
			sql.append("AND CARC_CAPO_NU_POLIZA=CRC.CARC_CAPO_NU_POLIZA  ");
			sql.append("AND CRC.CARC_CASU_CD_SUCURSAL=CRC.CARC_CASU_CD_SUCURSAL ");
			sql.append("AND CRC.CARC_CARP_CD_RAMO=CRC.CARC_CARP_CD_RAMO  ");
			sql.append("AND CARC_NU_ENDOSO=CRC.CARC_NU_ENDOSO) ");
			sql.append("AND ROWNUM=1) VAL_DEDUCIBLE,  ");

			sql.append("(SELECT CRPD_DE_DATO  ");
			sql.append("FROM CRET_PRODUCTOS_DATOS, CRET_DATOS, CRET_TABLAS  ");
			sql.append("WHERE CRPD_CASU_CD_SUCURSAL=CCEND.CACW_CASU_CD_SUCURSAL  ");
			sql.append("AND CRPD_CARP_CD_RAMO=CCEND.CACW_CARP_CD_RAMO ");
			sql.append("AND CRPD_CAPO_NU_POLIZA=CCEND.CACW_CAPO_NU_POLIZA  ");
			sql.append("AND CRPD_CACE_NU_CERTIFICADO=CCEND.CACW_CACE_NU_CERTIFICADO  ");
			sql.append("AND CRPD_NU_ENDOSO=CCEND.CACW_NU_ENDOSO ");
			sql.append("AND CRPD_CAPU_CD_PRODUCTO=CCEND.CACW_CAPU_CD_PRODUCTO  ");
			sql.append("AND CRPD_CD_BIEN_ASEG IN (2,3)  ");
			sql.append("AND CRPD_CRCD_CD_DATO=140009  ");
			sql.append("AND CRCD_CD_DATO=CRPD_CRCD_CD_DATO  ");
			sql.append("AND CRCD_CD_TABLA_VALID=CRTB_CD_TABLA  ");
			sql.append("AND CRPD_DE_DATO=CRTB_INDEX  ");
			sql.append(") RC, ");

			sql.append("(SELECT ICPOLANT.ICPO_POLIZA_ID  ");
			sql.append("FROM CONSULTAS_SQL.IC_POLIZAS ICPOLANT, CART_POLIZAS CP  ");

			sql.append("WHERE CCERT.CACE_CARP_CD_RAMO=CP.CAPO_CARP_CD_RAMO  ");
			sql.append(" AND CCERT.CACE_CAPO_NU_POLIZA=CP.CAPO_NU_POLIZA  ");

			sql.append("AND CP.CAPO_CARP_CD_RAMO=ICPOLANT.ICPO_RAMO  ");
			sql.append("AND CP.CAPO_NU_POLIZA_ANTERIOR=ICPOLANT.ICPO_NU_POLIZA  ");
			sql.append("AND ROWNUM=1) ID_POLIZA_ANTERIOR, ");

			sql.append("ICPO_PORCENT_BONIF_COMERCIAL BONIF_COMERCIAL, ");

			sql.append("(SELECT CRPD_DE_DATO  ");
			sql.append("FROM CRET_PRODUCTOS_DATOS  ");
			sql.append("WHERE CRPD_CASU_CD_SUCURSAL=CCEND.CACW_CASU_CD_SUCURSAL  ");
			sql.append("AND CRPD_CARP_CD_RAMO=CCEND.CACW_CARP_CD_RAMO  ");
			sql.append("AND CRPD_CAPO_NU_POLIZA=CCEND.CACW_CAPO_NU_POLIZA  ");
			sql.append("AND CRPD_CACE_NU_CERTIFICADO=CCEND.CACW_CACE_NU_CERTIFICADO  ");
			sql.append("AND CRPD_NU_ENDOSO=CCEND.CACW_NU_ENDOSO  ");
			sql.append("AND CRPD_CAPU_CD_PRODUCTO=CCEND.CACW_CAPU_CD_PRODUCTO  ");
			sql.append("AND CRPD_CD_BIEN_ASEG=1  ");
			sql.append("AND CRPD_CRCD_CD_DATO=141024) NIVEL_BONUS, ");

			sql.append("(SELECT CRPD_DE_DATO  ");
			sql.append("FROM CRET_PRODUCTOS_DATOS  ");
			sql.append("WHERE CRPD_CASU_CD_SUCURSAL=CCEND.CACW_CASU_CD_SUCURSAL  ");
			sql.append("AND CRPD_CARP_CD_RAMO=CCEND.CACW_CARP_CD_RAMO  ");
			sql.append("AND CRPD_CAPO_NU_POLIZA=CCEND.CACW_CAPO_NU_POLIZA  ");
			sql.append("AND CRPD_CACE_NU_CERTIFICADO=CCEND.CACW_CACE_NU_CERTIFICADO ");
			sql.append("AND CRPD_NU_ENDOSO=CCEND.CACW_NU_ENDOSO ");
			sql.append("AND CRPD_CAPU_CD_PRODUCTO=CCEND.CACW_CAPU_CD_PRODUCTO ");
			sql.append("AND CRPD_CD_BIEN_ASEG=1 ");
			sql.append("AND CRPD_CRCD_CD_DATO=905044) PCT_PROMOCION, ");

			sql.append("ICPOL.ICPO_MONEDA MONEDA, ");
			sql.append("ICPOL.ICPO_CANTIDAD_CUOTAS CUOTAS,  ");
			sql.append("ICPOL.ICPO_IMPORTE_PRIMA PREMIO,  ");
			sql.append("ICPOL.ICPO_IMPORTE_PREMIO TOTAL, ");
			sql.append("ICPOL.ICPO_DE_MEDIO_PAGO FORMA_PAGO  ");

			sql.append("FROM CART_CERTIFICADOS CCERT,  ");
			sql.append("CART_CERTIFICADOS_ENDOSOS CCEND,  ");
			sql.append("CONSULTAS_SQL.IC_POLIZAS ICPOL,  ");
			sql.append("CART_PERSONAS_DIRECCIONES CPEDI,  ");
			sql.append(" GENT_CODIGOS_POSTALES GCOPO,  ");
			sql.append("CART_CLIENTES CCLI,  ");
			sql.append("CART_USUARIOS CUS  ");

			sql.append("WHERE CCERT.CACE_CARP_CD_RAMO = ICPOL.ICPO_RAMO  ");
			sql.append("AND CCERT.CACE_CAPO_NU_POLIZA = ICPOL.ICPO_NU_POLIZA  ");
			sql.append("AND CCERT.CACE_NU_CERTIFICADO = ICPOL.ICPO_CERTIFICADO  ");

			sql.append("AND CCERT.CACE_CARP_CD_RAMO = CCEND.CACW_CARP_CD_RAMO ");
			sql.append("AND CCERT.CACE_CAPO_NU_POLIZA = CCEND.CACW_CAPO_NU_POLIZA  ");
			sql.append("AND CCERT.CACE_CAPU_CD_PRODUCTO = CCEND.CACW_CAPU_CD_PRODUCTO  ");
			sql.append("AND CCERT.CACE_CASU_CD_SUCURSAL = CCEND.CACW_CASU_CD_SUCURSAL ");
			sql.append("AND CCERT.CACE_NU_CERTIFICADO = CCEND.CACW_CACE_NU_CERTIFICADO  ");
			sql.append("AND CCEND.CACW_NU_ENDOSO = (SELECT MAX(CCEND1.CACW_NU_ENDOSO)  ");
			sql.append("FROM CART_CERTIFICADOS_ENDOSOS CCEND1  ");
			sql.append("WHERE CCEND1.CACW_CASU_CD_SUCURSAL = CCERT.CACE_CASU_CD_SUCURSAL  ");
			sql.append("AND CCEND1.CACW_CARP_CD_RAMO = CCERT.CACE_CARP_CD_RAMO  ");
			sql.append("AND CCEND1.CACW_CAPO_NU_POLIZA = CCERT.CACE_CAPO_NU_POLIZA  ");
			sql.append("AND CCEND1.CACW_CACE_NU_CERTIFICADO = CCERT.CACE_NU_CERTIFICADO  ");
			sql.append("AND CCEND1.CACW_CAME_TP_TRANSAC NOT IN ('L','C')  ");
			sql.append("AND NOT EXISTS (SELECT 1 FROM CART_CERTIFICADOS_ENDOSOS CCEND2  ");
			sql.append(" WHERE CCEND1.CACW_CARP_CD_RAMO = CCEND2.CACW_CARP_CD_RAMO  ");
			sql.append("AND CCEND1.CACW_CASU_CD_SUCURSAL = CCEND2.CACW_CASU_CD_SUCURSAL  ");
			sql.append("AND CCEND1.CACW_CAPO_NU_POLIZA = CCEND2.CACW_CAPO_NU_POLIZA  ");
			sql.append("AND (CCEND1.CACW_NU_ENDOSO = CCEND2.CACW_NU_ENDOSO OR CCEND1.CACW_NU_ENDOSO+1 = CCEND2.CACW_NU_ENDOSO)  ");
			sql.append("AND CCEND2.CACW_CACE_NU_CERTIFICADO = 0  ");
			sql.append("AND CCEND2.CACW_CAME_TP_TRANSAC = 'U'))  ");

			sql.append("AND CCLI.CACN_CABU_NU_PERSONA = CPEDI.CADO_NU_PERSONA  ");
			sql.append("AND CCERT.CACE_CADO_NU_DIRE_COBRO = CPEDI.CADO_CONSECUTIVO_DIRECCION  ");

			sql.append("AND CPEDI.CADO_GECP_NU_POSTAL = GCOPO.GECP_NU_POSTAL  ");

			sql.append("AND CCERT.CACE_CACN_NU_CEDULA_RIF = CCLI.CACN_NU_CEDULA_RIF  ");

			sql.append("AND CCERT.CACE_CARP_CD_RAMO = 4  ");
			sql.append("AND CCERT.CACE_NU_CERTIFICADO > 0  ");
			sql.append("AND ICPOL.ICPO_FE_OPERACION BETWEEN NVL(?,ICPOL.ICPO_FE_OPERACION) AND NVL(?,ICPOL.ICPO_FE_OPERACION) ");

			sql.append("AND ICPOL.ICPO_CD_PRODUCTOR = CUS.CAUS_CAPD_CD_PRODUCTOR ");
			sql.append("AND CUS.CAUS_CD_USUARIO=PAC_WEB_UTIL.FUN_USUARIO_RECTOR(?) ");

			logueo.setParametro(Values.CONSULTA, sql.toString());
			pst = conn.prepareStatement(sql.toString());
			pst.setDate(1, toSqlDate(param.getFechaDesde()));
			pst.setDate(2, toSqlDate(param.getFechaHasta()));
			pst.setString(3, param.getUsuario());
			result = pst.executeQuery();

			while (result.next()) {

				Long idPoliza = result.getLong("ID_POLIZA");
				if (result.wasNull()) {
					idPoliza = null;
				}
				Integer codRamo = result.getInt("RAMO");
				if (result.wasNull()) {
					codRamo = null;
				}
				Long nroCliente = result.getLong("NRO_CLIENTE");
				if (result.wasNull()) {
					nroCliente = null;
				}
				String productor = result.getString("PRODUCTOR");
				
				String emision =ValuesUtils.toString(result.getDate("EMISION"));
				String inicioVigencia = ValuesUtils.toString(result.getDate("INICIO_VIG"));
				String finVigencia = ValuesUtils.toString(result.getDate("FIN_VIG"));
				Long numPoliza = result.getLong("POLIZA");
				if (result.wasNull()) {
					numPoliza = null;
				}
				Integer certificado = result.getInt("CERTIFICADO");
				if (result.wasNull()) {
					certificado = null;
				}
				String motivoAnulacion = result.getString("MOTIVO_ANULACION");
				String dirCobro = result.getString("DIR_COBRO");
				String tramite = result.getString("TRAMITE");
				String marca = result.getString("MARCA");
				String modelo = result.getString("MODELO");
				Integer anio = result.getInt("ANIO");
				if (result.wasNull()) {
					anio = null;
				}
				String combustible = result.getString("COMBUSTIBLE");
				String matricula = result.getString("MATRICULA");
				String motor = result.getString("MOTOR");
				String padron = result.getString("PADRON");
				String chasis = result.getString("CHASIS");
				String calidad = result.getString("CALIDAD");
				String cesionarioTelefonos = result.getString("CESIONARIOS_TELEFONOS");
				String plan = result.getString("PLAN");
				String zonaCircula = result.getString("ZONA_CIRCULA");
				String deducible = result.getString("DEDUCIBLE");
				String monDeducible = result.getString("MON_DEDUCIBLE");
				Double valDeducible = result.getDouble("VAL_DEDUCIBLE");
				if (result.wasNull()) {
					valDeducible = null;
				}
				String rC = result.getString("RC");
				Long polizaAnterior = result.getLong("ID_POLIZA_ANTERIOR");
				if (result.wasNull()) {
					polizaAnterior = null;
				}
				Double bonifComercial = result.getDouble("BONIF_COMERCIAL");
				if (result.wasNull()) {

				}
				Double nivelBonus = result.getDouble("NIVEL_BONUS");
				if (result.wasNull()) {
					bonifComercial = null;
				}
				Double porcPromocion = result.getDouble("PCT_PROMOCION");
				if (result.wasNull()) {
					porcPromocion = null;
				}
				String moneda = result.getString("MONEDA");
				Integer cuotas = result.getInt("CUOTAS");
				if (result.wasNull()) {
					cuotas = null;
				}
				Double premio = result.getDouble("PREMIO");
				if (result.wasNull()) {
					premio = null;
				}
				Double total = result.getDouble("TOTAL");
				if (result.wasNull()) {
					total = null;
				}
				String formaPago = result.getString("FORMA_PAGO");
				resultado.setUno(new ReporteSeguroAutomovil(idPoliza, numPoliza, codRamo, nroCliente, productor, emision, inicioVigencia, finVigencia, certificado, tramite, motivoAnulacion, dirCobro,
						marca, modelo, anio, combustible, matricula, motor, padron, chasis, calidad, cesionarioTelefonos, plan, zonaCircula, deducible, monDeducible, valDeducible, rC, polizaAnterior,
						bonifComercial, nivelBonus, porcPromocion, moneda, cuotas, premio, total, formaPago));
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

	public ResultObtenerNuevaTarifaIncendioProductor  obtenerNuevaTarifaIncendioProductor(ParamObtenerNuevaTarifaIncendioProductor param) {

		
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ConsultaReportesPersist.class);
		logueo.setMetodo("obtenerNuevaTarifaIncendioProductor");
		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet result = null;
		ResultObtenerNuevaTarifaIncendioProductor  resultado = new ResultObtenerNuevaTarifaIncendioProductor ();

		try {
			conn = crearConexion();

			final StringBuffer sql = new StringBuffer();
			
			sql.append(" SELECT COTIZACION, ");
			sql.append("                RAMO, ");
			sql.append("               PRODUCTO, ");
			sql.append("                CLIENTE, ");
			sql.append("               NOMBRE, ");
			sql.append("                CERTIFICADO, ");
			sql.append("               FE_DESDE, ");
			sql.append("                FE_HASTA, ");
			sql.append("               ACTIVIDAD_NUEVA, ");
			sql.append("                MONEDA, ");
			sql.append("               PREMIO_NUEVO, ");
			sql.append("                POLIZA_ANTERIOR, ");
			sql.append("               ACTIVIDAD_ANTERIOR, ");
			sql.append("                PREMIO_ANTERIOR, ");
			sql.append("               (NVL(PREMIO_NUEVO, 0) - PREMIO_ANTERIOR) DIFERENCIA ");
			sql.append("           FROM (SELECT CAZB_NU_COTIZACION COTIZACION, ");
			sql.append("                        CAZB_CARP_CD_RAMO RAMO, ");
			sql.append("                         CAZB_CAPU_CD_PRODUCTO PRODUCTO, ");
			sql.append("                        CAZB_CACN_CD_CLIENTE CLIENTE, ");
			sql.append("                         (select CACN_NM_APELLIDO_RAZON || ");
			sql.append("                                DECODE(CACN_NM_PERSONA_NATURAL, NULL, '', ', ') || ");
			sql.append("                                 CACN_NM_PERSONA_NATURAL ");
			sql.append("                           from cart_clientes cl ");
			sql.append("                          where cl.CACN_CD_NACIONALIDAD = ");
			sql.append("                                CAZB_CACN_CD_NACIONALIDAD ");
			sql.append("                            and cl.CACN_NU_CEDULA_RIF = CAZB_CACN_CD_CLIENTE) nombre, ");
			sql.append("                        CAZB_CACE_NU_CERTIFICADO CERTIFICADO, ");
			sql.append("                        CAZB_FE_DESDE FE_DESDE, ");
			sql.append("                        CAZB_FE_HASTA FE_HASTA, ");
			sql.append("                        (SELECT DISTINCT (CRCO_DE_DATO || '--' || CRTB_DE_DATO) ");
			sql.append("                           FROM CRET_COTIZA_DATOS, CRET_TABLAS ");
			sql.append("                          WHERE CRCO_CARP_CD_RAMO = CAZB_CARP_CD_RAMO ");
			sql.append("                            AND CRCO_CAZB_NU_COTIZACION = CAZB_NU_COTIZACION ");
			sql.append("                            AND CRCO_CAZB_NU_CONSECUTIVO = CAZB_NU_CONSECUTIVO ");
			sql.append("                            AND CRCO_CAPU_CD_PRODUCTO = CAZB_CAPU_CD_PRODUCTO ");
			sql.append("                            AND CRTB_CD_TABLA = CRCO_CRTD_CD_DATO ");
			sql.append("                            AND CRCO_DE_DATO = CRTB_INDEX ");
			sql.append("                            AND CRCO_CRTD_CD_DATO IN (130016)) ACTIVIDAD_NUEVA, ");
			sql.append("                        CAZB_CAMO_CD_MONEDA MONEDA, ");
			sql.append("                        (SELECT CACX_MT_COMPONENTE ");
			sql.append("                           FROM CART_COTIZA_COMPONENTES C ");
			sql.append("                          WHERE CACX_CAZB_NU_COTIZACION = CAZB_NU_COTIZACION ");
			sql.append("                            AND CACX_CAZB_NU_CONSECUTIVO = 0 ");
			sql.append("                            AND CACX_CAPP_CD_COMPONENTE = 'PRFAC') PREMIO_NUEVO, ");
			sql.append("                        CAZB_CAPO_NU_POLIZA POLIZA_ANTERIOR, ");
			sql.append("                        (SELECT CRPD_DE_DATO || '--' || CRTB_DE_DATO ");
			sql.append("                           FROM CRET_PRODUCTOS_DATOS, CRET_TABLAS ");
			sql.append("                          WHERE CRPD_CARP_CD_RAMO = CAZB_CARP_CD_RAMO ");
			sql.append("                            AND CRPD_CAPO_NU_POLIZA = CAZB_CAPO_NU_POLIZA ");
			sql.append("                            AND CRPD_CACE_NU_CERTIFICADO = 1 ");
			sql.append("                            AND CRPD_NU_ENDOSO = ");
			sql.append("                                (SELECT MAX(EN2.CACW_NU_ENDOSO) ");
			sql.append("                                   FROM CART_CERTIFICADOS_ENDOSOS EN2 ");
			sql.append("                                  WHERE EN2.CACW_CARP_CD_RAMO = ");
			sql.append("                                        CAZB_CARP_CD_RAMO ");
			sql.append("                                    AND EN2.CACW_CAPO_NU_POLIZA = ");
			sql.append("                                        CAZB_CAPO_NU_POLIZA ");
			sql.append("                                    AND EN2.CACW_CACE_NU_CERTIFICADO = 1 ");
			sql.append("                                    AND EN2.CACW_CAME_TP_TRANSAC NOT IN ");
			sql.append("                                        ('L', 'C', 'U') ");
			sql.append("                                    AND NOT EXISTS ");
			sql.append("                                  (SELECT 1 ");
			sql.append("                                           FROM CART_CERTIFICADOS_ENDOSOS EN3 ");
			sql.append("                                          WHERE EN3.CACW_CARP_CD_RAMO = ");
			sql.append("                                                EN2.CACW_CARP_CD_RAMO ");
			sql.append("                                            AND EN3.CACW_CAPO_NU_POLIZA = ");
			sql.append("                                                EN2.CACW_CAPO_NU_POLIZA ");
			sql.append("                                            AND EN3.CACW_NU_ENDOSO = ");
			sql.append("                                                EN2.CACW_NU_ENDOSO + 1 ");
			sql.append("                                            AND EN3.CACW_CACE_NU_CERTIFICADO = 0 ");
			sql.append("                                            AND EN3.CACW_CAME_TP_TRANSAC = 'U')) ");
			sql.append("                            AND CRPD_CAPU_CD_PRODUCTO = CAZB_CAPU_CD_PRODUCTO ");
			sql.append("                            AND CRTB_CD_TABLA = CRPD_CRCD_CD_DATO ");
			sql.append("                            AND CRPD_DE_DATO = CRTB_INDEX ");
			sql.append("                            AND CRPD_CRCD_CD_DATO = ");
			sql.append("                                DECODE(CAZB_CAPU_CD_PRODUCTO, ");
			sql.append("                                       'INCEMP', ");
			sql.append("                                       110001, ");
			sql.append("                                       'INCESP', ");
			sql.append("                                       110014, ");
			sql.append("                                       'INCTL', ");
			sql.append("                                       110001, ");
			sql.append("                                       'INCMCC', ");
			sql.append("                                       110001, ");
			sql.append("                                       'CCOMVT', ");
			sql.append("                                       130016, ");
			sql.append("                                       'CCOMPR', ");
			sql.append("                                       130016, ");
			sql.append("                                       'CCOMCC', ");
			sql.append("                                       130016, ");
			sql.append("                                       'SI02', ");
			sql.append("                                       130016, ");
			sql.append("                                       'SI03', ");
			sql.append("                                       130016, ");
			sql.append("                                       'SI04', ");
			sql.append("                                       110001)) ACTIVIDAD_ANTERIOR, ");
			sql.append("                        (SELECT SUM(CASB_MT_COMPONTE) ");
			sql.append("                           FROM CARH_CERTIFICADOS_COMPONENTES ");
			sql.append("                          WHERE CASB_CARP_CD_RAMO = CAZB_CARP_CD_RAMO ");
			sql.append("                            AND CASB_CAPO_NU_POLIZA = CAZB_CAPO_NU_POLIZA ");
			sql.append("                            AND CASB_CACE_NU_CERTIFICADO = 0 ");
			sql.append("                            AND CASB_CAPP_CD_COMPONENTE = 'PRFAC') PREMIO_ANTERIOR ");
			sql.append("                   FROM CART_COTIZA_BANCO, CART_USUARIOS D ");
			sql.append("                  WHERE CAZB_CARP_CD_RAMO IN (1, 3, 14) ");
			sql.append("                    AND D.CAUS_CD_USUARIO = ");
			sql.append("                        PAC_WEB_UTIL.FUN_USUARIO_RECTOR(?) ");
			sql.append("                    AND CAZB_CAPD_CD_PRODUCTOR = D.CAUS_CAPD_CD_PRODUCTOR ");
			sql.append("                    AND CAZB_CAPU_CD_PRODUCTO IN ");
			sql.append("                        ('INCEMP', ");
			sql.append("                         'INCESP', ");
			sql.append("                         'INCTL', ");
			sql.append("                         'INCMCC', ");
			sql.append("                         'CCOMVT', ");
			sql.append("                         'CCOMPR', ");
			sql.append("                         'CCOMCC', ");
			sql.append("                         'SI02', ");
			sql.append("                         'SI03', ");
			sql.append("                         'SI04') ");
			sql.append("                    AND CAZB_CACE_NU_CERTIFICADO = 1 ");
			sql.append("                    AND CAZB_CAME_TP_TRANSAC = 'R' ");
			sql.append("                    AND NVL(CAZB_IN_EMITIDA, 'N') = 'N' ");
			sql.append("                    AND CAZB_CAPO_NU_POLIZA IS NOT NULL ");
			sql.append("                    AND CAZB_FE_DESDE BETWEEN ");
			sql.append("                        TO_DATE('01' || ?, 'DDMMYYYY') AND ");
			sql.append("                        LAST_DAY(TO_DATE(?, 'MMYYYY')) ");
			sql.append("                 UNION ");
			sql.append("                 SELECT NULL COTIZACION, ");
			sql.append("                        CE.CACW_CARP_CD_RAMO RAMO, ");
			sql.append("                        CE.CACW_CAPU_CD_PRODUCTO PRODUCTO, ");
			sql.append("                        CE.CACW_CACE_NU_CEDULA CLIENTE, ");
			sql.append("                        (select CACN_NM_APELLIDO_RAZON || ");
			sql.append("                                DECODE(CACN_NM_PERSONA_NATURAL, NULL, '', ', ') || ");
			sql.append("                                CACN_NM_PERSONA_NATURAL ");
			sql.append("                           from cart_clientes cl ");
			sql.append("                          where cl.CACN_CD_NACIONALIDAD = ");
			sql.append("                                ce.cacw_cace_cd_nacionalidad ");
			sql.append("                            and cl.CACN_NU_CEDULA_RIF = ce.cacw_cace_nu_cedula) NOmbre, ");
			sql.append("                        CE.CACW_CACE_NU_CERTIFICADO CERTIFICADO, ");
			sql.append("                        CE.CACW_FE_DESDE FE_DESDE, ");
			sql.append("                        CE.CACW_FE_HASTA FE_HASTA, ");
			sql.append("                        NULL ACTIVIDAD_NUEVA, ");
			sql.append("                        CE.CACW_CAMO_CD_MONEDA MONEDA, ");
			sql.append("                        0 PREMIO_NUEVO, ");
			sql.append("                        CE.CACW_CAPO_NU_POLIZA POLIZA_ANTERIOR, ");
			sql.append("                        (SELECT CRPD_DE_DATO || '--' || CRTB_DE_DATO ");
			sql.append("                           FROM CRET_PRODUCTOS_DATOS, CRET_TABLAS ");
			sql.append("                          WHERE CRPD_CARP_CD_RAMO = CE.CACW_CARP_CD_RAMO ");
			sql.append("                            AND CRPD_CAPO_NU_POLIZA = CE.CACW_CAPO_NU_POLIZA ");
			sql.append("                            AND CRPD_CACE_NU_CERTIFICADO = 1 ");
			sql.append("                            AND CRPD_NU_ENDOSO = ");
			sql.append("                                (SELECT MAX(EN2.CACW_NU_ENDOSO) ");
			sql.append("                                   FROM CART_CERTIFICADOS_ENDOSOS EN2 ");
			sql.append("                                  WHERE EN2.CACW_CARP_CD_RAMO = ");
			sql.append("                                        CE.CACW_CARP_CD_RAMO ");
			sql.append("                                    AND EN2.CACW_CAPO_NU_POLIZA = ");
			sql.append("                                        CE.CACW_CAPO_NU_POLIZA ");
			sql.append("                                    AND EN2.CACW_CACE_NU_CERTIFICADO = 1 ");
			sql.append("                                    AND EN2.CACW_CAME_TP_TRANSAC NOT IN ");
			sql.append("                                        ('L', 'C', 'U') ");
			sql.append("                                    AND NOT EXISTS ");
			sql.append("                                  (SELECT 1 ");
			sql.append("                                           FROM CART_CERTIFICADOS_ENDOSOS EN3 ");
			sql.append("                                          WHERE EN3.CACW_CARP_CD_RAMO = ");
			sql.append("                                                EN2.CACW_CARP_CD_RAMO ");
			sql.append("                                            AND EN3.CACW_CAPO_NU_POLIZA = ");
			sql.append("                                                EN2.CACW_CAPO_NU_POLIZA ");
			sql.append("                                            AND EN3.CACW_NU_ENDOSO = ");
			sql.append("                                                EN2.CACW_NU_ENDOSO + 1 ");
			sql.append("                                            AND EN3.CACW_CACE_NU_CERTIFICADO = 0 ");
			sql.append("                                            AND EN3.CACW_CAME_TP_TRANSAC = 'U')) ");
			sql.append("                            AND CRPD_CAPU_CD_PRODUCTO = ");
			sql.append("                                CE.CACW_CAPU_CD_PRODUCTO ");
			sql.append("                            AND CRTB_CD_TABLA = CRPD_CRCD_CD_DATO ");
			sql.append("                            AND CRPD_DE_DATO = CRTB_INDEX ");
			sql.append("                            AND CRPD_CRCD_CD_DATO = ");
			sql.append("                                 DECODE(CE.CACW_CAPU_CD_PRODUCTO, ");
			sql.append("                                       'INCEMP', ");
			sql.append("                                       110001, ");
			sql.append("                                       'INCESP', ");
			sql.append("                                        110014, ");
			sql.append("                                       'INCTL', ");
			sql.append("                                        110001, ");
			sql.append("                                       'INCMCC', ");
			sql.append("                                        110001, ");
			sql.append("                                       'CCOMVT', ");
			sql.append("                                        130016, ");
			sql.append("                                       'CCOMPR', ");
			sql.append("                                        130016, ");
			sql.append("                                       'CCOMCC', ");
			sql.append("                                        130016, ");
			sql.append("                                       'SI02', ");
			sql.append("                                        130016, ");
			sql.append("                                       'SI03', ");
			sql.append("                                        130016, ");
			sql.append("                                       'SI04', ");
			sql.append("                                        110001)) ACTIVIDAD_ANTERIOR, ");
			sql.append("                         (SELECT SUM(CASB_MT_COMPONTE) ");
			sql.append("                           FROM CARH_CERTIFICADOS_COMPONENTES ");
			sql.append("                           WHERE CASB_CARP_CD_RAMO = CE.CACW_CARP_CD_RAMO ");
			sql.append("                            AND CASB_CAPO_NU_POLIZA = CE.CACW_CAPO_NU_POLIZA ");
			sql.append("                             AND CASB_CACE_NU_CERTIFICADO = 0 ");
			sql.append("                            AND CASB_CAPP_CD_COMPONENTE = 'PRFAC') PREMIO_ANTERIOR ");
			sql.append("                   FROM CART_CERTIFICADOS_ENDOSOS CE, ");
			sql.append("                        CART_USUARIOS D ");
			sql.append("                  WHERE ");
			sql.append("                  exists ");
			sql.append("                   (select null ");
			sql.append("                     from cart_certificados ");
			sql.append("                     where CACE_CARP_CD_RAMO = CE.CACW_CARP_CD_RAMO ");
			sql.append("                      AND CACE_CAPO_NU_POLIZA = CE.CACW_CAPO_NU_POLIZA ");
			sql.append("                       AND CACE_NU_CERTIFICADO = CE.CACW_CACE_NU_CERTIFICADO ");
			sql.append("                      AND CACE_CAPD_CD_PRODUCTOR = D.CAUS_CAPD_CD_PRODUCTOR) ");
			sql.append("               AND CE.CACW_CARP_CD_RAMO IN (1, 3, 14) ");
			sql.append("                AND CE.CACW_CAPU_CD_PRODUCTO IN ");
			sql.append("                  ('INCEMP', ");
			sql.append("                    'INCESP', ");
			sql.append("                   'INCTL', ");
			sql.append("                    'INCMCC', ");
			sql.append("                   'CCOMVT', ");
			sql.append("                    'CCOMPR', ");
			sql.append("                   'CCOMCC', ");
			sql.append("                    'SI02', ");
			sql.append("                   'SI03', ");
			sql.append("                    'SI04') ");
			sql.append("               AND CE.CACW_CACE_NU_CERTIFICADO = 1 ");
			sql.append("                AND CE.CACW_FE_HASTA BETWEEN ");
			sql.append("                  TO_DATE('01' || ?, 'DDMMYYYY') AND ");
			sql.append("                   LAST_DAY(TO_DATE(?, 'MMYYYY')) ");
			sql.append("                AND CE.CACW_FE_DESDE < (TO_DATE('01062018', 'DDMMYYYY'))  ");
			sql.append("               AND D.CAUS_CD_USUARIO = ");
			sql.append("                   PAC_WEB_UTIL.FUN_USUARIO_RECTOR(?) ");
			sql.append("                AND NOT EXISTS ");
			sql.append("                  (SELECT 1 ");
			sql.append("                      FROM CART_CERTIFICADOS CC2");
			sql.append("                    WHERE CC2.CACE_CARP_CD_RAMO = CE.CACW_CARP_CD_RAMO ");
			sql.append("                       AND CC2.CACE_CAPO_NU_POLIZA = CE.CACW_CAPO_NU_POLIZA ");
			sql.append("                      AND CC2.CACE_NU_CERTIFICADO = 0 ");
			sql.append("                       AND CC2.CACE_IN_RENOVACION = 'N') ");
			sql.append("                AND CE.CACW_NU_ENDOSO = ");
			sql.append("                  (SELECT MAX(EN2.CACW_NU_ENDOSO) ");
			sql.append("                      FROM CART_CERTIFICADOS_ENDOSOS EN2 ");
			sql.append("                    WHERE EN2.CACW_CARP_CD_RAMO = CE.CACW_CARP_CD_RAMO ");
			sql.append("                       AND EN2.CACW_CAPO_NU_POLIZA = CE.CACW_CAPO_NU_POLIZA ");
			sql.append("                      AND EN2.CACW_CACE_NU_CERTIFICADO = 1 ");
			sql.append("                       AND EN2.CACW_CAME_TP_TRANSAC NOT IN ('L', 'C', 'U') ");
			sql.append("                       AND NOT EXISTS ");
			sql.append("                    (SELECT 1 ");
			sql.append("                              FROM CART_CERTIFICADOS_ENDOSOS EN3 ");
			sql.append("                            WHERE EN3.CACW_CARP_CD_RAMO = EN2.CACW_CARP_CD_RAMO ");
			sql.append("                               AND EN3.CACW_CAPO_NU_POLIZA = ");
			sql.append("                                  EN2.CACW_CAPO_NU_POLIZA ");
			sql.append("                               AND EN3.CACW_NU_ENDOSO = EN2.CACW_NU_ENDOSO + 1 ");
			sql.append("                              AND EN3.CACW_CACE_NU_CERTIFICADO = 0 ");
			sql.append("                               AND EN3.CACW_CAME_TP_TRANSAC = 'U')) ");
			sql.append("                and not exists ");
			sql.append("                  (select null ");
			sql.append("                      from cart_cotiza_banco cot ");
			sql.append("                    where COT.CAZB_CARP_CD_RAMO = CE.CACW_CARP_CD_RAMO ");
			sql.append("                       and COT.CAZB_CAPO_NU_POLIZA = CE.CACW_CAPO_NU_POLIZA ");
			sql.append("                      AND COT.CAZB_CAPD_CD_PRODUCTOR = D.CAUS_CAPD_CD_PRODUCTOR ");
			sql.append("                       AND COT.CAZB_CAPU_CD_PRODUCTO = CE.CACW_CAPU_CD_PRODUCTO ");
			sql.append("                      AND COT.CAZB_CACE_NU_CERTIFICADO = 1 ");
			sql.append("                       AND COT.CAZB_CAME_TP_TRANSAC = 'R' ");
			sql.append("                      AND NVL(COT.CAZB_IN_EMITIDA, 'N') = 'N' ");
			sql.append("                       AND COT.CAZB_CAPO_NU_POLIZA IS NOT NULL ");
			sql.append("                      AND COT.CAZB_FE_DESDE BETWEEN ");
			sql.append("                      TO_DATE('01' || ?, 'DDMMYYYY') AND ");
			sql.append("                          LAST_DAY(TO_DATE(?, 'MMYYYY'))) ");
			sql.append("                 )");
			
			
			
			logueo.setParametro(Values.CONSULTA, sql.toString());

			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, param.getUsuario());
			pst.setString(2, param.getMesAnio());
			pst.setString(3, param.getMesAnio());
			pst.setString(4, param.getMesAnio());
			pst.setString(5, param.getMesAnio());
			pst.setString(6, param.getUsuario());
			pst.setString(7, param.getMesAnio());
			pst.setString(8, param.getMesAnio());
			
			result = pst.executeQuery();

			while (result.next()) {

				Long numCotizacion = result.getLong("COTIZACION");
				if (result.wasNull()) {
					numCotizacion = null;
				}
				Integer codRamo = result.getInt("RAMO");
				if (result.wasNull()) {
					codRamo = null;
				}
				Long nroCliente = result.getLong("CLIENTE");
				if (result.wasNull()) {
					nroCliente = null;
				}
				String codProducto = result.getString("PRODUCTO");
				
				String nombre = result.getString("NOMBRE");
				String inicioVigencia = ValuesUtils.toString(result.getDate("FE_DESDE"));
				String finVigencia = ValuesUtils.toString(result.getDate("FE_HASTA"));

				Integer certificado = result.getInt("CERTIFICADO");
				if (result.wasNull()) {
					certificado = null;
				}

				Long polizaAnterior = result.getLong("POLIZA_ANTERIOR");
				if (result.wasNull()) {
					polizaAnterior = null;
				}

				String moneda = result.getString("MONEDA");
				
				Double premioNuevo = result.getDouble("PREMIO_NUEVO");
				if (result.wasNull()) {
					premioNuevo = null;
				}
				String actividadNueva =result.getString("ACTIVIDAD_NUEVA");
				Double diferenciaPremios = result.getDouble("DIFERENCIA");
				if (result.wasNull()) {
					diferenciaPremios = null;
				}
				
				Double premioAnterior = result.getDouble("PREMIO_ANTERIOR");
				if (result.wasNull()) {
					premioAnterior = null;
				}
				String actividadAnterior = result.getString("ACTIVIDAD_ANTERIOR");
				
				
				ReporteNuevaTarifaIncendioProductor dato = new ReporteNuevaTarifaIncendioProductor(numCotizacion,codRamo,nroCliente,codProducto,nombre,certificado,polizaAnterior,moneda,premioNuevo,actividadNueva,premioAnterior,
						actividadAnterior,diferenciaPremios,inicioVigencia,finVigencia);
				resultado.setUno(dato);
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
	
	
	public ResultXmlPL obtenerConsultasExportables(ParamObtenerConsultasExportables param) {
		ResultXmlPL resultado = new ResultXmlPL();
		CallableStatement cstmt = null;
		Connection conn = null;
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPERSIST);
		logueo.setClase(ConsultasPersist.class);
		logueo.setMetodo("obtenerConsultasExportables");

		logueo.setParametro(Values.USUARIO, param.getUsuario());
		logueo.setParametro(Values.CLAVE, param.getClave());
		
		try {
			conn = this.crearConexion();
			String nombrePL = obtenerValor("proc_obtenerConsultasExportables");
			logueo.setNombrePl(nombrePL);
			logueo.setParametro("usuario", param.getUsuario());
			logueo.setParametro("clave", param.getClave());

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

}
