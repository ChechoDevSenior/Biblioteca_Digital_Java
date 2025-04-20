// Clase que representa un nodo en el árbol de préstamos
public class NodoPrestamo {
    Prestamo prestamo;
    NodoPrestamo izquierdo, derecho;

    // Constructor
    public NodoPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
        this.izquierdo = null;
        this.derecho = null;
    }

}
