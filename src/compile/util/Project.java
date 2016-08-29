/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compile.util;

import java.util.HashMap;

/**
 *
 * @author closea
 */
public abstract class Project implements IProject{
    private String _typeProject;
    private String _webappName;
    private String _pathProject;
    private String _mavenCmdCompil;
    private String _version;
    private String _artifactId;
    private HashMap<String,String> _mapDependencies;
    
    public String getArtifactId() {
        return _artifactId;
    }

    public void setArtifactId( String _artifactId ) {
        this._artifactId = _artifactId;
    }
    
    

    public HashMap<String , String> getMapDependencies() {
        return _mapDependencies;
    }

    public void setMapDependancies( HashMap<String , String> _mapDependencies ) {
        this._mapDependencies = _mapDependencies;
    }


    public String getVersion() {
        return _version;
    }

    public void setVersion( String _version ) {
        this._version = _version;
    }
   
    
    public String getTypeProject() {
        return _typeProject;
    }

    public void setTypeProject( String _typeProject ) {
        this._typeProject = _typeProject;
    }

    public String getMavenCmdCompil() {
        return _mavenCmdCompil;
    }

    public void setMavenCmdCompil( String _mavenCmdCompil ) {
        this._mavenCmdCompil = _mavenCmdCompil;
    }

    public String getWebappName() {
        return _webappName;
    }

    public void setWebappName( String _webappName ) {
        this._webappName = _webappName;
    }

    public String getPathProject() {
        return _pathProject;
    }

    public void setPathProject( String _pathProject ) {
        this._pathProject = _pathProject;
    }
    
    
    @Override
    public void compile(){
        Command.run(_mavenCmdCompil ,_pathProject);
    }
    
    @Override
    public void ant(){
        Command.run( PropertiesFile.getInstance().getParam( "antCmd" ) ,_pathProject+"\\target\\"+_webappName+"\\WEB-INF\\sql");
    }
    
    @Override
    public void adaptDBProperties(){
        
    }
    
    @Override
    public void adaptDBProperties(String pathDbProperties){
        
    }
    
    
    
}
