package com.bse.servicio.config;

import java.util.Locale;
import java.util.ResourceBundle;

public class Parameters {

public static String WSDL_UA = "wsdlUA";
public static String USUARIO_UA = "usuarioUA";
public static String CONTRASENA_UA = "contrasenaUA";
public static String USE_PROXY = "useProxy";
public static String PROXY_HOST = "proxyHost";
public static String PROXY_PORT = "proxyPort";	

public static String USUARIO_CELULAR_BSE = "usuarioCelularBSE";
public static String CONTRASENA_CELULAR_BSE = "contrasenaCelularBSE";

public static 
String getParameter(String code) { 
    Locale idioma = new Locale("es", "UY");
    String val = ResourceBundle.getBundle("Parameters", idioma).getString(code);
    
    //System.out.println("Buscando " + code + " valor = " + val);
    
    return val;
}
    
}
