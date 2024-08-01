package uy.com.bse.mibse;

import java.io.Serializable;
import java.util.List;

public class DatosFacturacion implements Serializable {

	private static final long serialVersionUID = 897598331621346778L;
	
	private Integer codSucursal;
	private Integer nroPoliza;
	private Integer codRamo;
	private Integer numCliente;
	private String descRamo;
	private String codProducto;
	private String descProducto;
	private Boolean esFactElectronica;
	private DatosPolizaADT datosADT;
	private String fechaEndosoMail;
	private List<DatosMatricula> matriculas;
	

	public Integer getNroPoliza() {
		return nroPoliza;
	}

	public void setNroPoliza(Integer nroPoliza) {
		this.nroPoliza = nroPoliza;
	}

	public Integer getCodRamo() {
		return codRamo;
	}

	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}

	public String getDescRamo() {
		return descRamo;
	}

	public void setDescRamo(String descRamo) {
		this.descRamo = descRamo;
	}

	public String getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}

	public String getDescProducto() {
		return descProducto;
	}

	public void setDescProducto(String descProducto) {
		this.descProducto = descProducto;
	}

	
	public Boolean getEsFactElectronica() {
		return esFactElectronica;
	}

	public void setEsFactElectronica(Boolean esFactElectronica) {
		this.esFactElectronica = esFactElectronica;
	}

	public Integer getNumCliente() {
		return numCliente;
	}

	public void setNumCliente(Integer numCliente) {
		this.numCliente = numCliente;
	}

	public List<DatosMatricula> getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(List<DatosMatricula> matriculas) {
		this.matriculas = matriculas;
	}

	public String getFechaEndosoMail() {
		return fechaEndosoMail;
	}

	public void setFechaEndosoMail(String fechaEndosoMail) {
		this.fechaEndosoMail = fechaEndosoMail;
	}

	public DatosPolizaADT getDatosADT() {
		return datosADT;
	}

	public void setDatosADT(DatosPolizaADT datosADT) {
		this.datosADT = datosADT;
	}

	public Integer getCodSucursal() {
		return codSucursal;
	}

	public void setCodSucursal(Integer codSucursal) {
		this.codSucursal = codSucursal;
	}
}
