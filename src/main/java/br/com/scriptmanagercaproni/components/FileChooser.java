package br.com.scriptmanagercaproni.components;

import javax.swing.JFileChooser;

public class FileChooser extends JFileChooser{
	private JFileChooser choose = new JFileChooser();

	public JFileChooser directoryChooser() {
		choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		choose.showOpenDialog(null);
		return choose;
	}


}
