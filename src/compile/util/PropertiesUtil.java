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
public class PropertiesUtil{
    
    private Properties _properties;

    public Properties getProperties() {
        return _properties;
    }

    public void setProperties( Properties _properties ) {
        this._properties = _properties;
    }
    
    
    
    public PropertiesUtil(String fileName){
        InputStream inputStream;
        try{
           
            Properties prop = new Properties();
            String propFileName = fileName;
            
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                prop.load(inputStream);
                inputStream.close();
                _properties=prop;
            }
            else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
        }catch (Exception e) {
			System.out.println("Exception: " + e);
		} 
        

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
