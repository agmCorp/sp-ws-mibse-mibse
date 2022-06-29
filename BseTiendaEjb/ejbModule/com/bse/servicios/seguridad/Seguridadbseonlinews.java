/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bse.servicios.seguridad;

import com.bse.negocio.FabricaNegocio;
import com.bse.servicios.seguridad.dt.DTSesion;
import com.bse.negocio.comun.Codigos;
import com.bse.negocio.comun.BSEException;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author paranz
 */
public class Seguridadbseonlinews {

    @PersistenceContext(unitName = "bsetiendaws-pu")
    private EntityManager em;

    @AroundInvoke
    public Object auditarCredenciales(InvocationContext inv) throws Exception {
        String interfaz = inv.getTarget().getClass().getSimpleName().split("_")[0];
        String metodo = inv.getMethod().getName().trim();
        Object[] params = inv.getParameters();
        DTSesion sesion = (DTSesion) params[0];
        FabricaNegocio neg = FabricaNegocio.getFabricaNegocio();
        String parameter = null;
        if (interfaz.equalsIgnoreCase("BSEonlinewsAdmEJB") && metodo.equalsIgnoreCase("cambiarContrasena")) {
            parameter = params[0].toString();
        } else {

            for (int i = 0; i < params.length; i++) {
                parameter = parameter + "|" + params[i];
            }
        }
        short auditar = neg.getSeguridadMgr().validarCredenciales(em, sesion, interfaz, metodo);
        try {
            if (auditar == Codigos.login_ok) {
                long idSesion = neg.getSeguridadMgr().crearSesion(em, sesion, interfaz, metodo, auditar, parameter);
                
            	//System.out.println("idSesion="+idSesion);

                sesion.setSesionId(idSesion);
                params[0] = sesion;
                inv.setParameters(params);
                return inv.proceed();
            } else {
                throw new BSEException(auditar);
            }
        } catch (BSEException ex) {
            throw ex;
        } finally {
            if (auditar != Codigos.login_ok) {
                neg.getSeguridadMgr().crearSesion(em, sesion, interfaz, metodo, auditar, parameter);
            }
        }
    }
}