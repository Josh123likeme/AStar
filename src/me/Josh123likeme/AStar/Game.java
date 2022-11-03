package me.Josh123likeme.AStar;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	public static final int INITIAL_WIDTH = 400, INITIAL_HEIGHT = 400;
	
	private Window window;
	private Thread thread;
	private boolean running = false;

	public Game() {
		
		window = new Window(INITIAL_WIDTH, INITIAL_HEIGHT, "The Labyrinth Of Recursion", this);

	}
	
	public synchronized void start() {
		
		thread = new Thread(this);
		thread.start();
		running = true;
		
	}
	
	public synchronized void stop() {
		
		try 
		{
			thread.join();
			running = false;
		}
		
		catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public void run() {
		
		while (true) {
			
			paint();
			
		}
		
	}

	public void paint() {
	
		BufferStrategy bufferStrategy = this.getBufferStrategy();
		if(bufferStrategy == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bufferStrategy.getDrawGraphics();
		
		//basic black background to stop flashing
		g.setColor(Color.black); 
		g.fillRect(0, 0, getWidth(), getHeight());
		
		//put rendering stuff here
		
		Board board = Main.board;
		
		for (int y = 0; y < board.getHeight(); y++) {
			
			for (int x = 0; x < board.getWidth(); x++) {
				
				g.setColor(Color.black);
				
				g.fillRect(x * 30, y * 30, 30, 30);
				
				if (board.getBlockAt(x, y) == -1) g.setColor(Color.black);
				else if (board.getBlockAt(x, y) == 0) g.setColor(Color.white);
				else if (board.getBlockAt(x, y) == 1) g.setColor(Color.blue);
				else if (board.getBlockAt(x, y) == 2) g.setColor(Color.orange);
				else if (board.getBlockAt(x, y) == 3) g.setColor(Color.green);
				else if (board.getBlockAt(x, y) == 4) g.setColor(Color.red);
				else if (board.getBlockAt(x, y) == 5) g.setColor(Color.pink);
				
				g.fillRect(x * 30 + 2, y * 30 + 2, 26, 26);
				
			}
			
		}
		
		//this pushes the graphics to the window
		bufferStrategy.show();
		
	}
	
}