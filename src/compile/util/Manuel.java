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
public class Manuel{
    public static String getManuel(){
        StringBuilder strBuild = new StringBuilder();
        strBuild.append("Bienvenue dans le manuel du logiciel de compilation rapide pour Lutece");
        strBuild.append("\n");
        strBuild.append("Les options pour la compilation sont :");
        strBuild.append("\n");
        strBuild.append("--restart : extinction du serveur Tomcat puis redemarrage du serveur Tomcat à la fin du process");
        strBuild.append("\n");
        strBuild.append("--m2 : installation des plugins listés dans le fichier de configuration dans le répertoire m2");
        strBuild.append("\n");
        strBuild.append("--db : modification du fichier db.properties du target du répertoire de travail en fonction des champs configurés dans le fichier de configuration");
        strBuild.append("\n");
        strBuild.append("--ant : execution des scripts ant (build.xml) du target du répertoire de travail");
        strBuild.append("\n");
        return strBuild.toString();
    }
}
