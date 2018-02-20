package be.ac.ulg.montefiore.oop;
import be.ac.ulg.montefiore.oop.graphics.MinesweeperView;

public class Random {
	
	private int level;
	private int nbreMines;
	private int height;
	private int width;
	private int[][] tabMines;
	
	// Constructor
	public Random(String currentLevel){
		
		switch(currentLevel){
			case "easy":
				this.level = MinesweeperView.EASY;
				this.nbreMines = MinesweeperView.EASY_MINES;
				this.height = MinesweeperView.EASY_HEIGHT;
				this.width = MinesweeperView.EASY_WIDTH;
				break;
			case "medium":
				this.level = MinesweeperView.MEDIUM;
				this.nbreMines = MinesweeperView.MEDIUM_MINES;
				this.height = MinesweeperView.MEDIUM_HEIGHT;
				this.width = MinesweeperView.MEDIUM_WIDTH;
				break;
			case "hard":
				this.level = MinesweeperView.HARD;
				this.nbreMines = MinesweeperView.HARD_MINES;
				this.height = MinesweeperView.HARD_HEIGHT;
				this.width = MinesweeperView.HARD_WIDTH;
				break;
		}
		
	}
	
	
	// Return array of the random mines coordinates
	public int[][] makeRandomNumbers(){
	
		int i = 0;
		int randomY;
		int randomX;
		tabMines = new int [nbreMines][2];
		
		while(i < nbreMines){
			randomY = (int) (Math.random() * height );
			randomX = (int) (Math.random() * width );
			
			if(existAlready(randomX, randomY, i) == false){
				tabMines[i][0] = randomX;
				tabMines[i][1] = randomY;
				i++;
			}
		}
		return tabMines;
	}
	
	
	// Check if a pair of coordinates already exists
		private boolean existAlready(int x, int y, int size){
			
			boolean exist = false;
			
			for(int i = 0; i < size; i++){
				if(tabMines[i][0] == x && tabMines[i][1] == y)
					exist = true;
				else
					exist = false;		
			}
			return exist;
		}
	
	
	// Get the level
		public int getLevel(){
			return level;
		}
			
	//Get the number of Mines
		public int getNbreMines(){
			return nbreMines;
		}
	
	// Get height
		public int getHeight(){
			return height;
		}
	
	// Get width
		public int getWidth(){
			return width;
		}
	

}