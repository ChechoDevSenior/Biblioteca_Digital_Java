// Clase que representa un libro en la biblioteca
public class Libro {
    // Atributos privados que definen las propiedades del libro
    private String isbn; // Código único del libro (clave primaria para búsqueda en árbol)
    private String titulo; // Título del libro
    private String autor; // Autor del libro
    private int anioPublicacion; // Año de publicación del libro
    private boolean disponible; // Indica si el libro está disponible para préstamo

    // Constructor: se utiliza para crear una nueva instancia de Libro con sus datos
    public Libro(String isbn, String titulo, String autor, int anioPublicacion) {
        this.isbn = isbn; // Asigna el ISBN del libro
        this.titulo = titulo; // Asigna el título del libro
        this.autor = autor; // Asigna el autor del libro
        this.anioPublicacion = anioPublicacion; // Asigna el año de publicación del libro
        this.disponible = true; // Al momento de crearse, el libro está disponible por defecto
    }

    // Métodos "getter" para obtener información de los atributos (acceso
    // controlado)
    public String getIsbn() {
        return isbn; // Devuelve el ISBN del libro
    }

    public String getTitulo() {
        return titulo; // Devuelve el título del libro
    }

    public String getAutor() {
        return autor; // Devuelve el autor del libro
    }

    public int getAnioPublicacion() {
        return anioPublicacion; // Devuelve el año de publicación del libro
    }

    public boolean isDisponible() {
        return disponible; // Devuelve el estado de disponibilidad del libro (si está disponible para
                           // préstamo)
    }

    // Método "setter" para modificar el estado de disponibilidad del libro
    public void setDisponible(boolean disponible) {
        this.disponible = disponible; // Modifica el estado de disponibilidad del libro
    }

    // Método que devuelve una representación legible del libro (útil para mostrar
    // en consola)
    @Override
    public String toString() {
        // Retorna una cadena con la información del libro de forma legible
        return "ISBN: [" + isbn + "], Título: " + titulo + ", Autor: " + autor +
                ", Año de Publicación: [" + anioPublicacion + "], Disponible: " + (disponible ? "Sí" : "No");
    }
}
