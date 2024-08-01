package uy.com.bse.clientes.consultas;



import uy.com.bse.cotizaciones.consultas.Cliente;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerDetalleConsultaClientes extends ResultGenerico{
	private Cliente detallelClientes = new Cliente();

	public Cliente getDetallelClientes() {
		return detallelClientes;
	}

	public void setDetallelClientes(Cliente detallelClientes) {
		this.detallelClientes = detallelClientes;
	}

	
}
