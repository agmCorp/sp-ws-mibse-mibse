package uy.bse.rector.servicios.parseo;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import uy.com.bse.cotizaciones.consultas.AcreedorBien;
import uy.com.bse.cotizaciones.consultas.BienCert;
import uy.com.bse.cotizaciones.consultas.Cobertura;
import uy.com.bse.cotizaciones.consultas.DatosBienCert;
import uy.com.bse.cotizaciones.consultas.DatosContratante;
import uy.com.bse.cotizaciones.consultas.Ubicacion;
import uy.com.bse.cotizaciones.entidades.Beneficiario;
import uy.com.bse.cotizaciones.entidades.DatosCotizacion;
import uy.com.bse.cotizaciones.entidades.ObjetoBien;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.parseo.ParserXmlValueUtil;

public class ParseoEntidades {

	private final ParserXmlValueUtil valueParser;

	public DatosCotizacion parsearDatosBasicos(final Element item) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearDatosBasicos()");
	

		DatosCotizacion resultado = new DatosCotizacion();
		resultado.setNumCotizacion(this.evaluarInt(item, "nu-cotizacion"));
		resultado.setNumPoliza(this.evaluarInt(item, "nu-poliza"));
		resultado.setNumCertificado(this.evaluarInt(item, "nu-certificado"));
		resultado.setCodMotivo(this.evaluarInt(item, "cod-motivo"));
		resultado.setSucursal(this.evaluarInt(item, "nu-sucursal"));
		resultado.setEstado(getParsedValueFromElement(item, "estado"));
		resultado.setNivelCod(this.evaluarInt(item, "cod-nivel"));
		resultado.setFechaEmision(getParsedValueFromElement(item, "fe-cotizacion"));
		resultado.setNivelDesc(getParsedValueFromElement(item, "desc-nivel"));
		resultado.setPlanPagoCod(getParsedValueFromElement(item, "cod-plan-pago"));
		resultado.setPlanPagoDesc(getParsedValueFromElement(item, "desc-plan-pago"));
		resultado.setMedioPagoCod(this.evaluarInt(item, "cod-medio-pago"));
		resultado.setMedioPagoDesc(getParsedValueFromElement(item, "desc-medio-pago"));
		resultado.setOrigenEndoso(getParsedValueFromElement(item, "cod-origen-endoso"));
		resultado.setFacturacionCod(getParsedValueFromElement(item, "cod-tp-facturacion"));
		resultado.setFacturacionDesc(getParsedValueFromElement(item, "desc-tp-facturacion"));
		resultado.setProductorCod(this.evaluarInt(item, "cod-productor"));
		resultado.setProductorDesc(getParsedValueFromElement(item, "desc-productor"));
		resultado.setCodBroker(this.evaluarInt(item, "cod-borker"));
		resultado.setDescBroker(getParsedValueFromElement(item, "desc-borker"));
		resultado.setMonedaCod(this.evaluarInt(item, "cod-moneda"));
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
		resultado.setPremioInformado(this.evaluarDouble(item, "premio-informado"));
		resultado.setRenovacionCod(getParsedValueFromElement(item, "cod-renovacion"));
		resultado.setRenovacionDesc(getParsedValueFromElement(item, "desc-renovacion"));
		resultado.setCodProducto(getParsedValueFromElement(item, "cod-producto"));
		resultado.setDescProducto(getParsedValueFromElement(item, "desc-producto"));
		resultado.setCodRamo(this.evaluarInt(item, "cod-ramo"));
		resultado.setDescRamo(getParsedValueFromElement(item, "desc-ramo"));
		resultado.setCodTipo(getParsedValueFromElement(item, "cod-tp-transac"));
		resultado.setDescTipo(getParsedValueFromElement(item, "desc-tp-transac"));
		resultado.setOrigenEndosoDesc(getParsedValueFromElement(item, "desc-origen-endoso"));
		
		resultado.setEsUnipersonal(getParsedValueFromElement(item, "es-unipersonal"));
		resultado.setEnviarFacturaEmail(getParsedValueFromElement(item, "envio-fact-mail"));
		resultado.setFacturaConRut(getParsedValueFromElement(item, "doc-defecto"));

		return resultado;
	}

	public Ubicacion parsearUbicacionBien(Element item) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearUbicacionBien()");
		Ubicacion resultado = new Ubicacion();

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

		return resultado;
	}

	public BienCert parsearBienCert(final Element item) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearBienCert()");

		final BienCert bienCert = new BienCert();

		bienCert.setConsecutivo(this.evaluarInt(item, "consec-bien"));
		bienCert.setDescripcion(getParsedValueFromElement(item, "desc-bien"));
		bienCert.setFechaBaja(getParsedValueFromElement(item, "fe-baja"));
		bienCert.setCertificado(this.evaluarInt(item, "certificado"));
		bienCert.setPosicionBien(this.evaluarInt(item, "pos-bien"));
		bienCert.setCliente(getParsedValueFromElement(item, "Cliente"));
		bienCert.setSeleccionado(getParsedValueFromElement(item, "st-certificado"));
		bienCert.setRequierePadron(getParsedValueFromElement(item, "requiere-padron"));
		if (getParsedValueFromElement(item, "cod-bien") != null) {
			bienCert.setCodigoBien(Integer.valueOf(getParsedValueFromElement(item, "cod-bien")));
		}
		bienCert.setHabilitoQuitarBien(getParsedValueFromElement(item, "habilito-borrar"));
		bienCert.setHabilitoListaBienes(getParsedValueFromElement(item, "habilito-lista-bienes"));
		bienCert.setHabilitoUbicacion(getParsedValueFromElement(item, "habilito-ubicacion"));
		bienCert.setHabilitoBeneficiarios(getParsedValueFromElement(item, "habilito-beneficiario"));
		bienCert.setHabilitoAcreedores(getParsedValueFromElement(item, "habilito-acreedor"));
		bienCert.setEtiquetaCapital(getParsedValueFromElement(item, "etiqueta-capital"));

		NodeList ubicaciones = item.getElementsByTagName("ubicacion");
		if (ubicaciones.getLength() > 0) {
			bienCert.setUbicacionBien(parsearUbicacionBien((Element) ubicaciones.item(0)));
		}

		final NodeList datos = item.getElementsByTagName("dato");
		
		if (datos.getLength() > 0) {
			final ArrayList<DatosBienCert> listaDatos = new ArrayList<DatosBienCert>();

			for (int j = 0; j < datos.getLength(); j++) {
				final Element unDato = (Element) datos.item(j);
				listaDatos.add(this.parsearDatosBienCert(unDato));
			}

			bienCert.setDatos(listaDatos);
		}

		return bienCert;
	}

	public DatosBienCert parsearDatosBienCert(final Element item) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearDatosBienCert()");

		final DatosBienCert datosBienCert = new DatosBienCert();

		datosBienCert.setValorCod(getParsedValueFromElement(item, "cod-valor"));
		datosBienCert.setDatoDesc(getParsedValueFromElement(item, "desc-dato"));
		datosBienCert.setValorDesc(getParsedValueFromElement(item, "desc-valor"));
		datosBienCert.setVisibilidad(getParsedValueFromElement(item, "visible"));
		datosBienCert.setDatoTipo(getParsedValueFromElement(item, "tipo-dato"));
		datosBienCert.setRequerido(getParsedValueFromElement(item, "requerido"));
		datosBienCert.setTieneReglas(getParsedValueFromElement(item, "tiene-reglas"));
		datosBienCert.setDatoCod(this.evaluarInt(item, "cod-dato"));
		datosBienCert.setDecimales(this.evaluarInt(item, "cant-decimales"));
		datosBienCert.setTabla(this.evaluarInt(item, "cod-tabla"));
		datosBienCert.setLongitud(this.evaluarInt(item, "longitud-dato"));
		datosBienCert.setDatoPosicion(this.evaluarInt(item, "posicion"));
		datosBienCert.setObligatorio(getParsedValueFromElement(item, "obligatorio-en"));
		datosBienCert.setCantidadFiltros(this.evaluarInt(item, "cant-filtros-tabla"));
		return datosBienCert;
	}

	public DatosContratante parsearDatosContratante(final Element item) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearDatosContratante()");

		DatosContratante datosContratante = null;

		datosContratante = new DatosContratante();

		datosContratante.setClienteCod(this.evaluarInt(item, "nu-cedula-rif"));
		datosContratante.setClienteDesc(getParsedValueFromElement(item, "nombre-persona"));
		datosContratante.setDepartamentoCod(this.evaluarInt(item, "cod-provincia"));
		datosContratante.setDepartamentoDesc(getParsedValueFromElement(item, "desc-estado"));
		datosContratante.setLocalidadDesc(getParsedValueFromElement(item, "desc-municipio"));
		datosContratante.setNacionalidadCod(getParsedValueFromElement(item, "cod-nacionalidad"));
		datosContratante.setLocalidadCod(this.evaluarInt(item, "cod-municipio"));
		datosContratante.setStCapital(this.evaluarInt(item, "st-capital"));
		datosContratante.setNumPersona(this.evaluarInt(item, "nu-persona"));
		datosContratante.setPaisCod(this.evaluarInt(item, "cod-pais"));
		datosContratante.setPaisDesc(getParsedValueFromElement(item, "desc-pais"));
		datosContratante.setRadioCod(this.evaluarInt(item, "cod-postal"));
		datosContratante.setDescripDpto(getParsedValueFromElement(item, "desc-departamento"));
		datosContratante.setTelefono(getParsedValueFromElement(item, "telefono"));
		datosContratante.setRadioDesc(getParsedValueFromElement(item, "desc-localidad"));
		datosContratante.setDireccionDesc(getParsedValueFromElement(item, "desc-calle"));
		datosContratante.setAclaracionDic(getParsedValueFromElement(item, "aclaracion-dir"));
		datosContratante.setNumPostal(this.evaluarInt(item, "nu-postal"));
		datosContratante.setCodCalle(this.evaluarInt(item, "cod-calle"));
		datosContratante.setDireccionNum(this.evaluarInt(item, "nu-calle"));
		datosContratante.setDireccionPiso(getParsedValueFromElement(item, "nu-piso"));
		datosContratante.setDireccionUnidad(getParsedValueFromElement(item, "nu-unidad"));
		datosContratante.setAnulacionCorrida(getParsedValueFromElement(item, "in-anul-corrida"));
		datosContratante.setTieneDeuda(getParsedValueFromElement(item, "tiene-deuda"));
		datosContratante.setNoTieneDocumento(getParsedValueFromElement(item, "no-tiene-documento"));
		datosContratante.setValidaComunicaciones(getParsedValueFromElement(item, "validacion-comunicaciones"));
		return datosContratante;
	}

	public ArrayList<Beneficiario> parsearBeneficiarios(Element itemBien) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearBeneficiarios()");

		ArrayList<Beneficiario> resultado = null;

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

		return resultado;
	}

	public ArrayList<ObjetoBien> parsearObjetosBien(Element itemBien) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearObjetosBien()");

		ArrayList<ObjetoBien> resultado = null;

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

		return resultado;
	}

	public ArrayList<AcreedorBien> parsearAcreedores(Element itemBien) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearAcreedores()");

		ArrayList<AcreedorBien> resultado = null;

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

		return resultado;

	}

	public String getParsedValue(Document doc, String clave) {
		return valueParser.getParsedValue(doc, clave);
	}

	public String getParsedValueFromElement(Element elemento, String clave) {
		return valueParser.getParsedValueFromElement(elemento, clave);
	}

	public Integer evaluarInt(Element item, String tag) {
		return valueParser.evaluarInt(item, tag);
	}

	public Double evaluarDouble(Element item, String tag) {
		return valueParser.evaluarDouble(item, tag);
	}

	public ParseoEntidades() {
		super();
		valueParser = new ParserXmlValueUtil();
	}

	public ArrayList<Cobertura> parsearCoberturas(Element item) {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotizaciones.class);
		logueo.setMetodo("parsearCoberturas()");

		ArrayList<Cobertura> resultado = null;

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

			// FIXME OIGRES esto no esta viniendo en todos los PL ejemplo el
			// de PAC_WEB_COTIZACION.PRO_DATOS_COTIZACION
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

		return resultado;
	}

}
