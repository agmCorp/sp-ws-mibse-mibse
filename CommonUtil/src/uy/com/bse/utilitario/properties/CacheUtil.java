package uy.com.bse.utilitario.properties;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class CacheUtil {

	final static Logger logger = LogManager.getLogger(CacheUtil.class);
	static Map<String, Map<String, Object>> myMap = new HashMap<String, Map<String, Object>>();

	public static String getKey(Class<?> claseOrigen, String key) {
		return new String(claseOrigen+"&"+key+"");
	}

	public static Object getObject(String myJNDIname, String keyCompleta) {
		Object salida = null;
		try {
	    	Map<String, Object> cacheObject = getInstance(myJNDIname);
	    	if (cacheObject != null) {
	    		salida = cacheObject.get(keyCompleta);
	    		//logger.debug("getObject - Devolviendo objeto ["+salida+"], con key["+keyCompleta+"] desde cache: "+myJNDIname);
	    	} 
		} catch (Exception e) {
			logger.error("getObject - Al obtener objeto del cache: " + e);
		}
    	return salida;
	}
	
	private static Map<String, Object> getInstance(String myJNDIname)  {
		Map<String, Object> instance = myMap.get(myJNDIname);
		if (instance == null) {
			instance = buildInstance(myJNDIname);
		}
		return instance;
	}
	private static synchronized Map<String, Object> buildInstance(String myJNDIname) {
		Map<String, Object> instance = myMap.get(myJNDIname);
		if (instance == null) {
			instance = new HashMap<String, Object>();
    		myMap.put(myJNDIname, instance);
		}
		return instance;
	}

	public static void put(String myJNDIname, Object objeto) {
		put(myJNDIname, "&", objeto);
	}
	public static void put(String myJNDIname, String keyCompleta, Object objeto) {
		try {
			Map<String, Object> cacheObject = getInstance(myJNDIname);
	    	if (cacheObject != null) {
	    		cacheObject.put(keyCompleta, objeto);
	    		//logger.debug("put - Poniendo objeto ["+objeto+"], con key["+keyCompleta+"], en cache: "+myJNDIname);
	    	} 
		} catch (Exception e) {
			logger.error("put - Al poner objeto del cache: " + e);
		}
	}

}
