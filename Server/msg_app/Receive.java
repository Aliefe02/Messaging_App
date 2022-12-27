package msg_app;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;

public class Receive implements Runnable{

    BufferedReader in;
    GUI chat;
    Receive(BufferedReader in,GUI chat){
        this.in = in;
        this.chat = chat;
    }
    public void run()
    {
        try
        {

            while(true) {
                String input = in.readLine();
                if(!input.equals("null")){
                System.out.println("Input: "+input);
                chat.input(input, Color.GREEN);
                }
            }
        }catch (IOException e){}
    }
}
