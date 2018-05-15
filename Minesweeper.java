package be.ac.ulg.montefiore.oop;
import be.ac.ulg.montefiore.oop.graphics.*;

public class Minesweeper {
	public static void main(String[] args) throws FileErrorException{
		
		MinesweeperTestHandler handler;
		Grid grid;
		MinesweeperSwingView msv;
		
		if(args.length != 2){
			System.err.println("Error : enter 2 arguments.");
			System.exit(1);
		}
			
		if(!(args[0].equals("load")) && !(args[0].equals("random"))){
			System.err.println("Error : first argument is invalid, choose either load or random.");
			System.exit(1);
		}
		
		
		if(args[0].equals("random") && !(args[1].equals("easy")) && !(args[1].equals("medium")) && !(args[1].equals("hard"))){
			System.err.println("Error : the second argument must be a valid level.");
			System.exit(1);
		}
		
		else{
		 
			try {
				
				handler = new MinesweeperTestHandler();
				
				handler.firstGame(args[0], args[1]);
				
				grid = handler.getGrid();
				
				msv = new MinesweeperSwingView(grid.getLevel(), handler);
				
				handler.setView(msv);
				
				msv.updateGrid(grid.convertGrid());
				
				msv.refreshWindow();
		
				} catch (BadDifficultyException | NullHandlerException e) {
					System.err.println("Error while building the grid in the main fonction."); 
					e.printStackTrace();
					
				} catch(NullArrayException | BadHeightException | BadWidthException | BadTileConstantException e){
					System.err.println("Error while updating the grid in the main fonction.");
					e.printStackTrace();
				}
		}
		
	}
}