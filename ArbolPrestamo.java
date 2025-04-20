// Clase que representa el Árbol de Búsqueda Binario (BST) para los préstamos
public class ArbolPrestamo {
    private NodoPrestamo raiz;

    // Constructor
    public ArbolPrestamo() {
        raiz = null;
    }

    // Método para insertar un préstamo en el árbol
    public void insertar(Prestamo prestamo) {
        raiz = insertarRec(raiz, prestamo);
    }

    // Método recursivo para insertar un préstamo en el árbol
    private NodoPrestamo insertarRec(NodoPrestamo raiz, Prestamo prestamo) {
        // Si el árbol está vacío, colocamos el préstamo aquí
        if (raiz == null) {
            raiz = new NodoPrestamo(prestamo);
            return raiz;
        }

        // Si el ID del préstamo es menor que el del nodo actual, va al subárbol izquierdo
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

    // Método para buscar un préstamo por ID
    public Prestamo buscar(int id) {
        NodoPrestamo resultado = buscarRec(raiz, id);
        return (resultado != null) ? resultado.prestamo : null;
    }

    // Método recursivo para buscar un préstamo por ID
    private NodoPrestamo buscarRec(NodoPrestamo raiz, int id) {
        // Si el árbol está vacío o el ID es igual al del nodo actual, retornamos el nodo
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

    // Método para eliminar un préstamo por ID (o marcar como devuelto)
    public void eliminar(int id) {
        raiz = eliminarRec(raiz, id);
    }

    // Método recursivo para eliminar un préstamo por ID
    private NodoPrestamo eliminarRec(NodoPrestamo raiz, int id) {
        // Si el árbol está vacío, retornamos null
        if (raiz == null) {
            return raiz;
        }

        // Si el ID a eliminar es menor que el del nodo actual, buscamos en el subárbol izquierdo
        if (id < raiz.prestamo.id) {
            raiz.izquierdo = eliminarRec(raiz.izquierdo, id);
        }
        // Si el ID a eliminar es mayor que el del nodo actual, buscamos en el subárbol derecho
        else if (id > raiz.prestamo.id) {
            raiz.derecho = eliminarRec(raiz.derecho, id);
        }
        // Si encontramos el nodo con el ID
        else {
            // Nodo con solo un hijo o sin hijos
            if (raiz.izquierdo == null) {
                return raiz.derecho;
            } else if (raiz.derecho == null) {
                return raiz.izquierdo;
            }

            // Nodo con dos hijos: Obtener el sucesor (el préstamo con el ID más pequeño en el subárbol derecho)
            raiz.prestamo = obtenerMin(raiz.derecho);

            // Eliminar el sucesor
            raiz.derecho = eliminarRec(raiz.derecho, raiz.prestamo.id);
        }
        return raiz;
    }

    // Método para obtener el préstamo con el ID más pequeño en el subárbol derecho
    private Prestamo obtenerMin(NodoPrestamo raiz) {
        Prestamo min = raiz.prestamo;
        while (raiz.izquierdo != null) {
            min = raiz.izquierdo.prestamo;
            raiz = raiz.izquierdo;
        }
        return min;
    }

    // Método para mostrar los préstamos del árbol en orden (inorder traversal)
    public void mostrarPrestamosInOrden() {
        mostrarInOrdenRec(raiz);
    }

    private void mostrarInOrdenRec(NodoPrestamo raiz) {
        if (raiz != null) {
            mostrarInOrdenRec(raiz.izquierdo);
            System.out.println(raiz.prestamo);
            mostrarInOrdenRec(raiz.derecho);
        }
    }
}
