package br.com.scriptmanagercaproni.parameter;

public interface DataBaseType {
	String ORACLE = "ORACLE";
	String FOLDER_ORACLE = "ORACLE";
	String SQLSERVER = "SQLSERVER";
	String FOLDER_SQLSERVER = "SQLSERVER";
	String DB2 = "DB2";
	String FOLDER_DB2 = "DB2";
	String[] ALL_TYPES = { "Selecione um tipo de banco", ORACLE, SQLSERVER, DB2 };
	String PG = "PG";
	String SG = "SG";
	String PG5 = "PG5";
	String SG5 = "SG5";
}
