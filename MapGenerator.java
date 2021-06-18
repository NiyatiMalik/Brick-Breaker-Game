package BrickBreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
	
	public int map[][];         //contains all the bricks
	public int brickwidth;
	public int brickheight;
	public MapGenerator(int rows, int cols) {
		map = new int[rows][cols];
		for(int i = 0; i < map.length; i ++) {
			for(int j = 0; j < map[0].length; j ++) {
				map[i][j] = 1;                              //1 tells that the particular brick has not been intersected with the ball
				
			}
		}
		brickwidth = 540/cols;
		brickheight = 150/rows;
	}
	
	public void draw(Graphics2D g) {                         //to draw the bricks at the required position
		for(int i = 0; i < map.length; i ++) {
			for(int j = 0; j < map[0].length; j ++) {
				if(map[i][j] > 0) {
					g.setColor(Color.lightGray);
					g.fillRect(j*brickwidth + 80, i*brickheight + 50, brickwidth, brickheight);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j*brickwidth + 80, i*brickheight + 50, brickwidth, brickheight);
				}
				
			}
		}
	}
	
	public void setBrickValue(int value, int row, int col) {
		map[row][col] = value;
	}
}
