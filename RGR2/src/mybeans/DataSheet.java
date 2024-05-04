package mybeans;

import java.util.ArrayList;

public class DataSheet {

    private ArrayList<Data> dataArrayList = null;
    public DataSheet(){
        dataArrayList = new ArrayList<Data>();
    }

    public void addData(Data data){
        dataArrayList.add(data);
    }
    public DataSheet(ArrayList<Data> dataArrayList){
        this.dataArrayList = dataArrayList;
    }

    public Data getDataItem(int rowIndex){
        return dataArrayList.get(rowIndex);
    }

    public Data getDataItem(Data data){
        return dataArrayList.get(dataArrayList.indexOf(data));
    }

    public ArrayList<Data> getDataArrayList() {
        return dataArrayList;
    }

    public int size(){
        return dataArrayList.size();
    }

    public void addDataItem(Data data) {
        dataArrayList.add(data);
    }

    public void removeDataItem(int i) {
        dataArrayList.remove(i);
    }

}
