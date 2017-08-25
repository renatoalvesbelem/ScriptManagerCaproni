package br.com.scriptmanagercaproni.control;

import java.io.File;

import br.com.objectfile.xml.XMLToObject;
import br.com.scriptmanagercaproni.model.CaproniFolderModel;

public class CaproniConfigurationControl {

	public String getPath() {
		if (new File("caproniConfig.xml").exists()) {
			CaproniFolderModel caproniFolderModel = (CaproniFolderModel) new XMLToObject(CaproniFolderModel.class,
					"caproniConfig.xml").instanceObjectParsed();
			if (caproniFolderModel.getPathFolder().isEmpty()) {
				return caproniFolderModel.getPathFolder();
			} else {

				return "Informar o diretório do Caproni";
			}

		} else {
			return "Informar o diretório do Caproni";
		}

	}

}
