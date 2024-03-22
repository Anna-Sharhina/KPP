package FirstVersion;

import java.io.Serializable;
import java.util.ArrayList;

public class Library implements Serializable {
    private final String name;
    private final ArrayList<BookStore> bookStores;
    private final ArrayList<BookReader> bookReaders;

    public Library(String name, ArrayList<BookStore> bookStores, ArrayList<BookReader> bookReaders){
        this.name = name;
        this.bookStores = bookStores;
        this.bookReaders = bookReaders;
    }

    public String getName() {
        return name;
    }

    public ArrayList<BookStore> getBookStores() {
        return bookStores;
    }

    public void addBookStores(BookStore bookStore){
        bookStores.add(bookStore);
    }

    public void removeBookStores(BookStore bookStore){
        bookStores.remove(bookStore);
    }

    public ArrayList<BookReader> getBookReaders() {
        return bookReaders;
    }

    public void addBookReader(BookReader reader) {
        bookReaders.add(reader);
    }

    public void removeBookReader(BookReader reader) {
        bookReaders.remove(reader);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Library: ").append(name).append("\n");
        sb.append(" Book stores:").append("\n");
        for (BookStore store : bookStores) {
            sb.append("\t").append(store.getName()).append(":\n");
            for (Book book : store.getBooks()) {
                sb.append("\t\t").append(book.getTitle()).append("\n");
            }
        }
        sb.append(" Book readers:").append("\n");
        for (BookReader reader : bookReaders) {
            sb.append("\t").append(reader.getFirstName()).append(" ").append(reader.getSurname())
                    .append(", ID: ").append(reader.getID()).append("\n");
            sb.append("\t\tBorrowed books:").append("\n");
            for (Book book : reader.getBorrowedBooks()) {
                sb.append("\t\t\t").append(book.getTitle()).append("\n");
            }
        }
        return sb.toString();
    }

}
