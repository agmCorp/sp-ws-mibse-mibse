package uy.com.bse.utilitario.properties;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public final class CachedPropertiesReader {
	private static Logger logger = LogManager.getLogger(CachedPropertiesReader.class);

	private static final String JNDI_PROPERTIES_READER ="BSECommonUtilitario/cache/Properties";

	public static Properties getProperties(String propertiesName) {
		String keyCompleta = CacheUtil.getKey(CachedPropertiesReader.class, propertiesName);
		Properties properties = (Properties)CacheUtil.getObject(JNDI_PROPERTIES_READER, keyCompleta);
		if (properties == null) {
			properties = buildProperties(propertiesName, keyCompleta);
		}
		return properties;
	}
	
	private static Properties buildProperties(String propertiesName, String keyCompleta) {
		Properties properties = null;
		synchronized(propertiesName) {
			logger.info("buildProperties - Construyendo properties: " + propertiesName);
			properties = (Properties)CacheUtil.getObject(JNDI_PROPERTIES_READER, keyCompleta);
			if (properties == null) {
				InputStream inputStream = null;
				try {
					properties = new Properties();
					inputStream = CachedPropertiesReader.class.getResourceAsStream(propertiesName);
					if (inputStream != null) {
						properties.load(inputStream);
						CacheUtil.put(JNDI_PROPERTIES_READER, keyCompleta, properties);
					}
				} catch (Exception e) {
					logger.error("buildProperties - No se puede cargar properties para [" + propertiesName + "]:"+e);
				} finally {
					if (inputStream != null) {
						try {
							inputStream.close();
						} catch (Exception e) {
							logger.error("buildProperties - Al cerrar el inpuStrem para [" + propertiesName + "]:"+e);
						}
					}
				}
			}
			logger.info("buildProperties - Construyendo properties: [" +propertiesName+"] - fin synchronized");
		}
		return properties;
	}

	public static String[] getPropertyKeys(String propertiesName) {
		String[] salida = null;
		Properties prop = getProperties(propertiesName);
		if (prop != null) {
			salida = new String[prop.size()];
			Enumeration<Object> keys = prop.keys();
			int i=0;
			while (keys.hasMoreElements()) {
				String aKey = (String)keys.nextElement();
				salida[i++] = aKey;
			}
		} else {
			logger.error("getPropertyKeys - No existe la properties para :" + propertiesName);
		}
		return salida;
	}
	
	public static String getPropertyValue(String propertiesName, String key) {
		String salida = null;
		Properties prop = getProperties(propertiesName);
		if (prop != null) {
			salida = prop.getProperty(key);
		} else {
			logger.error("getPropertyValue - No existe la properties para :" + propertiesName);
		}
		return salida;
	}


}
