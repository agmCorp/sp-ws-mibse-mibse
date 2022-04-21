package com.bse.negocio.comun;

import com.bse.accesodatos.comun.PatParametros;
import com.bse.accesodatos.comun.PatParametrosPK;
import java.util.HashMap;
import javax.persistence.EntityManager;

public class Codigos {

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
    
    private HashMap<PatParametrosPK, PatParametros> cache;
    
    private static Codigos instancia = null;
    
    private Codigos() { 
        cache = new HashMap<PatParametrosPK, PatParametros>();
    }
            
    public static Codigos getCodigos() {
        synchronized (Codigos.class) {
            if (instancia == null) {
                instancia = new Codigos();
            }
        }
        return instancia;
    }  
    
    public String getCache(EntityManager em, PatParametrosPK key) {
        synchronized (Codigos.class) {
            if (cache.get(key) == null) {
                PatParametros p = em.find(PatParametros.class, key);
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
        PatParametrosPK key = new PatParametrosPK("PAGOS", "TEMPORARIOS", "GENERACION");
        return getCache(em, key).charAt(0);
    }
    
    public char getPagosPagado(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("PAGOS", "TEMPORARIOS", "PAGADO");
        return getCache(em, key).charAt(0);
    }
    
    public String getTablaTemporaria(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("PAGOS", "TABLA", "TEMPORARIA");
        return getCache(em, key);
    }   
    
    public String getTablaPreliquidacion(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("PAGOS", "TABLA", "PRELIQUIDACION");
        return getCache(em, key);
    }     
    
    public short getTopeLogin(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("WS", "TOPE", "LOGIN");
        return new Short(getCache(em, key));
    }   
    
    public boolean esPlanAuxilio(EntityManager em, String plan) {
        PatParametrosPK key = new PatParametrosPK("AUME", "PLAN", plan);
        String p = getCache(em, key);
        return plan.trim().equalsIgnoreCase(p);
    }
    
    public String planAuxilioDoble(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("AUME", "PLAN", "DOBLE");
        return getCache(em, key);
    }
    
    public String planAuxilioTriple(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("AUME", "PLAN", "TRIPLE");
        return getCache(em, key);
    }    
    
    public String planAuxilioGlobal(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("AUME", "PLAN", "GLOBAL");
        return getCache(em, key);    
    } 
    
    public String planAuxilioBasico(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("AUME", "PLAN", "BASICO");
        return getCache(em, key);    
    } 
    
    public int getToleranciaDias(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("AUME", "TOLERANCIA", "DIAS");
        return new Integer(getCache(em, key));
    }

    public int getAuxiliosGlobalAnuales(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("AUME", "CANTIDAD_GLOBAL", "AUXILIOS");
        return new Integer(getCache(em, key));
    }
    
    public int getAuxiliosTripleAnuales(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("AUME", "CANTIDAD_TRIPLE", "AUXILIOS");
        return new Integer(getCache(em, key));
    }
    
    public int getAuxiliosDobleAnuales(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("AUME", "CANTIDAD_DOBLE", "AUXILIOS");
        return new Integer(getCache(em, key));
    }        
    
    public int getAuxiliosBasicoAnuales(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("AUME", "CANTIDAD_BASICO", "AUXILIOS");
        return new Integer(getCache(em, key));
    }        
    
    public int getForzarRegistroAuxilio(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("AUME", "FORZAR", "REGISTRO");
        return new Integer(getCache(em, key));
    }    
    
    public boolean esTipoVehiculoAuxilio(EntityManager em, String tipoVehiculo) {
        PatParametrosPK key = new PatParametrosPK("AUME", "TIPO_VEHICULO_VALIDO", tipoVehiculo);        
        String p = getCache(em, key);
        return tipoVehiculo.trim().equalsIgnoreCase(p);        
    }

    public String getTipoVehiculoMoto(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("AUME", "TIPO_VEHICULO", "MOTO");        
        String p = getCache(em, key);
        return p.trim();
    }

    public String getTipoVehiculoCuatriciclo(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("AUME", "TIPO_VEHICULO", "CUATRI");        
        String p = getCache(em, key);
        return p.trim();
    }

    public String getTipoVehiculoMotoneta(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("AUME", "TIPO_VEHICULO", "MOTONETA");        
        String p = getCache(em, key);
        return p.trim();
    }

    public int getCilindradaMinimaMotos(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("AUME", "CILINDRADA", "MINIMA");        
        String p = getCache(em, key);
        return Integer.parseInt(p.trim());        
    }

    public boolean esPromocionRentadora(EntityManager em, String codigoPromocion) {
    	if ( codigoPromocion == null || codigoPromocion.equals("") || codigoPromocion.equals("NOAP") || codigoPromocion.equals("BSE001")
    				|| codigoPromocion.equals("BSE002") || codigoPromocion.equals("BSE004") || codigoPromocion.equals("BSE005")
    				|| codigoPromocion.equals("BSE006") || codigoPromocion.equals("BSE070") ){
    		return false;
    	}
    	
        PatParametrosPK key = new PatParametrosPK("AUME", "PROMOCION", codigoPromocion);        
        String p = getCache(em, key);
        if (p == null || p.trim().equals(""))
        	return false;

        return true;        
    }

    public Short getEstadoImpagoAfap(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("AFAP", "ESTADO", "IMPAGO");
        return new Short(getCache(em, key));
    }
    
    public Short getEstadoImpagoSerdom(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("SERDOM", "ESTADO", "IMPAGO");
        return new Short(getCache(em, key));
    }   
    
    public Short getEstadoPagoSerdom(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("SERDOM", "ESTADO", "PAGO");
        return new Short(getCache(em, key));
    }      
    
    public Short getEstadoImpagoPrestamos(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("PRESTAMOS", "ESTADO", "IMPAGO");
        return new Short(getCache(em, key));
    }   
    
    public Short getEstadoPagoPrestamos(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("PRESTAMOS", "ESTADO", "PAGO");
        return new Short(getCache(em, key));
    }      

    public Short getEstadoPagoAfap(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("AFAP", "ESTADO", "PAGO");
        return new Short(getCache(em, key));
    }    
    
    public String getTablaAfap(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("PAGOS", "TABLA", "AFAP");
        return getCache(em, key);
    }    
    
    public String getTablaSerdom(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("PAGOS", "TABLA", "SERDOM");
        return getCache(em, key);
    }
    
    public String getTablaPrestamos(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("PAGOS", "TABLA", "PRESTAMOS");
        return getCache(em, key);
    }

    public String getTablaFacturas(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("COBROS", "TABLA", "FACTURAS");
        return getCache(em, key);
    }

    public String getEntidadesSistarbanc(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("COBROS", "ENTIDADES", "SISTARBANC");
        return getCache(em, key);
    }

    public String getEntidadesSistarbancFacturas(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("COBROS", "ENTIDADES", "SISTARBANC_FACT");
        return getCache(em, key);
    }

    public String getEntidadesSistarbancPolizas(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("COBROS", "ENTIDADES", "SISTARBANC_POLI");
        return getCache(em, key);
    }

    public String getMediosPago(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("COBROS", "MEDIOS", "PAGO");
        return getCache(em, key);
    }

    public String getFiltrarVtos(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("COBROS", "FILTRAR", "VTOS");
        return getCache(em, key);
    }
    
    public String getDiasFiltrarVtos(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("COBROS", "DIASFILTRAR", "VTOS");
        return getCache(em, key);
    }

    public String getFiltrarDebitos(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("COBROS", "FILTRAR", "DEBITOS");
        return getCache(em, key);
    }
    
	public String getEstadoImpagoCSMAsistencial(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("PAGOS", "CSM", "IMPAGO");
        return getCache(em, key);
	}
	
	public String getEstadoPagoCSMAsistencial(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("PAGOS", "CSM", "PAGO");
        return getCache(em, key);
	}	

    public String getPlanesPagoSoa(EntityManager em) {
    	//'ESOA', 'PLANES', 'PAGO'
        PatParametrosPK key = new PatParametrosPK("ESOA", "PLANES", "PAGO");
        return getCache(em, key);
    }

    public String getPlanesPagoViajeros(EntityManager em) {
    	//'EVIAJEROS', 'PLANES', 'PAGO'
        PatParametrosPK key = new PatParametrosPK("EVIAJEROS", "PLANES", "PAGO");
        return getCache(em, key);
    }

    // SOA
    public String getSucursalEmisionSoa(EntityManager em) {
    	//'ESOA', 'SUCURSAL', 'EMISION'
        PatParametrosPK key = new PatParametrosPK("ESOA", "SUCURSAL", "EMISION");
        return getCache(em, key);
    }

    public String getRamoEmisionSoa(EntityManager em) {
    	//'ESOA', 'RAMO', 'EMISION'
        PatParametrosPK key = new PatParametrosPK("ESOA", "RAMO", "EMISION");
        return getCache(em, key);
    }
    
    public String getProductoEmisionSoa(EntityManager em) {
    	//'ESOA', 'PRODUCTO', 'EMISION'
        PatParametrosPK key = new PatParametrosPK("ESOA", "PRODUCTO", "EMISION");
        return getCache(em, key);
    }

    public String getProductorEmisionSoa(EntityManager em) {
    	//'ESOA', 'PRODUCTOR', 'EMISION'
        PatParametrosPK key = new PatParametrosPK("ESOA", "PRODUCTOR", "EMISION");
        return getCache(em, key);
    }

    public String getMonedaEmisionSoa(EntityManager em) {
    	//'ESOA', 'MONEDA', 'EMISION'
        PatParametrosPK key = new PatParametrosPK("ESOA", "MONEDA", "EMISION");
        return getCache(em, key);
    }
    
    public String getAppIdPortal(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'TIPO_CALCULO', 'EMISION', 'N');
    	PatParametrosPK key = new PatParametrosPK("ESOA", "APPID", "PORTAL");
    	return getCache(em, key);
    }

    public String getClaveAppIdPortal(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'TIPO_CALCULO', 'EMISION', 'N');
    	PatParametrosPK key = new PatParametrosPK("ESOA", "CLAVE", "APPIDPORTAL");
    	return getCache(em, key);
    }

    public String getServerSeguridadPortal(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'TIPO_CALCULO', 'EMISION', 'N');
    	PatParametrosPK key = new PatParametrosPK("ESOA", "SERVER", "SEGPORTAL");
    	return getCache(em, key);
    }

    public String getServerServiciosPortal(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'TIPO_CALCULO', 'EMISION', 'N');
    	PatParametrosPK key = new PatParametrosPK("ESOA", "SERVER", "SERVPORTAL");
    	return getCache(em, key);
    }
    
    public String getTipoCalculoEmisionSoa(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'TIPO_CALCULO', 'EMISION', 'N');
    	PatParametrosPK key = new PatParametrosPK("ESOA", "TIPO_CALCULO", "EMISION");
    	return getCache(em, key);
    }

    public String getFormaPagoEmisionSoa(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'FORMA_PAGO', 'EMISION', 'A');
    	PatParametrosPK key = new PatParametrosPK("ESOA", "FORMA_PAGO", "EMISION");
    	return getCache(em, key);
    }

    public String getVigTecnicaEmisionSoa(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'VIG_TECNICA', 'EMISION', '');
    	PatParametrosPK key = new PatParametrosPK("ESOA", "VIG_TECNICA", "EMISION");
    	return getCache(em, key);
    }

    public String getMedioPagoEmisionSoa(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'MEDIO_PAGO', 'EMISION', '1');
    	PatParametrosPK key = new PatParametrosPK("ESOA", "MEDIO_PAGO", "EMISION");
    	return getCache(em, key);
    }

    public String getOrigenPagoEmisionSoa(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'ORIGEN_PAGO', 'EMISION', 'A');
    	PatParametrosPK key = new PatParametrosPK("ESOA", "ORIGEN_PAGO", "EMISION");
    	return getCache(em, key);
    }

    public String getTipoFactEmisionSoa(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'TIPO_FACT', 'EMISION', 'F');
    	PatParametrosPK key = new PatParametrosPK("ESOA", "TIPO_FACT", "EMISION");
    	return getCache(em, key);
    }

    public String getPromocionEmisionSoa(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'PROMOCION', 'EMISION', 'NOAP');
    	PatParametrosPK key = new PatParametrosPK("ESOA", "PROMOCION", "EMISION");
    	return getCache(em, key);
    }

    public String getRenovacionEmisionSoa(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'RENOVACION', 'EMISION', 'N');
    	PatParametrosPK key = new PatParametrosPK("ESOA", "RENOVACION", "EMISION");
    	return getCache(em, key);
    }

    public String getUsuarioWebEmisionSoa(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'USUARIO_WEB', 'EMISION', 'COTIZAWEB');    
    	PatParametrosPK key = new PatParametrosPK("ESOA", "USUARIO_WEB", "EMISION");
    	return getCache(em, key);
    }

    public String getDescMonedaSOA(EntityManager em, String codigoMoneda) {
        PatParametrosPK key = new PatParametrosPK("ESOA", "MONEDAS", codigoMoneda);        
        return getCache(em, key);
    }

    ////////////////////////////////////////////
    /////////////////////////////////////INDIVI
    
    public String getSucursalEmisionIndivi(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("EINDIVI", "SUCURSAL", "EMISION");
        return getCache(em, key);
    }

    public String getRamoEmisionIndivi(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("EINDIVI", "RAMO", "EMISION");
        return getCache(em, key);
    }
    
    public String getProductoEmisionIndivi(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("EINDIVI", "PRODUCTO", "EMISION");
        return getCache(em, key);
    }

    public String getProductorEmisionIndivi(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("EINDIVI", "PRODUCTOR", "EMISION");
        return getCache(em, key);
    }

    public String getMonedaEmisionIndivi(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("EINDIVI", "MONEDA", "EMISION");
        return getCache(em, key);
    }
    
    public String getTipoCalculoEmisionIndivi(EntityManager em) {
    	PatParametrosPK key = new PatParametrosPK("EINDIVI", "TIPO_CALCULO", "EMISION");
    	return getCache(em, key);
    }

    public String getFormaPagoEmisionIndivi(EntityManager em) {
    	PatParametrosPK key = new PatParametrosPK("EINDIVI", "FORMA_PAGO", "EMISION");
    	return getCache(em, key);
    }

    public String getVigTecnicaEmisionIndivi(EntityManager em) {
    	PatParametrosPK key = new PatParametrosPK("EINDIVI", "VIG_TECNICA", "EMISION");
    	return getCache(em, key);
    }

    public String getMedioPagoEmisionIndivi(EntityManager em) {
    	PatParametrosPK key = new PatParametrosPK("EINDIVI", "MEDIO_PAGO", "EMISION");
    	return getCache(em, key);
    }

    public String getOrigenPagoEmisionIndivi(EntityManager em) {
    	PatParametrosPK key = new PatParametrosPK("EINDIVI", "ORIGEN_PAGO", "EMISION");
    	return getCache(em, key);
    }

    public String getTipoFactEmisionIndivi(EntityManager em) {
    	PatParametrosPK key = new PatParametrosPK("EINDIVI", "TIPO_FACT", "EMISION");
    	return getCache(em, key);
    }

    public String getPromocionEmisionIndivi(EntityManager em) {
    	PatParametrosPK key = new PatParametrosPK("EINDIVI", "PROMOCION", "EMISION");
    	return getCache(em, key);
    }

    public String getRenovacionEmisionIndivi(EntityManager em) {
    	PatParametrosPK key = new PatParametrosPK("EINDIVI", "RENOVACION", "EMISION");
    	return getCache(em, key);
    }

    public String getUsuarioWebEmisionIndivi(EntityManager em) {
    	PatParametrosPK key = new PatParametrosPK("EINDIVI", "USUARIO_WEB", "EMISION");
    	return getCache(em, key);
    }

    ////////////////////////////////////////////
    ///////////////////////////////////// RCYATE
    public String getPlanesPagoEDepor(EntityManager em) {
    	//'ESOA', 'PLANES', 'PAGO'
        PatParametrosPK key = new PatParametrosPK("EDEPOR", "PLANES", "PAGO");
        return getCache(em, key);
    }

    public String getSucursalEmisionEDepor(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("EDEPOR", "SUCURSAL", "EMISION");
        return getCache(em, key);
    }

    public String getRamoEmisionEDepor(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("EDEPOR", "RAMO", "EMISION");
        return getCache(em, key);
    }
    
    public String getProductoEmisionEDepor(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("EDEPOR", "PRODUCTO", "EMISION");
        return getCache(em, key);
    }

    public String getProductorEmisionEDepor(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("EDEPOR", "PRODUCTOR", "EMISION");
        return getCache(em, key);
    }

    public String getMonedaEmisionEDepor(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("EDEPOR", "MONEDA", "EMISION");
        return getCache(em, key);
    }
    
    public String getTipoCalculoEmisionEDepor(EntityManager em) {
    	PatParametrosPK key = new PatParametrosPK("EDEPOR", "TIPO_CALCULO", "EMISION");
    	return getCache(em, key);
    }

    public String getFormaPagoEmisionEDepor(EntityManager em) {
    	PatParametrosPK key = new PatParametrosPK("EDEPOR", "FORMA_PAGO", "EMISION");
    	return getCache(em, key);
    }

    public String getVigTecnicaEmisionEDepor(EntityManager em) {
    	PatParametrosPK key = new PatParametrosPK("EDEPOR", "VIG_TECNICA", "EMISION");
    	return getCache(em, key);
    }

    public String getMedioPagoEmisionEDepor(EntityManager em) {
    	PatParametrosPK key = new PatParametrosPK("EDEPOR", "MEDIO_PAGO", "EMISION");
    	return getCache(em, key);
    }

    public String getOrigenPagoEmisionEDepor(EntityManager em) {
    	PatParametrosPK key = new PatParametrosPK("EDEPOR", "ORIGEN_PAGO", "EMISION");
    	return getCache(em, key);
    }

    public String getTipoFactEmisionEDepor(EntityManager em) {
    	PatParametrosPK key = new PatParametrosPK("EDEPOR", "TIPO_FACT", "EMISION");
    	return getCache(em, key);
    }

    public String getPromocionEmisionEDepor(EntityManager em) {
    	PatParametrosPK key = new PatParametrosPK("EDEPOR", "PROMOCION", "EMISION");
    	return getCache(em, key);
    }

    public String getRenovacionEmisionEDepor(EntityManager em) {
    	PatParametrosPK key = new PatParametrosPK("EDEPOR", "RENOVACION", "EMISION");
    	return getCache(em, key);
    }

    public String getUsuarioWebEmisionEDepor(EntityManager em) {
    	PatParametrosPK key = new PatParametrosPK("EDEPOR", "USUARIO_WEB", "EMISION");
    	return getCache(em, key);
    }

    public String getDescMonedaEDepor(EntityManager em, String codigoMoneda) {
        PatParametrosPK key = new PatParametrosPK("EDEPOR", "MONEDAS", codigoMoneda);        
        return getCache(em, key);
    }
    ////////////////////////////////////////////
    
    public String getSucursalEmisionViajeros(EntityManager em) {
    	//'EVIAJEROS', 'SUCURSAL', 'EMISION'
        PatParametrosPK key = new PatParametrosPK("EVIAJEROS", "SUCURSAL", "EMISION");
        return getCache(em, key);
    }

    public String getRamoEmisionViajeros(EntityManager em) {
    	//'EVIAJEROS', 'RAMO', 'EMISION'
        PatParametrosPK key = new PatParametrosPK("EVIAJEROS", "RAMO", "EMISION");
        return getCache(em, key);
    }
    
    public String getProductoEmisionViajeros(EntityManager em) {
    	//'EVIAJEROS', 'PRODUCTO', 'EMISION'
        PatParametrosPK key = new PatParametrosPK("EVIAJEROS", "PRODUCTO", "EMISION");
        return getCache(em, key);
    }

    public String getProductorEmisionViajeros(EntityManager em) {
    	//'EVIAJEROS', 'PRODUCTOR', 'EMISION'
        PatParametrosPK key = new PatParametrosPK("EVIAJEROS", "PRODUCTOR", "EMISION");
        return getCache(em, key);
    }

    public String getMonedaEmisionViajeros(EntityManager em) {
    	//'EVIAJEROS', 'MONEDA', 'EMISION'
        PatParametrosPK key = new PatParametrosPK("EVIAJEROS", "MONEDA", "EMISION");
        return getCache(em, key);
    }
    
    public String getTipoCalculoEmisionViajeros(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'TIPO_CALCULO', 'EMISION', 'N');
    	PatParametrosPK key = new PatParametrosPK("EVIAJEROS", "TIPO_CALCULO", "EMISION");
    	return getCache(em, key);
    }

    public String getFormaPagoEmisionViajeros(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'FORMA_PAGO', 'EMISION', 'A');
    	PatParametrosPK key = new PatParametrosPK("EVIAJEROS", "FORMA_PAGO", "EMISION");
    	return getCache(em, key);
    }

    public String getVigTecnicaEmisionViajeros(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'VIG_TECNICA', 'EMISION', '');
    	PatParametrosPK key = new PatParametrosPK("EVIAJEROS", "VIG_TECNICA", "EMISION");
    	return getCache(em, key);
    }

    public String getMedioPagoEmisionViajeros(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'MEDIO_PAGO', 'EMISION', '1');
    	PatParametrosPK key = new PatParametrosPK("EVIAJEROS", "MEDIO_PAGO", "EMISION");
    	return getCache(em, key);
    }

    public String getOrigenPagoEmisionViajeros(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'ORIGEN_PAGO', 'EMISION', 'A');
    	PatParametrosPK key = new PatParametrosPK("EVIAJEROS", "ORIGEN_PAGO", "EMISION");
    	return getCache(em, key);
    }

    public String getTipoFactEmisionViajeros(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'TIPO_FACT', 'EMISION', 'F');
    	PatParametrosPK key = new PatParametrosPK("EVIAJEROS", "TIPO_FACT", "EMISION");
    	return getCache(em, key);
    }

    public String getPromocionEmisionViajeros(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'PROMOCION', 'EMISION', 'NOAP');
    	PatParametrosPK key = new PatParametrosPK("EVIAJEROS", "PROMOCION", "EMISION");
    	return getCache(em, key);
    }

    public String getRenovacionEmisionViajeros(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'RENOVACION', 'EMISION', 'N');
    	PatParametrosPK key = new PatParametrosPK("EVIAJEROS", "RENOVACION", "EMISION");
    	return getCache(em, key);
    }

    public String getUsuarioWebEmisionViajeros(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'USUARIO_WEB', 'EMISION', 'COTIZAWEB');    
    	PatParametrosPK key = new PatParametrosPK("EVIAJEROS", "USUARIO_WEB", "EMISION");
    	return getCache(em, key);
    }
    
    public String getTablaAsistencial(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("PAGOS", "TABLA", "CSM");
        return getCache(em, key);
    }

	public String getTasaMsp(EntityManager em) {
        PatParametrosPK key = new PatParametrosPK("EVIAJEROS", "TASA", "MSP");
        return getCache(em, key);
	}     

// EComercial
    public String getUsuarioNotifPagoEC(EntityManager em) {
    	PatParametrosPK key = new PatParametrosPK("ECOMERCIAL", "NOTIF", "USUARIO");
    	return getCache(em, key);
    }

    public String getClaveNotifPagoEC(EntityManager em) {
    	PatParametrosPK key = new PatParametrosPK("ECOMERCIAL", "NOTIF", "CLAVE");
    	return getCache(em, key);
    }

    public String getAppIdNotifPagoEC(EntityManager em) {
    	PatParametrosPK key = new PatParametrosPK("ECOMERCIAL", "NOTIF", "APPID");
    	return getCache(em, key);
    }

    /////////////////////////////////////////
    /////////// BICIS
    
    public String getSucursalEmisionBici(EntityManager em) {
    	//'ESOA', 'SUCURSAL', 'EMISION'
        PatParametrosPK key = new PatParametrosPK("EBICI", "SUCURSAL", "EMISION");
        return getCache(em, key);
    }

    public String getRamoEmisionBici(EntityManager em) {
    	//'ESOA', 'RAMO', 'EMISION'
        PatParametrosPK key = new PatParametrosPK("EBICI", "RAMO", "EMISION");
        return getCache(em, key);
    }
    
    public String getProductoEmisionBici(EntityManager em) {
    	//'ESOA', 'PRODUCTO', 'EMISION'
        PatParametrosPK key = new PatParametrosPK("EBICI", "PRODUCTO", "EMISION");
        return getCache(em, key);
    }

    public String getProductorEmisionBici(EntityManager em) {
    	//'ESOA', 'PRODUCTOR', 'EMISION'
        PatParametrosPK key = new PatParametrosPK("EBICI", "PRODUCTOR", "EMISION");
        return getCache(em, key);
    }

    public String getMonedaEmisionBici(EntityManager em) {
    	//'ESOA', 'MONEDA', 'EMISION'
        PatParametrosPK key = new PatParametrosPK("EBICI", "MONEDA", "EMISION");
        return getCache(em, key);
    }
    
    public String getTipoCalculoEmisionBici(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'TIPO_CALCULO', 'EMISION', 'N');
    	PatParametrosPK key = new PatParametrosPK("EBICI", "TIPO_CALCULO", "EMISION");
    	return getCache(em, key);
    }

    public String getFormaPagoEmisionBici(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'FORMA_PAGO', 'EMISION', 'A');
    	PatParametrosPK key = new PatParametrosPK("EBICI", "FORMA_PAGO", "EMISION");
    	return getCache(em, key);
    }

    public String getVigTecnicaEmisionBici(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'VIG_TECNICA', 'EMISION', '');
    	PatParametrosPK key = new PatParametrosPK("EBICI", "VIG_TECNICA", "EMISION");
    	return getCache(em, key);
    }

    public String getMedioPagoEmisionBici(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'MEDIO_PAGO', 'EMISION', '1');
    	PatParametrosPK key = new PatParametrosPK("EBICI", "MEDIO_PAGO", "EMISION");
    	return getCache(em, key);
    }

    public String getOrigenPagoEmisionBici(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'ORIGEN_PAGO', 'EMISION', 'A');
    	PatParametrosPK key = new PatParametrosPK("EBICI", "ORIGEN_PAGO", "EMISION");
    	return getCache(em, key);
    }

    public String getTipoFactEmisionBici(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'TIPO_FACT', 'EMISION', 'F');
    	PatParametrosPK key = new PatParametrosPK("EBICI", "TIPO_FACT", "EMISION");
    	return getCache(em, key);
    }

    public String getPromocionEmisionBici(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'PROMOCION', 'EMISION', 'NOAP');
    	PatParametrosPK key = new PatParametrosPK("EBICI", "PROMOCION", "EMISION");
    	return getCache(em, key);
    }

    public String getRenovacionEmisionBici(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'RENOVACION', 'EMISION', 'N');
    	PatParametrosPK key = new PatParametrosPK("EBICI", "RENOVACION", "EMISION");
    	return getCache(em, key);
    }

    public String getUsuarioWebEmisionBici(EntityManager em) {
    	//insert into pat_parametros values ('ESOA', 'USUARIO_WEB', 'EMISION', 'COTIZAWEB');    
    	PatParametrosPK key = new PatParametrosPK("EBICI", "USUARIO_WEB", "EMISION");
    	return getCache(em, key);
    }

    
}
