/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package engine;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Eduardo Ferreira
 */
public class Background extends JPanel {

    private BufferedImage imagem;
    Graphics bufferGraphics; 
    private int pos_x,pos_y;
    
    public Background(String fileName, int pos_x, int pos_y) throws IOException{
        try {
            imagem = ImageIO.read(new File(fileName));
        } catch (IOException ex) {
            Logger.getLogger(Background.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        
    }

    public int getPosX(){
        return pos_x;
    }
    public int getPosY(){
        return pos_y;
    }
    public void setPosX(int pos_x){
        this.pos_x=pos_x;
    }
    public void setPosY(int pos_y){
        this.pos_y=pos_y;
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(imagem, pos_x, pos_y, Window.getInstance().getWidth(), Window.getInstance().getHeight(), this);
    }

    public BufferedImage getImagem() {
        return imagem;
    }

    
    
    
    
}