import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerTCP extends Thread {
    private ServerSocket serverSocket = null;
    private ServerTCP(){
        try{
            serverSocket = new ServerSocket(1500);
            System.out.println("Starting the server");
            start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private ArrayList<String> getListUsers(){
        BufferedReader reader;
        ArrayList<String> usersList = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader("src" + File.separator+ "users.txt"));
            String line = reader.readLine();
            while (line != null) {
                usersList.add(line);

                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usersList;
    }
    public void run(){
        try{
            while (true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connection accepted from " + clientSocket.getInetAddress().getHostAddress());
                if (getListUsers().contains(clientSocket.getInetAddress().getHostAddress())){
                    System.out.println("Sending message to user with IP " +
                                       clientSocket.getInetAddress().getHostAddress());
                    ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                    UsersListMessage message = new UsersListMessage("Hi! You are in the list");
                    out.writeObject(message);
                    out.close();
                }
                else {
                    System.out.println("User is not in file, IP: " + clientSocket.getInetAddress().getHostAddress());
                }
                clientSocket.close();

            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String args[]){
        new ServerTCP();
    }
}
