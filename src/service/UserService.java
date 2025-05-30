package service;
//Kullanıcılara kitap ver, geri al, fatura oluştur gibi işlemleri yapar.
import data.DataStore;
import model.*;
import util.InvoiceGenerator;

import java.util.*;

public class UserService {
    private static final int BOOK_LIMIT = 5;
    private static final double BOOK_PRICE = 10.0;

    private DataStore store;

    public UserService(DataStore store) {
        this.store = store;
    }

    public void addUser(User user) {
        store.getUsers().put(user.getId(), user);
    }

    public User findUserById(String id) {
        return store.getUsers().get(id);
    }

    public boolean borrowBook(String userId, String bookId) {
        User user = findUserById(userId);
        Book book = store.getBooks().get(bookId);

        if (user == null || book == null || !book.isAvailable()) {
            return false;
        }

        if (user.getBorrowedBooks().size() >= BOOK_LIMIT) {
            return false;
        }

        book.setAvailable(false);
        user.borrowBook(book);

        Invoice invoice = InvoiceGenerator.generateInvoice(user, book, BOOK_PRICE);
        store.getInvoices().add(invoice);

        return true;
    }

    public boolean returnBook(String userId, String bookId) {
        User user = findUserById(userId);
        Book book = store.getBooks().get(bookId);

        if (user == null || book == null || !user.getBorrowedBooks().contains(book)) {
            return false;
        }

        book.setAvailable(true);
        user.returnBook(book);

        // Refund invoice
        for (Invoice inv : store.getInvoices()) {
            if (inv.getUser().equals(user) && inv.getBook().equals(book) && !inv.isRefunded()) {
                inv.refund();
                break;
            }
        }

        return true;
    }

    public List<Book> listBorrowedBooks(String userId) {
        User user = findUserById(userId);
        return user != null ? (List<Book>) user.getBorrowedBooks() : new ArrayList<>();
    }

    public List<Invoice> getUserInvoices(String userId) {
        return store.getInvoices().stream()
                .filter(inv -> inv.getUser().getId().equals(userId))
                .toList();
    }
}
