package uy.com.bse.mibse.sp.ws.mibse.utilitario.parseo;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultGenerico;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.exception.ErrorResolver;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.exception.Values;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.log.Logueo;

public class ParseoXmlGenerico {

	protected static Logger log = LogManager.getLogger(ParseoXmlGenerico.class);
	
	public static String ENCABEZADO = "Parseo xml";

	private String textoParsear;
	private Boolean errorGenerado;
	protected Document doc;
	protected String claveError = null;
	private final ParserXmlValueUtil valueParser;

	public ParseoXmlGenerico(String textoParsear) {
		this.errorGenerado = Boolean.FALSE;
		this.textoParsear = textoParsear;
		this.valueParser = new ParserXmlValueUtil();
	}

	public Boolean getErrorGenerado() {
		return errorGenerado;
	}

	public void setErrorGenerado(Boolean errorGenerado) {
		this.errorGenerado = errorGenerado;
	}

	/**
	 * Este m\u00e9todo genera un doc XML del string recibido
	 * 
	 * @return True si pudo generar el doc
	 */
	public Boolean generarDoc() {
		Boolean control = false;

		Logueo logueo = new Logueo();
		logueo.setEncabezado("Error al generar documento para parsear");
		logueo.setClase(ParseoXmlGenerico.class);
		logueo.setMetodo("generarDoc()");

		try {
			// Creo el doc que contiene el xml
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			InputSource is = new InputSource(new StringReader(this.textoParsear));
			DocumentBuilder builder = null;

			builder = factory.newDocumentBuilder();
			this.doc = builder.parse(is);
			control = Boolean.TRUE;
		} catch (ParserConfigurationException e) {
			logueo.setException("ParserConfigurationException");
			logueo.setError(e.getMessage());
			log.error(logueo.getMensaje());
		} catch (SAXException e) {
			logueo.setException("SAXException");
			logueo.setError(e.getMessage());
			log.error(logueo.getMensaje());
		} catch (IOException e) {
			logueo.setException("IOException");
			logueo.setError(e.getMessage());
			log.error(logueo.getMensaje());
		} catch (Exception e) {
			logueo.setException("Exception");
			logueo.setError(e.getMessage());
			log.error(logueo.getMensaje());
		}

		return control;
	}
	
	/**
	 * 
	 * @param item
	 * @param tag
	 * @return
	 */
	protected Integer evaluarInt( Element item, String tag) {
		return valueParser.evaluarInt( item, tag);
	}

	/**
	 * 
	 * @param item
	 * @param tag
	 * @return
	 */
	protected Double evaluarDouble( Element item, String tag) {
		return valueParser.evaluarDouble( item, tag);
	}
	
	/**
	 * 
	 * @param item
	 * @param tag
	 * @return
	 */
	protected Long evaluarLong( Element item, String tag) {
		return valueParser.evaluarLong(item, tag);
	}


	/**
	 * Este m\u00e9todo busca la clave en el Document recibido
	 * 
	 * @param doc
	 * @param clave
	 * @return El valor correspondiente a la clave ingresada
	 * @throws NegocioException
	 */
	public String getParsedValue(Document doc, String clave) {
		return valueParser.getParsedValue(doc, clave);
	}

	/**
	 * Este m\u00e9todo busca la clave en un Element
	 * 
	 * @param elemento
	 * @param clave
	 * @return valor de la clave ingresada
	 * @throws NegocioException
	 */
	protected String getParsedValueFromElement(Element elemento, String clave) {
		return valueParser.getParsedValueFromElement(elemento, clave);
	}
	
	

	protected String catchException(final Logueo logueo, Exception ex) {
		logueo.setException(Values.PARSEXCEPTCLAVE);
		logueo.setError(ex.getMessage());
		log.error(logueo.getMensaje(), ex);
		return Values.PARSEXCEPTCLAVE;
	}

	protected String catchNumberFormatException(final Logueo logueo, NumberFormatException ex) {
		logueo.setException(Values.PARSNUMEXCEPTCLAVE);
		logueo.setError(ex.getMessage());
		log.error(logueo.getMensaje(),ex);
		return Values.PARSNUMEXCEPTCLAVE;
	}

	protected void finallyBlock(String claveError, ResultGenerico resultado) {
		if (claveError != null) {
			resultado.setHayError(Boolean.TRUE);
			resultado.setError(ErrorResolver.getError(claveError));
			this.claveError = null;
		}
	}
	
	
}
