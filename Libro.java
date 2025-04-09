public class Libro {
    String isbn;
    String titulo;
    int anio;
    String editorial;
    String autor;

    public Libro(String isbn, String titulo, int anio, String editorial, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.anio = anio;
        this.editorial = editorial;
        this.autor = autor;
    }
    @Override
    public String toString(){
        return "["+isbn+"] "+titulo+" - " +autor+ " ("+anio+")";
    }

}
