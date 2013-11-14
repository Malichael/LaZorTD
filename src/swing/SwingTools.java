package swing;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

import javax.swing.*;

public abstract class SwingTools extends JFrame
{
	
	
	
	//	PANEL AND PANE	//
	public JPanel panel;
	public static Graphics pane;
	//	END PANEL AND PANE	//
	
	
	
	//	VARIABLES	//
	public static int       ellipseMode = 1;
	public static Color     fillColor = new Color( 255, 255, 255 );
	public static boolean   filling = true;
	public static boolean   hasKeyListener;
	public static int       height;
	public static boolean   keyPressed;
	public static boolean   keyReleased;
	public static String[]  keys;
	public static boolean[] keysPressed;
	public static boolean[] keysReleased;
	public static boolean   mousePressed;
	public static boolean   mouseReleased;
	public static double    mouseX;
	public static double    mouseY;
	public static int       rectMode = 0;
	public static int       polyMode = 0;
	public static Color     strokeColor = new Color( 0, 0, 0 );
	public static double    strokeWeight = 1;
	public static boolean   stroking = true;
	public static int       width;
	//	END VARIABLES	//
	
	
	
	//	START	//
	public void start()
	{
		width = 500;
		height = 500;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Timer t = new Timer( 30,
				  new ActionListener(){ public void actionPerformed(ActionEvent e){drawPanel();} } );
		t.start();
	}
	public void start( int sp )
	{
		width = 500;
		height = 500;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Timer t = new Timer( sp,
				  new ActionListener(){ public void actionPerformed(ActionEvent e){drawPanel();} } );
		t.start();
	}
	public void start( int x, int y )
	{
		width = x;
		height = y;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Timer t = new Timer( 30,
				  new ActionListener(){ public void actionPerformed(ActionEvent e){drawPanel();} } );
		t.start();
	}
	public void start( int x, int y, int sp )
	{
		width = x;
		height = y;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Timer t = new Timer( sp,
				  new ActionListener(){ public void actionPerformed(ActionEvent e){drawPanel();} } );
		t.start();
	}
	//	END START	//
	
	
	
	//	DRAW PANEL	//
	private void drawPanel()
	{
		panel = new JPanel() 
        { 
            public void paintComponent(Graphics screen) 
            {
            	pane = screen;
            	updateMouse();
            	draw();
            } 
        };
        panel.setPreferredSize(new Dimension(width, height));
        getContentPane().add(panel);
        pack(); 
        setVisible(true);
	}
	//	END DRAW PANEL	//
	
	
	
	//	UPDATE MOUSE	//
	private void updateMouse()
    {
    	mouseX = MouseInfo.getPointerInfo().getLocation().x - panel.getLocationOnScreen().x;
    	mouseY = MouseInfo.getPointerInfo().getLocation().y - panel.getLocationOnScreen().y;
    	
    	panel.addMouseListener(new MouseListener()
    	{
    	    public void mouseClicked(MouseEvent e){}
			public void mouseEntered(MouseEvent e){}
			public void mouseExited(MouseEvent e){}
			public void mousePressed(MouseEvent e)
			{
				mousePressed = true;
			}
			public void mouseReleased(MouseEvent e)
			{
				mousePressed = false;
			}
    	});
    	panel.addMouseMotionListener(new MouseMotionListener()
    	{
			public void mouseDragged(MouseEvent e){}
			public void mouseMoved(MouseEvent e){}
    	});

    }
	//	END UPDATE MOUSE	//
	
	
	
	//	GET KEY PRESSED	//
	private void getKeyPressed( String[] theKeys )
    {
    	InputMap im = panel.getInputMap( JPanel.WHEN_IN_FOCUSED_WINDOW );
        ActionMap am = panel.getActionMap();
        for ( int i = 0; i < theKeys.length; i++ )
        {
        	im.put( KeyStroke.getKeyStroke( "" + theKeys[i] ), "keyPressed-" + theKeys[i] );
        	im.put( KeyStroke.getKeyStroke( "released " + theKeys[i] ), "keyReleased-" + theKeys[i] );
        	am.put( "keyPressed-" + theKeys[i], new keyAction( i, true ) );
        	am.put( "keyReleased-" + theKeys[i], new keyAction( i, false ) );
        }
    }
    
    private void updateKeysReleased()
    {
    	for ( int i = 0; i < keysReleased.length; i++ )
		{
    		if ( keysReleased[i] )	keysReleased[i] = false;
		}
    }
    
    private class keyAction extends AbstractAction
    {
    	boolean setTo;
    	int setChar;
    	
    	public keyAction( int whichChar, boolean set )
    	{
    		setChar = whichChar;
    		setTo = set;
    	}

		public void actionPerformed( ActionEvent e )
		{
			keysPressed[setChar] = setTo;
			if ( !setTo )	 keysReleased[setChar] = true;
		}
    }
    //	END GET KEY PRESSED	//
	
	
	//	DRAW AND SETUP	//
	public abstract void draw();
	public abstract void setup();
	//	END DRAW AND SETUP	//
	
	
	
	
	
	
	//	----------	FUNCTIONS	----------	//
	
	
	
	
	
	
	//	ADD KEY LISTENER	//
	public static void addKeyListener( char... addedChars )
    {
    	hasKeyListener = true;
    	keys = new String[addedChars.length];
    	for ( int i = 0; i < keys.length; i++ )
    	{
    		keys[i] = ""+addedChars[i];
    	}
    	keysPressed = new boolean[keys.length];
    	keysReleased = new boolean[keys.length];
    }
    public static void addKeyListener( String... addedStrings )
    {
    	hasKeyListener = true;
    	keys = new String[addedStrings.length];
    	for ( int i = 0; i < keys.length; i++ )
    	{
    		keys[i] = addedStrings[i];
    	}
    	keysPressed = new boolean[keys.length];
    	keysReleased = new boolean[keys.length];
    }
	//	END ADD KEY LISTENER	//
	
	
	
	//	BACKGROUND	//
	public static void background( double g )
    {
    	Color origCol = pane.getColor();
    	pane.setColor( new Color( (int) g, (int) g, (int) g ) );
    	pane.fillRect( 0, 0, width, height );
    	pane.setColor( origCol );
    }
	public static void background( double g, double a )
    {
    	Color origCol = pane.getColor();
    	pane.setColor( new Color( (int) g, (int) g, (int) g, (int) a ) );
    	pane.fillRect( 0, 0, width, height );
    	pane.setColor( origCol );
    }
	public static void background( double r, double g, double b )
    {
    	Color origCol = pane.getColor();
    	pane.setColor( new Color( (int) r, (int) g, (int) b ) );
    	pane.fillRect( 0, 0, width, height );
    	pane.setColor( origCol );
    }
	public static void background( double r, double g, double b, double a )
    {
    	Color origCol = pane.getColor();
    	pane.setColor( new Color( (int) r, (int) g, (int) b, (int) a ) );
    	pane.fillRect( 0, 0, width, height );
    	pane.setColor( origCol );
    }
	//	END BACKGROUND	//
	
	
	
	//	CIRCLE	//
	public static void circle( double x, double y, double r )
	{
		if ( filling )
		{
			pane.setColor( fillColor );
			if ( ellipseMode == 0 )
				pane.fillOval( (int) x, (int) y, (int) r, (int) r );
			else
				pane.fillOval( (int) ( x - r / 2 ), (int) ( y - r / 2 ), (int) r, (int) r );
		}
		if ( stroking )
		{
			Graphics2D g2d = (Graphics2D) pane;
			g2d.setStroke( new BasicStroke( (float) strokeWeight ) );
			pane.setColor( strokeColor );
			if ( ellipseMode == 0 )
				pane.drawOval( (int) x, (int) y, (int) r, (int) r );
			else
				pane.drawOval( (int) ( x - r / 2 ), (int) ( y - r / 2 ), (int) r, (int) r );
		}
	}
	//	END CIRCLE	//
	
	
	
	//	ELLIPSE	//
	public static void ellipse( double x, double y, double r )
	{
		if ( filling )
		{
			pane.setColor( fillColor );
			if ( ellipseMode == 0 )
				pane.fillOval( (int) x, (int) y, (int) r, (int) r );
			else
				pane.fillOval( (int) ( x - r / 2 ), (int) ( y - r / 2 ), (int) r, (int) r );
		}
		if ( stroking )
		{
			Graphics2D g2d = (Graphics2D) pane;
			g2d.setStroke( new BasicStroke( (float) strokeWeight ) );
			pane.setColor( strokeColor );
			if ( ellipseMode == 0 )
				pane.drawOval( (int) x, (int) y, (int) r, (int) r );
			else
				pane.drawOval( (int) ( x - r / 2 ), (int) ( y - r / 2 ), (int) r, (int) r );
		}
	}
	public static void ellipse( double x, double y, double w, double h )
	{
		if ( filling )
		{
			pane.setColor( fillColor );
			if ( ellipseMode == 0 )
				pane.fillOval( (int) x, (int) y, (int) w, (int) h );
			else if ( ellipseMode == 1 )
				pane.fillOval( (int) ( x - w / 2 ), (int) ( y - h / 2 ), (int) w, (int) h );
		}
		if ( stroking )
		{
			Graphics2D g2d = (Graphics2D) pane;
			g2d.setStroke( new BasicStroke( (float) strokeWeight ) );
			pane.setColor( strokeColor );
			if ( ellipseMode == 0 )
				pane.drawOval( (int) x, (int) y, (int) w, (int) h );
			else if ( ellipseMode == 1 )
				pane.drawOval( (int) ( x - w / 2 ), (int) ( y - h / 2 ), (int) w, (int) h );	
		}
	}
	//	END ELLIPSE	//
	
	
	
	//	ELLIPSE MODE	//
	public static void ellipseMode( int m )
	{
		if ( m == 0 )
			ellipseMode = 0;
		else if ( m == 1 )
			ellipseMode = 1;
	}
	public static void ellipseMode( String m )
	{
		if ( m.equals("CORNER") || m.equals("corner") )
			ellipseMode = 0;
		else if ( m.equals("CENTER") || m.equals("center") )
			ellipseMode = 1;
	}
	//	END ELLIPSE MODE	//
	
	
	
	//	FILL	//
	public static void fill( double g )
	{
		filling = true;
		fillColor = new Color( (int) g, (int) g, (int) g );
	}
	public static void fill( double g, double a )
	{
		filling = true;
		fillColor = new Color( (int) g, (int) g, (int) g, (int) a );
	}
	public static void fill( double r, double g, double b )
	{
		filling = true;
		fillColor = new Color( (int) r, (int) g, (int) b );
	}
	public static void fill( double r, double g, double b, double a )
	{
		filling = true;
		fillColor = new Color( (int) r, (int) g, (int) b, (int) a );
	}
	//	END FILL	//
	
	
	
	//	LINE	//
	public static void line( double x1, double y1, double x2, double y2 )
	{
		if ( stroking )
		{
			Graphics2D g2d = (Graphics2D) pane;
			g2d.setStroke( new BasicStroke( (float) strokeWeight ) );
			pane.setColor( strokeColor );
			pane.drawLine( (int) x1, (int) y1, (int) x2, (int) y2 );
		}
	}	
	//	END	LINE	//
	
	
	
	//	RECT	//
	public static void rect( double x, double y, double w, double h )
	{
		if ( filling )
		{
			pane.setColor( fillColor );
			if ( rectMode == 0 )
				pane.fillRect( (int) x, (int) y, (int) w, (int) h );
			else if ( rectMode == 1 )
				pane.fillRect( (int) ( x - w / 2 ), (int) ( y - h / 2 ), (int) w, (int) h );
		}
		if ( stroking )
		{
			Graphics2D g2d = (Graphics2D) pane;
			g2d.setStroke( new BasicStroke( (float) strokeWeight ) );
			pane.setColor( strokeColor );
			if ( rectMode == 0 )
				pane.drawRect( (int) x, (int) y, (int) w, (int) h );
			else if ( rectMode == 1 )
				pane.drawRect( (int) ( x - w / 2 ), (int) ( y - h / 2 ), (int) w, (int) h );	
		}
	}
	//	END RECT	//
	
	
	
	//	RECT MODE	//
	public static void rectMode( int m )
	{
		if ( m == 0 )
			rectMode = 0;
		else if ( m == 1 )
			rectMode = 1;
	}
	public static void rectMode( String m )
	{
		if ( m.equals("CORNER") || m.equals("corner") )
			rectMode = 0;
		else if ( m.equals("CENTER") || m.equals("center") )
			rectMode = 1;
	}
	//	END RECT MODE	//
	
	
	// POLY //
	public static void poly( int[] x, int[] y, int s)
	{
		if ( filling )
		{
			pane.setColor( fillColor );
			if ( polyMode == 0 )
				pane.fillPolygon( x, y, s );
			/* else if ( polyMode == 1 )
				pane.fillPolygon( (int) ( x - w / 2 ), (int) ( y - h / 2 ), (int) w, (int) h );
				*/
		}
		if ( stroking )
		{
			Graphics2D g2d = (Graphics2D) pane;
			g2d.setStroke( new BasicStroke( (float) strokeWeight ) );
			pane.setColor( strokeColor );
			if ( polyMode == 0 )
				pane.drawPolygon( (int[]) x, (int[]) y, (int) s );
			/*
				else if ( polyMode == 1 )
				pane.drawPolygon( (int) ( x - w / 2 ), (int) ( y - h / 2 ), (int) w, (int) h );	
				*/
		}
	}
	// END POLY //
	
	
//	POLY MODE	//
	public static void polyMode( int m )
	{
		if ( m == 0 )
			polyMode = 0;
		else if ( m == 1 )
			polyMode = 1;
	}
	public static void polyMode( String m )
	{
		if ( m.equals("CORNER") || m.equals("corner") )
			polyMode = 0;
		else if ( m.equals("CENTER") || m.equals("center") )
			polyMode = 1;
	}
	//	END POLY MODE	//
	
	//	SMOOTH	//
	public static void smooth()
    {
    	Graphics2D p2 = ( Graphics2D ) pane;
    	p2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
    }
	//	END SMOOTH	//
	
	
	
	//	STROKE	//
	public static void stroke( double g )
	{
		stroking = true;
		strokeColor = new Color( (int) g, (int) g, (int) g );
	}
	public static void stroke( double g, double a )
	{
		stroking = true;
		strokeColor = new Color( (int) g, (int) g, (int) g, (int) a );
	}
	public static void stroke( double r, double g, double b )
	{
		stroking = true;
		strokeColor = new Color( (int) r, (int) g, (int) b );
	}
	public static void stroke( double r, double g, double b, double a )
	{
		stroking = true;
		strokeColor = new Color( (int) r, (int) g, (int) b, (int) a );
	}
	//	END STROKE	//
	
	
	
	//	STROKE WEIGHT	//
	public static void strokeWeight( double w )
	{
		strokeWeight = w;
	}
	//	END STROKE WEIGHT	//
	
	
	
	//	TEXT	//
	public static void text( String text, double x, double y, double size )
    {
    	pane.setColor( fillColor );
		pane.setFont( new Font( "Arial", Font.BOLD, (int) size ) );
		pane.drawString( text, (int) x, (int) y );
    }
	//	END TEXT	//
	
	
	
}
