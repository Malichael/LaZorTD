package game;

import java.util.ArrayList;

import swing.SwingTools;

public class TowerDefense extends SwingTools
{

	public void setup(){}

	//	directions:
	//	1-up 2-down 3-left 4-right in last char of number
	//	types:
	//	0 - nothing
	//	2 - start
	//	3 - end
	int[][] map = new int[][]
			{
				{ 00, 22, 00, 00, 00, 00, 00, 00, 00, 00 },
				{ 00, 12, 00, 00, 00, 00, 00, 00, 00, 00 },
				{ 00, 14, 14, 14, 14, 14, 12, 00, 00, 00 },
				{ 00, 00, 00, 00, 00, 00, 14, 14, 12, 00 },
				{ 00, 00, 00, 00, 00, 00, 00, 00, 12, 00 },
				{ 00, 00, 00, 00, 00, 00, 12, 13, 13, 00 },
				{ 00, 00, 00, 12, 13, 13, 13, 00, 00, 00 },
				{ 00, 00, 00, 12, 00, 00, 00, 00, 00, 00 },
				{ 00, 00, 00, 14, 14, 12, 00, 00, 00, 00 },
				{ 00, 00, 00, 00, 00, 32, 00, 00, 00, 00 }
			};
	ArrayList<Blob> blobz = new ArrayList<>();
	
	public void draw()
	{
		
		smooth();
		displayMap( map );
		displayBlobs();
		
	}
	
	public void displayMap( int[][] _map )
	{
		
		background( 200 );
		stroking = false;
		for ( int j = 0; j < _map.length; j++ )
		{
			for ( int i = 0; i < _map[0].length; i++ )
			{
				switch( _map[j][i] / 10 )
				{
					case 0:
						break;
					case 1:
						fill( 100 );
						rect( i * 40, j * 40, 40, 40 );
						break;
					case 2: case 3:
						fill( 80, 127, 80 );
						rect( i * 40, j * 40, 40, 40 );
						break;
				}
			}
		}
		
	}
	
	public int[] getStart( int[][] _map )
	{
		int[] pos = new int[2];
		search :
		for ( int j = 0; j < _map.length; j++ )
		{
			for ( int i = 0; i < _map[0].length; i++ )
			{
				if( ( _map[j][i] + "" ).substring( 0, ( _map[j][i] + "" ).length() - 1 )
									   .equals( "2" ) )
				{
					pos[0] = i;
					pos[1] = j;
					break search;
				}
			}
		}
		return pos;
	}
	
	
	Blob b;
	
	public void displayBlobs()
	{
		
		int xStartBlock = getStart( map )[0];
		int yStartBlock = getStart( map )[1];
		if (b == null) b = new Blob( xStartBlock * 40 + 20, yStartBlock * 40 + 20, map );
		b.manage();
		
	}
	
	public class Blob
	{
		
		public double xPos, yPos, siz = 20, speed = 3;
		public int[][] theMap;
		
		public Blob( double x, double y, int[][] m ) { xPos = x; yPos = y; theMap = m; }
		
		public void manage()
		{
			
			move( theMap );
			display();
			
		}
		
		public void move( int[][] _map )
		{
			
			int j = (int) ( yPos / 40 );
			int i = (int) ( xPos / 40 );
			switch( Integer.parseInt( ( _map[j][i] + "" )
						   .charAt( ( _map[j][i] + "" ).length() - 1 ) + "" ) )
			{
				case 1:
					yPos -= speed;
					if ( xPos <= i * 40 + 19 )	xPos += speed;
					if ( xPos >= i * 40 + 31 )	xPos -= speed;
					break;
				case 2:
					yPos += speed;
					if ( xPos <= i * 40 + 19 )	xPos += speed;
					if ( xPos >= i * 40 + 31 )	xPos -= speed;
					break;
				case 3:
					xPos -= speed;
					if ( yPos <= j * 40 + 19 )	yPos += speed;
					if ( yPos >= j * 40 + 31 )	yPos -= speed;
					break;
				case 4:
					xPos += speed;
					if ( yPos <= j * 40 + 19 )	yPos += speed;
					if ( yPos >= j * 40 + 31 )	yPos -= speed;
					break;
					
			}
			
		}
		
		public void display()
		{
			
			fill( 0, 63, 100 );
			circle( xPos, yPos, siz );
			
		}
		
	}
	
	//	HELLO WORLD
	public static void main( String[] args )
	{
		new TowerDefense().start( 400, 400 );
	}

}
