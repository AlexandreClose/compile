/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compile.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
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
            inputStream = new FileInputStream(fileName);
            if (inputStream != null) {
                prop.load(inputStream);
                inputStream.close();
                _properties=prop;
            }
            else {
                throw new FileNotFoundException("property file '" + fileName + "' not found in the classpath");
            }
        }catch (Exception e) {
			System.out.println("Exception: " + e);
	} 
        

    }
    public PropertiesUtil(){
        _properties = new Properties();
    }
    

    public String getParam(String paramName){
        return _properties.getProperty(paramName );
    }
    
    public void showProperties(){
        for (String key : _properties.stringPropertyNames()){
            System.out.println("key : " + key + "   |   " + _properties.getProperty( key ));
        }
    }
    
    public void setParam(String strKey, String strValue){
        _properties.setProperty(strKey , strValue );
    }
    
    public boolean hasProperty(String param){
        if (_properties.containsKey( param )){
            return true;
        }
        return false;
    }
    
    public void save(String fileName){
        PrintWriter printWriter;
        System.out.println("testtestest");
        try{
            printWriter = new PrintWriter(new FileWriter(fileName));
            
            for (String key : _properties.stringPropertyNames()){
                printWriter.println(key + "=" + _properties.getProperty( key ));
            }
            
            printWriter.close();
        }
        catch (Exception e) {
            System.out.println("Erreur d'écriture du fichier " + fileName);
        }
    }
}
