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
		stroke(0);
		strokeWeight(3);
		fill(123, 72, 156);	
		poly(new double[]{ 0+xPos, 40+xPos, 20+xPos },
				new double[]{ 40+yPos, 40+yPos, 0+yPos }, 3 );
	}
	
	
	
}
