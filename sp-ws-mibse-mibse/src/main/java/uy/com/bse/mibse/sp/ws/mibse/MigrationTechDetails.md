# Descripción de arquitectura genérica utilizada en MiBSE:

El backend de la aplicación MiBSE está basada en una arquitectura genérica que permite la implementación 
rápida de nuevos Servicios Web, dónde la lógica de negocio de estos servicios se encuentra en Procedimientos 
Almacenados (PL/SQL sobre base Oracle).

La clave para resolver un nuevo servicio, bajo esta arquitectura genérica, está en definir el parámetro de entrada para 
el nuevo servicio, luego se configura en el Backend el mapeo entre el parámetro de entrada y la clase que resuelve el 
servicio. Dado que las clases que resuelven el servicio implementan una interfaz conocida, la lógica encargada de la 
llamada al procedimiento almacenado correspondiente se resuelve de forma genérica, sin necesidad de implementar 
nuevamente la lógica de glue code para cada nuevo servicio.

A continuación se detallan los pasos a seguir para implementar un nuevo servicio:

## 1. **Definir los Parámetros y Resultados:**
Lo primero es definir las clases que encapsulan los parámetros (ParamNuevoServicio) y los resultados (ResultNuevoServicio) 
específicos para el nuevo servicio. Estos deben extender las clases base proporcionadas: ParamGenerico y ResultGenerico.

```java
   public class ParamNuevoServicio extends ParamGenerico {
    // Definir campos específicos para el servicio
    }
   public class ResultNuevoServicio extends ResultGenerico {
   // Definir campos específicos para el resultado del servicio
    }
```

## 2. **Implementar el Solver:**
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

## 3. **Implementar el Service:**

Una vez que se ha implementado el nuevo servicio en la correspondiente clase Solver y registrado el mapeo correspondiente 
a partir del parámetro de entrada, se debe de implementar la adaptación de lo genérico a lo particular. Para ello, se debe
de implementar un método que reciba como parámetro el parámetro de entrada y devuelva el resultado correspondiente. Este
método debe ser público y su lógica es muy simple, ya que se resuelve su implementación de forma genérica mediante:

```java
ResultGenerico result = LogicaMiBSE.solve(param);
```

Podemos ver que el método solve de LogicaMiBSE es el encargado de resolver quién es el pedido, invocando el Solver adecuado
basado en los parámetros recibidos. La clase LogicaMiBSE es la encargada de resolver quién es el pedido, invocando el 
Solver adecuado basado en los parámetros recibidos, este mapping se realiza en LogicaMiBSE mediante el parseo del archivo 
logicaMiBSEMappers.properties utilizando técnias de reflexión en Java.

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


## 4. Exposición como Web Service

Finalmente, se expone este servicio como un Web Service. No hay detalles específicos para esto, se implementan como es 
usual en el resto de las aplicaciones de Java anotando la clase con @WebService y definiendo las configuraciones necesarias.



----
# Migración de Arquitectura Genérica a Spring Boot 3:

Dado que código que se encarga de conectar los Web Services con los Procedimientos Almacenados en la base (glue code), 
esta diseñado de forma genérica, el impácto en la migración es menor que en otros casos en el que el glue code es generado mediante 
copy/paste de código.

Por otro lado, la migración tecnológica ofrece la ventaja de permitir eliminar la necesidad de mantener manualmente el 
mapeo entre los parámetros y los solvers (o servicios), ya que ahora este proceso puede ser gestionado por Spring, al 
aprovechar las configuraciones de beans y la inyección de dependencias (con los cambios introducidos en la migración) 
Spring puede automáticamente resolver y mapear los componentes necesarios, simplificando la arquitectura y reduciendo la
posibilidad de errores manuales en el mapeo.

Esto no solo mejora la mantenibilidad del sistema, sino que también hace que la incorporación de nuevos servicios 
sea más ágil y menos propensa a errores, permitiendo un enfoque más limpio y modular para la gestión de la 
lógica de negocio.

## Migración del motor de ejecución de solvers

A continuación se detallan los pasos seguidos para migrar el motor de ejecución de solvers.

### 1. Configuración de Mapeos parámetros-solvers en Spring

Para garantizar que un nuevo servicio funcione correctamente en la arquitectura migrada, el único aspecto que debe tener 
en cuenta el desarrollador es especificar el nombre del parámetro de entrada siguiendo un patrón específico. 
Este nombre de parámetro debe comenzar con el prefijo "Param" seguido del nombre del servicio.

Por ejemplo, si el servicio que estás implementando se llama "ObtenerPolizasCliente", el parámetro de entrada 
correspondiente debería nombrarse como ParamObtenerPolizasCliente. Gracias a esta convención, el sistema resolverá 
automáticamente el solver adecuado para el servicio. En este caso, se esperaría que el solver se llame ObtenerPolizasClienteSolver.

Este enfoque elimina la necesidad de configuraciones adicionales, ya que el mapeo entre parámetros y solvers se realiza 
de manera dinámica, facilitando la implementación y manteniendo la consistencia en el código.

Para su implementación, en lugar de utilizar un archivo de propiedades combinado con mecanismos de reflexión en Java para 
mapear parámetros a los solvers, se propone una configuración basada en Spring. Esto se logrará mediante una clase anotada 
con @Configuration que expone los beans correspondientes.

Dado que AbstractSolver y XMLAbstractSolver son clases abstractas que heredan de LogicaSolver (Ver ANEXO), la configuración
de los mapeos en Spring se puede realizar de la siguiente manera:

```java
@Configuration
public class SolverConfig {
    private static final Logger logger = LoggerFactory.getLogger(SolverConfig.class);

    /**
     * Método que crea un `Map` de tipo `<String, LogicaSolver>` donde la clave es 
     * un identificador único basado en el nombre de la clase del solver y el valor es 
     * la instancia del solver. Este `Map` se utiliza para resolver dinámicamente el 
     * solver correspondiente según el nombre del parámetro recibido.
     *
     * @param solvers Lista de instancias de `LogicaSolver` disponibles en el contexto de Spring.
     * @return Un `Map` donde la clave es el nombre de la clase del solver en mayúsculas 
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

                // Extraer el nombre simple de la clase (sin el paquete).
                String simpleClassName = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);

                // Convertir el nombre de la clase a mayúsculas y eliminar espacios adicionales.
                String classId = simpleClassName.toUpperCase().trim();

                // Registrar el solver en el mapa usando el nombre de la clase como clave.
                map.put(classId, solver);

                // Registrar en el log que el solver ha sido registrado con éxito.
                logger.info("Registered solver: {}", classId);
            }
        }

        // Retornar el mapa con los solvers registrados.
        return map;
    }
}

```   

### 2. Cambios en lógica de despacho en la ejecución de los solvers:

Se refactorizó el componente LogicaMiBSE para que use el mapa de solvers para resolver dinámicamente el solver adecuado.
Esto podemos implementarlo mediante inyección del mapa de solvers generado en la Configuración de Spring, en LogicaMiBSE
y utilizando este mapa para resolver dinámicamente el solver adecuado.

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

Finalmente, se implementa el servicio en MiNuevoServicio. La clase MiNuevoServicioLocal toma el rol de service layer,
que es la capa de servicios de la aplicación. Para la aplicación MiBSE, la service layer es implementada por la clase MiBSEService

```java
public class MiNuevoServicio{

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


## 2. Implementación de servicios en el contexto del nuevo diseño del motor de ejecución de Solvers:

Cada Solver concreto implementa la interfaz LogicaSolver y define el parámetro de entrada que debe resolver. 

El nuevo mapeo de parámetros a solvers en el contexto del nuevo diseño del motor de ejecución de Solvers requiere que el
nombre del parámetro de entrada tenga esta forma: Param + NombreServicio. Por ejemplo, si el nuevo servicio que se está 
implementando se llama "ObtenerPolizasCliente", el parámetro de entrada correspondiente debería nombrarse como 
ParamObtenerPolizasCliente. Por otro lado, para derivar el nombre del solver asociado, este debe haberse nombrado con la 
forma NombreServicio + Solver. Por ejemplo, si el nuevo servicio que se está implementando se llama "ObtenerPolizasCliente", 
el solver correspondiente debería nombrarse como ObtenerPolizasClienteSolver.

```java
@Component
public class ObtenerPolizasClienteSolver extends XMLAbstractSolver {

    private final ServicioMiBsePersist servicioMiBsePersist; // O una instancia apropiada

    private final ParseoMiBse parseoMiBse;

    @Autowired
    public ObtenerPolizasClienteSolver(ServicioMiBsePersist servicioMiBsePersist, ParseoMiBse parseoMiBse) {
        this.servicioMiBsePersist = servicioMiBsePersist;
        this.parseoMiBse = parseoMiBse;
    }

    @Override
    protected ResultGenerico getMyResultInstance() {
        return new ResultObtenerPolizasCliente();
    }

    @Override
    protected ResultXmlPL getXmlResult(ParamGenerico param) {
        return servicioMiBsePersist.obtenerPolizasCliente( (ParamObtenerPolizasCliente) param);
    }

    @Override
    protected ResultGenerico parseValues(ResultXmlPL xmlResult) {
        parseoMiBse.setTextoParsear(xmlResult.getXml());
        if (parseoMiBse.generarDoc().booleanValue()) {
            return parseoMiBse.parsearObtenerPolizasCliente();
        }
        return null;
    }
}
```
Esta convención de nombres ya era utilizada en la versión de miBSE antes de la migración.

## Otros Desafíos en la Migración

El sistema de Solvers es un componente de gran impacto, debido a que los servicios se implementan utilizando sus servicios.
Pero, por otro lado, también existen algunos desafíos particulares que salen de lo general en la migración y que merecen
ser evaluados:

### Desafío 1: Colas para notificaciones de pago

//TODO
Idea1: Utilizar funciones Async de Spring para implementar colas de notificaciones de pago.

```java
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;
@Service
public class AsyncService {

    @Async
    public CompletableFuture<Void> processPurchase(Purchase purchase) {
        // Lógica para generar documentos y enviar correos electrónicos
        generateDocuments(purchase);
        sendEmail(purchase);
        return CompletableFuture.completedFuture(null);
    }

    private void generateDocuments(Purchase purchase) {
        // Implementar la lógica para generar documentos
    }

    private void sendEmail(Purchase purchase) {
        // Implementar la lógica para enviar correos electrónicos
    }
}
```
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
@Service
public class PurchaseService {
 
    @Autowired
    private AsyncService asyncService;
 
    public void handlePurchase(Purchase purchase) {
        // Lógica para manejar la compra
        // Ejecutar la operación de procesamiento de manera asíncrona
        asyncService.processPurchase(purchase);
    }
}
```

Idea2: Tener un servicio de broker/colas en OpenShift que se encargue de gestionar las colas de mensajes y ser un sistema 
de broker de mensajes entre los servicios.

### Desafío 2: Persistencia de Stream de subida de archivos
//TODO terminar

```java
public ResultSubirArchivo subirArchivo(ParamSubirArchivo param) {
    //filesystem local al jboss que corre esta app (dmz?)
    final String PATH = "/app/updownfile/corredores/tienda-bicibse/subir_archivos/";
    ResultSubirArchivo resultado = new ResultSubirArchivo();

    Logueo logueo = new Logueo();
    logueo.setEncabezado(Values.ENCABEZADOPERSIST);
    logueo.setClase(ServiciosMiBsePersist.class);
    logueo.setMetodo("subirArchivo");
    
    logueo.setParametro("Usuario", param.getUsuario());
    logueo.setParametro("Clave", param.getClave());
    logueo.setParametro("NombreArchivo", param.getNombreArchivo());
    logueo.setParametro("Archivo null?", param.getArchivo() == null);
    
    InputStream is = null;
    OutputStream os = null;
    try {
        is = param.getArchivo().getInputStream();
        os = new FileOutputStream(new File(PATH + param.getNombreArchivo()));
        IOUtils.copy(is, os);
    } catch(Exception e) {
        catchException(resultado, logueo, e);
    } finally {
        IOUtils.closeQuietly(os);
        IOUtils.closeQuietly(is);
    }
    
    return resultado;
}
```

### Desafío 3: Interceptores

//TODO

### Desafío 4: Autenticación G.U.C. (interacción con sistema de IBM)

Sistema de IBM que se encarga de autenticar usuarios. Ya se encuentra migrado hay que integrarlo con el sistema de MiBSE.

### Desafío 5: RTimeLogger (interacción con sistema de IBM)

Sistema de IBM que se encarga de consultar datos a nivel de la transacción de la base de datos, y que permite obtener el
tiempo de ejecución de las transacciones.

Hay que evaluar con el equipo de Web, si sigue siendo necesario el uso de RTimeLogger en el sistema de MiBSE, y si es así, 
integrarlo con la migración que está realizando.

## ANEXO1: Implementación de Solvers

La implementación de los Solvers sigue un enfoque jerarquico, donde cada Solver hereda de AbstractSolver o XMLAbstractSolver,
dependiendo de si se necesita procesar XML o no. La jerarquía de clases se muestra a continuación:

```plantuml
                                    +-------------------+
                                    |  <<interface>>    |
                                    |   LogicaSolver    |
                                    |-------------------|
                                    | + solve(param:    |
                                    |   ParamGenerico): |
                                    |   ResultGenerico  |
                                    +-------------------+
                                              ^
                                              |
                                              |
                                    +----------------------------+
                                    |      AbstractSolver        |
                                    |----------------------------|
                                    | - LOG: Logger              |
                                    |----------------------------|
                                    | + solve(param:             |
                                    |   ParamGenerico):          |
                                    |   ResultGenerico           |
                                    |----------------------------|
                                    | # chequeoPreCondiciones(   |
                                    |   param: ParamGenerico):   |
                                    |   ResultGenerico           |
                                    |----------------------------|
                                    | # getMyResultInstance():   |
                                    |   ResultGenerico           |
                                    |----------------------------|
                                    | # procesoLogica(param:     |
                                    |   ParamGenerico):          |
                                    |   ResultGenerico           |
                                    |----------------------------|
                                    | # procesoPostCondiciones(  |
                                    |   param: ParamGenerico,    |
                                    |   result: ResultGenerico): |
                                    |   void                     |
                                    |----------------------------|
                                    | # checkNull(resultado:     |
                                    |   ResultGenerico):         |
                                    |   ResultGenerico           |
                                    +----------------------------+
                                              ^
                                              |
                                              |
                                    +---------------------------------+
                                    |        XMLAbstractSolver        |
                                    |---------------------------------|
                                    | - LOGGER: Logger                |
                                    |---------------------------------|
                                    | + procesoLogica(param:          |
                                    |   ParamGenerico):               |
                                    |   ResultGenerico                |
                                    |---------------------------------|
                                    | - procesarErrores(xmlResult:    |
                                    |   ResultXmlPL):                 |
                                    |   ResultGenerico                |
                                    |---------------------------------|
                                    | - procesoResultadoConErrorYXml( |
                                    |   resultED: ResultGenerico,     |
                                    |   xmlResult: ResultXmlPL):      |
                                    |   ResultGenerico                |
                                    |---------------------------------|
                                    | # getXmlResult(param:           |
                                    |   ParamGenerico):               |
                                    |   ResultXmlPL                   |
                                    |---------------------------------|
                                    | # parseValues(xmlResult:        |
                                    |   ResultXmlPL):                 |
                                    |   ResultGenerico                |
                                    +---------------------------------+
```

## ANEXO2: Servicios de la Interfaz IWsServiciosMiBse

Esta es una lista de los servicios disponibles en la interfaz `IWsServiciosMiBse`:

- `obtenerComunicacionesCliente`
- `obtenerPolizasCliente`
- `actualizarFacturacionPoliza`
- `obtenerDatosCliente`
- `obtenerNumeroCliente`
- `actualizarComunicacionesCliente`
- `actualizarDatosCliente`
- `actualizarFacturacionPolizaTodo`
- `validarCodigoAdhesion`
- `registrarCliente`
- `listaProfesiones`
- `listaTipoDocumentos`
- `obtenerSeudoMailCliente`
- `olvidoClave`
- `obtenerDatosValidadosCliente`
- `informarPagoBancario`
- `informarPagoRedes`
- `existeCliente`
- `obtenerMaximoEndoso`
- `validacionSOA`
- `validacionCartaVerde`
- `logActividadMibseWsExt`
- `borrarMailCliente`
- `modificarMailCliente`
- `altaMailCliente`
- `obtenerMailsEnvioFacturaCliente`
- `validarCertificadoLibreDeudaADT`
- `obtenerMapaMsgSiniestro`
- `subirArchivo`
- `proCarta`
- `correspondeCartaPoliza`
- `proCarta2`
- `obtenerPolizasFacturasPagasCliente`
- `adherirFacturaDigital`

**Total de servicios:** 34


