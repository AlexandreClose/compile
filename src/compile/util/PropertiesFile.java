/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compile.util;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 *
 * @author closea
 */
public class PropertiesFile{
    
    private static PropertiesFile _instance;
    private static String _fileName;
    private PropertiesUtil _propertiesUtil;
    
    private static final String CONFIG_FILE = "src/compile/properties/config.properties";
    
    public PropertiesFile(){        
    }

    public PropertiesUtil getPropertiesUtil() {
        return _propertiesUtil;
    }

    public void setPropertiesUtil( PropertiesUtil _propertiesUtil ) {
        this._propertiesUtil = _propertiesUtil;
    }
    
    
    public String getFileName() {
        return _fileName;
    }

    public void setFileName( String _fileName ) {
        this._fileName = _fileName;
    }
    
    public static PropertiesFile getInstance() {
        if (_instance != null){
            return _instance;
        }else{
            _instance = new PropertiesFile();
            _instance.setFileName( CONFIG_FILE );
            _instance.setPropertiesUtil(new PropertiesUtil(CONFIG_FILE) );
            
        }
        return _instance;
    }

    
    public boolean mustRestartServer(){
        for (String arg : Arguments.getInstance().getArguments()){
            if (arg.equals(Arguments.getInstance().MARK_RESTART_TOMCAT)){
                return true;
            }
        }
        
        return false;
    }
    
    public boolean mustAnt(){
        for (String arg : Arguments.getInstance().getArguments()){
            if (arg.equals(Arguments.getInstance().MARK_ANT)){
                return true;
            }
        }
        
        return false;
    }
    
    public boolean mustM2(){
        for (String arg : Arguments.getInstance().getArguments()){
            if (arg.equals(Arguments.getInstance().MARK_M2)){
                return true;
            }
        }
        
        return false;
    }
    
    public boolean mustCompile(){
        for (String arg : Arguments.getInstance().getArguments()){
            if (arg.equals(Arguments.getInstance().MARK_COMPILE)){
                return true;
            }
        }
        
        return false;
    }
    
    
    
    public boolean mustAdaptDBProperties(){
        for (String arg : Arguments.getInstance().getArguments()){
            if (arg.equals(Arguments.getInstance().MARK_DB)){
                return true;
            }
        }
        return false;
    }
    
    public boolean mustInitializePluginDat(){
        for (String arg : Arguments.getInstance().getArguments()){
            if (arg.equals(Arguments.getInstance().MARK_PLUGIN_DAT)){
                return true;
            }
        }
        return false;
    }
    
}
