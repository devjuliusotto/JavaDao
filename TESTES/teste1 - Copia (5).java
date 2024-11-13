import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

// Classe Livro
class Livro {
    private String titulo;
    private String autor;
    private String isbn;
    private boolean disponivel;

    public Livro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.disponivel = true;
    }

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}

// Classe Usuário
class Usuario {
    private String nome;
    private int id;
    private List<Emprestimo> emprestimos;

    public Usuario(String nome, int id) {
        this.nome = nome;
        this.id = id;
        this.emprestimos = new ArrayList<>();
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void adicionarEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
    }

    public void removerEmprestimo(Emprestimo emprestimo) {
        emprestimos.remove(emprestimo);
    }
}

// Classe Emprestimo
class Emprestimo {
    private Livro livro;
    private Usuario usuario;
    private Date dataEmprestimo;
    private Date dataDevolucao;

    public Emprestimo(Livro livro, Usuario usuario) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = new Date();
        // Definindo a data de devolução para 14 dias após o empréstimo
        this.dataDevolucao = new Date(dataEmprestimo.getTime() + (14L * 24 * 60 * 60 * 1000));
    }

    // Getters e Setters
    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }
}

// Classe Biblioteca
class Biblioteca {
    private List<Livro> catalogo;
    private List<Usuario> usuarios;
    private List<Emprestimo> emprestimosAtuais;

    public Biblioteca() {
        catalogo = new ArrayList<>();
        usuarios = new ArrayList<>();
        emprestimosAtuais = new ArrayList<>();
    }

    // Métodos para gerenciar livros
    public void adicionarLivro(Livro livro) {
        catalogo.add(livro);
        System.out.println("Livro adicionado ao catálogo: " + livro.getTitulo());
    }

    public void removerLivro(Livro livro) {
        catalogo.remove(livro);
        System.out.println("Livro removido do catálogo: " + livro.getTitulo());
    }

    public Livro buscarLivroPorISBN(String isbn) {
        for (Livro livro : catalogo) {
            if (livro.getIsbn().equals(isbn)) {
                return livro;
            }
        }
        return null;
    }

    public void listarLivros() {
        System.out.println("Catálogo de Livros:");
        for (Livro livro : catalogo) {
            System.out.println("Título: " + livro.getTitulo() + ", Autor: " + livro.getAutor() + ", ISBN: " + livro.getIsbn() + ", Disponível: " + livro.isDisponivel());
        }
    }

    // Métodos para gerenciar usuários
    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("Usuário registrado: " + usuario.getNome());
    }

    public Usuario buscarUsuarioPorId(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    public void listarUsuarios() {
        System.out.println("Lista de Usuários:");
        for (Usuario usuario : usuarios) {
            System.out.println("ID: " + usuario.getId() + ", Nome: " + usuario.getNome());
        }
    }

    // Métodos para gerenciar empréstimos
    public void emprestarLivro(String isbn, int usuarioId) {
        Livro livro = buscarLivroPorISBN(isbn);
        Usuario usuario = buscarUsuarioPorId(usuarioId);

        if (livro == null) {
            System.out.println("Livro não encontrado.");
            return;
        }
        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }
        if (!livro.isDisponivel()) {
            System.out.println("Livro indisponível para empréstimo.");
            return;
        }

        Emprestimo emprestimo = new Emprestimo(livro, usuario);
        emprestimosAtuais.add(emprestimo);
        usuario.adicionarEmprestimo(emprestimo);
        livro.setDisponivel(false);

        System.out.println("Livro emprestado com sucesso a " + usuario.getNome());
    }

    public void devolverLivro(String isbn, int usuarioId) {
        Livro livro = buscarLivroPorISBN(isbn);
        Usuario usuario = buscarUsuarioPorId(usuarioId);

        if (livro == null || usuario == null) {
            System.out.println("Livro ou usuário não encontrado.");
            return;
        }

        Emprestimo emprestimoEncontrado = null;
        for (Emprestimo emprestimo : emprestimosAtuais) {
            if (emprestimo.getLivro().equals(livro) && emprestimo.getUsuario().equals(usuario)) {
                emprestimoEncontrado = emprestimo;
                break;
            }
        }

        if (emprestimoEncontrado != null) {
            emprestimosAtuais.remove(emprestimoEncontrado);
            usuario.removerEmprestimo(emprestimoEncontrado);
            livro.setDisponivel(true);
            System.out.println("Livro devolvido com sucesso por " + usuario.getNome());
        } else {
            System.out.println("Empréstimo não encontrado.");
        }
    }

    public void listarEmprestimos() {
        System.out.println("Empréstimos Atuais:");
        for (Emprestimo emprestimo : emprestimosAtuais) {
            System.out.println("Livro: " + emprestimo.getLivro().getTitulo() + ", Usuário: " + emprestimo.getUsuario().getNome() + ", Data de Devolução: " + emprestimo.getDataDevolucao());
        }
    }
}

// Classe principal
public class SistemaBiblioteca {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        // Adicionando alguns livros e usuários iniciais
        biblioteca.adicionarLivro(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", "978-0007525546"));
        biblioteca.adicionarLivro(new Livro("1984", "George Orwell", "978-0451524935"));
        biblioteca.adicionarLivro(new Livro("Dom Quixote", "Miguel de Cervantes", "978-0060934347"));

        biblioteca.registrarUsuario(new Usuario("Alice", 1));
        biblioteca.registrarUsuario(new Usuario("Bob", 2));

        do {
            System.out.println("\n--- Sistema de Gerenciamento de Biblioteca ---");
            System.out.println("1. Listar Livros");
            System.out.println("2. Adicionar Livro");
            System.out.println("3. Remover Livro");
            System.out.println("4. Listar Usuários");
            System.out.println("5. Registrar Usuário");
            System.out.println("6. Emprestar Livro");
            System.out.println("7. Devolver Livro");
            System.out.println("8. Listar Empréstimos");
            System.out.println("9. Sair");
            System.out.print("Selecione uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha

            switch (opcao) {
                case 1:
                    biblioteca.listarLivros();
                    break;
                case 2:
                    System.out.print("Título do Livro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor do Livro: ");
                    String autor = scanner.nextLine();
                    System.out.print("ISBN do Livro: ");
                    String isbn = scanner.nextLine();
                    biblioteca.adicionarLivro(new Livro(titulo, autor, isbn));
                    break;
                case 3:
                    System.out.print("ISBN do Livro a ser removido: ");
                    String isbnRemover = scanner.nextLine();
                    Livro livroRemover = biblioteca.buscarLivroPorISBN(isbnRemover);
                    if (livroRemover != null) {
                        biblioteca.removerLivro(livroRemover);
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                    break;
                case 4:
                    biblioteca.listarUsuarios();
                    break;
                case 5:
                    System.out.print("Nome do Usuário: ");
                    String nomeUsuario = scanner.nextLine();
                    System.out.print("ID do Usuário: ");
                    int idUsuario = scanner.nextInt();
                    scanner.nextLine(); // Consumir nova linha
                    biblioteca.registrarUsuario(new Usuario(nomeUsuario, idUsuario));
                    break;
                case 6:
                    System.out.print("ISBN do Livro: ");
                    String isbnEmprestimo = scanner.nextLine();
                    System.out.print("ID do Usuário: ");
                    int idUsuarioEmprestimo = scanner.nextInt();
                    scanner.nextLine(); // Consumir nova linha
                    biblioteca.emprestarLivro(isbnEmprestimo, idUsuarioEmprestimo);
                    break;
                case 7:
                    System.out.print("ISBN do Livro: ");
                    String isbnDevolucao = scanner.nextLine();
                    System.out.print("ID do Usuário: ");
                    int idUsuarioDevolucao = scanner.nextInt();
                    scanner.nextLine(); // Consumir nova linha
                    biblioteca.devolverLivro(isbnDevolucao, idUsuarioDevolucao);
                    break;
                case 8:
                    biblioteca.listarEmprestimos();
                    break;
                case 9:
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 9);

        scanner.close();
    }
}
