/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compile.exceptions;

/**
 *
 * @author closea
 */
public class ArgumentInvalidException extends Exception{ 
    
  @Override
  public String getMessage(){
    return "Vous avez entre un argument invalide. Utilisez l'option \"man\" pour avoir des indications sur les arguments à indiquer.";
  }  
}
