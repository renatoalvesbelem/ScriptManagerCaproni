
package br.com.scriptmanagercaproni.parameter;

public interface DbChangeFile {
    String HEADER_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>";
    String BEGIN_TAG_HAVILLAN = "<havillan>";
    String END_TAG_HAVILLAN = "</havillan>";
    String BEGIN_TAG_SCRIPT = "<script a_name=\"";
    String END_TAG_SCRIPT = "\" version=\"99.99.99-99\" z_description=\"Default\"/>";
}
