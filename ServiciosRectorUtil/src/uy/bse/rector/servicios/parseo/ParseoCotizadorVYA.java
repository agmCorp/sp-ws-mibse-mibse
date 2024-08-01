package uy.bse.rector.servicios.parseo;

import java.util.ArrayList;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import uy.com.bse.cotizadorvya.entidades.Plan;
import uy.com.bse.cotizadorvya.entidades.Referencia;
import uy.com.bse.cotizadorvya.entidades.Rescate;
import uy.com.bse.cotizadorvya.operaciones.ResultCalcularPlanes;
import uy.com.bse.cotizadorvya.operaciones.ResultMontosIniciales;
import uy.com.bse.cotizadorvya.operaciones.ResultPorcentajeAnticipo;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.parseo.ParseoXmlGenerico;

public class ParseoCotizadorVYA extends ParseoXmlGenerico {

	public ParseoCotizadorVYA(String textoParsear) {
		super(textoParsear);
	}

	public ResultCalcularPlanes parsearCalcularPlanes() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(ParseoCotizadorVYA.ENCABEZADO);
		logueo.setClase(ParseoCotizadorVYA.class);
		logueo.setMetodo("parsearCalcularPlanes");

		ResultCalcularPlanes resultado = new ResultCalcularPlanes();
		try {
			NodeList elementos = this.doc.getElementsByTagName("cotizador-vida");
			if (elementos.getLength() > 0) {
				Element item = (Element) elementos.item(0);
				resultado.setCodMoneda(evaluarInt(item, "cod-moneda"));
				resultado.setDesMoneda(getParsedValueFromElement(item, "desc-moneda"));				
				resultado.setSimboloMoneda(getParsedValueFromElement(item, "simbolo-moneda"));
				resultado.setFechaNacimiento(getParsedValueFromElement(item, "fe-nacimiento"));
				resultado.setEdad(evaluarInt(item, "edad"));
				resultado.setCapital(evaluarDouble(item, "mt-capital"));
				resultado.setMuerteAccidental(getParsedValueFromElement(item, "adic-muerte").equals("S"));
				resultado.setInvalidez(getParsedValueFromElement(item, "adic-inv").equals("S"));
				resultado.setIngresoSeguro(getParsedValueFromElement(item, "adic-ing-seg").equals("S"));
				resultado.setIngresoMensual(evaluarDouble(item, "mt-ing-mensual"));
				resultado.setRentaIngresoSeguro(evaluarDouble(item, "mt-ing-seg"));

				// Planes
				ArrayList<Plan> resultPlanes = new ArrayList<Plan>();
				NodeList planesList = item.getElementsByTagName("plan-list");
				
				if (planesList.getLength() > 0) {
					Element itemPlanesList = (Element) planesList.item(0);
					NodeList elemPlan = itemPlanesList.getElementsByTagName("plan");
					
					for (int i = 0; i < elemPlan.getLength(); i++) {
						Element itemPlan = (Element) elemPlan.item(i);
						Plan plan = parsePlan(itemPlan);
						resultPlanes.add(plan);
					}
					resultado.setPlanes(resultPlanes);
				}
				
				// Referencias
				ArrayList<Referencia> resultReferencias = new ArrayList<Referencia>();
				NodeList referenciasList = item.getElementsByTagName("referencia-list");
				
				if (referenciasList.getLength() > 0) {
					Element itemReferenciasList = (Element) referenciasList.item(0);
					NodeList elemReferencia = itemReferenciasList.getElementsByTagName("referencia");
					
					for (int i = 0; i < elemReferencia.getLength(); i++) {
						Element itemReferencia = (Element) elemReferencia.item(i);
						Referencia referencia = parseReferencia(itemReferencia);
						resultReferencias.add(referencia);
					}
					resultado.setReferencias(resultReferencias);
				}
			}
		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			ex.printStackTrace();
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}
		return resultado;
	}
	
	private Plan parsePlan(Element item) {
		Plan plan = new Plan();
		plan.setCodProducto(getParsedValueFromElement(item, "cod-producto"));
		plan.setCodPlan(getParsedValueFromElement(item, "cod-plan"));
		plan.setNombrePlan(getParsedValueFromElement(item, "desc-plan"));
		plan.setMensual(evaluarDouble(item, "mensual"));
		plan.setTrimestral(evaluarDouble(item, "trimestral"));
		plan.setSemestral(evaluarDouble(item, "semestral"));
		plan.setAnual(evaluarDouble(item, "anual"));
		if(getParsedValueFromElement(item, "adicional-muerte")!=null){
		plan.setMuerteAccidental(getParsedValueFromElement(item, "adicional-muerte").equals("S"));
		}
		if(getParsedValueFromElement(item, "adicional-invalidez")!=null){
		plan.setInvalidez(getParsedValueFromElement(item, "adicional-invalidez").equals("S"));
		}
		if(getParsedValueFromElement(item, "adicional-ing-seg")!=null){
		plan.setIngresoSeguro(getParsedValueFromElement(item, "adicional-ing-seg").equals("S"));
		}
		plan.setTextoError(getParsedValueFromElement(item, "texto-error"));
		plan.setIndicadorReferencia(evaluarInt(item, "indicador-referencia"));
		
		return plan;
	}	
	
	private Referencia parseReferencia(Element item) {
		Referencia referencia = new Referencia();
		referencia.setCodigo(evaluarInt(item, "cod-referencia"));
		referencia.setDescripcion(getParsedValueFromElement(item, "desc-referencia"));
		
		return referencia;
	}
	
	public ResultMontosIniciales parsearMontosIniciales() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(ParseoCotizadorVYA.ENCABEZADO);
		logueo.setClase(ParseoCotizadorVYA.class);
		logueo.setMetodo("parsearMontosIniciales");

		ResultMontosIniciales resultado = new ResultMontosIniciales();
		try {
			NodeList elementos = this.doc.getElementsByTagName("datos");
			if (elementos.getLength() > 0) {
				Element item = (Element) elementos.item(0);
				resultado.setMinCapital(evaluarDouble(item, "min-mt-cap-inicial"));
				resultado.setMaxCapital(evaluarDouble(item, "max-mt-cap-inicial"));
				resultado.setMinIngresoMensual(evaluarDouble(item, "min-mt-ing-mensual"));
				resultado.setMaxIngresoMensual(evaluarDouble(item, "max-mt-ing-mensual"));
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
	
	public ResultPorcentajeAnticipo parsearObtenerPorcentajeAnticipo() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(ParseoCotizadorVYA.ENCABEZADO);
		logueo.setClase(ParseoCotizadorVYA.class);
		logueo.setMetodo("parsearObtenerPorcentajeAnticipo");

		ResultPorcentajeAnticipo resultado = new ResultPorcentajeAnticipo();
		try {
			ArrayList<Rescate> resultRescates = new ArrayList<Rescate>();
			NodeList rescatesList = this.doc.getElementsByTagName("rescate-list");
			if (rescatesList.getLength() > 0) {
				Element itemRescatesList = (Element) rescatesList.item(0);
				NodeList elemRescate = itemRescatesList.getElementsByTagName("rescate");
				
				for (int i = 0; i < elemRescate.getLength(); i++) {
					Element itemRescate = (Element) elemRescate.item(i);
					Rescate rescate = parseRescate(itemRescate);
					resultRescates.add(rescate);
				}
				resultado.setRescates(resultRescates);
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
	
	private Rescate parseRescate(Element item) {
		Rescate rescate = new Rescate();
		rescate.setAntiguedad(evaluarInt(item, "antiguedad"));
		rescate.setValor(evaluarDouble(item, "valor"));
		
		return rescate;
	}	

}
