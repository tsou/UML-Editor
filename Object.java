package umlEditor;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Object extends JPanel{
	
	protected int uid = 0;
	protected int depth = 0;
	protected int width = -1;
	protected int high = -1;
	protected JLabel nameArea;
	protected boolean selected = false;
	protected boolean composited = false;
	
	protected boolean[] port = {false,false,false,false};
	
	public void setPoint(int x,int y)
	{
		this.setLocation(this.getX()+x,this.getY()+y);
	}
	public void changeName(String s)
	{
		nameArea.setText(s);
		nameArea.setFont(new Font("Verdana",1,20));
		//nameArea.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		//nameArea.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
	}
}