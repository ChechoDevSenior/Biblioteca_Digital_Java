public class Usuario {
    int id;
    String nombre;
    String email;
    String telefono;

    public Usuario(int id, String nombre, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }
    @Override
    public String toString(){
        return nombre + " ID: "+id;
    }
    


}
