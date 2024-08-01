package uy.com.bse.servicios.rector.interfaces;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaTipoDoc extends ResultGenerico{
	ArrayList<Documento> documento = new ArrayList<Documento>();

	public ArrayList<Documento> getDocumento() {
		return documento;
	}

	public void setDocumento(ArrayList<Documento> documento) {
		this.documento = documento;
	}
	
}
