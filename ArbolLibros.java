public class ArbolLibros {
    private NodoLibro raiz;

    // Constructor
    public ArbolLibros() {
        raiz = null;
    }

    // Método para insertar un libro en el árbol
    public void insertar(Libro libro) {
        raiz = insertarRec(raiz, libro);
    }

    // Método recursivo para insertar un libro en el árbol
    private NodoLibro insertarRec(NodoLibro raiz, Libro libro) {
        // Si el árbol está vacío, colocamos el libro aquí
        if (raiz == null) {
            raiz = new NodoLibro(libro);
            return raiz;
        }

        // Si el ISBN es menor que el del nodo actual, va al subárbol izquierdo
        if (libro.getIsbn().compareTo(raiz.libro.getIsbn()) < 0) {
            raiz.izquierdo = insertarRec(raiz.izquierdo, libro);
        }
        // Si el ISBN es mayor que el del nodo actual, va al subárbol derecho
        else if (libro.getIsbn().compareTo(raiz.libro.getIsbn()) > 0) {
            raiz.derecho = insertarRec(raiz.derecho, libro);
        }

        // Retornamos el nodo sin cambios
        return raiz;
    }

    // Método para buscar un libro por ISBN
    public Libro buscar(String isbn) {
        NodoLibro resultado = buscarRec(raiz, isbn);
        return (resultado != null) ? resultado.libro : null;
    }

    // Método recursivo para buscar un libro por ISBN
    private NodoLibro buscarRec(NodoLibro raiz, String isbn) {
        // Si el árbol está vacío o el ISBN es igual al del nodo actual, retornamos el nodo
        if (raiz == null || raiz.libro.getIsbn().equals(isbn)) {
            return raiz;
        }

        // Si el ISBN a buscar es menor, buscamos en el subárbol izquierdo
        if (isbn.compareTo(raiz.libro.getIsbn()) < 0) {
            return buscarRec(raiz.izquierdo, isbn);
        }

        // Si el ISBN a buscar es mayor, buscamos en el subárbol derecho
        return buscarRec(raiz.derecho, isbn);
    }

    // Método para eliminar un libro por ISBN
    public void eliminar(String isbn) {
        raiz = eliminarRec(raiz, isbn);
    }

    // Método recursivo para eliminar un libro por ISBN
    private NodoLibro eliminarRec(NodoLibro raiz, String isbn) {
        // Si el árbol está vacío, retornamos null
        if (raiz == null) {
            return raiz;
        }

        // Si el ISBN a eliminar es menor que el del nodo actual, buscamos en el subárbol izquierdo
        if (isbn.compareTo(raiz.libro.getIsbn()) < 0) {
            raiz.izquierdo = eliminarRec(raiz.izquierdo, isbn);
        }
        // Si el ISBN a eliminar es mayor que el del nodo actual, buscamos en el subárbol derecho
        else if (isbn.compareTo(raiz.libro.getIsbn()) > 0) {
            raiz.derecho = eliminarRec(raiz.derecho, isbn);
        }
        // Si encontramos el nodo con el ISBN
        else {
            // Nodo con solo un hijo o sin hijos
            if (raiz.izquierdo == null) {
                return raiz.derecho;
            } else if (raiz.derecho == null) {
                return raiz.izquierdo;
            }

            // Nodo con dos hijos: Obtener el sucesor (el libro más pequeño en el subárbol derecho)
            raiz.libro = obtenerMin(raiz.derecho);

            // Eliminar el sucesor
            raiz.derecho = eliminarRec(raiz.derecho, raiz.libro.getIsbn());
        }
        return raiz;
    }

    // Método para obtener el libro más pequeño en el subárbol derecho
    private Libro obtenerMin(NodoLibro raiz) {
        Libro min = raiz.libro;
        while (raiz.izquierdo != null) {
            min = raiz.izquierdo.libro;
            raiz = raiz.izquierdo;
        }
        return min;
    }

    // Método para mostrar los libros del árbol en orden (inorder traversal)
    public void mostrarLibrosInOrden() {
        mostrarInOrdenRec(raiz);
    }

    private void mostrarInOrdenRec(NodoLibro raiz) {
        if (raiz != null) {
            mostrarInOrdenRec(raiz.izquierdo);
            System.out.println(raiz.libro);
            mostrarInOrdenRec(raiz.derecho);
        }
    }

    public boolean estaVacio(){
        return raiz == null;
    }

}
