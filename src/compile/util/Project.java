/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compile.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Properties;

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
    private String _DB_Properties = "db.properties";
    private HashMap<String,String> _mapDependencies;
    private static final String SEP = File.separator;
    
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
        Command.run( PropertiesFile.getInstance().getPropertiesUtil().getParam( "antCmd" ) ,_pathProject+SEP+"target"+SEP+_webappName+SEP+"WEB-INF"+SEP+"sql");
    }
    
    @Override
    public void adaptDBProperties(){
        
        InputStream inputStream;
        OutputStream outputStream;
        OutputStreamWriter outputStreamWriter;
        
        try{           
            Properties prop = new Properties();
            String propFileName = getPathProject()+SEP+"target"+SEP+_webappName+SEP+"WEB-INF"+SEP+"conf"+SEP+"db.properties";
            String poolUrl = "jdbc:mysql://localhost/"+PropertiesFile.getInstance().getPropertiesUtil().getParam("dbname")+"?autoReconnect=true&useUnicode=yes&characterEncoding=utf8";
            
            inputStream = new FileInputStream(propFileName);
            
            prop.load(inputStream);
            inputStream.close();
            prop.setProperty("portal.url", poolUrl);
            prop.setProperty("portal.user", PropertiesFile.getInstance().getPropertiesUtil().getParam("username"));
            prop.setProperty("portal.password", PropertiesFile.getInstance().getPropertiesUtil().getParam("mdp"));
                        
            outputStream = new FileOutputStream(propFileName);
            outputStreamWriter = new OutputStreamWriter(outputStream);
            
            prop.store(outputStreamWriter, null);
            
            outputStream.close();
            outputStreamWriter.close();
            
            }
        catch (Exception e) {
            System.out.println("Erreur de lecture du fichier de propriété db.properties: " + e);
        }
    
    }
    
}
