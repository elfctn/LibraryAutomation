package main;

import data.DataStore;
import model.*;
import service.LibraryService;
import service.UserService;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final DataStore store = new DataStore();
    private static final LibraryService libraryService = new LibraryService(store);
    private static final UserService userService = new UserService(store);

    public static void main(String[] args) {
        System.out.println(" Kütüphane Otomasyon Sistemine Hoş Geldiniz!");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addBook();
                case "2" -> updateBook();
                case "3" -> deleteBook();
                case "4" -> listBooks();
                case "5" -> searchBooks();
                case "6" -> borrowBook();
                case "7" -> returnBook();
                case "8" -> createUser();
                case "9" -> listUserBooks();
                case "10" -> listUserInvoices();
                case "0" -> {
                    running = false;
                    System.out.println("Çıkış yapılıyor...");
                }
                default -> System.out.println("Geçersiz seçim.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n=== Ana Menü ===");
        System.out.println("1. Kitap Ekle");
        System.out.println("2. Kitap Güncelle");
        System.out.println("3. Kitap Sil");
        System.out.println("4. Tüm Kitapları Listele");
        System.out.println("5. Kitap Ara (id, isim, yazar, kategori)");
        System.out.println("6. Kitap Ödünç Al");
        System.out.println("7. Kitap İade Et");
        System.out.println("8. Yeni Kullanıcı Oluştur");
        System.out.println("9. Kullanıcının Aldığı Kitapları Listele");
        System.out.println("10. Kullanıcının Faturalarını Listele");
        System.out.println("0. Çıkış\nSeçiminiz: ");
    }

    private static void addBook() {
        System.out.print("Kitap adı: ");
        String title = scanner.nextLine();
        System.out.print("Yazar adı: ");
        String authorName = scanner.nextLine();
        System.out.print("Kategori adı: ");
        String categoryName = scanner.nextLine();

        Author author = new Author(authorName);
        Category category = new Category(categoryName);
        Book book = new Book(UUID.randomUUID().toString(), title, author, category);
        libraryService.addBook(book);
        System.out.println("Kitap eklendi.");
    }

    private static void updateBook() {
        System.out.print("Güncellenecek kitap ID: ");
        String id = scanner.nextLine();
        Book book = libraryService.findBookById(id);

        if (book == null) {
            System.out.println("Kitap bulunamadı.");
            return;
        }

        System.out.print("Yeni kitap adı: ");
        String title = scanner.nextLine();
        System.out.print("Yeni yazar adı: ");
        String authorName = scanner.nextLine();
        System.out.print("Yeni kategori: ");
        String categoryName = scanner.nextLine();

        Author author = new Author(authorName);
        Category category = new Category(categoryName);
        Book updatedBook = new Book(id, title, author, category);
        updatedBook.setAvailable(book.isAvailable());

        libraryService.updateBook(id, updatedBook);
        System.out.println("Kitap güncellendi.");
    }

    private static void deleteBook() {
        System.out.print("Silinecek kitap ID: ");
        String id = scanner.nextLine();
        libraryService.removeBook(id);
        System.out.println("Kitap silindi.");
    }

    private static void listBooks() {
        for (Book book : libraryService.listAllBooks()) {
            System.out.println(book);
        }
    }

    private static void searchBooks() {
        System.out.println("Arama türü (id/title/author/category): ");
        String type = scanner.nextLine();
        System.out.println("Aranacak ifade: ");
        String value = scanner.nextLine();

        List<Book> results = switch (type.toLowerCase()) {
            case "id" -> {
                Book b = libraryService.findBookById(value);
                yield b != null ? List.of(b) : List.of();
            }
            case "title" -> libraryService.findBooksByTitle(value);
            case "author" -> libraryService.findBooksByAuthor(value);
            case "category" -> libraryService.listBooksByCategory(value);
            default -> List.of();
        };

        if (results.isEmpty()) {
            System.out.println("Sonuç bulunamadı.");
        } else {
            results.forEach(System.out::println);
        }
    }

    private static void borrowBook() {
        System.out.print("Kullanıcı ID: ");
        String userId = scanner.nextLine();
        System.out.print("Kitap ID: ");
        String bookId = scanner.nextLine();

        if (userService.borrowBook(userId, bookId)) {
            System.out.println("Kitap başarıyla ödünç alındı.");
        } else {
            System.out.println("Kitap ödünç alınamadı.");
        }
    }

    private static void returnBook() {
        System.out.print("Kullanıcı ID: ");
        String userId = scanner.nextLine();
        System.out.print("Kitap ID: ");
        String bookId = scanner.nextLine();

        if (userService.returnBook(userId, bookId)) {
            System.out.println("Kitap başarıyla iade edildi.");
        } else {
            System.out.println("İade işlemi başarısız.");
        }
    }

    private static void createUser() {
        System.out.print("Kullanıcı adı: ");
        String name = scanner.nextLine();
        User user = new User(UUID.randomUUID().toString(), name);
        userService.addUser(user);
        System.out.println("Kullanıcı oluşturuldu. ID: " + user.getId());
    }

    private static void listUserBooks() {
        System.out.print("Kullanıcı ID: ");
        String userId = scanner.nextLine();
        List<Book> books = userService.listBorrowedBooks(userId);
        books.forEach(System.out::println);
    }

    private static void listUserInvoices() {
        System.out.print("Kullanıcı ID: ");
        String userId = scanner.nextLine();
        List<Invoice> invoices = userService.getUserInvoices(userId);
        invoices.forEach(System.out::println);
    }
}
