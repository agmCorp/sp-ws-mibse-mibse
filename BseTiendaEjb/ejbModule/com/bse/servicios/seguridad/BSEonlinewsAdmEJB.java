package com.bse.servicios.seguridad;

import com.bse.negocio.FabricaNegocio;
import com.bse.negocio.seguridad.ISeguridad;
import com.bse.servicios.seguridad.dt.DTSesion;
import com.bse.negocio.comun.CodigosError;
import com.bse.negocio.comun.BSEException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(mappedName = "BSEonlinewsAdmEJB")
@Interceptors({Seguridadbseonlinews.class})
public class BSEonlinewsAdmEJB implements IBSEonlinewsAdmEJBLocal {

    @PersistenceContext(unitName = "bsetiendaws-pu")
    private EntityManager em;

    public void cambiarContrasena(DTSesion sesion, String nuevaContrasena) throws BSEException {
        try {
            ISeguridad neg = FabricaNegocio.getFabricaNegocio().getSeguridadMgr();
            neg.cambiarContrasena(em, sesion, nuevaContrasena);
        } catch (BSEException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.ERROR, ex.getMessage(), ex);            
            throw ex;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.ERROR, ex.getMessage(), ex);
            throw new BSEException(CodigosError.excepcion_generica, ex);
        }
    }
}
