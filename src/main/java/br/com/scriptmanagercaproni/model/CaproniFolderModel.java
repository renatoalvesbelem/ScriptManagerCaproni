package br.com.scriptmanagercaproni.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "caproniConfig")
public class CaproniFolderModel {

	private String pathFolder;
	private String pathScriptFolder;

	@XmlElement(name = "caproniFolder")
	public String getPathFolder() {
		return pathFolder;
	}

	public void setPathFolder(String pathFolder) {
		this.pathFolder = pathFolder;
	}

	@XmlElement(name = "scriptFolder")
	public String getPathScriptFolder() {
		return pathScriptFolder;
	}

	public void setPathScriptFolder(String pathScriptFolder) {
		this.pathScriptFolder = pathScriptFolder;
	}

}
