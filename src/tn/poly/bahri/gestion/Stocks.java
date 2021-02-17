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
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Stocks extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Connection con = ConnectDB.getConnexion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stocks frame = new Stocks();
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
	public Stocks() {
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				allProducts(table);
				
			}
		});
		setTitle("Stocks");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1000, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		Panel panel = new Panel();
		panel.setLayout(null);
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Stocks.class.getResource("/tn/poly/bahri/images/Warehouse.png")));
		lblNewLabel.setBounds(453, 12, 102, 102);
		panel.add(lblNewLabel);
		
		JButton btnRefrech = new JButton("");
		btnRefrech.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				allProducts(table);
			}
		});
		btnRefrech.setIcon(new ImageIcon(Stocks.class.getResource("/tn/poly/bahri/images/update.png")));
		btnRefrech.setBackground(Color.WHITE);
		btnRefrech.setBounds(331, 493, 62, 32);
		panel.add(btnRefrech);
		
		JButton btnAdd = new JButton("");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		        MessageFormat Header = new MessageFormat("Warehouse" + " " + showDateTime() + "");
		        MessageFormat footer = new MessageFormat("page{0,number,integer}");
		        try {

		            table.print(JTable.PrintMode.FIT_WIDTH, Header, footer);

		        } catch (java.awt.print.PrinterException ex) {
		            System.err.format("Ops, Couldn't print !", ex.getMessage());
		        }
			}
		});
		btnAdd.setIcon(new ImageIcon(Stocks.class.getResource("/tn/poly/bahri/images/savePrint.png")));
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setBounds(493, 490, 62, 35);
		panel.add(btnAdd);
		
		JScrollPane jScrollPane2 = new JScrollPane();
		jScrollPane2.setBounds(53, 149, 908, 309);
		panel.add(jScrollPane2);
		
		table = new JTable();
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
			},
			new String[] {
				"Ref", "Designation", "Category", "Unity", "Provider Name", "Quantity", "Sold", "In Stock", "Net Price", "Price + TVA"
			}
		));
		jScrollPane2.setViewportView(table);
		
		JLabel backgroundLabel = new JLabel("");
		backgroundLabel.setIcon(new ImageIcon(Stocks.class.getResource("/tn/poly/bahri/images/StocksBackground.png")));
		backgroundLabel.setBounds(0, -20, 2050, 613);
		panel.add(backgroundLabel);
	}

    public String showDateTime() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
        return sdf.format(d);

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
}
