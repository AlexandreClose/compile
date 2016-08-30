/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import compile.exceptions.ArgumentInvalidException;
import compile.util.Arguments;
import compile.util.Plugin;
import compile.util.Pom;
import compile.util.Project;
import compile.util.PropertiesFile;
import compile.util.Tomcat;
import java.io.File;
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
    private static final String SEP = File.separator;
    
    public static void main( String[] args ) {
        
        String[] test = {"--pluginDat"};
        Arguments.getInstance().setArguments(test);
        
        //Check si les arguments passés existent dans les possibilites du soft
        try{
            Arguments.getInstance().checkArgument();
        }
        catch (ArgumentInvalidException e){
            System.out.println(e.getMessage());
            System.exit( 0 );
        }
        
        //check si l'utilisateur fait appel au manuel d'utilisation
        Arguments.getInstance().checkMan();

        
        PropertiesFile.getInstance().getPropertiesUtil().showProperties();
        
        //Eteint le server Tomcat si c'est demandé par l'argument --restart
        if ( PropertiesFile.getInstance().mustRestartServer() ){
            Tomcat.getInstance().stop();
        }
        
        //Si l'utilisateur veut installer des plugins de travail dans le répertoire m2
        List<Plugin> listInstalledPlugin= new ArrayList<Plugin>(); 
        if(PropertiesFile.getInstance().mustM2()){
            for (String key : PropertiesFile.getInstance().getPropertiesUtil().getProperties().stringPropertyNames()){
                 if ( key.contains( "plugin")){
                     if (PropertiesFile.getInstance().getPropertiesUtil().getProperties().get( key )!=null && !PropertiesFile.getInstance().getPropertiesUtil().getProperties().get( key ).equals("")){
                         Pom pom = new Pom(PropertiesFile.getInstance().getPropertiesUtil().getProperties().get( key )+SEP+POM_FILE);
                         Project project = pom.computeProjectFromPom();
                         Plugin plugin = (Plugin)project;
                         System.out.println("Plugin installé dans M2 : "+plugin.getArtifactId()+" avec la version : "+plugin.getVersion());
                         plugin.installInM2();
                         listInstalledPlugin.add(plugin);
                     }
                 }
             }
        } 
        
        //Les plugins de travail sont listés avec leur version
        for (Plugin plug : listInstalledPlugin){
            for (Map.Entry<String,String> entry : plug.getMapDependencies().entrySet()){
                System.out.println("dependance : "+entry.getKey()+ " version :"+entry.getValue() );
            }
        }
        
        if (PropertiesFile.getInstance().getPropertiesUtil().hasProperty("workingDir")){
            Pom pom = new Pom(PropertiesFile.getInstance().getPropertiesUtil().getParam( "workingDir")+SEP+POM_FILE);
            //Récupere le projet (site ou plugin) du pom
            Project proj = pom.computeProjectFromPom();
            //Ajoute le site ou projet dans le contexte du Tomcat si n'est pas déjà existant
            Tomcat.getInstance().checkIfInContextFile(proj);
            for (Map.Entry<String,String> entry : proj.getMapDependencies().entrySet()){
                for (Plugin plug : listInstalledPlugin){
                    if (plug.getArtifactId().equals(entry.getKey())){
                        //Si les versions des plugins (entre le m2 et le site/plugin principal) ne sont pas les mêmes.
                        if (!plug.getVersion().equals( entry.getValue() )){
                            System.out.println("Les version du plugin "+plug.getArtifactId()+" ne concordent pas dans le working dir et les plugins M2 !");
                            System.exit( 0 );
                        }
                    }
                }
            }
            
            //Pour compiler le projet
            if ( PropertiesFile.getInstance().mustCompile() ){
                proj.compile();
            }

            //Pour changer les valeurs du DBProperties du target
            if ( PropertiesFile.getInstance().mustAdaptDBProperties() ){
                proj.adaptDBProperties();
            }
            
            //Pour executer les scripts ant
            if ( PropertiesFile.getInstance().mustAnt() ){
                proj.ant();
            }
            
            //Pour initialiser le fichier plugin.dat avec tous les plugins installés
            if ( PropertiesFile.getInstance().mustInitializePluginDat() ){
                proj.pluginDat();
            }
        }
        
        //Allume le serveur si c'est demandé par l'argument --restart
        if ( PropertiesFile.getInstance().mustRestartServer() ){
            Tomcat.getInstance().start();
        }
    }
    
   
}

      
    
