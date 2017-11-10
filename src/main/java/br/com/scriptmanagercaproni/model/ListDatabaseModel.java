package br.com.scriptmanagercaproni.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "databaseList")
public class ListDatabaseModel {
	private List<DatabaseModel> databaseModel;

	@XmlElement(name = "database")
	public List<DatabaseModel> getDatabaseModel() {
		return databaseModel;
	}

	public void setDatabaseModel(List<DatabaseModel> databaseModel) {
		this.databaseModel = databaseModel;
	}
}
