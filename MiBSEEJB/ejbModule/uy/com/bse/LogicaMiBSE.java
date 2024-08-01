package uy.com.bse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.dato.ResultGenerico;
import uy.com.bse.utilitario.excepcion.ErrorHandler;
import uy.com.bse.utilitario.logica.LogicaSolver;
import uy.ibm.responseTimeLogger.RTimeLogger;

public final class LogicaMiBSE {

	private static Logger myLogger = LogManager.getLogger(LogicaMiBSE.class);
	private static Properties mappers = null;
	private static Map<String, Class> myClassMappers = new HashMap<String, Class>();
	private static final String FILE = "/logicaMiBSEMappers.properties";

	private static LogicaSolver getMapperNewInstance(String param) {
		LogicaSolver solver = null;
		if (mappers == null) {
			buildMapper();
		}
		
		String msgError=null;
		if (myClassMappers == null) {
			msgError = "No pude cargar el hash de mappers!";
			myLogger.error(msgError);
			ErrorHandler.handle(LogicaSolver.class, "getMapperNewInstance");
		} else {
			Class myClass = myClassMappers.get(param);
			if (myClass == null) {
				msgError = "No pude encontrar mapper para param:" + param;
				myLogger.error(msgError);
				ErrorHandler.handle(LogicaSolver.class, "getMapperNewInstance");
			} else try {
				solver = (LogicaSolver)myClass.newInstance();
			} catch (Exception e) {
				String msg = "No pude construir instancia para clase: " + myClass;
				myLogger.error(msg);
				ErrorHandler.handle(LogicaSolver.class, "getMapperNewInstance");
			}
		}
		
		return solver;
	}
	
	private static synchronized void buildMapper() {
		String msgError  = null;
		if (mappers == null) {
			mappers = new Properties();
			InputStream is = null;
			try {
				is = LogicaMiBSE.class.getResourceAsStream(FILE);
				mappers.load(is);
				
				if (mappers == null) {
					msgError = "No pude encontrar archivo " + FILE;
					myLogger.error(msgError);
					ErrorHandler.handle(LogicaSolver.class, "buildMapper");
				} else {
					myLogger.info("Archivo properties cargado : " + FILE);
					Enumeration mapkeys = mappers.keys();
					while (mapkeys.hasMoreElements()) {
						String oneParamClass = (String) mapkeys.nextElement();
						String mapperClass = (String)mappers.get(oneParamClass);
						myLogger.info("Mapper para [" + oneParamClass + "] = [" + mapperClass + "].");
						try {
							Class newClass = Class.forName(mapperClass);
							myClassMappers.put(oneParamClass, Class.forName(mapperClass));
							myLogger.info("Mapper Class cargada :" + newClass);
						} catch (ClassNotFoundException cnfe) {
							msgError = "No pude cargar clase: " + mapperClass;
							myLogger.error(msgError);
						}
					}
				}
			} catch (IOException e) {
				msgError = "No pude cargar archivo " + FILE + ", error: " + e.getMessage();
				myLogger.error(msgError);
				throw new RuntimeException(msgError);
			} finally {
				if (is!=null) {
					try {
						is.close();
					} catch (IOException e) {
						msgError = "No pude cerrar stream: " + is + ", error: " + e.getMessage();
						myLogger.error(msgError);
					}
				}
				
			}
			
		}
	
	}

	public static ResultGenerico solve(ParamGenerico param) {
		ResultGenerico result = null;
		if (param != null) {
			String paramClass = param.getClass().getName();

			RTimeLogger.registerStart(paramClass);
			RTimeLogger.addCustomData("user", param.getUsuario());
			try {
				LogicaSolver solver = getMapperNewInstance(paramClass);
				result = solver.solve(param);
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

}