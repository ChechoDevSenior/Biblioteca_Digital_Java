// Clase que representa un préstamo de un libro a un usuario
public class Prestamo {
    // Atributos del préstamo
    Libro libro;         // Objeto del libro prestado
    Usuario usuario;     // Usuario al que se le prestó el libro

    // Constructor que inicializa el préstamo con el libro y el usuario involucrado
    public Prestamo(Libro libro, Usuario usuario) {
        this.libro = libro;
        this.usuario = usuario;
    }

    // Método sobrescrito para mostrar una descripción del préstamo
    @Override
    public String toString() {
        // Ejemplo de salida: El libro 'Cien años de soledad' fue prestado a Gabriel García
        return "El libro '" + libro.titulo + "' fue prestado a " + usuario.nombre;
    }
}
