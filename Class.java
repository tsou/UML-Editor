package umlEditor;

import java.awt.*;

import javax.swing.JLabel;

public class Class extends Object{
	
	public Class(int x, int y, int depth, int id) {

		this.width = 100;
		this.high = 150;
		this.setLocation(x,y);
		this.depth = depth;
		this.uid = id;
		this.setBounds(x, y, 120, 170);
		//
		this.nameArea = new JLabel();
		this.setLayout(null);
		this.nameArea.setBounds(20, 10, 100, 50);
		this.add(this.nameArea);
		//
		this.setOpaque(false);
	}
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
    	g.setColor(Color.LIGHT_GRAY);
    	g.fillRect(10, 10, width, high/3);
    	g.fillRect(10, 10+high/3, width, high/3);
    	g.fillRect(10, 10+2*high/3, width, high/3);
    	g.setColor(Color.BLACK);
    	g.drawRoundRect(10, 10, width, high/3, 0, 0);
    	g.drawRoundRect(10, 10+high/3, width, high/3, 0, 0);
    	g.drawRoundRect(10, 10+2*high/3,width, high/3, 0, 0);
    	
    	/*if(this.port[0]==true)g.fillRect(5+width/2, 0, 10, 10);
    	if(this.port[1]==true)g.fillRect(10+width, 5+high/2, 10, 10);
    	if(this.port[2]==true)g.fillRect(5+width/2, 10+high, 10, 10);
    	if(this.port[3]==true)g.fillRect(0, 5+high/2, 10, 10);*/

    	if(this.selected==true)
    	{
    		g.fillRect(5+width/2, 0, 10, 10);
        	g.fillRect(10+width, 5+high/2, 10, 10);
        	g.fillRect(5+width/2, 10+high, 10, 10);
        	g.fillRect(0, 5+high/2, 10, 10);
    	}
    }
}