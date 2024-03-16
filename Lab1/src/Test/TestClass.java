package Test;

import java.io.Serializable;

public class TestClass extends TestSuperClass implements Serializable {

    public int age;
    private String name;

    public TestClass(int age, String name){
        this.age = age;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void remake(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName(){
        return name;
    }

    public int getYearOfBirth(){
        return super.year - age;
    }

    public int getAge(){
        return age;
    }

    @Override
    public String toString() {
        return "\nTestClass:\n" +
                "\tage = " + age +
                "\n\tname = " + name;
    }
}