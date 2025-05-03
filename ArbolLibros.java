// Clase que representa un árbol binario de búsqueda para almacenar libros ordenados por ISBN
public class ArbolLibros {
    private NodoLibro raiz; // Nodo raíz del árbol

    // Constructor que inicializa el árbol vacío
    public ArbolLibros() {
        raiz = null; // La raíz está inicialmente en null
    }

    // Método público para insertar un libro en el árbol
    public void insertar(Libro libro) {
        raiz = insertarRec(raiz, libro); // Llama al método recursivo para insertar el libro
    }

    // Método recursivo para insertar un libro en el árbol
    private NodoLibro insertarRec(NodoLibro raiz, Libro libro) {
        // Si el árbol está vacío, colocamos el libro aquí
        if (raiz == null) {
            raiz = new NodoLibro(libro); // Crea un nuevo nodo con el libro
            return raiz; // Retorna el nodo creado
        }

        // Si el ISBN es menor que el del nodo actual, va al subárbol izquierdo
        if (libro.getIsbn().compareTo(raiz.libro.getIsbn()) < 0) {
            raiz.izquierdo = insertarRec(raiz.izquierdo, libro); // Recursión a la izquierda
        }
        // Si el ISBN es mayor que el del nodo actual, va al subárbol derecho
        else if (libro.getIsbn().compareTo(raiz.libro.getIsbn()) > 0) {
            raiz.derecho = insertarRec(raiz.derecho, libro); // Recursión a la derecha
        }

        // Retorna el nodo sin cambios (si el ISBN ya está en el árbol no hace nada)
        return raiz;
    }

    // Método para buscar un libro por ISBN
    public Libro buscar(String isbn) {
        NodoLibro resultado = buscarRec(raiz, isbn); // Llama al método recursivo para buscar el libro
        return (resultado != null) ? resultado.libro : null; // Retorna el libro encontrado o null
    }

    // Método recursivo para buscar un libro por ISBN
    private NodoLibro buscarRec(NodoLibro raiz, String isbn) {
        // Si el árbol está vacío o el ISBN es igual al del nodo actual, retornamos el
        // nodo
        if (raiz == null || raiz.libro.getIsbn().equals(isbn)) {
            return raiz; // Retorna el nodo si se encontró el libro
        }

        // Si el ISBN a buscar es menor, buscamos en el subárbol izquierdo
        if (isbn.compareTo(raiz.libro.getIsbn()) < 0) {
            return buscarRec(raiz.izquierdo, isbn); // Recursión a la izquierda
        }

        // Si el ISBN a buscar es mayor, buscamos en el subárbol derecho
        return buscarRec(raiz.derecho, isbn); // Recursión a la derecha
    }

    // Método para eliminar un libro por ISBN
    public void eliminar(String isbn) {
        raiz = eliminarRec(raiz, isbn); // Llama al método recursivo para eliminar el libro
    }

    // Método recursivo para eliminar un libro por ISBN
    private NodoLibro eliminarRec(NodoLibro raiz, String isbn) {
        // Si el árbol está vacío, retornamos null
        if (raiz == null) {
            return raiz;
        }

        // Si el ISBN a eliminar es menor que el del nodo actual, buscamos en el
        // subárbol izquierdo
        if (isbn.compareTo(raiz.libro.getIsbn()) < 0) {
            raiz.izquierdo = eliminarRec(raiz.izquierdo, isbn); // Recursión a la izquierda
        }
        // Si el ISBN a eliminar es mayor que el del nodo actual, buscamos en el
        // subárbol derecho
        else if (isbn.compareTo(raiz.libro.getIsbn()) > 0) {
            raiz.derecho = eliminarRec(raiz.derecho, isbn); // Recursión a la derecha
        }
        // Si encontramos el nodo con el ISBN a eliminar
        else {
            // Nodo con solo un hijo o sin hijos
            if (raiz.izquierdo == null) {
                return raiz.derecho; // Retorna el subárbol derecho
            } else if (raiz.derecho == null) {
                return raiz.izquierdo; // Retorna el subárbol izquierdo
            }

            // Nodo con dos hijos: Obtener el sucesor (el libro más pequeño en el subárbol
            // derecho)
            raiz.libro = obtenerMin(raiz.derecho);

            // Eliminar el sucesor
            raiz.derecho = eliminarRec(raiz.derecho, raiz.libro.getIsbn()); // Recursión para eliminar el sucesor
        }
        return raiz; // Retorna el nodo actualizado
    }

    // Método para obtener el libro más pequeño en el subárbol derecho
    private Libro obtenerMin(NodoLibro raiz) {
        Libro min = raiz.libro;
        while (raiz.izquierdo != null) {
            min = raiz.izquierdo.libro; // Avanza al subárbol izquierdo
            raiz = raiz.izquierdo; // Cambia a su subárbol izquierdo
        }
        return min; // Retorna el libro más pequeño
    }

    // Método para mostrar los libros del árbol en orden (inorder traversal)
    public void mostrarLibrosInOrden() {
        mostrarInOrdenRec(raiz); // Llama al método recursivo para mostrar los libros en orden
    }

    // Método recursivo para mostrar los libros en orden inorden (izquierda, nodo,
    // derecha)
    private void mostrarInOrdenRec(NodoLibro raiz) {
        if (raiz != null) {
            mostrarInOrdenRec(raiz.izquierdo); // Muestra primero los libros a la izquierda
            System.out.println(raiz.libro); // Muestra el libro del nodo actual
            mostrarInOrdenRec(raiz.derecho); // Muestra después los libros a la derecha
        }
    }

    // Método para verificar si el árbol está vacío
    public boolean estaVacio() {
        return raiz == null; // Si la raíz es null, el árbol está vacío
    }
}
