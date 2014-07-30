/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package engine;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 *
 * @author Eduardo Ferreira
 */
public class CListener implements ComponentListener{

    int old_w=800;
    int old_h=600;
    
    @Override
    public void componentResized(ComponentEvent e) {
        WindowBuff.getInstance().resizeComps(old_w, old_h);
        old_w = e.getComponent().getWidth();
        old_h = e.getComponent().getHeight();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        
    }

    @Override
    public void componentShown(ComponentEvent e) {
        
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        
    }

}
