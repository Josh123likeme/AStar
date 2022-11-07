package me.Josh123likeme.AStar;

import java.util.ArrayList;
import java.util.List;

public class AStar {

	public Vector2D[] doAStar(Board board, Vector2D start, Vector2D end) {
		
		//starting algorithm
		
		boolean[][] cleared = new boolean[board.getHeight()][board.getWidth()];
		boolean[][] tried = new boolean[board.getHeight()][board.getWidth()];
		
		double[][] gCosts = new double[board.getHeight()][board.getWidth()];
		
		Vector2D[][] parents = new Vector2D[board.getHeight()][board.getWidth()];
		
		tried[start.Y][start.X] = true;
		
		Vector2D pick = start.clone();
		
		do {
			
			//expand
			for (int y = -1; y <= 1; y++) {
				
				for (int x = -1; x <= 1; x++) {
					
					if (x == 0 && y == 0) continue;
					
					Vector2D attempt = new Vector2D(pick.X + x, pick.Y + y);
					
					if (attempt.X < 0 || attempt.X >= board.getWidth() || attempt.Y < 0 || attempt.Y >= board.getHeight()) continue;
						
					if (board.getBlockAt(attempt.X, attempt.Y) == -1) continue;
							
					if (tried[attempt.Y][attempt.X]) continue;
					
					if (parents[attempt.Y][attempt.X] == null) {
						
						cleared[attempt.Y][attempt.X] = true;
						parents[attempt.Y][attempt.X] = pick.clone();
						
						if (x == 0 || y == 0) gCosts[attempt.Y][attempt.X] = gCosts[pick.Y][pick.X] + 1;
						else gCosts[attempt.Y][attempt.X] = gCosts[pick.Y][pick.X] + Math.sqrt(2);
						
					}
					
					if (gCosts[pick.Y][pick.X] < gCosts[parents[attempt.Y][attempt.X].Y][parents[attempt.Y][attempt.X].X]) {
						
						parents[attempt.Y][attempt.X] = pick.clone();
						
						if (x == 0 || y == 0) gCosts[attempt.Y][attempt.X] = gCosts[pick.Y][pick.X] + 1;
						else gCosts[attempt.Y][attempt.X] = gCosts[pick.Y][pick.X] + Math.sqrt(2);
						
					}	
					
				}
				
			}

			//pick best
			List<Vector2D> bestFCosts = new ArrayList<Vector2D>();
			double bestFCost = Double.MAX_VALUE;
			
			for (int y = 0; y < board.getHeight(); y++) {
				
				for (int x = 0; x < board.getWidth(); x++) {

					if (!cleared[y][x]) continue;
					if (tried[y][x]) continue;
					if (start.X == x && start.Y == y) continue;
					
					Vector2D current = new Vector2D(x, y);
					
					double fCost = gCosts[current.Y][current.X] + current.distanceTo(end);

					if (fCost < bestFCost) {
						
						bestFCosts.clear();
						
						bestFCosts.add(current);
						bestFCost = fCost;
						
					}
					else if (fCost <= bestFCost) {
						
						bestFCosts.add(current);
						
					}
					
				}
				
			}
			
			List<Vector2D> bestHCosts = new ArrayList<Vector2D>();
			double bestHCost = Double.MAX_VALUE;
			
			for (Vector2D current : bestFCosts) {
				
				double hCost = current.distanceTo(end);
				
				if (hCost < bestHCost) {
					
					bestHCosts.clear();
					
					bestHCosts.add(current);
					bestHCost = hCost;
					
				}
				else if (hCost <= bestHCost) {
					
					bestHCosts.add(current);
					
				}
				
			}
			
			pick = bestHCosts.get(0);
			tried[pick.Y][pick.X] = true;
			
			//for debugging purposes
			for (int y = 0; y < board.getHeight(); y++) {
				
				for (int x = 0; x < board.getWidth(); x++) {

					if (cleared[y][x]) board.setBlockAt(x, y, 3);
					if (tried[y][x]) board.setBlockAt(x, y, 4);
					
				}
				
			}
			
			board.setBlockAt(start.X, start.Y, 1);
			board.setBlockAt(end.X, end.Y, 2);
			
		} while (pick.X != end.X || pick.Y != end.Y);
		
		//backtracking path
		List<Vector2D> reversePath = new ArrayList<Vector2D>();
		
		reversePath.add(end);
		
		Vector2D parent = pick;
		
		do {
			
			//this line is for debugging purposes
			board.setBlockAt(parent.X, parent.Y, 5);
			
			reversePath.add(parent);
			
			parent = parents[parent.Y][parent.X];
			
		} while (parent != null);
		
		//flip path
		Vector2D[] flipped = new Vector2D[reversePath.size()];
		
		for (int i = 0; i < reversePath.size(); i++) {
			
			flipped[flipped.length - i - 1] = reversePath.get(i);
			
		}
		
		board.setBlockAt(start.X, start.Y, 1);
		board.setBlockAt(end.X, end.Y, 2);
		
		return flipped;
		
	}
	
}
