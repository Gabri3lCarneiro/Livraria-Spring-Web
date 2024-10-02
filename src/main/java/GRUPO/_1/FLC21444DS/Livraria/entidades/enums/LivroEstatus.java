package GRUPO._1.FLC21444DS.Livraria.entidades.enums;

public enum LivroEstatus {

	DISPONIVEL(1),
	RESERVADO(2);
	
	private int codigo;
	
	private LivroEstatus(int codigo) {
		this.codigo = codigo;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public static LivroEstatus valueOf(int codigo) {
		for(LivroEstatus valor : LivroEstatus.values()) {
			if(valor.getCodigo() == codigo) {
				return valor;
			}
			
		}
		throw new IllegalArgumentException("Estatos de codigo invalido");
	}
}