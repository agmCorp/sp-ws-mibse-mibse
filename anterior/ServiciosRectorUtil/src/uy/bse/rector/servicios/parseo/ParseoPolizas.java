package uy.bse.rector.servicios.parseo;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import uy.com.bse.cotizaciones.consultas.AcreedorBien;
import uy.com.bse.cotizaciones.consultas.Bien;
import uy.com.bse.cotizaciones.consultas.BienCert;
import uy.com.bse.cotizaciones.consultas.Cliente;
import uy.com.bse.cotizaciones.consultas.Cobertura;
import uy.com.bse.cotizaciones.consultas.DatosBien;
import uy.com.bse.cotizaciones.consultas.DatosBienCert;
import uy.com.bse.cotizaciones.consultas.Franquicia;
import uy.com.bse.cotizaciones.consultas.Ubicacion;
import uy.com.bse.cotizaciones.entidades.Ahorro;
import uy.com.bse.cotizaciones.entidades.Anexo;
import uy.com.bse.cotizaciones.entidades.Beneficiario;
import uy.com.bse.cotizaciones.entidades.Clausula;
import uy.com.bse.cotizaciones.entidades.DatosBanco;
import uy.com.bse.cotizaciones.entidades.DatosCotizacion;
import uy.com.bse.cotizaciones.entidades.Nota;
import uy.com.bse.cotizaciones.entidades.ObjetoBien;
import uy.com.bse.cotizaciones.lovs.MedioPago;
import uy.com.bse.cotizaciones.operaciones.Certificado;
import uy.com.bse.cotizaciones.operaciones.PlanCobertura;
import uy.com.bse.cotizaciones.operaciones.PlanPago;
import uy.com.bse.maestro.personas.comunicaciones.ComunicacionEC;
import uy.com.bse.maestro.personas.domicilio.DireccionEC;
import uy.com.bse.polizas.consultas.BienAsegurado;
import uy.com.bse.polizas.consultas.BonificacionPoliza;
import uy.com.bse.polizas.consultas.DatosCalculoRenta;
import uy.com.bse.polizas.consultas.DatosProductor;
import uy.com.bse.polizas.consultas.DatosSiniestro;
import uy.com.bse.polizas.consultas.DatosTotalesCuotas;
import uy.com.bse.polizas.consultas.DetalleRemesa;
import uy.com.bse.polizas.consultas.EncabezadoCuota;
import uy.com.bse.polizas.consultas.EncabezadoDetallePoliza;
import uy.com.bse.polizas.consultas.InfoPolizaSiniestro;
import uy.com.bse.polizas.consultas.InfoSiniestro;
import uy.com.bse.polizas.consultas.RemesaAutomatica;
import uy.com.bse.polizas.consultas.ResultDatosXDatosParametricosEndoso;
import uy.com.bse.polizas.consultas.ResultGenerarMarcaNoRenovar;
import uy.com.bse.polizas.consultas.ResultGuardarImprimirOrdenCarta;
import uy.com.bse.polizas.consultas.ResultGuardarImprimirReporte;
import uy.com.bse.polizas.consultas.ResultObtenerAcreedoresPoliza;
import uy.com.bse.polizas.consultas.ResultObtenerAhorrosPoliza;
import uy.com.bse.polizas.consultas.ResultObtenerAnexosPoliza;
import uy.com.bse.polizas.consultas.ResultObtenerAseguradoCertifPoliza;
import uy.com.bse.polizas.consultas.ResultObtenerBeneficiariosPoliza;
import uy.com.bse.polizas.consultas.ResultObtenerBienesAseguradosPoliza;
import uy.com.bse.polizas.consultas.ResultObtenerBonifNoSiniestroPoliza;
import uy.com.bse.polizas.consultas.ResultObtenerCancelacionesRemesa;
import uy.com.bse.polizas.consultas.ResultObtenerCertificadosPoliza;
import uy.com.bse.polizas.consultas.ResultObtenerContratantePoliza;
import uy.com.bse.polizas.consultas.ResultObtenerCotizacionPoliza;
import uy.com.bse.polizas.consultas.ResultObtenerCuotasPoliza;
import uy.com.bse.polizas.consultas.ResultObtenerDatosBasicosEndoso;
import uy.com.bse.polizas.consultas.ResultObtenerDatosCertificadoPoliza;
import uy.com.bse.polizas.consultas.ResultObtenerDatosEndosoPoliza;
import uy.com.bse.polizas.consultas.ResultObtenerDatosEndosoSinPremio;
import uy.com.bse.polizas.consultas.ResultObtenerDatosFacturaDigital;
import uy.com.bse.polizas.consultas.ResultObtenerDatosPoliza;
import uy.com.bse.polizas.consultas.ResultObtenerDatosRemesa;
import uy.com.bse.polizas.consultas.ResultObtenerDatosSiniestro;
import uy.com.bse.polizas.consultas.ResultObtenerDeclaracionSiniestro;
import uy.com.bse.polizas.consultas.ResultObtenerDetallePoliza;
import uy.com.bse.polizas.consultas.ResultObtenerDetalleRemesa;
import uy.com.bse.polizas.consultas.ResultObtenerEndososPoliza;
import uy.com.bse.polizas.consultas.ResultObtenerFacturasPoliza;
import uy.com.bse.polizas.consultas.ResultObtenerFranquiciasPoliza;
import uy.com.bse.polizas.consultas.ResultObtenerImputacionesPoliza;
import uy.com.bse.polizas.consultas.ResultObtenerListaBienesPoliza;
import uy.com.bse.polizas.consultas.ResultObtenerNotasSiniestro;
import uy.com.bse.polizas.consultas.ResultObtenerOrigenRemesaAuto;
import uy.com.bse.polizas.consultas.ResultObtenerPolizaCabezalDetalle;
import uy.com.bse.polizas.consultas.ResultObtenerPolizas;
import uy.com.bse.polizas.consultas.ResultObtenerPolizasCabezal;
import uy.com.bse.polizas.consultas.ResultObtenerPreliqPendientesPoliza;
import uy.com.bse.polizas.consultas.ResultObtenerProductoresPoliza;
import uy.com.bse.polizas.consultas.ResultObtenerRemesasPoliza;
import uy.com.bse.polizas.consultas.ResultObtenerRemesasXCliente;
import uy.com.bse.polizas.consultas.ResultObtenerSiniestrosCliente;
import uy.com.bse.polizas.consultas.ResultObtenerSiniestrosConFiltro;
import uy.com.bse.polizas.consultas.ResultObtenerTextosPoliza;
import uy.com.bse.polizas.consultas.ResultObtenerTotalesCuotasPoliza;
import uy.com.bse.polizas.consultas.ResultObtenerUbicacionBienPoliza;
import uy.com.bse.polizas.consultas.ResultObtenerValidarImpresionPoliza;
import uy.com.bse.polizas.entidades.Acreedor;
import uy.com.bse.polizas.entidades.BienPoliza;
import uy.com.bse.polizas.entidades.Cancelacion;
import uy.com.bse.polizas.entidades.CertificadoPoliza;
import uy.com.bse.polizas.entidades.CertificadoPolizaBasico;
import uy.com.bse.polizas.entidades.ContratanteAsegurado;
import uy.com.bse.polizas.entidades.CotizacionPoliza;
import uy.com.bse.polizas.entidades.Cuota;
import uy.com.bse.polizas.entidades.DatoParametricoPoliza;
import uy.com.bse.polizas.entidades.DatosBasicosEndoso;
import uy.com.bse.polizas.entidades.DatosBasicosPoliza;
import uy.com.bse.polizas.entidades.DatosBasicosSiniestro;
import uy.com.bse.polizas.entidades.DatosParticularesEndoso;
import uy.com.bse.polizas.entidades.DatosPoliza;
import uy.com.bse.polizas.entidades.DatosXDatoParametrico;
import uy.com.bse.polizas.entidades.DetalleRefacturacionPoliza;
import uy.com.bse.polizas.entidades.EncabezadoPoliza;
import uy.com.bse.polizas.entidades.Endoso;
import uy.com.bse.polizas.entidades.Factura;
import uy.com.bse.polizas.entidades.ImpresionMercosur;
import uy.com.bse.polizas.entidades.ImpresionPoliza;
import uy.com.bse.polizas.entidades.ImpresionSOA;
import uy.com.bse.polizas.entidades.Imputacion;
import uy.com.bse.polizas.entidades.Opcion;
import uy.com.bse.polizas.entidades.PlanCoberturaPoliza;
import uy.com.bse.polizas.entidades.Preliquidacion;
import uy.com.bse.polizas.entidades.Remesa;
import uy.com.bse.polizas.entidades.Siniestro;
import uy.com.bse.polizas.entidades.Texto;
import uy.com.bse.polizas.operaciones.CotizacionAnulacion;
import uy.com.bse.polizas.operaciones.ResultAgregarCertificadoEndoso;
import uy.com.bse.polizas.operaciones.ResultAnularCertificadoEndoso;
import uy.com.bse.polizas.operaciones.ResultAnularPoliza;
import uy.com.bse.polizas.operaciones.ResultCotizacionPendiente;
import uy.com.bse.polizas.operaciones.ResultNuevaCotizacionEndoso;
import uy.com.bse.polizas.operaciones.ResultNuevaFacturacionInteractiva;
import uy.com.bse.polizas.operaciones.ResultValidarAnulacionPoliza;
import uy.com.bse.polizas.operaciones.ResultValidarPoliza;
import uy.com.bse.polizas.operaciones.ValidacionGenerica;
import uy.com.bse.recuotificacion.Consolidado;
import uy.com.bse.recuotificacion.CuotaRecuotificacion;
import uy.com.bse.recuotificacion.DatosCertificado;
import uy.com.bse.recuotificacion.DetalleRecuotificacion;
import uy.com.bse.recuotificacion.OrigenPagoEndoso;
import uy.com.bse.recuotificacion.Recibo;
import uy.com.bse.recuotificacion.Recuotificacion;
import uy.com.bse.recuotificacion.ResultCalcularRec;
import uy.com.bse.recuotificacion.ResultListaPlanesPagoRec;
import uy.com.bse.recuotificacion.ResultObtenerConsolidado;
import uy.com.bse.recuotificacion.ResultRecuotificarPoliza;
import uy.com.bse.recuotificacion.ResultValidarDetectarEndoso;
import uy.com.bse.recuotificacion.ResultValidarPolizaRec;
import uy.com.bse.refacturar.Advertencia;
import uy.com.bse.refacturar.Refacturacion;
import uy.com.bse.refacturar.ResultRefacturacionPoliza;
import uy.com.bse.refacturar.ResultValidarRefacturacion;
import uy.com.bse.utilitario.dato.Codiguera;
import uy.com.bse.utilitario.dato.ResultCodiguera;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.parseo.ParseoXmlGenerico;

public class ParseoPolizas extends ParseoXmlGenerico {

	private static Logger LOG = LogManager.getLogger(ParseoPolizas.class);

	public ParseoPolizas(String textoParsear) {
		super(textoParsear);
	}

	public ResultObtenerDatosPoliza parsearObtenerDatosPoliza() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerDatosPoliza()");

		ResultObtenerDatosPoliza resultado = new ResultObtenerDatosPoliza();

		try {
			NodeList elementos = this.doc.getElementsByTagName("encabezado");
			Element item = (Element) elementos.item(0);
			DatosBasicosPoliza dPoliza = new DatosBasicosPoliza();

			if (getParsedValueFromElement(item, "cod-sucursal") != null) {
				dPoliza.setCodSucursal(Integer.valueOf(getParsedValueFromElement(item, "cod-sucursal")));
			}
			dPoliza.setDescripSucursal(getParsedValueFromElement(item, "desc-sucursal"));

			if (getParsedValueFromElement(item, "cod-ramo") != null) {
				dPoliza.setCodRamo(Integer.valueOf(getParsedValueFromElement(item, "cod-ramo")));
			}
			dPoliza.setDescripRamo(getParsedValueFromElement(item, "desc-ramo"));

			if (getParsedValueFromElement(item, "cod-poliza") != null) {
				dPoliza.setNumPoliza(Integer.valueOf(getParsedValueFromElement(item, "cod-poliza")));
			}

			NodeList elementosDatos = this.doc.getElementsByTagName("datos-basico");
			Element Item = (Element) elementosDatos.item(0);

			dPoliza.setCodProducto(getParsedValueFromElement(Item, "cod-producto"));
			dPoliza.setDescripProducto(getParsedValueFromElement(Item, "desc-producto"));
			dPoliza.setCodMoneda(getParsedValueFromElement(Item, "cod-moneda"));
			dPoliza.setDescripMoneda(getParsedValueFromElement(Item, "desc-moneda"));
			dPoliza.setCodTipoFacturacion(getParsedValueFromElement(Item, "cod-tp-facturacion"));
			dPoliza.setDescripTipoFacturacion(getParsedValueFromElement(Item, "desc-tp-facturacion"));
			dPoliza.setCodPromocion(getParsedValueFromElement(Item, "cod-promocion"));
			dPoliza.setDescripPromocion(getParsedValueFromElement(Item, "desc-promocion"));
			dPoliza.setCodRenovacion(getParsedValueFromElement(Item, "cod-renovacion"));
			dPoliza.setDescripRenovacion(getParsedValueFromElement(Item, "desc-renovacion"));
			dPoliza.setCodCompromiso(getParsedValueFromElement(Item, "nu-compromiso"));

			if (getParsedValueFromElement(Item, "nu-refact-efect") != null) {
				dPoliza.setNumRefactEfectiva(Integer.valueOf(getParsedValueFromElement(Item, "nu-refact-efect")));
			}
			if (getParsedValueFromElement(Item, "nu-poliza-anterior") != null) {
				dPoliza.setNumPolizaAnterior(Integer.valueOf(getParsedValueFromElement(Item, "nu-poliza-anterior")));
			}
			if (getParsedValueFromElement(Item, "nu-poliza-siguiente") != null) {
				dPoliza.setNumPolizaSiguiente(Integer.valueOf(getParsedValueFromElement(Item, "nu-poliza-siguiente")));
			}
			dPoliza.setCodVigencia(getParsedValueFromElement(Item, "cod-vigencia"));
			dPoliza.setDescripVigencia(getParsedValueFromElement(Item, "desc-vigencia"));
			dPoliza.setFechaDesde(getParsedValueFromElement(Item, "fe-desde"));
			dPoliza.setFechaHasta(getParsedValueFromElement(Item, "fe-hasta"));
			dPoliza.setCodVigenciaTecnica(getParsedValueFromElement(Item, "cod-vigencia-tecnica"));
			dPoliza.setDescripVigenciaTecnica(getParsedValueFromElement(Item, "desc-vigencia-tecnica"));
			dPoliza.setFechaDesdeVigenciaTecnica(getParsedValueFromElement(Item, "fe-desde-tecnica"));
			dPoliza.setFechaHastaVigenciaTecnica(getParsedValueFromElement(Item, "fe-hasta-tecnica"));
			dPoliza.setCodUsuario(getParsedValueFromElement(Item, "cod-usuario"));
			dPoliza.setDescripUsuario(getParsedValueFromElement(Item, "desc-usuario"));
			dPoliza.setAnulacionCorrida(getParsedValueFromElement(Item, "in-anulacion-corrida"));
			dPoliza.setCodBroker(evaluarInt(item, "cod-broker"));
			dPoliza.setDescBroker(getParsedValueFromElement(item, "desc-broker"));
			dPoliza.setCodProductor(evaluarInt(item, "cod-productor"));
			dPoliza.setDescProductor(getParsedValueFromElement(item, "desc-productor"));

			resultado.setDatosPoliza(dPoliza);

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;

	}

	private EncabezadoPoliza parsearEncabezadoPoliza() {
		EncabezadoPoliza resultado = new EncabezadoPoliza();
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearEncabezadoPoliza()");
		try {
			NodeList elementos = this.doc.getElementsByTagName("encabezado");
			Element item = (Element) elementos.item(0);

			if (getParsedValueFromElement(item, "cod-sucursal") != null) {
				resultado.setCodSucursal(Integer.valueOf(getParsedValueFromElement(item, "cod-sucursal")));
			}
			resultado.setDescripSucursal(getParsedValueFromElement(item, "desc-sucursal"));

			if (getParsedValueFromElement(item, "cod-ramo") != null) {
				resultado.setCodRamo(Integer.valueOf(getParsedValueFromElement(item, "cod-ramo")));
			}
			resultado.setDescripRamo(getParsedValueFromElement(item, "desc-ramo"));

			if (getParsedValueFromElement(item, "cod-poliza") != null) {
				resultado.setNumPoliza(Integer.valueOf(getParsedValueFromElement(item, "cod-poliza")));
			}
		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		}
		return resultado;
	}

	public ResultObtenerCertificadosPoliza parsearObtenerCertificadosPoliza() {

		ResultObtenerCertificadosPoliza resultado = new ResultObtenerCertificadosPoliza();
		resultado.setEncabezado(this.parsearEncabezadoPoliza());
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerCertificadosPoliza()");

		try {
			NodeList elementos = this.doc.getElementsByTagName("certificado");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element itemCertif = (Element) elementos.item(i);
				CertificadoPolizaBasico cert = new CertificadoPolizaBasico();

				if (getParsedValueFromElement(itemCertif, "nu-certificado") != null) {
					cert.setNumCertificado(Integer.valueOf(getParsedValueFromElement(itemCertif, "nu-certificado")));
				}
				cert.setAsegurado(getParsedValueFromElement(itemCertif, "desc-asegurado"));
				cert.setEstadoPoliza(getParsedValueFromElement(itemCertif, "desc-st-poliza"));
				cert.setDescripMedioPago(getParsedValueFromElement(itemCertif, "desc-medio-pago"));
				cert.setDescripOrigen(getParsedValueFromElement(itemCertif, "desc-origen"));
				cert.setDescripPlanPago(getParsedValueFromElement(itemCertif, "desc-plan-pago"));
				cert.setFechaDesdeVigencia(getParsedValueFromElement(itemCertif, "fe-desde"));

				resultado.setUnCertificado(cert);
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

	public ResultObtenerDatosCertificadoPoliza parsearObtenerDatosCertificadoPoliza() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerDatosCertificadoPoliza()");

		ResultObtenerDatosCertificadoPoliza resultado = new ResultObtenerDatosCertificadoPoliza();
		resultado.setEncabezado(this.parsearEncabezadoPoliza());

		try {
			NodeList elementos = this.doc.getElementsByTagName("datos-ampl-cert");
			Element item = (Element) elementos.item(0);
			CertificadoPoliza cert = new CertificadoPoliza();

			if (getParsedValueFromElement(item, "nu-certificado") != null) {
				cert.setNumCertificado(Integer.valueOf(getParsedValueFromElement(item, "nu-certificado")));
			}
			cert.setAsegurado(getParsedValueFromElement(item, "desc-cliente"));
			cert.setDescripMedioPago(getParsedValueFromElement(item, "desc-medio-pago"));
			cert.setDescripOrigen(getParsedValueFromElement(item, "desc-origen"));
			cert.setFechaDesdeVigencia(getParsedValueFromElement(item, "fe-desde"));
			cert.setFechaSuscripcion(getParsedValueFromElement(item, "fe-suscripcion"));
			cert.setEstadoCertificado(getParsedValueFromElement(item, "desc-st-certificado"));

			if (getParsedValueFromElement(item, "nu-endoso") != null) {
				cert.setNumEndoso(Integer.valueOf(getParsedValueFromElement(item, "nu-endoso")));
			}
			cert.setCodNacionalidad(getParsedValueFromElement(item, "cod-nacionalidad"));
			cert.setCodCliente(getParsedValueFromElement(item, "nu-cedula-rif"));

			if (getParsedValueFromElement(item, "cod-medio-pago") != null) {
				cert.setCodMedioPago(Integer.valueOf(getParsedValueFromElement(item, "cod-medio-pago")));
			}
			cert.setCodOrigen(getParsedValueFromElement(item, "cod-origen"));
			cert.setCodCuenta(getSeudoTarjeta(getParsedValueFromElement(item, "nu-cuenta")));

			if (getParsedValueFromElement(item, "dia-vencimiento") != null) {
				cert.setDiaVencimiento(Integer.valueOf(getParsedValueFromElement(item, "dia-vencimiento")));
			}
			cert.setCodVigencia(getParsedValueFromElement(item, "cod-vigencia"));
			cert.setDescripVigencia(getParsedValueFromElement(item, "desc-vigencia"));
			cert.setFechaHastaVigencia(getParsedValueFromElement(item, "fe-hasta"));
			cert.setCodVigenciaTecnica(getParsedValueFromElement(item, "cod-vigencia-tecnica"));
			cert.setDescripVigenciaTecnica(getParsedValueFromElement(item, "desc-vigencia-tecnica"));
			cert.setFechaDesdeVigenciaTecnica(getParsedValueFromElement(item, "fe-desde-vigencia-tecnica"));
			cert.setFechaHastaVigenciaTecnica(getParsedValueFromElement(item, "fe-hasta-vigencia-tecnica"));

			if (getParsedValueFromElement(item, "cant-serv-utilizados") != null) {
				cert.setCantidadServiciosUtilizados(Integer.valueOf(getParsedValueFromElement(item, "cant-serv-utilizados")));
			}
			cert.setFacultativo(getParsedValueFromElement(item, "in-facultativo"));

			if (getParsedValueFromElement(item, "nu-direccion-cobro") != null) {
				cert.setCodDireccionCobro(Integer.valueOf(getParsedValueFromElement(item, "nu-direccion-cobro")));
			}

			if (getParsedValueFromElement(item, "nu-direccion-envio") != null) {
				cert.setCodDireccionEnvio(Integer.valueOf(getParsedValueFromElement(item, "nu-direccion-envio")));
			}

			if (getParsedValueFromElement(item, "cant-premio-total") != null) {
				cert.setCantPremioTotal(Double.valueOf(getParsedValueFromElement(item, "cant-premio-total")));
			}

			cert.setNumTarjeta(getSeudoTarjeta(getParsedValueFromElement(item, "nu-tarjeta")));
			resultado.setDatosCertificado(cert);

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;

	}

	public ResultObtenerEndososPoliza parsearObtenerEndososPoliza() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerEndososPoliza()");

		ResultObtenerEndososPoliza resultado = new ResultObtenerEndososPoliza();
		resultado.setEncabezado(this.parsearEncabezadoPoliza());

		try {

			NodeList elementosEndoso = this.doc.getElementsByTagName("endoso");
			for (int i = 0; i < elementosEndoso.getLength(); i++) {
				Element itemEndoso = (Element) elementosEndoso.item(i);
				DatosBasicosEndoso end = new DatosBasicosEndoso();

				if (getParsedValueFromElement(itemEndoso, "nu-endoso") != null) {
					end.setNumEndoso(Integer.valueOf(getParsedValueFromElement(itemEndoso, "nu-endoso")));
				}

				if (getParsedValueFromElement(itemEndoso, "cod-motivo") != null) {
					end.setCodMotivo(Integer.valueOf(getParsedValueFromElement(itemEndoso, "cod-motivo")));
				}
				end.setDescripMotivo(getParsedValueFromElement(itemEndoso, "desc-motivo"));

				if (getParsedValueFromElement(itemEndoso, "nu-cotizacion") != null) {
					end.setNumCotizacion(Integer.valueOf(getParsedValueFromElement(itemEndoso, "nu-cotizacion")));
				}

				end.setCodPlanCobertura(getParsedValueFromElement(itemEndoso, "cod-plan"));
				end.setDescripPlanCobertura(getParsedValueFromElement(itemEndoso, "desc-plan"));

				if (getParsedValueFromElement(itemEndoso, "nu-refacturacion") != null) {
					end.setNumRefacturacion(Integer.valueOf(getParsedValueFromElement(itemEndoso, "nu-refacturacion")));
				}

				resultado.setUnDatoBasicoEndoso(end);
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

	public ResultObtenerDatosEndosoPoliza parsearObtenerDatosEndosoPoliza() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerDatosEndosoPoliza()");

		ResultObtenerDatosEndosoPoliza resultado = new ResultObtenerDatosEndosoPoliza();
		resultado.setEncabezado(this.parsearEncabezadoPoliza());

		try {
			NodeList elementos = this.doc.getElementsByTagName("datos-ampl-endoso");
			Element item = (Element) elementos.item(0);
			Endoso end = new Endoso();

			if (getParsedValueFromElement(item, "nu-endoso") != null) {
				end.setNumEndoso(Integer.valueOf(getParsedValueFromElement(item, "nu-endoso")));
			}

			if (getParsedValueFromElement(item, "nu-certificado") != null) {
				end.setNumCertificado(Integer.valueOf(getParsedValueFromElement(item, "nu-certificado")));
			}

			if (getParsedValueFromElement(item, "nu-cotizacion") != null) {
				end.setNumCotizacion(Integer.valueOf(getParsedValueFromElement(item, "nu-cotizacion")));
			}

			end.setFechaOperacion(getParsedValueFromElement(item, "fe-operacion"));
			end.setCodVigencia(getParsedValueFromElement(item, "cod-vigencia"));
			end.setDescripVigencia(getParsedValueFromElement(item, "desc-vigencia"));
			end.setFechaDesdeVigencia(getParsedValueFromElement(item, "fe-desde"));
			end.setFechaHastaVigencia(getParsedValueFromElement(item, "fe-hasta"));

			if (getParsedValueFromElement(item, "cant-dias") != null) {
				end.setCantidadDias(Integer.valueOf(getParsedValueFromElement(item, "cant-dias")));
			}

			if (getParsedValueFromElement(item, "cod-motivo") != null) {
				end.setCodMotivo(Integer.valueOf(getParsedValueFromElement(item, "cod-motivo")));
			}
			end.setDescripMotivo(getParsedValueFromElement(item, "desc-motivo"));
			end.setCodTransaccion(getParsedValueFromElement(item, "cod-tp-transac"));
			end.setDescripTransaccion(getParsedValueFromElement(item, "desc-tp-transac"));

			if (getParsedValueFromElement(item, "cod-origen") != null) {
				end.setCodOrigen(Integer.valueOf(getParsedValueFromElement(item, "cod-origen")));
			}
			end.setDescripOrigen(getParsedValueFromElement(item, "desc-origen"));
			end.setCodPlanCobertura(getParsedValueFromElement(item, "cod-plan"));
			end.setDescripPlanCobertura(getParsedValueFromElement(item, "desc-plan"));

			if (getParsedValueFromElement(item, "nu-refacturacion") != null) {
				end.setNumRefacturacion(Integer.valueOf(getParsedValueFromElement(item, "nu-refacturacion")));
			}

			if (getParsedValueFromElement(item, "cod-plan-pago") != null) {
				end.setCodPlanPago(Integer.valueOf(getParsedValueFromElement(item, "cod-plan-pago")));
			}
			end.setDescripPlanPago(getParsedValueFromElement(item, "desc-plan-pago"));

			if (getParsedValueFromElement(item, "catn-cuotas") != null) {
				end.setCantidadCuotas(Integer.valueOf(getParsedValueFromElement(item, "catn-cuotas")));
			}
			end.setCodTipoCalculo(getParsedValueFromElement(item, "cod-tp-calculo"));
			end.setDescripTipoCalculo(getParsedValueFromElement(item, "desc-tp-calculo"));
			end.setCodImpresion(getParsedValueFromElement(item, "in-impresion"));
			end.setCodUsuario(getParsedValueFromElement(item, "cod-usuario"));
			end.setDescripUsuario(getParsedValueFromElement(item, "desc-usuario"));

			if (getParsedValueFromElement(item, "cod-motivo-abandono") != null) {
				end.setCodMotivoAbandono(Integer.valueOf(getParsedValueFromElement(item, "cod-motivo-abandono")));
			}
			end.setDescripMotivoAbandono(getParsedValueFromElement(item, "desc-motivo-abandono"));
			end.setDescripAclaracion(getParsedValueFromElement(item, "desc-aclaracion"));

			resultado.setDatosEndoso(end);

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;

	}

	public ResultObtenerProductoresPoliza parsearObtenerProductoresPoliza() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerProductoresPoliza()");

		ResultObtenerProductoresPoliza resultado = new ResultObtenerProductoresPoliza();
		resultado.setEncabezado(this.parsearEncabezadoPoliza());

		try {

			NodeList elementosEndoso = this.doc.getElementsByTagName("list-productor");
			for (int i = 0; i < elementosEndoso.getLength(); i++) {
				Element item = (Element) elementosEndoso.item(i);
				DatosProductor dPro = new DatosProductor();

				if (getParsedValueFromElement(item, "nu-endoso") != null) {
					dPro.setNumEndoso(Integer.valueOf(getParsedValueFromElement(item, "nu-endoso")));
				}

				if (getParsedValueFromElement(item, "cod-productor") != null) {
					dPro.setCodProductor(Integer.valueOf(getParsedValueFromElement(item, "cod-productor")));
				}
				dPro.setDescripProductor(getParsedValueFromElement(item, "desc-productor"));

				resultado.setUnDatoProductor(dPro);
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

	public ResultObtenerDetallePoliza parsearObtenerDetallePoliza() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerDetallePoliza()");

		ResultObtenerDetallePoliza resultado = new ResultObtenerDetallePoliza();

		try {
			NodeList elementos = this.doc.getElementsByTagName("datos-poliza");
			Element item = (Element) elementos.item(0);
			DatosPoliza dPoliza = new DatosPoliza();

			if (getParsedValueFromElement(item, "cod-sucursal") != null) {
				dPoliza.setCodSucursal(Integer.valueOf(getParsedValueFromElement(item, "cod-sucursal")));
			}

			if (getParsedValueFromElement(item, "cod-ramo") != null) {
				dPoliza.setCodRamo(Integer.valueOf(getParsedValueFromElement(item, "cod-ramo")));
			}

			if (getParsedValueFromElement(item, "nu-poliza") != null) {
				dPoliza.setNumPoliza(Integer.valueOf(getParsedValueFromElement(item, "nu-poliza")));
			}
			dPoliza.setCodNacionalidad(getParsedValueFromElement(item, "cod-nacionalidad"));
			dPoliza.setCodCliente(getParsedValueFromElement(item, "nu-cedula-rif"));
			dPoliza.setCliente(getParsedValueFromElement(item, "desc-cliente"));
			dPoliza.setDescripEstado(getParsedValueFromElement(item, "desc-estado"));
			dPoliza.setDescripMoneda(getParsedValueFromElement(item, "desc-moneda"));
			dPoliza.setDescripTipoNegocio(getParsedValueFromElement(item, "desc-tipo-negocio"));
			dPoliza.setDescripTipoFacturacion(getParsedValueFromElement(item, "desc-tipo-facturacion"));
			dPoliza.setFechaAmortizacion(getParsedValueFromElement(item, "fe-amortizacion"));

			if (getParsedValueFromElement(item, "nu-refact-efect") != null) {
				dPoliza.setNumRefactEfectiva(Integer.valueOf(getParsedValueFromElement(item, "nu-refact-efect")));
			}

			if (getParsedValueFromElement(item, "nu-refact-permit") != null) {
				dPoliza.setNumRefactPermitida(Integer.valueOf(getParsedValueFromElement(item, "nu-refact-permit")));
			}

			if (getParsedValueFromElement(item, "nu-poliza-anterior") != null) {
				dPoliza.setNumPolizaAnterior(Integer.valueOf(getParsedValueFromElement(item, "nu-poliza-anterior")));
			}
			dPoliza.setDescripMedioPago(getParsedValueFromElement(item, "desc-medio-pago"));
			dPoliza.setCodVigencia(getParsedValueFromElement(item, "cod-vigencia"));
			dPoliza.setDescripVigencia(getParsedValueFromElement(item, "desc-vigencia"));
			dPoliza.setFechaHasta(getParsedValueFromElement(item, "fe-hasta-vigencia"));
			dPoliza.setFechaDesde(getParsedValueFromElement(item, "fe-desde-vigencia"));
			dPoliza.setCodVigenciaTecnica(getParsedValueFromElement(item, "cod-vigencia-tecnica"));
			dPoliza.setDescripVigenciaTecnica(getParsedValueFromElement(item, "desc-vigencia-tecnica"));
			dPoliza.setFechaHastaVigenciaTecnica(getParsedValueFromElement(item, "fe-hasta-vigencia-tecnica"));
			dPoliza.setFechaDesdeVigenciaTecnica(getParsedValueFromElement(item, "fe-desde-vigencia-tecnica"));
			dPoliza.setCodTipoCalculo(getParsedValueFromElement(item, "cod-tp-calculo"));
			dPoliza.setDescTipoCalculo(getParsedValueFromElement(item, "desc-tp-calculo"));
			dPoliza.setCodBroker(evaluarInt(item, "cod-broker"));
			dPoliza.setDescBroker(getParsedValueFromElement(item, "desc-broker"));
			dPoliza.setCodProductor(evaluarInt(item, "cod-productor"));
			dPoliza.setDescProductor(getParsedValueFromElement(item, "desc-productor"));

			resultado.setDetallePoliza(dPoliza);

			NodeList elementosEndoso = this.doc.getElementsByTagName("endoso");
			for (int i = 0; i < elementosEndoso.getLength(); i++) {
				Element valItem = (Element) elementosEndoso.item(i);
				Endoso end = new Endoso();

				if (getParsedValueFromElement(valItem, "nu-endoso") != null) {
					end.setNumEndoso(Integer.valueOf(getParsedValueFromElement(valItem, "nu-endoso")));
				}

				if (getParsedValueFromElement(valItem, "nu-certificado") != null) {
					end.setNumCertificado(Integer.valueOf(getParsedValueFromElement(valItem, "nu-certificado")));
				}

				if (getParsedValueFromElement(valItem, "nu-cotizacion") != null) {
					end.setNumCotizacion(Integer.valueOf(getParsedValueFromElement(valItem, "nu-cotizacion")));
				}
				end.setCliente(getParsedValueFromElement(valItem, "desc-cliente"));
				end.setFechaOperacion(getParsedValueFromElement(valItem, "fe-operacion"));
				end.setFechaDesdeVigencia(getParsedValueFromElement(valItem, "fe-desde"));
				end.setFechaHastaVigencia(getParsedValueFromElement(valItem, "fe-hasta"));
				end.setCodTransaccion(getParsedValueFromElement(valItem, "cod-tp-transac"));
				end.setDescripTransaccion(getParsedValueFromElement(valItem, "desc-tp-transac"));

				if (getParsedValueFromElement(valItem, "cod-motivo") != null) {
					end.setCodMotivo(Integer.valueOf(getParsedValueFromElement(valItem, "cod-motivo")));
				}
				end.setDescripMotivo(getParsedValueFromElement(valItem, "desc-motivo"));
				end.setCodUsuario(getParsedValueFromElement(valItem, "cod-usuario"));
				end.setDescripUsuario(getParsedValueFromElement(valItem, "desc-usuario"));
				end.setCodPlanCobertura(getParsedValueFromElement(valItem, "cod-plan-cobertura"));
				end.setDescripPlanCobertura(getParsedValueFromElement(valItem, "desc-plan-cobertura"));

				resultado.setUnEndoso(end);

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
	
	
	public ResultValidarRefacturacion parsearValidarRefacturacion() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearValidarRefacturacion()");

		ResultValidarRefacturacion resultado = new ResultValidarRefacturacion();

		try {
			NodeList elementos = this.doc.getElementsByTagName("poliza");
			Element itemPoliza = (Element) elementos.item(0);
			
			
			NodeList datos = itemPoliza.getElementsByTagName("datos-poliza");
			
			
			Element item = (Element) datos.item(0);

			resultado.setHabilitoEmitir(getParsedValueFromElement(item, "habilito-emitir"));
			resultado.setHabilitoModificar(getParsedValueFromElement(item, "habilito-editar"));
			resultado.setHabilitoFacturacion(getParsedValueFromElement(item, "habilito-facturacion"));
			resultado.setHabilitoRefacturar(getParsedValueFromElement(item, "habilito-refacturar"));
            
			DetalleRefacturacionPoliza dPoliza = new DetalleRefacturacionPoliza();

			if (getParsedValueFromElement(item, "cod-sucursal") != null) {
				dPoliza.setCodSucursal(Integer.valueOf(getParsedValueFromElement(item, "cod-sucursal")));
			}
			
			if (getParsedValueFromElement(item, "cod-ramo") != null) {
				dPoliza.setCodRamo(Integer.valueOf(getParsedValueFromElement(item, "cod-ramo")));
			}
			

			if (getParsedValueFromElement(item, "nu-poliza") != null) {
				dPoliza.setNumPoliza(Integer.valueOf(getParsedValueFromElement(item, "nu-poliza")));
			}

			dPoliza.setCodNacionalidad(getParsedValueFromElement(item, "cod-nacionalidad"));
			dPoliza.setCodDocumento(getParsedValueFromElement(item, "nu-cedula-rif"));
			dPoliza.setDescCliente(getParsedValueFromElement(item, "desc-cliente"));
			dPoliza.setDescEstado(getParsedValueFromElement(item, "desc-estado"));
			dPoliza.setDescripMoneda(getParsedValueFromElement(item, "desc-moneda"));
			
			
			dPoliza.setDescTipoNegocio(getParsedValueFromElement(item, "desc-tipo-negocio"));
			dPoliza.setDescripTipoFacturacion(getParsedValueFromElement(item, "desc-tp-facturacion"));
			

			if (getParsedValueFromElement(item, "nu-refact-efect") != null) {
				dPoliza.setNuRefactEfect(Integer.valueOf(getParsedValueFromElement(item, "nu-refact-efect")));
			}
			if (getParsedValueFromElement(item, "nu-refact-permit") != null) {
				dPoliza.setNuRefactPerm(Integer.valueOf(getParsedValueFromElement(item, "nu-refact-permit")));
			}
			if (getParsedValueFromElement(item, "nu-poliza-anterior") != null) {
				dPoliza.setNumPolizaAnterior(Integer.valueOf(getParsedValueFromElement(item, "nu-poliza-anterior")));
			}
			
			dPoliza.setCodVigencia(getParsedValueFromElement(item, "cod-vigencia"));
			dPoliza.setDescripVigencia(getParsedValueFromElement(item, "desc-vigencia"));
			dPoliza.setFechaDesde(getParsedValueFromElement(item, "fe-desde-vigencia"));
			dPoliza.setFechaHasta(getParsedValueFromElement(item, "fe-hasta-vigencia"));
			dPoliza.setCodVigenciaTecnica(getParsedValueFromElement(item, "cod-vigencia-tecnica"));
			dPoliza.setDescripVigenciaTecnica(getParsedValueFromElement(item, "desc-vigencia-tecnica"));
			dPoliza.setFechaDesdeVigenciaTecnica(getParsedValueFromElement(item, "fe-desde-vigencia-tecnica"));
			dPoliza.setFechaHastaVigenciaTecnica(getParsedValueFromElement(item, "fe-hasta-vigencia-tecnica"));
			dPoliza.setCodTpCalculo(getParsedValueFromElement(item, "cod-tp-calculo"));
			dPoliza.setDescTpCalculo(getParsedValueFromElement(item, "desc-tp-calculo"));
			
			resultado.setDetallePoliza(dPoliza);
			
			
			
			
			
			NodeList bienes = item.getElementsByTagName("bienes-list");
			if (bienes.getLength() > 0) {

				Element itemBienesList = (Element) elementos.item(0);
				NodeList elemBien = itemBienesList.getElementsByTagName("bien");
				ArrayList<Bien> bienesList = new ArrayList<Bien>();
				for (int k = 0; k < elemBien.getLength(); k++) {
					Element bienItem = (Element) elemBien.item(k);
					
					Bien bien = new Bien();
					if (getParsedValueFromElement(bienItem, "certificado") != null) {
						bien.setCertificado(Integer.valueOf(getParsedValueFromElement(bienItem, "certificado")));
					}

					if (getParsedValueFromElement(bienItem, "consec-bien") != null) {
						bien.setConsecutivo(Integer.valueOf(getParsedValueFromElement(bienItem, "consec-bien")));
					}

					if (getParsedValueFromElement(bienItem, "cod-bien") != null) {
						bien.setCodBien(Integer.valueOf(getParsedValueFromElement(bienItem, "cod-bien")));
					}

					bien.setBienDescripcion(getParsedValueFromElement(bienItem, "desc-bien"));

					bien.setFechaBaja(getParsedValueFromElement(bienItem, "fe-baja"));

					if (getParsedValueFromElement(bienItem, "pos-bien") != null) {
						bien.setPosBien(Integer.valueOf(getParsedValueFromElement(bienItem, "pos-bien")));
					}

					bien.setEtiquetaCapital(getParsedValueFromElement(bienItem, "etiqueta-capital"));
					bien.setHabilitoQuitar(getParsedValueFromElement(bienItem, "habilito-borrar"));
					bien.setHabilitoListaBienes(getParsedValueFromElement(bienItem, "habilito-lista-bienes"));
					bien.setHabilitoUbicacion(getParsedValueFromElement(bienItem, "habilito-ubicacion"));
					bien.setHabilitoBeneficiarios(getParsedValueFromElement(bienItem, "habilito-beneficiario"));
					bien.setHabilitoAcreedores(getParsedValueFromElement(bienItem, "habilito-acreedor"));
					

					NodeList datosListItem = bienItem.getElementsByTagName("datos-list");
					
					if (datosListItem.getLength() > 0) {

						Element itemDatos = (Element) datosListItem.item(0);
						NodeList datosItem = itemDatos.getElementsByTagName("dato");
						ArrayList<DatosBien> datosList = new ArrayList<DatosBien>();
						for (int l = 0; l < datosItem.getLength(); l++) {

							Element itemDato = (Element) datosItem.item(l);
							DatosBien objDato = new DatosBien();

							if (getParsedValueFromElement(itemDato, "cod-dato") != null) {
								objDato.setCodDato(getParsedValueFromElement(itemDato, "cod-dato"));
							}

							objDato.setDatoDesc(getParsedValueFromElement(itemDato, "desc-dato"));
							objDato.setValorCod(getParsedValueFromElement(itemDato, "cod-valor"));
							objDato.setValorDesc(getParsedValueFromElement(itemDato, "desc-valor"));
							objDato.setDatoTipo(getParsedValueFromElement(itemDato, "tipo-dato"));
							objDato.setVisibilidad(getParsedValueFromElement(itemDato, "visible"));
							objDato.setRequerido(getParsedValueFromElement(itemDato, "requerido"));
							
							if (getParsedValueFromElement(itemDato, "cant-decimales") != null) {
								objDato.setCantDecimales(getParsedValueFromElement(itemDato, "cant-decimales"));
							}

							if (getParsedValueFromElement(itemDato, "cod-tabla") != null) {
								objDato.setCodTabla(getParsedValueFromElement(itemDato, "cod-tabla"));
							}
							if (getParsedValueFromElement(itemDato, "longitud-dato") != null) {
								objDato.setLongitudDato(getParsedValueFromElement(itemDato, "longitud-dato"));
							}

							objDato.setRequerido(getParsedValueFromElement(itemDato, "obligatorio-en"));
							objDato.setColorNotificacion(getParsedValueFromElement(itemDato, "color-notificacion"));
				           
							datosList.add(objDato);
							
							bien.setUnDato(objDato);
							
						}
						bienesList.add(bien);	
				}
				
				resultado.setBienes(bienesList);
				
			}
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
	
	
	public ResultRefacturacionPoliza parsearRefacturarPoliza() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearRefacturarPoliza()");

		ResultRefacturacionPoliza resultado = new ResultRefacturacionPoliza();

		try {
			NodeList elementos = this.doc.getElementsByTagName("refacturacion");
			Element item = (Element) elementos.item(0);
			Refacturacion detalleRef = new Refacturacion();

			detalleRef.setHabilitoEmitir(getParsedValueFromElement(item, "habilito-emitir"));
			detalleRef.setHabilitoModificar(getParsedValueFromElement(item, "habilito-editar"));
			detalleRef.setHabilitoFacturacion(getParsedValueFromElement(item, "habilito-facturacion"));
			detalleRef.setHabilitoRefacturar(getParsedValueFromElement(item, "habilito-refacturar"));
			detalleRef.setNroCotizacion(getParsedValueFromElement(item, "nu-cotizacion"));
			detalleRef.setDescMensaje(getParsedValueFromElement(item, "desc-mensaje"));
			if(getParsedValueFromElement(item, "mt-premio_fact")!= null)
			detalleRef.setPremioFact(Double.valueOf(getParsedValueFromElement(item, "mt-premio_fact")));
			if (getParsedValueFromElement(item, "cod-moneda") != null) {
				detalleRef.setCodMoneda(Integer.valueOf(getParsedValueFromElement(item, "cod-moneda")));
			}
			detalleRef.setCodProducto(getParsedValueFromElement(item, "cod-producto"));
			detalleRef.setDescRamo(getParsedValueFromElement(item, "desc-ramo"));
			detalleRef.setCodPlan(Integer.valueOf(getParsedValueFromElement(item, "cod-plan-pago")));
			
			NodeList advertenciaList = item.getElementsByTagName("advertencia-list");
			
			if(advertenciaList != null ){
				for (int k = 0; k < advertenciaList.getLength(); k++) {
					Element itemAdvertencia = (Element) advertenciaList.item(k);
					NodeList mensajes = itemAdvertencia.getElementsByTagName("advertencia");
					Element itemMensajes = (Element) mensajes.item(0);
				
					Advertencia adv = new Advertencia();
					if (getParsedValueFromElement(itemMensajes, "mensaje") != null) {
						adv.setAdvertencia(getParsedValueFromElement(itemMensajes, "mensaje"));
						detalleRef.setUnAdvertencia(adv);
					}
				}
			}
			resultado.setDetalleRefacturacion(detalleRef);
			


			

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;
	}

	private EncabezadoCuota parsearEncabezadoCuota() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearEncabezadoCuota()");
		EncabezadoCuota resultado = new EncabezadoCuota();
		try {
			NodeList elementos = this.doc.getElementsByTagName("encabezado");
			Element item = (Element) elementos.item(0);
			if (getParsedValueFromElement(item, "cod-ramo") != null) {
				resultado.setCodRamo(Integer.valueOf(getParsedValueFromElement(item, "cod-ramo")));
			}
			if (getParsedValueFromElement(item, "nu-poliza") != null) {
				resultado.setNumPoliza(Integer.valueOf(getParsedValueFromElement(item, "nu-poliza")));
			}
			if (getParsedValueFromElement(item, "nu-certificado") != null) {
				resultado.setNumCertificado(Integer.valueOf(getParsedValueFromElement(item, "nu-certificado")));
			}
			resultado.setCodMoneda(getParsedValueFromElement(item, "cod-moneda"));
			resultado.setDescripMoneda(getParsedValueFromElement(item, "desc-moneda"));
			resultado.setCliente(getParsedValueFromElement(item, "desc-cliente"));
		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		}
		return resultado;
	}

	public ResultObtenerCuotasPoliza parsearObtenerCuotasPoliza() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerCuotasPoliza()");

		ResultObtenerCuotasPoliza resultado = new ResultObtenerCuotasPoliza();
		resultado.setEncabezado(this.parsearEncabezadoCuota());

		try {

			NodeList elementos = this.doc.getElementsByTagName("list-cuota");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				Cuota cuo = new Cuota();

				if (getParsedValueFromElement(item, "nu-endoso") != null) {
					cuo.setNumEndoso(Integer.valueOf(getParsedValueFromElement(item, "nu-endoso")));
				}
				cuo.setFechaEmision(getParsedValueFromElement(item, "fe-emision"));
				cuo.setFechaHasta(getParsedValueFromElement(item, "fe-hasta"));
				cuo.setDescripTipoTransaccion(getParsedValueFromElement(item, "desc-tp-transac"));
				cuo.setDescripEstado(getParsedValueFromElement(item, "desc-status"));
				cuo.setNumCuota(getParsedValueFromElement(item, "nu-cuota"));

				if (getParsedValueFromElement(item, "cant-prima-pura") != null) {
					cuo.setCantPrimaPura(Double.valueOf(getParsedValueFromElement(item, "cant-prima-pura")));
				}

				if (getParsedValueFromElement(item, "cant-prima") != null) {
					cuo.setCantPrima(Double.valueOf(getParsedValueFromElement(item, "cant-prima")));
				}

				if (getParsedValueFromElement(item, "cant-facturar") != null) {
					cuo.setCantFacturar(Double.valueOf(getParsedValueFromElement(item, "cant-facturar")));
				}
				cuo.setAnulada(getParsedValueFromElement(item, "anulada"));

				resultado.setUnaCuota(cuo);
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

	public ResultObtenerFacturasPoliza parsearObtenerFacturasPoliza() {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerFacturasPoliza()");

		ResultObtenerFacturasPoliza resultado = new ResultObtenerFacturasPoliza();
		resultado.setEncabezado(this.parsearEncabezadoCuota());

		try {

			NodeList elementos = this.doc.getElementsByTagName("list-facturacion");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				Factura fac = new Factura();

				fac.setNumFacturaElectronica(getParsedValueFromElement(item, "nu-cfe"));

				if (getParsedValueFromElement(item, "nu-factura") != null) {
					fac.setNumFactura(Integer.valueOf(getParsedValueFromElement(item, "nu-factura")));
				}
				fac.setFechaEmisionFactura(getParsedValueFromElement(item, "fe-emi-factura"));
				fac.setFechaVto(getParsedValueFromElement(item, "fe-vto"));
				fac.setFechaSegundoVto(getParsedValueFromElement(item, "fe-svto"));
				fac.setDescripEstadoFactura(getParsedValueFromElement(item, "desc-st-factura"));
				fac.setFechaPago(getParsedValueFromElement(item, "fe-pago"));

				if (getParsedValueFromElement(item, "cant-total-importe") != null) {
					fac.setTotalImporte(Double.valueOf(getParsedValueFromElement(item, "cant-total-importe")));
				}

				if (getParsedValueFromElement(item, "cant-importe") != null) {
					fac.setImporte(Double.valueOf(getParsedValueFromElement(item, "cant-importe")));
				}

				if (getParsedValueFromElement(item, "cant-rec-fin") != null) {
					fac.setRecargoFinanciero(Double.valueOf(getParsedValueFromElement(item, "cant-rec-fin")));
				}

				if (getParsedValueFromElement(item, "cant-punitario") != null) {
					fac.setPunitario(Double.valueOf(getParsedValueFromElement(item, "cant-punitario")));
				}

				if (getParsedValueFromElement(item, "cant-iva") != null) {
					fac.setIva(Double.valueOf(getParsedValueFromElement(item, "cant-iva")));
				}

				if (getParsedValueFromElement(item, "cant-otros") != null) {
					fac.setCantOtros(Double.valueOf(getParsedValueFromElement(item, "cant-otros")));
				}

				if (getParsedValueFromElement(item, "cant-total-factura") != null) {
					fac.setTotalFactura(Double.valueOf(getParsedValueFromElement(item, "cant-total-factura")));
				}
				fac.setAplicada(getParsedValueFromElement(item, "aplicada"));
				if (getParsedValueFromElement(item, "nu-document-id") != null) {
					fac.setDocumentId(getParsedValueFromElement(item, "nu-document-id"));
				}

				fac.setPermiteImprimir(getParsedValueFromElement(item, "permiteImprimir"));

				resultado.setUnaFactura(fac);
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

	public ResultObtenerImputacionesPoliza parsearObtenerImputacionesPoliza() {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerImputacionesPoliza()");

		ResultObtenerImputacionesPoliza resultado = new ResultObtenerImputacionesPoliza();
		resultado.setEncabezado(this.parsearEncabezadoCuota());

		try {

			NodeList elementos = this.doc.getElementsByTagName("list-imputacion");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				Imputacion imp = new Imputacion();

				if (getParsedValueFromElement(item, "nu-preliquidacion") != null) {
					imp.setNumPreliquidacion(Integer.valueOf(getParsedValueFromElement(item, "nu-preliquidacion")));
				}
				imp.setFechaPreliquidacion(getParsedValueFromElement(item, "fe-preliquidacion"));
				imp.setCodMoneda(getParsedValueFromElement(item, "cod-moneda"));

				if (getParsedValueFromElement(item, "cant-pagado") != null) {
					imp.setMonto(Double.valueOf((getParsedValueFromElement(item, "cant-pagado"))));
				}
				imp.setDebitoCredito(getParsedValueFromElement(item, "in-deb-cred"));
				imp.setDescripMoneda(getParsedValueFromElement(item, "desc-moneda"));

				if (getParsedValueFromElement(item, "cod-medio-pago") != null) {
					imp.setCodMedioPago(Integer.valueOf(getParsedValueFromElement(item, "cod-medio-pago")));
				}
				imp.setDescMedioPago(getParsedValueFromElement(item, "desc-medio-pago"));
				imp.setCodOrigenPago(getParsedValueFromElement(item, "cod-origen-pago"));
				imp.setDescOrigenPago(getParsedValueFromElement(item, "desc-origen-pago"));

				resultado.setUnaImputacion(imp);
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

	public ResultObtenerRemesasPoliza parsearObtenerRemesasPoliza() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerRemesasPoliza()");

		ResultObtenerRemesasPoliza resultado = new ResultObtenerRemesasPoliza();
		resultado.setEncabezado(this.parsearEncabezadoCuota());

		try {

			NodeList elementos = this.doc.getElementsByTagName("list-remesa");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				Remesa rem = new Remesa();

				if (getParsedValueFromElement(item, "nu-endoso") != null) {
					rem.setNumEndoso(Integer.valueOf(getParsedValueFromElement(item, "nu-endoso")));
				}

				if (getParsedValueFromElement(item, "nu-remesa") != null) {
					rem.setNumRemesa(Integer.valueOf(getParsedValueFromElement(item, "nu-remesa")));
				}
				rem.setFechaIngreso(getParsedValueFromElement(item, "fe-ingreso"));

				if (getParsedValueFromElement(item, "monto-orig") != null) {
					rem.setMontoOriginal(Double.valueOf(getParsedValueFromElement(item, "monto-orig")));
				}

				if (getParsedValueFromElement(item, "monto-saldo") != null) {
					rem.setMontoSaldo(Double.valueOf(getParsedValueFromElement(item, "monto-saldo")));
				}

				rem.setCodEstado(getParsedValueFromElement(item, "cod-estado"));
				rem.setDescripEstado(getParsedValueFromElement(item, "desc-estado"));
				rem.setCodCliente(getParsedValueFromElement(item, "nu-cedula-rif"));

				resultado.setUnaRemesa(rem);
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

	public ResultObtenerTotalesCuotasPoliza parsearObtenerTotalesCuotasPoliza() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerTotalesCuotasPoliza()");

		ResultObtenerTotalesCuotasPoliza resultado = new ResultObtenerTotalesCuotasPoliza();
		resultado.setEncabezado(this.parsearEncabezadoCuota());

		try {

			NodeList elementos = this.doc.getElementsByTagName("totales");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				DatosTotalesCuotas dat = new DatosTotalesCuotas();

				if (getParsedValueFromElement(item, "premio-emitido") != null) {
					dat.setPremioEmitido(Double.valueOf(getParsedValueFromElement(item, "premio-emitido")));
				}

				if (getParsedValueFromElement(item, "premio-facturado") != null) {
					dat.setPremioFacturado(Double.valueOf(getParsedValueFromElement(item, "premio-facturado")));
				}

				if (getParsedValueFromElement(item, "saldo-premio") != null) {
					dat.setSaldoPremio(Double.valueOf(getParsedValueFromElement(item, "saldo-premio")));
				}

				if (getParsedValueFromElement(item, "saldo-premio-vencido") != null) {
					dat.setSaldoPremioVencido(Double.valueOf(getParsedValueFromElement(item, "saldo-premio-vencido")));
				}

				if (getParsedValueFromElement(item, "total-facturado") != null) {
					dat.setTotalFacturado(Double.valueOf(getParsedValueFromElement(item, "total-facturado")));
				}

				if (getParsedValueFromElement(item, "total-cobrado") != null) {
					dat.setTotalCobrado(Double.valueOf(getParsedValueFromElement(item, "total-cobrado")));
				}

				if (getParsedValueFromElement(item, "preliq-pendiente") != null) {
					dat.setPreliqPendiente(Double.valueOf(getParsedValueFromElement(item, "preliq-pendiente")));
				}

				if (getParsedValueFromElement(item, "saldo-total") != null) {
					dat.setSaldoTotal(Double.valueOf(getParsedValueFromElement(item, "saldo-total")));
				}

				if (getParsedValueFromElement(item, "saldo-total-vencido") != null) {
					dat.setSaldoTotalVencido(Double.valueOf(getParsedValueFromElement(item, "saldo-total-vencido")));
				}

				if (getParsedValueFromElement(item, "total-comision") != null) {
					dat.setTotalComision(Double.valueOf(getParsedValueFromElement(item, "total-comision")));
				}

				if (getParsedValueFromElement(item, "comision-pagada") != null) {
					dat.setComisionLiberada(Double.valueOf(getParsedValueFromElement(item, "comision-pagada")));
				}

				if (getParsedValueFromElement(item, "total-comision-pagada") != null) {
					dat.setTotalComisionLiberada(Double.valueOf(getParsedValueFromElement(item, "total-comision-pagada")));
				}

				resultado.setUnDato(dat);
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

	public ResultObtenerPreliqPendientesPoliza parsearObtenerPreliqPendientesPoliza() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerPreliqPendientesPoliza()");

		ResultObtenerPreliqPendientesPoliza resultado = new ResultObtenerPreliqPendientesPoliza();
		resultado.setEncabezado(this.parsearEncabezadoCuota());

		try {

			NodeList elementos = this.doc.getElementsByTagName("list-preliquidacion");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				Preliquidacion pre = new Preliquidacion();

				if (getParsedValueFromElement(item, "nu-preliquidacion") != null) {
					pre.setCodPreliquidacion(Integer.valueOf(getParsedValueFromElement(item, "nu-preliquidacion")));
				}
				pre.setFechaPreliquidacion(getParsedValueFromElement(item, "fe-preliquidacion"));

				if (getParsedValueFromElement(item, "cant-preliquidacion") != null) {
					pre.setImporte(Double.valueOf(getParsedValueFromElement(item, "cant-preliquidacion")));
				}

				resultado.setUnaPreliq(pre);
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

	public ResultObtenerCotizacionPoliza parsearObtenerCotizacionPoliza() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerCotizacionPoliza()");

		ResultObtenerCotizacionPoliza resultado = new ResultObtenerCotizacionPoliza();

		try {
			NodeList elementos = this.doc.getElementsByTagName("poliza");
			Element item = (Element) elementos.item(0);
			CotizacionPoliza cotPoliza = new CotizacionPoliza();

			if (getParsedValueFromElement(item, "cod-sucursal") != null) {
				cotPoliza.setCodSucursal(Integer.valueOf(getParsedValueFromElement(item, "cod-sucursal")));
			}

			if (getParsedValueFromElement(item, "cod-ramo") != null) {
				cotPoliza.setCodRamo(Integer.valueOf(getParsedValueFromElement(item, "cod-ramo")));
			}

			if (getParsedValueFromElement(item, "nu-poliza") != null) {
				cotPoliza.setNumPoliza(Integer.valueOf(getParsedValueFromElement(item, "nu-poliza")));
			}

			if (getParsedValueFromElement(item, "nu-endoso") != null) {
				cotPoliza.setNumEndoso(Integer.valueOf(getParsedValueFromElement(item, "nu-endoso")));
			}

			if (getParsedValueFromElement(item, "nu-cotizacion") != null) {
				cotPoliza.setNumCotizacion(Integer.valueOf(getParsedValueFromElement(item, "nu-cotizacion")));
			}

			if (getParsedValueFromElement(item, "nu-consecutivo") != null) {
				cotPoliza.setNumCertificado(Integer.valueOf(getParsedValueFromElement(item, "nu-consecutivo")));
			}
			cotPoliza.setCodProducto(getParsedValueFromElement(item, "cod-producto"));
			cotPoliza.setDescripProducto(getParsedValueFromElement(item, "desc-producto"));
			cotPoliza.setNombreAsegurado(getParsedValueFromElement(item, "nom-asegurado"));
			cotPoliza.setFechaCotizacion(getParsedValueFromElement(item, "fe-cotizacion"));

			if (getParsedValueFromElement(item, "cod-productor") != null) {
				cotPoliza.setProductorCod(Integer.valueOf(getParsedValueFromElement(item, "cod-productor")));
			}
			cotPoliza.setProductorDesc(getParsedValueFromElement(item, "desc-productor"));
			

			//FIXME OIGRES YA ADD BROKER
			if (getParsedValueFromElement(item, "cod-broker") != null) {
				cotPoliza.setCodBroker(Integer.valueOf(getParsedValueFromElement(item, "cod-broker")));
			}

			cotPoliza.setDescBroker(getParsedValueFromElement(item, "desc-broker"));

			if (getParsedValueFromElement(item, "cod-motivo") != null) {
				cotPoliza.setCodMotivo(Integer.valueOf(getParsedValueFromElement(item, "cod-motivo")));
			}
			cotPoliza.setDescripMotivo(getParsedValueFromElement(item, "desc-motivo"));
			cotPoliza.setModoCalculoCod(getParsedValueFromElement(item, "cod-tp-calculo"));
			cotPoliza.setModoCalculoDesc(getParsedValueFromElement(item, "desc-tp-calculo"));

			if (getParsedValueFromElement(item, "cod-moneda") != null) {
				cotPoliza.setMonedaCod(Integer.valueOf(getParsedValueFromElement(item, "cod-moneda")));
			}
			cotPoliza.setMonedaDesc(getParsedValueFromElement(item, "desc-moneda"));
			cotPoliza.setVigenciaCod(getParsedValueFromElement(item, "cod-vigencia"));
			cotPoliza.setVigenciaDesc(getParsedValueFromElement(item, "desc-vigencia"));
			cotPoliza.setVigenciaDesde(getParsedValueFromElement(item, "fe-desde"));
			cotPoliza.setVigenciaHasta(getParsedValueFromElement(item, "fe-hasta"));

			if (getParsedValueFromElement(item, "premio-inf") != null) {
				cotPoliza.setPremioInformado(Double.valueOf(getParsedValueFromElement(item, "premio-inf")));
			}

			if (getParsedValueFromElement(item, "po-premio-inf") != null) {
				cotPoliza.setPorcentajePremioInformado(Integer.valueOf(getParsedValueFromElement(item, "po-premio-inf")));
			}

			if (getParsedValueFromElement(item, "cod-medio-pago") != null) {
				cotPoliza.setMedioPagoCod(Integer.valueOf(getParsedValueFromElement(item, "cod-medio-pago")));
			}
			cotPoliza.setMedioPagoDesc(getParsedValueFromElement(item, "desc-medio-pago"));

			if (getParsedValueFromElement(item, "cod-plan-pago") != null) {
				cotPoliza.setPlanPagoCod(getParsedValueFromElement(item, "cod-plan-pago"));
			}
			cotPoliza.setPlanPagoDesc(getParsedValueFromElement(item, "desc-plan-pago"));
			cotPoliza.setTarjeta(getSeudoTarjeta(getParsedValueFromElement(item, "nu-tarjeta")));

			resultado.setDatosCotizacionPoliza(cotPoliza);

			NodeList elementosCobertura = this.doc.getElementsByTagName("plan-cobertura");
			for (int i = 0; i < elementosCobertura.getLength(); i++) {
				Element valItem = (Element) elementosCobertura.item(i);
				PlanCoberturaPoliza cob = new PlanCoberturaPoliza();

				cob.setSeleccionado(getParsedValueFromElement(valItem, "in-seleccion"));
				cob.setCodigo(getParsedValueFromElement(valItem, "codigo"));
				cob.setDescripcion(getParsedValueFromElement(valItem, "descripcion"));

				if (getParsedValueFromElement(item, "premio") != null) {
					cob.setMontoPremio(Double.valueOf(getParsedValueFromElement(item, "premio")));
				}
				if (getParsedValueFromElement(item, "premio-facturacion") != null) {
					cob.setPremioFacturacion(Double.valueOf(getParsedValueFromElement(item, "premio-facturacion")));
				}
				resultado.setUnPlan(cob);

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

	public ResultObtenerContratantePoliza parsearObtenerContratantePoliza() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerContratantePoliza()");

		ResultObtenerContratantePoliza resultado = new ResultObtenerContratantePoliza();

		try {
			NodeList elementos = this.doc.getElementsByTagName("contratante");
			Element item = (Element) elementos.item(0);
			ContratanteAsegurado ase = new ContratanteAsegurado();

			ase.setCodNacionalidad(getParsedValueFromElement(item, "cod-nacionalidad"));
			ase.setCodCliente(getParsedValueFromElement(item, "nu-cedula-rif"));
			ase.setNombrePersona(getParsedValueFromElement(item, "nombre-persona"));
			ase.setRut(getParsedValueFromElement(item, "cuit"));

			if (getParsedValueFromElement(item, "cod-personeria") != null) {
				ase.setCodPersoneria(Integer.valueOf(getParsedValueFromElement(item, "cod-personeria")));
			}
			ase.setDescripPersoneria(getParsedValueFromElement(item, "desc-personeria"));
			ase.setCodCondicionIva(getParsedValueFromElement(item, "cod-condiva"));
			ase.setDescripCondicionIva(getParsedValueFromElement(item, "desc-condiva"));
			ase.setCodTarjeta(getParsedValueFromElement(item, "cod-tarjeta"));
			ase.setDescripTarjeta(getSeudoTarjeta(getParsedValueFromElement(item, "desc-tarjeta")));

			NodeList elementosDireEnvio = this.doc.getElementsByTagName("direccion-envio");
			Element itemEnvio = (Element) elementosDireEnvio.item(0);
			DireccionEC dEnvio = new DireccionEC();

			if (getParsedValueFromElement(itemEnvio, "nu-radio") != null) {
				dEnvio.setNumRadio(Integer.valueOf(getParsedValueFromElement(itemEnvio, "nu-radio")));
			}
			dEnvio.setDescripRadio(getParsedValueFromElement(itemEnvio, "desc-localidad"));

			if (getParsedValueFromElement(item, "cod-provincia") != null) {
				dEnvio.setCodDepto(Integer.valueOf(getParsedValueFromElement(item, "cod-provincia")));
			}
			dEnvio.setDescripDepto(getParsedValueFromElement(itemEnvio, "desc-estado"));
			dEnvio.setDireccion(getParsedValueFromElement(itemEnvio, "desc-calle"));
			dEnvio.setNumeroPuerta(getParsedValueFromElement(itemEnvio, "nu-calle"));
			dEnvio.setPiso(getParsedValueFromElement(itemEnvio, "nu-piso"));
			dEnvio.setApto(getParsedValueFromElement(itemEnvio, "departamento"));

			if (getParsedValueFromElement(itemEnvio, "cod-municipio") != null) {
				dEnvio.setCodLocalidad(Integer.valueOf(getParsedValueFromElement(itemEnvio, "cod-municipio")));
			}
			dEnvio.setDescripLocalidad(getParsedValueFromElement(itemEnvio, "desc-municipio"));

			ase.setDireccionEnvio(dEnvio);

			NodeList elementosDireCobro = this.doc.getElementsByTagName("direccion-cobro");
			Element itemCobro = (Element) elementosDireCobro.item(0);
			DireccionEC dCobro = new DireccionEC();

			if (getParsedValueFromElement(itemCobro, "nu-radio") != null) {
				dCobro.setNumRadio(Integer.valueOf(getParsedValueFromElement(itemCobro, "nu-radio")));
			}
			dCobro.setDescripRadio(getParsedValueFromElement(itemCobro, "desc-localidad"));

			if (getParsedValueFromElement(itemCobro, "cod-provincia") != null) {
				dCobro.setCodDepto(Integer.valueOf(getParsedValueFromElement(itemCobro, "cod-provincia")));
			}
			dCobro.setDescripDepto(getParsedValueFromElement(itemCobro, "desc-estado"));
			dCobro.setDireccion(getParsedValueFromElement(itemCobro, "desc-calle"));
			dCobro.setNumeroPuerta(getParsedValueFromElement(itemCobro, "nu-calle"));
			dCobro.setPiso(getParsedValueFromElement(itemCobro, "nu-piso"));
			dCobro.setApto(getParsedValueFromElement(itemCobro, "departamento"));

			if (getParsedValueFromElement(itemCobro, "cod-municipio") != null) {
				dCobro.setCodLocalidad(Integer.valueOf(getParsedValueFromElement(itemCobro, "cod-municipio")));
			}
			dCobro.setDescripLocalidad(getParsedValueFromElement(itemCobro, "desc-municipio"));

			ase.setDireccionCobro(dCobro);

			resultado.setDatosContratante(ase);

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;

	}

	public ResultObtenerAseguradoCertifPoliza parsearObtenerAseguradoCertifPoliza() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerAseguradoCertifPoliza()");

		ResultObtenerAseguradoCertifPoliza resultado = new ResultObtenerAseguradoCertifPoliza();

		try {
			NodeList elementos = this.doc.getElementsByTagName("asegurado");
			Element item = (Element) elementos.item(0);
			ContratanteAsegurado ase = new ContratanteAsegurado();

			ase.setCodNacionalidad(getParsedValueFromElement(item, "cod-nacionalidad"));
			ase.setCodCliente(getParsedValueFromElement(item, "nu-cedula-rif"));
			ase.setNombrePersona(getParsedValueFromElement(item, "nombre-persona"));
			ase.setRut(getParsedValueFromElement(item, "cuit"));

			if (getParsedValueFromElement(item, "cod-personeria") != null) {
				ase.setCodPersoneria(Integer.valueOf(getParsedValueFromElement(item, "cod-personeria")));
			}
			ase.setDescripPersoneria(getParsedValueFromElement(item, "desc-personeria"));
			ase.setCodCondicionIva(getParsedValueFromElement(item, "cod-condiva"));
			ase.setDescripCondicionIva(getParsedValueFromElement(item, "desc-condiva"));
			ase.setCodTarjeta(getParsedValueFromElement(item, "cod-tarjeta"));
			ase.setDescripTarjeta(getSeudoTarjeta(getParsedValueFromElement(item, "desc-tarjeta")));

			NodeList elementosDireEnvio = this.doc.getElementsByTagName("direccion-envio");
			Element itemEnvio = (Element) elementosDireEnvio.item(0);
			DireccionEC dEnvio = new DireccionEC();

			if (getParsedValueFromElement(itemEnvio, "nu-radio") != null) {
				dEnvio.setNumRadio(Integer.valueOf(getParsedValueFromElement(itemEnvio, "nu-radio")));
			}
			dEnvio.setDescripRadio(getParsedValueFromElement(itemEnvio, "desc-localidad"));

			if (getParsedValueFromElement(item, "cod-provincia") != null) {
				dEnvio.setCodDepto(Integer.valueOf(getParsedValueFromElement(item, "cod-provincia")));
			}
			dEnvio.setDescripDepto(getParsedValueFromElement(itemEnvio, "desc-estado"));
			dEnvio.setDireccion(getParsedValueFromElement(itemEnvio, "desc-calle"));
			dEnvio.setNumeroPuerta(getParsedValueFromElement(itemEnvio, "nu-calle"));
			dEnvio.setPiso(getParsedValueFromElement(itemEnvio, "nu-piso"));
			dEnvio.setApto(getParsedValueFromElement(itemEnvio, "departamento"));

			if (getParsedValueFromElement(itemEnvio, "cod-municipio") != null) {
				dEnvio.setCodLocalidad(Integer.valueOf(getParsedValueFromElement(itemEnvio, "cod-municipio")));
			}
			dEnvio.setDescripLocalidad(getParsedValueFromElement(itemEnvio, "desc-municipio"));

			ase.setDireccionEnvio(dEnvio);

			NodeList elementosDireCobro = this.doc.getElementsByTagName("direccion-cobro");
			Element itemCobro = (Element) elementosDireCobro.item(0);
			DireccionEC dCobro = new DireccionEC();

			if (getParsedValueFromElement(itemCobro, "nu-radio") != null) {
				dCobro.setNumRadio(Integer.valueOf(getParsedValueFromElement(itemCobro, "nu-radio")));
			}
			dCobro.setDescripRadio(getParsedValueFromElement(itemCobro, "desc-localidad"));

			if (getParsedValueFromElement(itemCobro, "cod-provincia") != null) {
				dCobro.setCodDepto(Integer.valueOf(getParsedValueFromElement(itemCobro, "cod-provincia")));
			}
			dCobro.setDescripDepto(getParsedValueFromElement(itemCobro, "desc-estado"));
			dCobro.setDireccion(getParsedValueFromElement(itemCobro, "desc-calle"));
			dCobro.setNumeroPuerta(getParsedValueFromElement(itemCobro, "nu-calle"));
			dCobro.setPiso(getParsedValueFromElement(itemCobro, "nu-piso"));
			dCobro.setApto(getParsedValueFromElement(itemCobro, "departamento"));

			if (getParsedValueFromElement(itemCobro, "cod-municipio") != null) {
				dCobro.setCodLocalidad(Integer.valueOf(getParsedValueFromElement(itemCobro, "cod-municipio")));
			}
			dCobro.setDescripLocalidad(getParsedValueFromElement(itemCobro, "desc-municipio"));

			ase.setDireccionCobro(dCobro);

			resultado.setDatosAsegurado(ase);

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;
	}

	private EncabezadoDetallePoliza parsearEncabezadoDetallePoliza() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearEncabezadoDetallePoliza()");
		EncabezadoDetallePoliza resultado = new EncabezadoDetallePoliza();
		try {
			NodeList elementos = this.doc.getElementsByTagName("encabezado");
			Element item = (Element) elementos.item(0);

			if (getParsedValueFromElement(item, "cod-ramo") != null) {
				resultado.setCodRamo(Integer.valueOf(getParsedValueFromElement(item, "cod-ramo")));
			}

			if (getParsedValueFromElement(item, "nu-poliza") != null) {
				resultado.setNumPoliza(Integer.valueOf(getParsedValueFromElement(item, "nu-poliza")));
			}

			if (getParsedValueFromElement(item, "nu-endoso") != null) {
				resultado.setNumEndoso(Integer.valueOf(getParsedValueFromElement(item, "nu-endoso")));
			}

			if (getParsedValueFromElement(item, "nu-certificado") != null) {
				resultado.setNumCertificado(Integer.valueOf(getParsedValueFromElement(item, "nu-certificado")));
			}

			if (getParsedValueFromElement(item, "cod-estado") != null) {
				resultado.setCodEstado(Integer.valueOf(getParsedValueFromElement(item, "cod-estado")));
			}
			resultado.setDescripEstado(getParsedValueFromElement(item, "desc-estado"));
			resultado.setCodProducto(getParsedValueFromElement(item, "cod-producto"));
			resultado.setCodPlanCobertura(getParsedValueFromElement(item, "cod-plan-cobertura"));
			resultado.setDescripPlanCobertura(getParsedValueFromElement(item, "desc-plan-cobertura"));

			if (getParsedValueFromElement(item, "cod-agencia") != null) {
				resultado.setCodAgencia(Integer.valueOf(getParsedValueFromElement(item, "cod-agencia")));
			}
			resultado.setDescripAgencia(getParsedValueFromElement(item, "desc-agencia"));
			resultado.setAnexos(getParsedValueFromElement(item, "anexos"));
			resultado.setCodNacionalidad(getParsedValueFromElement(item, "cod-nacionalidad"));

			if (getParsedValueFromElement(item, "nu-cedula-rif") != null) {
				resultado.setCodCliente(getParsedValueFromElement(item, "nu-cedula-rif"));
			}
			resultado.setNombreAsegurado(getParsedValueFromElement(item, "nombre-asegurado"));

			if (getParsedValueFromElement(item, "cod-bien-aseg") != null) {
				resultado.setCodBienAsegurado(Integer.valueOf(getParsedValueFromElement(item, "cod-bien-aseg")));
			}
			resultado.setDescripBienAsegurado(getParsedValueFromElement(item, "desc-bien-aseg"));
		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		}
		return resultado;
	}

	public ResultObtenerBienesAseguradosPoliza parsearObtenerBienesAseguradosPoliza() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerBienesAseguradosPoliza()");

		ResultObtenerBienesAseguradosPoliza resultado = new ResultObtenerBienesAseguradosPoliza();
		resultado.setEncabezadoDetallePoliza(this.parsearEncabezadoDetallePoliza());

		try {
			NodeList elementos = this.doc.getElementsByTagName("bien");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				BienAsegurado bien = new BienAsegurado();

				if (getParsedValueFromElement(item, "certificado") != null) {
					bien.setCertificado(Integer.valueOf(getParsedValueFromElement(item, "certificado")));
				}

				if (getParsedValueFromElement(item, "consec-bien") != null) {
					bien.setConsecutivo(Integer.valueOf(getParsedValueFromElement(item, "consec-bien")));
				}
				if (getParsedValueFromElement(item, "cod-bien") != null) {
					bien.setCodigoBien(Integer.valueOf(getParsedValueFromElement(item, "cod-bien")));
				}
				bien.setDescripcion(getParsedValueFromElement(item, "desc-bien"));
				bien.setFechaBaja(getParsedValueFromElement(item, "fe-baja"));

				if (getParsedValueFromElement(item, "pos-bien") != null) {
					bien.setPosicionBien(Integer.valueOf(getParsedValueFromElement(item, "pos-bien")));
				}
				bien.setHabilitarBotonAnexos(getParsedValueFromElement(item, "boton-anexos"));
				bien.setHabilitarBotonTextos(getParsedValueFromElement(item, "boton-textos"));
				bien.setHabilitarListaBienes(getParsedValueFromElement(item, "boton-lista-bienes"));
				bien.setHabilitarUbicacion(getParsedValueFromElement(item, "boton-ubicacion"));
				bien.setHabilitarBeneficiarios(getParsedValueFromElement(item, "boton-beneficiarios"));
				bien.setHabilitarAcreedores(getParsedValueFromElement(item, "boton-acreedores"));

				NodeList elem = item.getElementsByTagName("dato");

				for (int j = 0; j < elem.getLength(); j++) {
					Element valItem = (Element) elem.item(j);
					DatosBienCert bienCert = new DatosBienCert();

					bienCert.setDatoDesc(getParsedValueFromElement(valItem, "desc-dato"));
					bienCert.setValorCod(getParsedValueFromElement(valItem, "cod-valor"));
					bienCert.setValorDesc(getParsedValueFromElement(valItem, "desc-valor"));
					bienCert.setCotizar(getParsedValueFromElement(valItem, "cotizar"));

					bien.setUnDatoBienCert(bienCert);

				}

				resultado.setUnBienAsegurado(bien);

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

	public ResultObtenerTextosPoliza parsearObtenerTextosPoliza() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerTextosPoliza()");

		ResultObtenerTextosPoliza resultado = new ResultObtenerTextosPoliza();
		resultado.setEncabezadoDetallePoliza(this.parsearEncabezadoDetallePoliza());

		try {
			NodeList elementos = this.doc.getElementsByTagName("texto");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				Texto tex = new Texto();

				if (getParsedValueFromElement(item, "nu-consecutivo") != null) {
					tex.setNumConsecutivo(Integer.valueOf(getParsedValueFromElement(item, "nu-consecutivo")));
				}

				if (getParsedValueFromElement(item, "nu-endoso") != null) {
					tex.setNumEndoso(Integer.valueOf(getParsedValueFromElement(item, "nu-endoso")));
				}

				if (getParsedValueFromElement(item, "cod-texto") != null) {
					tex.setCodTexto(Integer.valueOf(getParsedValueFromElement(item, "cod-texto")));
				}
				tex.setDescripTexto(getParsedValueFromElement(item, "desc-texto"));
				tex.setImpresion(getParsedValueFromElement(item, "in-impresion"));
				tex.setFechaDesde(getParsedValueFromElement(item, "fe-desde"));
				tex.setPersiste(getParsedValueFromElement(item, "in-persiste"));
				tex.setDarBaja(getParsedValueFromElement(item, "in-cambio-fecha"));
				tex.setFechaHasta(getParsedValueFromElement(item, "fe-hasta"));

				if (getParsedValueFromElement(item, "cod-sucursal") != null) {
					tex.setCodSucursal(Integer.valueOf(getParsedValueFromElement(item, "cod-sucursal")));
				}

				resultado.setUnTexto(tex);

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

	public ResultObtenerAnexosPoliza parsearObtenerAnexosPoliza() {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerAnexosPoliza()");

		ResultObtenerAnexosPoliza resultado = new ResultObtenerAnexosPoliza();
		resultado.setEncabezadoDetallePoliza(this.parsearEncabezadoDetallePoliza());

		try {
			NodeList elementos = this.doc.getElementsByTagName("linea-anexo");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				Anexo ane = new Anexo();

				if (getParsedValueFromElement(item, "cod-linea") != null) {
					ane.setCodigo(Integer.valueOf(getParsedValueFromElement(item, "cod-linea")));
				}
				ane.setDescripcion(getParsedValueFromElement(item, "desc-linea"));

				NodeList elem = item.getElementsByTagName("clausula");

				for (int j = 0; j < elem.getLength(); j++) {
					Element valItem = (Element) elem.item(j);
					Clausula cla = new Clausula();

					cla.setCodClausulaPoliza(getParsedValueFromElement(item, "cod-clausula"));
					cla.setDescripcionClausula(getParsedValueFromElement(valItem, "desc-clausula"));
					cla.setModifica(getParsedValueFromElement(valItem, "in-exclusion"));
					cla.setImpresion(getParsedValueFromElement(valItem, "in-impresion"));

					if (getParsedValueFromElement(item, "cod-ramo") != null) {
						cla.setCodRamo(Integer.valueOf(getParsedValueFromElement(item, "cod-ramo")));
					}

					ane.setUnaClausula(cla);

				}

				resultado.setUnAnexo(ane);

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

	public ResultObtenerListaBienesPoliza parsearObtenerListaBienesPoliza() {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerListaBienesPoliza()");

		ResultObtenerListaBienesPoliza resultado = new ResultObtenerListaBienesPoliza();
		resultado.setEncabezadoDetallePoliza(this.parsearEncabezadoDetallePoliza());

		try {
			NodeList elementos = this.doc.getElementsByTagName("lista-bien");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				ObjetoBien obj = new ObjetoBien();

				if (getParsedValueFromElement(item, "cod-objeto") != null) {
					obj.setCodObjeto(Integer.valueOf(getParsedValueFromElement(item, "cod-objeto")));
				}
				obj.setDescripcionObjeto(getParsedValueFromElement(item, "desc-objeto"));
				obj.setDescripcion(getParsedValueFromElement(item, "descripcion"));

				if (getParsedValueFromElement(item, "valor") != null) {
					obj.setValor(Double.valueOf(getParsedValueFromElement(item, "valor")));
				}

				if (getParsedValueFromElement(item, "nu-unidades") != null) {
					obj.setNumUnidades(Integer.valueOf(getParsedValueFromElement(item, "nu-unidades")));
				}

				if (getParsedValueFromElement(item, "valor-total") != null) {
					obj.setValorTotal(Double.valueOf(getParsedValueFromElement(item, "valor-total")));
				}

				if (getParsedValueFromElement(item, "cod-marca") != null) {
					obj.setCodMarca(Integer.valueOf(getParsedValueFromElement(item, "cod-marca")));
				}
				obj.setDescripcionMarca(getParsedValueFromElement(item, "desc-marca"));
				obj.setSerial(getParsedValueFromElement(item, "serial"));

				if (getParsedValueFromElement(item, "nu-siniestro") != null) {
					obj.setNumOrdinal(Integer.valueOf(getParsedValueFromElement(item, "nu-siniestro")));
				}

				resultado.setUnObjeto(obj);

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

	public ResultObtenerUbicacionBienPoliza parsearObtenerUbicacionBienPoliza() {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerUbicacionBienPoliza()");

		ResultObtenerUbicacionBienPoliza resultado = new ResultObtenerUbicacionBienPoliza();
		resultado.setEncabezadoDetallePoliza(this.parsearEncabezadoDetallePoliza());

		try {
			NodeList elementos = this.doc.getElementsByTagName("ubicacion");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				Ubicacion ubi = new Ubicacion();

				if (getParsedValueFromElement(item, "cod-postal") != null) {
					ubi.setRadio(Integer.valueOf(getParsedValueFromElement(item, "cod-postal")));
				}
				ubi.setDescripRadio(getParsedValueFromElement(item, "desc-localidad"));
				ubi.setCalle(getParsedValueFromElement(item, "desc-calle"));

				ubi.setNumero(getParsedValueFromElement(item, "nu-calle"));
				ubi.setPiso(getParsedValueFromElement(item, "nu-piso"));
				ubi.setNumDpto(getParsedValueFromElement(item, "nu-depto"));

				if (getParsedValueFromElement(item, "cod-municipio") != null) {
					ubi.setCodLocalidad(Integer.valueOf(getParsedValueFromElement(item, "cod-municipio")));
				}
				ubi.setLocalidad(getParsedValueFromElement(item, "desc-municipio"));

				if (getParsedValueFromElement(item, "cod-provincia") != null) {
					ubi.setCodDepartamento(Integer.valueOf(getParsedValueFromElement(item, "cod-provincia")));
				}
				ubi.setDepartamento(getParsedValueFromElement(item, "desc-provincia"));

				if (getParsedValueFromElement(item, "cod-pais") != null) {
					ubi.setCodPais(Integer.valueOf(getParsedValueFromElement(item, "cod-pais")));
				}
				ubi.setPais(getParsedValueFromElement(item, "desc-pais"));
				ubi.setTelefono(getParsedValueFromElement(item, "telefono"));
				ubi.setPadron(getParsedValueFromElement(item, "padron-catastral"));
				ubi.setFax(getParsedValueFromElement(item, "fax"));

				resultado.setUbicacion(ubi);

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

	public ResultObtenerBeneficiariosPoliza parsearObtenerBeneficiariosPoliza() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerBeneficiariosPoliza()");

		ResultObtenerBeneficiariosPoliza resultado = new ResultObtenerBeneficiariosPoliza();
		resultado.setEncabezadoDetallePoliza(this.parsearEncabezadoDetallePoliza());

		try {
			NodeList elementos = this.doc.getElementsByTagName("beneficiario");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				Beneficiario ben = new Beneficiario();

				ben.setTipoDocumento(getParsedValueFromElement(item, "tp-documento"));
				ben.setNumDocumento(getParsedValueFromElement(item, "nu-documento"));
				ben.setNombreBeneficiario(getParsedValueFromElement(item, "nombres"));
				ben.setCodParentesco(getParsedValueFromElement(item, "cod-parentesco"));

				if (getParsedValueFromElement(item, "po-participacion") != null) {
					ben.setPorcentajeParticipacion(Double.valueOf(getParsedValueFromElement(item, "po-participacion")));
				}
				ben.setFechaInclusion(getParsedValueFromElement(item, "fe-inclusion"));
				ben.setFechaNacimiento(getParsedValueFromElement(item, "fe-nacimiento"));
				ben.setFechaExclusion(getParsedValueFromElement(item, "fe-exclusion"));
				ben.setObservaciones(getParsedValueFromElement(item, "observaciones"));

				resultado.setUnBeneficiario(ben);

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

	public ResultObtenerAcreedoresPoliza parsearObtenerAcreedoresPoliza() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerAcreedoresPoliza()");

		ResultObtenerAcreedoresPoliza resultado = new ResultObtenerAcreedoresPoliza();
		resultado.setEncabezadoDetallePoliza(this.parsearEncabezadoDetallePoliza());

		try {
			NodeList elementos = this.doc.getElementsByTagName("acreedor");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				Acreedor acr = new Acreedor();

				if (getParsedValueFromElement(item, "cod-acreedor") != null) {
					acr.setCodAcreedor(Integer.valueOf(getParsedValueFromElement(item, "cod-acreedor")));
				}
				acr.setDescripAcreedor(getParsedValueFromElement(item, "desc-acreedor"));
				acr.setCodTipoAcreedor(getParsedValueFromElement(item, "cod-tp-acreedor"));
				acr.setDescripTipoAcreedor(getParsedValueFromElement(item, "desc-tp-acreedor"));

				if (getParsedValueFromElement(item, "cod-objeto") != null) {
					acr.setCodObjeto(Integer.valueOf(getParsedValueFromElement(item, "cod-objeto")));
				}
				acr.setDescripObjeto(getParsedValueFromElement(item, "desc-objeto"));

				if (getParsedValueFromElement(item, "po-participacion") != null) {
					acr.setPorcentajeParticipacion(Integer.valueOf(getParsedValueFromElement(item, "po-participacion")));
				}
				acr.setFechaExclusion(getParsedValueFromElement(item, "fe-exclusion"));

				resultado.setUnAcreedor(acr);

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

	public ResultObtenerFranquiciasPoliza parsearObtenerFranquiciasPoliza() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerFranquiciasPoliza()");

		ResultObtenerFranquiciasPoliza resultado = new ResultObtenerFranquiciasPoliza();

		try {
			NodeList elementos = this.doc.getElementsByTagName("bien");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				Bien bie = new Bien();

				if (getParsedValueFromElement(item, "certificado") != null) {
					bie.setCertificado(Integer.valueOf(getParsedValueFromElement(item, "certificado")));
				}

				if (getParsedValueFromElement(item, "consec-bien") != null) {
					bie.setConsecutivo(Integer.valueOf(getParsedValueFromElement(item, "consec-bien")));
				}

				if (getParsedValueFromElement(item, "cod-bien") != null) {
					bie.setCodBien(Integer.valueOf(getParsedValueFromElement(item, "cod-bien")));
				}
				bie.setBienDescripcion(getParsedValueFromElement(item, "desc-bien"));
				bie.setFechaBaja(getParsedValueFromElement(item, "fe-baja"));

				if (getParsedValueFromElement(item, "pos-bien") != null) {
					bie.setPosBien(Integer.valueOf(getParsedValueFromElement(item, "pos-bien")));
				}

				NodeList elemFra = item.getElementsByTagName("franquicia");
				for (int j = 0; j < elemFra.getLength(); j++) {
					Element valItem = (Element) elemFra.item(j);
					Franquicia fra = new Franquicia();

					if (getParsedValueFromElement(valItem, "cod-ramo-franquicia") != null) {
						fra.setRamoCod(Integer.valueOf(getParsedValueFromElement(valItem, "cod-ramo-franquicia")));
					}

					if (getParsedValueFromElement(valItem, "cod-franquicia") != null) {
						fra.setCodFranquicia(Integer.valueOf(getParsedValueFromElement(valItem, "cod-franquicia")));
					}
					fra.setDescripcionFranquicia(getParsedValueFromElement(valItem, "desc-franquicia"));

					if (getParsedValueFromElement(valItem, "porc-franquicia") != null) {
						fra.setFranquiciaPorc(Double.valueOf(getParsedValueFromElement(valItem, "porc-franquicia")));
					}

					if (getParsedValueFromElement(valItem, "monto-franquicia") != null) {
						fra.setFranquiciaMonto(Double.valueOf(getParsedValueFromElement(valItem, "monto-franquicia")));
					}

					if (getParsedValueFromElement(valItem, "porc-deducible") != null) {
						fra.setDeduciblePorc(Double.valueOf(getParsedValueFromElement(valItem, "porc-deducible")));
					}

					if (getParsedValueFromElement(valItem, "monto-deducible") != null) {
						fra.setDeducibleMonto(Double.valueOf(getParsedValueFromElement(valItem, "monto-deducible")));
					}

					bie.setUnaFranquicia(fra);
				}

				resultado.setUnBien(bie);

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

	public ResultObtenerAhorrosPoliza parsearObtenerAhorrosPoliza() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerAhorrosPoliza()");

		ResultObtenerAhorrosPoliza resultado = new ResultObtenerAhorrosPoliza();
		resultado.setEncabezadoDetallePoliza(this.parsearEncabezadoDetallePoliza());

		try {
			NodeList elementos = this.doc.getElementsByTagName("ahorro");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				Ahorro aho = new Ahorro();

				aho.setCodProceso(getParsedValueFromElement(item, "cod-proceso"));
				aho.setDescripcionProceso(getParsedValueFromElement(item, "desc-proceso"));

				if (getParsedValueFromElement(item, "monto-pagar") != null) {
					aho.setMontoPagar(Double.valueOf(getParsedValueFromElement(item, "monto-pagar")));
				}

				if (getParsedValueFromElement(item, "monto-aporte") != null) {
					aho.setMontoAporte(Double.valueOf(getParsedValueFromElement(item, "monto-aporte")));
				}

				if (getParsedValueFromElement(item, "cod-plan-pago") != null) {
					aho.setCodPlanPago(Integer.valueOf(getParsedValueFromElement(item, "cod-plan-pago")));
				}
				aho.setDescripcionPlanPago(getParsedValueFromElement(item, "desc-plan-pago"));
				aho.setFechaDesde(getParsedValueFromElement(item, "fe-desde"));
				aho.setFechaHasta(getParsedValueFromElement(item, "fe-hasta"));

				resultado.setUnAhorro(aho);

			}

			NodeList elemDato = this.doc.getElementsByTagName("dato");
			for (int i = 0; i < elemDato.getLength(); i++) {
				Element valItem = (Element) elemDato.item(i);
				DatosCalculoRenta dat = new DatosCalculoRenta();

				dat.setDescripDato(getParsedValueFromElement(valItem, "desc-dato"));

				if (getParsedValueFromElement(valItem, "cod-valor") != null) {
					dat.setCodValor(Double.valueOf(getParsedValueFromElement(valItem, "cod-valor")));
				}
				dat.setDescripValor(getParsedValueFromElement(valItem, "desc-valor"));

				resultado.setUnDatoCalculoRenta(dat);
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

	public ResultObtenerRemesasXCliente parsearObtenerRemesasXCliente() {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerRemesasXCliente()");

		ResultObtenerRemesasXCliente resultado = new ResultObtenerRemesasXCliente();

		try {
			NodeList elementos = this.doc.getElementsByTagName("datos-remesa");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				Remesa rem = new Remesa();

				if (getParsedValueFromElement(item, "nu-remesa") != null) {
					rem.setNumRemesa(Integer.valueOf(getParsedValueFromElement(item, "nu-remesa")));
				}
				rem.setFechaIngreso(getParsedValueFromElement(item, "fe-ingreso"));
				rem.setTipoRemesa(getParsedValueFromElement(item, "tp-remesa"));

				if (getParsedValueFromElement(item, "cod-medio-pago") != null) {
					rem.setCodMedioPago(Integer.valueOf(getParsedValueFromElement(item, "cod-medio-pago")));
				}
				rem.setDescripMedioPago(getParsedValueFromElement(item, "desc-medio-pago"));
				rem.setCodOrigen(getParsedValueFromElement(item, "cod-origen"));
				rem.setDescripOrigen(getParsedValueFromElement(item, "desc-origen"));
				rem.setCodMoneda(getParsedValueFromElement(item, "cod-moneda"));
				rem.setDescripMoneda(getParsedValueFromElement(item, "desc-moneda"));

				if (getParsedValueFromElement(item, "mt-total") != null) {
					rem.setMontoTotal(Double.valueOf(getParsedValueFromElement(item, "mt-total")));
				}

				if (getParsedValueFromElement(item, "mt-saldo") != null) {
					rem.setMontoSaldo(Double.valueOf(getParsedValueFromElement(item, "mt-saldo")));
				}

				if (getParsedValueFromElement(item, "cod-compania") != null) {
					rem.setCodCompania(Integer.valueOf(getParsedValueFromElement(item, "cod-compania")));
				}

				if (getParsedValueFromElement(item, "nu-ingreso") != null) {
					rem.setNumIngreso(Integer.valueOf(getParsedValueFromElement(item, "nu-ingreso")));
				}

				rem.setDocumentId(getParsedValueFromElement(item, "nu-document-id"));

				resultado.setUnaRemesa(rem);

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

	public ResultObtenerDatosRemesa parsearObtenerDatosRemesa() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerDatosRemesa()");

		ResultObtenerDatosRemesa resultado = new ResultObtenerDatosRemesa();

		try {
			NodeList elementos = this.doc.getElementsByTagName("datos-remesa");
			Element item = (Element) elementos.item(0);
			Remesa rem = new Remesa();

			if (getParsedValueFromElement(item, "nu-remesa") != null) {
				rem.setNumRemesa(Integer.valueOf(getParsedValueFromElement(item, "nu-remesa")));
			}
			rem.setFechaIngreso(getParsedValueFromElement(item, "fe-ingreso"));

			if (getParsedValueFromElement(item, "cod-origen-tipo") != null) {
				rem.setCodOrigenTipo(Integer.valueOf(getParsedValueFromElement(item, "cod-origen-tipo")));
			}
			rem.setTipoRemesa(getParsedValueFromElement(item, "cod-tipo-remesa"));
			rem.setDescripTipoRemesa(getParsedValueFromElement(item, "desc-tipo-remesa"));

			if (getParsedValueFromElement(item, "cod-medio-pago") != null) {
				rem.setCodMedioPago(Integer.valueOf(getParsedValueFromElement(item, "cod-medio-pago")));
			}
			rem.setDescripMedioPago(getParsedValueFromElement(item, "desc-medio-pago"));
			rem.setCodOrigen(getParsedValueFromElement(item, "cod-origen"));
			rem.setDescripOrigen(getParsedValueFromElement(item, "desc-origen"));

			if (getParsedValueFromElement(item, "cod-productor") != null) {
				rem.setCodProductor(Integer.valueOf(getParsedValueFromElement(item, "cod-productor")));
			}
			
			if (getParsedValueFromElement(item, "cod-broker") != null) {
				rem.setCodBroker(Integer.valueOf(getParsedValueFromElement(item, "cod-broker")));
			}

			rem.setDescBroker(getParsedValueFromElement(item, "desc-broker"));
			
			rem.setProductor(getParsedValueFromElement(item, "desc-productor"));
			rem.setCodNacionalidad(getParsedValueFromElement(item, "cod-nacionalidad"));
			rem.setCodCliente(getParsedValueFromElement(item, "nu-cedula-rif"));
			rem.setCliente(getParsedValueFromElement(item, "desc-cliente"));

			if (getParsedValueFromElement(item, "cod-cobrador") != null) {
				rem.setCodCobrador(Integer.valueOf(getParsedValueFromElement(item, "cod-cobrador")));
			}
			rem.setDescripCobrador(getParsedValueFromElement(item, "desc-cobrador"));
			rem.setCodMoneda(getParsedValueFromElement(item, "cod-moneda"));
			rem.setDescripMoneda(getParsedValueFromElement(item, "desc-moneda"));
			rem.setCodEstado(getParsedValueFromElement(item, "cod-estado"));
			rem.setDescripEstado(getParsedValueFromElement(item, "desc-estado"));

			if (getParsedValueFromElement(item, "mt-total") != null) {
				rem.setMontoTotal(Double.valueOf(getParsedValueFromElement(item, "mt-total")));
			}

			if (getParsedValueFromElement(item, "mt-saldo") != null) {
				rem.setMontoSaldo(Double.valueOf(getParsedValueFromElement(item, "mt-saldo")));
			}

			if (getParsedValueFromElement(item, "mt-conv-total") != null) {
				rem.setMontoConversionTotal(Double.valueOf(getParsedValueFromElement(item, "mt-conv-total")));
			}

			if (getParsedValueFromElement(item, "mt-conv-saldo") != null) {
				rem.setMontoConversionSaldo(Double.valueOf(getParsedValueFromElement(item, "mt-conv-saldo")));
			}

			if (getParsedValueFromElement(item, "cod-sucursal") != null) {
				rem.setCodSucursal(Integer.valueOf(getParsedValueFromElement(item, "cod-sucursal")));
			}
			rem.setDescripSucursal(getParsedValueFromElement(item, "desc-sucursal"));
			rem.setCodUsuario(getParsedValueFromElement(item, "cod-usuario"));
			rem.setUsuario(getParsedValueFromElement(item, "desc-usuario"));
			rem.setFechaActualizacion(getParsedValueFromElement(item, "fe-actualizacion"));

			if (getParsedValueFromElement(item, "nu-secuencia") != null) {
				rem.setNumSecuencia(Integer.valueOf(getParsedValueFromElement(item, "nu-secuencia")));
			}

			resultado.setDatosRemesa(rem);

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;
	}

	public ResultObtenerCancelacionesRemesa parsearObtenerCancelacionesRemesa() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerCancelacionesRemesa()");

		ResultObtenerCancelacionesRemesa resultado = new ResultObtenerCancelacionesRemesa();

		try {
			NodeList elementos = this.doc.getElementsByTagName("cancelacion");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				Cancelacion can = new Cancelacion();

				if (getParsedValueFromElement(item, "nu-cancelacion") != null) {
					can.setNumCancelacion(Integer.valueOf(getParsedValueFromElement(item, "nu-cancelacion")));
				}
				can.setCodCancelacion(getParsedValueFromElement(item, "cod-cancelacion"));
				can.setDescripMoneda(getParsedValueFromElement(item, "desc-moneda"));

				if (getParsedValueFromElement(item, "mt-cancelado") != null) {
					can.setMontoCancelado(Double.valueOf(getParsedValueFromElement(item, "mt-cancelado")));
				}
				can.setFechaActualizacion(getParsedValueFromElement(item, "fe-actualizacion"));
				can.setCodUsuario(getParsedValueFromElement(item, "cod-usuario"));

				if (getParsedValueFromElement(item, "nu-liquidacion") != null) {
					can.setNumLiquidacion(Integer.valueOf(getParsedValueFromElement(item, "nu-liquidacion")));
				}

				resultado.setUnaCancelacion(can);

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

	public ResultObtenerDetalleRemesa parsearObtenerDetalleRemesa() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerDetalleRemesa()");

		ResultObtenerDetalleRemesa resultado = new ResultObtenerDetalleRemesa();

		try {
			NodeList elementos = this.doc.getElementsByTagName("detalle-remesa");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				DetalleRemesa rem = new DetalleRemesa();

				if (getParsedValueFromElement(item, "cod-tp-movimiento") != null) {
					rem.setCodTipoMovimiento(Integer.valueOf(getParsedValueFromElement(item, "cod-tp-movimiento")));
				}
				rem.setDescripTipoMovimiento(getParsedValueFromElement(item, "desc-tp-movimiento"));
				rem.setCodMoneda(getParsedValueFromElement(item, "cod-moneda"));
				rem.setDescripMoneda(getParsedValueFromElement(item, "desc-moneda"));

				if (getParsedValueFromElement(item, "nu-boleta") != null) {
					rem.setNumBoleta(Integer.valueOf(getParsedValueFromElement(item, "nu-boleta")));
				}
				rem.setFecha(getParsedValueFromElement(item, "fe-fecha"));
				rem.setPosdatado(getParsedValueFromElement(item, "posdatado"));

				if (getParsedValueFromElement(item, "cod-banco") != null) {
					rem.setCodBanco(Integer.valueOf(getParsedValueFromElement(item, "cod-banco")));
				}
				rem.setDescripBanco(getParsedValueFromElement(item, "desc-banco"));

				if (getParsedValueFromElement(item, "nu-cheque") != null) {
					rem.setNumCheque(Integer.valueOf(getParsedValueFromElement(item, "nu-cheque")));
				}

				if (getParsedValueFromElement(item, "mt-cheque") != null) {
					rem.setMontoCheque(Double.valueOf(getParsedValueFromElement(item, "mt-cheque")));
				}

				resultado.setUnDetalle(rem);

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

	public ResultObtenerOrigenRemesaAuto parsearObtenerOrigenRemesaAutomatica() {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerOrigenRemesaAutomatica()");

		ResultObtenerOrigenRemesaAuto resultado = new ResultObtenerOrigenRemesaAuto();

		try {
			NodeList elementos = this.doc.getElementsByTagName("remesa-automatica");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				RemesaAutomatica rem = new RemesaAutomatica();

				rem.setNumOrigen(getParsedValueFromElement(item, "nu-origen"));

				if (getParsedValueFromElement(item, "nu-valor") != null) {
					rem.setNumValor(Integer.valueOf(getParsedValueFromElement(item, "nu-valor")));
				}
				rem.setDescripDatos(getParsedValueFromElement(item, "desc-datos"));

				resultado.setUnaRemesa(rem);

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

	public ResultObtenerNotasSiniestro parsearObtenerNotasSiniestro() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerNotasSiniestro()");

		ResultObtenerNotasSiniestro resultado = new ResultObtenerNotasSiniestro();

		try {
			NodeList elementos = this.doc.getElementsByTagName("nota");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				Nota not = new Nota();

				not.setEnlace(getParsedValueFromElement(item, "enlace"));

				if (getParsedValueFromElement(item, "nu-nota") != null) {
					not.setNumNota(Integer.valueOf(getParsedValueFromElement(item, "nu-nota")));
				}
				not.setTipoNota(getParsedValueFromElement(item, "cod-nota"));
				not.setDescripNota(getParsedValueFromElement(item, "des-nota"));
				not.setFechaActualizacion(getParsedValueFromElement(item, "fe-actualizacion"));
				not.setObservacion(getParsedValueFromElement(item, "observacion"));
				not.setUsuario(getParsedValueFromElement(item, "usuario"));

				if (getParsedValueFromElement(item, "cod-st-nota") != null) {
					not.setCodEstadoNota(Integer.valueOf(getParsedValueFromElement(item, "cod-st-nota")));
				}
				not.setDescripEstadoNota(getParsedValueFromElement(item, "des-st-nota"));
				not.setNumComprobante(getParsedValueFromElement(item, "nu-comprobante"));
				not.setDescripSector(getParsedValueFromElement(item, "desc-sector"));
				if (getParsedValueFromElement(item, "cod-sub-tipo-nota") != null) {
					not.setCodSubTipoNota(Integer.valueOf(getParsedValueFromElement(item, "cod-sub-tipo-nota")));
				}
				not.setDescSubTipoNota(getParsedValueFromElement(item, "des-sub-tipo-nota"));
				

				resultado.setUnaNota(not);

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

	public ResultObtenerBonifNoSiniestroPoliza parsearObtenerBonifNoSiniestroPoliza() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerBonifNoSiniestroPoliza()");

		ResultObtenerBonifNoSiniestroPoliza resultado = new ResultObtenerBonifNoSiniestroPoliza();

		try {

			NodeList elemPoli = this.doc.getElementsByTagName("poliza");
			Element itemPoli = (Element) elemPoli.item(0);

			NodeList elemCertPoli = itemPoli.getElementsByTagName("certificado-pol");
			for (int i = 0; i < elemCertPoli.getLength(); i++) {
				Element itemCert = (Element) elemCertPoli.item(i);
				BonificacionPoliza bonPol = new BonificacionPoliza();

				if (getParsedValueFromElement(itemCert, "nu-certificado-pol") != null) {
					bonPol.setNumCertificado(Integer.valueOf(getParsedValueFromElement(itemCert, "nu-certificado-pol")));
				}

				if (getParsedValueFromElement(itemCert, "clausula-pol") != null) {
					bonPol.setClausula(getParsedValueFromElement(itemCert, "clausula-pol"));
				}

				NodeList elemPoliEnc = itemCert.getElementsByTagName("poliza-bns");
				for (int k = 0; k < elemPoliEnc.getLength(); k++) {
					Element itemPoliEnc = (Element) elemPoliEnc.item(k);
					InfoPolizaSiniestro infPol = new InfoPolizaSiniestro();

					EncabezadoPoliza encPol = new EncabezadoPoliza();

					if (getParsedValueFromElement(itemPoliEnc, "cod-ramo") != null) {
						encPol.setCodRamo(Integer.valueOf(getParsedValueFromElement(itemPoliEnc, "cod-ramo")));
					}

					if (getParsedValueFromElement(itemPoliEnc, "nu-poliza") != null) {
						encPol.setNumPoliza(Integer.valueOf(getParsedValueFromElement(itemPoliEnc, "nu-poliza")));
					}
					if (getParsedValueFromElement(itemPoliEnc, "clausula-pol") != null) {
						encPol.setClausula(getParsedValueFromElement(itemPoliEnc, "clausula-pol"));
					}

					infPol.setDatosPoliza(encPol);

					NodeList elemSin = itemPoliEnc.getElementsByTagName("siniestro");
					for (int j = 0; j < elemSin.getLength(); j++) {
						Element itemSin = (Element) elemSin.item(j);
						DatosBasicosSiniestro datSin = new DatosBasicosSiniestro();

						if (getParsedValueFromElement(itemSin, "anio") != null) {
							datSin.setAnio(Integer.valueOf(getParsedValueFromElement(itemSin, "anio")));
						}

						if (getParsedValueFromElement(itemSin, "ramo") != null) {
							datSin.setCodRamo(Integer.valueOf(getParsedValueFromElement(itemSin, "ramo")));
						}

						if (getParsedValueFromElement(itemSin, "nu-siniestro") != null) {
							datSin.setNumSiniestro(Integer.valueOf(getParsedValueFromElement(itemSin, "nu-siniestro")));
						}

						infPol.setUnDatoBasicoSiniestro(datSin);

					}

					bonPol.setUnaInfoPolizaSiniestro(infPol);
				}

				resultado.setUnaBonPoliza(bonPol);

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

	public ResultObtenerDeclaracionSiniestro parsearObtenerDeclaracionSiniestro() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerDeclaracionSiniestro()");

		ResultObtenerDeclaracionSiniestro resultado = new ResultObtenerDeclaracionSiniestro();

		try {

			NodeList elementosDatos = this.doc.getElementsByTagName("datos-siniestro");
			Element Item = (Element) elementosDatos.item(0);
			Siniestro sin = new Siniestro();

			if (getParsedValueFromElement(Item, "nu-anio") != null) {
				sin.setAnio(Integer.valueOf(getParsedValueFromElement(Item, "nu-anio")));
			}

			if (getParsedValueFromElement(Item, "cod-ramo") != null) {
				sin.setCodRamo(Integer.valueOf(getParsedValueFromElement(Item, "cod-ramo")));
			}

			if (getParsedValueFromElement(Item, "nu-siniestro") != null) {
				sin.setNumSiniestro(Integer.valueOf(getParsedValueFromElement(Item, "nu-siniestro")));
			}
			sin.setDescripRamo(getParsedValueFromElement(Item, "desc-ramo"));

			if (getParsedValueFromElement(Item, "nu-notificacion") != null) {
				sin.setNumNotificacion(Integer.valueOf(getParsedValueFromElement(Item, "nu-notificacion")));
			}
			sin.setFechaNotificacion(getParsedValueFromElement(Item, "fe-notificacion"));
			sin.setDescripNotificador(getParsedValueFromElement(Item, "desc-notificador"));
			sin.setDescripPremioCero(getParsedValueFromElement(Item, "desc-premio-cero"));
			sin.setFechaDeclaracion(getParsedValueFromElement(Item, "fe-declaracion"));
			sin.setFechaOcurrencia(getParsedValueFromElement(Item, "fe-ocurrencia"));
			sin.setHoraOcurrencia(getParsedValueFromElement(Item, "hora-ocurrencia"));
			sin.setFechaRegistro(getParsedValueFromElement(Item, "fe-registro"));
			sin.setSerieDenuncia(getParsedValueFromElement(Item, "serie-denuncia"));

			if (getParsedValueFromElement(Item, "nu-denuncia") != null) {
				sin.setNumDenuncia(Integer.valueOf(getParsedValueFromElement(Item, "nu-denuncia")));
			}

			if (getParsedValueFromElement(Item, "nu-poliza") != null) {
				sin.setNumPoliza(Integer.valueOf(getParsedValueFromElement(Item, "nu-poliza")));
			}

			if (getParsedValueFromElement(Item, "nu-certificado") != null) {
				sin.setNumCertificado(Integer.valueOf(getParsedValueFromElement(Item, "nu-certificado")));
			}

			if (getParsedValueFromElement(Item, "nu-endoso") != null) {
				sin.setNumEndoso(Integer.valueOf(getParsedValueFromElement(Item, "nu-endoso")));
			}
			sin.setFacultativo(getParsedValueFromElement(Item, "in-facultativo"));
			sin.setSiniestroGraciable(getParsedValueFromElement(Item, "tp-graciable"));
			sin.setCodNacionalidad(getParsedValueFromElement(Item, "cod-nacionalidad"));

			if (getParsedValueFromElement(Item, "nu-cedula-rif") != null) {
				sin.setCodCliente(Integer.valueOf(getParsedValueFromElement(Item, "nu-cedula-rif")));
			}
			sin.setDescripCliente(getParsedValueFromElement(Item, "desc-cliente"));
			sin.setCodCausaSiniestro(getParsedValueFromElement(Item, "cod-causa-siniestro"));
			sin.setDescripCausa(getParsedValueFromElement(Item, "desc-causa"));
			sin.setDescripPlan(getParsedValueFromElement(Item, "desc-plan"));
			sin.setCodTipoSiniestro(getParsedValueFromElement(Item, "cod-tp-siniestro"));
			sin.setDescripTipoSiniestro(getParsedValueFromElement(Item, "desc-tp-siniestro"));
			sin.setLugarOcurrencia(getParsedValueFromElement(Item, "lugar-ocurrencia"));

			if (getParsedValueFromElement(Item, "cod-codigo-postal") != null) {
				sin.setCodCodigoPostal(Integer.valueOf(getParsedValueFromElement(Item, "cod-codigo-postal")));
			}

			sin.setDescripLocalidad(getParsedValueFromElement(Item, "desc-localidad"));
			sin.setDescripProvincia(getParsedValueFromElement(Item, "desc-provincia"));
			sin.setDescripSiniestro(getParsedValueFromElement(Item, "desc-siniestro"));

			if (getParsedValueFromElement(Item, "cod-sucursal") != null) {
				sin.setCodSucursal(Integer.valueOf(getParsedValueFromElement(Item, "cod-sucursal")));
			}
			sin.setDescripSucursal(getParsedValueFromElement(Item, "desc-sucursal"));

			if (getParsedValueFromElement(Item, "cod-sucursal-denuncia") != null) {
				sin.setCodSucursalDenuncia(Integer.valueOf(getParsedValueFromElement(Item, "cod-sucursal-denuncia")));
			}
			sin.setDescripSucursalDenuncia(getParsedValueFromElement(Item, "desc-sucursal-denuncia"));
			sin.setUsuario(getParsedValueFromElement(Item, "desc-usuario"));
			sin.setJuzgado(getParsedValueFromElement(Item, "juzgado"));
			sin.setComisaria(getParsedValueFromElement(Item, "comisaria"));

			if (getParsedValueFromElement(Item, "nu-acta") != null) {
				sin.setNumActa(Integer.valueOf(getParsedValueFromElement(Item, "nu-acta")));
			}
			sin.setFinanciera(getParsedValueFromElement(Item, "in-financiera"));
			sin.setTecnica(getParsedValueFromElement(Item, "in-tecnica"));
			sin.setEnlace(getParsedValueFromElement(Item, "cod-enlace"));

			resultado.setSiniestro(sin);

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;

	}

	public ResultObtenerDatosSiniestro parsearObtenerDatosSiniestro() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerDatosSiniestro()");

		ResultObtenerDatosSiniestro resultado = new ResultObtenerDatosSiniestro();

		try {

			NodeList elementosDatos = this.doc.getElementsByTagName("siniestro");
			Element Item = (Element) elementosDatos.item(0);
			DatosSiniestro sin = new DatosSiniestro();

			if (getParsedValueFromElement(Item, "nu-anio") != null) {
				sin.setAnio(Integer.valueOf(getParsedValueFromElement(Item, "nu-anio")));
			}

			if (getParsedValueFromElement(Item, "cd-ramo") != null) {
				sin.setCodRamo(Integer.valueOf(getParsedValueFromElement(Item, "cd-ramo")));
			}

			if (getParsedValueFromElement(Item, "nu-siniestro") != null) {
				sin.setNumSiniestro(Integer.valueOf(getParsedValueFromElement(Item, "nu-siniestro")));
			}

			if (getParsedValueFromElement(Item, "nu-poliza") != null) {
				sin.setNumPoliza(Integer.valueOf(getParsedValueFromElement(Item, "nu-poliza")));
			}

			if (getParsedValueFromElement(Item, "nu-certificado") != null) {
				sin.setNumCertificado(Integer.valueOf(getParsedValueFromElement(Item, "nu-certificado")));
			}

			if (getParsedValueFromElement(Item, "nu-endoso") != null) {
				sin.setNumEndoso(Integer.valueOf(getParsedValueFromElement(Item, "nu-endoso")));
			}
			sin.setDescripCliente(getParsedValueFromElement(Item, "desc-cliente"));

			NodeList elementos = Item.getElementsByTagName("list-datos-parametricos");

			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				BienPoliza bie = new BienPoliza();

				if (getParsedValueFromElement(item, "cod-bien") != null) {
					bie.setCodBien(Integer.valueOf(getParsedValueFromElement(item, "cod-bien")));
				}
				bie.setDescripBien(getParsedValueFromElement(item, "desc-bien"));

				NodeList elem = item.getElementsByTagName("list-cesionario");

				for (int j = 0; j < elem.getLength(); j++) {
					Element valItem = (Element) elem.item(j);
					String desc = new String();

					desc = getParsedValueFromElement(valItem, "desc-secionario");

					if (desc != null) {
						bie.setUnString(desc);
					}
				}

				NodeList element = item.getElementsByTagName("list-dato");

				for (int k = 0; k < element.getLength(); k++) {
					Element valorItem = (Element) element.item(k);
					DatoParametricoPoliza dat = new DatoParametricoPoliza();

					dat.setCodDato(getParsedValueFromElement(valorItem, "cod-dato"));
					dat.setDescripDato(getParsedValueFromElement(valorItem, "desc-dato"));
					dat.setValorDato(getParsedValueFromElement(valorItem, "valor-dato"));

					bie.setUnDatoParametrico(dat);
				}

				sin.setUnBienPoliza(bie);
			}

			resultado.setDatosSiniestro(sin);

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}
		return resultado;

	}

	public ResultObtenerSiniestrosCliente parsearObtenerSiniestrosCliente() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerSiniestrosCliente()");

		ResultObtenerSiniestrosCliente resultado = new ResultObtenerSiniestrosCliente();

		try {

			NodeList elementosDatos = this.doc.getElementsByTagName("siniestro");

			Element Item = (Element) elementosDatos.item(0);
			Cliente cli = new Cliente();

			cli.setNacionalidad(getParsedValueFromElement(Item, "cd-nacionalidad"));
			cli.setCodCliente(getParsedValueFromElement(Item, "nu-cedula-rif"));
			cli.setApellidoRazon(getParsedValueFromElement(Item, "desc-cliente"));

			resultado.setDatosCliente(cli);

			NodeList elementos = this.doc.getElementsByTagName("list-siniestralidad");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				Siniestro sin = new Siniestro();

				if (getParsedValueFromElement(item, "nu-poliza") != null) {
					sin.setNumPoliza(Integer.valueOf(getParsedValueFromElement(item, "nu-poliza")));
				}

				if (getParsedValueFromElement(item, "nu-certificado") != null) {
					sin.setNumCertificado(Integer.valueOf(getParsedValueFromElement(item, "nu-certificado")));
				}

				if (getParsedValueFromElement(item, "nu-anio") != null) {
					sin.setAnio(Integer.valueOf(getParsedValueFromElement(item, "nu-anio")));
				}

				if (getParsedValueFromElement(item, "cd-ramo") != null) {
					sin.setCodRamo(Integer.valueOf(getParsedValueFromElement(item, "cd-ramo")));
				}

				if (getParsedValueFromElement(item, "nu-siniestro") != null) {
					sin.setNumSiniestro(Integer.valueOf(getParsedValueFromElement(item, "nu-siniestro")));
				}
				sin.setFechaOcurrencia(getParsedValueFromElement(item, "fe-ocurrencia"));

				NodeList elem = item.getElementsByTagName("list-bien");

				for (int j = 0; j < elem.getLength(); j++) {
					Element valItem = (Element) elem.item(j);
					InfoSiniestro inf = new InfoSiniestro();

					if (getParsedValueFromElement(valItem, "nu-tercero") != null) {
						inf.setNumTercero(Integer.valueOf(getParsedValueFromElement(valItem, "nu-tercero")));
					}

					if (getParsedValueFromElement(valItem, "nu-asegurado") != null) {
						inf.setNumAsegurado(Integer.valueOf(getParsedValueFromElement(valItem, "nu-asegurado")));
					}
					inf.setDescripBien(getParsedValueFromElement(valItem, "desc-bien"));

					if (getParsedValueFromElement(valItem, "cd-ramo") != null) {
						inf.setCodRamo(Integer.valueOf(getParsedValueFromElement(valItem, "cd-ramo")));
					}
					inf.setCodCobertura(getParsedValueFromElement(valItem, "cd-cobertura"));
					inf.setCodTipoReserva(getParsedValueFromElement(valItem, "cd-tp-reserva"));

					if (getParsedValueFromElement(valItem, "cd-hecho-gen") != null) {
						inf.setCodHechoGen(Integer.valueOf(getParsedValueFromElement(valItem, "cd-hecho-gen")));
					}
					inf.setDescripMoneda(getParsedValueFromElement(valItem, "desc-moneda"));
					inf.setEstadoSiniestro(getParsedValueFromElement(valItem, "st-siniestro"));
					inf.setDescripEstado(getParsedValueFromElement(valItem, "desc-estado"));

					if (getParsedValueFromElement(valItem, "mt-reserva") != null) {
						inf.setMontoReserva(Double.valueOf(getParsedValueFromElement(valItem, "mt-reserva")));
					}

					if (getParsedValueFromElement(valItem, "mt-costo") != null) {
						inf.setMontoCosto(Double.valueOf(getParsedValueFromElement(valItem, "mt-costo")));
					}

					sin.setUnInfoSiniestro(inf);
				}

				resultado.setUnSiniestro(sin);
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

	public ResultObtenerPolizas parsearObtenerPolizas() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerPolizas()");

		ResultObtenerPolizas resultado = new ResultObtenerPolizas();

		try {
			NodeList elementos = this.doc.getElementsByTagName("poliza");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				DatosPoliza dat = new DatosPoliza();

				if (getParsedValueFromElement(item, "cd-sucursal") != null) {
					dat.setCodSucursal(Integer.valueOf(getParsedValueFromElement(item, "cd-sucursal")));
				}

				if (getParsedValueFromElement(item, "nu-poliza") != null) {
					dat.setNumPoliza(Integer.valueOf(getParsedValueFromElement(item, "nu-poliza")));
				}
				dat.setFechaEmision(getParsedValueFromElement(item, "fe-suscripcion"));
				dat.setFechaDesde(getParsedValueFromElement(item, "fe-desde"));
				dat.setFechaHasta(getParsedValueFromElement(item, "fe-hasta"));
				dat.setCliente(getParsedValueFromElement(item, "desc-contratante"));
				dat.setDescripEstado(getParsedValueFromElement(item, "desc-estado"));
				dat.setAsegurado(getParsedValueFromElement(item, "desc-asegurado"));
				dat.setCodMoneda(getParsedValueFromElement(item, "cd-moneda"));
				dat.setDescripMoneda(getParsedValueFromElement(item, "desc-moneda"));
				dat.setCodCliente(getParsedValueFromElement(item, "nu-contratante"));
				dat.setCodTipoFacturacion(getParsedValueFromElement(item, "tp-facturacion"));
				dat.setDescripTipoFacturacion(getParsedValueFromElement(item, "desc-facturacion"));
				dat.setDescripPromocion(getParsedValueFromElement(item, "desc-promocion"));
				dat.setCodPromocion(getParsedValueFromElement(item, "cd-promocion"));
				dat.setCodRenovacion(getParsedValueFromElement(item, "in-renovacion"));
				dat.setDescripRenovacion(getParsedValueFromElement(item, "desc-renovacion"));
				dat.setCodCompromiso(getParsedValueFromElement(item, "cant-compromiso"));

				if (getParsedValueFromElement(item, "nu-refacturacion") != null) {
					dat.setNumRefactEfectiva(Integer.valueOf(getParsedValueFromElement(item, "nu-refacturacion")));
				}

				if (getParsedValueFromElement(item, "nu-poliza-ant") != null) {
					dat.setNumPolizaAnterior(Integer.valueOf(getParsedValueFromElement(item, "nu-poliza-ant")));
				}

				if (getParsedValueFromElement(item, "nu-poliza-sig") != null) {
					dat.setNumPolizaSiguiente(Integer.valueOf(getParsedValueFromElement(item, "nu-poliza-sig")));
				}

				dat.setCodVigencia(getParsedValueFromElement(item, "tp-vigencia"));
				dat.setDescripVigencia(getParsedValueFromElement(item, "desc-vigencia"));
				dat.setAnulacionCorrida(getParsedValueFromElement(item, "in-anulacion"));
				if (getParsedValueFromElement(item, "cod-ramo") != null) {
					dat.setCodRamo(Integer.valueOf(getParsedValueFromElement(item, "cod-ramo")));
				}
				dat.setDescripRamo(getParsedValueFromElement(item, "desc-ramo"));
				dat.setCodProducto(getParsedValueFromElement(item, "cod-producto"));
				dat.setDescripProducto(getParsedValueFromElement(item, "desc-producto"));
				dat.setHabilitoRecuotificar(getParsedValueFromElement(item, "habilito-recuotificar"));
				dat.setHabilitoModificarSinPremio(getParsedValueFromElement(item, "habilito-modif-sin-premio"));
				dat.setHabilitoNoRenovar(getParsedValueFromElement(item, "habilito-no-renovar"));
				dat.setHabilitoEndosar(getParsedValueFromElement(item, "habilito-endosar"));
				dat.setHabilitoClonar(getParsedValueFromElement(item, "habilito-clonar"));
				dat.setHabilitoAnular(getParsedValueFromElement(item, "habilito-anular"));
				dat.setEnviarFacturaEMail(getParsedValueFromElement(item, "envio-fact-mail"));
				resultado.setUnaPoliza(dat);
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

	public ResultObtenerSiniestrosConFiltro parsearObtenerSiniestrosConFiltro() {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerSiniestrosConFiltro()");

		ResultObtenerSiniestrosConFiltro resultado = new ResultObtenerSiniestrosConFiltro();

		try {
			NodeList elementos = this.doc.getElementsByTagName("siniestro");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				DatosBasicosSiniestro dat = new DatosBasicosSiniestro();

				if (getParsedValueFromElement(item, "nu-anio") != null) {
					dat.setAnio(Integer.valueOf(getParsedValueFromElement(item, "nu-anio")));
				}

				if (getParsedValueFromElement(item, "cd-ramo") != null) {
					dat.setCodRamo(Integer.valueOf(getParsedValueFromElement(item, "cd-ramo")));
				}

				if (getParsedValueFromElement(item, "nu-siniestro") != null) {
					dat.setNumSiniestro(Integer.valueOf(getParsedValueFromElement(item, "nu-siniestro")));
				}

				if (getParsedValueFromElement(item, "nu-certificado") != null) {
					dat.setNumCertificado(Integer.valueOf(getParsedValueFromElement(item, "nu-certificado")));
				}

				if (getParsedValueFromElement(item, "nu-poliza") != null) {
					dat.setNumPoliza(Integer.valueOf(getParsedValueFromElement(item, "nu-poliza")));
				}
				dat.setFechaOcurrencia(getParsedValueFromElement(item, "fe-ocurrencia"));
				dat.setFechaDeclaracion(getParsedValueFromElement(item, "fe-declaracion"));
				dat.setCodNacionalidad(getParsedValueFromElement(item, "cd-nacionalidad"));

				if (getParsedValueFromElement(item, "nu-cedula-rif") != null) {
					dat.setCodCliente(Integer.valueOf(getParsedValueFromElement(item, "nu-cedula-rif")));
				}
				dat.setCliente(getParsedValueFromElement(item, "desc-cliente"));
				dat.setDescripEjecutivo(getParsedValueFromElement(item, "desc-usuario"));
				dat.setAsegurado(getParsedValueFromElement(item, "desc-asegurado"));
				resultado.setUnDatoBasico(dat);

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

	public ResultValidarAnulacionPoliza parsearValidarAnulacionPoliza() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearValidarAnulacionPoliza()");

		ResultValidarAnulacionPoliza resultado = new ResultValidarAnulacionPoliza();

		try {
			NodeList elementos = this.doc.getElementsByTagName("anulacion");
			Element item = (Element) elementos.item(0);
			CotizacionAnulacion cot = new CotizacionAnulacion();

			cot.setFechaDesde(getParsedValueFromElement(item, "fe-desde"));
			cot.setFechaHasta(getParsedValueFromElement(item, "fe-hasta"));
			cot.setFechaAnulacion(getParsedValueFromElement(item, "fe-anulacion"));
			cot.setEnlace(getParsedValueFromElement(item, "enlace"));

			if (getParsedValueFromElement(item, "cod-causa-anulacion") != null) {
				cot.setCodCausaAnulacion(Integer.valueOf(getParsedValueFromElement(item, "cod-causa-anulacion")));
			}
			cot.setDescripCausaAnulacion(getParsedValueFromElement(item, "desc-causa-anulacion"));
			cot.setCodModoCalculo(getParsedValueFromElement(item, "cod-modo-calculo"));
			cot.setDescripModoCalculo(getParsedValueFromElement(item, "desc-modo-calculo"));
			cot.setCliente(getParsedValueFromElement(item, "desc-cliente"));

			if (getParsedValueFromElement(item, "cod-productor") != null) {
				cot.setCodProductor(Integer.valueOf(getParsedValueFromElement(item, "cod-productor")));
			}
			cot.setDescripProductor(getParsedValueFromElement(item, "desc-productor"));
			
			if (getParsedValueFromElement(item, "cod-broker") != null) {
				cot.setCodBroker(Integer.valueOf(getParsedValueFromElement(item, "cod-broker")));
			}

			cot.setDescBroker(getParsedValueFromElement(item, "desc-broker"));
			
			cot.setAnioSiniestro(getParsedValueFromElement(item, "anio-siniestro"));

			if (getParsedValueFromElement(item, "cod-ramo-siniestro") != null) {
				cot.setCodRamoSiniestro(Integer.valueOf(getParsedValueFromElement(item, "cod-ramo-siniestro")));
			}

			if (getParsedValueFromElement(item, "nu-siniestro") != null) {
				cot.setNumSiniestro(Integer.valueOf(getParsedValueFromElement(item, "nu-siniestro")));
			}

			if (getParsedValueFromElement(item, "mt-premio-poliza") != null) {
				cot.setMontoPremioPoliza(Double.valueOf(getParsedValueFromElement(item, "mt-premio-poliza")));
			}

			if (getParsedValueFromElement(item, "mt-premio-anulacion") != null) {
				cot.setMontoPremioAnulacion(Double.valueOf(getParsedValueFromElement(item, "mt-premio-anulacion")));
			}
			cot.setFechaImpago(getParsedValueFromElement(item, "fe-impago"));
			cot.setMensajeAviso(getParsedValueFromElement(item, "mensaje-aviso"));

			if (getParsedValueFromElement(item, "nu-cotizacion") != null) {
				cot.setNumCotizacion(Integer.valueOf(getParsedValueFromElement(item, "nu-cotizacion")));
			}
			cot.setBotonNotasVisible(getParsedValueFromElement(item, "boton-notas-visible"));
			cot.setBotonConsultaVisible(getParsedValueFromElement(item, "boton-consulta-visible"));
			cot.setBotonImprimirVisible(getParsedValueFromElement(item, "boton-imprimir-visible"));
			cot.setBotonAnularVisible(getParsedValueFromElement(item, "boton-anular-visible"));
			cot.setBotonSiniestroVisible(getParsedValueFromElement(item, "boton-siniestro-visible"));
			cot.setHabilitoCausa(getParsedValueFromElement(item, "habilito-causa"));
			cot.setHabilitoModoCalculo(getParsedValueFromElement(item, "habilito-modo-calc"));
			cot.setHabilitoAnular(getParsedValueFromElement(item, "habilito-anular"));
			cot.setHabilitoSiniestro(getParsedValueFromElement(item, "habilito-siniestro"));

			resultado.setCotizacionAnulacion(cot);

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;
	}

	public ResultListaPlanesPagoRec parsearListaPlanesPagoRecuotificacion() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearListaPlanesPagoRecuotificacion()");
		ResultListaPlanesPagoRec resultado = new ResultListaPlanesPagoRec();

		try {
			NodeList elementos = this.doc.getElementsByTagName("plan-pago");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				PlanPago pla = new PlanPago();

				if (getParsedValueFromElement(item, "cod-plan-pago") != null) {
					pla.setCodigo(getParsedValueFromElement(item, "cod-plan-pago"));
				}

				pla.setDescripcion(getParsedValueFromElement(item, "desc-plan-pago"));

				if (getParsedValueFromElement(item, "nu-cuotas") != null) {
					pla.setNumCuotas(Integer.valueOf(getParsedValueFromElement(item, "nu-cuotas")));
				}

				resultado.setUnPlan(pla);
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

	public ResultValidarPolizaRec parsearValidarPolizaRecuotificacion() {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearValidarPolizaRecuotificacion()");

		ResultValidarPolizaRec resultado = new ResultValidarPolizaRec();

		try {

			NodeList elementosDatos = this.doc.getElementsByTagName("recuotificacion");
			Element Item = (Element) elementosDatos.item(0);
			Recuotificacion rec = new Recuotificacion();

			if (getParsedValueFromElement(Item, "mt-facturado") != null) {
				rec.setImporteFacturado(Double.valueOf(getParsedValueFromElement(Item, "mt-facturado")));
			}

			if (getParsedValueFromElement(Item, "dia-vencimiento") != null) {
				rec.setDiaVencimiento(Integer.valueOf(getParsedValueFromElement(Item, "dia-vencimiento")));
			}

			if (getParsedValueFromElement(Item, "mt-a-facturar") != null) {
				rec.setSaldoFacturar(Double.valueOf(getParsedValueFromElement(Item, "mt-a-facturar")));
			}
			rec.setFechaEmision(getParsedValueFromElement(Item, "fe-emision"));

			if (getParsedValueFromElement(Item, "nu-cuotas-facturadas") != null) {
				rec.setNumCuotasFacturadas(Integer.valueOf(getParsedValueFromElement(Item, "nu-cuotas-facturadas")));
			}
			rec.setFechaDesdeVigencia(getParsedValueFromElement(Item, "fe-desde"));
			rec.setFechaHastaVigencia(getParsedValueFromElement(Item, "fe-hasta"));
			rec.setFechaDesdeVigenciaTecnica(getParsedValueFromElement(Item, "fe-desde-tecnica"));
			rec.setFechaHastaVigenciaTecnica(getParsedValueFromElement(Item, "fe-hasta-tecnica"));

			if (getParsedValueFromElement(Item, "nu-endoso") != null) {
				rec.setNumEndoso(Integer.valueOf(getParsedValueFromElement(Item, "nu-endoso")));
			}

			if (getParsedValueFromElement(Item, "nu-cotizacion") != null) {
				rec.setNumCotizacion(Integer.valueOf(getParsedValueFromElement(Item, "nu-cotizacion")));
			}

			if (getParsedValueFromElement(Item, "mt-prima") != null) {
				rec.setMontoPrima(Double.valueOf(getParsedValueFromElement(Item, "mt-prima")));
			}

			if (getParsedValueFromElement(Item, "mt-premio") != null) {
				rec.setMontoPremio(Double.valueOf(getParsedValueFromElement(Item, "mt-premio")));
			}

			rec.setHabilitoDiaVto(getParsedValueFromElement(Item, "habilito-dia-vto"));

			resultado.setDatosRecuotificacion(rec);

			NodeList dConfirmacion = this.doc.getElementsByTagName("confirmacion");
			ValidacionGenerica datosValidacion = new ValidacionGenerica();
			Element conf = (Element) dConfirmacion.item(0);

			if (getParsedValueFromElement(conf, "nu-cotizacion") != null) {
				datosValidacion.setNumCotizacion(Integer.valueOf(getParsedValueFromElement(conf, "nu-cotizacion")));
			}
			datosValidacion.setMensaje(getParsedValueFromElement(conf, "mensaje"));

			int codOperacion = 0;

			if (getParsedValueFromElement(conf, "codigo-operacion") != null) {
				codOperacion = Integer.valueOf(getParsedValueFromElement(conf, "codigo-operacion"));
			}

			if (codOperacion == 1) {
				datosValidacion.setOperacionAInvocar("anularCotizacion");
			}

			resultado.setConfirmacion(datosValidacion);

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;

	}

	public ResultCalcularRec parsearCalcularRecuotificacion() {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearCalcularRecuotificacion()");

		ResultCalcularRec resultado = new ResultCalcularRec();

		try {
			DetalleRecuotificacion detalle = new DetalleRecuotificacion();

			NodeList elementosCuota = this.doc.getElementsByTagName("cuota");
			for (int i = 0; i < elementosCuota.getLength(); i++) {
				Element Item = (Element) elementosCuota.item(i);
				CuotaRecuotificacion cuo = new CuotaRecuotificacion();

				if (getParsedValueFromElement(Item, "mt-recargos") != null) {
					cuo.setMontoRecargo(Double.valueOf(getParsedValueFromElement(Item, "mt-recargos")));
				}

				if (getParsedValueFromElement(Item, "mt-iva") != null) {
					cuo.setMontoIva(Double.valueOf(getParsedValueFromElement(Item, "mt-iva")));
				}

				cuo.setVencimiento(getParsedValueFromElement(Item, "fe-hasta"));

				if (getParsedValueFromElement(Item, "mt-premio-fact") != null) {
					cuo.setTotal(Double.valueOf(getParsedValueFromElement(Item, "mt-premio-fact")));
				}

				if (getParsedValueFromElement(Item, "mt-prima") != null) {
					cuo.setMontoPrima(Double.valueOf(getParsedValueFromElement(Item, "mt-prima")));
				}

				if (getParsedValueFromElement(Item, "mt-premio") != null) {
					cuo.setCuotaPura(Double.valueOf(getParsedValueFromElement(Item, "mt-premio")));
				}

				if (getParsedValueFromElement(Item, "mt-otros") != null) {
					cuo.setMontoOtros(Double.valueOf(getParsedValueFromElement(Item, "mt-otros")));
				}

				detalle.setUnaCuotaRecuotificacion(cuo);
			}

			NodeList elementosDetalle = this.doc.getElementsByTagName("cuotas-list");
			Element item = (Element) elementosDetalle.item(0);

			if (getParsedValueFromElement(item, "mt-total-prima") != null) {
				detalle.setTotalCuotaPura(Double.valueOf(getParsedValueFromElement(item, "mt-total-prima")));
			}

			if (getParsedValueFromElement(item, "mt-total-recargos") != null) {
				detalle.setTotalRecargos(Double.valueOf(getParsedValueFromElement(item, "mt-total-recargos")));
			}

			if (getParsedValueFromElement(item, "mt-total-iva") != null) {
				detalle.setTotalIVA(Double.valueOf(getParsedValueFromElement(item, "mt-total-iva")));
			}

			if (getParsedValueFromElement(item, "mt-total-otros") != null) {
				detalle.setTotalOtros(Double.valueOf(getParsedValueFromElement(item, "mt-total-otros")));
			}

			if (getParsedValueFromElement(item, "mt-total-facturado") != null) {
				detalle.setTotalCuotas(Double.valueOf(getParsedValueFromElement(item, "mt-total-facturado")));
			}

			resultado.setDetalleRecuotificacion(detalle);

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;

	}

	public ResultRecuotificarPoliza parsearRecuotificarPoliza() {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearRecuotificarPoliza()");

		ResultRecuotificarPoliza resultado = new ResultRecuotificarPoliza();

		try {
			NodeList elementosRec = this.doc.getElementsByTagName("recuotificacion");
			Element item = (Element) elementosRec.item(0);

			resultado.setMensaje(getParsedValueFromElement(item, "mensaje"));

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;

	}

	public ResultObtenerConsolidado parsearObtenerConsolidadoPoliza() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerConsolidadoPoliza()");

		ResultObtenerConsolidado resultado = new ResultObtenerConsolidado();

		try {

			Consolidado con = new Consolidado();
			NodeList elementosRec = this.doc.getElementsByTagName("recuotificacion");
			Element Item = (Element) elementosRec.item(0);

			if (getParsedValueFromElement(Item, "cod-ramo") != null) {
				con.setCodRamo(Integer.valueOf(getParsedValueFromElement(Item, "cod-ramo")));
			}

			if (getParsedValueFromElement(Item, "nu-poliza") != null) {
				con.setNumPoliza(Integer.valueOf(getParsedValueFromElement(Item, "nu-poliza")));
			}

			if (getParsedValueFromElement(Item, "nu-certificado") != null) {
				con.setNumCertificado(Integer.valueOf(getParsedValueFromElement(Item, "nu-certificado")));
			}

			if (getParsedValueFromElement(Item, "cod-moneda") != null) {
				con.setCodMoneda(Integer.valueOf(getParsedValueFromElement(Item, "cod-moneda")));
			}

			con.setDescripMoneda(getParsedValueFromElement(Item, "desc-moneda"));

			con.setNombreCliente(getParsedValueFromElement(Item, "desc-cliente"));

			if (getParsedValueFromElement(Item, "mt-total-recibos") != null) {
				con.setMontoTotalRecibos(Double.valueOf(getParsedValueFromElement(Item, "mt-total-recibos")));
			}

			if (getParsedValueFromElement(Item, "mt-total-imputaciones") != null) {
				con.setMontoTotalImputaciones(Double.valueOf(getParsedValueFromElement(Item, "mt-total-imputaciones")));
			}

			if (getParsedValueFromElement(Item, "mt-saldo") != null) {
				con.setMontoSaldo(Double.valueOf(getParsedValueFromElement(Item, "mt-saldo")));
			}

			if (getParsedValueFromElement(Item, "mt-comisiones") != null) {
				con.setMontoComisiones(Double.valueOf(getParsedValueFromElement(Item, "mt-comisiones")));
			}

			NodeList elementosRecibo = Item.getElementsByTagName("recibo");
			for (int i = 0; i < elementosRecibo.getLength(); i++) {
				Element itemRecibo = (Element) elementosRecibo.item(i);
				Recibo rec = new Recibo();

				rec.setFechaHasta(getParsedValueFromElement(itemRecibo, "fe-hasta"));

				if (getParsedValueFromElement(itemRecibo, "cod-estado") != null) {
					rec.setCodEstado(Integer.valueOf(getParsedValueFromElement(itemRecibo, "cod-estado")));
				}

				rec.setDescripEstado(getParsedValueFromElement(itemRecibo, "desc-estado"));

				if (getParsedValueFromElement(itemRecibo, "mt-premio") != null) {
					rec.setMontoPremio(Double.valueOf(getParsedValueFromElement(itemRecibo, "mt-premio")));
				}

				con.setUnRecibo(rec);

			}

			NodeList elementosImput = Item.getElementsByTagName("imputacion");
			for (int i = 0; i < elementosImput.getLength(); i++) {
				Element itemImput = (Element) elementosImput.item(i);
				Imputacion imp = new Imputacion();

				if (getParsedValueFromElement(itemImput, "nu-preliquidacion") != null) {
					imp.setNumPreliquidacion(Integer.valueOf(getParsedValueFromElement(itemImput, "nu-preliquidacion")));
				}

				imp.setFechaPreliquidacion(getParsedValueFromElement(itemImput, "fe-cobro"));

				if (getParsedValueFromElement(itemImput, "mt-pago") != null) {
					imp.setMonto(Double.valueOf(getParsedValueFromElement(itemImput, "mt-pago")));
				}

				con.setUnaImputacion(imp);

			}

			resultado.setDatosConsolidado(con);

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;

	}

	public ResultValidarPoliza parsearValidarPoliza() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearValidarPoliza()");

		ResultValidarPoliza resultado = new ResultValidarPoliza();

		try {

			NodeList elementosDatos = this.doc.getElementsByTagName("validacion-anulacion");
			Element Item = (Element) elementosDatos.item(0);

			if (getParsedValueFromElement(Item, "nu-certificado") != null) {
				resultado.setNumCertificado(Integer.valueOf(getParsedValueFromElement(Item, "nu-certificado")));
			}

			resultado.setDatosRelevantes(getParsedValueFromElement(Item, "datos-relevantes"));

			resultado.setTieneFacultativo(getParsedValueFromElement(Item, "in-tiene-facultativo"));

			NodeList dConfirmacion = this.doc.getElementsByTagName("confirmacion");
			ValidacionGenerica datosValidacion = new ValidacionGenerica();
			Element conf = (Element) dConfirmacion.item(0);

			if (getParsedValueFromElement(conf, "nu-cotizacion") != null) {
				datosValidacion.setNumCotizacion(Integer.valueOf(getParsedValueFromElement(conf, "nu-cotizacion")));
			}
			datosValidacion.setMensaje(getParsedValueFromElement(conf, "mensaje"));

			int codOperacion = 0;

			if (getParsedValueFromElement(conf, "codigo-operacion") != null) {
				codOperacion = Integer.valueOf(getParsedValueFromElement(conf, "codigo-operacion"));
			}

			if (codOperacion == 1) {
				datosValidacion.setOperacionAInvocar("anularCotizacion");
			}

			resultado.setConfirmacion(datosValidacion);

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;

	}

	public ResultNuevaCotizacionEndoso parsearNuevaCotizacionEndoso() {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearNuevaCotizacionEndoso()");

		ResultNuevaCotizacionEndoso resultado = new ResultNuevaCotizacionEndoso();

		try {

			NodeList dConfirmacion = this.doc.getElementsByTagName("confirmacion");
			if (dConfirmacion.getLength() != 0) {
				ValidacionGenerica datosValidacion = new ValidacionGenerica();
				Element conf = (Element) dConfirmacion.item(0);

				if (getParsedValueFromElement(conf, "nu-cotizacion") != null) {
					datosValidacion.setNumCotizacion(Integer.valueOf(getParsedValueFromElement(conf, "nu-cotizacion")));
				}
				datosValidacion.setMensaje(getParsedValueFromElement(conf, "mensaje"));

				int codOperacion = 0;

				if (getParsedValueFromElement(conf, "codigo-operacion") != null) {
					codOperacion = Integer.valueOf(getParsedValueFromElement(conf, "codigo-operacion"));
				}

				if (codOperacion == 1) {
					datosValidacion.setOperacionAInvocar("anularCotizacion");
				}

				resultado.setConfirmacion(datosValidacion);

			}

			NodeList cotizacion = this.doc.getElementsByTagName("cotizacion");
			if (cotizacion.getLength() != 0) {

				NodeList certificados = this.doc.getElementsByTagName("certificado-cot");
				ParseoEntidades parser = new ParseoEntidades();
				for (int i = 0; i < certificados.getLength(); i++) {
					Element itemCert = (Element) certificados.item(i);
					Certificado cert = new Certificado();

					cert.setPermiteAnular(getParsedValueFromElement(itemCert, "permite-anular"));
					cert.setActivo(getParsedValueFromElement(itemCert, "activo"));
					cert.setHabilitoInsertar(getParsedValueFromElement(itemCert, "habilito-insertar"));
					cert.setExisteAsegurado(getParsedValueFromElement(itemCert, "existe-asegurado"));
					cert.setRequiereAsegurado(getParsedValueFromElement(itemCert, "requiere-asegurado"));
					if (getParsedValueFromElement(itemCert, "nu-certificado") != null) {
						cert.setNumCertificado(Integer.valueOf(getParsedValueFromElement(itemCert, "nu-certificado")));
					}
					cert.setCertificadoNuevo(getParsedValueFromElement(itemCert, "es-cert-nuevo"));
					cert.setCodPlanCobertura(getParsedValueFromElement(itemCert, "cod-plan-cob"));
					cert.setDescPlanCobertura(getParsedValueFromElement(itemCert, "desc-plan-cob"));
					cert.setAnulado(getParsedValueFromElement(itemCert, "estado-anulado"));

					NodeList datosAseg = this.doc.getElementsByTagName("datos-basicos-asegurado");
					if (datosAseg.getLength() != 0) {
						Element itemDatosAseg = (Element) datosAseg.item(0);

						Cliente aseg = new Cliente();
						ComunicacionEC celAse = new ComunicacionEC();
						ComunicacionEC telAse = new ComunicacionEC();
						ComunicacionEC mailAse = new ComunicacionEC();

						if (getParsedValueFromElement(itemDatosAseg, "nu-persona") != null) {
							aseg.setNumPersona(getParsedValueFromElement(itemDatosAseg, "nu-persona"));
						}

						if (getParsedValueFromElement(itemDatosAseg, "cod-tp-documento") != null) {
							if (getParsedValueFromElement(itemDatosAseg, "cod-tp-documento").equals("-1")) {
								aseg.setTipoDoc(null);
							} else {
								aseg.setTipoDoc(getParsedValueFromElement(itemDatosAseg, "cod-tp-documento"));
							}
						}
						aseg.setDescripTipoDoc(getParsedValueFromElement(itemDatosAseg, "desc-tp-documento"));
						aseg.setNumDoc(getParsedValueFromElement(itemDatosAseg, "nu-documento"));
						aseg.setRut(getParsedValueFromElement(itemDatosAseg, "rut"));
						aseg.setNombre(getParsedValueFromElement(itemDatosAseg, "nombre-persona"));
						aseg.setApellidoRazon(getParsedValueFromElement(itemDatosAseg, "apellido-persona"));
						if (getParsedValueFromElement(itemDatosAseg, "cod-profesion") != null) {
							aseg.setCodProfesion(Integer.valueOf(getParsedValueFromElement(itemDatosAseg, "cod-profesion")));
						}
						aseg.setProfesion(getParsedValueFromElement(itemDatosAseg, "desc-profesion"));
						aseg.setCodCliente(getParsedValueFromElement(itemDatosAseg, "nu-cedula-rif"));
						if (getParsedValueFromElement(itemDatosAseg, "nu-cons-telefono") != null && !getParsedValueFromElement(itemDatosAseg, "nu-cons-telefono").equals("-1")) {
							telAse.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(itemDatosAseg, "nu-cons-telefono")));
							if (!getParsedValueFromElement(itemDatosAseg, "telefono").equals(" ")) {
								telAse.setValorComunicacion(getParsedValueFromElement(itemDatosAseg, "telefono"));
							}
							aseg.setTel(telAse);
						}
						if (getParsedValueFromElement(itemDatosAseg, "nu-cons-celular") != null && !getParsedValueFromElement(itemDatosAseg, "nu-cons-celular").equals("-1")) {
							celAse.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(itemDatosAseg, "nu-cons-celular")));
							if (!getParsedValueFromElement(itemDatosAseg, "celular").equals(" ")) {
								celAse.setValorComunicacion(getParsedValueFromElement(itemDatosAseg, "celular"));
							}
							aseg.setCel(celAse);
						}
						if (getParsedValueFromElement(itemDatosAseg, "nu-cons-mail") != null && !getParsedValueFromElement(itemDatosAseg, "nu-cons-mail").equals("-1")) {
							mailAse.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(itemDatosAseg, "nu-cons-mail")));
							if (!getParsedValueFromElement(itemDatosAseg, "mail").equals(" ")) {
								mailAse.setValorComunicacion(getParsedValueFromElement(itemDatosAseg, "mail"));
							}
							aseg.setMail(mailAse);
						}
						cert.setAsegurado(aseg);
					}
					NodeList direAseg = this.doc.getElementsByTagName("direccion-asegurado");
					for (int j = 0; j < direAseg.getLength(); j++) {
						Element itemDireAseg = (Element) direAseg.item(j);

						DireccionEC dirAs = new DireccionEC();

						dirAs.setDestinoDireccion(getParsedValueFromElement(itemDireAseg, "destino-direccion"));

						if (getParsedValueFromElement(itemDireAseg, "nu-direccion") != null) {
							dirAs.setCodDireccion(Integer.valueOf(getParsedValueFromElement(itemDireAseg, "nu-direccion")));
						}

						if (getParsedValueFromElement(itemDireAseg, "cod-tp-direccion") != null) {
							dirAs.setTipoDireccion(Integer.valueOf(getParsedValueFromElement(itemDireAseg, "cod-tp-direccion")));
						}
						dirAs.setDescripTipoDirecion(getParsedValueFromElement(itemDireAseg, "desc-tp-direccion"));

						if (getParsedValueFromElement(itemDireAseg, "nu-postal") != null) {
							dirAs.setNumPostal(Integer.valueOf(getParsedValueFromElement(itemDireAseg, "nu-postal")));
						}
						if (getParsedValueFromElement(itemDireAseg, "cod-postal") != null) {
							dirAs.setNumRadio(Integer.valueOf(getParsedValueFromElement(itemDireAseg, "cod-postal")));
						}
						dirAs.setDescripRadio(getParsedValueFromElement(itemDireAseg, "desc-localidad"));
						dirAs.setDireccion(getParsedValueFromElement(itemDireAseg, "desc-calle"));
						dirAs.setNumeroPuerta(getParsedValueFromElement(itemDireAseg, "nu-calle"));
						dirAs.setUnidad(getParsedValueFromElement(itemDireAseg, "nu-unidad"));
						dirAs.setPiso(getParsedValueFromElement(itemDireAseg, "nu-piso"));
						dirAs.setApto(getParsedValueFromElement(itemDireAseg, "nu-departamento"));
						dirAs.setDescripLocalidad(getParsedValueFromElement(itemDireAseg, "desc-municipio"));
						dirAs.setDescripDepto(getParsedValueFromElement(itemDireAseg, "desc-estado"));
						dirAs.setDescripPais(getParsedValueFromElement(itemDireAseg, "desc-pais"));

						cert.setUnaDireccion(dirAs);
					}

					NodeList elementos = itemCert.getElementsByTagName("bienes-list");
					if (elementos.getLength() > 0) {

						Element itemBienesList = (Element) elementos.item(0);
						NodeList elemBien = itemBienesList.getElementsByTagName("bien");

						for (int k = 0; k < elemBien.getLength(); k++) {
							Element itemBien = (Element) elemBien.item(k);
							BienCert obj = new BienCert();

							if (getParsedValueFromElement(itemBien, "certificado") != null) {
								obj.setCertificado(Integer.valueOf(getParsedValueFromElement(itemBien, "certificado")));
							}
							if (getParsedValueFromElement(itemBien, "consec-bien") != null) {
								obj.setConsecutivo(Integer.valueOf(getParsedValueFromElement(itemBien, "consec-bien")));
							}
							if (getParsedValueFromElement(itemBien, "cod-bien") != null) {
								obj.setCodigoBien(Integer.valueOf(getParsedValueFromElement(itemBien, "cod-bien")));
							}
							obj.setDescripcion(getParsedValueFromElement(itemBien, "desc-bien"));
							obj.setFechaBaja(getParsedValueFromElement(itemBien, "fe-baja"));
							if (getParsedValueFromElement(itemBien, "pos-bien") != null) {
								obj.setPosicionBien(Integer.valueOf(getParsedValueFromElement(itemBien, "pos-bien")));
							}
							obj.setEtiquetaCapital(getParsedValueFromElement(itemBien, "etiqueta-capital"));
							obj.setHabilitoQuitarBien(getParsedValueFromElement(itemBien, "habilito-borrar"));
							obj.setHabilitoListaBienes(getParsedValueFromElement(itemBien, "habilito-lista-bienes"));
							obj.setHabilitoUbicacion(getParsedValueFromElement(itemBien, "habilito-ubicacion"));
							obj.setHabilitoBeneficiarios(getParsedValueFromElement(itemBien, "habilito-beneficiario"));
							obj.setHabilitoAcreedores(getParsedValueFromElement(itemBien, "habilito-acreedor"));

							NodeList datos = itemBien.getElementsByTagName("dato");

							if (datos.getLength() > 0) {
								ArrayList<DatosBienCert> listaDatos = new ArrayList<DatosBienCert>();

								for (int l = 0; l < datos.getLength(); l++) {
									Element itemDato = (Element) datos.item(l);
									DatosBienCert objDato = new DatosBienCert();

									if (getParsedValueFromElement(itemDato, "cod-dato") != null) {
										objDato.setDatoCod(Integer.valueOf(getParsedValueFromElement(itemDato, "cod-dato")));
									}

									objDato.setDatoDesc(getParsedValueFromElement(itemDato, "desc-dato"));
									objDato.setValorCod(getParsedValueFromElement(itemDato, "cod-valor"));
									objDato.setValorDesc(getParsedValueFromElement(itemDato, "desc-valor"));
									objDato.setDatoTipo(getParsedValueFromElement(itemDato, "tipo-dato"));
									objDato.setVisibilidad(getParsedValueFromElement(itemDato, "visible"));
									objDato.setRequerido(getParsedValueFromElement(itemDato, "requerido"));
									objDato.setTieneReglas(getParsedValueFromElement(itemDato, "tiene-reglas"));
									if (getParsedValueFromElement(itemDato, "cant-decimales") != null) {
										objDato.setDecimales(Integer.valueOf(getParsedValueFromElement(itemDato, "cant-decimales")));
									}

									if (getParsedValueFromElement(itemDato, "cod-tabla") != null) {
										objDato.setTabla(Integer.valueOf(getParsedValueFromElement(itemDato, "cod-tabla")));
									}
									if (getParsedValueFromElement(itemDato, "longitud-dato") != null) {
										objDato.setLongitud(Integer.valueOf(getParsedValueFromElement(itemDato, "longitud-dato")));
									}

									objDato.setObligatorio(getParsedValueFromElement(itemDato, "obligatorio-en"));

									if (getParsedValueFromElement(itemDato, "posicion") != null) {
										objDato.setDatoPosicion(Integer.valueOf(getParsedValueFromElement(itemDato, "posicion")));
									}

									if (getParsedValueFromElement(itemDato, "cant-filtros-tabla") != null) {
										objDato.setCantidadFiltros(Integer.valueOf(getParsedValueFromElement(itemDato, "cant-filtros-tabla")));
									}
									listaDatos.add(objDato);
								}

								obj.setDatos(listaDatos);
								obj.setCoberturas(parser.parsearCoberturas(itemBien));

								NodeList ubicaciones = itemBien.getElementsByTagName("ubicacion");
								if (ubicaciones.getLength() > 0) {
									obj.setUbicacionBien(parser.parsearUbicacionBien((Element) ubicaciones.item(0)));
								}

								obj.setAcreedoresBien(parser.parsearAcreedores(itemBien));
								obj.setObjetosBien(parser.parsearObjetosBien(itemBien));
								obj.setBeneficiariosBien(parser.parsearBeneficiarios(itemBien));

							}
							cert.setUnBienCert(obj);
						}
					}
					resultado.setUnCertificado(cert);

					final NodeList datosBasicos = this.doc.getElementsByTagName("datos-basicos");
					if (datosBasicos.getLength() == 0) {
						LOG.info("No hay datos básicos para esta cotizacion");
					} else {
						final Element item = (Element) datosBasicos.item(0);
						resultado.setDatosCotizacion(parser.parsearDatosBasicos(item));
					}

					final NodeList contratante = this.doc.getElementsByTagName("contratante");
					if (contratante.getLength() == 0) {
						LOG.info("No hay contratantes para esta cotizacion");
					} else {
						final Element unContratante = (Element) contratante.item(0);
						resultado.setDatosContratante(parser.parsearDatosContratante(unContratante));
					}
				}
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

	public ResultObtenerValidarImpresionPoliza parsearObtenerValidarImpresionPoliza() {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerValidarImpresionPoliza()");
		ResultObtenerValidarImpresionPoliza resultado = new ResultObtenerValidarImpresionPoliza();
		try {
			ImpresionPoliza imPoliza = new ImpresionPoliza();
			NodeList elementosDatos = this.doc.getElementsByTagName("poliza");
			Element Item = (Element) elementosDatos.item(0);

			imPoliza.setCodSucursal(getParsedValueFromElement(Item, "cod-sucursal"));
			if (getParsedValueFromElement(Item, "cod-ramo") != null) {
				imPoliza.setCodRamo(Integer.valueOf(getParsedValueFromElement(Item, "cod-ramo")));
			}

			if (getParsedValueFromElement(Item, "nu-poliza") != null) {
				imPoliza.setNumPoliza(Integer.valueOf(getParsedValueFromElement(Item, "nu-poliza")));
			}

			if (getParsedValueFromElement(Item, "nu-certificado") != null) {
				imPoliza.setNumCertificado(Integer.valueOf(getParsedValueFromElement(Item, "nu-certificado")));
			}

			if (getParsedValueFromElement(Item, "nu-endoso") != null) {
				imPoliza.setNumEndoso(Integer.valueOf(getParsedValueFromElement(Item, "nu-endoso")));
			}

			imPoliza.setUrlDorso(getParsedValueFromElement(Item, "url-dorso"));
			imPoliza.setReportePremio(getParsedValueFromElement(Item, "nom-reporte-con-premio"));
			imPoliza.setReporteSinPremio(getParsedValueFromElement(Item, "nom-reporte-sin-premio"));
			imPoliza.setImpresionPoliza(getParsedValueFromElement(Item, "impresion-poliza"));
			imPoliza.setImpresionResumen(getParsedValueFromElement(Item, "impresion-resumen"));
			imPoliza.setImpresionCartas(getParsedValueFromElement(Item, "imprime-cartas"));
			imPoliza.setImpresionCertificado(getParsedValueFromElement(Item, "impresion-certificado"));
			imPoliza.setImpresionAccidentesTrabajo(getParsedValueFromElement(Item, "impresion-accidentes-trabajo"));
			imPoliza.setImpresionSoa(getParsedValueFromElement(Item, "impresion-soa"));
			imPoliza.setImpresionDorso(getParsedValueFromElement(Item, "impresion-dorso"));
			imPoliza.setImpresionMercosur(getParsedValueFromElement(Item, "impresion-mercosur"));
			NodeList elementosMercosur = Item.getElementsByTagName("impresion-mercosur-form");
			for (int i = 0; i < elementosMercosur.getLength(); i++) {
				Element merco = (Element) elementosMercosur.item(i);
				ImpresionMercosur merc = new ImpresionMercosur();
				if (getParsedValueFromElement(merco, "cod-impresion-mercosur") != null) {
					merc.setCodigoImpresionMercosur(Integer.valueOf(getParsedValueFromElement(merco, "cod-impresion-mercosur")));
				}
				if (getParsedValueFromElement(merco, "nu-consecutivo-bien-mercosur") != null) {
					merc.setNumConsecutivoBien(Integer.valueOf(getParsedValueFromElement(merco, "nu-consecutivo-bien-mercosur")));
				}
				merc.setDescripcionFormulario(getParsedValueFromElement(merco, "desc-formulario"));
				merc.setReporte(getParsedValueFromElement(merco, "cod-reporte-mercosur"));
				imPoliza.setUno(merc);
			}

			NodeList elementosSOA = Item.getElementsByTagName("impresion-soa-form");
			for (int i = 0; i < elementosSOA.getLength(); i++) {
				Element soa = (Element) elementosSOA.item(i);
				ImpresionSOA isoa = new ImpresionSOA();
				if (getParsedValueFromElement(soa, "cod-impresion-soa") != null) {
					isoa.setCodigoImpresion(Integer.valueOf(getParsedValueFromElement(soa, "cod-impresion-soa")));
				}
				if (getParsedValueFromElement(soa, "nu-consecutivo-bien-soa") != null) {
					isoa.setNumConsecutivoBien(Integer.valueOf(getParsedValueFromElement(soa, "nu-consecutivo-bien-soa")));
				}
				isoa.setDescripcionFormulario(getParsedValueFromElement(soa, "desc-formulario-soa"));
				isoa.setReporte(getParsedValueFromElement(soa, "cod-reporte-soa"));
				imPoliza.setUno(isoa);
			}

			NodeList partes = Item.getElementsByTagName("item-frente");
			for (int i = 0; i < partes.getLength(); i++) {
				Element el = (Element) partes.item(i);
				Opcion parte = new Opcion();
				parte.setCodigo(getParsedValueFromElement(el, "nu-pagina"));
				parte.setDescripcion(getParsedValueFromElement(el, "desc-pagina"));
				parte.setSeleccionada(getParsedValueFromElement(el, "seleccionado"));
				imPoliza.setUnoParte(parte);
			}

			NodeList cartas = Item.getElementsByTagName("carta");
			for (int i = 0; i < cartas.getLength(); i++) {
				Element el = (Element) cartas.item(i);
				Opcion carta = new Opcion();
				carta.setCodigo(getParsedValueFromElement(el, "cod-carta"));
				carta.setDescripcion(getParsedValueFromElement(el, "no-reporte"));
				carta.setSeleccionada(getParsedValueFromElement(el, "cod-reporte"));
				imPoliza.setUnoCarta(carta);
			}
			resultado.setImpresionPoliza(imPoliza);

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;
	}

	public ResultGuardarImprimirOrdenCarta parsearGuardarImprimirOrdenCarta() {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearGuardarImprimirOrdenCarta()");
		ResultGuardarImprimirOrdenCarta resultado = new ResultGuardarImprimirOrdenCarta();
		try {

			NodeList elementosDatos = this.doc.getElementsByTagName("resultado");
			Element Item = (Element) elementosDatos.item(0);
			resultado.setNumSecuencia(getParsedValueFromElement(Item, "secuencia"));

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;
	}

	public ResultGuardarImprimirReporte parsearGuardarImprimirReporte() {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearGuardarImprimirReporte()");
		ResultGuardarImprimirReporte resultado = new ResultGuardarImprimirReporte();
		try {

			NodeList elementosDatos = this.doc.getElementsByTagName("resultado");
			Element Item = (Element) elementosDatos.item(0);
			resultado.setNumSecuencia(getParsedValueFromElement(Item, "secuencia"));

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;
	}

	public ResultAgregarCertificadoEndoso parsearAgregarCertificadoEndoso() {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearAgregarCertificadoEndoso()");

		ResultAgregarCertificadoEndoso resultado = new ResultAgregarCertificadoEndoso();

		try {
			NodeList cotizacion = this.doc.getElementsByTagName("cotizacion");
			Element itemCoti = (Element) cotizacion.item(0);

			if (getParsedValueFromElement(itemCoti, "nu-cotizacion") != null) {
				resultado.setNumCotizacion(Integer.valueOf(getParsedValueFromElement(itemCoti, "nu-cotizacion")));
			}
			resultado.setPermiteAnular(getParsedValueFromElement(itemCoti, "permite-anular"));
			resultado.setActivo(getParsedValueFromElement(itemCoti, "activo"));
			resultado.setCodPlanCobertura(getParsedValueFromElement(itemCoti, "cod-plan-cob"));
			resultado.setDescPlanCobertura(getParsedValueFromElement(itemCoti, "desc-plan-cob"));
			resultado.setHabilitoInsertar(getParsedValueFromElement(itemCoti, "habilito-insertar"));

			NodeList bienes = this.doc.getElementsByTagName("bien");
			ParseoEntidades parser = new ParseoEntidades();
			for (int i = 0; i < bienes.getLength(); i++) {
				Element bienCert = (Element) bienes.item(i);
				resultado.setUnBienCert(parser.parsearBienCert(bienCert));
			}

			NodeList certificadosList = this.doc.getElementsByTagName("certificado-list");
			if (certificadosList.getLength() != 0) {
				Element certList = (Element) certificadosList.item(0);
				NodeList certificados = certList.getElementsByTagName("certificado");
				if (certificados.getLength() != 0) {
					for (int i = 0; i < certificados.getLength(); i++) {
						Element cert = (Element) certificados.item(i);
						Certificado certificado = new Certificado();
						if (getParsedValueFromElement(cert, "nu-certificado") != null) {
							certificado.setNumCertificado(Integer.valueOf(getParsedValueFromElement(cert, "nu-certificado")));
						}
						certificado.setPermiteAnular(getParsedValueFromElement(cert, "permite-anular"));
						certificado.setCertificadoNuevo(getParsedValueFromElement(cert, "es-cert-nuevo"));
						certificado.setAnulado(getParsedValueFromElement(cert, "estado-anulado"));
						resultado.setUnCertificado(certificado);
					}
				}
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

	public ResultObtenerDatosBasicosEndoso parsearObtenerDatosBasicosEndoso() {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerDatosBasicosEndoso()");

		ResultObtenerDatosBasicosEndoso resultado = new ResultObtenerDatosBasicosEndoso();

		try {

			NodeList elementos = this.doc.getElementsByTagName("datos-basicos");

			Element item = (Element) elementos.item(0);
			DatosCotizacion datos = new DatosCotizacion();

			final String cotizacion = getParsedValueFromElement(item, "nu-cotizacion");

			if (cotizacion != null) {
				datos.setNumCotizacion(Integer.valueOf(cotizacion));
			}

			final String poliza = getParsedValueFromElement(item, "nu-poliza");

			if (poliza != null) {
				datos.setNumPoliza(Integer.valueOf(poliza));
			}

			final String certificado = getParsedValueFromElement(item, "nu-certificado");

			if (certificado != null) {
				datos.setNumCertificado(Integer.valueOf(certificado));
			}

			final String motivo = getParsedValueFromElement(item, "cod-motivo");

			if (motivo != null) {
				datos.setCodMotivo(Integer.valueOf(motivo));
			}

			if (getParsedValueFromElement(item, "nu-sucursal") != null) {
				datos.setSucursal(Integer.valueOf(getParsedValueFromElement(item, "nu-sucursal")));
			}

			datos.setEstado(getParsedValueFromElement(item, "estado"));

			final String codNivel = getParsedValueFromElement(item, "cod-nivel");

			if (codNivel != null) {
				datos.setNivelCod(Integer.valueOf(codNivel));
			}

			datos.setFechaEmision(getParsedValueFromElement(item, "fe-cotizacion"));
			datos.setNivelDesc(getParsedValueFromElement(item, "desc-nivel"));

			final String codPlanPago = getParsedValueFromElement(item, "cod-plan-pago");

			if (codPlanPago != null) {
				datos.setPlanPagoCod(codPlanPago);
			}

			datos.setPlanPagoDesc(getParsedValueFromElement(item, "desc-plan-pago"));

			final String codMedioPago = getParsedValueFromElement(item, "cod-medio-pago");

			if (codMedioPago != null) {
				datos.setMedioPagoCod(Integer.valueOf(codMedioPago));
			}

			datos.setMedioPagoDesc(getParsedValueFromElement(item, "desc-medio-pago"));
			datos.setOrigenEndoso(getParsedValueFromElement(item, "cod-origen-endoso"));
			datos.setOrigenEndosoDesc(getParsedValueFromElement(item, "desc-origen-endoso"));
			datos.setFacturacionCod(getParsedValueFromElement(item, "cod-tp-facturacion"));
			datos.setFacturacionDesc(getParsedValueFromElement(item, "desc-tp-facturacion"));

			if (getParsedValueFromElement(item, "cod-banco") != null) {
				datos.setCodBanco(Integer.valueOf(getParsedValueFromElement(item, "cod-banco")));
			}

			if (getParsedValueFromElement(item, "nu-dom-bancario") != null) {
				datos.setNuDomBancario(Integer.valueOf(getParsedValueFromElement(item, "nu-dom-bancario")));
			}

			datos.setDescBanco(getParsedValueFromElement(item, "desc-banco"));

			datos.setNumTarjeta(getSeudoTarjeta(getParsedValueFromElement(item, "nu-tarjeta")));

			final String codProductor = getParsedValueFromElement(item, "cod-productor");

			if (codProductor != null) {
				datos.setProductorCod(Integer.valueOf(codProductor));
			}

			datos.setProductorDesc(getParsedValueFromElement(item, "desc-productor"));
			
			if (getParsedValueFromElement(item, "cod-broker") != null) {
				datos.setCodBroker(Integer.valueOf(getParsedValueFromElement(item, "cod-broker")));
			}

			datos.setDescBroker(getParsedValueFromElement(item, "desc-broker"));

			final String codMoneda = getParsedValueFromElement(item, "cod-moneda");

			if (codMoneda != null) {
				datos.setMonedaCod(Integer.valueOf(codMoneda));
			}

			datos.setMonedaDesc(getParsedValueFromElement(item, "desc-moneda"));
			datos.setOrigenCod(getParsedValueFromElement(item, "cod-origen"));
			datos.setOrigenDesc(getParsedValueFromElement(item, "desc-origen"));
			datos.setPromocionCod(getParsedValueFromElement(item, "cod-promocion"));
			datos.setPromocionDesc(getParsedValueFromElement(item, "desc-promocion"));
			datos.setModoCalculoCod(getParsedValueFromElement(item, "cod-tp-calculo"));
			datos.setModoCalculoDesc(getParsedValueFromElement(item, "desc-tp-calculo"));
			datos.setVigenciaCod(getParsedValueFromElement(item, "cod-fr-pago"));
			datos.setVigenciaDesc(getParsedValueFromElement(item, "desc-fr-pago"));
			datos.setFrecTecnicaCod(getParsedValueFromElement(item, "cod-vig-tecnica"));
			datos.setFrecTecnicaDesc(getParsedValueFromElement(item, "desc-vig-tecnica"));
			datos.setUsuarioCod(getParsedValueFromElement(item, "cod-usuario"));
			datos.setUsuarioDesc(getParsedValueFromElement(item, "desc-usuario"));
			datos.setVigenciaDesde(getParsedValueFromElement(item, "fe-desde"));
			datos.setVigenciaHasta(getParsedValueFromElement(item, "fe-hasta"));
			datos.setVigenciaTecnicaDesde(getParsedValueFromElement(item, "fe-desde-tecnica"));
			datos.setVigenciaTecnicaHasta(getParsedValueFromElement(item, "fe-hasta-tecnica"));

			final String premioInformado = getParsedValueFromElement(item, "premio-informado");

			if (premioInformado != null) {
				datos.setPremioInformado(Double.valueOf(premioInformado));
			}
			datos.setRenovacionCod(getParsedValueFromElement(item, "cod-renovacion"));
			datos.setRenovacionDesc(getParsedValueFromElement(item, "desc-renovacion"));
			datos.setCodProducto(getParsedValueFromElement(item, "cod-producto"));
			datos.setDescProducto(getParsedValueFromElement(item, "desc-producto"));

			if (getParsedValueFromElement(item, "cod-ramo") != null) {
				datos.setCodRamo(Integer.valueOf(getParsedValueFromElement(item, "cod-ramo")));
			}
			datos.setDescRamo(getParsedValueFromElement(item, "desc-ramo"));
			
			if(getParsedValueFromElement(item, "nu-dire-envio")!=null){
				datos.setNumDireEnvio(Integer.valueOf(getParsedValueFromElement(item, "nu-dire-envio")));
			}
			if(getParsedValueFromElement(item, "nu-dire-cobro")!=null){
				datos.setNumDireCobro(Integer.valueOf(getParsedValueFromElement(item, "nu-dire-cobro"))); 
			}

			resultado.setDatos(datos);

			NodeList clieDatos = this.doc.getElementsByTagName("datos-basicos-cliente");

			Element itemCli = (Element) clieDatos.item(0);
			Cliente cli = new Cliente();
			ComunicacionEC cel = new ComunicacionEC();
			ComunicacionEC tel = new ComunicacionEC();
			ComunicacionEC mail = new ComunicacionEC();

			if (getParsedValueFromElement(itemCli, "nu-persona") != null) {
				cli.setNumPersona(getParsedValueFromElement(itemCli, "nu-persona"));
			}

			cli.setTipoDoc(getParsedValueFromElement(itemCli, "cod-tp-documento"));
			cli.setDescripTipoDoc(getParsedValueFromElement(itemCli, "desc-tp-documento"));
			cli.setNumDoc(getParsedValueFromElement(itemCli, "nu-documento"));
			cli.setRut(getParsedValueFromElement(itemCli, "rut"));
			cli.setNombre(getParsedValueFromElement(itemCli, "nombre-persona"));
			cli.setApellidoRazon(getParsedValueFromElement(itemCli, "apellido-persona"));
			if (getParsedValueFromElement(itemCli, "cod-profesion") != null) {
				cli.setCodProfesion(Integer.valueOf(getParsedValueFromElement(itemCli, "cod-profesion")));
			}
			cli.setProfesion(getParsedValueFromElement(itemCli, "desc-profesion"));
			cli.setCodCliente(getParsedValueFromElement(itemCli, "nu-cedula-rif"));
			if (getParsedValueFromElement(itemCli, "nu-cons-telefono") != null && !getParsedValueFromElement(itemCli, "nu-cons-telefono").equals("-1")) {
				tel.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(itemCli, "nu-cons-telefono")));
				if (!" ".equals(getParsedValueFromElement(itemCli, "telefono"))) {
					tel.setValorComunicacion(getParsedValueFromElement(itemCli, "telefono"));
				}
				cli.setTel(tel);
			}
			if (getParsedValueFromElement(itemCli, "nu-cons-celular") != null && !getParsedValueFromElement(itemCli, "nu-cons-celular").equals("-1")) {
				cel.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(itemCli, "nu-cons-celular")));
				if (!" ".equals(getParsedValueFromElement(itemCli, "celular"))) {
					cel.setValorComunicacion(getParsedValueFromElement(itemCli, "celular"));
				}
				cli.setCel(cel);
			}
			if (getParsedValueFromElement(itemCli, "nu-cons-mail") != null && !getParsedValueFromElement(itemCli, "nu-cons-mail").equals("-1")) {
				mail.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(itemCli, "nu-cons-mail")));
				if (!" ".equals(getParsedValueFromElement(itemCli, "mail"))) {
					mail.setValorComunicacion(getParsedValueFromElement(itemCli, "mail"));
				}

				cli.setMail(mail);
			}

			resultado.setCliente(cli);

			NodeList elemDir = this.doc.getElementsByTagName("direccion-cliente");
			for (int i = 0; i < elemDir.getLength(); i++) {
				Element itemDir = (Element) elemDir.item(i);
				DireccionEC dato = new DireccionEC();
				dato.setDestinoDireccion(getParsedValueFromElement(itemDir, "destino-direccion"));

				if (getParsedValueFromElement(itemDir, "nu-direccion") != null) {
					dato.setCodDireccion(Integer.valueOf(getParsedValueFromElement(itemDir, "nu-direccion")));
				}

				if (getParsedValueFromElement(itemDir, "cod-tp-direccion") != null) {
					dato.setTipoDireccion(Integer.valueOf(getParsedValueFromElement(itemDir, "cod-tp-direccion")));
				}
				dato.setDescripTipoDirecion(getParsedValueFromElement(itemDir, "desc-tp-direccion"));

				if (getParsedValueFromElement(itemDir, "nu-postal") != null) {
					dato.setNumPostal(Integer.valueOf(getParsedValueFromElement(itemDir, "nu-postal")));
				}
				if (getParsedValueFromElement(itemDir, "cod-postal") != null) {
					dato.setNumRadio(Integer.valueOf(getParsedValueFromElement(itemDir, "cod-postal")));
				}
				dato.setDescripRadio(getParsedValueFromElement(itemDir, "desc-localidad"));
				dato.setDireccion(getParsedValueFromElement(itemDir, "desc-calle"));
				dato.setNumeroPuerta(getParsedValueFromElement(itemDir, "nu-calle"));
				dato.setUnidad(getParsedValueFromElement(itemDir, "nu-unidad"));
				dato.setPiso(getParsedValueFromElement(itemDir, "nu-piso"));
				dato.setApto(getParsedValueFromElement(itemDir, "nu-departamento"));
				dato.setDescripLocalidad(getParsedValueFromElement(itemDir, "desc-municipio"));
				dato.setDescripDepto(getParsedValueFromElement(itemDir, "desc-estado"));
				dato.setDescripPais(getParsedValueFromElement(itemDir, "desc-pais"));

				resultado.setUnaDireccion(dato);
			}

			NodeList certificadoList = this.doc.getElementsByTagName("certificado-list");
			if (certificadoList.getLength() != 0) {
				Element itemList = (Element) certificadoList.item(0);
				NodeList certificados = itemList.getElementsByTagName("certificado");
				for (int i = 0; i < certificados.getLength(); i++) {
					Element itemCert = (Element) certificados.item(i);
					Certificado cert = new Certificado();

					if (getParsedValueFromElement(itemCert, "nu-certificado") != null) {
						cert.setNumCertificado(Integer.valueOf(getParsedValueFromElement(itemCert, "nu-certificado")));
					}
					//CERTIFICADO ENDOSO
					cert.setCertificadoNuevo(getParsedValueFromElement(itemCert, "es-cert-nuevo"));
					cert.setAnulado(getParsedValueFromElement(itemCert, "estado-anulado"));
					resultado.setUnCertificado(cert);
				}
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

	public ResultAnularCertificadoEndoso parsearAnularCertificadoEndoso() {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearAnularCertificadoEndoso()");

		ResultAnularCertificadoEndoso resultado = new ResultAnularCertificadoEndoso();
		ArrayList<Bien> bienes = new ArrayList<Bien>();
		ArrayList<Certificado> certificados = new ArrayList<Certificado>();
		ParseoCotizaciones parseoCot = new ParseoCotizaciones(null);

		try {
			NodeList cotizacion = this.doc.getElementsByTagName("cotizacion");
			Element itemCoti = (Element) cotizacion.item(0);

			resultado.setPermiteAnular(getParsedValueFromElement(itemCoti, "permite-anular"));
			resultado.setActivo(getParsedValueFromElement(itemCoti, "activo"));
			resultado.setCodPlanCobertura(getParsedValueFromElement(itemCoti, "cod-plan-cob"));
			resultado.setDescPlanCobertura(getParsedValueFromElement(itemCoti, "desc-plan-cob"));
			resultado.setHabilitoInsertar(getParsedValueFromElement(itemCoti, "habilito-insertar"));

			if (getParsedValueFromElement(itemCoti, "mt-cuota-cert") != null) {
				resultado.setMontoCuotaCert(Double.valueOf(getParsedValueFromElement(itemCoti, "mt-cuota-cert")));
			}

			if (getParsedValueFromElement(itemCoti, "mt-total-cert") != null) {
				resultado.setMontoTotalCert(Double.valueOf(getParsedValueFromElement(itemCoti, "mt-total-cert")));
			}

			NodeList bienNodo = this.doc.getElementsByTagName("bien");
			if (bienNodo.getLength() != 0) {
				for (int i = 0; i < bienNodo.getLength(); i++) {
					Element itemBien = (Element) bienNodo.item(i);
					Bien obj = new Bien();

					if (getParsedValueFromElement(itemBien, "certificado") != null) {
						obj.setCertificado(Integer.valueOf(getParsedValueFromElement(itemBien, "certificado")));
					}
					if (getParsedValueFromElement(itemBien, "consec-bien") != null) {
						obj.setConsecutivo(Integer.valueOf(getParsedValueFromElement(itemBien, "consec-bien")));
					}
					if (getParsedValueFromElement(itemBien, "cod-bien") != null) {
						obj.setCodBien(Integer.valueOf(getParsedValueFromElement(itemBien, "cod-bien")));
					}
					obj.setBienDescripcion(getParsedValueFromElement(itemBien, "desc-bien"));
					obj.setFechaBaja(getParsedValueFromElement(itemBien, "fe-baja"));
					if (getParsedValueFromElement(itemBien, "pos-bien") != null) {
						obj.setPosBien(Integer.valueOf(getParsedValueFromElement(itemBien, "pos-bien")));
					}
					obj.setEtiquetaCapital(getParsedValueFromElement(itemBien, "etiqueta-capital"));
					obj.setHabilitoQuitar(getParsedValueFromElement(itemBien, "habilito-borrar"));
					obj.setHabilitoListaBienes(getParsedValueFromElement(itemBien, "habilito-lista-bienes"));
					obj.setHabilitoUbicacion(getParsedValueFromElement(itemBien, "habilito-ubicacion"));
					obj.setHabilitoBeneficiarios(getParsedValueFromElement(itemBien, "habilito-beneficiario"));
					obj.setHabilitoAcreedores(getParsedValueFromElement(itemBien, "habilito-acreedor"));

					NodeList elementos = this.doc.getElementsByTagName("ubicacion");
					if (elementos.getLength() != 0) {
						Element itemUbi = (Element) elementos.item(0);
						Ubicacion ubi = new Ubicacion();

						if (getParsedValueFromElement(itemUbi, "cod-provincia") != null) {
							ubi.setCodDepartamento(Integer.valueOf(getParsedValueFromElement(itemUbi, "cod-provincia")));
						}
						if (getParsedValueFromElement(itemUbi, "cod-provincia") != null) {
							ubi.setCodDepartamento(Integer.valueOf(getParsedValueFromElement(itemUbi, "cod-provincia")));
						}
						ubi.setDepartamento(getParsedValueFromElement(itemUbi, "desc-estado"));
						ubi.setLocalidad(getParsedValueFromElement(itemUbi, "desc-municipio"));
						if (getParsedValueFromElement(itemUbi, "cod-municipio") != null) {
							ubi.setCodLocalidad(Integer.valueOf(getParsedValueFromElement(itemUbi, "cod-municipio")));
						}

						if (getParsedValueFromElement(itemUbi, "st-capital") != null) {
							ubi.setStCapital(Integer.valueOf(getParsedValueFromElement(itemUbi, "st-capital")));
						}

						if (getParsedValueFromElement(itemUbi, "cod-pais") != null) {
							ubi.setCodPais(Integer.valueOf(getParsedValueFromElement(itemUbi, "cod-pais")));
						}

						ubi.setPais(getParsedValueFromElement(itemUbi, "desc-pais"));

						if (getParsedValueFromElement(itemUbi, "cod-postal") != null) {
							ubi.setRadio(Integer.valueOf(getParsedValueFromElement(itemUbi, "cod-postal")));
						}
						ubi.setTelefono(getParsedValueFromElement(itemUbi, "telefono"));
						ubi.setDescripRadio(getParsedValueFromElement(itemUbi, "desc-localidad"));
						ubi.setCalle(getParsedValueFromElement(itemUbi, "desc-calle"));
						ubi.setAclaracion(getParsedValueFromElement(itemUbi, "aclaracion-dir"));

						if (getParsedValueFromElement(itemUbi, "nu-postal") != null) {
							ubi.setNumPostal(Integer.valueOf(getParsedValueFromElement(itemUbi, "nu-postal")));
						}

						if (getParsedValueFromElement(itemUbi, "cod-calle") != null) {
							ubi.setCodCalle(Integer.valueOf(getParsedValueFromElement(itemUbi, "cod-calle")));
						}
						ubi.setNumero(getParsedValueFromElement(itemUbi, "nu-calle"));
						ubi.setPiso(getParsedValueFromElement(itemUbi, "nu-piso"));
						if (getParsedValueFromElement(itemUbi, "nu-direccion") != null) {
							ubi.setNumDireccion(Integer.valueOf(getParsedValueFromElement(itemUbi, "nu-direccion")));
						}
						obj.setUbicacionBien(ubi);
					}

					obj.setFranquicias(parseoCot.parsearFranquicia(itemBien));

					NodeList elemCob = this.doc.getElementsByTagName("cobertura");

					for (int h = 0; h < elemCob.getLength(); h++) {

						Element item = (Element) elemCob.item(h);
						Cobertura dato = new Cobertura();

						if (getParsedValueFromElement(item, "cod-ramo-cob") != null) {
							dato.setRamoCod(Integer.valueOf(getParsedValueFromElement(item, "cod-ramo-cob")));
						}

						dato.setCoberturaCod(getParsedValueFromElement(item, "cod-cobertura"));
						dato.setCoberturaDesc(getParsedValueFromElement(item, "desc-cobertura"));

						if (getParsedValueFromElement(item, "nu-elemento") != null) {
							dato.setElementosNum(Integer.valueOf(getParsedValueFromElement(item, "nu-elemento")));
						}

						String capital = getParsedValueFromElement(item, "capital");
						if (capital != null) {
							dato.setCapital(Double.valueOf(capital));
						}

						dato.setTasaPrima(getParsedValueFromElement(item, "tasa-prima"));

						if (getParsedValueFromElement(item, "monto-prima") != null) {
							dato.setPrima(Double.valueOf(getParsedValueFromElement(item, "monto-prima")));
						}

						dato.setRequerido(getParsedValueFromElement(item, "requerido"));

						dato.setEditable(getParsedValueFromElement(item, "editable"));

						obj.setUnaCobertura(dato);
					}

					bienes.add(obj);
				}
				resultado.setBienes(bienes);
			}

			NodeList certNodo = this.doc.getElementsByTagName("certificado-endoso");
			if (certNodo.getLength() != 0) {
				for (int i = 0; i < certNodo.getLength(); i++) {
					Element itemCert = (Element) certNodo.item(i);
					Certificado cert = new Certificado();

					if (getParsedValueFromElement(itemCert, "nu-certificado") != null) {
						cert.setNumCertificado(Integer.valueOf(getParsedValueFromElement(itemCert, "nu-certificado")));
					}
					cert.setCertificadoNuevo(getParsedValueFromElement(itemCert, "es-cert-nuevo"));
					cert.setAnulado(getParsedValueFromElement(itemCert, "estado-anulado"));
					NodeList coberturasCert = itemCert.getElementsByTagName("plan-cobertura-cert");

					for (int j = 0; j < coberturasCert.getLength(); j++) {
						Element itemCob = (Element) coberturasCert.item(j);
						PlanCobertura planCob = new PlanCobertura();

						planCob.setCodigo(getParsedValueFromElement(itemCob, "cod-plan-cobertura-cert"));

						if (getParsedValueFromElement(itemCob, "mt-deducible") != null) {
							planCob.setMontoDeducible(Double.valueOf(getParsedValueFromElement(itemCob, "mt-deducible")));
						}

						if (getParsedValueFromElement(itemCob, "mt-prima") != null) {
							planCob.setMontoPrima(Double.valueOf(getParsedValueFromElement(itemCob, "mt-prima")));
						}

						if (getParsedValueFromElement(itemCob, "mt-premio") != null) {
							planCob.setMontoPremio(Double.valueOf(getParsedValueFromElement(itemCob, "mt-premio")));
						}

						NodeList planPagoCert = itemCob.getElementsByTagName("plan-pago");

						for (int k = 0; k < planPagoCert.getLength(); k++) {
							PlanPago planP = new PlanPago();
							Element planItem = (Element) planPagoCert.item(k);

							planP.setCodigo(getParsedValueFromElement(planItem, "cod-plan-pago"));

							planP.setDescripcion(getParsedValueFromElement(planItem, "desc-plan-pago"));

							if (getParsedValueFromElement(planItem, "mt-cuota") != null) {
								planP.setMontoCuota(Double.valueOf(getParsedValueFromElement(planItem, "mt-cuota")));
							}
							if (getParsedValueFromElement(planItem, "mt-total") != null) {
								planP.setMontoTotal(Double.valueOf(getParsedValueFromElement(planItem, "mt-total")));
							}

							planCob.setUnPlanPago(planP);
						}
						cert.setUnaCobertura(planCob);

					}
					certificados.add(cert);
				}
				resultado.setCertificados(certificados);
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

	public ResultGenerarMarcaNoRenovar parsearGenerarMarcaNoRenovar() {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearGenerarMarcaNoRenovar()");
		ResultGenerarMarcaNoRenovar resultado = new ResultGenerarMarcaNoRenovar();
		try {

			NodeList elementosDatos = this.doc.getElementsByTagName("poliza");
			Element Item = (Element) elementosDatos.item(0);
			resultado.setMsgAviso(getParsedValueFromElement(Item, "mensaje-aviso"));
			if (resultado.getMsgAviso()==null&& getParsedValueFromElement(Item, "mensaje-confirmacion")!=null) {
					resultado.setMsgAviso(getParsedValueFromElement(Item, "mensaje-confirmacion"));
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

	public ResultGenerico parsearObtenerDatosEndosoSinPremio() {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerDatosEndosoSinPremio()");
		ResultObtenerDatosEndosoSinPremio resultado = new ResultObtenerDatosEndosoSinPremio();
		try {

			NodeList elementosDatos = this.doc.getElementsByTagName("poliza");
			Element Item = (Element) elementosDatos.item(0);
			DatosParticularesEndoso datosPartEndoso = new DatosParticularesEndoso();
			datosPartEndoso.setCodRamo(Integer.valueOf(getParsedValueFromElement(Item, "cod-ramo")));
			datosPartEndoso.setDescRamo(getParsedValueFromElement(Item, "desc-ramo"));
			datosPartEndoso.setNumPoliza(Integer.valueOf(getParsedValueFromElement(Item, "nu-poliza")));
			datosPartEndoso.setNumEndoso(Integer.valueOf(getParsedValueFromElement(Item, "nu-endoso")));
			datosPartEndoso.setCodCliente(Integer.valueOf(getParsedValueFromElement(Item, "cod-cliente")));
			datosPartEndoso.setDescCliente(getParsedValueFromElement(Item, "desc-cliente"));
			datosPartEndoso.setFechaDesde(getParsedValueFromElement(Item, "fe-desde"));
			datosPartEndoso.setFechaHasta(getParsedValueFromElement(Item, "fe-hasta"));

			datosPartEndoso.setCodMedioPago(Integer.valueOf(getParsedValueFromElement(Item, "cod-medio-pago")));
			datosPartEndoso.setDescMedioPago(getParsedValueFromElement(Item, "desc-medio-pago"));
			datosPartEndoso.setCodOrigen(getParsedValueFromElement(Item, "cod-origen-pago"));
			datosPartEndoso.setDescOrigen(getParsedValueFromElement(Item, "desc-origen-pago"));
			datosPartEndoso.setCodOrigenEndoso(Integer.valueOf(getParsedValueFromElement(Item, "cod-origen-endoso")));
			datosPartEndoso.setDescOrigenEndoso(getParsedValueFromElement(Item, "desc-origen-endoso"));

			NodeList elementosCertificados = this.doc.getElementsByTagName("certificado");
			for (int i = 0; i < elementosCertificados.getLength(); i++) {
				Element it = (Element) elementosCertificados.item(i);
				DatosCertificado datos = new DatosCertificado();
				datos.setFechaEndoso(getParsedValueFromElement(it, "fe-endoso"));
				datos.setNumCentificado(Integer.valueOf(getParsedValueFromElement(it, "nu-certificado")));
				datosPartEndoso.setUnDatoCertificado(datos);
			}

			resultado.setDatosParticulares(datosPartEndoso);

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;
	}

	public ResultValidarDetectarEndoso parsearValidarDetectarEndoso() {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearValidarDetectarEndoso");

		ResultValidarDetectarEndoso resultado = new ResultValidarDetectarEndoso();

		try {
			NodeList tree = this.doc.getElementsByTagName("poliza");
			resultado.setFechaEmision(getParsedValueFromElement((Element) tree.item(0), "fecha-emision"));
			NodeList elementos = this.doc.getElementsByTagName("bienes-list");
			if (elementos.getLength() > 0) {
				Element itemBienesList = (Element) elementos.item(0);
				NodeList elemBien = itemBienesList.getElementsByTagName("bien");

				for (int k = 0; k < elemBien.getLength(); k++) {
					Element itemBien = (Element) elemBien.item(k);
					BienCert obj = new BienCert();

					if (getParsedValueFromElement(itemBien, "certificado") != null) {
						obj.setCertificado(Integer.valueOf(getParsedValueFromElement(itemBien, "certificado")));
					}
					if (getParsedValueFromElement(itemBien, "consec-bien") != null) {
						obj.setConsecutivo(Integer.valueOf(getParsedValueFromElement(itemBien, "consec-bien")));
					}
					if (getParsedValueFromElement(itemBien, "cod-bien") != null) {
						obj.setCodigoBien(Integer.valueOf(getParsedValueFromElement(itemBien, "cod-bien")));
					}
					obj.setDescripcion(getParsedValueFromElement(itemBien, "desc-bien"));
					obj.setFechaBaja(getParsedValueFromElement(itemBien, "fe-baja"));
					if (getParsedValueFromElement(itemBien, "pos-bien") != null) {
						obj.setPosicionBien(Integer.valueOf(getParsedValueFromElement(itemBien, "pos-bien")));
					}
					obj.setEtiquetaCapital(getParsedValueFromElement(itemBien, "etiqueta-capital"));
					obj.setHabilitoQuitarBien(getParsedValueFromElement(itemBien, "habilito-borrar"));
					obj.setHabilitoListaBienes(getParsedValueFromElement(itemBien, "habilito-lista-bienes"));
					obj.setHabilitoUbicacion(getParsedValueFromElement(itemBien, "habilito-ubicacion"));
					obj.setHabilitoBeneficiarios(getParsedValueFromElement(itemBien, "habilito-beneficiario"));
					obj.setHabilitoAcreedores(getParsedValueFromElement(itemBien, "habilito-acreedor"));

					NodeList datos = itemBien.getElementsByTagName("dato");

					if (datos.getLength() > 0) {
						ArrayList<DatosBienCert> listaDatos = new ArrayList<DatosBienCert>();

						for (int l = 0; l < datos.getLength(); l++) {
							Element itemDato = (Element) datos.item(l);
							DatosBienCert objDato = new DatosBienCert();

							if (getParsedValueFromElement(itemDato, "cod-dato") != null) {
								objDato.setDatoCod(Integer.valueOf(getParsedValueFromElement(itemDato, "cod-dato")));
							}

							objDato.setDatoDesc(getParsedValueFromElement(itemDato, "desc-dato"));
							objDato.setValorCod(getParsedValueFromElement(itemDato, "cod-valor"));
							objDato.setValorDesc(getParsedValueFromElement(itemDato, "desc-valor"));
							objDato.setDatoTipo(getParsedValueFromElement(itemDato, "tipo-dato"));
							objDato.setVisibilidad(getParsedValueFromElement(itemDato, "visible"));
							objDato.setRequerido(getParsedValueFromElement(itemDato, "requerido"));
							if (getParsedValueFromElement(itemDato, "cant-decimales") != null) {
								objDato.setDecimales(Integer.valueOf(getParsedValueFromElement(itemDato, "cant-decimales")));
							}

							if (getParsedValueFromElement(itemDato, "cod-tabla") != null) {
								objDato.setTabla(Integer.valueOf(getParsedValueFromElement(itemDato, "cod-tabla")));
							}
							if (getParsedValueFromElement(itemDato, "longitud-dato") != null) {
								objDato.setLongitud(Integer.valueOf(getParsedValueFromElement(itemDato, "longitud-dato")));
							}

							objDato.setObligatorio(getParsedValueFromElement(itemDato, "obligatorio-en"));

							if (getParsedValueFromElement(itemDato, "posicion") != null) {
								objDato.setDatoPosicion(Integer.valueOf(getParsedValueFromElement(itemDato, "posicion")));
							}
							listaDatos.add(objDato);
						}

						obj.setDatos(listaDatos);
					}

					NodeList acList = itemBien.getElementsByTagName("acreedores-list");
					if (acList.getLength() > 0) {
						Element itemACList = (Element) acList.item(0);
						NodeList listaAc = itemACList.getElementsByTagName("acreedor");
						ArrayList<AcreedorBien> listadoAcreedores = new ArrayList<AcreedorBien>(listaAc.getLength());
						for (int R = 0; R < listaAc.getLength(); R++) {
							Element itemA = (Element) listaAc.item(R);
							AcreedorBien acr = new AcreedorBien();

							if (getParsedValueFromElement(itemA, "cod-acreedor") != null) {
								acr.setCodAcreedor(Integer.valueOf(getParsedValueFromElement(itemA, "cod-acreedor")));
							}
							acr.setDescripAcreedor(getParsedValueFromElement(itemA, "desc-acreedor"));

							if (getParsedValueFromElement(itemA, "cod-objeto") != null) {
								acr.setCodObjeto(Integer.valueOf(getParsedValueFromElement(itemA, "cod-objeto")));
							}
							acr.setDescripObjeto(getParsedValueFromElement(itemA, "desc-objeto"));
							acr.setCodTipoAcreedor(getParsedValueFromElement(itemA, "cod-tp-acreedor"));
							acr.setDescripTipoAcreedor(getParsedValueFromElement(itemA, "desc-tp-acreedor"));

							if (getParsedValueFromElement(itemA, "po-participacion") != null) {
								acr.setPorcentajeParticipacion(Double.valueOf(getParsedValueFromElement(itemA, "po-participacion")));
							}
							acr.setIdentificador(getParsedValueFromElement(itemA, "identificador"));
							acr.setFechaExclusion(getParsedValueFromElement(itemA, "fe-exclusion"));
							acr.setFechaInclusion(getParsedValueFromElement(itemA, "fe-inclusion"));

							listadoAcreedores.add(acr);

						}
						obj.setAcreedoresBien(listadoAcreedores);
					}
					resultado.setUnBien(obj);
				}
			}
			NodeList listMedios = this.doc.getElementsByTagName("medio-pago-list");
			Element it = (Element) tree.item(0);
			DatosBanco dbanco = new DatosBanco();
			if (getParsedValueFromElement(it, "cod-banco-cert") != null) {
				dbanco.setCodBanco(Integer.valueOf(getParsedValueFromElement(it, "cod-banco-cert")));
			}
			dbanco.setDescripcionBanco(getParsedValueFromElement(it, "desc-banco-cert"));
			dbanco.setNumCuenta(getSeudoTarjeta(getParsedValueFromElement(it, "nu-cuenta-cert")));

			MedioPago mediop = new MedioPago();
			if (getParsedValueFromElement(it, "cod-medio-pago-cert") != null) {
				mediop.setCodigo(Integer.valueOf(getParsedValueFromElement(it, "cod-medio-pago-cert")));
			}
			mediop.setDescripcion(getParsedValueFromElement(it, "desc-medio-pago-cert"));
			OrigenPagoEndoso origen = new OrigenPagoEndoso();
			origen.setCodigo(getParsedValueFromElement(it, "cod-origen-pago-cert"));
			origen.setDescripcion(getParsedValueFromElement(it, "desc-origen-pago-cert"));
			resultado.setDatosBancarios(dbanco);
			resultado.setMedioPago(mediop);
			resultado.setOrigenPago(origen);
			if (listMedios.getLength() > 0) {
				Element itemList = (Element) listMedios.item(0);
				NodeList medios = itemList.getElementsByTagName("medio-pago");

				for (int i = 0; i < medios.getLength(); i++) {
					Element item = (Element) medios.item(i);
					ArrayList<OrigenPagoEndoso> origenes = new ArrayList<OrigenPagoEndoso>();
					MedioPago medio = new MedioPago();
					if (getParsedValueFromElement(item, "cod-medio-pago") != null) {
						medio.setCodigo(Integer.valueOf(getParsedValueFromElement(item, "cod-medio-pago")));
					}
					medio.setDescripcion(getParsedValueFromElement(item, "desc-medio-pago"));

					NodeList origlist = item.getElementsByTagName("origen-pago-list");
					if (origlist.getLength() > 0) {
						Element eOrigen = (Element) origlist.item(0);
						NodeList lista = eOrigen.getElementsByTagName("origen-pago");

						for (int j = 0; j < lista.getLength(); j++) {
							Element itemp = (Element) lista.item(j);
							OrigenPagoEndoso opago = new OrigenPagoEndoso();
							opago.setCodigo(getParsedValueFromElement(itemp, "cod-origen-pago"));
							opago.setDescripcion(getParsedValueFromElement(itemp, "desc-origen-pago"));
							NodeList bancolist = itemp.getElementsByTagName("dato-bancario-list");
							if (bancolist.getLength() > 0) {
								Element eBanco = (Element) bancolist.item(0);
								NodeList listaBank = eBanco.getElementsByTagName("dato-bancario");
								for (int k = 0; k < listaBank.getLength(); k++) {
									Element itempb = (Element) listaBank.item(k);
									DatosBanco db = new DatosBanco();
									if (getParsedValueFromElement(itempb, "cod-banco") != null)
										db.setCodBanco(Integer.valueOf(getParsedValueFromElement(itempb, "cod-banco")));
									db.setDescripcionBanco(getParsedValueFromElement(itempb, "desc-banco"));
									db.setNumCuenta(getSeudoTarjeta(getParsedValueFromElement(itempb, "nu-cuenta")));
									if (getParsedValueFromElement(itempb, "nu-domicilio") != null)
										db.setNumDomicilio(Integer.valueOf(getParsedValueFromElement(itempb, "nu-domicilio")));
									opago.setUno(db);
								}
							}
							origenes.add(opago);
						}
					}
					resultado.setUnMedioPago(medio, origenes);
				}
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

	public ResultGenerico parsearCotizacionPendientePoliza() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearCotizacionPendientePoliza");

		ResultCotizacionPendiente resultado = new ResultCotizacionPendiente();

		try {
			if (this.doc.hasChildNodes()) {
				NodeList dConfirmacion = this.doc.getElementsByTagName("confirmacion");
				if (dConfirmacion.getLength() > 0) {

					ValidacionGenerica datosValidacion = new ValidacionGenerica();
					Element conf = (Element) dConfirmacion.item(0);

					if (getParsedValueFromElement(conf, "nu-cotizacion") != null) {
						datosValidacion.setNumCotizacion(Integer.valueOf(getParsedValueFromElement(conf, "nu-cotizacion")));
					}
					datosValidacion.setMensaje(getParsedValueFromElement(conf, "mensaje"));

					int codOperacion = 0;

					if (getParsedValueFromElement(conf, "codigo-operacion") != null) {
						codOperacion = Integer.valueOf(getParsedValueFromElement(conf, "codigo-operacion"));
					}

					if (codOperacion == 1) {
						datosValidacion.setOperacionAInvocar("anularCotizacion");
					}
					resultado.setConfirmacion(datosValidacion);
				}
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

	private String getSeudoTarjeta(String tarjeta) {
		String resultado = null;
		if (tarjeta != null && !tarjeta.isEmpty()) {

			StringBuffer sb = new StringBuffer(tarjeta.length());
			char[] array = tarjeta.toCharArray();
			for (int i = 0; i < array.length; i++) {
				if (i > array.length - 5) {
					sb.append(array[i]);
				} else {
					sb.append('x');
				}
			}
			resultado = sb.toString();
		}

		return resultado;
	}

	public ResultGenerico parsearListaCertificadosEndosar() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearListaCertificadosEndosar");

		ResultCodiguera resultado = new ResultCodiguera();

		try {
			if (this.doc.hasChildNodes()) {

				NodeList listado = this.doc.getElementsByTagName("certificado-endoso-list");

				if (listado.getLength() > 0) {
					Element eOrigen = (Element) listado.item(0);
					NodeList lista = eOrigen.getElementsByTagName("certificado-endoso");

					for (int j = 0; j < lista.getLength(); j++) {
						Element itemp = (Element) lista.item(j);
						Codiguera codiguera = new Codiguera();
						codiguera.setCodigo(getParsedValueFromElement(itemp, "nu-certificado-endoso"));
						if ("0".equals(codiguera.getCodigo())) {
							codiguera.setDescripcion("Nuevo Certificado");
						} else {
							codiguera.setDescripcion("Certificado " + codiguera.getCodigo());
						}
						resultado.setUno(codiguera);
					}
				}
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

	public ResultObtenerDatosFacturaDigital parsearObtenerDatosFacturaDigital() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerDatosFacturaDigital()");
		ResultObtenerDatosFacturaDigital resultado = new ResultObtenerDatosFacturaDigital();
		try {
			NodeList elements = this.doc.getElementsByTagName("factura-digital");
			Element item = (Element) elements.item(0);
			resultado.setConsecutivo(Integer.parseInt(getParsedValueFromElement(item, "nu-consecutivo")));
			resultado.setCodComunicacion(Integer.parseInt(getParsedValueFromElement(item, "cod-tp-comunicacion")));
			resultado.setComunicacion(getParsedValueFromElement(item, "desc-consecutivo"));
			resultado.setComValidada(getParsedValueFromElement(item, "com-validada"));
			resultado.setEnvioFacturaEMail(getParsedValue(this.doc, "envio-fact-mail"));
		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;
	}

	public ResultObtenerPolizasCabezal parsearObtenerPolizasCabezal() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerPolizasCabezal()");

		ResultObtenerPolizasCabezal resultado = new ResultObtenerPolizasCabezal();

		try {
			NodeList elementos = this.doc.getElementsByTagName("poliza");
			for (int i = 0; i < elementos.getLength(); i++) {

				Element item = (Element) elementos.item(i);
				DatosPoliza dat = new DatosPoliza();

				dat.setCodSucursal(evaluarInt(item, "cd-sucursal"));
				dat.setNumPoliza(evaluarInt(item, "nu-poliza"));
				dat.setFechaEmision(getParsedValueFromElement(item, "fe-suscripcion"));
				dat.setFechaDesde(getParsedValueFromElement(item, "fe-desde"));
				dat.setFechaHasta(getParsedValueFromElement(item, "fe-hasta"));
				dat.setCliente(getParsedValueFromElement(item, "desc-contratante"));
				dat.setDescripEstado(getParsedValueFromElement(item, "desc-estado"));
				dat.setAsegurado(getParsedValueFromElement(item, "desc-asegurado"));
				dat.setCodMoneda(getParsedValueFromElement(item, "cd-moneda"));
				dat.setCodCliente(getParsedValueFromElement(item, "nu-contratante"));
				dat.setCodRamo(evaluarInt(item, "cod-ramo"));
				dat.setDescripRamo(getParsedValueFromElement(item, "desc-ramo"));
				dat.setCodProducto(getParsedValueFromElement(item, "cod-producto"));
				dat.setDescripProducto(getParsedValueFromElement(item, "desc-producto"));
				dat.setCodBroker(evaluarInt(item, "cod-broker"));
				dat.setDescBroker(getParsedValueFromElement(item, "desc-broker"));
				dat.setCodProductor(evaluarInt(item, "cod-productor"));
				dat.setDescProductor(getParsedValueFromElement(item, "desc-productor"));
				dat.setNumPersona(getParsedValueFromElement(item, "nu-persona"));
				resultado.setUnaPoliza(dat);
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

	public ResultObtenerPolizaCabezalDetalle parsearObtenerPolizaCabezalDetalle() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerPolizaCabezalDetalle()");

		ResultObtenerPolizaCabezalDetalle resultado = new ResultObtenerPolizaCabezalDetalle();

		try {
			NodeList elementos = this.doc.getElementsByTagName("poliza");
			if (elementos.getLength() > 0) {

				Element item = (Element) elementos.item(0);
				DatosPoliza dat = new DatosPoliza();

				dat.setCodSucursal(evaluarInt(item, "nu-sucursal"));
				dat.setNumPoliza(evaluarInt(item, "nu-poliza"));
				dat.setFechaEmision(getParsedValueFromElement(item, "fe-suscripcion"));
				dat.setFechaDesde(getParsedValueFromElement(item, "fe-desde"));
				dat.setFechaHasta(getParsedValueFromElement(item, "fe-hasta"));
				dat.setCliente(getParsedValueFromElement(item, "desc-contratante"));
				dat.setDescripEstado(getParsedValueFromElement(item, "desc-estado"));
				dat.setAsegurado(getParsedValueFromElement(item, "desc-asegurado"));
				dat.setCodMoneda(getParsedValueFromElement(item, "cod-moneda"));
				dat.setDescripMoneda(getParsedValueFromElement(item, "desc-moneda"));
				dat.setCodCliente(getParsedValueFromElement(item, "nu-contratante"));
				dat.setCodTipoFacturacion(getParsedValueFromElement(item, "tp-facturacion"));
				dat.setDescripTipoFacturacion(getParsedValueFromElement(item, "desc-facturacion"));
				dat.setDescripPromocion(getParsedValueFromElement(item, "desc-promocion"));
				dat.setCodPromocion(getParsedValueFromElement(item, "cd-promocion"));
				dat.setCodRenovacion(getParsedValueFromElement(item, "in-renovacion"));
				dat.setDescripRenovacion(getParsedValueFromElement(item, "desc-renovacion"));
				dat.setCodCompromiso(getParsedValueFromElement(item, "cant-compromiso"));
				dat.setNumRefactEfectiva(evaluarInt(item, "nu-refacturacion"));
				dat.setNumPolizaAnterior(evaluarInt(item, "nu-poliza-ant"));
				dat.setNumPolizaSiguiente(evaluarInt(item, "nu-poliza-sig"));

				dat.setCodVigencia(getParsedValueFromElement(item, "tp-vigencia"));
				dat.setDescripVigencia(getParsedValueFromElement(item, "desc-vigencia"));
				dat.setAnulacionCorrida(getParsedValueFromElement(item, "in-anulacion"));
				dat.setCodRamo(evaluarInt(item, "cod-ramo"));
				dat.setDescripRamo(getParsedValueFromElement(item, "desc-ramo"));
				dat.setCodProducto(getParsedValueFromElement(item, "cod-producto"));
				dat.setDescripProducto(getParsedValueFromElement(item, "desc-producto"));
				dat.setHabilitoRecuotificar(getParsedValueFromElement(item, "habilito-recuotificar"));
				dat.setHabilitoModificarSinPremio(getParsedValueFromElement(item, "habilito-modif-sin-premio"));
				dat.setHabilitoNoRenovar(getParsedValueFromElement(item, "habilito-no-renovar"));
				dat.setHabilitoEndosar(getParsedValueFromElement(item, "habilito-endosar"));
				dat.setHabilitoClonar(getParsedValueFromElement(item, "habilito-clonar"));
				dat.setHabilitoAnular(getParsedValueFromElement(item, "habilito-anular"));
				dat.setEnviarFacturaEMail(getParsedValueFromElement(item, "envio-fact-mail"));
				dat.setCodBroker(evaluarInt(item, "cod-broker"));
				dat.setDescBroker(getParsedValueFromElement(item, "desc-broker"));
				dat.setCodProductor(evaluarInt(item, "cod-productor"));
				dat.setDescProductor(getParsedValueFromElement(item, "desc-productor"));
				dat.setNumPersona(getParsedValueFromElement(item, "nu-persona"));
				dat.setCodAdhesion(getParsedValueFromElement(item, "cod-adhesion"));
				dat.setCodPlanCobertura(getParsedValueFromElement(item,"cod-plan-cob"));
			    dat.setDescPlanCobertura(getParsedValueFromElement(item,"desc-plan-cob"));

				if(getParsedValueFromElement(item,"mt-deducible")!=null)
				dat.setDeducible(Double.valueOf(getParsedValueFromElement(item,"mt-deducible")));
				
				
				NodeList elemDato = item.getElementsByTagName("dato");
				for (int j = 0; j < elemDato.getLength(); j++) {
					Element Item = (Element) elemDato.item(j);
					DatosBien datos = new DatosBien();

					datos.setCodDato(getParsedValueFromElement(Item, "cod-dato"));
					datos.setDatoDesc(getParsedValueFromElement(Item, "desc-dato"));
					datos.setValorCod(getParsedValueFromElement(Item, "cod-valor"));
					datos.setValorDesc(getParsedValueFromElement(Item, "desc-valor"));
					dat.setUnDato(datos);
					

				}
				
				
				resultado.setPoliza(dat);
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

	public ResultNuevaFacturacionInteractiva parsearNuevaFacturacionInteractiva() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearNuevaFacturacionInteractiva");

		ResultNuevaFacturacionInteractiva resultado = new ResultNuevaFacturacionInteractiva();
		try {

			NodeList elementos = doc.getElementsByTagName("factura");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				String factura = getParsedValueFromElement(item, "nro-factura");
				resultado.setUnNumeroFactura(factura);
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

	public ResultAnularPoliza parsearAnularPoliza() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearAnularPoliza");

		ResultAnularPoliza resultado = new ResultAnularPoliza();
		try {

			NodeList elementosAnulacion = this.doc.getElementsByTagName("anulacion");
			if (elementosAnulacion.getLength() > 0) {
				Element item = (Element) elementosAnulacion.item(0);
				resultado.setNumEndoso(evaluarInt(item, "nro-endoso"));
			}

			NodeList elementos = doc.getElementsByTagName("factura");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				String factura = getParsedValueFromElement(item, "nro-factura");
				resultado.setUnNumeroFactura(factura);
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

	public ResultDatosXDatosParametricosEndoso parsearObtenerDatosXDatoParametricoEndoso() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearObtenerDatosXDatoParametricoEndoso");

		ResultDatosXDatosParametricosEndoso resultado = new ResultDatosXDatosParametricosEndoso();
		try {

			NodeList elementos = this.doc.getElementsByTagName("resultado");
			ArrayList<DatosXDatoParametrico> datos = new ArrayList<>(elementos.getLength());
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				DatosXDatoParametrico dato = new DatosXDatoParametrico();
				dato.setNumCertificado(evaluarInt(item, "nu-certificado"));
				dato.setNumEndoso(evaluarInt(item, "nu-endoso"));
				dato.setCliente(getParsedValueFromElement(item, "asegurado"));
				dato.setCodBien(evaluarInt(item, "cod-bien"));
				dato.setDescBien(getParsedValueFromElement(item, "desc-bien"));
				datos.add(dato);
			}
         resultado.setDatos(datos);
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
