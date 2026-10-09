package com.hmsystem.Hotel_management_system;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Dashboard extends JFrame implements ActionListener{
    
    public Dashboard() {
        super("HOTEL MANAGEMENT SYSTEM");
	
        setForeground(Color.CYAN);
        setLayout(null); 

        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1950, 1000,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2); 
	JLabel NewLabel = new JLabel(i3);
	NewLabel.setBounds(0, 0, 1950, 1000); 
        add(NewLabel);
        
        JLabel label = new JLabel("NOVAREL WELCOMES YOU");
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Tahoma", Font.PLAIN, 46));
        label.setBounds(600, 60, 1000, 85);
	NewLabel.add(label);
		
		
        JMenuBar menuBar = new JMenuBar();
	setJMenuBar(menuBar);
		
        JMenu label1 = new JMenu("HOTEL MANAGEMENT");
        label1.setForeground(Color.BLUE);
	menuBar.add(label1);
		
        JMenuItem reception = new JMenuItem("RECEPTION");
        reception.addActionListener(this);
        label1.add(reception);
		
	JMenu admin = new JMenu("ADMIN");
	admin.setForeground(Color.RED);
	menuBar.add(admin);
        
        JMenuItem addEmployee = new JMenuItem("ADD EMPLOYEE");
        addEmployee.addActionListener(this);
        admin.add(addEmployee);
        

        JMenuItem addRoom = new JMenuItem("ADD ROOMS");
        addRoom.addActionListener(this);
        admin.add(addRoom);
        
        JMenuItem addDrivers = new JMenuItem("ADD DRIVERS");
        addDrivers.addActionListener(this);
        admin.add(addDrivers);
        
	  setSize(1950,1090);
		setVisible(true);
}
    public void actionPerformed(ActionEvent ae) {
    	if(ae.getActionCommand().equals("ADD EMPLOYEE")) {
    		new AddEmployee();
    	}else if(ae.getActionCommand().equals("ADD ROOMS")) {
    		new AddRooms();
    	}else if(ae.getActionCommand().equals("ADD DRIVERS")) {
    		new AddDriver();
    	}else if(ae.getActionCommand().equals("RECEPTION")) {
    		new Reception();
    	}
    }
    public static void main (String[] args) {
    	new Dashboard();
	}
}