package Test;

public class TestSuperClass implements TestInterface{

    protected int year = 2023;

    @Override
    public void print(int a) {
        System.out.println(a);
    }
}