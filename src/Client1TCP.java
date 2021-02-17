import java.io.ObjectInputStream;
import java.net.Socket;

public class Client1TCP {
    public static void main(String args[]){
        try {
            Socket clientSocket = new Socket("localhost", 1500);
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            UsersListMessage nameMessage = (UsersListMessage) in.readObject();
            System.out.println(nameMessage.getMessage());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
