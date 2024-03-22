package FirstVersion;

import java.io.Serializable;
import java.util.ArrayList;

public class Book implements Serializable {
    private final String title;
    private final ArrayList<Author> authors;
    private final int year;
    private final int number;

    public Book(String title, ArrayList<Author> authors, int year, int number) {
        this.title = title;
        this.authors = authors;
        this.year = year;
        this.number = number;
    }

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

}
