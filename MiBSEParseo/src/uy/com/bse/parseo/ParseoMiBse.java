package uy.com.bse.parseo;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import uy.com.bse.mibse.DatosCliente;
import uy.com.bse.mibse.DatosComunicacion;
import uy.com.bse.mibse.DatosFacturacion;
import uy.com.bse.mibse.ResultObtenerComunicacionesCliente;
import uy.com.bse.mibse.ResultObtenerDatosCliente;
import uy.com.bse.mibse.ResultObtenerMapaMsgSiniestro;
import uy.com.bse.mibse.ResultObtenerPolizasCliente;
import uy.com.bse.mibse.ResultObtenerPolizasFacturasPagasCliente;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.parseo.ParseoXmlGenerico;

public class ParseoMiBse extends ParseoXmlGenerico {

	public ParseoMiBse(String textoParsear) {
		super(textoParsear);
	}

	public ResultObtenerDatosCliente parsearObtenerDatosCliente() {

		ResultObtenerDatosCliente resultado = new ResultObtenerDatosCliente();
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(getClass());
		logueo.setMetodo("parsearObtenerDatosCliente");

		try {
			NodeList elementos = this.doc.getElementsByTagName("persona");
			if (elementos.getLength() > 0) {
				Element item = (Element) elementos.item(0);
				DatosCliente datos = new DatosCliente();
				datos.setNombre(getParsedValueFromElement(item, "nm-persona"));
				datos.setCodProfesion(evaluarInt(item, "cod-profesion"));
				datos.setDescProfesion(getParsedValueFromElement(item, "desc-profesion"));
				datos.setCodSexo(getParsedValueFromElement(item, "cod-sexo"));
				datos.setCodTipoDocumento(getParsedValueFromElement(item, "cod-tp-documento"));
				datos.setDescTipoDocumento(getParsedValueFromElement(item, "desc-tp-documento"));
				datos.setDescSexo(getParsedValueFromElement(item, "desc-sexo"));
				datos.setFechaNacimiento(getParsedValueFromElement(item, "fe-nacimiento"));
				datos.setApellido(getParsedValueFromElement(item, "nm-apellido"));
				datos.setNumDocumento(getParsedValueFromElement(item, "nu-documento"));
				datos.setRazonSocial(getParsedValueFromElement(item, "nm-apellido"));
				datos.setRut(getParsedValueFromElement(item, "nu-rut"));
				datos.setNumPersona(evaluarInt(item, "nu-persona"));
				datos.setNumCI(getParsedValueFromElement(item, "nu-ci"));
				NodeList comunicaciones = item.getElementsByTagName("comunicacion-list");
				for (int i = 0; i < comunicaciones.getLength(); i++) {
					datos.setUno(parseComunicacion((Element) comunicaciones.item(i)));
				}

				resultado.setCliente(datos);
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

	public ResultObtenerComunicacionesCliente parsearObtenerComunicacionesCliente() {
		ResultObtenerComunicacionesCliente resultado = new ResultObtenerComunicacionesCliente();
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(getClass());
		logueo.setMetodo("parsearObtenerComunicacionesCliente");

		try {
			NodeList lista = this.doc.getElementsByTagName("comunicacion-list");
			if (lista.getLength() > 0) {
				NodeList elementos = lista.item(0).getChildNodes();
				for (int i = 0; i < elementos.getLength(); i++) {
					DatosComunicacion comunicacion = parseComunicacion((Element) elementos.item(i));
					resultado.setUno(comunicacion);
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

	private DatosComunicacion parseComunicacion(Element item) {
		DatosComunicacion comunicacion = new DatosComunicacion();
		comunicacion.setNumPersona(evaluarInt(item, "nu-persona"));
		comunicacion.setCodComunicacion(evaluarInt(item, "cod-comunicacion"));
		comunicacion.setTipoComunicacion(evaluarInt(item, "cod-tipo"));
		comunicacion.setValorComunicacion(getParsedValueFromElement(item, "valor"));
		comunicacion.setFechaActualizacion(getParsedValueFromElement(item, "fe-actualizacion"));
		return comunicacion;
	}

	public ResultObtenerPolizasCliente parsearObtenerPolizasCliente() {
		ResultObtenerPolizasCliente resultado = new ResultObtenerPolizasCliente();
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(getClass());
		logueo.setMetodo("parsearObtenerPolizasCliente");

		try {
			NodeList elementos = this.doc.getElementsByTagName("poliza");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				DatosFacturacion datos = new DatosFacturacion();
				datos.setCodSucursal(evaluarInt(item, "cd-sucursal"));
				datos.setNroPoliza(evaluarInt(item, "nu-poliza"));
				datos.setCodProducto(getParsedValueFromElement(item, "cd-producto"));
				datos.setDescProducto(getParsedValueFromElement(item, "de-producto"));
				datos.setCodRamo(evaluarInt(item, "cd-ramo"));
				datos.setDescRamo(getParsedValueFromElement(item, "de-ramo"));
				datos.setNumCliente(evaluarInt(item, "nu-cliente"));
				String electronica=getParsedValueFromElement(item, "in-mail");
				if (electronica!=null && "S".equalsIgnoreCase(electronica)) {
					datos.setEsFactElectronica(Boolean.TRUE);
				} else {
					datos.setEsFactElectronica(Boolean.FALSE);
				}
				datos.setFechaEndosoMail(getParsedValueFromElement(item, "fecha-endoso_mail"));
 				resultado.setUno(datos);
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
	
	
	public ResultObtenerMapaMsgSiniestro parsearObtenerMapaMsgSiniestro() {

		ResultObtenerMapaMsgSiniestro resultado = new ResultObtenerMapaMsgSiniestro();
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(getClass());
		logueo.setMetodo("parsearObtenerMapaMsgSiniestro");

		try {
			NodeList elementos = this.doc.getElementsByTagName("mensaje");
			if (elementos.getLength() > 0) {
				Element item = (Element) elementos.item(0);
				resultado.setIdMsg(evaluarInt(item, "msg-id"));
				resultado.setTituloMsg(getParsedValueFromElement(item, "msg-titulo"));
				resultado.setEstadoMsg(getParsedValueFromElement(item, "msg-estado"));
				resultado.setDescripcionMsg(getParsedValueFromElement(item, "msg-actual"));
				resultado.setTitulofuturoMsg(getParsedValueFromElement(item, "msg-futuro-titulo"));
				resultado.setFuturoMsg(getParsedValueFromElement(item, "msg-futuro"));
				resultado.setFinalMsg(getParsedValueFromElement(item, "msg-final"));
				Integer carril = evaluarInt(item, "carril");
				if (carril != null) {
					resultado.setCarril(carril);
				}
				resultado.setEtapa(getParsedValueFromElement(item, "etapa-esquema"));
				resultado.setTiempoPromedio(getParsedValueFromElement(item, "tiempo-promedio"));
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

	public ResultObtenerPolizasFacturasPagasCliente parsearObtenerPolizasFacturasPagasCliente() {
		ResultObtenerPolizasFacturasPagasCliente resultado = new ResultObtenerPolizasFacturasPagasCliente();
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(getClass());
		logueo.setMetodo("parsearObtenerPolizasFacturasPagasCliente");

		try {
			NodeList elementos = this.doc.getElementsByTagName("poliza");
			for (int i = 0; i < elementos.getLength(); i++) {
				Element item = (Element) elementos.item(i);
				DatosFacturacion datos = new DatosFacturacion();
				datos.setNroPoliza(evaluarInt(item, "nu-poliza"));
				datos.setCodProducto(getParsedValueFromElement(item, "cd-producto"));
				datos.setDescProducto(getParsedValueFromElement(item, "de-producto"));
				datos.setCodRamo(evaluarInt(item, "cd-ramo"));
				datos.setDescRamo(getParsedValueFromElement(item, "de-ramo"));
				datos.setNumCliente(evaluarInt(item, "nu-cliente"));
				String electronica=getParsedValueFromElement(item, "in-mail");
				if (electronica!=null && "S".equalsIgnoreCase(electronica)) {
					datos.setEsFactElectronica(Boolean.TRUE);
				} else {
					datos.setEsFactElectronica(Boolean.FALSE);
				}
				datos.setFechaEndosoMail(getParsedValueFromElement(item, "fecha-endoso_mail"));
 				resultado.setUno(datos);
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