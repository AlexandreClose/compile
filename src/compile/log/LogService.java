/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compile.log;

/**
 *
 * @author closea
 */
public class LogService{
    public static void tomcatStop(){System.out.println("[TOMCAT] Extinction du serveur Tomcat");}
    public static void tomcatStart(){System.out.println("[TOMCAT] Demmarage du serveur Tomcat");}
    public static void tomcatAddingContext(String context){System.out.println("[TOMCAT] Ajout du contexte "+context+" pour le workDir");}
    
    public static void pluginInstalledInM2(String artifactId, String version){System.out.println("[MVN_INSTALL] plugin -> M2 : "+artifactId+"      version : "+version);}
    public static void pomType(String artifactId,String type){System.out.println("[MVN_POM_TYPE] Pom de "+type+" -> "+artifactId);}
    public static void cleanWorkDir(){System.out.println("[MVN_CLEAN] Nettoyage du target");}
    public static void compile(String artifactId,String version){System.out.println("[MVN_COMPILE] "+artifactId+ " version "+version);}
    
    public static void ant(){System.out.println("[ANT] Execution des scripts Ant");}
    
    public static void adaptDB(String user, String mdp, String dbName){System.out.println("[DB_PROPERTIES] MAJ db.properties   user "+user+" mdp "+mdp+" db_name "+dbName);}
    public static void pluginDat(){System.out.println("[PLUGINS_DAT] Initialisation du fichier plugins_dat.");}
    
    public static void wrongVersions(String artifactId, String versionWorkDir, String versionPluginInstalled){System.out.println("[ERROR_MVN_COMPARE_VERSIONS] "+artifactId+ " versionWorkDir "+versionWorkDir+" versionPlugin "+versionPluginInstalled);}
    
    public static void endProcess(){System.out.println("[END_PROCESS]");}
}
