package uy.bse.rector.servicios.parseo;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import uy.com.bse.autodata.operaciones.DatosAuto;
import uy.com.bse.autodata.operaciones.Modelo;
import uy.com.bse.autodata.operaciones.ResultObtenerDatosAuto;
import uy.com.bse.autodata.operaciones.ResultObtenerModelosAutodata;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.parseo.ParseoXmlGenerico;

public class ParseoObtenerDatosAuto extends ParseoXmlGenerico {

	public ParseoObtenerDatosAuto(String textoParsear) {
		super(textoParsear);
	}

	public ResultObtenerDatosAuto parsearObtenerDatosAuto() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(ParseoObtenerDatosAuto.ENCABEZADO);
		logueo.setClase(ParseoObtenerDatosAuto.class);
		logueo.setMetodo("parsearObtenerDatosAuto");

		ResultObtenerDatosAuto resultado = new ResultObtenerDatosAuto();

		try {
			NodeList elementos = this.doc.getElementsByTagName("autodata");
			for (int i = 0; i < elementos.getLength(); i++) {
				
				Element item = (Element) elementos.item(i);
				DatosAuto dato= new DatosAuto();
				dato.setCodAno(getParsedValueFromElement(item, "anio"));
				dato.setCodMarca(getParsedValueFromElement(item, "marca"));
				dato.setCodModelo(getParsedValueFromElement(item, "modelo"));
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
	
	public ResultObtenerModelosAutodata parsearObtenerModelosAutodata() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(ParseoObtenerDatosAuto.ENCABEZADO);
		logueo.setClase(ParseoObtenerDatosAuto.class);
		logueo.setMetodo("parsearObtenerDatosAuto");

		ResultObtenerModelosAutodata resultado = new ResultObtenerModelosAutodata();

		try {
			NodeList elementos = this.doc.getElementsByTagName("modelo");
			for (int i = 0; i < elementos.getLength(); i++) {
				
				Element item = (Element) elementos.item(i);
				Modelo dato= new Modelo();
				
	
				dato.setCodTipoVehiculo(getParsedValueFromElement(item, "cod-tp-vehiculo"));
				dato.setDescTipoVehiculo(getParsedValueFromElement(item, "desc-tp-vehiculo"));
				dato.setCodDestVehiculo(getParsedValueFromElement(item, "cod-destino-veh"));
				dato.setDescDestVehiculo(getParsedValueFromElement(item, "desc-destino-veh"));
				dato.setCodCombustible(getParsedValueFromElement(item, "cod-combustible"));
				dato.setDescCombustible(getParsedValueFromElement(item, "desc-combustible"));
				dato.setCodAnoDesde(getParsedValueFromElement(item, "anio-desde"));
				dato.setCodAnioHasta(getParsedValueFromElement(item, "anio-hasta"));
				dato.setCodMarca(getParsedValueFromElement(item, "cod-marca"));
				dato.setDescMarca(getParsedValueFromElement(item, "desc-marca"));
				dato.setCodModelo(getParsedValueFromElement(item, "cod-modelo"));
				dato.setDescModelo(getParsedValueFromElement(item, "desc-modelo"));
				dato.setTipoMovimiento(getParsedValueFromElement(item,"tipo-movimiento"));
				dato.setCodAutoData(getParsedValueFromElement(item,"cod-autodata"));
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
