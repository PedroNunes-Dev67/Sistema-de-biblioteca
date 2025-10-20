package application;


import model.entities.Livro;
import model.services.BibliotecaService;

import java.util.List;

public class Program {
    public static void main(String[] args) {

        List<Livro> listLivro = BibliotecaService.findAllLivroForAutor(1);

        listLivro.forEach(livro -> System.out.println(livro.getNome()));
    }
}
