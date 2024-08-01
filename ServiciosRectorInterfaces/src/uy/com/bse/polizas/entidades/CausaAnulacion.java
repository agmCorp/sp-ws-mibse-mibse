package uy.com.bse.polizas.entidades;

import java.io.Serializable;

public class CausaAnulacion  implements Serializable{
	private static final long serialVersionUID = 7766977093349284843L;
	private Integer codMotivo;
	private String descripMotivo;
	private String codModoCalculo;
	private String descripModoCalculo;
	
	public Integer getCodMotivo() {
		return codMotivo;
	}
	public void setCodMotivo(Integer codMotivo) {
		this.codMotivo = codMotivo;
	}
	public String getDescripMotivo() {
		return descripMotivo;
	}
	public void setDescripMotivo(String descripMotivo) {
		this.descripMotivo = descripMotivo;
	}
	public String getCodModoCalculo() {
		return codModoCalculo;
	}
	public void setCodModoCalculo(String codModoCalculo) {
		this.codModoCalculo = codModoCalculo;
	}
	public String getDescripModoCalculo() {
		return descripModoCalculo;
	}
	public void setDescripModoCalculo(String descripModoCalculo) {
		this.descripModoCalculo = descripModoCalculo;
	}
}
