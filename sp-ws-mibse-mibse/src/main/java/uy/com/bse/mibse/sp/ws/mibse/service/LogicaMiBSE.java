package uy.com.bse.mibse.sp.ws.mibse.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ParamGenerico;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultGenerico;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.logica.LogicaSolver;

import java.util.Map;

@Component
public final class LogicaMiBSE {

   @Autowired
   private Map<String, LogicaSolver> solverMap; // Inyección del mapa de solvers

   private static final Logger logger = LoggerFactory.getLogger(LogicaMiBSE.class);

   public ResultGenerico solve(ParamGenerico param) {
      ResultGenerico result = null;
      if (param != null) {
         String paramClass = param.getClass().getName();

        logger.info("Start processing: {}", paramClass);
        logger.info("User: {}", param.getUsuario());
         try {
            LogicaSolver solver = getMapperNewInstance(paramClass);
            result = solver.solve(param); // Llamar al método solve del solver correspondiente
         } catch (RuntimeException re) {
            logger.error("No se pudo resolver param: {} para usuario: {}", paramClass, param.getUsuario(), re);
            logger.error(re.getMessage());
            throw re;
         } finally {
            logger.info("Stop processing: {}", paramClass);
         }
      }
      return result;
   }

   private LogicaSolver getMapperNewInstance(String paramClass) {
      LogicaSolver solver = solverMap.get(paramClass); // Obtener el solver del mapa
      if (solver == null) {
         String msgError = "No pude encontrar mapper para param: " + paramClass;
         logger.error(msgError);
         // Manejo de error
      }
      return solver;
   }
}