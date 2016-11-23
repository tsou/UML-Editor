package umlEditor;

import java.awt.*;
import javax.swing.JLabel;

public class UseCass extends Object{
	
	public UseCass(int x,int y,int depth, int id)
	{
		this.width = 150;
		this.high = 100;
		this.setLocation(x,y);
		this.depth = depth;
		this.uid = id;
		this.setBounds(x, y, 170, 120);
		//
		this.nameArea = new JLabel();
		this.setLayout(null);
		this.nameArea.setBounds(40, 35, 100, 50);
		this.add(this.nameArea);
		//
		this.setOpaque(false);
	}
	@Override
	protected void paintComponent(Graphics g) {
		
    	super.paintComponent(g);
    	
		g.setColor(Color.LIGHT_GRAY);
		g.fillOval(10, 10, width, high);
    	g.setColor(Color.BLACK);
		g.drawOval(10, 10, width, high);
		
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