package model;

import java.time.LocalDate;

public class Invoice {
    private String id;
    private User user;
    private Book book;
    private double amount;
    private LocalDate date;
    private boolean isRefunded;

    public Invoice(String id, User user, Book book, double amount) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.amount = amount;
        this.date = LocalDate.now();
        this.isRefunded = false;
    }

    public String getId() { return id; }
    public User getUser() { return user; }
    public Book getBook() { return book; }
    public double getAmount() { return amount; }
    public LocalDate getDate() { return date; }
    public boolean isRefunded() { return isRefunded; }
    public void refund() { this.isRefunded = true; }
}
