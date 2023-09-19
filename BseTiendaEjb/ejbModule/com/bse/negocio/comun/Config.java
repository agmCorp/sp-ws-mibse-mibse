package com.bse.negocio.comun;

import java.io.IOException;
import java.io.InputStream;
import java.util.MissingResourceException;
import java.util.Properties;

public class Config {

    private Config() {}

    //  private static final Properties exception_properties = loadProperties("", "BSEDescentralizadoConfig.properties");
    private static final Properties exception_properties = loadProperties("", "BSEDescentralizado_es_UY.properties");

    public static String getString(String key) {
        try {
            return exception_properties.getProperty(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }
/*---------------LOAD PROPERTIES -----------------------*/

    public static Properties loadProperties(String MODULE_BASE_DIR, String fileName) {
        Properties prop = new Properties();
        InputStream im = null;
        try {
            im = findInClasspath( fileName);
            prop.load(im);
        } catch (Exception ignore) {

        } finally {
            if (im != null) {
                try {
                    im.close();
                } catch (IOException ignore) {

                }
            }
        }
        return prop;
    }






    public static   InputStream findInClasspath(String fileName) {
       //InputStream is = Config.class.getClassLoader().getResourceAsStream(fileName);
       //return is;
        return Config.class.getClassLoader().getResourceAsStream(fileName);

    }


    /*---------------END LOAD PROPERTIES -----------------------*/


}
