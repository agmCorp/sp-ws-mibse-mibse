package uy.com.bse.utilitario.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.utilitario.log.Logueo;

public class PropertiesManager {
	private static final Logger LOG = LogManager.getLogger(PropertiesManager.class);
	private String nombreArchivo;
	private Properties prop;
	
	/**
	 * Establece el nombre del archivo properties con el cual trabajar
	 * @param nombre
	 */
	public void setNombreArchivo(String nombre){
		this.nombreArchivo = nombre;
		this.prop = this.cargarArchivoProperties();
	}
	
	/**
	 * Abre el archivo properties y lo carga en memoria
	 * @return Un objeto properties o null en caso de fallar
	 */
	private Properties cargarArchivoProperties(){
		InputStream input = null;		
		Properties prop = new Properties();
		final Logueo errorGenerado = new Logueo();
		
		try{
			
			final String Archivo = this.nombreArchivo;
			
			errorGenerado.setEncabezado("-Cargar Recursos-");
			errorGenerado.setClase(PropertiesManager.class);
			errorGenerado.setMetodo("cargarArchivoProperties");
			errorGenerado.setParametro("Archivo", Archivo);

			input = PropertiesManager.class.getClassLoader().getResourceAsStream(Archivo);
			if (input != null) {
				prop.load(input);
			} else {
				prop = null;
				errorGenerado.setError("No se pudo encontrar el archivo");			
				LOG.error(errorGenerado.getMensaje());
			}
		}catch (IOException e) {
			prop = null;
			errorGenerado.setError(e.getMessage());			
			LOG.error(errorGenerado.getMensaje());
		} catch (Exception ex){
			prop = null;
			errorGenerado.setError(ex.getMessage());			
			LOG.error(errorGenerado.getMensaje());
		}
		
		return prop;
	}
	
	/**
	 * Carga el valor del archivo properties correspondiente a la clave recibida
	 * @param clave
	 * @return String con el valor correspondiente a la clave o null en caso de que no exista
	 */
	public String obtenerValor(final String clave) {
		String retorno = null;
		final Logueo errorGenerado = new Logueo();
		
		try{
			errorGenerado.setEncabezado("Error al obtener valor");
			errorGenerado.setClase(PropertiesManager.class);
			errorGenerado.setMetodo("obtenerValor");
			
			if(this.prop == null){
				errorGenerado.setError("No hay archivo properties para cargar el valor");
				LOG.error(errorGenerado.getMensaje());
			}else{
				retorno = prop.getProperty(clave);
			}	
		}catch(Exception ex){
			errorGenerado.setError(ex.getMessage());
			LOG.error(errorGenerado.getMensaje());
		}
		
		return retorno;
	}
	
	/**
	 * Carga el valor del archivo properties correspondiente a la clave recibida
	 * @param clave
	 * @return String con el valor correspondiente a la clave o el valor por defecto 
	 * en caso de que lo obtenido no sea un valor v\u00e1lido
	 */	
	public Integer obtenerValor(final Integer valorDefecto, final String clave) {
		Integer retorno = null;
		final Logueo errorGenerado = new Logueo();
		
		try{
			errorGenerado.setEncabezado("Error al obtener valor");
			errorGenerado.setClase(PropertiesManager.class);
			errorGenerado.setMetodo("obtenerValor");
			
			if(this.prop == null){
				errorGenerado.setError("No hay archivo properties para cargar el valor");
				LOG.error(errorGenerado.getMensaje());
				retorno = valorDefecto;				
			}else{
				final String valor = prop.getProperty(clave);
				retorno = this.checkValorPropertiesNumerico(valorDefecto, valor);
			}	
		}catch(Exception ex){
			errorGenerado.setError(ex.getMessage());
			LOG.error(errorGenerado.getMensaje());
		}
		
		return retorno;
	}
	
	/**
	 * Carga el valor del archivo properties correspondiente a la clave recibida
	 * @param clave
	 * @return String con el valor correspondiente a la clave o null en caso de que no exista
	 */
	public String obtenerValor(final String valorDefecto, final String clave) {
		String retorno = valorDefecto;
		final Logueo errorGenerado = new Logueo();
		
		try{
			errorGenerado.setEncabezado("Error al obtener valor");
			errorGenerado.setClase(PropertiesManager.class);
			errorGenerado.setMetodo("obtenerValor");
			
			if(this.prop == null){
				errorGenerado.setError("No hay archivo properties para cargar el valor");
				LOG.error(errorGenerado.getMensaje());				
			}else{				
				retorno = this.checkValorPropertiesString(valorDefecto, prop.getProperty(clave));
			}	
		}catch(Exception ex){
			errorGenerado.setError(ex.getMessage());
			LOG.error(errorGenerado.getMensaje());
		}
		
		return retorno;
	}
	
	
	
	
	/**
	 * Comprueba que el string recibido sea numerico y pueda ser casteado utilizando expresiones regulares 
	 * @param textoComparar
	 * @return
	 */
	private boolean isNumeric(final String textoComparar) {  
	    return textoComparar.matches("[-+]?\\d*\\.?\\d+");  
	}
	
	/**
	 * Comprueba que el valor recibido de un properties es numerico, comprueba que no sea null ni vacio
	 * Asume que el parametro Integer es un entero.
	 * @param valorDefecto
	 * @param valorComprobar
	 * @return El valor por defecto que es el primer parametro recibido
	 */
	private Integer checkValorPropertiesNumerico(final Integer valorDefecto, final String valorComprobar){
		Integer codigo = Integer.valueOf(valorDefecto);
		
		if(valorComprobar != null && this.isNumeric(valorComprobar)){
			codigo = Integer.valueOf(valorComprobar);
		}
		
		return codigo;
	}
	
	
	/**
	 * Comprueba que el valor recibido de un properties no sea null ni vacio
	 * @param valorDefecto
	 * @param valorComprobar
	 * @return El valor por defecto que es el primer parametro recibido
	 */
	private String checkValorPropertiesString(final String valorDefecto, final String valorComprobar){
		String descripcion = valorDefecto;
		
		if(valorComprobar != null && !valorComprobar.equals("")){
			descripcion = valorComprobar;
		}
		
		return descripcion;
	}
}
