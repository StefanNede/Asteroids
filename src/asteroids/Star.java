package asteroids;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Star {
	
	private int x,y;
	private int size = 2;
	
	public Star() {
		this.x = (int) (Math.random()*500) + 1;
		this.y = (int) (Math.random()*400) + 1;
//		System.out.println(x);
//		System.out.println(y);
	}
	
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.WHITE);
		g.fillOval(x, y, size, size);
	}

}
