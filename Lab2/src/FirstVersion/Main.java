package FirstVersion;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Author author1 = new Author("Ivan", "Ivanov");
        Author author2 = new Author("Oleg", "Olegov");

        Book book1 = new Book("Blood", new ArrayList<>(Arrays.asList(author1, author2)), 1, 1);
        Book book2 = new Book("Java", new ArrayList<>(List.of(author1)), 2, 2);

        BookStore bs1 = new BookStore("BookWorld", new ArrayList<>(Arrays.asList(book1, book2)));

        BookReader br1 = new BookReader("Dmitro", "Dmitrov", 10);
        BookReader br2 = new BookReader("Nastya", "Family", 15);

        Library library = new Library("Library", new ArrayList<>(List.of(bs1)), new ArrayList<>(List.of(br1)));

        br1.borrowBook(book1);
        br1.borrowBook(book2);

        br2.borrowBook(book2);

        library.addBookReader(br2);

        System.out.println("First version:");
        System.out.println("Serialization:");
        System.out.println(library);
        serializeObject("FirstData.dat", library);

        library = (Library) deSerializeObject("FirstData.dat");
        System.out.println("Deserialization:");
        System.out.println(library);

    }

    public static Object deSerializeObject(String fileName){
        Object obj = null;
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileName));
            obj = is.readObject();
            is.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static void serializeObject(String fileName, Object obj){
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
            os.writeObject(obj);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
