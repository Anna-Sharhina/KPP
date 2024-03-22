package ThirdVersion;

import java.io.Externalizable;

public abstract class Human implements Externalizable {

    protected String firstName;
    protected String surname;

    public Human(String firstName, String surname){
        this.firstName = firstName;
        this.surname = surname;
    }

    public Human(){

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
