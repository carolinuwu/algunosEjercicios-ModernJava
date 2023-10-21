package Resumen;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import Overview.Practica.Trader;
import Overview.Practica.Transaction;
public class CuartaClase {
	//De nuevo, esto lo hice asi por una cuestion de practicidad. 
	/*
	 * Corresponde al ejercicio de la pagina 150 para poner en practica lo anteriormente visto
	 * 
	 */
	
		public record Trader(String nombre, String ciudad) {
			 public boolean soydeCambridge() {return this.ciudad.equals("Cambridge");}
			 public boolean soydeMilan() {return this.ciudad.equals("Milan");}
		}
		public record Transaction(Trader trader, int anio, int monto){
			public boolean miTraderEsDeCambridge(){return trader.soydeCambridge();}
			public boolean miTraderEsDeMilan() {return trader.soydeMilan();}

		}

		//EJERCICIO DE PRACTICA DEL CAP 4!

		static Trader raoul = new Trader("Raoul", "Cambridge");
		static Trader mario = new Trader("Mario","Milan");
		static Trader alan = new Trader("Alan","Cambridge");
		static Trader brian = new Trader("Brian","Cambridge");
		static List<Transaction> transacciones = Arrays.asList(
				 new Transaction(brian, 2011, 300),
				 new Transaction(raoul, 2012, 1000),
				 new Transaction(raoul, 2011, 400),
				 new Transaction(mario, 2012, 710),
				 new Transaction(mario, 2012, 700),
				 new Transaction(alan, 2012, 950)
				);

		public static void main(String[]args) {
			/*
			 * Practice : 
			 * 1 Find all transactions in the year 2011 and sort them by value (small to high).
2 What are all the unique cities where the traders work?
3 Find all traders from Cambridge and sort them by name.
4 Return a string of all traders’ names sorted alphabetically.
5 Are any traders based in Milan?
6 Print the values of all transactions from the traders living in Cambridge.
7 What’s the highest value of all the transactions?
8 Find the transaction with the smallest value.

			 */
			//1. Me pide q retorne todas las transacciones en 2011 ordenadas en orden ascendente por el monto de transaccion.

			List<Transaction> transacciones2011Ordenadas = transacciones.stream()
					.filter((transaccion)->transaccion.anio==2011)
					.sorted((Transaction transaccion1, Transaction transaccion2)-> transaccion1.monto-transaccion2.monto)
					.collect(toList());
			System.out.println("TRANSACCIONES DE 2011 ORDENADAS POR PRECIO EN FORMA ASCENDENTE");
			transacciones2011Ordenadas.forEach(transaccion->System.out.println(transaccion));

			//2. Me pide las   ciudades (sin repetidos) en las q los traders "trabajan"

			List<String> ciudadesSinRepetidos = transacciones.stream()
					.map((transaccion)-> transaccion.trader.ciudad)
					.distinct()
					.collect(toList());

			System.out.println("LAS CIUDADES SON :");
			ciudadesSinRepetidos.forEach(ciudad->System.out.println(ciudad));

			//3. Me pide q encuentre todos los traders de Cambridge y q los ordene por nombre

			List<Transaction> ordenadas =
			transacciones.stream()
			.sorted((Transaction uno, Transaction dos)->uno.trader.ciudad.compareTo(dos.trader.ciudad))
			.toList();

			List<Trader> ordenadosYdeCambridge=
			transacciones.stream()
			.takeWhile((Transaction uno)->uno.miTraderEsDeCambridge())
			.sorted((Transaction uno, Transaction dos)->uno.trader.nombre.compareTo(dos.trader.nombre))
	        .map(transacction->transacction.trader)
			.toList();

			ordenadosYdeCambridge.forEach(transaction->System.out.println(transaction));
			System.out.println("-------------------------------------");
			//4 me pide q retorne un string q contenga los nombres de todos los traders ordenado alfabeticamente
			//y q no admita duplicados
			String nombres = transacciones.stream()
					.map(Transaccion->Transaccion.trader.nombre)
					.sorted()
					.distinct()
					.collect(Collectors.joining(" "));
			System.out.println("-----------------");
			 System.out.println(nombres);

			//5. Pregunta si hay algun trader de milan

			 Optional<Trader> algunTraderEnMilan = transacciones.stream()
					    .map(transaccion -> transaccion.trader)
					    .filter(trader -> trader.soydeMilan())
					    .findAny();
			System.out.println(algunTraderEnMilan.isPresent() ? "Si, hay al menos un trader de milan  " + algunTraderEnMilan.get(): "No");
			System.out.println("-------------------------------------");
			//6. Me pide q muestre todos los valores de todas las transacciones de traders de Cambridge
			System.out.println("VALORES DE TODAS LAS TRANSACCIONES DE TRADERS DE CAMBRIDGE");
			  ordenadas.stream()
			  .filter(transaction->transaction.miTraderEsDeCambridge())
			  .map(transaction->transaction.monto)
			  .forEach(monto->System.out.println("El monto es : " + monto));

				System.out.println("-------------------------------------");

			//7.Me pide la transaccion con mayor valor
			  System.out.println("TRANSACCION DE MAYOR VALOR : ");
			final String MSJ_NO_TRANSACCIONES = "No hay transacciones";
			Optional<Integer> maximoValorDeTransaccion = transacciones.stream()
					.map(transaction->transaction.monto)
					.reduce(Integer::max);
			System.out.println(maximoValorDeTransaccion.isPresent()?"El maximo valor de transaccion es : " + maximoValorDeTransaccion.get() : MSJ_NO_TRANSACCIONES);

			//8. Me pide la transaccion de menor valor
			System.out.println("-------------------------------------");
			System.out.println("TRANSACCION DE MENOR VALOR : ");

			Optional<Integer> minValorTransaccion = transacciones.stream()
					.map(transaction->transaction.monto)
					.reduce(Integer::min);
			System.out.println(minValorTransaccion.isPresent()?"El minimo valor de transaccion es " + minValorTransaccion.get() : MSJ_NO_TRANSACCIONES);


		}


	}