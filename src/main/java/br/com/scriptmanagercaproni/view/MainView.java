package br.com.scriptmanagercaproni.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import br.com.scriptmanagercaproni.components.FileChooser;
import br.com.scriptmanagercaproni.components.PanelCheckBox;

public class MainView extends JFrame {
	private static final long serialVersionUID = 8862157956341479195L;
	private JPanel contentPane;
	private JTextField txFilePath;
	private PanelCheckBox panelCheckBox;

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
				JFileChooser chooser = new FileChooser();
				String pathScriptFolder = chooser.getSelectedFile().getPath();
				txFilePath.setText(pathScriptFolder);
				createPanelCheckBox(pathScriptFolder);
			}
		});
		btnSelecione.setBounds(658, 39, 113, 25);
		contentPane.add(btnSelecione);

		txFilePath = new JTextField();
		txFilePath.setBounds(111, 42, 535, 19);
		contentPane.add(txFilePath);
		txFilePath.setColumns(10);

		panelCheckBox = new PanelCheckBox();
		panelCheckBox.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Folder Script",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCheckBox.setBounds(44, 71, 719, 43);
		contentPane.add(panelCheckBox);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCheckBox.removeAll();
				panelCheckBox.validate();
				panelCheckBox.repaint();
			}
		});
		btnNewButton.setBounds(24, 468, 98, 25);
		contentPane.add(btnNewButton);
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
			height = 43;
			break;

		case 1:
			height = 43;
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

}
