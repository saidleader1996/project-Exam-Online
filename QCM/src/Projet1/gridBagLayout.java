package Projet1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;

public class gridBagLayout extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gridBagLayout frame = new gridBagLayout();
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
	public gridBagLayout() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 450);
		contentPane = new JPanel(new GridBagLayout()); 
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.green);
		setContentPane(contentPane);
		
		GridBagConstraints gbc=new GridBagConstraints();
		
		JPanel pane=new JPanel();
		pane.setPreferredSize(new Dimension(600,250));
		pane.setBackground(Color.cyan);
		 
		gbc.gridx=1;
		gbc.gridy=1; 
		gbc.fill=GridBagConstraints.BOTH;
		contentPane.add(pane,gbc);
		
		JPanel pane2=new JPanel();
		pane2.setPreferredSize(new Dimension(800,250));
		pane2.setBackground(Color.yellow);
		 
		gbc.gridx=8;
		gbc.gridy=3; 
		//contentPane.add(pane2,gbc);
		
	/*	GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {4};
		gbl_contentPane.rowHeights = new int[]{0};
		gbl_contentPane.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);*/
	}

}
