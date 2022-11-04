package asteroids;
import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Station {

	private double x,y;
	private double angle = Math.PI / 2.0;
	private double hits;

	public Station(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void checkHit(Asteroid rock) {
		if (rock.nearTo(x, y)) {
			hits += rock.size;
		}
	}

	public void moveLeft() {
		angle += 0.1;
	}

	public void moveRight() {
		angle -= 0.1;
	}

	public void fire(LinkedList<Rocket> rockets) {
		// pass by reference is used here so we can change the linked list rockets, as a pointer to the memory address is taken in
		double cosAngle = Math.cos(angle);
		double sinAngle = Math.sin(angle);
		Rocket r = new Rocket(x+15 * cosAngle, y - 15 * sinAngle, 2 * cosAngle, -2 * sinAngle); // to move something up a screen it needs to have a negative number
		rockets.add(r);
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);	
		double lv = 20 * Math.sin(angle);
		double lh = 20 * Math.cos(angle);
		g.drawLine((int)x, (int)y, (int)(x+lh), (int)(y-lv));
		g.drawString("Hits: " + hits, (int) x+50, (int)y-5);
	}	

	
}