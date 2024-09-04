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
    /**
     * Método que crea un `Map` de tipo `<String, LogicaSolver>` donde la clave es
     * un identificador único basado en el nombre del servicio y el valor es
     * la instancia del solver. Este `Map` se utiliza para resolver dinámicamente el
     * solver correspondiente según el nombre del parámetro recibido.
     *
     * @param solvers Lista de instancias de `LogicaSolver` disponibles en el contexto de Spring.
     * @return Un `Map` donde la clave es el nombre del servicio que implementa el solver en mayúsculas
     *         y el valor es la instancia del solver.
     */
    @Bean
    public Map<String, LogicaSolver> solverMap(List<LogicaSolver> solvers) {
        // Crear un nuevo HashMap para almacenar los mapeos de solvers.
        Map<String, LogicaSolver> map = new HashMap<>();

        // Iterar sobre cada solver en la lista proporcionada.
        for (LogicaSolver solver : solvers) {
            if (solver != null) {
                // Obtener el nombre completo de la clase del solver (incluyendo el paquete).
                String fullClassName = solver.getClass().getName();
                // Extraer el nombre del servicio (sin prefijo Param).
                String serviceName = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);
                // Registrar el solver en el mapa usando el nombre del servicio como clave.
                String classId = serviceName.toUpperCase().trim();
                map.put(classId, solver);
                logger.info("Registered solver: {}", classId);
            }
        }
        // Retornar el mapa con los solvers registrados.
        return map;
    }
}