package Entidades;
 

public record Vehiculo(TipoVehiculo tipo ,EstadoMotor estado , ColorVehiculo color) {
	public TipoVehiculo getTipo() {return this.tipo;}
	public EstadoMotor getEstado() {return this.estado;}
	public ColorVehiculo getColor() {return this.color;}

	public enum TipoVehiculo {
	    AUTOMOVIL,
	    MOTOCICLETA,
	    CAMIONETA
	}
	public enum EstadoMotor {
	    ENCENDIDO,
	    APAGADO,
	    EN_ESPERA
	}

	public enum ColorVehiculo {
	    ROJO,
	    AZUL,
	    VERDE,
	    BLANCO,
	    NEGRO
	}
}
