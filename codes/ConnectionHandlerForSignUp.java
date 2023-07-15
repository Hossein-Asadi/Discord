import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionHandlerForSignUp implements Runnable {
    private Socket currentSocket;
    private User currentUser = null;
    private String username;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    public ConnectionHandlerForSignUp(Socket currentSocket){
        this.currentSocket = currentSocket;
        try {
            objectInputStream = new ObjectInputStream(currentSocket.getInputStream());
            objectOutputStream = new ObjectOutputStream(currentSocket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Connection handler for sign up successfully instantiated");
    }


    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            System.out.println("Server socket successfully accepted a connection for sign up");
            try {
                String receivedUserNameToBeChecked = (String) objectInputStream.readObject();
                boolean isReceivedUsernameOk = false;
                while (!isReceivedUsernameOk){
                    objectOutputStream.writeObject("The username is already taken.");
                    receivedUserNameToBeChecked = (String) objectInputStream.readObject();
                    isReceivedUsernameOk = UsersDatabase.getInstance().checkForUsername(receivedUserNameToBeChecked);
                }
                objectOutputStream.writeObject("The username is not taken.");

            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
