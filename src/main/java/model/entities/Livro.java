package model.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private LocalDate dataPublicacao;
    private int numeroDePaginas;

    @ManyToOne
    @JoinColumn(name = "idAutor")
    private Autor autor;

    public Livro() {
    }

    public Livro(String nome, LocalDate dataPublicacao, int numeroDePaginas, Autor autor) {
        this.nome = nome;
        this.dataPublicacao = dataPublicacao;
        this.numeroDePaginas = numeroDePaginas;
        this.autor = autor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public int getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public void setNumeroDePaginas(int numeroDePaginas) {
        this.numeroDePaginas = numeroDePaginas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
