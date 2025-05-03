
// Clase que representa un préstamo de un libro a un usuario
import java.text.SimpleDateFormat; // Importa la clase para formatear la fecha
import java.util.Date; // Importa la clase para manejar fechas

public class Prestamo {
    int id; // Identificador único del préstamo
    Usuario usuario; // Usuario que realiza el préstamo
    Libro libro; // Libro que se está prestando
    Date fechaPrestamo; // Fecha en que se realiza el préstamo

    // Constructor: inicializa un nuevo objeto Prestamo con los datos proporcionados
    public Prestamo(int id, Usuario usuario, Libro libro) {
        this.id = id; // Asigna el ID del préstamo
        this.usuario = usuario; // Asigna el usuario que realiza el préstamo
        this.libro = libro; // Asigna el libro que se está prestando
        this.fechaPrestamo = new Date(); // Asigna la fecha actual del préstamo
    }

    // Método para mostrar la información del préstamo
    @Override
    public String toString() {
        // Formatea la fecha del préstamo a un formato específico (dd/MM/yyyy HH:mm)
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        // Retorna una cadena con la información del préstamo
        return "ID del préstamo: " + id + "\n" +
                "ISBN del libro: " + libro.getIsbn() + "\n" +
                "Usuario: " + usuario.getNombre() + "\n" +
                "Título del libro: " + libro.getTitulo() + "\n" +
                "Fecha del préstamo: " + sdf.format(fechaPrestamo) + "\n"; // Muestra la fecha formateada
    }
}
