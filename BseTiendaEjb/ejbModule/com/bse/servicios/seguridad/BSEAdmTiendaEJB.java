package com.bse.servicios.seguridad;

import com.bse.negocio.FabricaNegocioTienda;
import com.bse.negocio.seguridad.ISeguridadTienda;
import com.bse.servicios.seguridad.dt.DTSesionTienda;
import com.bse.negocio.comun.CodigosErrorTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(mappedName = "BSEAdmTiendaEJB")
@Interceptors({SeguridadTiendaws.class})
public class BSEAdmTiendaEJB implements IBSEAdmTiendaEJBLocal {

    @PersistenceContext(unitName = "bselatiendaws-pu")
    private EntityManager em;

    public void cambiarContrasena(DTSesionTienda sesion, String nuevaContrasena) throws BSEExceptionTienda {
        try {
            ISeguridadTienda neg = FabricaNegocioTienda.getFabricaNegocio().getSeguridadMgr();
            neg.cambiarContrasena(em, sesion, nuevaContrasena);
        } catch (BSEExceptionTienda ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.ERROR, ex.getMessage(), ex);            
            throw ex;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.ERROR, ex.getMessage(), ex);
            throw new BSEExceptionTienda(CodigosErrorTienda.excepcion_generica, ex);
        }
    }
}
