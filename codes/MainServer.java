import java.io.*;
import java.net.ServerSocket;

public class MainServer implements Runnable{
    ServerSocket serverSocketForSignIn;
    ServerSocket getServerSocketForSignUp;
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            System.out.println("Main Server successfully is run");
            UsersDatabase usersDatabase;
            if (new File( System.getProperty("user.dir") +"/db/UsersDataBase.bin").isFile()){
                try {
                    FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") +"/db/UsersDataBase.bin");
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                    usersDatabase = (UsersDatabase) objectInputStream.readObject();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }else{
                usersDatabase = UsersDatabase.getInstance();

            }
            System.out.println("Database handled successfully");
            try {
                serverSocketForSignIn = new ServerSocket(6000);
                getServerSocketForSignUp = new ServerSocket(1969);
                System.out.println("Main Server Socket for sign in set successfully, Server listening");
                System.out.println("Main Server Socket for sign up set successfully, Server listening");
                new ConnectionHandlerForSignIn(serverSocketForSignIn.accept()).run();
                new ConnectionHandlerForSignUp(getServerSocketForSignUp.accept()).run();
            } catch (IOException e) {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(System.getProperty("user.dir") +"/db/UsersDataBase.bin");
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                    try {
                        objectOutputStream.writeObject(usersDatabase);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                Thread.currentThread().interrupt();
            }
        }
        //System.out.println("Working Directory = " + System.getProperty("user.dir"));
    }
}
