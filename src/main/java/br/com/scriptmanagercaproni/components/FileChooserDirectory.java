package br.com.scriptmanagercaproni.components;

import javax.swing.JFileChooser;

public class FileChooserDirectory extends JFileChooser {

	private static final long serialVersionUID = -4872266272841164209L;

	public FileChooserDirectory() {
		this.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		this.showOpenDialog(null);

	}

}