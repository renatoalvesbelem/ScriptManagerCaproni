package br.com.scriptmanagercaproni.components;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileChooserFile extends JFileChooser {

	private static final long serialVersionUID = -4872266272841164209L;

	public FileChooserFile() {
		this.setFileSelectionMode(JFileChooser.FILES_ONLY);
		this.setFileFilter(new FileNameExtensionFilter(".ini", "*.ini", "ini"));
		this.showOpenDialog(null);

	}

}
