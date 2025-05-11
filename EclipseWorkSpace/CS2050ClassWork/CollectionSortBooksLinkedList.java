import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;

public class CollectionSortBooksLinkedList {

    public static void main(String[] args) {

        // Step 1: Create a LinkedList (dynamic)
        LinkedList<Book> bookInventory = new LinkedList<>();

        // Step X: Allow user to enter a new book and search by title using HashMap
        Scanner scanner = new Scanner(System.in);
        Map<String, Book> bookMapByTitle = new HashMap<>();

        // Add all current books to the map by title
        for (Book book : bookInventory) {
            bookMapByTitle.put(book.getTitle(), book);
        }

        System.out.print("Enter a book title to add: ");
        String newTitle = scanner.nextLine();
        System.out.print("Enter the author: ");
        String newAuthor = scanner.nextLine();
        System.out.print("Enter the year: ");
        int newYear = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline

        Book newBook = new Book(newTitle, newAuthor, newYear);
        bookInventory.add(newBook);
        bookMapByTitle.put(newTitle, newBook);

        System.out.print("Enter a book title to search: ");
        String searchTitle = scanner.nextLine();
        if (bookMapByTitle.containsKey(searchTitle)) {
            System.out.println("Book found: " + bookMapByTitle.get(searchTitle));
        } else {
            System.out.println("Book not found.");
        }

        // Preload books
        bookInventory.add(new Book("Unmasking AI", "Dr. Joy Buolamwini", 2023));
        bookInventory.add(new Book("Hello World", "Hannah Fry", 2018));
        bookInventory.add(new Book("The Mathematics of Love", "Hannah Fry", 2015));
        bookInventory.add(new Book("Weapons of Math Destruction", "Cathy O’Neil", 2016));
        bookInventory.add(new Book("Race After Technology", "Ruha Benjamin", 2019));

        System.out.println("Original LinkedList of books:");
        for (Book currentBook : bookInventory) {
            System.out.println(currentBook);
        }
        System.out.println();

        // Step 2: Convert to ArrayList for sorting
        List<Book> books = new ArrayList<>(bookInventory);

        // Step 3: Sort by Title
        System.out.println("Books sorted by title:");
        books.sort(Comparator.comparing(Book::getTitle));
        for (Book currentBook : books) {
            System.out.println(currentBook);
        }
        System.out.println();

        // Step 4: Sort by year - newest to oldest
        System.out.println("Books sorted by year (newest to oldest):");
        books.sort(Comparator.comparing(Book::getYear).reversed());
        for (Book book : books) {
            System.out.println(book);
        }
        System.out.println();

        // Step 5: Sort by author then title
        System.out.println("Books sorted by author then title:");
        books.sort(Comparator.comparing(Book::getAuthor).thenComparing(Book::getTitle));
        for (Book book : books) {
            System.out.println(book);
        }
        System.out.println();

        // Step 6: Add Queue for signing out books
        Queue<Book> signOutQueue = new LinkedList<>();
        signOutQueue.add(bookInventory.get(0)); // Unmasking AI
        signOutQueue.add(bookInventory.get(2)); // Weapons of Math Destruction

        System.out.println("Sign-out queue:");
        for (Book book : signOutQueue) {
            System.out.println(book);
        }
        System.out.println();

        // Step 7: Mark books in queue as signed out
        for (Book book : signOutQueue) {
            book.setSignedOut(true);
        }

        // Step 7: Process signed out books
        System.out.println("Signed out books:");
        for (Book book : books) {
            if (book.isSignedOut()) {
                System.out.println(book);
            }
        }
        System.out.println();

        // Step 8: Search by author
        System.out.println("Searching for books by Hannah Fry:");
        List<Book> searchBooksResult = findBooksByAuthor(bookInventory, "Hannah Fry");
        printBooks(searchBooksResult, "Hannah Fry", -1);

        // Step 9: Dynamic search by author and year
        System.out.print("Enter author name to search: ");
        String searchAuthor = scanner.nextLine();

        System.out.print("Enter publication year to search: ");
        int searchYear = scanner.nextInt();
        scanner.nextLine(); // consume leftover newline

        List<Book> searchResults = searchBooksByAuthorAndYear(books, searchAuthor, searchYear);
        if (searchResults.isEmpty()) {
            System.out.println("No books found for that author and year.");
        } else {
            System.out.println("Search results:");
            for (Book book : searchResults) {
                System.out.println(book);
            }
        }
        System.out.println();

        // Step 10: Group books by year using TreeMap
        TreeMap<Integer, List<Book>> booksByYear = new TreeMap<>();

        for (Book currentBook : bookInventory) {
            booksByYear.putIfAbsent(currentBook.getYear(), new ArrayList<>());
            booksByYear.get(currentBook.getYear()).add(currentBook);
        }

        System.out.println("Books grouped by year:");
        for (Map.Entry<Integer, List<Book>> entry : booksByYear.entrySet()) {
            System.out.println("\nYear: " + entry.getKey());
            for (Book book : entry.getValue()) {
                System.out.println(book);
            }
        }
        System.out.println();
    }

    public static List<Book> findBooksByAuthor(List<Book> inventory, String author) {
        List<Book> results = new ArrayList<>();
        for (Book currentBook : inventory) {
            if (currentBook.getAuthor().equalsIgnoreCase(author)) {
                results.add(currentBook);
            }
        }
        return results;
    }

    public static List<Book> searchBooksByAuthorAndYear(List<Book> books, String author, int year) {
        List<Book> filtered = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author) && book.getYear() == year) {
                filtered.add(book);
            }
        }
        filtered.sort(Comparator.comparing(Book::getTitle));
        return filtered;
    }

    public static void printBooks(List<Book> books, String author, int year) {
        String label = (year != -1) ? " in " + year : "";
        if (books.isEmpty()) {
            System.out.println("No books found by " + author + label + ".");
        } else {
            System.out.println("Books by " + author + label + ":");
            for (Book currentBook : books) {
                System.out.println(currentBook);
            }
        }
    }
}

// Book class with signedOut support
class Book {
    private String title;
    private String author;
    private int year;
    private boolean signedOut;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.signedOut = false;
    }

    public boolean isSignedOut() {
        return signedOut;
    }

    public void setSignedOut(boolean signedOut) {
        this.signedOut = signedOut;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }

    @Override
    public String toString() {
        return "\"" + title + "\" by " + author + " (" + year + ")" + (signedOut ? " [SIGNED OUT]" : "");
    }
}
