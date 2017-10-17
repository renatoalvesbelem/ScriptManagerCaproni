package br.com.scriptmanagercaproni.control;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.scriptmanagercaproni.parameter.DataBaseFolders;
import br.com.scriptmanagercaproni.parameter.DataBaseType;
import br.com.scriptmanagercaproni.parameter.DbChangeFile;
import br.com.scriptmanagercaproni.parameter.SystemParameter;

public class ScriptFolderControl {
	File file = null;
	String instancia;
	String[] folders;

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

	@SuppressWarnings("null")
	public void createDirectoryDestination(String databaseType, String instancia, List<String> aplicationsSelected,
			String pathOrigin) {

		List<String> foldersDatabase = new ArrayList<String>();
		if (databaseType.equals(DataBaseType.ORACLE)) {
			if (instancia.equals(DataBaseType.PG)) {
				folders = DataBaseFolders.FOLDER_ORACLEPG;
			} else {
				folders = DataBaseFolders.FOLDER_ORACLESG;
			}
			this.instancia = DataBaseType.FOLDER_ORACLE;
		} else if (databaseType.equals(DataBaseType.SQLSERVER)) {
			if (instancia.equals(DataBaseType.PG)) {
				folders = DataBaseFolders.FOLDER_SQLSERVERPG;
			} else {
				folders = DataBaseFolders.FOLDER_SQLSERVERSG;
			}
			this.instancia = DataBaseType.FOLDER_SQLSERVER;
		} else {
			if (instancia.equals(DataBaseType.PG)) {
				folders = DataBaseFolders.FOLDER_DB2PG;
			} else {
				folders = DataBaseFolders.FOLDER_DB2SG;
			}
			this.instancia = DataBaseType.FOLDER_DB2;
		}
		String folderCaproni = file.getAbsolutePath();
		try {
			remover(new File(folderCaproni + SystemParameter.CAPRONI_FOLDER_INPUT));
			for (String folder : folders) {
				String folderFinalTmp = folderCaproni + SystemParameter.CAPRONI_FOLDER_INPUT + folder;
				new File(folderFinalTmp).mkdir();
				foldersDatabase.add(folderFinalTmp);
			}

		} catch (Exception e) {

		}
		for (String aplication : aplicationsSelected) {
			copyFiles(pathOrigin + "\\" + aplication + "\\DBCHANGE");
		}
		deleteFoldersEmpty(folders);
		createDbChange();

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

	public void copyFiles(String origemAplication) {

		for (String folder : folders) {
			if (new File(origemAplication + "\\" + instancia + "\\" + folder).listFiles() != null) {
				copyFile(new File(origemAplication + "\\" + instancia + "\\" + folder).listFiles(),
						new File(file.getAbsolutePath() + "\\" + SystemParameter.CAPRONI_FOLDER_INPUT + folder));
			}

		}

	}

	private void copyFile(File[] filesOrigem, File destination) {
		for (File fileOrigem : filesOrigem) {
			copy(fileOrigem, destination);
		}

	}

	public void copy(File fileOrigem, File destination) {
		String inFileName = fileOrigem.getAbsolutePath();
		String FileName = "\\" + fileOrigem.getName();
		String outFileName = destination.getAbsolutePath() + FileName;
		try {
			FileInputStream in = new FileInputStream(inFileName);
			FileOutputStream out = new FileOutputStream(outFileName);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.close();
			in.close();
		} catch (Exception e) {
			System.out.println("Não foi encontrado nenhum arquivo para a pasta " + inFileName);
		}
	}

	private void createDbChange() {

		String pathInput = file.getAbsolutePath() + SystemParameter.CAPRONI_FOLDER_INPUT;
		for (String folder : new File(pathInput).list()) {
			String fileDbChange = pathInput + folder;
			writeDbChange(new File(fileDbChange));

		}
	}

	private void deleteFoldersEmpty(String[] folders) {
		String folderCaproni = file.getAbsolutePath();
		for (String folder : folders) {
			String folderFinalTmp = folderCaproni + SystemParameter.CAPRONI_FOLDER_INPUT + folder;
			File file = new File(folderFinalTmp);
			if (file.list().length == 0) {
				file.delete();
			}
		}
	}

	private void writeDbChange(File fileDbChange) {
		FileWriter fileW;
		try {
			fileW = new FileWriter(fileDbChange.getAbsolutePath() + "\\" + "dbChange.xml");
			BufferedWriter buffW = new BufferedWriter(fileW);
			buffW.write(DbChangeFile.HEADER_XML);
			buffW.newLine();
			buffW.write(DbChangeFile.BEGIN_TAG_HAVILLAN);
			buffW.newLine();
			for (String file : fileDbChange.list()) {
				if (file.contains(".DH4")) {
					buffW.write(DbChangeFile.BEGIN_TAG_SCRIPT + file + DbChangeFile.END_TAG_SCRIPT);
					buffW.newLine();
				}
			}
			buffW.write(DbChangeFile.END_TAG_HAVILLAN);
			buffW.close();
			fileW.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
