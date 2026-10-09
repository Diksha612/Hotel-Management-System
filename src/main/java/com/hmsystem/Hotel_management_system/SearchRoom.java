package com.hmsystem.Hotel_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

import net.proteanit.sql.DbUtils;

public class SearchRoom extends JFrame implements ActionListener {

    JTable table;
    JButton search, back;

    JComboBox<String> availabilityBox;
    JComboBox<String> cleaningBox;
    JComboBox<String> bedTypeBox;

    JTextField roomNumberField;
    JTextField minPriceField;
    JTextField maxPriceField;

    SearchRoom() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Search Room");
        heading.setBounds(400, 10, 250, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 22));
        add(heading);

        JLabel l1 = new JLabel("Availability");
        l1.setBounds(20, 60, 100, 20);
        add(l1);

        availabilityBox = new JComboBox<>(new String[]{"All", "Available", "Occupied"});
        availabilityBox.setBounds(120, 60, 120, 25);
        add(availabilityBox);

        JLabel l2 = new JLabel("Cleaning");
        l2.setBounds(260, 60, 100, 20);
        add(l2);

        cleaningBox = new JComboBox<>(new String[]{"All", "Cleaned", "Dirty"});
        cleaningBox.setBounds(340, 60, 120, 25);
        add(cleaningBox);

        JLabel l3 = new JLabel("Bed Type");
        l3.setBounds(480, 60, 100, 20);
        add(l3);

        bedTypeBox = new JComboBox<>(new String[]{"All", "Single Bed", "Double Bed"});
        bedTypeBox.setBounds(560, 60, 120, 25);
        add(bedTypeBox);

        JLabel l4 = new JLabel("Room No");
        l4.setBounds(700, 60, 80, 20);
        add(l4);

        roomNumberField = new JTextField();
        roomNumberField.setBounds(780, 60, 100, 25);
        add(roomNumberField);

        JLabel l5 = new JLabel("Min Price");
        l5.setBounds(20, 100, 80, 20);
        add(l5);

        minPriceField = new JTextField();
        minPriceField.setBounds(100, 100, 100, 25);
        add(minPriceField);

        JLabel l6 = new JLabel("Max Price");
        l6.setBounds(220, 100, 80, 20);
        add(l6);

        maxPriceField = new JTextField();
        maxPriceField.setBounds(300, 100, 100, 25);
        add(maxPriceField);

        search = new JButton("Search");
        search.setBounds(450, 100, 100, 30);
        search.addActionListener(this);
        add(search);

        back = new JButton("Back");
        back.setBounds(570, 100, 100, 30);
        back.addActionListener(this);
        add(back);

        table = new JTable();

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 150, 1000, 300);
        add(sp);

        loadRooms();

        setBounds(200, 150, 1060, 550);
        setVisible(true);
    }

    private void loadRooms() {

        try {

            Conn c = new Conn();

            ResultSet rs = c.s.executeQuery(
                    "SELECT roomnumber, availability, cleaning_status, price, bed_type FROM room");

            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == search) {

            try {

                String query =
                        "SELECT roomnumber, availability, cleaning_status, price, bed_type FROM room WHERE 1=1";

                if (!availabilityBox.getSelectedItem().equals("All")) {
                    query += " AND availability='" +
                            availabilityBox.getSelectedItem() + "'";
                }

                if (!cleaningBox.getSelectedItem().equals("All")) {
                    query += " AND cleaning_status='" +
                            cleaningBox.getSelectedItem() + "'";
                }

                if (!bedTypeBox.getSelectedItem().equals("All")) {
                    query += " AND bed_type='" +
                            bedTypeBox.getSelectedItem() + "'";
                }

                if (!roomNumberField.getText().trim().isEmpty()) {
                    query += " AND roomnumber='" +
                            roomNumberField.getText().trim() + "'";
                }

                if (!minPriceField.getText().trim().isEmpty()) {
                    query += " AND price >= " +
                            minPriceField.getText().trim();
                }

                if (!maxPriceField.getText().trim().isEmpty()) {
                    query += " AND price <= " +
                            maxPriceField.getText().trim();
                }

                Conn c = new Conn();

                ResultSet rs = c.s.executeQuery(query);

                table.setModel(DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (ae.getSource() == back) {
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args) {
        new SearchRoom();
    }
}