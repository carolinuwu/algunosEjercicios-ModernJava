package Resumen;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
 

import org.junit.Test;
 

public class TerceraClase{
	 
 
	/*
	 * Se habia mencionado otros metodos como el takeWhile. Este, en contraposicion al filter, me permite
	 * no tener que recorrer todo el Stream sino simplemente recorrerlo mientras que un determinado predicate sea verdadero
	 * y quedarme entonces con los elementos que lo cumplen. El recorrido interno finaliza cuando se evalua como falso.
	 * Es menester que los elementos esten ordenados, analogamente a como lo haria en una busqueda binaria.
	 * Ej : aca quiero imprimir solo los q son multiplos de 3
	 */
	 static List<Integer> nros = new ArrayList<>(Arrays.asList(3,6,9,20,45,11,23,45));
	 
	 @Test
	public void mostrarMultiplosde3() {
		 System.out.println("MUESTRO LOS MULTIPLOS DE 3 ");
		nros.stream().takeWhile(nro->nro%3==0).forEach(System.out::println);
		 System.out.println("----------------------- ");
	}
	 /*
	  * Tambien, alternativamente, podria querer quedarme solo con aquellos nros que NO fuesen multiplos de 3
	  * Lo que hago con el dropWhile es quedarme con aquellos elementos en los que el predicate se evalua como falso
	  * pero no se recorre completo el Stream, una vez que se evaluo como falso se queda con todo lo sucesivo.
	  * 
	  */
	 @Test
	 public void mostrarNOMultiplosde3() {
		 System.out.println("MUESTRO LOS NO MULTIPLOS DE 3 ");
		 nros.stream().dropWhile(nro->nro%3==0).forEach(System.out::println);
	 }
	 
 /*
  * Mas operaciones
  * skip-> me pasa por alto los primeros elementos que le indique
  * distinct-> me permite obtener un Stream sin duplicados
  * limit-> me permite especificar cuantos elementos quiero
  * flatMap->me permite aplanar un stream compuesto por varios streams en uno solo
  *  
  * Otro requerimento comun en el procesamiento de datos consiste en determinar si los elementos que conforman una determinada estructura
  * cumplen una determinada propiedad. 
  * Para enfrentar esto la API de Streams provee metodos como allMatch, anyMatch y noneMatch. Estos no precisan a priori procesar
  * todo el Stream para producir un resultado. Abajo dejo ejemplos
  * 	
  *  
  * 
  * 
  */
	 @Test
	 public void testAllMatch() {
		 //Me devuelve si todos los elementos del Stream cumplen el predicate (decidi que sea q todos son multiplos de 3)
		 System.out.println(nros.stream().allMatch(nro->nro%3==0));
	 }
	 @Test
	 public void testAnyMatch() {
		 //me devuelve si encontro un elemento que cumple el predicate
		 System.out.println(nros.stream().anyMatch(nro->nro%3==0));
	 }
	 @Test
	 public void testNoneMatch() {
		 //me devuelve si ningun elemento del stream cumple el predicate
		 System.out.println(nros.stream().noneMatch(nro->nro%3==0));
		 
	 }
	 /*
	  * Los dos metodos restantes en este segmento corresponden a metodos que me permiten obtener un elemento puntual
	  * por ejemplo, el priemr multiplo de 3 de nuestra lista en el primer caso o en el segundo cualquier multiplo de 3
	  */
	 @Test
	 public void testFindFirst() {
		 /*
		  * Me devuelve el primer elemento que cumpla el predicate, no en su forma pura sino contenido en un Optional
		  *  Los optionals son sumamente utiles conceptualmente ya que nos ayudan a tener presente la idea de que puede haber algo o bien no puede haber nada.
		  *  Esto nos hace menos propensos a sufrir NullPointerException por si olvidamos que el retorno puede ser null e invocamos algo
		  *  Proveen un metodo analogo a la verificacion !=null, el isPresent().
		  *  
		  */
		 
		 System.out.println(nros.stream().filter(nro->nro%3==0).findFirst());
	 }
	 @Test
	 public void testFindAny() {
		 //Me devuelve cualquier ocurrencia, no la primera necesariamente, tambien contenida en un Optional
		 System.out.println(nros.stream().filter(nro->nro%3==0).findAny());
		 
	 }
	 /*
	  * reduce()-> me permite combinar los elementos de un stream para obtener un resultado. Un ejemplo podria ser
	  * hacer una adicion de todos mis nros
	  */
	 @Test
	 public void testReduce() {
		 int sumaNumeros = nros.stream().reduce(0, (a,b)->a+b);
		 System.out.println("La sumatoria de todos los numeros en la lista es : " + sumaNumeros);
		 /*
		  * con un enfoque imperativo tendria que ir recorriendo toda la lista e ir acumulando los numeros en una variable acumulador.
		  * reduce() itera internamente tomando a 0 como valor inicial(a), luego 3 se consume y se adicionan ambos
		  * a+b = 0+3-> 3
		  * 3 pasa a ser el nuevo a y el elemento siguiente pasa a ser b
		  * 3+6 = 9;-> a, 20-> b
		  * Este metodo esta explotado, hay una invocacion que no requiere un elemento inicial 
		  * pero retorna un Optional<Integer>
		  */
		 
	 }
	 
	 /*
	  * Gracias al reduce puedo obtener el maximo u minimo de un stream
	  */
		 @Test 
		 public void testMaxMin() {
			 Optional<Integer> maximoDelStream = nros.stream().reduce(Integer::max);
			 Optional<Integer> minDelStream = nros.stream().reduce(Integer::min);
			 
			 System.out.println(maximoDelStream.isPresent()? maximoDelStream.get() : "NO HAY ELEMENTOS");
			 System.out.println(minDelStream.isPresent()? minDelStream.get() : "NO HAY ELEMENTOS");
			 
		 }
		 
		 /*
		  * 
		  */
	  
 
 
	
	
  
}
