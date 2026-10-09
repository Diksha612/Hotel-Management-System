package com.hmsystem.Hotel_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

import net.proteanit.sql.DbUtils;

public class ReleaseDriver extends JFrame implements ActionListener {

    JTextField driverNameField;

    Choice statusChoice;

    JTable table;

    JButton search;
    JButton release;
    JButton back;

    int selectedPickupId = -1;
    String selectedDriver = "";

    ReleaseDriver() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Release Driver");
        heading.setBounds(400, 20, 250, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        add(heading);

        JLabel l1 = new JLabel("Driver Name");
        l1.setBounds(30, 80, 100, 25);
        add(l1);

        driverNameField = new JTextField();
        driverNameField.setBounds(140, 80, 180, 25);
        add(driverNameField);

        JLabel l2 = new JLabel("Status");
        l2.setBounds(350, 80, 80, 25);
        add(l2);

        statusChoice = new Choice();
        statusChoice.add("Occupied");
        statusChoice.add("Completed");
        statusChoice.setBounds(430, 80, 150, 25);
        add(statusChoice);

        search = new JButton("Search");
        search.setBounds(620, 80, 120, 25);
        search.addActionListener(this);
        add(search);

        table = new JTable();

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 130, 960, 330);
        add(sp);

        release = new JButton("Release Driver");
        release.setBounds(300, 500, 180, 35);
        release.addActionListener(this);
        add(release);

        back = new JButton("Back");
        back.setBounds(520, 500, 120, 35);
        back.addActionListener(this);
        add(back);

        table.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {

                int row = table.getSelectedRow();

                selectedPickupId =
                        Integer.parseInt(
                                table.getValueAt(row, 0).toString());

                selectedDriver =
                        table.getValueAt(row, 4).toString();
            }
        });

        loadAllRecords();

        setBounds(180, 100, 1020, 620);
        setVisible(true);
    }

    private void loadAllRecords() {

        try {

            Conn c = new Conn();

            ResultSet rs =
                    c.s.executeQuery(
                            "select * from pickup_service");

            table.setModel(
                    DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == search) {

            try {

                Conn c = new Conn();

                String query =
                        "select * from pickup_service where "
                        + "driver_name like '%"
                        + driverNameField.getText()
                        + "%' and status='"
                        + statusChoice.getSelectedItem()
                        + "'";

                ResultSet rs =
                        c.s.executeQuery(query);

                table.setModel(
                        DbUtils.resultSetToTableModel(rs));

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        else if (ae.getSource() == release) {

            try {

                if (selectedPickupId == -1) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Select a Pickup Record First");

                    return;
                }

                Conn c = new Conn();

                ResultSet rs =
                        c.s.executeQuery(
                                "select status from pickup_service "
                                + "where pickup_id="
                                + selectedPickupId);

                if (rs.next()) {

                    if (rs.getString("status")
                            .equalsIgnoreCase("Completed")) {

                        JOptionPane.showMessageDialog(
                                null,
                                "Driver Already Released");

                        return;
                    }
                }

                c.s.executeUpdate(
                        "update pickup_service "
                        + "set status='Completed' "
                        + "where pickup_id="
                        + selectedPickupId);

                c.s.executeUpdate(
                        "update driver "
                        + "set available='Available' "
                        + "where name='"
                        + selectedDriver + "'");

                JOptionPane.showMessageDialog(
                        null,
                        "Driver Released Successfully");

                loadAllRecords();

                selectedPickupId = -1;
                selectedDriver = "";

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        else if (ae.getSource() == back) {

            setVisible(false);
            new Pickup();
        }
    }

    public static void main(String[] args) {

        new ReleaseDriver();
    }
}