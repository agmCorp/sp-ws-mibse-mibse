package uy.bse.rector.servicios.parseo;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import uy.com.bse.autodata.operaciones.ResultConsultaAutodata;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.parseo.ParseoXmlGenerico;

public class ParseoConsultaAutodata extends ParseoXmlGenerico {

	public ParseoConsultaAutodata(String textoParsear) {
		super(textoParsear);
	}

	public ResultConsultaAutodata parsearConsultaAutodata() {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(ParseoConsultaAutodata.ENCABEZADO);
		logueo.setClase(ParseoConsultaAutodata.class);
		logueo.setMetodo("parsearConsultaAutodata()");

		ResultConsultaAutodata resultado = new ResultConsultaAutodata();

		try {
			String valor = "";
			NodeList nodeList = this.doc.getElementsByTagName("valor");
			if (nodeList.getLength() > 0) {
				Node nodo = nodeList.item(0).getFirstChild();
				if (nodo != null) {
					valor = nodo.getNodeValue();
				}
			}
			resultado.setValor(valor);
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
