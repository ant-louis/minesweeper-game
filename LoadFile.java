package be.ac.ulg.montefiore.oop;
import be.ac.ulg.montefiore.oop.graphics.*;
import java.io.*;

public class LoadFile {
	
	private FileInputStream fis = null;
	private InputStreamReader isr = null;
	private BufferedReader br = null;
	private String fileLevel;
	private int level;
	private int nbreMines;
	private int height;
	private int width;
	private int [][] tabMines;
	
	public LoadFile(String fileName) throws FileErrorException{
		
		// Open the file
		try{
			fis = new FileInputStream(fileName);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
		}catch(IOException e){
			this.close();
			System.err.println("Error while opening the file.");
			e.printStackTrace();
		}
		
		// Get the level from the file
		try{
			fileLevel = br.readLine();
		}catch(IOException e){
			this.close();
			System.err.println("Error while reading the first line of the file.");
			e.printStackTrace();
		}
		
		
		// Set the variables corresponding to the level
		switch(fileLevel){
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
			default:
				throw new FileErrorException("The level of the game in the file is incorrect.");
		}
	}

	// Get mines coordinates
		public int[][] getMinesCoordinates () throws FileErrorException{
			tabMines = new int [nbreMines][2];
			int i = 0;
			String currentLine;
			String str[];
			
			try{
				while((currentLine = br.readLine()) != null){
					str = currentLine.split(",");
					
					if(str.length != 2)
						throw new FileErrorException("Error: each mine must have 2 coordinates.");
					
					tabMines[i][0] = Integer.parseInt(str[0]);
					tabMines[i][1] = Integer.parseInt(str[1]);
					
					if(tabMines[i][0] < 0 || tabMines[i][0] >= width)
						throw new FileErrorException("The coordinate x must fit the grid.");
					
					if(tabMines[i][1] < 0 || tabMines[i][1] >= height)
						throw new FileErrorException("The coordinate y must fit the grid.");
					
					i++;
				}
				
			}catch(IOException e){
				this.close();
				System.err.println("Error while reading the lines of the file.");
				e.printStackTrace();
				tabMines = null;
			}
			return tabMines;
		}
		
		
	// Close the file
		public void close (){
			try{
				if(br != null) br.close();
				if(isr != null) isr.close();
				if(fis != null) fis.close();
			}catch(IOException e){
				System.err.println("Error while closing the file.");
					e.printStackTrace();
			}
			finally{
				br = null;
				isr = null;
				fis = null;
			}
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