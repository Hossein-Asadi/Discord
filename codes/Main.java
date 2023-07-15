import java.util.Objects;

public class Main {
    // source code log in ...
    public static void main(String[] args) {
        DiscordServer discord = new DiscordServer();
        System.out.println("Running discord...");
        discord.run();
    }

}
//
//    public static void register() {
//        Scanner scanner = new Scanner(System.in);
//        String username;
//        System.out.println("Hello :))\n welcome to Discord\n");
//        while (true) {
//            try {
//                System.out.println("1- Sign in\n2- Sign up");
//                int input = Integer.parseInt(scanner.nextLine());
//                if (input != 1 && input != 2) {
//                    throw new Exception();
//                } else if (input == 1) {
//                    username = signinRegister();
//                    break;
//                } else {
//                    username = signupRegister();
//                    break;
//                }
//            } catch (Exception e) {
//                System.out.println("invalid value!!! try again");
//            }
//        }
//
//    }
//    public static void MainMenu(){
//        System.out.println("1- Servers");
//        System.out.println("2- Private chats"); // incomplete
//        System.out.println("3- Setting");
//        System.out.println("4- Friends Request "); // incomplete
//        System.out.println("5- Log out");
//        System.out.println(">> ");
//    }
//    public static String signupRegister() {
//        Scanner scanner = new Scanner(System.in);
//        String username;
//        String password;
//        String email;
//        String phoneNumber;
//        while (true) {
//            try {
//                System.out.println("please enter your user name : ");
//                System.out.print("[Caution] >> User name must be unique and contain at least 6 letters and only English letters and " +
//                        "numbers. \n>> ");
//                username = scanner.nextLine();
//                // check username : if it is invalid throw exception
//            }catch (Exception e) {
//                System.out.println("invalid input!!! try again");
//                continue;
//            }
//            try {
//                System.out.println("please enter your password :");
//                System.out.print("[Caution]>> password must be at least 8 characters and contain [0-9] , [a-z] & [A-Z].\n>> ");
//                password = scanner.nextLine();
//                // check if it invalid throw exception
//            }catch (Exception e) {
//                System.out.println("invalid input!!! try again");
//                continue;
//            }
//            try {
//                System.out.println("please enter your email :");
//                System.out.print("[caution]>> please enter complete format [abcdef@email.com]\n>> ");
//                email = scanner.nextLine();
//                // check if it is invalid throw exception
//            }catch (Exception e) {
//                System.out.println("invalid input!!! try again");
//                continue;
//            }
//            try {
//                System.out.print("please enter your phone number : (it is optional)\n>> ");
//                phoneNumber = scanner.nextLine();
//                // create and add user
//                //  User user = new User(username,password,email,phoneNumber);
//            } catch (Exception e) {
//                System.out.println("invalid input!!! try again");
//                continue;
//            }
//            return username;
//        }
//    }
//
//    public static String signinRegister() {
//        Scanner scanner = new Scanner(System.in);
//        String username;
//        String password;
//        while (true) {
//            try {
//                System.out.println("please enter your user name : ");
//                username = scanner.nextLine();
//                System.out.println("please enter your password :");
//                password = scanner.nextLine();
//                // check username & password : if it is invalid throw exception
//            } catch (Exception e) {
//                System.out.println("invalid input!!! try again");
//                continue;
//            }
//            return username;
//        }
//    }
//}
