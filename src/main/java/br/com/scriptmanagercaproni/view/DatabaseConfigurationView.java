package br.com.scriptmanagercaproni.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.scriptmanagercaproni.components.FileChooserDirectory;
import br.com.scriptmanagercaproni.components.FileChooserFile;
import br.com.scriptmanagercaproni.control.CaproniConfigurationControl;

public class DatabaseConfigurationView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7439009097407169326L;
	private JPanel contentPane;
	private CaproniConfigurationControl caproniConfigurationControl;
	private JComboBox comboBox;
	private JTextField txDatabaseFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatabaseConfigurationView frame = new DatabaseConfigurationView();
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
	public DatabaseConfigurationView() {
		caproniConfigurationControl = new CaproniConfigurationControl();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 587, 381);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setBounds(121, 42, 414, 24);
		contentPane.add(comboBox);
		
		JLabel lblTipodeDeBanco = new JLabel("Tipo de Banco");
		lblTipodeDeBanco.setBounds(24, 47, 94, 15);
		contentPane.add(lblTipodeDeBanco);
		
		txDatabaseFile = new JTextField();
		txDatabaseFile.setBounds(121, 89, 377, 24);
		contentPane.add(txDatabaseFile);
		txDatabaseFile.setColumns(10);
		
		JButton button = new JButton("...");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new FileChooserFile();
				String pathScriptFolder = chooser.getSelectedFile().getPath();
				txDatabaseFile.setText(pathScriptFolder);
			}
		});
		button.setBounds(510, 88, 25, 25);
		contentPane.add(button);
	}
}
