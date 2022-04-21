package com.bse.servicios.seguridad.dt;

import java.io.Serializable;

public class DTSesion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombreUsuario;
    private String contrasena;
    private String direccionIp;
    private long sesionId;

    public DTSesion(String nombreUsuario, String contrasena, String direccionIp) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.direccionIp = direccionIp;
    }

    public DTSesion() {
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setDireccionIp(String direccionIp) {
        this.direccionIp = direccionIp;
    }

    public String getDireccionIp() {
        return direccionIp;
    }

    public void setSesionId(long sesionId) {
        this.sesionId = sesionId;
    }

    public long getSesionId() {
        return sesionId;
    }

    @Override
    public String toString() {
        return "(" + nombreUsuario + "," + direccionIp + ")";
    }
    
}
