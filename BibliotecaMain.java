//Sergio Arturo Peña Chaparro 
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BibliotecaMain {

    // Scanner para capturar datos del usuario desde consola
    static Scanner sc = new Scanner(System.in);

    // Lista de libros registrados
    static ArrayList<Libro> libros = new ArrayList<>();

    // Lista de usuarios registrados
    static ArrayList<Usuario> usuarios = new ArrayList<>();

    // Cola de préstamos activos
    static Queue<Prestamo> prestamos = new LinkedList<>();

    // Pila de devoluciones realizadas
    static Stack<Prestamo> devoluciones = new Stack<>();

    public static void main(String[] args) {
        int opcion = 0;
        // Menú principal interactivo que se repite hasta que el usuario elige salir
        do {
            System.out.println("-------------------");
            System.out.println("BIBLIOTECA VIRTUAL");
            System.out.println("1. Registrar libro.");
            System.out.println("2. Registrar usuario.");
            System.out.println("3. Solicitar prestado un libro.");
            System.out.println("4. Devolver libro.");
            System.out.println("5. Mostrar libros disponibles.");
            System.out.println("6. Mostrar usuarios registrados.");
            System.out.println("7. Ver historial de devoluciones.");
            System.out.println("8. Mostrar prestamos.");
            System.out.println("9. Salir.");
            System.out.print("Digite una opción: ");
            opcion = sc.nextInt(); // Se captura la opción del usuario
            sc.nextLine(); // Limpieza del buffer

            // Lógica para ejecutar la opción seleccionada
            switch (opcion) {
                case 1:
                    registrarLibro(); // Agrega un nuevo libro
                    break;
                case 2:
                    registrarUsuario(); // Agrega un nuevo usuario
                    break;
                case 3:
                    prestarLibro(); // Presta un libro a un usuario
                    break;
                case 4:
                    devolverLibro(); // Devuelve el libro prestado
                    break;
                case 5:
                    mostrarLibros(); // Muestra libros disponibles
                    break;
                case 6:
                    mostrarUsuarios(); // Muestra usuarios registrados
                    break;
                case 7:
                    mostrarDevoluciones(); // Muestra libros devueltos
                    break;
                case 8:
                    mostrarPrestamos(); // Muestra préstamos activos
                    break;
                case 9:
                    salir(); // Mensaje de despedida
                    break;
                default:
                    System.out.println("Opción invalida"); // Manejo de errores de entrada
                    break;
            }

        } while (opcion != 9); // Repite hasta que se elige la opción de salir
    }

    // Método para registrar un nuevo libro
    static void registrarLibro() {
        System.out.println("Ingrese ISBN: ");
        String isbn = sc.nextLine();
        System.out.println("Ingrese el título del libro: ");
        String titulo = sc.nextLine();
        System.out.println("Ingrese el año de publicación del libro: ");
        int anio = sc.nextInt();
        sc.nextLine();
        System.out.println("Ingrese la editorial del libro: ");
        String editorial = sc.nextLine();
        System.out.println("Ingrese el autor del libro: ");
        String autor = sc.nextLine();

        // Se agrega el libro a la lista
        libros.add(new Libro(isbn, titulo, anio, editorial, autor));
        System.out.println();
        System.out.println("Libro registrado con exito.");
    }

    // Método para registrar un nuevo usuario
    static void registrarUsuario() {
        System.out.println();
        System.out.println("ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Correo electrónico: ");
        String email = sc.nextLine();
        System.out.println("Teléfono: ");
        String telefono = sc.nextLine();

        // Se agrega el usuario a la lista
        usuarios.add(new Usuario(id, nombre, email, telefono));
        System.out.println("Usuario registrado con exito.");
    }

    // Método para prestar un libro a un usuario registrado
    static void prestarLibro() {
        System.out.println();
        System.out.println("Ingrese el nombre del libro: ");
        String titulo = sc.nextLine();
        Libro libroPrestado = null;

        // Se busca el libro en la lista
        for (Libro l : libros) {
            if (l.titulo.equalsIgnoreCase(titulo)) {
                libroPrestado = l;
                break;
            }
        }

        if (libroPrestado == null) {
            System.out.println("Libro no encontrado, verifique la consulta.");
            return;
        }

        System.out.println("Ingrese el ID responsable del prestamo: ");
        int idUsuario = sc.nextInt();
        Usuario usuarioEncontrado = null;

        // Se busca el usuario en la lista
        for (Usuario u : usuarios) {
            if (u.id == idUsuario) {
                usuarioEncontrado = u;
                break;
            }
        }

        if (usuarioEncontrado == null) {
            System.out.println(
                    "Usuario no encontrado. Verifique primero el ID de los usuarios registrados para solicitar un prestamo.");
            return;
        }

        // Se registra el préstamo y se elimina el libro de los disponibles
        prestamos.add(new Prestamo(libroPrestado, usuarioEncontrado));
        libros.remove(libroPrestado);
        System.out.println("Libro prestado correctamente.");
    }

    // Método para devolver un libro
    static void devolverLibro() {
        System.out.println();
        if (prestamos.isEmpty()) {
            System.out.println("No hay prestamos vigentes.");
            return;
        }

        // Se toma el primer préstamo y se registra en la pila de devoluciones
        Prestamo p = ((LinkedList<Prestamo>) prestamos).poll();
        libros.add(p.libro); // Se regresa el libro al catálogo
        devoluciones.push(p); // Se guarda el préstamo como devolución
        System.out.println("Libro devuelto con exito.");
    }

    // Método para mostrar los libros disponibles actualmente
    static void mostrarLibros() {
        System.out.println();
        System.out.println("Los libros que actualmente tenemos son los siguientes: ");
        if (libros.isEmpty()) {
            System.out.println("No hay libros disponibles en este momento.");
        } else {
            for (Libro l : libros) {
                System.out.println(l);
            }
        }
    }

    // Método para mostrar todos los usuarios registrados
    static void mostrarUsuarios() {
        System.out.println();
        System.out.println("Los usuarios actualmente registrados son: ");
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            for (Usuario u : usuarios) {
                System.out.println(u);
            }
        }
    }

    // Método para mostrar el historial de devoluciones
    static void mostrarDevoluciones() {
        System.out.println();
        if (devoluciones.isEmpty()) {
            System.out.println("No hay devoluciones registradas.");
        } else {
            for (Prestamo p : devoluciones) {
                System.out.println("El Libro " + p.libro.titulo + " ha sido devuelto por " + p.usuario.nombre);
            }
        }
    }

    // Método para mostrar los préstamos activos
    static void mostrarPrestamos() {
        System.out.println();
        if (prestamos.isEmpty()) {
            System.out.println("No hay libros prestados en este momento.");
            return;
        } else {
            for (Prestamo p : prestamos) {
                System.out.println("El libro " + p.libro.titulo + " está prestado a: " + p.usuario.nombre);
            }
        }
    }

    // Método para cerrar el programa
    static void salir() {
        System.out.println("Gracias por utilizar la biblioteca digital. ¡Vuelve pronto!");
    }
}
