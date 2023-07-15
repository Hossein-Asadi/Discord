import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectionHandlerForSignIn implements Runnable {
    private String username;
    private User currentUser = null;
    private Socket currentSocket;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    public ConnectionHandlerForSignIn(Socket currentSocket){
        this.currentSocket = currentSocket;
        try {
            objectInputStream = new ObjectInputStream(currentSocket.getInputStream());
            objectOutputStream = new ObjectOutputStream(currentSocket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Connection handler for sign in successfully instantiated");
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            System.out.println("Server socket successfully accepted a connection for sign in");
            try {
                String receivedCredentialPacket = (String) objectInputStream.readObject();
                currentUser = UsersDatabase.getInstance().authenticate(receivedCredentialPacket);
                while (currentUser == null){
                    objectOutputStream.writeObject("Invalid login credentials");
                    receivedCredentialPacket = (String) objectInputStream.readObject();
                    currentUser = UsersDatabase.getInstance().authenticate(receivedCredentialPacket);
                }
                objectOutputStream.writeObject("Successfully logged in.");

            } catch (Exception e) {
                Thread.currentThread().interrupt();
            }



        }


    }
}
