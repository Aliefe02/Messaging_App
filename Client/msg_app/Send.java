package msg_app;

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
            while ((userInput = stdin.readLine()) != System.lineSeparator()) {
                out.println(userInput);
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
