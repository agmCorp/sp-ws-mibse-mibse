package com.bse.negocio.seguridad;

import com.bse.servicios.seguridad.dt.DTSesionTienda;

import com.bse.negocio.comun.BSEExceptionTienda;
import java.security.NoSuchAlgorithmException;
import javax.persistence.EntityManager;

public interface ISeguridadTienda {

    public short validarCredenciales(EntityManager em, DTSesionTienda sesion, String interfaz, String metodo);

    public long crearSesion(EntityManager em, DTSesionTienda sesion, String interfaz, String metodo, short resultado, String parametros) throws BSEExceptionTienda; 
    
    public void cambiarContrasena(EntityManager em, DTSesionTienda sesion, String nuevaContrasena) throws BSEExceptionTienda, NoSuchAlgorithmException;
        
}
