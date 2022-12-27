package msg_app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI<color> implements ActionListener, KeyListener {

    private JFrame frame;
    private JTextField textField;
    private JPanel inputPanel;
    private JPanel messagePanel;
    private JButton button;
    private Send send;
    private JLabel[] labels = new JLabel[10];


    private int FRAME_X = 450;
    private int FRAME_Y = 600;
    private int INPUT_PANEL_X = 450;
    private int INPUT_PANEL_Y = 100;
    private int MESSAGE_PANEL_X = 450;
    private int MESSAGE_PANEL_Y = 500;
    private int LABEL_X = 20;
    private int LABEL_Y = 30;

    private Dimension messageDimension = new Dimension(375,40);

    GUI( String name,Send send)
    {
        this.send = send;

        inputPanel = new JPanel();
        inputPanel.setBackground(Color.BLACK);
        inputPanel.setBounds(0,500,INPUT_PANEL_X,INPUT_PANEL_Y);
        inputPanel.setLayout(null);

        messagePanel = new JPanel();
        messagePanel.setBackground(Color.BLACK);
        messagePanel.setBounds(0,0,MESSAGE_PANEL_X,MESSAGE_PANEL_Y);
        messagePanel.setLayout(new GridLayout(10,1,0,-100));

        button = new JButton();
        button.setBounds(350,10,60,35);
        button.setFont(new Font("Consolas",Font.PLAIN,10));
        button.setText("Send");
        button.addActionListener(this);
        button.setBackground(Color.white);



        for(int i=0; i<10; i++){
            labels[i]=new JLabel();
            labels[i].setForeground(Color.WHITE);
            labels[i].setMaximumSize(messageDimension);
            labels[i].setText("");
            messagePanel.add(labels[i]);
        }


        textField = new JTextField();
        textField.setBounds(35,10,300,35);
        textField.setSize(300,35);
        textField.setBackground(Color.WHITE);
        textField.setCaretColor(Color.BLACK);
        textField.setForeground(Color.BLACK);
        textField.setFont(new Font("Consolas",Font.PLAIN,17));
        textField.addKeyListener(this);

        inputPanel.add(textField);
        inputPanel.add(button);

        frame = new JFrame(name);
        frame.setSize(FRAME_X,FRAME_Y);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(inputPanel);
        frame.add(messagePanel);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == button)
        {
            String message = textField.getText();
            if(!message.equals("")) {
                this.input(message,Color.WHITE);
                send.sendMessage(message);
                textField.setText("");
            }
        }

    }

    private Color color;
    public void input(String input,Color color) {

        this.color = color;

        int BEGINNING = 0;
        int ENDING = 60;
        boolean secondTime = false;

        if (!input.equals("")) {

            if(input.length()<60) {
                for (int i = 0; i < 10; i++) {
                    if (i < 9) {
                        labels[i].setForeground(labels[i+1].getForeground());
                        labels[i].setText(labels[i + 1].getText());
                    }
                    else{
                        labels[i].setForeground(color);
                        labels[i].setText("    "+input);
                    }
                }
            return;
            }

            int n = input.length() / 50 + 1 ;
            for (int y = 0; y < n; y++) {
                for (int m = 0; m < 10; m++) {
                    if (m < 9){
                        labels[m].setForeground(labels[m+1].getForeground());
                        labels[m].setText(labels[m + 1].getText());
                    }
                    else {
                        if(secondTime){
                            labels[m].setForeground(color);
                            labels[m].setText("         "+input.substring(BEGINNING, ENDING));
                            BEGINNING +=50;

                        }
                        else {
                            labels[m].setForeground(color);
                            labels[m].setText("    "+input.substring(BEGINNING, ENDING));
                            secondTime = true;
                            BEGINNING +=60;
                        }
                        ENDING+=50;
                        if(ENDING > input.length())
                            ENDING = input.length();

                    }
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER)
        {
            String message = textField.getText();
            if(!message.equals("")) {
                this.input(message,Color.WHITE);
                send.sendMessage(message);
                textField.setText("");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
