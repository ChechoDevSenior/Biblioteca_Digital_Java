// Clase que representa un nodo en el árbol de libros
public class NodoLibro {
    Libro libro;
    NodoLibro izquierdo, derecho;

    // Constructor
    public NodoLibro(Libro libro) {
        this.libro = libro;
        this.izquierdo = null;
        this.derecho = null;
    }

}
