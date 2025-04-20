// Clase que representa un préstamo de un libro a un usuario
import java.util.Date;

public class Prestamo {
    int id;
    Usuario usuario;
    Libro libro;
    Date fechaPrestamo;

    // Constructor
    public Prestamo(int id, Usuario usuario, Libro libro) {
        this.id = id;
        this.usuario = usuario;
        this.libro = libro;
        this.fechaPrestamo = new Date(); // Fecha actual del préstamo
    }

    // Método para mostrar la información del préstamo
    @Override
    public String toString() {
        return "ID: " + id + ", Usuario: " + usuario.getNombre() + ", Libro: " + libro.getTitulo() + ", Fecha de Préstamo: " + fechaPrestamo;
    }
}
