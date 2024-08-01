
package uy.com.bse.mibse;

import uy.com.bse.utilitario.dato.ResultGenerico;



public class ResultLogActividadMibseWsExt
    extends ResultGenerico
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -9114189831137067670L;
	private Integer codError;
    private String descError;
    private String sqlError;

  
    public Integer getCodError() {
        return codError;
    }

  
    public void setCodError(Integer value) {
        this.codError = value;
    }

  
    public String getDescError() {
        return descError;
    }

  
    public void setDescError(String value) {
        this.descError = value;
    }

  
    public String getSqlError() {
        return sqlError;
    }

  
    public void setSqlError(String value) {
        this.sqlError = value;
    }

}
