package fis;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Restaurante {
	private String nombre;
	private String provincia;
	private String localidad;
	private String direccion;
	private String telefono;
	private String tipoCocina;
	private float precioMedio;
	private String descripcion;
	private String horario;
	private Estado estado;
	private ResponsableRestaurante responsable;
	private List<Reserva> reservasRestaurante = new ArrayList<Reserva>();

	void crear(String nombre, String provincia, String localidad,
			String direccion, String telefono) {
		this.nombre = nombre;
		this.provincia = provincia;
		this.localidad = localidad;
		this.direccion = direccion;
		this.telefono = telefono;
		this.estado = Estado.PendienteAlta;
	}

	void incluir(ResponsableRestaurante responsable) {
		this.responsable = responsable;
	}

	boolean pendienteAlta() {
		if (this.estado.equals(Estado.PendienteAlta)) {
			return true;
		} else
			return false;
	}

	String obtenerDatosRestaurante() {
		return "Restaurante [nombre=" + nombre + ", provincia=" + provincia
				+ ", localidad=" + localidad + ", direccion=" + direccion
				+ ", telefono=" + telefono + ", tipoCocina=" + tipoCocina
				+ ", precioMedio=" + precioMedio + ", descripcion="
				+ descripcion + ", horario=" + horario + "]";
	}

	void activarRestaurante() {
		this.estado = Estado.Activo;
	}

	void eliminarReserva(Reserva reserva) {
		if(reservasRestaurante.contains(reserva)){
			reservasRestaurante.remove(reserva);
		}
	}

	void registrarReserva(Reserva reserva) {
		reservasRestaurante.add(reserva);
	}

	void actualizarRestaurante(String tipoCocina, float precioMedio,
			String descripcion, String horario) {
		this.tipoCocina = tipoCocina;
		this.precioMedio = precioMedio;
		this.descripcion = descripcion;
		this.horario = horario;
	}

	void confirmarReserva(String codigoReserva) {
		for(Reserva reserva : reservasRestaurante){
			if(reserva.getCodigoReserva().equals(codigoReserva)){
				reserva.confirmar();
			}
		}
	}

	private Reserva buscarReserva(String codigoReserva) {
		Reserva reservaAuxiliar = null;
		for(Reserva reserva : reservasRestaurante){
			if(reserva.getCodigoReserva().equals(codigoReserva)){
				reservaAuxiliar = reserva;
			}
		}
		return reservaAuxiliar;
	}

	@Override
	public String toString() {
		return "Restaurante [nombre=" + nombre + ", provincia=" + provincia
				+ ", localidad=" + localidad + ", direccion=" + direccion
				+ ", telefono=" + telefono + ", tipoCocina=" + tipoCocina
				+ ", precioMedio=" + precioMedio + ", descripcion="
				+ descripcion + ", horario=" + horario + "]";
	}

	public String getNombre() {
		return nombre;
	}

	public String getProvincia() {
		return provincia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getTipoCocina() {
		return tipoCocina;
	}

	public float getPrecioMedio() {
		return precioMedio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getHorario() {
		return horario;
	}

	public Estado getEstado() {
		return estado;
	}

	public List<Reserva> getReservasRestaurante() {
		return reservasRestaurante;
	}
	
	

}
