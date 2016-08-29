/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compile.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 *
 * @author closea
 */
public class PropertiesFile{
    
    private static PropertiesFile _instance;
    private static String _fileName;
    private HashMap<String,String> _mapParameters;
    
    private static final String CONFIG_FILE = "config.properties";
    private static final String MARK_RESTART_TOMCAT = "--restart";
    private static final String MARK_ANT = "--ant";
    private static final String MARK_M2 = "--m2";
    private static final String MARK_DB = "--db";
    
            

    public HashMap<String , String> getMapParameters() {
        return _mapParameters;
    }

    public void setMapParameters( HashMap<String , String> _mapParameters ) {
        this._mapParameters = _mapParameters;
    }
    
    public PropertiesFile(){        
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
            _instance.setMapParameters( _instance.getPropertiesMap() );
            
        }
        return _instance;
    }

    
    
    
    /**
     * return MapOfProperties
     * @param fileName the relative path of 
     */
    public HashMap<String,String> getPropertiesMap(  ) {
        HashMap<String,String> map = new HashMap<String,String>();
        try{
            
            InputStream inputStream = new FileInputStream( _fileName );
            InputStreamReader inputStreamReader = new InputStreamReader (inputStream);
            String line = null;
            try{
                BufferedReader bufferReader = new BufferedReader(inputStreamReader);
                 while ((line = bufferReader.readLine()) != null) {
                    String[] tab = line.split("=");
                    if (tab.length == 2){
                        map.put( tab[0] , tab[1]);
                    }
                    else{
                        
                    }
                    
                }       
            }catch(Exception e){
                 e.printStackTrace();
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Fichier de configuration manquant");
        }
        return map;
    }
    
    public boolean mustRestartServer(){
        for (String arg : Arguments.getArguments()){
            if (arg.equals(MARK_RESTART_TOMCAT)){
                return true;
            }
        }
        
        return false;
    }
    
    public boolean mustAnt(){
        for (String arg : Arguments.getArguments()){
            if (arg.equals(MARK_ANT)){
                return true;
            }
        }
        
        return false;
    }
    
    public boolean mustM2(){
        for (String arg : Arguments.getArguments()){
            if (arg.equals(MARK_M2)){
                return true;
            }
        }
        
        return false;
    }
    
    public boolean mustAdaptDBProperties(){
        for (String arg : Arguments.getArguments()){
            if (arg.equals(MARK_DB)){
                return true;
            }
        }
        return false;
    }
    
    
    
    public String getParam(String paramName){
        return _mapParameters.get( paramName );
    }
    
    public void showMapParams(){
        for (Entry<String,String> entry : _mapParameters.entrySet()){
            System.out.println("key : " + entry.getKey() + "   |   " + entry.getValue());
        }
    }
    
    public boolean hasParam(String param){
        if (_mapParameters.containsKey( param )){
            if (_mapParameters.get( param )!=null && !_mapParameters.get( param ).equals("")){
                return true;
            }
        }
        return false;
    }
}
