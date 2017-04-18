package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Runway;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JList;

public class SelectImportRunwaysGUI extends JFrame {

	private JPanel contentPane;
	private JList<Runway> list;
	
	

	
	public JList getList() {
		return list;
	}
	
	


	/**
	 * Create the frame.
	 */
	public SelectImportRunwaysGUI(ArrayList<Runway> imported,Controller c) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		list = new JList(imported.toArray());
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);    
		contentPane.add(list, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JButton selectAll = new JButton("Select All");
		selectAll.addActionListener(c.getSelectAllItems());
		panel.add(selectAll);
		
		JButton importButton = new JButton("Import");
		importButton.addActionListener(c.getImportSomeRunways());
		panel.add(importButton);
		
		JButton importAndOverwrite = new JButton("Import and Overwrite");
		importAndOverwrite.addActionListener(c.getOverwriteAirport());
		panel.add(importAndOverwrite);
		
		setVisible(true);
	}
	

}
