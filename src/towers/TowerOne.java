package towers;

public class TowerOne extends Tower
{

	public TowerOne( int x, int y )
	{
		xPos = x * 40;
		yPos = y * 40;
	}
	
	public void display()
	{
		pane.drawPolygon( new int[]{ 0+xPos, 40+xPos, 20+xPos },
						  new int[]{ 40+yPos, 40+yPos, 0+yPos }, 3 );
	}
	
	
	
}
