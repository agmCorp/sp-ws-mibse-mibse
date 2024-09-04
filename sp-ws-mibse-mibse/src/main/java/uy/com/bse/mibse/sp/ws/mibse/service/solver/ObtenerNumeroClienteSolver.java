package uy.com.bse.mibse.sp.ws.mibse.service.solver;

import uy.com.bse.mibse.sp.ws.mibse.model.dto.ParamObtenerNumeroCliente;
import uy.com.bse.mibse.sp.ws.mibse.model.dto.ResultObtenerNumeroCliente;
import uy.com.bse.mibse.sp.ws.mibse.repository.ServicioMiBsePersist;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.logica.AbstractSolver;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultGenerico;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ParamGenerico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.parseo.ParseoMiBse;
@Component
public class ObtenerNumeroClienteSolver extends AbstractSolver {

    private final ServicioMiBsePersist servicioMiBsePersist;

    private final ParseoMiBse parseoMiBse;

    @Autowired
    public ObtenerNumeroClienteSolver(ServicioMiBsePersist servicioMiBsePersist, ParseoMiBse parseoMiBse) {
        this.servicioMiBsePersist = servicioMiBsePersist;
        this.parseoMiBse = parseoMiBse;
    }

    @Override
    public ResultGenerico procesoLogica(ParamGenerico param){
        ResultGenerico res= servicioMiBsePersist.obtenerNumeroCliente((ParamObtenerNumeroCliente) param);
        //ResultGenerico res= checkNull(servicioMiBsePersist.obtenerNumeroCliente((ParamObtenerNumeroCliente) param));
        return res;
    }

    @Override
    protected ResultGenerico getMyResultInstance() {
        return new ResultObtenerNumeroCliente();
    }

}