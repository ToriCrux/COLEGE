package org.example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class SistemaFilmes implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FilmeRepository filmeRepository;

    private static Scanner scanner;

    public static void main(String[] args) {
        SpringApplication.run(SistemaFilmes.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n\nMenu Principal:");
            System.out.println("1. ACESSAR");
            System.out.println("2. CADASTRAR");
            System.out.println("3. SAIR");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    acessarUsuario();
                    break;
                case 2:
                    cadastrarUsuario();
                    break;
                case 3:
                    System.out.println("Obrigado por acessar nosso sistema");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void acessarUsuario() {
        System.out.println("Informe o email:");
        String email = scanner.nextLine();
        System.out.println("Informe a senha:");
        String senha = scanner.nextLine();

        Optional<Usuario> usuarioOptional = Optional.ofNullable(usuarioRepository.findByEmailAndSenha(email, senha));

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            menuUsuario(usuario.getNome(), email, usuario.getId());
        } else {
            System.out.println("Email ou senha incorretos");
            menuAcessarNovamenteOuSair();
        }
    }

    private void menuAcessarNovamenteOuSair() {
        System.out.println("Opções:");
        System.out.println("1. ACESSAR NOVAMENTE");
        System.out.println("2. SAIR");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                acessarUsuario();
                break;
            case 2:
                System.out.println("Obrigado por acessar nosso sistema");
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida");
                menuAcessarNovamenteOuSair();
        }
    }

    private void menuUsuario(String nomeUsuario, String emailUsuario, Long idUsuario) {
        while (true) {
            System.out.println("\nSeja Bem-Vindo(a) " + nomeUsuario);
            System.out.println("Opções:");
            System.out.println("1. NOVO REGISTRO");
            System.out.println("2. MEU USUARIO");
            System.out.println("3. SUPORTE");
            System.out.println("4. SAIR");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    novoRegistro(nomeUsuario, emailUsuario, idUsuario);
                    break;
                case 2:
                    meuUsuario(nomeUsuario, emailUsuario, idUsuario);
                    break;
                case 3:
                    suporte();
                    break;
                case 4:
                    System.out.println("Obrigado por acessar nosso sistema " + nomeUsuario);
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void cadastrarUsuario() {
        System.out.println("Informe o Nome do Usuário:");
        String nomeUsuario = scanner.nextLine();
        System.out.println("Informe o Email do Usuário:");
        String emailUsuario = scanner.nextLine();
        System.out.println("Informe a Senha do Usuário:");
        String senhaUsuario = scanner.nextLine();

        Usuario novoUsuario = new Usuario(nomeUsuario, emailUsuario, senhaUsuario);
        usuarioRepository.save(novoUsuario);

        System.out.println("Usuário cadastrado com sucesso!");

        menuUsuario(nomeUsuario, emailUsuario, novoUsuario.getId());
    }

    private void novoRegistro(String nomeUsuario, String emailUsuario, Long idUsuario) {
        System.out.println("Nome do Filme:");
        String nomeFilme = scanner.nextLine();
        float notaFilme = lerFloat("Qual nota para esse filme? (De 0 a 10)");
        System.out.println("Você recomendaria esse filme? (preencha com sim ou nao)");
        String recomendacaoFilme = scanner.nextLine();

        Filme novoFilme = new Filme();
        novoFilme.setUsuario(usuarioRepository.findById(idUsuario).orElseThrow());
        novoFilme.setNomeFilme(nomeFilme);
        novoFilme.setNotaFilme(notaFilme);
        novoFilme.setRecomendacaoFilme(recomendacaoFilme);

        filmeRepository.save(novoFilme);

        System.out.println("Novo registro criado com sucesso!");
    }

    private static float lerFloat(String mensagem) {
        float valor = 0.0F;
        boolean entradaValida = false;

        do {
            try {
                System.out.println(mensagem);
                valor = Float.parseFloat(scanner.nextLine());
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número válido.");
            }
        } while (!entradaValida);

        return valor;
    }

    private void meuUsuario(String nomeUsuario, String emailUsuario, Long idUsuario) {
        System.out.println("Filmes do Usuário:");

        List<Filme> filmes = filmeRepository.findByUsuario(usuarioRepository.findById(idUsuario).orElseThrow());

        for (int numRegistro = 1; numRegistro <= filmes.size(); numRegistro++) {
            Filme filme = filmes.get(numRegistro - 1);
            System.out.println(numRegistro + ". " + filme.getNomeFilme());
        }

        menuEditarExcluir(idUsuario);
    }

    private void suporte() {
        System.out.println("Esse é um sistema que tem como objetivo possibilitar que o usuário registre os filmes que assistiu e tenha um controle na hora da sua experiência de entretenimento! \nEm caso de problemas, contatar o suporte técnico através do email: suporte_tecnico@gmail.com");
    }

    private void menuEditarExcluir(Long idUsuario) {
        System.out.println("Opções:");
        System.out.println("1. EDITAR");
        System.out.println("2. EXCLUIR");
        System.out.println("3. VOLTAR");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                editarFilme(idUsuario);
                break;
            case 2:
                excluirFilme(idUsuario);
                break;
            case 3:
                break;
            default:
                System.out.println("Opção inválida");
                menuEditarExcluir(idUsuario);
        }
    }

    private void excluirFilme(Long idUsuario) {
        System.out.println("Indique o número do filme que deseja excluir:");
        int numFilme = scanner.nextInt();
        scanner.nextLine();

        List<Filme> filmes = filmeRepository.findByUsuario(usuarioRepository.findById(idUsuario).orElseThrow());

        if (numFilme >= 1 && numFilme <= filmes.size()) {
            Filme filmeParaExcluir = filmes.get(numFilme - 1);
            filmeRepository.delete(filmeParaExcluir);
            System.out.println("Filme excluído com sucesso!");
        } else {
            System.out.println("Número de filme inválido.");
        }
    }

    private void editarFilme(Long idUsuario) {
        System.out.println("Indique o número do filme que deseja editar:");
        int numFilme = scanner.nextInt();
        scanner.nextLine();

        List<Filme> filmes = filmeRepository.findByUsuario(usuarioRepository.findById(idUsuario).orElseThrow());

        if (numFilme >= 1 && numFilme <= filmes.size()) {
            Filme filmeParaEditar = filmes.get(numFilme - 1);

            System.out.println("Nome do Filme:");
            String novoNomeFilme = scanner.nextLine();
            float novaNotaFilme = lerFloat("Qual nota para esse filme? (De 0 a 10)");
            System.out.println("Você recomendaria esse filme? (preencha com sim ou nao)");
            String novaRecomendacaoFilme = scanner.nextLine();

            filmeParaEditar.setNomeFilme(novoNomeFilme);
            filmeParaEditar.setNotaFilme(novaNotaFilme);
            filmeParaEditar.setRecomendacaoFilme(novaRecomendacaoFilme);

            filmeRepository.save(filmeParaEditar);

            System.out.println("Filme editado com sucesso!");
        } else {
            System.out.println("Número de filme inválido.");
        }
    }

}
