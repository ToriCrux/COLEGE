package org.example;
import org.example.Api.CepService;
import org.example.Endereco.Endereco;
import org.example.Filme.Filme;
import org.example.Filme.FilmeRepository;
import org.example.Usuario.Usuario;
import org.example.Usuario.UsuarioRepository;
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

    @Autowired
    private org.example.Endereco.EnderecoRepository EnderecoRepository;

    private static Scanner scanner;

    private final CepService cepService;

    public SistemaFilmes(CepService cepService) {
        this.cepService = cepService;
        this.scanner = new Scanner(System.in);
    }

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
            System.out.println("\n\nBem-Vindo ao Sistema Filmes para Recordar!");
            System.out.println("\nMenu Principal:");
            System.out.println("1. Cadastrar Novo Usuário");
            System.out.println("2. Acessar Sistema");
            System.out.println("3. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    acessarUsuario();
                    break;
                case 3:
                    System.out.println("Obrigado por acessar nosso sistema! Aproveite a experiência ^-^ no Filmes para Recordar");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida! Tente Novamente...");
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
            System.out.println("Email ou senha incorretos! Tente novamente com mais atenção ^-^");
            menuAcessarNovamenteOuSair();
        }
    }

    private void menuAcessarNovamenteOuSair() {
        System.out.println("Opções:");
        System.out.println("1. Acessar Novamente");
        System.out.println("2. Sair");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                acessarUsuario();
                break;
            case 2:
                System.out.println("Obrigado por acessar nosso sistema! Aproveite a experiência ^-^ no Filmes para Recordar");
                System.exit(0);
                break;
            default:
                System.out.println("Opção inválida! Tente Novamente...");
                menuAcessarNovamenteOuSair();
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

        System.out.println("Usuário cadastrado com sucesso! Desfrute a experiência ^-^");

        menuUsuario(nomeUsuario, emailUsuario, novoUsuario.getId());
    }

    private void menuUsuario(String nomeUsuario, String emailUsuario, Long idUsuario) {
        while (true) {
            System.out.println("\n\n"+"Agora sim começa sua experiência nesse sistema!\n" +
                    "O Filmes para Recordar tem o intuito de ser um sistema de gestão pessoal onde você consegue \n" +
                    "ter um histórico de filmes que viu podendo assim ter noção de como foi sua opinião com base na \n" +
                    "última experiência vendo algum filme  que você cadastrou anteriormente! ^-^");
            System.out.println("\nPortanto, Seja Bem-Vindo(a) " + nomeUsuario + "!");
            System.out.println("Menu Principal :");
            System.out.println("1. Novo Registro de Filme");
            System.out.println("2. Meu Usuário");
            System.out.println("3. Suporte ao Sistema");
            System.out.println("4. Sair");

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
                    System.out.println("Obrigado por acessar nosso sistema " + nomeUsuario + "!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida! Tente Novamente...");
            }
        }
    }


    private void novoRegistro(String nomeUsuario, String emailUsuario, Long idUsuario) {
        System.out.println("\nQual o nome do filme a ser registrado?");
        String nomeFilme = scanner.nextLine();
        float notaFilme = lerFloat("Qual a nota para esse filme?(0 a 10)");
        System.out.println("Você recomendaria esse filme para um amigo?(sim ou nao)");
        String recomendacaoFilme = scanner.nextLine();
        System.out.println("Qual o CEP de onde você assistiu esse filme?");
        String cepFilme = scanner.nextLine();

        Endereco endereco = cepService.buscarEnderecoPorCep(cepFilme);

        EnderecoRepository.save(endereco);

        Filme novoFilme = new Filme();
        novoFilme.setUsuario(usuarioRepository.findById(idUsuario).orElseThrow());
        novoFilme.setNomeFilme(nomeFilme);
        novoFilme.setNotaFilme(notaFilme);
        novoFilme.setRecomendacaoFilme(recomendacaoFilme);
        novoFilme.setCep(cepFilme);
        novoFilme.setEndereco(endereco);

        filmeRepository.save(novoFilme);

        System.out.println("Novo registro criado com sucesso! Mais um registro para recordar ^-^!");
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
        System.out.println("\n\nFilmes do Registrados por esse Usuário:");

        List<Filme> filmes = filmeRepository.findByUsuario(usuarioRepository.findById(idUsuario).orElseThrow());

        for (int numRegistro = 1; numRegistro <= filmes.size(); numRegistro++) {
            Filme filme = filmes.get(numRegistro - 1);
            System.out.println(numRegistro + ". " + filme.getNomeFilme());
        }

        menuEditarExcluir(idUsuario);
    }

    private void menuEditarExcluir(Long idUsuario) {
        System.out.println("Opções:");
        System.out.println("1. Editar");
        System.out.println("2. Excluir");
        System.out.println("3. Voltar");

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
                System.out.println("Opção inválida... Tente Novamente!");
                menuEditarExcluir(idUsuario);
        }
    }

    private void editarFilme(Long idUsuario) {
        System.out.println("\nHmm.. Sua opinião sobre esse registro mudou é?!");
        System.out.println("Indique o número do filme que deseja editar:");
        int numFilme = scanner.nextInt();
        scanner.nextLine();

        List<Filme> filmes = filmeRepository.findByUsuario(usuarioRepository.findById(idUsuario).orElseThrow());

        if (numFilme >= 1 && numFilme <= filmes.size()) {
            Filme filmeParaEditar = filmes.get(numFilme - 1);

            System.out.println("\nRegistro Anterior:");
            System.out.println("Nome do Filme: " + filmeParaEditar.getNomeFilme());
            System.out.println("Nota do Filme: " + filmeParaEditar.getNotaFilme());
            System.out.println("Recomendação do Filme: " + filmeParaEditar.getRecomendacaoFilme());
            System.out.println("CEP do Filme: " + filmeParaEditar.getCep());

            System.out.println("\nVamos lá! Digite as novas informações para esse filme:");

            System.out.println("Qual o nome do filme a ser registrado?");
            String novoNomeFilme = scanner.nextLine();
            float novaNotaFilme = lerFloat("Qual nota para esse filme?(0 a 10)");
            System.out.println("Você recomendaria esse filme para um amigo?(sim ou nao)");
            String novaRecomendacaoFilme = scanner.nextLine();
            System.out.println("Qual o CEP de onde você assistiu esse filme?");
            String novoCepFilme = scanner.nextLine();

            filmeParaEditar.setNomeFilme(novoNomeFilme);
            filmeParaEditar.setNotaFilme(novaNotaFilme);
            filmeParaEditar.setRecomendacaoFilme(novaRecomendacaoFilme);
            filmeParaEditar.setCep(novoCepFilme);

            filmeRepository.save(filmeParaEditar);

            System.out.println("O regsitro do seu filme foi editado com sucesso ^-^!");
        } else {
            System.out.println("Número de filme inválido. Informe novamente um válido!");
        }
    }


    private void excluirFilme(Long idUsuario) {
        System.out.println("\nIndique o número do filme que deseja excluir:");
        int numFilme = scanner.nextInt();
        scanner.nextLine();

        List<Filme> filmes = filmeRepository.findByUsuario(usuarioRepository.findById(idUsuario).orElseThrow());

        if (numFilme >= 1 && numFilme <= filmes.size()) {
            Filme filmeParaExcluir = filmes.get(numFilme - 1);
            filmeRepository.delete(filmeParaExcluir);
            System.out.println("Filme excluído com sucesso!");
        } else {
            System.out.println("Número de filme inválido. Informe novamente um válido!");
        }
    }

    private void suporte() {
        System.out.println("\nEsse é um sistema que tem como objetivo possibilitar que o usuário registre os filmes que assistiu e tenha um controle na hora da sua experiência de entretenimento! \nEm caso de problemas, contatar o suporte técnico através do email: suporte_tecnico@gmail.com");
    }

}
