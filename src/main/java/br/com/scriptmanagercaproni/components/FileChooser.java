package br.com.scriptmanagercaproni.components;

import javax.swing.JFileChooser;

public class FileChooser extends JFileChooser{

	private static final long serialVersionUID = -5578334058933510567L;
	private JFileChooser choose;

	public JFileChooser directoryChooser() {
		choose = new JFileChooser();
		choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		choose.showOpenDialog(null);
		return choose;
	}
	
	
}
