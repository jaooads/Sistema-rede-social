package com.redesocial.gerenciador;

import com.redesocial.modelo.Post;
import com.redesocial.modelo.Comentario;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorPosts {

    private List<Post> posts;

    private int proximoId;

    public GerenciadorPosts() {
        this.posts = new ArrayList<>();
        this.proximoId = 1;
    }



    public void criar(Post post) {
        if (post == null || post.getAutor() == null || post.getConteudo() == null || post.getConteudo().isEmpty()) {
            throw new IllegalArgumentException("Post inválido: autor e conteúdo são obrigatórios.");
        }
        post.setId(proximoId++);
        posts.add(post);
    }

    public Post buscarPorId(int id) {
        for (Post post : posts) {
            if (post.getId() != null && post.getId() == id) {
                return post;
            }
        }
        return null;
    }

    public List<Post> listarPorUsuario(int idUsuario) {
        List<Post> postsUsuario = new ArrayList<>();
        for (Post post : posts) {
            if (post.getAutor() != null && post.getAutor().getId() == idUsuario) {
                postsUsuario.add(post);
            }
        }
        return postsUsuario;
    }

    public void curtir(int idPost, int idUsuario) {
        Post post = buscarPorId(idPost);
        if (post == null) {
            throw new IllegalArgumentException("Post com o ID " + idPost + " não encontrado.");
        }
    }

    public void descurtir(int idPost, int idUsuario) {
        Post post = buscarPorId(idPost);
        if (post == null) {
            throw new IllegalArgumentException("Post com o ID " + idPost + " não encontrado.");
        }
    }

    public void comentar(Comentario comentario) {
        if (comentario == null) {
            throw new IllegalArgumentException("Comentário não pode ser nulo.");
        }

        Post post = buscarPorId(comentario.getPost().getId());
        if (post == null) {
            throw new IllegalArgumentException("Post com o ID " + comentario.getPost().getId() + " não encontrado.");
        }

        boolean sucesso = post.adicionarComentario(comentario);
        if (!sucesso) {
            throw new IllegalArgumentException("Não foi possível adicionar o comentário.");
        }
    }

    public boolean deletar(int id) {
        Post post = buscarPorId(id);
        if (post == null) {
            return false;
        }
        return posts.remove(post);
    }

    private void validarPost(Post post) {
        if (post == null) {
            throw new IllegalArgumentException("Post não pode ser nulo.");
        }

        if (post.getAutor() == null) {
            throw new IllegalArgumentException("O post deve ter ao menos um autor.");
        }

        if (post.getConteudo() == null || post.getConteudo().trim().isEmpty()) {
            throw new IllegalArgumentException("O conteúdo do post não pode ser vazio.");
        }

        if (post.getDataPublicacao() == null) {
            throw new IllegalArgumentException("A data de publicação não pode ser nula.");
        }
    }


}
