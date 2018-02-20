package be.ac.ulg.montefiore.oop;
import be.ac.ulg.montefiore.oop.graphics.MinesweeperView;

public class Grid {
	
	private int level;
	private int height;
	private int width;
	private int nbreMines;
	private Tile[][] tileGrid;
	private int[][] printedGrid;
	
	// Constructor
	public Grid(int level, int height, int width, int nbreMines){
		this.level = level;
		this.height = height;
		this.width = width;
		this.nbreMines = nbreMines;
		this.tileGrid = new Tile[height][width];
		for (int y=0; y < height; y++){
			for (int x=0; x < width; x++){
				tileGrid[y][x] = new EmptyTile(MinesweeperView.TILE_EMPTY, false, false);
			}
		}
	}
	
	
	// Set mines tiles
		public void setMinesTiles(int[][] coordinates){
			int x, y;
			for (int k=0; k< nbreMines; k++){
				x = coordinates[k][0];
				y = coordinates[k][1];
				tileGrid [y][x] = new MineTile(MinesweeperView.TILE_MINE, false, false);
			}
		}

		
	// Set the numbered tiles
		public void setNumberedTiles (){
			int MinesNear = 0;
			
			for (int y = 0; y<height; y++){
				for(int x = 0; x<width; x++){
					
					if(!(tileGrid[y][x] instanceof MineTile)){
						MinesNear = getMinesNear(x,y);
						
						switch(MinesNear){
							case 1:
								tileGrid[y][x] = new NumberedTile(MinesweeperView.TILE_1, false, false);
								break;
							case 2:
								tileGrid[y][x] = new NumberedTile(MinesweeperView.TILE_2, false, false);
								break;
							case 3: 
								tileGrid[y][x] = new NumberedTile(MinesweeperView.TILE_3, false, false);
								break;
							case 4:
								tileGrid[y][x] = new NumberedTile(MinesweeperView.TILE_4, false, false);
								break;
							case 5: 
								tileGrid[y][x] = new NumberedTile(MinesweeperView.TILE_5, false, false);
								break;
							case 6: 
								tileGrid[y][x] = new NumberedTile(MinesweeperView.TILE_6, false, false);
								break;
							case 7: 
								tileGrid[y][x] = new NumberedTile(MinesweeperView.TILE_7, false, false);
								break;
							case 8:
								tileGrid[y][x] = new NumberedTile(MinesweeperView.TILE_8, false, false);
								break;
						}
					}
				}
			}
		} 
		
		
	// Get the mines around a case (x,y)
		private int getMinesNear(int x, int y){
			int counterMines = 0;
			int xMin = x-1;
			int xMax = x+1;
			int yMin = y-1;
			int yMax = y+1;
			if(x==0) xMin=0;
			if(y==0) yMin=0;
			if(x==(width-1)) xMax = x;
			if(y==(height-1)) yMax = y;
			
			for (int i = xMin; i <= xMax; i++){
                for (int j = yMin; j <= yMax; j++){
                    if (tileGrid[j][i] instanceof MineTile){
                        counterMines++;
                    }
                }
            }
			return counterMines;		
		}
	
		
	// Convert grid from type Tile to type integer
		public int[][] convertGrid(){
			printedGrid = new int[height][width];
			for(int y = 0; y < height; y++){
				for(int x = 0; x < width; x++){
					printedGrid[y][x] = tileGrid[y][x].updateValue();
				}
			}
			return printedGrid;
		}
		
		
	// Get tileGrid
		public Tile[][] getTileGrid(){
			return tileGrid;
		}
		
	// Get the amount of mines
		public int getNbreMines(){
			return nbreMines;
		}
		
	//Get level
		public int getLevel(){
			return level;
		}
		
	//Get width
		public int getWidth(){
			return width;
		}
		
	// Get height
		public int getHeight(){
			return height;
		}
	
}