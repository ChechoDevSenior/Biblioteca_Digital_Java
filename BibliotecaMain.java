
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

    // Instancia de los árboles
    static ArbolLibros arbolLibros = new ArbolLibros();
    static ArbolUsuarios arbolUsuarios = new ArbolUsuarios();
    static ArbolPrestamo arbolPrestamos = new ArbolPrestamo();

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
                    solicitarPrestamo(); // Presta un libro a un usuario
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
        System.out.println(" ");
        // Solicita al usuario ingresar los datos del libro
        System.out.print("Ingrese ISBN: ");
        String isbn = sc.nextLine();
        System.out.print("Ingrese el título del libro: ");
        String titulo = sc.nextLine();
        System.out.print("Ingrese el autor del libro: ");
        String autor = sc.nextLine();
        System.out.print("Ingrese la editorial del libro: ");
        String editorial = sc.nextLine();
        Libro nuevoLibro = new Libro(isbn, titulo, autor, editorial);
        arbolLibros.insertar(nuevoLibro);
        System.out.println("Libro registrado con éxito.");
    }

    // Método para registrar un nuevo usuario
    static void registrarUsuario() {
        System.out.println("Ingrese ID del usuario: ");
        int id = sc.nextInt();
        sc.nextLine(); // Limpiar buffer
        System.out.println("Ingrese nombre del usuario: ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese dirección del usuario: ");
        String direccion = sc.nextLine();
        System.out.println("Ingrese teléfono del usuario: ");
        String telefono = sc.nextLine();

        Usuario nuevoUsuario = new Usuario(id, nombre, direccion, telefono);
        arbolUsuarios.insertar(nuevoUsuario);
        System.out.println("Usuario registrado con éxito.");
    }

    // Método para registrar un nuevo préstamo
    static void solicitarPrestamo() {
        System.out.println();
        System.out.println("Ingrese ID del usuario que solicita el préstamo: ");
        int usuarioId = sc.nextInt();
        Usuario usuario = arbolUsuarios.buscar(usuarioId);

        if (usuario != null) {
            System.out.println("Ingrese ISBN del libro a solicitar: ");
            String isbn = sc.next();
            Libro libro = arbolLibros.buscar(isbn);

            if (libro != null && libro.isDisponible()) {
                int prestamoId = generarIdPrestamo(); // Método para generar un ID único
                Prestamo prestamo = new Prestamo(prestamoId, usuario, libro);
                arbolPrestamos.insertar(prestamo);
                libro.setDisponible(false); // Marcar libro como no disponible
                System.out.println("Préstamo registrado con éxito.");
            } else {
                System.out.println("El libro no está disponible.");
            }
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    // Método para devolver un libro
    static void devolverLibro() {
        System.out.println();
        System.out.println("Ingrese el ID del préstamo a devolver: ");
        int prestamoId = sc.nextInt();
        Prestamo prestamo = arbolPrestamos.buscar(prestamoId);

        if (prestamo != null) {
            prestamo.libro.setDisponible(true); // Marcar el libro como disponible
            arbolPrestamos.eliminar(prestamoId); // Eliminar el préstamo del árbol
            System.out.println("Libro devuelto correctamente.");
        } else {
            System.out.println("Préstamo no encontrado.");
        }
    }

    // Método para mostrar los libros disponibles actualmente
    static void mostrarLibros() {
        System.out.println();
        System.out.println("Libros disponibles:");
        arbolLibros.mostrarLibrosInOrden();

    }

    // Método para mostrar todos los usuarios registrados
    static void mostrarUsuarios() {
        System.out.println("Usuarios registrados:");
        arbolUsuarios.mostrarUsuariosInOrden();
    }

    // Método para mostrar el historial de devoluciones
    static void mostrarDevoluciones() {
        System.out.println();
        if (devoluciones.isEmpty()) {
            System.out.println("No hay devoluciones registradas.");
        } else {
            for (Prestamo p : devoluciones) {
                System.out.println("El Libro " + p.libro.getTitulo() + " ha sido devuelto por " + p.usuario.nombre);
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
                System.out.println("El libro " + p.libro.getTitulo() + " está prestado a: " + p.usuario.nombre);
            }
        }
    }

    // Método para generar un ID único para los préstamos
    static int generarIdPrestamo() {
        // Aquí se podría implementar un método para generar IDs únicos
        return (int) (Math.random() * 10000);
    }

    // Método para cerrar el programa
    static void salir() {
        System.out.println("Gracias por utilizar la biblioteca digital. ¡Vuelve pronto!");
    }
}
