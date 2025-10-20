package model.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.entities.Autor;
import model.entities.Livro;

public class BibliotecaService {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("biblioteca");
    private static final EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static void adicionarLivro(Livro livro){

        try {
            entityManager.getTransaction().begin();
            entityManager.persist(livro);
            entityManager.getTransaction().commit();
        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    public static Autor findAutor(int id){

        Autor autor = entityManager.find(Autor.class, id);
        if(autor == null){
            throw new RuntimeException("Erro! Autor não encontrado");
        }
        else {
            return autor;
        }
    }

    public static Livro findLivro(int id){
        Livro livro = entityManager.find(Livro.class, id);

        if (livro == null){
                throw new RuntimeException("ERRO! Livro não encontrado.");
        }
        else{
            return livro;
        }
    }
}
