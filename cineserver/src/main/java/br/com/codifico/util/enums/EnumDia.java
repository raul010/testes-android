package br.com.codifico.util.enums;

public enum EnumDia {
	HOJE(0), AMANHA(1), DEPOIS(2);
	
	private int dia;
	
	private EnumDia(int i) {
		this.dia = i;
	}

	public int getDia() {
		return dia;
	}
}
