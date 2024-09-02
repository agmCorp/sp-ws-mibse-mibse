package uy.com.bse.mibse.sp.ws.mibse.utilitario.parseo;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultGenerico;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.exception.ErrorResolver;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.exception.Values;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.log.Logueo;

@Component
public class ParseoXmlGenerico {

    protected static Logger log = LogManager.getLogger(ParseoXmlGenerico.class);
    public static String ENCABEZADO = "Parseo xml";

    protected String textoParsear;
    private Boolean errorGenerado;
    protected Document doc;
    protected String claveError = null;
    private final ParserXmlValueUtil valueParser;

    @Autowired
    public ParseoXmlGenerico(ParserXmlValueUtil valueParser) {
        this.errorGenerado = Boolean.FALSE;
        this.valueParser = valueParser;
    }

    public Boolean getErrorGenerado() {
        return errorGenerado;
    }

    protected void setTextoParsear(String textoParsear){
        this.textoParsear = textoParsear;
    }

    public void setErrorGenerado(Boolean errorGenerado) {
        this.errorGenerado = errorGenerado;
    }

    /**
     * Este método genera un doc XML del string recibido
     * 
     * @return True si pudo generar el doc
     */
    public Boolean generarDoc() {
        Boolean control = false;

        try {
            // Creo el doc que contiene el xml
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            InputSource is = new InputSource(new StringReader(this.textoParsear));
            DocumentBuilder builder = factory.newDocumentBuilder();
            this.doc = builder.parse(is);
            control = Boolean.TRUE;
        } catch (ParserConfigurationException e) {
            log.error("Error al generar documento para parsear", e);
        } catch (SAXException e) {
            log.error("Error al analizar XML", e);
        } catch (IOException e) {
            log.error("Error de entrada/salida", e);
        } catch (Exception e) {
            log.error("Error inesperado", e);
        }

        return control;
    }

    protected Integer evaluarInt(Element item, String tag) {
        return valueParser.evaluarInt(item, tag);
    }

    protected Double evaluarDouble(Element item, String tag) {
        return valueParser.evaluarDouble(item, tag);
    }

    protected Long evaluarLong(Element item, String tag) {
        return valueParser.evaluarLong(item, tag);
    }

    public String getParsedValue(Document doc, String clave) {
        return valueParser.getParsedValue(doc, clave);
    }

    protected String getParsedValueFromElement(Element elemento, String clave) {
        return valueParser.getParsedValueFromElement(elemento, clave);
    }

    protected String catchException(final Logueo logueo, Exception ex) {
        log.error("Excepción durante el parseo", ex);
        return Values.PARSEXCEPTCLAVE;
    }

    protected String catchNumberFormatException(final Logueo logueo, NumberFormatException ex) {
        log.error("Excepción de formato numérico", ex);
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
