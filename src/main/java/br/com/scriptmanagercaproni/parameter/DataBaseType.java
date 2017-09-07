package br.com.scriptmanagercaproni.parameter;

public interface DataBaseType {
	String ORACLE = "Oracle";
	String SQLSERVER = "SQL Server";
	String DB2 = "DB2";
	String[] ALL_TYPES = {"Selecione um tipo de banco", ORACLE, SQLSERVER, DB2};
	String PG = "PG";
	String SG = "SG";
}
