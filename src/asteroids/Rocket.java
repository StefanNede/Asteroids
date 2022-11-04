package asteroids;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Rocket {
	
	public double x, y; // need to be public because when calling asteroid's nearTo method then these need to be checked
	// otherwise we would have to create a getX and getY method
	private double dx, dy;
	
	public Rocket(double x, double y, double dx, double dy) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}
	
	public boolean move(LinkedList<Asteroid> asteroids) {
		// returns true if the rocket hits the asteroid 
		// true => rocket stops moving + going to get destroyed
		x += dx;
		y += dy;
		for (Asteroid asteroid : asteroids) {
			if (asteroid.nearTo(x, y)) {
				asteroid.hit(); // decrease size of asteroid 
				return true;
			}
		}
		return false;
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval((int) x-3, (int) y-3, 6, 6);
	}
	
}
