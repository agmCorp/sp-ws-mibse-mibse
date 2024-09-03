package uy.com.bse.mibse.sp.ws.mibse.model.dto;
import java.io.Serializable;

import javax.xml.datatype.XMLGregorianCalendar;


public class DatoAuxilioOtorgado implements Serializable {
    private static final long serialVersionUID = 1L;
    private String comentarios;
    private String email;
    private XMLGregorianCalendar fecha;
    private String idServicio;
    private Long reversado;
    private String telefono;

    public DatoAuxilioOtorgado(String comentarios, String email, XMLGregorianCalendar fecha, String idServicio,
                               Long reversado, String telefono) {
        super();
        this.comentarios = comentarios;
        this.email = email;
        this.fecha = fecha;
        this.idServicio = idServicio;
        this.reversado = reversado;
        this.telefono = telefono;
    }


    public String getComentarios() {
        return comentarios;
    }
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public XMLGregorianCalendar getFecha() {
        return fecha;
    }
    public void setFecha(XMLGregorianCalendar fecha) {
        this.fecha = fecha;
    }
    public String getIdServicio() {
        return idServicio;
    }
    public void setIdServicio(String idServicio) {
        this.idServicio = idServicio;
    }
    public Long getReversado() {
        return reversado;
    }
    public void setReversado(Long reversado) {
        this.reversado = reversado;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


}
