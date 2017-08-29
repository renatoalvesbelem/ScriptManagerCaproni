package br.com.scriptmanagercaproni.control;

import java.io.File;
import java.util.HashMap;

public class ScriptFolderControl {
	File file = null;

	public ScriptFolderControl(String pathFolderScripts) {
		file = new File(pathFolderScripts);
	}

	public HashMap<String, String> getSystemDirectoriesAndName() {
		HashMap<String, String> listaArquivos = new HashMap<String, String>();
		for (File arquivo : file.listFiles()) {
			if (arquivo.isDirectory())
				listaArquivos.put(arquivo.getAbsolutePath(), arquivo.getName());
		}
		return listaArquivos;
	}

}
