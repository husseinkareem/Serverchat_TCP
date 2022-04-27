import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MultiUserChatServer extends Thread{
    private final Socket clientSocket;
    public PrintWriter out;
    private List<MultiUserChatServer> username = new ArrayList<>();

    public MultiUserChatServer(Socket clientSocket, List username) throws IOException {
        this.clientSocket = clientSocket;
        this.username = username;
        out = new PrintWriter(clientSocket.getOutputStream(), true);

    }
    public void run() {

        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            //out.println("The client is online");
            while ((inputLine = in.readLine()) != null) {
                System.out.println("read from client " + inputLine);
                for (MultiUserChatServer user: username) {
                    System.out.println("sending to all clients" + inputLine);
                    user.out.println(inputLine);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
