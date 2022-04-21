package com.bse.accesodatos.esoa;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Direction;
import org.eclipse.persistence.annotations.NamedStoredProcedureQueries;
import org.eclipse.persistence.annotations.NamedStoredProcedureQuery;
import org.eclipse.persistence.annotations.StoredProcedureParameter;

@Entity
@Table(name = "CART_RAMOS_POLIZAS")

@NamedStoredProcedureQueries({

        @NamedStoredProcedureQuery(  
        name="PRO_COTIZAR_SOA",  
        //procedureName="RECTOR.PACK_SOA_PORTAL.PRO_COTIZAR_SOA",
        procedureName="RECTOR.PACK_EMI_MIBSE.PRO_COTIZAR_SOA",
        parameters={
			@StoredProcedureParameter(queryParameter="P_TP_DOCUMENTO",name="P_TP_DOCUMENTO",direction=Direction.IN,type=String.class),
			@StoredProcedureParameter(queryParameter="P_NRO_DOCUMENTO",name="P_NRO_DOCUMENTO",direction=Direction.IN,type=String.class), 
			@StoredProcedureParameter(queryParameter="P_SUCURSAL",name="P_SUCURSAL",direction=Direction.IN,type=String.class),

			@StoredProcedureParameter(queryParameter="P_RAMO",name="P_RAMO",direction=Direction.IN,type=String.class),
			@StoredProcedureParameter(queryParameter="P_PRODUCTO",name="P_PRODUCTO",direction=Direction.IN,type=String.class),
			@StoredProcedureParameter(queryParameter="P_PRODUCTOR",name="P_PRODUCTOR",direction=Direction.IN,type=String.class),

			@StoredProcedureParameter(queryParameter="P_FECHA_DESDE",name="P_FECHA_DESDE",direction=Direction.IN,type=Date.class),
			@StoredProcedureParameter(queryParameter="P_MARCA_VEHICULO",name="P_MARCA_VEHICULO",direction=Direction.IN,type=String.class),
			@StoredProcedureParameter(queryParameter="P_ANIO_VEHICULO",name="P_ANIO_VEHICULO",direction=Direction.IN,type=String.class),
			@StoredProcedureParameter(queryParameter="P_CATEGORIA_VEHICULO",name="P_CATEGORIA_VEHICULO",direction=Direction.IN,type=String.class),
			@StoredProcedureParameter(queryParameter="P_MATRICULA_VEHICULO",name="P_MATRICULA_VEHICULO",direction=Direction.IN,type=String.class),
			@StoredProcedureParameter(queryParameter="P_PADRON_VEHICULO",name="P_PADRON_VEHICULO",direction=Direction.IN,type=String.class),
			@StoredProcedureParameter(queryParameter="P_MOTOR_VEHICULO",name="P_MOTOR_VEHICULO",direction=Direction.IN,type=String.class),
			@StoredProcedureParameter(queryParameter="P_CHASIS_VEHICULO",name="P_CHASIS_VEHICULO",direction=Direction.IN,type=String.class),
			@StoredProcedureParameter(queryParameter="P_PLAN_COBERTURA",name="P_PLAN_COBERTURA",direction=Direction.IN,type=String.class),
			@StoredProcedureParameter(queryParameter="P_PLAN_PAGO",name="P_PLAN_PAGO",direction=Direction.IN,type=Integer.class),
			@StoredProcedureParameter(queryParameter="P_MONEDA",name="P_MONEDA",direction=Direction.IN,type=String.class),
			
			@StoredProcedureParameter(queryParameter="P_TIPO_CALCULO",name="P_TIPO_CALCULO",direction=Direction.IN,type=String.class),
			@StoredProcedureParameter(queryParameter="P_FORMA_PAGO",name="P_FORMA_PAGO",direction=Direction.IN,type=String.class),
			@StoredProcedureParameter(queryParameter="P_VIG_TECNICA",name="P_VIG_TECNICA",direction=Direction.IN,type=String.class),
			@StoredProcedureParameter(queryParameter="P_MEDIO_PAGO",name="P_MEDIO_PAGO",direction=Direction.IN,type=Integer.class),
			@StoredProcedureParameter(queryParameter="P_ORIGEN_PAGO",name="P_ORIGEN_PAGO",direction=Direction.IN,type=String.class),
			@StoredProcedureParameter(queryParameter="P_TIPO_FACT",name="P_TIPO_FACT",direction=Direction.IN,type=String.class),
			@StoredProcedureParameter(queryParameter="P_PROMOCION",name="P_PROMOCION",direction=Direction.IN,type=String.class),
			@StoredProcedureParameter(queryParameter="P_RENOVACION",name="P_RENOVACION",direction=Direction.IN,type=String.class),
			@StoredProcedureParameter(queryParameter="P_USUARIO_WEB",name="P_USUARIO_WEB",direction=Direction.IN,type=String.class),

			@StoredProcedureParameter(queryParameter="P_CONSUMIDOR_FINAL",name="P_CONSUMIDOR_FINAL",direction=Direction.IN,type=String.class),
			@StoredProcedureParameter(queryParameter="P_FACT_MAIL",name="P_FACT_MAIL",direction=Direction.IN,type=String.class),
			@StoredProcedureParameter(queryParameter="P_COASEGURO",name="P_COASEGURO",direction=Direction.IN,type=String.class),
			@StoredProcedureParameter(queryParameter="P_EMITIR",name="P_EMITIR",direction=Direction.IN,type=String.class),
			
			@StoredProcedureParameter(queryParameter="P_COTIZACION_EMITIDA",name="P_COTIZACION_EMITIDA",direction=Direction.OUT,type=Long.class),
			@StoredProcedureParameter(queryParameter="P_PREMIO_COTIZACION",name="P_PREMIO_COTIZACION",direction=Direction.OUT,type=Float.class),
			@StoredProcedureParameter(queryParameter="P_PREMIO_COTIZACION_FACTURAR",name="P_PREMIO_COTIZACION_FACTURAR",direction=Direction.OUT,type=Float.class),
			@StoredProcedureParameter(queryParameter="P_FECHA_HASTA",name="P_FECHA_HASTA",direction=Direction.OUT,type=Date.class),
			@StoredProcedureParameter(queryParameter="P_CUOTAS",name="P_CUOTAS",direction=Direction.OUT,type=String.class),
			@StoredProcedureParameter(queryParameter="P_PLANES",name="P_PLANES",direction=Direction.OUT,type=String.class),
			
			@StoredProcedureParameter(queryParameter="P_RESULTADO",name="P_RESULTADO",direction=Direction.OUT,type=String.class),
			@StoredProcedureParameter(queryParameter="P_MENSAJE_EMISION",name="P_MENSAJE_EMISION",direction=Direction.OUT,type=String.class)
		    
        	}
        ),


        @NamedStoredProcedureQuery(  
        name="PRO_EMITIR_SOA",  
        //procedureName="RECTOR.PACK_SOA_PORTAL.PRO_EMITIR_SOA",
        procedureName="RECTOR.PACK_EMI_MIBSE.PRO_EMITIR_SOA",
        parameters={
			@StoredProcedureParameter(queryParameter="P_TP_DOCUMENTO",name="P_TP_DOCUMENTO",direction=Direction.IN,type=String.class),
			@StoredProcedureParameter(queryParameter="P_NRO_DOCUMENTO",name="P_NRO_DOCUMENTO",direction=Direction.IN,type=String.class), 

			@StoredProcedureParameter(queryParameter="P_SUCURSAL",name="P_SUCURSAL",direction=Direction.IN,type=String.class),
			@StoredProcedureParameter(queryParameter="P_RAMO",name="P_RAMO",direction=Direction.IN,type=String.class),
			@StoredProcedureParameter(queryParameter="P_PRODUCTO",name="P_PRODUCTO",direction=Direction.IN,type=String.class),

			@StoredProcedureParameter(queryParameter="P_MATRICULA_VEHICULO",name="P_MATRICULA_VEHICULO",direction=Direction.IN,type=String.class),
			@StoredProcedureParameter(queryParameter="P_PADRON_VEHICULO",name="P_PADRON_VEHICULO",direction=Direction.IN,type=String.class),
			@StoredProcedureParameter(queryParameter="P_MOTOR_VEHICULO",name="P_MOTOR_VEHICULO",direction=Direction.IN,type=String.class),
			@StoredProcedureParameter(queryParameter="P_CHASIS_VEHICULO",name="P_CHASIS_VEHICULO",direction=Direction.IN,type=String.class),
			@StoredProcedureParameter(queryParameter="P_COTIZACION_EMITIDA",name="P_COTIZACION_EMITIDA",direction=Direction.IN,type=Long.class),
			
			@StoredProcedureParameter(queryParameter="P_CONSUMO_FINAL",name="P_CONSUMO_FINAL",direction=Direction.IN,type=String.class),
			
			@StoredProcedureParameter(queryParameter="P_POLIZA_EMITIDA",name="P_POLIZA_EMITIDA",direction=Direction.OUT,type=Long.class),
			@StoredProcedureParameter(queryParameter="P_PREMIO_COTIZACION",name="P_PREMIO_COTIZACION",direction=Direction.OUT,type=Float.class),
			@StoredProcedureParameter(queryParameter="P_PREMIO_COTIZACION_FACTURAR",name="P_PREMIO_COTIZACION_FACTURAR",direction=Direction.OUT,type=Float.class),
			@StoredProcedureParameter(queryParameter="P_MARCA_VEHICULO",name="P_MARCA_VEHICULO",direction=Direction.OUT,type=String.class),
			@StoredProcedureParameter(queryParameter="P_ANIO_VEHICULO",name="P_ANIO_VEHICULO",direction=Direction.OUT,type=String.class),
			@StoredProcedureParameter(queryParameter="P_CATEGORIA_VEHICULO",name="P_CATEGORIA_VEHICULO",direction=Direction.OUT,type=String.class),
			@StoredProcedureParameter(queryParameter="P_FECHA_DESDE",name="P_FECHA_DESDE",direction=Direction.OUT,type=Date.class),
			@StoredProcedureParameter(queryParameter="P_FECHA_HASTA",name="P_FECHA_HASTA",direction=Direction.OUT,type=Date.class),
			@StoredProcedureParameter(queryParameter="P_PLAN_COBERTURA",name="P_PLAN_COBERTURA",direction=Direction.OUT,type=String.class),
			@StoredProcedureParameter(queryParameter="P_PLAN_PAGO",name="P_PLAN_PAGO",direction=Direction.OUT,type=Integer.class), 
			@StoredProcedureParameter(queryParameter="P_CUOTAS",name="P_CUOTAS",direction=Direction.OUT,type=String.class),

			@StoredProcedureParameter(queryParameter="P_RESULTADO",name="P_RESULTADO",direction=Direction.OUT,type=String.class),
			@StoredProcedureParameter(queryParameter="P_MENSAJE_EMISION",name="P_MENSAJE_EMISION",direction=Direction.OUT,type=String.class)
		    
        	}
        ),
        
        @NamedStoredProcedureQuery(  
                name="PRO_COTIZAR_INDIVI",  
                procedureName="RECTOR.PACK_EMI_MIBSE.PRO_COTIZAR_INDIVI",
                parameters={
        			@StoredProcedureParameter(queryParameter="P_TP_DOCUMENTO",name="P_TP_DOCUMENTO",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_NRO_DOCUMENTO",name="P_NRO_DOCUMENTO",direction=Direction.IN,type=String.class), 
        			@StoredProcedureParameter(queryParameter="P_SUCURSAL",name="P_SUCURSAL",direction=Direction.IN,type=String.class),

        			@StoredProcedureParameter(queryParameter="P_RAMO",name="P_RAMO",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_PRODUCTO",name="P_PRODUCTO",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_PRODUCTOR",name="P_PRODUCTOR",direction=Direction.IN,type=String.class),

        			@StoredProcedureParameter(queryParameter="P_FECHA_DESDE",name="P_FECHA_DESDE",direction=Direction.IN,type=Date.class),
        			@StoredProcedureParameter(queryParameter="P_MARCA_VEHICULO",name="P_MARCA_VEHICULO",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_ANIO_VEHICULO",name="P_ANIO_VEHICULO",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_MATRICULA_VEHICULO",name="P_MATRICULA_VEHICULO",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_PADRON_VEHICULO",name="P_PADRON_VEHICULO",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_MOTOR_VEHICULO",name="P_MOTOR_VEHICULO",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_CHASIS_VEHICULO",name="P_CHASIS_VEHICULO",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_PLAN_COBERTURA",name="P_PLAN_COBERTURA",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_PLAN_PAGO",name="P_PLAN_PAGO",direction=Direction.IN,type=Integer.class),
        			@StoredProcedureParameter(queryParameter="P_MONEDA",name="P_MONEDA",direction=Direction.IN,type=String.class),
        			
        			@StoredProcedureParameter(queryParameter="P_TIPO_CALCULO",name="P_TIPO_CALCULO",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_FORMA_PAGO",name="P_FORMA_PAGO",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_VIG_TECNICA",name="P_VIG_TECNICA",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_MEDIO_PAGO",name="P_MEDIO_PAGO",direction=Direction.IN,type=Integer.class),
        			@StoredProcedureParameter(queryParameter="P_ORIGEN_PAGO",name="P_ORIGEN_PAGO",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_TIPO_FACT",name="P_TIPO_FACT",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_PROMOCION",name="P_PROMOCION",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_RENOVACION",name="P_RENOVACION",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_USUARIO_WEB",name="P_USUARIO_WEB",direction=Direction.IN,type=String.class),

        			@StoredProcedureParameter(queryParameter="P_CONSUMIDOR_FINAL",name="P_CONSUMIDOR_FINAL",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_FACT_MAIL",name="P_FACT_MAIL",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_COASEGURO",name="P_COASEGURO",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_EMITIR",name="P_EMITIR",direction=Direction.IN,type=String.class),
        			
        			@StoredProcedureParameter(queryParameter="P_TIPO_VEHICULO",name="P_TIPO_VEHICULO",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_COMBUSTIBLE",name="P_COMBUSTIBLE",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_MODALIDAD",name="P_MODALIDAD",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_AREA_CIRCULACION",name="P_AREA_CIRCULACION",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_MODELO_VEHICULO",name="P_MODELO_VEHICULO",direction=Direction.IN,type=String.class),
        			
        			@StoredProcedureParameter(queryParameter="P_COTIZACION_EMITIDA",name="P_COTIZACION_EMITIDA",direction=Direction.OUT,type=Long.class),
        			@StoredProcedureParameter(queryParameter="P_PREMIO_COTIZACION",name="P_PREMIO_COTIZACION",direction=Direction.OUT,type=Float.class),
        			@StoredProcedureParameter(queryParameter="P_PREMIO_COTIZACION_FACTURAR",name="P_PREMIO_COTIZACION_FACTURAR",direction=Direction.OUT,type=Float.class),
        			@StoredProcedureParameter(queryParameter="P_FECHA_HASTA",name="P_FECHA_HASTA",direction=Direction.OUT,type=Date.class),
        			@StoredProcedureParameter(queryParameter="P_CUOTAS",name="P_CUOTAS",direction=Direction.OUT,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_PLANES",name="P_PLANES",direction=Direction.OUT,type=String.class),
        			
        			@StoredProcedureParameter(queryParameter="P_RESULTADO",name="P_RESULTADO",direction=Direction.OUT,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_MENSAJE_EMISION",name="P_MENSAJE_EMISION",direction=Direction.OUT,type=String.class)
        		    
                	}
                ),

                @NamedStoredProcedureQuery(  
                name="PRO_EMITIR_INDIVI",  
                procedureName="RECTOR.PACK_EMI_MIBSE.PRO_EMITIR_INDIVI",
                parameters={
        			@StoredProcedureParameter(queryParameter="P_TP_DOCUMENTO",name="P_TP_DOCUMENTO",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_NRO_DOCUMENTO",name="P_NRO_DOCUMENTO",direction=Direction.IN,type=String.class), 

        			@StoredProcedureParameter(queryParameter="P_SUCURSAL",name="P_SUCURSAL",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_RAMO",name="P_RAMO",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_PRODUCTO",name="P_PRODUCTO",direction=Direction.IN,type=String.class),

        			@StoredProcedureParameter(queryParameter="P_MATRICULA_VEHICULO",name="P_MATRICULA_VEHICULO",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_PADRON_VEHICULO",name="P_PADRON_VEHICULO",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_MOTOR_VEHICULO",name="P_MOTOR_VEHICULO",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_CHASIS_VEHICULO",name="P_CHASIS_VEHICULO",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_COTIZACION_EMITIDA",name="P_COTIZACION_EMITIDA",direction=Direction.IN,type=Long.class),
        			
        			@StoredProcedureParameter(queryParameter="P_CONSUMO_FINAL",name="P_CONSUMO_FINAL",direction=Direction.IN,type=String.class),
        			
        			@StoredProcedureParameter(queryParameter="P_POLIZA_EMITIDA",name="P_POLIZA_EMITIDA",direction=Direction.OUT,type=Long.class),
        			@StoredProcedureParameter(queryParameter="P_PREMIO_COTIZACION",name="P_PREMIO_COTIZACION",direction=Direction.OUT,type=Float.class),
        			@StoredProcedureParameter(queryParameter="P_PREMIO_COTIZACION_FACTURAR",name="P_PREMIO_COTIZACION_FACTURAR",direction=Direction.OUT,type=Float.class),
        			@StoredProcedureParameter(queryParameter="P_MARCA_VEHICULO",name="P_MARCA_VEHICULO",direction=Direction.OUT,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_ANIO_VEHICULO",name="P_ANIO_VEHICULO",direction=Direction.OUT,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_FECHA_DESDE",name="P_FECHA_DESDE",direction=Direction.OUT,type=Date.class),
        			@StoredProcedureParameter(queryParameter="P_FECHA_HASTA",name="P_FECHA_HASTA",direction=Direction.OUT,type=Date.class),
        			@StoredProcedureParameter(queryParameter="P_PLAN_COBERTURA",name="P_PLAN_COBERTURA",direction=Direction.OUT,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_PLAN_PAGO",name="P_PLAN_PAGO",direction=Direction.OUT,type=Integer.class), 
        			@StoredProcedureParameter(queryParameter="P_CALIDAD_CONTRATANTE",name="P_CALIDAD_CONTRATANTE",direction=Direction.IN,type=Integer.class), 
        			@StoredProcedureParameter(queryParameter="P_CUOTAS",name="P_CUOTAS",direction=Direction.OUT,type=String.class),

        			@StoredProcedureParameter(queryParameter="P_RESULTADO",name="P_RESULTADO",direction=Direction.OUT,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_MENSAJE_EMISION",name="P_MENSAJE_EMISION",direction=Direction.OUT,type=String.class)

                	}
                ),
        
        @NamedStoredProcedureQuery(  
                name="PRO_COTIZAR_VIAJEROS",  
                procedureName="RECTOR.PACK_EMI_MIBSE.PRO_COTIZAR_VIAJEROS",
                parameters={
        			@StoredProcedureParameter(queryParameter="P_TP_DOCUMENTO",name="P_TP_DOCUMENTO",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_NRO_DOCUMENTO",name="P_NRO_DOCUMENTO",direction=Direction.IN,type=String.class), 
        			@StoredProcedureParameter(queryParameter="P_SUCURSAL",name="P_SUCURSAL",direction=Direction.IN,type=String.class),
        			
        			@StoredProcedureParameter(queryParameter="P_RAMO",name="P_RAMO",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_PRODUCTO",name="P_PRODUCTO",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_PRODUCTOR",name="P_PRODUCTOR",direction=Direction.IN,type=String.class),
        			
        			@StoredProcedureParameter(queryParameter="P_FECHA_DESDE",name="P_FECHA_DESDE",direction=Direction.IN,type=Date.class),
        			@StoredProcedureParameter(queryParameter="P_FECHA_HASTA",name="P_FECHA_HASTA",direction=Direction.IN,type=Date.class),
        			@StoredProcedureParameter(queryParameter="P_PLAN_COBERTURA",name="P_PLAN_COBERTURA",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_PLAN_PAGO",name="P_PLAN_PAGO",direction=Direction.IN,type=Integer.class),
        			@StoredProcedureParameter(queryParameter="P_MONEDA",name="P_MONEDA",direction=Direction.IN,type=String.class),
        			
        			@StoredProcedureParameter(queryParameter="P_TIPO_CALCULO",name="P_TIPO_CALCULO",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_FORMA_PAGO",name="P_FORMA_PAGO",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_VIG_TECNICA",name="P_VIG_TECNICA",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_MEDIO_PAGO",name="P_MEDIO_PAGO",direction=Direction.IN,type=Integer.class),
        			@StoredProcedureParameter(queryParameter="P_ORIGEN_PAGO",name="P_ORIGEN_PAGO",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_TIPO_FACT",name="P_TIPO_FACT",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_PROMOCION",name="P_PROMOCION",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_RENOVACION",name="P_RENOVACION",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_USUARIO_WEB",name="P_USUARIO_WEB",direction=Direction.IN,type=String.class),
        			
        			@StoredProcedureParameter(queryParameter="P_CONSUMIDOR_FINAL",name="P_CONSUMIDOR_FINAL",direction=Direction.IN,type=String.class), 
        			@StoredProcedureParameter(queryParameter="P_FACT_MAIL",name="P_FACT_MAIL",direction=Direction.IN,type=String.class), 
        			@StoredProcedureParameter(queryParameter="P_COASEGURO",name="P_COASEGURO",direction=Direction.IN,type=String.class), 
        			@StoredProcedureParameter(queryParameter="P_EMITIR",name="P_EMITIR",direction=Direction.IN,type=String.class),

        			@StoredProcedureParameter(queryParameter="P_EXTENSION",name="P_EXTENSION",direction=Direction.IN,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_FECHA_SALIDA_PAIS",name="P_FECHA_SALIDA_PAIS",direction=Direction.IN,type=Date.class),
        			
        	        @StoredProcedureParameter(queryParameter="P_COTIZACION_EMITIDA",name="P_COTIZACION_EMITIDA",direction=Direction.OUT,type=Long.class),
        			@StoredProcedureParameter(queryParameter="P_PREMIO_COTIZACION",name="P_PREMIO_COTIZACION",direction=Direction.OUT,type=Float.class),
        			@StoredProcedureParameter(queryParameter="P_PREMIO_COTIZACION_FACTURAR",name="P_PREMIO_COTIZACION_FACTURAR",direction=Direction.OUT,type=Float.class),
        			@StoredProcedureParameter(queryParameter="P_CUOTAS",name="P_CUOTAS",direction=Direction.OUT,type=String.class),

        			@StoredProcedureParameter(queryParameter="P_LISTA_PERSONAS", name="P_LISTA_PERSONAS",direction=Direction.IN_OUT,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_RESULTADO",name="P_RESULTADO",direction=Direction.OUT,type=String.class),
        			@StoredProcedureParameter(queryParameter="P_MENSAJE_EMISION",name="P_MENSAJE_EMISION",direction=Direction.OUT,type=String.class)

                	}
                ),
                
                @NamedStoredProcedureQuery(  
                        name="PRO_OBT_PAR_BUSQUEDA",  
                        procedureName="RECTOR.PACK_EMI_MIBSE.PRO_OBT_PAR_BUSQUEDA",
                        parameters={
                			@StoredProcedureParameter(queryParameter="P_FECHA_DESDE",name="P_FECHA_DESDE",direction=Direction.IN,type=Date.class),
                			@StoredProcedureParameter(queryParameter="P_PAR_BUSQUEDA", name="P_PAR_BUSQUEDA",direction=Direction.OUT,type=String.class)
                        }
                ),


                @NamedStoredProcedureQuery(  
	                name="PRO_EMITIR_VIAJEROS",  
	                procedureName="RECTOR.PACK_EMI_MIBSE.PRO_EMITIR_VIAJEROS",
	                parameters={
	                	@StoredProcedureParameter(queryParameter="P_TP_DOCUMENTO_CONTRATANTE",name="P_TP_DOCUMENTO_CONTRATANTE",direction=Direction.IN,type=String.class), 
	                	@StoredProcedureParameter(queryParameter="P_NRO_DOCUMENTO_CONTRATANTE",name="P_NRO_DOCUMENTO_CONTRATANTE",direction=Direction.IN,type=String.class),
	                	
	        			@StoredProcedureParameter(queryParameter="P_SUCURSAL",name="P_SUCURSAL",direction=Direction.IN,type=String.class),
	        			@StoredProcedureParameter(queryParameter="P_RAMO",name="P_RAMO",direction=Direction.IN,type=String.class),
	        			@StoredProcedureParameter(queryParameter="P_PRODUCTO",name="P_PRODUCTO",direction=Direction.IN,type=String.class),
	
	        			@StoredProcedureParameter(queryParameter="P_COTIZACION_EMITIDA",name="P_COTIZACION_EMITIDA",direction=Direction.IN,type=Long.class),
	
	        			@StoredProcedureParameter(queryParameter="P_CONSUMIDOR_FINAL",name="P_CONSUMIDOR_FINAL",direction=Direction.IN,type=Long.class),
	        			@StoredProcedureParameter(queryParameter="P_LISTA_PERSONAS",name="P_LISTA_PERSONAS",direction=Direction.IN,type=Long.class),
	        			@StoredProcedureParameter(queryParameter="P_POLIZA_EMITIDA",name="P_POLIZA_EMITIDA",direction=Direction.OUT,type=Long.class),
	        			@StoredProcedureParameter(queryParameter="P_PREMIO_COTIZACION",name="P_PREMIO_COTIZACION",direction=Direction.OUT,type=Float.class),
	        			@StoredProcedureParameter(queryParameter="P_PREMIO_COTIZACION_FACTURAR",name="P_PREMIO_COTIZACION_FACTURAR",direction=Direction.OUT,type=Float.class),
	        			@StoredProcedureParameter(queryParameter="P_FECHA_DESDE",name="P_FECHA_DESDE",direction=Direction.OUT,type=Date.class),
	        			@StoredProcedureParameter(queryParameter="P_FECHA_HASTA",name="P_FECHA_HASTA",direction=Direction.OUT,type=Date.class),
	        			@StoredProcedureParameter(queryParameter="P_PLAN_COBERTURA",name="P_PLAN_COBERTURA",direction=Direction.OUT,type=String.class),
	        			@StoredProcedureParameter(queryParameter="P_PLAN_PAGO",name="P_PLAN_PAGO",direction=Direction.OUT,type=Integer.class), 
	        			@StoredProcedureParameter(queryParameter="P_EXTENSION",name="P_EXTENSION",direction=Direction.IN,type=String.class),
	        			@StoredProcedureParameter(queryParameter="P_FECHA_SALIDA_PAIS",name="P_FECHA_SALIDA_PAIS",direction=Direction.IN,type=Date.class),
	        			@StoredProcedureParameter(queryParameter="P_CUOTAS",name="P_CUOTAS",direction=Direction.OUT,type=String.class),
	        			@StoredProcedureParameter(queryParameter="P_RESULTADO",name="P_RESULTADO",direction=Direction.OUT,type=String.class),
	        			@StoredProcedureParameter(queryParameter="P_MENSAJE_EMISION",name="P_MENSAJE_EMISION",direction=Direction.OUT,type=String.class)
	        		    
                	}
                ),
                
                @NamedStoredProcedureQuery(  
                        name="PRO_COTIZAR_EDEPOR",  
                        procedureName="RECTOR.PACK_EMI_MIBSE.PRO_COTIZAR_EDEPOR",
                        parameters={
                    			@StoredProcedureParameter(queryParameter="P_TP_DOCUMENTO",name="P_TP_DOCUMENTO",direction=Direction.IN,type=String.class),
                    			@StoredProcedureParameter(queryParameter="P_NRO_DOCUMENTO",name="P_NRO_DOCUMENTO",direction=Direction.IN,type=String.class), 
                    			@StoredProcedureParameter(queryParameter="P_SUCURSAL",name="P_SUCURSAL",direction=Direction.IN,type=String.class),
                    			@StoredProcedureParameter(queryParameter="P_RAMO",name="P_RAMO",direction=Direction.IN,type=String.class),
                    			@StoredProcedureParameter(queryParameter="P_PRODUCTO",name="P_PRODUCTO",direction=Direction.IN,type=String.class),
                    			@StoredProcedureParameter(queryParameter="P_PRODUCTOR",name="P_PRODUCTOR",direction=Direction.IN,type=String.class),
                    			@StoredProcedureParameter(queryParameter="P_FECHA_DESDE",name="P_FECHA_DESDE",direction=Direction.IN,type=Date.class),
                    			@StoredProcedureParameter(queryParameter="P_FECHA_HASTA",name="P_FECHA_HASTA",direction=Direction.IN,type=Date.class),
                    			@StoredProcedureParameter(queryParameter="P_PLAN_COBERTURA",name="P_PLAN_COBERTURA",direction=Direction.IN,type=String.class),
                    			@StoredProcedureParameter(queryParameter="P_PLAN_PAGO",name="P_PLAN_PAGO",direction=Direction.IN,type=Integer.class), 
                    			@StoredProcedureParameter(queryParameter="P_MONEDA",name="P_MONEDA",direction=Direction.IN,type=String.class),
                    			@StoredProcedureParameter(queryParameter="P_TIPO_CALCULO",name="P_TIPO_CALCULO",direction=Direction.IN,type=String.class),
                    			@StoredProcedureParameter(queryParameter="P_FORMA_PAGO",name="P_FORMA_PAGO",direction=Direction.IN,type=String.class),
                    			@StoredProcedureParameter(queryParameter="P_VIG_TECNICA",name="P_VIG_TECNICA",direction=Direction.IN,type=String.class),
                    			@StoredProcedureParameter(queryParameter="P_MEDIO_PAGO",name="P_MEDIO_PAGO",direction=Direction.IN,type=Integer.class),
                    			@StoredProcedureParameter(queryParameter="P_ORIGEN_PAGO",name="P_ORIGEN_PAGO",direction=Direction.IN,type=String.class),
                    			@StoredProcedureParameter(queryParameter="P_TIPO_FACT",name="P_TIPO_FACT",direction=Direction.IN,type=String.class),
                    			@StoredProcedureParameter(queryParameter="P_PROMOCION",name="P_PROMOCION",direction=Direction.IN,type=String.class),
                    			@StoredProcedureParameter(queryParameter="P_RENOVACION",name="P_RENOVACION",direction=Direction.IN,type=String.class),
                    			@StoredProcedureParameter(queryParameter="P_USUARIO_WEB",name="P_USUARIO_WEB",direction=Direction.IN,type=String.class),
                    			@StoredProcedureParameter(queryParameter="P_CONSUMIDOR_FINAL",name="P_CONSUMIDOR_FINAL",direction=Direction.IN,type=String.class),
                    			@StoredProcedureParameter(queryParameter="P_FACT_MAIL",name="P_FACT_MAIL",direction=Direction.IN,type=String.class),
                    			@StoredProcedureParameter(queryParameter="P_COASEGURO",name="P_COASEGURO",direction=Direction.IN,type=String.class),
                    			@StoredProcedureParameter(queryParameter="P_EMITIR",name="P_EMITIR",direction=Direction.IN,type=String.class),
                    			@StoredProcedureParameter(queryParameter="P_TIPO_EMBARCACION",name="P_TIPO_EMBARCACION",direction=Direction.IN,type=String.class),
                    			@StoredProcedureParameter(queryParameter="P_CAPITAL_RC", name="P_CAPITAL_RC",direction=Direction.IN,type=String.class),
                    			@StoredProcedureParameter(queryParameter="P_COTIZACION_EMITIDA",name="P_COTIZACION_EMITIDA",direction=Direction.OUT,type=Long.class),
                    			@StoredProcedureParameter(queryParameter="P_PREMIO_COTIZACION",name="P_PREMIO_COTIZACION",direction=Direction.OUT,type=Float.class),
                    			@StoredProcedureParameter(queryParameter="P_PREMIO_COTIZACION_FACTURAR",name="P_PREMIO_COTIZACION_FACTURAR",direction=Direction.OUT,type=Float.class),
                    			@StoredProcedureParameter(queryParameter="P_CUOTAS",name="P_CUOTAS",direction=Direction.OUT,type=String.class),
                    			@StoredProcedureParameter(queryParameter="P_RESULTADO",name="P_RESULTADO",direction=Direction.OUT,type=String.class),
                    			@StoredProcedureParameter(queryParameter="P_MENSAJE_EMISION",name="P_MENSAJE_EMISION",direction=Direction.OUT,type=String.class)
                        	}
                        ),
                                
                        @NamedStoredProcedureQuery(  
                        name="PRO_EMITIR_EDEPOR",  
                        procedureName="RECTOR.PACK_EMI_MIBSE.PRO_EMITIR_EDEPOR",
                        parameters={
                			@StoredProcedureParameter(queryParameter="P_TP_DOCUMENTO_CONTRATANTE",name="P_TP_DOCUMENTO_CONTRATANTE",direction=Direction.IN,type=String.class),
                			@StoredProcedureParameter(queryParameter="P_NRO_DOCUMENTO_CONTRATANTE",name="P_NRO_DOCUMENTO_CONTRATANTE",direction=Direction.IN,type=String.class), 
                			@StoredProcedureParameter(queryParameter="P_SUCURSAL",name="P_SUCURSAL",direction=Direction.IN,type=String.class),
                			@StoredProcedureParameter(queryParameter="P_RAMO",name="P_RAMO",direction=Direction.IN,type=String.class),
                			@StoredProcedureParameter(queryParameter="P_PRODUCTO",name="P_PRODUCTO",direction=Direction.IN,type=String.class),
                			@StoredProcedureParameter(queryParameter="P_COTIZACION_EMITIDA",name="P_COTIZACION_EMITIDA",direction=Direction.IN,type=Long.class),
                			@StoredProcedureParameter(queryParameter="P_CONSUMIDOR_FINAL",name="P_CONSUMIDOR_FINAL",direction=Direction.IN,type=String.class),
                			@StoredProcedureParameter(queryParameter="P_MATRICULA_BUQUE",name="P_MATRICULA_BUQUE",direction=Direction.IN,type=String.class),
                			@StoredProcedureParameter(queryParameter="P_NOMBRE_BUQUE",name="P_NOMBRE_BUQUE",direction=Direction.IN,type=String.class),
                			@StoredProcedureParameter(queryParameter="P_USUARIO_WEB",name="P_USUARIO_WEB",direction=Direction.IN,type=String.class),
    	        			@StoredProcedureParameter(queryParameter="P_POLIZA_EMITIDA",name="P_POLIZA_EMITIDA",direction=Direction.OUT,type=Long.class),
    	        			@StoredProcedureParameter(queryParameter="P_PREMIO_COTIZACION",name="P_PREMIO_COTIZACION",direction=Direction.OUT,type=Float.class),
    	        			@StoredProcedureParameter(queryParameter="P_PREMIO_COTIZACION_FACTURAR",name="P_PREMIO_COTIZACION_FACTURAR",direction=Direction.OUT,type=Float.class),
    	        			@StoredProcedureParameter(queryParameter="P_FECHA_DESDE",name="P_FECHA_DESDE",direction=Direction.OUT,type=Date.class),
    	        			@StoredProcedureParameter(queryParameter="P_FECHA_HASTA",name="P_FECHA_HASTA",direction=Direction.OUT,type=Date.class),
    	        			@StoredProcedureParameter(queryParameter="P_PLAN_COBERTURA",name="P_PLAN_COBERTURA",direction=Direction.OUT,type=String.class),
    	        			@StoredProcedureParameter(queryParameter="P_PLAN_PAGO",name="P_PLAN_PAGO",direction=Direction.OUT,type=Integer.class), 
    	        			@StoredProcedureParameter(queryParameter="P_TIPO_EMBARCACION",name="P_TIPO_EMBARCACION",direction=Direction.OUT,type=String.class),
                			@StoredProcedureParameter(queryParameter="P_CAPITAL_RC", name="P_CAPITAL_RC",direction=Direction.OUT,type=Float.class),
    	        			@StoredProcedureParameter(queryParameter="P_CUOTAS",name="P_CUOTAS",direction=Direction.OUT,type=String.class),
    	        			@StoredProcedureParameter(queryParameter="P_RESULTADO",name="P_RESULTADO",direction=Direction.OUT,type=String.class),
    	        			@StoredProcedureParameter(queryParameter="P_MENSAJE_EMISION",name="P_MENSAJE_EMISION",direction=Direction.OUT,type=String.class)
 	        			
    	        			
                        	}
                        ),
                                
                        @NamedStoredProcedureQuery(  // calcula el cod de barras para los pagos parciales, covid19 abril 2020
                        name="PRO_OBT_CBS",  
                        procedureName="RECTOR.ZPU_COB_CODBARRAS.PRO_OBT_CBS",
                        parameters={
                			@StoredProcedureParameter(queryParameter="P_CB1",name="P_CB1",direction=Direction.IN,type=String.class),
                			@StoredProcedureParameter(queryParameter="P_CB2",name="P_CB2",direction=Direction.IN_OUT,type=String.class), 
    	        			@StoredProcedureParameter(queryParameter="P_IMP_FACTURA_NEW",name="P_IMP_FACTURA_NEW",direction=Direction.IN,type=Float.class),
    	        			@StoredProcedureParameter(queryParameter="P_IMP_GRABADO_NEW",name="P_IMP_GRABADO_NEW",direction=Direction.IN,type=Float.class)
                        	}
                        ),

                        @NamedStoredProcedureQuery(  
                                name="PRO_COTIZAR_BICI",  
                                //procedureName="RECTOR.PACK_SOA_PORTAL.PRO_COTIZAR_SOA",
                                procedureName="RECTOR.PACK_EMI_MIBSE.PRO_COTIZAR_BICI",
                                parameters={
                        			@StoredProcedureParameter(queryParameter="P_TP_DOCUMENTO",name="P_TP_DOCUMENTO",direction=Direction.IN,type=String.class),
                        			@StoredProcedureParameter(queryParameter="P_NRO_DOCUMENTO",name="P_NRO_DOCUMENTO",direction=Direction.IN,type=String.class), 
                        			@StoredProcedureParameter(queryParameter="P_SUCURSAL",name="P_SUCURSAL",direction=Direction.IN,type=String.class),

                        			@StoredProcedureParameter(queryParameter="P_RAMO",name="P_RAMO",direction=Direction.IN,type=String.class),
                        			@StoredProcedureParameter(queryParameter="P_PRODUCTO",name="P_PRODUCTO",direction=Direction.IN,type=String.class),
                        			@StoredProcedureParameter(queryParameter="P_PRODUCTOR",name="P_PRODUCTOR",direction=Direction.IN,type=String.class),

                        			@StoredProcedureParameter(queryParameter="P_FECHA_DESDE",name="P_FECHA_DESDE",direction=Direction.IN,type=Date.class),
                        			@StoredProcedureParameter(queryParameter="P_VALOR_BICI",name="P_VALOR_BICI",direction=Direction.IN,type=Float.class),
                        			@StoredProcedureParameter(queryParameter="P_VIGENCIA_SEGURO",name="P_VIGENCIA_SEGURO",direction=Direction.IN,type=Integer.class),
                        			@StoredProcedureParameter(queryParameter="P_PLAN_PAGO",name="P_PLAN_PAGO",direction=Direction.IN,type=Integer.class),
                        			@StoredProcedureParameter(queryParameter="P_PLAN_COBERTURA",name="P_PLAN_COBERTURA",direction=Direction.IN,type=String.class),
                        			@StoredProcedureParameter(queryParameter="P_MONEDA",name="P_MONEDA",direction=Direction.IN,type=String.class),
                        			
                        			@StoredProcedureParameter(queryParameter="P_TIPO_CALCULO",name="P_TIPO_CALCULO",direction=Direction.IN,type=String.class),
                        			@StoredProcedureParameter(queryParameter="P_FORMA_PAGO",name="P_FORMA_PAGO",direction=Direction.IN,type=String.class),
                        			@StoredProcedureParameter(queryParameter="P_VIG_TECNICA",name="P_VIG_TECNICA",direction=Direction.IN,type=String.class),
                        			@StoredProcedureParameter(queryParameter="P_MEDIO_PAGO",name="P_MEDIO_PAGO",direction=Direction.IN,type=Integer.class),
                        			@StoredProcedureParameter(queryParameter="P_ORIGEN_PAGO",name="P_ORIGEN_PAGO",direction=Direction.IN,type=String.class),
                        			@StoredProcedureParameter(queryParameter="P_TIPO_FACT",name="P_TIPO_FACT",direction=Direction.IN,type=String.class),
                        			@StoredProcedureParameter(queryParameter="P_PROMOCION",name="P_PROMOCION",direction=Direction.IN,type=String.class),
                        			@StoredProcedureParameter(queryParameter="P_RENOVACION",name="P_RENOVACION",direction=Direction.IN,type=String.class),
                        			@StoredProcedureParameter(queryParameter="P_USUARIO_WEB",name="P_USUARIO_WEB",direction=Direction.IN,type=String.class),

                        			@StoredProcedureParameter(queryParameter="P_CONSUMIDOR_FINAL",name="P_CONSUMIDOR_FINAL",direction=Direction.IN,type=String.class),
                        			@StoredProcedureParameter(queryParameter="P_FACT_MAIL",name="P_FACT_MAIL",direction=Direction.IN,type=String.class),
                        			@StoredProcedureParameter(queryParameter="P_COASEGURO",name="P_COASEGURO",direction=Direction.IN,type=String.class),
                        			@StoredProcedureParameter(queryParameter="P_EMITIR",name="P_EMITIR",direction=Direction.IN,type=String.class),
                        			
                        			@StoredProcedureParameter(queryParameter="P_COTIZACION_EMITIDA",name="P_COTIZACION_EMITIDA",direction=Direction.OUT,type=Long.class),
                        			@StoredProcedureParameter(queryParameter="P_PREMIO_COTIZACION",name="P_PREMIO_COTIZACION",direction=Direction.OUT,type=Float.class),
                        			@StoredProcedureParameter(queryParameter="P_PREMIO_COTIZACION_FACTURAR",name="P_PREMIO_COTIZACION_FACTURAR",direction=Direction.OUT,type=Float.class),
                        			@StoredProcedureParameter(queryParameter="P_FECHA_HASTA",name="P_FECHA_HASTA",direction=Direction.OUT,type=Date.class),
                        			@StoredProcedureParameter(queryParameter="P_CUOTAS",name="P_CUOTAS",direction=Direction.OUT,type=String.class),
                        			@StoredProcedureParameter(queryParameter="P_PLANES",name="P_PLANES",direction=Direction.OUT,type=String.class),
                        			
                        			@StoredProcedureParameter(queryParameter="P_RESULTADO",name="P_RESULTADO",direction=Direction.OUT,type=String.class),
                        			@StoredProcedureParameter(queryParameter="P_MENSAJE_EMISION",name="P_MENSAJE_EMISION",direction=Direction.OUT,type=String.class)
                        		    
                                	}
                                ),

                                @NamedStoredProcedureQuery(  
                                name="PRO_EMITIR_BICI",  
                                procedureName="RECTOR.PACK_EMI_MIBSE.PRO_EMITIR_BICI",
                                parameters={
                        			@StoredProcedureParameter(queryParameter="P_TP_DOCUMENTO",name="P_TP_DOCUMENTO",direction=Direction.IN,type=String.class),
                        			@StoredProcedureParameter(queryParameter="P_NRO_DOCUMENTO",name="P_NRO_DOCUMENTO",direction=Direction.IN,type=String.class), 

                        			@StoredProcedureParameter(queryParameter="P_SUCURSAL",name="P_SUCURSAL",direction=Direction.IN,type=String.class),
                        			@StoredProcedureParameter(queryParameter="P_RAMO",name="P_RAMO",direction=Direction.IN,type=String.class),
                        			@StoredProcedureParameter(queryParameter="P_PRODUCTO",name="P_PRODUCTO",direction=Direction.IN,type=String.class),

                        			@StoredProcedureParameter(queryParameter="P_MARCA",name="P_MARCA",direction=Direction.IN,type=String.class),
                        			@StoredProcedureParameter(queryParameter="P_MODELO",name="P_MODELO",direction=Direction.IN,type=String.class),
                        			@StoredProcedureParameter(queryParameter="P_SERIE",name="P_SERIE",direction=Direction.IN,type=String.class),
                        			@StoredProcedureParameter(queryParameter="P_TIPO_VEHICULO",name="P_TIPO_VEHICULO",direction=Direction.IN,type=String.class),
                        			@StoredProcedureParameter(queryParameter="P_COTIZACION_EMITIDA",name="P_COTIZACION_EMITIDA",direction=Direction.IN,type=Long.class),
                        			
                        			@StoredProcedureParameter(queryParameter="P_CONSUMO_FINAL",name="P_CONSUMO_FINAL",direction=Direction.IN,type=String.class),
                        			
                        			@StoredProcedureParameter(queryParameter="P_POLIZA_EMITIDA",name="P_POLIZA_EMITIDA",direction=Direction.OUT,type=Long.class),
                        			@StoredProcedureParameter(queryParameter="P_PREMIO_COTIZACION",name="P_PREMIO_COTIZACION",direction=Direction.OUT,type=Float.class),
                        			@StoredProcedureParameter(queryParameter="P_PREMIO_COTIZACION_FACTURAR",name="P_PREMIO_COTIZACION_FACTURAR",direction=Direction.OUT,type=Float.class),
                        			@StoredProcedureParameter(queryParameter="P_FECHA_DESDE",name="P_FECHA_DESDE",direction=Direction.OUT,type=Date.class),
                        			@StoredProcedureParameter(queryParameter="P_FECHA_HASTA",name="P_FECHA_HASTA",direction=Direction.OUT,type=Date.class),
                        			@StoredProcedureParameter(queryParameter="P_PLAN_COBERTURA",name="P_PLAN_COBERTURA",direction=Direction.OUT,type=String.class),
                        			@StoredProcedureParameter(queryParameter="P_PLAN_PAGO",name="P_PLAN_PAGO",direction=Direction.IN_OUT,type=Integer.class), 
                        			@StoredProcedureParameter(queryParameter="P_CUOTAS",name="P_CUOTAS",direction=Direction.OUT,type=String.class),

                        			@StoredProcedureParameter(queryParameter="P_FECHA_FACTURA",name="P_FECHA_FACTURA",direction=Direction.IN,type=Date.class),
                        			@StoredProcedureParameter(queryParameter="P_FECHA_NAC_CLI",name="P_FECHA_NAC_CLI",direction=Direction.IN,type=Date.class),

                        			@StoredProcedureParameter(queryParameter="P_RESULTADO",name="P_RESULTADO",direction=Direction.OUT,type=String.class),
                        			@StoredProcedureParameter(queryParameter="P_MENSAJE_EMISION",name="P_MENSAJE_EMISION",direction=Direction.OUT,type=String.class)
                        		    
                                	}
                                ),

                        
                                @NamedStoredProcedureQuery(  
                                        name="PRO_CONTROLAR_CLIENTE_DEUDA",  
                                        //procedureName="RECTOR.PACK_SOA_PORTAL.PRO_EMITIR_SOA",
                                        procedureName="RECTOR.PACK_EMI_MIBSE.PRO_CONTROLAR_CLIENTE_DEUDA",
                                        parameters={
                                			@StoredProcedureParameter(queryParameter="P_TP_DOCUMENTO",name="P_TP_DOCUMENTO",direction=Direction.IN_OUT,type=String.class),
                                			@StoredProcedureParameter(queryParameter="P_NRO_DOCUMENTO",name="P_NRO_DOCUMENTO",direction=Direction.IN_OUT,type=String.class), 
                                			@StoredProcedureParameter(queryParameter="P_NU_COTIZACION",name="P_NU_COTIZACION",direction=Direction.IN_OUT,type=Integer.class),
                                			@StoredProcedureParameter(queryParameter="P_NU_CERTIFICADO",name="P_NU_CERTIFICADO",direction=Direction.IN_OUT,type=Integer.class),
                                			@StoredProcedureParameter(queryParameter="P_RESULTADO",name="P_RESULTADO",direction=Direction.OUT,type=String.class),
                                		    
                                        	}
                                        ),
                                
        
})

public class Ramo{

@Id
@Column(name = "CARP_CD_RAMO")
private int ramo;
	
@Column(name = "CARP_DE_RAMO")
private String descripcion;

public Ramo() {
}

public Ramo(int ramo, String descripcion) {
	this.ramo = ramo;
	this.descripcion = descripcion;
}

public int getRamo() {
	return ramo;
}

public void setRamo(int ramo) {
	this.ramo = ramo;
}

public String getDescripcion() {
	return descripcion;
}

public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}

}
