package com.hmsystem.Hotel_management_system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

import net.proteanit.sql.DbUtils;

public class AssignDriver extends JFrame implements ActionListener {

    Choice companyChoice;
    Choice modelChoice;
    Choice locationChoice;

    JTextField txtCustomerId;
    JTextField txtCustomerName;
    JTextField txtGender;
    JTextField txtRoomNumber;
    JTextField txtPickupLocation;

    JTable table;

    JButton searchDriver;
    JButton loadCustomer;
    JButton assignDriver;
    JButton back;

    String selectedDriver = "";
    String selectedCarModel = "";

    AssignDriver() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Assign Driver");
        heading.setBounds(420, 10, 250, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        add(heading);

        JLabel l1 = new JLabel("Company");
        l1.setBounds(20, 60, 100, 25);
        add(l1);

        companyChoice = new Choice();

        companyChoice.setBounds(120, 60, 150, 25);

        companyChoice.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {

                loadCarModels();
            }
        });

        add(companyChoice);

        JLabel l2 = new JLabel("Car Model");
        l2.setBounds(300, 60, 100, 25);
        add(l2);

        modelChoice = new Choice();
        modelChoice.setBounds(400, 60, 150, 25);
        add(modelChoice);

        JLabel l3 = new JLabel("Location");
        l3.setBounds(580, 60, 100, 25);
        add(l3);

        locationChoice = new Choice();
        locationChoice.setBounds(680, 60, 150, 25);
        add(locationChoice);

        searchDriver = new JButton("Search Drivers");
        searchDriver.setBounds(850, 60, 130, 25);
        searchDriver.addActionListener(this);
        add(searchDriver);

        try {

            Conn c = new Conn();

            ResultSet rs1 =
                    c.s.executeQuery("select distinct company from driver");

            while(rs1.next()) {

                companyChoice.add(rs1.getString("company"));
            }

            loadCarModels();

            ResultSet rs3 =
                    c.s.executeQuery("select distinct location from driver");

            while(rs3.next()) {

                locationChoice.add(rs3.getString("location"));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        table = new JTable();

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 110, 960, 250);
        add(sp);

        JLabel c1 = new JLabel("Customer ID");
        c1.setBounds(20, 390, 120, 25);
        add(c1);

        txtCustomerId = new JTextField();
        txtCustomerId.setBounds(140, 390, 180, 25);
        add(txtCustomerId);

        loadCustomer = new JButton("Load Customer");
        loadCustomer.setBounds(340, 390, 150, 25);
        loadCustomer.addActionListener(this);
        add(loadCustomer);

        JLabel c2 = new JLabel("Customer Name");
        c2.setBounds(20, 440, 120, 25);
        add(c2);

        txtCustomerName = new JTextField();
        txtCustomerName.setBounds(140, 440, 180, 25);
        txtCustomerName.setEditable(false);
        add(txtCustomerName);

        JLabel c3 = new JLabel("Gender");
        c3.setBounds(350, 440, 100, 25);
        add(c3);

        txtGender = new JTextField();
        txtGender.setBounds(430, 440, 120, 25);
        txtGender.setEditable(false);
        add(txtGender);

        JLabel c4 = new JLabel("Room Number");
        c4.setBounds(580, 440, 100, 25);
        add(c4);

        txtRoomNumber = new JTextField();
        txtRoomNumber.setBounds(680, 440, 120, 25);
        txtRoomNumber.setEditable(false);
        add(txtRoomNumber);

        JLabel c5 = new JLabel("Pickup Location");
        c5.setBounds(20, 490, 120, 25);
        add(c5);

        txtPickupLocation = new JTextField();
        txtPickupLocation.setBounds(140, 490, 250, 25);
        add(txtPickupLocation);

        assignDriver = new JButton("Assign Driver");
        assignDriver.setBounds(450, 490, 150, 30);
        assignDriver.addActionListener(this);
        add(assignDriver);

        back = new JButton("Back");
        back.setBounds(650, 490, 120, 30);
        back.addActionListener(this);
        add(back);

        table.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {

                int row = table.getSelectedRow();

                selectedDriver =
                        table.getValueAt(row, 0).toString();

                selectedCarModel =
                        table.getValueAt(row, 4).toString();
            }
        });

        setBounds(200,100,1020,620);
        setVisible(true);
    }
    
    private void loadCarModels() {

        try {

            modelChoice.removeAll();

            Conn c = new Conn();

            ResultSet rs =
                    c.s.executeQuery(
                            "select distinct car_model from driver where company='"
                                    + companyChoice.getSelectedItem() + "'");

            while(rs.next()) {

                modelChoice.add(
                        rs.getString("car_model"));
            }

        } catch(Exception e) {

            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {

        if(ae.getSource() == searchDriver) {

            try {

                Conn c = new Conn();

                String query =
                        "select * from driver where company='"
                        + companyChoice.getSelectedItem()
                        + "' and car_model='"
                        + modelChoice.getSelectedItem()
                        + "' and location='"
                        + locationChoice.getSelectedItem()
                        + "' and available='Available'";

                ResultSet rs = c.s.executeQuery(query);

                table.setModel(DbUtils.resultSetToTableModel(rs));

            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        else if(ae.getSource() == loadCustomer) {

            try {

                Conn c = new Conn();

                ResultSet rs =
                        c.s.executeQuery(
                                "select * from customer where id_number='"
                                + txtCustomerId.getText() + "'");

                if(rs.next()) {

                    txtCustomerName.setText(
                            rs.getString("name"));

                    txtGender.setText(
                            rs.getString("gender"));

                    txtRoomNumber.setText(
                            rs.getString("room_number"));

                } else {

                    JOptionPane.showMessageDialog(
                            null,
                            "Customer Not Found");
                }

            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        else if(ae.getSource() == assignDriver) {

            try {

                if(selectedDriver.equals("")) {

                    JOptionPane.showMessageDialog(
                            null,
                            "Select Driver From Table");

                    return;
                }

                Conn c = new Conn();

                c.s.executeUpdate(
                        "insert into pickup_service("
                        + "customer_name,"
                        + "id_number,"
                        + "room_number,"
                        + "driver_name,"
                        + "car_model,"
                        + "pickup_location,"
                        + "status)"
                        + " values('"
                        + txtCustomerName.getText() + "','"
                        + txtCustomerId.getText() + "','"
                        + txtRoomNumber.getText() + "','"
                        + selectedDriver + "','"
                        + selectedCarModel + "','"
                        + txtPickupLocation.getText() + "','"
                        + "Occupied')");

                c.s.executeUpdate(
                        "update driver set available='Unavailable' "
                        + "where name='"
                        + selectedDriver + "'");

                JOptionPane.showMessageDialog(
                        null,
                        "Driver Assigned Successfully");

            } catch(Exception e) {

                e.printStackTrace();
            }
        }

        else if(ae.getSource() == back) {

            setVisible(false);
            new Pickup();
        }
    }

    public static void main(String[] args) {

        new AssignDriver();
    }
}