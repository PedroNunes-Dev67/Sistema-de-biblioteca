package application;


import model.entities.Livro;
import model.services.BibliotecaService;

import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);






        BibliotecaService.closeConnection();
        sc.close();
    }
}
