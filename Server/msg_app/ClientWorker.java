package msg_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


import java.net.Socket;

class ClientWorker implements Runnable {
    private Socket client;
    private int clientNo;
    BufferedReader stdin = new BufferedReader(new InputStreamReader((System.in)));

    //Constructor
    ClientWorker(Socket client,int clientno ) {
        this.client = client;
        this.clientNo=clientno;
        System.out.println("client worker "+clientNo+"  is ready now");

    }

    public void run(){
        String line;
        BufferedReader in = null;
        PrintWriter out = null;
        try{
            in = new BufferedReader(new
                    InputStreamReader(client.getInputStream()));
            out = new
                    PrintWriter(client.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("in or out failed");
            System.exit(-1);
        }

        Send send = new Send(stdin,out);
        GUI chat = new GUI("Server",send);
        Thread sender = new Thread(send);

        Receive receive = new Receive(in,chat);
        Thread receiver = new Thread(receive);

        //sender.start();
        receiver.start();
        while (true){}

        /*while(true){
            try{
                line = in.readLine();
                System.out.println("Client-> "+line);
                Thread.sleep(1000);
            }catch (IOException | java.lang.InterruptedException e) {
                System.out.println("Read failed");
                System.exit(-1);
            }
        }

         */
    }
}