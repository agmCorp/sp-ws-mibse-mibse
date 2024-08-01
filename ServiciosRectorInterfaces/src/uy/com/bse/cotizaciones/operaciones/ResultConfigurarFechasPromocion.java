package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultConfigurarFechasPromocion extends ResultGenerico{

	private static final long serialVersionUID = 6667065013727822817L;
	private String fechaHastaVigencia;
	private String fechaHastaVigenciaTecnica;
	private String vigencia;
	private String descVigencia;
	private String modoCalculo;
	private String descModoCalculo;
	
	public String getFechaHastaVigencia() {
		return fechaHastaVigencia;
	}
	public void setFechaHastaVigencia(String fechaHastaVigencia) {
		this.fechaHastaVigencia = fechaHastaVigencia;
	}
	public String getFechaHastaVigenciaTecnica() {
		return fechaHastaVigenciaTecnica;
	}
	public void setFechaHastaVigenciaTecnica(String fechaHastaVigenciaTecnica) {
		this.fechaHastaVigenciaTecnica = fechaHastaVigenciaTecnica;
	}
	public String getVigencia() {
		return vigencia;
	}
	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}
	public String getDescVigencia() {
		return descVigencia;
	}
	public void setDescVigencia(String descVigencia) {
		this.descVigencia = descVigencia;
	}
	public String getModoCalculo() {
		return modoCalculo;
	}
	public void setModoCalculo(String modoCalculo) {
		this.modoCalculo = modoCalculo;
	}
	public String getDescModoCalculo() {
		return descModoCalculo;
	}
	public void setDescModoCalculo(String descModoCalculo) {
		this.descModoCalculo = descModoCalculo;
	}
	
}
