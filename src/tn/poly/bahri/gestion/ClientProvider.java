package tn.poly.bahri.gestion;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;

import java.awt.Font;
import javax.swing.JRadioButton;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientProvider extends JFrame {

    private ButtonGroup buttonGroup1 = new ButtonGroup();
	private JPanel contentPane;
	private JTextField registrationInput;
	private JTextField fullNameInput;
	private JTextField phoneInput;
	private JTextField emailInput;
	private JTextField bankName;
	private JTextField agencyName;
	private JTextField ribInput;
	private JTextField searchField;
	private JTable tableClient;
	private JTable tableProvider;
	private JRadioButton rdatBasetnClient = new JRadioButton("Client");
	private JRadioButton rdatBasetnProvider = new JRadioButton("Provider");
	private JComboBox<String> comboRegion = new JComboBox<>();
	private JComboBox<String> comboSearch = new JComboBox<String>();

	JLabel backgroundLabel = new JLabel("");

	private Connection con = ConnectDB.getConnexion();
	private String query;
	private Statement stmt;
	
	 public ClientProvider() {
		 initComponents();
	    }
	 
	public void initComponents() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				allClients(tableClient);
				allProviders(tableProvider);
			}
		});

		setTitle("Client / Provider");	
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1350, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Panel panel = new Panel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(542, 12, 102, 102);
		lblNewLabel.setIcon(new ImageIcon(ClientProvider.class.getResource("/tn/poly/bahri/images/gathering.png")));
		panel.setLayout(null);
		panel.add(lblNewLabel);
		
		rdatBasetnClient.setFont(new Font("Dialog", Font.BOLD, 14));
		rdatBasetnClient.setOpaque(false);
		rdatBasetnClient.setForeground(new Color(255, 51, 0));
		rdatBasetnClient.setBounds(50, 54, 78, 23);
		panel.add(rdatBasetnClient);

		rdatBasetnProvider.setFont(new Font("Dialog", Font.BOLD, 14));
		rdatBasetnProvider.setOpaque(false);
		rdatBasetnProvider.setForeground(new Color(255, 51, 0));
		rdatBasetnProvider.setBounds(149, 54, 102, 23);
		panel.add(rdatBasetnProvider);
	
        buttonGroup1.add(rdatBasetnClient);
        buttonGroup1.add(rdatBasetnProvider);
		
		JLabel matriculeLabel = new JLabel("Registration N°");
		matriculeLabel.setFont(new Font("FreeSerif", Font.BOLD, 16));
		matriculeLabel.setForeground(Color.WHITE);
		matriculeLabel.setBounds(43, 110, 121, 15);
		panel.add(matriculeLabel);
		
		registrationInput = new JTextField();
		registrationInput.setBounds(182, 102, 155, 32);
		panel.add(registrationInput);
		registrationInput.setColumns(10);
		
		JLabel raisonSocialeLabel = new JLabel("FullName");
		raisonSocialeLabel.setFont(new Font("FreeSerif", Font.BOLD, 16));
		raisonSocialeLabel.setForeground(Color.WHITE);
		raisonSocialeLabel.setBounds(43, 173, 70, 15);
		panel.add(raisonSocialeLabel);
		
		fullNameInput = new JTextField();
		fullNameInput.setColumns(10);
		fullNameInput.setBounds(182, 164, 155, 32);
		panel.add(fullNameInput);
		
		JLabel lblAdress = new JLabel("Region");
		lblAdress.setFont(new Font("FreeSerif", Font.BOLD, 16));
		lblAdress.setForeground(Color.WHITE);
		lblAdress.setBounds(50, 226, 63, 15);
		panel.add(lblAdress);	
		
		comboRegion.setModel(new DefaultComboBoxModel<>(new String[] {"Tunis", "Sousse", "Sfax", "Bizerte", "Monastir", "Mahdia"}));
		comboRegion.setBounds(182, 220, 155, 24);
		panel.add(comboRegion);
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setFont(new Font("FreeSerif", Font.BOLD, 16));
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setBounds(50, 326, 54, 15);
		panel.add(lblEmail);
		
		phoneInput = new JTextField();
		phoneInput.setColumns(10);
		phoneInput.setBounds(182, 266, 155, 32);
		panel.add(phoneInput);
		
		JLabel lblPhoneN = new JLabel("Phone N°");
		lblPhoneN.setFont(new Font("FreeSerif", Font.BOLD, 16));
		lblPhoneN.setForeground(Color.WHITE);
		lblPhoneN.setBounds(50, 275, 78, 15);
		panel.add(lblPhoneN);
		
		emailInput = new JTextField();
		emailInput.setColumns(10);
		emailInput.setBounds(182, 317, 155, 32);
		panel.add(emailInput);
		
		JLabel lblBankAccount = new JLabel("Insert your Bank account details carefully");
		lblBankAccount.setFont(new Font("Georgia", Font.BOLD, 18));
		lblBankAccount.setForeground(new Color(153, 102, 255));
		lblBankAccount.setBounds(40, 375, 414, 23);
		panel.add(lblBankAccount);
		
		JLabel lblBank = new JLabel("Bank");
		lblBank.setForeground(Color.WHITE);
		lblBank.setFont(new Font("FreeSerif", Font.BOLD, 16));
		lblBank.setBounds(50, 434, 58, 15);
		panel.add(lblBank);
		
		bankName = new JTextField();
		bankName.setColumns(10);
		bankName.setBounds(182, 425, 155, 32);
		panel.add(bankName);
		
		JLabel lblAgency = new JLabel("Agency");
		lblAgency.setForeground(Color.WHITE);
		lblAgency.setFont(new Font("FreeSerif", Font.BOLD, 16));
		lblAgency.setBounds(50, 496, 54, 15);
		panel.add(lblAgency);
		
		agencyName = new JTextField();
		agencyName.setColumns(10);
		agencyName.setBounds(182, 488, 155, 32);
		panel.add(agencyName);
		
		JLabel lblRib = new JLabel("RIB");
		lblRib.setForeground(Color.WHITE);
		lblRib.setFont(new Font("FreeSerif", Font.BOLD, 16));
		lblRib.setBounds(50, 548, 54, 15);
		panel.add(lblRib);
		
		ribInput = new JTextField();
		ribInput.setColumns(50);
		ribInput.setBounds(182, 539, 155, 32);
		panel.add(ribInput);
		
		JButton btnRefrech = new JButton("");
		btnRefrech.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				allClients(tableClient);
				allProviders(tableProvider);
				searchField.setText("");
			}
		});
		btnRefrech.setBackground(new Color(255, 255, 255));
		btnRefrech.setIcon(new ImageIcon(ClientProvider.class.getResource("/tn/poly/bahri/images/update.png")));
		btnRefrech.setBounds(542, 153, 62, 32);
		panel.add(btnRefrech);
		
		JButton btnAdd = new JButton("");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				AddtnActionPerformed(evt);
			}
		});
		btnAdd.setForeground(SystemColor.controlLtHighlight);
		btnAdd.setIcon(new ImageIcon(ClientProvider.class.getResource("/tn/poly/bahri/images/addIcon.png")));
		btnAdd.setBounds(542, 215, 62, 35);
		panel.add(btnAdd);
		
		JButton btnUpdate = new JButton("");

		btnUpdate.setIcon(new ImageIcon(ClientProvider.class.getResource("/tn/poly/bahri/images/refresh.png")));
		btnUpdate.setBounds(542, 284, 62, 35);
		panel.add(btnUpdate);
		
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(rdatBasetnProvider.isSelected()){
						
							stmt = con.createStatement();
							String sql= "UPDATE provider SET registration = '"+registrationInput.getText()+"',fullName= '"+fullNameInput.getText()+"', region= '"+comboRegion.getSelectedItem()+"', phone='"+phoneInput.getText()+"',email='"+emailInput.getText()+"', bankName='"+bankName.getText()+"',agencyName='"+agencyName.getText()+"',rib='"+ribInput.getText()+"' WHERE registration='"+registrationInput.getText()+"';";
							stmt.executeUpdate(sql);
							JOptionPane.showMessageDialog (null, "Provider Updated Successfully", "Updated", JOptionPane.INFORMATION_MESSAGE);
							
					}
					
					else if(rdatBasetnClient.isSelected()){

							stmt = con.createStatement();
							String sql= "UPDATE client SET registrationClient = '"+registrationInput.getText()+"',fullNameClient= '"+fullNameInput.getText()+"', regionClient= '"+comboRegion.getSelectedItem()+"', phoneClient='"+phoneInput.getText()+"',emailClient='"+emailInput.getText()+"', bankNameClient='"+bankName.getText()+"',agencyNameClient='"+agencyName.getText()+"',ribClient='"+ribInput.getText()+"' WHERE registrationClient='"+registrationInput.getText()+"';";
							stmt.executeUpdate(sql);
							JOptionPane.showMessageDialog (null, "Client Updated Successfully", "Updated", JOptionPane.INFORMATION_MESSAGE);
						}
					
					buttonGroup1.clearSelection();
					registrationInput.setText("");
					fullNameInput.setText("");
					comboRegion.setSelectedIndex(0);
					phoneInput.setText("");
					emailInput.setText("");
					bankName.setText("");
					agencyName.setText("");
					ribInput.setText("");
					
					allClients(tableClient);
					allProviders(tableProvider);
					
				}catch (SQLException ex) {
					JOptionPane.showMessageDialog(null,"Something Went wrong...", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		JButton btnDelete = new JButton("");
		btnDelete.setIcon(new ImageIcon(ClientProvider.class.getResource("/tn/poly/bahri/images/delete.png")));
		btnDelete.setBounds(542, 358, 62, 32);
		panel.add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
						
				try{
					
					if(rdatBasetnProvider.isSelected()){
						
						if(JOptionPane.showConfirmDialog(null, "Are you sure you want to delete ?",
																"Delete",
																JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION){
							
							String sql = "DELETE FROM provider WHERE registration='"+registrationInput.getText()+"';";
							stmt=con.createStatement();
							stmt.executeUpdate(sql);
					        JOptionPane.showMessageDialog (null, "Provider Successfully Deleted ", "Deleted", JOptionPane.INFORMATION_MESSAGE);

							DeleteAllElementTab(tableProvider, 8,49);
							DeleteAllElementTab(tableClient, 8,49);
							}
						}
					else if(rdatBasetnClient.isSelected()){
						
						if(JOptionPane.showConfirmDialog(null, "Are you sure you want to delete ?",
								"Delete",
								JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION){

							stmt=con.createStatement();
					        String sql = "DELETE FROM client WHERE registrationClient= '"+registrationInput.getText()+"';";
					        stmt.executeUpdate(sql);
					        JOptionPane.showMessageDialog (null, "Client Successfully Deleted ", "Deleted", JOptionPane.INFORMATION_MESSAGE);

				        	DeleteAllElementTab(tableClient, 8,49);
				        	DeleteAllElementTab(tableProvider, 8,49);

				        	 }
						}
				      
					allClients(tableClient);
					allProviders(tableProvider);
				}
				catch(Exception e){
					//JOptionPane.showMessageDialog(null, "Opps, Something Went Wrong");
					System.out.println(e.getMessage());
				}			
			}
		});
				
		JButton btnSearch = new JButton("");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(rdatBasetnProvider.isSelected()){
					
					DeleteAllElementTab(tableProvider, 7,49);
			        FindFornisseur(tableProvider, comboSearch.getSelectedItem().toString(), searchField.getText());	
				}
				else if(rdatBasetnClient.isSelected()){
					DeleteAllElementTab(tableClient, 7,49);
			        FindClient(tableClient, searchField.getText());	
				}
			}
		});

		btnSearch.setForeground(SystemColor.text);
		btnSearch.setIcon(new ImageIcon(ClientProvider.class.getResource("/tn/poly/bahri/images/search.png")));
		btnSearch.setBounds(1056, 82, 85, 32);
		panel.add(btnSearch);
		
		searchField = new JTextField();
		searchField.setBounds(912, 80, 117, 32);
		panel.add(searchField);
		searchField.setColumns(10);
		
		JLabel lblClient = new JLabel("Client");
		lblClient.setFont(new Font("Georgia", Font.BOLD, 16));
		lblClient.setForeground(new Color(0, 204, 0));
		lblClient.setBounds(731, 126, 70, 15);
		panel.add(lblClient);
		
		JScrollPane jScrollPane2 = new JScrollPane();
		jScrollPane2.setBounds(731, 153, 578, 188);
		panel.add(jScrollPane2);
		
		tableClient = new JTable();
		
		tableClient.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableClient.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Registration N", "FullName", "Region", "Phone N", "E-Mail", "Bank Name", "Agency Name", "RIB"
			}
		));
		
		tableClient.addMouseListener(new MouseAdapter() {  // Retrieve Client  data from 
			@Override
			public void mouseClicked(MouseEvent e) {
				int i;
				  try{  
			           i= tableClient.getSelectedRow();
			           //System.out.println("i="+i);
			           registrationInput.setText( tableClient.getValueAt(i, 0)+"");
			           fullNameInput.setText( tableClient.getValueAt(i, 1)+"");
			           comboRegion.setSelectedItem( tableClient.getValueAt(i, 2)+"");
			           phoneInput.setText( tableClient.getValueAt(i, 3)+"");
			           emailInput.setText( tableClient.getValueAt(i, 4)+"");
			           bankName.setText( tableClient.getValueAt(i, 5)+"");
			           agencyName.setText( tableClient.getValueAt(i, 6)+"");
			           ribInput.setText( tableClient.getValueAt(i, 7)+"");
			           
			     }catch(Exception exp){
			    	JOptionPane.showMessageDialog(null,"Something Went wrong...", "Warning", JOptionPane.WARNING_MESSAGE);

			     }
			}
		});

		jScrollPane2.setViewportView(tableClient);
		
			tableClient.getColumnModel().getColumnCount();	
			tableClient.getColumnModel().getColumn(0).setPreferredWidth(103);
			tableClient.getColumnModel().getColumn(2).setPreferredWidth(67);
			tableClient.getColumnModel().getColumn(4).setPreferredWidth(94);
			tableClient.getColumnModel().getColumn(6).setPreferredWidth(94);
			
		JLabel lblProvider = new JLabel("Provider");
		lblProvider.setForeground(new Color(0, 204, 0));
		lblProvider.setFont(new Font("Georgia", Font.BOLD, 16));
		lblProvider.setBounds(731, 349, 96, 15);
		panel.add(lblProvider);
		
		JScrollPane jScrollPane2_1 = new JScrollPane();
		jScrollPane2_1.setBounds(731, 380, 583, 188);
		panel.add(jScrollPane2_1);
		
		tableProvider = new JTable();
		tableProvider.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableProvider.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Registration N", "FullName", "Region", "Phone N", "E-Mail", "Bank Name", "Agency Name", "RIB"
			}
		));
		
			
		comboSearch.setModel(new DefaultComboBoxModel(new String[] {"FullName", "Region"}));
		comboSearch.setBounds(746, 79, 155, 35);
		panel.add(comboSearch);
		JLabel backgroundLabel = new JLabel("");
		backgroundLabel.setBounds(0, -20, 2050, 613);
		backgroundLabel.setIcon(new ImageIcon(ClientProvider.class.getResource("/tn/poly/bahri/images/ClientProv.png")));
		panel.add(backgroundLabel);

				

		tableProvider.addMouseListener(new MouseAdapter() {// Retrieve Provider  data from Table
			@Override
			public void mouseClicked(MouseEvent e) { 
				int i;
				  try{  
			           i= tableProvider.getSelectedRow();
			           //System.out.println("i="+i);
			           registrationInput.setText( tableProvider.getValueAt(i, 0)+"");
			           fullNameInput.setText( tableProvider.getValueAt(i, 1)+"");
			           comboRegion.setSelectedItem( tableProvider.getValueAt(i, 2)+"");
			           phoneInput.setText( tableProvider.getValueAt(i, 3)+"");
			           emailInput.setText( tableProvider.getValueAt(i, 4)+"");
			           bankName.setText( tableProvider.getValueAt(i, 5)+"");
			           agencyName.setText( tableProvider.getValueAt(i, 6)+"");
			           ribInput.setText( tableProvider.getValueAt(i, 7)+"");
			           
			     }catch(Exception exp){
			    	 JOptionPane.showMessageDialog(null,"Something Went wrong...", "Warning", JOptionPane.WARNING_MESSAGE);
			     }
			}
		});

		jScrollPane2_1.setViewportView(tableProvider);
			tableProvider.getColumnModel().getColumnCount();
			tableProvider.getColumnModel().getColumn(0).setPreferredWidth(97);
			tableProvider.getColumnModel().getColumn(1).setPreferredWidth(93);
			tableProvider.getColumnModel().getColumn(2).setPreferredWidth(73);
			tableProvider.getColumnModel().getColumn(3).setPreferredWidth(88);
			tableProvider.getColumnModel().getColumn(4).setPreferredWidth(87);
			tableProvider.getColumnModel().getColumn(5).setPreferredWidth(81);
			tableProvider.getColumnModel().getColumn(6).setPreferredWidth(91);	
		
		backgroundLabel.setBounds(0, -20, 2050, 613);
		backgroundLabel.setIcon(new ImageIcon(ClientProvider.class.getResource("/tn/poly/bahri/images/ClientProv.png")));
		panel.add(backgroundLabel);
	}

    
	private void AddtnActionPerformed(ActionEvent evt){ // Adding to the Data Base
		
		if (registrationInput.getText().equals("")) {
			JOptionPane.showMessageDialog(null,"Please Fill your Registration Number.", "Empty Field", JOptionPane.WARNING_MESSAGE);
		}
		else if (fullNameInput.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please Fill your Fullname.", "Empty Field", JOptionPane.WARNING_MESSAGE);
		}
		else if (comboRegion.getSelectedItem()==null) {
			JOptionPane.showMessageDialog(null, "Please choose your Region.", "Empty Field", JOptionPane.WARNING_MESSAGE);
		}
		else if (phoneInput.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter your Phone Number.", "Empty Field", JOptionPane.WARNING_MESSAGE);
		
		}
		else if (emailInput.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please enter your Email.", "Empty Field", JOptionPane.WARNING_MESSAGE);
		
		}
		else if (bankName.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please Fill your Bank Name.", "Empty Field", JOptionPane.WARNING_MESSAGE);
		
		}
		else if (agencyName.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please Fill your Agency Name.", "Empty Field", JOptionPane.WARNING_MESSAGE);
		
		}
		else if (ribInput.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Please Fill your RIB.", "Empty Field", JOptionPane.WARNING_MESSAGE);
		
		}
		else {//Champ pas vide
			
			if(rdatBasetnClient.isSelected()){
				try {
					query="insert into client values ('"+registrationInput.getText()+"','"+fullNameInput.getText()+"','"+comboRegion.getSelectedItem()+"','"+phoneInput.getText()+"','"+emailInput.getText()+"','"+bankName.getText()+"','"+agencyName.getText()+"', '"+ribInput.getText()+"');";
					stmt=con.createStatement();
					stmt.executeUpdate(query);
					
					JOptionPane.showMessageDialog(null, "Client :'"+fullNameInput.getText()+"' Added Successfully");
					
					buttonGroup1.clearSelection();
					registrationInput.setText("");
					fullNameInput.setText("");
					comboRegion.setSelectedIndex(-1);
					phoneInput.setText("");
					emailInput.setText("");
					bankName.setText("");
					agencyName.setText("");
					ribInput.setText("");
					
					allClients(tableClient);
					allProviders(tableProvider);
					
				
				}catch(SQLException exp) {
					if (exp.getErrorCode()==1062) {
						JOptionPane.showMessageDialog(null, "We already have you in our DataBase","Duplication Error",JOptionPane.ERROR_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null,exp.getMessage());
					}
				}
			
			}
			
			else if(rdatBasetnProvider.isSelected()){
				
				try {
			
					query="insert into provider values ('"+registrationInput.getText()+"','"+fullNameInput.getText()+"','"+comboRegion.getSelectedItem()+"','"+phoneInput.getText()+"','"+emailInput.getText()+"','"+bankName.getText()+"','"+agencyName.getText()+"', '"+ribInput.getText()+"');";
					stmt=con.createStatement();
					stmt.executeUpdate(query);
					
					JOptionPane.showMessageDialog(null, "Provider :'"+fullNameInput.getText()+"' Added Successfully");
		
					buttonGroup1.clearSelection();
					registrationInput.setText("");
					fullNameInput.setText("");
					comboRegion.setSelectedIndex(0);
					phoneInput.setText("");
					emailInput.setText("");
					bankName.setText("");
					agencyName.setText("");
					ribInput.setText("");
					
					allClients(tableClient);
					allProviders(tableProvider);
				
				}catch(SQLException exp) {
					if (exp.getErrorCode()==1062) {
						JOptionPane.showMessageDialog(null, "We already have you in our DataBase","Duplication Error",JOptionPane.ERROR_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null,exp.getMessage());
					}
				}
		     }
		}//Fin else champs pas vide 
		    
	}
	
	public void allClients(JTable T) {
	        try {
	        	
	        	Statement stmt = con.createStatement();
	            String sql = "select * from client";
	            ResultSet rs = (ResultSet) stmt.executeQuery(sql);

	            int i = 0, j;
	            while (rs.next()) {
	                j = 0; //Cols
	                T.setValueAt(rs.getString("registrationClient").toString(), i, j);
	                j++;
	                T.setValueAt(rs.getString("fullNameClient").toString(), i, j);
	                j++;
	                T.setValueAt(rs.getString("regionClient").toString(), i, j);
	                j++;
	                T.setValueAt(rs.getInt("phoneClient"), i, j);
	                j++;
	                T.setValueAt(rs.getString("emailClient").toString(), i, j);
	                j++;
	                T.setValueAt(rs.getString("bankNameClient").toString(), i, j);
	                j++;
	                T.setValueAt(rs.getString("agencyNameClient").toString(), i, j);
	                j++;
	                T.setValueAt(rs.getString("ribClient").toString(), i, j);
	                j++;
	                
	                i++; //Rows
	            }

	        } catch (SQLException ex) {
	            JOptionPane.showMessageDialog(null, ex + " Opps, Can't retreieve Data !!");
	        }
	    }
	
	public void allProviders(JTable T) {

		    try {
		    	
	        	Statement stmt = con.createStatement();
	            String sql = "select * from provider";
	            ResultSet rs = (ResultSet) stmt.executeQuery(sql);
	            int i = 0, j;
	            while (rs.next()) {
	                j = 0;
	                T.setValueAt(rs.getString("registration").toString(), i, j);
	                j++;
	                T.setValueAt(rs.getString("fullName").toString(), i, j);
	                j++;
	                T.setValueAt(rs.getString("region").toString(), i, j);
	                j++;
	                T.setValueAt(rs.getInt("phone"), i, j);
	                j++;
	                T.setValueAt(rs.getString("email").toString(), i, j);
	                j++;
	                T.setValueAt(rs.getString("bankName").toString(), i, j);
	                j++;
	                T.setValueAt(rs.getString("agencyName").toString(), i, j);
	                j++;
	                T.setValueAt(rs.getString("rib").toString(), i, j);
	                j++;
	                
	                i++;
		        }
		
		    } catch (SQLException ex) {
	            JOptionPane.showMessageDialog(null, ex + " Opps, Can't retreieve Data !!");
		    }
	    }
    
    public void DeleteAllElementTab(JTable T, int column, int row) {
        int i = 0;
        int j = 0;
        while (i < row) {
            while (j <= column) {
                T.setValueAt("", i, j);
                j++;
            }
            j = 0;
            i++;
        }

    }
    
    public void FindFornisseur(JTable T, String word, String searchName) {
        try {
        	ResultSet rs;
            stmt = con.createStatement();
            String sql = "SELECT * FROM provider WHERE " + word + " like '" + searchName + "%'  ";
            rs = stmt.executeQuery(sql);
            int i = 0, j;
            while (rs.next()) {
                j = 0;
                T.setValueAt(rs.getString("registration").toString(), i, j);
                j++;
                T.setValueAt(rs.getString("fullName").toString(), i, j);
                j++;
                T.setValueAt(rs.getString("region").toString(), i, j);
                j++;
                T.setValueAt(rs.getInt("phone"), i, j);
                j++;
                T.setValueAt(rs.getString("email").toString(), i, j);
                j++;
                T.setValueAt(rs.getString("bankName").toString(), i, j);
                j++;
                T.setValueAt(rs.getString("agencyName").toString(), i, j);
                j++;
                T.setValueAt(rs.getString("rib").toString(), i, j);
                j++;
                
                i++;
            }

        } catch (SQLException e) {
        	
        	System.out.println(e.getMessage());
        	
        }
    }
    
    
    public void FindClient(JTable T, String word) {
        try {
        	ResultSet rs;
            stmt = con.createStatement();
             
            String sql = "SELECT * FROM client WHERE fullNameClient like '" + word + "%'";
            rs = stmt.executeQuery(sql);
            int i = 0, j;
            while (rs.next()) {
                j = 0; //Cols
                T.setValueAt(rs.getString("registrationClient").toString(), i, j);
                j++;
                T.setValueAt(rs.getString("fullNameClient").toString(), i, j);
                j++;
                T.setValueAt(rs.getString("regionClient").toString(), i, j);
                j++;
                T.setValueAt(rs.getInt("phoneClient"), i, j);
                j++;
                T.setValueAt(rs.getString("emailClient").toString(), i, j);
                j++;
                T.setValueAt(rs.getString("bankNameClient").toString(), i, j);
                j++;
                T.setValueAt(rs.getString("agencyNameClient").toString(), i, j);
                j++;
                T.setValueAt(rs.getString("ribClient").toString(), i, j);
                j++;
                
                i++; //Rows
            }

        } catch (SQLException e) {
        	System.out.println(e.getMessage());

        }
    }
    
    
    
        
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientProvider frame = new ClientProvider();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

	
	
	
	

