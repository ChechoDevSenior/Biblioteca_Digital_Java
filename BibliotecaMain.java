// Sergio Arturo Peña Chaparro 

// Importación de librerías necesarias
import java.text.SimpleDateFormat; // Para formatear fechas
import java.util.ArrayList; // Para almacenar listas dinámicas de libros y usuarios
import java.util.LinkedList; // Para la implementación de la cola de préstamos
import java.util.Queue; // Interfaz para la cola de préstamos
import java.util.Scanner; // Para leer entradas del usuario desde consola
import java.util.Stack; // Para implementar la pila de devoluciones
import java.io.BufferedReader; // Para lectura eficiente de archivos de texto
import java.io.FileReader; // Para leer archivos desde el sistema de archivos
import java.io.IOException; // Para manejar excepciones de entrada/salida

public class BibliotecaMain {

    // Scanner global para leer entradas del usuario
    static Scanner sc = new Scanner(System.in);

    // Estructuras para almacenar datos de la biblioteca
    static ArrayList<Libro> libros = new ArrayList<>(); // Lista de libros
    static ArrayList<Usuario> usuarios = new ArrayList<>(); // Lista de usuarios
    static Queue<Prestamo> prestamos = new LinkedList<>(); // Cola de préstamos activos
    static Stack<Prestamo> devoluciones = new Stack<>(); // Pila de devoluciones realizadas

    // Árboles binarios para organizar los datos eficientemente
    static ArbolLibros arbolLibros = new ArbolLibros(); // Árbol por ISBN
    static ArbolUsuarios arbolUsuarios = new ArbolUsuarios(); // Árbol de usuarios
    static ArbolPrestamo arbolPrestamos = new ArbolPrestamo(); // Árbol de préstamos
    static ArbolLibrosPorNombre arbolLibrosPorNombre = new ArbolLibrosPorNombre(); // Árbol por nombre
    static ArbolLibrosPorAnio arbolLibrosPorAnio = new ArbolLibrosPorAnio(); // Árbol por año

    public static void main(String[] args) {
        int opcion = 0;
        // Ciclo principal del menú interactivo
        do {
            System.out.println("-------------------");
            System.out.println("BIBLIOTECA VIRTUAL");
            System.out.println("1. Registrar libro.");
            System.out.println("2. Mostrar libros registrados.");
            System.out.println("3. Registrar usuario.");
            System.out.println("4. Mostrar usuarios registrados.");
            System.out.println("5. Solicitar prestado un libro.");
            System.out.println("6. Mostrar prestamos.");
            System.out.println("7. Devolver libro.");
            System.out.println("8. Ver historial de devoluciones.");
            System.out.println("9. Cargar libros desde archivos TXT.");
            System.out.println("10. Cargar usuarios desde archivos TXT.");
            System.out.println("11. Salir.");
            System.out.print("Digite una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpieza del buffer para evitar errores en la lectura

            // Estructura switch para ejecutar la opción correspondiente
            switch (opcion) {
                case 1:
                    registrarLibro(); // Llama al método para registrar un libro
                    break;
                case 2:
                    mostrarLibros(); // Muestra los libros registrados
                    break;
                case 3:
                    registrarUsuario(); // Registra un nuevo usuario
                    break;
                case 4:
                    mostrarUsuarios(); // Muestra los usuarios registrados
                    break;
                case 5:
                    solicitarPrestamo(); // Realiza un préstamo de libro
                    break;
                case 6:
                    mostrarPrestamos(); // Muestra todos los préstamos activos
                    break;
                case 7:
                    devolverLibro(); // Devuelve un libro prestado
                    break;
                case 8:
                    mostrarDevoluciones(); // Muestra el historial de devoluciones
                    break;
                case 9:
                    System.out.print("Ingrese la ruta del archivo de libros: ");
                    String archivoLibros = sc.nextLine();
                    cargarLibrosDesdeArchivo(archivoLibros); // Carga libros desde archivo
                    break;
                case 10:
                    System.out.print("Ingrese la ruta del archivo de usuarios: ");
                    String archivoUsuarios = sc.nextLine();
                    cargarUsuariosDesdeArchivo(archivoUsuarios); // Carga usuarios desde archivo
                    break;
                case 11:
                    salir(); // Finaliza el programa
                    break;
                default:
                    System.out.println("Opción invalida"); // Opción no reconocida
                    break;
            }

        } while (opcion != 11); // El ciclo continúa hasta seleccionar la opción de salir
    }

    // Método para registrar un nuevo libro
    static void registrarLibro() {
        System.out.println("--- Registro de Libro ---");

        // Solicita al usuario el ISBN del libro
        System.out.print("Ingrese ISBN del libro: ");
        String isbn = sc.nextLine();

        // Verifica si el ISBN ya existe en el árbol principal
        if (arbolLibros.buscar(isbn) != null) {
            System.out.println("Ya existe un libro con ese ISBN.");
            return; // Si existe, no se registra el libro y se sale del método
        }

        // Solicita los demás datos del libro
        System.out.print("Ingrese título del libro: ");
        String titulo = sc.nextLine();

        System.out.print("Ingrese autor del libro: ");
        String autor = sc.nextLine();

        System.out.print("Ingrese año de publicación: ");
        int anio = sc.nextInt();
        sc.nextLine(); // Limpia el buffer del scanner para evitar errores

        // Crea una instancia del libro con los datos ingresados
        Libro nuevoLibro = new Libro(isbn, titulo, autor, anio);

        // Inserta el libro en los tres árboles para búsquedas y ordenamientos
        // eficientes
        arbolLibros.insertar(nuevoLibro); // Árbol por ISBN
        arbolLibrosPorNombre.insertar(nuevoLibro); // Árbol por título
        arbolLibrosPorAnio.insertar(nuevoLibro); // Árbol por año de publicación

        // Informa que el libro fue registrado correctamente
        System.out.println("Libro registrado correctamente.");
    }

    // Método para registrar un nuevo usuario
    static void registrarUsuario() {
        // Solicita al usuario ingresar el ID del nuevo usuario
        System.out.println("Ingrese ID del usuario: ");
        int id = sc.nextInt(); // Lee el ID desde la entrada de la consola
        sc.nextLine(); // Limpiar el buffer para evitar problemas con la entrada posterior

        // Solicita al usuario ingresar el nombre del nuevo usuario
        System.out.println("Ingrese nombre del usuario: ");
        String nombre = sc.nextLine(); // Lee el nombre del usuario

        // Solicita al usuario ingresar la dirección del nuevo usuario
        System.out.println("Ingrese dirección del usuario: ");
        String direccion = sc.nextLine(); // Lee la dirección del usuario

        // Solicita al usuario ingresar el teléfono del nuevo usuario
        System.out.println("Ingrese teléfono del usuario: ");
        String telefono = sc.nextLine(); // Lee el teléfono del usuario

        // Crea un nuevo objeto Usuario con los datos ingresados
        Usuario nuevoUsuario = new Usuario(id, nombre, direccion, telefono);

        // Inserta el nuevo usuario en el árbol de usuarios
        arbolUsuarios.insertar(nuevoUsuario); // Utiliza el árbol binario para insertar el nuevo usuario de manera
                                              // ordenada

        // Muestra un mensaje indicando que el usuario fue registrado con éxito
        System.out.println("Usuario registrado con éxito.");
    }

    // Método para registrar un nuevo préstamo
    static void solicitarPrestamo() {
        System.out.println(); // Imprime una línea en blanco para separar la entrada de los mensajes

        // Solicita el ISBN del libro que el usuario desea solicitar
        System.out.println("Ingrese ISBN del libro a solicitar: ");
        String isbn = sc.next(); // Lee el ISBN desde la entrada de la consola
        Libro libro = arbolLibros.buscar(isbn); // Busca el libro en el árbol binario usando el ISBN

        // Verifica si el libro existe y está disponible para préstamo
        if (libro != null && libro.isDisponible()) {
            // Solicita al usuario ingresar el ID del usuario que desea hacer el préstamo
            System.out.println("Ingrese ID del usuario que solicita el préstamo: ");
            int usuarioId = sc.nextInt(); // Lee el ID del usuario desde la entrada
            Usuario usuario = arbolUsuarios.buscar(usuarioId); // Busca al usuario en el árbol de usuarios

            // Verifica si el usuario existe
            if (usuario == null) {
                System.out.println("Usuario no encontrado."); // Muestra un mensaje si el usuario no se encuentra
            } else {
                // Si el usuario existe, genera un ID único para el préstamo
                int prestamoId = generarIdPrestamo(); // Llama a un método para generar un ID único para el préstamo

                // Crea un nuevo objeto Prestamo con los datos proporcionados
                Prestamo prestamo = new Prestamo(prestamoId, usuario, libro);

                // Inserta el nuevo préstamo en el árbol de préstamos
                arbolPrestamos.insertar(prestamo);

                // Marca el libro como no disponible
                libro.setDisponible(false); // El libro ahora está prestado, por lo que se marca como no disponible

                // Muestra un mensaje indicando que el préstamo fue registrado con éxito
                System.out.println("Préstamo registrado con éxito.");
            }
        } else {
            // Si el libro no existe o no está disponible, muestra un mensaje
            System.out.println("El libro no está disponible.");
        }
    }

    // Método para devolver un libro
    static void devolverLibro() {
        System.out.println(); // Imprime una línea en blanco para separar la entrada de los mensajes
        System.out.print("Ingrese el ISBN del libro a devolver: "); // Solicita el ISBN del libro que se desea devolver
        String isbn = sc.next(); // Lee el ISBN desde la entrada del usuario

        // Busca el préstamo activo correspondiente al ISBN proporcionado
        Prestamo prestamoADevolver = arbolPrestamos.buscarPorIsbn(isbn);

        // Verifica si se encontró un préstamo con ese ISBN
        if (prestamoADevolver != null) {
            // Si el préstamo existe, marca el libro como disponible nuevamente
            prestamoADevolver.libro.setDisponible(true);

            // Agrega el préstamo a la lista de devoluciones
            devoluciones.add(prestamoADevolver);

            // Elimina el préstamo del árbol de préstamos, ya que se ha devuelto
            arbolPrestamos.eliminar(prestamoADevolver.id);

            // Muestra un mensaje indicando que el libro fue devuelto correctamente
            System.out.println("Libro devuelto correctamente.");
        } else {
            // Si no se encuentra el préstamo activo, muestra un mensaje de error
            System.out.println("No se encontró un préstamo activo con ese ISBN.");
        }
    }

    // Método para mostrar los libros disponibles actualmente
    static void mostrarLibros() {

        System.out.println(); // Imprime una línea en blanco para separar la entrada de los mensajes

        // Verifica si el árbol de libros está vacío
        if (arbolLibros.estaVacio()) {
            // Si el árbol de libros está vacío, informa al usuario
            System.out.println("No hay libros disponibles en este momento.");
        } else {

            // Variable para almacenar la opción del menú de visualización de libros
            int opcion1;

            do {
                // Muestra el menú con las opciones de visualización de libros
                System.out.println();
                System.out.println("    [1.] Mostrar todos los libros por ISBN");
                System.out.println("    [2.] Mostrar libros por nombre (orden alfabético)");
                System.out.println("    [3.] Mostrar libros por año de publicación");
                System.out.println("    [4.] Salir");
                System.out.print("  Digite una opción: ");
                opcion1 = sc.nextInt(); // Lee la opción seleccionada por el usuario
                sc.nextLine(); // Limpiar el buffer de entrada

                // Estructura switch para ejecutar la opción seleccionada
                switch (opcion1) {
                    case 1:
                        // Muestra todos los libros por ISBN (se asume que el orden inOrder ya está
                        // implementado en el árbol)
                        System.out.println("Libros ordenados por ISBN:");
                        arbolLibros.mostrarLibrosInOrden(); // Muestra los libros en orden de ISBN
                        break;

                    case 2:
                        // Muestra todos los libros por nombre (en orden alfabético)
                        System.out.println("Libros ordenados por nombre:");
                        arbolLibrosPorNombre.mostrarInOrden(); // Muestra los libros en orden alfabético por nombre
                        break;

                    case 3:
                        // Muestra todos los libros por año de publicación
                        System.out.println("Libros ordenados por año de publicación:");
                        arbolLibrosPorAnio.mostrarInOrden(); // Muestra los libros en orden por año de publicación
                        break;

                    case 4:
                        // Opción para salir del menú
                        System.out.println("Retornando al menú principal.");
                        break;

                    default:
                        // Si el usuario ingresa una opción no válida, muestra un mensaje de error
                        System.out.println("Opción no válida. Intente nuevamente.");
                        break;
                }
            } while (opcion1 != 4); // El ciclo continúa hasta que el usuario seleccione la opción 4 para salir
        }

    }

    // Método para mostrar todos los usuarios registrados
    static void mostrarUsuarios() {
        System.out.println(); // Imprime una línea en blanco para separar la entrada de los mensajes

        // Verifica si el árbol de usuarios está vacío
        if (arbolUsuarios.estaVacio()) {
            // Si el árbol de usuarios está vacío, informa al usuario
            System.out.println("No hay usuarios registrados.");
        } else {
            // Si hay usuarios registrados, muestra el mensaje de inicio
            System.out.println("Usuarios registrados:");

            // Muestra los usuarios en orden utilizando el árbol binario (orden inOrder)
            arbolUsuarios.mostrarUsuariosInOrden();
        }
    }

    // Método para mostrar el historial de devoluciones
    static void mostrarDevoluciones() {
        System.out.println(); // Imprime una línea en blanco para separar la entrada de los mensajes

        // Verifica si la lista de devoluciones está vacía
        if (devoluciones.isEmpty()) {
            // Si no hay devoluciones, informa al usuario
            System.out.println("No hay devoluciones registradas.");
        } else {
            // Si hay devoluciones registradas, muestra el mensaje de inicio
            System.out.println("Devoluciones Registradas:");

            // Recorre la lista de devoluciones y muestra la información de cada una
            for (Prestamo p : devoluciones) {
                // Muestra el título del libro devuelto
                System.out.println("Título del libro: " + p.libro.getTitulo());

                // Muestra el ISBN del libro devuelto
                System.out.println("ISBN: " + p.libro.getIsbn());

                // Muestra el nombre del usuario que realizó la devolución
                System.out.println("Usuario: " + p.usuario.getNombre());

                // Muestra la fecha de la devolución formateada como día/mes/año hora:minuto
                System.out.println(
                        "Fecha de devolución: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(p.fechaPrestamo));
            }
        }
    }

    // Método para mostrar los préstamos activos
    static void mostrarPrestamos() {
        System.out.println(); // Imprime una línea en blanco para separar la entrada de los mensajes

        // Verifica si el árbol de préstamos está vacío
        if (arbolPrestamos.estaVacio()) {
            // Si no hay préstamos activos, informa al usuario
            System.out.println("No hay libros prestados en este momento.");
            return; // Finaliza la ejecución del método si no hay préstamos
        } else {
            // Si hay préstamos activos, muestra el mensaje de inicio
            System.out.println("Listado de prestamos: ");

            // Llama al método para mostrar los préstamos activos en orden
            arbolPrestamos.mostrarPrestamosInOrden();
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

    // Método para cargar libros desde un archivo
    static void cargarLibrosDesdeArchivo(String nombreArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) { // Se abre el archivo para leerlo
            String linea; // Variable para almacenar cada línea leída del archivo
            while ((linea = br.readLine()) != null) { // Lee el archivo línea por línea
                // Se separa cada línea en partes usando la coma como delimitador
                String[] partes = linea.split(",");
                // Se verifica que la línea contenga exactamente 4 partes (ISBN, título, autor,
                // año)
                if (partes.length == 4) {
                    String isbn = partes[0].trim(); // Eliminar espacios alrededor del ISBN
                    String titulo = partes[1].trim(); // Eliminar espacios alrededor del título
                    String autor = partes[2].trim(); // Eliminar espacios alrededor del autor
                    int anio = Integer.parseInt(partes[3].trim()); // Convertir el año de publicación a entero

                    // Verifica si ya existe un libro con el mismo ISBN
                    if (arbolLibros.buscar(isbn) == null) {
                        // Si no existe, crea un nuevo objeto libro
                        Libro libro = new Libro(isbn, titulo, autor, anio);
                        // Inserta el nuevo libro en los árboles organizados por ISBN, nombre y año
                        arbolLibros.insertar(libro);
                        arbolLibrosPorNombre.insertar(libro);
                        arbolLibrosPorAnio.insertar(libro);
                    }
                }
            }
            // Informa al usuario que los libros se cargaron correctamente
            System.out.println("Libros cargados correctamente desde archivo.");
        } catch (IOException e) { // Captura cualquier error al leer el archivo
            System.out.println("Error al leer el archivo de libros: " + e.getMessage());
        }
    }

    // Método para cargar usuarios desde un archivo
    static void cargarUsuariosDesdeArchivo(String nombreArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) { // Se abre el archivo para leerlo
            String linea; // Variable para almacenar cada línea leída del archivo
            while ((linea = br.readLine()) != null) { // Lee el archivo línea por línea
                // Se separa cada línea en partes usando la coma como delimitador
                String[] partes = linea.split(",");
                // Se verifica que la línea contenga exactamente 4 partes (ID, nombre,
                // dirección, teléfono)
                if (partes.length == 4) {
                    int id = Integer.parseInt(partes[0].trim()); // Convertir el ID a entero, eliminando los espacios
                    String nombre = partes[1].trim(); // Eliminar espacios alrededor del nombre
                    String direccion = partes[2].trim(); // Eliminar espacios alrededor de la dirección
                    String telefono = partes[3].trim(); // Eliminar espacios alrededor del teléfono

                    // Verifica si ya existe un usuario con el mismo ID
                    if (arbolUsuarios.buscar(id) == null) {
                        // Si no existe, crea un nuevo objeto Usuario
                        Usuario usuario = new Usuario(id, nombre, direccion, telefono);
                        // Inserta el nuevo usuario en el árbol de usuarios
                        arbolUsuarios.insertar(usuario);
                    }
                }
            }
            // Informa al usuario que los usuarios se cargaron correctamente
            System.out.println("Usuarios cargados correctamente desde archivo.");
        } catch (IOException e) { // Captura cualquier error al leer el archivo
            System.out.println("Error al leer el archivo de usuarios: " + e.getMessage());
        }
    }

}
