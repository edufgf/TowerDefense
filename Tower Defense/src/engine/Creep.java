/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package engine;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.Timer;

/**
 *
 * @author Eduardo Ferreira
 */
public class Creep extends Sprite {

    Creep c;
    Timer t_move;
    int speed;
    //Centro do Sprite
    int cx,cy;

    public Creep(String imageName, int numFrames, boolean sprite_sheet) throws IOException{
        super(imageName,numFrames,sprite_sheet);
        c=this;
    }
    
    public Creep(String imageName, int numFrames, int pos_x, int pos_y) throws IOException{
        super(imageName,numFrames,pos_x,pos_y);
        c=this;
    }

    public void setSpeed(int speed){
        this.speed=speed;
    }
    public int getSpeed(){
        return speed;
    }
    
    public void moveTo(final Region[] region, final int t_speed, final int i) {
        cx=c.getPosX()+c.width/2;
        cy=c.getPosY()+c.height/2;
        ActionListener move = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cx==region[i].getX()&&cy==region[i].getY() || ((cy>=region[i].getY()-c.speed && cy<=region[i].getY()+c.speed) && (cx>=region[i].getX()-c.speed && cx<=region[i].getX()+c.speed))){
                    t_move.stop();
                    if (region[i+1]!=null){
                        moveTo(region,t_speed,i+1);
                    }
                }
                if (cy==region[i].getY()|| (cy>=region[i].getY()-c.speed && cy<=region[i].getY()+c.speed)){
                    if(region[i].getX()>cx){
                      c.x=2;
                      c.y =++c.y%c.maxFrames;
                      c.setPosX(c.getPosX()-Window.getInstance().getInsets().left+c.speed);
                      cx+=c.speed;
                    }else if (region[i].getX()<cx){
                      c.x=2;
                      c.y =++c.y%c.maxFrames;  
                      c.setPosX(c.getPosX()-Window.getInstance().getInsets().left-c.speed);
                      cx+=-c.speed;
                    }
                }else if (cx==region[i].getX()||(cx>=region[i].getX()-c.speed && cx<=region[i].getX()+c.speed)){
                    if(region[i].getY()>cy){
                      c.x=0;
                      c.y=++c.y%c.maxFrames;
                      c.setPosY(c.getPosY()-Window.getInstance().getInsets().top+c.speed);
                      cy+=c.speed;
                    }else if (region[i].getY()<cy){
                      c.x=3;
                      c.y=++c.y%c.maxFrames;
                      c.setPosY(c.getPosY()-Window.getInstance().getInsets().top-c.speed);
                      cy+=-c.speed;
                    }
                }
                WindowBuff.getInstance().update(WindowBuff.getInstance().getGraphics());
            }
        }; 
        Timer t_move = new Timer(t_speed,move);
        this.t_move=t_move;
        this.t_move.start();
    }
    
}
