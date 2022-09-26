package com.example.docker.screen;

import com.example.docker.helper.XMLConvert;
import com.example.docker.model.Person;
import net.miginfocom.swing.MigLayout;
import org.apache.log4j.BasicConfigurator;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;

public class DHFrame extends JFrame implements ActionListener {

    private JPanel background;
    private JLabel personID;
    private JTextField idText;
    private JLabel name;
    private JTextField nameText;
    private JLabel birthDay;
    private JTextField birthDayText;
    private JLabel ms;
    private JTextField msText;
    private JButton buttonSend;

    private Destination destination;
    private Connection con;
    public DHFrame() throws NamingException, JMSException {
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

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        background = new JPanel();
        personID = new JLabel();
        idText = new JTextField();
        name = new JLabel();
        nameText = new JTextField();
        birthDay = new JLabel();
        birthDayText = new JTextField();
        ms = new JLabel();
        msText = new JTextField();
        buttonSend = new JButton();

        background.setLayout(new MigLayout("wrap", "push[center]push", "push[center]0[center]10[center]0[center]10[center]0[center]10[center]0[center]30[center]push"));

        personID.setText("Mã số sinh viên:");
        background.add(personID, "w 80%");
        background.add(idText, "w 80%, h 40!");

        name.setText("Họ tên:");
        background.add(name, "w 80%");
        background.add(nameText, "w 80%, h 40!");

        birthDay.setText("Ngày sinh:");
        background.add(birthDay, "w 80%");
        background.add(birthDayText, "w 80%, h 40!");

        ms.setText("Tin nhắn");
        background.add(ms, "w 80%");
        background.add(msText, "w 80%, h 40!");

        buttonSend.setText("Submit");
        buttonSend.addActionListener(this);
        background.add(buttonSend, "w 80%, h 40!");

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(background, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(background, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(400, 400);
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    con.close();
                    System.out.println("Finish...");
                } catch (JMSException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(buttonSend)) {
            Session session = null;
            String ms = this.msText.getText();
            int personId = Integer.parseInt(this.idText.getText());
            String name = this.nameText.getText();
            Date birthDay = new Date(this.birthDayText.getText());
            System.out.println(birthDay);
            try {
                session = con.createSession(/* transaction */false, /* ACK */Session.AUTO_ACKNOWLEDGE);
                MessageProducer producer = session.createProducer(destination);
                Person p = new Person(personId, name, birthDay);
                //Message msg = session.createTextMessage(p);
                //producer.send(msg);

                String xml = new XMLConvert<Person>(p).object2XML(p);
                Message msg = session.createTextMessage(xml);
                producer.send(msg);
                session.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);

            }
        }
    }
}
