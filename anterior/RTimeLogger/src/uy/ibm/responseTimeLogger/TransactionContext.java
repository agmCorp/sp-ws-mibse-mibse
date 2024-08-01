/*
 * Created on Nov 8, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package uy.ibm.responseTimeLogger;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * IBM Uruguay
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public final class TransactionContext implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int START = 0;
	static final int STOP = 1;
	private static Logger myLogger = LogManager.getLogger("NFRLogger");
	private static final ThreadLocal<TransactionContext> myContext = new ThreadLocal<TransactionContext>(){
		
		protected TransactionContext initialValue() {
			return new TransactionContext();
		}
	};
	
	String transactionType = null;
	String transactionId = null;
	Map<String, List<String>> transactionCustomData = null;

	
	private Map<String, Long> mapLogs = null;
	private Map<String, Integer> mapComponentCount = null;
	
	private final Map<String, Long> getMap() {
		if (mapLogs==null) {
			mapLogs = new Hashtable<String, Long>();
		}
		return mapLogs;
	}

	private final Map<String, Integer> getMapComponentCount() {
		if (mapComponentCount==null) {
			mapComponentCount = new Hashtable<String, Integer>();
		}
		return mapComponentCount;
	}

		/**
	 * @param gc
	 * @param componentId
	 * @param transactionId
	 * @param trasnactionType
	 * @param event
	 */
	long refreshMap(String componentId, String transactionId, String transactionType, int event) {
		long timeStamp = -1;
		try {
			StringBuffer keyBefore = new StringBuffer(componentId);
			keyBefore.append(transactionId);
			keyBefore.append(transactionType);
			String key = keyBefore.toString();
			Integer componentCount = (Integer) getMapComponentCount().get(key);
			int componentCountInt = 0;
			if (componentCount != null)   {
				//Component has been registered previously
				componentCountInt = componentCount.intValue();
			}
			if (event == START) {
				long timeStart = System.currentTimeMillis();
				Long lt = new Long(timeStart);
				componentCountInt++;
				Integer newComponentCount = new Integer(componentCountInt);
				//Registro la nueva cantidad para el componente
				getMapComponentCount().put(key,newComponentCount);
				keyBefore.append(newComponentCount);
				key = keyBefore.toString();
				getMap().put(key, lt);          
			} else {
				if (event == STOP) {
					componentCount = new Integer(componentCountInt);
					//Me armo un nuevo valor para el componentCount con un n�mero menos
					componentCountInt--;
					Integer newComponentCount = new Integer(componentCountInt);
					//Registro el nuevo valor para componentCount
					getMapComponentCount().put(key,newComponentCount);
            
					//Me armo la misma clave que us� para el �ltimo elemento de la pila
					keyBefore.append(componentCount);
					key = keyBefore.toString();
					timeStamp = timeCalculate(key);          
					getMap().remove(key);
				}
			}
		} catch (Exception e) {
			myLogger.error("refreshMap - componentId:"+componentId+" transactionId:"+transactionId+" transactionType:"+transactionType+":" + e.getMessage());
		}
		return timeStamp;
	}

	/**
	 * @param key
	 * @return
	 */
	private long timeCalculate(String key) {
		long timeStamp = -1;
		try {
			Long lt = (Long)mapLogs.get(key);
            if (lt!=null) {
                long timeStart = lt.longValue();
                long timeStop = System.currentTimeMillis();
                timeStamp = timeStop-timeStart;
            }
		} catch (Exception e) {
			myLogger.error("timeCalculate - key:"+key+":"+ e.getMessage());
		}
		return timeStamp;
		
	}

	public static final TransactionContext get(){
		return myContext.get();
	}
	

	static final void set(String tId, String tType){
		myContext.set(new TransactionContext(tId, tType));
	}

	
	/**
	 * 
	 */
	private TransactionContext() {
		super();
	}
		
	private TransactionContext(String tId, String tType) {
		super();
		setTransactionId(tId);
		setTransactionType(tType);
	}

	/**
	 * @return
	 */
	public String getTransactionType() {
		return transactionType;
	}

	/**
	 * @param string
	 */
	public void setTransactionType(String string) {
		transactionType = string;
	}

	/**
	 * @return
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * @param string
	 */
	public void setTransactionId(String string) {
		transactionId = string;
	}

	public boolean isInitiated() {
		return ( getTransactionId()!=null && getTransactionType()!=null );
	}

	public static void reset() {
		myContext.set(new TransactionContext());
		
	}

	public String[] getCustomData() {
		String[] customData = {};
		if (transactionCustomData != null) {
			int size = transactionCustomData.size();
			if (size >0) {
				customData = new String[size];
				int i =0;
				Set<Entry<String, List<String>>> myentries = transactionCustomData.entrySet();
				for (Entry<String, List<String>> entry : myentries) {
					customData[i++]=entry.getKey()+":"+getString(entry.getValue());
				}
			}
		}
		return customData;
	}

	private String getString(List<String> value) {
		String outString = "";
		if (value!=null) {
			outString = "<>";
			int x = value.size();
			if (x>0) {
				StringBuffer buf = new StringBuffer("<");
				for (Iterator iterator = value.iterator(); iterator.hasNext();) {
					String string = (String) iterator.next();
					buf.append(string+"|");						
				}
				buf.deleteCharAt(buf.length()-1);
				outString = buf.toString()+">";
			}
		}
		return outString;
	}

	static final void addCustomData(String key, String aCustomData) {
		TransactionContext ctx = myContext.get();
		if (ctx.transactionCustomData == null) {
			ctx.transactionCustomData = new HashMap<String,List<String>>();
		}
		List<String> listString = ctx.transactionCustomData.get(key);
		if (listString==null) {
			listString = new ArrayList<String>();
			ctx.transactionCustomData.put(key, listString);
		}
		listString.add(aCustomData);
	}

}
