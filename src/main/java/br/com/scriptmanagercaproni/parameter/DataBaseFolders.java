
package br.com.scriptmanagercaproni.parameter;

import static java.util.Arrays.copyOf;

@SuppressWarnings({"deprecation", "Since15"})
public interface DataBaseFolders {
    String[] FOLDER_ORACLEPG = {"NET", "PG5", "IND"};
    String[] FOLDER_ORACLESG = {"NETG", "SG5", "INDG"};
    String[] FOLDER_SQLSERVERPG = {"NET", "PG5", "IND", "PG52", "PG53"};
    String[] FOLDER_SQLSERVERSG = {"NETG", "SG5", "INDG"};
    String[] FOLDER_DB2PG = copyOf(FOLDER_SQLSERVERPG, FOLDER_SQLSERVERPG.length);
    String[] FOLDER_DB2SG = copyOf(FOLDER_SQLSERVERSG, FOLDER_SQLSERVERSG.length);
}