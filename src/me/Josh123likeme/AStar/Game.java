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
		
		double blockSize = (double) getWidth() / board.getWidth() < (double) getHeight() / board.getHeight() ? (double) getWidth() / board.getWidth() : (double) getHeight() / board.getHeight();
		
		for (int y = 0; y < board.getHeight(); y++) {
			
			for (int x = 0; x < board.getWidth(); x++) {
				
				int colour = 0;
				
				if (board.getBlockAt(x, y) == -1) colour = 255 << 24 | 0 << 16 | 0 << 8 | 0; //wall
				else if (board.getBlockAt(x, y) == 0) colour = 255 << 24 | 255 << 16 | 255 << 8 | 255; //empty tile
				else if (board.getBlockAt(x, y) == 1) colour = 255 << 24 | 0 << 16 | 0 << 8 | 255; //start
				else if (board.getBlockAt(x, y) == 2) colour = 255 << 24 | 0 << 16 | 0 << 8 | 255; //end
				else if (board.getBlockAt(x, y) == 3) colour = 255 << 24 | 0 << 16 | 255 << 8 | 0; //cleared
				else if (board.getBlockAt(x, y) == 4) colour = 255 << 24 | 255 << 16 | 0 << 8 | 0; //tried
				else if (board.getBlockAt(x, y) == 5) colour = 255 << 24 | 0 << 16 | 0 << 8 | 255; //path
				
				Color color = new Color(colour);
				
				g.setColor(color);
				
				g.fillRect((int) (x * blockSize) , (int) (y * blockSize), (int) (blockSize) + 1, (int) (blockSize) + 1);
				
			}
			
		}
		
		//this pushes the graphics to the window
		bufferStrategy.show();
		
	}
	
}