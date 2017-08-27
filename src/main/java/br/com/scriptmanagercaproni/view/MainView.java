package br.com.scriptmanagercaproni.view;

import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.scriptmanagercaproni.components.FileChooser;
import br.com.scriptmanagercaproni.control.ScriptFolderControl;

public class MainView extends JFrame {
	private static final long serialVersionUID = 8862157956341479195L;
	private JPanel contentPane;
	private JTextField txFilePath;

	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;
	final static boolean RIGHT_TO_LEFT = true;

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
				txFilePath.setText(chooser.getSelectedFile().getPath());
			}
		});
		btnSelecione.setBounds(658, 39, 113, 25);
		contentPane.add(btnSelecione);

		txFilePath = new JTextField();
		txFilePath.setBounds(111, 42, 535, 19);
		contentPane.add(txFilePath);
		txFilePath.setColumns(10);

		JPanel panelCheckBox = new JPanel();
		panelCheckBox.setBounds(44, 71, 719, 350);
		contentPane.add(panelCheckBox);

		panelCheckBox.setLayout(new GridBagLayout());
		List<JCheckBox> checkboxes = new ArrayList<JCheckBox>();
		ScriptFolderControl scriptFolderControl = new ScriptFolderControl("/home/renato/Downloads");
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		c.fill = GridBagConstraints.VERTICAL;
		c.anchor = GridBagConstraints.WEST;
		int x = 1;
		int y = 1;
		for (String element : scriptFolderControl.getSystemDirectoriesAndName().values()) {

			JCheckBox box = new JCheckBox(element);
			checkboxes.add(box);
			c.weightx = 0.5;
			c.gridx = x;
			c.gridy = y;
			panelCheckBox.add(box, c);
			if (y>=10) {
				x++;
				y = 0;
			}
			y++;

		}


	}

}
