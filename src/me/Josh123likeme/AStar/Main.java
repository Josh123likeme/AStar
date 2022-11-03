package me.Josh123likeme.AStar;

import java.util.Random;

public class Main {

	private static Random random = new Random();
	
	public static Board board;
	
	public static void main(String[] args) {
		
		Game game = new Game();
		
		game.start();
		
		AStar aStar = new AStar();
		
		board = new Board(40, 40);
		
		for (int i = 0; i < 10; i++) {
			
			int ox = random.nextInt(38) + 1;
			int oy = random.nextInt(38) + 1;
			
			for (int y = -1; y <= 1; y++) {
				
				for (int x = -1; x <= 1; x++) {
					
					board.setBlockAt(ox + x, oy + y, -1);
					
				}
				
			}
			
		}
		
		long startTime = System.nanoTime();
		
		aStar.doAStar(board, new Vector2D(random.nextInt(40), random.nextInt(40)), new Vector2D(random.nextInt(40), random.nextInt(40)));
		
		System.out.println("DONE IN " + ((double) (System.nanoTime() - startTime) / 1000000) + " ms");
		
	}
	
}
