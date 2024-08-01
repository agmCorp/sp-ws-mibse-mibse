package uy.bse.rector.servicios.parseo;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import uy.com.bse.cotizaciones.consultas.Bien;
import uy.com.bse.cotizaciones.consultas.BienCert;
import uy.com.bse.cotizaciones.consultas.Cliente;
import uy.com.bse.cotizaciones.consultas.Cobertura;
import uy.com.bse.cotizaciones.consultas.DatosBienCert;
import uy.com.bse.cotizaciones.entidades.DatoBien;
import uy.com.bse.cotizaciones.entidades.DatoDependiente;
import uy.com.bse.cotizaciones.entidades.DatosCotizacionVehiculo;
import uy.com.bse.cotizaciones.operaciones.BienCobertura;
import uy.com.bse.cotizaciones.operaciones.Certificado;
import uy.com.bse.cotizaciones.operaciones.DatosValidos;
import uy.com.bse.cotizaciones.operaciones.InfoGuardarCobertura;
import uy.com.bse.cotizaciones.operaciones.PlanCobertura;
import uy.com.bse.cotizaciones.operaciones.PlanPago;
import uy.com.bse.cotizaciones.operaciones.ResultActualizarDatos;
import uy.com.bse.cotizaciones.operaciones.ResultActualizarPromocionVehiculo;
import uy.com.bse.cotizaciones.operaciones.ResultAgregarBien;
import uy.com.bse.cotizaciones.operaciones.ResultAgregarCertificado;
import uy.com.bse.cotizaciones.operaciones.ResultCalcularCertificado;
import uy.com.bse.cotizaciones.operaciones.ResultCalcularCobertura;
import uy.com.bse.cotizaciones.operaciones.ResultCalcularCotizacion;
import uy.com.bse.cotizaciones.operaciones.ResultCalcularCuotasVehiculo;
import uy.com.bse.cotizaciones.operaciones.ResultGuardarCoberturas;
import uy.com.bse.cotizaciones.operaciones.ResultNuevaCotizacion;
import uy.com.bse.cotizaciones.operaciones.ResultNuevaCotizacionVehiculo;
import uy.com.bse.cotizaciones.operaciones.ResultQuitarBien;
import uy.com.bse.cotizaciones.operaciones.ResultValidarDatos;
import uy.com.bse.cotizaciones.operaciones.ResultValidarDatosVehiculo;
import uy.com.bse.cotizaciones.operaciones.Validacion;
import uy.com.bse.maestro.personas.comunicaciones.ComunicacionEC;
import uy.com.bse.maestro.personas.domicilio.DireccionEC;
import uy.com.bse.polizas.entidades.Cuota;
import uy.com.bse.servicios.rector.emision.Emision;
import uy.com.bse.servicios.rector.emision.ResultEmision;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.excepcion.ErrorResolver;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.parseo.ParseoXmlGenerico;

public class ParseoCotOperaciones extends ParseoXmlGenerico {

	private static final Logger LOG = LogManager.getLogger(ParseoCotOperaciones.class);

	public ParseoCotOperaciones(final String textoParsear) {
		super(textoParsear);
	}

	public ResultNuevaCotizacion parsearNuevaCotizacion() {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotOperaciones.class);
		logueo.setMetodo("parsearNuevaCotizacion");

		final ResultNuevaCotizacion resultado = new ResultNuevaCotizacion();

		try {
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
						aseg.setCodProfesion(Integer.valueOf(getParsedValueFromElement(itemDatosAseg, "cod-profesion")));
					}
					aseg.setProfesion(getParsedValueFromElement(itemDatosAseg, "desc-profesion"));
					aseg.setCodCliente(getParsedValueFromElement(itemDatosAseg, "nu-cedula-rif"));
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

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;
	}

	public ResultAgregarBien parsearAgregarBien() {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotOperaciones.class);
		logueo.setMetodo("parsearAgregarBien");

		final ResultAgregarBien resultado = new ResultAgregarBien();

		try {
			NodeList cotizacion = this.doc.getElementsByTagName("cotizacion");
			Element elemento = (Element) cotizacion.item(0);
			ParseoEntidades parser = new ParseoEntidades();
			resultado.setHabilitoInsertar(getParsedValueFromElement(elemento, "habilito-insertar"));

			final NodeList bienes = this.doc.getElementsByTagName("bien");

			for (int i = 0; i < bienes.getLength(); i++) {
				final Element bienCert = (Element) bienes.item(i);
				resultado.setUnBien(parser.parsearBienCert(bienCert));
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

	public ResultAgregarCertificado parsearAgregarCertificado() {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotOperaciones.class);
		logueo.setMetodo("parsearAgregarCertificado");

		final ResultAgregarCertificado resultado = new ResultAgregarCertificado();

		try {
			NodeList cotizacion = this.doc.getElementsByTagName("cotizacion");
			Element cot = (Element) cotizacion.item(0);
			ParseoEntidades parser = new ParseoEntidades();
			resultado.setExisteAsegurado(getParsedValueFromElement(cot, "existe-asegurado"));
			resultado.setPermiteAnular(getParsedValueFromElement(cot, "permite-anular"));
			resultado.setHabilitoInsertar(getParsedValueFromElement(cot, "habilito-insertar"));
			resultado.setRequiereAsegurado(getParsedValueFromElement(cot, "requiere-asegurado"));
			final NodeList bienes = this.doc.getElementsByTagName("bien");

			for (int i = 0; i < bienes.getLength(); i++) {
				final Element bienCert = (Element) bienes.item(i);
				resultado.setUnBien(parser.parsearBienCert(bienCert));
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

	public ResultQuitarBien parsearQuitarBien() {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotOperaciones.class);
		logueo.setMetodo("parsearQuitarBien");

		final ResultQuitarBien resultado = new ResultQuitarBien();

		try {
			NodeList cotizacion = this.doc.getElementsByTagName("cotizacion");
			Element elemento = (Element) cotizacion.item(0);
			ParseoEntidades parser = new ParseoEntidades();
			resultado.setHabilitoInsertar(getParsedValueFromElement(elemento, "habilito-insertar"));

			final NodeList bienes = this.doc.getElementsByTagName("bien");

			for (int i = 0; i < bienes.getLength(); i++) {
				final Element bienCert = (Element) bienes.item(i);
				resultado.setUnBienCert(parser.parsearBienCert(bienCert));
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

	public ResultValidarDatos parsearValidarDatos() {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotOperaciones.class);
		logueo.setMetodo("parsearValidarDatos");

		ResultValidarDatos resultado = new ResultValidarDatos();

		try {
			NodeList validaciones = this.doc.getElementsByTagName("validacion");
			DatosValidos dValidos = new DatosValidos();

			NodeList errorValidacion = this.doc.getElementsByTagName("cotizacion");
			Element errorItem = (Element) errorValidacion.item(0);
			dValidos.setErrorValidacion((getParsedValueFromElement(errorItem, "existe-error")));
			resultado.setHabilitoInsertar(getParsedValueFromElement(errorItem, "habilito-insertar"));
			resultado.setCodPlanCobertura(getParsedValueFromElement(errorItem, "cod-plan-cobertura"));

			NodeList datos = this.doc.getElementsByTagName("plan-cobertura-list");
			Element itemDatos = (Element) datos.item(0);
			dValidos.setDescripMoneda(getParsedValueFromElement(itemDatos, "desc-moneda"));
			dValidos.setSimboloMoneda(getParsedValueFromElement(itemDatos, "simbolo-moneda"));
			if (getParsedValueFromElement(itemDatos, "dia-vto") != null) {
				dValidos.setDiaVencimiento(Integer.valueOf(getParsedValueFromElement(itemDatos, "dia-vto")));
			}

			for (int i = 0; i < validaciones.getLength(); i++) {
				Validacion validacion = new Validacion();
				Element valItem = (Element) validaciones.item(i);

				validacion.setMensajeValidacion(getParsedValueFromElement(valItem, "mensaje"));
				dValidos.setUnaValidacion(validacion);

			}

			NodeList coberturas = this.doc.getElementsByTagName("plan-cobertura");
			for (int i = 0; i < coberturas.getLength(); i++) {
				PlanCobertura planCob = new PlanCobertura();
				Element valItem = (Element) coberturas.item(i);
				planCob.setCodigo(getParsedValueFromElement(valItem, "codigo"));
				planCob.setDescripcion(getParsedValueFromElement(valItem, "descripcion"));
				if (getParsedValueFromElement(valItem, "monto-premio") != null) {
					planCob.setMontoPremio(Double.valueOf(getParsedValueFromElement(valItem, "monto-premio")));
				}
				if (getParsedValueFromElement(valItem, "monto-prima") != null) {
					planCob.setMontoPrima(Double.valueOf(getParsedValueFromElement(valItem, "monto-prima")));
				}
				planCob.setErrorPlanCob(getParsedValueFromElement(valItem, "error-plan-cob"));
				planCob.setExcluido(getParsedValueFromElement(valItem, "excluido"));

				dValidos.setUnPlanCobertura(planCob);

			}

			NodeList bienes = this.doc.getElementsByTagName("bien");
			for (int i = 0; i < bienes.getLength(); i++) {
				Bien bien = new Bien();
				Element valItem = (Element) bienes.item(i);
				if (getParsedValueFromElement(valItem, "certificado") != null) {
					bien.setCertificado(Integer.valueOf(getParsedValueFromElement(valItem, "certificado")));
				}
				if (getParsedValueFromElement(valItem, "consec-bien") != null) {
					bien.setConsecutivo(Integer.valueOf(getParsedValueFromElement(valItem, "consec-bien")));
				}
				if (getParsedValueFromElement(valItem, "cod-bien") != null) {
					bien.setCodBien(Integer.valueOf(getParsedValueFromElement(valItem, "cod-bien")));
				}

				bien.setBienDescripcion(getParsedValueFromElement(valItem, "desc-bien"));
				bien.setFechaBaja(getParsedValueFromElement(valItem, "fe-baja"));

				if (getParsedValueFromElement(valItem, "pos-bien") != null) {
					bien.setPosBien(Integer.valueOf(getParsedValueFromElement(valItem, "pos-bien")));
				}
				bien.setEtiquetaCapital(getParsedValueFromElement(valItem, "etiqueta-capital"));
				bien.setHabilitoQuitar(getParsedValueFromElement(valItem, "habilito-borrar"));
				bien.setHabilitoListaBienes(getParsedValueFromElement(valItem, "habilito-lista-bienes"));
				bien.setHabilitoUbicacion(getParsedValueFromElement(valItem, "habilito-ubicacion"));
				bien.setHabilitoBeneficiarios(getParsedValueFromElement(valItem, "habilito-beneficiario"));
				bien.setHabilitoAcreedores(getParsedValueFromElement(valItem, "habilito-acreedor"));

				NodeList coberturasBien = valItem.getElementsByTagName("cobertura");

				for (int j = 0; j < coberturasBien.getLength(); j++) {
					Cobertura cobBien = new Cobertura();
					Element cobItem = (Element) coberturasBien.item(j);
					if (getParsedValueFromElement(cobItem, "cod-ramo-cob") != null) {
						cobBien.setRamoCod((Integer.valueOf(getParsedValueFromElement(cobItem, "cod-ramo-cob"))));
					}

					cobBien.setCoberturaCod(getParsedValueFromElement(cobItem, "cod-cobertura"));

					cobBien.setCoberturaDesc(getParsedValueFromElement(cobItem, "desc-cobertura"));
					if (getParsedValueFromElement(cobItem, "nu-elemento") != null) {
						cobBien.setElementosNum((Integer.valueOf(getParsedValueFromElement(cobItem, "nu-elemento"))));
					}
					if (getParsedValueFromElement(cobItem, "capital") != null) {
						cobBien.setCapital(Double.valueOf(getParsedValueFromElement(cobItem, "capital")));
					}

					cobBien.setTasaPrima(getParsedValueFromElement(cobItem, "tasa-prima"));
					if (getParsedValueFromElement(cobItem, "monto-prima") != null) {
						cobBien.setPrima((Double.valueOf(getParsedValueFromElement(cobItem, "monto-prima"))));
					}
					cobBien.setRequerido(getParsedValueFromElement(cobItem, "requerido"));
					cobBien.setEditable(getParsedValueFromElement(cobItem, "editable"));

					bien.setUnaCobertura(cobBien);

				}
				dValidos.setUnBien(bien);

			}

			resultado.setDatosValidos(dValidos);

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;

	}

	public ResultGuardarCoberturas parsearGuardarCoberturas() {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotOperaciones.class);
		logueo.setMetodo("parsearGuardarCoberturas");

		ResultGuardarCoberturas resultado = new ResultGuardarCoberturas();
		InfoGuardarCobertura guardarCobertura = new InfoGuardarCobertura();
		try {
			NodeList planesCobertura = this.doc.getElementsByTagName("plan-cobertura-list");
			for (int i = 0; i < planesCobertura.getLength(); i++) {
				Element valItem = (Element) planesCobertura.item(i);
				guardarCobertura.setTieneFranquicia(getParsedValueFromElement(valItem, "tiene-franquicias"));
				guardarCobertura.setHabilitoInsertar(getParsedValueFromElement(valItem, "habilito-insertar"));

			}
			NodeList coberturasPlan = this.doc.getElementsByTagName("plan-cobertura");
			for (int i = 0; i < coberturasPlan.getLength(); i++) {
				Element valItem = (Element) coberturasPlan.item(i);
				PlanCobertura pCobertura = new PlanCobertura();
				pCobertura.setCodigo(getParsedValueFromElement(valItem, "codigo"));
				pCobertura.setDescripcion(getParsedValueFromElement(valItem, "descripcion"));
				pCobertura.setExcluido(getParsedValueFromElement(valItem, "excluido"));
				if (getParsedValueFromElement(valItem, "monto-prima") != null) {
					pCobertura.setMontoPrima(Double.valueOf(getParsedValueFromElement(valItem, "monto-prima")));
				}
				if (getParsedValueFromElement(valItem, "monto-premio") != null) {
					pCobertura.setMontoPremio(Double.valueOf(getParsedValueFromElement(valItem, "monto-premio")));
				}
				pCobertura.setErrorPlanCob(getParsedValueFromElement(valItem, "error-plan-cob"));
				guardarCobertura.setUnElemento(pCobertura);

			}

			NodeList bienes = this.doc.getElementsByTagName("bien");
			for (int i = 0; i < bienes.getLength(); i++) {

				Element valItem = (Element) bienes.item(i);
				BienCobertura bien = new BienCobertura();
				if (getParsedValueFromElement(valItem, "certificado") != null) {
					bien.setCertificado((Integer.valueOf(getParsedValueFromElement(valItem, "certificado"))));
				}
				if (getParsedValueFromElement(valItem, "consec-bien") != null) {
					bien.setConsecutivo((Integer.valueOf(getParsedValueFromElement(valItem, "consec-bien"))));
				}
				bien.setDescripcion(getParsedValueFromElement(valItem, "desc-bien"));
				bien.setFechaBaja(getParsedValueFromElement(valItem, "fe-baja"));
				if (getParsedValueFromElement(valItem, "cod-bien") != null) {
					bien.setCodigoBien((Integer.valueOf(getParsedValueFromElement(valItem, "cod-bien"))));
				}
				if (getParsedValueFromElement(valItem, "pos-bien") != null) {
					bien.setPosicionBien((Integer.valueOf(getParsedValueFromElement(valItem, "pos-bien"))));
				}
				bien.setEtiquetaCapital(getParsedValueFromElement(valItem, "etiqueta-capital"));
				bien.setHabilitoQuitarBien(getParsedValueFromElement(valItem, "habilito-borrar"));
				bien.setHabilitoListaBienes(getParsedValueFromElement(valItem, "habilito-lista-bienes"));
				bien.setHabilitoUbicacion(getParsedValueFromElement(valItem, "habilito-ubicacion"));
				bien.setHabilitoAcreedores(getParsedValueFromElement(valItem, "habilito-acreedor"));
				bien.setHabilitoBeneficiarios(getParsedValueFromElement(valItem, "habilito-beneficiario"));

				NodeList coberturas = valItem.getElementsByTagName("cobertura");

				for (int j = 0; j < coberturas.getLength(); j++) {
					Cobertura coberturaBien = new Cobertura();
					Element cobItem = (Element) coberturas.item(j);
					if (getParsedValueFromElement(cobItem, "cod-ramo-cob") != null) {
						coberturaBien.setRamoCod((Integer.valueOf(getParsedValueFromElement(cobItem, "cod-ramo-cob"))));
					}

					coberturaBien.setCoberturaCod(getParsedValueFromElement(cobItem, "cod-cobertura"));

					coberturaBien.setCoberturaDesc(getParsedValueFromElement(cobItem, "desc-cobertura"));
					if (getParsedValueFromElement(cobItem, "nu-elemento") != null) {
						coberturaBien.setElementosNum((Integer.valueOf(getParsedValueFromElement(cobItem, "nu-elemento"))));
					}
					coberturaBien.setSumaAsegurada(getParsedValueFromElement(cobItem, "capital"));
					coberturaBien.setTasaPrima(getParsedValueFromElement(cobItem, "tasa-prima"));
					if (getParsedValueFromElement(cobItem, "monto-prima") != null) {
						coberturaBien.setPrima((Double.valueOf(getParsedValueFromElement(cobItem, "monto-prima"))));
					}
					coberturaBien.setRequerido(getParsedValueFromElement(cobItem, "requerido"));
					coberturaBien.setEditable(getParsedValueFromElement(cobItem, "editable"));
					bien.setUnElemento(coberturaBien);
				}

				guardarCobertura.setUnElemento(bien);

			}
			resultado.setUnElemento(guardarCobertura);

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}
		return resultado;
	}

	public ResultCalcularCotizacion parsearCalcularCotizacion() {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotOperaciones.class);
		logueo.setMetodo("parsearCalcularCotizacion");

		ResultCalcularCotizacion resultado = new ResultCalcularCotizacion();

		try {
			NodeList cobertura = this.doc.getElementsByTagName("plan-cobertura");

			for (int i = 0; i < cobertura.getLength(); i++) {
				Element valItem = (Element) cobertura.item(i);
				PlanCobertura cob = new PlanCobertura();

				cob.setCodigo(getParsedValueFromElement(valItem, "cod-plan-cob"));
				cob.setDescripcion(getParsedValueFromElement(valItem, "desc-plan-cob"));
				cob.setErrorPlanCob(getParsedValueFromElement(valItem, "error-plan-cob"));

				if (getParsedValueFromElement(valItem, "monto-prima") != null) {
					cob.setMontoPrima(Double.valueOf(getParsedValueFromElement(valItem, "monto-prima")));
				}
				if (getParsedValueFromElement(valItem, "monto-premio") != null) {
					cob.setMontoPremio(Double.valueOf(getParsedValueFromElement(valItem, "monto-premio")));
				}

				NodeList planPago = this.doc.getElementsByTagName("plan-pago");

				for (int j = 0; j < planPago.getLength(); j++) {
					PlanPago pPago = new PlanPago();
					Element pPagoItem = (Element) planPago.item(j);

					pPago.setCodigo(getParsedValueFromElement(pPagoItem, "codigo"));

					pPago.setFormaPago(getParsedValueFromElement(pPagoItem, "descripcion"));

					if (getParsedValueFromElement(pPagoItem, "monto-cuota") != null) {
						pPago.setMontoCuota(Double.valueOf(getParsedValueFromElement(pPagoItem, "monto-cuota")));
					}
					if (getParsedValueFromElement(pPagoItem, "monto-total") != null) {
						pPago.setMontoTotal(Double.valueOf(getParsedValueFromElement(pPagoItem, "monto-total")));
					}
					pPago.setError(getParsedValueFromElement(pPagoItem, "error-plan-pago"));

					cob.setUnPlanPago(pPago);

				}

				resultado.setUnPlanCobertura(cob);
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

	public ResultCalcularCobertura parsearCalcularCobertura() {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotOperaciones.class);
		logueo.setMetodo("parsearCalcularCobertura");

		ResultCalcularCobertura resultado = new ResultCalcularCobertura();

		try {

			NodeList nodoCoti = this.doc.getElementsByTagName("cotizacion");
			Element itemCot = (Element) nodoCoti.item(0);

			resultado.setCodPlanPago(getParsedValueFromElement(itemCot, "cod-plan-pago"));

			if (getParsedValueFromElement(itemCot, "dia-vto") != null) {
				resultado.setDiaVto(Integer.valueOf(getParsedValueFromElement(itemCot, "dia-vto")));
			}

			if (getParsedValueFromElement(itemCot, "cod-nivel-comision") != null) {
				resultado.setCodNivelComision(Integer.valueOf(getParsedValueFromElement(itemCot, "cod-nivel-comision")));
			}

			resultado.setDescNivelComision(getParsedValueFromElement(itemCot, "desc-nivel-comision"));

			NodeList nodo = this.doc.getElementsByTagName("plan-cobertura-list");
			Element Item = (Element) nodo.item(0);
			resultado.setHabilitoInsertar(getParsedValueFromElement(Item, "habilito-insertar"));
			resultado.setTieneFranquicias(getParsedValueFromElement(Item, "tiene-franquicias"));
			DatosValidos dValidos = new DatosValidos();

			NodeList coberturas = Item.getElementsByTagName("plan-cobertura");
			for (int i = 0; i < coberturas.getLength(); i++) {
				PlanCobertura planCob = new PlanCobertura();
				Element valItem = (Element) coberturas.item(i);
				planCob.setCodigo(getParsedValueFromElement(valItem, "codigo"));
				planCob.setDescripcion(getParsedValueFromElement(valItem, "descripcion"));
				planCob.setExcluido(getParsedValueFromElement(valItem, "excluido"));
				if (getParsedValueFromElement(valItem, "monto-premio") != null) {
					planCob.setMontoPremio(Double.valueOf(getParsedValueFromElement(valItem, "monto-premio")));
				}
				if (getParsedValueFromElement(valItem, "monto-prima") != null) {
					planCob.setMontoPrima(Double.valueOf(getParsedValueFromElement(valItem, "monto-prima")));
				}
				planCob.setErrorPlanCob(getParsedValueFromElement(valItem, "error-plan-cob"));

				dValidos.setUnPlanCobertura(planCob);

			}

			NodeList bienes = this.doc.getElementsByTagName("bien");
			for (int i = 0; i < bienes.getLength(); i++) {
				Bien bien = new Bien();
				Element valItem = (Element) bienes.item(i);
				if (getParsedValueFromElement(valItem, "certificado") != null) {
					bien.setCertificado(Integer.valueOf(getParsedValueFromElement(valItem, "certificado")));
				}
				if (getParsedValueFromElement(valItem, "consec-bien") != null) {
					bien.setConsecutivo(Integer.valueOf(getParsedValueFromElement(valItem, "consec-bien")));
				}
				if (getParsedValueFromElement(valItem, "cod-bien") != null) {
					bien.setCodBien(Integer.valueOf(getParsedValueFromElement(valItem, "cod-bien")));
				}

				bien.setBienDescripcion(getParsedValueFromElement(valItem, "desc-bien"));
				bien.setFechaBaja(getParsedValueFromElement(valItem, "fe-baja"));

				if (getParsedValueFromElement(valItem, "pos-bien") != null) {
					bien.setPosBien(Integer.valueOf(getParsedValueFromElement(valItem, "pos-bien")));
				}
				bien.setEtiquetaCapital(getParsedValueFromElement(valItem, "etiqueta-capital"));
				bien.setHabilitoQuitar(getParsedValueFromElement(valItem, "habilito-borrar"));
				bien.setHabilitoListaBienes(getParsedValueFromElement(valItem, "habilito-lista-bienes"));
				bien.setHabilitoUbicacion(getParsedValueFromElement(valItem, "habilito-ubicacion"));
				bien.setHabilitoBeneficiarios(getParsedValueFromElement(valItem, "habilito-beneficiario"));
				bien.setHabilitoAcreedores(getParsedValueFromElement(valItem, "habilito-acreedor"));

				NodeList coberturasBien = valItem.getElementsByTagName("cobertura");

				for (int j = 0; j < coberturasBien.getLength(); j++) {
					Cobertura cobBien = new Cobertura();
					Element cobItem = (Element) coberturasBien.item(j);
					if (getParsedValueFromElement(cobItem, "cod-ramo-cob") != null) {
						cobBien.setRamoCod((Integer.valueOf(getParsedValueFromElement(cobItem, "cod-ramo-cob"))));
					}

					cobBien.setCoberturaCod(getParsedValueFromElement(cobItem, "cod-cobertura"));

					cobBien.setCoberturaDesc(getParsedValueFromElement(cobItem, "desc-cobertura"));
					if (getParsedValueFromElement(cobItem, "nu-elemento") != null) {
						cobBien.setElementosNum((Integer.valueOf(getParsedValueFromElement(cobItem, "nu-elemento"))));
					}
					if (getParsedValueFromElement(cobItem, "capital") != null) {
						cobBien.setCapital(Double.valueOf(getParsedValueFromElement(cobItem, "capital")));
					}
					cobBien.setTasaPrima(getParsedValueFromElement(cobItem, "tasa-prima"));
					if (getParsedValueFromElement(cobItem, "monto-prima") != null) {
						cobBien.setPrima((Double.valueOf(getParsedValueFromElement(cobItem, "monto-prima"))));
					}
					cobBien.setRequerido(getParsedValueFromElement(cobItem, "requerido"));
					cobBien.setEditable(getParsedValueFromElement(cobItem, "editable"));

					bien.setUnaCobertura(cobBien);

				}
				dValidos.setUnBien(bien);

			}

			resultado.setDatosValidos(dValidos);

		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, resultado);
		}

		return resultado;

	}

	public ResultCalcularCertificado parsearCalcularCertificado() {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotOperaciones.class);
		logueo.setMetodo("parsearCalcularCertificado");

		ResultCalcularCertificado resultado = new ResultCalcularCertificado();

		try {

			NodeList coti = this.doc.getElementsByTagName("cotizacion");
			Element Item = (Element) coti.item(0);

			if (getParsedValueFromElement(Item, "mt-cuota-cert") != null) {
				resultado.setMontoCuotaCert(Double.valueOf(getParsedValueFromElement(Item, "mt-cuota-cert")));
			}

			if (getParsedValueFromElement(Item, "mt-total-cert") != null) {
				resultado.setMontoTotalCert(Double.valueOf(getParsedValueFromElement(Item, "mt-total-cert")));
			}

			NodeList certificado = this.doc.getElementsByTagName("certificado");
			for (int i = 0; i < certificado.getLength(); i++) {
				Element valItem = (Element) certificado.item(i);
				Certificado cert = new Certificado();

				if (getParsedValueFromElement(valItem, "nu-certificado") != null) {
					cert.setNumCertificado(Integer.valueOf(getParsedValueFromElement(valItem, "nu-certificado")));
				}
				cert.setCertificadoNuevo(getParsedValueFromElement(valItem, "es-cert-nuevo"));
				cert.setAnulado(getParsedValueFromElement(valItem, "estado-anulado"));
				NodeList cobertura = valItem.getElementsByTagName("plan-cobertura-cert");

				for (int j = 0; j < cobertura.getLength(); j++) {
					Element itemCob = (Element) cobertura.item(j);
					PlanCobertura cob = new PlanCobertura();

					cob.setCodigo(getParsedValueFromElement(itemCob, "cod-plan-cobertura-cert"));
					cob.setDescripcion(getParsedValueFromElement(itemCob, "desc-plan-cobertura-cert"));
					if (getParsedValueFromElement(itemCob, "mt-deducible") != null) {
						cob.setMontoDeducible(Double.valueOf(getParsedValueFromElement(itemCob, "mt-deducible")));
					}

					if (getParsedValueFromElement(itemCob, "mt-prima") != null) {
						cob.setMontoPrima(Double.valueOf(getParsedValueFromElement(itemCob, "mt-prima")));
					}
					if (getParsedValueFromElement(itemCob, "mt-premio") != null) {
						cob.setMontoPremio(Double.valueOf(getParsedValueFromElement(itemCob, "mt-premio")));
					}

					NodeList planPago = itemCob.getElementsByTagName("plan-pago");

					for (int k = 0; k < planPago.getLength(); k++) {
						PlanPago pPago = new PlanPago();
						Element pPagoItem = (Element) planPago.item(k);

						pPago.setCodigo(getParsedValueFromElement(pPagoItem, "cod-plan-pago"));

						pPago.setDescripcion(getParsedValueFromElement(pPagoItem, "desc-plan-pago"));

						if (getParsedValueFromElement(pPagoItem, "mt-cuota") != null) {
							pPago.setMontoCuota(Double.valueOf(getParsedValueFromElement(pPagoItem, "mt-cuota")));
						}
						if (getParsedValueFromElement(pPagoItem, "mt-total") != null) {
							pPago.setMontoTotal(Double.valueOf(getParsedValueFromElement(pPagoItem, "mt-total")));
						}

						cob.setUnPlanPago(pPago);
					}
					cert.setUnaCobertura(cob);
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

	public ResultActualizarDatos parsearActualizarDatos() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotOperaciones.class);
		logueo.setMetodo("parsearActualizarDatos");

		ResultActualizarDatos resultado = new ResultActualizarDatos();

		try {

			NodeList nodoDato = this.doc.getElementsByTagName("dato-dependiente");
			if (nodoDato.getLength() != 0) {
				for (int i = 0; i < nodoDato.getLength(); i++) {
					Element item = (Element) nodoDato.item(i);
					DatoDependiente dato = new DatoDependiente();

					if (getParsedValueFromElement(item, "cod-dato") != null) {
						dato.setCodDato(Integer.valueOf(getParsedValueFromElement(item, "cod-dato")));
					}
					if (getParsedValueFromElement(item, "valor-dato") != null) {
						dato.setValorDato(getParsedValueFromElement(item, "valor-dato"));
					}
					if (getParsedValueFromElement(item, "cod-bien") != null) {
						dato.setCodBien(Integer.valueOf(getParsedValueFromElement(item, "cod-bien")));
					}
					dato.setDescValor(getParsedValueFromElement(item, "desc-valor"));
					resultado.setUnDependiente(dato);
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

	public ResultNuevaCotizacionVehiculo parsearNuevaCotizacionVehiculo() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotOperaciones.class);
		logueo.setMetodo("parsearNuevaCotizacionVehiculo");
		ResultNuevaCotizacionVehiculo resultado = new ResultNuevaCotizacionVehiculo();
		try {

			NodeList nodoDato = this.doc.getElementsByTagName("cotizacion");
			if (nodoDato.getLength() != 0) {
				Element item = (Element) nodoDato.item(0);
				DatosCotizacionVehiculo dato = new DatosCotizacionVehiculo();
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

	public ResultValidarDatosVehiculo parsearValidarDatosVehiculo() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotOperaciones.class);
		logueo.setMetodo("parsearValidarDatosVehiculo");
		ResultValidarDatosVehiculo resultado = new ResultValidarDatosVehiculo();
		try {
			NodeList nodoDato = this.doc.getElementsByTagName("cotizacion");
			if (nodoDato.getLength() != 0) {
				Element item = (Element) nodoDato.item(0);
				resultado.setErrorValidacion(getParsedValueFromElement(item, "existe-error"));
				
				NodeList nodoDatos = this.doc.getElementsByTagName("dato");
				for (int i = 0; i < nodoDatos.getLength(); i++) {
					Element itemDato = (Element) nodoDatos.item(i);
					DatoBien dato = new DatoBien();
					dato.setCodDato(evaluarInt(itemDato, "cod-dato"));
					dato.setValorDato(getParsedValueFromElement(itemDato, "valor-retorno"));
					dato.setFiltro(getParsedValueFromElement(itemDato, "valor-filtro"));
					resultado.setUno(dato);
				}
				
				NodeList validaciones = this.doc.getElementsByTagName("validacion");
				for (int i = 0; i < validaciones.getLength(); i++) {
					Validacion validacion = new Validacion();
					Element valItem = (Element) validaciones.item(i);
					validacion.setMensajeValidacion(getParsedValueFromElement(valItem, "mensaje"));
					resultado.setUno(validacion);
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

	public ResultCalcularCuotasVehiculo parsearCalcularCuotasVehiculo() {
		final Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotOperaciones.class);
		logueo.setMetodo("parsearCalcularCuotasVehiculo");

		ResultCalcularCuotasVehiculo resultado = new ResultCalcularCuotasVehiculo();

		try {
			NodeList cot = this.doc.getElementsByTagName("cotizacion");
			if (cot.getLength()!=0) {
				Element item = (Element) cot.item(0);
				
				String texto =getParsedValueFromElement(item,"lista-cuotas");
				if (texto!=null&&!texto.equals("")) {
					String [] cuotas = texto.split(",");
					for (int i = 0; i < cuotas.length; i++) {
						resultado.setUno(Integer.valueOf(cuotas[i]));
					}
				}
				resultado.setFechaCotizacion(getParsedValueFromElement(item,"fe-cotizacion")); 
				resultado.setDatosReporte(getParsedValueFromElement(item,"query-reporte"));
			}

			NodeList certificados = this.doc.getElementsByTagName("certificado");
			for (int i = 0; i < certificados.getLength(); i++) {
				Element valItem = (Element) certificados.item(i);
				Certificado cert = new Certificado();
				  cert.setNumCertificado(evaluarInt(valItem, "nu-certificado"));
				
				NodeList cobertura = valItem.getElementsByTagName("plan-cobertura-cert");

				for (int j = 0; j < cobertura.getLength(); j++) {
					Element itemCob = (Element) cobertura.item(j);
					PlanCobertura cob = new PlanCobertura();

					cob.setCodigo(getParsedValueFromElement(itemCob, "cod-plan-cobertura-cert"));
					cob.setDescripcion(getParsedValueFromElement(itemCob, "desc-plan-cobertura-cert"));
					if (getParsedValueFromElement(itemCob, "mt-deducible") != null) {
						cob.setMontoDeducible(Double.valueOf(getParsedValueFromElement(itemCob, "mt-deducible")));
					}

					if (getParsedValueFromElement(itemCob, "mt-prima") != null) {
						cob.setMontoPrima(Double.valueOf(getParsedValueFromElement(itemCob, "mt-prima")));
					}
					if (getParsedValueFromElement(itemCob, "mt-premio") != null) {
						cob.setMontoPremio(Double.valueOf(getParsedValueFromElement(itemCob, "mt-premio")));
					}

					NodeList planPago = itemCob.getElementsByTagName("plan-pago");

					for (int k = 0; k < planPago.getLength(); k++) {
						PlanPago pPago = new PlanPago();
						Element pPagoItem = (Element) planPago.item(k);

						pPago.setCodigo(getParsedValueFromElement(pPagoItem, "cod-plan-pago"));

						pPago.setDescripcion(getParsedValueFromElement(pPagoItem, "desc-plan-pago"));

						if (getParsedValueFromElement(pPagoItem, "mt-cuota") != null) {
							pPago.setMontoCuota(Double.valueOf(getParsedValueFromElement(pPagoItem, "mt-cuota")));
						}
						if (getParsedValueFromElement(pPagoItem, "mt-total") != null) {
							pPago.setMontoTotal(Double.valueOf(getParsedValueFromElement(pPagoItem, "mt-total")));
						}

						cob.setUnPlanPago(pPago);
					}
					cert.setUnaCobertura(cob);
				}
				resultado.setUno(cert);
				
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

	public ResultGenerico parsearActualizarPromocionVehiculo() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotOperaciones.class);
		logueo.setMetodo("parsearActualizarPromocionVehiculo");
		ResultActualizarPromocionVehiculo resultado = new ResultActualizarPromocionVehiculo();
		try {
			NodeList nodoCotizacion = this.doc.getElementsByTagName("cotizacion");
			if (nodoCotizacion.getLength() != 0) {
				Element item = (Element) nodoCotizacion.item(0);
				resultado.setErrorValidacion(getParsedValueFromElement(item, "existe-error"));
				
				NodeList nodoDatos = this.doc.getElementsByTagName("dato");
				for (int i = 0; i < nodoDatos.getLength(); i++) {
					Element itemDato = (Element) nodoDatos.item(i);
					DatoBien dato = new DatoBien();
					dato.setCodDato(evaluarInt(itemDato, "cod-dato"));
					dato.setValorDato(getParsedValueFromElement(itemDato, "valor-retorno"));
					dato.setHabilitado(getParsedValueFromElement(itemDato, "habilitado"));
					dato.setFiltro(getParsedValueFromElement(itemDato, "valor-filtro"));
					resultado.setUno(dato);
				}
                
				NodeList validaciones = this.doc.getElementsByTagName("validacion");
				for (int i = 0; i < validaciones.getLength(); i++) {
					Validacion validacion = new Validacion();
					Element valItem = (Element) validaciones.item(i);
					validacion.setMensajeValidacion(getParsedValueFromElement(valItem, "mensaje"));
					resultado.setUno(validacion);
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
	
	public ResultEmision parsearEmitirPoliza (){
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParseoCotOperaciones.class);
		logueo.setMetodo("parsearEmitirPoliza");		
		
		ResultEmision retorno = new ResultEmision();
		
		try{
			
			 NodeList elementos = this.doc.getElementsByTagName("datos-poliza");
			 Element item = (Element)elementos.item(0);	
			 Emision emi = new Emision();
				 
			 if(getParsedValueFromElement(item,"sucursal")!= null){
				 emi.setCodSucursal(Integer.valueOf(getParsedValueFromElement(item,"sucursal")));
			 }
			 if(getParsedValueFromElement(item,"ramo")!= null){
				 emi.setCodRamo(Integer.valueOf(getParsedValueFromElement(item,"ramo")));
			 }
			 if(getParsedValueFromElement(item,"poliza")!= null){
				 emi.setNumPoliza(Integer.valueOf(getParsedValueFromElement(item,"poliza")));
			 }
			 if(getParsedValueFromElement(item,"certificado")!= null){
				 emi.setNumCertificado(Integer.valueOf(getParsedValueFromElement(item,"certificado")));
			 }
			 if(getParsedValueFromElement(item,"endoso")!= null){
				 emi.setNumEndoso(Integer.valueOf(getParsedValueFromElement(item,"endoso")));
			 }
			 if(getParsedValueFromElement(item,"diavto")!= null){
				 emi.setDiaVencimiento(Integer.valueOf(getParsedValueFromElement(item,"diavto")));
			 }
			 if(getParsedValueFromElement(item,"totalprimapura")!= null){
				 emi.setTotalPrimaPura(Double.valueOf(getParsedValueFromElement(item,"totalprimapura")));
			 }
			 if(getParsedValueFromElement(item,"totalpremio")!= null){
				 emi.setTotalPremio(Double.valueOf(getParsedValueFromElement(item,"totalpremio")));
			 }
			 if(getParsedValueFromElement(item,"totalpremiofacturacion")!= null){
				 emi.setTotalPremioFacturacion(Double.valueOf(getParsedValueFromElement(item,"totalpremiofacturacion")));
			 }
			 if(getParsedValueFromElement(item,"mensaje-extra")!= null){
				 emi.setFacturaElectronica(getParsedValueFromElement(item,"mensaje-extra"));
			 }
			 
			 NodeList elemCuotas = item.getElementsByTagName("cuota");
			 for(int i=0;i<elemCuotas.getLength();i++){
				 Element itemCuo = (Element)elemCuotas.item(i);	
				 Cuota cuo = new Cuota();	
				 
				 cuo.setNumCuota(getParsedValueFromElement(itemCuo,"nrocuota"));
				 cuo.setMotivo(getParsedValueFromElement(itemCuo,"motivo"));
				 
				 if(getParsedValueFromElement(itemCuo,"factura")!=null){
					 cuo.setFactura(Integer.valueOf(getParsedValueFromElement(itemCuo,"factura")));
				 }
				 
				 cuo.setFechaHasta(getParsedValueFromElement(itemCuo,"fechahasta"));
				 
				 if(getParsedValueFromElement(item,"primapura")!= null){
					 cuo.setCantPrimaPura(Double.valueOf(getParsedValueFromElement(itemCuo,"primapura")));
				 }
				 if(getParsedValueFromElement(item,"premio")!= null){
					 cuo.setPremio(Double.valueOf(getParsedValueFromElement(itemCuo,"premio")));
				 }
				 
				 if(getParsedValueFromElement(item,"premiofacturacion")!= null){
					 cuo.setCantFacturar(Double.valueOf(getParsedValueFromElement(itemCuo,"premiofacturacion")));
				 }
				 
				 emi.setUnaCuota(cuo);
				 
			 }
			 
			retorno.setEmision(emi); 
			 
			 
		} catch (NumberFormatException ex) {
			claveError = catchNumberFormatException(logueo, ex);
		} catch (Exception ex) {
			claveError = catchException(logueo, ex);
		} finally {
			finallyBlock(claveError, retorno);
		}		
		
		return retorno;
	}
	

}
