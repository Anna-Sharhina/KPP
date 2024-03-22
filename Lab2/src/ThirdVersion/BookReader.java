package ThirdVersion;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;

public class BookReader extends Human implements Externalizable {
    private int ID;
    private ArrayList<Book> borrowedBooks = new ArrayList<>();

    public BookReader(String firstName, String surname, int ID) {
        super(firstName, surname);
        this.ID = ID;
        this.borrowedBooks = new ArrayList<>();
    }

    public BookReader() {
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(firstName);
        out.writeObject(surname);
        out.writeInt(borrowedBooks.size());
        for(Book book : borrowedBooks) book.writeExternal(out);
        out.writeInt(ID);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        firstName =(String) in.readObject();
        surname =(String) in.readObject();
        int size = in.readInt();
        for(int i = 0; i < size; i++){
            Book book = new Book();
            book.readExternal(in);
            borrowedBooks.add(book);
        }
        ID = in.readInt();
    }

}
