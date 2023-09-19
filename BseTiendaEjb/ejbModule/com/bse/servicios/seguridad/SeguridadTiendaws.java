/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bse.servicios.seguridad;

import com.bse.negocio.FabricaNegocioTienda;
import com.bse.servicios.seguridad.dt.DTSesionTienda;
import com.bse.negocio.comun.CodigosTienda;
import com.bse.negocio.comun.BSEExceptionTienda;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author paranz
 */
public class SeguridadTiendaws {

    @PersistenceContext(unitName = "bselatiendaws-pu")
    private EntityManager em;

    @AroundInvoke
    public Object auditarCredenciales(InvocationContext inv) throws Exception {
        String interfaz = inv.getTarget().getClass().getSimpleName().split("_")[0];
     
        String metodo = inv.getMethod().getName().trim();
        Object[] params = inv.getParameters();
        DTSesionTienda sesion = (DTSesionTienda) params[0];
        FabricaNegocioTienda neg = FabricaNegocioTienda.getFabricaNegocio();
        String parameter = null;
        if (interfaz.equalsIgnoreCase("BSEonlinewsAdmTiendaEJB") && metodo.equalsIgnoreCase("cambiarContrasena")) {
            parameter = params[0].toString();
        } else {

            for (int i = 0; i < params.length; i++) {
                parameter = parameter + "|" + params[i];
            }
        }
        //System.out.println("*****************auditarCredenciales - Parametros " + parameter);
        
        short auditar = neg.getSeguridadMgr().validarCredenciales(em, sesion, interfaz, metodo);
        try {
            if (auditar == CodigosTienda.login_ok) {
                long idSesion = neg.getSeguridadMgr().crearSesion(em, sesion, interfaz, metodo, auditar, parameter);
                
            	//System.out.println("idSesion="+idSesion);

                sesion.setSesionId(idSesion);
                params[0] = sesion;
                inv.setParameters(params);
                return inv.proceed();
            } else {
                throw new BSEExceptionTienda(auditar);
            }
        } catch (BSEExceptionTienda ex) {
            throw ex;
        } finally {
            if (auditar != CodigosTienda.login_ok) {
                neg.getSeguridadMgr().crearSesion(em, sesion, interfaz, metodo, auditar, parameter);
            }
        }
    }
}