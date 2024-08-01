package uy.bse.rector.servicios.parseo;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import uy.com.bse.cotizadores.pymes.CapitalPlan;
import uy.com.bse.cotizadores.pymes.CoberturaPymes;
import uy.com.bse.cotizadores.pymes.DatosCotizacionPymes;
import uy.com.bse.cotizadores.pymes.Modulo;
import uy.com.bse.cotizadores.pymes.ModuloCobertura;
import uy.com.bse.cotizadores.pymes.ResultCalcularPymes;
import uy.com.bse.cotizadores.pymes.ResultNuevaCotizacionPymes;
import uy.com.bse.utilitario.excepcion.ErrorResolver;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.parseo.ParseoXmlGenerico;

public class ParseoCotizadorPymes extends ParseoXmlGenerico {

	public ParseoCotizadorPymes(String textoParsear) {
		super(textoParsear);
	}

	
	
	public ResultNuevaCotizacionPymes parsearNuevaCotizacionPymes() {
		
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotOperaciones.class);
		logueo.setMetodo("parsearNuevaCotizacionPymes");
		ResultNuevaCotizacionPymes resultado = new ResultNuevaCotizacionPymes();
		try {

			NodeList nodoDato = this.doc.getElementsByTagName("cotizacion");
			if (nodoDato.getLength() != 0) {
				Element item = (Element) nodoDato.item(0);
				DatosCotizacionPymes dato = new DatosCotizacionPymes();
				dato.setNumCotizacion(evaluarInt(item, "nu-cotizacion"));
				dato.setNumSucursal(evaluarInt(item, "nu-sucursal"));
				dato.setNumCertificado(evaluarInt(item, "nu-consecutivo"));
				dato.setCodMoneda(evaluarInt(item, "cod-moneda"));
				dato.setCodPromocion(getParsedValueFromElement(item, "cod-promocion"));
				dato.setCodModoCalculo(getParsedValueFromElement(item, "cod-modo-calculo"));
				dato.setCodVigencia(getParsedValueFromElement(item, "cod-vigencia"));
				dato.setFechaDesde(getParsedValueFromElement(item, "fe-desde"));
				dato.setFechaHasta(getParsedValueFromElement(item, "fe-hasta"));
				resultado.setDatosCotizacion(dato);
			} else {
				resultado.setHayError(Boolean.TRUE);
				resultado.setError(ErrorResolver.getError(Values.PARSEXCEPESTRUCTURA));
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



	public ResultCalcularPymes parsearCalcularPymes() {
		
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotOperaciones.class);
		logueo.setMetodo("parsearCalcularPymes");
		ResultCalcularPymes resultado = new ResultCalcularPymes();
		try {

			NodeList datos = this.doc.getElementsByTagName("capital");

			for (int i = 0; i < datos.getLength(); i++) {
				Element item = (Element) datos.item(i);
				CapitalPlan datoCapitalPlan = new CapitalPlan();
				datoCapitalPlan.setMontoCapital(evaluarDouble(item, "mt-capital"));
				NodeList modulos = item.getElementsByTagName("modulo");

				for (int j = 0; j < modulos.getLength(); j++) {
					Element item2 = (Element) modulos.item(j);
					Modulo modulo = new Modulo();
					modulo.setCodModulo(evaluarInt(item2, "cod-modulo"));
					modulo.setDescModulo(getParsedValueFromElement(item2, "desc-modulo"));
					modulo.setMontoModulo(evaluarDouble(item2, "mt-modulo"));
					datoCapitalPlan.setUnoModulos(modulo);
				}

				resultado.setUnoCapitalPlan(datoCapitalPlan);
			}
			
			
			NodeList datosModCoberturas = this.doc.getElementsByTagName("cobertura");

			for (int i = 0; i < datosModCoberturas.getLength(); i++) {
				Element item = (Element) datosModCoberturas.item(i);
				CoberturaPymes cobertura = new CoberturaPymes();
				cobertura.setDescCobertura(getParsedValueFromElement(item, "desc-cob"));
				NodeList modulos = item.getElementsByTagName("modulo-cobertura");

				for (int j = 0; j < modulos.getLength(); j++) {
					Element item2 = (Element) modulos.item(j);
					ModuloCobertura modulo = new ModuloCobertura();
					modulo.setCodModCob(evaluarInt(item2, "cod-modulo"));
					modulo.setDescModCob(getParsedValueFromElement(item2, "desc-modulo"));
					modulo.setMontoSumaAsegurada(getParsedValueFromElement(item2, "mt-suma-asegurada"));
					cobertura.setUnoModuloCobertura(modulo);
				}

				resultado.setUnoCobertura(cobertura);
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
