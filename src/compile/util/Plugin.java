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
public class Plugin extends Project{

    private static final String PLUGIN_TYPE = "plugin";
    
    Plugin(){
        super.setMavenCmdCompil( PropertiesFile.getInstance().getPropertiesUtil().getParam( "mvnPluginCompilCmd"));
        super.setTypeProject( PLUGIN_TYPE);
    }
    
    public void installInM2(){
        Command.run(PropertiesFile.getInstance().getPropertiesUtil().getParam( "mvnInstallM2Cmd") ,super.getPathProject());
    }
    
    @Override
    public void adaptDBProperties(){
        super.adaptDBProperties(getPathProject()+"\\target\\lutece\\WEB-INF\\conf\\db.properties");
    }
    
}
