package br.com.scriptmanagercaproni.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.scriptmanagercaproni.components.FileChooserFile;
import br.com.scriptmanagercaproni.control.CaproniConfigurationControl;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DatabaseCatalogView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7439009097407169326L;
	private JPanel contentPane;
	private JTextField txCaproniPath;
	private JButton btFechar;
	private JButton btSalvar;
	private CaproniConfigurationControl caproniConfigurationControl;
	private JTextField textField;
	private JLabel lblNomeCatalogo;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DatabaseCatalogView frame = new DatabaseCatalogView();
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
	public DatabaseCatalogView() {
		caproniConfigurationControl = new CaproniConfigurationControl();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 581, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbCaproniFolder = new JLabel("Catalogo Caproni");
		lbCaproniFolder.setBounds(24, 65, 83, 15);
		contentPane.add(lbCaproniFolder);

		txCaproniPath = new JTextField();
		txCaproniPath.setBounds(117, 62, 342, 19);
		contentPane.add(txCaproniPath);
		txCaproniPath.setColumns(10);
		txCaproniPath.setEditable(false);
		txCaproniPath.setText("Favor informar o arquivo .ini");
		JButton button = new JButton("...");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new FileChooserFile();
				txCaproniPath.setText(chooser.getSelectedFile().getPath());
			}
		});
		button.setBounds(469, 60, 43, 25);
		contentPane.add(button);

		btFechar = new JButton("Fechar");
		btFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btFechar.setBounds(134, 294, 98, 25);
		contentPane.add(btFechar);

		btSalvar = new JButton("Salvar");
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				caproniConfigurationControl.saveFile(txCaproniPath.getText());
			}
		});
		btSalvar.setBounds(24, 294, 98, 25);
		contentPane.add(btSalvar);

		textField = new JTextField();
		textField.setBounds(115, 21, 397, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		lblNomeCatalogo = new JLabel("Nome Catalogo");
		lblNomeCatalogo.setBounds(24, 24, 81, 15);
		contentPane.add(lblNomeCatalogo);

		String[] columnNames = { "ALIAS", "JDBC" };
		Object[][] data = { };

		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 125, 545, 77);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable(data, columnNames);
		
		scrollPane_1.setViewportView(table_1);
		table_1.getColumn(1).setPreferredWidth(120);
		table_1.getColumn(0).setPreferredWidth(27);
	}
}
