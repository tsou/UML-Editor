package umlEditor;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;  

public class Canvas extends JPanel implements MouseListener{
	
	public ArrayList<Object> objs;
	public ArrayList<LineObject> liobjs;
	public ArrayList<Composite> composites;
	private Mode mode;
	Object objin;
	Object objout;
	int tinport;
	int toutport;
	int tsx;
	int tsy;
	int tex;
	int tey;
	int depthcount = 0;
	int changeObj = -1;
	
	
	public Canvas(Mode mode){
		
		objs = new ArrayList<Object>() ;
		liobjs = new ArrayList<LineObject>() ;
		composites = new ArrayList<Composite>() ;
		addMouseListener(this);
		this.mode = mode;
		
	}

	public void clearSelect()
	{
		for(int i=0;i<objs.size();i++)
		{
			objs.get(i).selected = false;
		}
		repaint();
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			if(mode.getMode() ==1)//select only one object
			{
				int k = -1;
				changeObj = -1;
				//select one composite
				for(int i=composites.size()-1;i>=0;i--)
				{
					if(e.getX()>composites.get(i).minx+10 && e.getX()<composites.get(i).maxx-10 && 
					   e.getY()>composites.get(i).miny+10 && e.getY()<composites.get(i).maxy-10)
					{
						k = i;
						break;
					}
				}
				//select one object
				for(int i=objs.size()-1;i>=0;i--)
				{
					if((k == -1||objs.get(i).depth > composites.get(k).depth) &&
					   e.getX()>objs.get(i).getX()+10 && e.getX()<objs.get(i).getX()+10+objs.get(i).width && 
					   e.getY()>objs.get(i).getY()+10 && e.getY()<objs.get(i).getY()+10+objs.get(i).high)
					{
						objs.get(i).selected = true;
						changeObj = objs.get(i).uid;
						k = -1;
						break;
					}
				}
				//set
				if(k != -1)
				{
					composites.get(k).selected = true;
					for(int j=0;j<composites.get(k).objls.size();j++)
					{
						for(int t=0;t<objs.size();t++)
						{
							if(objs.get(t).uid == composites.get(k).objls.get(j).uid)
							{
								objs.get(t).selected = true;
								break;
							}	
						}
					}
				}
			}
	        else if(mode.getMode()==5)//new class
	        {
	        	Class c= new Class(e.getX(),e.getY(),depthcount++,objs.size());
	        	objs.add(c);
	        	this.add(c,0);
	        }
	        else if(mode.getMode()==6)//new use cass
	        {
	        	UseCass u= new UseCass(e.getX(),e.getY(),depthcount++,objs.size());
	        	objs.add(u);//add to object list
	        	this.add(u,0);//add to canvas
	        }
			
		}
		repaint();
	}

	@Override
	public void mousePressed( MouseEvent e ) {
		if(mode.getMode() ==1)
		{
			tsx = e.getX();
			tsy = e.getY();
			//reset all selected
			for(int i = 0; i<objs.size();i++)
			{
				objs.get(i).selected = false;
			}
			for(int i = 0; i<composites.size();i++)
			{
				composites.get(i).selected = false;
			}
		}
		else if(mode.getMode()>1&&mode.getMode()<5)
		{
			objin = pointcheck(e.getX(),e.getY(),1);//line
		}
	}
	
	@Override
	public void mouseReleased( MouseEvent e ) {
		
		if(mode.getMode()==1)
		{
			tex = e.getX();
			tey = e.getY();
			checkSelect();
			if(tsx!=tex)checkMove();//mouse drag! not click (select)
		}
		else if(mode.getMode()>1 && mode.getMode()<5 && objin!=null)
		{
			objout = pointcheck(e.getX(),e.getY(),2);
			if(objout!=null && objin.uid!=objout.uid)//press and release in different object
			{
				//
				objs.get(objin.uid).port[tinport] = true;
				objs.get(objout.uid).port[toutport] = true;
				if(mode.getMode()==2)
				{
					associationLine a= new associationLine(objin,objout,tinport,toutport,depthcount++);
		        	liobjs.add(a);//add to line object list
		        	this.add(a,0);//add to canvas
				}
				else if(mode.getMode()==3)
				{
					genaralizationLine g= new genaralizationLine(objin,objout,tinport,toutport,depthcount++);
		        	liobjs.add(g);//add to line object list
		        	this.add(g,0);//add to canvas
				}
				else if(mode.getMode()==4)
				{
					compositionLine c= new compositionLine(objin,objout,tinport,toutport,depthcount++);
		        	liobjs.add(c);//add to line object list
		        	this.add(c,0);//add to canvas
				}
			}
		}
		repaint();
	}
	
	private void checkMove() {
		
		int k = -1;
		//move composite
		for(int i=composites.size()-1;i>=0;i--)
		{
			if(tsx>composites.get(i).minx+10 && tsx<composites.get(i).maxx-10 && 
			   tsy>composites.get(i).miny+10 && tsy<composites.get(i).maxy-10)
			{
				k=i;
				break;
			}
		}
		//move one object
		for(int i=objs.size()-1;i>=0;i--)
		{
			if((k == -1||objs.get(i).depth > composites.get(k).depth) && objs.get(i).getX()+10< tsx && objs.get(i).getX()+10+objs.get(i).width>tsx &&
				objs.get(i).getY()+10<tsy && objs.get(i).getY()+10+objs.get(i).high>tsy)
			{
				objs.get(i).setPoint(tex-tsx,tey-tsy);
				k = -1;
				break;
			}
		}
		if(k != -1)
		{
			//set objects position
			for(int j=0;j<composites.get(k).objls.size();j++)
			{
				for(int t=0;t<objs.size();t++)
				{
					if(composites.get(k).objls.get(j).uid == objs.get(t).uid)
					{
						objs.get(t).setPoint(tex-tsx,tey-tsy);
					}
				}
			}
			//set composite position
			composites.get(k).minx += tex-tsx;
			composites.get(k).maxx += tex-tsx;
			composites.get(k).miny += tey-tsy;
			composites.get(k).maxy += tey-tsy;
			composites.get(k).setPoint(composites.get(k).getX()+tex-tsx, composites.get(k).getY()+tey-tsy);
			//
			for(int i=0;i<k;i++)
			{
				int b = 0;
				for(int j=0;j<composites.get(k).objls.size();j++)
				{
					for(int t=0;t<composites.get(i).objls.size();t++)
					{
						if(composites.get(i).objls.get(t).uid == composites.get(k).objls.get(j).uid)
						{
							composites.get(i).minx += tex-tsx;
							composites.get(i).maxx += tex-tsx;
							composites.get(i).miny += tey-tsy;
							composites.get(i).maxy += tey-tsy;
							composites.get(i).setPoint(composites.get(i).getX()+tex-tsx, composites.get(i).getY()+tey-tsy);
							b = 1;
							break;
						}
					}
					if(b==1)break;
				}
			}
		}
		//repaint line
		for(int j=0;j<liobjs.size();j++)
		{
			liobjs.get(j).repaintLine();
		}
		
	}

	private void checkSelect() {
		
		int count = 0;
		//select composite
		for(int i=composites.size()-1;i>=0;i--)
		{
			if(composites.get(i).minx+10>Math.min(tsx,tex) && composites.get(i).maxx-10<Math.max(tsx,tex) && 
			   composites.get(i).miny+10>Math.min(tsy,tey) && composites.get(i).maxy-10<Math.max(tsy,tey))
			{
				boolean inside = false;
				for(int j=composites.size()-1;j>i;j--)
				{
					if(inside == true)break;
					for(int t=0;t<composites.get(i).objls.size();t++)
					{
						if(composites.get(j).objls.get(t).uid == composites.get(i).objls.get(t).uid)
						{
							inside = true;
							break;
						}	
					}
				}
				if(inside == false)//not overlap
				{
					composites.get(i).selected = true;
					for(int j=0;j<composites.get(i).objls.size();j++)
					{
						for(int t=0;t<objs.size();t++)
						{
							if(objs.get(t).uid == composites.get(i).objls.get(j).uid)
							{
								objs.get(t).selected = true;
								break;
							}	
						}
					}
				}
				
			}
		}
		//select object
		for(int i=0;i<objs.size();++i)
		{
			if(objs.get(i).composited == false && objs.get(i).getX()+10>Math.min(tsx,tex) && objs.get(i).getX()+10+objs.get(i).width<Math.max(tsx,tex) &&
				objs.get(i).getY()+10>Math.min(tsy,tey) && objs.get(i).getY()+10+objs.get(i).high<Math.max(tsy,tey))
			{
				objs.get(i).selected = true;
				changeObj = objs.get(i).uid;
				count++;
			}
		}
		if(count != 1)changeObj = -1;
		
	}

	private Object pointcheck(int x, int y, int io) {
		
		Object temp = null;
		for(int i=0;i<objs.size();i++)
		{
			if(x>objs.get(i).getX() && x<objs.get(i).getX()+objs.get(i).width+10){
				if(y>objs.get(i).getY() && y<objs.get(i).getY()+objs.get(i).high+10){
					//find angle
					int vx = x-(objs.get(i).getX()+(objs.get(i).width/2));
					int vy = y-(objs.get(i).getY()+(objs.get(i).high/2));
					double squareLenth = Math.sqrt(vx*vx+vy*vy);
					double angle = -vy / squareLenth;
					angle = Math.toDegrees(Math.acos(angle));
					//find port
					if(objs.get(i).width>objs.get(i).high)//oval
					{
						if(angle<=60)
						{
							if(io == 1)tinport = 0;
							else if(io == 2)toutport = 0;
						}
						else if(angle>120 && angle<=180)
						{
							if(io == 1)tinport = 2;
							else if(io == 2)toutport = 2;
						}
						else
						{
							if(x<=objs.get(i).getX()+objs.get(i).width/2)//left=3
							{
								if(io == 1)tinport = 3;
								else if(io == 2)toutport = 3;
							}
							else//right=1
							{
								if(io == 1)tinport = 1;
								else if(io == 2)toutport = 1;
							}
						}
					}
					else//rectangle
					{
						if(angle<=30)
						{
							if(io == 1)tinport = 0;
							else if(io == 2)toutport = 0;
						}
						else if(angle>150 && angle<=180)
						{
							if(io == 1)tinport = 2;
							else if(io == 2)toutport = 2;
						}
						else
						{
							if(x<=objs.get(i).getX()+objs.get(i).width/2)//left=3
							{
								if(io == 1)tinport = 3;
								else if(io == 2)toutport = 3;
							}
							else//right=2
							{
								if(io == 1)tinport = 1;
								else if(io == 2)toutport = 1;
							}
						}
					}
					//
					temp = objs.get(i);
				}
			}
		}
		return temp;
	}
	//
	public void changeObjName(String s) {
		
		if(changeObj!=-1)
		{
			for(int i=0;i<objs.size();i++)
			{
				if(objs.get(i).uid == changeObj)
				{
					objs.get(i).changeName(s);
					break;
				}
			}
		}
	}

	public void group() {
		
		int k = -1;
		int count = 0;
		//find selected composite
		for(int i=0;i<composites.size();i++)
		{
			if(composites.get(i).selected == true)
			{
				count++;
			}
		}
		//find selected object
		for(int i=0;i<objs.size();i++)
		{
			if(objs.get(i).selected == true && objs.get(i).composited == false)
			{
				count++;
				objs.get(i).composited = true;
				k=i;
			}
		}
		if(count >1)
		{
			Composite c= new Composite(objs,depthcount++);
			this.add(c);//add to Canvas OAQ"
        	composites.add(c);//add to composite list
		}
		else if(count == 1 && k!=-1)objs.get(k).composited = false; //only select one object, not composite.
	}

	public void ungroup() {
		
		int k = -1;
		int count = 0;
		//find selected composite
		for(int i=0;i<composites.size();i++)
		{
			if(composites.get(i).selected == true)
			{
				k=i;
				count++;
			}
		}
		//find selected object
		for(int i=0;i<objs.size();i++)
		{
			if(objs.get(i).selected == true && objs.get(i).composited == false)
			{
				count++;
			}
		}
		//
		if(k != -1 && count == 1)
		{
			for(int i=0;i<composites.get(k).objls.size();i++)
			{
				boolean stillIn = false;
				//check
				for(int j=0;j<k;j++)
				{
					for(int t=0;t<composites.get(j).objls.size();t++)
					{
						if(composites.get(k).objls.get(i).uid == composites.get(j).objls.get(t).uid)
						{
							stillIn = true;
							break;
						}
					}
				}
				//set
				if(stillIn == false)//change object' composite to false
				{
					for(int j=0;j<objs.size();j++)
					{
						if(composites.get(k).objls.get(i).uid == objs.get(j).uid)
						{
							objs.get(j).composited = false;
							break;
						}
					}
				}
			}
			this.remove(composites.get(k));//OAQ"
			composites.remove(k);
			
		}
	}
}