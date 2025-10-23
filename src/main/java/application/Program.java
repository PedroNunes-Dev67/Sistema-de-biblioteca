package application;


import model.entities.Livro;
import model.services.BibliotecaService;

import java.util.List;

public class Program {
    public static void main(String[] args) {

        List<Livro> list = BibliotecaService.findAllLivro();

        list.forEach(System.out::println);
    }
}
