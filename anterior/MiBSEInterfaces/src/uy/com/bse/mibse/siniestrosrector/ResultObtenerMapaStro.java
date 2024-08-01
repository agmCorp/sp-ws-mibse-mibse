package uy.com.bse.mibse.siniestrosrector;

import java.util.ArrayList;
import java.util.List;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerMapaStro extends ResultGenerico {

	private static final long serialVersionUID = -6640901525846711316L;
	private Integer anio;
    private String categoriaTaller;
    private Integer certificado;
    private Integer codigoTaller;
    private Integer endoso;
    private String estadoReclamo;
    private String fechaEstadoReclamo;
    private String fechaOcurrencia;
    private List<ResultMapaStro> mapaStros;
    
	private String nombreTaller;
    private Integer nroDenuncia;
    private Integer nroReclamo;
    private Integer poliza;
    private Integer ramo;
    private String reclamosEnParalelo;
    private String rutaMapa;
    private String serie;
    private Integer stro;
    private Integer substro;
    private Integer sucursalReclamo;
    
    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer value) {
        this.anio = value;
    }

    public String getCategoriaTaller() {
        return categoriaTaller;
    }

    public void setCategoriaTaller(String value) {
        this.categoriaTaller = value;
    }

    public Integer getCertificado() {
        return certificado;
    }

    public void setCertificado(Integer value) {
        this.certificado = value;
    }

    public Integer getCodigoTaller() {
        return codigoTaller;
    }

    public void setCodigoTaller(Integer value) {
        this.codigoTaller = value;
    }

    public Integer getEndoso() {
        return endoso;
    }

    public void setEndoso(Integer value) {
        this.endoso = value;
    }

    public String getEstadoReclamo() {
        return estadoReclamo;
    }

    public void setEstadoReclamo(String value) {
        this.estadoReclamo = value;
    }

    public String getFechaEstadoReclamo() {
        return fechaEstadoReclamo;
    }

    public void setFechaEstadoReclamo(String value) {
        this.fechaEstadoReclamo = value;
    }

    public String getFechaOcurrencia() {
        return fechaOcurrencia;
    }

    public void setFechaOcurrencia(String value) {
        this.fechaOcurrencia = value;
    }

    public List<ResultMapaStro> getMapaStros() {
        if (mapaStros == null) {
            mapaStros = new ArrayList<ResultMapaStro>();
        }
        return this.mapaStros;
    }
    
    public void setMapaStros(List<ResultMapaStro> mapaStros) {
		this.mapaStros = mapaStros;
	}


    public String getNombreTaller() {
        return nombreTaller;
    }

    public void setNombreTaller(String value) {
        this.nombreTaller = value;
    }

    public Integer getNroDenuncia() {
        return nroDenuncia;
    }

    public void setNroDenuncia(Integer value) {
        this.nroDenuncia = value;
    }

    public Integer getNroReclamo() {
        return nroReclamo;
    }

    public void setNroReclamo(Integer value) {
        this.nroReclamo = value;
    }

    public Integer getPoliza() {
        return poliza;
    }

    public void setPoliza(Integer value) {
        this.poliza = value;
    }

    public Integer getRamo() {
        return ramo;
    }

    public void setRamo(Integer value) {
        this.ramo = value;
    }

    public String getReclamosEnParalelo() {
        return reclamosEnParalelo;
    }

    public void setReclamosEnParalelo(String value) {
        this.reclamosEnParalelo = value;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String value) {
        this.serie = value;
    }

    public Integer getStro() {
        return stro;
    }

    public void setStro(Integer value) {
        this.stro = value;
    }

    public Integer getSubstro() {
        return substro;
    }

    public void setSubstro(Integer value) {
        this.substro = value;
    }

    public Integer getSucursalReclamo() {
        return sucursalReclamo;
    }

    public void setSucursalReclamo(Integer value) {
        this.sucursalReclamo = value;
    }

	public String getRutaMapa() {
		return rutaMapa;
	}

	public void setRutaMapa(String rutaMapa) {
		this.rutaMapa = rutaMapa;
	}



}
