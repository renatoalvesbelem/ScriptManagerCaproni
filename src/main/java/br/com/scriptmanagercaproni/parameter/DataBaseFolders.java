
package br.com.scriptmanagercaproni.parameter;

import java.util.Arrays;

public interface DataBaseFolders {
	String[] FOLDER_ORACLEPG = { "NET", "PG5TINT", "INDTINT" };
	String[] FOLDER_ORACLESG = { "NETTING", "SG5TINTG", "INDTING" };

	String[] FOLDER_SQLSERVERPG = { "NET", "PG5TINT", "INDTINT", "PG5TINT2", "PG5TINT3" };
	String[] FOLDER_SQLSERVERSG = { "NETTING", "SG5TINTG", "INDTING" };

	String[] FOLDER_DB2PG = Arrays.copyOf(FOLDER_SQLSERVERPG, FOLDER_SQLSERVERPG.length);

	String[] FOLDER_DB2SG = Arrays.copyOf(FOLDER_SQLSERVERSG, FOLDER_SQLSERVERSG.length);
}
