package lazorz;

import game.TowerDefense;

public abstract class Lazor extends TowerDefense
{
	public double xPos, yPos, siz, speed;
	public int[][] theMap;
	
	public Lazor( double x, double y, int[][] m ) 
	{ 
		xPos = x; 
		yPos = y; 
		theMap = m;
	}
	
	public abstract void manage();

	
	public abstract void move( int[][] _map );
	
	public abstract void display();

}
