package com.hmsystem.Hotel_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class RoomStatus extends JFrame implements ActionListener {

    JTextField roomSearchField;
    JTextField idSearchField;

    JTextField roomNumberField;
    JTextField availabilityField;

    JComboBox<String> cleaningStatusBox;

    JButton search;
    JButton update;
    JButton back;

    String currentRoomNo = "";

    RoomStatus() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Room Status");
        heading.setBounds(180, 20, 250, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        add(heading);

        JLabel l1 = new JLabel("Room Number");
        l1.setBounds(30, 80, 120, 25);
        add(l1);

        roomSearchField = new JTextField();
        roomSearchField.setBounds(170, 80, 180, 25);
        add(roomSearchField);

        JLabel l2 = new JLabel("Customer ID");
        l2.setBounds(30, 120, 120, 25);
        add(l2);

        idSearchField = new JTextField();
        idSearchField.setBounds(170, 120, 180, 25);
        add(idSearchField);

        JLabel l3 = new JLabel("Room Number");
        l3.setBounds(30, 200, 120, 25);
        add(l3);

        roomNumberField = new JTextField();
        roomNumberField.setBounds(170, 200, 180, 25);
        roomNumberField.setEditable(false);
        add(roomNumberField);

        JLabel l4 = new JLabel("Availability");
        l4.setBounds(30, 240, 120, 25);
        add(l4);

        availabilityField = new JTextField();
        availabilityField.setBounds(170, 240, 180, 25);
        availabilityField.setEditable(false);
        add(availabilityField);

        JLabel l5 = new JLabel("Cleaning Status");
        l5.setBounds(30, 280, 120, 25);
        add(l5);

        cleaningStatusBox = new JComboBox<>(
                new String[]{"Cleaned", "Dirty"});
        cleaningStatusBox.setBounds(170, 280, 180, 25);
        add(cleaningStatusBox);

        search = new JButton("Search");
        search.setBounds(30, 360, 100, 30);
        search.addActionListener(this);
        add(search);

        update = new JButton("Update");
        update.setBounds(150, 360, 100, 30);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(270, 360, 100, 30);
        back.addActionListener(this);
        add(back);

        setBounds(450, 180, 450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void loadRoom() {

        try {

            Conn c = new Conn();

            String roomNo = "";

            if (!roomSearchField.getText().trim().isEmpty()) {

                roomNo = roomSearchField.getText().trim();

            } else if (!idSearchField.getText().trim().isEmpty()) {

                ResultSet rs = c.s.executeQuery(
                        "SELECT room_number FROM customer WHERE id_number='"
                                + idSearchField.getText().trim() + "'");

                if (rs.next()) {

                    roomNo = rs.getString("room_number");

                } else {

                    JOptionPane.showMessageDialog(null,
                            "Customer not found.");
                    return;
                }

            } else {

                JOptionPane.showMessageDialog(null,
                        "Enter Room Number or Customer ID");
                return;
            }

            ResultSet rs2 = c.s.executeQuery(
                    "SELECT * FROM room WHERE roomnumber='"
                            + roomNo + "'");

            if (rs2.next()) {

                currentRoomNo = roomNo;

                roomNumberField.setText(
                        rs2.getString("roomnumber"));

                availabilityField.setText(
                        rs2.getString("availability"));

                cleaningStatusBox.setSelectedItem(
                        rs2.getString("cleaning_status"));

            } else {

                JOptionPane.showMessageDialog(null,
                        "Room not found.");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == search) {

            loadRoom();
        }

        else if (ae.getSource() == update) {

            try {

                if (currentRoomNo.equals("")) {

                    JOptionPane.showMessageDialog(null,
                            "Search room first.");
                    return;
                }

                Conn c = new Conn();

                c.s.executeUpdate(
                        "UPDATE room SET cleaning_status='"
                                + cleaningStatusBox.getSelectedItem()
                                + "' WHERE roomnumber='"
                                + currentRoomNo + "'");

                JOptionPane.showMessageDialog(null,
                        "Room Status Updated Successfully");

                loadRoom();

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        else if (ae.getSource() == back) {

            setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args) {

        new RoomStatus();
    }
}