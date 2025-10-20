package application;


import model.entities.Livro;
import model.services.BibliotecaService;

public class Program {
    public static void main(String[] args) {

        Livro livro = BibliotecaService.findLivro(1);

        System.out.println(livro.getNome());
    }
}
