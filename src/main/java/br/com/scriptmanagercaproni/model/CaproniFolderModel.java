package br.com.scriptmanagercaproni.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CaproniConfig")
public class CaproniFolderModel {
	@XmlElement(name = "folder-path", required = true)
	private String pathFolder;

	public String getPathFolder() {
		return pathFolder;
	}

	public void setPathFolder(String pathFolder) {
		this.pathFolder = pathFolder;
	}

}
