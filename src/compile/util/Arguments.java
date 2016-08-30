/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compile.util;

import compile.exceptions.ArgumentInvalidException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author closea
 */
public class Arguments{
    
    private static Arguments _instance;
    private static String[] _arguments;
    public final String MARK_RESTART_TOMCAT = "--restart";
    public final String MARK_ANT = "--ant";
    public final String MARK_COMPILE = "--compile";
    public final String MARK_M2 = "--m2";
    public final String MARK_DB = "--db";
    public final String MARK_PLUGIN_DAT = "--pluginDat";
    public final String MARK_MAN = "man";
    
    

    public String[] getArguments() {
        return _arguments;
    }
    
    private Arguments(){
    }
    
    public void checkArgument () throws ArgumentInvalidException {
        List<String> tabMarks = Arrays.asList(MARK_RESTART_TOMCAT, MARK_ANT, MARK_M2,MARK_DB,MARK_MAN,MARK_COMPILE,MARK_PLUGIN_DAT);
        for (String strArgument : getArguments()){
            if (!tabMarks.contains( strArgument )){
                throw new ArgumentInvalidException();
            }
        }
    }
    
    public void checkMan(){
        for (String strArgument : getArguments()){
            if (strArgument.equals( MARK_MAN )){
                System.out.println(Manuel.getManuel());
                System.exit( 0 );
            }
        }
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
