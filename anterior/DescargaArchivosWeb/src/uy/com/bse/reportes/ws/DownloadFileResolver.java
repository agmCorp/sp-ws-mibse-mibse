package uy.com.bse.reportes.ws;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uy.com.bse.serviciosEJB.SeguridadServiciosRemote;
import uy.com.bse.utilitario.dato.ParamGenerico;
import uy.com.bse.utilitario.excepcion.ErrorResolver;
import uy.com.bse.utilitario.excepcion.Values;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.properties.EARPropertiesManager;
import uy.com.bse.utilitario.seguridad.ResultObtenerUsuario;

public class DownloadFileResolver implements DownloadResolver {

	private static Logger LOG = LogManager.getLogger(DownloadFileResolver.class);
   private  EARPropertiesManager manager= new EARPropertiesManager("configServiciosRector.properties");

	public void resolve(HttpServletRequest request, HttpServletResponse response) {
		Logueo logueo = new Logueo();
		logueo.setEncabezado(Values.ENCABEZADOWS);
		logueo.setClase(DownloadFileResolver.class);
		logueo.setMetodo("resolve");
		logueo.setParametro("PATH", request.getPathInfo());
		Enumeration<?> e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String object = (String) e.nextElement();
			logueo.setParametro(object, request.getParameter(object));
		}

		String token = request.getParameter("token");

		ParamGenerico param = new ParamGenerico();
		param.setClave(token);
		ResultObtenerUsuario resultado;
		try {
			resultado = getEJBSeguridad().obtenerUsuario(param);
			logueo.setParametro(Values.USUARIO, resultado.getUsuario());
			logueo.setParametro("Hay error", resultado.getHayError());
			LOG.info(logueo.getSoloParametros());

			if (!resultado.getHayError()) {

				String fileName = request.getParameter("id");
				if (fileName != null) {
	
					String resultRuta = obtenerRutaDescargaUsuario(resultado.getUsuario(),token);
					logueo.setParametro("resultRuta", resultRuta);
					if (resultRuta!=null) {
						doDownload(request, response, resultRuta + fileName, fileName);

					} else {
						LOG.error(ErrorResolver.getError(Values.CONFIGURATION_EXCEPTION).getDescripcion());
						response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					}
				} else {

					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				}

			} else {
				LOG.error(resultado.getError().getDescripcion());
				try {
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "token no v\u00e1lido");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
			
			LOG.info(logueo.getSoloParametros());
		} catch (NamingException e1) {
			LOG.error("Error obteniendo el EJB de seguridad", e1);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		}

	}

	private void doDownload(HttpServletRequest req, HttpServletResponse resp, String filename, String original_filename) {
		File f = new File(filename);
		LOG.debug("filename ::"+filename);
		LOG.debug("original_filename"+original_filename);
		
		if (f.exists()) {
			int length = 0;
			ServletOutputStream op = null;
			try {
				op = resp.getOutputStream();
				
				String extension = filename.substring(filename.indexOf("."));
				LOG.debug("La extension es "+extension);
				if(extension.equals("pdf")){
					
				resp.setContentType("application/pdf");
				LOG.debug("content-type "+extension);
				}else{
					resp.setContentType("application/vnd.ms-excel");
					LOG.debug("content-type "+extension);
				}
				resp.setContentLength((int) f.length());
				resp.setHeader("Content-Disposition", "attachment; filename=\"" + original_filename + "\"");

				byte[] bbuf = new byte[4080];
				boolean existe = false;
				DataInputStream in = new DataInputStream(new FileInputStream(f));

				while ((in != null) && ((length = in.read(bbuf)) != -1)) {
					op.write(bbuf, 0, length);
					existe = true;
				}
				in.close();
				op.flush();
				op.close();
				if (existe) {
					resp.setStatus(HttpServletResponse.SC_OK);
				} else {
					resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
				}

			} catch (IOException e) {
				e.printStackTrace();
				if (op != null) {
					try {
						op.flush();
						op.close();
					} catch (IOException e1) {

					}

				}

			}catch (Exception ex){
				
				ex.printStackTrace();
				LOG.debug(ex.getMessage());
				
			}

		} else {
			handleError(req, resp);
		}

	}


	private static SeguridadServiciosRemote getEJBSeguridad() throws NamingException {
		final InitialContext ctx = new InitialContext();
		return (SeguridadServiciosRemote) ctx.lookup("java:global/SeguridadServicios/SeguridadEJB/SeguridadServicios!uy.com.bse.serviciosEJB.SeguridadServiciosRemote");

	}
	
	
	private String obtenerRutaDescargaUsuario(String usuario, String clave){
		Logueo logueo = new Logueo();
		logueo.setMetodo("obtenerRutaDescargaUsuario");
		logueo.setParametro(Values.USUARIO, usuario);
		logueo.setParametro(Values.CLAVE, clave);
        String resultado=null;
		String ruta = manager.obtenerValor("serviciosRector.ruta_descarga_ficheros");
		String carpeta = manager.obtenerValor("serviciosRector.carpeta_descargas");
		logueo.setParametro("Ruta de descarga ficheros ", ruta);
		logueo.setParametro("Carpeta de descarga ", carpeta);
		usuario = usuario.toLowerCase();
		if (ruta == null) {
			resultado=null;
		} else {
			if (ruta != null && carpeta != null) {
				resultado=(ruta + usuario + "/" + carpeta + "/");
			} else {
				resultado=(ruta + usuario + "/");
			}
		}
		logueo.setParametro("RUTA DE DESCARGA",resultado);
		LOG.info(logueo.getSoloParametros());
		return resultado;
	}

	public void handleError(HttpServletRequest request, HttpServletResponse response) {
		try {
		
			PrintWriter writer = response.getWriter();
			LOG.debug("Generando archivo txt porque ocurrio un error en la descarga ");
			response.setContentType("text/plain");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + "Error.txt" + "\"");
			writer.append("No se encontr\u00f3 el archivo a descargar. Especifique un id v\u00e1lido.");
			writer.flush();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
