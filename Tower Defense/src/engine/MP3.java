/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package engine;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import towerdefense.TowerDefense;

/**
 *
 * @author Eduardo Ferreira
 */
public class MP3 extends Thread {

    private File mp3;
    private Player player;
    private boolean repete=false;
    private int game_loop_check;
    
    public MP3(String fileName){
        mp3 = new File(fileName);
    }
    
    public void play() throws FileNotFoundException, JavaLayerException{
        FileInputStream fis = new FileInputStream(mp3);
        BufferedInputStream bis = new BufferedInputStream(fis);
        player = new Player(bis);
        this.start();
    }
    
    public void stopMusic(){
        player.close();
    }
    
    public boolean repeat(){
        return repete;
    }
    
    public String getMp3Name(){
        return mp3.getName();
    }
    
    public int getSecond(){
        return player.getPosition();
    }
    
    @Override
    public void run(){
        repete=false;
        game_loop_check=TowerDefense.game_count;
        try {
            player.play();
        } catch (JavaLayerException ex) {
            Logger.getLogger(MP3.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (game_loop_check==TowerDefense.game_count){
           repete=true;
        }
    }
    
    
    
    
}
