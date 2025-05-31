package model;

public class Book implements Borrowable {
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

    @Override
    public void borrow() { this.isBorrowed = true; }

    @Override
    public void returnBook() { this.isBorrowed = false; }

    @Override
    public boolean isAvailable() {
        return !isBorrowed;
    }

    public void setAvailable(boolean available) {
        this.isBorrowed = !available;
    }

    @Override
    public String toString() {
        return "Kitap [id=" + id
                + ", başlık=" + title
                + ", yazar=" + author
                + ", kategori=" + category
                + ", ödünçDurumu=" + (isBorrowed ? "Ödünç Alındı" : "Mevcut") + "]";
    }
}
