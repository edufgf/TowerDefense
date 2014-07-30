/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package engine;

/**
 *
 * @author Eduardo Ferreira
 */
public class Region {

    private int x,y;
    
    public Region(int x, int y){
        this.x=x+Window.getInstance().getInsets().left;
        this.y=y+Window.getInstance().getInsets().top;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
