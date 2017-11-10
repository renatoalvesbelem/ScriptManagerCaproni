package br.com.scriptmanagercaproni.components;

import java.io.File;

import javax.swing.JFileChooser;

public class FileChooserDirectory extends JFileChooser {

	private static final long serialVersionUID = -4872266272841164209L;

	public FileChooserDirectory(String currentDirectory) {
		this.setCurrentDirectory(new File(currentDirectory));
		this.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		this.showOpenDialog(null);

	}

}
