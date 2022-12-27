package msg_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) throws IOException {

        Configurator configurator = new Configurator();

        while(configurator.getPort()==0)
        {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        String ip = configurator.getIP();
        int portNumber = configurator.getPort();

        InetAddress address = InetAddress.getLocalHost();

        try (

                Socket socket = new Socket(/*hostname*/ip, portNumber);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader stdin = new BufferedReader(new InputStreamReader((System.in)));
        ) {


            Send send = new Send(stdin,out);
            GUI chat = new GUI("Client",send);

            Receive receive = new Receive(in,chat);
            Thread receiver = new Thread(receive);

            receiver.start();
            while (true){}

        } catch (UnknownHostException e) {
            System.out.println("Unknown host");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("No I/O");
            System.exit(1);
        }
    }
}
