package com.bse.servicios.seguridad;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.bse.negocio.FabricaNegocioTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import com.bse.negocio.comun.CodigosErrorTienda;
import com.bse.negocio.seguridad.ISeguridadTienda;
import com.bse.servicios.seguridad.dt.DTSesionTienda;

@Stateless(mappedName = "BSEAdmTiendaEJB")
@Interceptors({SeguridadTiendaws.class})
public class BSEAdmTiendaEJB implements IBSEAdmTiendaEJBLocal {

    private static final Logger logger = LogManager.getLogger(BSEAdmTiendaEJB.class);

    @PersistenceContext(unitName = "bselatiendaws-pu")
    private EntityManager em;

    @Override
    public void cambiarContrasena(DTSesionTienda sesion, String nuevaContrasena) throws BSEExceptionTienda {
        try {
            ISeguridadTienda neg = FabricaNegocioTienda.getFabricaNegocio().getSeguridadMgr();
            neg.cambiarContrasena(em, sesion, nuevaContrasena);
        } catch (BSEExceptionTienda ex) {
            //Logger.getLogger(this.getClass().getName()).log(Level.ERROR, ex.getMessage(), ex);
            logger.log(Level.ERROR, ex.getMessage(), ex);
            throw ex;
        } catch (Exception ex) {
            //Logger.getLogger(this.getClass().getName()).log(Level.ERROR, ex.getMessage(), ex);
            logger.log(Level.ERROR, ex.getMessage(), ex);
            throw new BSEExceptionTienda(CodigosErrorTienda.excepcion_generica, ex);
        }
    }
}
