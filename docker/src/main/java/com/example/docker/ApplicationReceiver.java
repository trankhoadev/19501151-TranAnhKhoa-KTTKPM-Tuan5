package com.example.docker;

import com.example.docker.screen.DHFrameReceiver;

import javax.jms.JMSException;
import javax.naming.NamingException;
import java.awt.*;

public class ApplicationReceiver {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            DHFrameReceiver frame = null;
            try {
                frame = new DHFrameReceiver();
            } catch (NamingException | JMSException e) {
                throw new RuntimeException(e);
            }
            frame.setVisible(true);
        });
    }
}
