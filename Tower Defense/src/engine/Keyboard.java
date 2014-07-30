/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import towerdefense.TowerDefense;

/**
 *
 * @author Eduardo Ferreira
 */
public class Keyboard implements KeyListener {

    private Sprite character;
    private int last_key=-1,keys_pressed=0,e_key;
    private boolean up,down,left,right;
    
    //Nao Utilizado
    public void Control(Sprite character){
        this.character=character;
    }
    //Nao Utilizado
    public void Movimento() throws InterruptedException{
        MoveCharacter(e_key);
        Thread.sleep(50);
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
         
    }

    @Override
    public void keyPressed(KeyEvent e) {
        e_key = e.getKeyCode();
        if (e.getKeyCode() == KeyEvent.VK_ENTER && TowerDefense.game_count==0){
            TowerDefense.game_count=1;
            
        }
        if (e.getKeyCode() == KeyEvent.VK_UP && up==false){
            up=true;
            keys_pressed++;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN && down==false){
            down=true;
            keys_pressed++;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && right==false){
            right=true;
            keys_pressed++;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT && left==false){
            left=true;
            keys_pressed++;
        }
    }
    
    //Nao Utilizado
    public void MoveCharacter(int e_key){
        
        if (keys_pressed==1){
           if (last_key != e_key && last_key !=-1)
                character.y--;
           last_key = e_key;
           character.y=(character.y+1)%7;
           if (up){
               character.pos_y-=5;
               character.x=4;
           }
           if (down){
               character.pos_y+=5;
               character.x=0;
           }
           if (right){
               character.pos_x+=5;
               character.x=2;
           }
           if (left){
               character.pos_x-=5;
               character.x=6;
           }
        }
        if (keys_pressed==2){
           last_key = e_key;
           if(!(up&down)&&!(left&right))
           character.y=(character.y+1)%7;
           if (up&&left){
               character.pos_y-=5;
               character.pos_x-=5;
               character.x=5;
           }
           if (up&&right){
               character.pos_y-=5;
               character.pos_x+=5;
               character.x=3;
           }
           if (down&&left){
               character.pos_y+=5;
               character.pos_x-=5;
               character.x=7;
           }
           if (down&&right){
               character.pos_y+=5;
               character.pos_x+=5;
               character.x=1;
           }
        }
        if (character.pos_x>Window.getInstance().getWidth()){
            character.pos_x=-64;
        }
        if (character.pos_x<-64){
            character.pos_x=Window.getInstance().getWidth();
        }
        if (character.pos_y>Window.getInstance().getHeight()){
            character.pos_y=-64;
        }
        if (character.pos_y<-64){
            character.pos_y=Window.getInstance().getHeight();
        }
        
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP){
            up=false;
            keys_pressed--;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN){
            down=false;
            keys_pressed--;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            right=false;
            keys_pressed--;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT){
            left=false;
            keys_pressed--;
        }
    }

}
