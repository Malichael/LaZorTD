package lazorz;

public class LazorOne extends Lazor
{
	
		public LazorOne(double x, double y, int[][] m) 
		{
		
			super(x, y, m);
		
		}

		public void display() {
			
		fill( 0, 63, 100 );
		circle( xPos, yPos, siz );	

	}

		public void move(int[][] _map) {
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

		public void manage() {
			
			move( theMap );
			
			
		}

}