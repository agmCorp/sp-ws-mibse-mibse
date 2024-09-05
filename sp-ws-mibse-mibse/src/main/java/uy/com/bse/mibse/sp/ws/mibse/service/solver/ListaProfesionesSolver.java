package uy.com.bse.mibse.sp.ws.mibse.service.solver;

import uy.com.bse.mibse.sp.ws.mibse.model.dto.ParamListaProfesiones;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultCodiguera;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.logica.XMLAbstractSolver;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.logica.AbstractSolver;

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
public class ListaProfesionesSolver extends AbstractSolver {

    private final ServicioMiBsePersist servicioMiBsePersist; // O una instancia apropiada

    private final ParseoMiBse parseoMiBse;


    @Autowired
    public ListaProfesionesSolver(ServicioMiBsePersist servicioMiBsePersist, ParseoMiBse parseoMiBse) {
        this.servicioMiBsePersist = servicioMiBsePersist;
        this.parseoMiBse = parseoMiBse;
    }

    @Override
    public ResultGenerico procesoLogica(ParamGenerico param){
        return listaProfesiones((ParamListaProfesiones) param);
    }

    @Override
    protected ResultGenerico getMyResultInstance() {
        return new ResultCodiguera();
    }

    private ResultCodiguera listaProfesiones(ParamListaProfesiones param) {
        return (ResultCodiguera) this.checkNull(servicioMiBsePersist.listaProfesiones(param));
    }
}
