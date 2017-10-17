package br.com.scriptmanagercaproni.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import br.com.scriptmanagercaproni.components.FileChooserDirectory;
import br.com.scriptmanagercaproni.components.PanelCheckBox;
import br.com.scriptmanagercaproni.control.CaproniConfigurationControl;
import br.com.scriptmanagercaproni.control.DatabaseCatalogControl;
import br.com.scriptmanagercaproni.control.ScriptFolderControl;
import br.com.scriptmanagercaproni.parameter.DataBaseType;
import br.com.scriptmanagercaproni.parameter.SystemParameter;

public class MainView extends JFrame {
	private static final long serialVersionUID = 8862157956341479195L;
	private JPanel contentPane;
	private JTextField txFilePath;
	private PanelCheckBox panelCheckBox;
	CaproniConfigurationControl caproniConfigurationControl;
	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	final static boolean RIGHT_TO_LEFT = true;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox<String> cbDatabaseCatalog = new JComboBox<String>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setVisible(true);
					try {
						UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
						SwingUtilities.updateComponentTreeUI(frame);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
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
		lblScript.setBounds(44, 44, 86, 15);
		contentPane.add(lblScript);

		JButton btnSelecione = new JButton("...");
		btnSelecione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new FileChooserDirectory();
				String pathScriptFolder = chooser.getSelectedFile().getPath();
				txFilePath.setText(pathScriptFolder);
				createPanelCheckBox(pathScriptFolder);

			}
		});
		btnSelecione.setBounds(720, 39, 43, 25);
		contentPane.add(btnSelecione);

		txFilePath = new JTextField();
		txFilePath.setBounds(134, 42, 563, 19);
		contentPane.add(txFilePath);
		txFilePath.setColumns(10);

		panelCheckBox = new PanelCheckBox();
		panelCheckBox.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Folder Script",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCheckBox.setBounds(44, 71, 719, 43);
		contentPane.add(panelCheckBox);

		final JComboBox<String> cbDatabaseType = new JComboBox<String>();
		cbDatabaseType.setModel(new DefaultComboBoxModel<String>(DataBaseType.ALL_TYPES));
		cbDatabaseType.setBounds(44, 328, 577, 24);
		contentPane.add(cbDatabaseType);

		JButton btnAdicionarBase = new JButton("Add");
		btnAdicionarBase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatabaseCatalogView databaseCatalogView = new DatabaseCatalogView();
				databaseCatalogView.setModal(true);
				databaseCatalogView.setVisible(true);
				updateCbDatabaseCatalog();
			}
		});
		btnAdicionarBase.setBounds(631, 282, 51, 25);
		contentPane.add(btnAdicionarBase);

		JButton btnExecute = new JButton("Executar");
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScriptFolderControl scriptFolderControl = new ScriptFolderControl(
						new CaproniConfigurationControl().getPath());
				scriptFolderControl.createDirectoryDestination(cbDatabaseType.getSelectedItem().toString(),
						buttonGroup.getSelection().getActionCommand(), panelCheckBox.getListCheckBoxSelected(),
						txFilePath.getText());

			}
		});
		btnExecute.setBounds(44, 509, 98, 25);
		contentPane.add(btnExecute);

		JRadioButton rbPG = new JRadioButton(DataBaseType.PG);
		buttonGroup.add(rbPG);
		rbPG.setBounds(44, 371, 121, 23);
		rbPG.setSelected(true);
		rbPG.setActionCommand(DataBaseType.PG);
		contentPane.add(rbPG);

		JRadioButton rbSG = new JRadioButton(DataBaseType.SG);
		buttonGroup.add(rbSG);
		rbSG.setBounds(44, 392, 121, 23);
		rbSG.setActionCommand(DataBaseType.SG);
		contentPane.add(rbSG);

		cbDatabaseCatalog.setBounds(44, 282, 577, 20);
		updateCbDatabaseCatalog();
		contentPane.add(cbDatabaseCatalog);

		JButton btnEdit = new JButton("Del");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (new DatabaseCatalogControl().deleteDatabaseFile(cbDatabaseCatalog.getSelectedItem().toString())) {
					updateCbDatabaseCatalog();
				}

			}
		});
		btnEdit.setBounds(684, 282, 51, 25);
		contentPane.add(btnEdit);
	}

	public void createPanelCheckBox(String pahtScript) {

		if (panelCheckBox.getComponentCount() > 0)

		{
			panelCheckBox.removeAll();
			panelCheckBox.repaint();
		}
		int height = 43;
		panelCheckBox.createCheckBox(pahtScript);
		switch (panelCheckBox.getComponentCount()) {

		case 0:
			break;
		case 1:
			break;
		case 2:
			height = 83;
			break;
		case 3:
			height = 103;
			break;
		case 4:
			height = 123;
			break;
		case 5:
			height = 153;
			break;
		default:
			height = 193;
			break;
		}

		panelCheckBox.setBounds(44, 71, 719, height);

		contentPane.validate();

	}

	public void updateCbDatabaseCatalog() {
		List<String> databaseCatalogControl = null;
		cbDatabaseCatalog.removeAllItems();
		try {
			databaseCatalogControl = new DatabaseCatalogControl().getListCatalogFiles(new File("").getCanonicalFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (String databaseCatalog : databaseCatalogControl) {
			if (databaseCatalog.contains(SystemParameter.CAPRONI_XML_EXT)) {
				cbDatabaseCatalog.addItem(databaseCatalog.replace(SystemParameter.CAPRONI_XML_EXT, ""));
			}
		}

	}
}