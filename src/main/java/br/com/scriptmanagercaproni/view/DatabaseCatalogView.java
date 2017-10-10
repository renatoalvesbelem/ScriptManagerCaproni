package br.com.scriptmanagercaproni.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.scriptmanagercaproni.components.FileChooserFile;
import br.com.scriptmanagercaproni.control.DatabaseCatalogControl;
import br.com.scriptmanagercaproni.model.DatabaseModel;

public class DatabaseCatalogView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7439009097407169326L;
	private JPanel contentPane;
	private JTextField txCaproniPath;
	private JButton btFechar;
	private JButton btCatalog;
	private DatabaseCatalogControl databaseCatalogControl;
	private JTextField txtNameCatalog;
	private JLabel lblNomeCatalogo;
	private JTable table;
	private String[] columnNames = { "ALIAS", "JDBC" };
	private Object[][] data = {};
	DefaultTableModel model = new DefaultTableModel(data, columnNames);
	

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
		databaseCatalogControl = new DatabaseCatalogControl();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 822, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lbCaproniFolder = new JLabel("Catalogo Caproni");
		lbCaproniFolder.setBounds(24, 65, 83, 15);
		contentPane.add(lbCaproniFolder);

		txCaproniPath = new JTextField();
		txCaproniPath.setBounds(147, 62, 550, 19);
		contentPane.add(txCaproniPath);
		txCaproniPath.setColumns(10);
		txCaproniPath.setEditable(false);
		txCaproniPath.setText("Favor informar o arquivo .ini");
		JButton button = new JButton("...");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new FileChooserFile();
				txCaproniPath.setText(chooser.getSelectedFile().getPath());
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				List<DatabaseModel> databaseModels = null;
				try {
					databaseModels = databaseCatalogControl.listaDatabaseModel(new File(txCaproniPath.getText()));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				for (DatabaseModel databaseModel : databaseModels) {
					model.addRow(new Object[] { databaseModel.getDatabaseAlias(), databaseModel.getDatabaseJDBC() });
				}

			}
		});
		button.setBounds(720, 60, 43, 25);
		contentPane.add(button);

		btFechar = new JButton("Fechar");
		btFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btFechar.setBounds(134, 436, 98, 25);
		contentPane.add(btFechar);

		btCatalog = new JButton("Catalogar");
		btCatalog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				databaseCatalogControl.catalogDatabase(new File(txCaproniPath.getText()), txtNameCatalog.getText());
			}
		});
		btCatalog.setBounds(24, 436, 98, 25);
		contentPane.add(btCatalog);

		txtNameCatalog = new JTextField();
		txtNameCatalog.setBounds(147, 21, 616, 20);
		contentPane.add(txtNameCatalog);
		txtNameCatalog.setColumns(10);

		lblNomeCatalogo = new JLabel("Nome Catalogo");
		lblNomeCatalogo.setBounds(24, 24, 81, 15);
		contentPane.add(lblNomeCatalogo);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(22, 125, 741, 240);
		contentPane.add(scrollPane_1);

		table = new JTable(data, columnNames);
		table.setModel(model);
		scrollPane_1.setViewportView(table);
	}
}
