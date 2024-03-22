package ThirdVersion;

import java.io.*;
import java.util.ArrayList;

public class Library implements Externalizable {
    private String name;
    private ArrayList<BookStore> bookStores = new ArrayList<>();
    private ArrayList<BookReader> bookReaders = new ArrayList<>();

    public Library(String name, ArrayList<BookStore> bookStores, ArrayList<BookReader> bookReaders){
        this.name = name;
        this.bookStores = bookStores;
        this.bookReaders = bookReaders;
    }

    public Library() {
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(bookStores.size());
        for(BookStore bookStore : bookStores) bookStore.writeExternal(out);
        out.writeInt(bookReaders.size());
        for(BookReader bookReader : bookReaders) bookReader.writeExternal(out);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        int size = in.readInt();
        for(int i = 0; i < size; i++){
            BookStore bookStore = new BookStore();
            bookStore.readExternal(in);
            bookStores.add(bookStore);
        }
        int size2 = in.readInt();
        for(int i = 0; i < size2; i++){
            BookReader bookReader = new BookReader();
            bookReader.readExternal(in);
            bookReaders.add(bookReader);
        }
    }

}
