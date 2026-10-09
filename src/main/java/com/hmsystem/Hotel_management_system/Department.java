package com.hmsystem.Hotel_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

import net.proteanit.sql.DbUtils;

public class Department extends JFrame implements ActionListener {

    JTable table;
    JButton back;

    Department() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Heading
        JLabel heading = new JLabel("Department Details");
        heading.setBounds(300, 10, 300, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 22));
        add(heading);

        // Column Labels
        JLabel l1 = new JLabel("Department");
        l1.setBounds(100, 50, 150, 20);
        l1.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(l1);

        JLabel l2 = new JLabel("Budget");
        l2.setBounds(450, 50, 100, 20);
        l2.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(l2);

        // Table
        table = new JTable();

        try {
            Conn c = new Conn();

            ResultSet rs = c.s.executeQuery(
                "SELECT department, budget FROM department"
            );

            table.setModel(DbUtils.resultSetToTableModel(rs));

            // Hide default table header
            table.setTableHeader(null);

        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(50, 80, 700, 300);
        add(sp);

        // Back Button
        back = new JButton("Back");
        back.setBounds(320, 420, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        setBounds(350, 150, 850, 550);
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
        new Department();
    }
}