/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bse.accesodatos.comun;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author paranz
 */
@Embeddable
public class PatTransaccionPagosPK implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    @Column(name = "ID_TRANSACCION")
    private long idTransaccion;
    @Basic(optional = false)
    @Column(name = "ID_PAGO")
    private long idPago;

    public PatTransaccionPagosPK() {
    }

    public PatTransaccionPagosPK(long idTransaccion, long idPago) {
        this.idTransaccion = idTransaccion;
        this.idPago = idPago;
    }

    public long getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(long idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public long getIdPago() {
        return idPago;
    }

    public void setIdPago(long idPago) {
        this.idPago = idPago;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTransaccion;
        hash += (int) idPago;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PatTransaccionPagosPK)) {
            return false;
        }
        PatTransaccionPagosPK other = (PatTransaccionPagosPK) object;
        if (this.idTransaccion != other.idTransaccion) {
            return false;
        }
        if (this.idPago != other.idPago) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bse.accesodatos.temporarias.PatTransaccionPagosPK[ idTransaccion=" + idTransaccion + ", idPago=" + idPago + " ]";
    }
    
}
