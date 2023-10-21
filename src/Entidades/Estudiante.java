package Entidades;

public record Estudiante(String nape, Ciudad ciudad) {

	public enum Ciudad{
		BUENOS_AIRES, CORDOBA,SANTA_FE;
	}
}
