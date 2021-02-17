package tn.poly.bahri.gestion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Label;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Date;
import javax.swing.Timer;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainScreen extends JFrame {

	private JPanel contentPane;

	Panel panel = new Panel();
	JLabel lblNewLabel = new JLabel("WELCOME");
	JLabel BackgroundLabel = new JLabel("");
	private final JLabel lblEntreprise = new JLabel("Company");
	private final JLabel lblClientFournisseur = new JLabel("Client / Fournisseur");
	private final JLabel lblStocks = new JLabel("Stocks");
	private final JLabel lblVentes = new JLabel("Recipe");
	private final JLabel lblAchat = new JLabel("Purchase");
	private final JLabel clientFournisLabel = new JLabel("");
	private final JLabel stocksLabel = new JLabel("");
	private final JLabel recipeLabel = new JLabel("");
	private final JLabel purchaseLabel = new JLabel("");
	

	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen frame = new MainScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		
    public MainScreen() {
    	setTitle("Menu");
        initComponents();
        setResizable(false);
    }
    	
	
	/**
	 * Create the frame.
	 */
	
	private void initComponents() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 839, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		contentPane.add(panel, BorderLayout.CENTER);
		
		lblNewLabel.setFont(new Font("Garuda", Font.BOLD, 50));
		lblNewLabel.setBounds(287, 12, 256, 53);
		lblNewLabel.setForeground(new Color(245, 255, 250));
		panel.setLayout(null);
		panel.add(lblNewLabel);
		lblEntreprise.setFont(new Font("DialogInput", Font.BOLD, 12));
		lblEntreprise.setForeground(new Color(255, 250, 250));
		lblEntreprise.setBounds(249, 213, 63, 15);
		
		panel.add(lblEntreprise);
		lblClientFournisseur.setFont(new Font("DialogInput", Font.BOLD, 12));
		lblClientFournisseur.setForeground(new Color(255, 250, 250));
		lblClientFournisseur.setBounds(487, 213, 153, 15);
		
		panel.add(lblClientFournisseur);
		lblStocks.setFont(new Font("DialogInput", Font.BOLD, 12));
		lblStocks.setForeground(new Color(255, 250, 250));
		lblStocks.setBounds(110, 400, 70, 15);
		
		panel.add(lblStocks);
		lblVentes.setForeground(new Color(255, 250, 250));
		lblVentes.setFont(new Font("DialogInput", Font.BOLD, 12));
		lblVentes.setBounds(377, 400, 70, 15);
		
		panel.add(lblVentes);
		lblAchat.setForeground(new Color(255, 250, 250));
		lblAchat.setFont(new Font("DialogInput", Font.BOLD, 12));
		lblAchat.setBounds(674, 400, 70, 15);
		
		panel.add(lblAchat);
		
		JLabel companyLabel = new JLabel("");
		companyLabel.setToolTipText("Entreprise");
		companyLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		companyLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CompanyInfo companyInfo=new CompanyInfo();
				companyInfo.setVisible(true);

			}
		});
		companyLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/tn/poly/bahri/images/entrepriseIcone.png")));
		companyLabel.setBounds(229, 109, 96, 92);
		panel.add(companyLabel);
		clientFournisLabel.setToolTipText("Click Me");
		clientFournisLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		clientFournisLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ClientProvider clientProvider =new ClientProvider();
				clientProvider.setVisible(true);
			}
		});
		clientFournisLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/tn/poly/bahri/images/clientFournisseur.png")));
		clientFournisLabel.setBounds(487, 134, 123, 67);
		
		panel.add(clientFournisLabel);
		stocksLabel.setToolTipText("Click Me");
		stocksLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		stocksLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Stocks stocks= new Stocks();
				stocks.setVisible(true);
			}
		});
		stocksLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/tn/poly/bahri/images/Warehouse.png")));
		stocksLabel.setBounds(77, 300, 103, 92);
		
		panel.add(stocksLabel);
		recipeLabel.setToolTipText("Vente");
		recipeLabel.setCursor(new Cursor (Cursor.HAND_CURSOR));
		recipeLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Products products= new Products();
				products.setVisible(true);
			}
		});
		recipeLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/tn/poly/bahri/images/vente.png")));
		recipeLabel.setBounds(342, 300, 116, 81);
		
		panel.add(recipeLabel);
		purchaseLabel.setToolTipText("Achat");
		purchaseLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		purchaseLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Selling selling= new Selling();
				selling.setVisible(true);
			}
		});
		purchaseLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/tn/poly/bahri/images/buyMe.png")));
		purchaseLabel.setBounds(662, 300, 103, 105);
		
		panel.add(purchaseLabel);
		
		BackgroundLabel.setBounds(0, -21, 1920, 1060);
		BackgroundLabel.setIcon(new ImageIcon(MainScreen.class.getResource("/tn/poly/bahri/images/wallpaper.jpg")));
		panel.add(BackgroundLabel);
	
	}
}
