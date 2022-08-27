package io.github.marcondesnjr.sismovie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Usuario{

    private String nome;
    private String sobrenome;
    private String apelido;
    private String email;
    private String senha;
    private LocalDate dataNasc;
    private String cidade;
    private Estado estado;
    private String foto;
    private Permissao permissao;
    private List<Grupo> grupos;
    private List<Recomendacao> recomendacoes;

    public Usuario(String nome, String sobrenome, String email, String senha,
            LocalDate dataNasc, String cidade, Estado estado, Permissao per) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.dataNasc = dataNasc;
        this.cidade = cidade;
        this.estado = estado;
        this.permissao = per;
        this.grupos = new ArrayList<>();
        this.recomendacoes = new ArrayList<>();
    }

    public List<Recomendacao> getRecomendacoes() {
        return recomendacoes;
    }

    public void setRecomendacoes(List<Recomendacao> recomendacoes) {
        this.recomendacoes = recomendacoes;
    }
    
    public void addRecomendacao(Recomendacao re){
        this.recomendacoes.add(re);
    }
    
    public void addGrupo(Grupo gp){
        this.grupos.add(gp);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
    }

    public List<Grupo> getGrupos() {
        return Collections.unmodifiableList(grupos);
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.email);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }
        
}
