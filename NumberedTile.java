package be.ac.ulg.montefiore.oop;

public class NumberedTile extends Tile {
	
	// Constructor
	public NumberedTile(int value, boolean revealed, boolean flag){
		super(value, revealed, flag);
	}

	
	// In case of left click on a number
		void leftClickAction(Grid grid, Tile[][] tileGrid, int x, int y){
			revealContent();
		}
	
}