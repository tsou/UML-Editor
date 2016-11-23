package umlEditor;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;

public class compositionLine extends LineObject{
	
	public compositionLine(Object objin, Object objout, int inport, int outport, int depth)
	{
		this.in = objin;
		this.inport = inport;
		this.out = objout;
		this.outport = outport;
		this.depth = depth;
    	//in
    	if(inport == 0)
    	{
    		sx = in.getX() + in.width/2;
    		sy = in.getY();
    	}
    	else if(inport == 1)
    	{
    		sx = in.getX() + in.width;
    		sy = in.getY() + in.high/2;
    	}
    	else if(inport == 2)
    	{
    		sx = in.getX() + in.width/2;
    		sy = in.getY() + in.high;
    	}
    	else if(inport == 3)
    	{
    		sx = in.getX();
    		sy = in.getY() + in.high/2;
    	}
    	//out
    	if(outport == 0)
    	{
    		ex = out.getX() + out.width/2;
    		ey = out.getY();
    	}
    	else if(outport == 1)
    	{
    		ex = out.getX() + out.width;
    		ey = out.getY() + out.high/2;
    	}
    	else if(outport == 2)
    	{
    		ex = out.getX() + out.width/2;
    		ey = out.getY() + out.high;
    	}
    	else if(outport == 3)
    	{
    		ex = out.getX();
    		ey = out.getY() + out.high/2;
    	}
    	sx+=10;
    	sy+=10;
    	ex+=10;
    	ey+=10;
    	//
    	this.setBounds(Math.min(sx,ex)-10,Math.min(sy,ey)-10,Math.abs(sx-ex)+20,Math.abs(sy-ey)+20);
    	this.setOpaque(false);//transparent

	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
    	super.paintComponent(g);
    	//
    	int[] lingX = { 0,0,0,0 } ; 
    	int[] lingY = { 0,0,0,0 } ;
    	
    	g.setColor(Color.BLACK);
    	if((sx<ex&&sy<ey)||(sx>ex&&sy>ey))
    	{
    		g.drawLine(10,10,Math.abs(sx-ex)+10,Math.abs(sy-ey)+10);//left up or right down
    		if(sx<ex&&sy<ey)//left up
    		{
    			lingX[0] = 5 ;lingY[0] = 10 ;
    			lingX[1] = 10 ;lingY[1] = 5 ;
    			lingX[2] = 15 ;lingY[2] = 10 ;
    			lingX[3] = 10 ;lingY[3] = 15 ;
    		}
    		else//right down
    		{
    			lingX[0] = Math.abs(sx-ex)+5 ;lingY[0] = Math.abs(sy-ey)+10 ;
    			lingX[1] = Math.abs(sx-ex)+10 ;lingY[1] = Math.abs(sy-ey)+5 ;
    			lingX[2] = Math.abs(sx-ex)+15 ;lingY[2] = Math.abs(sy-ey)+10 ;
    			lingX[3] = Math.abs(sx-ex)+10 ;lingY[3] = Math.abs(sy-ey)+15 ;
    		}
    	}
    	else
    	{
    		g.drawLine(10,Math.abs(sy-ey)+10,Math.abs(sx-ex)+10,10);//left down or right up
    		if(sx<ex&&sy>ey)//left down
    		{
    			lingX[0] = 5 ;lingY[0] = Math.abs(sy-ey)+10 ;
    			lingX[1] = 10 ;lingY[1] = Math.abs(sy-ey)+5 ;
    			lingX[2] = 15 ;lingY[2] = Math.abs(sy-ey)+10 ;
    			lingX[3] = 10 ;lingY[3] = Math.abs(sy-ey)+15 ;
    		}
    		else//right up
    		{
    			lingX[0] = Math.abs(sx-ex)+5 ;lingY[0] = 10 ;
    			lingX[1] = Math.abs(sx-ex)+10 ;lingY[1] = 5 ;
    			lingX[2] = Math.abs(sx-ex)+15 ;lingY[2] = 10 ;
    			lingX[3] = Math.abs(sx-ex)+10 ;lingY[3] = 15 ;
    		}
    	}
    	//
    	g.setColor(Color.WHITE);
    	g.fillPolygon(lingX, lingY, 4);
    	g.setColor(Color.BLACK);
    	g.drawPolygon(lingX, lingY, 4);

    }
	
}
