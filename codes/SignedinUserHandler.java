import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SignedinUserHandler implements Runnable{
    Scanner scanner = new Scanner(System.in);
    private User currentUser;
    private boolean isSignedStillSignedin = true;
    public SignedinUserHandler(User currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            while (isSignedStillSignedin){
                printMainMenu();
                int choice;
                do{
                    System.out.print(">> ");
                    choice = scanner.nextInt();
                    switch (choice) {
                        case 1:
                         privateChats();
                            break;
                        case 2:
                         friendsList();
                            break;
                        case 3:
                         serversStuff();
                            break;
                        case 4:
                            friendsRequest();
                            break;
                        case 5:
                             globalSearch();
                            break;
                        case 6:
                            blockedUsers();
                            break;
                        case 7:
                            setting();
                            break;
                        case 8:
                            isSignedStillSignedin = false;
                            break;
                        default:
                            System.out.println("invalid input!!! try again");
                    }
                }while (choice<1 || choice >8);
            }
            Thread.currentThread().interrupt();
        }
    }
    public void printMainMenu(){
        System.out.println("1) Private chats and calls"); //incomplete
        System.out.println("2) Friends list"); // complete
        System.out.println("3) Servers stuff");
        System.out.println("4) Friends requests");  // complete
        System.out.println("5) Global search");
        System.out.println("6) Blocked users");  // complete
        System.out.println("7) Settings");   // complete
        System.out.println("8) Log out");  // complete
    }
    // it is incomplete
    public void privateChats(){
        System.out.println("1) Show chats list and start chat");
        System.out.print("2) Back\n>> ");
        while (true){
            int choice = scanner.nextInt();
            try{
                if(choice == 1){
                    ArrayList<User> friends = currentUser.getFriends();
                    int i = 1;
                    for(User x : friends){
                        System.out.println(i+". "+x.getUsername());
                        i++;
                    }
                    while (true){
                        try {
                            System.out.println("Choose your user or enter /back to exit:");
                            String input = scanner.nextLine();
                            if(input.compareTo("/back")==0){
                                break;
                            } else if (Integer.parseInt(input) >= 1 &&  Integer.parseInt(input)<= friends.size()) {
                                int index = Integer.parseInt(input);
                                String friendUsername = friends.get(index).getUsername();
                                String currentUsername = currentUser.getUsername();

                                // code ........



                            } else {
                                throw new Exception();
                            }
                        }catch (Exception e){
                            System.out.println("Invalid input!!!");
                        }
                    }

                    break;
                }else  if(choice == 2){
                    break;
                }else {
                    throw  new Exception();
                }
            }catch (Exception e){
                System.out.println("Invalid input!!!");
            }
        }
    }
    // done
    public void friendsList(){
        System.out.println("1) Show friends list");
        System.out.println("2) Add friends");
        System.out.print("3) Back \n>> ");
        while (true){
            int choice = scanner.nextInt();
            try{
                if(choice == 1){
                    ArrayList<User> friends = new ArrayList<>();
                    int i =1;
                    for(User x : friends){
                        System.out.println(i+ ". "+x.getUsername());
                    }
                    while (true){
                        System.out.println("please enter '/back' to exit :");
                        String input1 = scanner.nextLine();
                        if(input1.equals("/back"))
                            break;
                    }
                    break;
                }else  if(choice == 2){
                    while (true) {
                        try {
                            boolean  sendRequest = false;
                            System.out.println("please enter your friend's username :");
                            String friendUsername = scanner.nextLine();
                            ArrayList<User> allUsers = UsersDatabase.getInstance().getAllUsers();
                            for(User x : allUsers){
                                if(x.getUsername().equals(friendUsername)){
                                    ArrayList<User> friendsRequest = x.getFriendsRequest();
                                    friendsRequest.add(currentUser);
                                    x.setFriendsRequest(friendsRequest);
                                    System.out.println("Successfully send");
                                    sendRequest = true;
                                    break;
                                }
                            }
                            if(!sendRequest){
                                System.out.println("this username has not existed!!");
                            }
                            break;
                        }catch (Exception e){
                            // it is related to choice variable
                            System.out.println("Invalid value!!!");
                        }
                    }
                    break;
                }else  if(choice == 3){
                    break;
                }else {
                    throw  new Exception();
                }
            }catch (Exception e){
                System.out.println("Invalid input!!!");
            }
        }
    }
    // it is incomplete
    public void serversStuff(){
        System.out.println("1) Show server");
        System.out.println("2) Create server");
        System.out.println("3) Manage my server");
        System.out.print("4) Back\n>> ");
        while (true){
            int choice = scanner.nextInt();
            try{
                if(choice == 1){
                    // code
                    break;
                }else  if(choice == 2){
                    //code
                    break;
                }else  if(choice == 3){
                    //code
                    break;
                }else if(choice == 4){
                    break;
                } else {
                    throw  new Exception();
                }
            }catch (Exception e){
                System.out.println("Invalid input!!!");
            }
        }

    }
    // done
    public void friendsRequest(){
        System.out.println("1) Show requests");
        System.out.print("2) Back\n>> ");
        while (true){
            int choice = scanner.nextInt();
            try{
                if(choice == 1){
                    ArrayList<User> friendsRequest = currentUser.getFriendsRequest();
                    int i = 1;
                    for(User x : friendsRequest){
                        System.out.println(i + ". "+ x.getUsername());
                    }
                    System.out.println("Enter '/accept index' for accept request (for example /accept 1)");
                    System.out.println("Enter '/reject index' for reject request (for example /reject 2)");
                    System.out.println("Enter /back to exit");
                    while(true){
                        try {
                        String input = scanner.nextLine();
                        if(input.contains("accept")){
                            String[] inputs = input.split(" ");
                            int index = Integer.parseInt(inputs[1]);
                            if(index >= 1 && index <= friendsRequest.size()){
                                // adding to friends list
                                ArrayList<User> friends = currentUser.getFriends();
                                User newFriend = friendsRequest.get(index-1);
                                currentUser.setFriends(friends);
                                // remove from requests list
                                friendsRequest.remove(index-1);
                                currentUser.setFriendsRequest(friendsRequest);
                                // adding me to his friends list
                                ArrayList<User> hisFriends = newFriend.getFriends();
                                hisFriends.add(currentUser);
                                newFriend.setFriends(hisFriends);
                            }else{
                                throw new Exception();
                            }
                        }else if(input.contains("reject")){
                            String[] inputs = input.split(" ");
                            int index = Integer.parseInt(inputs[1]);
                            if(index >= 1 && index <= friendsRequest.size()){
                                // just remove from requests list
                                friendsRequest.remove(index-1);
                                currentUser.setFriendsRequest(friendsRequest);
                            }else{
                                throw new Exception();
                            }
                        }else if(input.compareTo("/back")==0){
                            break;
                        }else
                            throw new Exception();
                        }catch(Exception e){
                            // if user did not type accept , reject or back
                            // if user type invalid index
                            System.out.println("Invalid input!!!");
                        }
                    }
                    break;
                }else  if(choice == 2){
                    break;
                }else {
                    throw  new Exception();
                }
            }catch (Exception e){
                System.out.println("Invalid input!!!");
            }
        }
    }
    // it is incomplete
    public void globalSearch(){
        System.out.println("1) Search user"); // just one part is incomplete
        System.out.println("2) Search server");
        System.out.print("3) Back\n>> ");
        while (true){
            int choice = scanner.nextInt();
            try{
                if(choice == 1){
                    System.out.print("Please enter username :\n>> ");
                    String input = scanner.nextLine();
                    boolean isSuccessful = false;
                    ArrayList<User> allUsers = UsersDatabase.getInstance().getAllUsers();
                    for(User x : allUsers){
                        if(x.getUsername().compareTo(input)==0){
                            System.out.println("Successfully found user "+x.getUsername());
                            // if he is current user 's friend ,i will type in menu that do you want to start private chat else i type do you want to send friend request
                            ArrayList<User> currnetUserFriends = currentUser.getFriends();
                            if(currnetUserFriends.contains(x)) {
                                while (true) {
                                    try {
                                        System.out.println("Do you want to start a private chat ?\n1) Yes     2) No");
                                        int input2 = scanner.nextInt();
                                        if (input2 == 1) {
                                             // code....



                                            break;
                                        } else if (input2 == 2) {
                                             break;
                                        } else
                                            throw new Exception();
                                    }catch (Exception e){
                                        // for invalid input (if user enter number except 1 or 2)
                                        System.out.println("Invalid input!!!");
                                    }
                                }
                            }else{
                                while (true) {
                                    try {
                                        System.out.println("Do you want to send a friend request?\n1) Yes     2) No");
                                        int input2 = Integer.parseInt(scanner.nextLine());
                                        if(input2 == 1){
                                            // send request
                                           ArrayList<User> friendsRequest =  x.getFriendsRequest();
                                           friendsRequest.add(currentUser);
                                           x.setFriendsRequest(friendsRequest);
                                        }else if(input2 == 2){
                                            break;
                                        }else
                                            throw new Exception();
                                    }catch (Exception e){
                                        // for invalid input (if user enter number except 1 or 2)
                                        System.out.println("Invalid input!!!");
                                    }
                                }
                            }
                            isSuccessful = true;
                        }
                    }
                    if(!isSuccessful){
                        System.out.println("This username has not existed");
                    }
                    break;
                }else  if(choice == 2){
                    //code
                    break;
                }else  if(choice == 3){
                    break;
                }else {
                    throw  new Exception();
                }
            }catch (Exception e){
                System.out.println("Invalid input!!!");
            }
        }
    }
    // done
    public void blockedUsers(){
        System.out.println("1) Show blocked users");
        System.out.println("2) Remove from block list");
        System.out.println("3) Back");
        while (true){
            try {
                int choice3 = scanner.nextInt();
                if (choice3 == 1) {
                    int i = 1;
                    ArrayList<User> users = currentUser.getBlockedUsers();
                    for(User x : users){
                        System.out.println(i+" "+x.getUsername());
                        i++;
                    }
                    while (true) {
                        System.out.println("please enter '/back' to exit :");
                        String input1 = scanner.nextLine();
                        if(input1.equals("/back"))
                        break;
                    }
                    break;
                } else if (choice3 == 2) {
                    ArrayList<User> users = currentUser.getBlockedUsers();
                    int i = 1;
                    for(User x : users){
                        System.out.println(i+". "+x.getUsername());
                        i++;
                    }
                    while (true){
                        try {
                            System.out.println("Choose your user :");
                            int index = scanner.nextInt();
                            if (index >= 1 && index <= users.size()) {
                                users.remove(index - 1);
                                currentUser.setBlockedUsers(users);
                                break;
                            } else {
                                throw new Exception();
                            }
                        }catch (Exception e){
                            System.out.println("Invalid input!!!");
                        }
                    }
                    break;
                } else if (choice3 == 3) {
                    break;
                } else {
                    throw new Exception();
                }
            }catch (Exception e){
                System.out.println("Invalid input!!!");
            }
        }
    }
    // done
    public void setting(){
        while (true){
            try {
                System.out.println("1) Set Status");
                System.out.println("2) Change password");
                System.out.println("3) Change email");
                System.out.println("4) Change phone number");
                System.out.print("5) Back\n>> ");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    while (true) {
                        int choice1;
                        System.out.println("Your current status : "+ currentUser.getSituation());
                        System.out.println("Please choose your status");
                        System.out.println("1) Online");
                        System.out.println("2) Idle");
                        System.out.println("3) Invisible");
                        System.out.println("4) Do not Disturb");
                        System.out.println("5) Back");
                        try {
                            choice1 = scanner.nextInt();
                            if (choice1 == 1) {
                                currentUser.setSituation(UserStatus.ONLINE);
                                break;
                            } else if (choice1 == 2) {
                                currentUser.setSituation(UserStatus.IDLE);
                                break;
                            } else if (choice1 == 3) {
                                currentUser.setSituation(UserStatus.INVISIBLE);
                                break;
                            } else if (choice1 == 4) {
                                currentUser.setSituation(UserStatus.DONOTDISTURB);
                                break;
                            } else if (choice1 == 5) {
                                break;
                            } else if (choice1 < 1 || choice1 > 5) {
                                throw new Exception();
                            }
                        } catch (Exception e) {
                            System.out.println("Invalid input!!! try again");
                            continue;
                        }
                    }
                } else if (choice == 2) {
                    String password;
                    while (true) {
                        try {
                            System.out.println("please enter your password : ");
                            System.out.print("[Caution]>> password must be at least 8 characters and contain [0-9] , [a-z] & [A-Z].\n>> ");
                            password = scanner.nextLine();
                            if (!(Pattern.matches("[a-z[A-z[0-9]]]", password) || password.length() >= 8)) {
                                throw new Exception();
                            } else {
                                currentUser.setPassword(password);
                            }
                        } catch (Exception e) {
                            System.out.println("invalid input!!! try again");
                            continue;
                        }
                        break;
                    }
                } else if (choice == 3) {
                    String email;
                    while (true) {
                        try {
                            System.out.println("please enter your email :");
                            System.out.print("[caution]>> please enter complete format [abcdef@email.com]\n>> ");
                            email = scanner.nextLine();
                            if (!(email.contains("@gmail.com")) && !(email.contains("@email.com"))) {
                                throw new Exception();
                            } else {
                                currentUser.setEmail(email);
                            }
                        } catch (Exception e) {
                            System.out.println("invalid input!!! try again");
                            continue;
                        }
                        break;
                    }
                } else if (choice == 4) {
                    String phoneNumber;
                    while (true) {
                        try {
                            System.out.print("please enter your new phone number\n>> ");
                            phoneNumber = scanner.nextLine();
                            if (phoneNumber.compareTo("-") != 0 || phoneNumber.length() > 10) {
                                throw new Exception();
                            } else {
                                currentUser.setPhoneNumber(phoneNumber);
                            }
                        } catch (Exception e) {
                            System.out.println("invalid input!!! try again");
                            continue;
                        }
                        break;
                    }
                } else if (choice == 5) {
                        break;
                } else {
                    throw new Exception();
                }
            }catch (Exception e){
                System.out.println("Invalid input!!!");
            }
        }
    }
}
