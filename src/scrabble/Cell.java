package scrabble;

import org.newdawn.slick.Image;

public class Cell {
	
	private int multiplier;
	private char letter;
	private int boardX;
	private int boardY;
	private int windowX;
	private int windowY;
	
	private Image img;
	
	private final int cellSize = 40;
	
	public Cell(int multiplier, int boardX, int boardY)
	{
		this.multiplier = multiplier;
		this.boardX = boardX;
		this.boardY = boardY;
		this.windowX = boardX * cellSize;
		this.windowY = boardY * cellSize;
		this.letter = '\0';
	}
	public Cell(int multiplier, int boardX, int boardY, char letter)
	{
		this.multiplier = multiplier;
		this.boardX = boardX;
		this.boardY = boardY;
		this.letter = letter;
	}
	
	public void setLetter(char l)
	{
		letter = l;
	}
	public char getLetter()
	{
		return letter;
	}
	public int getMultiplier()
	{
		return multiplier;
	}
	public int getWindowX()
	{
		return windowX;
	}
	public int getWindowY()
	{
		return windowY;
	}
}