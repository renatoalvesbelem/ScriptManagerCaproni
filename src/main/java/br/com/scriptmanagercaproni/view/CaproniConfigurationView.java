package br.com.scriptmanagercaproni.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.scriptmanagercaproni.components.FileChooser;
import br.com.scriptmanagercaproni.control.CaproniConfigurationControl;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CaproniConfigurationView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7439009097407169326L;
	private JPanel contentPane;
	private JTextField txCaproniPath;
	private JButton btFechar;
	private JButton btSalvar;
	private CaproniConfigurationControl caproniConfigurationControl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CaproniConfigurationView frame = new CaproniConfigurationView();
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
	public CaproniConfigurationView() {
		caproniConfigurationControl = new CaproniConfigurationControl();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbCaproniFolder = new JLabel("Caproni Folder");
		lbCaproniFolder.setBounds(12, 14, 78, 15);
		contentPane.add(lbCaproniFolder);

		txCaproniPath = new JTextField();
		txCaproniPath.setBounds(108, 12, 275, 19);
		contentPane.add(txCaproniPath);
		txCaproniPath.setColumns(10);
		txCaproniPath.setEditable(false);
		txCaproniPath.setText(caproniConfigurationControl.getPath());
		JButton button = new JButton("...");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new FileChooser().directoryChooser();
				txCaproniPath.setText(chooser.getSelectedFile().getPath());
			}
		});
		button.setBounds(395, 9, 43, 25);
		contentPane.add(button);

		btFechar = new JButton("Fechar");
		btFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btFechar.setBounds(134, 230, 98, 25);
		contentPane.add(btFechar);

		btSalvar = new JButton("Salvar");
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				caproniConfigurationControl.saveFile(txCaproniPath.getText());
			}
		});
		btSalvar.setBounds(24, 230, 98, 25);
		contentPane.add(btSalvar);
	}
}
