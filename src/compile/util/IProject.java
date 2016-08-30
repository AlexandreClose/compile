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
public interface IProject{
    public void compile();
    public void ant();
    public void clean();
    public void adaptDBProperties();
    public void pluginDat();
}
