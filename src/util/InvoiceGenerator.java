package util;
//Basit fatura üretiyor

import model.*;

import java.util.UUID;

public class InvoiceGenerator {
    public static Invoice generateInvoice(User user, Book book, double amount) {
        String id = UUID.randomUUID().toString();
        return new Invoice(id, user, book, amount);
    }
}
