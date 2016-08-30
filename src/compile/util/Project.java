/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compile.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author closea
 */
public abstract class Project implements IProject{
    private String _typeProject;
    private String _webappName;
    private String _pathProject;
    private String _mavenCmdCompil;
    private String _mavenCmdClean=PropertiesFile.getInstance().getPropertiesUtil().getParam( "mvnClean");
    private String _version;
    private String _artifactId;
    private HashMap<String,String> _mapDependencies;
    private static final String SEP = File.separator;
    
    public String getArtifactId() {
        return _artifactId;
    }

    public void setArtifactId( String _artifactId ) {
        this._artifactId = _artifactId;
    }

    public String getMavenCmdClean() {
        return _mavenCmdClean;
    }

    public void setMavenCmdClean( String _mavenCmdClean ) {
        this._mavenCmdClean = _mavenCmdClean;
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
    public void clean(){
        Command.run(_mavenCmdClean ,_pathProject);
    }
    
    
    
    @Override
    public void ant(){
        Command.run( PropertiesFile.getInstance().getPropertiesUtil().getParam( "antCmd" ) ,_pathProject+SEP+"target"+SEP+_webappName+SEP+"WEB-INF"+SEP+"sql");
    }
    
    @Override
    public void adaptDBProperties(){
            String propFileName = getPathProject()+SEP+"target"+SEP+_webappName+SEP+"WEB-INF"+SEP+"conf"+SEP+"db.properties";
            String poolUrl = "jdbc:mysql://localhost/"+PropertiesFile.getInstance().getPropertiesUtil().getParam("dbname")+"?autoReconnect=true&useUnicode=yes&characterEncoding=utf8";
            PropertiesUtil dbProperties = new PropertiesUtil(propFileName);
            dbProperties.setParam( "portal.user" ,PropertiesFile.getInstance().getPropertiesUtil().getParam("username")) ;
            dbProperties.setParam( "portal.password" ,PropertiesFile.getInstance().getPropertiesUtil().getParam("mdp")) ;
            dbProperties.setParam( "portal.url" ,poolUrl) ;
            dbProperties.save( propFileName );
    }
    
    @Override
    public void pluginDat( ){
        List<String> listPlugins = new ArrayList<String>();
        String pathPluginXmlFiles = getPathProject()+SEP+"target"+SEP+_webappName+SEP+"WEB-INF"+SEP+"plugins"+SEP;
        String pathPluginDat = pathPluginXmlFiles + "plugin.dat";
        File folder = new File(pathPluginXmlFiles);
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
          if (listOfFiles[i].isFile()) {
            if (listOfFiles[i].getName().contains( ".xml")){
                System.out.println(listOfFiles[i].getName());
                listPlugins.add(listOfFiles[i].getName().split("\\.")[0]);
            }
          } 
        }
        PropertiesUtil pluginDatProperties = new PropertiesUtil();
        for (String plugin : listPlugins){
            pluginDatProperties.setParam(plugin+".installed","1");
        }
        pluginDatProperties.save( pathPluginDat );
    }
    
}
