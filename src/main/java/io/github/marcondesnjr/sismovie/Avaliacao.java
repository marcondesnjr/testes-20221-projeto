package io.github.marcondesnjr.sismovie;

public class Avaliacao {

    private int id;
    private int rating;
    private String desc;
    private Filme filme;
    private Usuario usr;

    public Avaliacao(int rating, String desc) {
        this.rating = rating;
        this.desc = desc;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Usuario getUsr() {
        return usr;
    }

    public void setUsr(Usuario usr) {
        this.usr = usr;
    }

    
    
}
