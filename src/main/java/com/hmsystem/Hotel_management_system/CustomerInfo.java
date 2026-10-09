package com.hmsystem.Hotel_management_system;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

import net.proteanit.sql.DbUtils;

public class CustomerInfo extends JFrame implements ActionListener {

    JTable table;
    JButton back;

    CustomerInfo() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Customer Information");
        heading.setBounds(400, 10, 300, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        add(heading);

        table = new JTable();

        try {

            Conn c = new Conn();

            ResultSet rs = c.s.executeQuery(
            	    "SELECT * FROM customer"
            	);

            	javax.swing.table.DefaultTableModel model =
            	        new javax.swing.table.DefaultTableModel();

            	model.addColumn("Document Type");
            	model.addColumn("ID Number");
            	model.addColumn("Name");
            	model.addColumn("Gender");
            	model.addColumn("Country");
            	model.addColumn("Room Number");
            	model.addColumn("Check In Time");
            	model.addColumn("Deposit");
            	model.addColumn("Status / Checkout Time");

            	while(rs.next()) {

            	    String status;

            	    if(rs.getTimestamp("checkout_time") == null) {

            	        status = "Active";
            	    }
            	    else {

            	        status =
            	                rs.getTimestamp("checkout_time").toString();
            	    }

            	    model.addRow(new Object[] {
            	            rs.getString("document_type"),
            	            rs.getString("id_number"),
            	            rs.getString("name"),
            	            rs.getString("gender"),
            	            rs.getString("country"),
            	            rs.getString("room_number"),
            	            rs.getString("checkin_time"),
            	            rs.getString("deposit"),
            	            status
            	    });
            	}

            	table.setModel(model);

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
        sp.setBounds(20, 60, 1040, 350);
        add(sp);

        back = new JButton("Back");
        back.setBounds(470, 440, 120, 35);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        setBounds(180, 150, 1100, 560);
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
        new CustomerInfo();
    }
}