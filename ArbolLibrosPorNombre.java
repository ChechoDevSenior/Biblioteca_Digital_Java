// Clase que representa un árbol binario de búsqueda para almacenar libros ordenados por título
public class ArbolLibrosPorNombre {
    private NodoLibro raiz; // Nodo raíz del árbol

    // Constructor que inicializa el árbol vacío
    public ArbolLibrosPorNombre() {
        raiz = null; // La raíz está inicialmente en null
    }

    // Método público para insertar un libro en el árbol
    public void insertar(Libro libro) {
        raiz = insertarRec(raiz, libro); // Llama al método recursivo para insertar el libro
    }

    // Método recursivo para insertar un libro en el árbol
    private NodoLibro insertarRec(NodoLibro nodo, Libro libro) {
        // Si el nodo es nulo, creamos un nuevo nodo con el libro
        if (nodo == null)
            return new NodoLibro(libro);

        // Compara el título del libro con el del nodo actual (ignorando
        // mayúsculas/minúsculas)
        // Si el título del libro es menor, se inserta en el subárbol izquierdo
        if (libro.getTitulo().compareToIgnoreCase(nodo.libro.getTitulo()) < 0) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, libro); // Recursión a la izquierda
        } else {
            // Si el título del libro es mayor, se inserta en el subárbol derecho
            nodo.derecho = insertarRec(nodo.derecho, libro); // Recursión a la derecha
        }
        return nodo; // Retorna el nodo actualizado
    }

    // Método para mostrar los libros del árbol en orden (inorder traversal)
    public void mostrarInOrden() {
        mostrarInOrdenRec(raiz); // Llama al método recursivo para mostrar los libros en orden
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
