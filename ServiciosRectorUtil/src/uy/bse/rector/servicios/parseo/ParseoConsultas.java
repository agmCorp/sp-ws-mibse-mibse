package uy.bse.rector.servicios.parseo;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import uy.com.bse.consultas.entidades.CabezalNomina;
import uy.com.bse.consultas.entidades.CoberturaIntegranteNomina;
import uy.com.bse.consultas.entidades.DatoNominaVida;
import uy.com.bse.consultas.entidades.DatoPolizaFlotante;
import uy.com.bse.consultas.entidades.DetallePolizaFlotante;
import uy.com.bse.consultas.entidades.IntegranteNomina;
import uy.com.bse.consultas.entidades.IntegranteNominaAccidenteTrabajo;
import uy.com.bse.consultas.entidades.Movimiento;
import uy.com.bse.consultas.entidades.Resumen;
import uy.com.bse.consultas.entidades.Saldo;
import uy.com.bse.consultas.operaciones.ResultObtenerCabezalNomina;
import uy.com.bse.consultas.operaciones.ResultObtenerCoberturasIntegranteNomina;
import uy.com.bse.consultas.operaciones.ResultObtenerDetallePolizaFlotante;
import uy.com.bse.consultas.operaciones.ResultObtenerIntegrantesNomina;
import uy.com.bse.consultas.operaciones.ResultObtenerIntegrantesNominaAccidenteTrabajo;
import uy.com.bse.consultas.operaciones.ResultObtenerMovimientosComision;
import uy.com.bse.consultas.operaciones.ResultObtenerNominasVida;
import uy.com.bse.consultas.operaciones.ResultObtenerPolizasFlotantes;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.excepcion.ErrorResolver;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.parseo.ParseoXmlGenerico;

public class ParseoConsultas extends ParseoXmlGenerico {

	public ParseoConsultas(String textoParsear) {
		super(textoParsear);
	}

	public ResultGenerico parsearObtenerCabezalNomina() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoConsultas.class);
		logueo.setMetodo("parsearObtenerCabezalNomina");

		ResultObtenerCabezalNomina resultado = new ResultObtenerCabezalNomina();
		try {
			NodeList elementos = this.doc.getElementsByTagName("nomina");
			if (elementos.getLength()>0) {
				Element item = (Element) elementos.item(0);
				CabezalNomina dato = new CabezalNomina();
				dato.setTipoDocumento(getParsedValueFromElement(item, "cod-tp-documento"));
				dato.setNumDocumento(getParsedValueFromElement(item, "nu-documento"));
				dato.setNombre(getParsedValueFromElement(item, "nombre"));
				dato.setCodMoneda(evaluarInt(item, "cod-moneda"));
				dato.setDescMoneda(getParsedValueFromElement(item, "desc-moneda"));
				dato.setRangoCapitalesInicio(getParsedValueFromElement(item, "rango-inicio"));
				dato.setRangoCapitalesFin(getParsedValueFromElement(item, "rango-fin"));
				dato.setNumNomina(evaluarInt(item, "nu-nomina"));
				dato.setDescNomina(getParsedValueFromElement(item, "desc-nomina"));
				resultado.setCabezalNomina(dato);
			}else{
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

	public ResultGenerico parsearObtenerCoberturasIntegranteNomina() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoConsultas.class);
		logueo.setMetodo("parsearObtenerCoberturasIntegranteNomina");

		ResultObtenerCoberturasIntegranteNomina resultado = new ResultObtenerCoberturasIntegranteNomina();

		try {
		
			NodeList elementos = this.doc.getElementsByTagName("cobertura-list");
			if (elementos.getLength()>0) {
				Element item = (Element) elementos.item(0);
				String prima=item.getAttributeNode("total-prima").getNodeValue();
				String premio =item.getAttributeNode("total-premio").getNodeValue();
				String pemiofacturacion=item.getAttributeNode("total-premio-facturacion").getNodeValue();
				
				if (prima!=null) {
					resultado.setTotalPrima(Double.valueOf(prima));
				}
				if (premio!=null) {
					resultado.setTotalPremio(Double.valueOf(premio));
				}
				if (pemiofacturacion!=null) {
					resultado.setTotalPremioFactura(Double.valueOf(pemiofacturacion));
				}
				
			}
			
			NodeList coberturasList = this.doc.getElementsByTagName("cobertura");
			for (int i = 0; i < coberturasList.getLength(); i++) {
				Element item = (Element) coberturasList.item(i);
				CoberturaIntegranteNomina dato= new CoberturaIntegranteNomina();
				dato.setCodRamoContable(evaluarInt(item, "cod-ramo-contable"));
				dato.setCodCobertura(getParsedValueFromElement(item, "cod-cobertura"));
				dato.setDescCobertura(getParsedValueFromElement(item, "desc-cobertura"));
				dato.setMontoSumaAsegurada(evaluarDouble(item, "mt-suma-aseg"));
				dato.setFechaInclusion(getParsedValueFromElement(item, "fe-inclusion"));
				dato.setFechaExclusion(getParsedValueFromElement(item, "fe-exclusion"));  
				dato.setTasa(evaluarDouble(item, "tasa"));
				dato.setPrima(evaluarDouble(item, "prima"));
				dato.setPremio(evaluarDouble(item, "premio"));
				dato.setPremioFacturacion(evaluarDouble(item, "premio-fac"));
				dato.setOpcional(getParsedValueFromElement(item, "opcional"));
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

	public ResultGenerico parsearObtenerIntegrantesNomina() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoConsultas.class);
		logueo.setMetodo("parsearObtenerIntegrantesNomina");

		ResultObtenerIntegrantesNomina resultado = new ResultObtenerIntegrantesNomina();

		try {
			
			NodeList elementos = this.doc.getElementsByTagName("integrante");
			
			if(elementos.getLength()>0) {
				Element item = (Element) elementos.item(0);
				IntegranteNomina dato = new IntegranteNomina();
				
				dato.setTipoDocumento(getParsedValueFromElement(item, "cod-tp-documento"));
				dato.setNumDocumento(getParsedValueFromElement(item, "nu-documento"));
				dato.setNombre(getParsedValueFromElement(item, "nombre"));
				dato.setCodIntegrante(getParsedValueFromElement(item, "cod-integrante"));
				dato.setDescIntegrante(getParsedValueFromElement(item, "desc-integrante"));
				dato.setNumNomina(evaluarInt(item, "id-nomina"));
				
				dato.setNumPersona(evaluarInt(item, "nu-persona"));
				
				dato.setCantAniosRecargo(evaluarInt(item, "anios-recargo"));
				dato.setFechaAdhesion(getParsedValueFromElement(item, "fe-adhesion"));
				dato.setFechaInicioVigencia(getParsedValueFromElement(item, "fe-ini-vigencia"));

				dato.setNombreRelacionado(getParsedValueFromElement(item, "nombre-rel"));
				dato.setTipoDocumentoRelacionado(getParsedValueFromElement(item, "cod-tp-documento-rel"));
				dato.setNumDocumentoRelacionado(getParsedValueFromElement(item, "nu-documento-rel"));
				dato.setNumPersonaRelacionado(evaluarInt(item, "nu-documento-rel"));
				
				
				dato.setCodCategoriaFuncional(getParsedValueFromElement(item, "cod-cat-funcional"));
				dato.setCodProductor(getParsedValueFromElement(item, "cod-productor"));
				dato.setDescProductor(getParsedValueFromElement(item, "desc-productor"));
				
				dato.setFechaModificacion(getParsedValueFromElement(item, "fe-modif"));
				dato.setUsuarioModifica(getParsedValueFromElement(item, "cod-usu-modif"));
				

				dato.setCapitalMuerte(evaluarDouble(item, "capital-muerte"));
				dato.setSumaAsegurado(evaluarDouble(item, "suma-aseg"));
				dato.setNumCertificado(evaluarInt(item, "nu-certificado"));
				
				dato.setTieneDeclaracion(getParsedValueFromElement(item, "in-declaracion"));
				dato.setTieneFacturaImpresa(getParsedValueFromElement(item, "fact-impresa"));
				
				dato.setFechaVigenciaCapital(getParsedValueFromElement(item, "fe-vig-capital"));
				dato.setOpcionSumaAsegurado(getParsedValueFromElement(item, "opc-suma-aseg"));
				dato.setFechaNacimientoIntegrante(getParsedValueFromElement(item, "fe-nacimiento"));
				dato.setNumCobro(evaluarInt(item, "nu-cobro"));
				dato.setCodBaja(getParsedValueFromElement(item, "cd-baja"));
				dato.setDescBaja(getParsedValueFromElement(item, "desc-baja"));
				dato.setFechaBaja(getParsedValueFromElement(item, "fe-baja"));
				dato.setObservaciones(getParsedValueFromElement(item, "observaciones"));
				dato.setRevalorizado(getParsedValueFromElement(item, "revaloriza"));
				dato.setMensaje(getParsedValueFromElement(item, "mensaje"));
				dato.setTope(evaluarInt(item, "tope"));
				resultado.setIntegrante(dato);
				
			}else{
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

	public ResultGenerico parsearObtenerNominasVida() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoConsultas.class);
		logueo.setMetodo("parsearObtenerNominasVida");

		ResultObtenerNominasVida resultado = new ResultObtenerNominasVida();

		try {

			NodeList elementos = this.doc.getElementsByTagName("nomina");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				DatoNominaVida dato = new DatoNominaVida();
				dato.setNumNomina(evaluarInt(item, "nu-nomina"));
				dato.setNumDetalle(evaluarInt(item, "id-detalle"));
				dato.setDescNomina(getParsedValueFromElement(item, "desc-nomina"));
				dato.setEstado(getParsedValueFromElement(item, "estado-nomina"));
				dato.setTipoDocumento(getParsedValueFromElement(item, "cod-tp-documento"));
				dato.setNumDocumento(getParsedValueFromElement(item, "nu-documento"));
				dato.setNombre(getParsedValueFromElement(item, "nombre"));
				dato.setCodIntegrante(getParsedValueFromElement(item, "cod-integrante"));
				dato.setTipoDocumentoRelacionado(getParsedValueFromElement(item, "cod-tp-documento-rel"));
				dato.setNumDocumentoRelacionado(getParsedValueFromElement(item, "nu-documento-rel"));
				dato.setNombreRelacionado(getParsedValueFromElement(item, "nombre-rel"));
				dato.setNumCobro(getParsedValueFromElement(item, "nu-cobro"));
				dato.setNumPersona(evaluarInt(item, "nu-persona"));
				dato.setTieneRevaloriza(getParsedValueFromElement(item, "in-revaloriza"));
				dato.setCantAniosCargo(evaluarInt(item, "anios-cargo"));
				dato.setFechaAdhesion(getParsedValueFromElement(item, "fe-adhesion"));
				dato.setFechaInicioVigencia(getParsedValueFromElement(item, "fe-inicio-vigencia"));

				dato.setCodProductor(getParsedValueFromElement(item, "cod-productor"));
				dato.setUsuarioModifica(getParsedValueFromElement(item, "cod-usu-modificador"));
				dato.setCodCategoriaFuncional(getParsedValueFromElement(item, "cat-funcional"));

				dato.setCapitalMuerte(evaluarDouble(item, "capital-muerte"));
				dato.setSumaAsegurada(evaluarDouble(item, "suma-asegurada"));
				dato.setOpcionSumaAsegurada(getParsedValueFromElement(item, "opcion-suma-asegurada"));
				dato.setNumCertificado(evaluarInt(item, "nu-certificado"));
				dato.setFechaBaja(getParsedValueFromElement(item, "fe-baja"));
				dato.setCodBaja(getParsedValueFromElement(item, "cod-baja"));
				dato.setFechaBajaIntegrante(getParsedValueFromElement(item, "fe-baja-integrante"));
				
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

	public ResultObtenerDetallePolizaFlotante parsearObtenerDetallePolizaFlotante() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoConsultas.class);
		logueo.setMetodo("parsearObtenerDetallePolizaFlotante");

		ResultObtenerDetallePolizaFlotante resultado = new ResultObtenerDetallePolizaFlotante();

		try {

			NodeList elementos = this.doc.getElementsByTagName("polflo");
			if (elementos.getLength()>0) {
				Element item = (Element) elementos.item(0);
				DetallePolizaFlotante dato = new DetallePolizaFlotante();
			
				dato.setIdentificador(evaluarInt(item, "identificador"));
				dato.setCodTipoFacturacion(getParsedValueFromElement(item, "cod-tp-facturacion"));
				dato.setFechaInscripcion(getParsedValueFromElement(item, "fe-inscripcion"));
				dato.setCodAsignado(getParsedValueFromElement(item, "cod-asignado"));
				dato.setNumSucursal(evaluarInt(item, "nu-sucursal"));
				dato.setCodRamo(evaluarInt(item, "cod-ramo"));
				dato.setNumPoliza(evaluarInt(item, "nu-poliza"));
				dato.setNumEndoso(evaluarInt(item, "nu-endoso"));
				dato.setNumCertificado(evaluarInt(item, "nu-certificado"));
				dato.setNombreAsegurado(getParsedValueFromElement(item, "nom-asegurado"));
				dato.setDireccionAsegurado(getParsedValueFromElement(item, "dir-asegurado"));
				dato.setCodCorredor(evaluarInt(item, "cod-corredor"));
				dato.setDescCorredor(getParsedValueFromElement(item, "desc-corredor"));
				dato.setDescMercaderia(getParsedValueFromElement(item, "desc-mercaderia"));
				
				dato.setDescEmbalaje(getParsedValueFromElement(item, "desc-embalaje"));
				dato.setCodViaje(getParsedValueFromElement(item, "cod-viaje"));
				dato.setCantidadTransbordo(evaluarInt(item, "cant-transbordo"));
				dato.setCodArancelario(getParsedValueFromElement(item, "cod-arancelario"));
				dato.setDescOrigen(getParsedValueFromElement(item, "desc-origen"));
				dato.setCiudadOrigen(getParsedValueFromElement(item, "ciudad-orgien"));
				dato.setDescDestino(getParsedValueFromElement(item, "desc-destino"));
				dato.setCiudadDestino(getParsedValueFromElement(item, "ciudad-destino"));
				dato.setDescMedioTransporte(getParsedValueFromElement(item, "desc-medio-transp"));
				dato.setNombreMedioTransporte(getParsedValueFromElement(item, "nom-medio-transp"));
				dato.setFechaEmbarque(getParsedValueFromElement(item, "fe-embarque"));
				dato.setNumDua(getParsedValueFromElement(item, "nu-dua"));
				dato.setDescMoneda(getParsedValueFromElement(item, "desc-moneda"));
				dato.setValorMercaderia(evaluarDouble(item, "valor-mercaderia"));
				dato.setDescPlanCobertura(getParsedValueFromElement(item, "desc-plan-cob"));
				
				dato.setValorFlete(evaluarDouble(item, "valor-flete"));
				dato.setTasa(evaluarDouble(item, "tasa"));
				dato.setGastosRecargo(evaluarDouble(item, "gastos-recargo"));
				dato.setTieneRecargo(getParsedValueFromElement(item, "tiene-recargo"));
				dato.setIncremento(evaluarDouble(item, "incremento"));
				dato.setIncluyePrima(getParsedValueFromElement(item, "incluye-prima"));
				dato.setCesionDerechos(getParsedValueFromElement(item, "cesion-derechos"));
				dato.setSumaAsegurada(evaluarDouble(item, "suma-asegurada"));
				dato.setDescMonedaPremio(getParsedValueFromElement(item, "desc-moneda-premio"));
				dato.setPremioNeto(evaluarDouble(item, "premio-neto"));
				dato.setPremioNacional(evaluarDouble(item, "premio-nacional"));
				dato.setPremioTotal(evaluarDouble(item, "premio-total"));
				dato.setPremioInternacional(evaluarDouble(item, "premio-internacional"));
				dato.setUsuarioModifica(getParsedValueFromElement(item, "cod-usu-modif"));
				dato.setFechaModificacion(getParsedValueFromElement(item, "fe-modif"));
				dato.setCodCausaAnulacion(getParsedValueFromElement(item, "cod-causa-anulacion"));
				dato.setDescEstado(getParsedValueFromElement(item, "desc-estado"));
				dato.setDescCausaAnulacion(getParsedValueFromElement(item, "desc-causa-anulacion"));
				dato.setObservaciones(getParsedValueFromElement(item, "observaciones"));
				dato.setDescUsuario(getParsedValueFromElement(item, "desc-usuario"));
				dato.setDescTipoFacturacion(getParsedValueFromElement(item, "desc-tp-facturacion"));
				resultado.setDato(dato);
				
			}else{
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

	public ResultObtenerPolizasFlotantes parsearObtenerPolizasFlotantes() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoConsultas.class);
		logueo.setMetodo("parsearObtenerPolizasFlotantes");

		ResultObtenerPolizasFlotantes resultado = new ResultObtenerPolizasFlotantes();

		try { 

			NodeList elementos = this.doc.getElementsByTagName("polflo");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				DatoPolizaFlotante dato = new DatoPolizaFlotante();
				dato.setAsegurado(getParsedValueFromElement(item, "asegurado"));
				dato.setNumPoliza(evaluarInt(item, "contrato"));
				dato.setIdentificadorWeb(evaluarInt(item, "identificador"));
				dato.setFechaEmbarque(getParsedValueFromElement(item, "fe-embarque"));
				dato.setTieneSeccionDerechos(getParsedValueFromElement(item, "cesion-derechos"));
				dato.setCodTipoFacturacion(getParsedValueFromElement(item, "cod-tp-facturacion"));
				dato.setDescEstado(getParsedValueFromElement(item, "desc-estado"));
				dato.setCodMoneda(evaluarInt(item, "cod-moneda"));
				dato.setCodAsignado(getParsedValueFromElement(item, "cod-asignado"));
				dato.setSumaAsegurada(evaluarDouble(item, "suma-asegurada"));
				dato.setPremioNeto(evaluarDouble(item, "premio-neto"));
				dato.setCantidadPolizas(evaluarInt(item, "cant-polizas"));
				dato.setPolizaFactura(evaluarInt(item, "poliza-factura"));
				dato.setIdentificadorEmbarque(getParsedValueFromElement(item, "cod-viaje"));
				dato.setNumDua(getParsedValueFromElement(item, "nu-dua"));
				dato.setObservaciones(getParsedValueFromElement(item, "observaciones"));
				dato.setDescTipoFacturacion(getParsedValueFromElement(item, "desc-tp-facturacion"));
				
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

	
	public ResultObtenerMovimientosComision parsearObtenerMovimientosComision() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoConsultas.class);
		logueo.setMetodo("parsearObtenerMovimientosComision");

		ResultObtenerMovimientosComision resultado = new ResultObtenerMovimientosComision();

		try { 

			NodeList elementos = this.doc.getElementsByTagName("movimiento");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element itemMov = (Element) elementos.item(i);
				Movimiento datosMov = new Movimiento();	
				
				datosMov.setCodDebCred(getParsedValueFromElement(itemMov,"cod-deb-cred"));
				datosMov.setCodMoneda(evaluarInt(itemMov,"cod-moneda"));
				datosMov.setDescAsegurado(getParsedValueFromElement(itemMov ,"desc-asegurado"));
				datosMov.setDescMovimiento(getParsedValueFromElement(itemMov,"desc-movimiento"));
				datosMov.setDescObservaciones(getParsedValueFromElement(itemMov, "desc-observaciones"));
				datosMov.setFechaMovimiento(getParsedValueFromElement(itemMov, "fe-movimiento"));
				datosMov.setMontoMovimiento(evaluarDouble(itemMov, "mt-movimiento"));
				datosMov.setNumPoliza(getParsedValueFromElement(itemMov, "nu-poliza"));
				datosMov.setTipoMovimiento(getParsedValueFromElement(itemMov, "tp-movimiento"));
				datosMov.setCodRamo(getParsedValueFromElement(itemMov, "cod-ramo"));
				datosMov.setDescRamo(getParsedValueFromElement(itemMov, "desc-ramo"));
				datosMov.setDescMoneda(getParsedValueFromElement(itemMov, "desc-moneda"));
				
				
				resultado.setUnoMovimiento(datosMov);
			}
			
			NodeList resumen = this.doc.getElementsByTagName("resumen");
			
			for (int i = 0; i < resumen.getLength(); i++) {
				Element itemRes = (Element) resumen.item(i);
				
				Resumen datoRes = new Resumen();
				
				datoRes.setCantTipoMovs(evaluarInt(itemRes,"cant-tipo-movs"));
				datoRes.setCodTipo(evaluarInt(itemRes,"cod-tipo"));
				datoRes.setDescMovimiento(getParsedValueFromElement(itemRes,"desc-tp-movimiento"));
				datoRes.setCodProductor(evaluarInt(itemRes,"cod-productor"));
				datoRes.setCodMoneda(evaluarInt(itemRes,"cod-moneda"));
				datoRes.setDescMoneda(getParsedValueFromElement(itemRes,"desc-moneda"));
				datoRes.setMontoCredito(evaluarDouble(itemRes, "mt-credito"));
				datoRes.setMontoDebito(evaluarDouble(itemRes, "mt-debito"));
				datoRes.setMontoSaldo(evaluarDouble(itemRes, "mt-saldo"));
				datoRes.setPeriodo(getParsedValueFromElement(itemRes,"periodo"));
				datoRes.setTipoMovimiento(getParsedValueFromElement(itemRes,"cod-tp-movimiento"));
				
				resultado.setUnoResumen(datoRes);

			}
			NodeList saldoNodo = this.doc.getElementsByTagName("saldo");
			Element itemSaldo = (Element) saldoNodo.item(0);
			
			Saldo saldo = new Saldo();
			
		
			saldo.setCodTipo(evaluarInt(itemSaldo, "cod-tipo"));
		    saldo.setCodProductor(evaluarInt(itemSaldo, "cod-productor"));
		    saldo.setPeriodo(getParsedValueFromElement(itemSaldo, "periodo"));
		    saldo.setMontoCredito(evaluarDouble(itemSaldo,"montoCredito"));
		    saldo.setCodMoneda(evaluarInt(itemSaldo, "cod-moneda"));
		    saldo.setDescMoneda(getParsedValueFromElement(itemSaldo,"desc-moneda"));
		    saldo.setMontoDebito(evaluarDouble(itemSaldo, "debe"));
		    saldo.setMontoCredito(evaluarDouble(itemSaldo, "credito"));
		    saldo.setMontoSaldo(evaluarDouble(itemSaldo, "saldo"));
		    resultado.setSaldo(saldo);  
			
		    NodeList ccNodo = this.doc.getElementsByTagName("cuenta-corriente");
		    Element itemCC = (Element) ccNodo.item(0);
		    
		    resultado.setMontoAcumulado(evaluarDouble(itemCC, "mt-acumulado"));
			resultado.setMontoInicial(evaluarDouble(itemCC,"mt-inicial"));
			resultado.setMontoTotal(evaluarDouble(itemCC,"mt-total"));
		
			
		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;
	}

	public ResultObtenerIntegrantesNominaAccidenteTrabajo parsearObtenerIntegrantesNominaAccidenteTrabajo() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoConsultas.class);
		logueo.setMetodo("parsearObtenerIntegrantesNominaAccidenteTrabajo");

		ResultObtenerIntegrantesNominaAccidenteTrabajo resultado = new ResultObtenerIntegrantesNominaAccidenteTrabajo();

		try {
			
			NodeList elementos = this.doc.getElementsByTagName("nomina");
			for (int i = 0; i < elementos.getLength(); i++) {
			
				Element item = (Element) elementos.item(i);
				IntegranteNominaAccidenteTrabajo dato = new IntegranteNominaAccidenteTrabajo();
				dato.setMesCargo(getParsedValueFromElement(item, "mes-cargo"));
				dato.setTipoDocumento(getParsedValueFromElement(item, "tp-documento"));
				dato.setDescTipoDocumento(getParsedValueFromElement(item, "desc-tp-documento"));
				dato.setNumDocumento(getParsedValueFromElement(item, "nu-documento"));
				dato.setNombre(getParsedValueFromElement(item, "nombre"));
				dato.setCodTipoSalario(getParsedValueFromElement(item, "cod-tp-salario"));
				dato.setDescTipoSalario(getParsedValueFromElement(item, "desc-tp-salario"));
				dato.setMontoSalario(evaluarDouble(item,"mt-salario"));
				dato.setFechaAlta(getParsedValueFromElement(item, "fe-alta"));
				dato.setFechaBaja(getParsedValueFromElement(item, "fe-baja"));
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
