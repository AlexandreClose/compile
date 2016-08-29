/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compile.util;

/**
 *
 * @author closea
 */
public class Arguments{
    
    private static Arguments _instance;
    private static String[] _arguments;
    public final String MARK_RESTART_TOMCAT = "--restart";
    public final String MARK_ANT = "--ant";
    public final String MARK_M2 = "--m2";
    public final String MARK_DB = "--db";

    public String[] getArguments() {
        return _arguments;
    }
    
    private Arguments(){
    }

    public void setArguments( String[] _arguments ) {
        Arguments._arguments = _arguments;
    }
    public static Arguments getInstance() {
        if (_instance != null){
            return _instance;
        }else{
            _instance = new Arguments();
            
        }
        return _instance;
    }
    
    
    
}
