/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compile.util;

import java.util.HashMap;
import java.util.List;
import org.jdom2.Element;

/**
 *
 * @author closea
 */
public class Pom{
    
    private String _strPath;
    private IProject _project;
    private XMLUtil _xmlUtil;

    /**
     * Get the value of _xmlUtil
     *
     * @return the value of _xmlUtil
     */
    public XMLUtil getXmlUtil() {
        return _xmlUtil;
    }

    /**
     * Set the value of _xmlUtil
     *
     * @param xmlUtil new value of _xmlUtil
     */
    public void setXmlUtil( XMLUtil xmlUtil ) {
        this._xmlUtil = xmlUtil;
    }


    /**
     * Get the value of _project
     *
     * @return the value of _project
     */
    public IProject getProject() {
        return _project;
    }

    /**
     * Set the value of _project
     *
     * @param _project new value of _project
     */
    public void setProject( IProject _project ) {
        this._project = _project;
    }



     /** Get the value of _strPath
     *
     * @return the value of _strPath
     */
    public String getStrPath() {
        return _strPath;
    }

    /**
     * Set the value of _strPath
     *
     * @param strPath new value of _strPath
     */
    public void setStrPath( String strPath ) {
        this._strPath = strPath;
    }
    
    public Pom(String pomPath){
        _strPath = pomPath;
        _xmlUtil = new XMLUtil(_strPath);
    }
    
    public String getParentArtifactId(){
        Element racine = _xmlUtil.getRacine();
        Element projectParent = racine.getChild( "parent" , null );
        Element parentArtifactId = projectParent.getChild( "artifactId",null);
        return parentArtifactId.getText();
    }
    
    public String getArtifactId(){
        Element racine = _xmlUtil.getRacine();
        Element projectArtifactId = racine.getChild( "artifactId" , null );
        return projectArtifactId.getText();
    }
    public String getVersion(){
        Element racine = _xmlUtil.getRacine();
        Element projectVersion = racine.getChild( "version" , null );
        return projectVersion.getText();
    }
    
    public HashMap<String,String> getMapDependencies(){
        HashMap<String,String> mapDependenciesProject = new HashMap<String,String>();
        Element racine = _xmlUtil.getRacine();
        Element projectDependencies = racine.getChild( "dependencies" , null );
        List<Element> listProjectDependencies = projectDependencies.getChildren("dependency");
        for (Element elem : listProjectDependencies ){
            mapDependenciesProject.put(elem.getChild("artifactId").getText(),elem.getChild("version").getText());
        }
        return mapDependenciesProject;
    }
    
    public Project computeProjectFromPom(){
        Project project;
        if (getParentArtifactId().equals( "lutece-site-pom")){
            project = new Site();
        } else if (getParentArtifactId().equals( "lutece-global-pom")){
            project = new Plugin();
        }
        else{return null;}
        project.setPathProject(PropertiesFile.getInstance().getParam( "workingDir") );
        project.setWebappName( getWebAppNameFromProjectType(project));
        project.setVersion(getVersion());
        project.setMapDependancies( getMapDependencies() );
        project.setArtifactId( getArtifactId());
        return project;
    }
    
    public String getWebAppNameFromProjectType(Project proj){
        if (proj.getTypeProject().equals( "plugin")){
            return "lutece";
        }
        else if (proj.getTypeProject().equals( "site")){
            return getArtifactId()+"-"+getVersion();
        }
        else{return null;}
    }
    


}
