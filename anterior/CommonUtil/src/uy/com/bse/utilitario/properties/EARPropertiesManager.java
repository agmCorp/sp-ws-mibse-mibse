package uy.com.bse.utilitario.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.utilitario.log.Logueo;
/**
 * 
 * Clase para cargar properties desde el EAR. Su objetivo fue que se carguen las properties de afuera del codigo fuente
 * en una localizacion externa. Busca en la configuracion interna la llave <code>externalConfigPath</code> en el archivo 
 * <bold>externalConfig.properties</bold> por defecto. 
 * 
 * Puede usarse otro archivo. Esta implementacion, carga la configuracion por defecto y si hay una externa, la carga, sustituyendo la configuracion por defecto.
 *
 */
public class EARPropertiesManager {
	private static final Logger LOG = LogManager.getLogger(EARPropertiesManager.class);
	private String nombreArchivo;
	private String defaultArchivo;
	private Properties prop;
	public final String EXTERNAL_PROPERTY_PATH = "externalConfigPath";

	public EARPropertiesManager(String nombreArchivo) {
		super();
		this.defaultArchivo = "externalConfig.properties";
		this.nombreArchivo = nombreArchivo;
		this.prop = this.cargarArchivoProperties();
	}
	

	/**
	 * Establece el nombre del archivo properties con el cual trabajar
	 * 
	 * @param nombre
	 */
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
		this.prop = this.cargarArchivoProperties();
	}

	/**
	 * Abre el archivo properties y lo carga en memoria
	 * 
	 * @return Un objeto properties o null en caso de fallar
	 */
	private Properties cargarArchivoProperties() {
		InputStream input = null;
		Properties prop = new Properties();
		final Logueo logueo = new Logueo();

		logueo.setEncabezado("-Cargar Recursos-");
		logueo.setClase(EARPropertiesManager.class);
		logueo.setMetodo("cargarArchivoProperties");
		logueo.setParametro("Archivo", nombreArchivo);
		LOG.info(logueo.getSoloParametros());
		try {
			
			if (nombreArchivo!=null) {
				//File file = ne .
				// AR: Se debe usar getClassLoader().getResource
				URL recurso = this.getClass().getClassLoader().getResource(nombreArchivo);
				String pathNombreArchivo = recurso.getPath();
				
				LOG.info(String.format("pathNombreArchivo: ---> %s ",pathNombreArchivo));
				File file = new File(pathNombreArchivo);

				if (file.exists()) {
					input = new FileInputStream(file);
					prop.load(input);
				} else {
					logueo.setError("Utilizando valores internos (por defecto) para properties, no existe el archivo externo " + nombreArchivo);
					LOG.error(logueo.getMensaje());
					
					URL dato = this.getClass().getResource(defaultArchivo);

					File f = new File(dato.getPath());

					LOG.info("EAR CONFIG FILE: " + f.getParentFile().getParentFile().getParentFile().getAbsolutePath() + defaultArchivo);

					input = new FileInputStream(new File(f.getParentFile().getParentFile().getParentFile().getAbsolutePath() + defaultArchivo));
					if (input != null) {
						prop.load(input);
						logueo.setError("Utilizando valores internos (por defecto) para properties");
					}
				}
			}else{
				
			}

			

		} catch (IOException e) {
			prop = null;
			logueo.setError(e.getMessage());
			LOG.error(logueo.getMensaje());
		} catch (Exception ex) {
			prop = null;
			logueo.setError(ex.getMessage());
			LOG.error(logueo.getMensaje());
		}

		return prop;
	}

	/**
	 * Carga el valor del archivo properties correspondiente a la clave recibida
	 * 
	 * @param clave
	 * @return String con el valor correspondiente a la clave o null en caso de
	 *         que no exista
	 */
	public String obtenerValor(final String clave) {
		String retorno = null;
		final Logueo logueo = new Logueo();

		try {
			logueo.setEncabezado("Error al obtener valor");
			logueo.setClase(EARPropertiesManager.class);
			logueo.setMetodo("obtenerValor");

			if (this.prop == null) {
				logueo.setError("No hay archivo properties para cargar el valor");
				LOG.error(logueo.getMensaje());
			} else {
				retorno = prop.getProperty(clave);
			}
		} catch (Exception ex) {
			logueo.setError(ex.getMessage());
			LOG.error(logueo.getMensaje());
		}

		return retorno;
	}

	/**
	 * Carga el valor del archivo properties correspondiente a la clave recibida
	 * 
	 * @param clave
	 * @return String con el valor correspondiente a la clave o el valor por
	 *         defecto en caso de que lo obtenido no sea un valor v\u00e1lido
	 */
	public Integer obtenerValor(final Integer valorDefecto, final String clave) {
		Integer retorno = null;
		final Logueo logueo = new Logueo();

		try {
			logueo.setEncabezado("Error al obtener valor");
			logueo.setClase(EARPropertiesManager.class);
			logueo.setMetodo("obtenerValor");

			if (this.prop == null) {
				logueo.setError("No hay archivo properties para cargar el valor");
				LOG.error(logueo.getMensaje());
				retorno = valorDefecto;
			} else {
				final String valor = prop.getProperty(clave);
				retorno = this.checkValorPropertiesNumerico(valorDefecto, valor);
			}
		} catch (Exception ex) {
			logueo.setError(ex.getMessage());
			LOG.error(logueo.getMensaje());
		}

		return retorno;
	}

	/**
	 * Carga el valor del archivo properties correspondiente a la clave recibida
	 * 
	 * @param clave
	 * @return String con el valor correspondiente a la clave o null en caso de
	 *         que no exista
	 */
	public String obtenerValor(final String valorDefecto, final String clave) {
		String retorno = valorDefecto;
		final Logueo logueo = new Logueo();

		try {
			logueo.setEncabezado("Error al obtener valor");
			logueo.setClase(EARPropertiesManager.class);
			logueo.setMetodo("obtenerValor");

			if (this.prop == null) {
				logueo.setError("No hay archivo properties para cargar el valor");
				LOG.error(logueo.getMensaje());
			} else {
				retorno = this.checkValorPropertiesString(valorDefecto, prop.getProperty(clave));
			}
		} catch (Exception ex) {
			logueo.setError(ex.getMessage());
			LOG.error(logueo.getMensaje());
		}

		return retorno;
	}

	/**
	 * Comprueba que el string recibido sea numerico y pueda ser casteado
	 * utilizando expresiones regulares
	 * 
	 * @param textoComparar
	 * @return
	 */
	private boolean isNumeric(final String textoComparar) {
		return textoComparar.matches("[-+]?\\d*\\.?\\d+");
	}

	/**
	 * Comprueba que el valor recibido de un properties es numerico, comprueba
	 * que no sea null ni vacio Asume que el parametro Integer es un entero.
	 * 
	 * @param valorDefecto
	 * @param valorComprobar
	 * @return El valor por defecto que es el primer parametro recibido
	 */
	private Integer checkValorPropertiesNumerico(final Integer valorDefecto, final String valorComprobar) {
		Integer codigo = Integer.valueOf(valorDefecto);

		if (valorComprobar != null && this.isNumeric(valorComprobar)) {
			codigo = Integer.valueOf(valorComprobar);
		}

		return codigo;
	}

	/**
	 * Comprueba que el valor recibido de un properties no sea null ni vacio
	 * 
	 * @param valorDefecto
	 * @param valorComprobar
	 * @return El valor por defecto que es el primer parametro recibido
	 */
	private String checkValorPropertiesString(final String valorDefecto, final String valorComprobar) {
		String descripcion = valorDefecto;

		if (valorComprobar != null && !valorComprobar.equals("")) {
			descripcion = valorComprobar;
		}

		return descripcion;
	}
}
