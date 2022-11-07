package me.Josh123likeme.AStar;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

public class Main {

	private static Random random = new Random();
	
	public static Board board;
	
	public static void main(String[] args) {
		
		Game game = new Game();
		
		game.start();
		
		board = new Board(200, 200);
		
		for (int i = 0; i < board.getWidth() * board.getHeight() / 150; i++) {
			
			int ox = random.nextInt(board.getWidth());
			int oy = random.nextInt(board.getHeight());
			
			int w = random.nextInt(20);
			int h = random.nextInt(20);
			
			for (int y = 0; y < h; y++) {
				
				for (int x = 0; x < w; x++) {
					
					if (ox + x < 0 || ox + x >= board.getWidth() || oy + y < 0 || oy + y >= board.getHeight()) continue;
					
					board.setBlockAt(ox + x, oy + y, -1);
					
				}
				
			}
			
		}
		
		long startTime = System.nanoTime();
		
		AStar aStar = new AStar();
		
		aStar.doAStar(board, 
				new Vector2D(0, 0), 
				new Vector2D(board.getWidth() - 1, board.getHeight() - 1));
		
		System.out.println("DONE IN " + ((double) (System.nanoTime() - startTime) / 1000000) + " ms");
		/*
		BufferedImage image = new BufferedImage(board.getWidth(), board.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
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
				
				image.setRGB(x, y, colour);
				
			}
			
		}
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	    Date date = new Date();
	    File outputfile = new File(String.valueOf(dateFormat.format(date)) + ".png");
		
		try {
	        ImageIO.write(image, "png", outputfile);
	      } catch (IOException e) {
	        e.printStackTrace();
	      } 
		*/
	}
	
}
