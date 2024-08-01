package uy.com.bse.mibse;

import java.util.List;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultFiniquitosCliente extends ResultGenerico {
	private static final long serialVersionUID = -1628935595488523604L;
	
	private List<Finiquito> finiquitoList;

	public List<Finiquito> getFiniquitoList() {
		return finiquitoList;
	}

	public void setFiniquitoList(List<Finiquito> finiquitoList) {
		this.finiquitoList = finiquitoList;
	}
}
