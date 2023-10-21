package Resumen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;

import static java.util.stream.Collectors.toList;

public class SegundaClase {
	 
/*
 * La mayoria de las aplicaciones en Java utilizan colecciones, pero estas
 * no nos permiten manipular sus elementos de forma tan concisa y declarativa como los Streams
 * Los streams son abstracciones que representan un conjunto de elementos que pueden ser procesados de forma
 * tanto paralela como secuencial. Asimismo, permiten manipular la data de forma mas concisa, legible, menos propensa a errores
 * Supongamos que quiero quedarme solo con multiplos de 5 de los nros de esta lista : 
 */
	private static List<Integer> listaDeNros = new ArrayList<>(Arrays.asList(45,9,3,4,15,20));
	
	/*
	 * Para enfatizar uno de los principales beneficios (a mi criterio) de este enfoque, voy a hacer lo del filtrado de numeros usando 
	 * la perspectiva imperativa
	 * 
	 */
	public void procesarImperativamente() {
	Iterator<Integer> iteradorNros = listaDeNros.iterator();
	List<Integer> multiplosde5 = new ArrayList<>();
	 
	while (iteradorNros.hasNext()) {
		Integer nro = iteradorNros.next();
		if (nro%5==0) {
			multiplosde5.add(nro);
		}
	}
	for (Integer i: multiplosde5) {
		System.out.println(i);
	}}
	 
	@Test
	public void procesarconStream() {
 
		System.out.println("ANTES DE PROCESAR");
		listaDeNros.forEach(System.out::println);
		/*
		 * Para quedarme con aquellos nros que sean multiplos de 5 utilizo una funcion ampliamente usada en la introduccion a Streams,
		 * filter. Esta recibe un Predicate, como habiamos visto antes en la funcion para filtrar a las personas
		 */
		System.out.println("-------------------------");
		System.out.println("LUEGO DE PROCESAR");
		listaDeNros.stream()
		.filter(i->i%5==0)
		.forEach(System.out::println);
		
		/*
		 * Utilizar este enfoque me permite ademas prescindir de usar una iteracion externa
		 * internamente el propio stream se preocupa de iterar y aplicarle a cada elemento
		 * la funcion seleccionada 
		 * 
		 * Los streams tienen dos tipos de operaciones, las intermedias (filter,map,dropWhile,skip, y otras mas q usaremos)
		 * y las terminales (forEach, toList, count, close,etc)
		 * 
		 * Lo que diferencia a ambas grosso modo es q las ultimas no retornan un stream.
		 * Ademas, garantizan que se vuelvan efectivas las operaciones intermedias (si no utilizo una operacion terminal los elementos no se procesan!)
		 * Las operaciones intermedias mas utilizadas en principio son filter y
		 *  map(esta ultima recibe una implementacion del metodo definido en Function el cual q permite recibir A y retornar B (a partir de A), 
		 *   un ejemplo podria ser si tengo un stream de personas quedarme con sus nombres meramente
		 *   	System.out.println("-----------------------------");
		//Con esto solo me voy a quedar con el nombre de las personas
		Stream<Persona> personas = Stream.of(new Persona("Raul",25, 'M', false),
				new Persona("Raquel",65, 'M', false));
		personas.map(Persona::getNombre).forEach(System.out::println);
		
		Una operacion terminal comun a utilizar va a ser collect, la cual es un metodo de la interfaz Stream
		q me permite acumular o recolectar elementos en un stream, para producir una estructura de datos determinados (ej un Array, una Lista, etc)
	 
		 
		 * 		 
		 * 
		 * 
		 */
	 
		 
		
  
	}
	
	
	}
