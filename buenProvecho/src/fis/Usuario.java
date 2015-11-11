package fis;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

	private String nombre;
	private String correo;
	private String telefono;
	private List<Reserva> misReservas;

	void crear(String nombreUsuario, String correo, String telefono) {
		this.nombre = nombreUsuario;
		this.correo = correo;
		this.telefono = telefono;
		misReservas = new ArrayList<Reserva>();
	}

	boolean tienesReserva() {
		if (misReservas.size() != 0) {
			return true;
		} else {
			return false;
		}
	}

	void anularReserva(Reserva codigoReserva) {
		/**
		 * Recorremos la lista de reservas del usuario si encontramos una
		 * reserva que coincide con el numero de reserva que proporcionamos, lo
		 * eliminamos En caso de no encontrar el codigo de reserva, devolvemos
		 * un error.
		 */
		boolean encontrada = false;
		
		if (misReservas.contains(codigoReserva)) {
			misReservas.remove(codigoReserva);
			encontrada = true;
		}

		if (!encontrada) {
			System.out.println("[ERROR]. No se ha encontrado ninguna reserva.");
		} else {
			System.out.println("Se ha anulado la reserva correctamente.");
		}
	}

	void incluirReserva(Reserva miReserva) {
		misReservas.add(miReserva);
	}

	List<Reserva> consultarMisReservas() {
		return misReservas;
	}

	void eliminarReservasPasadas() {
		for (Reserva reserva : misReservas) {
			if (reserva.yaPasada()) {
				misReservas.remove(reserva);
			}
		}
	}

	private Reserva buscarReserva(String codigoReserva) {
		Reserva aux = null;
		for (Reserva reserva : misReservas) {
			if (reserva.getCodigoReserva().equals(codigoReserva)) {
				aux = reserva;
			}
		}
		return aux;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", correo=" + correo
				+ ", telefono=" + telefono + "]";
	}

	public String getNombre() {
		return nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public List<Reserva> getMisReservas() {
		return misReservas;
	}

}
