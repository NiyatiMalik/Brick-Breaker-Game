package BrickBreaker;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame obj = new JFrame();             //making the main window frame
		Gameplay game = new Gameplay();
		obj.setBounds(10, 10, 700, 600);         //setting size of the main window
		obj.setTitle("Brick Breaker");           //title of game
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(game);
	}

}
