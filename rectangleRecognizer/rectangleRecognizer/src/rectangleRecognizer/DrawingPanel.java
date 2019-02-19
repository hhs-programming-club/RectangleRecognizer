package rectangleRecognizer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.*;

public class DrawingPanel extends JPanel {
	
	private BufferedImage currentImage;
	private Point[] polyPoints;
	private Polygon overlay;
	public boolean displayRectangle = true;
	private JLabel mousePositionLabel = new JLabel("");
	
	public DrawingPanel() {
		// TODO Auto-generated constructor stub
		this.setBackground(Color.BLACK);
		overlay = new Polygon();
		addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
//                background = getBackground();
//                setBackground(Color.RED);
            	System.out.print("new Point(" + e.getX() + ", " + e.getY() + "), ");
//                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
//                setBackground(background);
            }
            
           
        });
		
		addMouseMotionListener(new MouseAdapter(){
			
			 @Override
	            public void mouseMoved(MouseEvent e)
	            {
	              mouseX = e.getX();
	              mouseY = e.getY();
//	              mousePositionLabel.setText("(" + x + ", " + y + ")");
//	              mousePositionLabel.setBounds(x, y, 40, 20);
//	              System.out.println("Mouse moved");
	              repaint();
	            }
			
		});
	}
	
	private int mouseX = 0;
	private int mouseY = 0;

	public DrawingPanel(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public DrawingPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public DrawingPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}
	
	public void setPoints(Point[] points)
	{ 
		 polyPoints = points;
		 Color myColour = new Color(0, 0, 255, 40);
		 overlay.reset();
//		 polyPoints = new Point[4];
//		 polyPoints[1] = new Point(0, 0);
//		 polyPoints[2] = new Point(1070, 0);
//		 polyPoints[3] = new Point(1070, 720);
//		 polyPoints[0] = new Point(0, 720);
		 
		 
		 //Sorts points appropriately to be drawn
		 Arrays.sort(polyPoints);
		 Point temp = polyPoints[3];
		 polyPoints[3] = polyPoints[2];
		 polyPoints[2] = temp;
	
		 
		 for(int i=0; i<4; i++)
		 {
			 overlay.addPoint(polyPoints[i].x, polyPoints[i].y);
		 }
	}
	
	public void paintComponent(Graphics g) {
		
		 super.paintComponent(g);
		 
		 
		 
		 g.drawImage(currentImage, 0, 0, null);
//		 
		 Color myColour = new Color(255, 255, 0, 80);
//		 Polygon overlay = new Polygon();
//		 overlay.reset();
//		 polyPoints = new Point[4];
//		 polyPoints[1] = new Point(0, 0);
//		 polyPoints[2] = new Point(1070, 0);
//		 polyPoints[3] = new Point(1070, 720);
//		 polyPoints[0] = new Point(0, 720);
//		 
//		 
//		 //Sorts points appropriately to be drawn
//		 Arrays.sort(polyPoints);
//		 Point temp = polyPoints[3];
//		 polyPoints[3] = polyPoints[2];
//		 polyPoints[2] = temp;
//
//		 
//		 for(int i=0; i<4; i++)
//		 {
//			 overlay.addPoint(polyPoints[i].x, polyPoints[i].y);
//		 }
		 
//		 overlay.addPoint(40, 40);
//		 overlay.addPoint(180, 40);
//		 overlay.addPoint(140, 160);
//		 overlay.addPoint(40, 160);
		 g.setColor(Color.WHITE);
		g.drawString("(" + mouseX + ", " + mouseY + ")", mouseX, mouseY);
		 g.setColor(myColour);
//		 g.fillRect(40, 40, 200, 200);
		 if(displayRectangle)
		 {
		 g.fillPolygon(overlay);
		 }
		
		    }
	
	public void updateImage(BufferedImage img)
	{
		currentImage = img;
	}


}
