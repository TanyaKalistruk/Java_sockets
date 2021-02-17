import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientTCP {
    public static void main(String args[]){
        try {
            Socket clientSocket = new Socket("0.0.0.0", 1500);
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
            UsersListMessage nameMessage = (UsersListMessage) in.readObject();
            System.out.println(nameMessage.getMessage());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
