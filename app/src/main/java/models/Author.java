package models;

// Classe Autor
public class Author {

    // O nome do autor
    private String name;

    // Construtor da classe
    public Author(String name) {
        this.name = name;
    }

    public String getName() { return name; }
        
    @Override
    public String toString() {
        return name;
    }
}