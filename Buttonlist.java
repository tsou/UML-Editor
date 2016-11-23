package umlEditor;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Buttonlist extends JPanel{
	
	private JButton icon1 = new JButton();
	private JButton icon2 = new JButton();
	private JButton icon3 = new JButton();
	private JButton icon4 = new JButton();
	private JButton icon5 = new JButton();
	private JButton icon6 = new JButton();
    
	public Buttonlist(Mode mode,Canvas canvas){
		
	    icon1.setIcon(new ImageIcon(getClass().getResource("icon1.jpg")));
	    icon1.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {
	        	change(canvas);
	        	icon1.setEnabled(false);
	        	mode.setMode(1);
	        }
	    });
		this.add(icon1);
		
		icon2.setIcon(new ImageIcon(getClass().getResource("icon2.jpg")));
	    icon2.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {
	        	change(canvas);
	        	icon2.setEnabled(false);
	        	mode.setMode(2);
	        }
	    });
		this.add(icon2);
		
		icon3.setIcon(new ImageIcon(getClass().getResource("icon3.jpg")));
	    icon3.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {
	        	change(canvas);
	        	icon3.setEnabled(false);
	        	mode.setMode(3);
	        }
	    });
		this.add(icon3);
		
		icon4.setIcon(new ImageIcon(getClass().getResource("icon4.jpg")));
	    icon4.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {
	        	change(canvas);
	        	icon4.setEnabled(false);
	        	mode.setMode(4);
	        }
	    });
		this.add(icon4);
		
		icon5.setIcon(new ImageIcon(getClass().getResource("icon5.jpg")));
	    icon5.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {
	        	change(canvas);
	        	icon5.setEnabled(false);
	        	mode.setMode(5);
	        }
	    });
		this.add(icon5);

		icon6.setIcon(new ImageIcon(getClass().getResource("icon6.jpg")));
	    icon6.addActionListener(new ActionListener()
	    {
	        public void actionPerformed(ActionEvent e)
	        {
	        	change(canvas);
	        	icon6.setEnabled(false);
	        	mode.setMode(6);
	        	
	        }
	    });
		this.add(icon6);
		
	}

	protected void change(Canvas canvas) {
		
		canvas.clearSelect();
    	icon1.setEnabled(true);
    	icon2.setEnabled(true);
    	icon3.setEnabled(true);
    	icon4.setEnabled(true);
    	icon5.setEnabled(true);
    	icon6.setEnabled(true);
    	
	}

}