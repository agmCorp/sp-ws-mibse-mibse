package uy.bse.rector.servicios.parseo;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import uy.com.bse.cotizaciones.consultas.AcreedorBien;
import uy.com.bse.cotizaciones.consultas.Bien;
import uy.com.bse.cotizaciones.consultas.BienCert;
import uy.com.bse.cotizaciones.consultas.BonificacionCotizacion;
import uy.com.bse.cotizaciones.consultas.Cliente;
import uy.com.bse.cotizaciones.consultas.Cobertura;
import uy.com.bse.cotizaciones.consultas.Cotizacion;
import uy.com.bse.cotizaciones.consultas.DatoParametrico;
import uy.com.bse.cotizaciones.consultas.DatosBien;
import uy.com.bse.cotizaciones.consultas.DatosBienCert;
import uy.com.bse.cotizaciones.consultas.DatosContratante;
import uy.com.bse.cotizaciones.consultas.DatosUbicacionBien;
import uy.com.bse.cotizaciones.consultas.EncabezadoCotizacion;
import uy.com.bse.cotizaciones.consultas.Franquicia;
import uy.com.bse.cotizaciones.consultas.FueraPauta;
import uy.com.bse.cotizaciones.consultas.InfoBienFranquicia;
import uy.com.bse.cotizaciones.consultas.Plan;
import uy.com.bse.cotizaciones.consultas.ResultBonificacionNS;
import uy.com.bse.cotizaciones.consultas.ResultCertificados;
import uy.com.bse.cotizaciones.consultas.ResultCotizaciones;
import uy.com.bse.cotizaciones.consultas.ResultDatoParametrico;
import uy.com.bse.cotizaciones.consultas.ResultDatosCertificados;
import uy.com.bse.cotizaciones.consultas.ResultDatosCotizacion;
import uy.com.bse.cotizaciones.consultas.ResultDatosModificacion;
import uy.com.bse.cotizaciones.consultas.ResultEncabezadoCotizacion;
import uy.com.bse.cotizaciones.consultas.ResultListaPlanesPagoRen;
import uy.com.bse.cotizaciones.consultas.ResultObtenerAcreedoresXBien;
import uy.com.bse.cotizaciones.consultas.ResultObtenerAnexosCotizacion;
import uy.com.bse.cotizaciones.consultas.ResultObtenerBeneficiarios;
import uy.com.bse.cotizaciones.consultas.ResultObtenerCabezalCotizaciones;
import uy.com.bse.cotizaciones.consultas.ResultObtenerCoberturasXBien;
import uy.com.bse.cotizaciones.consultas.ResultObtenerCoberturasXCertificado;
import uy.com.bse.cotizaciones.consultas.ResultObtenerComponentes;
import uy.com.bse.cotizaciones.consultas.ResultObtenerContratanteAsegurado;
import uy.com.bse.cotizaciones.consultas.ResultObtenerDatosBancarios;
import uy.com.bse.cotizaciones.consultas.ResultObtenerDatosBasicosCotiza;
import uy.com.bse.cotizaciones.consultas.ResultObtenerDatosPreviosEndoso;
import uy.com.bse.cotizaciones.consultas.ResultObtenerDatosRenovacion;
import uy.com.bse.cotizaciones.consultas.ResultObtenerDetalleBusquedaCotizacion;
import uy.com.bse.cotizaciones.consultas.ResultObtenerDetalleCuota;
import uy.com.bse.cotizaciones.consultas.ResultObtenerDomiciliosCliente;
import uy.com.bse.cotizaciones.consultas.ResultObtenerFranquicias;
import uy.com.bse.cotizaciones.consultas.ResultObtenerListaBienes;
import uy.com.bse.cotizaciones.consultas.ResultObtenerNotasPoliza;
import uy.com.bse.cotizaciones.consultas.ResultObtenerPlanesCobertura;
import uy.com.bse.cotizaciones.consultas.ResultObtenerPlanesPago;
import uy.com.bse.cotizaciones.consultas.ResultObtenerResumenPlanesCobertura;
import uy.com.bse.cotizaciones.consultas.ResultObtenerUbicacionBien;
import uy.com.bse.cotizaciones.consultas.ResultPersonaSeleccionada;
import uy.com.bse.cotizaciones.consultas.ResultTextosCotizacion;
import uy.com.bse.cotizaciones.consultas.Ubicacion;
import uy.com.bse.cotizaciones.entidades.Ahorro;
import uy.com.bse.cotizaciones.entidades.Anexo;
import uy.com.bse.cotizaciones.entidades.Beneficiario;
import uy.com.bse.cotizaciones.entidades.Clausula;
import uy.com.bse.cotizaciones.entidades.Componente;
import uy.com.bse.cotizaciones.entidades.Cumulo;
import uy.com.bse.cotizaciones.entidades.DatosBanco;
import uy.com.bse.cotizaciones.entidades.DatosBasicosCliente;
import uy.com.bse.cotizaciones.entidades.DatosBasicosCotizacion;
import uy.com.bse.cotizaciones.entidades.DatosCotizacion;
import uy.com.bse.cotizaciones.entidades.Nota;
import uy.com.bse.cotizaciones.entidades.ObjetoBien;
import uy.com.bse.cotizaciones.lovs.Moneda;
import uy.com.bse.cotizaciones.lovs.ResultTiposNotaGenerica;
import uy.com.bse.cotizaciones.lovs.ResultValoresTipoNota;
import uy.com.bse.cotizaciones.lovs.TiposNotaGenerica;
import uy.com.bse.cotizaciones.operaciones.Certificado;
import uy.com.bse.cotizaciones.operaciones.PlanCobertura;
import uy.com.bse.cotizaciones.operaciones.PlanPago;
import uy.com.bse.cotizaciones.operaciones.PlanPagoCotizacion;
import uy.com.bse.cotizaciones.operaciones.ResultActualizarCoberturaBien;
import uy.com.bse.cotizaciones.operaciones.ResultConfigurarFechasPromocion;
import uy.com.bse.cotizaciones.operaciones.Riesgo;
import uy.com.bse.maestro.personas.comunicaciones.ComunicacionEC;
import uy.com.bse.maestro.personas.domicilio.DireccionEC;
import uy.com.bse.polizas.consultas.BonificacionPoliza;
import uy.com.bse.polizas.consultas.InfoPolizaSiniestro;
import uy.com.bse.polizas.entidades.DatosBasicosSiniestro;
import uy.com.bse.polizas.entidades.DetalleCuota;
import uy.com.bse.polizas.entidades.EncabezadoPoliza;
import uy.com.bse.polizas.entidades.ParametroNotaPoliza;
import uy.com.bse.polizas.entidades.Texto;
import uy.com.bse.polizas.operaciones.ValidacionGenerica;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.parseo.ParseoXmlGenerico;

public class ParseoCotizaciones extends ParseoXmlGenerico {

	private static final Logger LOG = LogManager.getLogger(ParseoCotizaciones.class);

	public ParseoCotizaciones(final String textoParsear) {
		super(textoParsear);
	}

	public ResultObtenerCabezalCotizaciones parsearObtenerCabezalCotizaciones() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerCabezalCotizaciones");

		ResultObtenerCabezalCotizaciones resultado = new ResultObtenerCabezalCotizaciones();
		
		try {
			NodeList elemCoti = this.doc.getElementsByTagName("cotizacion");
			for (int i = 0; i < elemCoti.getLength(); i++) {
				Element item = (Element) elemCoti.item(i);
				Cotizacion cot = new Cotizacion();

				if (getParsedValueFromElement(item, "nombre-cliente") != null) {
					cot.setNombreCliente(getParsedValueFromElement(item, "nombre-cliente"));
				}

				if (getParsedValueFromElement(item, "apellido-cliente") != null) {
					cot.setApellidoRazonSocialCliente(getParsedValueFromElement(item, "apellido-cliente"));
				}

				if (getParsedValueFromElement(item, "nu-cotizacion") != null) {
					cot.setNumCotizacion(Integer.valueOf(getParsedValueFromElement(item, "nu-cotizacion")));
				}

				if (getParsedValueFromElement(item, "nu-poliza") != null) {
					cot.setNumPoliza(Integer.valueOf(getParsedValueFromElement(item, "nu-poliza")));
				}
				cot.setSucursal(getParsedValueFromElement(item, "nu-sucursal"));
				cot.setFechaDesde(getParsedValueFromElement(item, "fe-desde"));
				cot.setFechaHasta(getParsedValueFromElement(item, "fe-hasta"));
				cot.setCodMoneda(getParsedValueFromElement(item, "cod-moneda"));
				cot.setMoneda(getParsedValueFromElement(item, "desc-moneda"));
				cot.setSimboloMoneda(getParsedValueFromElement(item, "simbolo-moneda"));

				if (getParsedValueFromElement(item, "cod-plan-pago") != null) {
					cot.setPlanPago(Integer.valueOf(getParsedValueFromElement(item, "cod-plan-pago")));
				}
				cot.setDescripPlanPago(getParsedValueFromElement(item, "desc-plan-pago"));

				if (getParsedValueFromElement(item, "mt-premio-cot") != null) {
					cot.setPremioCotizacion(Double.valueOf(getParsedValueFromElement(item, "mt-premio-cot")));
				}

				if (getParsedValueFromElement(item, "mt-premio-pol") != null) {
					cot.setPremioPoliza(Double.valueOf(getParsedValueFromElement(item, "mt-premio-pol")));
				}

				cot.setEstado(getParsedValueFromElement(item, "estado"));

				if (getParsedValueFromElement(item, "cod-ramo") != null) {
					cot.setCodRamo(Integer.valueOf(getParsedValueFromElement(item, "cod-ramo")));
				}

				cot.setDescripRamo(getParsedValueFromElement(item, "desc-ramo"));
				cot.setCodProducto(getParsedValueFromElement(item, "cod-producto"));
				cot.setDescripProducto(getParsedValueFromElement(item, "desc-producto"));
				cot.setModificada(getParsedValueFromElement(item, "modificada"));
				cot.setCodTipo(getParsedValueFromElement(item, "cod-tp-transac"));
				cot.setDescripTipo(getParsedValueFromElement(item, "desc-tp-transac"));
				cot.setEnPauta(getParsedValueFromElement(item, "cod-en-pauta"));
				cot.setHabilitoVer(getParsedValueFromElement(item, "habilito-ver"));
				cot.setHabilitoFueraPauta(getParsedValueFromElement(item, "habilito-fuera-pauta"));

				if (getParsedValueFromElement(item, "cod-productor") != null) {
					cot.setCodProductor(Integer.valueOf(getParsedValueFromElement(item, "cod-productor")));
				}

				cot.setDescripProductor(getParsedValueFromElement(item, "desc-productor"));
				
				if (getParsedValueFromElement(item, "cod-broker") != null) {
					cot.setCodBroker(Integer.valueOf(getParsedValueFromElement(item, "cod-broker")));
				}

				cot.setDescBroker(getParsedValueFromElement(item, "desc-broker"));

				if (getParsedValueFromElement(item, "cod-medio-pago") != null) {
					cot.setCodMedioPago(Integer.valueOf(getParsedValueFromElement(item, "cod-medio-pago")));
				}

				cot.setDescripMedioPago(getParsedValueFromElement(item, "desc-medio-pago"));
				cot.setCodOrigenPago(getParsedValueFromElement(item, "cod-origen-pago"));
				cot.setDescripOrigenPago(getParsedValueFromElement(item, "desc-origen-pago"));

				if (getParsedValueFromElement(item, "dia-vto") != null) {
					cot.setDiaVencimiento(Integer.valueOf(getParsedValueFromElement(item, "dia-vto")));
				}

				cot.setDescripNivel(getParsedValueFromElement(item, "desc-nivel"));
				cot.setCodTipoDoc(getParsedValueFromElement(item, "cod-tp-documento"));
				cot.setNumDocumento(getParsedValueFromElement(item, "nu-documento"));

				if (getParsedValueFromElement(item, "cant-cuotas") != null) {
					cot.setCantCuotas(Integer.valueOf(getParsedValueFromElement(item, "cant-cuotas")));
				}

				if (getParsedValueFromElement(item, "mt-cuota") != null) {
					cot.setMontoCuota(Double.valueOf(getParsedValueFromElement(item, "mt-cuota")));
				}

				if (getParsedValueFromElement(item, "mt-total") != null) {
					cot.setMontoTotal(Double.valueOf(getParsedValueFromElement(item, "mt-total")));
				}

				cot.setDescripPlanCobertura(getParsedValueFromElement(item, "desc-plan-cob"));

				cot.setRut(getParsedValueFromElement(item, "rut"));

				resultado.setUnElemento(cot);
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
	
	
	
	public ResultObtenerDetalleBusquedaCotizacion parsearObtenerDetalleBusquedaCotizacion() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerDetalleBusquedaCotizacion");

		ResultObtenerDetalleBusquedaCotizacion resultado = new ResultObtenerDetalleBusquedaCotizacion();

		try {
			NodeList elemCoti = this.doc.getElementsByTagName("cotizacion");
			if(elemCoti.getLength()>0) {
				Element item = (Element) elemCoti.item(0);
				Cotizacion cot = new Cotizacion();

				if (getParsedValueFromElement(item, "nombre-cliente") != null) {
					cot.setNombreCliente(getParsedValueFromElement(item, "nombre-cliente"));
				}

				if (getParsedValueFromElement(item, "apellido-cliente") != null) {
					cot.setApellidoRazonSocialCliente(getParsedValueFromElement(item, "apellido-cliente"));
				}

				if (getParsedValueFromElement(item, "nu-cotizacion") != null) {
					cot.setNumCotizacion(Integer.valueOf(getParsedValueFromElement(item, "nu-cotizacion")));
				}

				if (getParsedValueFromElement(item, "nu-poliza") != null) {
					cot.setNumPoliza(Integer.valueOf(getParsedValueFromElement(item, "nu-poliza")));
				}
				cot.setSucursal(getParsedValueFromElement(item, "nu-sucursal"));
				cot.setFechaDesde(getParsedValueFromElement(item, "fe-desde"));
				cot.setFechaHasta(getParsedValueFromElement(item, "fe-hasta"));
				cot.setCodMoneda(getParsedValueFromElement(item, "cod-moneda"));
				cot.setMoneda(getParsedValueFromElement(item, "desc-moneda"));
				cot.setSimboloMoneda(getParsedValueFromElement(item, "simbolo-moneda"));

				if (getParsedValueFromElement(item, "cod-plan-pago") != null) {
					cot.setPlanPago(Integer.valueOf(getParsedValueFromElement(item, "cod-plan-pago")));
				}
				cot.setDescripPlanPago(getParsedValueFromElement(item, "desc-plan-pago"));

				if (getParsedValueFromElement(item, "mt-premio-cot") != null) {
					cot.setPremioCotizacion(Double.valueOf(getParsedValueFromElement(item, "mt-premio-cot")));
				}

				if (getParsedValueFromElement(item, "mt-premio-pol") != null) {
					cot.setPremioPoliza(Double.valueOf(getParsedValueFromElement(item, "mt-premio-pol")));
				}

				cot.setEstado(getParsedValueFromElement(item, "estado"));

				if (getParsedValueFromElement(item, "cod-ramo") != null) {
					cot.setCodRamo(Integer.valueOf(getParsedValueFromElement(item, "cod-ramo")));
				}

				cot.setDescripRamo(getParsedValueFromElement(item, "desc-ramo"));
				cot.setCodProducto(getParsedValueFromElement(item, "cod-producto"));
				cot.setDescripProducto(getParsedValueFromElement(item, "desc-producto"));
				cot.setModificada(getParsedValueFromElement(item, "modificada"));
				cot.setCodTipo(getParsedValueFromElement(item, "cod-tp-transac"));
				cot.setDescripTipo(getParsedValueFromElement(item, "desc-tp-transac"));
				cot.setEnPauta(getParsedValueFromElement(item, "cod-en-pauta"));

				if (getParsedValueFromElement(item, "cod-productor") != null) {
					cot.setCodProductor(Integer.valueOf(getParsedValueFromElement(item, "cod-productor")));
				}

				cot.setDescripProductor(getParsedValueFromElement(item, "desc-productor"));

				
				if (getParsedValueFromElement(item, "cod-broker") != null) {
					cot.setCodBroker(Integer.valueOf(getParsedValueFromElement(item, "cod-broker")));
				}

				cot.setDescBroker(getParsedValueFromElement(item, "desc-broker"));
				if (getParsedValueFromElement(item, "mt-deducible") != null) {
				cot.setMontoDeducible(Double.valueOf(getParsedValueFromElement(item, "mt-deducible")));
				}
				if (getParsedValueFromElement(item, "cod-medio-pago") != null) {
					cot.setCodMedioPago(Integer.valueOf(getParsedValueFromElement(item, "cod-medio-pago")));
				}

				cot.setDescripMedioPago(getParsedValueFromElement(item, "desc-medio-pago"));
				cot.setCodOrigenPago(getParsedValueFromElement(item, "cod-origen-pago"));
				cot.setDescripOrigenPago(getParsedValueFromElement(item, "desc-origen-pago"));

				if (getParsedValueFromElement(item, "cant-certificados") != null) {
					cot.setCantCertificados(Integer.valueOf(getParsedValueFromElement(item, "cant-certificados")));
				}

				if (getParsedValueFromElement(item, "cant-bienes") != null) {
					cot.setCantBienes(Integer.valueOf(getParsedValueFromElement(item, "cant-bienes")));
				}

				cot.setCodPlanCobertura(getParsedValueFromElement(item, "cod-plan-cob"));

				if (getParsedValueFromElement(item, "dia-vto") != null) {
					cot.setDiaVencimiento(Integer.valueOf(getParsedValueFromElement(item, "dia-vto")));
				}

				cot.setDescripNivel(getParsedValueFromElement(item, "desc-nivel"));
				cot.setCodTipoDoc(getParsedValueFromElement(item, "cod-tp-documento"));
				cot.setNumDocumento(getParsedValueFromElement(item, "nu-documento"));

				if (getParsedValueFromElement(item, "cant-cuotas") != null) {
					cot.setCantCuotas(Integer.valueOf(getParsedValueFromElement(item, "cant-cuotas")));
				}

				if (getParsedValueFromElement(item, "mt-cuota") != null) {
					cot.setMontoCuota(Double.valueOf(getParsedValueFromElement(item, "mt-cuota")));
				}

				if (getParsedValueFromElement(item, "mt-total") != null) {
					cot.setMontoTotal(Double.valueOf(getParsedValueFromElement(item, "mt-total")));
				}

				cot.setDescripPlanCobertura(getParsedValueFromElement(item, "desc-plan-cob"));

				Bien bie = new Bien();

				if (getParsedValueFromElement(item, "consec-bien-principal") != null) {
					bie.setConsecutivo(Integer.valueOf(getParsedValueFromElement(item, "consec-bien-principal")));
				}

				if (getParsedValueFromElement(item, "cod-bien-principal") != null) {
					bie.setCodBien(Integer.valueOf(getParsedValueFromElement(item, "cod-bien-principal")));
				}

				bie.setBienDescripcion(getParsedValueFromElement(item, "desc-bien-principal"));

				cot.setRut(getParsedValueFromElement(item, "rut"));
				cot.setHabilitoVer(getParsedValueFromElement(item,"habilito-ver"));
				cot.setHabilitoEmitir(getParsedValueFromElement(item, "habilito-emitir"));
				cot.setHabilitoModificar(getParsedValueFromElement(item, "habilito-modificar"));
				cot.setHabilitoImprimir(getParsedValueFromElement(item, "habilito-imprimir"));
				cot.setHabilitoCopia(getParsedValueFromElement(item, "habilito-copia"));
				cot.setHabilitoAnular(getParsedValueFromElement(item, "habilito-anular"));
				cot.setHabilitoFueraPauta(getParsedValueFromElement(item, "habilito-fuera-pauta"));
				cot.setHabilitoNoSiniestro(getParsedValueFromElement(item, "habilito-no-siniestro"));

				NodeList elemDato = item.getElementsByTagName("dato");
				for (int j = 0; j < elemDato.getLength(); j++) {
					Element Item = (Element) elemDato.item(j);
					DatosBien dat = new DatosBien();

					dat.setCodDato(getParsedValueFromElement(Item, "cod-dato"));
					dat.setDatoDesc(getParsedValueFromElement(Item, "desc-dato"));
					dat.setValorCod(getParsedValueFromElement(Item, "cod-valor"));
					dat.setValorDesc(getParsedValueFromElement(Item, "desc-valor"));

					bie.setUnDato(dat);

				}

				cot.setBien(bie);

				resultado.setCotizacion(cot);
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
	

	public ResultCotizaciones parsearObtenerCotizaciones() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerCotizaciones()");

		ResultCotizaciones resultado = new ResultCotizaciones();

		try {
			NodeList elemCoti = this.doc.getElementsByTagName("cotizacion");
			for (int i = 0; i < elemCoti.getLength(); i++) {
				Element item = (Element) elemCoti.item(i);
				Cotizacion cot = new Cotizacion();

				if (getParsedValueFromElement(item, "nombre-cliente") != null) {
					cot.setNombreCliente(getParsedValueFromElement(item, "nombre-cliente"));
				}

				if (getParsedValueFromElement(item, "apellido-cliente") != null) {
					cot.setApellidoRazonSocialCliente(getParsedValueFromElement(item, "apellido-cliente"));
				}

				if (getParsedValueFromElement(item, "nu-cotizacion") != null) {
					cot.setNumCotizacion(Integer.valueOf(getParsedValueFromElement(item, "nu-cotizacion")));
				}

				if (getParsedValueFromElement(item, "nu-poliza") != null) {
					cot.setNumPoliza(Integer.valueOf(getParsedValueFromElement(item, "nu-poliza")));
				}
				cot.setSucursal(getParsedValueFromElement(item, "nu-sucursal"));
				cot.setFechaDesde(getParsedValueFromElement(item, "fe-desde"));
				cot.setFechaHasta(getParsedValueFromElement(item, "fe-hasta"));
				cot.setCodMoneda(getParsedValueFromElement(item, "cod-moneda"));
				cot.setMoneda(getParsedValueFromElement(item, "desc-moneda"));
				cot.setSimboloMoneda(getParsedValueFromElement(item, "simbolo-moneda"));

				if (getParsedValueFromElement(item, "cod-plan-pago") != null) {
					cot.setPlanPago(Integer.valueOf(getParsedValueFromElement(item, "cod-plan-pago")));
				}
				cot.setDescripPlanPago(getParsedValueFromElement(item, "desc-plan-pago"));

				if (getParsedValueFromElement(item, "mt-premio-cot") != null) {
					cot.setPremioCotizacion(Double.valueOf(getParsedValueFromElement(item, "mt-premio-cot")));
				}

				if (getParsedValueFromElement(item, "mt-premio-pol") != null) {
					cot.setPremioPoliza(Double.valueOf(getParsedValueFromElement(item, "mt-premio-pol")));
				}

				cot.setEstado(getParsedValueFromElement(item, "estado"));

				if (getParsedValueFromElement(item, "cod-ramo") != null) {
					cot.setCodRamo(Integer.valueOf(getParsedValueFromElement(item, "cod-ramo")));
				}

				cot.setDescripRamo(getParsedValueFromElement(item, "desc-ramo"));
				cot.setCodProducto(getParsedValueFromElement(item, "cod-producto"));
				cot.setDescripProducto(getParsedValueFromElement(item, "desc-producto"));
				cot.setModificada(getParsedValueFromElement(item, "modificada"));
				cot.setCodTipo(getParsedValueFromElement(item, "cod-tp-transac"));
				cot.setDescripTipo(getParsedValueFromElement(item, "desc-tp-transac"));
				cot.setEnPauta(getParsedValueFromElement(item, "cod-en-pauta"));

				if (getParsedValueFromElement(item, "cod-productor") != null) {
					cot.setCodProductor(Integer.valueOf(getParsedValueFromElement(item, "cod-productor")));
				}

				cot.setDescripProductor(getParsedValueFromElement(item, "desc-productor"));

			
				if (getParsedValueFromElement(item, "cod-broker") != null) {
					cot.setCodBroker(Integer.valueOf(getParsedValueFromElement(item, "cod-broker")));
				}

				cot.setDescBroker(getParsedValueFromElement(item, "desc-broker"));

				if (getParsedValueFromElement(item, "cod-medio-pago") != null) {
					cot.setCodMedioPago(Integer.valueOf(getParsedValueFromElement(item, "cod-medio-pago")));
				}

				cot.setDescripMedioPago(getParsedValueFromElement(item, "desc-medio-pago"));
				cot.setCodOrigenPago(getParsedValueFromElement(item, "cod-origen-pago"));
				cot.setDescripOrigenPago(getParsedValueFromElement(item, "desc-origen-pago"));

				if (getParsedValueFromElement(item, "cant-certificados") != null) {
					cot.setCantCertificados(Integer.valueOf(getParsedValueFromElement(item, "cant-certificados")));
				}

				if (getParsedValueFromElement(item, "cant-bienes") != null) {
					cot.setCantBienes(Integer.valueOf(getParsedValueFromElement(item, "cant-bienes")));
				}

				cot.setCodPlanCobertura(getParsedValueFromElement(item, "cod-plan-cob"));

				if (getParsedValueFromElement(item, "dia-vto") != null) {
					cot.setDiaVencimiento(Integer.valueOf(getParsedValueFromElement(item, "dia-vto")));
				}

				cot.setDescripNivel(getParsedValueFromElement(item, "desc-nivel"));
				cot.setCodTipoDoc(getParsedValueFromElement(item, "cod-tp-documento"));
				cot.setNumDocumento(getParsedValueFromElement(item, "nu-documento"));

				if (getParsedValueFromElement(item, "cant-cuotas") != null) {
					cot.setCantCuotas(Integer.valueOf(getParsedValueFromElement(item, "cant-cuotas")));
				}

				if (getParsedValueFromElement(item, "mt-cuota") != null) {
					cot.setMontoCuota(Double.valueOf(getParsedValueFromElement(item, "mt-cuota")));
				}

				if (getParsedValueFromElement(item, "mt-total") != null) {
					cot.setMontoTotal(Double.valueOf(getParsedValueFromElement(item, "mt-total")));
				}

				cot.setDescripPlanCobertura(getParsedValueFromElement(item, "desc-plan-cob"));

				Bien bie = new Bien();

				if (getParsedValueFromElement(item, "consec-bien-principal") != null) {
					bie.setConsecutivo(Integer.valueOf(getParsedValueFromElement(item, "consec-bien-principal")));
				}

				if (getParsedValueFromElement(item, "cod-bien-principal") != null) {
					bie.setCodBien(Integer.valueOf(getParsedValueFromElement(item, "cod-bien-principal")));
				}

				bie.setBienDescripcion(getParsedValueFromElement(item, "desc-bien-principal"));

				cot.setRut(getParsedValueFromElement(item, "rut"));

				cot.setHabilitoEmitir(getParsedValueFromElement(item, "habilito-emitir"));
				cot.setHabilitoModificar(getParsedValueFromElement(item, "habilito-modificar"));
				cot.setHabilitoImprimir(getParsedValueFromElement(item, "habilito-imprimir"));
				cot.setHabilitoCopia(getParsedValueFromElement(item, "habilito-copia"));
				cot.setHabilitoAnular(getParsedValueFromElement(item, "habilito-anular"));
				cot.setHabilitoFueraPauta(getParsedValueFromElement(item, "habilito-fuera-pauta"));
				cot.setHabilitoNoSiniestro(getParsedValueFromElement(item, "habilito-no-siniestro"));

				NodeList elemDato = item.getElementsByTagName("dato");
				for (int j = 0; j < elemDato.getLength(); j++) {
					Element Item = (Element) elemDato.item(j);
					DatosBien dat = new DatosBien();

					dat.setCodDato(getParsedValueFromElement(Item, "cod-dato"));
					dat.setDatoDesc(getParsedValueFromElement(Item, "desc-dato"));
					dat.setValorCod(getParsedValueFromElement(Item, "cod-valor"));
					dat.setValorDesc(getParsedValueFromElement(Item, "desc-valor"));

					bie.setUnDato(dat);

				}

				cot.setBien(bie);

				resultado.setUnElemento(cot);
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

	public ResultDatosCotizacion parsearDatosCotizacion() {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearDatosCotizacion()");
		ResultDatosCotizacion resultado = new ResultDatosCotizacion();

		try {

			NodeList cotizacion = this.doc.getElementsByTagName("cotizacion");
			Element cot = (Element) cotizacion.item(0);

			if (getParsedValueFromElement(cot, "mt-premio-emision") != null) {
				resultado.setPremioEmision(Double.valueOf(getParsedValueFromElement(cot, "mt-premio-emision")));
			}
			
			if (getParsedValueFromElement(cot, "mt-premio-facturacion") != null) {
				resultado.setPremioFacturacion(Double.valueOf(getParsedValueFromElement(cot, "mt-premio-facturacion")));
			}

			resultado.setCotizacion(this.parsearCotizacion());
			if (resultado.getCotizacion()!=null) {
				resultado.setEnviarFacturaEmail("S".equalsIgnoreCase(resultado.getCotizacion().getEnviarFacturaEmail()));
				resultado.setEsUnipersonal("S".equalsIgnoreCase(resultado.getCotizacion().getEsUnipersonal()));
				resultado.setEmitirConRUT("S".equalsIgnoreCase(resultado.getCotizacion().getFacturaConRut()));
			}
			resultado.setCliente(this.parsearCliente());

			resultado.setDireccionesCliente(this.parsearDireccionesClientes());
			resultado.setCertificados(this.parsearCertificadosCotizacion());
			resultado.setPlanesPago(this.parsearPlanesPago());
			resultado.setComponentes(this.parsearComponentes());
			resultado.setCumulos(this.parsearCumulos());
			resultado.setFuerasPauta(this.parsearFueraPautas());
			resultado.setCertificadosResumen(this.parsearCertResumen());
			resultado.setCertificadosCobertura(this.parsearCertificadosCobertura());

		} catch (NumberFormatException ex) {
			claveError = Values.PARSNUMEXCEPTCLAVE;
			catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = Values.PARSEXCEPTCLAVE;
			catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}
		return resultado;
	}

	private ArrayList<Certificado> parsearCertificadosCobertura() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearCertificadosCobertura()");

		ArrayList<Certificado> resultado = null;

		try {
			resultado = new ArrayList<Certificado>();
			NodeList nodoCert = this.doc.getElementsByTagName("certificado-cob");
			if (nodoCert.getLength() != 0) {
				for (int i = 0; i < nodoCert.getLength(); i++) {
					Element itemCert = (Element) nodoCert.item(i);
					Certificado cert = new Certificado();

					cert.setPermiteAnular(getParsedValueFromElement(itemCert, "permite-anular"));
					cert.setHabilitoInsertar(getParsedValueFromElement(itemCert, "habilito-insertar"));
					cert.setExisteAsegurado(getParsedValueFromElement(itemCert, "existe-asegurado"));
					if (getParsedValueFromElement(itemCert, "nu-certificado") != null) {
						cert.setNumCertificado(Integer.valueOf(getParsedValueFromElement(itemCert, "nu-certificado")));
					}
					cert.setCertificadoNuevo(getParsedValueFromElement(itemCert, "es-cert-nuevo"));
					cert.setAnulado(getParsedValueFromElement(itemCert, "estado-anulado"));

					cert.setCodPlanCobertura(getParsedValueFromElement(itemCert, "cod-plan-cob"));
					cert.setDescPlanCobertura(getParsedValueFromElement(itemCert, "desc-plan-cob"));

					cert.setBien(this.parsearBienesCobertura());

					resultado.add(cert);

				}
			}
		} catch (NumberFormatException ex) {
			claveError = Values.PARSNUMEXCEPTCLAVE;
			catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = Values.PARSEXCEPTCLAVE;
			catchException(logueo, ex);
		}
		return resultado;
	}

	private ArrayList<Bien> parsearBienesCobertura() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearBienesCobertura()");

		ArrayList<Bien> resultado = null;

		try {
			resultado = new ArrayList<Bien>();

			NodeList elemBien = this.doc.getElementsByTagName("bien-cob");
			if (elemBien.getLength() != 0) {
				for (int i = 0; i < elemBien.getLength(); i++) {
					Element itemBien = (Element) elemBien.item(i);
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

					obj.setCoberturas(this.parsearCoberturas(itemBien));

					resultado.add(obj);

				}

			}
		} catch (NumberFormatException ex) {
			claveError = Values.PARSNUMEXCEPTCLAVE;
			catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = Values.PARSEXCEPTCLAVE;
			catchException(logueo, ex);
		}
		return resultado;
	}

	private ArrayList<Certificado> parsearCertResumen() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearFueraPautas()");

		ArrayList<Certificado> resultado = null;

		try {
			resultado = new ArrayList<Certificado>();

			NodeList nodoCert = this.doc.getElementsByTagName("certificado-resumen");

			for (int i = 0; i < nodoCert.getLength(); i++) {
				Element itemCert = (Element) nodoCert.item(i);
				Certificado cert = new Certificado();

				if (getParsedValueFromElement(itemCert, "nu-certificado") != null) {
					cert.setNumCertificado(Integer.valueOf(getParsedValueFromElement(itemCert, "nu-certificado")));
				}
				cert.setCertificadoNuevo(getParsedValueFromElement(itemCert, "es-cert-nuevo"));
				cert.setAnulado(getParsedValueFromElement(itemCert, "estado-anulado"));
				NodeList nodoAnexo = itemCert.getElementsByTagName("linea-anexo");

				for (int j = 0; j < nodoAnexo.getLength(); j++) {
					Element itemAnexo = (Element) nodoAnexo.item(j);
					Anexo anexo = new Anexo();

					anexo.setDescripcion(getParsedValueFromElement(itemAnexo, "desc-linea"));
					if (getParsedValueFromElement(itemAnexo, "cod-linea") != null) {
						anexo.setCodigo(Integer.valueOf(getParsedValueFromElement(itemAnexo, "cod-linea")));
					}

					NodeList nodoClausula = itemAnexo.getElementsByTagName("clausula");

					for (int k = 0; k < nodoClausula.getLength(); k++) {
						Element itemClausula = (Element) nodoClausula.item(k);
						Clausula clau = new Clausula();

						if (getParsedValueFromElement(itemClausula, "cod-clausula") != null) {
							clau.setCodClausula(getParsedValueFromElement(itemClausula, "cod-clausula"));
						}
						clau.setDescripcionClausula(getParsedValueFromElement(itemClausula, "desc-clausula"));

						clau.setModifica(getParsedValueFromElement(itemClausula, "in-exclusion"));
						clau.setImpresion(getParsedValueFromElement(itemClausula, "in-impresion"));

						if (getParsedValueFromElement(itemClausula, "cod-ramo") != null) {
							clau.setCodRamo(Integer.valueOf(getParsedValueFromElement(itemClausula, "cod-ramo")));
						}
						anexo.setUnaClausula(clau);
					}
					cert.setUnAnexo(anexo);
				}
				resultado.add(cert);
			}

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		}
		return resultado;
	}

	private ArrayList<FueraPauta> parsearFueraPautas() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearFueraPautas()");

		ArrayList<FueraPauta> resultado = null;

		try {

			resultado = new ArrayList<FueraPauta>();
			NodeList nodoFueraPauta = this.doc.getElementsByTagName("fuera-pauta");

			for (int i = 0; i < nodoFueraPauta.getLength(); i++) {
				Element item = (Element) nodoFueraPauta.item(i);
				FueraPauta fPauta = new FueraPauta();

				fPauta.setMensaje(getParsedValueFromElement(item, "mensaje"));
				fPauta.setDescripcion(getParsedValueFromElement(item, "mensaje"));

				if (getParsedValueFromElement(item, "nu-certificado") != null) {
					fPauta.setNumCertificado(Integer.valueOf(getParsedValueFromElement(item, "nu-certificado")));

				}
				fPauta.setFirmante(getParsedValueFromElement(item, "firmante"));
				fPauta.setFechaActualizacion(getParsedValueFromElement(item, "fe-actualizacion"));
		        //PDP-1018 <in-autorizado>N</in-autorizado>
				fPauta.setAutorizado(getParsedValueFromElement(item, "in-autorizado"));
				resultado.add(fPauta);

			}

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		}
		return resultado;
	}

	private ArrayList<Cumulo> parsearCumulos() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearCumulos()");

		ArrayList<Cumulo> resultado = null;

		try {

			resultado = new ArrayList<Cumulo>();

			NodeList nodoCumulo = this.doc.getElementsByTagName("cumulo");

			for (int i = 0; i < nodoCumulo.getLength(); i++) {
				Element item = (Element) nodoCumulo.item(i);
				Cumulo cumulo = new Cumulo();

				cumulo.setMensaje(getParsedValueFromElement(item, "mensaje"));
				resultado.add(cumulo);

			}
		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		}
		return resultado;
	}

	private ArrayList<Componente> parsearComponentes() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearComponentes()");

		ArrayList<Componente> resultado = null;

		try {
			resultado = new ArrayList<Componente>();
			NodeList componentes = this.doc.getElementsByTagName("componente");

			for (int i = 0; i < componentes.getLength(); i++) {
				Componente componente = new Componente();
				final Element compItem = (Element) componentes.item(i);

				componente.setCodigo(getParsedValueFromElement(compItem, "cod-componente"));
				componente.setDescripcion(getParsedValueFromElement(compItem, "desc-componente"));
				String monto = getParsedValueFromElement(compItem, "importe");
				if (monto != null && !monto.equals("")) {
					componente.setMonto(Double.valueOf(monto));
				}
				resultado.add(componente);

			}
		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		}
		return resultado;
	}

	private ArrayList<PlanPagoCotizacion> parsearPlanesPago() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearPlanesPago()");

		ArrayList<PlanPagoCotizacion> resultado = null;

		try {
			resultado = new ArrayList<PlanPagoCotizacion>();
			NodeList planPago = this.doc.getElementsByTagName("planes");

			for (int k = 0; k < planPago.getLength(); k++) {
				PlanPagoCotizacion planP = new PlanPagoCotizacion();
				Element planItem = (Element) planPago.item(k);

				if (getParsedValueFromElement(planItem, "dia-vto") != null) {
					planP.setDiaVto(Integer.valueOf(getParsedValueFromElement(planItem, "dia-vto")));
				}

				planP.setDescMoneda(getParsedValueFromElement(planItem, "desc-moneda"));
				planP.setSimboloMoneda(getParsedValueFromElement(planItem, "simbolo-moneda"));

				if (getParsedValueFromElement(planItem, "cod-nivel-comision") != null) {
					planP.setCodNivelComision(Integer.valueOf(getParsedValueFromElement(planItem, "cod-nivel-comision")));
				}

				planP.setDescNivelComision(getParsedValueFromElement(planItem, "desc-nivel-comision"));
				
				if (getParsedValueFromElement(planItem, "cod-nivel-comision-broker") != null) {
					planP.setCodNivelComisionBroker(Integer.valueOf(getParsedValueFromElement(planItem, "cod-nivel-comision-broker")));
				}

				planP.setDescNivelComisionBroker(getParsedValueFromElement(planItem, "desc-nivel-comision-broker"));
				
				
				if (getParsedValueFromElement(planItem, "cod-plan-pago-cot") != null) {
					planP.setCodigo(getParsedValueFromElement(planItem, "cod-plan-pago-cot"));
				}

				planP.setDescripcion(getParsedValueFromElement(planItem, "desc-plan-pago-cot"));

				if (getParsedValueFromElement(planItem, "mt-cuota-cert") != null) {
					planP.setMontoCuota(Double.valueOf(getParsedValueFromElement(planItem, "mt-cuota-cert")));
				}
				if (getParsedValueFromElement(planItem, "mt-total-cert") != null) {
					planP.setMontoTotal(Double.valueOf(getParsedValueFromElement(planItem, "mt-total-cert")));
				}

				NodeList certificado = this.doc.getElementsByTagName("certificado-plan");

				for (int i = 0; i < certificado.getLength(); i++) {
					Element cert = (Element) certificado.item(i);
					Certificado certPlanes = new Certificado();

					if (getParsedValueFromElement(cert, "nu-certificado") != null) {
						certPlanes.setNumCertificado(Integer.valueOf(getParsedValueFromElement(cert, "nu-certificado")));
					}
					
					certPlanes.setCertificadoNuevo(getParsedValueFromElement(cert, "es-cert-nuevo"));
					certPlanes.setAnulado(getParsedValueFromElement(cert, "estado-anulado"));
					NodeList planCobCert = cert.getElementsByTagName("plan-cobertura-cert-list");

					for (int j = 0; j < planCobCert.getLength(); j++) {
						Element planCob = (Element) planCobCert.item(j);
						PlanCobertura plan = new PlanCobertura();

						plan.setCodigo(getParsedValueFromElement(planCob, "cod-plan-cobertura-cert"));
						plan.setDescripcion(getParsedValueFromElement(planCob, "desc-plan-cobertura-cert"));
						if (getParsedValueFromElement(planCob, "mt-deducible") != null) {
							plan.setMontoDeducible(Double.valueOf(getParsedValueFromElement(planCob, "mt-deducible")));
						}

						NodeList planPagoCert = planCob.getElementsByTagName("plan-pago");

						for (int l = 0; l < planPagoCert.getLength(); l++) {
							Element planPagoC = (Element) planPagoCert.item(l);
							PlanPago pPago = new PlanPago();

							pPago.setCodigo(getParsedValueFromElement(planPagoC, "cod-plan-pago"));
							pPago.setDescripcion(getParsedValueFromElement(planPagoC, "desc-plan-pago"));

							if (getParsedValueFromElement(planPagoC, "mt-cuota") != null) {
								pPago.setMontoCuota(Double.valueOf(getParsedValueFromElement(planPagoC, "mt-cuota")));
							}

							if (getParsedValueFromElement(planPagoC, "mt-total") != null) {
								pPago.setMontoTotal(Double.valueOf(getParsedValueFromElement(planPagoC, "mt-total")));
							}

							if (getParsedValueFromElement(planPagoC, "mt-prima") != null) {
								pPago.setMontoPrima(Double.valueOf(getParsedValueFromElement(planPagoC, "mt-prima")));
							}

							if (getParsedValueFromElement(planPagoC, "mt-premio") != null) {
								pPago.setMontoPremio(Double.valueOf(getParsedValueFromElement(planPagoC, "mt-premio")));
							}

							plan.setUnPlanPago(pPago);

						}
						certPlanes.setUnaCobertura(plan);

					}

					planP.setUnCertificado(certPlanes);

				}

				resultado.add(planP);

			}
		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		}
		return resultado;

	}

	private ArrayList<Certificado> parsearCertificadosCotizacion() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearCertificadosCotizacion()");

		ArrayList<Certificado> resultado = null;

		try {
			resultado = new ArrayList<Certificado>();
			NodeList certificados = this.doc.getElementsByTagName("certificado-cot");
			for (int i = 0; i < certificados.getLength(); i++) {
				Element itemCert = (Element) certificados.item(i);
				Certificado cert = new Certificado();

				cert.setPermiteAnular(getParsedValueFromElement(itemCert, "permite-anular"));
				cert.setHabilitoInsertar(getParsedValueFromElement(itemCert, "habilito-insertar"));
				cert.setExisteAsegurado(getParsedValueFromElement(itemCert, "existe-asegurado"));
				if (getParsedValueFromElement(itemCert, "nu-certificado") != null) {
					cert.setNumCertificado(Integer.valueOf(getParsedValueFromElement(itemCert, "nu-certificado")));
				}
				cert.setCertificadoNuevo(getParsedValueFromElement(itemCert, "es-cert-nuevo"));
				cert.setAnulado(getParsedValueFromElement(itemCert, "estado-anulado"));
				cert.setCodPlanCobertura(getParsedValueFromElement(itemCert, "cod-plan-cob"));
				cert.setDescPlanCobertura(getParsedValueFromElement(itemCert, "desc-plan-cob"));

				cert.setAsegurado(this.parsearAsegurado());
				cert.setDireccionesAsegurado(this.parsearDireccionesAsegurado());
				cert.setBienCert(this.parsearBienesCert());

				resultado.add(cert);

			}
		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		}
		return resultado;
	}

	private ArrayList<BienCert> parsearBienesCert() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearBienesCert()");

		ArrayList<BienCert> resultado = null;

		try {
			resultado = new ArrayList<BienCert>();

			NodeList elementos = this.doc.getElementsByTagName("bienes-list");
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
				obj.setHabilitoAnexos(getParsedValueFromElement(itemBien, "boton-anexos"));
				obj.setHabilitoTextos(getParsedValueFromElement(itemBien, "boton-textos"));
				obj.setHabilitoListaBienes(getParsedValueFromElement(itemBien, "boton-lista-bienes"));
				obj.setHabilitoUbicacion(getParsedValueFromElement(itemBien, "boton-ubicacion"));
				obj.setHabilitoBeneficiarios(getParsedValueFromElement(itemBien, "boton-beneficiarios"));
				obj.setHabilitoAcreedores(getParsedValueFromElement(itemBien, "boton-acreedores"));
				obj.setHabilitoNominas(getParsedValueFromElement(itemBien, "boton-nominas"));

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

				obj.setCoberturas(this.parsearCoberturas(itemBien));
				obj.setUbicacionBien(this.parsearUbicacionBien(itemBien));
				obj.setAcreedoresBien(this.parsearAcreedores(itemBien));
				obj.setObjetosBien(this.parsearObjetosBien(itemBien));
				obj.setBeneficiariosBien(this.parsearBeneficiarios(itemBien));

				resultado.add(obj);
			}

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		}
		return resultado;
	}

	private ArrayList<Beneficiario> parsearBeneficiarios(Element itemBien) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearBeneficiarios()");

		ArrayList<Beneficiario> resultado = null;

		try {
			resultado = new ArrayList<Beneficiario>();

			NodeList elementos = itemBien.getElementsByTagName("beneficiario");
			for (int i = 0; i < elementos.getLength(); i++) {
				Beneficiario dBeneficiarios = new Beneficiario();
				Element Item = (Element) elementos.item(i);

				dBeneficiarios.setTipoDocumento(getParsedValueFromElement(Item, "tp-documento"));
				dBeneficiarios.setNumDocumento(getParsedValueFromElement(Item, "nu-documento"));
				dBeneficiarios.setNombreBeneficiario(getParsedValueFromElement(Item, "nombres"));
				dBeneficiarios.setCodParentesco(getParsedValueFromElement(Item, "cod-parentesco"));
				dBeneficiarios.setDescripcionParentesco(getParsedValueFromElement(Item, "desc-parentesco"));
				if (getParsedValueFromElement(Item, "po-participacion") != null) {
					dBeneficiarios.setPorcentajeParticipacion(Double.valueOf(getParsedValueFromElement(Item, "po-participacion")));
				}
				String fechaInclusion = getParsedValueFromElement(Item, "fe_inclusion");
				String fechaExclusion = getParsedValueFromElement(Item, "fe_exclusion");
				String fechaNacimiento = getParsedValueFromElement(Item, "fe-nacimiento");
				if (fechaInclusion != null) {
					dBeneficiarios.setFechaInclusion(fechaInclusion);
				}
				if (fechaNacimiento != null) {
					dBeneficiarios.setFechaNacimiento(fechaNacimiento);
				}

				if (fechaExclusion != null) {
					dBeneficiarios.setFechaExclusion(fechaExclusion);
				}
				dBeneficiarios.setObservaciones(getParsedValueFromElement(Item, "observaciones"));
				dBeneficiarios.setEsNuevo(getParsedValueFromElement(Item, "es-nuevo"));
				resultado.add(dBeneficiarios);
			}

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		}
		return resultado;
	}

	private ArrayList<ObjetoBien> parsearObjetosBien(Element itemBien) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObjetosBien()");

		ArrayList<ObjetoBien> resultado = null;

		try {
			resultado = new ArrayList<ObjetoBien>();

			NodeList elementos = itemBien.getElementsByTagName("lista-bien");

			for (int j = 0; j < elementos.getLength(); j++) {
				ObjetoBien objBien = new ObjetoBien();
				Element objeto = (Element) elementos.item(j);

				if (getParsedValueFromElement(objeto, "cod-marca") != null) {
					objBien.setCodMarca(Integer.valueOf(getParsedValueFromElement(objeto, "cod-marca")));
				}
				if (getParsedValueFromElement(objeto, "cod-objeto") != null) {
					objBien.setCodObjeto(Integer.valueOf(getParsedValueFromElement(objeto, "cod-objeto")));
				}
				objBien.setDescripcion(getParsedValueFromElement(objeto, "descripcion"));
				objBien.setDescripcionMarca(getParsedValueFromElement(objeto, "desc-marca"));
				objBien.setDescripcionObjeto(getParsedValueFromElement(objeto, "desc-objeto"));
				if (getParsedValueFromElement(objeto, "nu-ordinal") != null) {
					objBien.setNumOrdinal(Integer.valueOf(getParsedValueFromElement(objeto, "nu-ordinal")));
				}

				if (getParsedValueFromElement(objeto, "nu-unidades") != null) {
					objBien.setNumUnidades(Integer.valueOf(getParsedValueFromElement(objeto, "nu-unidades")));
				}
				objBien.setSerial(getParsedValueFromElement(objeto, "serial"));
				if (getParsedValueFromElement(objeto, "valor") != null) {
					objBien.setValor(Double.valueOf(getParsedValueFromElement(objeto, "valor")));
				}
				if (getParsedValueFromElement(objeto, "valor-total") != null) {
					objBien.setValorTotal(Double.valueOf(getParsedValueFromElement(objeto, "valor-total")));
				}

				resultado.add(objBien);
			}
		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		}
		return resultado;
	}

	private ArrayList<AcreedorBien> parsearAcreedores(Element itemBien) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearAcreedores()");

		ArrayList<AcreedorBien> resultado = null;

		try {
			resultado = new ArrayList<AcreedorBien>();

			NodeList elementos = itemBien.getElementsByTagName("acreedor");

			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				AcreedorBien acr = new AcreedorBien();

				if (getParsedValueFromElement(item, "cod-acreedor") != null) {
					acr.setCodAcreedor(Integer.valueOf(getParsedValueFromElement(item, "cod-acreedor")));
				}
				acr.setDescripAcreedor(getParsedValueFromElement(item, "desc-acreedor"));

				if (getParsedValueFromElement(item, "cod-objeto") != null) {
					acr.setCodObjeto(Integer.valueOf(getParsedValueFromElement(item, "cod-objeto")));
				}
				acr.setDescripObjeto(getParsedValueFromElement(item, "desc-objeto"));
				acr.setCodTipoAcreedor(getParsedValueFromElement(item, "cod-tp-acreedor"));
				acr.setDescripTipoAcreedor(getParsedValueFromElement(item, "desc-tp-acreedor"));

				if (getParsedValueFromElement(item, "po-participacion") != null) {
					acr.setPorcentajeParticipacion(Double.valueOf(getParsedValueFromElement(item, "po-participacion")));
				}
				acr.setIdentificador(getParsedValueFromElement(item, "identificador"));
				acr.setFechaExclusion(getParsedValueFromElement(item, "fe-exclusion"));

				resultado.add(acr);
			}

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		}
		return resultado;

	}

	private Ubicacion parsearUbicacionBien(Element itemBien) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearUbicacionBien()");

		Ubicacion resultado = null;

		try {
			resultado = new Ubicacion();
			NodeList elementos = itemBien.getElementsByTagName("ubicacion");
			if (elementos.getLength() != 0) {
				Element item = (Element) elementos.item(0);

				resultado.setCodDepartamento(this.evaluarInt(item, "cod-provincia"));
				resultado.setDepartamento(getParsedValueFromElement(item, "desc-estado"));
				resultado.setLocalidad(getParsedValueFromElement(item, "desc-municipio"));
				resultado.setCodLocalidad(this.evaluarInt(item, "cod-municipio"));
				resultado.setStCapital(this.evaluarInt(item, "st-capital"));

				resultado.setCodPais(this.evaluarInt(item, "cod-pais"));
				resultado.setPais(getParsedValueFromElement(item, "desc-pais"));
				resultado.setRadio(this.evaluarInt(item, "cod-postal"));
				resultado.setTelefono(getParsedValueFromElement(item, "telefono"));
				resultado.setDescripRadio(getParsedValueFromElement(item, "desc-localidad"));
				resultado.setCalle(getParsedValueFromElement(item, "desc-calle"));
				resultado.setAclaracion(getParsedValueFromElement(item, "aclaracion-dir"));
				resultado.setNumPostal(this.evaluarInt(item, "nu-postal"));
				resultado.setCodCalle(this.evaluarInt(item, "cod-calle"));
				resultado.setNumero(getParsedValueFromElement(item, "nu-calle"));
				resultado.setPiso(getParsedValueFromElement(item, "nu-piso"));
				resultado.setPadron(getParsedValueFromElement(item, "nu-padron"));
				resultado.setNumDpto(getParsedValueFromElement(item, "desc-departamento"));
				resultado.setNumDireccion(this.evaluarInt(item, "nu-direccion"));
				resultado.setInicioRangoNumeracion(this.evaluarInt(item, "inicio-rango-numeracion"));
				resultado.setFinRangoNumeracion(this.evaluarInt(item, "fin-rango-numeracion"));
			}

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		}
		return resultado;
	}

	private ArrayList<Cobertura> parsearCoberturas() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearCoberturas()");

		ArrayList<Cobertura> resultado = null;

		try {
			resultado = new ArrayList<Cobertura>();

			NodeList elementos = this.doc.getElementsByTagName("cobertura");

			for (int i = 0; i < elementos.getLength(); i++) {

				Element item = (Element) elementos.item(i);
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

				resultado.add(dato);

			}
		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		}
		return resultado;
	}

	private ArrayList<DireccionEC> parsearDireccionesAsegurado() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearDireccionesAsegurado()");

		ArrayList<DireccionEC> resultado = null;

		try {
			resultado = new ArrayList<DireccionEC>();
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

				resultado.add(dirAs);
			}

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		}
		return resultado;

	}

	public Cliente parsearAsegurado() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearAsegurado()");

		Cliente aseg = null;

		aseg = new Cliente();

		NodeList datosAseg = this.doc.getElementsByTagName("datos-basicos-asegurado");
		if (datosAseg.getLength() != 0) {
			Element itemDatosAseg = (Element) datosAseg.item(0);

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
			aseg.setEsPep(getParsedValueFromElement(itemDatosAseg, "es-pep"));
			aseg.setFechaPep(getParsedValueFromElement(itemDatosAseg, "fe-pep"));
			if (getParsedValueFromElement(itemDatosAseg, "nu-cons-telefono") != null && !getParsedValueFromElement(itemDatosAseg, "nu-cons-telefono").equals("-1")) {
				telAse.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(itemDatosAseg, "nu-cons-telefono")));
				if (!" ".equals(getParsedValueFromElement(itemDatosAseg, "telefono"))) {
					telAse.setValorComunicacion(getParsedValueFromElement(itemDatosAseg, "telefono"));
				}
				aseg.setTel(telAse);
			}
			if (getParsedValueFromElement(itemDatosAseg, "nu-cons-celular") != null && !getParsedValueFromElement(itemDatosAseg, "nu-cons-celular").equals("-1")) {
				celAse.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(itemDatosAseg, "nu-cons-celular")));
				if (!" ".equals(getParsedValueFromElement(itemDatosAseg, "celular"))) {
					celAse.setValorComunicacion(getParsedValueFromElement(itemDatosAseg, "celular"));
				}
				aseg.setCel(celAse);
			}
			if (getParsedValueFromElement(itemDatosAseg, "nu-cons-mail") != null && !getParsedValueFromElement(itemDatosAseg, "nu-cons-mail").equals("-1")) {
				mailAse.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(itemDatosAseg, "nu-cons-mail")));
				if (!" ".equals(getParsedValueFromElement(itemDatosAseg, "mail"))) {
					mailAse.setValorComunicacion(getParsedValueFromElement(itemDatosAseg, "mail"));
				}

				aseg.setMail(mailAse);
			}

			aseg.setAnulacionCorrida(getParsedValueFromElement(itemDatosAseg, "in-anulacion"));
		}

		return aseg;

	}

	private ArrayList<DireccionEC> parsearDireccionesClientes() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearDireccionesClientes()");

		ArrayList<DireccionEC> resultado = null;

		try {
			resultado = new ArrayList<DireccionEC>();
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

				resultado.add(dato);
			}
		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		}
		return resultado;
	}

	private ArrayList<Bien> parsearBienes() {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearBienes()");

		ArrayList<Bien> resultado = null;

		try {
			resultado = new ArrayList<Bien>();

			final NodeList elementos = this.doc.getElementsByTagName("bien");

			for (int i = 0; i < elementos.getLength(); i++) {
				final Element item = (Element) elementos.item(i);
				final Bien obj = new Bien();

				final String consecutivo = getParsedValueFromElement(item, "consec-bien");

				if (consecutivo == null) {
					logueo.setErrorDatos("El tag consec-bien es null");
					LOG.error(logueo.getErrorDatos());
				} else {
					obj.setConsecutivo(Integer.valueOf(consecutivo));
				}

				final String codBien = getParsedValueFromElement(item, "cod-bien");

				if (codBien == null) {
					logueo.setErrorDatos("El tag cod-bien es null");
					LOG.error(logueo.getErrorDatos());
				} else {
					obj.setCodBien(Integer.valueOf(codBien));
				}

				final String posBien = getParsedValueFromElement(item, "pos-bien");

				if (posBien == null) {
					logueo.setErrorDatos("El tag pos-bien es null");
					LOG.error(logueo.getErrorDatos());
				} else {
					obj.setPosBien(Integer.valueOf(posBien));
				}

				obj.setBienDescripcion(getParsedValueFromElement(item, "desc-bien"));
				obj.setFechaBaja(getParsedValueFromElement(item, "fe-baja"));

				if (getParsedValueFromElement(item, "certificado") != null) {
					obj.setCertificado(Integer.valueOf(getParsedValueFromElement(item, "certificado")));
				}

				obj.setHabilitoAnexos(getParsedValueFromElement(item, "boton-anexos"));
				obj.setHabilitoTextos(getParsedValueFromElement(item, "boton-textos"));
				obj.setHabilitoListaBienes(getParsedValueFromElement(item, "boton-lista-bienes"));
				obj.setHabilitoUbicacion(getParsedValueFromElement(item, "boton-ubicacion"));
				obj.setHabilitoBeneficiarios(getParsedValueFromElement(item, "boton-beneficiarios"));
				obj.setHabilitoAcreedores(getParsedValueFromElement(item, "boton-acreedores"));
				obj.setHabilitoNominas(getParsedValueFromElement(item, "boton-nominas"));

				final NodeList datos = item.getElementsByTagName("dato");

				if (datos.getLength() > 0) {
					final ArrayList<DatosBien> listaDatos = new ArrayList<DatosBien>();

					for (int j = 0; j < datos.getLength(); j++) {
						final Element itemDato = (Element) datos.item(j);
						final DatosBien objDato = new DatosBien();
						objDato.setValorCod(getParsedValueFromElement(itemDato, "cod-valor"));
						objDato.setDatoDesc(getParsedValueFromElement(itemDato, "desc-dato"));
						objDato.setValorDesc(getParsedValueFromElement(itemDato, "desc-valor"));
						objDato.setVisibilidad(getParsedValueFromElement(itemDato, "visible"));
						objDato.setDatoTipo(getParsedValueFromElement(itemDato, "tipo-dato"));
						objDato.setRequerido(getParsedValueFromElement(itemDato, "requerido"));

						objDato.setCodDato(getParsedValueFromElement(itemDato, "cod-dato"));
						objDato.setLongitudDato(getParsedValueFromElement(itemDato, "longitud-dato"));
						objDato.setCantDecimales(getParsedValueFromElement(itemDato, "cant-decimales"));
						objDato.setCodTabla(getParsedValueFromElement(itemDato, "cod-tabla"));

						listaDatos.add(objDato);
					}

					obj.setDatos(listaDatos);
				}

				ArrayList<Franquicia> franquiciasList = new ArrayList<Franquicia>();
				franquiciasList = parsearFranquicia(item);
				obj.setFranquicias(franquiciasList);

				ArrayList<Cobertura> coberturasList = new ArrayList<Cobertura>();
				coberturasList = parsearCoberturas(item);
				obj.setCoberturas(coberturasList);

				resultado.add(obj);

			}

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		}

		return resultado;
	}

	private ArrayList<Cobertura> parsearCoberturas(Element item) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearCoberturas");

		ArrayList<Cobertura> resultado = null;

		try {
			resultado = new ArrayList<Cobertura>();

			final NodeList elementos = item.getElementsByTagName("cobertura");

			for (int h = 0; h < elementos.getLength(); h++) {
				final Element itemCob = (Element) elementos.item(h);
				final Cobertura cob = new Cobertura();

				final String codCobertura = getParsedValueFromElement(itemCob, "cod-cobertura");

				if (codCobertura != null) {
					cob.setCoberturaCod(codCobertura);
				}

				final String codRamo = getParsedValueFromElement(itemCob, "cod-ramo-cob");

				if (codRamo != null) {
					cob.setRamoCod(Integer.valueOf(codRamo));
				}

				String capital = getParsedValueFromElement(itemCob, "capital");
				if (capital != null) {
					cob.setCapital(Double.valueOf(capital));
				}

				String requerido = getParsedValueFromElement(itemCob, "requerido");
				if (requerido != null) {
					cob.setRequerido(requerido);
				}

				cob.setEditable(getParsedValueFromElement(itemCob, "editable"));
				cob.setBienDesc(getParsedValueFromElement(itemCob, "desc-bien"));
				cob.setCoberturaDesc(getParsedValueFromElement(itemCob, "desc-cobertura"));

				final String montoPrima = getParsedValueFromElement(itemCob, "monto-prima");

				if (montoPrima != null) {
					cob.setPrima(Double.valueOf(montoPrima));
				}

				final String nuBien = getParsedValueFromElement(itemCob, "nu-bien");

				if (nuBien != null) {
					cob.setBienNum(Integer.valueOf(nuBien));
				}

				final String nuElemento = getParsedValueFromElement(itemCob, "nu-elemento");

				if (nuElemento != null) {
					cob.setElementosNum(Integer.valueOf(nuElemento));
				}

				final String sumaAsegurada = getParsedValueFromElement(itemCob, "suma-asegurada");

				if (sumaAsegurada != null) {
					cob.setSumaAsegurada(sumaAsegurada);
				}

				final String tasaPrima = getParsedValueFromElement(itemCob, "tasa-prima");

				if (tasaPrima != null) {
					cob.setTasaPrima(tasaPrima);
				}

				resultado.add(cob);
			}
		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		}

		return resultado;
	}

	private Cliente parsearCliente() {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearCliente()");

		Cliente cli = null;

		try {
			NodeList elemClie = this.doc.getElementsByTagName("cliente");
			Element itemClie = (Element) elemClie.item(0);

			NodeList clieDatos = itemClie.getElementsByTagName("datos-basicos-cliente");

			if (clieDatos.getLength() == 0) {
				LOG.info("No hay clientes para esta cotizacion");
			} else {
				Element item = (Element) clieDatos.item(0);
				cli = new Cliente();
				ComunicacionEC cel = new ComunicacionEC();
				ComunicacionEC tel = new ComunicacionEC();
				ComunicacionEC mail = new ComunicacionEC();
				ComunicacionEC mailFacturas = new ComunicacionEC();

				if (getParsedValueFromElement(item, "nu-persona") != null) {
					cli.setNumPersona(getParsedValueFromElement(item, "nu-persona"));
				}

				cli.setTipoDoc(getParsedValueFromElement(item, "cod-tp-documento"));
				cli.setDescripTipoDoc(getParsedValueFromElement(item, "desc-tp-documento"));
				cli.setNumDoc(getParsedValueFromElement(item, "nu-documento"));
				cli.setRut(getParsedValueFromElement(item, "rut"));
				cli.setNombre(getParsedValueFromElement(item, "nombre-persona"));
				cli.setApellidoRazon(getParsedValueFromElement(item, "apellido-persona"));
				if (getParsedValueFromElement(item, "cod-profesion") != null) {
					cli.setCodProfesion(Integer.valueOf(getParsedValueFromElement(item, "cod-profesion")));
				}
				cli.setProfesion(getParsedValueFromElement(item, "desc-profesion"));
				cli.setCodCliente(getParsedValueFromElement(item, "nu-cedula-rif"));
				cli.setEsPep(getParsedValueFromElement(item, "es-pep"));
				cli.setFechaPep(getParsedValueFromElement(item, "fe-pep"));
				if (getParsedValueFromElement(item, "nu-cons-telefono") != null && !getParsedValueFromElement(item, "nu-cons-telefono").equals("-1")) {
					tel.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(item, "nu-cons-telefono")));
					if (!" ".equals(getParsedValueFromElement(item, "telefono"))) {
						tel.setValorComunicacion(getParsedValueFromElement(item, "telefono"));
					}
					cli.setTel(tel);
				}
				if (getParsedValueFromElement(item, "nu-cons-celular") != null && !getParsedValueFromElement(item, "nu-cons-celular").equals("-1")) {
					cel.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(item, "nu-cons-celular")));
					if (!" ".equals(getParsedValueFromElement(item, "celular"))) {
						cel.setValorComunicacion(getParsedValueFromElement(item, "celular"));
					}
					cli.setCel(cel);
				}
				if (getParsedValueFromElement(item, "nu-cons-mail") != null && !getParsedValueFromElement(item, "nu-cons-mail").equals("-1")) {
					mail.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(item, "nu-cons-mail")));
					if (!" ".equals(getParsedValueFromElement(item, "mail"))) {
						mail.setValorComunicacion(getParsedValueFromElement(item, "mail"));
					}

					cli.setMail(mail);
				}
				
				if (getParsedValueFromElement(item, "nu-cons-mail-factura") != null && !getParsedValueFromElement(item, "nu-cons-mail-factura").equals("-1")) {
					
					mailFacturas.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(item, "nu-cons-mail-factura")));
					if (getParsedValueFromElement(item, "mail-factura") != null && !getParsedValueFromElement(item, "mail-factura").equals(" ")) {
						mailFacturas.setValorComunicacion(getParsedValueFromElement(item, "mail-factura"));
					}

					cli.setMailFacturas(mailFacturas);
				}


			}
		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		}

		return cli;
	}

	private DatosCotizacion parsearCotizacion() {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearCotizacion()");

		DatosCotizacion resultado = null;

		try {
			final NodeList elementos = this.doc.getElementsByTagName("datos-basicos");

			if (elementos.getLength() == 0) {
				LOG.info("No hay datos básicos para esta cotizacion");
			} else {
				final Element item = (Element) elementos.item(0);
				resultado = new DatosCotizacion();

				final String cotizacion = getParsedValueFromElement(item, "nu-cotizacion");

				if (cotizacion != null) {
					resultado.setNumCotizacion(Integer.valueOf(cotizacion));
				}

				final String poliza = getParsedValueFromElement(item, "nu-poliza");

				if (poliza != null) {
					resultado.setNumPoliza(Integer.valueOf(poliza));
				}

				final String certificado = getParsedValueFromElement(item, "nu-certificado");

				if (certificado != null) {
					resultado.setNumCertificado(Integer.valueOf(certificado));
				}

				final String motivo = getParsedValueFromElement(item, "cod-motivo");

				if (motivo != null) {
					resultado.setCodMotivo(Integer.valueOf(motivo));
				}

				if (getParsedValueFromElement(item, "nu-sucursal") != null) {
					resultado.setSucursal(Integer.valueOf(getParsedValueFromElement(item, "nu-sucursal")));
				}

				resultado.setEstado(getParsedValueFromElement(item, "estado"));

				final String codNivel = getParsedValueFromElement(item, "cod-nivel");

				if (codNivel != null) {
					resultado.setNivelCod(Integer.valueOf(codNivel));
				}

				resultado.setFechaEmision(getParsedValueFromElement(item, "fe-cotizacion"));
				resultado.setNivelDesc(getParsedValueFromElement(item, "desc-nivel"));
				
				final String codNivelBroker = getParsedValueFromElement(item, "cod-nivel-broker");
				
				if (codNivelBroker != null) {
					resultado.setNivelCodBroker(Integer.valueOf(codNivelBroker));
				}
				
				
				resultado.setNivelDescBroker(getParsedValueFromElement(item, "desc-nivel-broker"));
				
				final String codPlanPago = getParsedValueFromElement(item, "cod-plan-pago");

				if (codPlanPago != null) {
					resultado.setPlanPagoCod(codPlanPago);
				}

				resultado.setPlanPagoDesc(getParsedValueFromElement(item, "desc-plan-pago"));

				final String codMedioPago = getParsedValueFromElement(item, "cod-medio-pago");

				if (codMedioPago != null) {
					resultado.setMedioPagoCod(Integer.valueOf(codMedioPago));
				}

				resultado.setMedioPagoDesc(getParsedValueFromElement(item, "desc-medio-pago"));
				resultado.setOrigenEndoso(getParsedValueFromElement(item, "cod-origen-endoso"));
				resultado.setOrigenEndosoDesc(getParsedValueFromElement(item, "desc-origen-endoso"));
				resultado.setFacturacionCod(getParsedValueFromElement(item, "cod-tp-facturacion"));
				resultado.setFacturacionDesc(getParsedValueFromElement(item, "desc-tp-facturacion"));
				
				final String codProductor = getParsedValueFromElement(item, "cod-productor");

				if (codProductor != null) {
					resultado.setProductorCod(Integer.valueOf(codProductor));
				}

				resultado.setProductorDesc(getParsedValueFromElement(item, "desc-productor"));

				// FIXME OIGRES YA ADD BROKER
				if (getParsedValueFromElement(item, "cod-broker") != null) {
					resultado.setCodBroker(Integer.valueOf(getParsedValueFromElement(item, "cod-broker")));
				}

				resultado.setDescBroker(getParsedValueFromElement(item, "desc-broker"));

				final String codMoneda = getParsedValueFromElement(item, "cod-moneda");

				if (codMoneda != null) {
					resultado.setMonedaCod(Integer.valueOf(codMoneda));
				}

				resultado.setMonedaDesc(getParsedValueFromElement(item, "desc-moneda"));
				resultado.setOrigenCod(getParsedValueFromElement(item, "cod-origen"));
				resultado.setOrigenDesc(getParsedValueFromElement(item, "desc-origen"));
				resultado.setPromocionCod(getParsedValueFromElement(item, "cod-promocion"));
				resultado.setPromocionDesc(getParsedValueFromElement(item, "desc-promocion"));
				resultado.setModoCalculoCod(getParsedValueFromElement(item, "cod-tp-calculo"));
				resultado.setModoCalculoDesc(getParsedValueFromElement(item, "desc-tp-calculo"));
				resultado.setVigenciaCod(getParsedValueFromElement(item, "cod-fr-pago"));
				resultado.setVigenciaDesc(getParsedValueFromElement(item, "desc-fr-pago"));
				resultado.setFrecTecnicaCod(getParsedValueFromElement(item, "cod-vig-tecnica"));
				resultado.setFrecTecnicaDesc(getParsedValueFromElement(item, "desc-vig-tecnica"));
				resultado.setUsuarioCod(getParsedValueFromElement(item, "cod-usuario"));
				resultado.setUsuarioDesc(getParsedValueFromElement(item, "desc-usuario"));
				resultado.setVigenciaDesde(getParsedValueFromElement(item, "fe-desde"));
				resultado.setVigenciaHasta(getParsedValueFromElement(item, "fe-hasta"));
				resultado.setVigenciaTecnicaDesde(getParsedValueFromElement(item, "fe-desde-tecnica"));
				resultado.setVigenciaTecnicaHasta(getParsedValueFromElement(item, "fe-hasta-tecnica"));

				final String premioInformado = getParsedValueFromElement(item, "premio-informado");

				if (premioInformado != null) {
					resultado.setPremioInformado(Double.valueOf(premioInformado));
				}
				resultado.setRenovacionCod(getParsedValueFromElement(item, "cod-renovacion"));
				resultado.setRenovacionDesc(getParsedValueFromElement(item, "desc-renovacion"));
				resultado.setCodProducto(getParsedValueFromElement(item, "cod-producto"));
				resultado.setDescProducto(getParsedValueFromElement(item, "desc-producto"));

				if (getParsedValueFromElement(item, "cod-ramo") != null) {
					resultado.setCodRamo(Integer.valueOf(getParsedValueFromElement(item, "cod-ramo")));
				}
				resultado.setDescRamo(getParsedValueFromElement(item, "desc-ramo"));

				if (getParsedValueFromElement(item, "cod-banco") != null) {
					resultado.setCodBanco(Integer.valueOf(getParsedValueFromElement(item, "cod-banco")));
				}

				if (getParsedValueFromElement(item, "nu-dom-bancario") != null) {
					resultado.setNuDomBancario(Integer.valueOf(getParsedValueFromElement(item, "nu-dom-bancario")));
				}

				resultado.setDescBanco(getParsedValueFromElement(item, "desc-banco"));

				resultado.setNumTarjeta(getSeudoTarjeta(getParsedValueFromElement(item, "nu-tarjeta")));
				
				resultado.setEsUnipersonal(getParsedValueFromElement(item, "es-unipersonal"));
				resultado.setEnviarFacturaEmail(getParsedValueFromElement(item, "envio-fact-mail"));
				resultado.setFacturaConRut(getParsedValueFromElement(item, "doc-defecto"));

			}
		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		}

		return resultado;
	}

	public ArrayList<Franquicia> parsearFranquicia(Element item) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearFranquicia()");

		ArrayList<Franquicia> resultado = null;

		try {
			resultado = new ArrayList<Franquicia>();

			NodeList elementos = item.getElementsByTagName("franquicia");

			if (elementos.getLength() > 0) {
				for (int k = 0; k < elementos.getLength(); k++) {
					final Element itemFranq = (Element) elementos.item(k);
					final Franquicia fra = new Franquicia();

					final String nuBien = getParsedValueFromElement(itemFranq, "nu-bien");

					if (nuBien != null) {
						fra.setBienNum(Integer.valueOf(nuBien));
					}

					fra.setBienDesc(getParsedValueFromElement(itemFranq, "desc-bien"));

					final String codRamo = getParsedValueFromElement(itemFranq, "cod-ramo-franquicia");

					if (codRamo != null) {
						fra.setRamoCod(Integer.valueOf(codRamo));
					}

					String codFranquicia = getParsedValueFromElement(itemFranq, "cod-franquicia");
					if (codFranquicia != null) {
						fra.setCodFranquicia(Integer.valueOf(codFranquicia));
					}

					String descFranquicia = getParsedValueFromElement(itemFranq, "desc-franquicia");
					if (descFranquicia != null) {
						fra.setDescripcionFranquicia(descFranquicia);
					}

					final String codCobertura = getParsedValueFromElement(itemFranq, "cod-cobertura");

					if (codCobertura != null) {
						fra.setCoberturaCod(Integer.valueOf(codCobertura));
					}

					fra.setCoberturaDesc(getParsedValueFromElement(itemFranq, "desc-cobertura"));

					final String porcFranquicia = getParsedValueFromElement(itemFranq, "porc-franquicia");

					if (porcFranquicia != null) {
						fra.setFranquiciaPorc(Double.valueOf(porcFranquicia));
					}

					final String montoFranquicia = getParsedValueFromElement(itemFranq, "monto-franquicia");

					if (montoFranquicia != null) {
						fra.setFranquiciaMonto(Double.valueOf(montoFranquicia));
					}

					final String porcDeducible = getParsedValueFromElement(itemFranq, "porc-deducible");

					if (porcDeducible != null) {
						fra.setDeduciblePorc(Double.valueOf(porcDeducible));
					}

					final String montoDeducible = getParsedValueFromElement(itemFranq, "monto-deducible");

					if (montoDeducible != null) {
						fra.setDeducibleMonto(Double.valueOf(montoDeducible));
					}

					resultado.add(fra);
				}
			}
		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		}

		return resultado;
	}

	private ArrayList<Plan> parsearPlan() {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearPlan()");

		ArrayList<Plan> resultado = null;

		try {
			resultado = new ArrayList<Plan>();

			final NodeList elementos = this.doc.getElementsByTagName("plan");

			int cantidad = elementos.getLength();

			for (int i = 0; i < elementos.getLength(); i++) {
				final Element item = (Element) elementos.item(i);
				final Plan obj = new Plan();

				obj.setPlanDesc(getParsedValueFromElement(item, "descripcion"));
				obj.setPlanCod(getParsedValueFromElement(item, "codigo"));

				final String premio = getParsedValueFromElement(item, "premio");

				if (premio == null) {
					logueo.setErrorDatos("El tag premio es null");
					LOG.error(logueo.getErrorDatos());
				} else {
					obj.setPremio(Double.valueOf(getParsedValueFromElement(item, "premio")));
				}

				obj.setSeleccion(getParsedValueFromElement(item, "seleccion"));

				resultado.add(obj);
			}
		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		}

		return resultado;
	}

	private ArrayList<Ahorro> parsearAhorro() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearAhorro()");

		ArrayList<Ahorro> resultado = null;

		try {
			resultado = new ArrayList<Ahorro>();

			NodeList elementos = this.doc.getElementsByTagName("ahorro");

			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				Ahorro objAhorro = new Ahorro();

				objAhorro.setCodProceso(getParsedValueFromElement(item, "cod-proceso"));
				objAhorro.setDescripcionProceso(getParsedValueFromElement(item, "desc-proceso"));

				if (getParsedValueFromElement(item, "monto-pagar") != null) {
					objAhorro.setMontoPagar(Double.valueOf(getParsedValueFromElement(item, "monto-pagar")));
				}
				if (getParsedValueFromElement(item, "monto-aparte") != null) {
					objAhorro.setMontoAporte(Double.valueOf(getParsedValueFromElement(item, "monto-aporte")));
				}
				if (getParsedValueFromElement(item, "cod-plan-pago") != null) {
					objAhorro.setCodPlanPago(Integer.valueOf(getParsedValueFromElement(item, "cod-plan-pago")));
				}
				objAhorro.setDescripcionPlanPago(getParsedValueFromElement(item, "desc-plan-pago"));
				objAhorro.setFechaDesde(getParsedValueFromElement(item, "fe-desde"));
				objAhorro.setFechaHasta(getParsedValueFromElement(item, "fe-hasta"));

				resultado.add(objAhorro);

			}

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		}

		return resultado;
	}

	public ResultCertificados parsearCertificados() {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearCertificados()");

		final ResultCertificados resultado = new ResultCertificados();

		try {
			final NodeList elementos = this.doc.getElementsByTagName("bien");

			for (int i = 0; i < elementos.getLength(); i++) {

				final Element item = (Element) elementos.item(i);
				final BienCert obj = new BienCert();

				if (getParsedValueFromElement(item, "certificado") != null) {
					obj.setCertificado(Integer.valueOf(getParsedValueFromElement(item, "certificado")));
				}
				obj.setCliente(getParsedValueFromElement(item, "cliente"));
				if (getParsedValueFromElement(item, "consec-bien") != null) {
					obj.setConsecutivo(Integer.valueOf(getParsedValueFromElement(item, "consec-bien")));
				}
				obj.setDescripcion(getParsedValueFromElement(item, "descripcion"));
				obj.setSeleccionado(getParsedValueFromElement(item, "st-certificado"));
				obj.setCodPlanCobertura(getParsedValueFromElement(item, "cod-plan-cobertura"));

				resultado.setUnElemento(obj);
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

	public ResultDatoParametrico parsearDatoParametrico() {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearDatoParametrico()");

		final ResultDatoParametrico resultado = new ResultDatoParametrico();

		try {
			final NodeList elementos = this.doc.getElementsByTagName("bien");

			for (int i = 0; i < elementos.getLength(); i++) {

				final Element item = (Element) elementos.item(i);
				final DatoParametrico obj = new DatoParametrico();

				if (getParsedValueFromElement(item, "certificado") != null) {
					obj.setCertificado(Integer.valueOf(getParsedValueFromElement(item, "certificado")));
				}
				if (getParsedValueFromElement(item, "consec-bien") != null) {
					obj.setConsecutivo(Integer.valueOf(getParsedValueFromElement(item, "consec-bien")));
				}
				obj.setAsegurado(getParsedValueFromElement(item, "cliente"));
				obj.setBienDesc(getParsedValueFromElement(item, "descripcion"));
				obj.setDatoDesc(getParsedValueFromElement(item, "desc-valor"));
				obj.setDatoValor(getParsedValueFromElement(item, "valor-dato"));
				obj.setEstado(getParsedValueFromElement(item, "st-certificado"));

				resultado.setUnElemento(obj);
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

	public ResultDatosCertificados parsearDatosCertificados() {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearCoberturas()");

		ResultDatosCertificados retorno = new ResultDatosCertificados();

		try {

			final NodeList elementos = this.doc.getElementsByTagName("bien");

			for (int i = 0; i < elementos.getLength(); i++) {
				final Element item = (Element) elementos.item(i);
				final BienCert obj = new BienCert();

				if (getParsedValueFromElement(item, "certificado") != null) {
					obj.setCertificado(Integer.valueOf(getParsedValueFromElement(item, "certificado")));
				}
				if (getParsedValueFromElement(item, "consec-bien") != null) {
					obj.setConsecutivo(Integer.valueOf(getParsedValueFromElement(item, "consec-bien")));
				}
				if (getParsedValueFromElement(item, "cod-bien") != null) {
					obj.setCodigoBien(Integer.valueOf(getParsedValueFromElement(item, "cod-bien")));
				}
				obj.setDescripcion(getParsedValueFromElement(item, "desc-bien"));
				obj.setFechaBaja(getParsedValueFromElement(item, "fe-baja"));
				if (getParsedValueFromElement(item, "pos-bien") != null) {
					obj.setPosicionBien(Integer.valueOf(getParsedValueFromElement(item, "pos-bien")));
				}
				obj.setHabilitoAnexos(getParsedValueFromElement(item, "boton-anexos"));
				obj.setHabilitoTextos(getParsedValueFromElement(item, "boton-textos"));
				obj.setHabilitoListaBienes(getParsedValueFromElement(item, "boton-lista-bienes"));
				obj.setHabilitoUbicacion(getParsedValueFromElement(item, "boton-ubicacion"));
				obj.setHabilitoBeneficiarios(getParsedValueFromElement(item, "boton-beneficiarios"));
				obj.setHabilitoAcreedores(getParsedValueFromElement(item, "boton-acreedores"));
				obj.setHabilitoNominas(getParsedValueFromElement(item, "boton-nominas"));

				final NodeList datos = item.getElementsByTagName("dato");

				if (datos.getLength() > 0) {
					final ArrayList<DatosBienCert> listaDatos = new ArrayList<DatosBienCert>();

					for (int j = 0; j < datos.getLength(); j++) {
						final Element itemDato = (Element) datos.item(j);
						final DatosBienCert objDato = new DatosBienCert();

						if (getParsedValueFromElement(itemDato, "cod-dato") != null) {
							objDato.setDatoCod(Integer.valueOf(getParsedValueFromElement(itemDato, "cod-dato")));
						}
						// LOG.debug(getParsedValueFromElement(item,
						// "cod-dato"));
						objDato.setDatoDesc(getParsedValueFromElement(itemDato, "desc-dato"));
						// LOG.debug(getParsedValueFromElement(item,
						// "desc-dato"));
						objDato.setValorCod(getParsedValueFromElement(itemDato, "cod-valor"));
						objDato.setValorDesc(getParsedValueFromElement(itemDato, "desc-valor"));
						objDato.setDatoTipo(getParsedValueFromElement(itemDato, "tipo-dato"));
						objDato.setVisibilidad(getParsedValueFromElement(itemDato, "visible"));
						objDato.setRequerido(getParsedValueFromElement(itemDato, "requerido"));
						if (getParsedValueFromElement(itemDato, "cant-decimales") != null) {
							objDato.setDecimales(Integer.valueOf(getParsedValueFromElement(itemDato, "cant-decimales")));
						}

						// LOG.debug(getParsedValueFromElement(itemDato,
						// "cod-tabla"));

						if (getParsedValueFromElement(itemDato, "cod-tabla") != null) {
							objDato.setTabla(Integer.valueOf(getParsedValueFromElement(itemDato, "cod-tabla")));
						}
						if (getParsedValueFromElement(itemDato, "longitud-dato") != null) {
							objDato.setLongitud(Integer.valueOf(getParsedValueFromElement(itemDato, "longitud-dato")));
						}

						listaDatos.add(objDato);
					}

					obj.setDatos(listaDatos);
				}

				retorno.setUnBienCert(obj);
			}
		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, retorno);
		}

		return retorno;
	}

	public ResultObtenerDatosBasicosCotiza parsearObtenerDatosBasicosCotizacion() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerDatosBasicosCotizacion()");
		ResultObtenerDatosBasicosCotiza resultado = new ResultObtenerDatosBasicosCotiza();
		try {

			DatosBasicosCotizacion datosBasicos = new DatosBasicosCotizacion();
			NodeList datosBasicosCot = this.doc.getElementsByTagName("cotizacion");
			for (int i = 0; i < datosBasicosCot.getLength(); i++) {
				Element valItem = (Element) datosBasicosCot.item(i);

				datosBasicos.setFechaEmision(getParsedValueFromElement(valItem, "fe-emision"));
				if (getParsedValueFromElement(valItem, "cod-productor") != null) {
					datosBasicos.setProductorCod(Integer.valueOf(getParsedValueFromElement(valItem, "cod-productor")));
				}
				datosBasicos.setProductorDesc(getParsedValueFromElement(valItem, "desc-productor"));

				// FIXME OIGRES YA ADD BROKER
				if (getParsedValueFromElement(valItem, "cod-broker") != null) {
					datosBasicos.setCodBroker(Integer.valueOf(getParsedValueFromElement(valItem, "cod-broker")));
				}

				datosBasicos.setDescBroker(getParsedValueFromElement(valItem, "desc-broker"));

				if (getParsedValueFromElement(valItem, "cod-moneda") != null) {
					datosBasicos.setMonedaCod(Integer.valueOf(getParsedValueFromElement(valItem, "cod-moneda")));
				}
				datosBasicos.setMonedaDesc(getParsedValueFromElement(valItem, "desc-moneda"));
				datosBasicos.setPromocionCod(getParsedValueFromElement(valItem, "cod-promocion"));
				datosBasicos.setPromocionDesc(getParsedValueFromElement(valItem, "desc-promocion"));
				datosBasicos.setRenovacionCod(getParsedValueFromElement(valItem, "cod-renovacion"));
				datosBasicos.setRenovacionDesc(getParsedValueFromElement(valItem, "desc-renovacion"));
				datosBasicos.setModoCalculoCod(getParsedValueFromElement(valItem, "cod-tp-calculo"));
				datosBasicos.setModoCalculoDesc(getParsedValueFromElement(valItem, "desc-tp-calculo"));

				resultado.setDatosBasicos(datosBasicos);
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

	public ResultObtenerUbicacionBien parsearObtenerUbicacionBien() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerUbicacionBien()");

		ResultObtenerUbicacionBien resultado = new ResultObtenerUbicacionBien();
		DatosUbicacionBien datosUbicacion = new DatosUbicacionBien();
		try {
			NodeList bien = this.doc.getElementsByTagName("bien");
			for (int i = 0; i < bien.getLength(); i++) {
				BienCert datoBien = new BienCert();
				Element valItem = (Element) bien.item(i);

				if (getParsedValueFromElement(valItem, "certificado") != null) {
					datoBien.setCertificado(Integer.valueOf(getParsedValueFromElement(valItem, "certificado")));
				}
				if (getParsedValueFromElement(valItem, "consec-bien") != null) {
					datoBien.setConsecutivo(Integer.valueOf(getParsedValueFromElement(valItem, "consec-bien")));
				}
				if (getParsedValueFromElement(valItem, "cod-bien") != null) {
					datoBien.setCodigoBien(Integer.valueOf(getParsedValueFromElement(valItem, "cod-bien")));
				}
				datoBien.setDescripcion(getParsedValueFromElement(valItem, "desc-bien"));
				datoBien.setFechaBaja(getParsedValueFromElement(valItem, "fe-baja"));

				if (getParsedValueFromElement(valItem, "pos-bien") != null) {
					datoBien.setPosicionBien(Integer.valueOf(getParsedValueFromElement(valItem, "pos-bien")));
				}
				datosUbicacion.setDatosBien(datoBien);

				NodeList ubicacion = this.doc.getElementsByTagName("ubicacion");

				for (int j = 0; j < ubicacion.getLength(); j++) {
					Ubicacion ub = new Ubicacion();
					Element ubItem = (Element) ubicacion.item(j);

					ub.setCalle(getParsedValueFromElement(ubItem, "desc-calle"));
					ub.setDepartamento(getParsedValueFromElement(ubItem, "desc-departamento"));
					ub.setDescripRadio(getParsedValueFromElement(ubItem, "desc-localidad"));
					ub.setLocalidad(getParsedValueFromElement(ubItem, "desc-municipio"));

					if (getParsedValueFromElement(ubItem, "cod-calle") != null) {
						ub.setCodCalle(Integer.valueOf(getParsedValueFromElement(ubItem, "cod-calle")));
					}
					if (getParsedValueFromElement(ubItem, "cod-postal") != null) {
						ub.setRadio(Integer.valueOf(getParsedValueFromElement(ubItem, "cod-postal")));
					}
					if (getParsedValueFromElement(ubItem, "nu-direccion") != null) {
						ub.setNumDireccion(Integer.valueOf(getParsedValueFromElement(ubItem, "nu-direccion")));
					}
					ub.setNumero(getParsedValueFromElement(ubItem, "nu-calle"));
					ub.setPiso(getParsedValueFromElement(ubItem, "nu-piso"));
					ub.setNumDpto(getParsedValueFromElement(ubItem, "desc-departamento"));
					ub.setTelefono(getParsedValueFromElement(ubItem, "telefono"));
					ub.setFax(getParsedValueFromElement(ubItem, "aclaracion-dir"));
					if (getParsedValueFromElement(ubItem, "nu-postal") != null) {
						ub.setNumPostal(Integer.valueOf(getParsedValueFromElement(ubItem, "nu-postal")));
					}

					datosUbicacion.setDatosUbicacion(ub);

				}
			}

			resultado.setDatosUbicacionBien(datosUbicacion);
		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;

	}

	public ResultObtenerBeneficiarios parsearObtenerBeneficiarios() {

		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerBeneficiarios()");

		ResultObtenerBeneficiarios resultado = new ResultObtenerBeneficiarios();
		BienCert datoBien = new BienCert();

		try {

			NodeList bien = this.doc.getElementsByTagName("bien");
			for (int i = 0; i < bien.getLength(); i++) {

				Element valItem = (Element) bien.item(i);
				if (getParsedValueFromElement(valItem, "certificado") != null) {
					datoBien.setCertificado(Integer.valueOf(getParsedValueFromElement(valItem, "certificado")));
				}
				if (getParsedValueFromElement(valItem, "consec-bien") != null) {
					datoBien.setConsecutivo(Integer.valueOf(getParsedValueFromElement(valItem, "consec-bien")));
				}
				if (getParsedValueFromElement(valItem, "cod-bien") != null) {
					datoBien.setCodigoBien(Integer.valueOf(getParsedValueFromElement(valItem, "cod-bien")));
				}
				datoBien.setDescripcion(getParsedValueFromElement(valItem, "desc-bien"));
				datoBien.setFechaBaja(getParsedValueFromElement(valItem, "fe-baja"));

				if (getParsedValueFromElement(valItem, "pos-bien") != null) {
					datoBien.setPosicionBien(Integer.valueOf(getParsedValueFromElement(valItem, "pos-bien")));
				}
			}

			resultado.setBien(datoBien);

			NodeList infoBeneficiarios = this.doc.getElementsByTagName("beneficiario");

			for (int i = 0; i < infoBeneficiarios.getLength(); i++) {
				Beneficiario dBeneficiarios = new Beneficiario();
				Element Item = (Element) infoBeneficiarios.item(i);

				dBeneficiarios.setTipoDocumento(getParsedValueFromElement(Item, "tp-documento"));
				dBeneficiarios.setNumDocumento(getParsedValueFromElement(Item, "nu-documento"));
				dBeneficiarios.setNombreBeneficiario(getParsedValueFromElement(Item, "nombres"));
				dBeneficiarios.setCodParentesco(getParsedValueFromElement(Item, "cod-parentesco"));
				dBeneficiarios.setDescripcionParentesco(getParsedValueFromElement(Item, "desc-parentesco"));
				if (getParsedValueFromElement(Item, "po-participacion") != null) {
					dBeneficiarios.setPorcentajeParticipacion(Double.valueOf(getParsedValueFromElement(Item, "po-participacion")));
				}
				String fechaInclusion = getParsedValueFromElement(Item, "fe_inclusion");
				String fechaExclusion = getParsedValueFromElement(Item, "fe_exclusion");
				String fechaNacimiento = getParsedValueFromElement(Item, "fe-nacimiento");
				if (fechaInclusion != null) {
					dBeneficiarios.setFechaInclusion(fechaInclusion);
				}
				if (fechaNacimiento != null) {
					dBeneficiarios.setFechaNacimiento(fechaNacimiento);
				}

				if (fechaExclusion != null) {
					dBeneficiarios.setFechaExclusion(fechaExclusion);
				}
				dBeneficiarios.setObservaciones(getParsedValueFromElement(Item, "observaciones"));

				resultado.setUnElemento(dBeneficiarios);
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

	public ResultObtenerListaBienes parsearObtenerListaBienes() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerListaBienes()");

		ResultObtenerListaBienes resultado = new ResultObtenerListaBienes();
		try {
			final NodeList bienes = this.doc.getElementsByTagName("bien");

			for (int i = 0; i < bienes.getLength(); i++) {
				Element bienCert = (Element) bienes.item(i);
				resultado.setBien(this.parsearBienCert(bienCert));
				NodeList listaObjetos = this.doc.getElementsByTagName("lista-bien");

				for (int j = 0; j < listaObjetos.getLength(); j++) {
					ObjetoBien objBien = new ObjetoBien();
					Element objeto = (Element) listaObjetos.item(j);
					if (getParsedValueFromElement(objeto, "cod-marca") != null) {
						objBien.setCodMarca(Integer.valueOf(getParsedValueFromElement(objeto, "cod-marca")));
					}
					if (getParsedValueFromElement(objeto, "cod-objeto") != null) {
						objBien.setCodObjeto(Integer.valueOf(getParsedValueFromElement(objeto, "cod-objeto")));
					}
					objBien.setDescripcion(getParsedValueFromElement(objeto, "descripcion"));
					objBien.setDescripcionMarca(getParsedValueFromElement(objeto, "desc-marca"));
					objBien.setDescripcionObjeto(getParsedValueFromElement(objeto, "desc-objeto"));
					if (getParsedValueFromElement(objeto, "nu-ordinal") != null) {
						objBien.setNumOrdinal(Integer.valueOf(getParsedValueFromElement(objeto, "nu-ordinal")));
					}

					if (getParsedValueFromElement(objeto, "nu-unidades") != null) {
						objBien.setNumUnidades(Integer.valueOf(getParsedValueFromElement(objeto, "nu-unidades")));
					}
					objBien.setSerial(getParsedValueFromElement(objeto, "serial"));
					if (getParsedValueFromElement(objeto, "valor") != null) {
						objBien.setValor(Double.valueOf(getParsedValueFromElement(objeto, "valor")));
					}
					if (getParsedValueFromElement(objeto, "valor-total") != null) {
						objBien.setValorTotal(Double.valueOf(getParsedValueFromElement(objeto, "valor-total")));
					}
					resultado.setUnElemento(objBien);
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

	public ResultObtenerAnexosCotizacion parsearObtenerAnexosCotizacion() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerAnexosCotizacion()");

		ResultObtenerAnexosCotizacion resultado = new ResultObtenerAnexosCotizacion();
		try {
			NodeList anexos = this.doc.getElementsByTagName("linea-anexo");

			for (int i = 0; i < anexos.getLength(); i++) {
				Anexo anexo = new Anexo();
				Element anexoItem = (Element) anexos.item(i);

				anexo.setCodigo(Integer.valueOf(getParsedValueFromElement(anexoItem, "cod-linea")));
				anexo.setDescripcion(getParsedValueFromElement(anexoItem, "desc-linea"));

				NodeList clausulas = this.doc.getElementsByTagName("clausula");
				for (int j = 0; j < clausulas.getLength(); j++) {
					Clausula clausula = new Clausula();
					Element clausulaItem = (Element) clausulas.item(j);

					if (clausulaItem != null && getParsedValueFromElement(clausulaItem, "cod-clausula") != null) {
						String codClausula = getParsedValueFromElement(clausulaItem, "cod-clausula");
						if (getParsedValueFromElement(clausulaItem, "cod-ramo") != null) {
							Integer codRamo = Integer.valueOf(getParsedValueFromElement(clausulaItem, "cod-ramo"));
							clausula.setCodRamo(codRamo);
						}
						String descripcionClausula = getParsedValueFromElement(clausulaItem, "desc-clausula");
						String impresion = getParsedValueFromElement(clausulaItem, "in-impresion");
						String modifica = getParsedValueFromElement(clausulaItem, "in-exclusion");

						clausula.setCodClausula(codClausula);

						clausula.setDescripcionClausula(descripcionClausula);
						clausula.setImpresion(impresion);
						clausula.setModifica(modifica);
					}
					anexo.setUnaClausula(clausula);

				}
				resultado.setUnAnexo(anexo);

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

	public ResultObtenerComponentes parsearObtenerComponentes() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerComponentes()");

		ResultObtenerComponentes resultado = new ResultObtenerComponentes();
		try {
			NodeList componentes = this.doc.getElementsByTagName("componente");

			for (int i = 0; i < componentes.getLength(); i++) {
				Componente componente = new Componente();
				final Element compItem = (Element) componentes.item(i);

				componente.setCodigo(getParsedValueFromElement(compItem, "cod-componente"));
				componente.setDescripcion(getParsedValueFromElement(compItem, "desc-componente"));
				String monto = getParsedValueFromElement(compItem, "importe");
				if (monto != null && !monto.equals("")) {
					componente.setMonto(Double.valueOf(monto));
				}
				resultado.setUnElemento(componente);
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

	public ResultObtenerFranquicias parsearObtenerFranquicias() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerFranquicias()");

		ResultObtenerFranquicias resultado = new ResultObtenerFranquicias();
		try {
			NodeList infoFranqBien = this.doc.getElementsByTagName("bien");
			for (int i = 0; i < infoFranqBien.getLength(); i++) {
				InfoBienFranquicia franquiciaBien = new InfoBienFranquicia();
				Element bienItem = (Element) infoFranqBien.item(i);

				if (getParsedValueFromElement(bienItem, "certificado") != null) {
					franquiciaBien.setCertificado(Integer.valueOf(getParsedValueFromElement(bienItem, "certificado")));
				}
				if (getParsedValueFromElement(bienItem, "consec-bien") != null) {
					franquiciaBien.setConsecutivo(Integer.valueOf(getParsedValueFromElement(bienItem, "consec-bien")));
				}
				if (getParsedValueFromElement(bienItem, "cod-bien") != null) {
					franquiciaBien.setCodigoBien(Integer.valueOf(getParsedValueFromElement(bienItem, "cod-bien")));
				}
				franquiciaBien.setDescripcion(getParsedValueFromElement(bienItem, "desc-bien"));
				franquiciaBien.setFechaBaja(getParsedValueFromElement(bienItem, "fe-baja"));
				if (getParsedValueFromElement(bienItem, "pos-bien") != null) {
					franquiciaBien.setPosicionBien(Integer.valueOf(getParsedValueFromElement(bienItem, "pos-bien")));
				}

				NodeList tagFranquicia = bienItem.getElementsByTagName("franquicia");
				for (int j = 0; j < tagFranquicia.getLength(); j++) {
					Franquicia franquicia = new Franquicia();
					Element franquiciaItem = (Element) tagFranquicia.item(j);

					if (getParsedValueFromElement(franquiciaItem, "cod-ramo-franquicia") != null) {
						franquicia.setRamoCod(Integer.valueOf(getParsedValueFromElement(franquiciaItem, "cod-ramo-franquicia")));
					}
					if (getParsedValueFromElement(franquiciaItem, "cod-franquicia") != null) {
						franquicia.setCodFranquicia(Integer.valueOf(getParsedValueFromElement(franquiciaItem, "cod-franquicia")));
					}
					franquicia.setDescripcionFranquicia(getParsedValueFromElement(franquiciaItem, "desc-franquicia"));
					if (getParsedValueFromElement(franquiciaItem, "porc-franquicia") != null) {
						franquicia.setFranquiciaPorc(Double.valueOf(getParsedValueFromElement(franquiciaItem, "porc-franquicia")));
					}
					if (getParsedValueFromElement(franquiciaItem, "monto-franquicia") != null) {
						franquicia.setFranquiciaMonto(Double.valueOf(getParsedValueFromElement(franquiciaItem, "monto-franquicia")));
					}
					if (getParsedValueFromElement(franquiciaItem, "porc-deducible") != null) {
						franquicia.setDeduciblePorc(Double.valueOf(getParsedValueFromElement(franquiciaItem, "porc-deducible")));
					}
					if (getParsedValueFromElement(franquiciaItem, "monto-deducible") != null) {
						franquicia.setDeducibleMonto(Double.valueOf(getParsedValueFromElement(franquiciaItem, "monto-deducible")));
					}

					franquiciaBien.setUnaFranquicia(franquicia);

				}
				resultado.setUnElemento(franquiciaBien);
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

	public ResultTiposNotaGenerica parsearListaGenericaXTiposNota() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearListaGenericaXTiposNota()");

		ResultTiposNotaGenerica resultado = new ResultTiposNotaGenerica();
		try {
			NodeList elemento = this.doc.getElementsByTagName("elemento");

			for (int i = 0; i < elemento.getLength(); i++) {

				Element tipoNotaItem = (Element) elemento.item(i);
				TiposNotaGenerica tiposNota = new TiposNotaGenerica();
				tiposNota.setCodigo(getParsedValueFromElement(tipoNotaItem, "codigo"));
				tiposNota.setDescripcion(getParsedValueFromElement(tipoNotaItem, "descripcion"));

				resultado.setUnTipoNotaGenerica(tiposNota);
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

	public ResultObtenerNotasPoliza parsearObtenerNotasPoliza() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerNotasPoliza()");

		ResultObtenerNotasPoliza resultado = new ResultObtenerNotasPoliza();
		try {
			NodeList notasPoliza = this.doc.getElementsByTagName("nota");

			for (int i = 0; i < notasPoliza.getLength(); i++) {
				Element valItem = (Element) notasPoliza.item(i);
				Nota notaPoliza = new Nota();

				notaPoliza.setEnlace(getParsedValueFromElement(valItem, "enlace"));
				if (getParsedValueFromElement(valItem, "nu-nota") != null) {
					notaPoliza.setNumNota(Integer.valueOf(getParsedValueFromElement(valItem, "nu-nota")));
				}
				notaPoliza.setTipoNota(getParsedValueFromElement(valItem, "cod-nota"));
				notaPoliza.setDescripNota(getParsedValueFromElement(valItem, "des-nota"));
				notaPoliza.setFechaActualizacion(getParsedValueFromElement(valItem, "fe-actualizacion"));
				notaPoliza.setObservacion(getParsedValueFromElement(valItem, "observacion"));
				notaPoliza.setUsuario(getParsedValueFromElement(valItem, "usuario"));
				if (getParsedValueFromElement(valItem, "cod-st-nota") != null) {
					notaPoliza.setCodEstadoNota(Integer.valueOf(getParsedValueFromElement(valItem, "cod-st-nota")));
				}
				notaPoliza.setDescripEstadoNota(getParsedValueFromElement(valItem, "des-st-nota"));
				notaPoliza.setNumComprobante(getParsedValueFromElement(valItem, "nu-comprobante"));

				NodeList elementos = valItem.getElementsByTagName("parametros-list");
				Element itemParametros = (Element) elementos.item(0);
				NodeList parametrosNota = itemParametros.getElementsByTagName("parametro");
				ArrayList<ParametroNotaPoliza> parametros = new ArrayList<ParametroNotaPoliza>();
				for (int j = 0; j < parametrosNota.getLength(); j++) {
					Element itemParam = (Element) parametrosNota.item(j);
					ParametroNotaPoliza paramNota = new ParametroNotaPoliza();

					if (getParsedValueFromElement(itemParam, "codigo") != null) {
						paramNota.setCodigo(getParsedValueFromElement(itemParam, "codigo"));
					}
					paramNota.setDescripcion(getParsedValueFromElement(itemParam, "descripcion"));
					paramNota.setEtiqueta(getParsedValueFromElement(itemParam, "etiqueta"));

					parametros.add(paramNota);
				}
				notaPoliza.setParametrosNotas(parametros);
				resultado.SetUnaNotaRenovacion(notaPoliza);

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

	public ResultObtenerDatosRenovacion parsearObtenerDatosRenovacion() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerDatosRenovacion()");

		ResultObtenerDatosRenovacion resultado = new ResultObtenerDatosRenovacion();
		try {
			NodeList dCotizacion = this.doc.getElementsByTagName("cotizacion-renov");
			DatosCotizacion datosCotizacion = new DatosCotizacion();
			Element valItem = (Element) dCotizacion.item(0);
			resultado.setTieneNotas(getParsedValueFromElement(valItem, "tiene-notas"));

			if (getParsedValueFromElement(valItem, "cod-ramo") != null) {
				datosCotizacion.setCodRamo(Integer.valueOf(getParsedValueFromElement(valItem, "cod-ramo")));
			}

			if (getParsedValueFromElement(valItem, "nu-poliza") != null) {
				datosCotizacion.setNumPoliza(Integer.valueOf(getParsedValueFromElement(valItem, "nu-poliza")));
			}

			if (getParsedValueFromElement(valItem, "cod-productor") != null) {
				datosCotizacion.setProductorCod(Integer.valueOf(getParsedValueFromElement(valItem, "cod-productor")));
			}

			datosCotizacion.setProductorDesc(getParsedValueFromElement(valItem, "desc-productor"));
			// FIXME OIGRES YA ADD BROKER
			if (getParsedValueFromElement(valItem, "cod-broker") != null) {
				datosCotizacion.setCodBroker(Integer.valueOf(getParsedValueFromElement(valItem, "cod-broker")));
			}

			datosCotizacion.setDescBroker(getParsedValueFromElement(valItem, "desc-broker"));

			if (getParsedValueFromElement(valItem, "cod-moneda") != null) {
				datosCotizacion.setMonedaCod(Integer.valueOf(getParsedValueFromElement(valItem, "cod-moneda")));
			}

			if (getParsedValueFromElement(valItem, "nu-sucursal") != null) {
				datosCotizacion.setSucursal(Integer.valueOf(getParsedValueFromElement(valItem, "nu-sucursal")));
			}

			datosCotizacion.setMonedaDesc(getParsedValueFromElement(valItem, "desc-moneda"));
			datosCotizacion.setPromocionCod(getParsedValueFromElement(valItem, "cod-promocion"));
			datosCotizacion.setPromocionDesc(getParsedValueFromElement(valItem, "desc-promocion"));
			datosCotizacion.setRenovacionCod(getParsedValueFromElement(valItem, "cod-renovacion"));
			datosCotizacion.setRenovacionDesc(getParsedValueFromElement(valItem, "desc-renovacion"));
			datosCotizacion.setCodTipoCalculo(getParsedValueFromElement(valItem, "cod-tp-calculo"));
			datosCotizacion.setDescTipoCalculo(getParsedValueFromElement(valItem, "desc-tp-calculo"));
			datosCotizacion.setVigenciaCod(getParsedValueFromElement(valItem, "cod-fr-pago"));
			datosCotizacion.setVigenciaDesc(getParsedValueFromElement(valItem, "desc-fr-pago"));
			datosCotizacion.setFrecTecnicaCod(getParsedValueFromElement(valItem, "cod-vig-tecnica"));
			datosCotizacion.setFrecTecnicaDesc(getParsedValueFromElement(valItem, "desc-vig-tecnica"));

			if (getParsedValueFromElement(valItem, "cod-plan-pago") != null) {
				datosCotizacion.setPlanPagoCod(getParsedValueFromElement(valItem, "cod-plan-pago"));
			}
			datosCotizacion.setPlanPagoDesc(getParsedValueFromElement(valItem, "desc-plan-pago"));

			if (getParsedValueFromElement(valItem, "cod-medio-pago") != null) {
				datosCotizacion.setMedioPagoCod(Integer.valueOf(getParsedValueFromElement(valItem, "cod-medio-pago")));
			}
			datosCotizacion.setMedioPagoDesc(getParsedValueFromElement(valItem, "desc-medio-pago"));
			datosCotizacion.setOrigenCod(getParsedValueFromElement(valItem, "cod-origen"));
			datosCotizacion.setOrigenDesc(getParsedValueFromElement(valItem, "desc-origen"));

			if (getParsedValueFromElement(valItem, "cod-banco") != null) {
				datosCotizacion.setCodBanco(Integer.valueOf(getParsedValueFromElement(valItem, "cod-banco")));
			}

			datosCotizacion.setDescBanco(getParsedValueFromElement(valItem, "desc-banco"));
			datosCotizacion.setVigenciaDesde(getParsedValueFromElement(valItem, "fe-desde"));
			datosCotizacion.setVigenciaHasta(getParsedValueFromElement(valItem, "fe-hasta"));
			datosCotizacion.setVigenciaTecnicaDesde(getParsedValueFromElement(valItem, "fe-desde-tecnica"));
			datosCotizacion.setVigenciaTecnicaHasta(getParsedValueFromElement(valItem, "fe-hasta-tecnica"));
			datosCotizacion.setDescRamo(getParsedValueFromElement(valItem, "desc-ramo"));
			datosCotizacion.setDescProducto(getParsedValueFromElement(valItem, "desc-producto"));
			datosCotizacion.setCodProducto(getParsedValueFromElement(valItem, "cod-producto"));
			datosCotizacion.setFacturacionCod(getParsedValueFromElement(valItem, "cod-tp-facturacion"));
			datosCotizacion.setFacturacionDesc(getParsedValueFromElement(valItem, "desc-tp-facturacion"));
			datosCotizacion.setCodTipoCalculo(getParsedValueFromElement(valItem, "cod-tp-calculo"));
			datosCotizacion.setDescTipoCalculo(getParsedValueFromElement(valItem, "desc-tp-calculo"));
			datosCotizacion.setFechaEmision(getParsedValueFromElement(valItem, "fe-emision"));

			resultado.setDatosCotizacion(datosCotizacion);

			NodeList dContratante = this.doc.getElementsByTagName("contratante");
			DatosContratante datosContratante = new DatosContratante();
			Element valor = (Element) dContratante.item(0);
			datosContratante.setNacionalidadCod(getParsedValueFromElement(valor, "cod-nacionalidad"));

			if (getParsedValueFromElement(valor, "nu-cedula-rif") != null) {
				datosContratante.setClienteCod(Integer.valueOf(getParsedValueFromElement(valor, "nu-cedula-rif")));
			}

			if (getParsedValueFromElement(valor, "nu-persona") != null) {
				datosContratante.setNumPersona(Integer.valueOf(getParsedValueFromElement(valor, "nu-persona")));
			}

			datosContratante.setClienteDesc(getParsedValueFromElement(valor, "nombre-persona"));

			resultado.setDatosContratante(datosContratante);

			NodeList dConfirmacion = this.doc.getElementsByTagName("confirmacion");
			ValidacionGenerica datosValidacion = null;
			if (dConfirmacion.getLength() > 0) {
				datosValidacion = new ValidacionGenerica();
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
			}

			if (datosValidacion == null) {
				resultado.setConfirmacion(null);
			} else {
				resultado.setConfirmacion(datosValidacion);
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

	public ResultObtenerPlanesPago parsearObtenerPlanesPago() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerPlanesPago()");

		ResultObtenerPlanesPago resultado = new ResultObtenerPlanesPago();
		try {
			NodeList pPago = this.doc.getElementsByTagName("planes-pago");

			for (int i = 0; i < pPago.getLength(); i++) {
				PlanPago planPago = new PlanPago();
				Element valor = (Element) pPago.item(i);

				planPago.setCodigo(getParsedValueFromElement(valor, "cod-plan-pago"));

				planPago.setFormaPago(getParsedValueFromElement(valor, "desc-plan-pago"));

				resultado.setUnPlanPago(planPago);

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

	public ResultDatosModificacion parsearDatosModificacion() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearDatosModificacion()");

		ResultDatosModificacion resultado = new ResultDatosModificacion();

		try {

			NodeList elemCoti = this.doc.getElementsByTagName("datos-basicos");
			for (int i = 0; i < elemCoti.getLength(); i++) {
				Element itemCoti = (Element) elemCoti.item(i);
				DatosCotizacion coti = new DatosCotizacion();

				if (getParsedValueFromElement(itemCoti, "nu-cotizacion") != null) {
					coti.setNumCotizacion(Integer.valueOf(getParsedValueFromElement(itemCoti, "nu-cotizacion")));
				}
				if (getParsedValueFromElement(itemCoti, "nu-poliza") != null) {
					coti.setNumPoliza(Integer.valueOf(getParsedValueFromElement(itemCoti, "nu-poliza")));
				}
				if (getParsedValueFromElement(itemCoti, "nu-certificado") != null) {
					coti.setNumCertificado(Integer.valueOf(getParsedValueFromElement(itemCoti, "nu-certificado")));
				}
				if (getParsedValueFromElement(itemCoti, "cod-motivo") != null) {
					coti.setCodMotivo(Integer.valueOf(getParsedValueFromElement(itemCoti, "cod-motivo")));
				}

				if (getParsedValueFromElement(itemCoti, "nu-sucursal") != null) {
					coti.setSucursal(Integer.valueOf(getParsedValueFromElement(itemCoti, "nu-sucursal")));
				}

				coti.setFechaEmision(getParsedValueFromElement(itemCoti, "fe-cotizacion"));
				if (getParsedValueFromElement(itemCoti, "cod-productor") != null) {
					coti.setProductorCod(Integer.valueOf(getParsedValueFromElement(itemCoti, "cod-productor")));
				}
				coti.setProductorDesc(getParsedValueFromElement(itemCoti, "desc-productor"));

				
				if (getParsedValueFromElement(itemCoti, "cod-broker") != null) {
					coti.setCodBroker(Integer.valueOf(getParsedValueFromElement(itemCoti, "cod-broker")));
				}
				

				coti.setDescBroker(getParsedValueFromElement(itemCoti, "desc-broker"));
				
				
				
				if (getParsedValueFromElement(itemCoti, "cod-nivel-broker") != null) {
					coti.setNivelCodBroker(Integer.valueOf(getParsedValueFromElement(itemCoti, "cod-nivel-broker")));
				}
				
				
				coti.setNivelDescBroker(getParsedValueFromElement(itemCoti, "desc-nivel-broker"));

				if (getParsedValueFromElement(itemCoti, "cod-moneda") != null) {
					coti.setMonedaCod(Integer.valueOf(getParsedValueFromElement(itemCoti, "cod-moneda")));
				}
				coti.setMonedaDesc(getParsedValueFromElement(itemCoti, "desc-moneda"));
				if (getParsedValueFromElement(itemCoti, "cod-medio-pago") != null) {
					coti.setMedioPagoCod(Integer.valueOf(getParsedValueFromElement(itemCoti, "cod-medio-pago")));
				}
				coti.setMedioPagoDesc(getParsedValueFromElement(itemCoti, "desc-medio-pago"));
				coti.setOrigenCod(getParsedValueFromElement(itemCoti, "cod-origen"));
				coti.setOrigenDesc(getParsedValueFromElement(itemCoti, "desc-origen"));
				coti.setPromocionCod(getParsedValueFromElement(itemCoti, "cod-promocion"));
				coti.setPromocionDesc(getParsedValueFromElement(itemCoti, "desc-promocion"));
				coti.setRenovacionCod(getParsedValueFromElement(itemCoti, "cod-renovacion"));
				coti.setRenovacionDesc(getParsedValueFromElement(itemCoti, "desc-renovacion"));
				coti.setModoCalculoCod(getParsedValueFromElement(itemCoti, "cod-tp-calculo"));
				coti.setModoCalculoDesc(getParsedValueFromElement(itemCoti, "desc-tp-calculo"));
				coti.setVigenciaCod(getParsedValueFromElement(itemCoti, "cod-fr-pago"));
				coti.setVigenciaDesc(getParsedValueFromElement(itemCoti, "desc-fr-pago"));
				coti.setVigenciaDesde(getParsedValueFromElement(itemCoti, "fe-desde"));
				coti.setVigenciaHasta(getParsedValueFromElement(itemCoti, "fe-hasta"));
				coti.setVigenciaTecnicaDesde(getParsedValueFromElement(itemCoti, "fe-desde-tecnica"));
				coti.setVigenciaTecnicaHasta(getParsedValueFromElement(itemCoti, "fe-hasta-tecnica"));
				coti.setOrigenEndoso(getParsedValueFromElement(itemCoti, "cod-origen-endoso"));
				coti.setOrigenEndosoDesc(getParsedValueFromElement(itemCoti, "desc-origen-endoso"));
				coti.setFrecTecnicaCod(getParsedValueFromElement(itemCoti, "cod-vig-tecnica"));
				coti.setFrecTecnicaDesc(getParsedValueFromElement(itemCoti, "desc-vig-tecnica"));
				coti.setEstado(getParsedValueFromElement(itemCoti, "estado"));
				coti.setUsuarioCod(getParsedValueFromElement(itemCoti, "cod-usuario"));
				coti.setUsuarioDesc(getParsedValueFromElement(itemCoti, "desc-usuario"));
				
				coti.setEsUnipersonal(getParsedValueFromElement(itemCoti, "es-unipersonal"));
				coti.setEnviarFacturaEmail(getParsedValueFromElement(itemCoti, "envio-fact-mail"));
				coti.setFacturaConRut(getParsedValueFromElement(itemCoti, "doc-defecto"));

				if (getParsedValueFromElement(itemCoti, "cod-banco") != null) {
					coti.setCodBanco(Integer.valueOf(getParsedValueFromElement(itemCoti, "cod-banco")));
				}

				if (getParsedValueFromElement(itemCoti, "nu-dom-bancario") != null) {
					coti.setNuDomBancario(Integer.valueOf(getParsedValueFromElement(itemCoti, "nu-dom-bancario")));
				}

				coti.setDescBanco(getParsedValueFromElement(itemCoti, "desc-banco"));

				coti.setNumTarjeta(getSeudoTarjeta(getParsedValueFromElement(itemCoti, "nu-tarjeta")));

				if (getParsedValueFromElement(itemCoti, "cod-nivel") != null) {
					coti.setNivelCod(Integer.valueOf(getParsedValueFromElement(itemCoti, "cod-nivel")));
				}

				coti.setNivelDesc(getParsedValueFromElement(itemCoti, "desc-nivel"));

				coti.setPlanPagoCod(getParsedValueFromElement(itemCoti, "cod-plan-pago"));

				coti.setPlanPagoDesc(getParsedValueFromElement(itemCoti, "desc-plan-pago"));

				if (getParsedValueFromElement(itemCoti, "cod-tp-facturacion") != null) {
					coti.setFacturacionCod(getParsedValueFromElement(itemCoti, "cod-tp-facturacion"));
				}
				coti.setFacturacionDesc(getParsedValueFromElement(itemCoti, "desc-tp-facturacion"));

				if (getParsedValueFromElement(itemCoti, "premio-informado") != null) {
					coti.setPremioInformado(Double.valueOf(getParsedValueFromElement(itemCoti, "premio-informado")));
				}

				coti.setDescProducto(getParsedValueFromElement(itemCoti, "desc-producto"));
				coti.setDescRamo(getParsedValueFromElement(itemCoti, "desc-ramo"));
				coti.setCodProducto(getParsedValueFromElement(itemCoti, "cod-producto"));

				coti.setCodTipo(getParsedValueFromElement(itemCoti, "cod-tp-transac"));
				coti.setDescTipo(getParsedValueFromElement(itemCoti, "desc-tp-transac"));

				if (getParsedValueFromElement(itemCoti, "cod-ramo") != null) {
					coti.setCodRamo(Integer.valueOf(getParsedValueFromElement(itemCoti, "cod-ramo")));
				}

				coti.setHabilitoVigenciaTecnica(getParsedValueFromElement(itemCoti, "habilito-vigencia-tecnica"));
				if(getParsedValueFromElement(itemCoti, "nu-dire-envio")!=null){
					coti.setNumDireEnvio(Integer.valueOf(getParsedValueFromElement(itemCoti, "nu-dire-envio")));
				}
				if(getParsedValueFromElement(itemCoti, "nu-dire-cobro")!=null){
					coti.setNumDireCobro(Integer.valueOf(getParsedValueFromElement(itemCoti, "nu-dire-cobro"))); 
				}
				resultado.setCotizacion(coti);

			}

			NodeList elemClie = this.doc.getElementsByTagName("cliente");
			Element itemClie = (Element) elemClie.item(0);
			
			
			NodeList clieDatos = itemClie.getElementsByTagName("datos-basicos-cliente");
			Element item = (Element) clieDatos.item(0);

			Cliente cli = new Cliente();
			cli.setValidaComunicaciones(getParsedValueFromElement(itemClie, "validacion-comunicaciones"));
			cli.setTieneDeuda(getParsedValueFromElement(item, "tiene-deuda"));
			cli.setNoTieneDocumento(getParsedValueFromElement(item, "no-tiene-documento"));
			
			
			ComunicacionEC cel = new ComunicacionEC();
			ComunicacionEC tel = new ComunicacionEC();
			ComunicacionEC mail = new ComunicacionEC();
			ComunicacionEC mailFacturas= new ComunicacionEC();

			if (getParsedValueFromElement(item, "nu-persona") != null) {
				cli.setNumPersona(getParsedValueFromElement(item, "nu-persona"));
			}

			cli.setTipoDoc(getParsedValueFromElement(item, "cod-tp-documento"));
			cli.setDescripTipoDoc(getParsedValueFromElement(item, "desc-tp-documento"));
			cli.setNumDoc(getParsedValueFromElement(item, "nu-documento"));
			cli.setRut(getParsedValueFromElement(item, "rut"));
			cli.setNombre(getParsedValueFromElement(item, "nombre-persona"));
			cli.setApellidoRazon(getParsedValueFromElement(item, "apellido-persona"));
			if (getParsedValueFromElement(item, "cod-profesion") != null) {
				cli.setCodProfesion(Integer.valueOf(getParsedValueFromElement(item, "cod-profesion")));
			}
			cli.setProfesion(getParsedValueFromElement(item, "desc-profesion"));
			cli.setCodCliente(getParsedValueFromElement(item, "nu-cedula-rif"));
			cli.setEsPep(getParsedValueFromElement(item, "es-pep"));
			cli.setFechaPep(getParsedValueFromElement(item, "fe-pep"));
			if (getParsedValueFromElement(item, "nu-cons-telefono") != null && !getParsedValueFromElement(item, "nu-cons-telefono").equals("-1")) {
				tel.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(item, "nu-cons-telefono")));
				if (!" ".equals(getParsedValueFromElement(item, "telefono"))) {
					tel.setValorComunicacion(getParsedValueFromElement(item, "telefono"));
				}
				cli.setTel(tel);
			}
			if (getParsedValueFromElement(item, "nu-cons-celular") != null && !getParsedValueFromElement(item, "nu-cons-celular").equals("-1")) {
				cel.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(item, "nu-cons-celular")));
				if (!" ".equals(getParsedValueFromElement(item, "celular"))) {
					cel.setValorComunicacion(getParsedValueFromElement(item, "celular"));
				}
				cli.setCel(cel);
			}
			if (getParsedValueFromElement(item, "nu-cons-mail") != null && !getParsedValueFromElement(item, "nu-cons-mail").equals("-1")) {
				mail.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(item, "nu-cons-mail")));
				if (getParsedValueFromElement(item, "mail") != null && !getParsedValueFromElement(item, "mail").equals(" ")) {
					mail.setValorComunicacion(getParsedValueFromElement(item, "mail"));
				}

				cli.setMail(mail);
			}
			
			if (getParsedValueFromElement(item, "nu-cons-mail-factura") != null && !getParsedValueFromElement(item, "nu-cons-mail-factura").equals("-1")) {
				
				mailFacturas.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(item, "nu-cons-mail-factura")));
				if (getParsedValueFromElement(item, "mail-factura") != null && !getParsedValueFromElement(item, "mail-factura").equals(" ")) {
					mailFacturas.setValorComunicacion(getParsedValueFromElement(item, "mail-factura"));
				}

				cli.setMailFacturas(mailFacturas);
			}

			resultado.setCliente(cli);

			NodeList elemDir = item.getElementsByTagName("direccion-cliente");
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

			NodeList certificados = this.doc.getElementsByTagName("certificado-cot");
			for (int i = 0; i < certificados.getLength(); i++) {
				Element itemCert = (Element) certificados.item(i);
				Certificado cert = new Certificado();

				cert.setPermiteAnular(getParsedValueFromElement(itemCert, "permite-anular"));
				cert.setHabilitoInsertar(getParsedValueFromElement(itemCert, "habilito-insertar"));
				cert.setExisteAsegurado(getParsedValueFromElement(itemCert, "existe-asegurado"));
				cert.setRequiereAsegurado(getParsedValueFromElement(itemCert, "requiere-asegurado"));
				if (getParsedValueFromElement(itemCert, "nu-certificado") != null) {
					cert.setNumCertificado(Integer.valueOf(getParsedValueFromElement(itemCert, "nu-certificado")));
				}
				
				cert.setCertificadoNuevo(getParsedValueFromElement(itemCert, "es-cert-nuevo"));
				cert.setAnulado(getParsedValueFromElement(itemCert, "estado-anulado"));
				cert.setCodPlanCobertura(getParsedValueFromElement(itemCert, "cod-plan-cob"));
				cert.setDescPlanCobertura(getParsedValueFromElement(itemCert, "desc-plan-cob"));

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
						aseg.setCodProfesion(Integer.parseInt(getParsedValueFromElement(itemDatosAseg, "cod-profesion")));
					}
					aseg.setProfesion(getParsedValueFromElement(itemDatosAseg, "desc-profesion"));
					aseg.setCodCliente(getParsedValueFromElement(itemDatosAseg, "nu-cedula-rif"));
					cli.setEsPep(getParsedValueFromElement(itemDatosAseg, "es-pep"));
					cli.setFechaPep(getParsedValueFromElement(itemDatosAseg, "fe-pep"));
					if (getParsedValueFromElement(itemDatosAseg, "nu-cons-telefono") != null && !getParsedValueFromElement(itemDatosAseg, "nu-cons-telefono").equals("-1")) {
						telAse.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(itemDatosAseg, "nu-cons-telefono")));
						if (!" ".equals(getParsedValueFromElement(itemDatosAseg, "telefono"))) {
							telAse.setValorComunicacion(getParsedValueFromElement(itemDatosAseg, "telefono"));
						}
						aseg.setTel(telAse);
					}
					if (getParsedValueFromElement(itemDatosAseg, "nu-cons-celular") != null && !getParsedValueFromElement(itemDatosAseg, "nu-cons-celular").equals("-1")) {
						celAse.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(itemDatosAseg, "nu-cons-celular")));
						if (!" ".equals(getParsedValueFromElement(itemDatosAseg, "celular"))) {
							celAse.setValorComunicacion(getParsedValueFromElement(itemDatosAseg, "celular"));
						}
						aseg.setCel(celAse);
					}
					if (getParsedValueFromElement(itemDatosAseg, "nu-cons-mail") != null && !getParsedValueFromElement(itemDatosAseg, "nu-cons-mail").equals("-1")) {
						mailAse.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(itemDatosAseg, "nu-cons-mail")));
						if (!" ".equals(getParsedValueFromElement(itemDatosAseg, "mail"))) {
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
						obj.setRequierePadron(getParsedValueFromElement(itemBien, "requiere-padron"));
						obj.setAcreedoresBien(this.parsearAcreedores(itemBien));
						obj.setBeneficiariosBien(this.parsearBeneficiarios(itemBien));
						obj.setObjetosBien(this.parsearObjetosBien(itemBien));

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

							NodeList ubicacion = this.doc.getElementsByTagName("ubicacion");
							if (ubicacion.getLength() != 0) {
								Element ubItem = (Element) ubicacion.item(0);
								Ubicacion ub = new Ubicacion();

								ub.setCalle(getParsedValueFromElement(ubItem, "desc-calle"));
								ub.setDepartamento(getParsedValueFromElement(ubItem, "desc-departamento"));
								ub.setDescripRadio(getParsedValueFromElement(ubItem, "desc-localidad"));
								ub.setLocalidad(getParsedValueFromElement(ubItem, "desc-municipio"));

								if (getParsedValueFromElement(ubItem, "cod-municipio") != null) {
									ub.setCodLocalidad(Integer.valueOf(getParsedValueFromElement(ubItem, "cod-municipio")));
								}

								if (getParsedValueFromElement(ubItem, "cod-provincia") != null) {
									ub.setCodDepartamento(Integer.valueOf(getParsedValueFromElement(ubItem, "cod-provincia")));
								}

								ub.setDepartamento(getParsedValueFromElement(ubItem, "desc-estado"));

								if (getParsedValueFromElement(ubItem, "cod-calle") != null) {
									ub.setCodCalle(Integer.valueOf(getParsedValueFromElement(ubItem, "cod-calle")));
								}
								if (getParsedValueFromElement(ubItem, "cod-postal") != null) {
									ub.setRadio(Integer.valueOf(getParsedValueFromElement(ubItem, "cod-postal")));
								}

								if (getParsedValueFromElement(ubItem, "cod-pais") != null) {
									ub.setCodPais(Integer.valueOf(getParsedValueFromElement(ubItem, "cod-pais")));
								}

								ub.setPais(getParsedValueFromElement(ubItem, "desc-pais"));

								if (getParsedValueFromElement(ubItem, "st-capital") != null) {
									ub.setStCapital(Integer.valueOf(getParsedValueFromElement(ubItem, "st-capital")));
								}
								if (getParsedValueFromElement(ubItem, "nu-direccion") != null) {
									ub.setNumDireccion(Integer.valueOf(getParsedValueFromElement(ubItem, "nu-direccion")));
								}
								ub.setNumero(getParsedValueFromElement(ubItem, "nu-calle"));
								ub.setPiso(getParsedValueFromElement(ubItem, "nu-piso"));
								ub.setNumDpto(getParsedValueFromElement(ubItem, "desc-departamento"));
								ub.setTelefono(getParsedValueFromElement(ubItem, "telefono"));
								ub.setFax(getParsedValueFromElement(ubItem, "aclaracion-dir"));
								if (getParsedValueFromElement(ubItem, "nu-postal") != null) {
									ub.setNumPostal(Integer.valueOf(getParsedValueFromElement(ubItem, "nu-postal")));
								}

								ub.setPadron(getParsedValueFromElement(ubItem, "nu-padron"));

								if (getParsedValueFromElement(ubItem, "inicio-rango-numeracion") != null) {
									ub.setInicioRangoNumeracion(Integer.valueOf(getParsedValueFromElement(ubItem, "inicio-rango-numeracion")));
								}

								if (getParsedValueFromElement(ubItem, "fin-rango-numeracion") != null) {
									ub.setFinRangoNumeracion(Integer.valueOf(getParsedValueFromElement(ubItem, "fin-rango-numeracion")));
								}

								obj.setUbicacionBien(ub);
							}

						}
						resultado.setUnBienCert(obj);
					}
				}
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

	private BienCert parsearBienCert(final Element item) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearBienCert()");

		final BienCert bienCert = new BienCert();

		try {

			bienCert.setConsecutivo(this.evaluarInt(item, "consec-bien"));
			bienCert.setDescripcion(getParsedValueFromElement(item, "desc-bien"));
			bienCert.setFechaBaja(getParsedValueFromElement(item, "fe-baja"));
			bienCert.setCertificado(this.evaluarInt(item, "certificado"));
			bienCert.setPosicionBien(this.evaluarInt(item, "pos-bien"));
			if (getParsedValueFromElement(item, "cod-bien") != null) {
				bienCert.setCodigoBien(Integer.valueOf(getParsedValueFromElement(item, "cod-bien")));
			}

		} catch (NumberFormatException ex) {
			catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			catchException(logueo, ex);
		}

		return bienCert;
	}

	public ResultTextosCotizacion parsearObtenerTextosCotizacion() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerTextosCotizacion()");

		ResultTextosCotizacion resultado = new ResultTextosCotizacion();

		try {
			NodeList elementos = this.doc.getElementsByTagName("texto");

			for (int i = 0; i < elementos.getLength(); i++) {

				Element item = (Element) elementos.item(i);
				Texto tex = new Texto();

				if (getParsedValueFromElement(item, "nu-consecutivo") != null) {
					tex.setNumConsecutivo(Integer.valueOf(getParsedValueFromElement(item, "nu-consecutivo")));
				}

				if (getParsedValueFromElement(item, "cod-texto") != null) {
					tex.setCodTexto(Integer.valueOf(getParsedValueFromElement(item, "cod-texto")));
				}
				tex.setDescripTexto(getParsedValueFromElement(item, "desc-texto"));
				tex.setImpresion(getParsedValueFromElement(item, "in-impresion"));
				tex.setFechaDesde(getParsedValueFromElement(item, "fe-desde"));
				tex.setPersiste(getParsedValueFromElement(item, "in-persiste"));
				tex.setFechaHasta(getParsedValueFromElement(item, "fe-hasta"));

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

	public ResultEncabezadoCotizacion parsearObtenerEncabezadoCotizacion() {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearEncabezadoCotizacion()");

		ResultEncabezadoCotizacion resultado = new ResultEncabezadoCotizacion();

		try {
			NodeList elementos = this.doc.getElementsByTagName("cabezal-cotizacion");
			Element item = (Element) elementos.item(0);
			EncabezadoCotizacion enc = new EncabezadoCotizacion();

			if (getParsedValueFromElement(item, "cod-ramo") != null) {
				enc.setCodRamo(Integer.valueOf(getParsedValueFromElement(item, "cod-ramo")));
			}
			enc.setDescripRamo(getParsedValueFromElement(item, "desc-ramo"));

			if (getParsedValueFromElement(item, "nu-cotizacion") != null) {
				enc.setNumCotizacion(Integer.valueOf(getParsedValueFromElement(item, "nu-cotizacion")));
			}

			if (getParsedValueFromElement(item, "nu-certificado") != null) {
				enc.setNumCertificado(Integer.valueOf(getParsedValueFromElement(item, "nu-certificado")));
			}
			enc.setCodProducto(getParsedValueFromElement(item, "cod-producto"));
			enc.setDescripProducto(getParsedValueFromElement(item, "desc-producto"));
			enc.setCodNacionalidad(getParsedValueFromElement(item, "cod-nacionalidad"));
			enc.setCodCliente(getParsedValueFromElement(item, "nu-cedula-rif"));
			enc.setCliente(getParsedValueFromElement(item, "nombre-persona"));

			if (getParsedValueFromElement(item, "cod-bien") != null) {
				enc.setCodBien(Integer.valueOf(getParsedValueFromElement(item, "cod-bien")));
			}
			enc.setDescripBien(getParsedValueFromElement(item, "desc-bien"));

			resultado.setEncabezadoCotizacion(enc);

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;

	}

	public ResultObtenerAcreedoresXBien parsearObtenerAcreedoresXBien() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerAcreedoresXBien()");

		ResultObtenerAcreedoresXBien resultado = new ResultObtenerAcreedoresXBien();

		try {
			NodeList elementos = this.doc.getElementsByTagName("acreedor");

			for (int i = 0; i < elementos.getLength(); i++) {

				Element item = (Element) elementos.item(i);
				AcreedorBien acr = new AcreedorBien();

				if (getParsedValueFromElement(item, "cod-acreedor") != null) {
					acr.setCodAcreedor(Integer.valueOf(getParsedValueFromElement(item, "cod-acreedor")));
				}
				acr.setDescripAcreedor(getParsedValueFromElement(item, "desc-acreedor"));

				if (getParsedValueFromElement(item, "cod-objeto") != null) {
					acr.setCodObjeto(Integer.valueOf(getParsedValueFromElement(item, "cod-objeto")));
				}
				acr.setDescripObjeto(getParsedValueFromElement(item, "desc-objeto"));
				acr.setCodTipoAcreedor(getParsedValueFromElement(item, "cod-tp-acreedor"));
				acr.setDescripTipoAcreedor(getParsedValueFromElement(item, "desc-tp-acreedor"));

				if (getParsedValueFromElement(item, "po-participacion") != null) {
					acr.setPorcentajeParticipacion(Double.valueOf(getParsedValueFromElement(item, "po-participacion")));
				}
				acr.setIdentificador(getParsedValueFromElement(item, "identificador"));
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

	public ResultBonificacionNS parsearObtenerBonificacionNS() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerBonificacionNS()");

		ResultBonificacionNS resultado = new ResultBonificacionNS();

		try {
			NodeList elemCoti = this.doc.getElementsByTagName("cotizacion");
			Element itemCoti = (Element) elemCoti.item(0);
			NodeList elemCertCoti = itemCoti.getElementsByTagName("certificado-cot");

			for (int i = 0; i < elemCertCoti.getLength(); i++) {
				Element itemCert = (Element) elemCertCoti.item(i);
				BonificacionCotizacion bonCot = new BonificacionCotizacion();

				if (getParsedValueFromElement(itemCert, "nu-certificado-cot") != null) {
					bonCot.setNumCertificado(Integer.valueOf(getParsedValueFromElement(itemCert, "nu-certificado-cot")));
				}

				bonCot.setClausula(getParsedValueFromElement(itemCert, "clausula-cot"));

				resultado.setUnaBonCotizacion(bonCot);

			}

			NodeList elemPoli = this.doc.getElementsByTagName("poliza");
			Element itemPoli = (Element) elemPoli.item(0);
			NodeList elemCertPoli = itemPoli.getElementsByTagName("certificado-pol");
			for (int i = 0; i < elemCertPoli.getLength(); i++) {
				Element itemCert = (Element) elemCertPoli.item(i);
				BonificacionPoliza bonPol = new BonificacionPoliza();

				if (getParsedValueFromElement(itemCert, "nu-certificado-pol") != null) {
					bonPol.setNumCertificado(Integer.valueOf(getParsedValueFromElement(itemCert, "nu-certificado-pol")));
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

					encPol.setClausula(getParsedValueFromElement(itemPoliEnc, "clausula-pol"));

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

	public ResultObtenerDatosPreviosEndoso parsearObtenerDatosPreviosEndoso() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerDatosPreviosEndoso()");

		ResultObtenerDatosPreviosEndoso resultado = new ResultObtenerDatosPreviosEndoso();

		try {
			NodeList datosCoti = this.doc.getElementsByTagName("datos-basicos");
			Element itemCoti = (Element) datosCoti.item(0);
			DatosCotizacion cot = new DatosCotizacion();

			if (getParsedValueFromElement(itemCoti, "nu-poliza") != null) {
				cot.setNumPoliza(Integer.valueOf(getParsedValueFromElement(itemCoti, "nu-poliza")));
			}
			if (getParsedValueFromElement(itemCoti, "nu-certificado") != null) {
				cot.setNumCertificado(Integer.valueOf(getParsedValueFromElement(itemCoti, "nu-certificado")));
			}
			if (getParsedValueFromElement(itemCoti, "cod-medio-pago") != null) {
				cot.setMedioPagoCod(Integer.valueOf(getParsedValueFromElement(itemCoti, "cod-medio-pago")));
			}
			cot.setMedioPagoDesc(getParsedValueFromElement(itemCoti, "desc-medio-pago"));
			cot.setCodTipoCalculo(getParsedValueFromElement(itemCoti, "cod-tp-calculo"));
			cot.setDescTipoCalculo(getParsedValueFromElement(itemCoti, "desc-tp-calculo"));
			cot.setVigenciaCod(getParsedValueFromElement(itemCoti, "cod-fr-pago"));
			cot.setVigenciaDesc(getParsedValueFromElement(itemCoti, "desc-fr-pago"));
			cot.setFrecTecnicaCod(getParsedValueFromElement(itemCoti, "cod-vig-tecnica"));
			cot.setFrecTecnicaDesc(getParsedValueFromElement(itemCoti, "desc-vig-tecnica"));
			cot.setFacturacionCod(getParsedValueFromElement(itemCoti, "cod-tp-facturacion"));
			cot.setFacturacionDesc(getParsedValueFromElement(itemCoti, "desc-tp-facturacion"));

			if (getParsedValueFromElement(itemCoti, "cod-medio-pago") != null) {
				cot.setProductorCod(Integer.valueOf(getParsedValueFromElement(itemCoti, "cod-productor")));
			}
			cot.setProductorDesc(getParsedValueFromElement(itemCoti, "desc-productor"));

			// FIXME OIGRES YA ADD BROKER
			if (getParsedValueFromElement(itemCoti, "cod-broker") != null) {
				cot.setCodBroker(Integer.valueOf(getParsedValueFromElement(itemCoti, "cod-broker")));
			}

			cot.setDescBroker(getParsedValueFromElement(itemCoti, "desc-broker"));

			if (getParsedValueFromElement(itemCoti, "cod-moneda") != null) {
				cot.setMonedaCod(Integer.valueOf(getParsedValueFromElement(itemCoti, "cod-moneda")));
			}

			if (getParsedValueFromElement(itemCoti, "nu-sucursal") != null) {
				cot.setSucursal(Integer.valueOf(getParsedValueFromElement(itemCoti, "nu-sucursal")));
			}
			cot.setMonedaDesc(getParsedValueFromElement(itemCoti, "desc-moneda"));
			cot.setOrigenCod(getParsedValueFromElement(itemCoti, "cod-origen"));
			cot.setOrigenDesc(getParsedValueFromElement(itemCoti, "desc-origen"));
			cot.setPromocionCod(getParsedValueFromElement(itemCoti, "cod-promocion"));
			cot.setPromocionDesc(getParsedValueFromElement(itemCoti, "desc-promocion"));
			cot.setVigenciaDesde(getParsedValueFromElement(itemCoti, "fe-desde"));
			cot.setVigenciaHasta(getParsedValueFromElement(itemCoti, "fe-hasta"));
			cot.setVigenciaTecnicaDesde(getParsedValueFromElement(itemCoti, "fe-desde-tecnica"));
			cot.setVigenciaTecnicaHasta(getParsedValueFromElement(itemCoti, "fe-hasta-tecnica"));
			cot.setRenovacionCod(getParsedValueFromElement(itemCoti, "cod-renovacion"));
			cot.setRenovacionDesc(getParsedValueFromElement(itemCoti, "desc-renovacion"));

			resultado.setCotizacion(cot);

			NodeList datoCont = this.doc.getElementsByTagName("contratante");
			Element itemCont = (Element) datoCont.item(0);
			DatosContratante cont = new DatosContratante();

			cont.setNacionalidadCod(getParsedValueFromElement(itemCont, "cod-nacionalidad"));

			if (getParsedValueFromElement(itemCont, "nu-cedula-rif") != null) {
				cont.setClienteCod(Integer.valueOf(getParsedValueFromElement(itemCont, "nu-cedula-rif")));
			}
			cont.setClienteDesc(getParsedValueFromElement(itemCont, "nombre-persona"));
			cont.setDireccionDesc(getParsedValueFromElement(itemCont, "desc-calle"));

			if (getParsedValueFromElement(itemCont, "nu-calle") != null) {
				cont.setDireccionNum(Integer.valueOf(getParsedValueFromElement(itemCont, "nu-calle")));
			}
			cont.setDireccionUnidad(getParsedValueFromElement(itemCont, "nu-unidad"));
			cont.setTelefono(getParsedValueFromElement(itemCont, "telefono"));

			if (getParsedValueFromElement(itemCont, "cod-postal") != null) {
				cont.setRadioCod(Integer.valueOf(getParsedValueFromElement(itemCont, "cod-postal")));
			}
			cont.setRadioDesc(getParsedValueFromElement(itemCont, "desc-localidad"));

			if (getParsedValueFromElement(itemCont, "cod-municipio") != null) {
				cont.setDepartamentoCod(Integer.valueOf(getParsedValueFromElement(itemCont, "cod-municipio")));
			}

			cont.setDepartamentoDesc(getParsedValueFromElement(itemCont, "desc-municipio"));
			cont.setAnulacionCorrida(getParsedValueFromElement(itemCont, "in-anul-corrida"));

			resultado.setContratante(cont);

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;

	}

	public ResultObtenerDomiciliosCliente parsearObtenerDomiciliosCliente() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerDomiciliosCliente()");

		ResultObtenerDomiciliosCliente resultado = new ResultObtenerDomiciliosCliente();

		try {
			NodeList elementos = this.doc.getElementsByTagName("direccion");

			for (int i = 0; i < elementos.getLength(); i++) {

				Element item = (Element) elementos.item(i);
				DireccionEC dir = new DireccionEC();

				if (getParsedValueFromElement(item, "nu-direccion") != null) {
					dir.setCodDireccion(Integer.valueOf(getParsedValueFromElement(item, "nu-direccion")));
				}

				if (getParsedValueFromElement(item, "cod-tp-direccion") != null) {
					dir.setTipoDireccion(Integer.valueOf(getParsedValueFromElement(item, "cod-tp-direccion")));
				}

				dir.setDescripTipoDirecion(getParsedValueFromElement(item, "desc-tp-direccion"));

				if (getParsedValueFromElement(item, "nu-postal") != null) {
					dir.setNumPostal(Integer.valueOf(getParsedValueFromElement(item, "nu-postal")));
				}

				if (getParsedValueFromElement(item, "cod-postal") != null) {
					dir.setNumRadio(Integer.valueOf(getParsedValueFromElement(item, "cod-postal")));
				}

				dir.setDescripRadio(getParsedValueFromElement(item, "desc-localidad"));
				dir.setDireccion(getParsedValueFromElement(item, "desc-calle"));
				dir.setNumeroPuerta(getParsedValueFromElement(item, "nu-calle"));
				dir.setUnidad(getParsedValueFromElement(item, "nu-unidad"));
				dir.setPiso(getParsedValueFromElement(item, "nu-piso"));
				dir.setApto(getParsedValueFromElement(item, "nu-departamento"));
				dir.setDescripLocalidad(getParsedValueFromElement(item, "desc-municipio"));
				dir.setDescripDepto(getParsedValueFromElement(item, "desc-estado"));
				dir.setDescripPais(getParsedValueFromElement(item, "desc-pais"));

				resultado.getDirecciones().add(dir);

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

	public ResultObtenerContratanteAsegurado parsearObtenerContratanteAsegurado() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerContratanteAsegurado()");

		ResultObtenerContratanteAsegurado resultado = new ResultObtenerContratanteAsegurado();

		try {
			NodeList elementos = this.doc.getElementsByTagName("cliente");

			for (int i = 0; i < elementos.getLength(); i++) {

				Element item = (Element) elementos.item(i);
				DatosBasicosCliente dato = new DatosBasicosCliente();

				if (getParsedValueFromElement(item, "nu-persona") != null) {
					dato.setNumPersona(Integer.valueOf(getParsedValueFromElement(item, "nu-persona")));
				}

				if (getParsedValueFromElement(item, "apellido") != null) {
					dato.setApellidoRazonSocial(getParsedValueFromElement(item, "apellido"));
				}

				dato.setNombre(getParsedValueFromElement(item, "nombre"));
				dato.setRut(getParsedValueFromElement(item, "rut"));

				if (getParsedValueFromElement(item, "cod-tp_documento") != null) {
					if (getParsedValueFromElement(item, "cod-tp_documento").equals("-1")) {
						dato.setTipoDocumento(null);
					} else {
						dato.setTipoDocumento(getParsedValueFromElement(item, "cod-tp_documento"));
					}
				}

				dato.setDescripTipoDocumento(getParsedValueFromElement(item, "desc-tp-documento"));

				dato.setNumDocumento(getParsedValueFromElement(item, "nu-documento"));
				resultado.getClientes().add(dato);

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

	public ResultPersonaSeleccionada parsearObtenerPersonaSeleccionada() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerPersonaSeleccionada");

		ResultPersonaSeleccionada resultado = new ResultPersonaSeleccionada();

		try {
			NodeList elementos = this.doc.getElementsByTagName("datos-basicos-cliente");
			if (elementos.getLength() != 0) {
				Element item = (Element) elementos.item(0);
				Cliente cli = new Cliente();
				ComunicacionEC cel = new ComunicacionEC();
				ComunicacionEC tel = new ComunicacionEC();
				ComunicacionEC mail = new ComunicacionEC();
				ComunicacionEC mailFacturas = new ComunicacionEC();

				if (getParsedValueFromElement(item, "nu-persona") != null) {
					cli.setNumPersona(getParsedValueFromElement(item, "nu-persona"));
				}

				cli.setTipoDoc(getParsedValueFromElement(item, "cod-tp-documento"));
				cli.setDescripTipoDoc(getParsedValueFromElement(item, "desc-tp-documento"));
				cli.setNumDoc(getParsedValueFromElement(item, "nu-documento"));
				cli.setRut(getParsedValueFromElement(item, "rut"));
				cli.setNombre(getParsedValueFromElement(item, "nombre-persona"));
				cli.setApellidoRazon(getParsedValueFromElement(item, "apellido-persona"));
				if (getParsedValueFromElement(item, "cod-profesion") != null) {
					cli.setCodProfesion(Integer.valueOf(getParsedValueFromElement(item, "cod-profesion")));
				}
				cli.setProfesion(getParsedValueFromElement(item, "desc-profesion"));
				cli.setCodCliente(getParsedValueFromElement(item, "nu-cedula-rif"));
				cli.setEsPep(getParsedValueFromElement(item, "es-pep"));
				cli.setFechaPep(getParsedValueFromElement(item, "fe-pep"));
				if (getParsedValueFromElement(item, "nu-cons-telefono") != null && !getParsedValueFromElement(item, "nu-cons-telefono").equals("-1")) {
					tel.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(item, "nu-cons-telefono")));
					if (!" ".equals(getParsedValueFromElement(item, "telefono"))) {
						tel.setValorComunicacion(getParsedValueFromElement(item, "telefono"));
					}
					cli.setTel(tel);
				}
				if (getParsedValueFromElement(item, "nu-cons-celular") != null && !getParsedValueFromElement(item, "nu-cons-celular").equals("-1")) {
					cel.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(item, "nu-cons-celular")));
					if (!" ".equals(getParsedValueFromElement(item, "celular"))) {
						cel.setValorComunicacion(getParsedValueFromElement(item, "celular"));
					}
					cli.setCel(cel);
				}
				if (getParsedValueFromElement(item, "nu-cons-mail") != null && !getParsedValueFromElement(item, "nu-cons-mail").equals("-1")) {
					mail.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(item, "nu-cons-mail")));
					if (getParsedValueFromElement(item, "mail") != null && !getParsedValueFromElement(item, "mail").equals(" ")) {
						mail.setValorComunicacion(getParsedValueFromElement(item, "mail"));
					}

					cli.setMail(mail);
				}
				
				if (getParsedValueFromElement(item, "nu-cons-mail-factura") != null && !getParsedValueFromElement(item, "nu-cons-mail-factura").equals("-1")) {
					mailFacturas.setCodComunicacion(Integer.valueOf(getParsedValueFromElement(item, "nu-cons-mail-factura")));
					if (getParsedValueFromElement(item, "mail-factura") != null && !getParsedValueFromElement(item, "mail-factura").equals(" ")) {
						mailFacturas.setValorComunicacion(getParsedValueFromElement(item, "mail-factura"));
					}

					cli.setMailFacturas(mailFacturas);
				}

				resultado.setCliente(cli);

				NodeList elemDirCli = this.doc.getElementsByTagName("direccion-cliente");

				if (elemDirCli.getLength() != 0) {
					for (int i = 0; i < elemDirCli.getLength(); i++) {
						Element itemDir = (Element) elemDirCli.item(i);

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

						resultado.getDirecciones().add(dato);
					}
				}
			}

			NodeList elemAseg = this.doc.getElementsByTagName("datos-basicos-asegurado");
			if (elemAseg.getLength() != 0) {
				resultado.setCliente(this.parsearAsegurado());
				NodeList elemDir = this.doc.getElementsByTagName("direccion-asegurado");

				if (elemDir.getLength() != 0) {
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
						resultado.getDirecciones().add(dato);
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

	public ResultValoresTipoNota parsearListaValoresTipoNota() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearListaValoresTipoNota()");

		ResultValoresTipoNota resultado = new ResultValoresTipoNota();

		try {
			NodeList elementos = this.doc.getElementsByTagName("elemento");

			for (int i = 0; i < elementos.getLength(); i++) {

				Element item = (Element) elementos.item(i);
				TiposNotaGenerica dato = new TiposNotaGenerica();

				if (getParsedValueFromElement(item, "codigo") != null) {
					dato.setCodigo(getParsedValueFromElement(item, "codigo"));
				}

				if (getParsedValueFromElement(item, "descripcion") != null) {
					dato.setDescripcion(getParsedValueFromElement(item, "descripcion"));
				}

				resultado.setUnElemento(dato);

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

	public ResultObtenerPlanesCobertura parsearObtenerPlanesCobertura() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerPlanesCobertura()");

		ResultObtenerPlanesCobertura resultado = new ResultObtenerPlanesCobertura();

		try {
			NodeList datosCoti = this.doc.getElementsByTagName("cotizacion");
			Element itemCoti = (Element) datosCoti.item(0);
			Moneda moneda = new Moneda();

			if (getParsedValueFromElement(itemCoti, "dia-vto") != null) {
				resultado.setDiaVto(Integer.valueOf(getParsedValueFromElement(itemCoti, "dia-vto")));
			}
			moneda.setDescripcion(getParsedValueFromElement(itemCoti, "desc-moneda"));
			moneda.setSimbolo(getParsedValueFromElement(itemCoti, "simbolo-moneda"));
			resultado.setMoneda(moneda);

			if (getParsedValueFromElement(itemCoti, "mt-cuota-cert") != null) {
				resultado.setMontoCuotaCert(Double.valueOf(getParsedValueFromElement(itemCoti, "mt-cuota-cert")));
			}

			if (getParsedValueFromElement(itemCoti, "mt-total-cert") != null) {
				resultado.setMontoTotalCert(Double.valueOf(getParsedValueFromElement(itemCoti, "mt-total-cert")));
			}

			if (getParsedValueFromElement(itemCoti, "cod-plan-pago") != null) {
				if (getParsedValueFromElement(itemCoti, "cod-plan-pago").equals("-1")) {
					resultado.setCodPlanPago(null);
				} else {
					resultado.setCodPlanPago(Integer.valueOf(getParsedValueFromElement(itemCoti, "cod-plan-pago")));
				}
			}

			if (getParsedValueFromElement(itemCoti, "cod-nivel-comision") != null) {
				if (getParsedValueFromElement(itemCoti, "cod-nivel-comision").equals("-1")) {
					resultado.setCodNivelComision(null);
				} else {
					resultado.setCodNivelComision(Integer.valueOf(getParsedValueFromElement(itemCoti, "cod-nivel-comision")));
				}
			}

			if (getParsedValueFromElement(itemCoti, "cod-nivel-comision-broker") != null) {
				if (getParsedValueFromElement(itemCoti, "cod-nivel-comision-broker").equals("-1")) {
					resultado.setCodNivelComisionBroker(null);
				} else {
					resultado.setCodNivelComisionBroker(Integer.valueOf(getParsedValueFromElement(itemCoti, "cod-nivel-comision-broker")));
				}
			}

			resultado.setDescNivelComision(getParsedValueFromElement(itemCoti, "desc-nivel-comision"));
			resultado.setDescNivelComisionBroker(getParsedValueFromElement(itemCoti, "desc-nivel-comision-broker"));
			NodeList nodoRiesgo = itemCoti.getElementsByTagName("riesgo");
			
			
			for (int k = 0; k < nodoRiesgo.getLength(); k++) {
				Element itemRiesgo = (Element) nodoRiesgo.item(k);
				Riesgo riesgo = new Riesgo();

				if (getParsedValueFromElement(itemRiesgo, "nu-certificado") != null) {
					riesgo.setNumCertificado(Integer.valueOf(getParsedValueFromElement(itemRiesgo, "nu-certificado")));
				}
				riesgo.setDescRiesgo(getParsedValueFromElement(itemRiesgo, "desc-riesgo"));	
				resultado.setUnRiesgo(riesgo);
			}
			NodeList planesPago = this.doc.getElementsByTagName("planes-pago");

			for (int i = 0; i < planesPago.getLength(); i++) {
				PlanPago plan = new PlanPago();
				Element planItem = (Element) planesPago.item(i);

				plan.setCodigo(getParsedValueFromElement(planItem, "cod-plan-pago"));

				plan.setDescripcion(getParsedValueFromElement(planItem, "desc-plan-pago"));

				resultado.setUnPlanPago(plan);

			}

			NodeList certificados = this.doc.getElementsByTagName("certificado");

			for (int i = 0; i < certificados.getLength(); i++) {
				Certificado cert = new Certificado();
				Element certItem = (Element) certificados.item(i);

				if (getParsedValueFromElement(certItem, "nu-certificado") != null) {
					cert.setNumCertificado(Integer.valueOf(getParsedValueFromElement(certItem, "nu-certificado")));
				}
				cert.setCertificadoNuevo(getParsedValueFromElement(certItem, "es-cert-nuevo"));
				cert.setAnulado(getParsedValueFromElement(certItem, "estado-anulado"));
				NodeList planesCob = certItem.getElementsByTagName("plan-cobertura");

				for (int n = 0; n < planesCob.getLength(); n++) {
					PlanCobertura plan = new PlanCobertura();
					Element planItem = (Element) planesCob.item(n);

					plan.setCodigo(getParsedValueFromElement(planItem, "codigo"));
					plan.setDescripcion(getParsedValueFromElement(planItem, "descripcion"));
					plan.setExcluido(getParsedValueFromElement(planItem, "excluido"));
					if (getParsedValueFromElement(planItem, "monto-prima") != null) {
						plan.setMontoPrima(Double.valueOf(getParsedValueFromElement(planItem, "monto-prima")));
					}
					if (getParsedValueFromElement(planItem, "monto-premio") != null) {
						plan.setMontoPremio(Double.valueOf(getParsedValueFromElement(planItem, "monto-premio")));
					}
					plan.setErrorPlanCob(getParsedValueFromElement(planItem, "error-plan-cob"));
					cert.setUnaPosibleCobertura(plan);
				}

				NodeList coberturasCert = certItem.getElementsByTagName("plan-cobertura-cert");

				for (int j = 0; j < coberturasCert.getLength(); j++) {
					PlanCobertura planCob = new PlanCobertura();
					Element cobItem = (Element) coberturasCert.item(j);
					planCob.setCodigo(getParsedValueFromElement(cobItem, "cod-plan-cobertura-cert"));
					planCob.setDescripcion(getParsedValueFromElement(cobItem, "desc-plan-cobertura-cert"));
					cert.setDescPlanCobertura(getParsedValueFromElement(cobItem, "desc-plan-cobertura-cert"));
					cert.setCodPlanCobertura(getParsedValueFromElement(cobItem, "cod-plan-cobertura-cert"));

					if (getParsedValueFromElement(cobItem, "mt-deducible") != null) {
						planCob.setMontoDeducible(Double.valueOf(getParsedValueFromElement(cobItem, "mt-deducible")));
					}

					if (getParsedValueFromElement(cobItem, "mt-prima") != null) {
						planCob.setMontoPrima(Double.valueOf(getParsedValueFromElement(cobItem, "mt-prima")));
					}
					if (getParsedValueFromElement(cobItem, "mt-premio") != null) {
						planCob.setMontoPremio(Double.valueOf(getParsedValueFromElement(cobItem, "mt-premio")));
					}

					NodeList planPagoCert = cobItem.getElementsByTagName("plan-pago");

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

	public ResultActualizarCoberturaBien parsearActualizarCoberturaBien() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearActualizarCoberturaBien()");

		ResultActualizarCoberturaBien resultado = new ResultActualizarCoberturaBien();

		try {

			NodeList datosCoti = this.doc.getElementsByTagName("cotizacion");
			Element itemCoti = (Element) datosCoti.item(0);

			resultado.setDerivadosMismoBien(getParsedValueFromElement(itemCoti, "derivados-mismo-bien"));
			resultado.setDerivadosOtroBien(getParsedValueFromElement(itemCoti, "derivados-otro-bien"));

			NodeList elementos = this.doc.getElementsByTagName("cobertura");

			for (int i = 0; i < elementos.getLength(); i++) {

				Element item = (Element) elementos.item(i);
				Cobertura dato = new Cobertura();

				if (getParsedValueFromElement(item, "cod-ramo-cobertura") != null) {
					dato.setRamoCod(Integer.valueOf(getParsedValueFromElement(item, "cod-ramo-cobertura")));
				}

				dato.setCoberturaCod(getParsedValueFromElement(item, "cod-cobertura"));

				if (getParsedValueFromElement(item, "valor") != null) {
					dato.setValor(Double.valueOf(getParsedValueFromElement(item, "valor")));
				}

				dato.setActualizado(getParsedValueFromElement(item, "cambia-color"));

				if (getParsedValueFromElement(item, "cod-bien") != null) {
					dato.setBienNum(Integer.valueOf(getParsedValueFromElement(item, "cod-bien")));
				}
				resultado.setUnaCobertura(dato);

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

	public ResultObtenerDetalleCuota parsearObtenerDetalleCuotas() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerDetalleCuotas()");

		ResultObtenerDetalleCuota resultado = new ResultObtenerDetalleCuota();

		try {

			NodeList cotizacion = this.doc.getElementsByTagName("cotizacion");
			Element itemCoti = (Element) cotizacion.item(0);

			NodeList datosCuota = itemCoti.getElementsByTagName("cuota");

			for (int i = 0; i < datosCuota.getLength(); i++) {
				Element item = (Element) datosCuota.item(i);
				DetalleCuota detalle = new DetalleCuota();

				if (getParsedValueFromElement(item, "nu-cuota") != null) {
					detalle.setNumCuota(Integer.valueOf(getParsedValueFromElement(item, "nu-cuota")));
				}

				detalle.setFechaCuota(getParsedValueFromElement(item, "fe-cuota"));

				if (getParsedValueFromElement(item, "mt-cuota") != null) {
					detalle.setMontoCuota(Double.valueOf(getParsedValueFromElement(item, "mt-cuota")));
				}

				resultado.setUnaCuota(detalle);
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

	public ResultObtenerResumenPlanesCobertura parsearObtenerResumenPlanesCobertura() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerResumenPlanesCobertura()");

		ResultObtenerResumenPlanesCobertura resultado = new ResultObtenerResumenPlanesCobertura();

		try {

			NodeList cotizacion = this.doc.getElementsByTagName("cotizacion");
			Element itemCoti = (Element) cotizacion.item(0);

			if (getParsedValueFromElement(itemCoti, "mt-premio-emision") != null) {
				resultado.setPremioEmision(Double.valueOf(getParsedValueFromElement(itemCoti, "mt-premio-emision")));
			}

			if (getParsedValueFromElement(itemCoti, "mt-premio-facturacion") != null) {
				resultado.setPremioFacturacion(Double.valueOf(getParsedValueFromElement(itemCoti, "mt-premio-facturacion")));
			}
			NodeList nodo = itemCoti.getElementsByTagName("componente");

			for (int i = 0; i < nodo.getLength(); i++) {
				Element item = (Element) nodo.item(i);
				Componente componente = new Componente();

				componente.setCodigo(getParsedValueFromElement(item, "cod-componente"));
				componente.setDescripcion(getParsedValueFromElement(item, "desc-componente"));

				if (getParsedValueFromElement(item, "importe") != null) {
					componente.setMonto(Double.valueOf(getParsedValueFromElement(item, "importe")));
				}
				resultado.setUnComponente(componente);
			}

			NodeList nodoCumulo = itemCoti.getElementsByTagName("cumulo");

			for (int i = 0; i < nodoCumulo.getLength(); i++) {
				Element item = (Element) nodoCumulo.item(i);
				Cumulo cumulo = new Cumulo();

				cumulo.setMensaje(getParsedValueFromElement(item, "mensaje"));
				resultado.setUnCumulo(cumulo);

			}

			NodeList nodoFueraPauta = itemCoti.getElementsByTagName("fuera-pauta");

			for (int i = 0; i < nodoFueraPauta.getLength(); i++) {
				Element item = (Element) nodoFueraPauta.item(i);
				FueraPauta fPauta = new FueraPauta();
				if (getParsedValueFromElement(item, "nu-certificado") != null) {
					fPauta.setNumCertificado(Integer.valueOf(getParsedValueFromElement(item, "nu-certificado")));
				}
				fPauta.setDescripcion(getParsedValueFromElement(item, "mensaje"));
				fPauta.setFirmante(getParsedValueFromElement(item, "firmante"));
				fPauta.setFechaActualizacion(getParsedValueFromElement(item, "fe-actualizacion"));
		        //PDP-1018 <in-autorizado>N</in-autorizado>
				fPauta.setAutorizado(getParsedValueFromElement(item, "in-autorizado"));
				resultado.setUnaFueraPauta(fPauta);
			}

			NodeList nodoCert = itemCoti.getElementsByTagName("certificado");

			for (int i = 0; i < nodoCert.getLength(); i++) {
				Element itemCert = (Element) nodoCert.item(i);
				Certificado cert = new Certificado();

				if (getParsedValueFromElement(itemCert, "nu-certificado") != null) {
					cert.setNumCertificado(Integer.valueOf(getParsedValueFromElement(itemCert, "nu-certificado")));
				}

				NodeList nodoAnexo = itemCert.getElementsByTagName("linea-anexo");

				for (int j = 0; j < nodoAnexo.getLength(); j++) {
					Element itemAnexo = (Element) nodoAnexo.item(j);
					Anexo anexo = new Anexo();

					anexo.setDescripcion(getParsedValueFromElement(itemAnexo, "desc-linea"));
					if (getParsedValueFromElement(itemAnexo, "cod-linea") != null) {
						anexo.setCodigo(Integer.valueOf(getParsedValueFromElement(itemAnexo, "cod-linea")));
					}

					NodeList nodoClausula = itemAnexo.getElementsByTagName("clausula");

					for (int k = 0; k < nodoClausula.getLength(); k++) {
						Element itemClausula = (Element) nodoClausula.item(k);
						Clausula clau = new Clausula();

						if (getParsedValueFromElement(itemClausula, "cod-clausula") != null) {
							clau.setCodClausula(getParsedValueFromElement(itemClausula, "cod-clausula"));
						}
						clau.setDescripcionClausula(getParsedValueFromElement(itemClausula, "desc-clausula"));

						clau.setModifica(getParsedValueFromElement(itemClausula, "in-exclusion"));
						clau.setImpresion(getParsedValueFromElement(itemClausula, "in-impresion"));

						if (getParsedValueFromElement(itemClausula, "cod-ramo") != null) {
							clau.setCodRamo(Integer.valueOf(getParsedValueFromElement(itemClausula, "cod-ramo")));
						}
						anexo.setUnaClausula(clau);
					}
					cert.setUnAnexo(anexo);
				}
				resultado.setUnCertificado(cert);
				
				NodeList nodoRiesgo = itemCoti.getElementsByTagName("riesgo");
				
				
				for (int k = 0; k < nodoRiesgo.getLength(); k++) {
					Element itemRiesgo = (Element) nodoRiesgo.item(k);
					Riesgo riesgo = new Riesgo();

					if (getParsedValueFromElement(itemRiesgo, "nu-certificado") != null) {
						riesgo.setNumCertificado(Integer.valueOf(getParsedValueFromElement(itemRiesgo, "nu-certificado")));
					}
					riesgo.setDescRiesgo(getParsedValueFromElement(itemRiesgo, "desc-riesgo"));	
					resultado.setUnRiesgo(riesgo);
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

	public ResultObtenerCoberturasXCertificado parsearObtenerCoberturasXCertificado() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerCoberturasXCertificado()");

		ResultObtenerCoberturasXCertificado resultado = new ResultObtenerCoberturasXCertificado();

		try {

			NodeList cotizacion = this.doc.getElementsByTagName("cotizacion");
			Element itemCoti = (Element) cotizacion.item(0);

			resultado.setCodPlanCobertura(getParsedValueFromElement(itemCoti, "cod-plan-cobertura"));
			resultado.setCodPlanPago(getParsedValueFromElement(itemCoti, "cod-plan-pago"));

			if (getParsedValueFromElement(itemCoti, "dia-vto") != null) {
				resultado.setDiaVto(Integer.valueOf(getParsedValueFromElement(itemCoti, "dia-vto")));
			}

			if (getParsedValueFromElement(itemCoti, "cod-nivel-comision") != null) {
				resultado.setCodNivel(Integer.valueOf(getParsedValueFromElement(itemCoti, "cod-nivel-comision")));
			}

			resultado.setDescNivel(getParsedValueFromElement(itemCoti, "desc-nivel-comision"));
			resultado.setPermiteAnular(getParsedValueFromElement(itemCoti, "permite-anular"));
			resultado.setActivo(getParsedValueFromElement(itemCoti, "activo"));
			resultado.setDescPlanCobertura(getParsedValueFromElement(itemCoti, "desc-plan-cob"));
			resultado.setHabilitoInsertar(getParsedValueFromElement(itemCoti, "habilito-insertar"));
			resultado.setExisteAsegurado(getParsedValueFromElement(itemCoti, "existe-asegurado"));

			ArrayList<PlanCobertura> planesCob = new ArrayList<PlanCobertura>();
			ArrayList<Bien> bienes = new ArrayList<Bien>();

			NodeList planCobertura = this.doc.getElementsByTagName("plan-cobertura");

			if (planCobertura.getLength() != 0) {
				for (int i = 0; i < planCobertura.getLength(); i++) {
					Element planItem = (Element) planCobertura.item(i);
					PlanCobertura plan = new PlanCobertura();

					plan.setCodigo(getParsedValueFromElement(planItem, "codigo"));
					plan.setDescripcion(getParsedValueFromElement(planItem, "descripcion"));
					plan.setExcluido(getParsedValueFromElement(planItem, "excluido"));
					if (getParsedValueFromElement(planItem, "monto-prima") != null) {
						plan.setMontoPrima(Double.valueOf(getParsedValueFromElement(planItem, "monto-prima")));
					}
					if (getParsedValueFromElement(planItem, "monto-premio") != null) {
						plan.setMontoPremio(Double.valueOf(getParsedValueFromElement(planItem, "monto-premio")));
					}
					plan.setErrorPlanCob(getParsedValueFromElement(planItem, "error-plan-cob"));

					planesCob.add(plan);

				}

				resultado.setPlanesCobertura(planesCob);

			}

			NodeList bienNode = this.doc.getElementsByTagName("bien");
			if (bienNode.getLength() != 0) {
				for (int k = 0; k < bienNode.getLength(); k++) {
					Element bienItem = (Element) bienNode.item(k);
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

					bien.setCoberturas(this.parsearCoberturas(bienItem));
					bien.setFranquicias(this.parsearFranquicia(bienItem));

					bienes.add(bien);
				}
				resultado.setBienes(bienes);
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

	public ResultObtenerDatosBancarios parsearObtenerDatosBancarios() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerDatosBancarios()");

		ResultObtenerDatosBancarios resultado = new ResultObtenerDatosBancarios();

		try {

			NodeList datoCot = this.doc.getElementsByTagName("cotizacion");
			Element itemCot = (Element) datoCot.item(0);

			resultado.setRequiereDatos(getParsedValueFromElement(itemCot, "requiere-datos-bancarios"));

			NodeList datoNodo = this.doc.getElementsByTagName("dato-bancario");
			ArrayList<DatosBanco> datos = new ArrayList<DatosBanco>();

			for (int i = 0; i < datoNodo.getLength(); i++) {
				Element item = (Element) datoNodo.item(i);
				DatosBanco dato = new DatosBanco();

				if (getParsedValueFromElement(item, "cod-banco") != null) {
					dato.setCodBanco(Integer.valueOf(getParsedValueFromElement(item, "cod-banco")));
				}

				dato.setDescripcionBanco(getParsedValueFromElement(item, "desc-banco"));
				dato.setNumCuenta(getSeudoTarjeta(getParsedValueFromElement(item, "nu-cuenta")));

				if (getParsedValueFromElement(item, "nu-domicilio") != null) {
					dato.setNumDomicilio(Integer.valueOf(getParsedValueFromElement(item, "nu-domicilio")));
				}

				datos.add(dato);
				resultado.setDatosBanco(datos);
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

	public ResultObtenerCoberturasXBien parsearObtenerCoberturasXBien() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObtenerCoberturasXBien");

		ResultObtenerCoberturasXBien resultado = new ResultObtenerCoberturasXBien();

		try {

			NodeList cotizacion = this.doc.getElementsByTagName("cotizacion");
			Element itemCoti = (Element) cotizacion.item(0);

			if (getParsedValueFromElement(itemCoti, "nu-certificado") != null) {
				resultado.setNumCertificado(Integer.valueOf(getParsedValueFromElement(itemCoti, "nu-certificado")));
			}

			resultado.setPermiteAnular(getParsedValueFromElement(itemCoti, "permite-anular"));
			resultado.setActivo(getParsedValueFromElement(itemCoti, "activo"));
			resultado.setCodPlanCobertura(getParsedValueFromElement(itemCoti, "cod-plan-cob"));
			resultado.setDescPlanCobertura(getParsedValueFromElement(itemCoti, "desc-plan-cob"));
			resultado.setRequiereAsegurado(getParsedValueFromElement(itemCoti, "requiere-asegurado"));
			resultado.setExisteAsegurado(getParsedValueFromElement(itemCoti, "existe-asegurado"));
			resultado.setHabilitoInsertar(getParsedValueFromElement(itemCoti, "habilito-insertar"));

			ArrayList<Bien> bienes = new ArrayList<Bien>();

			NodeList bienNode = this.doc.getElementsByTagName("bien");
			if (bienNode.getLength() != 0) {
				for (int k = 0; k < bienNode.getLength(); k++) {
					Element bienItem = (Element) bienNode.item(k);
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

					bien.setCoberturas(this.parsearCoberturas(bienItem));

					bienes.add(bien);
				}
				resultado.setBienes(bienes);
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
	
	

	public ResultConfigurarFechasPromocion parsearConfigurarFechasPromocion() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearConfigurarFechasPromocion");

		ResultConfigurarFechasPromocion resultado = new ResultConfigurarFechasPromocion();

		try {

			NodeList cotizacion = this.doc.getElementsByTagName("cotizacion");
			Element itemCoti = (Element) cotizacion.item(0);
			resultado.setDescModoCalculo(getParsedValueFromElement(itemCoti, "desc-tp-calc"));
			resultado.setDescVigencia(getParsedValueFromElement(itemCoti, "desc-fr-pago"));
			resultado.setFechaHastaVigencia(getParsedValueFromElement(itemCoti, "fe-hasta"));
			resultado.setFechaHastaVigenciaTecnica(getParsedValueFromElement(itemCoti, "fe-hasta-tecnica"));
			resultado.setModoCalculo(getParsedValueFromElement(itemCoti, "cod-tp-calc"));
			resultado.setVigencia(getParsedValueFromElement(itemCoti, "cod-fr-pago"));

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

	public ResultGenerico parsearListaPlanesPagoRenovacion() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoPolizas.class);
		logueo.setMetodo("parsearListaPlanesPagoRenovacion()");
		ResultListaPlanesPagoRen resultado = new ResultListaPlanesPagoRen();

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


}
