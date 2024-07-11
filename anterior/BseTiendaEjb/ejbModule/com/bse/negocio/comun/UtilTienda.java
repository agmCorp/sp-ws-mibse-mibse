package com.bse.negocio.comun;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UtilTienda {

    
    public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
        List<T> r = new ArrayList<T>(c.size());
        for(Object o: c)
          r.add(clazz.cast(o));
        return r;
    }

    public static String getClobString(Clob clob) throws SQLException, IOException {
        BufferedReader stringReader = new BufferedReader(clob.getCharacterStream());
        String singleLine = null;
        StringBuffer strBuff = new StringBuffer();
        while ((singleLine = stringReader.readLine()) != null) {
            strBuff.append(singleLine.trim() + " ");
        }
        return strBuff.toString();
    }
    
}
