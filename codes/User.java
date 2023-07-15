import java.util.ArrayList;

public class User {
    // fields
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private UserStatus situation;
    private ArrayList<User> blockedUsers = new ArrayList<>();
    private ArrayList<User> friends = new ArrayList<>();
    private ArrayList<User> friendsRequest = new ArrayList<>();
    // constructor
    public User(String name, String password, String email, String phoneNumber, UserStatus situation) {
        this.username = name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.situation = situation;
    }

    public User(String name, String password, String email, String phoneNumber) {
        this.username = name;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // setters

    public void setFriendsRequest(ArrayList<User> friendsRequest) {
        this.friendsRequest = friendsRequest;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSituation(UserStatus situation) {
        this.situation = situation;
    }

    public void setBlockedUsers(ArrayList<User> blockedUsers) {
        this.blockedUsers = blockedUsers;
    }

    // getters

    public ArrayList<User> getFriendsRequest() {
        return friendsRequest;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserStatus getSituation() {
        return situation;
    }

    public ArrayList<User> getBlockedUsers() {
        return blockedUsers;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }
    // another methods

}
