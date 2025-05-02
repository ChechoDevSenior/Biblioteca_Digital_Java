// Clase que representa el Árbol de Búsqueda Binario (BST) para los usuarios
public class ArbolUsuarios {
    private NodoUsuario raiz;

    // Constructor
    public ArbolUsuarios() {
        raiz = null;
    }

    // Método para insertar un usuario en el árbol
    public void insertar(Usuario usuario) {
        raiz = insertarRec(raiz, usuario);
    }

    // Método recursivo para insertar un usuario en el árbol
    private NodoUsuario insertarRec(NodoUsuario raiz, Usuario usuario) {
        // Si el árbol está vacío, colocamos el usuario aquí
        if (raiz == null) {
            raiz = new NodoUsuario(usuario);
            return raiz;
        }

        // Si el ID del usuario es menor que el del nodo actual, va al subárbol
        // izquierdo
        if (usuario.id < raiz.usuario.id) {
            raiz.izquierdo = insertarRec(raiz.izquierdo, usuario);
        }
        // Si el ID del usuario es mayor que el del nodo actual, va al subárbol derecho
        else if (usuario.id > raiz.usuario.id) {
            raiz.derecho = insertarRec(raiz.derecho, usuario);
        }

        // Retornamos el nodo sin cambios
        return raiz;
    }

    // Método para buscar un usuario por ID
    public Usuario buscar(int id) {
        NodoUsuario resultado = buscarRec(raiz, id);
        return (resultado != null) ? resultado.usuario : null;
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

    // Método para eliminar un usuario por ID
    public void eliminar(int id) {
        raiz = eliminarRec(raiz, id);
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
                return raiz.derecho;
            } else if (raiz.derecho == null) {
                return raiz.izquierdo;
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
        while (raiz.izquierdo != null) {
            min = raiz.izquierdo.usuario;
            raiz = raiz.izquierdo;
        }
        return min;
    }

    // Método para mostrar los usuarios del árbol en orden (inorder traversal)
    public void mostrarUsuariosInOrden() {
        mostrarInOrdenRec(raiz);
    }

    private void mostrarInOrdenRec(NodoUsuario raiz) {
        if (raiz != null) {
            mostrarInOrdenRec(raiz.izquierdo);
            System.out.println(raiz.usuario);
            mostrarInOrdenRec(raiz.derecho);
        }
    }

    public boolean estaVacio(){
        return raiz == null;
    }

}
