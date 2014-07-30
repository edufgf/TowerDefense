/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package engine;

import engine.TowerData;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import towerdefense.TowerDefense;

/**
 *
 * @author Eduardo Ferreira
 */
public class Mouse implements MouseListener{
    
    Sprite info;

    @Override
    public void mouseClicked(MouseEvent e) {
        if (TowerDefense.game_count==2){
          Button bttn = (Button)e.getComponent();
          if (bttn.getID()==1){
              TowerDefense.game_count=3;
          }
          if (bttn.getID()==5){
              System.exit(0);
          }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (TowerDefense.game_count==2){
          Button bttn = (Button)e.getComponent();
          bttn.changeFrame();
        }
        if (TowerDefense.game_count>=4){
          Button bttn = (Button)e.getComponent();
          TowerData database = TowerData.getInstance();
          try {
                info = new Sprite(database.fileinfo[bttn.getID()],1);
          } catch (IOException ex) {
                Logger.getLogger(Mouse.class.getName()).log(Level.SEVERE, null, ex);
          }
          info.setPosX(bttn.getX()+e.getX());
          info.setPosY(bttn.getY()-info.height);
          WindowBuff.getInstance().add(info);
           
          
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
          if (TowerDefense.game_count==2){
          Button bttn = (Button)e.getComponent();
          bttn.changeFrame();
        }
          if (TowerDefense.game_count>=4){
          Button bttn = (Button)e.getComponent();
          TowerData database = TowerData.getInstance();
          WindowBuff.getInstance().remove(info);
          info=null;
          
          
        }
    }

}
