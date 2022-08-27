package io.github.marcondesnjr.sismovie;

public class Recomendacao {
    
    private Usuario rem;
    private Filme filme;

    public Recomendacao(Usuario rem, Filme filme) {
        this.rem = rem;
        this.filme = filme;
    }

    public Usuario getRem() {
        return rem;
    }

    public void setRem(Usuario rem) {
        this.rem = rem;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }
    
    
}