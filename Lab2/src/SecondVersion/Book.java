package SecondVersion;

import java.util.ArrayList;

public class Book {
    private transient final String title;
    private transient final ArrayList<Author> authors;
    private transient final int year;
    private transient final int number;

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
