package br.com.scriptmanagercaproni.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.scriptmanagercaproni.components.FileChooserDirectory;
import br.com.scriptmanagercaproni.control.CaproniConfigurationControl;

public class CaproniConfigurationView extends JFrame {

	private static final long serialVersionUID = -7439009097407169326L;
	private JPanel contentPane;
	private JTextField txCaproniPath;
	private JButton btFechar;
	private JButton btSalvar;
	private CaproniConfigurationControl caproniConfigurationControl;
	private JTextField txScriptPath;

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

	public CaproniConfigurationView() {
		caproniConfigurationControl = new CaproniConfigurationControl();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 467, 300);
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
		JButton btChooseCaproniFolder = new JButton("...");
		btChooseCaproniFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new FileChooserDirectory(txCaproniPath.getText());
				txCaproniPath.setText(chooser.getSelectedFile().getPath());
			}
		});
		btChooseCaproniFolder.setBounds(395, 9, 43, 25);
		contentPane.add(btChooseCaproniFolder);

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
				if (caproniConfigurationControl.saveFile(txCaproniPath.getText(), txScriptPath.getText())) {
					JOptionPane.showMessageDialog(null, "Configurações salvas com sucesso");
				} else {
					JOptionPane.showMessageDialog(null, "Houve algum erro ao salvar as configurações");
				}
			}
		});
		btSalvar.setBounds(24, 230, 98, 25);
		contentPane.add(btSalvar);

		JLabel lblScriptfolder = new JLabel("Script Folder");
		lblScriptfolder.setBounds(12, 50, 78, 15);
		contentPane.add(lblScriptfolder);

		txScriptPath = new JTextField();
		txScriptPath.setText("Favor informar o diretório");
		txScriptPath.setEditable(false);
		txScriptPath.setColumns(10);
		txScriptPath.setBounds(108, 48, 275, 18);
		contentPane.add(txScriptPath);

		JButton btChooseScriptFolder = new JButton("...");
		btChooseScriptFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new FileChooserDirectory(txScriptPath.getText());
				txScriptPath.setText(chooser.getSelectedFile().getPath());

			}
		});
		btChooseScriptFolder.setBounds(395, 45, 43, 25);
		contentPane.add(btChooseScriptFolder);
	}
}
