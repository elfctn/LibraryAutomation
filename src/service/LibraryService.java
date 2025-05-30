package service;
//Kitaplarla ilgili işlemleri yönetir (ekle, sil, güncelle, listele).
import data.DataStore;
import model.*;

import java.util.*;
import java.util.stream.Collectors;

public class LibraryService {
    private DataStore store;

    public LibraryService(DataStore store) {
        this.store = store;
    }

    public void addBook(Book book) {
        store.getBooks().put(book.getId(), book);
    }

    public void updateBook(String id, Book updatedBook) {
        store.getBooks().put(id, updatedBook);
    }

    public void removeBook(String id) {
        store.getBooks().remove(id);
    }

    public Book findBookById(String id) {
        return store.getBooks().get(id);
    }

    public List<Book> findBooksByTitle(String title) {
        return store.getBooks().values().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> findBooksByAuthor(String authorName) {
        return store.getBooks().values().stream()
                .filter(book -> book.getAuthor().getName().equalsIgnoreCase(authorName))
                .collect(Collectors.toList());
    }

    public List<Book> listBooksByCategory(String categoryName) {
        return store.getBooks().values().stream()
                .filter(book -> book.getCategory().getName().equalsIgnoreCase(categoryName))
                .collect(Collectors.toList());
    }

    public Collection<Book> listAllBooks() {
        return store.getBooks().values();
    }
}
