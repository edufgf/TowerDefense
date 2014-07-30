/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package engine;

import java.io.IOException;

/**
 *
 * @author Eduardo Ferreira
 */
public class Button extends Sprite {

    private int ID;
    
    public Button(String imageName, int numFrames) throws IOException{
          super(imageName,numFrames);        
    }
    
    
    public Button(String imageName, int pos_x, int pos_y) throws IOException{
         super(imageName,1,pos_x,pos_y);
    }
    
    public Button(String imageName, int pos_x, int pos_y, int ID) throws IOException{
         super(imageName,1,pos_x,pos_y);
         this.ID=ID;
    }
    
    public Button(String imageName, int numFrames, int pos_x, int pos_y, int ID) throws IOException{
         super(imageName,numFrames,pos_x,pos_y);
         this.ID=ID;
    }
    
    public void setID(int ID){
        this.ID=ID;
    } 
    public int getID(){
        return ID;
    }
    public void changeFrame(){
        this.x=++this.x%2;
    }
    
}
