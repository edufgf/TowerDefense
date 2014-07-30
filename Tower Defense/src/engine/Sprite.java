/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package engine;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Timer;

/**
 *
 * @author Eduardo Ferreira
 */
public class Sprite extends Component{

    BufferedImage imagem;
    BufferedImage[][] frame;
    int pos_x, pos_y;
    int width, height;
    int x,y=0;
    int maxFrames;
    ActionListener update;
    Timer animation;
    Boolean loop;
    

    public Sprite(String imageName, int numFrames) throws IOException{
        this(imageName,numFrames,0,0,false);
    }
    
    public Sprite(String imageName, int numFrames, boolean sprite_sheet) throws IOException{
        this(imageName,numFrames,0,0,sprite_sheet);
    }
    
    public Sprite(String imageName, int numFrames, int pos_x, int pos_y) throws IOException{
        this(imageName,numFrames,pos_x,pos_y,false);
    }
    
    public Sprite(String imageName, int numFrames, int pos_x, int pos_y, boolean sprite_sheet) throws IOException{
        if (sprite_sheet==false){
        imagem = ImageIO.read(new File(imageName));
        frame = new BufferedImage[numFrames][1];
        maxFrames = numFrames;
        this.pos_x=pos_x+Window.getInstance().getInsets().left;
        this.pos_y=pos_y+Window.getInstance().getInsets().top;
        this.setLocation(pos_x, pos_y);
        //Vetor Frames
        int w = imagem.getWidth()/numFrames;
        int h = imagem.getHeight();
        width = w;
        height= h;
        this.setSize(width, height);
        for (int i = 0; i < numFrames; i++) {
                frame[i][0] = imagem.getSubimage(i*w, 0, w, h);
         }
        }else{
        imagem = ImageIO.read(new File(imageName));
        frame = new BufferedImage[numFrames][numFrames];
        maxFrames = numFrames;
        //Vetor Frames
        int w = imagem.getWidth()/numFrames;
        int h = imagem.getHeight()/numFrames;
        this.width = w;
        this.height= h;
        setSize(w, h);
        for (int i = 0; i < numFrames; i++) {
            for (int j = 0; j < numFrames; j++) {
                frame[i][j] = imagem.getSubimage(j*w, i*h, w, h);
            }
         } 
        } 
    }

    public void setAnimation(int startFrame,int timeFrame, final boolean loop){
        x=startFrame;
        ActionListener update = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loop)
                   x=++x%maxFrames;
                else if (x==maxFrames-1){
                    animation.stop();
                }else{
                    x++;
                }
                   
            }   
        };
        Timer animation = new Timer(timeFrame,update);
        this.animation=animation;
    }

    public void play(){
        if(!animation.isRunning())
           animation.start();
    }
    public void stop(){
        animation.stop();
    }
    
    public int getPosX(){
        return pos_x;
    }
    public int getPosY(){
        return pos_y;
    }
    public void setPosX(int pos_x){
        this.pos_x=pos_x+Window.getInstance().getInsets().left;
        this.setLocation(pos_x, pos_y);
    }
    public void setPosY(int pos_y){
        this.pos_y=pos_y+Window.getInstance().getInsets().top;
        this.setLocation(pos_x, pos_y);
    }
    public void setDimension(int width,int height){
        this.width = width;
        this.height= height;
        this.setSize(width, height);
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(frame[x][y], pos_x, pos_y, width, height, this);
    }

    public BufferedImage getImagem() {
        return imagem;
    }
   
    
    
    
}
