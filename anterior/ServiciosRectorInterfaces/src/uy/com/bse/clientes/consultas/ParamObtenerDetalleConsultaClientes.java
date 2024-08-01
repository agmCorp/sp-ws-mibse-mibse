package uy.com.bse.clientes.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerDetalleConsultaClientes extends ParamGenerico{

	/*
	p_nu_persona     IN CART_MAE_PERSONAS.CABU_NU_PERSONA%TYPE,
    p_cd_usuario     IN COTW_USUARIOS_MAPPING.COUM_CD_USU_DOMINIO%TYPE, 
    p_clientes_todos IN VARCHAR2, 

    */
	/**
	 * 
	 */
	private static final long serialVersionUID = 5448509191903628579L;
	private String numPersona ;
	private String codCliente;
	private String codProductor;
	
	public String getCodProductor() {
		return codProductor;
	}
	public void setCodProductor(String codProductor) {
		this.codProductor = codProductor;
	}
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	private String todosLosClientes;
	
	
	public String getNumPersona() {
		return numPersona;
	}
	public void setNumPersona(String numPersona) {
		this.numPersona = numPersona;
	}
	public String getTodosLosClientes() {
		return todosLosClientes;
	}
	public void setTodosLosClientes(String todosLosClientes) {
		this.todosLosClientes = todosLosClientes;
	}


	
	
}
