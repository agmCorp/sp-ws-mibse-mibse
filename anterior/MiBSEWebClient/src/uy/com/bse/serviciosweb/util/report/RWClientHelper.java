package uy.com.bse.serviciosweb.util.report;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import oracle.reports.rwclient.RWWebService;
import oracle.reports.rwclient.RWWebService_Service;
import uy.com.bse.utilitario.properties.EARPropertiesManager;

public class RWClientHelper {
	private static Logger logger = LogManager.getLogger(RWClientHelper.class);

	// Singleton: instancia única
	private static RWClientHelper instance;

	private String server;
	private String userid;
	private String application;
	private String reportUrl;

	// Singleton: evita instancia
	private RWClientHelper() {
		EARPropertiesManager manager = new EARPropertiesManager("configMibse.properties");
		this.server = manager.obtenerValor("report.server");
		this.userid = manager.obtenerValor("report.userid");
		this.application = manager.obtenerValor("report.application");
		this.reportUrl = manager.obtenerValor("report.reportUrl");
	}

	// Singleton: obtiene instancia
	public static RWClientHelper getInstance() {
		if (instance == null) {
			instance = new RWClientHelper();
		}
		return instance;
	}

	public byte[] imprimir(String reportName, String pars) {
		logger.info("Generando reporte " + reportName + ", pars " + pars);
		URL url = getPDFReportURL(reportName, pars);
		return url != null ? getContent(url) : null;
	}

	public byte[] getContent(URL url) {
		logger.debug("getContent start");
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] resultado = null;
		if (url != null) {
			try {
				InputStream in = url.openStream();
				BufferedInputStream bufIn = new BufferedInputStream(in);
				byte[] buffer = new byte[1024];
				int read = bufIn.read(buffer);
				while (read >= 0) {
					if (read > 0) {
						out.write(buffer, 0, read);
					}
					read = bufIn.read(buffer);
				}
				bufIn.close();
				out.close();
				in.close();
			} catch (IOException e) {
				logger.error("Error procesando stream del reporte", e);
			}
		}
		resultado = out.toByteArray();
		logger.debug("getContent ends");
		return resultado;
	}

	private URL getPDFReportURL(String nomReport, String pars) {
		URL url = null;
		logger.debug("getPDFReportURL start");
		String cmdline = "report=" + nomReport + " " + pars + " server=" + server + " desformat=pdf "
				+ "destype=cache userid=" + userid;

		try {
			RWWebService_Service service = new RWWebService_Service(new URL(reportUrl + "?wsdl"),
					new QName("http://oracle.reports/rwclient/", "RWWebService"));
			RWWebService rWWebService = service.getRWWebServicePort();
			String res = rWWebService.runJob(cmdline, true);

			int i = res.indexOf("job id=");
			int j = res.indexOf("queueType");

			if (i > 0) {
				String k = res.substring(i + 8, j - 2);

				String jobInfo = "WAITING";
				while (jobInfo.contains("WAITING") || jobInfo.contains("RUNNING") || jobInfo.contains("OPENING")
						|| jobInfo.contains("ENQUEUED")) {
					jobInfo = rWWebService.getJobInfo(Integer.parseInt(k), server, userid).toUpperCase();
					int x1 = jobInfo.indexOf("<status".toUpperCase());
					int x2 = jobInfo.lastIndexOf("</status>".toUpperCase());
					jobInfo = jobInfo.substring(x1, x2).toUpperCase();

					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				String urlStr = "http://" + application + "/reports/rwservlet/getjobid" + k.trim() + "?server="
						+ server;
				try {
					url = new URL(urlStr);
					logger.info("URL del reporte " + url.toString());
				} catch (MalformedURLException e) {
					logger.error("Error en URL del reporte", e);
				}
			}
		} catch (NumberFormatException e) {
			logger.error("Error de parseo de datos", e);
		} catch (MalformedURLException e) {
			logger.error("Error obteniendo datos del reporte", e);
		}
		logger.debug("getPDFReportURL ends");

		return url;
	}
}
