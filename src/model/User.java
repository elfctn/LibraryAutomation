package model;


//PERSON'DAN KALITIM


import java.util.HashSet;
import java.util.Set;

public class User extends Person {
    private Set<Book> borrowedBooks = new HashSet<>();

    public User(String id, String name) {
        super(id, name);
    }

    public Set<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public boolean canBorrow() {
        return borrowedBooks.size() < 5;
    }
}
