package application;

import model.entities.Autor;
import model.entities.Livro;
import model.services.BibliotecaService;

import java.time.LocalDate;

public class Program {
    public static void main(String[] args) {

        Autor autor = BibliotecaService.findAutor(2);

        Livro livro = new Livro("Da terra a Lua", LocalDate.parse("1865-08-14"), 300, autor, true);

        BibliotecaService.adicionarLivro(livro);
        System.out.println("Livro cadastrado!");
    }
}
