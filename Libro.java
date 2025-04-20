// Clase que representa un libro en la biblioteca
public class Libro {
    // Atributos privados que definen las propiedades del libro
    private String isbn; // Código único del libro (clave primaria para búsqueda en árbol)
    private String titulo; // Título del libro
    private String autor; // Autor del libro
    private String editorial; // Editorial del libro
    private boolean disponible; // Indica si el libro está disponible para préstamo

    // Constructor: se utiliza para crear una nueva instancia de Libro con sus datos
    public Libro(String isbn, String titulo, String autor, String editorial) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.disponible = true; // Al momento de crearse, el libro está disponible por defecto
    }

    // Métodos "getter" para obtener información de los atributos (acceso
    // controlado)
    public String getIsbn() {
        return isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public boolean isDisponible() {
        return disponible;
    }

    // Método "setter" para modificar el estado de disponibilidad del libro
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // Método que devuelve una representación legible del libro (útil para mostrar
    // en consola)
    @Override
    public String toString() {
        return "ISBN: " + isbn + ", Título: " + titulo + ", Autor: " + autor +
                ", Editorial: " + editorial + ", Disponible: " + (disponible ? "Sí" : "No");
    }
}
