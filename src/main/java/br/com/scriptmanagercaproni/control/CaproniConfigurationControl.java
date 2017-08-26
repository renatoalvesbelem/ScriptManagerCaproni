package br.com.scriptmanagercaproni.control;

import java.io.File;

import javax.swing.JOptionPane;

import br.com.objectfile.xml.ObjectToXML;
import br.com.objectfile.xml.XMLToObject;
import br.com.scriptmanagercaproni.model.CaproniFolderModel;
import br.com.scriptmanagercaproni.parameter.SystemParameter;

public class CaproniConfigurationControl {

	public String getPath() {
		File file = new File(SystemParameter.CAPRONI_FILE_NAME);
		if (file.exists()) {
			CaproniFolderModel caproniFolderModel = (CaproniFolderModel) new XMLToObject(new CaproniFolderModel(),
					file.getAbsolutePath()).instanceObjectParsed();
			if (!caproniFolderModel.getPathFolder().isEmpty()) {
				return caproniFolderModel.getPathFolder();
			} else {
				return SystemParameter.FAVOR_INFORMAR_DIRETORIO;
			}

		} else {
			return SystemParameter.FAVOR_INFORMAR_DIRETORIO;
		}

	}
	
	public void saveFile(String pathCapron) {
		if (!pathCapron.equals(SystemParameter.FAVOR_INFORMAR_DIRETORIO) ) {
			CaproniFolderModel caproniFolderModel = new CaproniFolderModel();
			caproniFolderModel.setPathFolder(pathCapron);
			new ObjectToXML().createXML(caproniFolderModel, SystemParameter.CAPRONI_FILE_NAME);	
		}
		else {
			JOptionPane.showMessageDialog(null, "Informe todos os campos obrigatórios");
		}
	}

}
