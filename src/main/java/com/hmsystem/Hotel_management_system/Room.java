package com.hmsystem.Hotel_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

import net.proteanit.sql.DbUtils;

public class Room extends JFrame implements ActionListener {

    JTable table;
    JButton back;

    Room() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Heading
        JLabel heading = new JLabel("Room Details");
        heading.setBounds(220, 10, 200, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 22));
        add(heading);

        // Custom Column Labels
        JLabel l1 = new JLabel("Room No");
        l1.setBounds(20, 50, 100, 20);
        l1.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(l1);

        JLabel l2 = new JLabel("Availability");
        l2.setBounds(120, 50, 100, 20);
        l2.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(l2);

        JLabel l3 = new JLabel("Cleaning Status");
        l3.setBounds(230, 50, 120, 20);
        l3.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(l3);

        JLabel l4 = new JLabel("Price");
        l4.setBounds(390, 50, 80, 20);
        l4.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(l4);

        JLabel l5 = new JLabel("Bed Type");
        l5.setBounds(470, 50, 100, 20);
        l5.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(l5);

        // Table
        table = new JTable();

        try {
            Conn c = new Conn();

            ResultSet rs = c.s.executeQuery(
                "SELECT roomnumber, availability, cleaning_status, price, bed_type FROM room"
            );

            table.setModel(DbUtils.resultSetToTableModel(rs));

            // Hide MySQL column headers
            table.setTableHeader(null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(10, 80, 550, 350);
        add(sp);

        // Room Image
        ImageIcon i1 = new ImageIcon(
                ClassLoader.getSystemResource("icons/eight.jpg"));

        Image i2 = i1.getImage().getScaledInstance(
                450, 450, Image.SCALE_DEFAULT);

        ImageIcon i3 = new ImageIcon(i2);

        JLabel image = new JLabel(i3);
        image.setBounds(580, 50, 450, 450);
        add(image);

        // Back Button
        back = new JButton("Back");
        back.setBounds(220, 460, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        setBounds(250, 150, 1080, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == back) {
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args) {
        new Room();
    }
}