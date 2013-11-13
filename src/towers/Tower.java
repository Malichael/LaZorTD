package towers;

import game.TowerDefense;

public abstract class Tower extends TowerDefense
{
	
	public int xPos, yPos;
	public double fireSpeed, angle, range;
	public int towID;
	
	public abstract void display();
	
}
