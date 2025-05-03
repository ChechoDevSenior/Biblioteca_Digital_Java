// Clase que representa el Árbol de Búsqueda Binario (BST) para los préstamos
public class ArbolPrestamo {
    NodoPrestamo raiz; // Nodo raíz del árbol de préstamos

    // Constructor del árbol, inicializa la raíz como null
    public ArbolPrestamo() {
        raiz = null;
    }

    // Método para insertar un préstamo en el árbol
    public void insertar(Prestamo prestamo) {
        long inicio = System.nanoTime();
        raiz = insertarRec(raiz, prestamo); // Llama a la función recursiva para insertar
        long fin = System.nanoTime();
        System.out.println("Tiempo de búsqueda: " + (fin - inicio) + " ns");
    }

    // Método recursivo para insertar un préstamo en el árbol
    private NodoPrestamo insertarRec(NodoPrestamo raiz, Prestamo prestamo) {
        // Si el árbol está vacío, colocamos el préstamo aquí
        if (raiz == null) {
            raiz = new NodoPrestamo(prestamo); // Crear nuevo nodo con el préstamo
            return raiz;
        }

        // Si el ID del préstamo es menor que el del nodo actual, va al subárbol
        // izquierdo
        if (prestamo.id < raiz.prestamo.id) {
            raiz.izquierdo = insertarRec(raiz.izquierdo, prestamo);
        }
        // Si el ID del préstamo es mayor que el del nodo actual, va al subárbol derecho
        else if (prestamo.id > raiz.prestamo.id) {
            raiz.derecho = insertarRec(raiz.derecho, prestamo);
        }

        // Retornamos el nodo sin cambios
        return raiz;
    }

    // Método para obtener la raíz del árbol
    public NodoPrestamo getRaiz() {
        return raiz;
    }

    // Método para buscar un préstamo por su ID
    public Prestamo buscar(int id) {
        long inicio = System.nanoTime();
        NodoPrestamo resultado = buscarRec(raiz, id);
        long fin = System.nanoTime();
        System.out.println("Tiempo de búsqueda: " + (fin - inicio) + " ns");
        return (resultado != null) ? resultado.prestamo : null; // Retorna el préstamo si lo encuentra
    }

    // Método recursivo para buscar un préstamo por ID
    private NodoPrestamo buscarRec(NodoPrestamo raiz, int id) {
        // Si el árbol está vacío o el ID es igual al del nodo actual, retornamos el
        // nodo
        if (raiz == null || raiz.prestamo.id == id) {
            return raiz;
        }

        // Si el ID a buscar es menor, buscamos en el subárbol izquierdo
        if (id < raiz.prestamo.id) {
            return buscarRec(raiz.izquierdo, id);
        }

        // Si el ID a buscar es mayor, buscamos en el subárbol derecho
        return buscarRec(raiz.derecho, id);
    }

    // Método para eliminar un préstamo por ID (o marcarlo como devuelto)
    public void eliminar(int id) {
        long inicio = System.nanoTime();
        raiz = eliminarRec(raiz, id); // Llama a la función recursiva para eliminar
        long fin = System.nanoTime();
        System.out.println("Tiempo de búsqueda: " + (fin - inicio) + " ns");
    }

    // Método recursivo para eliminar un préstamo por ID
    private NodoPrestamo eliminarRec(NodoPrestamo raiz, int id) {
        // Si el árbol está vacío, retornamos null
        if (raiz == null) {
            return raiz;
        }

        // Si el ID a eliminar es menor que el del nodo actual, buscamos en el subárbol
        // izquierdo
        if (id < raiz.prestamo.id) {
            raiz.izquierdo = eliminarRec(raiz.izquierdo, id);
        }
        // Si el ID a eliminar es mayor que el del nodo actual, buscamos en el subárbol
        // derecho
        else if (id > raiz.prestamo.id) {
            raiz.derecho = eliminarRec(raiz.derecho, id);
        }
        // Si encontramos el nodo con el ID
        else {
            // Nodo con solo un hijo o sin hijos
            if (raiz.izquierdo == null) {
                return raiz.derecho; // Retorna el hijo derecho si no tiene izquierdo
            } else if (raiz.derecho == null) {
                return raiz.izquierdo; // Retorna el hijo izquierdo si no tiene derecho
            }

            // Nodo con dos hijos: Obtener el sucesor (el préstamo con el ID más pequeño en
            // el subárbol derecho)
            raiz.prestamo = obtenerMin(raiz.derecho);

            // Eliminar el sucesor
            raiz.derecho = eliminarRec(raiz.derecho, raiz.prestamo.id);
        }
        return raiz;
    }

    // Método para obtener el préstamo con el ID más pequeño en el subárbol derecho
    private Prestamo obtenerMin(NodoPrestamo raiz) {
        Prestamo min = raiz.prestamo;
        // Recorrer el subárbol izquierdo hasta encontrar el valor más pequeño
        while (raiz.izquierdo != null) {
            min = raiz.izquierdo.prestamo;
            raiz = raiz.izquierdo;
        }
        return min;
    }

    // Método para mostrar los préstamos del árbol en orden (inorder traversal)
    public void mostrarPrestamosInOrden() {
        mostrarInOrdenRec(raiz); // Llama a la función recursiva para mostrar en orden
    }

    // Método recursivo para mostrar los préstamos en orden
    private void mostrarInOrdenRec(NodoPrestamo raiz) {
        if (raiz != null) {
            mostrarInOrdenRec(raiz.izquierdo); // Muestra el subárbol izquierdo
            System.out.println(raiz.prestamo); // Muestra el préstamo actual
            mostrarInOrdenRec(raiz.derecho); // Muestra el subárbol derecho
        }
    }

    // Método para verificar si el árbol está vacío
    public boolean estaVacio() {
        return raiz == null; // Retorna true si la raíz es null, es decir, el árbol está vacío
    }

    // Método para buscar un préstamo por el ISBN del libro
    public Prestamo buscarPorIsbn(String isbn) {
        return buscarPorIsbnRec(raiz, isbn); // Llama a la función recursiva para buscar por ISBN
    }

    // Método recursivo para buscar un préstamo por el ISBN del libro
    private Prestamo buscarPorIsbnRec(NodoPrestamo nodo, String isbn) {
        if (nodo == null)
            return null; // Si el nodo es null, retornamos null

        // Primero busca en el subárbol izquierdo
        Prestamo encontradoIzq = buscarPorIsbnRec(nodo.izquierdo, isbn);
        if (encontradoIzq != null)
            return encontradoIzq; // Si lo encuentra en el subárbol izquierdo, lo retorna

        // Si el ISBN coincide con el del nodo actual, lo retorna
        if (nodo.prestamo.libro.getIsbn().equals(isbn)) {
            return nodo.prestamo;
        }

        // Si no se encuentra en el subárbol izquierdo ni en el nodo actual, lo busca en
        // el subárbol derecho
        return buscarPorIsbnRec(nodo.derecho, isbn);
    }
}
