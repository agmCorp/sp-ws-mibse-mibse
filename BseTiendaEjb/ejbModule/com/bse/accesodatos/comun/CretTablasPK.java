package com.bse.accesodatos.comun;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CretTablasPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "CRTB_CD_TABLA")
    private Integer tabla;

    @Column(name = "CRTB_INDEX")
    private String index;

    public CretTablasPK() {
    }

    public Integer getTabla() {
		return tabla;
	}

	public void setTabla(Integer tabla) {
		this.tabla = tabla;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

    
}
