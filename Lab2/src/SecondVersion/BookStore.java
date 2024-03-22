package SecondVersion;

import java.io.*;
import java.util.ArrayList;

public class BookStore implements Serializable {
    private final String name;
    private transient ArrayList<Book> books;

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

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); // все, що можна зберігаємо за замовчуванням
        out.writeInt(books.size()); // вручну зберігаємо все що треба
        for (Book b : books) { // для кожної книги зберігаємо все
            out.writeObject(b.getTitle());
            out.writeInt(b.getAuthors().size()); // та список авторів зберігаємо весь
            for (Author a : b.getAuthors()) {
                out.writeObject(a.getFirstName());
                out.writeObject(a.getSurname());
            }
            out.writeInt(b.getYear());
            out.writeInt(b.getNumber());
        }
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        books = new ArrayList<>();
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            String bookName = (String)in.readObject();
            int authorsCount = in.readInt();
            ArrayList <Author> authors = new ArrayList<>();
            for (int k = 0; k < authorsCount; k++) {
                String authorName = (String)in.readObject();
                String authorSurname = (String)in.readObject();
                Author author = new Author(authorName, authorSurname);
                authors.add(author);
            }
            int yearOfWriting = in.readInt();
            int series = in.readInt();
            Book book = new Book(bookName, authors, yearOfWriting, series);
            books.add(book);
        }
    }

}
