package br.com.scriptmanagercaproni.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.scriptmanagercaproni.components.FileChooser;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MainView extends JFrame {
	private static final long serialVersionUID = 8862157956341479195L;
	private JPanel contentPane;
	private JTextField txFilePath;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public MainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnConfiguracoes = new JMenu("Configurações");
		menuBar.add(mnConfiguracoes);

		JMenuItem mntmNewMenuItem = new JMenuItem("Caproni");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CaproniConfigurationView().setVisible(true);

			}
		});
		mnConfiguracoes.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblScript = new JLabel("Script Folder");
		lblScript.setBounds(24, 44, 86, 15);
		contentPane.add(lblScript);

		JButton btnSelecione = new JButton("Selecione...");
		
		btnSelecione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new FileChooser().directoryChooser();
				txFilePath.setText(String.format("", chooser.getSelectedFile()));
			}
		});
		btnSelecione.setBounds(658, 39, 113, 25);
		contentPane.add(btnSelecione);

		txFilePath = new JTextField();
		txFilePath.setBounds(111, 42, 535, 19);
		contentPane.add(txFilePath);
		txFilePath.setColumns(10);
	}
}
