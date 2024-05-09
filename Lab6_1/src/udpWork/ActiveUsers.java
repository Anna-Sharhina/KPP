package udpWork;


import java.io.Serializable;
import java.util.ArrayList;

public class ActiveUsers implements Serializable {
    private ArrayList<User> list = null;
    private final long serialVersionUID = 1;

    public ActiveUsers(){
        list = new ArrayList<>();
    }

    public void registerUser(User user){
        list.add(user);
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public int getUsersCount(){
        return list.size();
    }

    public boolean isUserRegistered(User user){
        boolean isRegistered = false;

        for (User userFromList: list)
            if (userFromList.equals(user)){
                isRegistered = true;
                break;
            }

        return isRegistered;
    }

    public User getUser(int pos){
        return list.get(pos);
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (User user: list){
            stringBuilder.append("\t").append(user.toString()).append("\n");
        }

        return stringBuilder.toString();
    }


}
