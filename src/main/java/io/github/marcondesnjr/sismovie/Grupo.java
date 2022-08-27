package io.github.marcondesnjr.sismovie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grupo {

    private int id;
    private String nome;
    private String descricao;
    private Usuario criador;
    private List<Usuario> participantes;
    private List<Topico> topicos;

    public Grupo(String nome, String descricao, Usuario criador) {
        this.nome = nome;
        this.descricao = descricao;
        this.criador = criador;
        this.participantes = new ArrayList<>();
        this.topicos = new ArrayList<>();
    }

    public void addTopico(Topico tp){
        this.topicos.add(tp);
    }
    
    public void adicionarParticipante(Usuario usr) {
        participantes.add(usr);
    }

    public Usuario removerParticipante(String email) {
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getCriador() {
        return criador;
    }

    public void setCriador(Usuario criador) {
        this.criador = criador;
    }

    public List<Usuario> getParticipantes() {
        return Collections.unmodifiableList(participantes);
    }

    public void setParticipantes(List<Usuario> participantes) {
        this.participantes = participantes;
    }

    public List<Topico> getTopicos() {
         return Collections.unmodifiableList(topicos);
    }

    public void setTopicos(List<Topico> topicos) {
        this.topicos = topicos;
    }

    
    
}
