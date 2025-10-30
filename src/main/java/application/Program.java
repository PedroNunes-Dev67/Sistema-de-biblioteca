package application;


import model.entities.Autor;
import model.entities.Livro;
import model.services.BibliotecaService;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int escolha;
        List<Livro> list;
        List<Autor> listAutor;
        int id;
        do {
            BibliotecaService.menuDeOpcoes();
            System.out.print("Operação: ");
            escolha = sc.nextInt();
            System.out.println();
            switch (escolha){
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
                case 7:
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
        }while(escolha!=8);
        System.out.println("Opereção finalizada!");
        BibliotecaService.closeConnection();
        sc.close();
    }
}
