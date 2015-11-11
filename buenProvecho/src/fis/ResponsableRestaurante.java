package fis;

import java.util.List;
import java.util.ArrayList;

public class ResponsableRestaurante {
	private String nombreUsuario;
	private String clave;
	private String correo;
	private List<Restaurante> miRestaurante;

	void crear(String nombreUsuario, String clave, String correo) {
		this.nombreUsuario = nombreUsuario;
		this.clave = clave;
		this.correo = correo;
		miRestaurante = new ArrayList<Restaurante>();
	}

	void incluirRestaurante(Restaurante miRestaurante) {
		this.miRestaurante.add(miRestaurante);
	}
	
	List<Restaurante> misRestaurantes() {
		return miRestaurante;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public String getClave() {
		return clave;
	}

	public String getCorreo() {
		return correo;
	}

	public List<Restaurante> getMiRestaurante() {
		return miRestaurante;
	}

	@Override
	public String toString() {
		return "ResponsableRestaurante [nombreUsuario=" + nombreUsuario
				+ ", clave=" + clave + ", correo=" + correo + "]";
	}
	
	
}
