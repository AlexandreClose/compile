/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import compile.util.Arguments;
import compile.util.Plugin;
import compile.util.Pom;
import compile.util.Project;
import compile.util.PropertiesFile;
import compile.util.Tomcat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/**
 *
 * @author closea
 */
public class Compile{


     /**
     * @param args the command line arguments
     */
    
    private static final String POM_FILE = "pom.xml";
    
    public static void main( String[] args ) {
        
        Arguments.setArguments(args);

        if ( PropertiesFile.getInstance().mustRestartServer() ){
            Tomcat.getInstance().stop();
        }
        
        List<Plugin> listInstalledPlugin= new ArrayList<Plugin>(); 
        
        if(PropertiesFile.getInstance().mustM2()){
            for (Map.Entry<String,String> entry : PropertiesFile.getInstance().getMapParameters().entrySet()){
                 if ( entry.getKey().contains( "plugin")){
                     if (entry.getValue()!=null && !entry.getValue().equals("")){
                         Pom pom = new Pom(entry.getValue()+"\\"+POM_FILE);
                         Project project = pom.computeProjectFromPom();
                         Plugin plugin = (Plugin)project;
                         System.out.println("Plugin installé dans M2 : "+plugin.getArtifactId()+" avec la version : "+plugin.getVersion());
                         plugin.installInM2();
                         listInstalledPlugin.add(plugin);
                     }
                 }
             }
        } 
        
        
        for (Plugin plug : listInstalledPlugin){
            for (Map.Entry<String,String> entry : plug.getMapDependencies().entrySet()){
                System.out.println("dependance : "+entry.getKey()+ " version :"+entry.getValue() );
            }
        }
        
        if (PropertiesFile.getInstance().hasParam("workingDir")){
            Pom pom = new Pom(PropertiesFile.getInstance().getParam( "workingDir")+"\\"+POM_FILE);
            Project proj = pom.computeProjectFromPom();
            for (Map.Entry<String,String> entry : proj.getMapDependencies().entrySet()){
                for (Plugin plug : listInstalledPlugin){
                    if (plug.getArtifactId().equals(entry.getKey())){
                        if (!plug.getVersion().equals( entry.getValue() )){
                            System.out.println("Les version du plugin "+plug.getArtifactId()+" ne concordent pas dans le working dir et les plugins M2 !");
                            System.exit( 0 );
                        }
                    }
                }
            }
            proj.compile();
            if ( PropertiesFile.getInstance().mustAdaptDBProperties() ){
                proj.adaptDBProperties();
            }
            if ( PropertiesFile.getInstance().mustAnt() ){
                proj.ant();
            }
        }
  
        
        if ( PropertiesFile.getInstance().mustRestartServer() ){
            Tomcat.getInstance().start();
        }
    }
    
   
}

      
    
