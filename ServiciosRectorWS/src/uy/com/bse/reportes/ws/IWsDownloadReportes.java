package uy.com.bse.reportes.ws;

import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.ws.soap.MTOM;


@WebService
//@StreamingAttachment(parseEagerly = true, memoryThreshold = 40000L)
@MTOM(enabled = true, threshold = 200000)
public interface IWsDownloadReportes {
	
	@XmlMimeType("application/octet-stream")
	@WebMethod
	public DataHandler fileDownload(String filename) ;

}