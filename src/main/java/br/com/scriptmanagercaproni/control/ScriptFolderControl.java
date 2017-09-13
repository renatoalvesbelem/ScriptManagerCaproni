package br.com.scriptmanagercaproni.control;

import java.io.File;
import java.util.HashMap;
import br.com.scriptmanagercaproni.parameter.DataBaseFolders;
import br.com.scriptmanagercaproni.parameter.DataBaseType;
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

	public void createDirectoryDestination(String databaseType, String instancia) {
		String[] folders;
		if (databaseType.equals(DataBaseType.ORACLE)) {
			if (instancia.equals(DataBaseType.PG)) {
				folders = DataBaseFolders.FOLDER_ORACLEPG;
			} else {
				folders = DataBaseFolders.FOLDER_ORACLESG;

			}
		} else if (databaseType.equals(DataBaseType.SQLSERVER)) {
			if (instancia.equals(DataBaseType.PG)) {
				folders = DataBaseFolders.FOLDER_SQLSERVERPG;
			} else {
				folders = DataBaseFolders.FOLDER_SQLSERVERSG;
			}
		} else {
			if (instancia.equals(DataBaseType.PG)) {
				folders = DataBaseFolders.FOLDER_DB2PG;
			} else {
				folders = DataBaseFolders.FOLDER_ORACLESG;

			}
		}

		try {
			String folderCaproni = file.getAbsolutePath();
			remover(new File(folderCaproni + SystemParameter.CAPRONI_FOLDER_INPUT));
			new File(folderCaproni + SystemParameter.CAPRONI_FOLDER_INPUT).mkdir();
			for (String folder : folders) {
				new File(folderCaproni + SystemParameter.CAPRONI_FOLDER_INPUT + folder).mkdir();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void remover(File files) {
		for (File file : files.listFiles()) {
			if (file.isDirectory()) {
				for (File archive : files.listFiles()) {
					remover(archive);
				}
			}
			file.delete();
		}
	}
}
