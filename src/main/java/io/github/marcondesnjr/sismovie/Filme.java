package io.github.marcondesnjr.sismovie;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Filme {

    private int id;
    private String foto;
    private String titulo;
    private String sinopse;
    private Year ano;
    private List<Genero> generos;
    private List<String> atores;
    private List<String> diretores;

    public Filme(String foto, String titulo, String sinopse, Year ano) {
        this.foto = foto;
        this.titulo = titulo;
        this.sinopse = sinopse;
        this.ano = ano;
        this.generos = new ArrayList<>();
        this.atores = new ArrayList<>();
        this.diretores = new ArrayList<>();
    }
   
    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public Year getAno() {
        return ano;
    }

    public void setAno(Year ano) {
        this.ano = ano;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Genero> getGeneros() {
        return Collections.unmodifiableList(generos);
    }

    public void addGeneros(Genero gen) {
        generos.add(gen);
    }

    public List<String> getAtores() {
        return Collections.unmodifiableList(atores);
    }

    public void addAtor(String ator) {
        atores.add(ator);
    }

    public List<String> getDiretores() {
        return Collections.unmodifiableList(diretores);
    }

    public void addDiretor(String diretor) {
        diretores.add(diretor);
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public void setAtores(List<String> atores) {
        this.atores = atores;
    }

    public void setDiretores(List<String> diretores) {
        this.diretores = diretores;
    }
        
    
    public void adicionarAvaliacao(Avaliacao avl) {
    }

    public void removerAvaliacao(int id) {
    }

    public void adicionarComentario(Comentario com) {
    }

    public Comentario removerComentario(int id) {
        return null;
    }
        
}
