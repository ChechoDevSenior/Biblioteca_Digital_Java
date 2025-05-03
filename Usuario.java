// Clase que representa a un usuario registrado en la biblioteca
public class Usuario {
    // Atributos del usuario
    int id; // Identificador único del usuario
    String nombre; // Nombre completo del usuario
    String email; // Correo electrónico del usuario
    String telefono; // Número de teléfono de contacto del usuario

    // Constructor para inicializar un objeto Usuario con sus datos
    public Usuario(int id, String nombre, String email, String telefono) {
        this.id = id; // Asigna el ID al usuario
        this.nombre = nombre; // Asigna el nombre al usuario
        this.email = email; // Asigna el correo electrónico al usuario
        this.telefono = telefono; // Asigna el teléfono al usuario
    }

    // Método sobrescrito que devuelve una representación en texto del usuario
    @Override
    public String toString() {
        // Devuelve una cadena representando al usuario con su nombre y ID
        // Ejemplo de salida: Juan Pérez ID: 101
        return nombre + " ID: " + id;
    }

    // Métodos "getter" y "setter" para acceder y modificar los atributos del
    // usuario

    public int getId() {
        return id; // Devuelve el ID del usuario
    }

    public void setId(int id) {
        this.id = id; // Establece el ID del usuario
    }

    public String getNombre() {
        return nombre; // Devuelve el nombre del usuario
    }

    public void setNombre(String nombre) {
        this.nombre = nombre; // Establece el nombre del usuario
    }

    public String getEmail() {
        return email; // Devuelve el correo electrónico del usuario
    }

    public void setEmail(String email) {
        this.email = email; // Establece el correo electrónico del usuario
    }

    public String getTelefono() {
        return telefono; // Devuelve el número de teléfono del usuario
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono; // Establece el número de teléfono del usuario
    }
}
