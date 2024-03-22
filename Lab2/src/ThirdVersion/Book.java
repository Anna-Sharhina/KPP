package ThirdVersion;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;

public class Book implements Externalizable {
    private String title;
    private ArrayList<Author> authors = new ArrayList<>();
    private int year;
    private int number;

    public Book(String title, ArrayList<Author> authors, int year, int number) {
        this.title = title;
        this.authors = authors;
        this.year = year;
        this.number = number;
    }

    public Book(){}

    public String getTitle() {
        return title;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public int getYear() {
        return year;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name: '" + title + '\'' +
                ", authors: " + authors +
                ", year: " + year +
                ", number: " + number +
                '}';
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(title);
        out.writeInt(authors.size());
        for(Author author : authors) author.writeExternal(out);
        out.writeInt(year);
        out.writeInt(number);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        title = (String) in.readObject();
        int size = in.readInt();
        for(int i = 0; i < size; i++){
            Author author = new Author();
            author.readExternal(in);
            authors.add(author);
        }
        year = in.readInt();
        number = in.readInt();
    }
}
