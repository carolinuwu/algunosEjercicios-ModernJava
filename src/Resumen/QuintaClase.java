package Resumen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;
 
import org.junit.Test;
 
import Entidades.Estudiante;
import Entidades.Estudiante.Ciudad;
import Entidades.Vehiculo;
import Entidades.Vehiculo.ColorVehiculo;
import Entidades.Vehiculo.EstadoMotor;
import Entidades.Vehiculo.TipoVehiculo;

public class QuintaClase {

	/* 
	 * Se utilizara en principio 
	 * el metodo groupingBy() el cual me permite agrupar elementos acorde a alguna propiedad
	 * de forma declarativa, retornandose un Map.
	 * Para esto sera preciso utilizar un collect
	 * 
	 * 
	 */
	 
	 
	static List<Estudiante> estudiantes = Arrays.asList(
			new Estudiante("Caro", Ciudad.values()[0]),
			new Estudiante("Catalina", Ciudad.values()[0]),
			new Estudiante("Jorge", Ciudad.values()[0]),
			new Estudiante("Pablo", Ciudad.values()[1])
			);
	
	@Test
	public void enfoqueImperativoParaAgrupar() {
		//Como vemos, este enfoque es mas verboso.
		
	    Map<Estudiante.Ciudad, List<Estudiante>> estudiantesXciudad = new HashMap<>();
	    for (Estudiante estudiante : estudiantes) {
	        Ciudad ciudadEstudiante = estudiante.ciudad(); 
	        if (estudiantesXciudad.containsKey(ciudadEstudiante)) {
	            estudiantesXciudad.get(ciudadEstudiante).add(estudiante);
	        } else {
	            List<Estudiante> estudiantesEnNuevaCiudad = new ArrayList<>();
	            estudiantesEnNuevaCiudad.add(estudiante);
	            estudiantesXciudad.put(ciudadEstudiante, estudiantesEnNuevaCiudad);
	        }
	    }
	   
	    System.out.println("----------------------------");
	    
	}
	
	@Test
	public void enfoqueDeclarativoParaAgrupar() {
		Map<Estudiante.Ciudad, List<Estudiante>> estudiantesXciudad =  
		 estudiantes.stream().collect(groupingBy(Estudiante::ciudad));
		 
		 estudiantesXciudad.forEach((ciudad, lista) -> {
		        System.out.println(ciudad);
		        lista.forEach(estudiante -> System.out.println("\t" + estudiante));
		    });
		  System.out.println("----------------------------");
	}
	/*
	 *	/*
	 * Partitioning is a special case of grouping:
	 * having a predicate called a partitioning function as a classification function.
	 *  The fact that the partitioning function returns a boolean means
	 *  the resulting grouping Map will have a Boolean as a key type,
	 *   and therefore,there can be at most two different groups—one for true
	 *    and one for false.  

	 */
	 
	@Test
	public void testPartitioning() {
		System.out.println("TEST PARTITIONING BY");
		//Me quedo con los que son de buenos aires y los muestro
		Map<Boolean, List<Estudiante>> estudiantesPartitioning = 
				estudiantes.stream()
				.collect(partitioningBy(e->e.ciudad()==Ciudad.BUENOS_AIRES));
	
		 estudiantesPartitioning.get(true).forEach(e->System.out.println(e));
				 
		
	}
	/*
	 * Tambien puedo agrupar en base a mas cosas, por ejemplo si tengo una lista  de vehiculos
	 * puedo agruparlos en base a su tipo y estado del motor
	 * en version declarativa seria :
	 * 
	 * 
	 */
	 @Test
	public void agruparVehiculosPorTipoYEstadoDelMotor() {
		List<Vehiculo> vehiculos =  obtenerVehiculos();
		System.out.println("----------------------------------------");

		System.out.println("GROUPING BY POR TIPO DE VEHICULO Y ESTADO DEL MOTOR ");
		//GROUPING BY COMPUESTO
		Map<TipoVehiculo, Map<EstadoMotor, List<Vehiculo>>> vehiculosPorTipoYporEstado =	vehiculos.stream()
		.collect(groupingBy(Vehiculo::getTipo,groupingBy(Vehiculo::getEstado)));
		
		vehiculosPorTipoYporEstado.forEach((tipoVehiculo, mapa) -> {
		    System.out.println("Tipo de Vehículo: " + tipoVehiculo);
		    mapa.forEach((estadoMotor, lista) -> {
		        System.out.println("Estado del Motor: " + estadoMotor);
		        lista.forEach(vehiculo -> {
		            System.out.println(vehiculo);
		        });
		    });
		});
		
		
	}
	@Test
	public void agruparVehiculosPorColorTipoYEstadoDelMotor() {
		List<Vehiculo> vehiculos = obtenerVehiculos();
		System.out.println("GROUPING BY POR COLOR, TIPO DE VEHICULO Y ESTADO DEL MOTOR");

		Map<ColorVehiculo,Map<TipoVehiculo, Map<EstadoMotor, List<Vehiculo>>>> vehiculosPorTipoColorYporEstado =
				vehiculos.stream()
				.collect(groupingBy(Vehiculo::getColor,groupingBy(Vehiculo::getTipo
						, groupingBy(Vehiculo::getEstado))));

		vehiculosPorTipoColorYporEstado.forEach(
				(color,Primermapa)->{
					System.out.println("Color del vehiculo : " + color);
					Primermapa.forEach((tipoVehiculo,segundoMapa)->{
						System.out.println("tipo de vehiculo " + tipoVehiculo);
						segundoMapa.forEach((estado,lista)->{
							System.out.println("estado del vehiculo : " + estado);
							System.out.println("MUESTRO VEHICULO");
							lista.forEach(vehiculo->System.out.println(vehiculo));
						});
					});
				});

	}


	private static List<Vehiculo> obtenerVehiculos() {
		List<Vehiculo> vehiculos = List.of(
				new Vehiculo(TipoVehiculo.AUTOMOVIL, EstadoMotor.APAGADO, ColorVehiculo.ROJO),
	            new Vehiculo(TipoVehiculo.MOTOCICLETA, EstadoMotor.ENCENDIDO, ColorVehiculo.AZUL),
	            new Vehiculo(TipoVehiculo.MOTOCICLETA, EstadoMotor.ENCENDIDO, ColorVehiculo.AZUL),
	            new Vehiculo(TipoVehiculo.MOTOCICLETA, EstadoMotor.ENCENDIDO, ColorVehiculo.AZUL),
	            new Vehiculo(TipoVehiculo.CAMIONETA, EstadoMotor.EN_ESPERA, ColorVehiculo.VERDE),
	            new Vehiculo(TipoVehiculo.AUTOMOVIL, EstadoMotor.APAGADO, ColorVehiculo.BLANCO),
	            new Vehiculo(TipoVehiculo.MOTOCICLETA, EstadoMotor.ENCENDIDO, ColorVehiculo.NEGRO),
	            new Vehiculo(TipoVehiculo.CAMIONETA, EstadoMotor.EN_ESPERA, ColorVehiculo.ROJO),
	            new Vehiculo(TipoVehiculo.AUTOMOVIL, EstadoMotor.APAGADO, ColorVehiculo.AZUL),
	            new Vehiculo(TipoVehiculo.MOTOCICLETA, EstadoMotor.APAGADO, ColorVehiculo.VERDE),
	            new Vehiculo(TipoVehiculo.CAMIONETA, EstadoMotor.ENCENDIDO, ColorVehiculo.BLANCO),
	            new Vehiculo(TipoVehiculo.AUTOMOVIL, EstadoMotor.ENCENDIDO, ColorVehiculo.NEGRO));
		return vehiculos;
		
	}

	//Hago esto meramente para contraponer esta version a la anterior.
	private static void versionImperativaDelGroupingBy() {
		List<Vehiculo> vehiculos = obtenerVehiculos();
	//1 ANIDAMIENTO
		Map<Vehiculo.TipoVehiculo, List<Vehiculo>> vehiculosPorTipo = new HashMap<>();

		for (Vehiculo vehiculo : vehiculos) {
		    TipoVehiculo tipo = vehiculo.getTipo();
		    if (!vehiculosPorTipo.containsKey(tipo)) {
		        vehiculosPorTipo.put(tipo, new ArrayList<>());
		    }
		    vehiculosPorTipo.get(tipo).add(vehiculo);
		}

		//2 ANIDAMIENTOS
		Map<TipoVehiculo, Map<EstadoMotor, List<Vehiculo>>> vehiculosPorTipoYporEstado = new HashMap<>();

		for (Vehiculo vehiculo : vehiculos) {
		   TipoVehiculo tipo = vehiculo.getTipo();
		   Vehiculo.EstadoMotor estado = vehiculo.getEstado();

		    if (!vehiculosPorTipoYporEstado.containsKey(tipo)) {
		        vehiculosPorTipoYporEstado.put(tipo, new HashMap<>());
		    }

		    if (!vehiculosPorTipoYporEstado.get(tipo).containsKey(estado)) {
		        vehiculosPorTipoYporEstado.get(tipo).put(estado, new ArrayList<>());
		    }

		    vehiculosPorTipoYporEstado.get(tipo).get(estado).add(vehiculo);
		}

		//3 ANIDAMIENTOS

		Map<ColorVehiculo, Map<TipoVehiculo, Map<EstadoMotor, List<Vehiculo>>>> vehiculosPorTipoColorYporEstado = new HashMap<>();

		for (Vehiculo vehiculo : vehiculos) {
		    ColorVehiculo color = vehiculo.getColor();
		    TipoVehiculo tipo = vehiculo.getTipo();
		    EstadoMotor estado = vehiculo.getEstado();

		    if (!vehiculosPorTipoColorYporEstado.containsKey(color)) {
		        vehiculosPorTipoColorYporEstado.put(color, new HashMap<>());
		    }

		    if (!vehiculosPorTipoColorYporEstado.get(color).containsKey(tipo)) {
		        vehiculosPorTipoColorYporEstado.get(color).put(tipo, new HashMap<>());
		    }

		    if (!vehiculosPorTipoColorYporEstado.get(color).get(tipo).containsKey(estado)) {
		        vehiculosPorTipoColorYporEstado.get(color).get(tipo).put(estado, new ArrayList<>());
		    }

		    vehiculosPorTipoColorYporEstado.get(color).get(tipo).get(estado).add(vehiculo);
		}



	}
 
	 

		
	}
	 
 
