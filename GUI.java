package umlEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class GUI extends JFrame {
	
	private Mode mode;
	private Buttonlist buttons;
	private Canvas canvas;
	//int editMode = -1;
	//main
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0,0,screenSize.width-10, screenSize.height -40);//frame size
        setTitle("UML Editor");
        //
        mode = new Mode();
        canvas = new Canvas(mode);
        buttons = new Buttonlist(mode, canvas);
        
        //MenuBar
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		//Edit Items
		JMenuItem mntmGroup = new JMenuItem("Group");
		mntmGroup.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				canvas.group();
				canvas.clearSelect();
			}
		});
		mnEdit.add(mntmGroup);
		
		JMenuItem mntmUngroup = new JMenuItem("UnGroup");
		mntmUngroup.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				canvas.ungroup();
				canvas.clearSelect();
			}
		});
		mnEdit.add(mntmUngroup);
		
		JMenuItem mntmChangeObjectName = new JMenuItem("Change object name");
		mntmChangeObjectName.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				String s = JOptionPane.showInputDialog("Enter to change name:");
				if(s != null)canvas.changeObjName(s);
				canvas.clearSelect();
			}
		});
		mnEdit.add(mntmChangeObjectName);
		
		//button
		getContentPane().add(buttons, BorderLayout.WEST);
		buttons.setPreferredSize(new Dimension(120, 120));
		buttons.setLayout(new GridLayout(6,1));
		
		//canvas
		canvas.setBackground(Color.WHITE);
		canvas.setLayout(null); //relative position (shrink does not disappear)

		getContentPane().add(canvas, BorderLayout.CENTER);
		
	}

}