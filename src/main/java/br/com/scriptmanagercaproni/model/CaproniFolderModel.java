package br.com.scriptmanagercaproni.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "caproniConfig")
public class CaproniFolderModel {

    private @SuppressWarnings("deprecation")
    String pathFolder;
    private @SuppressWarnings("deprecation")
    String pathScriptFolder;

    @SuppressWarnings("deprecation")
    @XmlElement(name = "caproniFolder")
    public String getPathFolder() {
        return pathFolder;
    }

    public void setPathFolder(@SuppressWarnings("deprecation") String pathFolder) {
        this.pathFolder = pathFolder;
    }

    @XmlElement(name = "scriptFolder")
    public @SuppressWarnings("deprecation")
    String getPathScriptFolder() {
        return pathScriptFolder;
    }

    public void setPathScriptFolder(@SuppressWarnings("deprecation") String pathScriptFolder) {
        this.pathScriptFolder = pathScriptFolder;
    }

}
