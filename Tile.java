package be.ac.ulg.montefiore.oop;
import be.ac.ulg.montefiore.oop.graphics.MinesweeperView;


public abstract class Tile {

	protected int value;
	protected boolean revealed;
	protected boolean flag;
	
	static int revealedTiles = 0;
	

	// Constructor
	protected Tile(int value, boolean revealed, boolean flag){
		this.value = value;
		this.revealed = revealed;
		this.flag = flag;
	}
	
	
	// In case of left click on  a tile
	abstract void leftClickAction(Grid grid, Tile[][] tileGrid, int x, int y);
	
	
	// Update the value the value
	public int updateValue(){
		if(isRevealed() == false && hasFlag() == false)
			return MinesweeperView.TILE_HIDDEN;
		if(isRevealed() == false && hasFlag() == true)
			return MinesweeperView.TILE_FLAG;
		else
			return value;
	}
	
	
	// Get state
	public boolean isRevealed(){
		return revealed;
	}
	
	// Get flag
	public boolean hasFlag(){
		return flag;
	}
	
	
	// Reveal the content of the tile
	public void revealContent(){
		this.revealed = true;
		revealedTiles++;
	}
	
	// Put a flag
	public void putFlag(){
		this.flag = true;
	}
	
	// Remove the flag
	public void removeFlag(){
		this.flag = false;
	}
	
	// Get number of revealed tiles
	public int getRevealedTiles(){
		return revealedTiles;
	}
	
	// Initialize revealed tiles
	public void initializeRevealedTiles(){
		revealedTiles = 0;
	}
	
	
}