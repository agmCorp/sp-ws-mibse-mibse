package com.bse.negocio.comun;

public class CodigosError {
    //seguridad
    public static short excepcion_generica = 1;
    public static short login_error_credenciales = 2;
    public static short login_error_usuario_contrasena = 3;
    public static short login_error_ip_invalida = 4;
    public static short login_error_usuario_bloqueado = 5;
    public static short debe_cambiar_contrasena = 6;
    public static short no_cumple_patron = 7;    
    //temporarias
    public static short no_hay_nada_para_pagar = 8;
    public static short no_se_puede_anular_fecha = 9;
    public static short no_se_puede_anular_otra_red = 10;
    public static short no_se_puede_anular_en_otra_agencia = 11;
    public static short no_existe_transaccion_original = 12;
    public static short transaccion_ya_anulada = 13;
    public static short transaccion_a_reversar_anulada = 14;
    //auxiliomecanico
    public static short datos_de_consulta_vacios = 15;
    public static short no_es_cliente = 16;
    public static short poliza_anulada = 17;
    public static short poliza_vencida = 18;
    public static short no_es_plan_valido = 19;
    public static short no_existe_servicio = 20;
    public static short auxilio_ya_reversado = 21;
    public static short error_en_invocacion = 22;
    public static short no_existe_consulta = 23;
    public static short tipo_vehiculo_invalido = 24;
    public static int no_posee_auxilios_restantes = 25;
    //preliquidaciones
    public static short no_existe_preliquidacion = 26;
    public static short no_existe_pago_preliquidacion = 27;
    // pagos sistarbanc & abitab
    public static short no_existe_cliente = 28;
    public static short tx_ya_procesada = 29;
    public static short tx_duplicada = 30;
    public static short tipo_documento_invalido = 31;
    public static short importe_erroneo = 32;
    public static short pago_erroneo = 33;
    public static short no_existe_factura = 34;
    public static short moneda_erronea = 35;
    public static short factura_no_pagable = 36;

    // cotizacion y emision rector
    public static short error_cotizacion_rector = 37;
    public static short marca_vehiculo_invalida = 38;
    public static short anio_vehiculo_invalido = 39;
    public static short categoria_vehiculo_invalida = 40;
	public static short plan_cobertura_invalido = 41;
	public static short documento_invalido = 42;
	public static short matricula_vehiculo_invalida = 43;
	public static short padron_vehiculo_invalido = 44;
	public static short motor_vehiculo_invalido = 45;
	public static short chasis_vehiculo_invalido = 46;
	public static short cotizacion_invalida = 47;
	public static short importe_cotizacion_erroneo = 48;
	public static short plan_pago_invalido = 49;
	
	public static short fechas_invalidas = 50;
	public static short capital_invalido = 51;
	public static short email_invalido = 52;
	public static short edad_invalida = 53;
    public static short consumidor_final_invalido = 54;
    public static short error_emision_rector = 55;
    public static short capital_muerte_invalido = 56;
    public static short capital_cobertura_prexistentes_invalido = 57;

    public static short nombre_invalido = 58;
    public static short apellido_invalido = 59;
    public static short lista_personas_invalida = 60;

    public static short tipo_buque_invalido = 61;
	public static short codigo_adhesion_invalido = 62;
	public static short faltan_datos_bicicleta = 63;
	public static short fecha_factura_invalida = 64;

}   
 
