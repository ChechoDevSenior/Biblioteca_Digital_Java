import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BibliotecaMain {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Libro> libros = new ArrayList<>();
    static ArrayList<Usuario> usuarios = new ArrayList<>();
    static Queue<Prestamo> prestamos = new LinkedList<>();
    static Stack<Prestamo> devoluciones = new Stack<>();

    public static void main(String[] args) {
        int opcion = 0;
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
            opcion = sc.nextInt();
            sc.nextLine();
            /*try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
                continue;
            } */          

            switch (opcion) {
                case 1:
                    registrarLibro();
                    break;
                case 2:
                    registrarUsuario();
                    break;
                case 3:
                    prestarLibro();
                    break;
                case 4:
                    devolverLibro();
                    break;
                case 5:
                    mostrarLibros();
                    break;
                case 6:
                    mostrarUsuarios();
                    break;
                case 7:
                    mostrarDevoluciones();
                    break;
                case 8:
                    mostrarPrestamos();
                    break;
                case 9:
                    salir();
                    break;
                default:
                    break;
            }

        } while (opcion != 10);
    }

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

        libros.add(new Libro(isbn, titulo, anio, editorial, autor));
        System.out.println();
        System.out.println("Libro registrado con exito.");
    }

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

        usuarios.add(new Usuario(id, nombre, email, telefono));
        System.out.println("Usuario registrado con exito.");

    }

    static void prestarLibro() {
        System.out.println();
        System.out.println("Ingrese el nombre del libro: ");
        String titulo = sc.nextLine();
        Libro libroPrestado = null;

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

        for (Usuario u : usuarios){
            if (u.id == idUsuario) {
                usuarioEncontrado = u;
                break;                
            }
        }

        if (usuarioEncontrado == null) {
            System.out.println("Usuario no encontrado. Verifique primero el ID de los usuarios registrados para solicitar un prestamo.");
            return;
        }

        prestamos.add(new Prestamo(libroPrestado, usuarioEncontrado));
        libros.remove(libroPrestado);
        System.out.println("Libro prestado correctamente.");
    }

    static void devolverLibro() {
        System.out.println();
        
        if (prestamos.isEmpty()) {
            System.out.println("No hay prestamos vigentes.");
            return;
        }

        Prestamo p = ((LinkedList<Prestamo>)prestamos).poll();
        libros.add(p.libro);
        devoluciones.push(p);
        System.out.println("Libro devuelto con exito.");
    }

    static void mostrarLibros() {
        System.out.println();
        System.out.println("Los libros que actualmente tenemos son los siguientes: ");
        if (libros.isEmpty()) {
            System.out.println("No hay libros disponibles en este momento.");
        } else {
            for (Libro l : libros){
                System.out.println(l);
            }
        }
    }

    static void mostrarUsuarios(){
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

    static void mostrarDevoluciones() {
        System.out.println();
        if (devoluciones.isEmpty()) {
            System.out.println("No hay devoluciones registradas.");            
        } else {
            for(Prestamo p : devoluciones){
                System.out.println("El Libro "+p.libro.titulo+" ha sido devuelto por "+p.usuario.nombre);
            }
        }
    }

    static void mostrarPrestamos(){
        System.out.println();
        if (prestamos.isEmpty()) {
            System.out.println("No hay libros prestados en este momento.");
            return;
        } else {
            for(Prestamo p : prestamos){
                System.out.println("El libro "+p.libro.titulo+" esta prestado a: "+p.usuario.nombre);
            }
            
        }
    }

    static void salir(){
        System.out.println("Gracias por utilizar la biblioteca digital. ¡Vuelve pronto!");
    }

}
