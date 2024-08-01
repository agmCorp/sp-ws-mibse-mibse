package uy.com.bse.autodata.operaciones;

import java.io.Serializable;

public class Modelo implements Serializable {

	private static final long serialVersionUID = 6412363241405551204L;
	
	private String codMarca;
	private String codAnoDesde;
	private String codModelo;
	private String tipoMovimiento;
	private String descMarca;
	private String descModelo;
	private String codAnioHasta;
	private String codAutoData;
	private String codTipoVehiculo;
	private String descTipoVehiculo;
	private String codDestVehiculo;
	private String descDestVehiculo;
	private String codCombustible;
	private String descCombustible;
	
	
	public String getCodAnoDesde() {
		return codAnoDesde;
	}

	public void setCodAnoDesde(String codAnoDesde) {
		this.codAnoDesde = codAnoDesde;
	}

	public String getDescMarca() {
		return descMarca;
	}

	public void setDescMarca(String descMarca) {
		this.descMarca = descMarca;
	}

	public String getDescModelo() {
		return descModelo;
	}

	public void setDescModelo(String descModelo) {
		this.descModelo = descModelo;
	}

	public String getCodAnioHasta() {
		return codAnioHasta;
	}

	public void setCodAnioHasta(String codAnioHasta) {
		this.codAnioHasta = codAnioHasta;
	}

	public String getCodAutoData() {
		return codAutoData;
	}

	public void setCodAutoData(String codAutoData) {
		this.codAutoData = codAutoData;
	}

	public String getCodTipoVehiculo() {
		return codTipoVehiculo;
	}

	public void setCodTipoVehiculo(String codTipoVehiculo) {
		this.codTipoVehiculo = codTipoVehiculo;
	}

	public String getDescTipoVehiculo() {
		return descTipoVehiculo;
	}

	public void setDescTipoVehiculo(String descTipoVehiculo) {
		this.descTipoVehiculo = descTipoVehiculo;
	}

	public String getCodDestVehiculo() {
		return codDestVehiculo;
	}

	public void setCodDestVehiculo(String codDestVehiculo) {
		this.codDestVehiculo = codDestVehiculo;
	}

	public String getDescDestVehiculo() {
		return descDestVehiculo;
	}

	public void setDescDestVehiculo(String descDestVehiculo) {
		this.descDestVehiculo = descDestVehiculo;
	}

	public String getCodCombustible() {
		return codCombustible;
	}

	public void setCodCombustible(String codCombustible) {
		this.codCombustible = codCombustible;
	}

	public String getDescCombustible() {
		return descCombustible;
	}

	public void setDescCombustible(String descCombustible) {
		this.descCombustible = descCombustible;
	}

  


	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public String getCodMarca() {
		return codMarca;
	}

	public void setCodMarca(String codMarca) {
		this.codMarca = codMarca;
	}


	public String getCodModelo() {
		return codModelo;
	}

	public void setCodModelo(String codModelo) {
		this.codModelo = codModelo;
	}

}
