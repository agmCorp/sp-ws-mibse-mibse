package uy.com.bse.mibse.sp.ws.mibse.solver;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.logica.XMLAbstractSolver;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultGenerico;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultXmlPL;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ParamGenerico;

import org.springframework.beans.factory.annotation.Autowired;

import uy.com.bse.mibse.sp.ws.mibse.model.dto.ParamObtenerComunicacionesCliente;
import uy.com.bse.mibse.sp.ws.mibse.model.dto.ResultObtenerComunicacionesCliente;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.parseo.ParseoMiBse;
import uy.com.bse.mibse.sp.ws.mibse.repository.ServicioMiBsePersist;

public class ObtenerComunicacionesClienteSolver extends XMLAbstractSolver {

	@Autowired
	ServicioMiBsePersist servicioMiBsePersist;

	@Override
	protected ResultGenerico getMyResultInstance() {
		return new ResultObtenerComunicacionesCliente();
	}

	@Override
	protected ResultXmlPL getXmlResult(ParamGenerico param) {
		return servicioMiBsePersist.obtenerComunicacionesCliente((ParamObtenerComunicacionesCliente) param);
	}

	@Override
	protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
		ParseoMiBse unParseo = new ParseoMiBse(xmlResult.getXml());
		if (unParseo.generarDoc().booleanValue()) {
			return unParseo.parsearObtenerComunicacionesCliente();
		}
		return null;
	}

	@Override
    public String getParameter() {
		return "ParamObtenerComunicacionesCliente"; //TODO revisar
	}
}
