package io.github.marcondesnjr.sismovie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Topico {

  private int id;
  private String titulo;
  private String comentario;
  private List<Comentario> comentarios;
  private Usuario criador;
  private Filme filme;

    public Topico(String titulo, String comentario, Usuario criador, Filme filme) {
        this.titulo = titulo;
        this.comentario = comentario;
        this.criador = criador;
        this.filme = filme;
        this.comentarios = new ArrayList<>();
    }

    
    public void addComentario(Comentario c){
        this.comentarios.add(c);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public List<Comentario> getComentarios() {
        return Collections.unmodifiableList(comentarios);
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Usuario getCriador() {
        return criador;
    }

    public void setCriador(Usuario criador) {
        this.criador = criador;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    
    
}