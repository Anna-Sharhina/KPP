package FirstVersion;

import java.io.Serializable;
import java.util.ArrayList;

public class BookStore implements Serializable {
    private final String name;
    private final ArrayList<Book> books;

    public BookStore(String name, ArrayList<Book> books) {
        this.name = name;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void addBook(Book book){
        books.add(book);
    }

    public void removeBook(Book book){
        books.remove(book);
    }

    @Override
    public String toString() {
        return "BookStore{" +
                "name: '" + name + '\'' +
                ", books: " + books +
                '}';
    }
}
