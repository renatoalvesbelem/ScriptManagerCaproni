package br.com.scriptmanagercaproni.control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.objectfile.xml.ObjectToXML;
import br.com.objectfile.xml.XMLToObject;
import br.com.scriptmanagercaproni.model.DatabaseModel;
import br.com.scriptmanagercaproni.model.ListDatabaseModel;
import br.com.scriptmanagercaproni.parameter.SystemParameter;

public class DatabaseCatalogControl {

	public void catalogDatabase(File arquivoOrigem, String nameFileDest) {
		ListDatabaseModel listDatabaseModel = new ListDatabaseModel();

		try {
			listDatabaseModel.setDatabaseModel(listaDatabaseModel(arquivoOrigem));
		} catch (IOException e) {
			e.printStackTrace();
		}
		saveFile(listDatabaseModel, nameFileDest);
	}

	private void saveFile(ListDatabaseModel listDatabaseModel, String nameFileDest) {
		new ObjectToXML().createXML(listDatabaseModel, nameFileDest + SystemParameter.CAPRONI_XML_EXT);
	}

	public List<DatabaseModel> listaDatabaseModel(File arquivoOrigem) throws IOException {
		List<DatabaseModel> listDatabase = new ArrayList<DatabaseModel>();
		DatabaseModel databaseModel;
		FileReader reader = new FileReader(arquivoOrigem);
		String tmp;
		BufferedReader leitor = new BufferedReader(reader);

		while ((tmp = leitor.readLine()) != null) {
			if (!tmp.equals("")) {
				databaseModel = new DatabaseModel();
				databaseModel.setDatabaseAlias(tmp);
				databaseModel.setDatabaseType(leitor.readLine().split("=")[1]);
				databaseModel.setDatabaseIP(leitor.readLine().split("=")[1]);
				databaseModel.setDatabasePort(Integer.valueOf(leitor.readLine().split("=")[1]));
				databaseModel.setDatabaseUser(leitor.readLine().split("=")[1]);
				databaseModel.setDatabasePasswd(leitor.readLine().split("=")[1]);
				databaseModel.setDatabaseSiglaSistema(leitor.readLine().split("=")[1]);
				databaseModel.setDatabaseReplicado(leitor.readLine().split("=")[1]);
				listDatabase.add(databaseModel);
			}
		}
		leitor.close();
		return listDatabase;
	}

	public List<String> getListCatalogFiles(File file) {
		List<String> listCatalogFiles = new ArrayList<String>();
		for (String fileName : file.list()) {
			listCatalogFiles.add(fileName);
		}
		return listCatalogFiles;
	}

	public boolean deleteDatabaseFile(String nameFile) {
		return new File(nameFile + SystemParameter.CAPRONI_XML_EXT).delete();
	}

	public List<String> returnDateCatalog(String nameFile) {
		ListDatabaseModel listDatabaseModel = (ListDatabaseModel) new XMLToObject(new ListDatabaseModel(),
				nameFile + SystemParameter.CAPRONI_XML_EXT).instanceObjectParsed();
		List<String> dadoRetornado = new ArrayList<String>();

		dadoRetornado.add(listDatabaseModel.getDatabaseModel().get(0).getDatabaseType());
		switch (listDatabaseModel.getDatabaseModel().get(0).getDatabaseSiglaSistema()) {
		case "SG5":
			dadoRetornado.add("SG");
			break;
		case "NETG":
			dadoRetornado.add("SG");
			break;
		case "PG5":
			dadoRetornado.add("PG");
			break;
		case "NET":
			dadoRetornado.add("PG");
			break;
		}
		return dadoRetornado;
	}
}
