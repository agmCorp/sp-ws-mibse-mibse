package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamCalcularCotizacion extends ParamGenerico{
		private String numCotizacion;
		private String numCertificado;
		private String codPlanPago;
		private Integer diaVencimiento;
		
		public String getNumCotizacion() {
			return numCotizacion;
		}
		public void setNumCotizacion(String numCotizacion) {
			this.numCotizacion = numCotizacion;
		}
		public String getNumCertificado() {
			return numCertificado;
		}
		public void setNumCertificado(String numCertificado) {
			this.numCertificado = numCertificado;
		}
		public String getCodPlanPago() {
			return codPlanPago;
		}
		public void setCodPlanPago(String codPlanPago) {
			this.codPlanPago = codPlanPago;
		}
		public Integer getDiaVencimiento() {
			return diaVencimiento;
		}
		public void setDiaVencimiento(Integer diaVencimiento) {
			this.diaVencimiento = diaVencimiento;
		}				
			
}
