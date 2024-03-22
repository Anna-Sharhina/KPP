package ThirdVersion;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Author extends Human implements Externalizable {

    public Author(String firstName, String surname) {
        super(firstName, surname);
    }

    public Author(){
        super();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(firstName);
        out.writeObject(surname);
    }


    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        firstName =(String) in.readObject();
        surname =(String) in.readObject();
    }
}
