package SecondVersion;

import java.io.Serializable;

public abstract class Human implements Serializable {

    protected final String firstName;
    protected final String surname;

    public Human(String firstName, String surname){
        this.firstName = firstName;
        this.surname = surname;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getSurname(){
        return surname;
    }

    @Override
    public String toString() {
        return "\nName: " + firstName + " Surname: " + surname;
    }

}
