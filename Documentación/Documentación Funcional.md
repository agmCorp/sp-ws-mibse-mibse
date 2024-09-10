# Índice

1. [Información general del proyecto](#Información-general-del-proyecto)
2. [Referentes](#Referentes)
3. [Introducción](#Introducción)
4. [Flujo EmisionEDepor (Embarcaciones)](#flujo-EmisionEDepor-(Embarcaciones))
    1. [WSDL del servicio](#WSDL-del-servicio-Embarcaciones)
5. [Flujo X](#flujo-X)
    1. [WSDL del servicio](#WSDL-del-servicio-x)
5. [Conexión a la BD de Test](#Conexión-a-la-BD-de-Test-para-pruebas)


## Información general del proyecto:
|Nombre|Descripción|
|------|-----------|
|MI BSE|El proyecto Mi BSE engloba todos los servicios para la operación del portal WEB Mi BSE del banco, donde los clientes pueden administrar y visualizar datos relevantes de el o los servicios contratados dentro del banco|

## Referentes
Referentes técnicos: Equipo WEB (alvaro)

Referentes funcionales: Equipo de desarrollo WEB (Leticia)



## Introducción:

El proyecto Mi BSE comprende todos los servicios para la operación del portal WEB Mi BSE del banco, donde los clientes pueden administrar y visualizar datos relevantes de el o los servicios contratados dentro del banco. con seguridad (guc y seguridad) que son desarrollados por el equipo IBM. 

Es un servicio SOAP con multimples operadaciones y cuenta con seguridad (guc y seguridad) que son desarrollados por el equipo IBM. 


Alcance - en progreso:
- **Existe Cliente:** Chequea si el cliente ya esta registrado o no, devolviendo un código de error y su descripción en caso de que ya exista, un atributo booleano "hay error" que indica si existe erro y un atributo booleano "existe" que indica en true = existe cliente o en false no existe cliente. 
- **ObtenerDatosCliente:** Consulta a partir de la CI los datos cargados en BD del cliente como pueden ser: appelido, sexo, comunicaciones, entre otros)
- **ObtenerNroCliente:** Consulta a partir de una CI y clave y devuelve el NroCliente asociado a la CI.

- **Registrar Cliente (ws:registrarCliente)**: Registrar un cliente en el sitio MI BSE.
- **Viajeros - Viajeros**: Cuenta con 8 operaciones, 6 de ellas consultas que alimentan los formularios de la web, los dos restantes son POST que envian datos para la cotización y emisión de la póliza.  
- **Olvido Clave (ws:olvidoClave)** Funcion para el olvido de contraseña. 



### WSDL del servicio Embarcaciones
|Ambiente|URL|
|--------|---|
|Anterior a migración TEST|http://jboss-test.bse.com.uy:8080/bsetiendaws/EmisionEDepor?wsdl|
|Post-Migración TEST|| 

#### Operación consultaCoberturasRc
Obtiene la lista de Coberturas posibles para el producto.

##### Parámetros de entrada:
|Nombre|Atributo|Descripción del parámetro|Tipo|Obligatorio|Comentarios|
|------|----------|-----------------|----|------|-----------|
|edep:consultaTiposBuques|usaurio|usuario, pero no el usuario final ya que este endpoint es anónimo, que realiza la consulta (el frontend de la tienda en prod es un usuario, para testing se generará uno especifico)|String|Si|En test: usuario: test ; contraseña: Prueba.2024|
||constraseña|contraseña del usuario|String|Si||



##### Parámetros de Salida:
|Nombre|Atributo|Descripción del parámetro|Tipo|Comentarios|
|------|--------|-------------------------|----|-----------|
|<coberturasRc>|codigo|código de la cobertura en BD|Number|solo lo devuelve para consulta valida|
||nombre|nombre de la cobertura que muestra en el front|String|solo lo devuelve para consulta valida|
|<return>|codigoError|codigo de error en la invocación|Numer|00 para invocaciones válidas|
||descripcionError|descripción del error|String|vacio para invocaciones válidas|


##### Ejemplos de invocación

NOTA: Se adjunta proyecto SOAPUI con invocaciones de ejemplo ya cargadas 

###### Ejemplo de invocación válida: 
Request:
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:edep="http://edeportivas.ws.servicios.bse.com/">
   <soapenv:Header/>
   <soapenv:Body>
      <edep:consultaCoberturasRc>
         <usuario>test</usuario>
         <contrasena>Prueba.2024</contrasena>
      </edep:consultaCoberturasRc>
   </soapenv:Body>
</soapenv:Envelope>
```


Response:
```xml
<env:Envelope xmlns:env="http://schemas.xmlsoap.org/soap/envelope/">
   <env:Header/>
   <env:Body>
      <ns2:consultaCoberturasRcResponse xmlns:ns2="http://edeportivas.ws.servicios.bse.com/">
         <return>
            <coberturasRc>
               <codigo>100000</codigo>
               <nombre>U$S 100.000</nombre>
            </coberturasRc>
            <coberturasRc>
               <codigo>25000</codigo>
               <nombre>U$S 25.000</nombre>
            </coberturasRc>
            <coberturasRc>
               <codigo>50000</codigo>
               <nombre>U$S 50.000</nombre>
            </coberturasRc>
            <codigoError>00</codigoError>
            <descripcionError/>
         </return>
      </ns2:consultaCoberturasRcResponse>
   </env:Body>
</env:Envelope>
```

###### Ejemplo de invocación inválida: Requeridos vacios

Request: 
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:edep="http://edeportivas.ws.servicios.bse.com/">
   <soapenv:Header/>
   <soapenv:Body>
      <edep:consultaCoberturasRc>
         <usuario></usuario>
         <contrasena></contrasena>
      </edep:consultaCoberturasRc>
   </soapenv:Body>
</soapenv:Envelope>
```

Response:
```
<env:Envelope xmlns:env="http://schemas.xmlsoap.org/soap/envelope/">
   <env:Header/>
   <env:Body>
      <ns2:consultaCoberturasRcResponse xmlns:ns2="http://edeportivas.ws.servicios.bse.com/">
         <return>
            <codigoError>3</codigoError>
            <descripcionError>Usuario o contrasena invalidos</descripcionError>
         </return>
      </ns2:consultaCoberturasRcResponse>
   </env:Body>
</env:Envelope>
```
##### Errores identificados:
|Resultado del request|Código de error|Descripción del error|
|---------------------|---------------|---------------------|
|200 OK|3|Usuario o contraseña inválida|

#### Operación consultaTiposBuques
Obtiene la lista de tipos de buques posibles para el producto que se quiere cotizar.

##### Parámetros de entrada:
|Nombre|Atributo|Descripción del parámetro|Tipo|Obligatorio|Comentarios|
|------|----------|-----------------|----|------|-----------|
|edep:consultaCoberturasRc|usaurio|usuario, pero no el usuario final ya que este endpoint es anónimo, que realiza la consulta (el frontend de la tienda en prod es un usuario, para testing se generará uno especifico)|String|Si|**Nota:Pendiente Usuario de test**|
||constraseña|contraseña del usuario|String|Si||



##### Parámetros de Salida:
|Nombre|Atributo|Descripción del parámetro|Tipo|Comentarios|
|------|--------|-------------------------|----|-----------|
|ns2:consultaTiposBuquesResponse|codigoError|Number?|00 si la invocación es válida y código de error si es inválida (ver lista de errores debajo)|
||descripcionError|Describe el error generado en caso de existir.|String| Para invocaciones válidas (código de error 00) viene vacío|
|tiposBuques|tiposBuques|lista de tipos de buques disponibles|List|Solo la devuelve si error:00|
||código|codigo que identifica al tipo de buque en BD|number||
||nombre|nombre que se muestra en el forntend|String||


##### Ejemplos de invocación

NOTA: Se adjunta proyecto SOAPUI con invocaciones de ejemplo ya cargadas 

###### Ejemplo de invocación válida: 
Request:
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:edep="http://edeportivas.ws.servicios.bse.com/">
   <soapenv:Header/>
   <soapenv:Body>
      <edep:consultaTiposBuques>
         <usuario>test</usuario>
         <contrasena>Prueba.2024</contrasena>
      </edep:consultaTiposBuques>
   </soapenv:Body>
</soapenv:Envelope>
```


Response:
```xml
<env:Envelope xmlns:env="http://schemas.xmlsoap.org/soap/envelope/">
   <env:Header/>
   <env:Body>
      <ns2:consultaTiposBuquesResponse xmlns:ns2="http://edeportivas.ws.servicios.bse.com/">
         <return>
            <codigoError>00</codigoError>
            <descripcionError/>
            <tiposBuques>
               <codigo>1</codigo>
               <nombre>LANCHA</nombre>
            </tiposBuques>
            <tiposBuques>
               <codigo>2</codigo>
               <nombre>VELERO</nombre>
            </tiposBuques>
            <tiposBuques>
               <codigo>3</codigo>
               <nombre>CRUCERO</nombre>
            </tiposBuques>
            <tiposBuques>
               <codigo>4</codigo>
               <nombre>GOMÒN</nombre>
            </tiposBuques>
            <tiposBuques>
               <codigo>5</codigo>
               <nombre>BOTE</nombre>
            </tiposBuques>
            <tiposBuques>
               <codigo>6</codigo>
               <nombre>OTROS</nombre>
            </tiposBuques>
         </return>
      </ns2:consultaTiposBuquesResponse>
   </env:Body>
</env:Envelope>
```



###### Ejemplo de invocación inválida: Contraseña incorrecta

Request: 
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:edep="http://edeportivas.ws.servicios.bse.com/">
   <soapenv:Header/>
   <soapenv:Body>
      <edep:consultaTiposBuques>
         <usuario>bse</usuario>
         <contrasena>contraseñaincorrecta</contrasena>
      </edep:consultaTiposBuques>
   </soapenv:Body>
</soapenv:Envelope>
```

Response:
```xml
<env:Envelope xmlns:env="http://schemas.xmlsoap.org/soap/envelope/">
   <env:Header/>
   <env:Body>
      <ns2:consultaTiposBuquesResponse xmlns:ns2="http://edeportivas.ws.servicios.bse.com/">
         <return>
            <codigoError>3</codigoError>
            <descripcionError>Usuario o contrasena invalidos</descripcionError>
         </return>
      </ns2:consultaTiposBuquesResponse>
   </env:Body>
</env:Envelope>
```


##### Errores identificados:
|Resultado del request|Código de error|Descripción del error|
|---------------------|---------------|---------------------|
|200 OK|3|Usuario o contraseña inválida|

#### Hallazgos durante analisis:

Todas las peticiones retornan un 200 OK, incluso si hay un error, el error está en el response. Por ejemplo al invocar con datos requeridos vacios, devuelve un 200OK pero error código 3.

>> Los cambios se pueden vaidar en la tabla (Nombre de la tabla), ver datasource debajo para conexión con la BD de test.



## Flujo X


### WSDL del servicio x
|Ambiente|URL|
|--------|---|
|Anterior a migración TEST||
|Post-Migración TEST|| 

###


#### Parámetros de entrada:
|Nombre|Atributo|Descripción del parámetro|Tipo|Obligatorio|Comentarios|
|------|----------|-----------------|----|------|-----------|



#### Parámetros de Salida:
|Nombre|Atributo|Descripción del parámetro|Tipo|Comentarios|
|------|--------|-------------------------|----|-----------|



#### Ejemplos de invocación

NOTA: Se adjunta proyecto SOAPUI con invocaciones de ejemplo ya cargadas


##### Ejemplo de invocación válida:

Request: 
```xml
```

Response:
```

```


#### Ejemplo de invocación inválida: 

```
```
Response:
```
```

## Conexión a la BD de Test para pruebas
```

```



