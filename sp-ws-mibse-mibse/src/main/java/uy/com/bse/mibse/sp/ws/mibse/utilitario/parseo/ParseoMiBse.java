package uy.com.bse.mibse.sp.ws.mibse.utilitario.parseo;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import uy.com.bse.mibse.sp.ws.mibse.model.dto.ResultObtenerComunicacionesCliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.log.Logueo;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.parseo.ParseoXmlGenerico;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.exception.Values;
import uy.com.bse.mibse.sp.ws.mibse.model.dto.DatosComunicacion;

public class ParseoMiBse extends ParseoXmlGenerico {

    private static final Logger logger = LoggerFactory.getLogger(ParseoMiBse.class);

	public ParseoMiBse(String textoParsear) {
		super(textoParsear);
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
	
}