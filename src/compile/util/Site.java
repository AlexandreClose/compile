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
public class Site extends Project{
    private static final String MAVEN_CMD_PROFILE = "-P";
            
    private String _profile=null;

    public String getProfile() {
        return _profile;
    }

    public void setProfile( String _profile ) {
        this._profile = _profile;
    }
    
    Site(){
        super.setTypeProject( "site");
        
        if (PropertiesFile.getInstance().getPropertiesUtil().hasProperty( "profile")){
            setProfile(PropertiesFile.getInstance().getPropertiesUtil().getParam( "profile") );
            super.setMavenCmdCompil( PropertiesFile.getInstance().getPropertiesUtil().getParam( "mvnSiteCompilCmd" )+" "+PropertiesFile.getInstance().getPropertiesUtil().getParam( "mvnProfileCmd" )+" "+PropertiesFile.getInstance().getPropertiesUtil().getParam( "profile" ) + " " +PropertiesFile.getInstance().getPropertiesUtil().getParam( "mvnSiteLogCmd" ));
        }
        else{
            super.setMavenCmdCompil( PropertiesFile.getInstance().getPropertiesUtil().getParam( "mvnSiteCompilCmd" ) + " " + PropertiesFile.getInstance().getPropertiesUtil().getParam( "mvnSiteLogCmd" ));
        }  
    }
}
