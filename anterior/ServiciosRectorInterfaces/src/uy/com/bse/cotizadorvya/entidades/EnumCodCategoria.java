package uy.com.bse.cotizadorvya.entidades;


public enum EnumCodCategoria {
	PROTECCION("P"), 
	AHORRO_PROTECCION("A"), 
	TODOS("T");

	private final String codCategoria;

	private EnumCodCategoria(String codCategoria) {
		this.codCategoria = codCategoria;
	}

	public String getStringValue() {
		return codCategoria;
	}

	public static EnumCodCategoria getEnumValue(String codCategoria) {
		for (EnumCodCategoria enumValue : EnumCodCategoria.values()) {
			if (enumValue.getStringValue().equals(codCategoria)) {
				return enumValue;
			}
		}
		return null;
	}
}