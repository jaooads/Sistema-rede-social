package com.redesocial.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario {

    private Integer id;
    private String nome;
    private String username;
    private String email;
    private String senha;
    private LocalDateTime dataCadastro;
    private List<Usuario> amigos;
    private List<Post> posts;

    public Usuario(String nome, String username, String email, String senha) {
        this.nome = nome;
        this.username = username;
        this.email = email;
        this.senha = senha;
    }

    public void setAmigos(List<Usuario> amigos) {
        this.amigos = amigos;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public List<Usuario> getAmigos() {
        return amigos;
    }

    public List<Post> getPosts() {
        return posts;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "Id: " + id +
                "Nome: " + nome + '\'' +
                "Username: " + username + '\'' +
                "Email: " + email + '\'' +
                "Senha: " + senha + '\'' +
                "DataCadastro: " + dataCadastro +
                "Amigos: " + amigos +
                "Posts: " + posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(getId(), usuario.getId()) && Objects.equals(getNome(), usuario.getNome()) && Objects.equals(getUsername(), usuario.getUsername()) && Objects.equals(getEmail(), usuario.getEmail()) && Objects.equals(getSenha(), usuario.getSenha()) && Objects.equals(getDataCadastro(), usuario.getDataCadastro()) && Objects.equals(getAmigos(), usuario.getAmigos()) && Objects.equals(getPosts(), usuario.getPosts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getUsername(), getEmail(), getSenha(), getDataCadastro(), getAmigos(), getPosts());
    }

    public boolean adicionarAmigo(Usuario amigo) {
        if (!amigos.contains(amigo)) {
            amigos.add(amigo);
            return true;
        }
        return false;
    }

    ;

    public boolean removerAmigo(Usuario amigo) {
        if (amigos.contains(amigo)) {
            amigos.remove(amigo);
            return true;
        }
        return false;
    }

    public boolean adicionarPost(Post post) {

        if (post == null) {
            return false;
        }
        return posts.add(post);

    }

    private boolean validarUsuario(Usuario usuario) {

        return usuario.getNome() != null && !usuario.getNome().isEmpty() &&
                usuario.getUsername() != null && !usuario.getUsername().isEmpty() &&
                usuario.getEmail() != null && !usuario.getEmail().isEmpty() &&
                usuario.getSenha() != null && !usuario.getSenha().isEmpty();
    }
}
