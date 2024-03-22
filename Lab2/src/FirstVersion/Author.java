package FirstVersion;

import java.io.Serializable;

public class Author extends Human implements Serializable {

    public Author(String firstName, String surname) {
        super(firstName, surname);
    }


}
