package application;


import model.entities.Autor;
import model.entities.Livro;
import model.exception.NotFindException;
import model.services.BibliotecaService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

            Scanner sc = new Scanner(System.in);
        try {
            int escolha;
            List<Livro> list;
            List<Autor> listAutor;
            int id;
            do {
                BibliotecaService.menuDeOpcoes();
                System.out.print("Operação: ");
                escolha = sc.nextInt();
                System.out.println();
                switch (escolha) {
                    case 1:
                        list = BibliotecaService.livrosDisponiveis();
                        System.out.println("Livros disponíveis para alugar:\n");
                        list.forEach(l -> System.out.println(l));
                        System.out.println("--------------------------------");
                        System.out.print("Id do livro: ");
                        id = sc.nextInt();
                        BibliotecaService.alugarLivro(id);
                        break;
                    case 2:
                        list = BibliotecaService.livrosDisponiveis();
                        System.out.println("Livros disponíveis para alugar:\n");
                        list.forEach(System.out::println);
                        break;
                    case 3:
                        System.out.println("Autores registrados: \n");
                        listAutor = BibliotecaService.findAllAutor();
                        listAutor.forEach(System.out::println);
                        System.out.println("--------------------------------");
                        System.out.print("Id do autor: ");
                        id = sc.nextInt();
                        list = BibliotecaService.findAllLivroForAutor(id);
                        list.forEach(System.out::println);
                        break;
                    case 4:
                        list = BibliotecaService.findAllLivro();
                        BibliotecaService.comp(list);
                        System.out.println("Dados de todos os livros: \n");
                        list.forEach(l -> System.out.println(l.toString("detalhes")));
                        break;
                    case 5:
                        System.out.println("Lista de todos os autores: \n");
                        listAutor = BibliotecaService.findAllAutor();
                        listAutor.forEach(System.out::println);
                        System.out.println("--------------------------------");
                        break;
                    case 6:
                        sc.nextLine();
                        System.out.print("Nome do livro: ");
                        String nomeLivro = sc.nextLine();
                        System.out.print("Data de publicação (yyyy-MM-dd):");
                        LocalDate localDate = LocalDate.parse(sc.next());
                        System.out.print("Numero de paginas: ");
                        int numeroDePaginas = sc.nextInt();
                        System.out.print("O Autor ja esta cadastrado (S/N): ");
                        char resposta = sc.next().toUpperCase().charAt(0);
                        if (resposta == 'S'){
                            System.out.println("Autores cadastrados: \n");
                            BibliotecaService.findAllAutor().forEach(System.out::println);
                            System.out.print("Id do autor: ");
                            id = sc.nextInt();
                            BibliotecaService.adicionarLivro(new Livro(nomeLivro, localDate, numeroDePaginas,
                                    BibliotecaService.findAutor(id),true));
                        }
                        else{
                            System.out.print("Nome do Autor a ser cadastrado: ");
                            sc.nextLine();
                            String nomeAutor = sc.nextLine();
                            List<Autor> listTest = BibliotecaService.findAllAutor().stream()
                                    .filter(a -> a.getNome().toUpperCase().equals(nomeAutor.toUpperCase())).toList();
                            if (!listTest.isEmpty()){
                                throw new NotFindException("Erro, Autor já cadastrado!");
                            }
                            BibliotecaService.adicionarLivro(new Livro(nomeLivro,localDate,numeroDePaginas,
                                    BibliotecaService.adicionarAutor(new Autor(nomeAutor)), true));
                        }
                        System.out.println("Livro cadastrado com sucesso!");
                        break;
                    case 7:
                        System.out.println("Livros: \n");
                        list = BibliotecaService.findAllLivro();
                        list.forEach(l -> System.out.println(l.toString(true)));
                        System.out.println("--------------------------------");
                        System.out.print("Id do livro: ");
                        id = sc.nextInt();
                        Livro livro = BibliotecaService.atualizarLivro(id);
                        System.out.println("Livro atualizado!");
                        System.out.println(livro);
                        break;
                    case 8:
                        System.out.println("Livros: \n");
                        list = BibliotecaService.findAllLivro();
                        list.forEach(System.out::println);
                        System.out.println("--------------------------------");
                        System.out.print("Id do livro: ");
                        id = sc.nextInt();
                        BibliotecaService.apagarLivro(id);
                        System.out.println("Livro apagado!");
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } while (escolha != 9);
        }
        catch (NotFindException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Opereção finalizada!");
        BibliotecaService.closeConnection();
        sc.close();
    }
}
