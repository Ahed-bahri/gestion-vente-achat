package tn.poly.bahri.gestion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CompanyInfo extends JFrame {

	private JPanel contentPane;
	private JTextField registrationInput;
	private JTextField fullNameInput;
	private JTextField phoneInput;
	private JTextField emailInput;
	private JTextField bankName;
	private JTextField agencyName;
	private JTextField ribInput;
	private JTextField serachField;
	private JTable tablePhysical;
	private JTable tableMoral;

	
	private JComboBox<String> comboRegion = new JComboBox<>();
	private JComboBox<String> comboSearch = new JComboBox<String>();

	JLabel backgroundLabel = new JLabel("");

	private Connection con = ConnectDB.getConnexion();
	private String query;
	private Statement stmt;
	private final ButtonGroup buttonGroup1 = new ButtonGroup();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CompanyInfo frame = new CompanyInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CompanyInfo() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				allPhyscical(tablePhysical);
				allMoral(tableMoral);
			}
		});
		setTitle("Company Registration");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1350, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Panel panel = new Panel();
		panel.setLayout(null);
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(CompanyInfo.class.getResource("/tn/poly/bahri/images/CompnyIcon.png")));
		lblNewLabel.setBounds(542, 12, 102, 102);
		panel.add(lblNewLabel);
		
		JRadioButton rdioBtnPhysique = new JRadioButton("Physical");
		buttonGroup1.add(rdioBtnPhysique);
		rdioBtnPhysique.setOpaque(false);
		rdioBtnPhysique.setForeground(new Color(255, 51, 0));
		rdioBtnPhysique.setFont(new Font("Dialog", Font.BOLD, 14));
		rdioBtnPhysique.setBounds(50, 54, 114, 23);
		panel.add(rdioBtnPhysique);
		
		JRadioButton rdioBtnMoral = new JRadioButton("Moral");
		buttonGroup1.add(rdioBtnMoral);
		rdioBtnMoral.setOpaque(false);
		rdioBtnMoral.setForeground(new Color(255, 51, 0));
		rdioBtnMoral.setFont(new Font("Dialog", Font.BOLD, 14));
		rdioBtnMoral.setBounds(149, 54, 102, 23);
		panel.add(rdioBtnMoral);
		
		JLabel matriculeLabel = new JLabel("Registration N°");
		matriculeLabel.setForeground(Color.WHITE);
		matriculeLabel.setFont(new Font("FreeSerif", Font.BOLD, 16));
		matriculeLabel.setBounds(43, 110, 121, 15);
		panel.add(matriculeLabel);
		
		registrationInput = new JTextField();
		registrationInput.setColumns(10);
		registrationInput.setBounds(182, 102, 155, 32);
		panel.add(registrationInput);
		
		JLabel raisonSocialeLabel = new JLabel("FullName");
		raisonSocialeLabel.setForeground(Color.WHITE);
		raisonSocialeLabel.setFont(new Font("FreeSerif", Font.BOLD, 16));
		raisonSocialeLabel.setBounds(43, 173, 70, 15);
		panel.add(raisonSocialeLabel);
		
		fullNameInput = new JTextField();
		fullNameInput.setColumns(10);
		fullNameInput.setBounds(182, 164, 155, 32);
		panel.add(fullNameInput);
		
		JLabel lblAdress = new JLabel("Region");
		lblAdress.setForeground(Color.WHITE);
		lblAdress.setFont(new Font("FreeSerif", Font.BOLD, 16));
		lblAdress.setBounds(50, 226, 63, 15);
		panel.add(lblAdress);
		comboRegion.setModel(new DefaultComboBoxModel<>(new String[] {"Tunis", "Sousse", "Sfax", "Bizerte", "Monastir", "Mahdia"}));
		
		comboRegion.setBounds(182, 220, 155, 24);
		panel.add(comboRegion);
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("FreeSerif", Font.BOLD, 16));
		lblEmail.setBounds(50, 326, 54, 15);
		panel.add(lblEmail);
		
		phoneInput = new JTextField();
		phoneInput.setColumns(10);
		phoneInput.setBounds(182, 266, 155, 32);
		panel.add(phoneInput);
		
		JLabel lblPhoneN = new JLabel("Phone N°");
		lblPhoneN.setForeground(Color.WHITE);
		lblPhoneN.setFont(new Font("FreeSerif", Font.BOLD, 16));
		lblPhoneN.setBounds(50, 275, 78, 15);
		panel.add(lblPhoneN);
		
		emailInput = new JTextField();
		emailInput.setColumns(10);
		emailInput.setBounds(182, 317, 155, 32);
		panel.add(emailInput);
		
		JLabel lblBankAccount = new JLabel("Insert your Bank account details carefully");
		lblBankAccount.setForeground(new Color(153, 102, 255));
		lblBankAccount.setFont(new Font("Georgia", Font.BOLD, 18));
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
				allPhyscical(tablePhysical);
				allMoral(tableMoral);
				serachField.setText("");
			}
		});
		btnRefrech.setIcon(new ImageIcon(CompanyInfo.class.getResource("/tn/poly/bahri/images/update.png")));
		btnRefrech.setBackground(Color.WHITE);
		btnRefrech.setBounds(542, 153, 62, 32);
		panel.add(btnRefrech);
		
		JButton btnAdd = new JButton("");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
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
				else { // champs n'est pas vide
					
					if(rdioBtnPhysique.isSelected()){
						try {
							
							query="insert into companyPhysical values ('"+registrationInput.getText()+"','"+fullNameInput.getText()+"','"+comboRegion.getSelectedItem()+"','"+phoneInput.getText()+"','"+emailInput.getText()+"','"+bankName.getText()+"','"+agencyName.getText()+"', '"+ribInput.getText()+"');";
							stmt=con.createStatement();
							stmt.executeUpdate(query);
							
							JOptionPane.showMessageDialog(null, "Company Of"+fullNameInput.getText()+"' Added Successfully");
							
							buttonGroup1.clearSelection();
							registrationInput.setText("");
							fullNameInput.setText("");
							comboRegion.setSelectedIndex(-1);
							phoneInput.setText("");
							emailInput.setText("");
							bankName.setText("");
							agencyName.setText("");
							ribInput.setText("");
							
							allPhyscical(tablePhysical);
							allMoral(tableMoral);
							
						
						}catch(SQLException exp) {
							if (exp.getErrorCode()==1062) {
								JOptionPane.showMessageDialog(null, "We already have you in our DataBase","Duplication Error",JOptionPane.ERROR_MESSAGE);
							}
							else {
								JOptionPane.showMessageDialog(null,exp.getMessage());
							}
						}
					
					}
					
					else if(rdioBtnMoral.isSelected()){
						
						try {
					
							query="insert into companyMoral values ('"+registrationInput.getText()+"','"+fullNameInput.getText()+"','"+comboRegion.getSelectedItem()+"','"+phoneInput.getText()+"','"+emailInput.getText()+"','"+bankName.getText()+"','"+agencyName.getText()+"', '"+ribInput.getText()+"');";
							stmt=con.createStatement();
							stmt.executeUpdate(query);
							
							JOptionPane.showMessageDialog(null, "Company Of :'"+fullNameInput.getText()+"' Added Successfully");
				
							buttonGroup1.clearSelection();
							registrationInput.setText("");
							fullNameInput.setText("");
							comboRegion.setSelectedIndex(0);
							phoneInput.setText("");
							emailInput.setText("");
							bankName.setText("");
							agencyName.setText("");
							ribInput.setText("");
							
							allPhyscical(tablePhysical);
							allMoral(tableMoral);
						
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
		});
		btnAdd.setIcon(new ImageIcon(CompanyInfo.class.getResource("/tn/poly/bahri/images/addIcon.png")));
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBounds(542, 215, 62, 35);
		panel.add(btnAdd);
		
		JButton btnUpdate = new JButton("");
		btnUpdate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					if(rdioBtnPhysique.isSelected()){
						
							stmt = con.createStatement();
							String sql= "UPDATE companyPhysical SET registrationPhysical = '"+registrationInput.getText()+"',fullNamePhysical= '"+fullNameInput.getText()+"', regionPhysical= '"+comboRegion.getSelectedItem()+"', phonePhysical='"+phoneInput.getText()+"',emailPhysical='"+emailInput.getText()+"', bankNamePhysical='"+bankName.getText()+"',agencyNamePhysical='"+agencyName.getText()+"',ribPhysical='"+ribInput.getText()+"' WHERE registrationPhysical='"+registrationInput.getText()+"';";
							stmt.executeUpdate(sql);
							JOptionPane.showMessageDialog (null, "Physical Company Updated Successfully", "Updated", JOptionPane.INFORMATION_MESSAGE);
							
					}
					
					else if(rdioBtnMoral.isSelected()){

							stmt = con.createStatement();
							String sql= "UPDATE companyMoral SET registrationMoral = '"+registrationInput.getText()+"',fullNameMoral= '"+fullNameInput.getText()+"', regionMoral= '"+comboRegion.getSelectedItem()+"', phoneMoral='"+phoneInput.getText()+"',emailMoral='"+emailInput.getText()+"', bankNameMoral='"+bankName.getText()+"',agencyNameMoral='"+agencyName.getText()+"',ribMoral='"+ribInput.getText()+"' WHERE registrationMoral='"+registrationInput.getText()+"';";
							stmt.executeUpdate(sql);
							JOptionPane.showMessageDialog (null, "Moral Company Updated Successfully", "Updated", JOptionPane.INFORMATION_MESSAGE);
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
					
					allPhyscical(tablePhysical);
					allMoral(tableMoral);
					
				}catch (SQLException ex) {
					JOptionPane.showMessageDialog(null,"Something Went wrong...", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnUpdate.setIcon(new ImageIcon(CompanyInfo.class.getResource("/tn/poly/bahri/images/refresh.png")));
		btnUpdate.setBounds(542, 284, 62, 35);
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				try{
					
					if(rdioBtnPhysique.isSelected()){
						
						if(JOptionPane.showConfirmDialog(null, "Are you sure you want to delete ?",
																"Delete",
																JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION){
							
							String sql = "DELETE FROM companyPhysical WHERE registrationPhysical='"+registrationInput.getText()+"';";
							stmt=con.createStatement();
							stmt.executeUpdate(sql);
					        JOptionPane.showMessageDialog (null, "Company Successfully Deleted ", "Deleted", JOptionPane.INFORMATION_MESSAGE);

							DeleteAllElementTab(tablePhysical, 8,49);
							DeleteAllElementTab(tableMoral, 8,49);
							}
						}
					else if(rdioBtnMoral.isSelected()){
						
						if(JOptionPane.showConfirmDialog(null, "Are you sure you want to delete ?",
								"Delete",
								JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION){

							stmt=con.createStatement();
					        String sql = "DELETE FROM companyMoral WHERE registrationMoral= '"+registrationInput.getText()+"';";
					        stmt.executeUpdate(sql);
					        JOptionPane.showMessageDialog (null, "Company Successfully Deleted ", "Deleted", JOptionPane.INFORMATION_MESSAGE);

							DeleteAllElementTab(tablePhysical, 8,49);
							DeleteAllElementTab(tableMoral, 8,49);

				        	 }
						}
				      
					allPhyscical(tablePhysical);
					allMoral(tableMoral);
				}
				catch(Exception e){
					//JOptionPane.showMessageDialog(null, "Opps, Something Went Wrong");
					System.out.println(e.getMessage());
				}	
			
			}
		});
		btnDelete.setIcon(new ImageIcon(CompanyInfo.class.getResource("/tn/poly/bahri/images/delete.png")));
		btnDelete.setBounds(542, 358, 62, 32);
		panel.add(btnDelete);
		
		JButton btnSearch = new JButton("");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(rdioBtnPhysique.isSelected()){
					
					DeleteAllElementTab(tablePhysical, 7,49);
					FindPhysicalCompany(tablePhysical, comboSearch.getSelectedItem().toString(), serachField.getText());	
				}
				else if(rdioBtnMoral.isSelected()){
					DeleteAllElementTab(tableMoral, 7,49);
					FindMoralCompany(tableMoral, serachField.getText());	
				}
				
				
			}
		});
		btnSearch.setIcon(new ImageIcon(CompanyInfo.class.getResource("/tn/poly/bahri/images/search.png")));
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setBounds(1056, 82, 85, 32);
		panel.add(btnSearch);
		
		serachField = new JTextField();
		serachField.setColumns(10);
		serachField.setBounds(912, 80, 117, 32);
		panel.add(serachField);
		
		JLabel lblClient = new JLabel("Pysical");
		lblClient.setForeground(new Color(0, 204, 0));
		lblClient.setFont(new Font("Georgia", Font.BOLD, 16));
		lblClient.setBounds(731, 126, 102, 15);
		panel.add(lblClient);
		
		JScrollPane jScrollPane2 = new JScrollPane();
		jScrollPane2.setBounds(731, 153, 578, 188);
		panel.add(jScrollPane2);
		
		tablePhysical = new JTable();
		tablePhysical.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i;
				  try{  
			           i= tablePhysical.getSelectedRow();
			           registrationInput.setText( tablePhysical.getValueAt(i, 0)+""); 
			           fullNameInput.setText( tablePhysical.getValueAt(i, 1)+"");
			           comboRegion.setSelectedItem( tablePhysical.getValueAt(i, 2)+"");
			           phoneInput.setText( tablePhysical.getValueAt(i, 3)+"");
			           emailInput.setText( tablePhysical.getValueAt(i, 4)+"");
			           bankName.setText( tablePhysical.getValueAt(i, 5)+"");
			           agencyName.setText( tablePhysical.getValueAt(i, 6)+"");
			           ribInput.setText( tablePhysical.getValueAt(i, 7)+"");
			           
			     }catch(Exception exp){
			    	JOptionPane.showMessageDialog(null,"Something Went wrong...", "Warning", JOptionPane.WARNING_MESSAGE);

			     }				
			}
		});
		tablePhysical.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablePhysical.setModel(new DefaultTableModel(
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
				"Registration", "FullName", "Region", "Phone", "E-Mail", "Bank Name", "Agency Name", "RIB"
			}
		));
		jScrollPane2.setViewportView(tablePhysical);
		
		comboSearch.setModel(new DefaultComboBoxModel<>(new String[] {"FullName", "Region"}));
		comboSearch.setBounds(746, 79, 155, 35);
		panel.add(comboSearch);
		
		JLabel lblProvider = new JLabel("Moral");
		lblProvider.setForeground(new Color(0, 204, 0));
		lblProvider.setFont(new Font("Georgia", Font.BOLD, 16));
		lblProvider.setBounds(726, 352, 96, 15);
		panel.add(lblProvider);
		
		JScrollPane jScrollPane2_1 = new JScrollPane();
		jScrollPane2_1.setBounds(726, 383, 583, 188);
		panel.add(jScrollPane2_1);
		
		tableMoral = new JTable();
		tableMoral.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i;
				  try{  
			           i= tableMoral.getSelectedRow();
			           registrationInput.setText( tableMoral.getValueAt(i, 0)+"");
			           fullNameInput.setText( tableMoral.getValueAt(i, 1)+"");
			           comboRegion.setSelectedItem( tableMoral.getValueAt(i, 2)+"");
			           phoneInput.setText( tableMoral.getValueAt(i, 3)+"");
			           emailInput.setText( tableMoral.getValueAt(i, 4)+"");
			           bankName.setText( tableMoral.getValueAt(i, 5)+"");
			           agencyName.setText( tableMoral.getValueAt(i, 6)+"");
			           ribInput.setText( tableMoral.getValueAt(i, 7)+"");
			           
			     }catch(Exception exp){
			    	 JOptionPane.showMessageDialog(null,"Something Went wrong...", "Warning", JOptionPane.WARNING_MESSAGE);
			     }
			}
		});
		tableMoral.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableMoral.setModel(new DefaultTableModel(
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
				"Registration", "FullName", "Region", "Phone", "E-mail", "Bank Name", "Agency Name", "RIB"
			}
		));
		jScrollPane2_1.setViewportView(tableMoral);
		
		JLabel backgroundLabel = new JLabel("");
		backgroundLabel.setIcon(new ImageIcon(CompanyInfo.class.getResource("/tn/poly/bahri/images/bgCompany.png")));
		backgroundLabel.setBounds(0, -20, 2050, 613);
		panel.add(backgroundLabel);
	}
	
	
	
	public void allPhyscical(JTable T) {

        try {
        	
        	Statement stmt = con.createStatement();
            String sql = "select * from companyPhysical";
            ResultSet rs = (ResultSet) stmt.executeQuery(sql);

            int i = 0, j;
            while (rs.next()) {
                j = 0; //Column
                T.setValueAt(rs.getString("registrationPhysical").toString(), i, j);
                j++;
                T.setValueAt(rs.getString("fullNamePhysical").toString(), i, j);
                j++;
                T.setValueAt(rs.getString("regionPhysical").toString(), i, j);
                j++;
                T.setValueAt(rs.getInt("phonePhysical"), i, j);
                j++;
                T.setValueAt(rs.getString("emailPhysical").toString(), i, j);
                j++;
                T.setValueAt(rs.getString("bankNamePhysical").toString(), i, j);
                j++;
                T.setValueAt(rs.getString("agencyNamePhysical").toString(), i, j);
                j++;
                T.setValueAt(rs.getString("ribPhysical").toString(), i, j);
                j++;
                
                i++; //Rows
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Opps, Can't retreieve Data !!");
        }
    }

	public void allMoral(JTable T) {

	    try {
	    	
        	Statement stmt = con.createStatement();
            String sql = "select * from companyMoral";
            ResultSet rs = (ResultSet) stmt.executeQuery(sql);
            int i = 0, j;
            while (rs.next()) {
                j = 0;
                T.setValueAt(rs.getString("registrationMoral").toString(), i, j);
                j++;
                T.setValueAt(rs.getString("fullNameMoral").toString(), i, j);
                j++;
                T.setValueAt(rs.getString("regionMoral").toString(), i, j);
                j++;
                T.setValueAt(rs.getInt("phoneMoral"), i, j);
                j++;
                T.setValueAt(rs.getString("emailMoral").toString(), i, j);
                j++;
                T.setValueAt(rs.getString("bankNameMoral").toString(), i, j);
                j++;
                T.setValueAt(rs.getString("agencyNameMoral").toString(), i, j);
                j++;
                T.setValueAt(rs.getString("ribMoral").toString(), i, j);
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

	public void FindPhysicalCompany(JTable T, String word, String searchName) {
	    try {
	    	ResultSet rs;
	        stmt = con.createStatement();
	        String sql = "SELECT * FROM companyPhysical WHERE " + word + " like '" + searchName + "%'  ";
	        rs = stmt.executeQuery(sql);
	        int i = 0, j;
	        while (rs.next()) {
	        	j=0;
	            T.setValueAt(rs.getString("registrationPhysical").toString(), i, j);
	            j++;
	            T.setValueAt(rs.getString("fullNamePhysical").toString(), i, j);
	            j++;
	            T.setValueAt(rs.getString("regionPhysical").toString(), i, j);
	            j++;
	            T.setValueAt(rs.getInt("phonePhysical"), i, j);
	            j++;
	            T.setValueAt(rs.getString("emailPhysical").toString(), i, j);
	            j++;
	            T.setValueAt(rs.getString("bankNamePhysical").toString(), i, j);
	            j++;
	            T.setValueAt(rs.getString("agencyNamePhysical").toString(), i, j);
	            j++;
	            T.setValueAt(rs.getString("ribPhysical").toString(), i, j);
	            j++;
	                       
	            i++;
	        }
	
	    } catch (SQLException e) {
	    	
	    	System.out.println(e.getMessage());
	    	
	    }
	}
	
	public void FindMoralCompany(JTable T, String word) {
	    try {
	    	ResultSet rs;
	        stmt = con.createStatement();
	         
	        String sql = "SELECT * FROM companyMoral WHERE fullNameMoral like '" + word + "%'";
	        rs = stmt.executeQuery(sql);
	        int i = 0, j;
	        while (rs.next()) {
	            j = 0;
	            T.setValueAt(rs.getString("registrationMoral").toString(), i, j);
	            j++;
	            T.setValueAt(rs.getString("fullNameMoral").toString(), i, j);
	            j++;
	            T.setValueAt(rs.getString("regionMoral").toString(), i, j);
	            j++;
	            T.setValueAt(rs.getInt("phoneMoral"), i, j);
	            j++;
	            T.setValueAt(rs.getString("emailMoral").toString(), i, j);
	            j++;
	            T.setValueAt(rs.getString("bankNameMoral").toString(), i, j);
	            j++;
	            T.setValueAt(rs.getString("agencyNameMoral").toString(), i, j);
	            j++;
	            T.setValueAt(rs.getString("ribMoral").toString(), i, j);
	            j++;
	            
	            i++;
	        }
	
	    } catch (SQLException e) {
	    	System.out.println(e.getMessage());
	
	    }
	}
	
	
}
