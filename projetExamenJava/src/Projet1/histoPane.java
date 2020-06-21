package Projet1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;

public class histoPane extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					histoPane frame = new histoPane();
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
	public histoPane() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 414);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(224, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout());
		
		JPanel tabpane = new JPanel();
		tabpane.setBorder(new LineBorder(new Color(0, 139, 139), 2, true));
		tabpane.setBackground(new Color(224, 255, 255));
		tabpane.setPreferredSize(new Dimension(500,340));
		contentPane.add(tabpane, BorderLayout.CENTER);
		tabpane.setLayout(null);
		
		String[] th= {"nom","exam","note","date"};
		Object[][] data= {{"said","java","16","2020-09-22"} };
		table = new JTable(data,th);
		table.setFont(new Font("Calibri", Font.PLAIN, 14));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "java", "16", "2020-09-22"},
				{"2", "php", "14", "2020-09-23"},
				{"3", "CSS", "18", "2020-0924"},
			},
			new String[] {
				"n", "Examen", "Note", "Date"
			}
		));
		DefaultTableModel model=(DefaultTableModel) table.getModel();
		//model.addColumn("col1");
		//model.addRow(new Object[]{"ob1","ob2","ob3","ob4"});
		table.setGridColor(new Color(0, 191, 255));
		table.getTableHeader().setBackground(new Color(32, 178, 170));
		table.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 14));
		table.getTableHeader().setForeground(Color.black);
		table.setBounds(202, 133, 359, 200);
		JScrollPane scrollTab=new JScrollPane(table);
		scrollTab.getViewport().setBackground(new Color(224, 255, 255));
		scrollTab.setBounds(70, 57, 359, 270);
		tabpane.add(scrollTab);
		
		JLabel lblhead = new JLabel("les notes de vos examens");
		lblhead.setHorizontalAlignment(SwingConstants.CENTER);
		lblhead.setForeground(new Color(0, 128, 128));
		lblhead.setFont(new Font("Calibri", Font.BOLD, 18));
		lblhead.setBounds(99, 11, 302, 35);
		tabpane.add(lblhead);
		
		JLabel lblback = new JLabel("");
		lblback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblback.setIcon(new ImageIcon("ressources\\back (4).png"));
		lblback.setBounds(0, 0, 25, 25);
		tabpane.add(lblback);
	}
}
