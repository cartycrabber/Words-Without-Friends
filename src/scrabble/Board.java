package scrabble;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class Board {
	
	private final int MIN_WORD_SIZE = 2;
	
	public enum WordDirection {
		DOWN,
		RIGHT
	}
	
	private Map<Character, Integer> letterPointVals = new HashMap<Character, Integer>();//Dictionary to look up the point values for each letter
	public Map<Character, Integer> letterLimits = new HashMap<Character, Integer>();//Dictionary to look up how many letters are left
	private int width;//width of the board in tiles
	private int height;//height of the board in tiles
	private Cell[][] tiles;//2d Array of tiles
	private ArrayList<String> validWords = new ArrayList<String>();
	
	public Board(int width, int height)
	{
		this.width = width;
		this.height = height;
		tiles = new Cell[width][height];
		for(int x = 0; x < this.width; x++) {
			for(int y = 0; y < this.height; y++) {
				tiles[x][y] = new Cell(1, x, y, false);
			}
		}
		
		//Write how many of each letter there are
		letterLimits.put('a', 9);
		letterLimits.put('b', 2);
		letterLimits.put('c', 2);
		letterLimits.put('d', 4);
		letterLimits.put('e', 12);
		letterLimits.put('f', 2);
		letterLimits.put('g', 3);
		letterLimits.put('h', 2);
		letterLimits.put('i', 9);
		letterLimits.put('j', 1);
		letterLimits.put('k', 1);
		letterLimits.put('l', 4);
		letterLimits.put('m', 2);
		letterLimits.put('n', 6);
		letterLimits.put('o', 8);
		letterLimits.put('p', 2);
		letterLimits.put('q', 1);
		letterLimits.put('r', 6);
		letterLimits.put('s', 4);
		letterLimits.put('t', 6);
		letterLimits.put('u', 4);
		letterLimits.put('v', 2);
		letterLimits.put('w', 2);
		letterLimits.put('x', 1);
		letterLimits.put('y', 2);
		letterLimits.put('z', 1);
		
		//Multipliers for different tiles
		tiles[0][0].setMultiplier(3);
		tiles[1][1].setMultiplier(2);
		tiles[2][2].setMultiplier(2);
		tiles[3][3].setMultiplier(2);
		tiles[4][4].setMultiplier(2);
		tiles[5][5].setMultiplier(3);
		tiles[6][6].setMultiplier(2);
		tiles[8][8].setMultiplier(2);
		tiles[9][9].setMultiplier(3);
		tiles[10][10].setMultiplier(2);
		tiles[11][11].setMultiplier(2);
		tiles[12][12].setMultiplier(2);
		tiles[13][13].setMultiplier(2);
		tiles[14][14].setMultiplier(3);
		
		tiles[14][0].setMultiplier(3);
		tiles[13][1].setMultiplier(2);
		tiles[12][2].setMultiplier(2);
		tiles[11][3].setMultiplier(2);
		tiles[10][4].setMultiplier(2);
		tiles[9][5].setMultiplier(3);
		tiles[8][6].setMultiplier(2);
		tiles[6][8].setMultiplier(2);
		tiles[5][9].setMultiplier(3);
		tiles[4][10].setMultiplier(2);
		tiles[3][11].setMultiplier(2);
		tiles[2][12].setMultiplier(2);
		tiles[1][13].setMultiplier(2);
		tiles[0][14].setMultiplier(3);
		
		tiles[0][3].setMultiplier(2);
		tiles[0][7].setMultiplier(3);
		tiles[1][5].setMultiplier(3);
		tiles[2][6].setMultiplier(2);
		tiles[3][7].setMultiplier(2);
		tiles[2][8].setMultiplier(2);
		tiles[1][9].setMultiplier(3);
		tiles[0][11].setMultiplier(2);
		
		tiles[3][0].setMultiplier(2);
		tiles[7][0].setMultiplier(3);
		tiles[11][0].setMultiplier(2);
		tiles[5][1].setMultiplier(3);
		tiles[6][2].setMultiplier(2);
		tiles[7][3].setMultiplier(2);
		tiles[8][2].setMultiplier(2);
		tiles[9][1].setMultiplier(3);
		
		tiles[14][3].setMultiplier(2);
		tiles[14][7].setMultiplier(3);
		tiles[14][11].setMultiplier(2);
		tiles[13][5].setMultiplier(3);
		tiles[12][6].setMultiplier(2);
		tiles[11][7].setMultiplier(2);
		tiles[12][8].setMultiplier(2);
		tiles[13][9].setMultiplier(3);
		
		tiles[3][14].setMultiplier(2);
		tiles[7][14].setMultiplier(3);
		tiles[11][14].setMultiplier(2);
		tiles[5][13].setMultiplier(3);
		tiles[6][12].setMultiplier(2);
		tiles[7][11].setMultiplier(2);
		tiles[8][12].setMultiplier(2);
		tiles[9][13].setMultiplier(3);
		
		//How many points each letter is worth
		letterPointVals.put('a', 1);
		letterPointVals.put('b', 5);
		letterPointVals.put('c', 4);
		letterPointVals.put('d', 3);
		letterPointVals.put('e', 1);
		letterPointVals.put('f', 5);
		letterPointVals.put('g', 4);
		letterPointVals.put('h', 4);
		letterPointVals.put('i', 1);
		letterPointVals.put('j', 9);
		letterPointVals.put('k', 6);
		letterPointVals.put('l', 2);
		letterPointVals.put('m', 4);
		letterPointVals.put('n', 2);
		letterPointVals.put('o', 1);
		letterPointVals.put('p', 4);
		letterPointVals.put('q', 10);
		letterPointVals.put('r', 2);
		letterPointVals.put('s', 1);
		letterPointVals.put('t', 1);
		letterPointVals.put('u', 2);
		letterPointVals.put('v', 5);
		letterPointVals.put('w', 5);
		letterPointVals.put('x', 8);
		letterPointVals.put('y', 5);
		letterPointVals.put('z', 10);
		
		//Read the dictionary and fill the validwords list from it
		Scanner s = null;
		try {
			s = new Scanner(new File("res/dictionaries/SCOWL-70.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (s.hasNext()){
			validWords.add(s.next().toLowerCase());
		}
		s.close();
		
	}
	
	//Place word starting at x,y going in direction dir. coordinates start from top left
	public boolean placeWord(String word, int x, int y, WordDirection dir)//Returns true if valid move, false if not
	{
		word = word.toLowerCase();
		switch(dir) {
		case RIGHT:
			if(((x + word.length() - 1) < width) && (y < height)) {
				if(isWord(word)) {
					for(int z = 0; z < word.length(); z++) {
						tiles[x + z][y].setLetter(word.substring(z, z + 1).charAt(0));
					}
					return true;
				}
				else {
					System.out.println("Invalid word selection: " + word +  " is not an English word");
					return false;
				}
			}
			else {
				System.out.println("Invalid word placement: Entire word must be on board");
				return false;
			}
		case DOWN:
			if((x < width) && ((y + word.length() - 1) < height)) {
				if(isWord(word)) {
					for(int z = 0; z < word.length(); z++) {
						tiles[x][y + z].setLetter(word.substring(z, z + 1).charAt(0));
					}
					return true;
				}
				else {
					System.out.println("Invalid word selection: " + word +  " is not an English word");
					return false;
				}
			}
			else {
				System.out.println("Invalid word placement: Entire word must be on board");
				return false;
			}
		}
		return false;
	}
	
	//Place a single letter at x,y. coordinates start from top left
	public boolean placeLetter(int x, int y, char letter, boolean window)//Place a single letter on the board, true if window cords, false if board cords
	{
		if(!window) {
			if(tiles[x][y].getLetter() == '\0') {
				tiles[x][y].setLetter(letter);
				tiles[x][y].setTemporary(true);
				return true;
			}
			else {
				return false;
			}
		}
		else {
			if(tiles[(int)(x / Cell.CELL_SIZE)][(int)(y / Cell.CELL_SIZE)].getLetter() == '\0') {
				tiles[(int)(x / Cell.CELL_SIZE)][(int)(y / Cell.CELL_SIZE)].setLetter(letter);
				tiles[(int)(x / Cell.CELL_SIZE)][(int)(y / Cell.CELL_SIZE)].setTemporary(true);
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	//Remove the letter at x,y. Only works if the tile has temporary set to true
	public void removeLetter(int x, int y, boolean window)//Letter must be temporary
	{
		if(!window) {
			tiles[x][y].setLetter('\0');
		}
		else {
			tiles[(int)(x / Cell.CELL_SIZE)][(int)(y / Cell.CELL_SIZE)].setLetter('\0');
		}
	}
	
	//Remove all tiles with temporary set to true
	public void removeAllTempLetters()
	{
		for(Cell[] a : tiles) {
			for(Cell b : a) {
				if(b.isTemporary()) {
					b.setLetter('\0');
					b.setTemporary(false);
				}
			}
		}
	}
	
	//Check if all placed but not submitted tiles for a word, and if so set temporary to false
	public int submitWord()//Returns how many points the word is worth, or -1 if its not a valid submission
	{
		List<Cell> placed = new ArrayList<Cell>();
		for(Cell[] a : tiles) {
			for(Cell b : a) {
				if(b.isTemporary()) {
					placed.add(b);
				}
			}
		}
		
		if(placed.size() > 0) {
			boolean sameX = true;
			boolean sameY = true;
			for(Cell c : placed) {
				if(c.getBoardX() != placed.get(0).getBoardX()) {
					System.out.println("Not same X: " + c.getBoardX() + " and " + placed.get(0).getBoardX());
					sameX = false;
				}
				if(c.getBoardY() != placed.get(0).getBoardY()) {
					System.out.println("Not same Y" + c.getBoardY() + " and " + placed.get(0).getBoardY());
					sameY = false;
				}
			}
			if(sameX) {
				boolean before;
				int loop = 0;
				do {
					if(((placed.get(0).getBoardY() - (1 + loop)) >= 0) && (tiles[placed.get(0).getBoardX()][placed.get(0).getBoardY() - (1 + loop)].getLetter() != '\0')) {
						placed.add(0, tiles[placed.get(0).getBoardX()][placed.get(0).getBoardY() - (1 + loop)]);
						loop++;
						before = true;
					}
					else {
						before = false;
					}
				} while(before);
				
				loop = 0;
				do {
					if(((placed.get(placed.size() - 1).getBoardY() + (1 + loop)) >= 0) && (tiles[placed.get(placed.size() - 1).getBoardX()][placed.get(placed.size() - 1).getBoardY() + (1 + loop)].getLetter() != '\0')) {
						placed.add(placed.size(), tiles[placed.get(placed.size() - 1).getBoardX()][placed.get(placed.size() - 1).getBoardY() + (1 + loop)]);
						loop++;
						before = true;
					}
					else {
						before = false;
					}
				} while (before);
				boolean nextTo = true;
				for(int x = 0; x < placed.size() - 1; x ++) {
					if(placed.get(x).getBoardY() != (placed.get(x + 1).getBoardY() - 1)) {
						if(tiles[placed.get(x).getBoardX()][placed.get(x).getBoardY() + 1].getLetter() == '\0') {
							nextTo = false;
						}
						else {
							placed.add(x + 1, tiles[placed.get(x).getBoardX()][placed.get(x).getBoardY() + 1]);
						}
					}
				}
				if(nextTo) {
					String word = "";
					for(Cell c : placed) {
						word += String.valueOf(c.getLetter());
					}
					System.out.println(word);
					if(isWord(word)) {
						int score = 0;
						for(Cell c : placed) {
							score += (letterPointVals.get(c.getLetter()) * c.getMultiplier());
							c.setTemporary(false);
						}
						return score;
					}
					else {
						System.out.println("Submitted letters are not a word");
						return -1;
					}
				}
			}
			else if(sameY) {
				boolean before;
				int loop = 0;
				do {
					if(((placed.get(0).getBoardX() - (1 + loop)) >= 0) && (tiles[placed.get(0).getBoardX() - (1 + loop)][placed.get(0).getBoardY()].getLetter() != '\0')) {
						placed.add(0, tiles[placed.get(0).getBoardX() - (1 + loop)][placed.get(0).getBoardY()]);
						before = true;
						System.out.println("adding beginning letter");
						loop++;
					}
					else {
						before = false;
					}
				} while(before);
				loop = 0;
				do {
					if(((placed.get(placed.size() - 1).getBoardX() + (1 + loop)) >= 0) && (tiles[placed.get(placed.size() - 1).getBoardX() + (1 + loop)][placed.get(placed.size() - 1).getBoardY()].getLetter() != '\0')) {
						placed.add(placed.size(), tiles[placed.get(placed.size() - 1).getBoardX() + (1 + loop)][placed.get(placed.size() - 1).getBoardY()]);
						before = true;
						System.out.println("adding end letter" + loop);
						for(Cell c : placed) {
							System.out.println(c.getBoardX());
						}
						loop++;
					}
					else {
						before = false;
					}
				} while(before);
				boolean nextTo = true;
				for(int x = 0; x < placed.size() - 1; x ++) {
					if(placed.get(x).getBoardX() != (placed.get(x + 1).getBoardX() - 1)) {
						if(tiles[placed.get(x).getBoardX() + 1][placed.get(x).getBoardY()].getLetter() == '\0') {
							nextTo = false;
						}
						else {
							placed.add(x + 1, tiles[placed.get(x).getBoardX() + 1][placed.get(x).getBoardY()]);
						}
					}
				}
				if(nextTo) {
					String word = "";
					for(Cell c : placed) {
						word += String.valueOf(c.getLetter());
					}
					System.out.println(word);
					if(isWord(word)) {
						int score = 0;
						for(Cell c : placed) {
							score += (letterPointVals.get(c.getLetter()) * c.getMultiplier());
							c.setTemporary(false);
						}
						return score;
					}
					else {
						System.out.println("Submitted letters are not a word");
						return -1;
					}
				}
			}
		}
		System.out.println("Submitted letters are not linear");
		return -1;
	}
	
	//Gets the total score of the board
	public int getScore()
	{
		int score = 0;
		for(int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles[0].length; y++) {
				if(tiles[x][y].getLetter() != '\0') {
					score += (letterPointVals.get(tiles[x][y].getLetter())) * tiles[x][y].getMultiplier();
				}
			}
		}
		return score;
	}
	
	//Checks if a word is in the dictionary
	public boolean isWord(String word)
	{
		if(word.length() < MIN_WORD_SIZE) {
			return false;
		}
		else {
			return validWords.contains(word);
		}
	}
	
	//Renders the tiles on the board. Temporary tiles are rendered with a red shading
	public void render(GameContainer arg0, Graphics arg1)
	{
		for(int x = 0; x < tiles.length; x++) {
			for(int y = 0; y < tiles[0].length; y++) {
				if(tiles[x][y].getLetter() != '\0') {
					if(tiles[x][y].isTemporary())
					{
						Scrabble.getImage(String.valueOf(tiles[x][y].getLetter())).draw(tiles[x][y].getWindowX(), tiles[x][y].getWindowY(), 40, 40, Color.transparent.red);
					}
					else {
						Scrabble.getImage(String.valueOf(tiles[x][y].getLetter())).draw(tiles[x][y].getWindowX(), tiles[x][y].getWindowY(), 40, 40);
					}
				}
			}
		}
	}
}
