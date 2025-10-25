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
            System.out.println("Erro! Não foi possivel cadastrar o livro.");
        }
    }

    public static void alugarLivro(int id){

        entityManager.getTransaction().begin();
        Livro livro = entityManager.find(Livro.class, id);

        if (livro == null){
            throw new RuntimeException("Erro! Livro não encontrado");
        }

        livro.setStatusDeAluguel(false);
        entityManager.getTransaction().commit();
    }
    
    public static List<Livro> livrosDisponiveis(){

        TypedQuery<Livro> query = entityManager.createQuery("SELECT l FROM Livro as l", Livro.class);

        return query.getResultList().stream().filter(Livro::isStatusDeAluguel).toList();

    }

    public static void apagarLivro(int id){

        entityManager.getTransaction().begin();
        Livro livro = entityManager.find(Livro.class, id);
        if (livro == null){
            throw new RuntimeException("Erro! Livro não encontrado!");
        }
        entityManager.remove(livro);
        entityManager.getTransaction().commit();
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
        return list.getResultList();
    }

    public static List<Autor> findAllAutor(){

        TypedQuery<Autor> query = entityManager.createQuery("SELECT a FROM Autor as a", Autor.class);

        return query.getResultList();
    }

    public static void menuDeOpcoes(){
        System.out.println("--------------------------------");
        System.out.println("          MENU DE OPÇÕES        ");
        System.out.println("--------------------------------");
        System.out.println("(1) Alugar livro");
        System.out.println("(2) Listar todos os livros disponíveis");
        System.out.println("(3) Listar todos os livros por Autor");
        System.out.println("(4) Listar dados de todos os livros");
        System.out.println("(5) Listar todos os autores");
        System.out.println("(6) Atualizar Livro");
        System.out.println("(7) Apagar livro");
        System.out.println("(8) SAIR");
        System.out.println("--------------------------------");
    }

    public static void closeConnection(){

        try{
            if(entityManagerFactory != null){
                entityManagerFactory.close();
            }

            if (entityManager == null){
                entityManager.close();
            }

            System.out.println("Conexão fechada!");
        }
        catch (NullPointerException e){
            System.out.println("Erro! nã foi possível fechar a conexão!");
        }
    }


}
