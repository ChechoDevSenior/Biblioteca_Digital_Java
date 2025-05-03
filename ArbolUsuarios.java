// Clase que representa el Árbol de Búsqueda Binario (BST) para los usuarios
public class ArbolUsuarios {
    private NodoUsuario raiz; // Nodo raíz del árbol de usuarios

    // Constructor que inicializa el árbol vacío
    public ArbolUsuarios() {
        raiz = null;
    }

    // Método para insertar un usuario en el árbol
    public void insertar(Usuario usuario) {
        long inicio = System.nanoTime();
        raiz = insertarRec(raiz, usuario); // Llama a la función recursiva para insertar el usuario
        long fin = System.nanoTime();
        System.out.println("Tiempo de inserción: " + (fin - inicio) + " ns");
    }

    // Método recursivo para insertar un usuario en el árbol
    private NodoUsuario insertarRec(NodoUsuario raiz, Usuario usuario) {
        // Si el árbol está vacío, colocamos el usuario aquí
        if (raiz == null) {
            raiz = new NodoUsuario(usuario); // Crear un nuevo nodo para el usuario
            return raiz;
        }

        // Si el ID del usuario es menor que el del nodo actual, va al subárbol
        // izquierdo
        if (usuario.id < raiz.usuario.id) {
            raiz.izquierdo = insertarRec(raiz.izquierdo, usuario); // Inserta en el subárbol izquierdo
        }
        // Si el ID del usuario es mayor que el del nodo actual, va al subárbol derecho
        else if (usuario.id > raiz.usuario.id) {
            raiz.derecho = insertarRec(raiz.derecho, usuario); // Inserta en el subárbol derecho
        }

        // Retorna el nodo sin cambios (caso cuando el ID ya existe o no se inserta
        // nada)
        return raiz;
    }

    // Método para buscar un usuario por su ID
    public Usuario buscar(int id) {
        long inicio = System.nanoTime();
        NodoUsuario resultado = buscarRec(raiz, id); // Llama a la función recursiva para buscar
        long fin = System.nanoTime();
        System.out.println("Tiempo de búsqueda: " + (fin - inicio) + " ns");
        return (resultado != null) ? resultado.usuario : null; // Retorna el usuario si se encuentra
    }

    // Método recursivo para buscar un usuario por ID
    private NodoUsuario buscarRec(NodoUsuario raiz, int id) {
        // Si el árbol está vacío o el ID es igual al del nodo actual, retornamos el
        // nodo
        if (raiz == null || raiz.usuario.id == id) {
            return raiz;
        }

        // Si el ID a buscar es menor, buscamos en el subárbol izquierdo
        if (id < raiz.usuario.id) {
            return buscarRec(raiz.izquierdo, id);
        }

        // Si el ID a buscar es mayor, buscamos en el subárbol derecho
        return buscarRec(raiz.derecho, id);
    }

    // Método para eliminar un usuario por su ID
    public void eliminar(int id) {
        long inicio = System.nanoTime();
        raiz = eliminarRec(raiz, id); // Llama a la función recursiva para eliminar el usuario
        long fin = System.nanoTime();
        System.out.println("Tiempo de búsqueda: " + (fin - inicio) + " ns");
    }

    // Método recursivo para eliminar un usuario por ID
    private NodoUsuario eliminarRec(NodoUsuario raiz, int id) {
        // Si el árbol está vacío, retornamos null
        if (raiz == null) {
            return raiz;
        }

        // Si el ID a eliminar es menor que el del nodo actual, buscamos en el subárbol
        // izquierdo
        if (id < raiz.usuario.id) {
            raiz.izquierdo = eliminarRec(raiz.izquierdo, id);
        }
        // Si el ID a eliminar es mayor que el del nodo actual, buscamos en el subárbol
        // derecho
        else if (id > raiz.usuario.id) {
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

            // Nodo con dos hijos: Obtener el sucesor (el usuario con el ID más pequeño en
            // el subárbol derecho)
            raiz.usuario = obtenerMin(raiz.derecho);

            // Eliminar el sucesor
            raiz.derecho = eliminarRec(raiz.derecho, raiz.usuario.id);
        }
        return raiz;
    }

    // Método para obtener el usuario con el ID más pequeño en el subárbol derecho
    private Usuario obtenerMin(NodoUsuario raiz) {
        Usuario min = raiz.usuario;
        // Recorrer el subárbol izquierdo hasta encontrar el valor más pequeño
        while (raiz.izquierdo != null) {
            min = raiz.izquierdo.usuario;
            raiz = raiz.izquierdo;
        }
        return min;
    }

    // Método para mostrar los usuarios del árbol en orden (inorder traversal)
    public void mostrarUsuariosInOrden() {
        mostrarInOrdenRec(raiz); // Llama a la función recursiva para mostrar en orden
    }

    // Método recursivo para mostrar los usuarios en orden
    private void mostrarInOrdenRec(NodoUsuario raiz) {
        if (raiz != null) {
            mostrarInOrdenRec(raiz.izquierdo); // Muestra el subárbol izquierdo
            System.out.println(raiz.usuario); // Muestra el usuario actual
            mostrarInOrdenRec(raiz.derecho); // Muestra el subárbol derecho
        }
    }

    // Método para verificar si el árbol está vacío
    public boolean estaVacio() {
        return raiz == null; // Retorna true si la raíz es null, es decir, el árbol está vacío
    }
}
