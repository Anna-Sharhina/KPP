package tcpWork;

public class AddMetroCardOperation extends CardOperation {
    private final MetroCard crd;

    public AddMetroCardOperation() {
        crd = new MetroCard();
    }

    public MetroCard getCrd() {
        return crd;
    }
}


