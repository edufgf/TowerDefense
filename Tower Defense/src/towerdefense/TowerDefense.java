
package towerdefense;

import engine.Background;
import engine.Button;
import engine.CListener;
import engine.Creep;
import engine.Keyboard;
import engine.MP3;
import engine.Mouse;
import engine.Region;
import engine.Sprite;
import engine.Tower;
import engine.TowerData;
import engine.Window;
import engine.WindowBuff;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.Timer;
import javazoom.jl.decoder.JavaLayerException;

/**
 *
 * @author Eduardo Ferreira
 */
public class TowerDefense {
    
    public static int game_count=0;
    static int sec=28;
    static Timer count;

    public static void main(String[] args) throws IOException, InterruptedException, FileNotFoundException, JavaLayerException {
        TowerData database = new TowerData(10);
        Tower.database=database;
        Window intro = new Window("Intro", 800, 600);
        WindowBuff wb = new WindowBuff();
        wb.setSize(intro.getSize());
        Background intro_b1 = new Background("IntroBack.jpg",0,0); 
        Background intro_b2 = new Background("IntroBack.jpg",intro.getWidth(),0);
        Sprite logo = new Sprite("logo.png",1,intro.getWidth()/6,intro.getHeight()/6);
        Sprite press = new Sprite("press.png",8,270,460);
        press.setAnimation(0, 175, true); 
        Keyboard keyboard = new Keyboard();
        Mouse mouse = new Mouse();
        CListener comp_listener = new CListener();
        wb.add(intro_b1);
        wb.add(intro_b2);
        wb.add(logo);
        wb.add(press);
        press.play();
        intro.add(wb);
        intro.addComponentListener(comp_listener);
        intro.addKeyListener(keyboard);
        MP3 intro_m = new MP3("intro.mp3");
        MP3 current;
        current = intro_m;
        current.play();
        JLabel countdown=null;

        while(true){
//#MusicaLoop            
        if (current.repeat()){
            current = new MP3(current.getMp3Name());
            current.play();
        }
//#IntroBackgroundLoop
        if(game_count==0){
        intro_b1.setPosX(intro_b1.getPosX()-5);
        intro_b2.setPosX(intro_b2.getPosX()-5);
        
        if (intro_b1.getPosX() < -intro.getWidth())
            intro_b1.setPosX(intro.getWidth());
        if (intro_b2.getPosX()< -intro.getWidth())
            intro_b2.setPosX(intro.getWidth());
        
        }
//#MenuLoop
        if(game_count==1){
            game_count++;
            press.stop();
            current.stopMusic();
            MP3 menu_m = new MP3("menu.mp3"); 
            current = menu_m;
            current.play();
            wb.removeAll();
            intro.setTitle("Menu");
            Background menu = new Background("MenuBack.png",0,0);
            Button newgame = new Button("NewGameButton.png",2,270,15,1);
            newgame.setDimension(250,80);
            newgame.addMouseListener(mouse);
            Button loadgame = new Button("LoadGameButton.png",2,newgame.getPosX(),newgame.getPosY()+newgame.getHeight()+3,2);
            loadgame.setDimension(250,80);
            loadgame.addMouseListener(mouse);
            Button savegame = new Button("SaveGameButton.png",2,newgame.getPosX(),loadgame.getPosY()+loadgame.getHeight()+3,3);
            savegame.setDimension(250,80);
            savegame.addMouseListener(mouse);
            Button instructions = new Button("InstructionsButton.png",2,newgame.getPosX(),savegame.getPosY()+savegame.getHeight()+3,4);
            instructions.setDimension(250,80);
            instructions.addMouseListener(mouse);
            Button exit = new Button("ExitButton.png",2,newgame.getPosX(),instructions.getPosY()+instructions.getHeight()+3,5);
            exit.setDimension(250,80);
            exit.addMouseListener(mouse);
            wb.add(menu);
            wb.add(newgame);
            wb.add(loadgame);
            wb.add(savegame);
            wb.add(instructions);
            wb.add(exit);
        }        
//#NewGame
        if (game_count==3){
            game_count++;
            current.stopMusic();
            MP3 menu_l = new MP3("level1.mp3"); 
            current = menu_l;
            current.play();
            wb.removeAll();
            intro.setTitle("Level 1");
            Background level1 = new Background("Level1Back.png",0,0);
            countdown = new JLabel("Wait "+sec+" seconds...");
            countdown.setLocation(500,500);
            Button tb1 = new Button("TowerButton1.png",100,500,1);
            tb1.addMouseListener(mouse);
            wb.add(level1);
            wb.add(countdown);
            wb.add(tb1);
            ActionListener cd = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sec==0){
                    count.stop();
                }else{
                sec--;
                }
            }   
        };
            Timer count = new Timer(1000,cd);
            TowerDefense.count = count;
            count.start();
        }
//#NewgameCountdown        
        if (game_count==4 && sec>=0){
            if (sec==0){
                sec--;
                wb.remove(countdown);
                countdown=null;
            }else{
                countdown.setText("Wait "+sec+" seconds...");
            }
        }
//#Level1        
        if (game_count==4 && current.getSecond()>=28000){
            game_count++;
            Region region[] = new Region[6];
            region[0] = new Region(118,68);
            region[1] = new Region(118,360);
            region[2] = new Region(676,360);
            region[3] = new Region(676,68);
            region[4] = new Region(840,68);
            Creep monster[] = new Creep[10];
            region[5] = null;
            for (int i = 0; i < 10; i++) {
                monster[i] = new Creep("Monster.png",4,true);
                monster[i].setPosX(-50-(monster[i].getWidth()/2));
                monster[i].setPosY(68-(monster[i].getHeight()/2));
                monster[i].setSpeed(7);
                monster[i].moveTo(region,100,0);
                monster[i].setVisible(false);
                wb.add(monster[i]);
                Thread.sleep(750); 
            }
         }        
//#ScreenUpdate       
        wb.update(wb.getGraphics());
        Thread.sleep(50);
       }  
    }
}
