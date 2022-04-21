package com.bse.servicios.seguridad;

import com.bse.servicios.seguridad.dt.DTSesion;
import com.bse.negocio.comun.BSEException;
import javax.ejb.Local;

@Local
public interface IBSEonlinewsAdmEJBLocal {
    
    public void cambiarContrasena(DTSesion sesion, String nuevaContrasena) throws BSEException; 
    
}
