public class Prestamo {
    Libro libro;
    Usuario usuario;

    public Prestamo(Libro libro, Usuario usuario) {
        this.libro = libro;
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "El libro '" + libro.titulo + "' fue prestado a " + usuario.nombre;
    }
}

