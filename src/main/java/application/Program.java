package application;

import model.entities.Autor;
import model.services.BibliotecaService;

public class Program {
    public static void main(String[] args) {


        Autor autor = BibliotecaService.findAutor(1);

        System.out.println(autor.getNome());
    }
}
