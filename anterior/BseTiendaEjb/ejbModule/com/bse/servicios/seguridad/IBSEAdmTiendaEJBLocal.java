package com.bse.servicios.seguridad;

import com.bse.servicios.seguridad.dt.DTSesionTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import javax.ejb.Local;

@Local
public interface IBSEAdmTiendaEJBLocal {
    
    public void cambiarContrasena(DTSesionTienda sesion, String nuevaContrasena) throws BSEExceptionTienda; 
    
}
