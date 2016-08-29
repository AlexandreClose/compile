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
            
    private String _profile;

    public String getProfile() {
        return _profile;
    }

    public void setProfile( String _profile ) {
        this._profile = _profile;
    }
    
    Site(){
        super.setTypeProject( "site");
        super.setMavenCmdCompil( PropertiesFile.getInstance().getPropertiesUtil().getParam( "mvnSiteCompilCmd" ));
    }
    
    @Override
    public void adaptDBProperties(String pathDbProperties){
        
    }

}
