package tcpWork;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MetroCardBank {
    ArrayList<MetroCard> store;

    public MetroCardBank(){
        store = ImportCardsXML.read();
    }

    public void addCard(MetroCard newCard){
        if(store.add(newCard)){
            ExportCardsXML.add(newCard);
        }
    }
    public int numCards(){
        return store.size();
    }

    public boolean removeCard(String serNum) throws IOException, ParserConfigurationException, TransformerException, SAXException {
        for(int i = 0; i < store.size(); i++){
            if(Objects.equals(store.get(i).getSerNum(), serNum)){
                ExportCardsXML.removeCard(serNum);
                store.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean getMoney(String serNum, double money) throws IOException, ParserConfigurationException, TransformerException, SAXException {
        for (MetroCard metroCard : store) {
            if (Objects.equals(metroCard.getSerNum(), serNum)) {
                if (metroCard.payMoney(money)) {
                    ExportCardsXML.payMoney(serNum, money);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addMoney(String serNum, double money) throws IOException, ParserConfigurationException, TransformerException, SAXException {
        for (MetroCard metroCard : store) {
            if (Objects.equals(metroCard.getSerNum(), serNum)) {
                ExportCardsXML.addMoney(serNum, money);
                return metroCard.addMoney(money);
            }
        }
        return false;
    }

    public int findMetroCard(String serNum){
        for(int i = 0; i < store.size(); i++){
            if(Objects.equals(store.get(i).getSerNum(), serNum)){
                return i;
            }
        }
        return -1;
    }

    public ArrayList<MetroCard> getStore() {
        return store;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder("List of MetroCards:");
        for (MetroCard c : store) {
            buf.append("\n\n").append(c);
        }
        return buf.toString();
    }

}
