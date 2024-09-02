package uy.com.bse.mibse.sp.ws.mibse.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.logica.AbstractSolver;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.logica.LogicaSolver;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class SolverConfig {

    @Bean
    public Map<String, LogicaSolver> solverMap(List<LogicaSolver> solvers) {
        Map<String, LogicaSolver> map = new HashMap<>();
        for (LogicaSolver solver : solvers) {
            if (solver != null) {
                LogicaSolver concreteSolver = (LogicaSolver) solver;
                map.put(concreteSolver.getParameter(), solver);
            }
        }
        return map;
    }
}