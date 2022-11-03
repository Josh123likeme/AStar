package me.Josh123likeme.AStar;

public class Board {

	private int[][] blocks;
	
	public Board(int width, int height) {
		
		blocks = new int[height][width];
		
	}
	
	public int getBlockAt(int x, int y) {
		
		return blocks[y][x];
		
	}
	
	public void setBlockAt(int x, int y, int block) {
		
		blocks[y][x] = block;
		
	}
	
	public int getWidth() {
		
		return blocks[0].length;
		
	}
	
	public int getHeight() {
		
		return blocks.length;
		
	}
	
}
