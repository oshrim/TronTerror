package Game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;

public class GameFrame extends JFrame implements ActionListener{

	public static final int FRAME_WIDTH = 1197, FRAME_HEIGHT = 899;

	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem start, controls, exit;
	
	public GamePanel gamePanel;
	private ContolersPanel controlersPanel;
	private WellcomePanel welcome;

	public GameFrame()
	{
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("TronTeror Version 0.5.1 beta - by SagiDayan");
		setResizable(false);
		
		//*************** Menu Bar *******************

		menuBar = new JMenuBar();
		menu = new JMenu("Menu");
		start = new JMenuItem("Start Game");
		controls = new JMenuItem("controlers");
		menu.add(start);
		menu.add(controls);
		menuBar.add(menu);
		
		start.addActionListener(this);
		controls.addActionListener(this);
		//add menuBar to frame
		
		
		this.setJMenuBar(menuBar);

		//End Menu.
		
	
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	
		welcome = new WellcomePanel();
		controlersPanel = new ContolersPanel();
		gamePanel = new GamePanel();
		
		
		//gamePanel.addKeyListener(gamePanel);
		
		add(welcome);
		
		
		setVisible(true);


	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == controls)
		{
			System.out.println("controles");
			welcome.setVisible(false);
			gamePanel.setVisible(false);
			add(controlersPanel);
			controlersPanel.setVisible(true);
		}
		else if( e.getSource() == start)
		{
			welcome.setVisible(false);
			controlersPanel.setVisible(false);
			gamePanel.setVisible(true);
			add(gamePanel);
			gamePanel.start();
		}
		
	}

}
