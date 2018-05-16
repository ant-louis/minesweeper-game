package be.ac.ulg.montefiore.oop;
import be.ac.ulg.montefiore.oop.graphics.MinesweeperView;

public class MineTile extends Tile {
	
	
	// Constructor
	public MineTile(int value, boolean revealed, boolean flag){
		super(value, revealed, flag);
	}

	
	// In case of left click on a mine
	void leftClickAction(Grid grid, Tile[][] tileGrid, int x, int y){
		explode();
		revealAllMines(grid, tileGrid);
	}
	
	// If a mine explode
	private void explode(){
		this.value = MinesweeperView.TILE_EXPLODED;
	}
	
	
	// Reveal the mines
	private void revealAllMines(Grid grid, Tile[][] tileGrid){
		for(int j=0; j < grid.getHeight(); j++){
			for(int i=0; i < grid.getWidth(); i++){
				if (tileGrid[j][i] instanceof MineTile){
					if(tileGrid[j][i].hasFlag() == false)
						tileGrid[j][i].revealContent();
				}else{
					if(tileGrid[j][i].hasFlag() == true){
						tileGrid[j][i].value = MinesweeperView.TILE_INCORRECT;
						tileGrid[j][i].revealContent();
					}
				}
			}
		}
	}
						
						
											
}