package model;

//Composition + Interface ekle


public class Book {
    private String id;
    private String title;
    private Author author;
    private Category category;
    private boolean isBorrowed = false;

    public Book(String id, String title, Author author, Category category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public Author getAuthor() { return author; }
    public Category getCategory() { return category; }
    public boolean isBorrowed() { return isBorrowed; }

    public void borrow() { this.isBorrowed = true; }
    public void returnBook() { this.isBorrowed = false; }
}
