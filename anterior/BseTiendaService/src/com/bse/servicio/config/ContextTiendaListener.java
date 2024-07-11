package com.bse.servicio.config;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//import java.io.File;
//import javax.servlet.ServletContext;
//import org.apache.log4j.PropertyConfigurator;

public class ContextTiendaListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        
        // Obtener vble que indica directorio de logueo del servidor jboss. 
        String logDir = System.getProperty("jboss.server.log.dir");
        
        if ( (logDir == null) || (logDir.equals("")) ) {
            System.out.println("TIENDA - NO ESTABLECE ARCHIVO PROPIO de LOGS - "
                              + "VARIABLE de ENTORNO [jboss.server.log.dir] no encontrada.");
        } else {
            System.out.println("TIENDA - VARIABLE de ENTORNO [jboss.server.log.dir]=[" + logDir + "]");

            System.setProperty("tienda.log.path", logDir);

            /*ServletContext context = event.getServletContext();
            String log4jConfigFile = context.getInitParameter("log4j-config-location");
            String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;
            PropertyConfigurator.configure(fullPath);*/
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // Auto-generated method stub
    }
}
