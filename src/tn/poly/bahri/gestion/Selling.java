package tn.poly.bahri.gestion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import tn.poly.bahri.models.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class Selling extends JFrame {

	private JPanel contentPane;
	private JTextField idProduct;
	private JTextField qteInput;
	private JTable tableProducts;
	private JTextField deleteAchat;
	private JLabel lblCost = new JLabel("");

	JComboBox<String> comboClient = new JComboBox<String>();
	ArrayList<String> DES = new ArrayList<>();
    
	private Connection con = ConnectDB.getConnexion();
	private Statement stmt;
	private ResultSet rs;
	private JTable tableTicket;
	
	int count;
    DefaultTableModel mo1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Selling frame = new Selling();
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
	public Selling() {
		setResizable(false);
		setTitle("BUY");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
			      DESIGNATION=new ArrayList<>();
			      PRIX=new ArrayList<>();
			      QTE=new ArrayList<>();
			      FindFullNameClient(comboClient);
			      allProducts(tableProducts);
		          AllVente(tableVente);

			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1350, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Panel panel = new Panel();
		panel.setLayout(null);
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel matriculeLabel = new JLabel("Products");
		matriculeLabel.setForeground(new Color(0, 255, 0));
		matriculeLabel.setFont(new Font("FreeSerif", Font.BOLD, 16));
		matriculeLabel.setBounds(39, 44, 74, 15);
		panel.add(matriculeLabel);
		
		idProduct = new JTextField();
		idProduct.setEditable(false);
		idProduct.setColumns(10);
		idProduct.setBounds(131, 35, 155, 32);
		panel.add(idProduct);
		
		JButton btnAddClient = new JButton("");
		btnAddClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ClientProvider clientProvider= new ClientProvider();
				clientProvider.setVisible(true);				
			}
		});
		btnAddClient.setIcon(new ImageIcon(Selling.class.getResource("/tn/poly/bahri/images/addIcon.png")));
		btnAddClient.setForeground(Color.WHITE);
		btnAddClient.setBounds(164, 411, 50, 32);
		panel.add(btnAddClient);
		
		JScrollPane jScrollPane2 = new JScrollPane();
		jScrollPane2.setBounds(226, 369, 756, 188);
		panel.add(jScrollPane2);
		
		tableVente = new JTable();
		tableVente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int I;
		        try {
		        	
		            I=tableVente.getSelectedRow();
		            deleteAchat.setText(tableVente.getValueAt(I, 0)+"");
		         
		         } catch (Exception ex) {
		        	 JOptionPane.showMessageDialog(null,"Oops; Something went wrong"," Error", JOptionPane.ERROR_MESSAGE);
		         }
			}
		});
		tableVente.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Client Name", "Cart", "Price", "Date"
			}
		));
		jScrollPane2.setViewportView(tableVente);
		
		JLabel lblProviderName = new JLabel("Ticket for Things Bought");
		lblProviderName.setForeground(new Color(50, 205, 50));
		lblProviderName.setFont(new Font("FreeSerif", Font.BOLD, 16));
		lblProviderName.setBounds(780, 78, 181, 15);
		panel.add(lblProviderName);
		
		comboClient.setBounds(12, 411, 141, 32);
		panel.add(comboClient);
		
		JButton btnDelete2 = new JButton("");
		btnDelete2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
			           if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete ?",
			        		   									"DELETE",
			        		   									JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION){
			        	   DeleteVente(Integer.parseInt(deleteAchat.getText()));
			        	   AllVente(tableVente);
			           }
			        
			        } catch (Exception e) {
			    		JOptionPane.showMessageDialog(null, "Oops, Something went wrong","Error", JOptionPane.ERROR_MESSAGE); 
			        }
				
			}
		});
		btnDelete2.setIcon(new ImageIcon(Selling.class.getResource("/tn/poly/bahri/images/delete.png")));
		btnDelete2.setBounds(1140, 405, 91, 35);
		panel.add(btnDelete2);
		
		JButton btnSave = new JButton("");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Vente v=new Vente(0,
									comboClient.getSelectedItem()+"",
									details,
									Float.parseFloat(lblCost.getText()),
									showDateTime()+"");	
				
	        	insertVente(v);
	        	AllVente(tableVente);
				
			}
		});
		btnSave.setIcon(new ImageIcon(Selling.class.getResource("/tn/poly/bahri/images/saveMe.png")));
		btnSave.setBounds(50, 472, 91, 35);
		panel.add(btnSave);
		
		JButton btnPrint2 = new JButton("");
		btnPrint2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		         MessageFormat Header = new MessageFormat("SOLD" + " " + showDateTime() + "");
		         MessageFormat footer = new MessageFormat("page{0,number,integer}");
		        try {
		        	tableVente.print(JTable.PrintMode.FIT_WIDTH, Header, footer);
		        } catch (java.awt.print.PrinterException ex) {
		            System.err.format("Opps, Seems like you can't print.", ex.getMessage());
		        }
			}
		});
		btnPrint2.setIcon(new ImageIcon(Selling.class.getResource("/tn/poly/bahri/images/savePrint.png")));
		btnPrint2.setBounds(1140, 447, 91, 35);
		panel.add(btnPrint2);
		
		JScrollPane jScrollPane2_1 = new JScrollPane();
		jScrollPane2_1.setBounds(12, 98, 529, 188);
		panel.add(jScrollPane2_1);
		
		tableProducts = new JTable();
		tableProducts.setFont(new Font("DialogInput", Font.BOLD, 12));
		tableProducts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int count;
			    count = tableProducts.getSelectedRow();
			    idProduct.setText(tableProducts.getValueAt(count, 0) + "");
			}
		});
		tableProducts.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"Reference", "Designation", "Quantity", "Sold", "In stock", "Price + TVA"
			}
		));
		jScrollPane2_1.setViewportView(tableProducts);
		
		JScrollPane jScrollPane2_1_1 = new JScrollPane();
		jScrollPane2_1_1.setBounds(780, 98, 529, 188);
		panel.add(jScrollPane2_1_1);
		
		tableTicket = new JTable();
		tableTicket.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
		        deleteItem.setText(tableTicket.getSelectedRow()+"");
				
			}
		});
		tableTicket.setFont(new Font("DialogInput", Font.BOLD, 12));
		tableTicket.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Designation", "Price + TVA", "Quantity"
			}
		));
		jScrollPane2_1_1.setViewportView(tableTicket);
		
		JButton btnDeleteFromCart = new JButton("Empty cart");
		btnDeleteFromCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
								
		    	try{
		    		if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete all tickets ?", "DELETING", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION){
		   
		    		   mo1.setRowCount(0);
				       ii=1;
				       DESIGNATION.clear();
				       PRIX.clear();
				       QTE.clear();

				       qteInput.setText("1");
				       deleteItem.setText("");
				       lblCost.setText("0 TND");

		    		}
		}catch(Exception e){
		     JOptionPane.showMessageDialog(null,"Oops; Something went wrong"," Error", JOptionPane.ERROR_MESSAGE);
		}
				
				
			}
		});
		btnDeleteFromCart.setIcon(new ImageIcon(Selling.class.getResource("/tn/poly/bahri/images/deleteCart.png")));
		btnDeleteFromCart.setForeground(Color.RED);
		btnDeleteFromCart.setBounds(780, 34, 165, 32);
		panel.add(btnDeleteFromCart);
		
		JButton btnRefresh = new JButton("");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    allProducts(tableProducts);
				idProduct.setText("");
			}
		});
		btnRefresh.setIcon(new ImageIcon(Selling.class.getResource("/tn/poly/bahri/images/update.png")));
		btnRefresh.setForeground(Color.WHITE);
		btnRefresh.setBounds(332, 35, 74, 32);
		panel.add(btnRefresh);
		
		JLabel lblQte = new JLabel("Qte");
		lblQte.setForeground(Color.WHITE);
		lblQte.setFont(new Font("FreeSerif", Font.BOLD, 16));
		lblQte.setBounds(559, 122, 50, 15);
		panel.add(lblQte);
		
		qteInput = new JTextField();
		qteInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
			
//		        try {
//		        	int count = tableProducts.getSelectedRow();
//		        	if (Integer.parseInt( qteInput.getText())>=Integer.parseInt(tableProducts.getValueAt(count, 2))){
//		        		JOptionPane.showMessageDialog (null, "Stock won't satisfy your needs", "No more left in stock", JOptionPane.WARNING_MESSAGE);
//		        		}
//		            }
//		        catch (Exception ex) {
//		        	
//		            JOptionPane.showMessageDialog(null, ex + " Something went wrong");
//		        }
						
			}
		});
		qteInput.setFont(new Font("Dialog", Font.BOLD, 14));
		qteInput.setForeground(new Color(0, 0, 128));
		qteInput.setText("1");
		qteInput.setColumns(10);
		qteInput.setBounds(608, 113, 107, 32);
		panel.add(qteInput);
		
		JButton btnAdd1 = new JButton("");
		btnAdd1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//tableProducts
				//tableTicket
				int count = tableProducts.getSelectedRow();
				
					fillTable(
							tableProducts.getValueAt(count, 1)+"",//Desgination
							Float.parseFloat(tableProducts.getValueAt(count, 5)+""), //Price
							Integer.parseInt( qteInput.getText()),//Qte Input
							tableTicket);
							
				UpdateQteResteMinus(Integer.parseInt(idProduct.getText()),Integer.parseInt(qteInput.getText()));
				allProducts(tableProducts);
				
			}
		});
		btnAdd1.setIcon(new ImageIcon(Selling.class.getResource("/tn/poly/bahri/images/addCart.png")));
		btnAdd1.setBounds(608, 197, 91, 35);
		panel.add(btnAdd1);
		
		JButton btnDelete1 = new JButton("");
		btnDelete1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{ 
					
			        UpdateQteResteAdd(DESIGNATION.get(Integer.parseInt(deleteItem.getText())), QTE.get(Integer.parseInt(deleteItem.getText())));
				    allProducts(tableProducts);
				    
				    DESIGNATION.remove(Integer.parseInt(deleteItem.getText()));
			        PRIX.remove(Integer.parseInt(deleteItem.getText()));
			        QTE.remove(Integer.parseInt(deleteItem.getText()));
			        
			        Object row1[] = new Object[4];
			        mo1.setRowCount(0);
			        ii=DESIGNATION.size()+1;
			        int r=1;
			        for(int i=0;i<DESIGNATION.size();i++){
			        	 row1[0]=  r++;  
				         row1[1]= DESIGNATION.get(i);
				         row1[2]= PRIX.get(i);
				         row1[3]= QTE.get(i);
				         mo1.addRow(row1);
			         }
			         deleteItem.setText("");
			}catch(Exception e){
			     JOptionPane.showMessageDialog(null, "Oops, Something went wrong","Error", JOptionPane.ERROR_MESSAGE);
			}
				
			}
		});
		btnDelete1.setIcon(new ImageIcon(Selling.class.getResource("/tn/poly/bahri/images/delete.png")));
		btnDelete1.setBounds(1050, 32, 91, 35);
		panel.add(btnDelete1);
		
		JLabel lblClient = new JLabel("Client");
		lblClient.setForeground(Color.WHITE);
		lblClient.setFont(new Font("FreeSerif", Font.BOLD, 16));
		lblClient.setBounds(55, 384, 50, 15);
		panel.add(lblClient);
		
		JButton btnPrint1 = new JButton("");
		btnPrint1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MessageFormat Header = new MessageFormat("Your Ticket Is Ready: "+lblCost.getText()+ " " + showDateTime() + "");
			    MessageFormat footer = new MessageFormat("page{0,number,integer}");
		   
			    try {
		        	tableTicket.print(JTable.PrintMode.NORMAL, Header, footer);

		        } catch (java.awt.print.PrinterException ex) {
				     JOptionPane.showMessageDialog(null, "Couldn't print "," Print Error", JOptionPane.ERROR_MESSAGE);
		        }
			
			}
		});
		btnPrint1.setIcon(new ImageIcon(Selling.class.getResource("/tn/poly/bahri/images/savePrint.png")));
		btnPrint1.setBounds(1175, 32, 91, 35);
		panel.add(btnPrint1);
		
		JButton btnCost = new JButton("");
		btnCost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				FillArray(tableTicket, lblCost);

			}
		});
		btnCost.setIcon(new ImageIcon(Selling.class.getResource("/tn/poly/bahri/images/PayNow.png")));
		btnCost.setBounds(559, 311, 98, 46);
		panel.add(btnCost);
		
		lblCost.setForeground(new Color(255, 0, 0));
		lblCost.setFont(new Font("FreeSerif", Font.BOLD, 35));
		lblCost.setBounds(693, 310, 252, 32);
		panel.add(lblCost);
		
		deleteAchat = new JTextField();
		deleteAchat.setEnabled(false);
		deleteAchat.setEditable(false);
		deleteAchat.setColumns(10);
		deleteAchat.setBounds(1021, 408, 107, 32);
		panel.add(deleteAchat);
		
		deleteItem = new JTextField();
		deleteItem.setEditable(false);
		deleteItem.setColumns(10);
		deleteItem.setBounds(975, 35, 56, 32);
		panel.add(deleteItem);
		
		JLabel backgroundLabel = new JLabel("");
		backgroundLabel.setIcon(new ImageIcon(Selling.class.getResource("/tn/poly/bahri/images/StocksBackground.png")));
		backgroundLabel.setBounds(0, -20, 2050, 613);
		panel.add(backgroundLabel);
	}
	
    
    public void DeleteVente(int id) {
    	String sql = "DELETE FROM vente WHERE idVente=" + id;
    	try {
    		stmt.executeUpdate(sql);
    	} 
    	catch (SQLException ex) {
    		JOptionPane.showMessageDialog(null, "Oops, Something went wrong","Error", JOptionPane.ERROR_MESSAGE);
    	}
    }
	
	
    public void AllVente(JTable T) {

    try {
        ArrayList<Vente> list = new ArrayList<Vente>();
        stmt = con.createStatement();
        String sql = "select * from vente";
        rs = stmt.executeQuery(sql);
        
        while (rs.next()) {
        	String s="";
        	s= rs.getDate("dateVente")+""+" " +rs.getTime("dateVente")+""; 
        	
            Vente V = new Vente(
            		rs.getInt("idVente"),
            		rs.getString("nomClient"),
            		rs.getString("details"),
            		rs.getFloat("prix"),
            		s);
            
            list.add(V);
        }

        DefaultTableModel mo = (DefaultTableModel) T.getModel();
        mo.setRowCount(0);
        DecimalFormat df = new DecimalFormat("0.00");
        Object row[] = new Object[5];
        
        for (int k = 0; k < list.size(); k++) {
            row[0] = list.get(k).getIdVente();
            row[1] = list.get(k).getNomClient();
            row[2] = list.get(k).getDetails();
            row[3] = df.format(list.get(k).getPrix());
            row[4] = /*df.format(*/ list.get(k).getDateVente()/*)*/;
            
            mo.addRow(row);
        }
       
    } catch (SQLException ex) {
	     JOptionPane.showMessageDialog(null, "Oops, Something went wrong","Error", JOptionPane.ERROR_MESSAGE);
    }
}
	
	
	
    public void insertVente(Vente V) {

        try {
            stmt = con.createStatement();
            String sql1 = "INSERT INTO vente( nomClient, details ,prix, dateVente) "
            		+ "VALUES ('" + V.getNomClient() + "'," + "'" + V.getDetails() + "','" + V.getPrix()+ "','"+V.getDateVente()+"')";
            stmt.executeUpdate(sql1);
    		JOptionPane.showMessageDialog (null, "Saved Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
    		JOptionPane.showMessageDialog (null, "Something went wrong", "Error", JOptionPane.WARNING_MESSAGE);
        }

    }
	
	
	String details;

	void FillArray (JTable T,JLabel j1){
		
    	DESIGNATION.clear();
    	QTE.clear();
    	PRIX.clear();

    	details="";
    	int   temp=0;
    	float somePrix=0;
    	
    	for(int i=0;i<T.getRowCount();i++){
    		
    		DESIGNATION.add((String)T.getValueAt(i, 1));
    		QTE.add((int)T.getValueAt(i, 3));
    		PRIX.add((float)T.getValueAt(i, 2));
    		somePrix=QTE.get(i)*PRIX.get(i)+somePrix;
    		temp=temp+1;
    		details=details+"N° "+temp+" Designation : "+DESIGNATION.get(i)+" Paid : "+PRIX.get(i) +" Quantity : "+QTE.get(i)+ "\n"  ;    
    	}
    	j1.setText(somePrix+"");
 	}

	
    public String showDateTime() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
        return sdf.format(d);

    }
	
    public void UpdateQteResteAdd( String des,int qte) {
        try {		            
        	
            stmt = con.prepareStatement("jdbc:mysql://127.0.0.1:3306/salesDB");
            String sql = "select quantiteReste,quantiteVente,designation from Products where designation = '"+ des +"'  ";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
            	
                int vente = rs.getInt("quantiteVente")-qte;
                int reste = rs.getInt("quantiteReste")+qte;
                
                String sql2 = "update Products set quantiteVente='" + vente + "',quantiteReste='" + reste + "'"
                		      + "where designation='"+des+"'";
                
                stmt.executeUpdate(sql2);
                
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Something went wrong");
        }
    }
	
   public void UpdateQteResteMinus( int id,int qte) {
        try {		
        	
            String sql = "SELECT quantite,quantiteVente FROM Products WHERE refStock = " + id + "  ";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                int totale = rs.getInt("quantite");
                int vente = rs.getInt("quantiteVente")+qte;
                
                String sql2 = "Update Products set quantiteVente='" + vente + "',quantiteReste='" 
                        + (totale-vente) + "'"
                    + "where refStock="+id;

            stmt.executeUpdate(sql2);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Something went wrong");
        }
    }
   //End of Updating Quantity


	//Filling Table of Ticket
	    int ii=1;//Id 
	    ArrayList<String> DESIGNATION;
	    ArrayList<Float> PRIX;
	    ArrayList<Integer> QTE;
	    private JTextField deleteItem;
	    private JTable tableVente;
	    void fillTable(String des,float prix,int qte,JTable t1){
	    	
	    	try {
	    		
	    		mo1 = (DefaultTableModel) t1.getModel();
	    		Object  row1[] = new Object[4];
	    		DESIGNATION.add(des);
	    		PRIX.add(prix);
	    		QTE.add(qte);

	    		row1[0]= ii++;  
	    		row1[1]= des;
	    		row1[2]= prix;
	    		row1[3]= qte;
	    		mo1.addRow(row1);

	    	}catch(Exception ex) {
	    		System.out.println(ex.getMessage());
	    	}
	}//END Of Filling Table of Ticket
		
	
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
	
    public void FindFullNameClient(JComboBox<String> combo) {
        try {
            stmt = con.createStatement();
            String sql = "select fullNameClient from client";
            rs =  stmt.executeQuery(sql);
            ArrayList<String> ar = new ArrayList<>();
            ArrayList<String> rplacing = new ArrayList<>();

            while (rs.next()) {

                ar.add(rs.getString("fullNameClient").toString());
            }
            rplacing = Replacing(ar);
            for (int i = 0; i < rplacing.size(); i++) {
                combo.addItem(rplacing.get(i));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Something went wrong");
        }

    }
 
    
    public void allProducts(JTable T) {

        try {
        	
            ArrayList<tn.poly.bahri.models.Stock> list = new ArrayList<tn.poly.bahri.models.Stock>();
            stmt = con.createStatement();
            String sql = "select refStock,designation,quantite,"
                    + " quantiteVente,quantiteReste,prixVente from Products";
            rs = (ResultSet) stmt.executeQuery(sql);
            DES.clear();
            while (rs.next()) {
                Stock ST = new Stock(rs.getInt("refStock"), rs.getString("designation"),
                        rs.getInt("quantite"),
                        rs.getInt("quantiteVente"), rs.getInt("quantiteReste"),
                        rs.getFloat("prixVente") );
            	
                list.add(ST);
            }

            DefaultTableModel mo = (DefaultTableModel) T.getModel();
            mo.setRowCount(0);
            DecimalFormat df = new DecimalFormat("0.00");
            Object row[] = new Object[6];
            for (int k = 0; k < list.size(); k++) {
                row[0] = list.get(k).getRefStock();
                row[1] = list.get(k).getDesignation();

                row[2] = df.format(list.get(k).getQuantite());
                row[3] = list.get(k).getQuantiteVente();
                row[4] = list.get(k).getQuantiteReste();

                row[5] = list.get(k).getPrixVente();

                mo.addRow(row);
            }
            list.clear();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Opps, Can't retreieve Data !!");
        }
    }






















    /* 
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
                   T.setValueAt(rs.getInt("quantite"), i, j);
                   j++;
                   T.setValueAt(rs.getInt("quantiteVente"), i, j);
                   j++;
                   T.setValueAt(rs.getInt("quantiteReste"), i, j);
                   j++;
                   T.setValueAt(rs.getFloat("prixVente"), i, j);
                   j++;
                   
                   i++;
               }

           } catch (SQLException ex) {
               JOptionPane.showMessageDialog(null, ex + " Opps, Can't retreieve Data !!");
           }
       }	

   */


}
