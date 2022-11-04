package asteroids;

import java.awt.Color;
import java.awt.Graphics;

public class Asteroid {
	// have 5 pieces of information
	// x, y, size, velocity - can be split into x and y component (dx,dy)
	
	private double x, y;
	private double dx, dy;
	public int size = 20; // diameter
	
	public Asteroid(double x, double y, double dx, double dy) {
		// like self in python 
		// this.x refers to the global variable, whilst just x refers to the local variable
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}
	
	public void move() {
		x += dx;
		y += dy;	
	}
	
	public boolean nearTo(double tx, double ty) {
		// tx - target x, ty - target y
		// used to check the coordinates of the target value
		double distance = Math.sqrt((x-tx)*(x-tx) + (y-ty)*(y-ty));
		return distance < size/2;
	}
	
	public void hit() {
		// if asteroid is hit reduce its diameter by 4
		size -= 4;
	}
	
	public void paintComponent(Graphics g) {
        // number of vertices
        int numVertices = (int) (Math.random()*5) + 5; // between 5 and 10 vertices
        
        // x coordinates of vertices
        int[] xVertices = new int[numVertices];
  
        // y coordinates of vertices
        int[] yVertices = new int[numVertices];
        
        for (int i=0; i<numVertices;i++) {
        	// get x and y coors
        	break;
        }
  
        // set the color of line drawn to blue
        g.setColor(Color.GRAY);
  
        // draw the polygon using drawPolygon function
        // g.drawPolygon(x, y, numVertices);
        g.fillOval((int) x , (int) y, size, size);
	}
	
}
