package umlEditor;

import javax.swing.JPanel;

public class LineObject extends JPanel{
	
	protected int inport = -1;
	protected int outport = -1;
	protected Object in = null;
	protected Object out = null;
	protected int depth = -1;
	//
	protected int sx = -1;
	protected int sy = -1;
	protected int ex = -1;
	protected int ey = -1;
	
	public void repaintLine()
	{
		if(this.inport == 0)
    	{
			this.sx = this.in.getX() + this.in.width/2;
			this.sy = this.in.getY();
    	}
    	else if(this.inport == 1)
    	{
    		this.sx = this.in.getX() + this.in.width;
    		this.sy = this.in.getY() + this.in.high/2;
    	}
    	else if(this.inport == 2)
    	{
    		this.sx = this.in.getX() + this.in.width/2;
    		this.sy = this.in.getY() + this.in.high;
    	}
    	else if(this.inport == 3)
    	{
    		this.sx = this.in.getX();
    		this.sy = this.in.getY() + this.in.high/2;
    	}
    	//out
    	if(this.outport == 0)
    	{
    		this.ex = this.out.getX() + this.out.width/2;
    		this.ey = this.out.getY();
    	}
    	else if(this.outport == 1)
    	{
    		this.ex = this.out.getX() + this.out.width;
    		this.ey = this.out.getY() + this.out.high/2;
    	}
    	else if(this.outport == 2)
    	{
    		this.ex = this.out.getX() + this.out.width/2;
    		this.ey = this.out.getY() + this.out.high;
    	}
    	else if(this.outport == 3)
    	{
    		this.ex = this.out.getX();
    		this.ey = this.out.getY() + this.out.high/2;
    	}
    	this.sx+=10;
    	this.sy+=10;
    	this.ex+=10;
    	this.ey+=10;
    	//
    	this.setLocation(Math.min(this.sx,this.ex)-10,Math.min(this.sy,this.ey)-10);
    	this.setBounds(Math.min(this.sx,this.ex)-10,Math.min(this.sy,this.ey)-10,Math.abs(this.sx-this.ex)+20,Math.abs(this.sy-this.ey)+20);
	}

}