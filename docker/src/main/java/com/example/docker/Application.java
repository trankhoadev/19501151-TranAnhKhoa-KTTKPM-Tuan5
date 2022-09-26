package com.example.docker;

import com.example.docker.screen.DHFrame;
import javax.jms.JMSException;
import javax.naming.NamingException;
import java.awt.*;

public class Application {
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            DHFrame frame = null;
            try {
                frame = new DHFrame();
            } catch (NamingException | JMSException e) {
                throw new RuntimeException(e);
            }
            frame.setVisible(true);
        });
    }
}
