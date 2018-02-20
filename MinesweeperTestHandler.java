package be.ac.ulg.montefiore.oop;
import be.ac.ulg.montefiore.oop.graphics.*;


public class MinesweeperTestHandler implements MinesweeperBonusEventsHandler{
	
	private MinesweeperSwingView view;
	private Grid grid;
	private Tile[][] tileGrid;
	private int nbreFlags = 0;
	private int gameLevel;
	
	
	// Constructor
	public MinesweeperTestHandler(){}
	
	
	// Set the grids
	public void setGrids(Grid grid, Tile[][] tileGrid){
		this.grid = grid;
		this.tileGrid = tileGrid;
	}
	
	// Get the grid
	public Grid getGrid(){
		return grid;
	}
	
	
	// My name
	public String getStudentName() {
		return ("Antoine Louis");
	}
	
	// Set view
	public void setView(MinesweeperSwingView view){
		this.view = view;
	}
	
	
	// Left click
	public void leftClickTile(final int x, final int y){
		
		if(tileGrid[y][x].isRevealed() == false && tileGrid[y][x].hasFlag() == false){
			
			tileGrid[y][x].leftClickAction(grid, tileGrid, x, y);
			
			if(tileGrid[y][x] instanceof MineTile)
			view.lose();
		}
		
		// If all tiles are revealed except mines, we win
		if(tileGrid[y][x].getRevealedTiles() == (grid.getHeight()*grid.getWidth())-grid.getNbreMines())
			view.win();
		
		try{
			view.updateGrid(grid.convertGrid());
		}catch(NullArrayException | BadHeightException | BadWidthException | BadTileConstantException e){
			System.err.println("Error while updating the grid after a left click.");
			e.printStackTrace();
		}
		view.refreshWindow();
		
	}

	
	// Right click
	public void rightClickTile(final int x, final int y){
		
		if(tileGrid[y][x].isRevealed() == false){
		
			if((tileGrid[y][x].hasFlag()) == false && nbreFlags < grid.getNbreMines()){
				tileGrid[y][x].putFlag();
				++nbreFlags;
			}
			else if((tileGrid[y][x].hasFlag()) == true){
				tileGrid[y][x].removeFlag();
				--nbreFlags;
			}	
		}
			
		try{
			view.updateGrid(grid.convertGrid());
		}catch(NullArrayException | BadHeightException | BadWidthException | BadTileConstantException e){
			System.err.println("Error while updating the grid after a right click.");
			e.printStackTrace();
		}
		view.updateFlagsNumber(nbreFlags);
		view.refreshWindow();
		
	}

	
	// Build a random grid
	private Grid buildRandomGrid(String level){
		
		Random random = new Random(level);
		
		Grid randomGrid = new Grid (random.getLevel(),random.getHeight(), random.getWidth(), random.getNbreMines());
		
		randomGrid.setMinesTiles(random.makeRandomNumbers());
		
		randomGrid.setNumberedTiles();
		
		return randomGrid;
	}
	
	// Build a loaded grid
	public Grid buildLoadedGrid(String fileName) throws FileErrorException{
		
		LoadFile file = new LoadFile(fileName);
		
		Grid loadedGrid = new Grid(file.getLevel(), file.getHeight(), file.getWidth(), file.getNbreMines());
		
		loadedGrid.setMinesTiles(file.getMinesCoordinates());
		
		loadedGrid.setNumberedTiles();
		
		file.close();
		
		return loadedGrid;
	}
	
	
	// First game
	public void firstGame(String args0, String args1) throws FileErrorException{
		
		if(args0.equals("load")){
			Grid loadedGrid = buildLoadedGrid(args1);
			setGrids(loadedGrid, loadedGrid.getTileGrid());
			setGameLevel(loadedGrid.getLevel());
		}
		else if(args0.equals("random")){
			Grid randomGrid = buildRandomGrid(args1);
			setGrids(randomGrid, randomGrid.getTileGrid());	
			setGameLevel(randomGrid.getLevel());
		}
	}
	
	
	// New game
	public void newGame(){
		
		Grid newGrid = buildRandomGrid(convertLevel(gameLevel));
		initializeNbreFlags();
		Tile[][] newTileGrid = newGrid.getTileGrid();
		newTileGrid[0][0].initializeRevealedTiles();
		setGrids(newGrid, newTileGrid);
		
		try{
			view.updateGrid(newGrid.convertGrid());
		}catch(NullArrayException | BadHeightException | BadWidthException | BadTileConstantException e){
			System.err.println("Error while updating the grid after a click on new game.");
			e.printStackTrace();
		}
		view.updateFlagsNumber(nbreFlags);
		view.refreshWindow();
		
	}
	
	
	// Set the game level
	private void setGameLevel(int level){
		this.gameLevel = level;
	}
	
	
	// Initialize number of flags
		private void initializeNbreFlags(){
			this.nbreFlags = 0;
		}
		
	// Convert the level from integer to string
		private String convertLevel(int level){
			String str="";
			switch(level){
			case 0:
				str = "easy";
				break;
			case 1:
				str = "medium";
				break;
			case 2:
				str = "hard";
				break;
			}
			return str;
		}
	
}