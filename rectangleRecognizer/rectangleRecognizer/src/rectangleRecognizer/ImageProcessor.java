package rectangleRecognizer;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class ImageProcessor {
	
	static final File dir = new File("testImages/");
	private BufferedImage[] imagesToProcess;
	public static int numberImages = 0;
	private Point[][] pointsForImages;
	private String[] fileNames;
	
	
	public ImageProcessor()
	{
		File[] fileList = ImageProcessor.dir.listFiles();
		Arrays.sort(fileList);
		if (fileList.length == 0)
		{
			System.out.println("No files in test images folder. Terminating...");
			System.exit(0);
		}
		else
		{
			System.out.println(fileList.length + " file(s) found:");
			imagesToProcess = new BufferedImage[fileList.length];
			ImageProcessor.numberImages = fileList.length;
			pointsForImages = new Point[fileList.length][4];
			this.fileNames = new String[fileList.length];
		}
		
		int counter = -1;
		
		// Load in all images
		for(File file : fileList)
		{
			counter++;
			System.out.println(file.getName());
			this.fileNames[counter] = file.getName();
			File imageFile = new File(dir.getPath() + "/" + file.getName());
			BufferedImage img = null;
			try {
			    img = ImageIO.read(imageFile);
			    imagesToProcess[counter] = img;
			    
			} catch (IOException e) {
				System.out.println("Reading images failed.");
			}
			
			
		}
		System.out.println("All image files read.");
	}
	
	public int getNumberImages()
	{
		return imagesToProcess.length;
	}
	
	public BufferedImage getImage(int i)
	{
		return imagesToProcess[i];
	}
	
	public Point[] getPoints(int index)
	{
		Point[] imgPoints = generateCorners(imagesToProcess[index]);
	    pointsForImages[index] = imgPoints;
		return pointsForImages[index];
	}
	
	public String getFilename(int index)
	{
		return this.fileNames[index];
	}
	
	
	// Complete this method
	// When run, your returned points will show up as a yellow polygon that you can toggle on and off
	private Point[] generateCorners(BufferedImage image)
	{
		Point[] rectPoints = new Point[4];		
		Color[][] imageColors = new Color[1080][720];
		
		// 'imageColors' is the color values of the current image's 1080x720 pixels (in case you ever need the full array)
		// 'pixelColor' is the current pixel's color value as the for loops increment through the image by column
		// Process those color values to locate the red rectangle in the image
		// The rectangle can be at any rotation and, for the harder image sets, exist in 3D space
		// Return the four corners as 4 'Point' objects in the 'rectPoints' array
		// For example: rectPoints[0] = new Point(180, 40);
		// Use this line of code inside an if condition to mask any pixels you aren't interested in: toProcess.setRGB(i, j, Color.green.getRGB());
		for(int i = 0; i < 1080; i++)
		{
		    for(int j = 0; j < 720; j++)
		    {
		    	int colorInt = image.getRGB(i, j);
		        imageColors[i][j] = new Color(colorInt);
		        Color pixelColor = new Color(colorInt);
		        //int red = pixelColor.getRed();
		        
		        
		    }
		}
		
		// Below is a generic return to use as a model for your own
		rectPoints[1] = new Point(40, 40);
		rectPoints[2] = new Point(140, 40);
		rectPoints[3] = new Point(140, 160);
		rectPoints[0] = new Point(40, 160);
		// End of generic return
		
		return rectPoints;
	}
	
}
