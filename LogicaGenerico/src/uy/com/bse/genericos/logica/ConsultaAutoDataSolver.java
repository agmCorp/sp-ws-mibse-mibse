package uy.com.bse.genericos.logica;

import uy.bse.rector.servicios.parseo.ParseoConsultaAutodata;
import uy.com.bse.autodata.operaciones.ParamConsultaAutodata;
import uy.com.bse.autodata.operaciones.ResultConsultaAutodata;
import uy.com.bse.autodata.persistencia.ServiciosAutodataPersist;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.dato.ResultXmlPL;
import uy.com.bse.utilitario.logica.XMLAbstractSolver;

public class ConsultaAutoDataSolver extends XMLAbstractSolver {

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultConsultaAutodata();
	}
	
	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return new ServiciosAutodataPersist().consultaAutodata((ParamConsultaAutodata) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ParseoConsultaAutodata unParseo = new ParseoConsultaAutodata(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			return unParseo.parsearConsultaAutodata();
		}
		return null;
	}
}
