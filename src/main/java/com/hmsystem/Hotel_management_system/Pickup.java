package com.hmsystem.Hotel_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pickup extends JFrame implements ActionListener {

    JButton assignDriver, releaseDriver, back;

    Pickup() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Pickup Service");
        heading.setBounds(170, 40, 250, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        add(heading);

        assignDriver = new JButton("Assign Driver");
        assignDriver.setBounds(120, 120, 180, 35);
        assignDriver.addActionListener(this);
        add(assignDriver);

        releaseDriver = new JButton("Release Driver");
        releaseDriver.setBounds(120, 180, 180, 35);
        releaseDriver.addActionListener(this);
        add(releaseDriver);

        back = new JButton("Back");
        back.setBounds(120, 240, 180, 35);
        back.addActionListener(this);
        add(back);

        setBounds(500, 200, 450, 400);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if(ae.getSource() == assignDriver) {
            setVisible(false);
            new AssignDriver();
        }

        else if(ae.getSource() == releaseDriver) {
            setVisible(false);
            new ReleaseDriver();
        }

        else if(ae.getSource() == back) {
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args) {
        new Pickup();
    }
}