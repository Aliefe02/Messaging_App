package msg_app;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Send implements Runnable{
    BufferedReader stdin;
    PrintWriter out;

    Send(BufferedReader stdin,PrintWriter out)
    {
        this.stdin = stdin;
        this.out = out;
    }

    public void run() {
        try {
            String userInput;
            System.out.println("type a sentence and press enter");
            //System.out.printf("-> ");
            while ((userInput = stdin.readLine()) != ".") {
                out.println(userInput);
                //System.out.printf("-> ");
            }
        }
        catch (IOException e)
        {}
    }
    public void sendMessage(String input)
    {
        out.println(input);
    }
}