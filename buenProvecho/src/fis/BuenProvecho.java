package fis;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BuenProvecho {
	private List<ResponsableRestaurante> responsables = new ArrayList<ResponsableRestaurante>();
	private List<Restaurante> restaurantes = new ArrayList<Restaurante>();
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	private int codigo = 0;

	private static BuenProvecho instancia;

	public static BuenProvecho getInstance() {
		if (instancia == null) {
			instancia = new BuenProvecho();
		}
		return instancia;
	}

	private BuenProvecho() {
	}

	public void solicitarRegistroRestaurante(String nombreUsuario,
			String nombre, String provincia, String localidad,
			String direccion, String telefono) {
		Restaurante restaurante = new Restaurante();
		restaurante.crear(nombre, provincia, localidad, direccion, telefono);
		restaurantes.add(restaurante);

		if (existeResponsableRestaurante(nombreUsuario)) {
			for (ResponsableRestaurante r : responsables) {
				if (r.getNombreUsuario().equals(nombreUsuario)) {
					r.incluirRestaurante(restaurante);
					restaurante.incluir(r);
					System.out.println("Operación realizada con éxito.");
				}
			}
		}
		else{
			System.out.println("No existe el responsable de restaurante.");
		}

	}

	public List<Restaurante> obtenerRestaurantesPendientesAlta() {
		List<Restaurante> aux = new ArrayList<Restaurante>();
		for (Restaurante re : restaurantes) {
			if (re.getEstado() == Estado.PendienteAlta) {
				aux.add(re);
			}
		}

		return aux;

	}

	public void confirmarRegistroRestaurante(String listaTelefonosRestaurante) {
		for (Restaurante re : restaurantes) {
			if (re.getTelefono().equals(listaTelefonosRestaurante)) {
				re.activarRestaurante();
			}
		}
	}

	public List<Restaurante> obtenerMisRestaurantes(String nombreUsuario) {
		List<Restaurante> aux = new ArrayList<Restaurante>();
		for (ResponsableRestaurante responsable : responsables) {
			if (responsable.getNombreUsuario().equals(nombreUsuario)) {
				aux = responsable.misRestaurantes();
			}
		}
		return aux;
	}

	public void anularReserva(String codigoReserva, String telefonoUsuario) {
		for (Restaurante restaurante : restaurantes) {
			for (Reserva reserva : restaurante.getReservasRestaurante()) {
				if (reserva.getCodigoReserva().equals(codigoReserva)) {
					restaurante.eliminarReserva(reserva);
					reserva.eliminarDelRestaurante();
					reserva.getUsuario().anularReserva(reserva);
				}
			}
		}
	}

	public void solicitarReservaRestaurante(String nombreUsuario,
			String correo, String telefono, String telefonoRestaurante,
			Date fecha, int numeroComensales) {
		Reserva reserva = new Reserva();
		Usuario usuario = new Usuario();
		usuario.crear(nombreUsuario, correo, telefono);
		Restaurante restaurante = new Restaurante();

		for (Restaurante restauranteAux : restaurantes) {
			if (restauranteAux.getTelefono().equals(telefonoRestaurante)) {
				restaurante = restauranteAux;
			}
		}

		reserva.crear(usuario, restaurante, fecha, numeroComensales, ""+codigo);
		usuarios.add(usuario);
		usuario.incluirReserva(reserva);
		restaurante.registrarReserva(reserva);
		codigo++;
	}

	public void confirmarReserva(String codigoReserva,
			String telefonoRestaurante) {
		for (Restaurante restaurante : restaurantes) {
			if (restaurante.getTelefono().equals(telefonoRestaurante)) {
				for (Reserva reserva : restaurante.getReservasRestaurante()) {
					if (reserva.getCodigoReserva().equals(codigoReserva)) {
						reserva.confirmar();
					}
				}
			}
		}
	}

	public List<Reserva> consultarMisReservas(String telefonoUsuario) {
		List<Reserva> aux = new ArrayList<Reserva>();
		for (Usuario usuario : usuarios) {
			if (usuario.getTelefono().equals(telefonoUsuario)) {
				aux = usuario.getMisReservas();
			}
		}
		return aux;
	}

	public void eliminarReservasPasadasClientesNoHabituales() {
		for (Restaurante restaurante : restaurantes) {
			for (Reserva reserva : restaurante.getReservasRestaurante()) {
				if (reserva.yaPasada()) {
					restaurante.eliminarReserva(reserva);
				}
			}
		}
	}

	public void altaRegistro(String nombreUsuario, String clave, String correo,
			String tipoUsuario) {
		switch (tipoUsuario) {
		case "ResponsableRestaurante":
			try {
				if (!existeResponsableRestaurante(nombreUsuario)) {
					ResponsableRestaurante responsable = new ResponsableRestaurante();
					responsable.crear(nombreUsuario, clave, correo);
					responsables.add(responsable);
					System.out
					.println("Usuario creado con éxito");
				}
			} catch (Exception e) {
				System.out
						.println("Ya existe un usuario con ese nombre de usuario");
			}
			break;
		default:
			break;
		}

	}

	public void actualizarDatosRestaurante(String telefono, String nombre,
			String tipoCocina, float precioMedio, String descripcion,
			String horario) {
		for (Restaurante restaurante : restaurantes) {
			if (restaurante.getTelefono().equals(telefono)) {
				restaurante.actualizarRestaurante(tipoCocina, precioMedio,
						descripcion, horario);
			}
		}
	}

	private ResponsableRestaurante buscarResponsable(String nombreUsuario) {
		ResponsableRestaurante aux = new ResponsableRestaurante();
		for (ResponsableRestaurante res : responsables) {
			if (res.getNombreUsuario().equals(nombreUsuario)) {
				aux = res;
			}
		}

		return aux;
	}

	private Usuario buscarUsuario(String telefonoUsuario) {
		Usuario aux = new Usuario();
		for(Usuario usuario : usuarios){
			if(usuario.getTelefono().equals(telefonoUsuario)){
				aux = usuario;
			}
		}
		return aux;
	}

	private Restaurante seleccionarRestaurantes(
			String listaTelefonosRestaurantes) {
		Restaurante aux = new Restaurante();
		for(Restaurante restaurante : restaurantes){
			if(restaurante.getTelefono().equals(listaTelefonosRestaurantes)){
				aux = restaurante;
			}
		}
		return aux;
	}

	private boolean existeUsuario(String telefonoUsuario) {
		boolean encontrado = false;
		for(Usuario usuario : usuarios){
			if(usuario.getTelefono().equals(telefonoUsuario)){
				encontrado = true;
			}
		}
		return encontrado;
	}

	private boolean existeResponsableRestaurante(String nombreUsuario) {
		boolean encontrado = false;
		for (ResponsableRestaurante res : responsables) {
			if (res.getNombreUsuario().equals(nombreUsuario))
				encontrado = true;
		}
		return encontrado;
	}

	private Restaurante buscarRestaurante(String telefonoRestaurante) {
		Restaurante encontrado = new Restaurante();
		for(Restaurante restaurante : restaurantes){
			if(restaurante.getTelefono().equals(telefonoRestaurante)){
				encontrado = restaurante;
			}
		}
		return encontrado;
	}

	List<ResponsableRestaurante> verResponsables() {
		return responsables;
	}

}
