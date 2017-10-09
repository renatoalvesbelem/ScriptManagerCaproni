package br.com.scriptmanagercaproni.model;

import javax.xml.bind.annotation.XmlElement;

public class DatabaseModel {
	private String databaseAlias;
	private String databaseType;
	private String databaseIP;
	private int databasePort;
	private String databaseUser;
	private String databasePasswd;
	private String databaseSiglaSistema;
	private String databaseReplicado;

	@XmlElement(name = "databasePort")
	public int getDatabasePort() {
		return databasePort;
	}

	public void setDatabasePort(int databasePort) {
		this.databasePort = databasePort;
	}

	@XmlElement(name = "databaseAlias")
	public String getDatabaseAlias() {
		return databaseAlias;
	}

	public void setDatabaseAlias(String databaseAlias) {
		this.databaseAlias = databaseAlias;
	}

	@XmlElement(name = "databaseType")
	public String getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}

	@XmlElement(name = "databaseIP")
	public String getDatabaseIP() {
		return databaseIP;
	}

	public void setDatabaseIP(String databaseIP) {
		this.databaseIP = databaseIP;
	}

	@XmlElement(name = "databaseUser")
	public String getDatabaseUser() {
		return databaseUser;
	}

	public void setDatabaseUser(String databaseUser) {
		this.databaseUser = databaseUser;
	}

	@XmlElement(name = "databasePasswd")
	public String getDatabasePasswd() {
		return databasePasswd;
	}

	public void setDatabasePasswd(String databasePasswd) {
		this.databasePasswd = databasePasswd;
	}

	@XmlElement(name = "databaseSiglaSistema")
	public String getDatabaseSiglaSistema() {
		return databaseSiglaSistema;
	}

	public void setDatabaseSiglaSistema(String databaseSiglaSistema) {
		this.databaseSiglaSistema = databaseSiglaSistema;
	}

	@XmlElement(name = "databaseReplicado")
	public String getDatabaseReplicado() {
		return databaseReplicado;
	}

	public void setDatabaseReplicado(String databaseReplicado) {
		this.databaseReplicado = databaseReplicado;
	}

	public String getDatabaseJDBC() {
		switch (databaseType) {
		case "ORACLE":
			return "jdbc:oracle:thin:@" + databaseIP + ":" + databasePort + ":" + databaseSiglaSistema;
		case "SQLSERVER":
			return "jdbc:sqlserver://" + databaseIP + ":" + databasePort + ";databaseName=" + databaseSiglaSistema;
		case "DB2":
			return "jdbc:db2://" + databaseIP + ":" + databasePort + "/" + databaseSiglaSistema;
		}
		return null;

	}

}
