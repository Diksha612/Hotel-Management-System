package com.hmsystem.Hotel_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class UpdateCheck extends JFrame implements ActionListener {

    JTextField roomSearchField;
    JTextField idSearchField;

    JTextField nameField;
    JTextField roomField;
    JTextField depositField;
    JTextField priceField;
    JTextField pendingField;
    JTextField amountPaidField;

    JButton check;
    JButton update;
    JButton back;

    String currentRoomNo = "";

    UpdateCheck() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Update Status");
        heading.setBounds(170, 20, 250, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        add(heading);

        JLabel l1 = new JLabel("Room Number");
        l1.setBounds(30, 80, 120, 25);
        add(l1);

        roomSearchField = new JTextField();
        roomSearchField.setBounds(170, 80, 180, 25);
        add(roomSearchField);

        JLabel l2 = new JLabel("ID Number");
        l2.setBounds(30, 120, 120, 25);
        add(l2);

        idSearchField = new JTextField();
        idSearchField.setBounds(170, 120, 180, 25);
        add(idSearchField);

        JLabel l3 = new JLabel("Customer Name");
        l3.setBounds(30, 180, 120, 25);
        add(l3);

        nameField = new JTextField();
        nameField.setBounds(170, 180, 180, 25);
        nameField.setEditable(false);
        add(nameField);

        JLabel l4 = new JLabel("Room Number");
        l4.setBounds(30, 220, 120, 25);
        add(l4);

        roomField = new JTextField();
        roomField.setBounds(170, 220, 180, 25);
        roomField.setEditable(false);
        add(roomField);

        JLabel l5 = new JLabel("Deposit Paid");
        l5.setBounds(30, 260, 120, 25);
        add(l5);

        depositField = new JTextField();
        depositField.setBounds(170, 260, 180, 25);
        depositField.setEditable(false);
        add(depositField);

        JLabel l6 = new JLabel("Room Price");
        l6.setBounds(30, 300, 120, 25);
        add(l6);

        priceField = new JTextField();
        priceField.setBounds(170, 300, 180, 25);
        priceField.setEditable(false);
        add(priceField);

        JLabel l7 = new JLabel("Pending Amount");
        l7.setBounds(30, 340, 120, 25);
        add(l7);

        pendingField = new JTextField();
        pendingField.setBounds(170, 340, 180, 25);
        pendingField.setEditable(false);
        add(pendingField);

        JLabel l8 = new JLabel("Amount Paid");
        l8.setBounds(30, 380, 120, 25);
        add(l8);

        amountPaidField = new JTextField();
        amountPaidField.setBounds(170, 380, 180, 25);
        add(amountPaidField);

        check = new JButton("Check");
        check.setBounds(20, 450, 100, 30);
        check.addActionListener(this);
        add(check);

        update = new JButton("Update Deposit");
        update.setBounds(140, 450, 140, 30);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(300, 450, 100, 30);
        back.addActionListener(this);
        add(back);

        setBounds(450, 150, 470, 560);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void loadCustomer() {

        try {

            Conn c = new Conn();

            String query = "";

            if (!roomSearchField.getText().trim().isEmpty()) {

                query = "SELECT * FROM customer WHERE room_number='"
                        + roomSearchField.getText().trim() + "'";

            } else if (!idSearchField.getText().trim().isEmpty()) {

                query = "SELECT * FROM customer WHERE id_number='"
                        + idSearchField.getText().trim() + "'";

            } else {

                JOptionPane.showMessageDialog(null,
                        "Enter Room Number or ID Number");
                return;
            }

            ResultSet rs = c.s.executeQuery(query);

            if (rs.next()) {

                currentRoomNo = rs.getString("room_number");

                String customerName = rs.getString("name");
                int deposit = Integer.parseInt(rs.getString("deposit"));

                nameField.setText(customerName);
                roomField.setText(currentRoomNo);
                depositField.setText(String.valueOf(deposit));

                ResultSet rs2 = c.s.executeQuery(
                        "SELECT price FROM room WHERE roomnumber='"
                                + currentRoomNo + "'");

                if (rs2.next()) {

                    int price = Integer.parseInt(rs2.getString("price"));

                    priceField.setText(String.valueOf(price));

                    int pending = price - deposit;

                    if (pending < 0) {
                        pending = 0;
                    }

                    pendingField.setText(String.valueOf(pending));
                }

            } else {

                JOptionPane.showMessageDialog(null,
                        "Customer not found.");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == check) {

            loadCustomer();
        }

        else if (ae.getSource() == update) {

            try {

                if (currentRoomNo.equals("")) {

                    JOptionPane.showMessageDialog(null,
                            "Search customer first.");
                    return;
                }

                int currentDeposit =
                        Integer.parseInt(depositField.getText());

                int amountPaid =
                        Integer.parseInt(amountPaidField.getText());

                int newDeposit =
                        currentDeposit + amountPaid;

                Conn c = new Conn();

                c.s.executeUpdate(
                        "UPDATE customer SET deposit='"
                                + newDeposit
                                + "' WHERE room_number='"
                                + currentRoomNo + "'");

                JOptionPane.showMessageDialog(null,
                        "Deposit Updated Successfully");

                loadCustomer();

                amountPaidField.setText("");

            } catch (Exception e) {

                JOptionPane.showMessageDialog(null,
                        "Enter valid amount.");
            }
        }

        else if (ae.getSource() == back) {

            setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args) {

        new UpdateCheck();
    }
}