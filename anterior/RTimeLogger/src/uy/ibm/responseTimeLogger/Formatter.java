package uy.ibm.responseTimeLogger;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** 
 * IBM Uruguay
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Formatter extends RuntimeException  {

	private static final long serialVersionUID = 1L;
	public static final int DEFAULT = 0;
    public static final int XLS = 1;
    
    private static final String FIELD_SEPARATOR = " ";
    private static final String XLS_FIELD_SEPARATOR = " ";

    private static Logger nfrLogger = LogManager.getLogger("NFRLogger");
    
    public Formatter() {
    }
    
    public static String format (GregorianCalendar gc, String componentId, String transactionId, String trasnactionType, long timeStamp, String[] customData, int formato) {
        String outstring = null;
        try {
            if (formato == XLS) {
                outstring = formatXLS(gc, componentId, transactionId, trasnactionType, timeStamp, customData);
            } else {
                outstring = formatDefault(gc, componentId, transactionId, trasnactionType, timeStamp, customData);
            }
        } catch (Exception e) {
        	nfrLogger.error("Formatter - format - trasnactionType:"+trasnactionType+" transactionId:"+transactionId+"PerformanceLogger could not format string",e);
		}
        return outstring;
    }
    
    private static String complete(String f) {
        if (f.length() == 1) {
            return new String("0"+f);
        } else if (f.length() == 0) {
            return "00";
        }
        return f;
    }
    private static String getYearString(GregorianCalendar gc) {
        String sm = Integer.toString(gc.get(Calendar.YEAR));
        return complete(sm);
    }
    private static String getMonthString(GregorianCalendar gc) {
        int rm = gc.get(Calendar.MONTH) + 1;
        String sm = Integer.toString(rm);
        return complete(sm);
    }
    private static String getDayString(GregorianCalendar gc) {
        String sm = Integer.toString(gc.get(Calendar.DATE));
        return complete(sm);
    }
    private static String getDateString(GregorianCalendar gc) {
        return new String(getYearString(gc) + getMonthString(gc) + getDayString(gc));
    }
    
    private static String getHourString(GregorianCalendar gc) {
        String sm = Integer.toString(gc.get(Calendar.HOUR_OF_DAY));
        return complete(sm);
    }

    private static String getMinuteString(GregorianCalendar gc) {
        String sm = Integer.toString(gc.get(Calendar.MINUTE));
        return complete(sm);
    }

    private static String getSecondString(GregorianCalendar gc) {
        String sm = Integer.toString(gc.get(Calendar.SECOND));
        return complete(sm);
    }
    private static String getHour24(GregorianCalendar gc) {
        return new String(getHourString(gc) + getMinuteString(gc) + getSecondString(gc));
    }
    
    private static String formatDefault (GregorianCalendar gc, String componentId, String transactionId, String trasnactionType, long timeStamp, String[] customData) {
        String outstring = null;
		try {
			StringBuffer logLineBuffer = new StringBuffer();
            logLineBuffer.append(FIELD_SEPARATOR);
			String fecha = getDateString(gc);
			logLineBuffer.append(fecha);
            logLineBuffer.append(FIELD_SEPARATOR);
			String hora = getHour24(gc);
			logLineBuffer.append(hora);
            logLineBuffer.append(FIELD_SEPARATOR);
			logLineBuffer.append(trasnactionType);
            logLineBuffer.append(FIELD_SEPARATOR);
			logLineBuffer.append(transactionId);
            logLineBuffer.append(FIELD_SEPARATOR);
			logLineBuffer.append(componentId);
            logLineBuffer.append(FIELD_SEPARATOR);      
			logLineBuffer.append(timeStamp);
            logLineBuffer.append(FIELD_SEPARATOR);      
			logLineBuffer.append(buildString(customData));
            outstring = logLineBuffer.toString();
        } catch (Exception e) {
        	nfrLogger.error("Formatter - formatDefault - trasnactionType:"+trasnactionType+" transactionId:"+transactionId+"PerformanceLogger could not log in disc",e);
		}
        return outstring;
    }

    private static String formatXLS (GregorianCalendar gc, String componentId, String transactionId, String trasnactionType, long timeStamp, String[] customData) {
        String outstring = null;
		try {
			StringBuffer logLineBuffer = new StringBuffer();
      logLineBuffer.append(XLS_FIELD_SEPARATOR);
			String fecha = new String (gc.get(Calendar.YEAR) + "/" + gc.get(Calendar.MONTH) + "/" + gc.get(Calendar.DATE));
			logLineBuffer.append(fecha);
      logLineBuffer.append(XLS_FIELD_SEPARATOR);
			String hora = new String (gc.get(Calendar.HOUR) + ":" + gc.get(Calendar.MINUTE) + ":" + gc.get(Calendar.SECOND));
			logLineBuffer.append(hora);
      logLineBuffer.append(XLS_FIELD_SEPARATOR);
			logLineBuffer.append(trasnactionType);
      logLineBuffer.append(XLS_FIELD_SEPARATOR);
			logLineBuffer.append(transactionId);
      logLineBuffer.append(XLS_FIELD_SEPARATOR);
			logLineBuffer.append(componentId);
      logLineBuffer.append(XLS_FIELD_SEPARATOR);      
			logLineBuffer.append(timeStamp);
	 logLineBuffer.append(XLS_FIELD_SEPARATOR);      
			logLineBuffer.append(buildString(customData));
            outstring = logLineBuffer.toString();
        } catch (Exception e) {
        	nfrLogger.error("Formatter - formatXLS - trasnactionType:"+trasnactionType+" transactionId:"+transactionId+"PerformanceLogger could not log in disc",e);
		}
        return outstring;
    }
    
    private static String buildString (String[] customData) {
    	String outstring = "";
    	if (customData!=null) {
    		int x = customData.length;
			if (x>0) {
				StringBuffer temp = new StringBuffer("{");
				for (int i = 0; i < customData.length; i++) {
					String string = customData[i];
					temp.append(string);
					temp.append(";");
				}
				temp.setCharAt(temp.length()-1, '}');
	    		outstring = temp.toString();
			} else {
				outstring = "{}";
			}
    	}
    	return outstring;
    }
    

}