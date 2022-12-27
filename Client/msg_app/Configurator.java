package msg_app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Configurator implements KeyListener {
    private JFrame frame;
    private JPanel panel;
    private JTextField IP;
    private JTextField Port;
    private JLabel IPLabel;
    private JLabel PortLabel;

    private String ip;
    private int port = 0;

    Configurator()
    {
        IPLabel = new JLabel("IP");
        IPLabel.setBounds(7,10,20,30);
        IPLabel.setForeground(Color.white);

        IP = new JTextField();
        IP.setBounds(60,10,150,30);
        IP.addKeyListener(this);

        PortLabel = new JLabel("Port");
        PortLabel.setForeground(Color.WHITE);
        PortLabel.setBounds(7,50,60,30);

        Port = new JTextField("");
        Port.setBounds(60,50,150,30);
        Port.addKeyListener(this);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0,0,230,130);
        panel.setBackground(Color.BLACK);
        panel.add(IP);
        panel.add(Port);
        panel.add(IPLabel);
        panel.add(PortLabel);

        frame = new JFrame();
        frame.setSize(230,130);
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public String getIP()
    {
        return this.ip;
    }

    public int getPort()
    {
        return this.port;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER)
        {
            if(Port.getText().equals("")) {
                this.Port.requestFocus();
                return;
            }
            this.ip = IP.getText();
            this.port = Integer.parseInt(Port.getText());
            frame.dispose();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
