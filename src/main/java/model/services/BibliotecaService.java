package model.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.entities.Autor;
import model.entities.Livro;

import java.util.List;

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
            throw new RuntimeException("Erro! Autor não encontrado.");
        }
        else {
            return autor;
        }
    }

    public static Livro findLivro(int id){
        Livro livro = entityManager.find(Livro.class, id);

        if (livro == null){
                throw new RuntimeException("Erro! Livro não encontrado.");
        }
        else{
            return livro;
        }
    }

    public static List<Livro> findAllLivroForAutor(int id){

        Autor autor = entityManager.find(Autor.class, id);

        if(autor == null){
            throw new RuntimeException("Erro! Autor não encontrado.");
        }
        else{
            return autor.getLista();
        }
    }

    public static List<Livro> findAllLivro(){
        TypedQuery<Livro> list = entityManager.createQuery("SELECT l FROM Livro as l", Livro.class);

        if (list.getResultList().isEmpty()){
            throw new RuntimeException("Erro! Lista não encontrada.");
        }
        List<Livro> listLivro = list.getResultList();
        return listLivro;
    }
}
