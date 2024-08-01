package uy.bse.rector.servicios.parseo;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import uy.com.bse.reportes.ResultObtenerConsultasExportables;
import uy.com.bse.reportes.ResultObtenerResumenEmision;
import uy.com.bse.reportes.ResultObtenerTotalesResumenEmision;
import uy.com.bse.reportes.entidades.ConsultaExportable;
import uy.com.bse.reportes.entidades.ReporteResumenEmision;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.parseo.ParseoXmlGenerico;

public class ParseoConsultasReportes extends ParseoXmlGenerico {

	public ParseoConsultasReportes(String textoParsear) {
		super(textoParsear);
	}

	public ResultGenerico parsearObtenerTotalesResumenEmision() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoConsultasReportes.class);
		logueo.setMetodo("parsearObtenerTotalesResumenEmision");

		ResultObtenerTotalesResumenEmision resultado = new ResultObtenerTotalesResumenEmision();

		try {
			NodeList elementos = this.doc.getElementsByTagName("cantidades");
			Element item = (Element) elementos.item(0);
			resultado.setCantidadAutomaticas(evaluarInt(item, "pol-pend-de-renov-auto"));
			resultado.setCantidadManuales(evaluarInt(item, "pol-pend-de-renov-manuales"));
			resultado.setCantidadRenovadas(evaluarInt(item, "pol-renovadas"));
			resultado.setCantidadModificadas(evaluarInt(item, "pol-renovadas-modif"));
			resultado.setCantidadAnuladas(evaluarInt(item, "pol-anuladas"));
			resultado.setCantidadNoRenuevan(evaluarInt(item, "pol-no-renuevan"));
			resultado.setCantidadNuevas(evaluarInt(item, "pol-nuevas"));
			resultado.setCantidadTotalPolizas(evaluarInt(item, "pol-total"));
			
			resultado.setCantidadFueraPauta(evaluarInt(item, "cot-pend-fp"));
			resultado.setCantidadEnPauta(evaluarInt(item, "cot-pend-en-pauta"));
			resultado.setTotalCotizacionesRenovacion(evaluarInt(item, "cot-total"));
			resultado.setCantidadCotizacionRenovacionModificadas(evaluarInt(item, "cot-total-modif"));
			
			resultado.setCantidadCotizaciones(evaluarInt(item, "pol-sin-cot-cot"));
			resultado.setCantidadPolizas(evaluarInt(item, "pol-sin-cot-polizas"));
			resultado.setRestaTotalesPolizasCotizacion(evaluarInt(item, "pol-sin-cot-resultado"));

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;
	}

	public ResultGenerico parsearObtenerResumenEmision() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoConsultasReportes.class);
		logueo.setMetodo("parsearObtenerResumenEmision");

		ResultObtenerResumenEmision resultado = new ResultObtenerResumenEmision();

		try {
		

			NodeList elementosDatos = this.doc.getElementsByTagName("detalle");
			for (int i = 0; i < elementosDatos.getLength(); i++) {
				Element item = (Element) elementosDatos.item(i);
				ReporteResumenEmision dato= new ReporteResumenEmision();
				dato.setCliente(getParsedValueFromElement(item, "nombre"));
				dato.setCodProducto(getParsedValueFromElement(item, "cod-producto"));
				dato.setCodRamo(evaluarInt(item, "cod-ramo"));
				//dato.setDescProducto(descProducto);
				//dato.setDescRamo(descRamo);
				dato.setFechaDesde(getParsedValueFromElement(item, "fe-desde"));
				dato.setFechaHasta(getParsedValueFromElement(item, "fe-hasta"));
				dato.setCodMotivoAnulacion(evaluarInt(item, "cod-motivo"));
				dato.setMotivoAnulacion(getParsedValueFromElement(item, "desc-motivo"));
				dato.setNumPoliza(evaluarInt(item, "nu-poliza"));
				dato.setNumPolizaNueva(evaluarInt(item, "nu-poliza-nueva"));
				dato.setSucursal(evaluarInt(item, "cod-sucursal"));
				dato.setTipoRenovacion(getParsedValueFromElement(item, "tp-renovacion"));
				dato.setUsuarioEmisor(getParsedValueFromElement(item, "cod-usuario"));
				dato.setUsuarioEmisorNueva(getParsedValueFromElement(item, "cod-usuario-nueva"));
				dato.setNumCliente(evaluarInt(item, "nu-cliente"));
				resultado.setUno(dato);
			}
			

		

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;
	}

	public ResultGenerico parsearObtenerConsultasExportables() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoConsultasReportes.class);
		logueo.setMetodo("parsearObtenerConsultasExportables");

		ResultObtenerConsultasExportables resultado = new ResultObtenerConsultasExportables();

		try {
		

			NodeList elementosDatos = this.doc.getElementsByTagName("consulta");
			for (int i = 0; i < elementosDatos.getLength(); i++) {
				Element item = (Element) elementosDatos.item(i);
				ConsultaExportable dato= new ConsultaExportable();
				dato.setIdConsulta(Integer.valueOf(getParsedValueFromElement(item, "id-consulta")));
				dato.setNumeroConsulta(Integer.valueOf(getParsedValueFromElement(item, "nu-consulta")));
				dato.setNomConsulta(getParsedValueFromElement(item, "des-consulta"));
				
				
				resultado.setUno(dato);
			}
			

		

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;
	}

}
