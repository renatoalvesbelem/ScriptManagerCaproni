package br.com.scriptmanagercaproni.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "caproniConfig")
public class CaproniFolderModel {

	private String pathFolder;

	@XmlElement(name = "caproniFolder")
	public String getPathFolder() {
		return pathFolder;
	}

	public void setPathFolder(String pathFolder) {
		this.pathFolder = pathFolder;
	}

}
