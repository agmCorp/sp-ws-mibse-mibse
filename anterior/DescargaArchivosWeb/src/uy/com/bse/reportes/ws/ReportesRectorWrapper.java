package uy.com.bse.reportes.ws;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import reports.oracle.rwclient.RWWebService;
import reports.oracle.rwclient.RWWebService_Service;
import uy.com.bse.utilitario.log.Logueo;
import uy.com.bse.utilitario.properties.EARPropertiesManager;
import uy.com.bse.utilitario.util.MailBuilder;

public class ReportesRectorWrapper implements Serializable {

	private static final long serialVersionUID = 4339465343946314959L;

	private static final Logger LOG = LogManager.getLogger(ReportesRectorWrapper.class);

	private final String server;
	private final String userid;
	private final String application;
	private final String reportUrl;

	public ReportesRectorWrapper() {
		super();
		EARPropertiesManager manager = new EARPropertiesManager("configServiciosRector.properties");
		server = manager.obtenerValor("serviciosRector.server_reporte");
		userid = manager.obtenerValor("serviciosRector.userid_reporte");
		application = manager.obtenerValor("serviciosRector.app_reporte");
		reportUrl = manager.obtenerValor("serviciosRector.url_reports_rector");
	}

	public void imprimir(HttpServletRequest request, HttpServletResponse response) {
		getContent(getPDFReportURL(getReport(request.getParameter("reportId")), getParts(request)), request.getParameter("reportId") + ".pdf", response);
	}

	private String getParts(HttpServletRequest request) {
		LOG.debug("getParts start");
		String cmd = "";

		if (request.getParameterNames().hasMoreElements()) {
			StringBuffer sb = new StringBuffer();
			Enumeration<?> en = request.getParameterNames();
			while (en.hasMoreElements()) {
				String paramname = (String) en.nextElement();
				if (!(paramname.equals("reportId") || paramname.equals("token"))) {
					sb.append(paramname);
					sb.append("=");
					sb.append(request.getParameter(paramname));
					sb.append(" ");
				}
			}
			cmd += sb.toString();
		}

		LOG.debug("getParts ends");

		return cmd;
	}

	private String getReport(String reportName) {
		LOG.debug("getReport start");
		if (reportName != null && !reportName.isEmpty()) {
			LOG.debug("Reporte de rector " + reportName);
			LOG.debug("getReport ends");
			return reportName;
		}
		LOG.error("No ha especificado el nombre del reporte");
		throw new IllegalArgumentException("Report name not found");
	}

	public void getContent(URL url, String fileName, HttpServletResponse response) {
		LOG.debug("getContent start");
		try {
			if (url != null) {
				LOG.info("URL-SERVLET-SERVICIOS " + url.toString());
				InputStream in = url.openStream();
				response.setContentType("application/octet-stream");
				response.setHeader("Content-disposition", "attachment; filename=" + fileName);

				OutputStream out = response.getOutputStream();

				byte[] buffer = new byte[8192];
				int length;
				boolean tieneDatos = false;
				while ((length = in.read(buffer)) > 0) {
					out.write(buffer, 0, length);
					tieneDatos = true;
				}

				in.close();
				out.flush();
				out.close();

				if (!tieneDatos) {
					LOG.debug("LA PETICION NO TRAE DATOS DEL ARCHIVO");
				}
			}
		} catch (MalformedURLException e) {
			LOG.debug("MalformedURLException - getContent", e);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			e.printStackTrace();
		} catch (IOException e) {
			LOG.debug("IOException - getContent", e);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		} catch (NullPointerException e) {
			LOG.debug("NullPointerException - getContent", e);
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} catch (Exception e) {
			LOG.debug("Exception - getContent", e);
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}

		LOG.debug("getContent ends");
	}

	public synchronized URL getPDFReportURL(String report, String pars) {
		URL url = null;
		LOG.debug("getPDFReportURL start");
		Logueo logueo= new Logueo();
		logueo.setClase(ReportesRectorWrapper.class);
		logueo.setMetodo("getPDFReportURL");
		String cmdline = "report=" + report + " " + pars + " server=" + server + " desformat=pdf " + "destype=cache userid=" + userid;
		LOG.info("URL RECTORREPORT " + cmdline);
		String urlStr;
		try {

			RWWebService_Service service = new RWWebService_Service(new URL(reportUrl), new QName("http://oracle.reports/rwclient/", "RWWebService"));

			RWWebService rWWebService = service.getRWWebServicePort();

			String res;
			

			res = rWWebService.runJob(cmdline, true);

			int i = res.indexOf("job id=");
			int j = res.indexOf("queueType");

			if (i > 0) {
				String k = res.substring(i + 8, j - 2);
				Object monitor = new Object();
				synchronized (monitor) {
					String jobInfo = "WAITING";
					while (jobInfo.contains("WAITING") || jobInfo.contains("RUNNING") || jobInfo.contains("OPENING") || jobInfo.contains("ENQUEUED")) {
						jobInfo = rWWebService.getJobInfo(Integer.parseInt(k), server, userid).toUpperCase();
						int x1 = jobInfo.indexOf("<status".toUpperCase());
						int x2 = jobInfo.lastIndexOf("</status>".toUpperCase());
						jobInfo = jobInfo.substring(x1, x2).toUpperCase();

						try {
							monitor.wait(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}

				urlStr = "http://" + application + "/reports/rwservlet/getjobid" + k.trim() + "?server=" + server;
				url = new URL(urlStr);
				LOG.debug("La url es : "+urlStr);
				LOG.debug("URL DEL REPORTE " + url.toString());
				
				
			}
					
		}
			catch (MalformedURLException em) {
				LOG.error("Error en URL del REPORTE", em);
				logueo.setException("MalformedURLException");
				logueo.setError("MalformedURLException  URL para la descarga del reporte de rector. ");
				enviarMail(logueo);
				
			} catch (NumberFormatException en) {
			   LOG.error("Error de parseo de datos", en);
			   logueo.setException("NumberFormatException");
			   logueo.setError("Error de parseo de datos ");
				enviarMail(logueo);
		} catch (Exception e1) {
			LOG.error("No se obtuvo URL para la descarga del reporte de rector. "+cmdline);
			 logueo.setException("Exception");
			logueo.setError("MalformedURLException  URL para la descarga del reporte de rector. "+ cmdline);
			enviarMail(logueo);
			LOG.error("Error de parseo URL de servicio RWWebService_Service", e1);
		}
		
		
		LOG.debug("getPDFReportURL ends");

		return url;

	}
	
	private void enviarMail(Logueo logueo) {
		try {
			EARPropertiesManager manager= new EARPropertiesManager("configServiciosRector.properties");
			MailBuilder.create().sentToDefaultConfigureMail(manager.obtenerValor("mail.soporte.persistencia"),manager.obtenerValor("ccmail.soporte.persistencia"),logueo.getMensaje(), "DESCARGA ARCHIVOS RECTOR : " + InetAddress.getLocalHost().getHostName());
		} catch (UnknownHostException e) {
			LOG.debug("No se encontro el nombre de Host", e);
		}
	}
	
}
