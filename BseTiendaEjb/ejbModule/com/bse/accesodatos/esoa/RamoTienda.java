package com.bse.accesodatos.esoa;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Direction;
import org.eclipse.persistence.annotations.NamedStoredProcedureQueries;
import org.eclipse.persistence.annotations.NamedStoredProcedureQuery;
import org.eclipse.persistence.annotations.StoredProcedureParameter;

@Entity
@Table(name = "CART_RAMOS_POLIZAS")

@NamedStoredProcedureQueries({

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA 
    // - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA 
    // - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA 
    // - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA 
    // - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA - SOA 
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - 
    // - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - 
    // - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - 
    // - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - 
    // - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - 
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @NamedStoredProcedureQuery(
    name="PRO_COTIZAR_INDIVI",
    procedureName="RECTOR.PACK_EMI_MIBSE.PRO_COTIZAR_INDIVI",
    parameters={
        @StoredProcedureParameter(queryParameter="P_TP_DOCUMENTO",name="P_TP_DOCUMENTO",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_NRO_DOCUMENTO",name="P_NRO_DOCUMENTO",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_TIPO_FACT",name="P_TIPO_FACT",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_SUCURSAL",name="P_SUCURSAL",direction=Direction.IN,type=Integer.class),
        @StoredProcedureParameter(queryParameter="P_RAMO",name="P_RAMO",direction=Direction.IN,type=Integer.class),
        @StoredProcedureParameter(queryParameter="P_PRODUCTO",name="P_PRODUCTO",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_MONEDA",name="P_MONEDA",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_MEDIO_PAGO",name="P_MEDIO_PAGO",direction=Direction.IN,type=Integer.class),
        @StoredProcedureParameter(queryParameter="P_ORIGEN_PAGO",name="P_ORIGEN_PAGO",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_PROMOCION",name="P_PROMOCION",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_TIPO_CALCULO",name="P_TIPO_CALCULO",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_FORMA_PAGO",name="P_FORMA_PAGO",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_RENOVACION",name="P_RENOVACION",direction=Direction.IN,type=String.class),

        @StoredProcedureParameter(queryParameter="P_MARCA_VEHICULO",name="P_MARCA_VEHICULO",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_TIPO_VEHICULO",name="P_TIPO_VEHICULO",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_ANIO_VEHICULO",name="P_ANIO_VEHICULO",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_COMBUSTIBLE",name="P_COMBUSTIBLE",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_MODELO_VEHICULO",name="P_MODELO_VEHICULO",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_AREA_CIRCULACION",name="P_AREA_CIRCULACION",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_USUARIO",name="P_USUARIO",direction=Direction.IN,type=String.class),

        @StoredProcedureParameter(queryParameter="P_COD_ERROR",name="P_COD_ERROR",direction=Direction.OUT,type=Integer.class),
        @StoredProcedureParameter(queryParameter="P_DESC_ERROR",name="P_DESC_ERROR",direction=Direction.OUT,type=String.class),
        @StoredProcedureParameter(queryParameter="P_SQL_ERROR",name="P_SQL_ERROR",direction=Direction.OUT,type=String.class),
        @StoredProcedureParameter(queryParameter="P_RESULTADO",name="P_RESULTADO",direction=Direction.OUT,type=java.sql.Clob.class)  
      }
    ),


    @NamedStoredProcedureQuery(
    name="PRO_EMITIR_INDIVI",
    procedureName="RECTOR.PACK_EMI_MIBSE.PRO_EMITIR_INDIVI",
    parameters={
        @StoredProcedureParameter(queryParameter="P_TP_DOCUMENTO",name="P_TP_DOCUMENTO",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_NRO_DOCUMENTO",name="P_NRO_DOCUMENTO",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_SUCURSAL",name="P_SUCURSAL",direction=Direction.IN,type=Integer.class),
        @StoredProcedureParameter(queryParameter="P_RAMO",name="P_RAMO",direction=Direction.IN,type=Integer.class),
        @StoredProcedureParameter(queryParameter="P_PRODUCTO",name="P_PRODUCTO",direction=Direction.IN,type=String.class),

        @StoredProcedureParameter(queryParameter="P_COTIZACION_EMITIDA",name="P_COTIZACION_EMITIDA",direction=Direction.IN,type=Long.class),

        @StoredProcedureParameter(queryParameter="P_MATRICULA_VEHICULO",name="P_MATRICULA_VEHICULO",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_PADRON_VEHICULO",name="P_PADRON_VEHICULO",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_MOTOR_VEHICULO",name="P_MOTOR_VEHICULO",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_CHASIS_VEHICULO",name="P_CHASIS_VEHICULO",direction=Direction.IN,type=String.class),

        @StoredProcedureParameter(queryParameter="P_PLAN_COBERTURA",name="P_PLAN_COBERTURA",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_PLAN_PAGO",name="P_PLAN_PAGO",direction=Direction.IN,type=Integer.class),
        @StoredProcedureParameter(queryParameter="P_CONSUMIDOR_FINAL",name="P_CONSUMIDOR_FINAL",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_MODALIDAD",name="P_MODALIDAD",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_USUARIO",name="P_USUARIO",direction=Direction.IN,type=String.class),

        @StoredProcedureParameter(queryParameter="P_POLIZA_EMITIDA",name="P_POLIZA_EMITIDA",direction=Direction.OUT,type=Long.class),
        @StoredProcedureParameter(queryParameter="P_PREMIO_COTIZACION",name="P_PREMIO_COTIZACION",direction=Direction.OUT,type=Float.class),
        @StoredProcedureParameter(queryParameter="P_PREMIO_COTIZACION_FACTURAR",name="P_PREMIO_COTIZACION_FACTURAR",direction=Direction.OUT,type=Float.class),
        @StoredProcedureParameter(queryParameter="P_MARCA_VEHICULO",name="P_MARCA_VEHICULO",direction=Direction.OUT,type=String.class),
        @StoredProcedureParameter(queryParameter="P_ANIO_VEHICULO",name="P_ANIO_VEHICULO",direction=Direction.OUT,type=String.class),
        @StoredProcedureParameter(queryParameter="P_FECHA_DESDE",name="P_FECHA_DESDE",direction=Direction.OUT,type=Date.class),
        @StoredProcedureParameter(queryParameter="P_FECHA_HASTA",name="P_FECHA_HASTA",direction=Direction.OUT,type=Date.class),
        @StoredProcedureParameter(queryParameter="P_CUOTAS",name="P_CUOTAS",direction=Direction.OUT,type=String.class),

        @StoredProcedureParameter(queryParameter="P_COD_ERROR",name="P_COD_ERROR",direction=Direction.OUT,type=Integer.class),
        @StoredProcedureParameter(queryParameter="P_DESC_ERROR",name="P_DESC_ERROR",direction=Direction.OUT,type=String.class),
        @StoredProcedureParameter(queryParameter="P_SQL_ERROR",name="P_SQL_ERROR",direction=Direction.OUT,type=String.class), 
      }
    ),


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS -
    // - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - 
    // - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS -
    // - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS -
    // - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS - VIAJEROS -
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - 
    // - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - 
    // - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - 
    // - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - 
    // - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - EDEPOR - 
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
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

    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI -
    // - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI -
    // - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI -
    // - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI -
    // - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI - BICI -
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
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

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - 
    // - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - 
    // - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - 
    // - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - 
    // - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - 
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @NamedStoredProcedureQuery(
    name="PRO_COTIZAR_VARIOS",
    procedureName="RECTOR.PACK_EMI_MIBSE.PRO_COTIZAR_VARIOS",
    parameters={
        @StoredProcedureParameter(queryParameter="P_TP_DOCUMENTO",name="P_TP_DOCUMENTO",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_NRO_DOCUMENTO",name="P_NRO_DOCUMENTO",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_SUCURSAL",name="P_SUCURSAL",direction=Direction.IN,type=String.class),

        @StoredProcedureParameter(queryParameter="P_RAMO",name="P_RAMO",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_PRODUCTO",name="P_PRODUCTO",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_PLAN_COBERTURA",name="P_PLAN_COBERTURA",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_PRODUCTOR",name="P_PRODUCTOR",direction=Direction.IN,type=String.class),

        @StoredProcedureParameter(queryParameter="P_FECHA_DESDE",name="P_FECHA_DESDE",direction=Direction.IN,type=Date.class),
        @StoredProcedureParameter(queryParameter="P_VALOR_OBJETO",name="P_VALOR_OBJETO",direction=Direction.IN,type=Float.class),
        @StoredProcedureParameter(queryParameter="P_MOVILIDAD",name="P_MOVILIDAD",direction=Direction.IN,type=Integer.class),
        @StoredProcedureParameter(queryParameter="P_TIPO_OBJETO",name="P_TIPO_OBJETO",direction=Direction.IN,type=Integer.class),
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
    name="PRO_EMITIR_VARIOS",
    procedureName="RECTOR.PACK_EMI_MIBSE.PRO_EMITIR_VARIOS",
    parameters={
        @StoredProcedureParameter(queryParameter="P_TP_DOCUMENTO",name="P_TP_DOCUMENTO",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_NRO_DOCUMENTO",name="P_NRO_DOCUMENTO",direction=Direction.IN,type=String.class),

        @StoredProcedureParameter(queryParameter="P_SUCURSAL",name="P_SUCURSAL",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_RAMO",name="P_RAMO",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_PRODUCTO",name="P_PRODUCTO",direction=Direction.IN,type=String.class),

        @StoredProcedureParameter(queryParameter="P_MARCA",name="P_MARCA",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_MODELO",name="P_MODELO",direction=Direction.IN,type=String.class),
        @StoredProcedureParameter(queryParameter="P_SERIE",name="P_SERIE",direction=Direction.IN,type=String.class),

        @StoredProcedureParameter(queryParameter="P_COTIZACION_EMITIDA",name="P_COTIZACION_EMITIDA",direction=Direction.IN,type=Long.class),
        @StoredProcedureParameter(queryParameter="P_CONSUMO_FINAL",name="P_CONSUMO_FINAL",direction=Direction.IN,type=String.class),

        @StoredProcedureParameter(queryParameter="P_POLIZA_EMITIDA",name="P_POLIZA_EMITIDA",direction=Direction.OUT,type=Long.class),
        @StoredProcedureParameter(queryParameter="P_PREMIO_COTIZACION",name="P_PREMIO_COTIZACION",direction=Direction.OUT,type=Float.class),
        @StoredProcedureParameter(queryParameter="P_PREMIO_COTIZACION_FACTURAR",name="P_PREMIO_COTIZACION_FACTURAR",direction=Direction.OUT,type=Float.class),
        @StoredProcedureParameter(queryParameter="P_FECHA_DESDE",name="P_FECHA_DESDE",direction=Direction.OUT,type=Date.class),
        @StoredProcedureParameter(queryParameter="P_FECHA_HASTA",name="P_FECHA_HASTA",direction=Direction.OUT,type=Date.class),
        @StoredProcedureParameter(queryParameter="P_PLAN_COBERTURA",name="P_PLAN_COBERTURA",direction=Direction.OUT,type=String.class),

        @StoredProcedureParameter(queryParameter="P_PLAN_PAGO",name="P_PLAN_PAGO",direction=Direction.IN,type=Integer.class),
        @StoredProcedureParameter(queryParameter="P_FECHA_FACTURA",name="P_FECHA_FACTURA",direction=Direction.IN,type=Date.class),

        @StoredProcedureParameter(queryParameter="P_CUOTAS",name="P_CUOTAS",direction=Direction.OUT,type=String.class),

        @StoredProcedureParameter(queryParameter="P_RESULTADO",name="P_RESULTADO",direction=Direction.OUT,type=String.class),
        @StoredProcedureParameter(queryParameter="P_MENSAJE_EMISION",name="P_MENSAJE_EMISION",direction=Direction.OUT,type=String.class)
      }
    ),

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN -
    // - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN -
    // - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN -
    // - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN -
    // - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN - COMUN -
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
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

@NamedNativeQueries({

    @NamedNativeQuery(name = "tiposCombustiblesIndivi", query =
            "SELECT E.CODIGO COD, D.CRTB_DE_DATO DES " +
            "FROM " +
               "( SELECT DISTINCT SUBSTR(B.CRTB_DATO2,3,1) CODIGO " +
                 "FROM ( SELECT DISTINCT A.CRTB_INDEX " +
                        "FROM CRET_TABLAS A " +
                        "WHERE A.CRTB_CD_TABLA = 140002 " +
                         "AND (A.CRTB_DATO2 LIKE 'A1%' " +
                           "OR A.CRTB_DATO2 LIKE 'A2%' " +
                           "OR A.CRTB_DATO2 LIKE 'D1%' " +
                           "OR A.CRTB_DATO2 LIKE 'D2%' " +
                           "OR A.CRTB_DATO2 LIKE 'D3%' " +
                           "OR A.CRTB_DATO2 LIKE 'D4%') " +
                      ") C, " +
                      "CRET_TABLAS B " +
                 "WHERE B.CRTB_CD_TABLA = 140002 " +
                   "AND B.CRTB_INDEX = C.CRTB_INDEX " +
                   "AND (SUBSTR(B.CRTB_DATO2,4,4) >= EXTRACT(YEAR FROM SYSDATE)-10 AND SUBSTR(B.CRTB_DATO2,4,4) <= EXTRACT(YEAR FROM SYSDATE)) " +
               ") E, " +
               "CRET_TABLAS D " +
            "WHERE D.CRTB_CD_TABLA = 142001 " +
              "AND D.CRTB_INDEX = E.CODIGO " +
            "ORDER BY 2"
    ),

    @NamedNativeQuery(name = "marcasVehiculosIndivi", query =
            "SELECT DISTINCT B.CRTB_DATO1 COD, D.CRTB_DE_DATO DES " +
                    "FROM ( SELECT DISTINCT A.CRTB_INDEX " +
                           "FROM CRET_TABLAS A " +
                           "WHERE A.CRTB_CD_TABLA = 140002 " +
                             "AND (A.CRTB_DATO2 LIKE 'A1%' " +
                               "OR A.CRTB_DATO2 LIKE 'A2%' " +
                               "OR A.CRTB_DATO2 LIKE 'D1%' " +
                               "OR A.CRTB_DATO2 LIKE 'D2%' " +
                               "OR A.CRTB_DATO2 LIKE 'D3%' " +
                               "OR A.CRTB_DATO2 LIKE 'D4%') " +
                         ") C, " +
                         "CRET_TABLAS B, " +
                         "CRET_TABLAS D " +
                    "WHERE B.CRTB_CD_TABLA = 140002 " +
                      "AND B.CRTB_INDEX = C.CRTB_INDEX " +
                      "AND D.CRTB_DATO1 = B.CRTB_DATO1 " +
                      "AND D.CRTB_CD_TABLA = 140001 " +
                      "AND (SUBSTR(B.CRTB_DATO2,4,4) >= EXTRACT(YEAR FROM SYSDATE)-10 AND SUBSTR(B.CRTB_DATO2,4,4) <= EXTRACT(YEAR FROM SYSDATE)) " +
                    "ORDER BY 2"
    ),

    @NamedNativeQuery(name = "familiasVehiculosIndivi", query =
            "SELECT * " +
            "FROM ( " +
                      "SELECT * " +
                      "FROM ( " +
                              "SELECT DISTINCT CRTD_DERIVADO, CRTB_DE_DATO " +
                              "FROM CRET_TABLAS_DERIVADOS, " +
                                   "CRET_TABLAS " +
                              "WHERE CRTD_CRTB_CD_TABLA= 140002 " +
                                "AND CRTD_CRDD_CONSECUTIVO = 145 " +
                                "AND CRTD_INDEX LIKE ? || '|%' " +
                                "AND CRTB_CD_TABLA = 143035 " +
                                "AND CRTB_INDEX = CRTD_DERIVADO " +
                              "ORDER BY 2 " +
                           ") " +
                    "UNION " +
                      "SELECT DISTINCT 'OTROS', 'OTROS' " +
                      "FROM CRET_TABLAS " +
                      "WHERE CRTB_CD_TABLA = 140002 " +
                        "AND CRTB_INDEX LIKE ? || '|%' " +
                        "AND (     NOT EXISTS (SELECT 1 " +
                                              "FROM CRET_TABLAS_DERIVADOS " +
                                              "WHERE CRTD_INDEX = CRTB_INDEX " +
                                                "AND CRTD_CRDD_CONSECUTIVO = 145) " +
                              "OR " +
                                   "'0' = (SELECT NVL(CRTD_DERIVADO, 0) " +
                                          "FROM CRET_TABLAS_DERIVADOS " +
                                          "WHERE CRTD_INDEX = CRTB_INDEX " +
                                            "AND CRTD_CRDD_CONSECUTIVO = 145)) " +
                 ") "
    ),

    @NamedNativeQuery(name = "versionesVehiculosIndivi", query =
            "SELECT * " +
            "FROM ( " +
                       "SELECT CRTB_DATO3, CRTB_DE_DATO " +
                       "FROM CRET_TABLAS " +
                       "WHERE CRTB_CD_TABLA= 140002 " +
                         "AND CRTB_INDEX LIKE ? || '|%' " +
                         "AND EXISTS (SELECT * " +
                                     "FROM CRET_TABLAS_DERIVADOS " +
                                     "WHERE CRTD_CRTB_CD_TABLA = 140002 " +
                                       "AND CRTD_CRDD_CONSECUTIVO = 145 " +
                                       "AND CRTD_INDEX LIKE ? || '|%' " +
                                       "AND CRTD_DERIVADO = ? " +
                                       "AND CRTD_CRTB_DATO3 = CRTB_DATO3) " +
                   "UNION " +
                     "SELECT CRTB_DATO3, CRTB_DE_DATO " +
                     "FROM CRET_TABLAS " +
                     "WHERE CRTB_CD_TABLA = 140002 " +
                       "AND CRTB_INDEX LIKE ? || '|%' " +
                       "AND (     NOT EXISTS (SELECT 1 " +
                                             "FROM CRET_TABLAS_DERIVADOS " +
                                             "WHERE CRTD_INDEX = CRTB_INDEX " +
                                               "AND CRTD_CRDD_CONSECUTIVO = 145) " +
                             "OR '0' = (SELECT NVL(CRTD_DERIVADO, 0) " +
                                       "FROM CRET_TABLAS_DERIVADOS " +
                                       "WHERE CRTD_INDEX = CRTB_INDEX " +
                                         "AND CRTD_CRDD_CONSECUTIVO = 145)) " +
                       "AND NOT EXISTS (SELECT 1 " +
                                       "FROM CRET_TABLAS J " +
                                       "WHERE J.CRTB_CD_TABLA= 140002 " +
                                         "AND J.CRTB_INDEX = CRTB_INDEX " +
                                         "AND EXISTS (SELECT * " +
                                                     "FROM CRET_TABLAS_DERIVADOS " +
                                                     "WHERE CRTD_CRTB_CD_TABLA = 140002 " +
                                                       "AND CRTD_CRDD_CONSECUTIVO = 145 " +
                                                       "AND CRTD_INDEX = J.CRTB_INDEX " +
                                                       "AND CRTD_DERIVADO = ? " +
                                                       "AND CRTD_CRTB_DATO3 = J.CRTB_DATO3)) " +
                  ") " +
             "ORDER BY 2 "
    )

})



public class RamoTienda{

    @Id
    @Column(name = "CARP_CD_RAMO")
    private int ramo;

    @Column(name = "CARP_DE_RAMO")
    private String descripcion;

    public RamoTienda() {
    }

    public RamoTienda(int ramo, String descripcion) {
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


    @Override
    public String toString() {
        String auxDescripcion;
        if (this.getDescripcion() == null) { auxDescripcion = "null";
        } else                             { auxDescripcion = this.getDescripcion(); }

        //return ("Ramo=[" + Integer.toString(this.getRamo()) + "] Descripcion=[" + auxDescripcion + "]");
        return (Integer.toString(this.getRamo()) + "-" + auxDescripcion);
    }

}
