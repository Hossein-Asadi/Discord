import java.io.*;
import java.util.ArrayList;

public class UsersDatabase {
    private ArrayList<User> allUsers = new ArrayList<>();
        private static UsersDatabase INSTANCE;
        private UsersDatabase(){

        }
        public static UsersDatabase getInstance(){
            if (INSTANCE == null){
                INSTANCE = new UsersDatabase();
            }
            return INSTANCE;
        }
    public User authenticate(String receivedCredentialPacket){
        return checker(receivedCredentialPacket);
    }
    public boolean checkForUsername(String username){
        for (User x : allUsers) {
            if (x.getUsername().equals(username)){
                return false;
            }
        }
        return true;
    }
    public ArrayList<User> getAllUsers() {
            return allUsers;
    }

    private User checker(String receivedCredentialPacket){
        String[] splitted = receivedCredentialPacket.split("\\+");
        for (User x : allUsers) {
            if (x.getUsername().equals(splitted[0]) && x.getPassword().equals(splitted[1])){
                return x;
            }
        }
        return null;
    }

    public void SaveUsersDataBase() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("db/UsersDataBase.db");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(allUsers);
            objectOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
