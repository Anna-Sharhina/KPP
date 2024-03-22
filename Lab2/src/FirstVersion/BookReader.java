package FirstVersion;

import java.io.Serializable;
import java.util.ArrayList;

public class BookReader extends Human implements Serializable {
    private final int ID;
    private final ArrayList<Book> borrowedBooks;

    public BookReader(String firstName, String surname, int ID) {
        super(firstName, surname);
        this.ID = ID;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public String toString() {
        return "\nName: " + firstName + " " +
                "Surname: " + surname +
                ", ID: " + ID;
    }

}
