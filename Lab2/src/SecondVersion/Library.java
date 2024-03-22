package SecondVersion;

import java.io.*;
import java.util.ArrayList;

public class Library implements Serializable {
    private final String name;
    private final ArrayList<BookStore> bookStores;
    private transient ArrayList<BookReader> bookReaders;

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

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeInt(bookReaders.size());
        for(BookReader bookReader : bookReaders){
            out.writeObject(bookReader.getFirstName());
            out.writeObject(bookReader.getSurname());
            out.writeInt(bookReader.getID());
            out.writeInt(bookReader.getBorrowedBooks().size());
            for (Book book : bookReader.getBorrowedBooks()) {
                out.writeObject(book.getTitle());
                out.writeInt(book.getAuthors().size());
                for (Author author : book.getAuthors()) {
                    out.writeObject(author.getFirstName());
                    out.writeObject(author.getSurname());
                }
                out.writeInt(book.getYear());
                out.writeInt(book.getNumber());
            }
        }
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        in.defaultReadObject();
        int readersCount = in.readInt();
        bookReaders = new ArrayList<>();
        for (int i = 0; i < readersCount; i++) {
            String name = (String)in.readObject();
            String surname = (String)in.readObject();
            int bookReaderNumber = in.readInt();
            BookReader bookReader = new BookReader(name, surname, bookReaderNumber);
            int takenBooksCount = in.readInt();
            for (int j = 0; j < takenBooksCount; j++) {
                String bookName = (String)in.readObject();
                int authorsCount = in.readInt();
                ArrayList<Author> authors = new  ArrayList<>();
                for (int k = 0; k < authorsCount; k++) {
                    String authorName = (String)in.readObject();
                    String authorSurname = (String)in.readObject();
                    Author author = new Author(authorName, authorSurname);
                    authors.add(author);
                }
                int year = in.readInt();
                int number = in.readInt();
                Book book = new Book(bookName, authors, year, number);
                bookReader.borrowBook(book);
            }
            bookReaders.add(bookReader);
        }
    }

}
