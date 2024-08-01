package uy.com.bse.reportes.ws;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.ws.soap.MTOM;


@WebService(endpointInterface = "uy.com.bse.reportes.ws.IWsDownloadReportes", serviceName = "WsDownloadReportes")
//@StreamingAttachment(parseEagerly = true, memoryThreshold = 40000L)
@MTOM(enabled = true, threshold = 2000000)
public class WsDownloadReportes implements IWsDownloadReportes{

	@XmlMimeType("application/octet-stream")
	@WebMethod
	public DataHandler fileDownload(String filename) {
		
		return new DataHandler(new FileDataSource("C:/descargas/admin/bajar_archivos/"+filename));
	}
}
