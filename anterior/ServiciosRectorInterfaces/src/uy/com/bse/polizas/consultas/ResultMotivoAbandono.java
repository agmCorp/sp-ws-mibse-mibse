package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.MotivoAbandono;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultMotivoAbandono extends ResultGenerico{
	private ArrayList<MotivoAbandono> motivoAbandono = new ArrayList<MotivoAbandono>();

	public ArrayList<MotivoAbandono> getMotivoAbandono() {
		return motivoAbandono;
	}

	public void setMotivoAbandono(ArrayList<MotivoAbandono> motivoAbandono) {
		this.motivoAbandono = motivoAbandono;
	}
	
	public void setUnMotivo(MotivoAbandono item){
		this.motivoAbandono.add(item);
	}
}
