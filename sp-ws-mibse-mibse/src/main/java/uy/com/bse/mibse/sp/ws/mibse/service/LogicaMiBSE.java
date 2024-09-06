package uy.com.bse.mibse.sp.ws.mibse.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ParamGenerico;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.dato.ResultGenerico;
import uy.com.bse.mibse.sp.ws.mibse.utilitario.logica.LogicaSolver;

@Component
public final class LogicaMiBSE {
   private static final Logger logger = LoggerFactory.getLogger(LogicaMiBSE.class);
   private Map<String, LogicaSolver> solverMap;

   @Autowired
   private List<LogicaSolver> solvers;

   @PostConstruct
   private void init() {
      solverMap = new HashMap<>();
      for (LogicaSolver solver : solvers) {
         String classId = solver.getMyParamFullName();
         solverMap.put(classId, solver);
         logger.info("Registered solver: {}", classId);
      }
   }

   public ResultGenerico solve(ParamGenerico param) {
      ResultGenerico res = null;
      if (param != null) {
         String paramClass = param.getClass().getName();
         logger.info("User: {}", param.getUsuario());
         try {
            LogicaSolver solver = solverMap.get(paramClass);
            logger.info("Start processing solver: {} with param: {} ", solver.getClass().getName(), paramClass);
            res = solver.solve(param);
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
}