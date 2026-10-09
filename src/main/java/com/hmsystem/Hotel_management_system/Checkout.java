package com.hmsystem.Hotel_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.Date;

public class Checkout extends JFrame implements ActionListener {

    JTextField tfIdNumber;
    JTextField tfName;
    JTextField tfRoom;
    JTextField tfDeposit;
    JTextField tfRoomPrice;
    JTextField tfRemaining;

    JLabel lblCheckoutTime;

    JButton search;
    JButton payPending;
    JButton checkout;
    JButton back;

    Checkout() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Customer Checkout");
        heading.setBounds(250,20,300,30);
        heading.setFont(new Font("Tahoma",Font.BOLD,24));
        add(heading);

        JLabel l1 = new JLabel("ID Number");
        l1.setBounds(50,80,120,25);
        add(l1);

        tfIdNumber = new JTextField();
        tfIdNumber.setBounds(200,80,180,25);
        add(tfIdNumber);

        search = new JButton("Search");
        search.setBounds(420,80,120,25);
        search.addActionListener(this);
        add(search);

        JLabel l2 = new JLabel("Customer Name");
        l2.setBounds(50,130,120,25);
        add(l2);

        tfName = new JTextField();
        tfName.setBounds(200,130,180,25);
        tfName.setEditable(false);
        add(tfName);

        JLabel l3 = new JLabel("Room Number");
        l3.setBounds(50,180,120,25);
        add(l3);

        tfRoom = new JTextField();
        tfRoom.setBounds(200,180,180,25);
        tfRoom.setEditable(false);
        add(tfRoom);

        JLabel l4 = new JLabel("Amount Paid");
        l4.setBounds(50,230,120,25);
        add(l4);

        tfDeposit = new JTextField();
        tfDeposit.setBounds(200,230,180,25);
        tfDeposit.setEditable(false);
        add(tfDeposit);

        JLabel l5 = new JLabel("Room Charge");
        l5.setBounds(50,280,120,25);
        add(l5);

        tfRoomPrice = new JTextField();
        tfRoomPrice.setBounds(200,280,180,25);
        tfRoomPrice.setEditable(false);
        add(tfRoomPrice);

        JLabel l6 = new JLabel("Remaining");
        l6.setBounds(50,330,120,25);
        add(l6);

        tfRemaining = new JTextField();
        tfRemaining.setBounds(200,330,180,25);
        tfRemaining.setEditable(false);
        add(tfRemaining);

        JLabel l7 = new JLabel("Checkout Time");
        l7.setBounds(50,380,120,25);
        add(l7);

        lblCheckoutTime = new JLabel("");
        lblCheckoutTime.setBounds(200,380,300,25);
        add(lblCheckoutTime);

        payPending = new JButton("Pay Remaining");
        payPending.setBounds(50,450,150,30);
        payPending.addActionListener(this);
        payPending.setEnabled(false);
        add(payPending);

        checkout = new JButton("Checkout");
        checkout.setBounds(240,450,120,30);
        checkout.addActionListener(this);
        add(checkout);

        back = new JButton("Back");
        back.setBounds(400,450,120,30);
        back.addActionListener(this);
        add(back);

        setBounds(350,150,700,600);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if(ae.getSource() == search) {

            try {

                Conn conn = new Conn();

                ResultSet rs =
                        conn.s.executeQuery(
                                "select * from customer where id_number='"
                                        + tfIdNumber.getText() + "'");

                if(rs.next()) {
                	if(rs.getTimestamp("checkout_time") != null) {

                	    JOptionPane.showMessageDialog(
                	            null,
                	            "Customer Already Checked Out");

                	    checkout.setEnabled(false);
                	    payPending.setEnabled(false);

                	    return;
                	}
                	else {

                	    checkout.setEnabled(true);
                	}

                    tfName.setText(rs.getString("name"));
                    tfRoom.setText(rs.getString("room_number"));
                    tfDeposit.setText(rs.getString("deposit"));

                    String roomNo = rs.getString("room_number");

                    ResultSet rsRoom =
                            conn.s.executeQuery(
                                    "select price from room where roomnumber='"
                                            + roomNo + "'");

                    if(rsRoom.next()) {

                        tfRoomPrice.setText(
                                rsRoom.getString("price"));
                    }

                    int paid =
                            Integer.parseInt(
                                    tfDeposit.getText());

                    int total =
                            Integer.parseInt(
                                    tfRoomPrice.getText());

                    int remaining = total - paid;

                    if(remaining < 0) {
                        remaining = 0;
                    }

                    tfRemaining.setText(
                            String.valueOf(remaining));

                    if(remaining > 0) {

                        payPending.setEnabled(true);

                    } else {

                        payPending.setEnabled(false);
                    }

                } else {

                    JOptionPane.showMessageDialog(
                            null,
                            "Customer Not Found");
                }

            } catch(Exception e) {

                e.printStackTrace();
            }
        }

        else if(ae.getSource() == payPending) {

            new UpdateCheck();
        }

        else if(ae.getSource() == checkout) {

            try {

                int remaining =
                        Integer.parseInt(
                                tfRemaining.getText());

                if(remaining > 0) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Pending Payment Exists");

                    return;
                }

                Conn conn = new Conn();

                conn.s.executeUpdate(
                        "update customer set checkout_time = NOW() "
                                + "where id_number='"
                                + tfIdNumber.getText()
                                + "'");

                conn.s.executeUpdate(
                        "update room set availability='Available' where roomnumber='"
                                + tfRoom.getText()
                                + "'");

                JOptionPane.showMessageDialog(
                        null,
                        "Checkout Successful");

                setVisible(false);
                new Reception();

            } catch(Exception e) {

                e.printStackTrace();
            }
        }

        else if(ae.getSource() == back) {

            setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args) {

        new Checkout();
    }
}