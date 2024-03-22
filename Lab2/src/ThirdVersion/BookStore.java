package ThirdVersion;

import java.io.*;
import java.util.ArrayList;

public class BookStore implements Externalizable {
    private String name;
    private ArrayList<Book> books = new ArrayList<>();

    public BookStore(String name, ArrayList<Book> books) {
        this.name = name;
        this.books = books;
    }

    public BookStore(){

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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(books.size());
        for(Book book : books) book.writeExternal(out);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        int size = in.readInt();
        for(int i = 0; i < size; i++){
            Book book = new Book();
            book.readExternal(in);
            books.add(book);
        }
    }

}
