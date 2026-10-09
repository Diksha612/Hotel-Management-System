package com.hmsystem.Hotel_management_system;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

import net.proteanit.sql.DbUtils;

public class EmployeeInfo extends JFrame implements ActionListener {

    JTable table;
    JButton back;

    EmployeeInfo() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Employee Information");
        heading.setBounds(400, 10, 300, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        add(heading);

        table = new JTable();

        try {
            Conn c = new Conn();

            ResultSet rs = c.s.executeQuery(
                    "SELECT name, age, gender, job, salary, phone, email, aadhar FROM employee");

            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }

        table.setRowHeight(28);
        table.setFont(new Font("Tahoma", Font.PLAIN, 13));
        table.setGridColor(Color.BLACK);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Tahoma", Font.BOLD, 14));
        header.setBackground(Color.LIGHT_GRAY);
        header.setPreferredSize(new Dimension(header.getWidth(), 30));

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 60, 1020, 350);
        add(sp);

        back = new JButton("Back");
        back.setBounds(460, 440, 120, 35);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        setBounds(200, 150, 1080, 560);
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
        new EmployeeInfo();
    }
}