/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package engine;

import javax.swing.JFrame;

/**
 *
 * @author Eduardo Ferreira
 */
public class Window extends JFrame {
    
    private static Window instance;
    
    public Window(String name, int width, int height){
        super(name);
        setSize(width,height);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.createBufferStrategy(2);
        instance = this;
    }
       
    public static Window getInstance(){
        return instance;
    }
    
}
