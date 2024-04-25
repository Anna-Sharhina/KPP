package tcpWork;

public class ShowBalanceOperation extends CardOperation{
    private String serNum;
    private double money;

    public ShowBalanceOperation(String serNum){
        this.serNum = serNum;
    }

    public String getSerNum() {
        return serNum;
    }

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
