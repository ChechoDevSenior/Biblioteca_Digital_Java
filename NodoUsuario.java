// Clase que representa un nodo en el árbol de usuarios
public class NodoUsuario {
    Usuario usuario;
    NodoUsuario izquierdo, derecho;

    // Constructor
    public NodoUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.izquierdo = null;
        this.derecho = null;
    }

}
