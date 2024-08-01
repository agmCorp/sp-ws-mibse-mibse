package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamActualizarDatos extends ParamGenerico{
		private String numCotizacion;
		private String numCertificado;
		private String numConsBien;
		private String datoCod;
		private String datoValor;
		
		
		
		
		public String getNumConsBien() {
			return numConsBien;
		}
		public void setNumConsBien(String numConsBien) {
			this.numConsBien = numConsBien;
		}
		
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
		
		public String getDatoCod() {
			return datoCod;
		}
		public void setDatoCod(String datoCod) {
			this.datoCod = datoCod;
		}
		public String getDatoValor() {
			return datoValor;
		}
		public void setDatoValor(String datoValor) {
			this.datoValor = datoValor;
		}
		
}
