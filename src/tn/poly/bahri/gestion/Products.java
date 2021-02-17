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
import javax.swing.Timer;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSpinner;

import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.io.FileOutputStream;


import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Products extends JFrame {

	private JPanel contentPane;
	private JTextField idInput;
	private JTextField categoryInput;
	private JTextField qteInput;
	private JTextField inStockInput;
	private JTextField soldInput;
	private JTextField SearchField;
	private JTable table;
	
	private JComboBox<String> comboProvider = new JComboBox<String>();
	private JComboBox<String> comboUnity = new JComboBox<String>();
	private JComboBox<String> comboDesg = new JComboBox<String>();
	private JComboBox<String> comboSearch = new JComboBox<String>();
	

	private Connection con = ConnectDB.getConnexion();
	private String query;
	private Statement stmt;
	private ResultSet rs;
	private JTextField tvaInput;
	private JTextField netPriceInput;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Products frame = new Products();
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
	public Products() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
											
				FindFullNameProvider(comboProvider);
				allProducts(table);
				
			}
		});
		setTitle("Products");
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
		lblNewLabel.setIcon(new ImageIcon(Products.class.getResource("/tn/poly/bahri/images/buyMe.png")));
		lblNewLabel.setBounds(31, 12, 102, 102);
		panel.add(lblNewLabel);
		
		JLabel matriculeLabel = new JLabel("Reference");
		matriculeLabel.setForeground(Color.WHITE);
		matriculeLabel.setFont(new Font("FreeSerif", Font.BOLD, 16));
		matriculeLabel.setBounds(216, 44, 121, 15);
		panel.add(matriculeLabel);
		
		idInput = new JTextField();
		idInput.setColumns(10);
		idInput.setBounds(184, 86, 155, 32);
		panel.add(idInput);
		
		JLabel raisonSocialeLabel = new JLabel("Designation");
		raisonSocialeLabel.setForeground(Color.WHITE);
		raisonSocialeLabel.setFont(new Font("FreeSerif", Font.BOLD, 16));
		raisonSocialeLabel.setBounds(422, 44, 102, 15);
		panel.add(raisonSocialeLabel);
		comboDesg.setModel(new DefaultComboBoxModel(new String[] {"Boissons", "Viandes", "Legumes", "Cereales", "Cake"}));
		
		comboDesg.setBounds(389, 85, 155, 32);
		panel.add(comboDesg);
		
		JLabel lblEmail = new JLabel("Unity");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("FreeSerif", Font.BOLD, 16));
		lblEmail.setBounds(836, 44, 54, 15);
		panel.add(lblEmail);
		
		categoryInput = new JTextField();
		categoryInput.setColumns(10);
		categoryInput.setBounds(620, 86, 155, 32);
		panel.add(categoryInput);
		
		JLabel lblPhoneN = new JLabel("Category");
		lblPhoneN.setForeground(Color.WHITE);
		lblPhoneN.setFont(new Font("FreeSerif", Font.BOLD, 16));
		lblPhoneN.setBounds(641, 44, 78, 15);
		panel.add(lblPhoneN);
		
		qteInput = new JTextField();
		qteInput.setColumns(10);
		qteInput.setBounds(1161, 82, 78, 32);
		panel.add(qteInput);
		
		JLabel lblBank = new JLabel("Quantity");
		lblBank.setForeground(Color.WHITE);
		lblBank.setFont(new Font("FreeSerif", Font.BOLD, 16));
		lblBank.setBounds(1161, 44, 78, 15);
		panel.add(lblBank);
		
		inStockInput = new JTextField();
		inStockInput.setEditable(false);
		inStockInput.setColumns(10);
		inStockInput.setBounds(389, 170, 155, 32);
		panel.add(inStockInput);
		
		JLabel lblAgency = new JLabel("In Stock");
		lblAgency.setForeground(Color.WHITE);
		lblAgency.setFont(new Font("FreeSerif", Font.BOLD, 16));
		lblAgency.setBounds(432, 143, 102, 15);
		panel.add(lblAgency);
		
		JLabel lblRib = new JLabel("Sold");
		lblRib.setForeground(Color.WHITE);
		lblRib.setFont(new Font("FreeSerif", Font.BOLD, 16));
		lblRib.setBounds(229, 143, 78, 15);
		panel.add(lblRib);
		
		soldInput = new JTextField();
		soldInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				 try {
		        	 int k = (int) Integer.parseInt(soldInput.getText());
		        	 int h = Integer.parseInt(qteInput.getText().toString()) - k;
		        	 inStockInput.setText(h + "");
		        	 
		        } catch (Exception ex) {
					JOptionPane.showMessageDialog(null,"Something Went wrong...", "Warning", JOptionPane.WARNING_MESSAGE);
		          }
				 
			}
		});
		soldInput.setColumns(50);
		soldInput.setBounds(184, 170, 155, 32);
	
		panel.add(soldInput);
		
		JButton btnRefrech = new JButton("");
		btnRefrech.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
            	allProducts(table);		
			}
		});
		btnRefrech.setIcon(new ImageIcon(Products.class.getResource("/tn/poly/bahri/images/update.png")));
		btnRefrech.setBackground(Color.WHITE);
		btnRefrech.setBounds(1009, 253, 91, 35);
		panel.add(btnRefrech);
		
		JButton btnAdd = new JButton("");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				
				
				if (idInput.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"Please Fill the Reference.", "Empty Field", JOptionPane.WARNING_MESSAGE);
				}
				else if (comboDesg.getSelectedItem()==null) {
					JOptionPane.showMessageDialog(null, "Please choose Designation.", "Empty Field", JOptionPane.WARNING_MESSAGE);
				}
				else if (categoryInput.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please Choose a category.", "Empty Field", JOptionPane.WARNING_MESSAGE);
				}
				
				else if (comboUnity.getSelectedItem()==null) {
					JOptionPane.showMessageDialog(null, "Please choose a Unity.", "Empty Field", JOptionPane.WARNING_MESSAGE);
				}	
				else if (comboProvider.getSelectedItem()==null) {
					JOptionPane.showMessageDialog(null, "Please choose a Provider.", "Empty Field", JOptionPane.WARNING_MESSAGE);
				}
				else if (qteInput.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter the Quantity.", "Empty Field", JOptionPane.WARNING_MESSAGE);
				
				}
				else if (soldInput.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please Sold Quantity.", "Empty Field", JOptionPane.WARNING_MESSAGE);
				
				}
				else if (inStockInput.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please Fill How much quantity left.", "Empty Field", JOptionPane.WARNING_MESSAGE);
				
				}
				else if (netPriceInput.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please Fill the price.", "Empty Field", JOptionPane.WARNING_MESSAGE);
				
				}
				else {
					try {
						
		                query="insert into Products values ('"+idInput.getText()+"','"+comboDesg.getSelectedItem()+"','"+categoryInput.getText()+"','"+comboUnity.getSelectedItem()+"','"+comboProvider.getSelectedItem()+"','"+qteInput.getText()+"','"+soldInput.getText()+"', '"+inStockInput.getText()+"','"+netPriceInput.getText() +"', '"+tvaInput.getText()+"');";                                                       
		                stmt=con.createStatement();
		            	stmt.executeUpdate(query);
		            	
		            	JOptionPane.showMessageDialog(null, "Product :'"+idInput.getText()+"' Added Successfully");
		            	
		            	idInput.setText("");
		            	comboDesg.setSelectedIndex(0);
		            	categoryInput.setText("");
		            	comboUnity.setSelectedIndex(0);
		            	comboProvider.setSelectedIndex(0);
		            	qteInput.setText("");
		            	soldInput.setText("");
		            	inStockInput.setText("");
		            	netPriceInput.setText("");
		            	tvaInput.setText("");
	
		            	allProducts(table);
		            	
					}catch(SQLException exp) {
	//					if (exp.getErrorCode()==1062) {
	//						JOptionPane.showMessageDialog(null, "We already have you in our DataBase","Duplication Error",JOptionPane.ERROR_MESSAGE);
	//					}
	//					else {
	//						JOptionPane.showMessageDialog(null,exp.getMessage());
	//					}
					}
			}
			}
		});
		btnAdd.setIcon(new ImageIcon(Products.class.getResource("/tn/poly/bahri/images/addIcon.png")));
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBounds(515, 253, 102, 35);
		panel.add(btnAdd);
		
		JButton btnUpdate = new JButton("");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					stmt = con.createStatement();																																																																																					
					String sql= "UPDATE Products SET refStock = '"+idInput.getText()+"',designation= '"+comboDesg.getSelectedItem()+"', category= '"+categoryInput.getText()+"', unite='"+comboUnity.getSelectedItem()+"',ProviderName='"+comboProvider.getSelectedItem()+"', quantite='"+qteInput.getText()+"',quantiteVente='"+soldInput.getText()+"',quantiteReste='"+inStockInput.getText()+"',prixUnitaire='"+netPriceInput.getText()+"',prixVente='"+tvaInput.getText()+"' WHERE refStock='"+idInput.getText()+"';";
					stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog (null, "Product Updated Successfully", "Updated", JOptionPane.INFORMATION_MESSAGE);
			

	            	idInput.setText("");
	            	comboDesg.setSelectedIndex(-1);
	            	categoryInput.setText("");
	            	comboUnity.setSelectedIndex(-1);
	            	comboProvider.setSelectedIndex(-1);
	            	qteInput.setText("");
	            	soldInput.setText("");
	            	inStockInput.setText("");
	            	netPriceInput.setText("");
	            	tvaInput.setText("");

	            	allProducts(table);
	            	
					
				}catch (SQLException ex) {
					JOptionPane.showMessageDialog(null,"Something Went wrong...", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnUpdate.setIcon(new ImageIcon(Products.class.getResource("/tn/poly/bahri/images/refresh.png")));
		btnUpdate.setBounds(690, 253, 91, 35);
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					if(JOptionPane.showConfirmDialog(null, "Are you sure you want to delete ?",
							"Delete",
							JOptionPane.YES_NO_OPTION)==JOptionPane.OK_OPTION){
						
						String sql = "DELETE FROM Products WHERE refStock='"+idInput.getText()+"';";
						stmt=con.createStatement();
						stmt.executeUpdate(sql);
						JOptionPane.showMessageDialog (null, "Product Successfully Deleted ", "Deleted", JOptionPane.INFORMATION_MESSAGE);

					}
				}
				catch(Exception e) {
					JOptionPane.showMessageDialog(null,"Something Went wrong...", "Warning", JOptionPane.WARNING_MESSAGE);

				}
				allProducts(table);	
			}
		});
		btnDelete.setIcon(new ImageIcon(Products.class.getResource("/tn/poly/bahri/images/delete.png")));
		btnDelete.setBounds(850, 256, 91, 32);
		panel.add(btnDelete);
		
		JButton btnSearch = new JButton("");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
		        try {
		        	String sql;  
		        
	                if(comboSearch.getSelectedItem().equals("Provider Name")){
			               
						DeleteAllElementTab(table, 9,40);
						FindProductByProvider(table, SearchField.getText());	
		            }

		        } catch (Exception e) {
		        	//System.out.println(e.getMessage());
		        	JOptionPane.showMessageDialog(null,"Something Went wrong...", "Warning", JOptionPane.WARNING_MESSAGE);
		        }
				
			}
		});
		btnSearch.setIcon(new ImageIcon(Products.class.getResource("/tn/poly/bahri/images/search.png")));
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setBounds(475, 332, 85, 32);
		panel.add(btnSearch);
		
		SearchField = new JTextField();
		SearchField.setColumns(10);
		SearchField.setBounds(347, 332, 117, 32);
		panel.add(SearchField);
		
		JScrollPane jScrollPane2 = new JScrollPane();
		jScrollPane2.setBounds(177, 383, 1062, 188);
		panel.add(jScrollPane2);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
		      int i;
		        try {
		        	
		            i = table.getSelectedRow();
		            idInput.setText(table.getValueAt(i, 0) + "");
		            comboDesg.setSelectedItem(table.getValueAt(i, 1) + "");
		            categoryInput.setText(table.getValueAt(i, 2) + "");
		            comboUnity.setSelectedItem(table.getValueAt(i, 3) + "");

		            comboProvider.setSelectedItem(table.getValueAt(i, 4) + "");
		            qteInput.setText(table.getValueAt(i, 5) + "");
		            soldInput.setText(table.getValueAt(i, 6) + "");
		            inStockInput.setText(table.getValueAt(i, 7) + "");
		            	            		           
		            netPriceInput.setText(table.getValueAt(i, 8) + "");
		            tvaInput.setText(table.getValueAt(i,9) + "");
		            
		        } catch (Exception ex) {
					JOptionPane.showMessageDialog(null,"Something Went wrong...", "Warning", JOptionPane.WARNING_MESSAGE);

		        }
			
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Reference", "Designation", "Category", "Unity", "Provider Name", "Quantity", "Sold", "In Stock", "Net Price", "Price + TVA"
			}
		));
		jScrollPane2.setViewportView(table);
		
		comboUnity.setModel(new DefaultComboBoxModel<>(new String[] {"Kg", "L", "Piece"}));
		comboUnity.setBounds(818, 84, 85, 32);
		panel.add(comboUnity);
		
		comboProvider.setBounds(954, 85, 155, 32);
		panel.add(comboProvider);
		
		JLabel lblProviderName = new JLabel("Provider Name");
		lblProviderName.setForeground(Color.WHITE);
		lblProviderName.setFont(new Font("FreeSerif", Font.BOLD, 16));
		lblProviderName.setBounds(979, 44, 110, 15);
		panel.add(lblProviderName);
		comboSearch.setModel(new DefaultComboBoxModel(new String[] {"Provider Name"}));
		

		comboSearch.setBounds(184, 332, 155, 32);
		panel.add(comboSearch);
		
		JButton btnSearch_1 = new JButton("");
		btnSearch_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
		        MessageFormat Header = new MessageFormat("All the Products" + " " + showDateTime() + "");
		        MessageFormat footer = new MessageFormat("page{0,number,integer}");
		        try {

		            table.print(JTable.PrintMode.FIT_WIDTH, Header, footer);

		        } catch (java.awt.print.PrinterException ex) {
		            System.err.format("Ops, Couldn't print !", ex.getMessage());
		        }
				
//				    Document document = new Document(PageSize.A4.rotate());
//				    try {
//				      PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("products.pdf"));
//
//				      document.open();
//				      PdfContentByte cb = writer.getDirectContent();
//
//				      cb.saveState();
//				      Graphics2D g2 = cb.createGraphicsShapes(500, 500);
//
//				      Shape oldClip = g2.getClip();
//				      g2.clipRect(0, 0, 500, 500);
//
//				      table.print(g2);
//				      g2.setClip(oldClip);
//
//				      g2.setColor(Color.BLACK);
//				      
//				      g2.dispose();
//				      cb.restoreState();
//				    } catch (Exception e) {
//				      System.err.println(e.getMessage());
//				    }
//				    document.close();
			}
		});
		btnSearch_1.setIcon(new ImageIcon(Products.class.getResource("/tn/poly/bahri/images/savePrint.png")));
		btnSearch_1.setForeground(Color.WHITE);
		btnSearch_1.setBounds(347, 253, 85, 35);
		panel.add(btnSearch_1);
		
		JLabel lblRib_1 = new JLabel("Net Price");
		lblRib_1.setForeground(Color.WHITE);
		lblRib_1.setFont(new Font("FreeSerif", Font.BOLD, 16));
		lblRib_1.setBounds(652, 143, 78, 15);
		panel.add(lblRib_1);
		
		JLabel lblAgency_1 = new JLabel("Price(including TVA)");
		lblAgency_1.setForeground(Color.WHITE);
		lblAgency_1.setFont(new Font("FreeSerif", Font.BOLD, 16));
		lblAgency_1.setBounds(1010, 143, 167, 15);
		panel.add(lblAgency_1);
		
		tvaInput = new JTextField();
		tvaInput.setEditable(false);
		tvaInput.setColumns(10);
		tvaInput.setBounds(991, 170, 167, 32);
		panel.add(tvaInput);
		
		netPriceInput = new JTextField();
		netPriceInput.setColumns(50);
		netPriceInput.setBounds(620, 170, 155, 32);
		panel.add(netPriceInput);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				 try {
		        	 int k = (int) Float.parseFloat(netPriceInput.getText());
		        	 int h = Integer.parseInt(qteInput.getText().toString()) * k;
		        	 tvaInput.setText(h + "");
		        	 
		        } catch (Exception e) {
					JOptionPane.showMessageDialog(null,"Something Went wrong...", "Warning", JOptionPane.WARNING_MESSAGE);
		          }
			}
		});
		btnCalculate.setForeground(Color.BLACK);
		btnCalculate.setBounds(836, 173, 117, 25);
		panel.add(btnCalculate);
		
		JLabel backgroundLabel = new JLabel("");
		backgroundLabel.setIcon(new ImageIcon(Products.class.getResource("/tn/poly/bahri/images/StocksBackground.png")));
		backgroundLabel.setBounds(0, -20, 2050, 613);
		panel.add(backgroundLabel);
	}
	
	
	
    public String showDateTime() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
        return sdf.format(d);

    }
	
	private void FindProductByDesignation(JTable T, String text) {
		  try {
	        	ResultSet rs;
	            stmt = con.createStatement();
	             
	            String sql = "SELECT * FROM Products WHERE designation like '" + text + "%'";
	            rs = stmt.executeQuery(sql);
	            int i = 0, j;
	            while (rs.next()) {
	                j = 0;
	                T.setValueAt(rs.getInt("refStock"), i, j);
	                j++;
	                T.setValueAt(rs.getString("designation").toString(), i, j);
	                j++;
	                T.setValueAt(rs.getString("category").toString(), i, j);
	                j++;
	                T.setValueAt(rs.getInt("unite"), i, j);
	                j++;
	                T.setValueAt(rs.getString("ProviderName").toString(), i, j);
	                j++;
	                T.setValueAt(rs.getInt("quantite"), i, j);
	                j++;
	                T.setValueAt(rs.getInt("quantiteVente"), i, j);
	                j++;
	                T.setValueAt(rs.getInt("quantiteReste"), i, j);
	                j++;
	                T.setValueAt(rs.getFloat("prixUnitaire"), i, j);
	                j++;
	                T.setValueAt(rs.getFloat("prixVente"), i, j);
	                j++;
	                
	                i++;
	            }

	        } catch (SQLException e) {
	        	System.out.println(e.getMessage());

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
    
    
    public void FindProductByProvider(JTable T, String word) {
        try {
        	ResultSet rs;
            stmt = con.createStatement();
             
            String sql = "SELECT * FROM Products WHERE ProviderName like '" + word + "%'";
            rs = stmt.executeQuery(sql);
            int i = 0, j;
            while (rs.next()) {
                j = 0; 
                T.setValueAt(rs.getInt("refStock"), i, j);
                j++;
                T.setValueAt(rs.getString("designation").toString(), i, j);
                j++;
                T.setValueAt(rs.getString("category").toString(), i, j);
                j++;
                T.setValueAt(rs.getString("unite").toString(), i, j);
                j++;
                T.setValueAt(rs.getString("ProviderName").toString(), i, j);
                j++;
                T.setValueAt(rs.getInt("quantite"), i, j);
                j++;
                T.setValueAt(rs.getInt("quantiteVente"), i, j);
                j++;
                T.setValueAt(rs.getInt("quantiteReste"), i, j);
                j++;
                T.setValueAt(rs.getFloat("prixUnitaire"), i, j);
                j++;
                T.setValueAt(rs.getFloat("prixVente"), i, j);
                j++;
                
                i++;
            }

        } catch (SQLException e) {
        	System.out.println(e.getMessage());

        }
    }
	
	public void allProducts(JTable T) {
        try {
        	
        	Statement stmt = con.createStatement();
            String sql = "select * from Products";
            ResultSet rs = (ResultSet) stmt.executeQuery(sql);

            int i = 0, j;
            while (rs.next()) {
                j = 0; 
                T.setValueAt(rs.getInt("refStock"), i, j);
                j++;
                T.setValueAt(rs.getString("designation").toString(), i, j);
                j++;
                T.setValueAt(rs.getString("category").toString(), i, j);
                j++;
                T.setValueAt(rs.getString("unite").toString(), i, j);
                j++;
                T.setValueAt(rs.getString("ProviderName").toString(), i, j);
                j++;
                T.setValueAt(rs.getInt("quantite"), i, j);
                j++;
                T.setValueAt(rs.getInt("quantiteVente"), i, j);
                j++;
                T.setValueAt(rs.getInt("quantiteReste"), i, j);
                j++;
                T.setValueAt(rs.getFloat("prixUnitaire"), i, j);
                j++;
                T.setValueAt(rs.getFloat("prixVente"), i, j);
                j++;
                
                i++;
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Opps, Can't retreieve Data !!");
        }
    }
	
	
    ArrayList<String> Replacing(ArrayList<String> ar) {
        ArrayList<String> rpl = new ArrayList<>();
        int K = 0;
        boolean test;
        while (K < ar.size()) {
            test = true;
            for (int i = 0; i < rpl.size(); i++) {
                if (ar.get(K).equals(rpl.get(i))) {
                    test = false;
                }
            }
            if (test) {
                rpl.add(ar.get(K));
            }
            K++;
        }

        return rpl;
    }
	
	
    public void FindFullNameProvider(JComboBox<String> combo) {
        try {
            stmt = con.createStatement();
            String sql = "select fullName from provider";
            rs =  stmt.executeQuery(sql);
            ArrayList<String> ar = new ArrayList<>();
            
            ArrayList<String> rplacing = new ArrayList<>();

            while (rs.next()) {

                ar.add(rs.getString("fullName").toString());
            }
            
            rplacing = Replacing(ar);
            for (int i = 0; i < rplacing.size(); i++) {
                combo.addItem(rplacing.get(i));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex + " Something went wrong");
        }

    }
}
