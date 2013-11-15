package game;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import lazorz.*;
import swing.SwingTools;
import towers.*;

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
	ArrayList<Lazor> lazorz = new ArrayList<>();
	
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
	
	
	Lazor l;
	Tower t;
	
	public void displayBlobs()
	{
		
		int xStartBlock = getStart( map )[0];
		int yStartBlock = getStart( map )[1];
		if ( l == null ) 
			l = new LazorOne( xStartBlock * 40 + 20, yStartBlock * 40 + 20, map );
		l.manage();
		l.display();
		if ( t == null ) t = new TowerOne( 3, 3 );
		t.display();
		
	}
	
		
	static int[] toIntArray(double[] arr) {
		  if (arr == null) return null;
		  int n = arr.length;
		  int[] ret = new int[n];
		  for (int i = 0; i < n; i++) {
		    ret[i] = (int)arr[i];
		  }
		  return ret;
		}
	// POLY //
	public static void poly( double[] x, double[] y, double s)
	{
	
		int[] intx = toIntArray(x); //rewrite this
		int[] inty = toIntArray(y); //rewrite this
		if ( filling )
		{	
			pane.setColor( fillColor );
					pane.fillPolygon( intx, inty, (int)s );
		}
		if ( stroking )
		{
			Graphics2D g2d = (Graphics2D) pane;
			g2d.setStroke( new BasicStroke( (float) strokeWeight ) );
			pane.setColor( strokeColor );
					pane.drawPolygon( intx, inty, (int) s );
		}
	}
	// END POLY //
	
	//	HELLO WORLD //
	public static void main( String[] args )
	{
		new TowerDefense().start( 400, 400 );
	}

}
