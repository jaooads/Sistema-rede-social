package com.redesocial.gerenciador;

import com.redesocial.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GerenciadorUsuarios {

    public GerenciadorUsuarios() {
        this.usuarios = new ArrayList<>();
    }

    private List<Usuario> usuarios;


    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void cadastrar(Usuario usuario) {
        if (usuario != null && validarUsuario(usuario)) {
            usuario.setId(geraId());
            usuarios.add(usuario);
        }
    }

    public Usuario buscarPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    public Usuario buscarPorUsername(String username) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsername().equals(username)) {
                return usuario;
            }
        }
        return null;
    }

    public List<Usuario> buscarPorNome(String nome) {
        List<Usuario> encontrados = new ArrayList<>();
        if (nome != null) {
            for (Usuario usuario : usuarios) {
                if (usuario.getNome() != null && usuario.getNome().contains(nome)) {
                    encontrados.add(usuario);
                }
            }
        }
        return encontrados;
    }


    public boolean atualizar(Usuario usuario) {
        Usuario existente = buscarPorId(usuario.getId());
        if (existente != null) {
            existente.setNome(usuario.getNome());
            existente.setUsername(usuario.getUsername());
            existente.setEmail(usuario.getEmail());
            existente.setSenha(usuario.getSenha());
            return true;
        }
        return false;
    }

    public boolean deletar(int id) {
        Usuario usuario = buscarPorId(id);
        if (usuario != null) {
            usuarios.remove(usuario);
            return true;
        }
        return false;
    }

    public void adicionarAmizade(int idUsuario1, int idUsuario2) {
        Usuario usuario1 = buscarPorId(idUsuario1);
        Usuario usuario2 = buscarPorId(idUsuario2);
        if (usuario1 != null && usuario2 != null && !usuario1.getAmigos().contains(usuario2)) {
            usuario1.adicionarAmigo(usuario2);
            usuario2.adicionarAmigo(usuario1);
        }
    }

    public void removerAmizade(int idUsuario1, int idUsuario2) {
        Usuario usuario1 = buscarPorId(idUsuario1);
        Usuario usuario2 = buscarPorId(idUsuario2);
        if (usuario1 != null && usuario2 != null) {
            usuario1.removerAmigo(usuario2);
            usuario2.removerAmigo(usuario1);
        }
    }

    private boolean validarUsuario(Usuario usuario) {
        return usuario.getNome() != null && !usuario.getNome().isEmpty() &&
                usuario.getUsername() != null && !usuario.getUsername().isEmpty() &&
                usuario.getEmail() != null && !usuario.getEmail().isEmpty() &&
                usuario.getSenha() != null && !usuario.getSenha().isEmpty();
    }

    private int geraId() {
        return usuarios.size() + 1;
    }
}
