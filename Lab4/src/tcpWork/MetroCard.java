package tcpWork;

import java.io.Serializable;

public class MetroCard implements Serializable {
    private String serNum;
    private User usr;
    private String college;
    private double balance;

    public MetroCard(User user, String serNum, String college, double balance){
        this.usr = user;
        this.serNum = serNum;
        this.college = college;
        this.balance = balance;
    }

    public MetroCard() {

    }

    public boolean payMoney(double money){
        if(balance >= money){
            balance -= money;
            return true;
        }
        return false;
    }

    public boolean addMoney(double money){
        balance += money;
        return true;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }

    public double getBalance() {
        return balance;
    }

    public String getCollege() {
        return college;
    }

    public String getSerNum() {
        return serNum;
    }

    public User getUsr() {
        return usr;
    }

    @Override
    public String toString() {
        return "No: " + serNum + "\ntcpWork.User: " + usr +
                "\nCollege: " + college +
                "\nBalance: " + balance;
    }
}
