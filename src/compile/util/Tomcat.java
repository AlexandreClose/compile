/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compile.util;

import java.util.List;
import org.jdom2.Element;

/**
 *
 * @author closea
 */
public class Tomcat{
    
    private static final String TOMCAT_START = "startup.bat";
    
    private String _tomcatPath;
    private static Tomcat _instance;
    private String _tomcatStartUpCmd;
    private String _tomcatShutdownCmd;
    private XMLUtil _serverXml;
    
    public static Tomcat getInstance() {
        if (_instance != null){
            return _instance;
        }else{
            _instance = new Tomcat();
        }
        return _instance;
    }

    public String getTomcatStartUpCmd() {
        return _tomcatStartUpCmd;
    }

    public void setTomcatStartUpCmd( String _tomcatStartUpCmd ) {
        this._tomcatStartUpCmd = _tomcatStartUpCmd;
    }

    public String getTomcatShutdownCmd() {
        return _tomcatShutdownCmd;
    }

    public void setTomcatShutdownCmd( String _tomcatShutdownCmd ) {
        this._tomcatShutdownCmd = _tomcatShutdownCmd;
    }


    public String getTomcatPath() {
        return _tomcatPath;
    }

    public void setTomcatPath( String _tomcatPath ) {
        this._tomcatPath = _tomcatPath;
    }
    
    private Tomcat( ){
        _tomcatPath=PropertiesFile.getInstance().getParam( "tomcatPath" );
        _tomcatStartUpCmd=PropertiesFile.getInstance().getParam( "tomcatStartUpCmd" );
        _tomcatShutdownCmd=PropertiesFile.getInstance().getParam( "tomcatShutdownCmd" );
        _serverXml = new XMLUtil(PropertiesFile.getInstance().getParam( "tomcatServerXmlPath" ));
    }
    
    public void start(){
        
        Command.run( getTomcatStartUpCmd(  ), _tomcatPath );
        
    }
    
    public void stop(){
        Command.run( getTomcatShutdownCmd(),_tomcatPath);
        
    }
    
    public void checkIfInContextFile(Project proj){
        Element racine = _serverXml.getRacine();
        Element service = racine.getChild( "Service" , null );
        Element engine = service.getChild( "Engine" , null );
        Element host = engine.getChild( "Host" , null );
        List<Element> listContext = host.getChildren( "Context" , null );
        boolean notInContext = true;
        for (Element context : listContext){
            if(context.getAttributeValue( "docBase" ).equals(proj.getPathProject()+"\\target\\"+proj.getWebappName())){
                notInContext=false;
            }
        }
        if (notInContext){          
            host.addContent( new Element("Context")
                    .setAttribute("docBase",proj.getPathProject()+"\\target\\"+proj.getWebappName() )
                    .setAttribute( "path", PropertiesFile.getInstance().getParam( "workingDirContext" ))
                    .setAttribute( "reloadable", "true"));
            _serverXml.save();
        }
    }
    
       
}