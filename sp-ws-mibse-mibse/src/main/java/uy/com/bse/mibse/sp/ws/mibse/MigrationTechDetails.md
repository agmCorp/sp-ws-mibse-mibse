## Descripción de arquitectura genérica utilizada en MiBSE:

El backend de la aplicación MiBSE está basada en una arquitectura genérica que permite la implementación 
rápida de nuevos Servicios Web, dónde la lógica de negocio de estos servicios se encuentra en Procedimientos 
Almacenados (PL/SQL sobre base Oracle).

La clave para resolver un nuevo servicio, bajo esta arquitectura genérica, está en definir el parámetro de entrada para 
el nuevo servicio, luego se configura en el Backend el mapeo entre el parámetro de entrada y la clase que resuelve el 
servicio. Dado que las clases que resuelven el servicio implementan una interfaz conocida, la lógica encargada de la 
llamada al procedimiento almacenado correspondiente se resuelve de forma genérica, sin necesidad de implementar 
nuevamente la lógica de glue code para cada nuevo servicio.

A continuación se detallan los pasos a seguir para implementar un nuevo servicio:

### 1. **Definir los Parámetros y Resultados:**
   Lo primero es definir las clases que encapsulan los parámetros (ParamNuevoServicio) y los resultados (ResultGenerico) 
   específicos para el nuevo servicio. Estos deben extender las clases base proporcionadas: ParamGenerico y 
   ResultGenerico.

```java
   public class ParamNuevoServicio extends ParamGenerico {
    // Definir campos específicos para el servicio
    }
   public class ResultNuevoServicio extends ResultGenerico {
   // Definir campos específicos para el resultado del servicio
    }
```

### 2. **Implementar el Solver:**
   Luego, se crea una nueva clase que extiende AbstractSolver o XMLAbstractSolver, dependiendo de si necesitas 
   procesar XML o no. Aquí es donde reside la lógica de negocio del servicio:
```java
   public class NuevoServicioSolver extends AbstractSolver {

    @Override
    protected ResultGenerico getMyResultInstance() {
        return new ResultNuevoServicio();
    }

    @Override
    protected ResultGenerico procesoLogica(ParamGenerico param) {
        ParamNuevoServicio myParam = (ParamNuevoServicio) param;
        ResultNuevoServicio result = new ResultNuevoServicio();
        
        // Lógica de negocio específica
        // Invocar métodos de persistencia o servicios remotos según sea necesario

        return result;
    }
}
```

En el archivo de configuración logicaMiBSEMappers.properties, se añade un mapeo que vincula la clase de parámetro con el Solver adecuado:

```properties
com.miempresa.ParamNuevoServicio=com.miempresa.NuevoServicioSolver
```

### 3. **Implementar el Service:**
Una vez que se ha implementado el nuevo servicio en la correspondiente clase Solver y registrado el mapeo correspondiente 
a partir del parámetro de entrada, se debe de implementar la adaptación de lo genérico a lo particular. Para ello, se debe
de implementar un método que reciba como parámetro el parámetro de entrada y devuelva el resultado correspondiente. Este
método debe ser público y su lógica es muy simple, ya que se resuelve su implementación de forma genérica mediante:
```java
ResultGenerico result = LogicaMiBSE.solve(param);
```

Podemos ver que el método solve de LogicaMiBSE es el encargado de resolver quién es el pedido, invocando el Solver adecuado
basado en los parámetros recibidos:

```java
public class MiNuevoServicio implements MiNuevoServicioLocal {

    private static Logger log = LogManager.getLogger(MiNuevoServicio.class);

    @Override
    public ResultNuevoServicio ejecutarNuevoServicio(ParamNuevoServicio param) {
        log.debug("ejecutarNuevoServicio start: " + param);
        ResultGenerico result = LogicaMiBSE.solve(param);
        log.debug("ejecutarNuevoServicio end");
        return (ResultNuevoServicio) result;
    }
}
        
```

Esta clase implementa la interfaz MiNuevoServicioLocal, que es la interfaz de acceso a los servicios de la aplicación. 
La clase MiNuevoServicioLocal toma el rol de service layer, que es la capa de servicios de la aplicación.
Para la aplicación MiBSE, la service layer es implementada por la clase MiBse (MiBSE.java), que implementa la interfaz 
MiBseLocal que juega el rol de interfaz de acceso a los servicios de la aplicación.

### 4. Exposición como Web Service

Finalmente, se expone este servicio como un Web Service. No hay detalles específicos para esto, se implementan como es 
usual en el resto de las aplicaciones de Java anotando la clase con @WebService y definiendo las configuraciones necesarias.


### 5. Resumen del Flujo

1. Parámetros (ParamNuevoServicio): Encapsula los datos de entrada.
2. LogicaMiBSE.solve: Mapea y ejecuta el Solver correspondiente.
3. NuevoServicioSolver: Implementa la lógica de negocio.
4. Resultado (ResultNuevoServicio):  Se devuelve al cliente.
5. MiNuevoServicio: Implementa la interfaz de acceso a los servicios de la aplicación.
6. Exposición como Web Service: Se expone este servicio como un Web Service.

Este patrón proporciona una forma escalable y reutilizable de agregar nuevos servicios al sistema sin necesidad de 
reescribir mucho código, aprovechando el polimorfismo para mantener la flexibilidad.

----
## Migración de Arquitectura Genérica a Spring Boot 3:

Dado que código que se encarga de conectar los web services con los procedimientos almacenados en la base (glue code), 
es genérico, el impácto en la migración es menor que en otros casos en el que el glue code es generado mediante 
copy/paste de código.

### 1. Configuración de Mapeos en Spring

   En lugar de usar un archivo de propiedades para mapear parámetros a solvers, la propuesta es usar una configuración 
de Spring con un @Configuration que expone los beans.

Dado que AbstractSolver y XMLAbstractSolver son clases abstractas que heredan de LogicaSolver, la configuración de 
mapeos en Spring se puede hacer de la siguiente manera:

```java
@Configuration
public class SolverConfig {
   @Bean
   public Map<String, LogicaSolver> solverMap(List<LogicaSolver> solvers) {
      Map<String, LogicaSolver> map = new HashMap<>();
      for (AbstractSolver solver : solvers) {
         map.put(solver.getParameter, solver);
      }
      return map;
   }
}
```   
Necesitamos modificar la clase LogicaSolver para que exponga el método getParameter() que devuelve el parámetro de 
entrada y será implementada por la clase Solver concreta que es encargada de conocer el parámetro de entrada.


### 2. Implementación de Solvers

   Cada Solver implementa la interfaz LogicaSolver y define el parámetro de entrada que debe resolver. 
   En este ejemplo, el parámetro de entrada es "paramA". Cada Solver debe implementar el método solve() que 
   realizará la lógica de negocio correspondiente.

```java
package uy.com.bse;

import org.springframework.stereotype.Component;

@Component
public class SolverA implements LogicaSolver {
   @Override
   public String getParameter() {
      return "paramA"; // Parámetro asociado
   }

   @Override
   public ResultGenerico solve(ParamGenerico param) {
      // Lógica de solución para SolverA
      return new ResultGenerico(); // Retorna el resultado
   }
}
```
### 2. Invocación del Servicio en Spring Boot

   Refactorizar LogicaMiBSE para Spring Boot para que use el mapa de solvers para resolver dinámicamente el solver adecuado.
   Esto podemos implementarlo mediante inyección del mapa de solvers en LogicaMiBSE y utilizando este mapa para resolver 
   dinámicamente el solver adecuado.

Ejemplo:
```java
package uy.com.bse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public final class LogicaMiBSE {

   @Autowired
   private Map<String, LogicaSolver> solverMap; // Inyección del mapa de solvers

   public static ResultGenerico solve(ParamGenerico param) {
      ResultGenerico result = null;
      if (param != null) {
         String paramClass = param.getClass().getName();

         RTimeLogger.registerStart(paramClass);
         RTimeLogger.addCustomData("user", param.getUsuario());
         try {
            LogicaSolver solver = getMapperNewInstance(paramClass);
            result = solver.solve(param); // Llamar al método solve del solver correspondiente
         } catch (RuntimeException re) {
            myLogger.error("No se pudo resolver param: " + paramClass + ", para usuario: " + param.getUsuario());
            myLogger.error(re.getMessage());
            RTimeLogger.addCustomData("solveError", re.getMessage());
            throw re;
         } finally {
            RTimeLogger.registerStop(paramClass);
         }
      }
      return result;
   }

   private LogicaSolver getMapperNewInstance(String paramClass) {
      LogicaSolver solver = solverMap.get(paramClass); // Obtener el solver del mapa
      if (solver == null) {
         String msgError = "No pude encontrar mapper para param: " + paramClass;
         myLogger.error(msgError);
         // Manejo de error
      }
      return solver;
   }
}
```

Finalmente, se implementa el servicio en MiNuevoServicio, que implementa la interfaz MiNuevoServicioLocal, que es la 
interfaz de acceso a los servicios de la aplicación. La clase MiNuevoServicioLocal toma el rol de service layer, que es 
la capa de servicios de la aplicación. Para la aplicación MiBSE, la service layer es implementada por la clase MiBse

```java
public class MiNuevoServicio implements MiNuevoServicioLocal {

    private static Logger log = LogManager.getLogger(MiNuevoServicio.class);

    @Autowired
    private LogicaMiBSE logicaMiBSE;

    @Override    
    public ResultNuevoServicio ejecutarNuevoServicio(ParamNuevoServicio param) {
        log.debug("ejecutarNuevoServicio start: " + param);
        ResultGenerico result = LogicaMiBSE.solve(param);
        log.debug("ejecutarNuevoServicio end");
        return (ResultNuevoServicio) result;
    }
}
```

### Resumen
**Interfaz LogicaSolver:** Define un método para obtener el parámetro asociado.
**Implementación de Solvers:** Cada solver implementa la interfaz y define su parámetro.
**Configuración de Mapeo:** En SolverConfig, se crea un Map que asocia cada parámetro con su solver.
**Uso en LogicaMiBSE:** Se inyecta el mapa y se utiliza para obtener el solver correspondiente según el parámetro.
