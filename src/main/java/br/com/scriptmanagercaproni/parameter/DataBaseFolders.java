
package br.com.scriptmanagercaproni.parameter;

import java.util.Arrays;

public interface DataBaseFolders {
	String[] FOLDER_ORACLEPG = { "NET", "PG5", "IND" };
	String[] FOLDER_ORACLESG = { "NETSG", "SG5", "INDSG" };

	String[] FOLDER_SQLSERVERPG = { "NET", "PG5", "IND", "PG52", "PG53" };
	String[] FOLDER_SQLSERVERSG = { "NETSG", "SG5", "INDSG" };

	String[] FOLDER_DB2PG = Arrays.copyOf(FOLDER_SQLSERVERPG, FOLDER_SQLSERVERPG.length);

	String[] FOLDER_DB2SG = Arrays.copyOf(FOLDER_SQLSERVERSG, FOLDER_SQLSERVERSG.length);
}
