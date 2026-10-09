package com.hmsystem.Hotel_management_system;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.PreparedStatement;

import javax.swing.*;

public class AddDriver extends JFrame implements ActionListener{
	JTextField tfname,tfcompany,tfage,tfcarmodel, tflocation;
	JComboBox  gendercombo,availablecombo;
	JButton add, cancel;
	
	AddDriver(){
		getContentPane().setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel heading =  new JLabel("Add Driver");
		heading.setFont(new Font("Tahoma", Font.BOLD, 18));
		heading.setBounds(150, 10, 200, 20);
		add(heading);
		
		JLabel lblname =  new JLabel("Name");
		lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblname.setBounds(60, 70, 120, 20);
		add(lblname);
		
		tfname = new JTextField();
		tfname.setBounds(200, 70, 150, 30);
		add(tfname);
		
		JLabel lblage =  new JLabel("Age");
		lblage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblage.setBounds(60, 110, 120, 20);
		add(lblage);
		
		tfage = new JTextField();
		tfage.setBounds(200, 110, 150, 30);
		add(tfage);
		
		JLabel lblgender =  new JLabel("Gender");
		lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblgender.setBounds(60, 150, 120, 20);
		add(lblgender);
		
		String genderOptions[] = {"Male", "Female"};
		gendercombo = new JComboBox(genderOptions);
		gendercombo.setBounds(200, 150, 150, 30);
		gendercombo.setBackground(Color.WHITE);
		add(gendercombo);
		
		JLabel lblcompany =  new JLabel("Car Company");
		lblcompany.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblcompany.setBounds(60, 190, 120, 20);
		add(lblcompany);
		
		tfcompany = new JTextField();
		tfcompany.setBounds(200, 190, 150, 30);
		add(tfcompany);
		
		JLabel lblcarmodel =  new JLabel("Car Model");
		lblcarmodel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblcarmodel.setBounds(60, 230, 120, 20);
		add(lblcarmodel);
		
		tfcarmodel = new JTextField();
		tfcarmodel.setBounds(200, 230, 150, 30);
		add(tfcarmodel);
		
		JLabel lblavailable =  new JLabel("Availability");
		lblavailable.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblavailable.setBounds(60, 270, 120, 20);
		add(lblavailable);
		
		String availableOptions[] = {"Available", "Not Available"};
		availablecombo = new JComboBox(availableOptions);
		availablecombo.setBounds(200, 270, 150, 30);
		availablecombo.setBackground(Color.WHITE);
		add(availablecombo);
		
		JLabel lbllocation =  new JLabel("Driver Location");
		lbllocation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbllocation.setBounds(60, 310, 120, 20);
		add(lbllocation);
		
		tflocation = new JTextField();
		tflocation.setBounds(200, 310, 120, 30);
		add(tflocation);
		
		add = new JButton("Add Driver");
		add.setForeground(Color.WHITE);
		add.setBackground(Color.BLACK);
		add.setBounds(60, 370, 130, 30);
		add.addActionListener(this);
		add(add);
		
		cancel = new JButton("Cancel Driver");
		cancel.setForeground(Color.WHITE);
		cancel.setBackground(Color.BLACK);
		cancel.setBounds(220, 370, 130, 30);
		cancel.addActionListener(this);
		add(cancel);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eleven.jpg"));
		Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		image.setBounds(400, 30, 500, 300);
		add(image);
		
		setBounds(300, 200, 980, 470);
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent ae) {

	    if(ae.getSource() == add) {

	        String name = tfname.getText();
	        String age = tfage.getText();
	        String gender = (String) gendercombo.getSelectedItem();
	        String company = tfcompany.getText();
	        String carModel = tfcarmodel.getText();
	        String available = (String) availablecombo.getSelectedItem();
	        String location = tflocation.getText();

	        try {

	            Conn conn = new Conn();

	            String query = "INSERT INTO driver (name, age, gender, company, car_model, available, location) VALUES (?, ?, ?, ?, ?, ?, ?)";

	            PreparedStatement pstmt = conn.c.prepareStatement(query);

	            pstmt.setString(1, name);
	            pstmt.setString(2, age);
	            pstmt.setString(3, gender);
	            pstmt.setString(4, company);
	            pstmt.setString(5, carModel);
	            pstmt.setString(6, available);
	            pstmt.setString(7, location);

	            pstmt.executeUpdate();

	            JOptionPane.showMessageDialog(null, "Driver Added Successfully");

	            setVisible(false);
	            new Dashboard();

	        } catch(Exception e) {
	            e.printStackTrace();
	        }

	    } else if(ae.getSource() == cancel) {

	        setVisible(false);
	        new Dashboard();
	    }
	}
	
	public static void main(String[] args) {
		new AddDriver();
	}
}
