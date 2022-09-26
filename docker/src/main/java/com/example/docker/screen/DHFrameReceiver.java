package com.example.docker.screen;

import com.example.docker.helper.XMLConvert;
import com.example.docker.model.Person;
import org.apache.log4j.BasicConfigurator;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.*;
import java.util.Properties;

public class DHFrameReceiver extends JFrame {
    private JLabel ms;
    private Destination destination;
    private Connection con;
    public DHFrameReceiver() throws NamingException, JMSException {
        BasicConfigurator.configure();

        Properties settings = new Properties();
        settings.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        settings.setProperty(Context.PROVIDER_URL, "tcp://localhost:61616");

        Context ctx = new InitialContext(settings);
        ConnectionFactory factory = (ConnectionFactory) ctx.lookup("ConnectionFactory");
        destination = (Destination) ctx.lookup("dynamicQueues/thanthidet");

        con = factory.createConnection("admin", "admin");
        con.start();
        initComponents();
    }

    private void initComponents() throws JMSException {
        Session session = con.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        MessageConsumer receiver = session.createConsumer(destination);

        receiver.setMessageListener((msg) -> {
            try {
                if (msg instanceof TextMessage) {
                    TextMessage tm = (TextMessage) msg;
                    String txt = tm.getText();
                    Person person = new Person();
                    XMLConvert<Person> xml = new XMLConvert<>(person);
                    person = xml.xml2Object(txt);
                    this.ms.setText(person.toString());
                    msg.acknowledge();
                } else if (msg instanceof ObjectMessage) {
                    ObjectMessage om = (ObjectMessage) msg;
                    System.out.println(om);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        ms = new JLabel();

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(ms, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(ms, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(400, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }
}
