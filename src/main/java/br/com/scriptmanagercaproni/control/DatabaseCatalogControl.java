package br.com.scriptmanagercaproni.control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.objectfile.xml.ObjectToXML;
import br.com.objectfile.xml.XMLToObject;
import br.com.scriptmanagercaproni.model.DatabaseModel;
import br.com.scriptmanagercaproni.model.ListDatabaseModel;
import br.com.scriptmanagercaproni.parameter.SystemParameter;

public class DatabaseCatalogControl {

	public void catalogDatabase(File arquivoOrigem, @SuppressWarnings("deprecation") String nameFileDest) {
		ListDatabaseModel listDatabaseModel = new ListDatabaseModel();

		try {
			listDatabaseModel.setDatabaseModel(listaDatabaseModel(arquivoOrigem));
			if (new File(nameFileDest + SystemParameter.CAPRONI_XML_EXT).exists()) {
				if (JOptionPane.showConfirmDialog(null, "O arquivo já existe, deseja sobrescrever o catalogo?",
						"WARNING", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					saveFile(listDatabaseModel, nameFileDest);
				}
			} else {
				saveFile(listDatabaseModel, nameFileDest);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void saveFile(ListDatabaseModel listDatabaseModel, @SuppressWarnings("deprecation") String nameFileDest) {
		new ObjectToXML().createXML(listDatabaseModel, nameFileDest + SystemParameter.CAPRONI_XML_EXT);
		JOptionPane.showMessageDialog(null, "Dados catalogado com sucesso");
	}

	public List<DatabaseModel> listaDatabaseModel(File arquivoOrigem) throws IOException {
		List<DatabaseModel> listDatabase = new ArrayList<DatabaseModel>();
		DatabaseModel databaseModel;
		FileReader reader = new FileReader(arquivoOrigem);
		@SuppressWarnings("deprecation") String tmp;
		BufferedReader leitor = new BufferedReader(reader);

		while ((tmp = leitor.readLine()) != null) {
			if (!tmp.equals("")) {
				databaseModel = new DatabaseModel();
				databaseModel.setDatabaseAlias(tmp);
				databaseModel.setDatabaseType(leitor.readLine().split("=")[1]);
				databaseModel.setDatabaseIP(leitor.readLine().split("=")[1]);
				databaseModel.setDatabasePort(Integer.valueOf(leitor.readLine().split("=")[1]));
				if ((tmp = leitor.readLine()).equals("Instancia=ISAJ01")) {
					databaseModel.setDatabaseUser(leitor.readLine().split("=")[1]);
				} else {
					databaseModel.setDatabaseUser(tmp.split("=")[1]);
				}
				databaseModel.setDatabasePasswd(leitor.readLine().split("=")[1]);
				databaseModel.setDatabaseSiglaSistema(leitor.readLine().split("=")[1]);
				databaseModel.setDatabaseReplicado(leitor.readLine().split("=")[1]);
				listDatabase.add(databaseModel);
			}
		}
		leitor.close();
		return listDatabase;
	}

	public @SuppressWarnings("deprecation") List<String> getListCatalogFiles(File file) {
		List<String> listCatalogFiles = new ArrayList<String>();
		for (String fileName : file.list()) {
			listCatalogFiles.add(fileName);
		}
		return listCatalogFiles;
	}

	public boolean deleteDatabaseFile(@SuppressWarnings("deprecation") String nameFile) {
		return new File(nameFile + SystemParameter.CAPRONI_XML_EXT).delete();
	}

	public @SuppressWarnings("deprecation")  List<String> returnDateCatalog(@SuppressWarnings("deprecation")  String nameFile) {
		ListDatabaseModel listDatabaseModel = (ListDatabaseModel) new XMLToObject(new ListDatabaseModel(),
				nameFile + SystemParameter.CAPRONI_XML_EXT).instanceObjectParsed();
		@SuppressWarnings("deprecation")List<String> dadoRetornado = new ArrayList<String>();

		//noinspection deprecation
		dadoRetornado.add(listDatabaseModel.getDatabaseModel().get(0).getDatabaseType());
		@SuppressWarnings("deprecation") String s = listDatabaseModel.getDatabaseModel().get(0).getDatabaseSiglaSistema();
		if (s.equals("SG5")) {
			dadoRetornado.add("SG");

		} else if (s.equals("NETG")) {
			dadoRetornado.add("SG");

		} else if (s.equals("PG5")) {
			dadoRetornado.add("PG");

		} else if (s.equals("NET")) {
			dadoRetornado.add("PG");
		}
		return dadoRetornado;
	}
}
