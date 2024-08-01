package uy.com.bse.maestro.personas.datosbancarios;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaBancosPersona extends ResultGenerico{
	ArrayList<Banco> bancos = new ArrayList<Banco>();

	public ArrayList<Banco> getBancos() {
		return bancos;
	}

	public void setBancos(ArrayList<Banco> bancos) {
		this.bancos = bancos;
	}
	public void setUnBanco(Banco banco){
		this.bancos.add(banco);
	}	
}
