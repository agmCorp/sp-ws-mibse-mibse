package uy.com.bse.mibse.sp.ws.mibse.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.logica.LogicaSolver;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class SolverConfig {
    private static final Logger logger = LoggerFactory.getLogger(SolverConfig.class);

    @Bean
    public Map<String, LogicaSolver> solverMap(List<LogicaSolver> solvers) {
        Map<String, LogicaSolver> map = new HashMap<>();
        for (LogicaSolver solver : solvers) {
            if (solver != null) {
                String fullClassName = solver.getClass().getName();
                String simpleClassName = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);
                String classId = simpleClassName.toUpperCase().trim();
                map.put(classId, solver);
                logger.info("Registered solver: {}", classId);
            }
        }
        return map;
    }
}