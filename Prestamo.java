// Clase que representa un préstamo de un libro a un usuario
import java.text.SimpleDateFormat;
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
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    return "ID del préstamo: " + id + "\n" +
           "ISBN del libro: " + libro.getIsbn() + "\n" +
           "Usuario: " + usuario.getNombre() + "\n" +
           "Título del libro: " + libro.getTitulo() + "\n" +
           "Fecha del préstamo: " + sdf.format(fechaPrestamo) + "\n";
}


}
