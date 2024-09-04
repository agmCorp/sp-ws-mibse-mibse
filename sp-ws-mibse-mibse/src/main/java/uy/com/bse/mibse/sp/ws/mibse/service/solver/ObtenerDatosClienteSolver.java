package uy.com.bse.mibse.sp.ws.mibse.service.solver;

import uy.com.bse.mibse.sp.ws.mibse.repository.ServicioMiBsePersist;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.logica.XMLAbstractSolver;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultGenerico;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultXmlPL;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ParamGenerico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.com.bse.mibse.sp.ws.mibse.model.dto.ResultObtenerDatosCliente;
import uy.com.bse.mibse.sp.ws.mibse.model.dto.ParamObtenerDatosCliente;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.parseo.ParseoMiBse;

@Component
public class ObtenerDatosClienteSolver extends XMLAbstractSolver {

    private final ServicioMiBsePersist servicioMiBsePersist; // O una instancia apropiada

    private final ParseoMiBse parseoMiBse;

    @Autowired
    public ObtenerDatosClienteSolver(ServicioMiBsePersist servicioMiBsePersist, ParseoMiBse parseoMiBse) {
        this.servicioMiBsePersist = servicioMiBsePersist;
        this.parseoMiBse = parseoMiBse;
    }

    @Override
    protected ResultGenerico getMyResultInstance() {
        return new ResultObtenerDatosCliente();
    }

    @Override
    protected ResultXmlPL getXmlResult(ParamGenerico param) {
        return servicioMiBsePersist.obtenerDatosCliente((ParamObtenerDatosCliente) param);
    }

    @Override
    protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
        parseoMiBse.setTextoParsear(xmlResult.getXml());
        if (parseoMiBse.generarDoc().booleanValue()) {
            return parseoMiBse.parsearObtenerDatosCliente();
        }
        return null;
    }
}
