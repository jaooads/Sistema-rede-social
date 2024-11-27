package com.redesocial.ui;

import com.redesocial.modelo.Post;
import com.redesocial.modelo.Usuario;
import com.redesocial.gerenciador.GerenciadorUsuarios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuUsuario {

    private GerenciadorUsuarios gerenciadorUsuarios;
    private Scanner scanner;

    public MenuUsuario(GerenciadorUsuarios gerenciadorUsuarios) {
        this.gerenciadorUsuarios = gerenciadorUsuarios;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;
        Usuario usuarioLogado = null;

        do {
            System.out.println("=== Menu de Gerenciamento de Usuários ===");
            System.out.println("1. Criar Post");
            System.out.println("2. Ver Perfil");
            System.out.println("3. Buscar Usuários");
            System.out.println("4. Gerenciar Amizades");
            System.out.println("5. Ver Feed de Notícias");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    if (usuarioLogado != null) {
                        criarPost(usuarioLogado);
                    } else {
                        System.out.println("Você precisa estar logado para criar um post.");
                    }
                    break;
                case 2:
                    if (usuarioLogado != null) {
                        verPerfil(usuarioLogado);
                    } else {
                        System.out.println("Você precisa estar logado para ver seu perfil.");
                    }
                    break;
                case 3:
                    buscarUsuarios();
                    break;
                case 4:
                    if (usuarioLogado != null) {
                        gerenciarAmizades(usuarioLogado);
                    } else {
                        System.out.println("Você precisa estar logado para gerenciar amizades.");
                    }
                    break;
                case 5:
                    if (usuarioLogado != null) {
                        verFeedNoticias(usuarioLogado);
                    } else {
                        System.out.println("Você precisa estar logado para ver o feed de notícias.");
                    }
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 9);
    }

    private void cadastrarUsuario() {
        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o email do usuário: ");
        String email = scanner.nextLine();

        System.out.print("Digite a senha do usuário: ");
        String senha = scanner.nextLine();

        System.out.println("Usuário cadastrado com sucesso!");

        exibirMenu();

    }

    private void fazerLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o e-mail: ");
        String email = scanner.nextLine();

        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

    }

    private void listarUsuarios() {
        for (Usuario usuario : gerenciadorUsuarios.getUsuarios()) {
            System.out.println(usuario);
        }
    }

    private void criarPost(Usuario usuarioLogado) {
        System.out.print("Digite o conteúdo do post: ");
        String conteudo = scanner.nextLine();

        Post novoPost = new Post(null, usuarioLogado, conteudo);
        novoPost.setDataPublicacao(LocalDateTime.now());
        novoPost.setCurtidas(new ArrayList<>());
        novoPost.setComentarios(new ArrayList<>());

        if (usuarioLogado.getPosts() == null) {
            usuarioLogado.setPosts(new ArrayList<>());
        }
        usuarioLogado.getPosts().add(novoPost);

        System.out.println("Post criado com sucesso!");
    }

    private void verPerfil(Usuario usuarioLogado) {
        System.out.println("Perfil de " + usuarioLogado.getNome());
        System.out.println("Email: " + usuarioLogado.getEmail());
        System.out.println("Data de Cadastro: " + usuarioLogado.getDataCadastro());
        System.out.println("Número de Posts: " + usuarioLogado.getPosts().size());
    }

    private void buscarUsuarios() {
        System.out.print("Digite o nome ou email para buscar: ");
        String termoBusca = scanner.nextLine();

        List<Usuario> usuariosEncontrados = new ArrayList<>();
        for (Usuario usuario : gerenciadorUsuarios.getUsuarios()) {
            if (usuario.getNome().contains(termoBusca) || usuario.getEmail().contains(termoBusca)) {
                usuariosEncontrados.add(usuario);
            }
        }

        if (usuariosEncontrados.isEmpty()) {
            System.out.println("Nenhum usuário encontrado.");
        } else {
            System.out.println("Usuários encontrados:");
            for (Usuario usuario : usuariosEncontrados) {
                System.out.println(usuario);
            }
        }
    }

    private void gerenciarAmizades(Usuario usuarioLogado) {
        System.out.println("1. Adicionar Amigo");
        System.out.println("2. Remover Amigo");
        System.out.println("3. Listar Amigos");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                adicionarAmigo(usuarioLogado);
                break;
            case 2:
                removerAmigo(usuarioLogado);
                break;
            case 3:
                listarAmigos(usuarioLogado);
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private void adicionarAmigo(Usuario usuarioLogado) {
        System.out.print("Digite o email do usuário para adicionar como amigo: ");
        String emailAmigo = scanner.nextLine();

        Usuario amigo = null;
        for (Usuario usuario : gerenciadorUsuarios.getUsuarios()) {
            if (usuario.getEmail().equals(emailAmigo)) {
                amigo = usuario;
                break;
            }
        }

        if (amigo != null && !usuarioLogado.getAmigos().contains(amigo)) {
            usuarioLogado.getAmigos().add(amigo);
            System.out.println(amigo.getNome() + " adicionado como amigo.");
        } else {
            System.out.println("Amigo não encontrado ou já é amigo.");
        }
    }

    private void removerAmigo(Usuario usuarioLogado) {
        System.out.print("Digite o email do usuário para remover como amigo: ");
        String emailAmigo = scanner.nextLine();

        Usuario amigo = null;
        for (Usuario usuario : usuarioLogado.getAmigos()) {
            if (usuario.getEmail().equals(emailAmigo)) {
                amigo = usuario;
                break;
            }
        }

        if (amigo != null) {
            usuarioLogado.getAmigos().remove(amigo);
            System.out.println(amigo.getNome() + " removido como amigo.");
        } else {
            System.out.println("Amigo não encontrado.");
        }
    }

    private void listarAmigos(Usuario usuarioLogado) {
        if (usuarioLogado.getAmigos().isEmpty()) {
            System.out.println("Você não tem amigos.");
        } else {
            System.out.println("Seus amigos:");
            for (Usuario amigo : usuarioLogado.getAmigos()) {
                System.out.println(amigo.getNome() + " (" + amigo.getEmail() + ")");
            }
        }
    }

    private void verFeedNoticias(Usuario usuarioLogado) {
        System.out.println("Feed de Notícias:");
        if (usuarioLogado.getPosts().isEmpty()) {
            System.out.println("Você não tem posts.");
        } else {
            for (Post post : usuarioLogado.getPosts()) {
                System.out.println(post);
            }
        }
    }
}