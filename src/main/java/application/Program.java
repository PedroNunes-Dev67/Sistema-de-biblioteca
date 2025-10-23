package application;


import model.services.BibliotecaService;

public class Program {
    public static void main(String[] args) {

        BibliotecaService.alugarLivro(1);
        System.out.println("Livro alugado com sucesso!");
    }
}
