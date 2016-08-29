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
    private Properties _properties;
    
    private static final String CONFIG_FILE = "config.properties";
    
    public PropertiesFile(){        
    }
    
    public Properties getProperties() {
        return _properties;
    }

    public void setProperties( Properties _properties ) {
        this._properties = _properties;
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
            _instance.setProperties(_instance.loadProperties() );
            
        }
        return _instance;
    }

    
    
    
    /**
     * return MapOfProperties
     * @param fileName the relative path of 
     */
    public Properties loadProperties(  ) {
        HashMap<String,String> map = new HashMap<String,String>();
        InputStream inputStream;
        try{
           
            Properties prop = new Properties();
            String propFileName = "config.properties";
            
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                prop.load(inputStream);
                inputStream.close();
                return prop;
            }
            else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
        }catch (Exception e) {
			System.out.println("Exception: " + e);
		} 
        
 
        return null;
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
    
    
    
    public String getParam(String paramName){
        return _properties.getProperty(paramName );
    }
    
    public void showProperties(){
        for (String key : _properties.stringPropertyNames()){
            System.out.println("key : " + key + "   |   " + _properties.getProperty( key ));
        }
    }
    
    public boolean hasProperty(String param){
        if (_properties.containsKey( param )){
            return true;
        }
        return false;
    }
}
