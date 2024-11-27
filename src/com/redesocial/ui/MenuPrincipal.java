package com.redesocial.ui;

import com.redesocial.gerenciador.GerenciadorUsuarios;
import com.redesocial.modelo.Usuario;
import com.redesocial.modelo.Post;


import java.util.Scanner;

public class MenuPrincipal {

    private GerenciadorUsuarios gerenciadorUsuarios;

    public MenuPrincipal(GerenciadorUsuarios gerenciadorUsuarios) {
        this.gerenciadorUsuarios = gerenciadorUsuarios;
    }

    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Fazer Login");
            System.out.println("2. Cadastrar Ysuário");
            System.out.println("3. Exibir Menu");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    fazerLogin();
                    break;
                case 2:
                    cadastrarUsuario();
                    break;
                case 3:
                    exibirMenuLogado();
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 4);
    }

    private void fazerLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o e-mail: ");
        String email = scanner.nextLine();

        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        MenuUsuario menuUsuario = new MenuUsuario(gerenciadorUsuarios);
        menuUsuario.exibirMenu();
    }

    private void exibirMenuLogado() {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Bem-vindo ao sistema! O que deseja fazer?");
            System.out.println("1. Visualizar Perfil");
            System.out.println("2. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 2);
    }

    private void cadastrarUsuario() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o username: ");
        String username = scanner.nextLine();

        System.out.print("Digite o e-mail: ");
        String email = scanner.nextLine();

        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        Usuario usuario = new Usuario(nome, username, email, senha);
        gerenciadorUsuarios.cadastrar(usuario);

        System.out.println("Usuário cadastrado com sucesso!");
    }

    private void exibirMenuLogado(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("=== Menu Principal ===");
            System.out.println("Bem-vindo, " + usuario.getNome());
            System.out.println("1. Ver Perfil");
            System.out.println("2. Criar Post");
            System.out.println("3. Listar Meus Posts");
            System.out.println("4. Adicionar Amigos");
            System.out.println("5. Ver Amigos");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    exibirPerfil(usuario);
                    break;
                case 2:
                    criarPost(usuario);
                    break;
                case 3:
                    listarPosts(usuario);
                    break;
                case 4:
                    adicionarAmigos(usuario);
                    break;
                case 5:
                    verAmigos(usuario);
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 6);
    }

    private void exibirPerfil(Usuario usuario) {
        System.out.println("=== Perfil de " + usuario.getNome() + " ===");
        System.out.println("Username: " + usuario.getUsername());
        System.out.println("Email: " + usuario.getEmail());
        System.out.println("Data de Cadastro: " + usuario.getDataCadastro());
        System.out.println("Total de Amigos: " + usuario.getAmigos().size());
        System.out.println("Total de Posts: " + usuario.getPosts().size());
    }

    private void criarPost(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o conteúdo do post: ");
        String conteudo = scanner.nextLine();

        int idPost = usuario.getPosts().size() + 1;
        Post novoPost = new Post(idPost, usuario, conteudo);

        usuario.getPosts().add(novoPost);
        System.out.println("Post criado com sucesso!");
    }

    private void listarPosts(Usuario usuario) {
        System.out.println("=== Meus Posts ===");
        if (usuario.getPosts().isEmpty()) {
            System.out.println("Você ainda não tem posts.");
        } else {
            for (Post post : usuario.getPosts()) {
                System.out.println(post);
            }
        }
    }

    private void adicionarAmigos(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o username do amigo que deseja adicionar: ");
        String usernameAmigo = scanner.nextLine();

        Usuario amigo = gerenciadorUsuarios.buscarPorUsername(usernameAmigo);

        if (amigo != null && !usuario.getAmigos().contains(amigo)) {
            usuario.getAmigos().add(amigo);
            amigo.getAmigos().add(usuario);
            System.out.println("Amigo adicionado com sucesso!");
        } else {
            System.out.println("Usuário não encontrado ou já é seu amigo.");
        }
    }

    private void verAmigos(Usuario usuario) {
        System.out.println("=== Meus Amigos ===");
        if (usuario.getAmigos().isEmpty()) {
            System.out.println("Você não tem amigos ainda.");
        } else {
            for (Usuario amigo : usuario.getAmigos()) {
                System.out.println(amigo.getNome());
            }
        }
    }


}
