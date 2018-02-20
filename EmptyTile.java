package be.ac.ulg.montefiore.oop;


public class EmptyTile extends Tile {
	
	
	// Constructor
	public EmptyTile(int value, boolean revealed, boolean flag){
		super(value, revealed, flag);
	}
	
	
	// In case of left click on an empty tile
		void leftClickAction(Grid grid, Tile[][] tileGrid, int x, int y){
			floodFill(grid, tileGrid, x, y);
		}
	
		

	// Reveal all the empty tiles of a block
		private void floodFill(Grid grid, Tile[][] tileGrid, int x, int y){
			
			if(x >= 0 && y >= 0 && x < grid.getWidth() && y < grid.getHeight()){
		
					if(tileGrid[y][x].isRevealed() == false && tileGrid[y][x].hasFlag() == false && tileGrid[y][x] instanceof NumberedTile)
						tileGrid[y][x].revealContent();
					
					if(tileGrid[y][x].isRevealed() == false && tileGrid[y][x].hasFlag() == false && tileGrid[y][x] instanceof EmptyTile){
						
						tileGrid[y][x].revealContent();
						
						for(int i = x-1; i <= x+1; i++){
							for(int j = y-1; j <= y+1; j++){
								floodFill(grid, tileGrid, i, j);
							}
						}
					}
			}
			else
				return;	
		}

		
}