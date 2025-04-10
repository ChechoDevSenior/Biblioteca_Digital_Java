// Clase que representa a un usuario registrado en la biblioteca
public class Usuario {
    // Atributos del usuario
    int id;             // Identificador único del usuario
    String nombre;      // Nombre completo del usuario
    String email;       // Correo electrónico
    String telefono;    // Número de teléfono de contacto

    // Constructor para inicializar un objeto Usuario con sus datos
    public Usuario(int id, String nombre, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    // Método sobrescrito que devuelve una representación en texto del usuario
    @Override
    public String toString(){
        // Ejemplo de salida: Juan Pérez ID: 101
        return nombre + " ID: " + id;
    }
}
