package Resumen;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import Entidades.Persona;

 
 

public class PrimerClase{
/*
 En principio se menciona que un problema harto conocido en la ingenieria de software es que, 
 no importa lo que hagas,
 los requerimientos del usuario van a cambiar. 
 Se pone como ejemplo una hipotetica app que ayude a un granjero a manejar su inventario. 
 Este granjero en principio podria solicitar una funcionalidad que le permitiese quedarse, por separado,con las manzanas verdes de su inventario.
 Luego, otro dia, podria pedir una lista separada con las que pesan mas de 150 gramos. A la otra semana que se permita tener una lista de todas las rojas
Si hiciese esto sin este enfoque tendria que hacer mucho codigo redundante(o boilerplate) con una misma funcionalidad subyacente.
A este respecto se menciona el enfoque de behavior parametrizacion, un patron de software que me permite manejar los requerimentos cambiantes de forma mas flexible, concisa, mantenible 
Se separa la logica de la iteracion de la del filtrado.
 
  En este ejemplo filtro a las personas que tienen hijos de las que no
  IMPORTANTE : Java 8 permite que las funciones pasen a ser valores de primer orden, 
  previamente para realizar algo como esto habia que usar 
  clases anonimas 
  
  (Por ejemplo, en este caso donde filtro a las personas que tienen hijos
   List<Persona> personasConHijos=  filtrarConPredicate(new Predicate<Persona>() {
	@Override
	public boolean test(Persona persona) {
		// TODO Auto-generated method stub
		return persona.tengoHijos();
	}
	 
 }
 );
 });
  Java 8 me permite mediante las expresiones lambda
 directamente pasar una funcion anonima , aminorando lo verborragico.
 Una expresion lambda se puede entender como una representacion mas concisa de una funcion anonima que puede ser enviada por parametro.
Dicha funcion no tiene un nombre, pero tiene una lista de parametros, un cuerpo, un retorno y una lista de posibles excepciones a ser lanzadas.
 
 
Es importante respetar esto, sino ocurriran errores al querer ejecutar el programa (por ejemplo, el run de los runnables es void y no recibe parametros, no podemos solicitarle que retorne nada ni reciba nada
Tanto el lambda como la clase anonima si o si tienen que respetar la firma del metodo)
Ademas, es importante tener en cuenta que en Java al menos(segun entiendo)
 estos lambdas solo se pueden utilizar como implementaciones directas de metodos definidos en interfaces funcionales (entiendo que  estas son interfaces que poseen dicha notacion @FunctionalInterface, y 
 poseen un solo metodo abstracto pudiendo contar con metodos default u otros)
 
		 *
		  
 */ 	 static List<Persona> personas = List.of(new Persona("Caro", 25, 'F',false),new Persona("Rodri", 26, 'M',false),new Persona("Papi", 56, 'M',true),new Persona("Mami", 56, 'F',true));
	
	public static void main(String[]args) {
	 System.out.println("Personas sin filtrar");
	 personas.forEach(System.out::println);
 System.out.println("---------------------------");
 
 System.out.println("Personas filtradas");
		 
	 //filtro utilizando el enfoque de behavior parametrization
 /*
  * Una de las utilidades que le veo en contraposicion a 
  * simplemente iterar por la lista y alli añadir elementos en base al retorno del tengoHijos()
  *  es que
  *  si a futuro quiero filtrar, como comentaba, por varios atributos distintos, simplemente la invoco de forma diferente,
  *  pero se mantiene tal cual esta.
  *  
  */

 List<Persona> listaFiltradadeLosqTienenHijos =	filtrarConPredicate(Persona::tengoHijos);
 listaFiltradadeLosqTienenHijos.forEach(p->System.out.println(p));
	/*
	 * recordar que :: es intercambiable con usar el operador ->
	 * el mismo se utiliza para crear una expresion lambda
	 * , y :: indica una referencia de metodo
 
	 */
 
 
 
 
  
  
 
		
	}
	private static List<Persona> filtrarConPredicate(Predicate<Persona>p) {
		List<Persona> listaFiltrada = new ArrayList<>();
		for (Persona persona : personas ) {
			if (p.test(persona)) {
				listaFiltrada.add(persona);
			}
		}

		return listaFiltrada;

	}
 
}
