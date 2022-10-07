package com.bse.negocio.comun;

import com.bse.accesodatos.comun.PatParametrosTienda;
import com.bse.accesodatos.comun.PatParametrosPKTienda;
import java.util.HashMap;
import javax.persistence.EntityManager;

public class CodigosTienda {

    public static short login_ok = 0;
    
    public static short codigo_false = 0;
    public static short codigo_true = 1;
    
    public static short codigo_invalido = -1;    
       
    public static String servicio_ok = "ok";
    public static String servicio_error = "error";
    
    public static String transaccion_cobro = "C";
    public static String transaccion_pago = "P";
    public static String transaccion_reversa = "R";
    public static String transaccion_anulacion = "A";     
       
    public static String patron_de_contrasena = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\p{Punct}).{6,20})";
    
    /*public static String plan_global = "GLOBAL";
    public static String plan_triple = "triple";
    public static String plan_doble = "doble";
    public static String plan_basico = "basico";*/
    
    public static String promo_3por2 = "3x2";
    
    private HashMap<PatParametrosPKTienda, PatParametrosTienda> cache;
    
    private static CodigosTienda instancia = null;
    
    private CodigosTienda() { 
        cache = new HashMap<PatParametrosPKTienda, PatParametrosTienda>();
    }
            
    public static CodigosTienda getCodigos() {
        synchronized (CodigosTienda.class) {
            if (instancia == null) {
                instancia = new CodigosTienda();
            }
        }
        return instancia;
    }  
    
    public String getCache(EntityManager em, PatParametrosPKTienda key) {
        synchronized (CodigosTienda.class) {
            if (cache.get(key) == null) {
                PatParametrosTienda p = em.find(PatParametrosTienda.class, key);
                if (p != null) {
                    cache.put(key, p);
                    return p.getValor();                    
                } else {
                    return null;
                }
            } else {
                return cache.get(key).getValor().trim();
            }
        }        
    }

    public void clearCache(EntityManager em){
    	System.out.println("TAM1 DE CACHE : " + cache.size());
		cache.clear();
		em.clear();
    	System.out.println("TAM2 DE CACHE : " + cache.size());
    }

    public char getPagosGeneracion(EntityManager em){
        PatParametrosPKTienda key = new PatParametrosPKTienda("PAGOS", "TEMPORARIOS", "GENERACION");
        return getCache(em, key).charAt(0);
    }
    
    public char getPagosPagado(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("PAGOS", "TEMPORARIOS", "PAGADO");
        return getCache(em, key).charAt(0);
    }
    
    public String getTablaTemporaria(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("PAGOS", "TABLA", "TEMPORARIA");
        return getCache(em, key);
    }   
    
    public String getTablaPreliquidacion(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("PAGOS", "TABLA", "PRELIQUIDACION");
        return getCache(em, key);
    }     
    
    public short getTopeLogin(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("WS", "TOPE", "LOGIN");
        return new Short(getCache(em, key));
    }   
    
    public boolean esPlanAuxilio(EntityManager em, String plan) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("AUME", "PLAN", plan);
        String p = getCache(em, key);
        return plan.trim().equalsIgnoreCase(p);
    }
    
    public String planAuxilioDoble(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("AUME", "PLAN", "DOBLE");
        return getCache(em, key);
    }
    
    public String planAuxilioTriple(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("AUME", "PLAN", "TRIPLE");
        return getCache(em, key);
    }    
    
    public String planAuxilioGlobal(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("AUME", "PLAN", "GLOBAL");
        return getCache(em, key);    
    } 
    
    public String planAuxilioBasico(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("AUME", "PLAN", "BASICO");
        return getCache(em, key);    
    } 
    
    public int getToleranciaDias(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("AUME", "TOLERANCIA", "DIAS");
        return new Integer(getCache(em, key));
    }

    public int getAuxiliosGlobalAnuales(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("AUME", "CANTIDAD_GLOBAL", "AUXILIOS");
        return new Integer(getCache(em, key));
    }
    
    public int getAuxiliosTripleAnuales(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("AUME", "CANTIDAD_TRIPLE", "AUXILIOS");
        return new Integer(getCache(em, key));
    }
    
    public int getAuxiliosDobleAnuales(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("AUME", "CANTIDAD_DOBLE", "AUXILIOS");
        return new Integer(getCache(em, key));
    }        
    
    public int getAuxiliosBasicoAnuales(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("AUME", "CANTIDAD_BASICO", "AUXILIOS");
        return new Integer(getCache(em, key));
    }        
    
    public int getForzarRegistroAuxilio(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("AUME", "FORZAR", "REGISTRO");
        return new Integer(getCache(em, key));
    }    
    
    public boolean esTipoVehiculoAuxilio(EntityManager em, String tipoVehiculo) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("AUME", "TIPO_VEHICULO_VALIDO", tipoVehiculo);        
        String p = getCache(em, key);
        return tipoVehiculo.trim().equalsIgnoreCase(p);        
    }

    public String getTipoVehiculoMoto(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("AUME", "TIPO_VEHICULO", "MOTO");        
        String p = getCache(em, key);
        return p.trim();
    }

    public String getTipoVehiculoCuatriciclo(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("AUME", "TIPO_VEHICULO", "CUATRI");        
        String p = getCache(em, key);
        return p.trim();
    }

    public String getTipoVehiculoMotoneta(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("AUME", "TIPO_VEHICULO", "MOTONETA");        
        String p = getCache(em, key);
        return p.trim();
    }

    public int getCilindradaMinimaMotos(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("AUME", "CILINDRADA", "MINIMA");        
        String p = getCache(em, key);
        return Integer.parseInt(p.trim());        
    }

    public boolean esPromocionRentadora(EntityManager em, String codigoPromocion) {
    	if ( codigoPromocion == null || codigoPromocion.equals("") || codigoPromocion.equals("NOAP") || codigoPromocion.equals("BSE001")
    				|| codigoPromocion.equals("BSE002") || codigoPromocion.equals("BSE004") || codigoPromocion.equals("BSE005")
    				|| codigoPromocion.equals("BSE006") || codigoPromocion.equals("BSE070") ){
    		return false;
    	}
    	
        PatParametrosPKTienda key = new PatParametrosPKTienda("AUME", "PROMOCION", codigoPromocion);        
        String p = getCache(em, key);
        if (p == null || p.trim().equals(""))
        	return false;

        return true;        
    }

    public Short getEstadoImpagoAfap(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("AFAP", "ESTADO", "IMPAGO");
        return new Short(getCache(em, key));
    }
    
    public Short getEstadoImpagoSerdom(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("SERDOM", "ESTADO", "IMPAGO");
        return new Short(getCache(em, key));
    }   
    
    public Short getEstadoPagoSerdom(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("SERDOM", "ESTADO", "PAGO");
        return new Short(getCache(em, key));
    }      
    
    public Short getEstadoImpagoPrestamos(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("PRESTAMOS", "ESTADO", "IMPAGO");
        return new Short(getCache(em, key));
    }   
    
    public Short getEstadoPagoPrestamos(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("PRESTAMOS", "ESTADO", "PAGO");
        return new Short(getCache(em, key));
    }      

    public Short getEstadoPagoAfap(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("AFAP", "ESTADO", "PAGO");
        return new Short(getCache(em, key));
    }    
    
    public String getTablaAfap(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("PAGOS", "TABLA", "AFAP");
        return getCache(em, key);
    }    
    
    public String getTablaSerdom(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("PAGOS", "TABLA", "SERDOM");
        return getCache(em, key);
    }
    
    public String getTablaPrestamos(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("PAGOS", "TABLA", "PRESTAMOS");
        return getCache(em, key);
    }

    public String getTablaFacturas(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("COBROS", "TABLA", "FACTURAS");
        return getCache(em, key);
    }

    public String getEntidadesSistarbanc(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("COBROS", "ENTIDADES", "SISTARBANC");
        return getCache(em, key);
    }

    public String getEntidadesSistarbancFacturas(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("COBROS", "ENTIDADES", "SISTARBANC_FACT");
        return getCache(em, key);
    }

    public String getEntidadesSistarbancPolizas(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("COBROS", "ENTIDADES", "SISTARBANC_POLI");
        return getCache(em, key);
    }

    public String getMediosPago(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("COBROS", "MEDIOS", "PAGO");
        return getCache(em, key);
    }

    public String getFiltrarVtos(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("COBROS", "FILTRAR", "VTOS");
        return getCache(em, key);
    }
    
    public String getDiasFiltrarVtos(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("COBROS", "DIASFILTRAR", "VTOS");
        return getCache(em, key);
    }

    public String getFiltrarDebitos(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("COBROS", "FILTRAR", "DEBITOS");
        return getCache(em, key);
    }
    
	public String getEstadoImpagoCSMAsistencial(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("PAGOS", "CSM", "IMPAGO");
        return getCache(em, key);
	}
	
	public String getEstadoPagoCSMAsistencial(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("PAGOS", "CSM", "PAGO");
        return getCache(em, key);
	}	

    public String getPlanesPagoSoa(EntityManager em) {
    	//'ESOA', 'PLANES', 'PAGO'
        PatParametrosPKTienda key = new PatParametrosPKTienda("ESOA", "PLANES", "PAGO");
        return getCache(em, key);
    }

    public String getPlanesPagoViajeros(EntityManager em) {
    	//'EVIAJEROS', 'PLANES', 'PAGO'
        PatParametrosPKTienda key = new PatParametrosPKTienda("EVIAJEROS", "PLANES", "PAGO");
        return getCache(em, key);
    }

    // SOA
    public String getSucursalEmisionSoa(EntityManager em) {
    	//'ESOA', 'SUCURSAL', 'EMISION'
        PatParametrosPKTienda key = new PatParametrosPKTienda("ESOA", "SUCURSAL", "EMISION");
        return getCache(em, key);
    }

    public String getRamoEmisionSoa(EntityManager em) {
    	//'ESOA', 'RAMO', 'EMISION'
        PatParametrosPKTienda key = new PatParametrosPKTienda("ESOA", "RAMO", "EMISION");
        return getCache(em, key);
    }
    
    public String getProductoEmisionSoa(EntityManager em) {
    	//'ESOA', 'PRODUCTO', 'EMISION'
        PatParametrosPKTienda key = new PatParametrosPKTienda("ESOA", "PRODUCTO", "EMISION");
        return getCache(em, key);
    }

    public String getProductorEmisionSoa(EntityManager em) {
    	//'ESOA', 'PRODUCTOR', 'EMISION'
        PatParametrosPKTienda key = new PatParametrosPKTienda("ESOA", "PRODUCTOR", "EMISION");
        return getCache(em, key);
    }

    public String getMonedaEmisionSoa(EntityManager em) {
    	//'ESOA', 'MONEDA', 'EMISION'
        PatParametrosPKTienda key = new PatParametrosPKTienda("ESOA", "MONEDA", "EMISION");
        return getCache(em, key);
    }
    
    public String getAppIdPortal(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'TIPO_CALCULO', 'EMISION', 'N');
    	PatParametrosPKTienda key = new PatParametrosPKTienda("ESOA", "APPID", "PORTAL");
    	return getCache(em, key);
    }

    public String getClaveAppIdPortal(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'TIPO_CALCULO', 'EMISION', 'N');
    	PatParametrosPKTienda key = new PatParametrosPKTienda("ESOA", "CLAVE", "APPIDPORTAL");
    	return getCache(em, key);
    }

    public String getServerSeguridadPortal(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'TIPO_CALCULO', 'EMISION', 'N');
    	PatParametrosPKTienda key = new PatParametrosPKTienda("ESOA", "SERVER", "SEGPORTAL");
    	return getCache(em, key);
    }

    public String getServerServiciosPortal(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'TIPO_CALCULO', 'EMISION', 'N');
    	PatParametrosPKTienda key = new PatParametrosPKTienda("ESOA", "SERVER", "SERVPORTAL");
    	return getCache(em, key);
    }
    
    public String getTipoCalculoEmisionSoa(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'TIPO_CALCULO', 'EMISION', 'N');
    	PatParametrosPKTienda key = new PatParametrosPKTienda("ESOA", "TIPO_CALCULO", "EMISION");
    	return getCache(em, key);
    }

    public String getFormaPagoEmisionSoa(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'FORMA_PAGO', 'EMISION', 'A');
    	PatParametrosPKTienda key = new PatParametrosPKTienda("ESOA", "FORMA_PAGO", "EMISION");
    	return getCache(em, key);
    }

    public String getVigTecnicaEmisionSoa(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'VIG_TECNICA', 'EMISION', '');
    	PatParametrosPKTienda key = new PatParametrosPKTienda("ESOA", "VIG_TECNICA", "EMISION");
    	return getCache(em, key);
    }

    public String getMedioPagoEmisionSoa(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'MEDIO_PAGO', 'EMISION', '1');
    	PatParametrosPKTienda key = new PatParametrosPKTienda("ESOA", "MEDIO_PAGO", "EMISION");
    	return getCache(em, key);
    }

    public String getOrigenPagoEmisionSoa(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'ORIGEN_PAGO', 'EMISION', 'A');
    	PatParametrosPKTienda key = new PatParametrosPKTienda("ESOA", "ORIGEN_PAGO", "EMISION");
    	return getCache(em, key);
    }

    public String getTipoFactEmisionSoa(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'TIPO_FACT', 'EMISION', 'F');
    	PatParametrosPKTienda key = new PatParametrosPKTienda("ESOA", "TIPO_FACT", "EMISION");
    	return getCache(em, key);
    }

    public String getPromocionEmisionSoa(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'PROMOCION', 'EMISION', 'NOAP');
    	PatParametrosPKTienda key = new PatParametrosPKTienda("ESOA", "PROMOCION", "EMISION");
    	return getCache(em, key);
    }

    public String getRenovacionEmisionSoa(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'RENOVACION', 'EMISION', 'N');
    	PatParametrosPKTienda key = new PatParametrosPKTienda("ESOA", "RENOVACION", "EMISION");
    	return getCache(em, key);
    }

    public String getUsuarioWebEmisionSoa(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'USUARIO_WEB', 'EMISION', 'COTIZAWEB');    
    	PatParametrosPKTienda key = new PatParametrosPKTienda("ESOA", "USUARIO_WEB", "EMISION");
    	return getCache(em, key);
    }

    public String getDescMonedaSOA(EntityManager em, String codigoMoneda) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("ESOA", "MONEDAS", codigoMoneda);        
        return getCache(em, key);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI -  // 
    // - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI -  // 
    // - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI - INDIVI -  // 
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public String getTipoFactEmisionIndivi(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EINDIVI", "TIPO_FACT", "EMISION");
        return getCache(em, key);
    }

    public String getSucursalEmisionIndivi(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EINDIVI", "SUCURSAL", "EMISION");
        return getCache(em, key);
    }

    public String getRamoEmisionIndivi(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EINDIVI", "RAMO", "EMISION");
        return getCache(em, key);
    }

    public String getMonedaEmisionIndivi(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EINDIVI", "MONEDA", "EMISION");
        return getCache(em, key);
    }
    
    public String getMedioPagoEmisionIndivi(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EINDIVI", "MEDIO_PAGO", "EMISION");
        return getCache(em, key);
    }

    public String getOrigenPagoEmisionIndivi(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EINDIVI", "ORIGEN_PAGO", "EMISION");
        return getCache(em, key);
    }

    public String getPromocionEmisionIndivi(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EINDIVI", "PROMOCION", "EMISION");
        return getCache(em, key);
    }

    public String getTipoCalculoEmisionIndivi(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EINDIVI", "TIPO_CALCULO", "EMISION");
        return getCache(em, key);
    }

    public String getFormaPagoEmisionIndivi(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EINDIVI", "FORMA_PAGO", "EMISION");
        return getCache(em, key);
    }

    public String getRenovacionEmisionIndivi(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EINDIVI", "RENOVACION", "EMISION");
        return getCache(em, key);
    }

    public String getUsuarioWebEmisionIndivi(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EINDIVI", "USUARIO_WEB", "EMISION");
        return getCache(em, key);
    }
    
    public String getProductoEmisionIndivi(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EINDIVI", "PRODUCTO", "EMISION");
        return getCache(em, key);
    }

    public String getProductorEmisionIndivi(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EINDIVI", "PRODUCTOR", "EMISION");
        return getCache(em, key);
    }

    public String getPlanPagoInicialIndivi(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EINDIVI", "PLAN_PAGO_INI", "EMISION");
        return getCache(em, key);
    }

    public String getMarcasEmisionIndivi(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EINDIVI", "MARCAS_PREF", "EMISION");
        return getCache(em, key);
    }

    public String getAreasCirculacionIndivi(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EINDIVI", "AREAS_CIRCULACION", "EMISION");
        return getCache(em, key);
    }

    public String getTiposVehiculosIndivi(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EINDIVI", "TIPOS_VEHICULOS", "EMISION");
        return getCache(em, key);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    

    ////////////////////////////////////////////
    ///////////////////////////////////// RCYATE
    public String getPlanesPagoEDepor(EntityManager em) {
    	//'ESOA', 'PLANES', 'PAGO'
        PatParametrosPKTienda key = new PatParametrosPKTienda("EDEPOR", "PLANES", "PAGO");
        return getCache(em, key);
    }

    public String getSucursalEmisionEDepor(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EDEPOR", "SUCURSAL", "EMISION");
        return getCache(em, key);
    }

    public String getRamoEmisionEDepor(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EDEPOR", "RAMO", "EMISION");
        return getCache(em, key);
    }
    
    public String getProductoEmisionEDepor(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EDEPOR", "PRODUCTO", "EMISION");
        return getCache(em, key);
    }

    public String getProductorEmisionEDepor(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EDEPOR", "PRODUCTOR", "EMISION");
        return getCache(em, key);
    }

    public String getMonedaEmisionEDepor(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EDEPOR", "MONEDA", "EMISION");
        return getCache(em, key);
    }
    
    public String getTipoCalculoEmisionEDepor(EntityManager em) {
    	PatParametrosPKTienda key = new PatParametrosPKTienda("EDEPOR", "TIPO_CALCULO", "EMISION");
    	return getCache(em, key);
    }

    public String getFormaPagoEmisionEDepor(EntityManager em) {
    	PatParametrosPKTienda key = new PatParametrosPKTienda("EDEPOR", "FORMA_PAGO", "EMISION");
    	return getCache(em, key);
    }

    public String getVigTecnicaEmisionEDepor(EntityManager em) {
    	PatParametrosPKTienda key = new PatParametrosPKTienda("EDEPOR", "VIG_TECNICA", "EMISION");
    	return getCache(em, key);
    }

    public String getMedioPagoEmisionEDepor(EntityManager em) {
    	PatParametrosPKTienda key = new PatParametrosPKTienda("EDEPOR", "MEDIO_PAGO", "EMISION");
    	return getCache(em, key);
    }

    public String getOrigenPagoEmisionEDepor(EntityManager em) {
    	PatParametrosPKTienda key = new PatParametrosPKTienda("EDEPOR", "ORIGEN_PAGO", "EMISION");
    	return getCache(em, key);
    }

    public String getTipoFactEmisionEDepor(EntityManager em) {
    	PatParametrosPKTienda key = new PatParametrosPKTienda("EDEPOR", "TIPO_FACT", "EMISION");
    	return getCache(em, key);
    }

    public String getPromocionEmisionEDepor(EntityManager em) {
    	PatParametrosPKTienda key = new PatParametrosPKTienda("EDEPOR", "PROMOCION", "EMISION");
    	return getCache(em, key);
    }

    public String getRenovacionEmisionEDepor(EntityManager em) {
    	PatParametrosPKTienda key = new PatParametrosPKTienda("EDEPOR", "RENOVACION", "EMISION");
    	return getCache(em, key);
    }

    public String getUsuarioWebEmisionEDepor(EntityManager em) {
    	PatParametrosPKTienda key = new PatParametrosPKTienda("EDEPOR", "USUARIO_WEB", "EMISION");
    	return getCache(em, key);
    }

    public String getDescMonedaEDepor(EntityManager em, String codigoMoneda) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EDEPOR", "MONEDAS", codigoMoneda);        
        return getCache(em, key);
    }
    ////////////////////////////////////////////
    
    public String getSucursalEmisionViajeros(EntityManager em) {
    	//'EVIAJEROS', 'SUCURSAL', 'EMISION'
        PatParametrosPKTienda key = new PatParametrosPKTienda("EVIAJEROS", "SUCURSAL", "EMISION");
        return getCache(em, key);
    }

    public String getRamoEmisionViajeros(EntityManager em) {
    	//'EVIAJEROS', 'RAMO', 'EMISION'
        PatParametrosPKTienda key = new PatParametrosPKTienda("EVIAJEROS", "RAMO", "EMISION");
        return getCache(em, key);
    }
    
    public String getProductoEmisionViajeros(EntityManager em) {
    	//'EVIAJEROS', 'PRODUCTO', 'EMISION'
        PatParametrosPKTienda key = new PatParametrosPKTienda("EVIAJEROS", "PRODUCTO", "EMISION");
        return getCache(em, key);
    }

    public String getProductorEmisionViajeros(EntityManager em) {
    	//'EVIAJEROS', 'PRODUCTOR', 'EMISION'
        PatParametrosPKTienda key = new PatParametrosPKTienda("EVIAJEROS", "PRODUCTOR", "EMISION");
        return getCache(em, key);
    }

    public String getMonedaEmisionViajeros(EntityManager em) {
    	//'EVIAJEROS', 'MONEDA', 'EMISION'
        PatParametrosPKTienda key = new PatParametrosPKTienda("EVIAJEROS", "MONEDA", "EMISION");
        return getCache(em, key);
    }
    
    public String getTipoCalculoEmisionViajeros(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'TIPO_CALCULO', 'EMISION', 'N');
    	PatParametrosPKTienda key = new PatParametrosPKTienda("EVIAJEROS", "TIPO_CALCULO", "EMISION");
    	return getCache(em, key);
    }

    public String getFormaPagoEmisionViajeros(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'FORMA_PAGO', 'EMISION', 'A');
    	PatParametrosPKTienda key = new PatParametrosPKTienda("EVIAJEROS", "FORMA_PAGO", "EMISION");
    	return getCache(em, key);
    }

    public String getVigTecnicaEmisionViajeros(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'VIG_TECNICA', 'EMISION', '');
    	PatParametrosPKTienda key = new PatParametrosPKTienda("EVIAJEROS", "VIG_TECNICA", "EMISION");
    	return getCache(em, key);
    }

    public String getMedioPagoEmisionViajeros(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'MEDIO_PAGO', 'EMISION', '1');
    	PatParametrosPKTienda key = new PatParametrosPKTienda("EVIAJEROS", "MEDIO_PAGO", "EMISION");
    	return getCache(em, key);
    }

    public String getOrigenPagoEmisionViajeros(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'ORIGEN_PAGO', 'EMISION', 'A');
    	PatParametrosPKTienda key = new PatParametrosPKTienda("EVIAJEROS", "ORIGEN_PAGO", "EMISION");
    	return getCache(em, key);
    }

    public String getTipoFactEmisionViajeros(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'TIPO_FACT', 'EMISION', 'F');
    	PatParametrosPKTienda key = new PatParametrosPKTienda("EVIAJEROS", "TIPO_FACT", "EMISION");
    	return getCache(em, key);
    }

    public String getPromocionEmisionViajeros(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'PROMOCION', 'EMISION', 'NOAP');
    	PatParametrosPKTienda key = new PatParametrosPKTienda("EVIAJEROS", "PROMOCION", "EMISION");
    	return getCache(em, key);
    }

    public String getRenovacionEmisionViajeros(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'RENOVACION', 'EMISION', 'N');
    	PatParametrosPKTienda key = new PatParametrosPKTienda("EVIAJEROS", "RENOVACION", "EMISION");
    	return getCache(em, key);
    }

    public String getUsuarioWebEmisionViajeros(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'USUARIO_WEB', 'EMISION', 'COTIZAWEB');    
    	PatParametrosPKTienda key = new PatParametrosPKTienda("EVIAJEROS", "USUARIO_WEB", "EMISION");
    	return getCache(em, key);
    }
    
    public String getTablaAsistencial(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("PAGOS", "TABLA", "CSM");
        return getCache(em, key);
    }

	public String getTasaMsp(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EVIAJEROS", "TASA", "MSP");
        return getCache(em, key);
	}     

// EComercial
    public String getUsuarioNotifPagoEC(EntityManager em) {
    	PatParametrosPKTienda key = new PatParametrosPKTienda("ECOMERCIAL", "NOTIF", "USUARIO");
    	return getCache(em, key);
    }

    public String getClaveNotifPagoEC(EntityManager em) {
    	PatParametrosPKTienda key = new PatParametrosPKTienda("ECOMERCIAL", "NOTIF", "CLAVE");
    	return getCache(em, key);
    }

    public String getAppIdNotifPagoEC(EntityManager em) {
    	PatParametrosPKTienda key = new PatParametrosPKTienda("ECOMERCIAL", "NOTIF", "APPID");
    	return getCache(em, key);
    }

    /////////////////////////////////////////
    /////////// BICIS
    
    public String getSucursalEmisionBici(EntityManager em) {
    	//'ESOA', 'SUCURSAL', 'EMISION'
        PatParametrosPKTienda key = new PatParametrosPKTienda("EBICI", "SUCURSAL", "EMISION");
        return getCache(em, key);
    }

    public String getRamoEmisionBici(EntityManager em) {
    	//'ESOA', 'RAMO', 'EMISION'
        PatParametrosPKTienda key = new PatParametrosPKTienda("EBICI", "RAMO", "EMISION");
        return getCache(em, key);
    }
    
    public String getProductoEmisionBici(EntityManager em) {
    	//'ESOA', 'PRODUCTO', 'EMISION'
        PatParametrosPKTienda key = new PatParametrosPKTienda("EBICI", "PRODUCTO", "EMISION");
        return getCache(em, key);
    }

    public String getProductorEmisionBici(EntityManager em) {
    	//'ESOA', 'PRODUCTOR', 'EMISION'
        PatParametrosPKTienda key = new PatParametrosPKTienda("EBICI", "PRODUCTOR", "EMISION");
        return getCache(em, key);
    }

    public String getMonedaEmisionBici(EntityManager em) {
    	//'ESOA', 'MONEDA', 'EMISION'
        PatParametrosPKTienda key = new PatParametrosPKTienda("EBICI", "MONEDA", "EMISION");
        return getCache(em, key);
    }
    
    public String getTipoCalculoEmisionBici(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'TIPO_CALCULO', 'EMISION', 'N');
    	PatParametrosPKTienda key = new PatParametrosPKTienda("EBICI", "TIPO_CALCULO", "EMISION");
    	return getCache(em, key);
    }

    public String getFormaPagoEmisionBici(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'FORMA_PAGO', 'EMISION', 'A');
    	PatParametrosPKTienda key = new PatParametrosPKTienda("EBICI", "FORMA_PAGO", "EMISION");
    	return getCache(em, key);
    }

    public String getVigTecnicaEmisionBici(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'VIG_TECNICA', 'EMISION', '');
    	PatParametrosPKTienda key = new PatParametrosPKTienda("EBICI", "VIG_TECNICA", "EMISION");
    	return getCache(em, key);
    }

    public String getMedioPagoEmisionBici(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'MEDIO_PAGO', 'EMISION', '1');
    	PatParametrosPKTienda key = new PatParametrosPKTienda("EBICI", "MEDIO_PAGO", "EMISION");
    	return getCache(em, key);
    }

    public String getOrigenPagoEmisionBici(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'ORIGEN_PAGO', 'EMISION', 'A');
    	PatParametrosPKTienda key = new PatParametrosPKTienda("EBICI", "ORIGEN_PAGO", "EMISION");
    	return getCache(em, key);
    }

    public String getTipoFactEmisionBici(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'TIPO_FACT', 'EMISION', 'F');
    	PatParametrosPKTienda key = new PatParametrosPKTienda("EBICI", "TIPO_FACT", "EMISION");
    	return getCache(em, key);
    }

    public String getPromocionEmisionBici(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'PROMOCION', 'EMISION', 'NOAP');
    	PatParametrosPKTienda key = new PatParametrosPKTienda("EBICI", "PROMOCION", "EMISION");
    	return getCache(em, key);
    }

    public String getRenovacionEmisionBici(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'RENOVACION', 'EMISION', 'N');
    	PatParametrosPKTienda key = new PatParametrosPKTienda("EBICI", "RENOVACION", "EMISION");
    	return getCache(em, key);
    }

    public String getUsuarioWebEmisionBici(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'USUARIO_WEB', 'EMISION', 'COTIZAWEB');    
    	PatParametrosPKTienda key = new PatParametrosPKTienda("EBICI", "USUARIO_WEB", "EMISION");
    	return getCache(em, key);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS -  // 
    // - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS -  // 
    // - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS - VARIOS -  // 
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public String getSucursalEmisionOPersonal(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EVARIOS", "SUCURSAL", "EMISION");
        return getCache(em, key);
    }

    public String getRamoEmisionOPersonal(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EVARIOS", "RAMO", "EMISION");
        return getCache(em, key);
    }
    
    public String getProductoEmisionOPersonal(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EVARIOS", "PRODUCTO", "EMISION");
        return getCache(em, key);
    }

    public String getProductorEmisionOPersonal(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EVARIOS", "PRODUCTOR", "EMISION");
        return getCache(em, key);
    }

    public String getMonedaEmisionOPersonal(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EVARIOS", "MONEDA", "EMISION");
        return getCache(em, key);
    }
    
    public String getTipoCalculoEmisionOPersonal(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EVARIOS", "TIPO_CALCULO", "EMISION");
        return getCache(em, key);
    }

    public String getFormaPagoEmisionOPersonal(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EVARIOS", "FORMA_PAGO", "EMISION");
        return getCache(em, key);
    }

    public String getVigTecnicaEmisionOPersonal(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EVARIOS", "VIG_TECNICA", "EMISION");
        return getCache(em, key);
    }

    public String getMedioPagoEmisionOPersonal(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EVARIOS", "MEDIO_PAGO", "EMISION");
        return getCache(em, key);
    }

    public String getOrigenPagoEmisionOPersonal(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EVARIOS", "ORIGEN_PAGO", "EMISION");
        return getCache(em, key);
    }

    public String getTipoFactEmisionOPersonal(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EVARIOS", "TIPO_FACT", "EMISION");
        return getCache(em, key);
    }

    public String getPromocionEmisionOPersonal(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EVARIOS", "PROMOCION", "EMISION");
        return getCache(em, key);
    }

    public String getRenovacionEmisionOPersonal(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EVARIOS", "RENOVACION", "EMISION");
        return getCache(em, key);
    }

    public String getUsuarioWebEmisionOPersonal(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EVARIOS", "USUARIO_WEB", "EMISION");
        return getCache(em, key);
    }

    public String getPlanesCoberturaEmisionOPersonal(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EVARIOS", "PLANES_COBERTURA", "EMISION");
        return getCache(em, key);
    }

    public String getTiposObjetosEmisionOPersonal(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EVARIOS", "TIPOS_OBJETOS", "EMISION");
        return getCache(em, key);
    }

    public String getTiposMovilidadEmisionOPersonal(EntityManager em) {
        PatParametrosPKTienda key = new PatParametrosPKTienda("EVARIOS", "TIPOS_MOVILIDAD", "EMISION");
        return getCache(em, key);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
}
