package com.hmsystem.Hotel_management_system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener
{
	
	Main(){
		//setSize(1400,570);
		//setLocation(100, 100);
		setBounds(100, 100, 1366, 565);
		setLayout(null);
		
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/first.jpg"));
		Image i2 = i1.getImage().getScaledInstance(1366, 565, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image  = new JLabel(i3);
		image.setBounds(0,0, 1366, 565 );
		add(image);
		
		JLabel text = new JLabel("Hotel Management System");
		text.setBounds(20,430,1000,90);
		text.setForeground(Color.WHITE);
		text.setFont(new Font("serif", Font.PLAIN, 50));
		image.add(text);
		
		JButton next = new JButton("Next");
		next.setBounds(1150,450,150,50);
		next.setBackground(Color.WHITE);
		next.setForeground(Color.BLUE);
		next.setFont(new Font("serif", Font.PLAIN, 30));
		next.addActionListener(this);
		image.add(next);
		setVisible(true);
		
		while(true) {
			text.setVisible(false);
			try {
				Thread.sleep(500);
			}catch(Exception e){
				e.printStackTrace();			
			}
			text.setVisible(true);
			try {
				Thread.sleep(500);
			}catch(Exception e){
				e.printStackTrace();			
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {

	    setVisible(false);

	    new Dashboard();
	}
	
    public static void main( String[] args ) {
    	new Main();
    }
}
