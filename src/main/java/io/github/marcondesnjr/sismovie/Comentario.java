package io.github.marcondesnjr.sismovie;

public class Comentario {

    private int id;
    private String texto;
    private Usuario autor;

    public Comentario(String texto, Usuario autor) {
        this.texto = texto;
        this.autor = autor;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    
    
}
