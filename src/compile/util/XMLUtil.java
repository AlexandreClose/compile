/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compile.util;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.jdom2.input.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author closea
 */
public class XMLUtil{
    
    private String _strPath;
    private Document _document;
    private Element _racine;

    /**
     * Get the value of _racine
     *
     * @return the value of _racine
     */
    public Element getRacine() {
        return _racine;
    }

    /**
     * Set the value of _racine
     *
     * @param _racine new value of _racine
     */
    public void setRacine( Element racine ) {
        this._racine = racine;
    }


    /**
     * Get the value of _document
     *
     * @return the value of _document
     */
    public Document getDocument() {
        return _document;
    }

    /**
     * Set the value of _document
     *
     * @param _document new value of _document
     */
    public void setDocument( Document document ) {
        this._document = document;
    }

    /**
     * Get the value of _path
     *
     * @return the value of _path
     */
    public String getPath() {
        return _strPath;
    }

    /**
     * Set the value of _path
     *
     * @param path new value of _path
     */
    public void setPath( String strPath ) {
        this._strPath = strPath;
    }
    
    public XMLUtil(String xmlPath){
        _strPath = xmlPath;
        SAXBuilder sxb = new SAXBuilder();
        try
        {
           _document = sxb.build(new File(_strPath));
           _racine = _document.getRootElement();
        }
      catch(Exception e){
          System.out.println("Probleme de lecture avec le fichier XML");
      }
    }
    
    public String getTextNodeChildFromRoot( String nameNode ){
        return _racine.getChildText(nameNode,null);
    }
    public List<Element> getChildrenFromNode( Element node ){
        return node.getChildren();
    }
    
    
    
    public void printAllChildren(){
        for (Object element : _racine.getChildren()) {
            System.out.println(element.toString());
        }
    }
    
    public void save(){
        _document.setContent( _racine );
    try {
        FileWriter writer = new FileWriter(_strPath);
        XMLOutputter outputter = new XMLOutputter();
        outputter.setFormat(Format.getPrettyFormat());
        outputter.output(_document, writer);
        writer.close(); 
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    
    

}
