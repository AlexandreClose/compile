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
    private static String[] _arguments;

    public static String[] getArguments() {
        return _arguments;
    }

    public static void setArguments( String[] _arguments ) {
        Arguments._arguments = _arguments;
    }
    
    
}
