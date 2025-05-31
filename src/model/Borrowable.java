package model;

public interface Borrowable {
    void borrow();
    void returnBook();
    boolean isAvailable();
}
