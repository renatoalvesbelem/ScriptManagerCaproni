package br.com.scriptmanagercaproni.control;

import java.io.File;
import java.util.HashMap;

import br.com.scriptmanagercaproni.parameter.SystemParameter;

public class ScriptFolderControl {
	File file = null;

	public ScriptFolderControl(String pathFolderScripts) {
		file = new File(pathFolderScripts);
	}

	public HashMap<String, String> getSystemDirectoriesAndName() {
		HashMap<String, String> listaArquivos = new HashMap<String, String>();
		for (File arquivo : file.listFiles()) {
			if (arquivo.isDirectory() & containsPaternCaproni(arquivo))
				listaArquivos.put(arquivo.getAbsolutePath(), arquivo.getName());
		}
		return listaArquivos;
	}

	private boolean containsPaternCaproni(File directories) {
		try {
			for (String directory : directories.list()) {
				if (directory.contains(SystemParameter.SCRIPT_FOLDER)) {
					return true;
				}
			}
			return false;

		} catch (NullPointerException e) {
			return false;

		}

	}
}
