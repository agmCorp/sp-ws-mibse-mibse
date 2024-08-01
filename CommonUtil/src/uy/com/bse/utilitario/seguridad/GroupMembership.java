package uy.com.bse.utilitario.seguridad;

public class GroupMembership {
	
	private String errorCode;
	private boolean member;
	
	public boolean isMember() {
		return member;
	}
	public void setMember(boolean member) {
		this.member = member;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
