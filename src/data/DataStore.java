package data;

//Kitap, kullanıcı vb. verileri saklayacak

import model.*;

import java.util.*;


public class DataStore {
    private Map<String, Book> books = new HashMap<>();
    private Map<String, User> users = new HashMap<>();
    private Map<String, Author> authors = new HashMap<>();
    private Map<String, Category> categories = new HashMap<>();
    private List<Invoice> invoices = new ArrayList<>();

    public Map<String, Book> getBooks() { return books; }
    public Map<String, User> getUsers() { return users; }
    public Map<String, Author> getAuthors() { return authors; }
    public Map<String, Category> getCategories() { return categories; }
    public List<Invoice> getInvoices() { return invoices; }
}
