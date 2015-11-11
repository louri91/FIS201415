package fis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BuenProvechoPrincipal {

	public static void main(String[] args) {

		// Obtener la única instancia de la clase BuenProvecho (patrón sigleton)
		BuenProvecho aComer = BuenProvecho.getInstance();
		// Definir la variable que nos permite leer String desde teclado
		final Scanner in = new Scanner(System.in);
		int opcion = 0;
		do {
			try {
				System.out
						.println("\n\n*********************************** MENU ***********************************\n"
								+ "GESTIÓN DE USUARIOS   \n"
								+ "\t10. Nuevo Responsable Restaurante \n"
								+ "\t11. Ver todos Responsables Restaurantes \n");

				System.out
						.println("GESTIÓN DE RESTAURANTES   \n"
								+ "\t20. Solicitar registro restaurante \n"
								+ "\t21. Ver restaurantes pendientes de registro \n"
								+ "\t22. Confirmar registros de restaurantes \n"
								+ "\t23. Consultar los restaurantes de un responsable \n"
								+ "\t24. Actualizar datos de un restaurante \n");

				System.out.println("GESTIÓN DE RESERVAS   \n"
						+ "\t30. Solicitar  reserva \n"
						+ "\t31. Confirmar reserva \n"
						+ "\t32. Ver las reservas de un usuario \n"
						+ "\t33. Anular una reserva \n"
						+ "\t34. Anular reservas pasadas \n");

				System.out.println("TERMINAR ");
				System.out.println("\t0. Terminar");
				System.out
						.println("\n**********************************************************************");

				opcion = Integer.parseInt(in.nextLine());

				switch (opcion) {
				case 10:// incluir un nuevo responsable de restaurante en el
						// sistema

					System.out.print("Nombre de responsable:");
					String nombreUsuario = in.nextLine();

					System.out.print("Clave de responsable:");
					String claveUsuario = in.nextLine();

					System.out.print("Correo de responsable:");
					String correoUsuario = in.nextLine();

					aComer.altaRegistro(nombreUsuario, claveUsuario,
							correoUsuario, "ResponsableRestaurante");
					break;

				case 11:/* Consultar todos los responsables de restaurantes que hay */

					List<ResponsableRestaurante> salida = aComer.verResponsables();
					System.out.println("Responsables:");
						for (ResponsableRestaurante s : salida) {
							System.out.println("\t" + s.toString());
						}
					break;

				case 20: /* Solicitar registro de restaurante */
					System.out.print("Nombre de responsable:");
					String nombreResponsable = in.nextLine();
					
					System.out.print("Nombre del restaurante:");
					String nombre = in.nextLine();
					
					System.out.print("Provincia:");
					String provincia = in.nextLine();
					
					System.out.print("Localidad:");
					String localidad = in.nextLine();
					
					System.out.print("Dirección:");
					String direccion = in.nextLine();
					
					System.out.print("Teléfono:");
					String telefono = in.nextLine();
					
					aComer.solicitarRegistroRestaurante(nombreResponsable, nombre, provincia, localidad, direccion, telefono);
					
					
					break;

				case 21: /* Ver restaurantes pendientes de registro */
					List<Restaurante> aux = new ArrayList<Restaurante>();
					aux = aComer.obtenerRestaurantesPendientesAlta();
					System.out.println("Listado de restaurantes pendientes de alta: "+ "["+aux.size()+"]");
					for(Restaurante restaurante : aux){
						System.out.println("\t" + restaurante.toString());
					}
					break;

				case 22: /* Cofirmar registro de restaurante */
					System.out.print("Teléfono:");
					String telefonoConfirmacion = in.nextLine();
					
					aComer.confirmarRegistroRestaurante(telefonoConfirmacion);
					
					break;

				case 23: /* Consultar los restaurantes de un responsable */
					System.out.print("Nombre Responsable:");
					String nombreResp = in.nextLine();
					
					List<Restaurante> aux1 = new ArrayList<Restaurante>();
					aux1 = aComer.obtenerMisRestaurantes(nombreResp);
					
					System.out.println("Listado de restaurantes del responsable " +nombreResp+ "["+ aux1.size() +"]" );
					
					for(Restaurante restaurante : aux1){
						System.out.println("\t" + restaurante.toString());
					}
					
					break;

				case 24: /* Actualizar datos de un restaurante */
					System.out.print("Telefono del restaurante:");
					String telefonoRes = in.nextLine();
					
					System.out.print("Nombre del restaurante:");
					String nombreRes = in.nextLine();
					
					System.out.print("Tipo de Cocina:");
					String tipoCocina = in.nextLine();
					
					System.out.print("Precio Medio:");
					String precioMedio = in.nextLine();
					float precioM = Float.parseFloat(precioMedio);
					
					System.out.print("Descripción:");
					String descripcion = in.nextLine();
					
					System.out.print("Horario:");
					String horario = in.nextLine();
					
					aComer.actualizarDatosRestaurante(telefonoRes, nombreRes, tipoCocina, precioM, descripcion, horario);

					break;

				case 30: /* Solicitar Reserva */
					System.out.print("Nombre del Cliente:");
					String nombreU = in.nextLine();
					
					System.out.print("Correo del Cliente:");
					String correoU = in.nextLine();
					
					System.out.print("Telefono del Cliente:");
					String telefonoU = in.nextLine();
					
					System.out.print("Telefono del restaurante:");
					String telefonoRest = in.nextLine();
					
					System.out.print("Fecha y hora:");
					String fecha = in.nextLine();
					SimpleDateFormat formatearFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");
					Date date = formatearFecha.parse(fecha);
					
					
					System.out.print("Número de comensales:");
					String comensales = in.nextLine();
					int numComensales = Integer.parseInt(comensales);
					
					aComer.solicitarReservaRestaurante(nombreU, correoU, telefonoU, telefonoRest, date, numComensales);
					
					break;

				case 31: /* Confirmar Reserva */
					System.out.print("Codigo de Reserva:");
					String codReserva = in.nextLine();
					
					System.out.print("Telefono del Restaurante:");
					String telfRest = in.nextLine();
					
					aComer.confirmarReserva(codReserva, telfRest);
					
					break;

				case 32: /* Ver las reservas de un usuario */
					System.out.print("Telefono del Usuario:");
					String telfUsu = in.nextLine();
					
					List<Reserva> auxiliar = new ArrayList<Reserva>();
					
					auxiliar = aComer.consultarMisReservas(telfUsu);
					
					System.out.println("Listado de reservas del usuario con teléfono "+telfUsu+ "["+ auxiliar.size() +"]");
					
					for(Reserva reserva : auxiliar){
						System.out.println("\t" + reserva.toString());
					}
					
					break;

				case 33: /* Anular una reserva */
					System.out.print("Codigo de Reserva:");
					String codReservaAnular = in.nextLine();
					
					System.out.print("Telefono del Usuario:");
					String telfUsuAnular = in.nextLine();
					
					aComer.anularReserva(codReservaAnular, telfUsuAnular);
					
					break;

				case 34: /* Anular reservas pasadas */
					
					aComer.eliminarReservasPasadasClientesNoHabituales();
					
					break;

				case 0:
					break;

				default:
					System.out.println("opcion no válida");
					break;
				}
				//
			} catch (Exception ex) { // captura de la excepción
				System.err.println("se ha producido la siguiente excepcion: "
						+ ex);
			}
		} while (opcion != 0);
		System.exit(0);
	}

}
