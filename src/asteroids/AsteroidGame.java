package asteroids;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AsteroidGame extends JFrame {

	/*
	 * Further developments:
	 * - put static stars in the background (DONE)
	 * - use polygon to create a better shape for the asteroids
	 * - change scoring system 
	 * - change from a station to a ship which you can move around the screen
	 *		consider controls and what will happen if you go off the screen
	 * - use mouse to shoot
	 * - you can lose (limited amount of health) -> draw health bar(?)
	 * - power ups 
	 * 		- increase speed (5s)
	 * 		- shield (5s)
	 * 		- area of destruction (spawn rate low and small radius)
	 * 		- area of deflection
	 * 		- nuke 
	 */
	
	/* 
	 * Decomposed:
	 * asteroid object
	 * rocket object
	 * station object
	 * 
	 * extends - Asteroid Game is a JFrame (access functions and procedures that are part of JFrame object 
	 * 			can only extend one class
	 * implement - tells us methods we have to include, whilst extends gives us access to stuff
	 * 			implement as many abstract classes as you want 
	 * 
	 * Attributes - the things that we need to know about a particular object
	 * Constructor - function that is called when object is created and allows us to implement attributes
	 * 				without a constructor you would have to implement setters to set your attributes to something
	 * 				constructor handles what happens when you create an instance of an object 
	 * 
	 * procedure - updates internal properties of an object
	 * function - takes something in and returns something back to us
	 * 
	 * parameters - the variable that we can use to access the values of the arguments so we can use those values in the function/procedure
	 * arguments - the value that we are passing in
	 * 
	 * a function/procedure should do what it states in the name and nothing more
	 * 
	 * main() -> creates an instance of the asteroid game that calls the asteroidGame() constructor
	 * 
	 * Threading:
	 * - main program thread (just waits for window to be closed)
	 * - update position of pieces and redraw
	 * 
	 * keydown is a completely separate class - easier to understand what's going on
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private int frameWidth = 500;
	private int frameHeight = 400;

	private LinkedList<Asteroid> asteroids = new LinkedList<Asteroid>();
	private LinkedList<Rocket> rockets = new LinkedList<Rocket>();
	private LinkedList<Star> stars = new LinkedList<Star>();
	private Station station = new Station(frameWidth / 2, frameHeight - 40);
	private Space space = new Space();
	private Timer timer; // threading - more than one program running at the same time 
	

	public static void main(String[] args) {
		new AsteroidGame(); // a JFrame
	}
	public AsteroidGame() {
		setTitle("Asteroids Game");
		setBounds(50, 50, frameWidth, frameHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(new KeyDown());
		add(space);
		setVisible(true);
	}

	public class Space extends JPanel {

		private static final long serialVersionUID = 1L;
		
		// a JPanel
		public Space() {
			setBackground(Color.BLACK);
			createStars();
			// recalculate positions and redraw them every 50 ms
			timer = new Timer(50, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					movePieces();
					repaint(); // built in method of JPanel that calls paintComponent
				}
			});
			timer.start();
		}

		public void paintComponent(Graphics g) {
			// need to use super. to get access to the method of a parent class
			super.paintComponent(g); // colour in the background
			station.paintComponent(g); // paint station
			for (Asteroid rock : asteroids) {
				rock.paintComponent(g);
			}
			for (Rocket rocket : rockets) {
				rocket.paintComponent(g);
			}
			for (Star star : stars) {
				star.paintComponent(g);
			}
		}
		
		private void createStars() {
			int noStars = (int) (Math.random()*10)+30; // we want between 30 and 40 stars
			for (int i=0;i<noStars;i++) {
				Star newStar = new Star();
				stars.add(newStar);
			}
		}

		// check if pieces have been destroyed (asteroid) or moved off the screen (asteroid or 
		private void movePieces() {
			if (Math.random() < 0.1) {
				Asteroid newRock = new Asteroid(frameWidth * Math.random(), 20, 10 * Math.random() - 5,
						2 + 3 * Math.random());
				asteroids.add(newRock);
			}

			LinkedList<Asteroid> destroyedRocks = new LinkedList<Asteroid>();
			for (Asteroid rock : asteroids) {
				rock.move();
				station.checkHit(rock); // check collision
				if (rock.size < 1) {
					// add to list of asteroids to be removed if size is smaller than 1
					destroyedRocks.add(rock);
				}
			}
			asteroids.removeAll(destroyedRocks); // .removeAll() is a link list method 

			LinkedList<Rocket> removeRockets = new LinkedList<Rocket>();
			for (Rocket rocket : rockets) {
				// rocket.move(asteroid) returns true if rocket has collided with asteroid
				if (rocket.move(asteroids) || rocket.x < -5 || rocket.x > frameWidth + 5 || rocket.y < -5) {
					removeRockets.add(rocket); // add to list of rockets to be removed
				}
			}
			rockets.removeAll(removeRockets);
		}
	}

	
	private class KeyDown implements KeyListener {
		public void keyPressed(KeyEvent e) {
			char key = e.getKeyChar();
			switch (key) {
				case 'j':
					station.moveLeft();
					break;
				case 'k':
					station.moveRight();
					break;
				case ' ':
					station.fire(rockets);
					break;
				case 'p':
					if (timer.isRunning())
						timer.stop();
					else
						timer.start();
			}
		}

		public void keyReleased(KeyEvent e) {
			// when a key is released
		}

		public void keyTyped(KeyEvent e) {
			// 
		}
	}
}
