package br.com.scriptmanagercaproni.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.JOptionPane;
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
import javax.swing.JCheckBox;

public class MainView extends JFrame {
	private static final long serialVersionUID = 8862157956341479195L;
	private JPanel contentPane;
	private JTextField txFilePath;
	private PanelCheckBox panelCheckBox;
	CaproniConfigurationControl caproniConfigurationControl = new CaproniConfigurationControl();
	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	final static boolean RIGHT_TO_LEFT = true;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox<String> cbDatabaseCatalog = new JComboBox<String>();
	final JComboBox<String> cbDatabaseType;
	JRadioButton rbPG;
	JRadioButton rbSG;

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

		JMenuItem mntmGerarSql = new JMenuItem("Gerar SQL");
		mntmGerarSql.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SQLToolView sqlToolView = new SQLToolView();
				// sqlToolView.setModal(true);
				sqlToolView.setVisible(true);
			}
		});
		mnConfiguracoes.add(mntmGerarSql);
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
				JFileChooser chooser = new FileChooserDirectory(txFilePath.getText());
				String pathScriptFolder = chooser.getSelectedFile().getPath();
				txFilePath.setText(pathScriptFolder);
				createPanelCheckBox(pathScriptFolder);

			}
		});

		btnSelecione.setBounds(720, 39, 43, 25);
		contentPane.add(btnSelecione);

		txFilePath = new JTextField();
		txFilePath.setEditable(false);
		txFilePath.setBounds(134, 42, 563, 19);
		contentPane.add(txFilePath);
		txFilePath.setColumns(10);

		panelCheckBox = new PanelCheckBox();
		panelCheckBox.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Folder Script",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCheckBox.setBounds(44, 71, 719, 43);
		contentPane.add(panelCheckBox);

		cbDatabaseType = new JComboBox<String>();
		cbDatabaseType.setEnabled(false);
		cbDatabaseType.setModel(new DefaultComboBoxModel<String>(new String[] { "ORACLE", "SQLSERVER", "DB2" }));
		cbDatabaseType.setBounds(44, 391, 577, 24);
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
		btnAdicionarBase.setBounds(631, 345, 51, 25);
		contentPane.add(btnAdicionarBase);

		JButton btnExecute = new JButton("Executar");
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScriptFolderControl scriptFolderControl = new ScriptFolderControl(
						caproniConfigurationControl.getPath());
				if (panelCheckBox.getListCheckBoxSelected().size() != 0) {
					if (scriptFolderControl.createDirectoryDestination(cbDatabaseType.getSelectedItem().toString(),
							buttonGroup.getSelection().getActionCommand(), panelCheckBox.getListCheckBoxSelected(),
							txFilePath.getText())) {
						JOptionPane.showMessageDialog(null, "Os scripts foram executados com sucesso");
					} else {
						JOptionPane.showMessageDialog(null, "Houve algum erro ao executar os scripts");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Ao menos um sistema deverá ser selecionado");
				}

			}
		});
		btnExecute.setBounds(44, 509, 98, 25);
		contentPane.add(btnExecute);

		rbPG = new JRadioButton(DataBaseType.PG);
		rbPG.setEnabled(false);
		buttonGroup.add(rbPG);
		rbPG.setBounds(44, 434, 121, 23);
		rbPG.setSelected(true);
		rbPG.setActionCommand(DataBaseType.PG);
		contentPane.add(rbPG);

		rbSG = new JRadioButton(DataBaseType.SG);
		rbSG.setEnabled(false);
		buttonGroup.add(rbSG);
		rbSG.setBounds(44, 455, 121, 23);
		rbSG.setActionCommand(DataBaseType.SG);
		contentPane.add(rbSG);
		cbDatabaseCatalog.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				updateFiltros();
			}
		});
		cbDatabaseCatalog.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {

			}
		});
		cbDatabaseCatalog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		cbDatabaseCatalog.setBounds(44, 345, 577, 20);
		updateCbDatabaseCatalog();
		contentPane.add(cbDatabaseCatalog);

		JButton btnEdit = new JButton("Del");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(null, "Deseja deletar o catalogo?", "WARNING",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					if (new DatabaseCatalogControl()
							.deleteDatabaseFile(cbDatabaseCatalog.getSelectedItem().toString())) {
						updateCbDatabaseCatalog();
					}
				}
			}
		});
		btnEdit.setBounds(684, 345, 51, 25);
		contentPane.add(btnEdit);

		final JCheckBox chckbxTodos = new JCheckBox("Selecionar Todos");
		chckbxTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				{
					panelCheckBox.selectAllCheckBox(chckbxTodos.isSelected());

				}
			}
		});
		chckbxTodos.setBounds(44, 315, 134, 23);
		contentPane.add(chckbxTodos);
		String pathScript = caproniConfigurationControl.getPathScript();
		if (!pathScript.equals("")) {
			txFilePath.setText(pathScript);
			createPanelCheckBox(pathScript);
		}
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

	public void updateFiltros() {
		try {
			List<String> infos = new DatabaseCatalogControl()
					.returnDateCatalog(cbDatabaseCatalog.getSelectedItem().toString());
			cbDatabaseType.setSelectedItem(infos.get(0));
			switch (infos.get(1)) {
			case "PG":
				rbPG.setSelected(true);
				break;
			case "SG":
				rbSG.setSelected(true);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}