package com.redesocial.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Post {

    private Integer id;
    private List<Usuario> autor;
    private String conteudo;

    private LocalDateTime dataPublicacao;

    private List<Usuario> curtidas;

    private List<Comentario> comentarios;

    public Post(Integer id, List<Usuario> autor, String conteudo, LocalDateTime dataPublicacao, List<Usuario> curtidas, List<Comentario> comentarios) {
        this.id = id;
        this.autor = autor;
        this.conteudo = conteudo;
        this.dataPublicacao = dataPublicacao;
        this.curtidas = new ArrayList();
        this.comentarios = new ArrayList();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Usuario> getAutor() {
        return autor;
    }

    public void setAutor(List<Usuario> autor) {
        this.autor = autor;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public LocalDateTime getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDateTime dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public List<Usuario> getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(List<Usuario> curtidas) {
        this.curtidas = curtidas;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return
                "Id: " + id +
                        "Autor: " + autor +
                        "Conteudo: " + conteudo + '\'' +
                        "DataPublicacao: " + dataPublicacao +
                        "Curtidas: " + curtidas +
                        "Comentarios: " + comentarios;
    }


    public boolean adicionarCurtida(Usuario usuario) {

        if (!curtidas.contains(usuario)) {
            curtidas.add(usuario);
            return true;
        }
        return false;
    }

    public boolean removerCurtida(Usuario usuario) {
        if (curtidas.contains(usuario)) {
            curtidas.remove(usuario);
            return true;
        }
        return false;
    }

    public boolean adicionarComentario(Comentario comentario) {

        if (comentario != null) {
            comentarios.add(comentario);
            return true;
        }
        return false;
    }


}
