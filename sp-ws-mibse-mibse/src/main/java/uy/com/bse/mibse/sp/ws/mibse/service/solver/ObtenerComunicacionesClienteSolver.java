package uy.com.bse.mibse.sp.ws.mibse.solver;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.logica.XMLAbstractSolver;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultGenerico;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultXmlPL;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ParamGenerico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uy.com.bse.mibse.sp.ws.mibse.model.dto.ParamObtenerComunicacionesCliente;
import uy.com.bse.mibse.sp.ws.mibse.model.dto.ResultObtenerComunicacionesCliente;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.parseo.ParseoMiBse;
import uy.com.bse.mibse.sp.ws.mibse.repository.ServicioMiBsePersist;

@Component
public class ObtenerComunicacionesClienteSolver extends XMLAbstractSolver {

	private final ServicioMiBsePersist servicioMiBsePersist; // O una instancia apropiada

	private final ParseoMiBse parseoMiBse;

    @Autowired
    public ObtenerComunicacionesClienteSolver(ServicioMiBsePersist servicioMiBsePersist, ParseoMiBse parseoMiBse) {
        this.servicioMiBsePersist = servicioMiBsePersist;
        this.parseoMiBse = parseoMiBse;
    }

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
		parseoMiBse.setTextoParsear(xmlResult.getXml());
        if (parseoMiBse.generarDoc().booleanValue()) {
            return parseoMiBse.parsearObtenerComunicacionesCliente();
        }
        return null;
	}

}
