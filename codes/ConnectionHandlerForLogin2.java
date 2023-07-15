import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionHandlerForLogin2 {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        boolean amIOn = true;
        {
            try {
                serverSocket = new ServerSocket(6000);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        while (amIOn){
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                new ConnectionHandlerForSignIn(socket).run();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}
