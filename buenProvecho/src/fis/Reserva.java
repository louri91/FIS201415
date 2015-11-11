package fis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Reserva {
	private Date fecha;
	private int numeroComensales;
	private Restaurante restaurante;
	private Usuario usuario;
	private boolean confirmada;
	private String codigoReserva;

	void crear(Usuario unUsuario, Restaurante unRestaurante, Date fecha,
			int numeroComensales, String codigoReserva) {
		this.restaurante = unRestaurante;
		this.usuario = unUsuario;
		this.fecha = fecha;
		this.numeroComensales = numeroComensales;
		this.confirmada = false;
		this.codigoReserva = codigoReserva;
	}

	void eliminarDelRestaurante() {
		this.restaurante.eliminarReserva(this);
	}

	List<String> obtenerDatosReserva() {
		List<String> aux = new ArrayList<String>();
		aux.add(this.codigoReserva);
		aux.add(this.restaurante.toString());
		aux.add(this.usuario.toString());
		aux.add(this.fecha.toString());
		aux.add("" + this.numeroComensales);
		aux.add("" + this.confirmada);
		return aux;
	}

	boolean yaPasada() {
		Date hoy = new Date();
		if (this.fecha.before(hoy)) {
			return true;
		} else {
			return false;
		}
	}

	void confirmar() {
		confirmada = true;
	}

	@Override
	public String toString() {
		return "Reserva [fecha=" + fecha + ", numeroComensales="
				+ numeroComensales + ", confirmada=" + confirmada
				+ ", codigoReserva=" + codigoReserva + "]";
	}

	public Date getFecha() {
		return fecha;
	}

	public int getNumeroComensales() {
		return numeroComensales;
	}

	public boolean isConfirmada() {
		return confirmada;
	}

	public String getCodigoReserva() {
		return codigoReserva;
	}

	public Restaurante getRestaurante() {
		return restaurante;
	}

	public Usuario getUsuario() {
		return usuario;
	}

}
