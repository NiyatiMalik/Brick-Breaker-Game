package BrickBreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import javax.swing.Timer;


import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
	private boolean play = false;       //game shouldnt play by itself when it starts
	private int score = 0;
	private int totalbricks = 21;
	
	private Timer timer;
	private int delay = 2;
	
	private int sliderX = 310;       //starting position of slider
	private int ballX = 120;         //starting position of ball
	private int ballY = 350;
	private int ballXdir = -1;
	private int ballYdir = -2;
	
	private MapGenerator map;
	
	public Gameplay() {
		// TODO Auto-generated constructor stub
		map = new MapGenerator(3, 7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics g) {                  //to draw shapes likes slider and ball
		
		//background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
		//drawing map
		map.draw((Graphics2D)g);
		
		//borders
		g.setColor(Color.white);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);                //these are borders for all 3 sides. Not adding border for the bottom because game ends when ball touches bottom.
		
		//Scores
		g.setColor(Color.green);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString(""+score, 590, 30);
		
		//Slider
		g.setColor(Color.cyan);
		g.fillRect(sliderX, 550, 100, 8);
		
		//Ball
		g.setColor(Color.blue);
		g.fillOval(ballX, ballY, 20, 20);
		
		if(totalbricks <= 0) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.green);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("You Won!", 260, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart", 230, 350);
		}
		
		if(ballY > 570) {                    //in case slider misses ball
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.green);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Game Over! Your score is : " + score, 190, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart", 230, 350);
		}
		
		g.dispose();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play) {
			
			if(new Rectangle(ballX, ballY, 20, 20).intersects(new Rectangle(sliderX, 550, 100, 8))) {
				ballYdir = -ballYdir;
			}
			
			A: for(int i = 0; i < map.map.length; i ++) {
				for(int j = 0; j < map.map[0].length; j ++) {
					if(map.map[i][j] > 0) {                       //detecting intersection of ball with brick
						int brickX = j*map.brickwidth + 80;
						int brickY = i*map.brickheight + 50;
						int brickwidth = map.brickwidth;
						int brickheight = map.brickheight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickwidth, brickheight);
						Rectangle ballrect = new Rectangle(ballX, ballY, 20, 20);                     //creating rect around ball so that its intersection with brick can be found out
						Rectangle brickrect = rect;
						
						if(ballrect.intersects(brickrect)) {
							map.setBrickValue(0, i, j);
							totalbricks --;
							score += 10;
							
							if(ballX + 19 <= brickrect.x || ballX + 1 >= brickrect.x + brickrect.width) {
								ballXdir = -ballXdir;
							}
							else {
								ballYdir = -ballYdir;
							}
							break A;
						}
					}
				}
			}
			
			ballX += ballXdir;
			ballY += ballYdir;
			if(ballX < 0) {
				ballXdir = -ballXdir;
			}
			if(ballY < 0) {
				ballYdir = -ballYdir;
			}
			if(ballX > 670) {
				ballXdir = -ballXdir;
			}
		}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(sliderX >= 600) {
				sliderX = 600;
			}
			else {
				moveRight();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(sliderX < 10) {
				sliderX = 10;
			}
			else {
				moveLeft();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play) {
				play = true;
				ballX = 120;
				ballY = 350;
				ballXdir = -1;
				ballYdir = -2;
				sliderX = 310;
				score = 0;
				totalbricks = 21;
				map = new MapGenerator(3, 7);
				repaint();
			}
		}
	}
	
	public void moveRight() {
		play = true;
		sliderX += 20;
	}
	
	public void moveLeft() {
		play = true;
		sliderX -= 20;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
