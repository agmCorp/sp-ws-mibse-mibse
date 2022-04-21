package com.bse.negocio.seguridad;

import com.bse.servicios.seguridad.dt.DTSesion;

import com.bse.negocio.comun.BSEException;
import java.security.NoSuchAlgorithmException;
import javax.persistence.EntityManager;

public interface ISeguridad {

    public short validarCredenciales(EntityManager em, DTSesion sesion, String interfaz, String metodo);

    public long crearSesion(EntityManager em, DTSesion sesion, String interfaz, String metodo, short resultado, String parametros) throws BSEException; 
    
    public void cambiarContrasena(EntityManager em, DTSesion sesion, String nuevaContrasena) throws BSEException, NoSuchAlgorithmException;
        
}
