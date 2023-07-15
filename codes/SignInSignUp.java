import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class SignInSignUp implements Runnable{

    Scanner scanner = new Scanner(System.in);
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    Socket currentUserSocketForSignin = null;
    Socket getCurrentUserSocketForSignup = null;
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            int choice = initializeChoiceFirstMenu();
            switch (choice){
                case 1:
                    signin(currentUserSocketForSignin);
                    break;
                case 2:
                    signup(getCurrentUserSocketForSignup);
                    break;
                case 3:
                    Thread.currentThread().interrupt();
                    break;
            }
        }
    }
    private int initializeChoiceFirstMenu(){
        int choice = 0;
        System.out.println("1) Login");
        System.out.println("2) Sign up");
        System.out.println("3) Exit");
        do {
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            }catch (InputMismatchException e){
                System.out.println("Please enter either 1 or 2 or 3");
            }
        }while (!(choice == 1 || choice == 2 || choice == 3));
        return choice;
    }



    private void signup(Socket socket){
        if (socket == null){
            try {
                getCurrentUserSocketForSignup = new Socket("127.0.0.1", 1969);
                System.out.println("Sign up socket to server successfully set");
                objectOutputStream = new ObjectOutputStream(getCurrentUserSocketForSignup.getOutputStream());
                System.out.println("oos set!");
                objectInputStream = new ObjectInputStream(getCurrentUserSocketForSignup.getInputStream());
                System.out.println("ois set");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        String receivedStringFromServer = "";
        System.out.println("[CAUTION] >> Username must be a combination of at least 6 characters long. Alphabet letters and numbers and _ and - and . is accepted.");
        System.out.println("Type /back to get back!");
        String enteredUsername = scanner.nextLine();
        boolean isRegexOk = checkRegex(enteredUsername);
        while (!enteredUsername.equals("/back")){

        }

        

        if (!enteredUsername.equals("/back")){
            if (isRegexOk){
                try{
                    objectOutputStream.writeObject(enteredUsername);
                } catch (Exception e){
                    System.out.println("Unsuccessful to send the entered username to server");
                }

            }else {
                while (!isRegexOk){
                    System.out.println("Invalid username");
                    enteredUsername = scanner.nextLine();
                    if (enteredUsername.equals("/back")){
                        return;
                    }else {
                        isRegexOk = checkRegex(enteredUsername);
                    }
                }
            }
        }else {
            return;
        }



    }

    private boolean checkRegex(String username){
        return username.matches("\\b[a-zA-Z][a-zA-Z0-9\\-._]{3,}\\b");
    }

    private void signin(Socket socket){
        if (socket == null){
            try {
                currentUserSocketForSignin = new Socket("127.0.0.1", 6000);
                System.out.println("Sign in socket to server successfully set");
                objectOutputStream = new ObjectOutputStream(currentUserSocketForSignin.getOutputStream());
                System.out.println("oos set!");
                objectInputStream = new ObjectInputStream(currentUserSocketForSignin.getInputStream());
                System.out.println("ois set");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String sentCredentialPacket;
        String receivedStringFromServer = "";
        while (!receivedStringFromServer.equals("Successfully logged in.")){
            System.out.println("Type /back to get back!");
            System.out.println("Please enter your username: ");
            String enteredUsername = scanner.nextLine();
            if (!enteredUsername.equals("/back")){
                System.out.println("Please enter your password: ");
                String enteredPassword = scanner.nextLine();
                if (!enteredPassword.equals("/back")){
                    sentCredentialPacket = enteredUsername + "+" + enteredPassword;
                    try {
                        objectOutputStream.writeObject(sentCredentialPacket);
                        objectOutputStream.flush();
                        receivedStringFromServer = (String) objectInputStream.readObject();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("Credential successfully sent to server");
                    System.out.println(receivedStringFromServer);
                }else {
                    return;
                }
            }else {
                return;
            }
        }
    }
}
