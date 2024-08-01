/*
 * Created on Nov 8, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package uy.ibm.responseTimeLogger;
import java.util.GregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/** 
 * IBM Uruguay
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public final class RTimeLogger {
	private static Logger logger = null;
	
	private static Logger nfrLogger = LogManager.getLogger("RTimeLoggerAppender");
	private final static Logger getLogger() {
		if (logger==null) {
			logger = LogManager.getLogger(RTimeLogger.class);
		}
		return logger;
	}
    
	public static void initContext(String transactionId, String transactionType, boolean mustCreate) {
		try {
			if (mustCreate) {
				nfrLogger.debug("initPLCtx: "+ transactionId+ " - "+ transactionType);
				TransactionContext.set(transactionId, transactionType);
			} else {
				// solamente se inicializa un contexto si antes no fue inicializado.
				if (!TransactionContext.get().isInitiated()) {
					nfrLogger.debug("initPLCtx isInitiated = false :"+ transactionId+ " - "+ transactionType);
					TransactionContext.set(transactionId, transactionType);
				} else {// else si ya existia un contexto no se inicializa nada
					nfrLogger.debug("initPLCtx isInitiated = true :"+ transactionId+ " - "+ transactionType + " plCtx: ");
				}
			}
		} catch (Exception e) {
			nfrLogger.error("initContext - transactionType:"+ transactionType+ " No se pudo inicializar el contexto del PerformanceLogger."	+ e.getMessage());
		}
	}
	public static void resetContext(){
		TransactionContext.reset();
	}
	public static void initContext(String transactionId, String transactionType) {
		initContext(transactionId,transactionType,false);
	}
	
	public static void initContext(String transactionType, boolean mustCreate) {
		long timeStampLog = System.currentTimeMillis();
		String hostName = getHostName();
		String transactionId = new String (hostName + "-" + timeStampLog);
      	initContext(transactionId, transactionType, mustCreate);
	}

	public static void initContext(String transactionType) {
		initContext(transactionType, false);
	}
	
	public static void registerStart(String idLogger) {		
		register(idLogger, TransactionContext.START);
	}
	
	public static void registerStop(String idLogger) {
		register(idLogger, TransactionContext.STOP);
	}


	public static void registerStart(Class<?> component) {
		Class<?> classComponent = component;
		register(classComponent, TransactionContext.START);
	}

	
	public static void  registerStop(Class<?> component) {
		Class<?> classComponent = component;
		register(classComponent, TransactionContext.STOP);
	}
	
	private static void register (String idLogger, int event) {
		try {
			log( idLogger, event);
		} catch (Exception e) {
			nfrLogger.error("register - idLogger:"+idLogger+" event:"+event+":" + e.getMessage());
		}
	}
	
	private static void register (Class<?> component, int event) {
		register(component.getName(), event);
	}
	

	protected static void log( String idLogger, int event) {
		try {
			String transactionType = TransactionContext.get().getTransactionType();            
			GregorianCalendar gc = new GregorianCalendar();
			String transactionId = TransactionContext.get().getTransactionId();
			String[] customData = TransactionContext.get().getCustomData();
			long timeStamp = TransactionContext.get().refreshMap(idLogger, transactionId, transactionType, event);
            if (event == TransactionContext.STOP) {
                logInFile(gc, idLogger, transactionId, transactionType, timeStamp, customData);
            }
		} catch (Exception e) {			
			nfrLogger.error("log - idLogger:"+idLogger+" event:"+event+":" + e.getMessage());
		}
	}

  
	/**
	 * @param gc
	 * @param componentId
	 * @param transactionId
	 * @param trasnactionType
	 * @param event
	 */
	private static void logInFile(GregorianCalendar gc, String componentId, String transactionId, String trasnactionType, long timeStamp, String[] customData) {
		try {
			int formato = Formatter.DEFAULT;
			String logline = Formatter.format(gc, componentId, transactionId, trasnactionType, timeStamp, customData, formato);
			getLogger().info(logline);
		} catch (Exception e) {
			nfrLogger.error("logInFile - componentId:"+componentId+" transactionId:"+transactionId+" trasnactionType:"+trasnactionType+":"+e.getMessage());
		}
	}
    
	private static final String getHostName() {
		String hn = "localhost";
		try {
			java.net.InetAddress ia = java.net.InetAddress.getLocalHost();
			String ipAdress = ia.getHostAddress();
			String hostName = ia.getHostName();
			hn = hostName + "(" + ipAdress + ")";
		} catch (Exception e) {
			nfrLogger.error("getHostName - could not load hostname." + e.getMessage());
		}
		return hn;
	}

	public static void addCustomData(String key, String aCustomData) {
		try {
			TransactionContext.addCustomData(key, aCustomData);
		} catch (Exception e) {
			nfrLogger.error("addCustomData - key:"+key+" aCustomData:"+aCustomData+":"+e.getMessage());
		}
	}
}
