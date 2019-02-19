package rectangleRecognizer;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import rectangleRecognizer.Point;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window {
	
	
	private SolvedImageProcessor processor;
	private JFrame frame;
	private int currentImageIndex = 0;
	private DrawingPanel drawingPanel;
	private JLabel totalImagesLabel;
	private JLabel filenameLabel;
	private JLabel averageDistanceLabel;
	private Point[][] correctAnswers;
	private JButton calcPoints;
	
	public Window()
	{
//		System.out.println("constructed");
		//1. Create the frame.
		frame = new JFrame("Rectangle Recognizer");
		frame.getContentPane().setPreferredSize(new Dimension(1290, 730));
		//2. Optional: What happens when the frame closes?
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//3. Create components and put them in the frame.
		//...create emptyLabel...
//		frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
//		frame.setIconImage(new ImageIcon("http://devsniper.com/wp-content/uploads/customersdesktop/customersdesktop-logo.png").getImage());
		
		//4. Size the frame.
		frame.pack();
		frame.setResizable(false);
//		frame.setLocationRelativeTo(null);
		
		processor = new SolvedImageProcessor();
		
		
		correctAnswers = new Point[SolvedImageProcessor.numberImages][4];
		correctAnswers[0] = new Point[]{new Point(241, 388), new Point(388, 387), new Point(387, 533), new Point(241, 534)};
		correctAnswers[1] = new Point[] {new Point(333, 169), new Point(925, 168), new Point(923, 314), new Point(333, 315)};
		correctAnswers[2] = new Point[] {new Point(208, 85), new Point(371, 85), new Point(372, 546), new Point(209, 546)};
		correctAnswers[3] = new Point[] {new Point(208, 85), new Point(371, 85), new Point(372, 546), new Point(209, 546)};
		correctAnswers[4] = new Point[] {new Point(208, 85), new Point(371, 85), new Point(372, 546), new Point(209, 546)};
		correctAnswers[5] = new Point[] {new Point(208, 85), new Point(371, 85), new Point(372, 546), new Point(209, 546)};
		correctAnswers[6] = new Point[] {new Point(208, 85), new Point(371, 85), new Point(372, 546), new Point(209, 546)};
		correctAnswers[7] = new Point[] {new Point(208, 85), new Point(371, 85), new Point(372, 546), new Point(209, 546)};
		correctAnswers[8] = new Point[] {new Point(208, 85), new Point(371, 85), new Point(372, 546), new Point(209, 546)};
		correctAnswers[9] = new Point[] {new Point(35, 326), new Point(169, 163), new Point(624, 541), new Point(490, 702)};
		correctAnswers[10] = new Point[] {new Point(238, 421), new Point(428, 271), new Point(613, 429), new Point(413, 611)};
		correctAnswers[11] = new Point[] {new Point(157, 267), new Point(481, 348), new Point(487, 386), new Point(202, 515)};
		correctAnswers[12] = new Point[] {new Point(144, 445), new Point(165, 462), new Point(298, 358), new Point(242, 345)};
//		correctAnswers[0] = [new Point(241, 388), new Point(388, 387), new Point(387, 533), new Point(241, 534)];
		
		drawingPanel = new DrawingPanel();
		drawingPanel.updateImage(processor.getImage(currentImageIndex));
		drawingPanel.setPreferredSize(new Dimension(1080, 720));
		drawingPanel.setMinimumSize(new Dimension(1080, 720));
		
		frame.setLayout(new BorderLayout());
		
		frame.getContentPane().add(drawingPanel, BorderLayout.CENTER);
		
		
		
		totalImagesLabel = new JLabel("Image " + (currentImageIndex+1) + " of " + SolvedImageProcessor.numberImages);
		totalImagesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		filenameLabel = new JLabel("");
		filenameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		averageDistanceLabel = new JLabel("");
		averageDistanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		

		updateDrawingPanel();
		
		JButton previousButton =new JButton("Previous");
		JButton nextButton =new JButton("Next");
		JButton toggleRectangle = new JButton("Toggle Rectangle");
		calcPoints = new JButton("Calculate Algorithm Score");
		
		
		calcPoints.addActionListener(new ActionListener() {
	        
			@Override
			public void actionPerformed(ActionEvent e) {
				calculatePoints();					
			}          
	      });
		
		nextButton.addActionListener(new ActionListener() {
	        
			@Override
			public void actionPerformed(ActionEvent e) {
				if(currentImageIndex+1<SolvedImageProcessor.numberImages)
				{
					currentImageIndex++;
					updateDrawingPanel();
				}
									
			}          
	      });
		
		toggleRectangle.addActionListener(new ActionListener() {
	        
			@Override
			public void actionPerformed(ActionEvent e) {
				drawingPanel.displayRectangle = !(drawingPanel.displayRectangle);
				frame.repaint();			
			}          
	      });
		
		previousButton.addActionListener(new ActionListener() {
	        
			@Override
			public void actionPerformed(ActionEvent e) {
				if(currentImageIndex>0)
				{
					currentImageIndex--;
					updateDrawingPanel();
				}
									
			}          
	      });

		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		
		JPanel rightCenterPanel = new JPanel();
		rightCenterPanel.setLayout(new BorderLayout());
		
		JPanel rightTopPanel = new JPanel();
		rightTopPanel.setLayout(new BorderLayout());
		
		//Gives Right JPanel a margin
		Border border2 = rightPanel.getBorder();
		Border margin2 = new EmptyBorder(20,20,20,20);
		rightPanel.setBorder(new CompoundBorder(border2, margin2));
		
		Border border = rightCenterPanel.getBorder();
		Border margin = new EmptyBorder(270,10,270,10);
		rightCenterPanel.setBorder(new CompoundBorder(border, margin));
//		rightCenterPanel.setBackground(Color.RED);
//		rightCenterPanel.setOpaque(true);
		
//		rightPanel.add(test2);
		frame.getContentPane().add(rightPanel, BorderLayout.EAST);
		rightTopPanel.add(previousButton, BorderLayout.LINE_START);
		rightTopPanel.add(nextButton, BorderLayout.LINE_END);
		rightTopPanel.add(totalImagesLabel, BorderLayout.NORTH);
		rightTopPanel.add(filenameLabel, BorderLayout.AFTER_LAST_LINE);
		rightPanel.add(rightCenterPanel, BorderLayout.CENTER);
		rightCenterPanel.add(averageDistanceLabel, BorderLayout.CENTER);
		rightCenterPanel.add(calcPoints, BorderLayout.SOUTH);
		rightPanel.add(rightTopPanel, BorderLayout.NORTH);
		rightPanel.add(toggleRectangle, BorderLayout.SOUTH);
		
		//5. Show it.
		frame.setVisible(true);
	}
	
	private double getAverageDistance(Point[] calculated, Point[] actual)
	{
		Arrays.sort(calculated);
		Point temp = calculated[3];
		calculated[3] = calculated[2];
		calculated[2] = temp;
		
		Arrays.sort(actual);
		temp = actual[3];
		actual[3] = actual[2];
		actual[2] = temp;
		double total = 0.0;
		
		for(int i = 0; i<4; i++)
		{
			double xC = calculated[i].x;
			double yC = calculated[i].y;
			double xA = actual[i].x;
			double yA = actual[i].y;
			
			double distX = Math.pow(Math.abs(xC - xA), 2);
			double distY = Math.pow(Math.abs(yC - yA), 2);
			double dist = Math.sqrt(distY + distX);
			total = total + dist;
		}
		return total/4.0;
		
	}
	
	private void calculatePoints()
	{
		int pointTally = 0;
		int greenTotal = 0;
		int yellowTotal = 0;
		for(int i = 0; i<SolvedImageProcessor.numberImages; i++)
		{
			Point[] points = processor.getPoints(i);
			double avgDist = Math.floor(getAverageDistance(points, this.correctAnswers[i]) * 100) / 100;
			if(avgDist > 7 && avgDist < 13)
			{
				pointTally += 1;
				yellowTotal++;
			}
			else if(avgDist < 8)
			{
				pointTally += 3;
				greenTotal++;
			}
		}

		String status = "Your algorithm";
		if(pointTally > SolvedImageProcessor.numberImages*2)
		{
			status += " is exemplary!";
		}
		else if(pointTally > SolvedImageProcessor.numberImages*3/2)
		{
			status = "Great work!";
		}
		else if(pointTally > SolvedImageProcessor.numberImages*3/4)
		{
			status = "You're getting there!";
		}
		else
		{
			status = "Keep going!";
		}
		
		int redTotal = SolvedImageProcessor.numberImages - greenTotal - yellowTotal;
		System.out.println("Points: " + pointTally + " of " + (SolvedImageProcessor.numberImages*3));
		String message = "Score: " + pointTally + " of " + (SolvedImageProcessor.numberImages*3) + "\n" + status + "\nG: " + greenTotal + " Y: " + yellowTotal + " R: " + redTotal;
		JOptionPane.showMessageDialog(frame, message);
	}
	
	
	private void updateDrawingPanel()
	{
		totalImagesLabel.setText("Image " + (currentImageIndex+1) + " of " + SolvedImageProcessor.numberImages);
		drawingPanel.updateImage(processor.getImage(currentImageIndex));
		Point[] points = processor.getPoints(currentImageIndex);
//		getAverageDistance(points, this.correctAnswers[currentImageIndex]);
		drawingPanel.setPoints(points);
		filenameLabel.setText(processor.getFilename(currentImageIndex));
		
		double avgDist = Math.floor(getAverageDistance(points, this.correctAnswers[currentImageIndex]) * 100) / 100;
		
		averageDistanceLabel.setText("Avg. Distance: " + avgDist);
		
		if(avgDist > 12)
		{
			averageDistanceLabel.setForeground(Color.RED);
		}
		else if(avgDist > 7)
		{
			averageDistanceLabel.setForeground(Color.YELLOW);
		}
		else
		{
			averageDistanceLabel.setForeground(Color.GREEN);
		}
		averageDistanceLabel.setBackground(Color.GRAY);
		averageDistanceLabel.setOpaque(true);
		frame.repaint();

	}
	
	

}
