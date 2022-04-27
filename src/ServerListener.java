import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class ServerListener extends Thread{
    private int serverPort;
    private List<MultiUserChatServer> username = new ArrayList<>();
    public ServerListener(int serverPort){
        this.serverPort = serverPort;
    }

    public static void main(String[] args) throws IOException {
        ServerListener serverListener = new ServerListener(12345);
        serverListener.start();
    }
    public void run(){
        while (true){
            try (ServerSocket serverSocket = new ServerSocket(12345)){
                final Socket socketClient = serverSocket.accept();
                MultiUserChatServer clientHandler = new MultiUserChatServer(socketClient, username);
                username.add(clientHandler);
                clientHandler.start();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
