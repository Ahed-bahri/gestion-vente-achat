package tn.poly.bahri.gestion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

public class LoginScreen extends JFrame {

	private JFrame frame;
	private JPasswordField mdp;
	private JTextField id;
	Connection cn= ConnectDB.getConnexion();
	PreparedStatement ps;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen window = new LoginScreen();
					window.frame.setVisible(true);
				    window.frame.setLocationRelativeTo(null);  // pr placer au milieu
				    
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginScreen(){
		initialize();
	}

	private void initialize() {
		frame = new JFrame("Let Me IN");
		frame.getContentPane().setBackground(new Color(51,0, 102));
		frame.setBounds(100, 100, 645, 512);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		id = new JTextField();
		id.setColumns(10);
		
		mdp = new JPasswordField();
		
		JButton btnConnexion = new JButton("> Enter >");
		btnConnexion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				try {
					//A SQL statement is precompiled and stored in a PreparedStatement object.
					ps=cn.prepareStatement("select login, password from connect where login = ? AND password = ?");
					
					ps.setString(1, id.getText());	
					ps.setString(2, String.valueOf(mdp.getPassword()));
					
					ResultSet rs= ps.executeQuery();
					if (rs.next()) {
						//Preloader preload= new Preloader();
						MainScreen mainScreen=new MainScreen();
						mainScreen.setVisible(true);
						mainScreen.setLocationRelativeTo(null);
						frame.dispose();
					}
					else {
						
						JOptionPane.showMessageDialog(frame,"Oops, Seems like you mistyped ☉_☉ ." ,"Empty Field", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				catch(Exception exception) {
					
					JOptionPane.showMessageDialog(frame,"Oops, You are facing database Error." ,"DataBase Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnConnexion.setForeground(new Color(255, 255, 255));
		btnConnexion.setBackground(new Color(0, 0, 128));
		btnConnexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					//A SQL statement is precompiled and stored in a PreparedStatement object.
					ps=cn.prepareStatement("SELECT login, password FROM connect WHERE login = ? AND password = ?");
					
					ps.setString(1, id.getText());	
					ps.setString(2, String.valueOf(mdp.getPassword()));
					
					ResultSet rs= ps.executeQuery();
					if (rs.next()) {
						//Preloader preload= new Preloader();
						MainScreen mainScreen=new MainScreen();
						mainScreen.setVisible(true);
						mainScreen.setLocationRelativeTo(null);
						frame.dispose();
						
					}
					else {
						
						JOptionPane.showMessageDialog(frame,"Oops, Seems like you mistyped ☉_☉ ." ,"Empty Field", JOptionPane.ERROR_MESSAGE);
					}
					
				}
				catch(Exception e) {
					
					JOptionPane.showMessageDialog(frame,"Oops, You are facing database Error." ,"DataBase Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JLabel lblIdBibliotcaire = new JLabel("Secret Access");
		lblIdBibliotcaire.setForeground(new Color(255, 255, 255));
		lblIdBibliotcaire.setFont(new Font("Nakula", Font.BOLD, 15));
		
		JLabel lblMotDePasse = new JLabel("Unique Pass");
		lblMotDePasse.setForeground(new Color(255, 255, 255));
		lblMotDePasse.setFont(new Font("Nakula", Font.BOLD, 15));
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(LoginScreen.class.getResource("/tn/poly/bahri/images/shop.png")));
		
		JLabel lblNewLabel = new JLabel("\"The purpose of a business is to create a customer who creates customers.\"- Shiv Singh");
		lblNewLabel.setForeground(new Color(255, 204, 255));
		lblNewLabel.setFont(new Font("FreeSerif", Font.BOLD | Font.ITALIC, 14));
		

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(280, Short.MAX_VALUE)
					.addComponent(label)
					.addGap(246))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(73, Short.MAX_VALUE)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 509, GroupLayout.PREFERRED_SIZE)
					.addGap(63))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(125)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblIdBibliotcaire)
						.addComponent(lblMotDePasse))
					.addGap(68)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(mdp, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
						.addComponent(id, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(155, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(244)
					.addComponent(btnConnexion, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(255, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
					.addGap(31)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(72)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIdBibliotcaire)
						.addComponent(id, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(mdp, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMotDePasse))
					.addGap(30)
					.addComponent(btnConnexion, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(65, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	
	
}
