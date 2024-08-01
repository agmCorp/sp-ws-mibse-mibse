package uy.com.bse.cotizaciones.operaciones;

import uy.bse.rector.servicios.parseo.ParseoCotOperaciones;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class AgregarCertificadoSolver extends XMLAbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultAgregarCertificado();
	}

	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return  new CotOperaciones().agregarCertificado((ParamAgregarCertificado) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ResultAgregarCertificado resultado=null;
		final ParseoCotOperaciones unParseo = new ParseoCotOperaciones(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			Integer consecutivo = ((ResultAgregarCertificado)xmlResult).getNumConsecutivo();
			resultado = unParseo.parsearAgregarCertificado();
			resultado.setNumConsecutivo(consecutivo);
		}
		return resultado;
	}

}
