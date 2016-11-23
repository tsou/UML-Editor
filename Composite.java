package umlEditor;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class Composite extends JPanel {
	
	public ArrayList<Object> objls;
	public boolean selected = false;
	public int minx = 9999;
	public int maxx = -1;
	public int miny = 9999;
	public int maxy = -1;
	public int depth = -1;
	
	public Composite(ArrayList<Object> objs, int depth){
		
		objls = new ArrayList<Object>() ;
		this.depth = depth;
		
		for(int i=0;i<objs.size();i++)
		{
			if(objs.get(i).selected ==true)
			{
				objls.add(objs.get(i));
				if(objs.get(i).getX()<this.minx) this.minx = objs.get(i).getX();
				if(objs.get(i).getX()+objs.get(i).width+20>this.maxx) this.maxx = objs.get(i).getX()+objs.get(i).width+20;
				if(objs.get(i).getY()<this.miny) this.miny = objs.get(i).getY();
				if(objs.get(i).getY()+objs.get(i).high+20>this.maxy) this.maxy = objs.get(i).getY()+objs.get(i).high+20;
			}
		}
		this.setLocation(minx, miny);
		this.setBounds(minx, miny, maxx-minx, maxy-miny);
		this.setBorder(BorderFactory.createLineBorder(Color.orange));
	}
	public void setPoint(int x,int y)
	{
		this.setLocation(x,y);
	}
}
