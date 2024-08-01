package uy.com.bse.mibse;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlMimeType;

import uy.com.bse.utilitario.dato.ParamGenerico;

/*
 * ATENCIÓN: Esta clase no es serializable ni externalizable porque el atributo javax.activation.DataHandler no lo es.
 * https://docs.oracle.com/cd/E19879-01/821-0269/abebo/index.html
 */
public class ParamSubirArchivo extends ParamGenerico {
	private static final long serialVersionUID = 2172828057565824068L;

	private String nombreArchivo;
	private DataHandler dataHandler;
	private String mimeType;

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	@XmlMimeType("application/octet-stream") 
	public DataHandler getArchivo() {
		return dataHandler;
	}

	public void setArchivo(DataHandler dataHandler) {
		this.dataHandler = dataHandler;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
}
