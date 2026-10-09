package com.hmsystem.Hotel_management_system;
import java.awt.EventQueue;


import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class AddEmployee extends JFrame implements ActionListener {
	JTextField tfname,tfage,tfsalary,tfphone,tfemail,tfaadhar;
	JRadioButton rbmale,rbfemale;
	JButton submit;
    JComboBox cbjob;

    public AddEmployee(){
        getContentPane().setForeground(Color.BLUE);
        getContentPane().setBackground(Color.WHITE);
        setTitle("ADD EMPLOYEE DETAILS");
	 
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(778,486);
        getContentPane().setLayout(null);
		
        JLabel lblname = new JLabel("NAME");
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblname.setBounds(60, 30, 150, 27);
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(200, 30, 150, 27);
        add(tfname);
		
        JLabel lblage = new JLabel("AGE");
        lblage.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblage.setBounds(60, 80, 150, 27);
        add(lblage);
		
        tfage = new JTextField();
        tfage.setBounds(200, 80, 150, 27);
        add(tfage);
        
        JLabel lblgender = new JLabel("GENDER");
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblgender.setBounds(60, 120, 150, 27);
        add(lblgender);
	
         rbmale = new JRadioButton("MALE");
        rbmale.setBackground(Color.WHITE);
        rbmale.setBounds(200, 120, 70, 27);
        add(rbmale);

         rbfemale = new JRadioButton("FEMALE");
        rbfemale.setBackground(Color.WHITE);
        rbfemale.setBounds(280, 120, 80, 27);
        add(rbfemale);
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbmale);
        bg.add(rbfemale);
        
        JLabel lbljob = new JLabel("JOB");
        lbljob.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lbljob.setBounds(60, 180, 150, 27);
        add(lbljob);
		
        String str[] = {"Front Desk Clerks","Porters","Housekeeping","Kitchen Staff","Room Service","Waiter/Waitress","Manager","Accountant","Chef"};
        cbjob = new JComboBox(str);
        cbjob.setBackground(Color.WHITE);
        cbjob.setBounds(200,180,150,30);
        add(cbjob);
        		
        JLabel lblsalary = new JLabel("SALARY");
        lblsalary.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblsalary.setBounds(60, 230, 150, 27);
        add(lblsalary);
		
        tfsalary = new JTextField();
        tfsalary.setBounds(200, 230, 150, 27);
        add(tfsalary);

        JLabel lblphone = new JLabel("PHONE");
        lblphone.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblphone.setBounds(60, 270, 150, 27);
        add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(200, 270, 150, 27);
        add(tfphone);

        
        JLabel lblemail = new JLabel("EMAIL");
        lblemail.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblemail.setBounds(60, 320, 150, 27);
        add(lblemail);
		
        tfemail = new JTextField();
        tfemail.setBounds(200, 320, 150, 27);
        add(tfemail);
        
        JLabel lblaadhar = new JLabel("AADHAR");
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblaadhar.setBounds(60 ,360, 120, 30);
        add(lblaadhar);
		
        tfaadhar = new JTextField();
        tfaadhar.setBounds(200, 360, 150, 30);
        add(tfaadhar);

        submit = new JButton("SUBMIT");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setBounds(200, 430, 150, 30);
        submit.addActionListener(this);
        add(submit);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tenth.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(380,60,450,370);
        add(image);
        
        getContentPane().setBackground(Color.WHITE);
        setBounds(350,200,850,540);
        setVisible(true);
}
    public void actionPerformed(ActionEvent ae) {
    	String name = tfname.getText();
    	String age = tfage.getText(); 
    	String salary = tfsalary.getText(); 
    	String phone = tfphone.getText(); 
    	String email = tfemail.getText(); 
    	String aadhar = tfaadhar.getText(); 
    	
    	if (name.equals("")) {
    		JOptionPane.showMessageDialog(null, "Name Should not be empty");
    		return;
    	}
    	
    	String gender = null;
    	if(rbmale.isSelected()) {
    		gender = "Male";
    	}else if(rbfemale.isSelected()) {
    		gender ="Female";
    	}
    	
    	String job = (String) cbjob.getSelectedItem();
    	
    	try {
    		Conn conn = new Conn();
    		
    		String query = "INSERT INTO employee (name, age, gender, job, salary, phone, email, aadhar) " +
                    "VALUES ('" + name + "', '" + age + "', '" + gender + "', '" + job + "', '" +
                    salary + "', '" + phone + "', '" + email + "', '" + aadhar + "')";

    		conn.s.executeUpdate(query);
    		JOptionPane.showMessageDialog(null, "Employee Added Successfully");
    	    setVisible(false);
    	    new Dashboard();
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
public static void main(String[] args){
    new AddEmployee();
}   
}
