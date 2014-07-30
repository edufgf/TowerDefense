/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package engine;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import javax.swing.JLabel;


/**
 *
 * @author Eduardo Ferreira
 */
public class WindowBuff extends Container{
    
    private static WindowBuff instance;

    public WindowBuff(){
        instance=this;
    }
    
    public static WindowBuff getInstance(){
        return instance;
    }
    
    //Redimensiona componentes ao alterar janela
    public void resizeComps(int old_w, int old_h){
        float c1,c2;
        for (Component c : this.getComponents()) {
            if (c instanceof Sprite){
                Sprite s = (Sprite) c;
                c1 = (float) s.getPosX()/old_w;
                c2 = (float) s.getPosY()/old_h;
                s.setPosX((int) (Window.getInstance().getWidth() * c1)-Window.getInstance().getInsets().left);
                s.setPosY((int) (Window.getInstance().getHeight() * c2)-Window.getInstance().getInsets().top);
                c1 = (float) s.width/old_w;
                c2 = (float) s.height/old_h;
                s.setDimension( (int)(Window.getInstance().getWidth()*c1), (int)(Window.getInstance().getHeight()*c2));
            }
        }
    }
    
     @Override
     //DoubleBuff
    public void paint(Graphics g) {
         BufferStrategy bf = Window.getInstance().getBufferStrategy();
         g=null;
         for (Component c : this.getComponents()) {
             if (c instanceof JLabel){
                JLabel a = (JLabel) c; 
                g = bf.getDrawGraphics();
                g.setColor(Color.WHITE);
                g.drawString(a.getText(), a.getX(), a.getY());
                g.dispose();
             }else{
                g = bf.getDrawGraphics();
                c.paint(g);
                g.dispose();
             }   
            }
        bf.show();
        Toolkit.getDefaultToolkit().sync();
     }
     
    
}
