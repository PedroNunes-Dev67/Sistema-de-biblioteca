package application;


import model.services.BibliotecaService;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BibliotecaService.menuDeOpcoes();




        BibliotecaService.closeConnection();
        sc.close();
    }
}
