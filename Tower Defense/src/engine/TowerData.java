/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package engine;

/**
 *
 * @author xxx
 */
public class TowerData {

    private static TowerData instance;
    //TowerDataBase
    public String filename[];
    public String fileinfo[];
    public String name[];
    public int numFrames[];
    public int damage[];
    public int value[];
    
    public TowerData(int max){
        //Initiate
        filename = new String[max];
        fileinfo = new String[max];
        name = new String[max];
        numFrames = new int[max];
        damage = new int[max];
        value = new int[max];
        instance=this;
        
        //Torre1
        filename[0]="torre1.png";
        fileinfo[1]="TowerInfo1.png";
        name[0]="Torre de Flecha";
        numFrames[0]=4;
        damage[0]=1;
        value[0]=10;
    }
    
    public static TowerData getInstance(){
        return instance;
    }
    
    
}
