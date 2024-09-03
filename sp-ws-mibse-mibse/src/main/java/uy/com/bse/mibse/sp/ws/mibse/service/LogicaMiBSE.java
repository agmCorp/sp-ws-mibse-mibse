package uy.com.bse.mibse.sp.ws.mibse.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ParamGenerico;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultGenerico;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.logica.LogicaSolver;

import java.util.HashMap;
import java.util.Map;

@Component
public final class LogicaMiBSE {

   @Autowired
   private Map<String, LogicaSolver> solverMap; // Inyección del mapa de solvers

   private static final Logger logger = LoggerFactory.getLogger(LogicaMiBSE.class);

   public ResultGenerico solve(ParamGenerico param) {
      ResultGenerico res = null;
      if (param != null) {
        String paramClass = param.getClass().getName();
        String cleanedClassName = paramClass.substring(paramClass.lastIndexOf('.') + 1).replace("Param", "").concat("Solver");
        String classId = cleanedClassName.toUpperCase().trim();
        logger.info("Start processing: {} with class id: {} ", cleanedClassName, classId);
        logger.info("User: {}", param.getUsuario());
         try {
             LogicaSolver solver = getMapperNewInstance(classId);
             res = solver.solve(param); // Llamar al método solve del solver correspondiente
         } catch (RuntimeException re) {
            logger.error("No se pudo resolver param: {} para usuario: {}", paramClass, param.getUsuario(), re);
            logger.error(re.getMessage());
            throw re;
         } finally {
            logger.info("Stop processing: {}", paramClass);
         }
      }
      return res;
   }

    private Map<String, LogicaSolver> getSolverMapUpper() {
        Map<String, LogicaSolver> solverMapUpper = new HashMap<>();
        for (Map.Entry<String, LogicaSolver> entry : solverMap.entrySet()) {
            String upperCaseKey = entry.getKey().toUpperCase();  // Convertir la clave a mayúsculas
            solverMapUpper.put(upperCaseKey, entry.getValue());
        }
        return solverMapUpper;
    }

   private LogicaSolver getMapperNewInstance(String paramClass) {
      LogicaSolver solver = getSolverMapUpper().get(paramClass); // Obtener el solver del mapa
      if (solver == null) {
         String msgError = "No pude encontrar mapper para param: " + paramClass;
         logger.error(msgError);
         // Manejo de error
      }
      return solver;
   }
}