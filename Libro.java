// Clase que representa un libro en la biblioteca
public class Libro {
    // Atributos del libro
    String isbn;         // Código ISBN del libro (identificador único)
    String titulo;       // Título del libro
    int anio;            // Año de publicación
    String editorial;    // Editorial del libro
    String autor;        // Autor del libro

    // Constructor para inicializar un objeto Libro con sus datos
    public Libro(String isbn, String titulo, int anio, String editorial, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.anio = anio;
        this.editorial = editorial;
        this.autor = autor;
    }

    // Método sobrescrito que devuelve una representación en texto del libro
    @Override
    public String toString(){
        // Ejemplo de salida: [1234567890] El Quijote - Miguel de Cervantes (1605)
        return "[" + isbn + "] " + titulo + " - " + autor + " (" + anio + ")";
    }
}
