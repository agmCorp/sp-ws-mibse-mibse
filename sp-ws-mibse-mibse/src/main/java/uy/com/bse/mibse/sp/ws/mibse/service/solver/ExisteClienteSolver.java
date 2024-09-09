package uy.com.bse.mibse.sp.ws.mibse.service.solver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uy.com.bse.mibse.sp.ws.mibse.model.dto.ParamExisteCliente;
import uy.com.bse.mibse.sp.ws.mibse.model.dto.ParamListaProfesiones;
import uy.com.bse.mibse.sp.ws.mibse.model.dto.ParamObtenerPolizasCliente;
import uy.com.bse.mibse.sp.ws.mibse.model.dto.ResultExisteCliente;
import uy.com.bse.mibse.sp.ws.mibse.repository.ServicioMiBsePersist;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ParamGenerico;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultCodiguera;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultCondicion;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultGenerico;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.logica.AbstractSolver;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.parseo.ParseoMiBse;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.seguridad.ParamValidar;

@Component
public class ExisteClienteSolver extends AbstractSolver {

    private final ServicioMiBsePersist servicioMiBsePersist; // O una instancia apropiada

    private final ParseoMiBse parseoMiBse;

    @Autowired
    public ExisteClienteSolver(ServicioMiBsePersist servicioMiBsePersist, ParseoMiBse parseoMiBse) {
        this.servicioMiBsePersist = servicioMiBsePersist;
        this.parseoMiBse = parseoMiBse;
    }

    @Override
    public ResultGenerico procesoLogica(ParamGenerico param) {
        // Devuelve existe en true si es usuario de MiBSE
        ResultExisteCliente resultado = null;
        resultado = new ResultExisteCliente();
        resultado.setExiste(false);
        ParamExisteCliente pexiste = (ParamExisteCliente) param;
        ResultCondicion registrado = clienteFueRegistrado(pexiste.getTipoDocumento(), pexiste.getNumDocumento());
        if (registrado.getResultado().booleanValue()) {
            if (registrado.getError().getCodigo().equals(1400))
                resultado.setExiste(true);
            resultado.setError(registrado.getError());
            resultado.setHayError(Boolean.TRUE);
        }
        return checkNull(resultado);
    }

    private ResultCondicion clienteFueRegistrado(String tipoDocumento, String numDocumento) {
        ParamValidar param = new ParamValidar();
        param.setUsuario(crearEstructuraUsuario(tipoDocumento, numDocumento));
        return servicioMiBsePersist.verificaDuplicidadUsuario(param);
    }

    @Override
    protected ParamGenerico getMyParamInstance() {
        return new ParamExisteCliente();
    }

    @Override
    protected ResultGenerico getMyResultInstance() {
        return new ResultExisteCliente();
    }

    private String crearEstructuraUsuario(String tipoDocumento, String numDocumento) {
        StringBuffer sb = new StringBuffer();
        sb.append(tipoDocumento);
        sb.append("#");
        sb.append(numDocumento);
        return sb.toString();
    }

}
