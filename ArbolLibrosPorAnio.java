// Clase que representa un árbol binario de búsqueda para almacenar libros ordenados por año de publicación
public class ArbolLibrosPorAnio {
    private NodoLibro raiz; // Nodo raíz del árbol

    // Constructor que inicializa el árbol vacío
    public ArbolLibrosPorAnio() {
        raiz = null; // La raíz está inicialmente en null
    }

    // Método público para insertar un libro en el árbol
    public void insertar(Libro libro) {
        long inicio = System.nanoTime();
        raiz = insertarRec(raiz, libro); // Llama al método recursivo para insertar el libro
        long fin = System.nanoTime();
        System.out.println("Tiempo de búsqueda: " + (fin - inicio) + " ns");
    }

    // Método recursivo para insertar un libro en el árbol binario de búsqueda
    private NodoLibro insertarRec(NodoLibro nodo, Libro libro) {
        if (nodo == null)
            return new NodoLibro(libro); // Si el nodo está vacío, crea un nuevo nodo

        // Si el año de publicación del libro es menor que el del nodo actual, se
        // inserta a la izquierda
        if (libro.getAnioPublicacion() < nodo.libro.getAnioPublicacion()) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, libro); // Recursión a la izquierda
        } else {
            nodo.derecho = insertarRec(nodo.derecho, libro); // Recursión a la derecha
        }
        return nodo; // Retorna el nodo actual
    }

    // Método público para mostrar todos los libros en el árbol en orden ascendente
    // por año
    public void mostrarInOrden() {
        long inicio = System.nanoTime();
        mostrarInOrdenRec(raiz); // Llama al método recursivo para mostrar los libros en orden
        long fin = System.nanoTime();
        System.out.println("Tiempo de búsqueda: " + (fin - inicio) + " ns");
    }

    // Método recursivo para mostrar los libros en orden inorden (izquierda, nodo,
    // derecha)
    private void mostrarInOrdenRec(NodoLibro nodo) {
        if (nodo != null) {
            mostrarInOrdenRec(nodo.izquierdo); // Muestra primero los libros a la izquierda
            System.out.println(nodo.libro); // Muestra el libro del nodo actual
            mostrarInOrdenRec(nodo.derecho); // Muestra después los libros a la derecha
        }
    }

    // Método para verificar si el árbol está vacío
    public boolean estaVacio() {
        return raiz == null; // Si la raíz es null, el árbol está vacío
    }
}
