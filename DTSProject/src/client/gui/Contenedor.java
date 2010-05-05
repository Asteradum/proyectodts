package client.gui;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

class Contenedor extends Container
{

	public ImageIcon icon = new ImageIcon ("files\\Wallpaper.jpg");


	public void paint (Graphics g){
		
		Rectangle r = g.getClipBounds();
		g.setColor(this.getBackground());
		g.fillRect (r.x, r.y, r.width, r.height);		

		g.drawImage (icon.getImage(), 0,0,this);	
		
		super.paint(g);
	}
}