package Entidades;
 
public record Persona(String nombre, int edad, char genero, boolean tengoHijos) {
	/*
	 * Hice esto asi por uan cuestion de practicidad meramente, 
	 * los records son posteriores a java 8
	 */
	
	public String getNombre() {return this.nombre;}
	 
	public boolean tengoHijos() {return this.tengoHijos;}

	public boolean soyMayorde25() {
		return this.edad>25;
	}

	public boolean soydeGeneroFemenino() {
		return this.genero=='F';
	}

	public boolean coincideNombre(String nombre) {
		return this.nombre.equals(nombre);
	}

	 

	/*
	 * En si los records proveen la gran facilidad de implementar un equals que compara en base a los atributos de los objetos
	 * en lugar de direcciones de memoria, pero queria dejar esto porque 
	 * me parecio interesante. En el libro se mencionaba que en Java 8
	 * no existia la posibilidad de no downcastear para hacer toda esta comparacion luego de usar el operador instanceof
	 *  Sin embargo, en versiones ulteriores a Java 8 se permitio prescindir
	 * de dicho casteo simplemente usando una variable 
	 * Ej
	 * 	@Override
	public boolean equals(Object obj) {
		 
		//ver PatternMatchingForInstanceOf
	    if (obj == this) {
	        return true;
	    }
	    if ((obj instanceof Persona persona)) {
	        return persona.nombre.equals(this.nombre) && persona.tengoHijos == this.tengoHijos && persona.genero==this.genero
	        		&& persona.edad==this.edad;
	    }
	     return false;
	}
	
	 */
 
}
