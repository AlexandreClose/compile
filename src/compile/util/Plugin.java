/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compile.util;

import compile.log.LogService;
import java.io.File;

/**
 *
 * @author closea
 */
public class Plugin extends Project{

    private static final String PLUGIN_TYPE = "plugin";
    private static final String SEP = File.separator;
       
    Plugin(){
        super.setMavenCmdCompil( PropertiesFile.getInstance().getPropertiesUtil().getParam( "mvnPluginCompilCmd"));
        super.setTypeProject( PLUGIN_TYPE);
    }
    
    public void installInM2(){
        LogService.pluginInstalledInM2( getArtifactId(), getVersion());
        Command.run(PropertiesFile.getInstance().getPropertiesUtil().getParam( "mvnInstallM2Cmd") ,super.getPathProject());
    }
    
}
