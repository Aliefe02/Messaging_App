package msg_app;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerT{

    ServerSocket server;
    int clientNo=0;

    public void startServerT(){
        try{
            server = new ServerSocket(4321);
            System.out.println("server is ready");
        } catch (IOException e) {
            System.out.println("Could not listen on port 4321");
            System.exit(-1);
        }
        while(true){
            ClientWorker w;
            try{
                //server.accept returns a client connection
                Socket client;
                client =server.accept();
                // w = new ClientWorker(server.accept(),clientNo);
                w = new ClientWorker(client,clientNo);
                Thread t = new Thread(w);
                t.start();
                clientNo++;
            } catch (IOException e) {
                System.out.println("Accept failed: 4444");
                System.exit(-1);
            }
        }
    }
}