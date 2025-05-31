package model;

import java.util.UUID;

public class Author {
    private String id;
    private String name;

    public Author(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Tek parametreli constructor - sadece isim verildiğinde id otomatik atanır
    public Author(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return name + " (ID: " + id + ")";
    }
}
