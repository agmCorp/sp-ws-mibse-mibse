package uy.com.bse.mibse.sp.ws.mibse.utilitario.parseo;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.exception.Values;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.exception.ErrorResolver;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.log.Logueo;
import java.io.IOException;
import java.io.StringReader;

@Component
public class ParserXmlValueUtil {

    protected static final Logger log = LoggerFactory.getLogger(ParserXmlValueUtil.class);

	public Document getDocumentFromString(String textoParsear) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado("Parseo XML");
		logueo.setClase(ParserXmlValueUtil.class);
		logueo.setMetodo("getDocumentFromString");
		Document doc = null;

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			InputSource is = new InputSource(new StringReader(textoParsear));
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(is);

		} catch (ParserConfigurationException | SAXException | IOException e) {
            logueo.setException(e.getClass().getSimpleName());
            logueo.setError(e.getMessage());
            log.error(logueo.getMensaje(), e);
        } catch (Exception e) {
            logueo.setException("Exception");
            logueo.setError(e.getMessage());
            log.error(logueo.getMensaje(), e);
        }
		return doc;
	}

	public String getParsedValue(Document doc, String clave) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParserXmlValueUtil.class);
		logueo.setMetodo("getParsedValue()");

		String valor = null;

		try {
			Node unNodo;

			if (doc.getElementsByTagName(clave).item(0) != null) {
				unNodo = doc.getElementsByTagName(clave).item(0).getFirstChild();

				if (unNodo != null) {
					valor = unNodo.getNodeValue();
				}
			}
		} catch (Exception e) {
			logueo.setException("Exception");
			logueo.setError(e.getMessage());
			log.error(logueo.getMensaje());
		}

		return valor;
	}

	public String getParsedValueFromElement(Element elemento, String clave) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParserXmlValueUtil.class);
		logueo.setMetodo("getParsedValueFromElement()");

		String valor = null;

		try {
			Node unNodo;

			if (elemento.getElementsByTagName(clave).item(0) != null) {
				unNodo = elemento.getElementsByTagName(clave).item(0).getFirstChild();

				if (unNodo != null) {
					valor = unNodo.getNodeValue();
				}
			}
		} catch (Exception e) {
			logueo.setException("Exception");
			logueo.setError(e.getMessage());
			log.error(logueo.getMensaje());
		}

		return valor;
	}

	public Integer evaluarInt(final Element item, final String tag) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParserXmlValueUtil.class);
		logueo.setMetodo("evaluarInt()");
		Integer retorno = null;

		try {
			final String valor = getParsedValueFromElement(item, tag);

			if (valor != null) {
				retorno = Integer.valueOf(valor);
			}
		} catch (NumberFormatException ex) {
			logueo.setException(Values.NMBEXCEPT);
			logueo.setError(ex.getMessage());
			log.error(logueo.getMensaje());
		} catch (Exception ex) {
			logueo.setException(Values.EXCEPTION);
			logueo.setError(ex.getMessage());
			log.error(logueo.getMensaje());
		}

		return retorno;
	}

	public Double evaluarDouble(final Element item, final String tag) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParserXmlValueUtil.class);
		logueo.setMetodo("evaluarDouble()");
		Double retorno = null;

		try {
			final String valor = getParsedValueFromElement(item, tag);

			if (valor != null) {
				retorno = Double.valueOf(valor);
			}
		} catch (NumberFormatException ex) {
			logueo.setException(Values.NMBEXCEPT);
			logueo.setError(ex.getMessage());
			log.error(logueo.getMensaje());
		} catch (Exception ex) {
			logueo.setException(Values.EXCEPTION);
			logueo.setError(ex.getMessage());
			log.error(logueo.getMensaje());
		}

		return retorno;
	}

	public Long evaluarLong(final Element item, final String tag) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOPARSEO);
		logueo.setClase(ParserXmlValueUtil.class);
		logueo.setMetodo("evaluarLong()");
		Long retorno = null;

		try {
			final String valor = getParsedValueFromElement(item, tag);

			if (valor != null) {
				retorno = Long.valueOf(valor);
			}
		} catch (NumberFormatException ex) {
			logueo.setException(Values.NMBEXCEPT);
			logueo.setError(ex.getMessage());
			log.error(logueo.getMensaje());
		} catch (Exception ex) {
			logueo.setException(Values.EXCEPTION);
			logueo.setError(ex.getMessage());
			log.error(logueo.getMensaje());
		}

		return retorno;
	}
}
