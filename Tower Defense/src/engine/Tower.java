/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package engine;

import engine.TowerData;
import java.io.IOException;

/**
 *
 * @author xxx
 */
public class Tower extends Sprite {

    public static TowerData database;
    String name;
    int damage;
    int value;
    
    public Tower(String imageName, int numFrames, int pos_x, int pos_y, boolean sprite_sheet) throws IOException{
         super(imageName,numFrames,pos_x,pos_y,sprite_sheet);
    }
    
     public Tower(String imageName, int numFrames) throws IOException{
         super(imageName,numFrames,0,0,false);
    }
    
    public Tower(int tipo) throws IOException{
        super(database.filename[tipo], database.numFrames[tipo]);
        this.name   = database.name  [tipo];
        this.damage = database.damage[tipo];
        this.value  = database.value [tipo];
        
        
    }
    
}
